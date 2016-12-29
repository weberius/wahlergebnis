#Wahlergebnis

Dieser Ergebnis stellt Wahlergebnisse zur Verfügung

#Status

Der Service befindet sich in der Entwicklung

#Service

## /wahlergebnis/service/ping

Diese Schnittstelle wird verwendet um zu prüfen, ob der Service selbst erreichbar ist.

## /wahlergebnis/service/landtagswahl/{land}/{gemeinde}/{datum}/{stimmart}/{nr}

Die Schnittstelle ruft die Landtagswahlergebnisse für die Landtagswahl 2012 in NRW für die Stadt Köln ab (Gemeindeschlüssel: 05315000) 

Beispiel: <a href="https://tom.cologne.codefor.de/wahlergebnis/service/landtagswahl/05/05315000/2012-05-13/10101/erststimmen">/wahlergebnis/service/landtagswahl/05/05315000/2012-05-13/10101/erststimmen</a>

## /wahlergebnis/service/wahldaten

Die Schnittstelle ruft alle Einträge für Wahldaten auf.

Beispiel: <a href="https://tom.cologne.codefor.de/wahlergebnis/service/wahldaten">/wahlergebnis/service/wahldaten</a>

Für eine Übersicht über die zur Verfügung stehenden Wahldaten steht eine Oberfläche zur Verfügung: <a href="https://tom.cologne.codefor.de/wahlergebnis">Wahldaten im System</a>

## /load

