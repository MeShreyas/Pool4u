function getMyPrefernces(){

	var req_data="requestId=fetchAllowedCompanies";
	$.ajax({
		url : "/Pool4u/DispatcherServlet",
		cache:false,
		type : "GET",
		dataType : "JSON",
		data: req_data,
		success : function(result){
			var parsedResponse = $.parseJSON(result);
			var allowedCompanyList = parsedResponse.allowedCompanies;
			var restrictedCompanyList = parsedResponse.restrictedCompanies;
			var htmlText="";
			var disabled ="";
			var inner_count=0;
			var divOpened=0;
			for(var i=0;i<allowedCompanyList.length;i++)
			{	
				if(allowedCompanyList[i].myCompany=="Y"){ 
					disabled="disabled";
				} else{
					disabled ="";
				}
				if(inner_count==0){
					htmlText+='<div class="row">';
					htmlText+='<div class="span4">';
					divOpened=1;
				} else if(inner_count==1)	{
					htmlText+='<div class="span3">';
				} else if(inner_count==2)	{
					htmlText+='<div class="span4">';
				} else if(inner_count==3)	{
					htmlText+='<div class="span3">';
				}
				htmlText+='<input type="checkbox" '+disabled+' id="allowedCompany'+(i+1)+'" value="'+allowedCompanyList[i].companyId+'" /> <span>'+allowedCompanyList[i].companyName+'</span>';
				htmlText+='</div>';
				
				inner_count++;
				
				if(inner_count==4)	{
					htmlText+='</div>';
					inner_count=0;
					divOpened=0;
				}				
			}
			if(divOpened==1){htmlText+='</div>';}
			
			$("#allowed_company_list").html(htmlText);
						
			htmlText="";
			inner_count=0;
			divOpened=0;
			for(var i=0;i<restrictedCompanyList.length;i++)
			{	
				if(inner_count==0){
					htmlText+='<div class="row">';
					htmlText+='<div class="span4">';
					divOpened=1;
				} else if(inner_count==1){
					htmlText+='<div class="span3">';
				} else if(inner_count==2){
					htmlText+='<div class="span4">';
				} else if(inner_count==3){
					htmlText+='<div class="span3">';
				}
				htmlText+='<input type="checkbox" id="restrictedCompany'+(i+1)+'" value="'+restrictedCompanyList[i].companyId+'" /> <span>'+restrictedCompanyList[i].companyName+'</span>';
				htmlText+='</div>';
				
				inner_count++;
				if(inner_count==4) {
					htmlText+='</div>';
					inner_count=0;
					divOpened=0;
				}				
			}
			if(divOpened==1){htmlText+='</div>';}
			$("#vicinity_company_list").html(htmlText);
		}
	});
}
function updateMailOrPhone(flag){
	var req_data="";
	alert(flag);
	if(flag=="E"){
		var email = document.getElementById("sec_email").value;
		req_data="requestId=updateEmail";
		req_data = req_data+"&emailId="+email;
	}
	else if(flag=="P"){
		var phone = document.getElementById("phone").value;
		req_data="requestId=updatePhone";
		req_data = req_data+"&phone="+phone;
	}
	alert(req_data);
	$.ajax({
		url : "/Pool4u/DispatcherServlet",
		cache:false,
		type : "POST",
		dataType : "JSON",
		data: req_data,
		success : function(result){
			var parsedResponse = $.parseJSON(result);
			if (parsedResponse.status == "SUCCESS"){
				alert("update successful!");
			}
			else if(parsedResponse.status == "FAILURE"){
				var errorCode = parsedResponse.errorCode;
				alert(errorCode);
			}
		}
	});
}

