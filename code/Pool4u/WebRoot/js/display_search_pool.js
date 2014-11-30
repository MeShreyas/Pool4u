function show_searchResults() {
	
	var req_data = "requestId=searchARide";


	var fromLatLng = $("#from-hidden").val();
	var toLatLng = $("#to-hidden").val();

	req_data = req_data + "&fromLatLng=" + fromLatLng + "&toLatLng=" + toLatLng;

	var depart_date = $("#when").val().replace(/^(?:\s)*/g, '').replace(/(?:\s)*$/g, '');
	
	if (depart_date == null || depart_date.length == 0) {
		alert("Please enter the departure date");
		return false;
	}

	req_data = req_data + "&depart_date=" + depart_date;

	var depart_time = $("#when-time").val().replace(/^(?:\s)*/g, '').replace(/(?:\s)*$/g, '');
	
	req_data = req_data + "&depart_time=" + depart_time;
	isReturn = $('#return-chkbox').is(':checked');
	req_data = req_data + "&isReturn=" + isReturn;
	var isSmoking = $('#smoking').is(':checked');

	req_data = req_data + "&isSmoking=" + isSmoking;

	if ($("#womenOnly") != null) {
		var isWomenOnly = $("#womenOnly").is(':checked');
		req_data = req_data + "&isWomenOnly=" + isWomenOnly;
	}
	var isAuthorized = document.getElementById("autoApprove").checked;
	req_data = req_data + "&isAuthorized="+isAuthorized;
	var htmlContent = '<div class="page-header"><h1>Search a Ride <small>Select your ride</small></h1></div>'
		+ '<div class="container"><div class="alert-message success">Search Results</div>';
	alert("req data : "+req_data)
	$.ajax({
				url : "/Pool4u/DispatcherServlet",
				cache : false,
				dataType : "JSON",
				type : "POST",
				data : req_data,
				success : function(result) {
					var parsedResponse = $.parseJSON(result);
					if (parsedResponse.status == "SUCCESS") {


						var poolDetails = parsedResponse.pools;
						

						for ( var i = 0; i < poolDetails.length; i++) {
							
							var title = poolDetails[i].pT.split('|');
							
							htmlContent += '<div class="well"><div class="row"><div class="span10">'
									+ '<strong class="custom">'+title[0]+ '</strong>'
									+ '<em class="custom-sep">-</em>'
									+ '<strong class="custom">'+ title[1]+ '</strong>'
									+ '<hr class="custom">'
									+ '</div>'
									+ '<div class="span2">'
									+ '<p class="label pull-left warning"><font style="font-size:15px;">'
									+ poolDetails[i].cTB
									+ ' </font>Rs. / Seat</p>'
									+ '</div>'
									+ '<div class="span2">'
									+ '<p class="label success pull-left">&nbsp;<font style="font-size:15px;">'
									+ poolDetails[i].sts
									+ '</font> Seats Left</p>'
									+ '</div>'
									+ '</div>'
									+ '<div class="row">'
									+ '<div class="span14">'
									+ '<div class="row">'
									+ '<div class="span10">'
									+ '<div class="row">'
									+ '<div class="span10">Departs : '
									+ poolDetails[i].sTm+
							'</div>';
							
							if (poolDetails[i].rtm != null) {
								htmlContent += '<div class="span6">'+poolDetails[i].rtm+'</div>';
							}
							
							htmlContent += '</div>'
									+ '</div>'
									+ '<div class="span2">'
									+ '<a class="btn" type="button">More Info</a>'
									+ '</div>' + '</div>' + '</div>' + '</div>'
									+ '<hr class="custom">'
									+ '<div class="row">';
							
							var features = '';
							if (poolDetails[i].wmn == "Y") {
								features += 'Women Only,';
							}

							if (poolDetails[i].smkng == "Y") {
								features += 'Smoking';
							} else {
								features += 'Non Smoking';
							}

							htmlContent += '<div class="span5">Features : '
									+ features + '</div>'
									+ '<div class="span5">Posted by: '
									+ poolDetails[i].uN + '</div>'
									+ '<div class="span4">Working For : '
									+ poolDetails[i].cN + '</div>' + '</div>'
									+ '</div>' + '</div>';
						}
						htmlContent+= '<div class="pagination pull-right">'+
							+ '<ul>'
							+ '<li class="active"><a href="#">1</a>'
							+ '</li>' + '<li><a href="#">2</a>'
							+ '</li>' + '<li><a href="#">3</a>'
							+ '</li>' + '<li><a href="#">4</a>'
							+ '</li>' + '<li><a href="#">5</a>'
							+ '</li>' + '</ul>' + '</div>' ;
					}
					else {

						var errorCode = parsedResponse.errorCode;
						alert(errorCode);
						// UserName does not exists. Show register screen
						if (errorCode == 1005) {
							// $("#login_div #error_message").html("User does
							// not exist. Please Register<br/>");
						}
						// Password is wrong.
						else if (errorCode == 1004) {
							// $("#login_div #error_message").html("Invalid
							// Password<br/>");
						}
						// User not verified
						else if (errorCode == 1006) {

							// $("#authorization_box_login
							// #login_auth_error").html("User not confirmed.
							// Enter confirmation code below<br/>");
						} else if (errorCode == 1007) {
							// $("#login_div #error_message").html("User not
							// active. Please contact administrator at
							// contact@pool4u.in to activate your
							// account!<br/>");

						} else if (errorCode = 3001) {
							htmlContent+='<div class="hero-unit"><h1>No Pools Found</h1></div> ';
						}
					}
					
					$("#sp_search_sec").hide();
					$("#sp_result_sec").html(htmlContent);
					$("#sp_result_sec").show();
				}
			});
}

