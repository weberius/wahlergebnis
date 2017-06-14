select 
  ergebnis.id, ergebnis.partei, ergebnis.stimmen, ergebnis.modtime
from wahldaten
  join stimmbezirk2wahldaten on wahldaten.id = stimmbezirk2wahldaten.wahldaten
  join stimmbezirk on stimmbezirk.id = stimmbezirk2wahldaten.stimmbezirk
  join ergebnis2stimmbezirk on stimmbezirk.id = ergebnis2stimmbezirk.stimmbezirk
  join ergebnis on ergebnis.id = ergebnis2stimmbezirk.ergebnis
where 
--  wahldaten.datum = to_timestamp('2012-05-13', 'YYYY-MM-DD') and
  wahldaten.wahl = ? and
  wahldaten.art = ? and
  wahldaten.bundesland = ? and
  wahldaten.gemeinde = ? and
  wahldaten.datum = ? and
  stimmbezirk.nr = ?;