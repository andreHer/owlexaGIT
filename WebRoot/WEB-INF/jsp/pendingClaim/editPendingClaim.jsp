<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<form action="pendingclaim-form" method="POST"  name="form1" id="form_layout">
<spring:bind path="pendingClaimForm.pendingClaimId">
		<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>

<input type="hidden" name="navigation" value="">


<%
String alert = (String) request.getAttribute("alert");
if (alert != null && !alert.trim().equals("")) {%>
	<div id="warning">
		<c:out value="${alert}"></c:out>
	</div>
	<%}%>
<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>
		<tr>
			<td class="dataLabel"></td>
			<td class="dataField"></td>
			<td class="dataLabel"></td>
			<td class="dataField"></td>
		</tr>	


	

			<tr>
				<td class="dataLabel"> Claim Number : </td>				
					
			<td class="dataField">
			<spring:bind path="pendingClaimForm.claimId">
					<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>">
			</spring:bind>
			<spring:bind path="pendingClaimForm.claimNumber">
					<input type="text" size="35" readonly="readonly" name="cl" value="<c:out value="${status.value}" />">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
		<td class="dataLabel"> Pending Category : </td>				
				<td class="dataField">
			<spring:bind path="pendingClaimForm.pendingCategory">
				<select name="<c:out value="${status.expression}" />">
					<c:forEach items="${pendingCategory}" var="pc">
						<option value="<c:out value="${pc.pendingCategoryId}"/>" <c:if test="${pc.pendingCategoryId eq status.value}">selected</c:if>><c:out value="${pc.pendingCategoryName}" /></option>
					</c:forEach>
				</select>
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
	</tr>

			
			


			

			<tr>
				<td class="dataLabel"> Pending Subject : </td>				
			

			<td class="dataField">
			<spring:bind path="pendingClaimForm.pendingSubject">
			<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
<td class="dataLabel"> Pending Due Date : </td>				
			

						<td class="dataField">
			<spring:bind path="pendingClaimForm.pendingDueDate">
					<input type="text" readonly="readonly" name="<c:out value="${status.expression}" />" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="10">
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
	</tr>
	


			

			<tr>
				<td class="dataLabel"> Remarks : </td>				
			

		    <td class="dataField" colspan="3">
			<spring:bind path="pendingClaimForm.remarks">
			<textarea  cols="60" rows="8" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>

	</tr>
		

<tr>
								<td class="dataLabel">
									
								</td>


								<td class="dataField">
									
								</td>
								<td class="dataLabel">
								
								</td>
								<td class="dataField">
									
									
								</td>
							</tr>	


	</tbody>
</table>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td style="padding-top: 2px;">
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
		document.form1.action = "pendingclaim-form";
		document.form1.submit();
	}
	function cancel (){
		document.form1.navigation.value = "extract";
		document.form1.action = "pendingclaim";
		document.form1.submit();
	}
	// forreign affairs
			// forreign affairs end
</script>