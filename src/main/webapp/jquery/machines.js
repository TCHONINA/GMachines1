$(document).ready(function() {
	var id;
	$.ajax({
		url: "MachineController",
		data: { op: "load" },
		type: 'POST',
		success: function(data, textStatus, jqXHR) {
			remplir(data[0]);
			remplirTable(data[1]);

		}

	});
	$("#ajouter").click(function() {
		var reference = $("#ref").val();
		var dateAchat = $("#date").val();
		var prix = $("#prix").val();
		var salle = $("#salle ").val();
		var marque = $("#marque").val();
		console.log(marque);
		if (id == null) {
			$.ajax({

				url: "MachineController",
				data: {reference: reference, dateAchat: dateAchat, prix: prix, salle: salle, marque: marque },
				type: 'POST',
				success: function(data, textStatus, jqXHR) {
					if (data[0]) alert("Cette Reference existe deja")
					else {
						remplirTable(data[1]);
						$("#ref").val('');
						$("#date").val('');
						$("#prix").val('');
					}
				},
				error: function(jqXHR, textStatus, errorThrown) {

					console.log(textStatus);

				}
			});
		}

		else {
			$.ajax({
				url: "MachineController",
				data: { id: id, reference: reference, dateAchat: dateAchat, prix: prix, salle: salle, marque: marque },
				type: 'POST',
				success: function(data, textStatus, jqXHR) {
					console.log(data);
					remplirTable(data);
					$("#ref").val('');
					$("#date").val('');
					$("#prix").val('');
					$("#add").text("Ajouter");
				},
				error: function(jqXHR, textStatus, errorThrown) {
					console.log(textStatus);
				}
			});
		}

	});


	$("#contenu ").on("click", ".delete", function() {
		var idS = $(this).closest("tr").find(' td:eq(0)').text();
		$.ajax({
			url: "MachineController",
			data: { op: "delete", id: idS },
			type: 'POST',
			success: function(data, textStatus, jqXHR) {
				remplirTable(data);
			},
			error: function(jqXHR, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});

	});

	$("#contenu ").on("click", ".update", function() {
		id = $(this).closest("tr").find(' td:eq(0)').text();
		var ref = $(this).closest("tr").find(' td:eq(1)').text();
		var date = $(this).closest("tr").find(' td:eq(2)').text();
		var prix = $(this).closest("tr").find(' td:eq(3)').text();
		$("#reference").val(ref);
		$("#date").val(formatDate(date));
		$("#prix").val(prix);
		$("#add").text("Modifier");
	});

	function formatDate(date) {
		var d = new Date(date),
			month = '' + (d.getMonth() + 1),
			day = '' + d.getDate(),
			year = d.getFullYear();

		if (month.length < 2)
			month = '0' + month;
		if (day.length < 2)
			day = '0' + day;

		return [year, month, day].join('-');
	}



	function remplir(data) {

		var ligne = "";
		for (var i = 0; i < data.length; i++) {
			ligne += "<option value= '" + data[i].id + "'>" + data[i].code + "</option>";
		}
		$("#salle").html(ligne);
	}
	function remplirTable(data) {
		console.log(data);
		var ligne = "";
		for (var i = 0; i < data.length; i++) {
			ligne += "<tr>"
				+ "<td>" + data[i].id + "</td>"
				+ "<td>" + data[i].reference + "</td>"
				+ "<td>" + data[i].marque + "</td>"
				+ "<td>" + data[i].dateAchat + "</td>"
				+ "<td>" + data[i].prix + "</td>"
				+ "<td>" + data[i].salle.code + "</td>"
				+ '<td>'
				+ '<div class="position-relative">'
				+ '<button  class="update link-dark d-inline-block border-0 bg-transparent" >'
				+ '<i class="gd-pencil icon-text"></i>'
				+ '</button>'
				+ '<button  class="delete link-dark d-inline-block border-0 bg-transparent">'
				+ '<i class="gd-trash icon-text"></i>'
				+ '</button>'
				+ '</div> </td>'
				+ "</tr>";
		}
		$("#contenu").html(ligne);
	}

	$("div #search").click(function() {

		var value = $("#searchtxt").val().toLowerCase();
		console.log(value)
		$("table tr").filter(function() {
			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		});
	});


});