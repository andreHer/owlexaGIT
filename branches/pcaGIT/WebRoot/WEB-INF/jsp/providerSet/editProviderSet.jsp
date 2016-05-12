

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

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
	
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
	  <c:choose>
		<c:when test="${navigation eq 'tambah'}">
     		<td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Create Provider Set Profile</h3></td>
     	</c:when>
	  </c:choose>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>
	
<form action="providerset-form" method="POST"  name="form1" id="form_layout">
			<spring:bind path="providerSetForm.providerSetId">
			<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
		</spring:bind>

		<input type="hidden" name="navigation" value="">
<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>

		<tr>
			<td class="dataLabel">&nbsp;</td>
			<td class="dataField">&nbsp;</td>
		    <td class="dataLabel"></td>
		    <td class="dataField"></td>
		</tr>

			<tr>
				<td class="dataLabel"> Provider Set Name : </td>				
			
	
						<td class="dataField">
				<spring:bind path="providerSetForm.providerSetName">
				<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" >
					<div id="fredcaption">
						<c:out value="${status.errorMessage}" />
					</div>
				</spring:bind>
			</td>
			<td class="dataLabel"></td>
			<td class="dataField"></td>
		</tr>
			

		
				
		
		

			<tr>
				<td class="dataLabel"> Provider Set Code : </td>				
			
	
						<td class="dataField">
				<spring:bind path="providerSetForm.providerSetCode">
				<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" >
					<div id="fredcaption">
						<c:out value="${status.errorMessage}" />
					</div>
				</spring:bind>
			</td>
			<td class="dataLabel"></td>
			<td class="dataField"></td>
		</tr>
			
		<c:if test="${theUser.userType eq 2}">
			<tr>
				<td class="dataLabel"> Client : </td>
				<td class="dataField">
					<spring:bind path="providerSetForm.clientId">
						<input type="hidden" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" id="clientId">
					</spring:bind>
					<spring:bind path="providerSetForm.clientName">
						<input type="text" size=45 name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" id="clientName">
					</spring:bind>
				</td>
			    <td class="dataLabel"></td>
			    <td class="dataField"></td>
			</tr>
		</c:if>
		
		<tr>
			<td class="dataLabel"> Member Group : </td>				
			<td class="dataField">
				<spring:bind path="providerSetForm.memberGroupId">
					<input type="hidden" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" id="groupId">	
				</spring:bind>
				<spring:bind path="providerSetForm.groupName">
					<input type="text" size=45 name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" id="groupName">	
				</spring:bind>
			</td>
		    <td class="dataLabel"></td>
		    <td class="dataField"></td>
		</tr>
		<tr>
			<td class="dataLabel"> Description : </td>				
			<td class="dataField">
				<spring:bind path="providerSetForm.description">
					<textarea  cols="60" rows="8" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
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
			<td class="dataField">&nbsp;</td>
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
	        },
	        extraParams: {
       			clientId: function() { return $("#clientId").val(); }
   			}    			             
		    }).bind("result", function(data, value){
		        $(this).parents("dd").find(".error_message").hide();
		        $("#clientId").val (value.id);
		   });
			
		$("#groupName").autocomplete("membergroup?navigation=lookupjson", {
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
		   });
	});
	
	function simpan (){
		document.form1.navigation.value = "tambah";
		document.form1.action = "providerset-form";
		document.form1.submit();
	}
	function cancel(){
		document.form1.action = "providerset";
		document.form1.submit();
	}
	// forreign affairs
	
		// forreign affairs end
</script>