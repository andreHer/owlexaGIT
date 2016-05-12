<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<link rel="stylesheet" type="text/css" href="css/autocomplete.css">
<link rel="stylesheet" type="text/css" href="css/button.css">

<link rel="stylesheet" type="text/css" href="css/jquery/autocomplete/jquery.autocomplete.css"/>

<script type="text/javascript" src="scripts/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="scripts/jquery/plugin/jquery.autocomplete.pack.js"></script>




<form action="claim-form" method="POST"  name="form1" id="form_layout">
<input type="hidden" name="navigation" value="">

<%
String alert = (String) request.getAttribute("alert");
if (alert != null && !alert.trim().equals("")) {%>
	<div id="warning">
		<c:out value="${alert}"></c:out>
	</div>
	<%}%>


<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>
								
		<tr>
			<td class="dataLabel"> </td>
  			<td class="dataField" colspan="3">&nbsp;</td>
		</tr>
		<tr>
		    <td class="dataLabel" width="20%">Provider Name : </td>		
			<td class="dataField" width="30%">
				<input type="hidden" tabindex="3"  id="providerId" name="providerId" value="" size="35" >
				<input type="text" tabindex="3"  id="providerName" name="providerName" value="" size="35" >
			</td>	  
			<td class="dataLabel"></td>
			<td class="dataField"></td>	 		
		</tr>
		<tr>
		    <td class="dataLabel">Member Card Number : </td>		
			<td class="dataField">
				<input type="text" tabindex="3"  id="name" name="cardNumber" value="" size="35" >
			</td>	  
			<td class="dataLabel"></td>
			<td class="dataField"></td>	 		
		</tr>
		<tr>
								    
		    <td class="dataLabel">Claim Service * : </td>		
			<td class="dataField">
				<select tabindex="1" name="claimService">
					<option value=""> -- SELECT SERVICE -- </option>
					<c:forEach items="${caseCategory}" var="cc">
						<option value="${cc.caseCategoryId}" ><c:out value="${cc.caseCategoryName}" /></option>
					</c:forEach>
				</select>										
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
		    		<c:if test="${theUser.roleId.roleId eq 19}">
			    		<input title="Save [Alt+Shift+S]" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:register()" name="Save" value=" Register " type="submit">		    		        
		    		</c:if>
		    		
		    	</c:if>
	        	         
	      	</td>
	      	<td align="right"></td>
    	</tr>
  	</tbody>
</table>
</form>

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
	     
	});
	
	function register (){
		document.form1.method = "POST";
		document.form1.navigation.value = "saveregisterrki";
		document.form1.action = "member";
		document.form1.submit();
	}

</script>