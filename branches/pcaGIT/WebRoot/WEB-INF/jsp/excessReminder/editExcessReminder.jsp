<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>




<form action="excessreminder-form" method="POST"  name="form1" id="form_layout">
<input type="hidden" name="navigation" value="">
<%
String alert = (String) request.getAttribute("alert");
if (alert != null && !alert.trim().equals("")) {%>
	<div id="warning">
		<c:out value="${alert}"></c:out>
	</div>
	<%}%>
<spring:bind path="excessReminderForm.excessReminderId">
		<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>
<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>
		<tr>
			<td>
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<tbody>

					<tr>
						<td class="dataLabel"> </td>				
						<td class="dataField" colspan="3">
						</td>			    
					</tr>

			<tr>
				<td class="dataLabel"> Excess Charge : </td>				
				<td class="dataField">

				<spring:bind path="excessReminderForm.excessChargeNumber">
					<input type="text" readonly="readonly" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>">
					<div id="fredcaption">
						<c:out value="${status.errorMessage}" />
					</div>
				</spring:bind>			
				<spring:bind path="excessReminderForm.excessChargeId">
					<input type="hidden" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>">
					<div id="fredcaption">
						<c:out value="${status.errorMessage}" />
					</div>
				</spring:bind>		
				</td>
				<td class="dataLabel"> Reminder Date : </td>				
			

						<td class="dataField">
			<spring:bind path="excessReminderForm.reminderDate">
					<input type="text" tabindex="1" name="<c:out value="${status.expression}" />" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="10">
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
				<td class="dataLabel"> Reminder Counter : </td>				
				<td class="dataField">
				<spring:bind path="excessReminderForm.reminderCounter">
				<input type="text" readonly="readonly" size="35" name="<c:out value="${status.expression}" />" value="<fmt:formatNumber><c:out value="${status.value}"/></fmt:formatNumber>" maxlength="11">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
			

			<tr>
				<td class="dataLabel"> Reminder Remarks : </td>				
				<td class="dataField" colspan="3">
					<spring:bind path="excessReminderForm.reminderRemarks">
					<textarea tabindex="2" cols="60" rows="8" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
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
			<tr>
				<td class="dataLabel"> </td>				
				<td class="dataField" colspan="3">
				</td>			    
			</tr>
		</tbody>
		
</table>
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td style="padding-top: 2px; padding-left: 1px;">
	    <input type="hidden" name="notyet" value="">
        <input title="Save [Alt+Shift+S]" tabindex="3" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:simpan()" name="Save" value=" Save " type="submit">
        <input title="Cancel [Alt+Shift+C]" tabindex="4" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:cancel()" name="Cancel" value=" Cancel " type="button">         
	  </td>
      <td align="right"></td>
    </tr>
  </tbody>
</table>

</form>

<script language="javascript">
	function simpan (){

		document.form1.method = "POST";
		document.form1.action = "excessreminder-form";
		document.form1.submit();
	}
	function cancel(){
		document.form1.navigation.value = "detail";
		document.form1.action = "excesscharge";
		document.form1.submit();
	}
	// forreign affairs
			// forreign affairs end
</script>