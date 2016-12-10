select 
  stimmbezirk.id, stimmbezirk.nr, stimmbezirk.wahlberechtigt, stimmbezirk.abgegeben, stimmbezirk.gueltig, stimmbezirk.ungueltig, stimmbezirk.modtime
from wahldaten
  join stimmbezirk2wahldaten on wahldaten.id = stimmbezirk2wahldaten.wahldaten
  join stimmbezirk on stimmbezirk.id = stimmbezirk2wahldaten.stimmbezirk
  join ergebnis2stimmbezirk on stimmbezirk.id = ergebnis2stimmbezirk.stimmbezirk
  join ergebnis on ergebnis.id = ergebnis2stimmbezirk.ergebnis
where 
  wahldaten.datum = ? and
  wahldaten.bundesland = ? and
  wahldaten.gemeinde = ? and
  stimmbezirk.nr = ?;