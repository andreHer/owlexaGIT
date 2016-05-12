<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>


<form action="memberdiagnosis-form" method="POST" name="form1" id="form_layout">
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

							<H1>
								Member Diagnosis
							</H1>
							<%String alert = (String) request.getAttribute("alert");
							if (alert != null && !alert.trim().equals("")) {%>
							<div id="warning">
								<c:out value="${alert}"></c:out>
							</div>
							<%}%>


							<spring:bind path="memberDiagnosisForm.memberDiagnosisId">
								<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />">
								<div id="fredcaption">
									<c:out value="${status.errorMessage}" />
								</div>
							</spring:bind>

							<input type="hidden" name="navigation" value=""><!--









							<tr>
								<td class="dataLabel">
									Created Time :
								</td>


								<td class="dataField">
									<spring:bind path="memberDiagnosisForm.createdTime">
										<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" id="<c:out value="${status.value}"/>" maxlength="19">
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
								<td class="dataLabel">
									Created By :
								</td>


								<td class="dataField">
									<spring:bind path="memberDiagnosisForm.createdBy">
										<textarea cols="60" rows="8" name="<c:out value="${status.expression}"/>">
											<c:out value="${status.value}" />
										</textarea>
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
								</td>
								<td class="dataLabel"></td>
								<td class="dataField"></td>
							</tr>





							<tr>
								<td class="dataLabel">
									Deleted Time :
								</td>


								<td class="dataField">
									<spring:bind path="memberDiagnosisForm.deletedTime">
										<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" id="<c:out value="${status.value}"/>" maxlength="19">
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
								<td class="dataLabel">
									Deleted By :
								</td>


								<td class="dataField">
									<spring:bind path="memberDiagnosisForm.deletedBy">
										<textarea cols="60" rows="8" name="<c:out value="${status.expression}"/>">
											<c:out value="${status.value}" />
										</textarea>
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
								</td>
								<td class="dataLabel"></td>
								<td class="dataField"></td>
							</tr>





							<tr>
								<td class="dataLabel">
									Modified Time :
								</td>


								<td class="dataField">
									<spring:bind path="memberDiagnosisForm.modifiedTime">
										<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" id="<c:out value="${status.value}"/>" maxlength="19">
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
								<td class="dataLabel">
									Modified By :
								</td>


								<td class="dataField">
									<spring:bind path="memberDiagnosisForm.modifiedBy">
										<textarea cols="60" rows="8" name="<c:out value="${status.expression}"/>">
											<c:out value="${status.value}" />
										</textarea>
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
								</td>
								<td class="dataLabel"></td>
								<td class="dataField"></td>
							</tr>-->














							<tr>
								<td class="dataLabel">
									Diagnosis Id :
								</td>
								<td class="dataField">
									<spring:bind path="memberDiagnosisForm.diagnosisId">
										<input type="text" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="11">
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
									<input type="button" name="PILIH" class="btn" value="pilih" onClick="javascript:lookupDiagnosis()">
								</td>
								<td class="dataLabel"></td>
								<td class="dataField"></td>
							</tr>


							<tr>
								<td class="dataLabel">
									Member Id :
								</td>
								<td class="dataField">
									<spring:bind path="memberDiagnosisForm.memberId">
										<input type="text" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="11">
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
									<input type="button" name="PILIH" class="btn" value="pilih" onClick="javascript:lookupMember()">
								</td>
								<td class="dataLabel"></td>
								<td class="dataField"></td>
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
		document.form1.action = "memberdiagnosis-form";
		document.form1.submit();
	}
	function kembali (){
		document.form1.navigation.value = "back";
		document.form1.action = "memberdiagnosis";
		document.form1.submit();
	}
	// forreign affairs
		function lookupDiagnosis (){
		window.open ("diagnosis?navigation=lookup&url=memberdiagnosis-form","Search","width=700, height=550, menubar=no, status=no, toolbar=no, scrollbars=yes, resizable=yes,screenX=200,screenY=100");
	}
	function setDiagnosis (idx){
		document.form1.method = "POST";
		document.form1.notyet.value="true";
		document.form1.action = "memberdiagnosis-form";
		//document.form1.memberDiagnosisForm.diagnosisId.value=idx;
		document.getElementById("diagnosisId").value = idx;
		document.form1.submit();
		}
			function lookupMember (){
		window.open ("member?navigation=lookup&url=memberdiagnosis-form","Search","width=700, height=550, menubar=no, status=no, toolbar=no, scrollbars=yes, resizable=yes,screenX=200,screenY=100");
	}
	function setMember (idx){
		document.form1.method = "POST";
		document.form1.notyet.value="true";
		document.form1.action = "memberdiagnosis-form";
		//document.form1.memberDiagnosisForm.memberId.value=idx;
		document.getElementById("memberId").value = idx;
		document.form1.submit();
		}
				// forreign affairs end
</script>
