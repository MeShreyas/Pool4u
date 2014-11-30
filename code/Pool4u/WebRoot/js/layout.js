/**
 * 
 */

function fetchMenuBar(selectedPage) {
	$.ajax({
		url : "/Pool4u/CodeFillerServlet",
		cache : false,
		dataType : "JSON",
		type : "GET",
		data : "option=1&selectedPage="+selectedPage,
		success : function(result) {
			var parsedResponse = $.parseJSON(result);
			$("#menubar").html(parsedResponse.htmlCode);
		}
	});
}