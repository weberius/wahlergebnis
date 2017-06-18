select 
  stimmbezirk.id, stimmbezirk.nr, stimmbezirk.wahlberechtigt, stimmbezirk.abgegeben, stimmbezirk.gueltig, stimmbezirk.ungueltig, stimmbezirk.modtime
from wahldaten
  join stimmbezirk2wahldaten on wahldaten.id = stimmbezirk2wahldaten.wahldaten
  join stimmbezirk on stimmbezirk.id = stimmbezirk2wahldaten.stimmbezirk
where 
  wahldaten.wahl = ? and
  wahldaten.art = ? and
  wahldaten.bundesland = ? and
  wahldaten.gemeinde = ? and
  wahldaten.datum = ?  and  stimmbezirk.nr  in ( ?,?,? ) 