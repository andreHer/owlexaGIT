<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<link rel="stylesheet" type="text/css" href="css/autocomplete.css">
<link rel="stylesheet" type="text/css" href="css/button.css">

<link rel="stylesheet" type="text/css" href="css/jquery/autocomplete/jquery.autocomplete.css"/>

<script type="text/javascript" src="scripts/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="scripts/jquery/plugin/jquery.autocomplete.pack.js"></script>

<%
String alert = (String) request.getAttribute("alert");
if (alert != null && !alert.trim().equals("")) {%>
	<div id="warning">
		<c:out value="${alert}"></c:out>
	</div>
	<%}%>
	
<form action="formularium-form" method="POST"  name="form1" id="form_layout">
	<spring:bind path="formulariumForm.formulariumId">
			<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
	</spring:bind>

	<input type="hidden" name="navigation" value="">

<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>

			<tr>
				<td class="dataLabel">&nbsp;</td>
				<td class="dataField"></td>
	    		<td class="dataLabel"></td>
	    		<td class="dataField"></td>
			</tr>
			<tr>
				<td class="dataLabel"> Formularium Name : </td>				
			

			<td class="dataField">
			<spring:bind path="formulariumForm.formulariumName">
				<input type="text" size="35" name="<c:out value="${status.expression}" />" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
	

		
				
		
		

			<tr>
				<td class="dataLabel"> Formularium Code : </td>				
			

			<td class="dataField">
			<spring:bind path="formulariumForm.formulariumCode">
				<input type="text" size="25" name="<c:out value="${status.expression}" />" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
	

		
	

		
				
		
		

			<tr>
				<td class="dataLabel"> Start Date : </td>				
				<td class="dataField">
					<spring:bind path="formulariumForm.startDate">
						<input type="text" size="15" name="<c:out value="${status.expression}" />" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="10">
							<img src="images/jscalendar.gif" alt="Enter Date" id="<c:out value="${status.expression}"/>_trigger" align="absmiddle" height="20" width="20">
							<script type="text/javascript">
		    					Calendar.setup({
		        					inputField     :    "<c:out value="${status.expression}"/>",     // id of the input field
		        					ifFormat       :    "%Y-%m-%d",      // format of the input field
		        					button         :    "<c:out value="${status.expression}"/>_trigger",  // trigger for the calendar (button ID)
		        					align          :    "Tl",           // alignment (defaults to "Bl")
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
				<td class="dataLabel"> End Date : </td>				
				<td class="dataField">
					<spring:bind path="formulariumForm.endDate">
						<input type="text" size="15" name="<c:out value="${status.expression}" />" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="10">
						<img src="images/jscalendar.gif" alt="Enter Date" id="<c:out value="${status.expression}"/>_trigger" align="absmiddle" height="20" width="20">
						<script type="text/javascript">
	    					Calendar.setup({
	        					inputField     :    "<c:out value="${status.expression}"/>",     // id of the input field
	        					ifFormat       :    "%Y-%m-%d",      // format of the input field
	        					button         :    "<c:out value="${status.expression}"/>_trigger",  // trigger for the calendar (button ID)
	        					align          :    "Tl",           // alignment (defaults to "Bl")
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
				<td class="dataLabel"> Provider : </td>
				<td class="dataField">
					<spring:bind path="formulariumForm.providerId">
						<input type="hidden" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" >				
					</spring:bind>
					<input type="text" size="35" name="providerName" id="providerName" value="" >
				</td>
			    <td class="dataLabel"></td>
			    <td class="dataField"></td>
			</tr>
			<tr>
				<td class="dataLabel"> Client : </td>				
				<td class="dataField">
					<spring:bind path="formulariumForm.clientId">
						<input type="hidden" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" >					
					</spring:bind>
					<input type="text" size="35" name="clientName" id="clientName" value="" >
				</td>
			    <td class="dataLabel"></td>
			    <td class="dataField"></td>
			</tr>
			<tr>
				<td class="dataLabel"> Policy : </td>
				<td class="dataField">
					<spring:bind path="formulariumForm.policyId">
						<input type="hidden" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" >				
					</spring:bind>
					<input type="text" size="35" name="policyNumber" id="policyNumber" value="" >
				</td>
			    <td class="dataLabel"></td>
			    <td class="dataField"></td>
			</tr>
			<tr>
				<td class="dataLabel"> Description : </td>
			    <td class="dataField">
					<spring:bind path="formulariumForm.description">
						<textarea rows="5" cols="40" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
						<div id="fredcaption">
							<c:out value="${status.errorMessage}" />
						</div>
					</spring:bind>
				</td>
			    <td class="dataLabel"></td>
			    <td class="dataField"></td>
			</tr>
			<tr>
				<td class="dataLabel">&nbsp;</td>
				<td class="dataField"></td>
	    		<td class="dataLabel"></td>
	    		<td class="dataField"></td>
			</tr>		
				
		
	</tbody>
</table>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td style="padding-top: 2px;">
	    <input type="hidden" name="notyet" value="">
        <input title="Save [Alt+S]" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:simpan()" name="Save" value=" Save " type="submit">
        <input title="Cancel [Alt+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:cancel()" name="Cancel" value=" Cancel " type="submit">         
	  </td>
      <td align="right"></td>
    </tr>
  </tbody>
</table>
</form>

<script language="javascript">
	$(document).ready(function(){	 
    	$("#clientName").autocomplete("client?navigation=lookupjson", {
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
	            return  "<font color='#000' style='align: left;' >"+  row.name +"</font>" ;
	        }			             
		    }).bind("result", function(data, value){
		        $(this).parents("dd").find(".error_message").hide();
		        $("#clientId").val (value.id);
		        $("#clientName").val (value.name);			        
	   	});			
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
	            return  "<font color='#000' style='align: left;' >"+  row.name +"</font>" ;
	        }			             
		    }).bind("result", function(data, value){
		        $(this).parents("dd").find(".error_message").hide();
		        $("#providerId").val (value.id);
		        $("#providerName").val (value.name);			        
	   		}
	   );	
	   $("#policyNumber").autocomplete("policy?navigation=lookupjson", {
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
	            return  "<font color='#000' style='align: left;' >"+  row.name +"</font>" ;
	        }			             
		    }).bind("result", function(data, value){
		        $(this).parents("dd").find(".error_message").hide();
		        $("#policyId").val (value.id);
		        $("#policyNumber").val (value.name);			        
		   }
		);	
	});
	function simpan (){

		document.form1.method = "POST";
		document.form1.action = "formularium-form";
		document.form1.submit();
	}
	function kembali (){
		document.form1.navigation.value = "back";
		document.form1.action = "formularium";
		document.form1.submit();
	}
	// forreign affairs
			// forreign affairs end
</script>