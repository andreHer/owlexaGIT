<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>



<script type='text/javascript' src='dwr/interface/AJAXClaimService.js'></script>
<script type='text/javascript' src='dwr/engine.js'></script>
<script type='text/javascript' src='dwr/util.js'></script>

<script type="text/javascript" src="scripts/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript"
	src="scripts/jquery/plugin/jquery.autocomplete.pack.js"></script>


<link rel="stylesheet" type="text/css"
	href="css/jquery/autocomplete/jquery.autocomplete.css" />

<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>
		<tr>
			<c:choose>
				<c:when test="${navigation eq 'update'}">
					<td nowrap="nowrap"><h3>
							<img src="images/h3Arrow.gif" border="0">&nbsp;Update
							Payment
						</h3>
					</td>
				</c:when>
				<c:when test="${navigation ne 'update'}">
					<td nowrap="nowrap"><h3>
							<img src="images/h3Arrow.gif" border="0">&nbsp;Register
							Payment
						</h3>
					</td>
				</c:when>
			</c:choose>
			<td width="100%"><img src="images/blank.gif" height="1"
				width="1">
			</td>
		</tr>
	</tbody>
</table>

<form action="payment-form" method="POST" name="form1" id="form_layout">
	<spring:bind path="paymentForm.paymentId">
		<input type="hidden" name="<c:out value="${status.expression}"/>"
			value="<c:out value="${status.value}" />">
		<div id="fredcaption">
			<c:out value="${status.errorMessage}" />
		</div>
	</spring:bind>

	<spring:bind path="paymentForm.batchClaim">
		<input type="hidden" name="<c:out value="${status.expression}"/>"
			value="<c:out value="${status.value}" />">
		<div id="fredcaption">
			<c:out value="${status.errorMessage}" />
		</div>
	</spring:bind>

	<input type="hidden" name="navigation" value="">


	<%
		String alert = (String) request.getAttribute("alert");
		Integer batchClaimId = (Integer) request
				.getAttribute("batchClaimId");
		if (alert != null && !alert.trim().equals("")) {
	%>
	<div id="warning">
		<c:out value="${alert}"></c:out>
	</div>
	<%
		}
	%>

	<input type="hidden" id="batchClaimId" name="batchClaimId" value="${batchClaimId}">

	<table class="tabForm" border="0" cellpadding="0" cellspacing="0"
		width="100%">
		<tbody>
			<tr>
				<td class="dataLabel">&nbsp;</td>
				<td class="dataField"></td>
				<td class="dataLabel"></td>
				<td class="dataField"></td>
			</tr>
			<tr>
				<td class="dataLabel">Nomor Batch :</td>


				<td class="dataField"><spring:bind
						path="paymentForm.batchNumber">
						<input class="inputtext" type="text" size="35"
							name="<c:out value="${status.expression}" />"
							value="<c:out value="${status.value}" />" / readonly="readonly">

						<div id="fredcaption">
							<c:out value="${status.errorMessage}" />
						</div>
					</spring:bind></td>
				<td class="dataLabel">Pengirim Batch :</td>


				<td class="dataField"><spring:bind
						path="paymentForm.paymentRecipient">
						<input class="inputtext" type="text" size="35"
							name="<c:out value="${status.expression}" />"
							value="<c:out value="${status.value}" />"
							<c:if test="${navigation eq 'confirm' }">readonly</c:if> />

						<div id="fredcaption">
							<c:out value="${status.errorMessage}" />
						</div>
					</spring:bind></td>

			</tr>
			<tr>
				<td class="dataLabel">Submission Date :</td>


				<td class="dataField"><spring:bind
						path="paymentForm.paymentTime">
						<input type="text" name="<c:out value="${status.expression}" />"
							id="<c:out value="${status.expression}"/>"
							value="<c:out value="${status.value}" />"
							<c:if test="${navigation eq 'confirm' }">readonly</c:if>
							maxlength="10">
						<c:if test="${navigation ne 'confirm' }">
							<img src="images/jscalendar.gif" alt="Enter Date"
								id="<c:out value="${status.expression}"/>_trigger"
								align="absmiddle" height="20" width="20">
						</c:if>
						<script type="text/javascript">
							Calendar
									.setup({
										inputField : "<c:out value="${status.expression}"/>", // id of the input field
										ifFormat : "%Y-%m-%d", // format of the input field
										button : "<c:out value="${status.expression}"/>_trigger", // trigger for the calendar (button ID)
										align : "Bl", // alignment (defaults to "Bl")
										singleClick : true
									});
						</script>
						<div id="fredcaption">
							<c:out value="${status.errorMessage}" />
						</div>
					</spring:bind></td>
			<tr>
				<td class="dataLabel"></td>


				<td class="dataField"></td>
			</tr>


			<tr>
				<td class="dataLabel">Claim Payment Value :</td>

				<td class="dataField"><spring:bind
						path="paymentForm.claimPaymentValue">
						<input type="text" size="35"
							name="<c:out value="${status.expression}" />"
							value="<c:out value="${status.value}" />" maxlength="22"
							<c:if test="${navigation eq 'confirm' }">readonly</c:if>>
						<div id="fredcaption">
							<c:out value="${status.errorMessage}" />
						</div>
					</spring:bind></td>
				<td class="dataLabel">Administration Cost :</td>

				<td class="dataField"><spring:bind
						path="paymentForm.administrationCost">
						<input type="text" size="35"
							name="<c:out value="${status.expression}" />"
							value="<c:out value="${status.value}" />" maxlength="22">
						<div id="fredcaption">
							<c:out value="${status.errorMessage}" />
						</div>
					</spring:bind></td>
			</tr>






			<tr>
				<td class="dataLabel">Bank Name :</td>


				<td class="dataField"><spring:bind path="paymentForm.bankName">
						<input class="inputtext" type="text" size="35"
							id="<c:out value="${status.expression}" />"
							name="<c:out value="${status.expression}" />"
							value="<c:out value="${status.value}" />"
							<c:if test="${navigation eq 'confirm' }">readonly</c:if> />

						<div id="fredcaption">
							<c:out value="${status.errorMessage}" />
						</div>
					</spring:bind></td>
				<td class="dataLabel">Bank Branch :</td>


				<td class="dataField"><spring:bind
						path="paymentForm.bankBranch">
						<input class="inputtext" type="text" size="35"
							name="<c:out value="${status.expression}" />"
							value="<c:out value="${status.value}" />"
							<c:if test="${navigation eq 'confirm' }">readonly</c:if> />

						<div id="fredcaption">
							<c:out value="${status.errorMessage}" />
						</div>
					</spring:bind></td>
			</tr>
			<tr>
				<td class="dataLabel">Account Number :</td>


				<td class="dataField"><spring:bind
						path="paymentForm.accountNumber">
						<input class="inputtext" type="text" size="35"
							name="<c:out value="${status.expression}" />"
							value="<c:out value="${status.value}" />"
							<c:if test="${navigation eq 'confirm' }">readonly</c:if> />

						<div id="fredcaption">
							<c:out value="${status.errorMessage}" />
						</div>
					</spring:bind></td>
				<td class="dataLabel">Payee Name :</td>


				<td class="dataField"><spring:bind path="paymentForm.payeeName">
						<input class="inputtext" type="text" size="35"
							name="<c:out value="${status.expression}" />"
							value="<c:out value="${status.value}" />"
							<c:if test="${navigation eq 'confirm' }">readonly</c:if> />

						<div id="fredcaption">
							<c:out value="${status.errorMessage}" />
						</div>
					</spring:bind></td>

			</tr>

			<c:if test="${navigation eq 'confirm' }">
				<tr>
					<td class="dataLabel">Giro Number :</td>


					<td class="dataField"><spring:bind
							path="paymentForm.giroNumber">
							<input class="inputtext" type="text" size="35"
								name="<c:out value="${status.expression}" />"
								value="<c:out value="${status.value}" />" />

							<div id="fredcaption">
								<c:out value="${status.errorMessage}" />
							</div>
						</spring:bind></td>
					<td class="dataLabel">Payment Date :</td>


					<td class="dataField"><spring:bind
							path="paymentForm.paymentConfirmDate">
							<input type="text" name="<c:out value="${status.expression}" />"
								id="<c:out value="${status.expression}"/>"
								value="<c:out value="${status.value}" />" maxlength="10">
							<img src="images/jscalendar.gif" alt="Enter Date"
								id="<c:out value="${status.expression}"/>_trigger"
								align="absmiddle" height="20" width="20">
							<script type="text/javascript">
								Calendar
										.setup({
											inputField : "<c:out value="${status.expression}"/>", // id of the input field
											ifFormat : "%Y-%m-%d", // format of the input field
											button : "<c:out value="${status.expression}"/>_trigger", // trigger for the calendar (button ID)
											align : "Bl", // alignment (defaults to "Bl")
											singleClick : true
										});
							</script>
							<div id="fredcaption">
								<c:out value="${status.errorMessage}" />
							</div>
						</spring:bind></td>
				</tr>
			</c:if>
			<c:if test="${navigation eq 'confirm' or navigation eq 'deliver'}">
				<tr>
					<td class="dataLabel">Confirmed Payment Value :</td>


					<td class="dataField"><spring:bind
							path="paymentForm.confirmedPaymentValue">
							<input class="inputtext" type="text" id="confirmedValue"
								size="35" name="<c:out value="${status.expression}" />"
								value="<c:out value="${status.value}" />" />

							<div id="fredcaption">
								<c:out value="${status.errorMessage}" />
							</div>
						</spring:bind> <input type="hidden" name="valueChecked" id="valueChecked"
						value="" /></td>
					<td class="dataLabel"></td>


					<td class="dataField"></td>
				</tr>
			</c:if>
			<tr>
				<td class="dataLabel">Remarks :
				</td>


				<td class="dataField" colspan="3"><spring:bind
						path="paymentForm.remarks"><textarea id="paymentRemarks" maxlength="4000" rows="8" cols="45"
							name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
						<div id="fredcaption">
							<c:out value="${status.errorMessage}" />
						</div>
					</spring:bind></td>
			</tr>
			<tr>
				<td class="dataLabel">&nbsp;</td>
				<td class="dataLabel">&nbsp;</td>
				<td class="dataLabel">&nbsp;</td>
				<td class="dataLabel">&nbsp;</td>
			</tr>


		</tbody>
	</table>
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tbody>
			<tr>
				<td style="padding-top: 2px;"><input type="hidden"
					name="notyet" value=""> <c:if
						test="${navigation ne 'confirm' and navigation ne 'deliver' }">
						<input title="Save [Alt+Shift+S]" accesskey="S"
							class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
							onClick="javascript:simpan()" name="Save" value=" Save Payment "
							type="submit">
					</c:if> <c:if test="${navigation eq 'confirm' }">
						<input title="Confirm Payment [Alt+Shift+S]" accesskey="S"
							class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
							onClick="javascript:confirmPayment()" name="ConfirmPay"
							value=" Confirm Payment " type="submit">
					</c:if> <c:if test="${navigation eq 'deliver' }">
						<input title="Payment Disposition [Alt+Shift+S]" accesskey="S"
							class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
							onClick="javascript:dispositionPayment()" name="disPoPayment"
							value=" Payment Disposition " type="submit">
					</c:if> <input title="Cancel [Alt+Shift+C]" accesskey="C"
					class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
					onClick="javascript:cancel()" name="Cancel" value=" Cancel "
					type="button"></td>
				<td align="right"></td>
			</tr>
		</tbody>
	</table>

	<br /> <br />
	<c:if test="${navigation eq 'confirm' or navigation eq 'deliver'}">


		<table class="listView" cellspacing="0" cellpadding="0" width="100%">
			<tbody>
				<tr height="20">
					<td width="2%" nowrap="nowrap" class="listViewThS1" scope="col">
						<img src="images/blank.gif" alt="asd" height="1" width="1">No.</td>
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Claim
						Number &nbsp;</td>
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Admission
						&nbsp;</td>
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Discharge
						&nbsp;</td>
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Member
						Name &nbsp;</td>
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Provider
						Name &nbsp;</td>
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Charge
						&nbsp;</td>
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Approve
						&nbsp;</td>
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">&nbsp;</td>
				</tr>


				<c:forEach items="${claimList}" var="claim" varStatus="status">

					<tr height="20">
						<td class="oddListRowS1" align="center" bgcolor="#e7f0fe"
							nowrap="nowrap" valign="top"><c:out
								value="${status.index+1}" />
						</td>
						<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap"
							valign="top"><a
							href="claim?navigation=detail&index=<c:out value="${index}" />&claimId=<c:out value="${claim.claimId}" />"
							class="linkDetail"><c:out value="${claim.claimNumber}" /> </a></td>
						<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap"
							valign="top"><c:out value="${claim.admissionDate}" /></td>
						<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap"
							valign="top"><c:out value="${claim.dischargeDate}" /></td>
						<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap"
							valign="top"><c:out value="${claim.memberName}" /></td>
						<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap"
							valign="top"><c:out value="${claim.providerName}" /></td>
						<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap"
							valign="top"><c:out value="${claim.claimChargeValue}" /></td>


						<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap"
							valign="top"><fmt:formatNumber>
								<c:out value="${claim.claimApprovedValue}" />
							</fmt:formatNumber></td>

						<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap"
							align="center" valign="top"><input type="checkbox"
							onclick="javascript:updateCheck();" name="claimList"
							value="<c:out value="${claim.claimId}" />"></td>

					</tr>

					<tr>
						<td colspan="20" class="listViewHRS1"></td>
					</tr>

				</c:forEach>
				<tr height="20">
					<td class="listViewThS1" align="center" bgcolor="#e7f0fe"
						nowrap="nowrap" valign="top"></td>
					<td class="listViewThS1" bgcolor="#e7f0fe" nowrap="nowrap"
						valign="top"></td>
					<td class="listViewThS1" bgcolor="#e7f0fe" nowrap="nowrap"
						valign="top"></td>
					<td class="listViewThS1" bgcolor="#e7f0fe" nowrap="nowrap"
						valign="top"></td>
					<td class="listViewThS1" bgcolor="#e7f0fe" nowrap="nowrap"
						valign="top"></td>
					<td class="listViewThS1" bgcolor="#e7f0fe" nowrap="nowrap"
						valign="top"></td>
					<td class="listViewThS1" bgcolor="#e7f0fe" nowrap="nowrap"
						valign="top"></td>


					<td class="listViewThS1" bgcolor="#e7f0fe" nowrap="nowrap"
						valign="top"></td>

					<td class="listViewThS1" bgcolor="#e7f0fe" nowrap="nowrap"
						style="text-align: center;" align="center" valign="top"><a
						href="javascript:selectAll();">SELECT ALL</a></td>

				</tr>

			</tbody>
		</table>
	</c:if>

