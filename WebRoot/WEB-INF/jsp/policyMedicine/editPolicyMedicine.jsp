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
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Upload Medicine</h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>	
<form action="policymedicine-form" method="POST"  name="form1" id="form_layout">

		<spring:bind path="policyMedicineForm.policyMedicineId">
			<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
		</spring:bind>
		
<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>
		<tr>
			<td class="dataLabel">&nbsp;</td>		
			<td class="dataField"></td>
		    <td class="dataLabel"></td>
		    <td class="dataField"></td>
		</tr>

			<input type="hidden" name="navigation" value="<c:out value="${navigation}" />" >
			<input type="hidden" name="policyId" value="<c:out value="${policyId}" />" >
			
			<tr>
				<td class="dataLabel"> Item Value : </td>				
			
			<td class="dataField">
			<spring:bind path="policyMedicineForm.itemValue">
					<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<fmt:formatNumber><c:out value="${status.value}" /></fmt:formatNumber>" maxlength="22">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
				<td class="dataLabel"> Reference Price : </td>				
			
			<td class="dataField">
			<spring:bind path="policyMedicineForm.referencePrice">
					<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<fmt:formatNumber><c:out value="${status.value}" /></fmt:formatNumber>" maxlength="22">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
	
	<tr>
		<td class="dataLabel">Policy : </td>		
		<td class="dataField">
		<spring:bind path="policyMedicineForm.policyId">
		<input type="hidden" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>	
		<input type="text" name="policyIdText" id="policyIdId" value="" >

		<input type="button" name="PILIH" class="btn" value="pilih" onClick="javascript:lookupPolicy()">
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
	<tr>
		<td class="dataLabel"> Description : </td>
		<td class="dataField">
			<spring:bind path="policyMedicineForm.description">
				<textarea class="inputbox" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>		
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
	function simpan (){
		<c:choose>
			<c:when test="${navigation eq 'upload'}">
					document.form1.action = "policymedicine-form";
					document.form1.navigation.value = "upload";		
					document.form1.policyId.value = <c:out value="${policyId}" />				
			</c:when>
		</c:choose>
		document.form1.method = "POST";
		document.form1.submit();
	}
	function cancel (){
		<c:choose>
			<c:when test="${navigation eq 'upload'}">
					document.form1.action = "policymedicine";
					document.form1.navigation.value = "listpolicy";		
					document.form1.policyId.value = <c:out value="${policyId}" />				
			</c:when>
		</c:choose>
// 		document.form1.action = "policymedicine";
		document.form1.submit();
	}
	
</script>