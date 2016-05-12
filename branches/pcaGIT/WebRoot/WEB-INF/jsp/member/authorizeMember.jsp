<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>


<form action="case-form" method="POST" name="form1" id="form_layout">

	<table class="moduleTitle" border="0" cellpadding="0" cellspacing="0" width="100%">
		<tbody>
			<tr>
				<td valign="top">
					<img src="images/Tasks.gif" style="margin-top: 3px;" alt="Tasks: " border="0" height="16" width="16">
					&nbsp;
				</td>
				<td width="100%">
					<h2>
						Authorize Member :
					</h2>
				</td>
				<td style="padding-top: 3px; padding-left: 5px;" align="right" nowrap="nowrap" valign="top">
					&nbsp;<a href="" target="_blank" class="utilsLink"><img src="images/help.gif" alt="Help" align="absmiddle" border="0" height="13" width="13"></a>&nbsp;<a href="" target="_blank" class="utilsLink">Help</a>
				</td>
		</tbody>
	</table>

	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tbody>
			<tr>
				<td style="padding-bottom: 2px;">
					<input type="hidden" name="notyet" value="">
					<input title="Save [Alt+Shift+S]" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:simpan()" name="Save" value=" Save " type="submit">
					<input title="Cancel [Alt+Shift+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:kembali()" name="Cancel" value=" Cancel " type="button">
					<td align="right" nowrap="nowrap">
						<span class="required">*</span> Indicates required field
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

							<!--							<H1>-->
							<!--								Case-->
							<!--							</H1>-->
							<%String alert = (String) request.getAttribute("alert");
			if (alert != null && !alert.trim().equals("")) {%>
							<div id="warning">
								<c:out value="${alert}"></c:out>
							</div>
							<%}%>


							<spring:bind path="myCaseForm.caseId">
								<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />">
								<div id="fredcaption">
									<c:out value="${status.errorMessage}" />
								</div>
							</spring:bind>

							<input type="hidden" name="navigation" value="">


							
							
							<tr>
								<td class="dataLabel" width="19%">
									Member Name :
								</td>							
								<td class="dataField" width="31%">
									
										<input type="text" tabindex="" name="memberName" id="memberName" value="<c:out value="${myCaseForm.memberId.firstName}" /> <c:out value="${myCaseForm.memberId.lastName}" />" maxlength="50" readonly="readonly">
									
								</td>
								
								
								
								<td class="dataLabel" width="19%">
									Client :
								</td>
								<td class="dataField" width="33%">
									
										<input type="text" tabindex="" name="clientName" value="<c:out value="${member.clientId.clientName}" />" maxlength="50" readonly="readonly">
									

								</td>
							</tr>

							<tr>
<!--							MEMBERSHIP STATUS	-->
								<td class="dataLabel">
									Membership Status :
								</td>
								<td class="dataField" nowrap="nowrap" width="33%">
									
										<input type="text" tabindex="" name="status"  value="<c:out value="${member.status.status}" />" maxlength="50" readonly="readonly">
									
								</td>
								<td class="dataLabel">
									
									Data of Birth :
									
								</td>
								<td class="dataField" nowrap="nowrap">
																		
									<table border="0" cellpadding="0" cellspacing="0">
										<tbody>
											<tr>
													<spring:bind path="myCaseForm.dateEnd">
														<INPUT type="text" size="10" readonly="readonly" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" id="date" maxlength="19">
													</spring:bind>
													<img src="images/jscalendar.gif" alt="Enter Date" id="date_trigger" align="absmiddle" height="20" width="20">
													<script type="text/javascript">
											    					Calendar.setup({
											        					inputField     :    "date",     // id of the input field
											        					ifFormat       :    "%Y-%m-%d",      // format of the input field
											        					button         :    "date_trigger",  // trigger for the calendar (button ID)
											        					align          :    "Tl",           // alignment (defaults to "Bl")
											        					singleClick    :    true
											    					});
													 	</script>
													<div id="fredcaption">
														<c:out value="${status.errorMessage}" />
													</div>					
												
											</tr>
											<tr>
												<td nowrap="nowrap">
													<span class="dateFormat">(yyyy-mm-dd)</span>
												</td>
												<td nowrap="nowrap"></td>
												<td nowrap="nowrap">
													&nbsp;
												</td>
											</tr>
										</tbody>
									</table>
									
								</td>

								
								
								
							</tr>
							
							<tr>
<!--							END DATE	-->
																
								<td class="dataLabel">	</td>
								<td class="dataField" width="31%"></td>

								<td class="dataLabel"></td>
								<td class="dataField" width="31%">&nbsp;</td>

							</tr>


		
							
						</tbody>

					</table>
		</tbody>
	</table>
	
	
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tbody>
			<tr>
				<td style="padding-bottom: 2px;">
					<input type="hidden" name="notyet" value="">
					<input title="Save [Alt+Shift+S]" accesskey="S" tabindex="" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:simpan()" name="Save" value=" Save " type="submit">
					<input title="Cancel [Alt+Shift+C]" accesskey="C" tabindex="" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:kembali()" name="Cancel" value=" Cancel " type="button">
				</td>
				<td align="right"></td>
			</tr>
		</tbody>
	</table>


<script language="javascript">
	function simpan (){
		document.form1.method = "POST";
		document.form1.action = "case-form";
		document.form1.submit();
	}
	
	function kembali (){
		document.form1.navigation.value = "back";
		document.form1.action = "case";
		document.form1.submit();
	}

				// forreign affairs end
</script>
