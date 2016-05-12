



<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>




<form action="rejectedcase-form" method="POST"  name="form1" id="form_layout">
<%
String alert = (String) request.getAttribute("alert");
if (alert != null && !alert.trim().equals("")) {%>
	<div id="warning">
		<c:out value="${alert}"></c:out>
	</div>
	<%}%>
&nbsp;

<input type="hidden" name="navigation" value="">
<spring:bind path="rejectedCaseForm.caseId">
	<input type="hidden" name="caseId" value="<c:out value="${status.value}" />">
</spring:bind>
<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>
		<tr>
			<td>
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<tbody>



	<spring:bind path="rejectedCaseForm.rejectedCaseId">
		<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>



	<tr>
		<td class="dataLabel"></td>		
		<td class="dataField">
		
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
	
	<tr>
		<td class="dataLabel">Case : </td>		
		<td class="dataField">
		<spring:bind path="rejectedCaseForm.caseNumber">
		<input readonly type="text" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="11">
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>	
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>



			<tr>
				<td class="dataLabel"> Rejection Date : </td>				
			

						<td class="dataField">
			<spring:bind path="rejectedCaseForm.rejectionDate">
					<input type="text" name="<c:out value="${status.expression}" />" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="10">
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
				<td class="dataLabel"> Category : </td>				
			

						<td class="dataField">
			<spring:bind path="rejectedCaseForm.categoryId">
					<select name="<c:out value="${status.expression}" />">
						<option value="-1">-- SELECT ONE --</option>
						<c:forEach items="${rejectCategories}" var="category">
							<option value="<c:out value="${category.rejectCategoryId}" />" <c:if test="${status.value eq category.rejectCategoryId }">selected</c:if>><c:out value="${category.rejectCategoryName}" /></option>
						</c:forEach>
					</select>
			</spring:bind>
			</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
			<tr>
				<td class="dataLabel"> Estimated Cost : </td>				
			
			<td class="dataField">
			<spring:bind path="rejectedCaseForm.estimatedCost">
					<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="22">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
		<tr>
				<td class="dataLabel"> Rejection Description : </td>				
			

		    <td class="dataField" colspan="3">
			<spring:bind path="rejectedCaseForm.rejectionDescription">
			<textarea cols="60" rows="8" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>

	</tr>
		
<tr>
				<td class="dataLabel">&nbsp;</td>				
			

		    <td class="dataField" colspan="3">

		</td>

	</tr>

						</tbody>
				</table>
			</td>
		</tr>
	</tbody>
</table>

<br />

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

</form>

<script language="javascript">
	function simpan (){

		document.form1.method = "POST";
		document.form1.action = "rejectedcase-form";
		document.form1.submit();
	}
	function cancel(){
		document.form1.navigation.value = "detail";
		document.form1.action = "case";
		document.form1.submit();
	}
	
</script>