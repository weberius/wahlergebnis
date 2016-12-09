select 
  *
  --stimmbezirk.wahlberechtigt, stimmbezirk.abgegeben, stimmbezirk.gueltig, stimmbezirk.ungueltig, ergebnis.partei, ergebnis.stimmen
from wahldaten
  join stimmbezirk2wahldaten on wahldaten.id = stimmbezirk2wahldaten.wahldaten
  join stimmbezirk on stimmbezirk.id = stimmbezirk2wahldaten.stimmbezirk
  join ergebnis2stimmbezirk on stimmbezirk.id = ergebnis2stimmbezirk.stimmbezirk
  join ergebnis on ergebnis.id = ergebnis2stimmbezirk.ergebnis
where 
  wahldaten.datum = to_timestamp('2012-05-13', 'YYYY-MM-DD') and
  wahldaten.bundesland = '05' and
  wahldaten.gemeinde = '05315000' and
  stimmbezirk.nr = 10101;