<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<form action="benefit-form" method="POST"  name="form1" id="form_layout">
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td style="padding-bottom: 2px;">
	    <input type="hidden" name="notyet" value="">
        <input title="Save [Alt+Shift+S]" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:simpan()" name="Save" value=" Save " type="submit">
        <input title="Cancel [Alt+Shift+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:cancel()" name="Cancel" value=" Cancel " type="button">         
	  </td>
      <td align="right"></td>
    </tr>
  </tbody>
</table>

<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>
		<tr>
			<td>
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<tbody>


<%
String alert = (String) request.getAttribute("alert");
if (alert != null && !alert.trim().equals("")) {%>
	<div id="warning">
		<c:out value="${alert}"></c:out>
	</div>
	<%}%>


	<spring:bind path="benefitForm.benefitId">
		<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>

<input type="hidden" name="navigation" value="">





			<tr>
				<td class="dataLabel"> Benefit Name : </td>				
			

			<td class="dataField">
			<spring:bind path="benefitForm.benefitName">
		<input type="text" size="35" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" />	
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
	


			
			

			<tr>
				<td class="dataLabel"> Benefit Value : </td>				
			
			<td class="dataField">
			<spring:bind path="benefitForm.benefitValue">
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
				<td class="dataLabel"> Benefit Type : </td>				
			

						<td class="dataField">
			<spring:bind path="benefitForm.benefitType">
	<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<fmt:formatNumber><c:out value="${status.value}"/></fmt:formatNumber>" maxlength="11">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
			


			

			<tr>
				<td class="dataLabel"> Benefit Category Id : </td>				
			

						<td class="dataField">
			<spring:bind path="benefitForm.benefitCategoryId">
	<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<fmt:formatNumber><c:out value="${status.value}"/></fmt:formatNumber>" maxlength="11">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
			


			

			<tr>
				<td class="dataLabel"> Benefit Measurement Unit : </td>				
			

			<td class="dataField">
			<spring:bind path="benefitForm.benefitMeasurementUnit">
				<select name="">
					<c:forEach items="${measurementUnit}" var="mu">
						<option value="${mu.measurementUnitId}" <c:if test="${status.value eq mu.measurementUnitId}" >selected</c:if> ><c:out value="${mu.measurementUnitName}" /></option>
					</c:forEach>
				</select>
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
				<td class="dataLabel"> Benefit Max Occurance : </td>				
			
			<td class="dataField">
			<spring:bind path="benefitForm.benefitMaxOccurance">
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
				<td class="dataLabel"> Benefit Occurance Unit : </td>				
			<td class="dataField">
			<spring:bind path="benefitForm.benefitOccuranceUnit">
		<input type="text" size="35" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" />	
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
	

			<tr>
				<td class="dataLabel"> Benefit Description : </td>				
			

			<td class="dataField" colspan="3">
			<spring:bind path="benefitForm.benefitDescription">
			<textarea  cols="60" rows="8" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>

	</tr>
	





						</tbody>
				</table>
			</td>
		</tr>
	</tbody>
</table>

</form>

<script language="javascript">
	function simpan (){

		document.form1.method = "POST";
		document.form1.action = "benefit-form";
		document.form1.submit();
	}
	function kembali (){
		document.form1.navigation.value = "back";
		document.form1.action = "benefit";
		document.form1.submit();
	}
	// forreign affairs
			// forreign affairs end
</script>