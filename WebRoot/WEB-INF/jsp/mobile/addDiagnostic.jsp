<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>


<html>
	<head>
	    <link href="css/mobile/stylesheets.css" rel="stylesheet" type="text/css" />	
	    
	    <link rel="stylesheet" type="text/css" href="css/jquery/autocomplete/jquery.autocomplete.css"/>
		
		<script type="text/javascript" src="scripts/jquery/jquery-1.4.4.min.js"></script>
		<script type="text/javascript" src="scripts/jquery/plugin/jquery.autocomplete.pack.js"></script>	
	</head>
	<body>
		<form name="form1" action="mobile" method="post">
			<input type="hidden" name="navigation" value="<c:out value="${navigation}" />" />
			<input type="hidden" name="login" value="<c:out value="${login}" />" />
			<input type="hidden" name="password" value="<c:out value="${password }" />" />
			<input type="hidden" name="caseId" value="<c:out value="${caseId}" />" />
			
			<div class="row-fluid">
	            <div class="span6">             
	
	                <div class="block">                                   
	                    <div class="data-fluid">
	                        
	                        <div class="row-form">
	                            <div class="span3">Diagnostic Test :</div>
	                            <div class="span5">
	                            	<select name="">
	                            		<option value="">-- SELECT ONE --</option>                            		
	                            		<c:forEach items="${itemList}" var="item">
	                            			<option value="<c:out value="${item.itemId}" />"><c:out value="${item.itemName}" /></option>                            		
	                            		</c:forEach>
	                            	</select>                            	
	                            </div>
	                        </div>
	                        <div class="row-form">
	                            <div class="span3">Total Charge :</div>
	                            <div class="span5">
	                            	<input type="text" value="" name="itemValue" id="itemValue"/>
	                            </div>
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
	                            <div class="span3"></div>
	                            <div class="span5">
	                          
	                            </div>
	                        </div>
	                    </div>                                   
	                    <div class="data-fluid">	                        
	                        <div class="row-form">
	                            <div class="span3">Diagnosis Conclusion :</div>
	                            <div class="span5">
	                            	<input type="hidden" value="" name="diagnosisId" id="diagnosisId"/>
	                            	<input type="text" value="" name="diagnosisName" id="diagnosisName"/>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	            </div>            
	        </div>
	        <div class="row-fluid">
	
	            <div class="span6">                
					<div class="row-form">
	                	<button class="btn btn-primary" type="button" onclick="javascript:simpan();">Save</button>
	                	<button class="btn btn-danger" type="button" onclick="javascript:kembali();">Cancel</button>
	                </div>
	            </div>
	            
	        </div>
        </form>
     </body>
</html>     
<script language="javascript">
	$(document).ready(function(){   
    
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
	function simpan(){
		document.form1.action = "mobile";
		document.form1.navigation.value = "savediagnostic";
		document.form1.submit();
	}
	function kembali(){
		document.form1.action = "mobile";
		document.form1.navigation.value = "detailcase";
		document.form1.submit();
	}
</script>