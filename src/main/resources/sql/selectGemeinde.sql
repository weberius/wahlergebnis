select 
  sum(stimmbezirk.wahlberechtigt)as wahlberechtigt, sum(stimmbezirk.abgegeben) as abgegeben, sum(stimmbezirk.gueltig) as gueltig, sum(stimmbezirk.ungueltig) as ungueltig, max(stimmbezirk.modtime) as modtime
from wahldaten
  join stimmbezirk2wahldaten on wahldaten.id = stimmbezirk2wahldaten.wahldaten
  join stimmbezirk on stimmbezirk.id = stimmbezirk2wahldaten.stimmbezirk
where 
  wahldaten.wahl = ? and
  wahldaten.art = ? and
  wahldaten.bundesland = ? and
  wahldaten.gemeinde = ? and
  wahldaten.datum = ?
