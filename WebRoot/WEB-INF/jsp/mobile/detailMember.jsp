<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>


<html>
	<head>		    
	    <link href="css/mobile/stylesheets.css" rel="stylesheet" type="text/css" />
		
	</head>
	<body>
		<form action="mobile" name="form1" method="POST">
			<input type="hidden" name="navigation" value="" />
			<input type="hidden" name="memberId" value="<c:out value="${member.memberId}" />" />
			<input type="hidden" name="login" value="<c:out value="${login}" />" />
			<input type="hidden" name="password" value="<c:out value="${password}" />" />
			<div class="row-fluid">
	            <div class="span6">             
	
	                <div class="block">                                   
	                    <div class="data-fluid">
	                        
	                        <div class="row-form">
	                            <div class="span3">Member Name / No. :</div>
	                            <div class="span5">
	                            	<input type="text" name="memberNumber" id="memberNumber" value="<c:out value="${member.firstName}" /> / <c:out value="${member.customerPolicyNumber}" />" readonly="readonly"/>	                            	 
	                            </div>
	                        </div>
	                        <div class="row-form">
	                            <div class="span3">Company :</div>
	                            <div class="span5">
	                            	<input type="text" name="memberNumber" id="memberNumber" value="<c:out value="${member.memberGroupId.groupName}" />" readonly="readonly"/>	                            	 
	                            </div>
	                        </div>
	                        <div class="row-form">
	                            <div class="span3">Status :</div>
	                            <div class="span5">
	                            	<input type="text" name="memberNumber" id="memberNumber" value="<c:out value="${status}" /> " readonly="readonly"/>
	                            	
	                            </div>
	                        </div>   
	                        <div class="row-form">
	                            <div class="span3">Expire Date :</div>
	                            <div class="span5">
	                            	<input type="text" name="memberNumber" id="memberNumber" value="<c:out value="${expireDate}" />" readonly="readonly"/>
	                            	
	                            </div>
	                        </div>      
	                    </div>
	                </div>
	            </div>            
	        </div>
	        <div class="row-fluid">	
	            <div class="span6">                
					<div class="row-form">
	                	<button class="btn btn-primary" type="button" onclick="javascript:registerCase();">Register Case</button>
	                	<button class="btn btn-danger" type="button" onclick="javascript:inquiryMember();">Back</button>
	                </div>
	            </div>
	            
	        </div>
        </form>
     </body>
</html>     

<script language="javascript">

 
 function registerReference(idx){
 
 }
 function registerCase(){
 	document.form1.action = "mobile";
 	document.form1.navigation.value = "registercase";
 	document.form1.method = "POST";
 	document.form1.submit();
 }
 function inquiryMember(){
 	document.form1.action = "mobile";
 	document.form1.navigation.value = "inquiry";
 	document.form1.submit();
 }
</script>