select 
  count(ergebnis.id) as id, ergebnis.partei, sum(ergebnis.stimmen) as stimmen, max(ergebnis.modtime) as modtime
from wahldaten
  join stimmbezirk2wahldaten on wahldaten.id = stimmbezirk2wahldaten.wahldaten
  join stimmbezirk on stimmbezirk.id = stimmbezirk2wahldaten.stimmbezirk
  join ergebnis2stimmbezirk on stimmbezirk.id = ergebnis2stimmbezirk.stimmbezirk
  join ergebnis on ergebnis.id = ergebnis2stimmbezirk.ergebnis
where 
  wahldaten.wahl = ? and
  wahldaten.art = ? and
  wahldaten.bundesland = ? and
  wahldaten.gemeinde = ? and
  wahldaten.datum = ?
group by 
  ergebnis.partei