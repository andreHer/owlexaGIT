<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>

<html>
	<head>	
	    <link href="css/mobile/stylesheets.css" rel="stylesheet" type="text/css" />	    
		<link rel="stylesheet" type="text/css" href="css/jquery/autocomplete/jquery.autocomplete.css"/>
		
		<script type="text/javascript" src="scripts/jquery/jquery-1.4.4.min.js"></script>
		<script type="text/javascript" src="scripts/jquery/plugin/jquery.autocomplete.pack.js"></script>
		
		
		<script type='text/javascript' src='dwr/interface/AJAXCaseService.js'></script>
		<script type='text/javascript' src='dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
	    
	</head>
	<body>
		<form name="form1" action="mobile" method="POST">
		<input type="hidden" name="navigation" value="saverefer" />
		<div class="row-fluid">
            <div class="span6">
                <div class="block">                                   
                    <div class="data-fluid">
                        
                        <div class="row-form">
                            <div class="span3">Name:</div>
                            <div class="span5"><input type="text" name=memberName"" value="<c:out value="${myCase.memberId.firstName}" />"/></div>
                        </div>
                        <div class="row-form">
                            <div class="span3">Company:</div>
                            <div class="span5"><input type="text" value="<c:out value="${myCase.memberId.memberGroupId.groupName }" />" readonly="readonly"/></div>
                        </div>
                        <div class="row-form">
                            <div class="span3">Status / Expire Date:</div>
                            <div class="span5"><input type="text" value="<c:out value="${status}" /> / <c:out value="${myCase.memberId.expireDate}" />" readonly="readonly"/></div>
                        </div>     
                        <div class="row-form">
                            <div class="span3">Diagnosis:</div>
                            <div class="span5"><input type="text" value="<c:out value="${myCase.diagnosis1Id.diagnosisName}" />" readonly="readonly"/></div>
                        </div>   
                    </div>
                </div>
            </div>            
        </div>
        <div class="row-fluid">
            <div class="span6">
                <div class="block">                                   
                    <div class="data-fluid">
                        
                        <div class="row-form">
                            <div class="span3">Provider:</div>
                            <div class="span5">
                            	<input type="hidden" name="providerId" id="providerId" value="" />
                            	<input type="text" name="providerName" id="providerName" value=""/>
                            </div>
                        </div>
                        <div class="row-form">
                            <div class="span3">Poliklinik:</div>
                            <div class="span5">
                            	<input type="hidden" id="poliId" value="" name="poliId" />
                            	<input type="text" id="poliName" value="" name="poliName" />
                            </div>
                        </div>
                        <div class="row-form">
                            <div class="span3">Doctor:</div>
                            <div class="span5">
                            <select name="doctorId">
                            
                            </select>
                            </div>
                        </div>      
                    </div>
                </div>
            </div>            
        </div>
        <div class="row-fluid">

            <div class="span6">                
				<div class="row-form">
                	<button class="btn btn-primary" type="button">Save</button>
                	<button class="btn btn-danger" type="button">Cancel</button>
                </div>
            </div>
            
        </div>
        </form>
     </body>
</html>     