</form>


<script language="javascript">
	$(document)
			.ready(
					function() {

						$('#paymentRemarks_feedback').html(
								'('
										+ $('#paymentRemarks').val().length
										+ '/'
										+ $('#paymentRemarks')
												.attr('maxlength') + ')');

						$('textarea[maxlength]')
								.keyup(
										function() {
											//get the limit from maxlength attribute  
											var limit = parseInt($(this).attr(
													'maxlength'));
											//get the current text inside the textarea  
											var text = $(this).val();
											//count the number of characters in the text  
											var chars = text.length;

											//alert($(this).attr('id'));
											$(
													'#' + $(this).attr('id')
															+ '_feedback')
													.html(
															'('
																	+ $(this)
																			.val().length
																	+ '/'
																	+ $(this)
																			.attr(
																					'maxlength')
																	+ ')');

											//check if there are more characters then allowed  
											if (chars > limit) {
												//and if there are use substr to get the text before the limit  
												var new_text = text.substr(0,
														limit);

												//and change the current text with the new text  
												$(this).val(new_text);
											}
										});

						$("#bankName").autocomplete(
								"bank?navigation=lookupjson",
								{
									max : 7,
									dataType : "json",
									parse : function(data) {
										return $.map(data, function(row) {
											return {
												data : row,
												value : row.name,
												result : row.name
											}
										});
									},
									formatItem : function(row) {
										return "<font color='#000' >"
												+ row.name + "</font>";
									}
								}).bind(
								"result",
								function(data, value) {
									$(this).parents("dd")
											.find(".error_message").hide();
								});
					});

	function updateCheck() {

		var checkedValues = $('input:checkbox:checked').map(function() {
			return this.value;
		}).get();

		var valueText = document.getElementById("valueChecked");
		var confirmedValue = document.getElementById("confirmedValue");
		var batchClaimId = document.getElementById("batchClaimId").value;

		valueText.value = checkedValues;

		var toCalc = valueText.value;

		AJAXClaimService.calculatePayment(toCalc, batchClaimId, {
			callback : function(valueRes) {
				confirmedValue.value = valueRes;
			}
		});

	}
	function simpan() {

		document.form1.method = "POST";
		document.form1.action = "payment-form";
		document.form1.submit();
	}
	function dispositionPayment() {

		document.form1.method = "POST";
		document.form1.action = "payment-form";
		document.form1.navigation.value = 'deliver';
		document.form1.submit();
	}
	function confirmPayment() {

		document.form1.method = "POST";
		document.form1.action = "payment-form";
		document.form1.navigation.value = 'confirm';
		document.form1.submit();
	}

	function selectAll() {

		$(':checkbox').each(function() {
			this.checked = true;
		});
		updateCheck();
	}
	function cancel() {
		history.back(-1);
	}
</script>