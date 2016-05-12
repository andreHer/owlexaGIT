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
			<input type="hidden" name="memberId" value="<c:out value="${memberId}" />" />
			<input type="hidden" name="login" value="<c:out value="${login}" />" />
			<input type="hidden" name="password" value="<c:out value="${password}" />" />
			<div class="row-fluid">
	            <div class="span6">             
	
	                <div class="block">                                   
	                    <div class="data-fluid">
	                        
	                        <div class="row-form">
	                            <div class="span3">Member Number :</div>
	                            <div class="span5">
	                            	<input type="text" name="memberNumber" id="memberNumber" value="" />
	                            </div>
	                        </div>
	                    </div>
	                </div>
	            </div>            
	        </div>
	        <div class="row-fluid">
	
	            <div class="span6">                
					<div class="row-form">
	                	<button class="btn btn-primary" type="button" onclick="javascript:inquiryMember();">Verify Membership</button>
	                </div>
	            </div>
	            
	        </div>
        </form>
     </body>
</html>     

<script language="javascript">
 
 function inquiryMember(){
 	document.form1.action = "mobile";
 	document.form1.navigation.value = "doinquiry";
 	document.form1.submit();
 }
</script>