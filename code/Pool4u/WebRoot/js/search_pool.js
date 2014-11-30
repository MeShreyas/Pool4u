function searchARide(){
	var req_data = "requestId=searchARide";
	
	var fromLatLng = document.getElementById("map_from_search_latlng").value;
	var toLatLng = document.getElementById("map_to_search_latlng").value;
	
	req_data = req_data+ "&fromLatLng="+fromLatLng+"&toLatLng="+toLatLng;
	
	var fromPlace = document.getElementById("from").value.replace(/^(?:\s)*/g,'').replace(/(?:\s)*$/g,'');
	if(fromPlace==null || fromPlace.length==0){
		alert("Please enter start point of a ride");
		return false;
	}
	else{
		req_data = req_data + "&fromPlace="+fromPlace;
	}
	
	var toPlace = document.getElementById("to").value.replace(/^(?:\s)*/g,'').replace(/(?:\s)*$/g,'');
	if(toPlace==null || toPlace.length==0){
		alert("Please enter destination point of a ride");
		return false;
	}
	else{
		req_data = req_data + "&toPlace="+toPlace;
	}
	var selectionFrequecy = document.search_ride.frequency;
	var frequency = "";
	var isSelected = false;
	for (var i=0; i<selectionFrequecy.length; i++){
		if (selectionFrequecy[i].checked == true){
		  	frequency = selectionFrequecy[i].value; 
		  }
	}
	alert("frequency : "+frequency);
	req_data = req_data + "&frequency="+frequency;
	
	var depart_date = document.getElementById("depart_date").value.replace(/^(?:\s)*/g,'').replace(/(?:\s)*$/g,'');
	if(depart_date==null || depart_date.length==0){
		alert("Please enter the departure date");
		return false;
	}
	else{
		f_tcalParseDate (depart_date);
		req_data = req_data + "&depart_date="+depart_date;
	}
	var depart_time = document.getElementById("depart_time").value.replace(/^(?:\s)*/g,'').replace(/(?:\s)*$/g,'');
	req_data = req_data + "&depart_time="+depart_time;
	
	var advancedOrBasic = document.getElementById("advancedOrBasicCheck").value;
	if(advancedOrBasic=="advanced"){
	var isSmoking = document.getElementById("poolFilter_item_1").checked;
	req_data = req_data + "&isSmoking="+isSmoking;
	// check if women only check box is available (it is available only for women users)
	if(document.getElementById("poolFilter_item_2")!=null){
		var isWomenOnly = document.getElementById("poolFilter_item_2").checked;
		req_data = req_data + "&isWomenOnly="+isWomenOnly;
	}
	var isAuthorized = document.getElementById("isAuthorized").checked;
	req_data = req_data + "&isAuthorized="+isAuthorized;
	}
	$.ajax({
			url : "/Pool4u/DispatcherServlet",
			cache : false,
			dataType : "JSON",
			type : "POST",
			data : req_data,
			success : function(result) {
				var parsedResponse = $.parseJSON(result);
				if (parsedResponse.status == "SUCCESS"){
					// show the result on screen
				} else {
					// show message that there are no carpools matching criteria					
				}
			}
	
	});
}