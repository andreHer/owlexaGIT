<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<script type="text/javascript" src="scripts/owlexa.function.js"></script>	

<%
String alert = (String) request.getAttribute("alert");
if (alert != null && !alert.trim().equals("")) {%>
	<div id="warning">
		<c:out value="${alert}"></c:out>
	</div>
	<%}%>
<div id="warning">
		<c:out value="${alertService}"></c:out>
	</div>
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Add Service</h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>

<form action="providerservice-form" method="POST"  name="form1" id="form_layout">
	

	<spring:bind path="providerServiceForm.providerServiceId">
		<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
	</spring:bind>

	<input type="hidden" name="navigation" value="">

<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>
<tr>
				<td class="dataLabel">&nbsp;</td>
				<td class="dataField">&nbsp;		
				</td>
			    <td class="dataLabel"></td>
			    <td class="dataField"></td>
			</tr>
			<tr>
				<td class="dataLabel"> Provider : </td>
				<td class="dataField">
					<spring:bind path="providerServiceForm.providerId">
						<input type="hidden" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" maxlength="11">						
					</spring:bind>
					<spring:bind path="providerServiceForm.providerName">
						<input type="text" size=40 name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>">						
					</spring:bind>
					</td>
			    <td class="dataLabel"></td>
			    <td class="dataField"></td>
			</tr>
			<tr>
				<td class="dataLabel"> Case Service : </td>
				<td class="dataField">
					<spring:bind path="providerServiceForm.caseCategoryId">
						<select name="<c:out value="${status.expression}" />">
							<%--<option value=""> --- PILIH SALAH SATU --- </option> --%>
							<c:forEach items="${itemCategories}" var="service">
								<option value="<c:out value="${service.caseCategoryId}" />" <c:if test="${status.value eq service.caseCategoryId }">selected</c:if>><c:out value="${service.caseCategoryCode}" /> - <c:out value="${service.caseCategoryName}" /></option>
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
				<td class="dataLabel"> Discount Type : </td>
				<td class="dataField">				
					<spring:bind path="providerServiceForm.discountType">
						<select name="<c:out value="${status.expression }" />">
							<option value="0" >NO DISCOUNT</option>
							<option value="1" >GENERAL DISCOUNT</option>
							<option value="2" >PER COVERAGE DISCOUNT</option>
						</select>				
					</spring:bind>		 			
				</td>
			    <td class="dataLabel"></td>
			    <td class="dataField"></td>
			</tr>
			<tr>
				<td class="dataLabel"> Discount : </td>
				<td class="dataField">
					<spring:bind path="providerServiceForm.discount">
						<input onkeypress="return isNumberKey(this,event)" type="text" size=10 name="<c:out value="${status.expression}" />" value="<fmt:formatNumber><c:out value="${status.value}"/></fmt:formatNumber>" > &nbsp;%
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
				<td class="dataField">&nbsp;		
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
        <input title="Cancel [Alt+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:cancel()" name="Cancel" value=" Cancel " type="button">         
	  </td>
      <td align="right"></td>
    </tr>
  </tbody>
</table>
</form>

<script language="javascript">
	function simpan (){

		document.form1.method = "POST";
		document.form1.action = "providerservice-form";
		document.form1.submit();
	}
	function cancel (){
		document.form1.navigation.value = "listprovider";
		document.form1.action = "providerservice";
		document.form1.submit();
	}
	// forreign affairs
			// forreign affairs end
</script>