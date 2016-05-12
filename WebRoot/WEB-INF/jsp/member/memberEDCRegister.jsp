<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<link rel="stylesheet" type="text/css" href="css/autocomplete.css">
<link rel="stylesheet" type="text/css" href="css/button.css">

<link rel="stylesheet" type="text/css" href="css/jquery/autocomplete/jquery.autocomplete.css"/>

<script type="text/javascript" src="scripts/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="scripts/jquery/plugin/jquery.autocomplete.pack.js"></script>

<div id="spinner" style="display:    none;
	    position:   fixed;
	    z-index:    10009;
	    top:        0;
	    left:       0;
	    height:     100%;
	    width:      100%;
	    background: rgba( 255, 255, 255, .8 ) 
	                url('images/owlexa/loading.gif') 
	                50% 50% 
	                no-repeat;">
</div>

<form action="member-edc-emulator" method="POST"  name="form1" id="form_layout">
<input type="hidden" name="navigation" value="saveregisterrki">



<%
String alert = (String) request.getAttribute("alert");
if (alert != null && !alert.trim().equals("")) {%>
	<div id="warning">
		<c:out value="${alert}"></c:out>
	</div>
	<%}%>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;EDC Register Emulator</h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>
<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>
								
		<tr>
			<td class="dataLabel"> </td>
  			<td class="dataField" colspan="3">&nbsp;</td>
		</tr>
		
		<c:if test="${theUser.userType eq 2}">
			<tr>
			    <td class="dataLabel" width="13%">Provider Name</td>		
				<td class="dataField" width="30%">
					<spring:bind path="memberForm.providerId">
					<input type="hidden" tabindex="3"  id="providerId" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" size="35" >
					</spring:bind>
					<input type="hidden" tabindex="3"  id="providerEDCCode" name="providerEDCCode" value="" size="35" >
					<spring:bind path="memberForm.providerName">
					<input type="text" tabindex="1"  id="providerName" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" size="35" >
						<div id="fredcaption">
							<c:out value="${status.errorMessage}" />
						</div>
					<br/></spring:bind>
				</td>
				<td class="dataLabel"></td>
				<td class="dataField"></td>	 
			</tr>
		</c:if>
				<c:if test="${theUser.userType eq 4}">
			<tr>
			    <td class="dataLabel" width="20%">Provider Name</td>		
				<td class="dataField" width="30%">
					<spring:bind path="memberForm.providerId">
					<input type="hidden" tabindex="3" readonly="readonly" id="providerId" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" size="35" >
					</spring:bind>
					<spring:bind path="memberForm.providerName">
					<input type="text" tabindex="1" readonly="readonly"  id="providerName" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" size="35" >
						<div id="fredcaption">
							<c:out value="${status.errorMessage}" />
						</div>
					<br/></spring:bind>
				</td>
				<td class="dataLabel"></td>
				<td class="dataField"></td>	 		
			</tr>
		</c:if>
		
		<tr>
		    <td class="dataLabel">Member Card Number *</td>		
			<td class="dataField">
				<spring:bind path="memberForm.cardNumber">
					<input id="cardNumber" type="text" tabindex="2" size="35" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
					<div id="fredcaption">
						<c:out value="${status.errorMessage}" />
					</div>
				<br/></spring:bind>				
			</td>	  
			<td class="dataLabel"></td>
			<td class="dataField"></td>	 		
		</tr>
		
		<tr>
			<td style="padding-top: 2px;padding-left: 1px;">
				<c:if test="${theUser.userType eq 2}">
		    		<c:if test="${theUser.roleId.roleId eq 19 or theUser.roleId.roleId eq 5  or theUser.roleId.roleId eq 0}">
			    		<input title="Load Card Available Service" tabindex="4" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" id="loadMemberAvailableService" name="loadMemberAvailableService" value="Load Service" type="button">		    		        
		    		</c:if>		    		
		    	</c:if> 			        	         
	      	</td>
		</tr>
		
		<tr>
								    
		    <td class="dataLabel">Claim Service *</td>		
			<td class="dataField">
				<spring:bind path="memberForm.caseCategoryId">
				<select id="memberService" tabindex="3" name="<c:out value="${status.expression}"/>">
					<option value=""> -- SELECT SERVICE -- </option>
					<!-- 
					<c:forEach items="${caseCategoryList}" var="cc">
						<option value="<c:out value="${cc.caseCategoryId }" />" <c:if test="${cc.caseCategoryId eq status.value }">selected</c:if>><c:out value="${cc.caseCategoryName}" /> - <c:out value="${cc.caseCategoryCode}" /></option>
					</c:forEach>
					-->			
				</select>		
				<div id="fredcaption">
						<c:out value="${status.errorMessage}" />
					</div>	
				<br/></spring:bind>							
			</td>
			<td class="dataLabel"></td>		
			<td class="dataField"></td>		
		</tr>
		
		<tr id="referenceSection">
		    <td class="dataLabel">Reference Number</td>		
			<td class="dataField">
				
					<input id="referenceNumber" type="text" tabindex="2" size="35" name="referenceNumber" value="" >
					<div id="fredcaption">
					</div>
				<br/>
								
			</td>	  
			<td class="dataLabel"></td>
			<td class="dataField"></td>	 		
		</tr>
		
		<tr>
		    <td class="dataLabel">Admission Date</td>		
			<td class="dataField">
				<spring:bind path="memberForm.admissionDate">
						<input type="text" size="25" name="<c:out value="${status.expression}" />" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="30">
						<img src="images/jscalendar.gif" alt="Enter Date" id="<c:out value="${status.expression}"/>_trigger" align="absmiddle" height="20" width="20">
						<script type="text/javascript">
	    					Calendar.setup({
	        					inputField     :    "<c:out value="${status.expression}"/>",     // id of the input field
	        					ifFormat       :    "%Y-%m-%d",      // format of the input field
	        					button         :    "<c:out value="${status.expression}"/>_trigger",  // trigger for the calendar (button ID)
	        					align          :    "Bl",           // alignment (defaults to "Bl")
	        					singleClick    :    true
	    					});
					 	</script>
						<div id="fredcaption">
							<c:out value="${status.errorMessage}" />
						</div>
					</spring:bind>				
			</td>	  
			<td class="dataLabel"></td>
			<td class="dataField"></td>	 		
		</tr>
		<tr>
			<td class="dataLabel"> </td>
	    	<td class="dataField" colspan="3">&nbsp;</td>
	    </tr>
		
	</tbody>
