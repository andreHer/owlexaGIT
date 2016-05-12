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
			<input type="hidden" name="memberId" value="<c:out value="${memberId}" />" />
			<input type="hidden" name="username" value="<c:out value="${username}" />" />
			<input type="hidden" name="password" value="<c:out value="${password}" />" />
			
			<input type="hidden" name="navigation" value="savegl"/>
			<div class="row-fluid">
	            <div class="span6">
	                <div class="block">                                   
	                    <div class="data-fluid">                        
	                        <div class="row-form">
	                            <div class="span3">Provider : </div>	                            
	                            <div class="span5">
	                            	<input type="text" value=""  name="providerName" id="providerName"/>
	                            	<input type="hidden" name="providerId" value="" id="providerId" />
	                            
	                            </div>
	                        </div>
	                        <div class="row-form">
	                            <div class="span3">Diagnosis : </div>
	                            <div class="span5">
	                            <input type="text" value="" name="diagnosisName" id="diagnosisName" />
	                            <input type="hidden" name="diagnosisId" value="" id="diagnosisId" />
	                            </div>
	                        </div>
	                        <div class="row-form">
	                            <div class="span3">Case Category : </div>
	                            <div class="span5">
	                            	<select name="roomStatus">
	                            		<option value="1" >INPATIENT</option>									 		
										<option value="2" >OUTPATIENT</option>									 		
									 	<option value="3" >MATERNITY</option>									 		
									 	<option value="4" >DENTAL</option>									 		
									 	<option value="5" >OPTICAL</option>
	                            	</select>	                            
	                            </div>
	                        </div>
	                        <div class="row-form">
	                            <div class="span3">Room Status : </div>
	                            <div class="span5">
	                            	<select name="roomStatus">
	                            		<option value="1" >SESUAI BENEFIT</option>									 		
										<option value="2" >NAIK PERMINTAAN SENDIRI</option>									 		
									 	<option value="3" >KAMAR PENUH</option>									 		
									 	<option value="4" >KAMAR TIDAK TERSEDIA</option>									 		
									 	<option value="5" >REKOMENDASI DOKTER</option>
	                            	</select>
	                            
	                            </div>
	                        </div>
	                        <div class="row-form">
	                            <div class="span3">Room & Board : </div>
	                            <div class="span5">
	                            	<input type="text" value="" name="roomAndBoard" id="roomAndBoard" />	                            
	                            </div>
	                        </div>
	                        <div class="row-form">
	                            <div class="span3">Anamnesa :</div>
	                            <div class="span5">
	                            	<textarea rows="4" cols="20" name="anamnesa"></textarea>
	                            </div>
	                        </div>    
	                        <div class="row-form">
	                            <div class="span3">Remarks :</div>
	                            <div class="span5">
	                            	<textarea rows="4" cols="20" name="remarks"></textarea>
	                            </div>
	                        </div>   
	                    </div>
	                </div>
	            </div>            
	        </div>
	        
	        <div class="row-fluid">
	            <div class="span6">                
					<div class="row-form">
	                	<button class="btn btn-primary" type="button" onclick="javascript:requestGL();">Save</button>
	                	<button class="btn btn-danger" type="button" onclick="javascript:cancel();">Cancel</button>
	                </div>
	            </div>	            
	        </div>
        </form>
     </body>
</html>     

<script language="javascript">
$(document).ready(function(){   
    
    $("#providerName").autocomplete("provider?navigation=lookupjson", {
        max: 7,
        dataType: "json",
        parse: function(data) {
            return $.map(data, function(row) {
                return {
                    data: row,
                    value: row.name,
                    result: row.name
                }
            });
        },
        formatItem: function(row) {
            return  "<font color='#000' style='align: left;' >"+ row.name +"</font>" ;
        }
    }).bind("result", function(data, value){
        $(this).parents("dd").find(".error_message").hide();
        $("#providerId").val (value.id);
    });
    
    $("#diagnosisName").autocomplete("diagnosis?navigation=lookupjson", {
        max: 7,
        dataType: "json",
        parse: function(data) {
            return $.map(data, function(row) {
                return {
                    data: row,
                    value: row.name,
                    result: row.name
                }
            });
        },
        formatItem: function(row) {
            return  "<font color='#000' style='align: left;' >"+ row.name +"</font>" ;
        }
    }).bind("result", function(data, value){
        $(this).parents("dd").find(".error_message").hide();
        $("#diagnosisId").val (value.id);
    });
 });
 function requestGL(){
 	document.form1.action = "mobile";
 	document.form1.navigation.value = "savegl";
 	document.form1.submit();
 }
 function cancel(){
 	document.form1.action = "mobile";
 	document.form1.navigation.value = "back";
 	document.form1.submit();
 }
</script>
 