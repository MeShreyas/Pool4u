function changePassword(){
	
	var password = document.getElementById("password").value.replace(/^(?:\s)*/g,'').replace(/(?:\s)*$/g,'');
	var cpassword = document.getElementById("cpassword").value.replace(/^(?:\s)*/g,'').replace(/(?:\s)*$/g,'');
	if(password==null || password.length==0){
			alert("Please enter the password");
			return false;
		}
		if(cpassword==null || cpassword.length==0){
			alert("Please enter the confirm password");
			return false;
		}
		if(password!=cpassword){
			alert("password does not match with confirm password ");
			return false;
		}
	var req_data = "requestId=changePassword&password="+password;
	$.ajax({
			cache : false,
			type : "POST",
			dataType : 'JSON',
			url : "/Pool4u/DispatcherServlet",
			data : req_data,
			success : function(result) {
				var parsedResponse = $.parseJSON(result);
				
				if (parsedResponse.status == "SUCCESS") {
					alert("Password changed successfully!");
				} else {
					var errorCode = parsedResponse.errorCode;
					alert(errorCode);
					
				}
			}
		
		});	
}