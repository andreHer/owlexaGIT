<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>


<form action="memberpackage-form" method="POST" name="form1" id="form_layout">
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
								Member Package
							</H1>
							<%String alert = (String) request.getAttribute("alert");
							if (alert != null && !alert.trim().equals("")) {%>
							<div id="warning">
								<c:out value="${alert}"></c:out>
							</div>
							<%}%>


							<spring:bind path="memberPackageForm.memberPackageId">
								<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />">
								<div id="fredcaption">
									<c:out value="${status.errorMessage}" />
								</div>
							</spring:bind>

							<input type="hidden" name="navigation" value="">









							<tr>
								<td class="dataLabel">
									Register Date :
								</td>


								<td class="dataField">
									<spring:bind path="memberPackageForm.registerDate">
										<input type="text" size="35" name="<c:out value="${status.expression}" />" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="10">
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
									Expire Date :
								</td>


								<td class="dataField">
									<spring:bind path="memberPackageForm.expireDate">
										<input type="text" size="35" name="<c:out value="${status.expression}" />" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="10">
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
									Renewal Date :
								</td>


								<td class="dataField">
									<spring:bind path="memberPackageForm.renewalDate">
										<input type="text" size="35" name="<c:out value="${status.expression}" />" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="10">
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







							<!--<tr>
								<td class="dataLabel">
									Created Time :
								</td>


								<td class="dataField">
									<spring:bind path="memberPackageForm.createdTime">
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
									<spring:bind path="memberPackageForm.createdBy">
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
									<spring:bind path="memberPackageForm.deletedTime">
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
									<spring:bind path="memberPackageForm.deletedBy">
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
									<spring:bind path="memberPackageForm.modifiedTime">
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
									<spring:bind path="memberPackageForm.modifiedBy">
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
									Resign Date :
								</td>


								<td class="dataField">
									<spring:bind path="memberPackageForm.resignDate">
										<input type="text" size="35" name="<c:out value="${status.expression}" />" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="10">
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
									Status Id :
								</td>
								<td class="dataField">
									<spring:bind path="memberPackageForm.memberPackageStatus">
										<input type="text" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="11">
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
									<input type="button" name="PILIH" class="btn" value="pilih" onClick="javascript:lookupSubscriptionStatus()">
								</td>
								<td class="dataLabel"></td>
								<td class="dataField"></td>
							</tr>


							<tr>
								<td class="dataLabel">
									Member Id :
								</td>
								<td class="dataField">
									<spring:bind path="memberPackageForm.memberId">
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


							<tr>
								<td class="dataLabel">
									Package Id :
								</td>
								<td class="dataField">
									<spring:bind path="memberPackageForm.packageId">
										<input type="text" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="11">
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
									<input type="button" name="PILIH" class="btn" value="pilih" onClick="javascript:lookupInsurancePackage()">
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
		document.form1.action = "memberpackage-form";
		document.form1.submit();
	}
	function kembali (){
		document.form1.navigation.value = "back";
		document.form1.action = "memberpackage";
		document.form1.submit();
	}
	// forreign affairs
		function lookupSubscriptionStatus (){
		window.open ("subscriptionstatus?navigation=lookup&url=memberpackage-form","Search","width=700, height=550, menubar=no, status=no, toolbar=no, scrollbars=yes, resizable=yes,screenX=200,screenY=100");
	}
	function setSubscriptionStatus (idx){
		document.form1.method = "POST";
		document.form1.notyet.value="true";
		document.form1.action = "memberpackage-form";
		//document.form1.memberPackageForm.statusId.value=idx;
		document.getElementById("statusId").value = idx;
		document.form1.submit();
		}
			function lookupMember (){
		window.open ("member?navigation=lookup&url=memberpackage-form","Search","width=700, height=550, menubar=no, status=no, toolbar=no, scrollbars=yes, resizable=yes,screenX=200,screenY=100");
	}
	function setMember (idx){
		document.form1.method = "POST";
		document.form1.notyet.value="true";
		document.form1.action = "memberpackage-form";
		//document.form1.memberPackageForm.memberId.value=idx;
		document.getElementById("memberId").value = idx;
		document.form1.submit();
		}
			function lookupInsurancePackage (){
		window.open ("insurancepackage?navigation=lookup&url=memberpackage-form","Search","width=700, height=550, menubar=no, status=no, toolbar=no, scrollbars=yes, resizable=yes,screenX=200,screenY=100");
	}
	function setInsurancePackage (idx){
		document.form1.method = "POST";
		document.form1.notyet.value="true";
		document.form1.action = "memberpackage-form";
		//document.form1.memberPackageForm.packageId.value=idx;
		document.getElementById("packageId").value = idx;
		document.form1.submit();
		}
				// forreign affairs end
</script>