function getMyData(){
	var req_data="requestId=getUserdetails";
	$.ajax({
		url : "/Pool4u/DispatcherServlet",
		cache:false,
		type : "POST",
		dataType : "JSON",
		data: req_data,
		success : function(result){
			var parsedResponse = $.parseJSON(result);
			var htmlText="";
			var name = parsedResponse.userData.firstName;
			if (parsedResponse.status == "SUCCESS"){
				
				
				/*htmlText=htmlText+"<div  class=\"label\">Name</div>";
				htmlText=htmlText+"<input type=\"text\" id=\"firstName\" name=\"firstName\" value=\""+parsedResponse.userData.firstName+"\" disabled/><br/>";
				*/
				$("#firstName").val(parsedResponse.userData.firstName);
				$("#username").val(parsedResponse.userData.userName);
				$("#pri_email").val(parsedResponse.userData.userName);
				
				var email = parsedResponse.userData.email;
				
				if(email=="null"){
					email="";
					
				}
				$("#sec_email").val(email);
				
				var phone = parsedResponse.userData.phone;
				
				if(phone=="null"){
					phone="";
					
				}
				$("#phone").val(phone);
				
				
				$("#companyName").val(parsedResponse.userData.companyName);
				$("#companyId").val(parsedResponse.userData.companyId);
				
			}
			else{
				var errorCode = parsedResponse.errorCode;
					alert(errorCode);
			}
		}
	});
}
function updateAllowedCompanies(flag){
	
	var req_data="requestId=updateAllowedCompanies";
	var tmpString="";
	var allowedCompany;
	var companyId;
	var j=1;
	var input = "";
	if(flag=="R"){
		input = "allowed";
	}
	else if(flag=="A"){
		input = "restricted";
	}
	for(var i=1;;i++){
	
		allowedCompany=document.getElementById(input+"Company"+i);
		
		if(allowedCompany){
			
			allowedCompany=document.getElementById(input+"Company"+i).checked;
			if(allowedCompany){
				companyId = document.getElementById(input+"Company"+i).value;
				tmpString=tmpString+"&company"+(j++)+"="+companyId;
			}
		}
		else{
			break;
		}
		

	}
	req_data= req_data+tmpString;
	req_data=req_data+"&updateFlag="+flag;
	$.ajax({
		url : "/Pool4u/DispatcherServlet",
		cache:false,
		type : "POST",
		dataType : "JSON",
		data: req_data,
		success : function(result){
			var parsedResponse = $.parseJSON(result);
			if (parsedResponse.status == "SUCCESS"){
				var allowedCompanyList = parsedResponse.allowedCompanies;
				var restrictedCompanyList = parsedResponse.restrictedCompanies;
				var htmlText="";
				var disabled ="";
				var inner_count=0;
				var divOpened=0;
				for(var i=0;i<allowedCompanyList.length;i++)
				{	
					if(allowedCompanyList[i].myCompany=="Y"){ 
						disabled="disabled";
					} else{
						disabled ="";
					}
					if(inner_count==0){
						htmlText+='<div class="row">';
						htmlText+='<div class="span4">';
						divOpened=1;
					} else if(inner_count==1)	{
						htmlText+='<div class="span3">';
					} else if(inner_count==2)	{
						htmlText+='<div class="span4">';
					} else if(inner_count==3)	{
						htmlText+='<div class="span3">';
					}
					htmlText+='<input type="checkbox" '+disabled+' id="allowedCompany'+(i+1)+'" value="'+allowedCompanyList[i].companyId+'" /> <span>'+allowedCompanyList[i].companyName+'</span>';
					htmlText+='</div>';
					
					inner_count++;
					if(inner_count==4)	{
						htmlText+='</div>';
						inner_count=0;
						divOpened=0;
					}				
				}
				if(divOpened==1){htmlText+='</div>';}
				
				$("#allowed_company_list").html(htmlText);
							
				htmlText="";
				inner_counter=0;
				divOpened=0;
				for(var i=0;i<restrictedCompanyList.length;i++)
				{	
					if(inner_count==0){
						htmlText+='<div class="row">';
						htmlText+='<div class="span4">';
						divOpened=1;
					} else if(inner_count==1){
						htmlText+='<div class="span3">';
					} else if(inner_count==2){
						htmlText+='<div class="span4">';
					} else if(inner_count==3){
						htmlText+='<div class="span3">';
					}
					htmlText+='<input type="checkbox" id="restrictedCompany'+(i+1)+'" value="'+restrictedCompanyList[i].companyId+'" /> <span>'+restrictedCompanyList[i].companyName+'</span>';
					htmlText+='</div>';
					
					inner_count++;
					if(inner_count==4) {
						htmlText+='</div>';
						inner_count=0;
						divOpened=0;
					}				
				}
				if(divOpened==1){htmlText+='</div>';}
				$("#vicinity_company_list").html(htmlText);
			} else{
				var errorCode = parsedResponse.errorCode;
					alert(errorCode);
			}
			

		}
	});
}
	
	
