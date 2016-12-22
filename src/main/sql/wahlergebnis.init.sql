DROP TABLE IF EXISTS WAHLDATEN;
CREATE TABLE WAHLDATEN (
  id         SERIAL PRIMARY KEY, 
  art        varchar(256),
  wahl       varchar(256),
  datum      timestamp,
  bundesland varchar(2),
  gemeinde   varchar(12),
  modtime    timestamp DEFAULT current_timestamp
);

DROP TABLE IF EXISTS STIMMBEZIRK;
CREATE TABLE STIMMBEZIRK (
  id             SERIAL PRIMARY KEY, 
  nr             integer,
  wahlberechtigt integer,
  abgegeben      integer,
  gueltig        integer,
  ungueltig      integer,
  modtime        timestamp DEFAULT current_timestamp
);

DROP TABLE IF EXISTS ERGEBNIS;
CREATE TABLE ERGEBNIS (
  id      SERIAL PRIMARY KEY, 
  partei  varchar(512),
  stimmen integer,
  modtime timestamp DEFAULT current_timestamp
);

DROP TABLE IF EXISTS ERGEBNIS2STIMMBEZIRK;
CREATE TABLE ERGEBNIS2STIMMBEZIRK (
  ergebnis    integer, 
  stimmbezirk integer
);

DROP TABLE IF EXISTS STIMMBEZIRK2WAHLDATEN;
CREATE TABLE STIMMBEZIRK2WAHLDATEN (
  stimmbezirk integer, 
  wahldaten   integer
);
