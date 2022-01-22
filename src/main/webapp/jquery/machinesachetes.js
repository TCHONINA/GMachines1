$(document).ready(function () {
	var id;
    $.ajax({
        url : "MachineController",
        data:{op:"load"},
        type: 'POST',
        success: function (data, textStatus, jqXHR) {
            remplirTable(data[1]);
            
            }
       
    });
    $("#find").click(function () {
        var dateD =$("#dated ").val();
        var dateF =$("#datef ").val();
        console.log(dateD);
        	$.ajax({
        		
        		 url: "MachineController",
                 data: {op:'searchByDates',dateD:dateD,dateF:dateF},
                 type: 'POST',
                 success: function (data, textStatus, jqXHR) {
                	 remplirTable(data[1]);

                	  $("#dated ").val('');
                      $("#datef ").val('');
                       
                 },
                 error: function (jqXHR, textStatus, errorThrown) {
                	 
                  
                 }
             });
      
    });
       
   
    function remplirTable(data) {
   	 var ligne = "";
   	 for (var i = 0; i < data.length; i++) {
          ligne += "<tr>"
       		   +"<td class='id py-3'>" + data[i].id +"</td>"
       		   +"<td class='py-3'>" + data[i].reference +"</td>"
       				 +"<td class='py-3'>" + data[i].marque +"</td>"
       		   +"<td class='py-3'>" + data[i].dateAchat +"</td>"
       		   +"<td class='py-3'>" + data[i].prix +"</td>"
       		   +"<td class='py-3'>" + data[i].salle.code +"</td>"
   	           +"</tr>";
      }
   $("#contenu").html(ligne);
   }

    


});
