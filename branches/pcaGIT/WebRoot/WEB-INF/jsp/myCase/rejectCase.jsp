<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>


<form action="case" method="POST" name="form1" id="form_layout">

	<table class="moduleTitle" border="0" cellpadding="0" cellspacing="0" width="100%">
		<tbody>
			<tr>
				<td valign="top">
					<img src="images/Tasks.gif" style="margin-top: 3px;" alt="Tasks: " border="0" height="16" width="16">
					&nbsp;
				</td>
				<td width="100%">
					<h2>
						Reject Guarantee Letter / Number :
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



						<tbody>
							<tr>
								<td class="dataLabel" width="19%">
									
									Policy Number : <span class="required">*</span>
									
								</td>
								<td class="dataField" width="31%">
									
									<spring:bind path="myCaseForm.memberId.customerPolicyNumber">
										<input type="text" tabindex="" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="50" readonly="readonly">
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
									
								</td>
								
								
								
								
								<td class="dataLabel" width="15%">
									
									Priority: <span class="required">*</span>
									
								</td>
								<td class="dataField" width="35%">
									
									<spring:bind path="myCaseForm.priorityId.priorityId">
										<select name="<c:out value="${status.expression}"/>" id="priorityId">
											<c:forEach items="${priorityId}" var="prio">
												<option value="<c:out value="${prio.priorityId}" />" <c:if test="${status.value eq prio.priorityId}">selected</c:if>>
													<c:out value="${prio.priorityCode}" />
												</option>
											</c:forEach>
										</select>
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
									
								</td>
							</tr>
							
							
							<tr>
								<td class="dataLabel" width="19%">
									Member Name :
								</td>							
								<td class="dataField" width="31%">
									
										<input type="text" tabindex="" name="<c:out value="${status.expression}"/>" id="memberName" value="<c:out value="${myCaseForm.memberId.firstName}" /> <c:out value="${myCaseForm.memberId.lastName}" />" maxlength="50" readonly="readonly">
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									
								</td>
								
								
								
								<td class="dataLabel" width="19%">
									Client :
								</td>
								<td class="dataField" width="33%">
									
									<spring:bind path="myCaseForm.memberId.clientId.clientName">
										<input type="text" tabindex="" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="50" readonly="readonly">
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
									

								</td>
							</tr>

							<tr>
<!--							MEMBERSHIP STATUS	-->
								<td class="dataLabel">
									Membership Status :
								</td>
								<td class="dataField" nowrap="nowrap" width="33%">
									
									<spring:bind path="myCaseForm.memberId.status.status">
										<input type="text" tabindex="" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="50" readonly="readonly">
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
									
								</td>
								
								
								
								<!--							SERVICE TYPE	-->
								<td class="dataLabel">
									Service Type :
								</td>
								<td class="dataField">									
									<spring:bind path="myCaseForm.caseCategoryId.caseCategoryId">
										<select name="<c:out value="${status.expression}"/>" id="caseCategoryId">
											<c:forEach items="${caseCategoryId}" var="ccat">
												<option value="<c:out value="${ccat.caseCategoryId}" />" <c:if test="${status.value eq ccat.caseCategoryId}">selected</c:if>>
													<c:out value="${ccat.caseCategoryName}" />
												</option>
											</c:forEach>
										</select>
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
								</td>
							</tr>

														
							

							
							
							



							<tr>

<!--							EXPIRE DATE	-->
								<td class="dataLabel">
																		
									
								</td>
								<td class="dataField" nowrap="nowrap">
																		
									
								</td>

								
								<td class="dataLabel">
									
									Diagnosis 3:
									
								</td>
								<td class="dataField" width="31%">
									<spring:bind path="myCaseForm.diagnosis3Id.diagnosisId">
										<select name="<c:out value="${status.expression}"/>" id="diagnosis3Id">
											<c:forEach items="${diagnosisId}" var="diag3">
												<option value="<c:out value="${diag3.diagnosisId}" />" <c:if test="${status.value eq diag3.diagnosisId}">selected</c:if>>
													<c:out value="${diag3.diagnosisName}" />
												</option>
											</c:forEach>
										</select>
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
								</td>
													
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
		document.form1.action = "case";
		document.form1.navigation.value = "savereject";
		document.form1.submit();
	}
	
	function kembali (){
		document.form1.navigation.value = "back";
		document.form1.action = "case";
		document.form1.submit();
	}			// forreign affairs end
</script>
