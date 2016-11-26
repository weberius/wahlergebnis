CREATE TABLE WAHLDATEN (
  id         SERIAL, 
  art        varchar(256),
  datum      timestamp,
  bundesland varchar(2),
  gemeinde   varchar(12),
  modtime    timestamp DEFAULT current_timestamp
);

CREATE TABLE STIMMBEZIRK (
  id             SERIAL, 
  nr             integer,
  wahlberechtigt integer,
  abgegeben      integer,
  gueltig        integer,
  ungueltig      integer,
  modtime        timestamp DEFAULT current_timestamp
);

CREATE TABLE ERGEBNIS (
  id      SERIAL, 
  partei  varchar(512),
  stimmen integer,
  modtime timestamp DEFAULT current_timestamp
);

CREATE TABLE ERGEBNIS2STIMMBEZIRK (
  ergebnis    integer, 
  stimmbezirk integer
);

CREATE TABLE STIMMBEZIRK2WAHLDATEN (
  stimmbezirk integer, 
  wahldaten   integer
);
