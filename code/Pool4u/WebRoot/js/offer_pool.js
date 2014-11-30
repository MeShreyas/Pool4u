function load_init_data() {

	var req_data = "requestId=FetchPoolData";
	$.ajax({
				url : "/Pool4u/DispatcherServlet",
				cache : false,
				dataType : "JSON",
				type : "POST",
				data : req_data,
				success : function(result) {
					var parsedResponse = $.parseJSON(result);
					if (parsedResponse.status == "SUCCESS") {
						var htmlContent = "<div class=\"row\">";
						var filterList = parsedResponse.poolFilters;
						for ( var i = 0; i < filterList.length; i++) {
							htmlContent = htmlContent
									+ "<div class=\"span3\">" +
											"<input type=\"checkbox\" name=\"poolFilter\" " +
											"id="+filterList[i].filterDesc+"\ value=\""+filterList[i].filterDesc+"\"/>" +
											"<span>"+filterList[i].filterDesc+"</span></div>";
							
						}


						htmlContent = htmlContent + "</div>";
						$("#poolFilters").html(htmlContent);
						$("#poolFilters-label").html("Filters");
					} else {

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

						}
					}
				}
			});
}

function offerARide() {

	var req_data = "requestId=OfferARide";

	var from = $("#from").val();
	var to = $("#to").val();
	req_data = req_data + "&from=" + from + "&to=" + to;
	var fromLatLng = $("#from-hidden").val();
	var toLatLng = $("#to-hidden").val();

	req_data = req_data + "&fromLatLng=" + fromLatLng + "&toLatLng=" + toLatLng;
	var seatsAvbl = $("#seatsAvbl").val().replace(/^(?:\s)*/g, '').replace(/(?:\s)*$/g, '');
	
	if (seatsAvbl != null && seatsAvbl.length > 0) {
		req_data = req_data + "&seatsAvbl=" + seatsAvbl;
	}

	var isReturn;
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

	if ($('#return-chkbox').is(':checked')) {
		return_date = $("#return").value.replace(
				/^(?:\s)*/g, '').replace(/(?:\s)*$/g, '');
		if (return_date == null || return_date.length == 0) {
			alert("Please enter the departure date");
			return false;
		}

		req_data = req_data + "&return_date=" + return_date;

		return_time = $("#return-time").val().replace(/^(?:\s)*/g, '').replace(/(?:\s)*$/g, '');
		req_data = req_data + "&return_time=" + return_time;
	}

	var contribution = $("#contribution").val().replace(/^(?:\s)*/g, '').replace(/(?:\s)*$/g, '');

	if (contribution == null || contribution.length == 0) {
		contribution = 0;
	}
	
	req_data = req_data + "&contribution=" + contribution;

	var isSmoking = $('#smoking').is(':checked');

	req_data = req_data + "&isSmoking=" + isSmoking;
	
	if ($("#womenOnly") != null) {
		var isWomenOnly = $("#womenOnly").is(':checked');
		req_data = req_data + "&isWomenOnly=" + isWomenOnly;
	}
	
	var isAuthorized = $('#autoApprove').is(':checked');

	req_data = req_data + "&isAuthorized=" + isAuthorized;
	
	
//	var isAuthorized = document.getElementById("isAuthorized").checked;
//	req_data = req_data + "&isAuthorized=" + isAuthorized;
//	var rideDescription = document.getElementById("rideDescription").value
//			.replace(/^(?:\s)*/g, '').replace(/(?:\s)*$/g, '');
//	if (rideDescription != null && rideDescription.length > 0) {
//		req_data = req_data + "&rideDescription=" + rideDescription;
//	}
	alert("req_data : " + req_data);
	$.ajax({
		url : "/Pool4u/DispatcherServlet",
		cache : false,
		dataType : "JSON",
		type : "POST",
		data : req_data,
		success : function(result) {
			var parsedResponse = $.parseJSON(result);
			if (parsedResponse.status == "SUCCESS") {
				alert("done");
			} else {
				var errorCode = parsedResponse.errorCode;
				alert(errorCode);
				// UserName does not exists. Show register screen
				if (errorCode == 1005) {
					// $("#login_div #error_message").html("User does not exist.
					// Please Register<br/>");
				}
				// Password is wrong.
				else if (errorCode == 1004) {
					// $("#login_div #error_message").html("Invalid
					// Password<br/>");
				}
				// User not verified
				else if (errorCode == 1006) {

					// $("#authorization_box_login
					// #login_auth_error").html("User
					// not confirmed. Enter confirmation code below<br/>");
				} else if (errorCode == 1007) {
					// $("#login_div #error_message").html("User not active.
					// Please
					// contact administrator at contact@pool4u.in to activate
					// your
					// account!<br/>");

				}
			}
		}
	});
}