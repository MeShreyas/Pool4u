/**
 * 
 */

	
	// Function to show authorization box
	function show_auth(caller){
		alert("hiding");
		$(":button#"+caller).hide();
		//$("#"+caller+"_div :input").attr('disabled',true);
		$("#"+caller+"_base").hide();
		$("#authorization_box_"+caller).show();
		$("#authorization_box_"+caller+" :input").attr('disabled',false);
	}
	

	// Function to do user login
	function user_login(){
		
		var userName = document.getElementById("loginName").value;
		
		var password = document.getElementById("loginPassword").value;
		if(userName==null || userName.length==0){
			alert("Please enter the user name");
			return false;
		}
		if(password==null || password.length==0){
			alert("Please enter the password");
			return false;
			
		}
		var req_data = "requestId=Login&userName="+userName+"&password="+password;
		$.ajax({
			url : "/Pool4u/DispatcherServlet",
			cache : false,
			dataType : "JSON",
			type : "POST",
			data : req_data,
			success : function(result) {
				var parsedResponse = $.parseJSON(result);
				if (parsedResponse.status == "SUCCESS") {
					window.location = parsedResponse.url;
				} else {
					var errorCode = parsedResponse.errorCode;
					// UserName does not exists. Show register screen
					if (errorCode == 1005) {
						$("#login_div #error_message").html("User does not exist. Please Register<br/>");
					}
					// Password is wrong. 
					else if (errorCode == 1004) {
						$("#login_div #error_message").html("Invalid Password<br/>");
					}
					// User not verified
					else if (errorCode == 1006) {
						
						show_auth("login");
						$("#authorization_box_login #login_auth_error").html("User not confirmed. Enter confirmation code below<br/>");
					}
					else if (errorCode == 1007) {
						$("#login_div #error_message").html("User not active. Please contact administrator at contact@pool4u.in to activate your account!<br/>");
						
					}
				}
			}
		});
	}
	
	// Function for user registration
	function user_register() {
		
		var userName = document.getElementById("userName").value.replace(/^(?:\s)*/g,'').replace(/(?:\s)*$/g,'');
		
		var password = document.getElementById("password").value.replace(/^(?:\s)*/g,'').replace(/(?:\s)*$/g,'');
		var cpassword = document.getElementById("cpassword").value.replace(/^(?:\s)*/g,'').replace(/(?:\s)*$/g,'');
		var company = document.getElementById("company").value;
		var displayName = document.getElementById("dName").value.replace(/^(?:\s)*/g,'').replace(/(?:\s)*$/g,'');
		var firstName = document.getElementById("firstName").value.replace(/^(?:\s)*/g,'').replace(/(?:\s)*$/g,'');
		
		var selectionGender = document.register.gender;
		var gender = "";
		var isSelected = false;
		for (var i=0; i<selectionGender.length; i++){
			if (selectionGender[i].checked == true){
			  	gender = selectionGender[i].value; 
			  }
		}
		
		
		var role = document.getElementById("role").value;
		
		if(userName==null || userName.length==0){
			alert("Please enter the user name");
			return false;
		}
		if(password==null || password.length==0){
			alert("Please enter the password");
			return false;
		}
		if(cpassword==null || cpassword.length==0){
			alert("Please enter the confirm password");
			return false;
		}	
		if(firstName==null || firstName.length==0){
			alert("Please enter the first name");
			return false;
		}
		if(displayName==null || displayName.length==0){
			alert("Please enter the display name");
			return false;
		}
		if(password!=cpassword){
			alert("password does not match with confirm password ");
			return false;
		}
		
		var req_data = "requestId=UserSignUp&userName="+userName+"&password="+password+"&company="+company+"&displayName="+displayName+"&gender="+gender+"&role="+role+"&firstName="+firstName;
		alert(req_data);
		$.ajax({
			cache : false,
			type : "POST",
			dataType : 'JSON',
			url : "/Pool4u/DispatcherServlet",
			data : req_data,
			success : function(result) {
				var parsedResponse = $.parseJSON(result);
				
				if (parsedResponse.status == "SUCCESS") {
					show_auth("register");
				} else {
					var errorCode = parsedResponse.errorCode;
					// UserName does not exists. Show register screen
					if (errorCode == 1001) {
						$("#register_div #register_error_message").html("User already exists.<br/>");
					}
					
				}
			}
		
		});		
		
		
		
	}
	
	// Hide the login div
	function hide_login(){
		$("#login_div").hide();
		$("#register_div").show();
		$("#register_div2").show();
		$("#register_div :input").attr('disabled',false);
		$("#authorization_box_register").hide();
		$(":button#register").show();
		
		// Load the company list for register box
		var req_data="requestId=fetchCompanyList";
		$.ajax({
			cache : true,
			type : "POST",
			dataType : 'JSON',
			data: req_data,
			url : "/Pool4u/DispatcherServlet",
			success : function(result) {
				var option = "";
				var parsedResponse = $.parseJSON(result);
				var companyList = parsedResponse.companies;
				for ( var i = 0; i < companyList.length; i++) {
					option += '<option value="'+  companyList[i].companyId + '">'
							+ companyList[i].companyName + '</option>';
				}
				$("select#company").html(option);
			}
		});		
	}
	
	// Hide the register div
	function hide_register(){
		$("#register_div").hide();
		$("#login_div").show();
		$("#login_div :input").attr('disabled',false);
		$("#authorization_box_login").hide();
		$(":button#login").show();
		$("#user_error").html("");
		$("#pass_error").html("");
	}
	
	// Function to authorize code
	function authorize_code(caller){
		var divName="";
		// To decide the elements
	var userName = "";
		if(caller=="register")
		{	userName = document.getElementById("userName").value
			divName="#authorization_box_register #";
		}
		else
		{ 	userName = document.getElementById("loginName").value
			divName="#authorization_box_login #login_";
		}
	
		var auth_data="requestId=ConfirmUser&auth_data="+$(divName+"auth_code").val()+"&userName="+userName;
		alert(auth_data);
		$.ajax({
			cache:false,
			url: "/Pool4u/DispatcherServlet",
			data: auth_data,
			dataType: "JSON",
			type:"POST",
			success:function(result){
				var parsedResponse = $.parseJSON(result);
				if(parsedResponse.status == "SUCCESS")
				{
					window.location = parsedResponse.url;
				}
				else
				{
					var errorCode = parsedResponse.errorCode;
					// UserName does not exists. Show register screen
					if (errorCode == 1002) {
						alert(divName+"auth_error");
						$(divName+"auth_error").html("User doesn't exist");
					}
					else if (errorCode == 1003) {
						alert(divName+"auth_error");
						$(divName+"auth_error").html("Confirmation Code does not match. Please Retry!");
					}
				}
			}
		});
		//return false;
	}
	function checkAvailability(){
		var displayName = document.getElementById("dName").value.replace(/^(?:\s)*/g,'').replace(/(?:\s)*$/g,'');
		var req_data="requestId=CheckDisplayName&displayName="+displayName;
		if(displayName!=null && displayName.length>0)
			
			$.ajax({
			cache : false,
			type : "POST",
			dataType : 'JSON',
			url : "/Pool4u/DispatcherServlet",
			data : req_data,
			success : function(result) {
				var parsedResponse = $.parseJSON(result);
				
				if (parsedResponse.status == "SUCCESS") {
					alert("good choice buddy")
				} else {
					var errorCode = parsedResponse.errorCode;
					
					if (errorCode == 1008) {
					//	$("#register_div #register_error_message").html("User already exists.<br/>");
						alert("bad choice pal");
					}
					
				}
			}
		
		});		
			
			
			
			
	}