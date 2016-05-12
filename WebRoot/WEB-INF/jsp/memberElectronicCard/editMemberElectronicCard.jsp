



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



<form action="memberelectroniccard-form" method="POST"  name="form1" id="form_layout">
	<spring:bind path="memberElectronicCardForm.id">
		<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
	</spring:bind>
	<spring:bind path="memberElectronicCardForm.memberId">
		<input type="hidden" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" maxlength="11">		
	</spring:bind>
	<input type="hidden" name="navigation" value="">


<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>

		<tr>
			<td class="dataLabel">&nbsp;</td>
			<td class="dataField"></td>
		    <td class="dataLabel"></td>
		    <td class="dataField"></td>
		</tr>		
		<tr>
			<td class="dataLabel"> Member Name : </td>
			<td class="dataField">
				<spring:bind path="memberElectronicCardForm.memberName">
					<input type=text width=45  name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" />				
				</spring:bind>
			</td>
		    <td class="dataLabel"></td>
		    <td class="dataField"></td>
		</tr>
		<tr>
			<td class="dataLabel"> Member Number : </td>
			<td class="dataField">
				<spring:bind path="memberElectronicCardForm.memberNumber">
					<input type=text width=45  name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" />				
				</spring:bind>
			</td>
		    <td class="dataLabel"></td>
		    <td class="dataField"></td>
		</tr>
		<tr>
			<td class="dataLabel"> Card Number : </td>
			<td class="dataField">
				<spring:bind path="memberElectronicCardForm.cardNumber">
					<input type=text width=45  name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" />				
				</spring:bind>
			</td>
		    <td class="dataLabel"></td>
		    <td class="dataField"></td>
		</tr>
		<tr>
			<td class="dataLabel"> Card Status : </td>
			<td class="dataField">
				<spring:bind path="memberElectronicCardForm.cardStatus">
					<select name="<c:out value="${status.expression}" />">
						<option value="-1" <c:if test="${status.value eq -1 }">selected</c:if>>PENDING</option>
						<option value="1" <c:if test="${status.value eq 1 }">selected</c:if>>ACTIVE</option>
						<option value="2" <c:if test="${status.value eq 2 }">selected</c:if>>BLOCKED</option>
						<option value="3" <c:if test="${status.value eq 3 }">selected</c:if>>EXPIRED</option>
					</select>
				</spring:bind>
			</td>
		    <td class="dataLabel"></td>
		    <td class="dataField"></td>
		</tr>
		
		<tr>
			<td class="dataLabel"> Replaced Card : </td>				
			<td class="dataField">
				<spring:bind path="memberElectronicCardForm.replacedCardId">
					<select name="<c:out value="${status.expression}" />">
						<option value="">-- SELECT ONE --</option>
						<c:forEach items="${cardList}" var="card">
							<option value="<c:out value="${card.id}" />" <c:if test="${status.value eq card.id }">selected</c:if>><c:out value="${card.cardNumber}" /></option>
						</c:forEach>
					</select>	
				</spring:bind>
			</td>
		    <td class="dataLabel"></td>
		    <td class="dataField"></td>
		</tr>		

		<tr>
			<td class="dataLabel"> Card Type : </td>				
			<td class="dataField">
				<spring:bind path="memberElectronicCardForm.cardType">
					<select name="<c:out value="${status.expression}" />">
						<option value="">-- SELECT ONE --</option>
						<c:forEach items="${cardTypeList}" var="card">
							<option value="<c:out value="${card.cardTypeId}" />" <c:if test="${status.value eq card.cardTypeId }">selected</c:if>><c:out value="${card.cardTypeName}" /></option>
						</c:forEach>
					</select>					
				</spring:bind>
			</td>
			<td class="dataLabel"></td>
			<td class="dataField"></td>
		</tr>
			

	
	
			<tr>
				<td class="dataLabel"> Registration Date : </td>				
			

						<td class="dataField">
			<spring:bind path="memberElectronicCardForm.registrationDate">
					<input type="text" size="15" name="<c:out value="${status.expression}" />" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="10">
					<img src="images/jscalendar.gif" alt="Enter Date" id="<c:out value="${status.expression}"/>_trigger" align="absmiddle" height="20" width="20">
					<script type="text/javascript">
    					Calendar.setup({
        					inputField     :    "<c:out value="${status.expression}"/>",     // id of the input field
        					ifFormat       :    "%Y-%m-%d",      // format of the input field
        					button         :    "<c:out value="${status.expression}"/>_trigger",  // trigger for the calendar (button ID)
        					align          :    "Bl",           // alignment (defaults to "Bl")
        					singleClick    :    true
    					});
				 	</script>
				</spring:bind>
			</td>
		    <td class="dataLabel"></td>
		    <td class="dataField"></td>
		</tr>
		<tr>
			<td class="dataLabel"> Description : </td>
			<td class="dataField">
				<spring:bind path="memberElectronicCardForm.description">
					<textarea  cols="60" rows="8" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
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
        <input title="Cancel [Alt+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:kembali()" name="Cancel" value=" Cancel " type="button">         
	  </td>
      <td align="right"></td>
    </tr>
  </tbody>
</table>
</form>

<script language="javascript">
	function simpan (){

		document.form1.method = "POST";
		document.form1.action = "memberelectroniccard-form";
		document.form1.submit();
	}
	function kembali (){
		document.form1.navigation.value = "list";
		document.form1.action = "memberelectroniccard";
		document.form1.submit();
	}
	// forreign affairs
			// forreign affairs end
</script>