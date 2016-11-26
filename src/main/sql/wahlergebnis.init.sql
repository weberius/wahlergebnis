CREATE TABLE WAHLDATEN (
  id         integer, 
  art        varchar(256),
  datum      timestamp,
  bundesland varchar(2),
  gemeinde   varchar(12),
  modtime    timestamp DEFAULT current_timestamp
);

CREATE TABLE STIMMBEZIRK (
  id             integer, 
  nr             integer,
  wahlberechtigt integer,
  abgegeben      integer,
  gueltig        integer,
  ungueltig      integer,
  modtime        timestamp DEFAULT current_timestamp
);

CREATE TABLE ERGEBNIS (
  id      integer, 
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
