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
<form action="policybillingrate-form" method="POST"  name="form1" id="form_layout">
			<spring:bind path="policyBillingRateForm.policyBillingRateId">
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
				<td class="dataLabel">Policy Number : </td>		
				<td class="dataField">
				<spring:bind path="policyBillingRateForm.policyId">
					<input type="hidden" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >					
				</spring:bind>	
				<spring:bind path="policyBillingRateForm.policyNumber">
					<input type="text" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" size="35">					
				</spring:bind>	
		
				</td>
			    <td class="dataLabel"></td>
			    <td class="dataField"></td>
			</tr>
				
		<tr>
			<td class="dataLabel"> Billing Rate Subject : </td>				
			<td class="dataField">
				<spring:bind path="policyBillingRateForm.billingRateSubject">
					<select name="<c:out value="${status.expression}" />">
						<option value="">-- PILIH SALAH SATU --</option>
						<option value="1" <c:if test="${status.value eq 1}">selected</c:if>>PER MEMBER</option>
						<option value="2" <c:if test="${status.value eq 2}">selected</c:if>>PER CLAIM</option>
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
			<td class="dataLabel"> Billing Rate Start Quantity : </td>				
			<td class="dataField">
				<spring:bind path="policyBillingRateForm.billingRateStartQuantity">
					<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<fmt:formatNumber><c:out value="${status.value}"/></fmt:formatNumber>" maxlength="5">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
				<td class="dataLabel"> Billing Rate End Quantity : </td>				
				<td class="dataField">
					<spring:bind path="policyBillingRateForm.billingRateEndQuantity">
						<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<fmt:formatNumber><c:out value="${status.value}"/></fmt:formatNumber>" maxlength="5">
						<div id="fredcaption">
							<c:out value="${status.errorMessage}" />
						</div>
					</spring:bind>
				</td>
			</tr>
			
			<tr>
				<td class="dataLabel"> Billing Rate : </td>				
				<td class="dataField">
					<spring:bind path="policyBillingRateForm.billingRate">
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
				<td class="dataLabel"> Claim Process Status Start : </td>				
				<td class="dataField">
					<spring:bind path="policyBillingRateForm.claimProcessStatusStart">
						<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<fmt:formatNumber><c:out value="${status.value}"/></fmt:formatNumber>" maxlength="5">
						<div id="fredcaption">
							<c:out value="${status.errorMessage}" />
						</div>
					</spring:bind>
					</td>
			    <td class="dataLabel"></td>
			    <td class="dataField"></td>
			</tr>				
			<tr>
				<td class="dataLabel">Item Id : </td>		
				<td class="dataField">
				<spring:bind path="policyBillingRateForm.itemId">
					<select name="<c:out value="${status.expression}" />">
						<option value="">-- PILIH SALAH SATU --</option>
						<c:forEach items="${itemList}" var="item">
							<option value="<c:out value="${item.itemId}" />" <c:if test="${item.itemId eq status.value}">selected</c:if>><c:out value="${item.itemName}" /></option>
						</c:forEach>
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
	
	
		
	function simpan (){

		document.form1.method = "POST";
		document.form1.action = "policybillingrate-form";
		document.form1.submit();
	}
	function kembali (){
		document.form1.method = "GET";
		document.form1.navigation.value = "detail";
		document.form1.action = "policy";
		document.form1.submit();
	}
	
</script>