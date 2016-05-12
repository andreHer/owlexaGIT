
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<script type="text/javascript" src="scripts/jquery/jquery-1.4.4.min.js"></script>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Add EDC Terminal</h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>

<%
String alert = (String) request.getAttribute("alert");
if (alert != null && !alert.trim().equals("")) {%>
	<div id="warning">
		<c:out value="${alert}"></c:out>
	</div>
	<%}%>
<form action="edcterminal-form" method="POST"  name="form1" id="form_layout">
<input type="hidden" name="navigation" value="">

<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>
		<tr>
			<td>
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<tbody>
						<spring:bind path="edcTerminalForm.id">
							<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >			
						</spring:bind>
						<tr>
							<td class="dataLabel"></td>
					    	<td class="dataField"></td>
				    		<td class="dataLabel"></td>
				    		<td class="dataField"></td>
						</tr>
						<tr>
							<td class="dataLabel"> Provider : </td>				
								<spring:bind path="edcTerminalForm.providerId">
									<input type="hidden"  name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>">									
								</spring:bind>
							
							<td class="dataField">
								<spring:bind path="edcTerminalForm.providerName">
									<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>">
									<div id="fredcaption">
										<c:out value="${status.errorMessage}" />
									</div>
								</spring:bind>
							</td>
						    <td class="dataLabel"></td>
						    <td class="dataField"></td>
						</tr>
						<tr>
							<td class="dataLabel"> Location * : </td>
		    				<td class="dataField">
								<spring:bind path="edcTerminalForm.location">
								<input type="text" size=35 class="inputbox" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" />
									<div id="fredcaption">
										<c:out value="${status.errorMessage}" />
									</div>
								</spring:bind>
							</td>
						    <td class="dataLabel"></td>
						    <td class="dataField"></td>
						</tr>				
						<tr>
							<td class="dataLabel"> Device Number * : </td>
							<td class="dataField">
								<spring:bind path="edcTerminalForm.deviceNumber">
								<input type="text" size=35 class="inputbox" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" />
									<div id="fredcaption">
										<c:out value="${status.errorMessage}" />
									</div>
									<%
										String alertEdc = (String) request.getAttribute("alertEdc");
										//String alertEdcProvider = (String) request.getAttribute("alertEdcTerminal");
										if (alertEdc != null && !alertEdc.trim().equals("")) {
									%>
									<div id="warning">
										<input type="hidden" id="alertEdcProv" name="alertEdcProv" value="<c:out value="${alertEdcTerminal}" />">
										<c:out value="${alertEdc}"></c:out>
									</div>
									<%
										}
									%>
								</spring:bind>
							</td>
						    <td class="dataLabel"></td>
						    <td class="dataField"></td>
						</tr>
						<tr>
							<td class="dataLabel"> Authorization Key : </td>				
						    <td class="dataField">
								<spring:bind path="edcTerminalForm.authorizationKey">
								<input type="text" size=35 class="inputbox" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" />
									<div id="fredcaption">
										<c:out value="${status.errorMessage}" />
									</div>
								</spring:bind>
								</td>
						    <td class="dataLabel"></td>
						    <td class="dataField"></td>
						</tr>
						<tr>
							<td class="dataLabel"> Description : </td>
						    <td class="dataField">
								<spring:bind path="edcTerminalForm.description">
									<textarea cols="45" rows="8" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
									<div id="fredcaption">
										<c:out value="${status.errorMessage}" />
									</div>
								</spring:bind>
							</td>
						    <td class="dataLabel"></td>
						    <td class="dataField"></td>
						</tr>
						<tr>
							<td class="dataLabel"></td>
		    				<td class="dataField"></td>
						    <td class="dataLabel"></td>
						    <td class="dataField"></td>
						</tr>
						<tr>
							<td class="dataLabel">EDC Fault : </td>
							<td class="dataField">
								<%--
								<spring:bind path="edcTerminalForm.isFault">
									<input type="checkbox" name="<c:out value="${status.expression}" />" value="1" <c:if test="${status.value eq '1' }">checked</c:if>>
								</spring:bind>
								 --%>
								 <spring:bind path="edcTerminalForm.isFault">
									<select name="<c:out value="${status.expression}"/>">
										<option value="0" <c:if test="${status.value eq 0}">selected</c:if>>NOT FAULT</option>
										<option value="1" <c:if test="${status.value eq 1}">selected</c:if>>DEFECTIVE</option>				
										<option value="2" <c:if test="${status.value eq 2}">selected</c:if>>LOST SIGNAL</option>
										<option value="3" <c:if test="${status.value eq 3}">selected</c:if>>EDC BELUM SAMPAI</option>
										<option value="4" <c:if test="${status.value eq 4}">selected</c:if>>OTHER</option>
									</select>
									<div id="fredcaption">
										<c:out value="${status.errorMessage}" />
									</div>
								</spring:bind>
							</td>
							<td class="dataLabel"></td>
						    <td class="dataField"></td>
						</tr>
						<tr>
							<td class="dataLabel"></td>
		    				<td class="dataField"></td>
						    <td class="dataLabel"></td>
						    <td class="dataField"></td>
						</tr>
					</tbody>
				</table>
			</td>
		</tr>
	</tbody>
</table>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td style="padding-top: 2px;">
	    <input type="hidden" name="notyet" value="">
        <input title="Save [Alt+S]" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:simpan()" name="Save" value=" Save " type="submit">
        <input title="Cancel [Alt+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:kembali()" name="Cancel" value=" Cancel " type="submit">         
	  </td>
      <td align="right"></td>
    </tr>
  </tbody>
</table>
</form>

<script language="javascript">

	$(document).ready(function(){
		var temp = $("#alertEdcProv").val();
		if($("#alertEdcProv").val() != null || temp.length > 0)
			alert($("#alertEdcProv").val());
	});
	
	function simpan (){

		document.form1.method = "POST";
		document.form1.action = "edcterminal-form";
		document.form1.submit();
	}
	function kembali (){
		document.form1.navigation.value = "list";
		document.form1.action = "edcterminal";
		document.form1.submit();
	}
	
</script>