</table>
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  	<tbody>
    	<tr>
	      	<td style="padding-top: 2px;padding-left: 1px;">
		    	<input type="hidden" name="notyet" value="">
		    	<c:if test="${theUser.userType eq 2}">
		    		<c:if test="${theUser.roleId.roleId eq 19 or theUser.roleId.roleId eq 5 or theUser.roleId.roleId eq 0 }">
			    		<input title="Save [Alt+Shift+S]" tabindex="4" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:registerRKI()" name="Save" value=" Register " type="button">		    		        
		    		</c:if>		    		
		    	</c:if>
		    	<c:if test="${theUser.userType eq 4}">		    		
		    		<input title="Save [Alt+Shift+S]" tabindex="4" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:registerRKI()" name="Save" value=" Register " type="button">
		    	</c:if>	        	         
	      	</td>
	      	<td align="right"></td>
    	</tr>
  	</tbody>
</table>
</form>

<script language="javascript">
	
	
	$(document).ready(function(){
		$("#spinner").hide();
		$("#referenceSection").hide();
     
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
	        $("#providerEDCCode").val (value.number);
	    });
	    
	    $("#loadMemberAvailableService").click(function(){
			//alert("Provider ID : " + $("#providerId").val() + "\nCard Number : " + $("#cardNumber").val());
			
			$.ajax({
                type : "POST",
                url : "member?navigation=lookupjsonedcservice",
                beforeSend : function(){
                	//alert("Before sending AJAX...");
                	//$body.addClass("loading");
                	$("#spinner").show();
                },
                complete : function(){
                	//alert("AJAX completed...");
                	//$body.removeClass("loading");
                	$("#spinner").hide();
                },
                data : "providerId=" + $("#providerId").val() + "&cardNumber=" + $("#cardNumber").val() + "&providerEdcCode=" + $("#providerEDCCode").val(),
                dataType: 'json',
                success : function(data) {
                    var arrServices = data.additionalMessage;
                    var services = arrServices.split("|");
                    
                    //clear it first
                    $("#memberService").empty();
                    $("#memberService").append("<option value=''> -- SELECT SERVICE -- </option>");
                    
                    for(var idxServices=0;idxServices<services.length;idxServices++){
                    	var tmpService = services[idxServices];
                    	var srvc = tmpService.split("#");
                    	//alert("Service Name : " + srvc[0] + "\nService Code : " + srvc[1]);
                    	$("#memberService").append("<option value='" + srvc[1] + "'>" + srvc[0] + "</option>");
                    }
                    
                    //peek a boo reference
                    //alert(data.reason);
                    switch(data.reason){
                    	case 'INDEMNITY' :
                    		$("#referenceSection").hide();
                    	break;
                    	case 'MANAGEDCARE' :
                    		$("#referenceSection").show();
                    	break;
                    	default:
                    		$("#referenceSection").hide();
                    	break;
                    }
                    
                }
            });
			
			//return false;
			
		});
		
		/* 
		$("#memberService").change(function(){
			alert($("#memberService").val());
		});
	     */
	      
	});
	
	
	function registerRKI(){
		
		document.form1.method = "POST";
		document.form1.navigation.value = "saveregisterrki";
		document.form1.action = "member-edc-emulator";
		document.form1.submit();
		
	}

</script>