Daten werden in Form eines json übergeben. Hierin finden sich alle Informationen, um sie 
zuordnen zu können. Beim upload wird eine Integritätsprüfung vorgenommen. Ein Beispiel Datensatz findet sich hier: [data.json](https://raw.githubusercontent.com/weberius/wahlergebnis/master/src/test/resources/data.json)

# Datenbank

## DB User auf Postgres einrichten

    sudo -u postgres createuser -P wahlergebnis
    
## Datenbank wahlergebnis anlegen

    sudo -u postgres createdb -O wahlergebnis wahlergebnis

## Tabelle

### WAHLDATEN

Die Tabelle WAHLDATEN enthält allgemeinen Informationen zur Wahl. Die Spalte 'ID' enthält einen intern verwendeten Schlüssel. Die Spalten 'BUNDESLAND' und 'GEMEINDE' enthalten die Schlüsselwerte. Da die Informationen mit führenden Nullen vorliegen (z.B. Gemeindeschlüssel Köln: '05315000') sind diese Spalten als varchar defniert. Das Bundesland ergibt sich prinzipiell aus dem Gemeindeschlüssel (z.B. Köln: '05' steht für das Land Nordrhein-Westfalen). Da z.B. Landtagswahlen sich an Bundesländern orientieren, wird dieser Schlüssel explizit gesetzt. Auch hier werden führende Nullen per varchar abgebildet.

Vergleiche auch [wikipedia: Amtlicher Gemeindeschlüssel](https://de.wikipedia.org/wiki/Amtlicher_Gemeindeschl%C3%BCssel)

| Spalte | Typ | Beschreibung |
| ------ | --- | ------------ |
| id | integer | interner Schlüssel für Relation |
| art | varchar(256) | Die Art der Stimmen, z.B. 'erststimmen', 'zweistimmen' |
| wahl | varchar(256) | Die Art der Wahl, z.B. 'landtagswahl', 'bundestagswahl' |
| datum | timestamp | Zeitpunkt der Wahl; typischerweise ein Datum, z.B. 11.11.206 |
| bundesland | varchar(2) | Schlüsselwert für das Bundesland, z.B. 05 für Nordrhein-Westfalen |
| gemeinde | varchar(12) | Schlüsselwert für die Gemeinde, z.B. 05315000 für Köln |
| modtime | timestamp DEFAULT current_timestamp | Zeitpunkt der Erstellung des Datensatzes |

    CREATE TABLE WAHLDATEN (
      id         integer, 
      art        varchar(256),
      wahl       varchar(256),
      datum      timestamp,
      bundesland varchar(2),
      gemeinde   varchar(12),
      modtime    timestamp DEFAULT current_timestamp
    );

### STIMMBEZIRK

In der Tabelle STIMMBEZIRK finden sich die Informationen, die zum Stimmbezirk gehören. Die Spalte 'ID' enthält einen intern verwendeten Schlüssel. Die Spalte 'NR' enthält den Schlüssel, an dem der Stimmbezirk fachlich identifiziert werden kann. 

| Spalte | Typ | Beschreibung |
| ------ | --- | ------------ |
| id | integer | interner Schlüssel für Relation |
| nr | integer | Schlüssel, dan dem der Stimmbezirk fachlich identifiziert werden kann |
| wahlberechtigt | integer | Anzahl der Wahlberechtigten im Stimmbezirk |
| abgegeben | integer | Anzahl der abgegebenden Stimmen |
| gueltig | integer | Anzahl der gültigen Stimmen |
| ungueltig | integer | Anzahl der ungültigen Stimmen | 
| modtime | timestamp DEFAULT current_timestamp | Zeitpunkt der Erstellung des Datensatzes |

    CREATE TABLE STIMMBEZIRK (
      id             integer, 
      nr             integer,
      wahlberechtigt integer,
      abgegeben      integer,
      gueltig        integer,
      ungueltig      integer,
      modtime        timestamp DEFAULT current_timestamp
    );

### ERGEBNIS

| Spalte | Typ | Beschreibung |
| ------ | --- | ------------ |
| id | integer | interner Schlüssel für Relation |
| partei | varchar(512) | Name der Partei |
| stimmen | integer | Anzahl der Stimmen, die die Partei für sich gewinnen konnte |
| modtime | timestamp DEFAULT current_timestamp | Zeitpunkt der Erstellung des Datensatzes |

    CREATE TABLE ERGEBNIS (
      id      integer, 
      partei  varchar(512),
      stimmen integer,
      modtime timestamp DEFAULT current_timestamp
    );

### ERGEBNIS2STIMMBEZIRK

| Spalte | Typ | Beschreibung |
| ------ | --- | ------------ |
| ergebnis | integer | id des ergebnisses |
| stimmbezirk | integer | id des stimmbezirks |

    CREATE TABLE ERGEBNIS2STIMMBEZIRK (
      ergebnis    integer, 
      stimmbezirk integer
    );

### STIMMBEZIRK2WAHLDATEN

| Spalte | Typ | Beschreibung |
| ------ | --- | ------------ |
| stimmbezirk | integer | id des stimmbezirk |
| wahldaten | integer | id der wahldaten |

    CREATE TABLE STIMMBEZIRK2WAHLDATEN (
      stimmbezirk integer, 
      wahldaten   integer
    );

## DB-Tabellen initial einrichten

    psql -h localhost -U wahlergebnis -d wahlergebnis -a -f src/main/sql/wahlergebnis.init.sql

## Verbindungsparameter

Die Datenbankverbindungsparameter werden per JNDI zur Verfügung gestellt. Dies bedeutet, dass sie im Container definiert sein müssen. Für den Online-Betrieb mit
Tomcat sind folgende Parameter zu setzen:

context.xml

    <Context>
        <ResourceLink 
             name="jdbc/wahlergebnis" 
             global="jdbc/wahlergebnis"
             type="javax.sql.DataSource" />
    </Context> 

server.xml

    <GlobalNamingResources>
        <Resource 
            name="jdbc/wahlergebnis"
            auth="Container"
            driverClassName="org.postgresql.Driver"
            maxTotal="25" 
            maxIdle="10"
            username="username"
            password="password"
            type="javax.sql.DataSource"
            url="jdbc:postgresql://localhost:5432/wahlergebnis"
            validationQuery="select 1"/>

Zu Testzwecken muss die Datei _src/test/resources/jndi.properties.template_ in _jndi.properties_ umbenannt und die Verbindungsparameter angepasst werden.

# Test

## Tests mit Datenbank

Da zur Zeit keine Integration Test Stage zur Verfügung steht, sind alle Tests, die eine Datenbank voraussetzt als main codiert. Um eine Datenbankverbindung hierfür zur Verfügung stellen zu können, muss die Datei src/test/resources/jndi.properties.template in src/test/resources/jndi.properties kopiert und die entsprechenden Parameter zur Datenbank gesetzt werden.

Beim ersten auschecken und mit Aufruf von 'mvn clean install' wird die Datei jndi.properties.template in jndi.properties kopiert. Vorausgesetzt die Datenbank ist über localhost erreichbar und kennt das Schema 'wahlergebnis' bzw. den User 'wahlergebnis/wahlergebnis' kann direkt mit der Datenbank verbunden werden.

# Installation

1. git clone git@github.com:weberius/wahlergebnis.git
2. mvn clean install
3. mvn jetty:run

# License

<a rel="license" href="http://creativecommons.org/licenses/by-sa/4.0/"><img alt="Creative Commons Lizenzvertrag" style="border-width:0" src="https://i.creativecommons.org/l/by-sa/4.0/88x31.png" /></a><br />Dieses Werk ist lizenziert unter einer <a rel="license" href="http://creativecommons.org/licenses/by-sa/4.0/">Creative Commons Namensnennung - Weitergabe unter gleichen Bedingungen 4.0 International Lizenz</a>.
