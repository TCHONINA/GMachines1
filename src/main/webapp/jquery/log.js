
$(document).ready(function() {

	function login() {
		if ($('#username').val() == "" && $('#password').val() == "") {
			alert("remplir les champs !!");
			return false;

		} else if ($('#username').val() == "") {
			alert("remplir les champs !!");
			return false;

		} else if ($('#password').val() == "") {
			alert("remplir les champs !!");
			return false;
		}
		var sdata = {
			username: $('#username').val(),
			password: $('#password').val()
		};
		$.ajax({
			url: "LoginController",
			type: "POST",
			data: sdata,
			dataType: 'JSON',
			success: function(data) {



				if (data.status === true) {

					window.location.replace("machines.jsp");
				}
				else {
					return false;
				}
			}
		})
	}
	$("#co").click(function() {
		login();

	});
});