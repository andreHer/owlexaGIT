



<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


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
			<td nowrap="nowrap">
				<h3>
					<c:choose>
						<c:when test="${navigation eq 'update-provider-configuration'}">
							<img src="images/h3Arrow.gif" border="0">&nbsp;Update Provider Configuration
						</c:when>
					</c:choose>	
				</h3>
			</td>
			<td width="100%"><img src="images/blank.gif" height="1"
				width="1">
			</td>
		</tr>
	</tbody>
</table>
<form action="configuration-form" method="POST"  name="form1" id="form_layout">
		<spring:bind path="configurationForm.configurationId">
			<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
		</spring:bind>

<input type="hidden" name="navigation" value="${navigation }">
<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>
		<tr>
			<td class="dataLabel"></td>				
			<td class="dataField"></td>
		    <td class="dataLabel"></td>
		    <td class="dataField"></td>
		</tr>
		<tr>
			<td class="dataLabel"> Edc Alert Default : </td>				
			<td class="dataField">
				<spring:bind path="configurationForm.edcAlertGlobal">
					<input type="text" size="35" name="<c:out value="${status.expression}" />" 
					onkeypress="return isNumberKey(this,event)" value="<c:out value="${status.value}" />" maxlength="22">	
				</spring:bind>
			</td>
		    <td class="dataLabel"></td>
		    <td class="dataField"></td>
		</tr>
		<tr>
			<td class="dataLabel"> Roll Paper Length (cm) : </td>				
			<td class="dataField">
				<spring:bind path="configurationForm.providerRollPaperLength">
					<input type="text" size="35" name="<c:out value="${status.expression}" />" 
					onkeypress="return isNumberKey(this,event)" value="<c:out value="${status.value}" />" maxlength="22">	
				</spring:bind>
			</td>
		    <td class="dataLabel"></td>
		    <td class="dataField"></td>
		</tr>
		<tr>
			<td class="dataLabel"> Limit Roll Paper Alert Default : </td>				
			<td class="dataField">
				<spring:bind path="configurationForm.providerRollPaperLimit">
					<input type="text" size="35" name="<c:out value="${status.expression}" />" 
					onkeypress="return isNumberKey(this,event)" value="<c:out value="${status.value}" />" maxlength="22">	
				</spring:bind>
			</td>
		    <td class="dataLabel"></td>
		    <td class="dataField"></td>
		</tr>
		<tr>
			<td class="dataLabel"> Register Roll Paper Limit Default (cm) : </td>				
			<td class="dataField">
				<spring:bind path="configurationForm.providerRollPaperRegister">
					<input type="text" size="35" name="<c:out value="${status.expression}" />" 
					onkeypress="return isNumberKey(this,event)" value="<c:out value="${status.value}" />" maxlength="22">	
				</spring:bind>
			</td>
		    <td class="dataLabel"></td>
		    <td class="dataField"></td>
		</tr>
		<tr>
			<td class="dataLabel"> Payment Roll Paper Limit Default (cm) : </td>				
			<td class="dataField">
				<spring:bind path="configurationForm.providerRollPaperPayment">
					<input type="text" size="35" name="<c:out value="${status.expression}" />" 
					onkeypress="return isNumberKey(this,event)" value="<c:out value="${status.value}" />" maxlength="22">	
				</spring:bind>
			</td>
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
	function simpan (){

		document.form1.method = "POST";
		document.form1.action = "configuration-form";
		document.form1.submit();
	}
	function kembali (){
		document.form1.navigation.value = "back";
		document.form1.action = "configuration";
		document.form1.submit();
	}
	// forreign affairs
		function lookupClient (){
		window.open ("client?navigation=lookup&url=configuration-form","Search","width=700, height=550, menubar=no, status=no, toolbar=no, scrollbars=yes, resizable=yes,screenX=200,screenY=100");
	}
	function setClient (idx,val){
		document.form1.method = "POST";
		document.form1.notyet.value="true";
		document.form1.action = "configuration-form";
		//document.form1.configurationForm.clientId.value=idx;
		document.getElementById("clientId").value = idx;
		document.getElementById("clientIdId").value = val;
		//document.form1.submit();
		}
				// forreign affairs end
</script>