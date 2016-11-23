#Wahlergebnis

Dieser Ergebnis stellt Wahlergebnisse zur Verfügung

#Status

Der Service befindet sich in der Entwicklung

#Service

## /wahlergebnis/service/ping

Diese Schnittstelle wird verwendet um zu prüfen, ob der Service selbst erreichbar ist.

## /wahlergebnis/service/landtagswahl

Dieser Service liefert alle Landtagswahlergebnisse zurück.

## /wahlergebnis/service/landtagswahl/nrw

Dieser Service liefert alle Landtagswahlergebnisse für NRW zurück.

## /wahlergebnis/service/landtagswahl/nrw/koeln

Dieser Service liefert alle Landtagswahlergebnisse für NRW in Köln zurück.

## /wahlergebnis/service/landtagswahl/nrw/koeln/2012

Die Schnittstelle ruft die Landtagswahlergebnisse für die Landtagswahl 2012 in NRW für die Stadt Köln ab (Gemeindeschlüssel: 05315000) 

Beispiel: <a href="">/wahlergebnis/landtagswahl/05315000/2012</a>

## /wahlergebnis/service/landtagswahl/nrw/koeln/2012?[<Liste Stimmbezirke>]

Die Schnittstelle ruft die Landtagswahlergebnisse für die Landtagswahl 2012 in NRW für die Stadt Köln ab (Gemeindeschlüssel: 05315000). Es werden nur die Ergebnisse für die Stimmbezirke 10101, 10102, 10103 geliefert.

Beispiel: <a href="">/wahlergebnis/landtagswahl/05315000/2012?10101,10102,10103</a>

## /load

Daten werden in Form eines json übergeben. Hierin finden sich alle Informationen, um sie 
zuordnen zu können. Beim upload wird eine Integritätsprüfung vorgenommen. Ein Beispiel Datensatz findet sich hier: [data.json](https://raw.githubusercontent.com/weberius/wahlergebnis/master/src/test/resources/data.json)

# Datenbank

## DB User auf Postgres einrichten

    sudo -u postgres createuser -P wahlergebnis
    
## Datenbank wahlergebnis anlegen

    sudo -u postgres createdb -O wahlergebnis wahlergebnis

## Tabelle

### ART

Die Tabelle ART enthält Informationen zur Art der Wahl. Mögliche Werte:

| Wert | Wahl |
| ---- | ---- |
| 0 | Europawahl |
| 1 | Bundestagswahl |
| 2 | Landtagswahl |
| 3 | Kommunalwahl |

    CREATE TABLE ART (
      id      integer, 
      name    varchar(256),
      modtime timestamp DEFAULT current_timestamp
    );

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
