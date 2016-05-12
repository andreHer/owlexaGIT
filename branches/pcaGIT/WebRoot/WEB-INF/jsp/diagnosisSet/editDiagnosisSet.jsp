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

<form action="diagnosisset-form" method="POST"  name="form1" id="form_layout">
	<spring:bind path="diagnosisSetForm.diagnosisSetId">
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
			<td class="dataLabel"> Diagnosis Set Name : </td>
			<td class="dataField">
				<spring:bind path="diagnosisSetForm.diagnosisSetName">
					<input type="text" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" size="35">
					<div id="fredcaption">
						<c:out value="${status.errorMessage}" />
					</div>
				</spring:bind>
			</td>
		    <td class="dataLabel"></td>
		    <td class="dataField"></td>
		</tr>
		<tr>
			<td class="dataLabel"> Diagnosis Set Code : </td>
			<td class="dataField">
				<spring:bind path="diagnosisSetForm.diagnosisSetCode">
					<input type="text" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" size="25">			
					<div id="fredcaption">
						<c:out value="${status.errorMessage}" />
					</div>
				</spring:bind>
			</td>
		    <td class="dataLabel"></td>
		    <td class="dataField"></td>
		</tr>
		<tr>
			<td class="dataLabel">Client : </td>		
			<td class="dataField">
				<spring:bind path="diagnosisSetForm.clientId">
				<input type="hidden" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
					<div id="fredcaption">
						<c:out value="${status.errorMessage}" />
					</div>
				</spring:bind>
				<spring:bind path="diagnosisSetForm.clientName">	
					<input type="text" size=40 name="<c:out value="${status.expression}" />" id="clientName" value="<c:out value="${status.value}" />" >
				</spring:bind>
			</td>
		    <td class="dataLabel"></td>
		    <td class="dataField"></td>
		</tr>

		
				
		
		

			<tr>
				<td class="dataLabel"> Description : </td>				
			

		    <td class="dataField">
			<spring:bind path="diagnosisSetForm.description">
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
	            return "<font color='#000' >"+ row.name +"</font>" ;
	        }
	    }).bind("result", function(data, value){
	        $(this).parents("dd").find(".error_message").hide();
	        $("#clientId").val(value.id);
	    });
	});
	function simpan (){

		document.form1.method = "POST";
		document.form1.action = "diagnosisset-form";
		document.form1.submit();
	}
	function kembali (){
		document.form1.navigation.value = "back";
		document.form1.action = "diagnosisset";
		document.form1.submit();
	}
	
</script>