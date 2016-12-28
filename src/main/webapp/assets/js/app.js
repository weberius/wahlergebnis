	var table = $('#wahldaten').DataTable({
		"ajax" : "/wahlergebnis/service/wahldaten?datatables",
		"columns" : [ {
			"data" : "art"
		}, {
			"data" : "wahl"
		}, {
			"data" : "bundesland"
		}, {
			"data" : "gemeinde"
		}, {
			"data" : "datum"
		} ]
	});
