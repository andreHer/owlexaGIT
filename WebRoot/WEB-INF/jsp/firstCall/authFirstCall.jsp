<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>

<script type="text/javascript" language="Javascript" src="scripts/clockinput.js">
</script>

<form action="firstcall-form" method="POST" name="form1" id="form_layout">


	<p>
	<table class="moduleTitle" border="0" cellpadding="0" cellspacing="0" width="100%">
		<tbody>
			<tr>
				<td valign="top">
					<img src="images/Tasks.gif" style="margin-top: 3px;" alt="Tasks: " border="0" height="16" width="16">					
						First Call :					
				</td>				
				<td style="padding-top: 3px; padding-left: 5px;" align="right" nowrap="nowrap" valign="top">
					&nbsp;<a href="" target="_blank" class="utilsLink"><img src="images/help.gif" alt="Help" align="absmiddle" border="0" height="13" width="13"></a>&nbsp;<a href="" target="_blank" class="utilsLink">Help</a>
				</td>
			</tr>
		</tbody>
	</table>




	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tbody>
			<tr>
				<td style="padding-bottom: 2px;">
					<input type="hidden" name="notyet" value="">
					<input title="Save [Alt+Shift+S]" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:save()" name="Save" value=" Save " type="submit">
					<input title="Cancel [Alt+Shift+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:cancel()" name="Cancel" value=" Cancel " type="button">
				
					<td align="right" nowrap="nowrap">
						<span class="required">*</span> Indicates required field
					</td>
				
				<td align="right">
				</td>
			</tr>
		</tbody>
	</table>

	<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
		<tbody>
			<tr>
				<td>
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tbody>

							<%String alert = (String) request.getAttribute("alert");
			if (alert != null && !alert.trim().equals("")) {%>
							<div id="warning">
								<c:out value="${alert}"></c:out>
							</div>
							<%}%>


							<spring:bind path="firstCallForm.callId">
								<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />">
								<div id="fredcaption">
									<c:out value="${status.errorMessage}" />
								</div>
							</spring:bind>

							<input type="hidden" name="navigation" value="">
						<tbody>
						
						
						
							<tr>
							
							
							
								<td class="dataLabel" width="19%">
									
									Policy Number : <span class="required">*</span>
									
								</td>
								<td class="dataField" width="31%">
									

									<input type="text" readonly="readonly" value="<c:out value="${firstCallForm.firstCall.customerId.customerPolicyNumber}"/>" maxlength="11">
									<div id="fredcaption">
										<c:out value="${status.errorMessage}" />
									</div>

									

								</td>

								<td class="dataLabel" width="15%">
									
									Member Status: <span class="required">*</span>
									
								</td>
								<td class="dataField" width="35%">
																						
										<input type="text" name="member_status"  value="${firstCallForm.firstCall.customerId.status.status}"  readonly="readonly">
									
								</td>
							</tr>


							<tr>
								<td class="dataLabel">
									
									Member Name :
									
								</td>
								<td>
									
									<input type="text" readonly="readonly" name="member_name" value="<c:out value="${firstCallForm.firstCall.customerId.firstName}" /> <c:out value="${firstCallForm.firstCall.customerId.lastName}" />" maxlength="30">
									
								</td>



								<td class="dataLabel">
									Client :
								</td>
								<td class="dataField">
									<input name="client_name"  value="${firstCallForm.firstCall.customerId.clientId.clientName}" type="text" readonly="readonly">
								</td>
							</tr>

							<tr>
<!--							MEMBERSHIP STATUS	-->
								<td class="dataLabel">
								</td>
								<td class="dataField" nowrap="nowrap">									
								</td>
<!--							Expire Date :-->
								<td class="dataLabel">
									Birth Date :
								</td>
								<td class="dataField">									
												<spring:bind path="claimForm.admissionDate">
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
							</tr>
							<tr>
								<td nowrap="nowrap"></td>
								<td nowrap="nowrap">
									&nbsp;
								</td>
								<td nowrap="nowrap">
									&nbsp;
								</td>
								<td nowrap="nowrap">
									<span class="dateFormat">(dd-MM-yyyy)</span>
								</td>

							</tr>






						</tbody>
					</table>
				</td>
			</tr>
		</tbody>
	</table>


	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tbody>
			<tr>
				<td style="padding-bottom: 2px;">
					<input type="hidden" name="notyet" value="">
					<input title="Save [Alt+Shift+S]" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:save()" name="Save" value=" Save " type="submit">
					<input title="Cancel [Alt+Shift+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:cancel()" name="Cancel" value=" Cancel " type="button">
				</td>
				<td align="right"></td>
			</tr>
		</tbody>
	</table>

</form>

<script language="javascript">
	
	function save (){

		document.form1.method = "POST";
		document.form1.action = "firstcall-form";
		document.form1.submit();
	}
	function cancel (){
		document.form1.navigation.value = "back";
		document.form1.action = "firstcall";
		document.form1.submit();
	}
	// forreign affairs
		function lookupUser (){
		window.open ("user?navigation=lookup&url=firstcall-form","Search","width=700, height=550, menubar=no, status=no, toolbar=no, scrollbars=yes, resizable=yes,screenX=200,screenY=100");
	}

				// forreign affairs end
</script>
