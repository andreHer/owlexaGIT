

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
<form action="policycoveragefund-form" method="POST"  name="form1" id="form_layout">
			<spring:bind path="policyCoverageFundForm.policyCoverageFundId">
			<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
		</spring:bind>
		<spring:bind path="policyCoverageFundForm.policyId">
			<input type="hidden" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" maxlength="10">			
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
				<td class="dataLabel"> Case Category : </td>				
			

						<td class="dataField">
			<spring:bind path="policyCoverageFundForm.caseCategoryId">
				<select name="<c:out value="${status.expression}" />">
					<option value="">-- SELECT ONE --</option>
					<c:forEach items="${caseCategoryList}" var="cc">
						<option value="<c:out value="${cc.caseCategoryId}" />" <c:if test="${status.value eq cc.caseCategoryId}">selected</c:if>><c:out value="${cc.caseCategoryName}" /></option>
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
				<td class="dataLabel"> Initial Fund Value : </td>				
			
			<td class="dataField">
				<spring:bind path="policyCoverageFundForm.initialAsoFundValue">
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
				<td class="dataLabel"> Initial Excess Value : </td>				
			
			<td class="dataField">
			<spring:bind path="policyCoverageFundForm.initialExcessFundValue">
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
				<td class="dataLabel"> Fund Warning % : </td>				
			
			<td class="dataField">
				<spring:bind path="policyCoverageFundForm.fundWarningPercentage">
					<input type="text" size="15" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="22"> %
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>

			<tr>
				<td class="dataLabel"> Fund Blocked % : </td>				
			
			<td class="dataField">
			<spring:bind path="policyCoverageFundForm.blockWarningPercentage">
					<input type="text" size="15" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="22"> %
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
		document.form1.action = "policycoveragefund-form";
		document.form1.submit();
	}
	function kembali (){
		document.form1.navigation.value = "back";
		document.form1.action = "policycoveragefund";
		document.form1.submit();
	}
	// forreign affairs
	
		// forreign affairs end
</script>