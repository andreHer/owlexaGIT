<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>


<script type="text/javascript" src="scripts/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript"
	src="scripts/jquery/plugin/jquery.autocomplete.pack.js"></script>

<link rel="stylesheet" type="text/css"
	href="css/jquery/autocomplete/jquery.autocomplete.css" />

<form action="quotation-form" method="POST" name="form1"
	id="form_layout"><input type="hidden" name="navigation" value="">


<%
	String alert = (String) request.getAttribute("alert");
	if (alert != null && !alert.trim().equals("")) {
%>
<div id="warning"><c:out value="${alert}"></c:out></div>
<%
	}
%> <spring:bind path="quotationForm.quotationId">
	<input type="hidden" name="<c:out value="${status.expression}"/>"
		value="<c:out value="${status.value}" />">
</spring:bind>
<table class="tabForm" border="0" cellpadding="0" cellspacing="0"
	width="100%">
	<tbody>
		<tr>
			<td>
			<table border="0" cellpadding="0" cellspacing="0" width="100%">
				<tbody>

					<tr>
						<td class="dataLabel"></td>
						<td class="dataField"></td>
						<td class="dataLabel">&nbsp;</td>
						<td class="dataField"></td>
					</tr>
					<tr>
						<td class="dataLabel">Quotation No :</td>
						<td class="dataField"><spring:bind
							path="quotationForm.quotationNo">
							<input type="text" size="35"
								name="<c:out value="${status.expression}"/>"
								value="<c:out value="${status.value}" />">

							<div id="fredcaption"><c:out value="${status.errorMessage}" />
							</div>
						</spring:bind></td>
						<td class="dataLabel">&nbsp;</td>
						<td class="dataField"></td>
					</tr>
					<tr>
						<td class="dataLabel">Request Date :</td>


						<td class="dataField"><spring:bind
							path="quotationForm.requestDate">
							<input type="text" size="15"
								name="<c:out value="${status.expression}" />"
								id="<c:out value="${status.expression}"/>"
								value="<c:out value="${status.value}" />" maxlength="10">
							<img src="images/jscalendar.gif" alt="Enter Date"
								id="<c:out value="${status.expression}"/>_trigger"
								align="absmiddle" height="20" width="20">
							<script type="text/javascript">
    					Calendar.setup({
        					inputField     :    "<c:out value="${status.expression}"/>",     // id of the input field
        					ifFormat       :    "%Y-%m-%d",      // format of the input field
        					button         :    "<c:out value="${status.expression}"/>_trigger",  // trigger for the calendar (button ID)
        					align          :    "Tl",           // alignment (defaults to "Bl")
        					singleClick    :    true
    					});
				 	</script>
							<div id="fredcaption"><c:out value="${status.errorMessage}" />
							</div>
						</spring:bind></td>
						<td class="dataLabel">Quotation Date :</td>
						<td class="dataField"><spring:bind
							path="quotationForm.quotationDate">
							<input type="text" size="15"
								name="<c:out value="${status.expression}" />"
								id="<c:out value="${status.expression}"/>"
								value="<c:out value="${status.value}" />" maxlength="10">
							<img src="images/jscalendar.gif" alt="Enter Date"
								id="<c:out value="${status.expression}"/>_trigger"
								align="absmiddle" height="20" width="20">
							<script type="text/javascript">
    					Calendar.setup({
        					inputField     :    "<c:out value="${status.expression}"/>",     // id of the input field
        					ifFormat       :    "%Y-%m-%d",      // format of the input field
        					button         :    "<c:out value="${status.expression}"/>_trigger",  // trigger for the calendar (button ID)
        					align          :    "Tl",           // alignment (defaults to "Bl")
        					singleClick    :    true
    					});
				 	</script>
						</spring:bind></td>
					</tr>


					<tr>
						<td class="dataLabel">Member Group :</td>
						<td class="dataField"><spring:bind
							path="quotationForm.memberGroupId">
							<input type="hidden" name="<c:out value="${status.expression}"/>"
								id="<c:out value="${status.expression}"/>"
								value="<c:out value="${status.value}" />" maxlength="11">
						</spring:bind> <input type="text" size="35" name="memberGroupName"
							id="memberGroupName"
							value="<c:out value="${quotationForm.memberGroupName}" />">


						</td>
						<td class="dataLabel"></td>
						<td class="dataField"></td>
					</tr>
					<tr>
						<td class="dataLabel">Client :</td>
						<td class="dataField"><spring:bind
							path="quotationForm.clientId">
							<input type="hidden" name="<c:out value="${status.expression}"/>"
								id="<c:out value="${status.expression}"/>"
								value="<c:out value="${status.value}" />" maxlength="11">
						</spring:bind> <input type="text" size="35" name="clientName" id="clientName"
							value="<c:out value="${quotationForm.clientName}" />"></td>
						<td class="dataLabel">Branch :</td>
						<td class="dataField"><spring:bind
							path="quotationForm.branchId">
							<input type="hidden" name="<c:out value="${status.expression}"/>"
								id="<c:out value="${status.expression}"/>"
								value="<c:out value="${status.value}" />" maxlength="10">
						</spring:bind> <input type="text" size="35" name="branchName" id="brahcnName"
							value="<c:out value="${quotationForm.branchName}" />"></td>
					</tr>
					<tr>
						<td class="dataLabel">Broker :</td>
						<td class="dataField"><spring:bind
							path="quotationForm.brokerId">
							<input type="hidden" name="<c:out value="${status.expression}"/>"
								id="<c:out value="${status.expression}"/>"
								value="<c:out value="${status.value}" />" maxlength="11">
						</spring:bind> <input type="text" size="35" name="clientName" id="clientName"
							value="<c:out value="${quotationForm.brokerName}" />"></td>
						<td class="dataLabel"></td>
						<td class="dataField"></td>
					</tr>

					<tr>
						<td class="dataLabel"></td>
						<td class="dataField"></td>
						<td class="dataLabel">Effective Date :</td>
						<td class="dataField"><spring:bind
							path="quotationForm.effectiveDate">
							<input type="text" size="15"
								name="<c:out value="${status.expression}" />"
								id="<c:out value="${status.expression}"/>"
								value="<c:out value="${status.value}" />" maxlength="10">
							<img src="images/jscalendar.gif" alt="Enter Date"
								id="<c:out value="${status.expression}"/>_trigger"
								align="absmiddle" height="20" width="20">
							<script type="text/javascript">
	    					Calendar.setup({
	        					inputField     :    "<c:out value="${status.expression}"/>",     // id of the input field
	        					ifFormat       :    "%Y-%m-%d",      // format of the input field
	        					button         :    "<c:out value="${status.expression}"/>_trigger",  // trigger for the calendar (button ID)
	        					align          :    "Tl",           // alignment (defaults to "Bl")
	        					singleClick    :    true
	    					});
					 	</script>
							<div id="fredcaption"><c:out value="${status.errorMessage}" />
							</div>
						</spring:bind></td>

					</tr>

					<tr>
						<td class="dataLabel"></td>
						<td class="dataField"></td>
						<td class="dataLabel">Renewal Date :</td>
						<td class="dataField"><spring:bind
							path="quotationForm.renewalDate">
							<input type="text" size="15"
								name="<c:out value="${status.expression}" />"
								id="<c:out value="${status.expression}"/>"
								value="<c:out value="${status.value}" />" maxlength="10">
							<img src="images/jscalendar.gif" alt="Enter Date"
								id="<c:out value="${status.expression}"/>_trigger"
								align="absmiddle" height="20" width="20">
							<script type="text/javascript">
	    					Calendar.setup({
	        					inputField     :    "<c:out value="${status.expression}"/>",     // id of the input field
	        					ifFormat       :    "%Y-%m-%d",      // format of the input field
	        					button         :    "<c:out value="${status.expression}"/>_trigger",  // trigger for the calendar (button ID)
	        					align          :    "Tl",           // alignment (defaults to "Bl")
	        					singleClick    :    true
	    					});
					 	</script>
						</spring:bind></td>
					</tr>
					<tr>
						<td class="dataLabel">Payment Mode :</td>

						<td class="dataField">
							<spring:bind path="quotationForm.paymentMode">
								<select name="<c:out value="${status.expression}" />">
									<option value="">-- SELECT ONE --</option>
									<option value="1" <c:if test="${status.value eq 1 }">selected</c:if>>Annually</option>
									<option value="2" <c:if test="${status.value eq 2 }">selected</c:if>>Semesterly</option>
									<option value="3" <c:if test="${status.value eq 3 }">selected</c:if>>Quarterly</option>
								</select>						
							</spring:bind>
						</td>
						<td class="dataLabel"></td>
						<td class="dataField"></td>
					</tr>
					<tr>
						<td class="dataLabel">Currency :</td>
						<td class="dataField"><spring:bind
							path="quotationForm.currencyId">
							<select name="<c:out value="${status.expression}"/>">
								<option value="">-- SELECT ONE --</option>
								<c:forEach items="${currencyList}" var="currency">									
									<option value="<c:out value="${currency.currencyId}" />" 
										<c:if test="${status.value eq currency.currencyId }">selected</c:if>><c:out	value="${currency.currencyName}" />
									</option>
								</c:forEach>
							</select>
						</spring:bind></td>
						<td class="dataLabel"></td>
						<td class="dataField"></td>
					</tr>
					<tr>
						<td class="dataLabel">Quotation Type :</td>
						<td class="dataField">
							<spring:bind path="quotationForm.quotationTypeId">
								<select name="<c:out value="${status.expression}" />">
									<option value="">-- SELECT ONE --</option>									
									<option value="1" <c:if test="${status.value eq 1 }">selected</c:if>>NEW</option>
									<option value="2" <c:if test="${status.value eq 2 }">selected</c:if>>RENEWAL</option>
								</select>
							</spring:bind>
						</td>
						<td class="dataLabel"></td>
						<td class="dataField"></td>
					</tr>
					<tr>
						<td class="dataLabel">Is Individual :</td>


						<td class="dataField"><spring:bind
							path="quotationForm.isIndividual">
							<input type="checkbox"
								name="<c:out value="${status.expression}" />" value="1"
								<c:if test="${status.value eq 1 }">checked</c:if>>

							<div id="fredcaption"><c:out value="${status.errorMessage}" />
							</div>
						</spring:bind></td>
						<td class="dataLabel">Is Unit Premi :</td>


						<td class="dataField"><spring:bind
							path="quotationForm.isUnitPremi">
							<input type="checkbox"
								name="<c:out value="${status.expression}" />" value="1"
								<c:if test="${status.value eq 1 }">checked</c:if>>

							<div id="fredcaption"><c:out value="${status.errorMessage}" />
							</div>
						</spring:bind></td>

					</tr>



					<tr>
						<td class="dataLabel">Is Family Plan :</td>


						<td class="dataField"><spring:bind
							path="quotationForm.isFamilyPlan">
							<input type="checkbox"
								name="<c:out value="${status.expression}" />" value="1"
								<c:if test="${status.value eq 1 }">checked</c:if>>

							<div id="fredcaption"><c:out value="${status.errorMessage}" />
							</div>
						</spring:bind></td>
						<td class="dataLabel">Is Wife Only :</td>


						<td class="dataField"><spring:bind
							path="quotationForm.isWifeOnly">
							<input type="checkbox"
								name="<c:out value="${status.expression}" />" value="1"
								<c:if test="${status.value eq 1 }">checked</c:if>>

							<div id="fredcaption"><c:out value="${status.errorMessage}" />
							</div>
						</spring:bind></td>

					</tr>







					<tr>
						<td class="dataLabel">Is Discount Group By Employee :</td>


						<td class="dataField"><spring:bind
							path="quotationForm.isDiscountGroupByEmployee">
							<input type="checkbox"
								name="<c:out value="${status.expression}" />" value="1"
								<c:if test="${status.value eq 1 }">checked</c:if>>

						</spring:bind></td>
						<td class="dataLabel"></td>
						<td class="dataField"></td>
					</tr>







					<tr>
						<td class="dataLabel">Installment Amount :</td>


						<td class="dataField"><spring:bind
							path="quotationForm.installmentAmount">
							<input type="text" size="35"
								name="<c:out value="${status.expression}" />"
								value="<fmt:formatNumber><c:out value="${status.value}"/></fmt:formatNumber>"
								maxlength="2">
							<div id="fredcaption"><c:out value="${status.errorMessage}" />
							</div>
						</spring:bind></td>
						<td class="dataLabel"></td>
						<td class="dataField"></td>
					</tr>







					<tr>
						<td class="dataLabel">Aggregate Limit :</td>

						<td class="dataField"><spring:bind
							path="quotationForm.aggregateLimit">
							<input type="text" size="35"
								name="<c:out value="${status.expression}" />"
								value="<fmt:formatNumber><c:out value="${status.value}" /></fmt:formatNumber>"
								maxlength="22">
							<div id="fredcaption"><c:out value="${status.errorMessage}" />
							</div>
						</spring:bind></td>
						<td class="dataLabel"></td>
						<td class="dataField"></td>
					</tr>







					<tr>
						<td class="dataLabel">Toc :</td>


						<td class="dataField"><spring:bind path="quotationForm.toc">
							<input type="text" size="35"
								name="<c:out value="${status.expression}" />"
								value="<fmt:formatNumber><c:out value="${status.value}"/></fmt:formatNumber>"
								maxlength="2">
							<div id="fredcaption"><c:out value="${status.errorMessage}" />
							</div>
						</spring:bind></td>
						<td class="dataLabel"></td>
						<td class="dataField"></td>
					</tr>







					<tr>
						<td class="dataLabel">Payor :</td>


						<td class="dataField"><spring:bind
							path="quotationForm.payorId">
							<input type="text" size="35"
								name="<c:out value="${status.expression}" />"
								value="<fmt:formatNumber><c:out value="${status.value}"/></fmt:formatNumber>"
								maxlength="5">
							<div id="fredcaption"><c:out value="${status.errorMessage}" />
							</div>
						</spring:bind></td>
						<td class="dataLabel"></td>
						<td class="dataField"></td>
					</tr>

					<tr>
						<td class="dataLabel">Is Agent Commision Gross Premi :</td>


						<td class="dataField">
							<spring:bind	path="quotationForm.isAgentCommisionGrossPremi">
								<input type="checkbox"	name="<c:out value="${status.expression}" />" value="1" <c:if test="${status.value eq 1 }">checked</c:if>>
							</spring:bind>
						</td>
						<td class="dataLabel"></td>
						<td class="dataField"></td>
					</tr>
					<tr>
						<td class="dataLabel">Max Children :</td>
						<td class="dataField">
							<spring:bind path="quotationForm.maxChildren">
								<input type="text" size="15" name="<c:out value="${status.expression}" />"
									value="<fmt:formatNumber><c:out value="${status.value}"/></fmt:formatNumber>"
										maxlength="2">							
							</spring:bind>
						</td>
						<td class="dataLabel"></td>
						<td class="dataField"></td>
					</tr>
					<tr>
						<td class="dataLabel">Is ASO Policy :</td>
						<td class="dataField"><spring:bind
							path="quotationForm.isAsoPolicy">
							<input type="checkbox"
								name="<c:out value="${status.expression}" />" value="1"
								<c:if test="${status.value eq 1 }">checked</c:if>>
						</spring:bind></td>
						<td class="dataLabel">ASO Deposit :</td>
						<td class="dataField">
							<spring:bind path="quotationForm.asoDeposit">
								<input type="text" size="35"
									name="<c:out value="${status.expression}" />"
										value="<fmt:formatNumber><c:out value="${status.value}" /></fmt:formatNumber>"
											maxlength="22">
							</spring:bind>
						</td>
					</tr>
					<tr>
						<td class="dataLabel">BRC Date :</td>
						<td class="dataField"><spring:bind
							path="quotationForm.brcDate">
							<input type="text" size="15"
								name="<c:out value="${status.expression}" />"
								id="<c:out value="${status.expression}"/>"
								value="<c:out value="${status.value}" />" maxlength="10">
							<img src="images/jscalendar.gif" alt="Enter Date"
								id="<c:out value="${status.expression}"/>_trigger"
								align="absmiddle" height="20" width="20">
							<script type="text/javascript">
    					Calendar.setup({
        					inputField     :    "<c:out value="${status.expression}"/>",     // id of the input field
        					ifFormat       :    "%Y-%m-%d",      // format of the input field
        					button         :    "<c:out value="${status.expression}"/>_trigger",  // trigger for the calendar (button ID)
        					align          :    "Tl",           // alignment (defaults to "Bl")
        					singleClick    :    true
    					});
				 	</script>
						</spring:bind></td>
						<td class="dataLabel"></td>
						<td class="dataField"></td>
					</tr>







					<tr>
						<td class="dataLabel">Uang Pertanggungan :</td>

						<td class="dataField"><spring:bind
							path="quotationForm.uangPertanggungan">
							<input type="text" size="35"
								name="<c:out value="${status.expression}" />"
								value="<fmt:formatNumber><c:out value="${status.value}" /></fmt:formatNumber>"
								maxlength="22">
							<div id="fredcaption"><c:out value="${status.errorMessage}" />
							</div>
						</spring:bind></td>
						<td class="dataLabel"></td>
						<td class="dataField"></td>
					</tr>







					<tr>
						<td class="dataLabel">Uang Premi :</td>

						<td class="dataField"><spring:bind
							path="quotationForm.uangPremi">
							<input type="text" size="35"
								name="<c:out value="${status.expression}" />"
								value="<fmt:formatNumber><c:out value="${status.value}" /></fmt:formatNumber>"
								maxlength="22">
							<div id="fredcaption"><c:out value="${status.errorMessage}" />
							</div>
						</spring:bind></td>
						<td class="dataLabel"></td>
						<td class="dataField"></td>
					</tr>







					<tr>
						<td class="dataLabel">Total Member :</td>


						<td class="dataField"><spring:bind
							path="quotationForm.totalMember">
							<input type="text" size="35"
								name="<c:out value="${status.expression}" />"
								value="<fmt:formatNumber><c:out value="${status.value}"/></fmt:formatNumber>"
								maxlength="6">
							<div id="fredcaption"><c:out value="${status.errorMessage}" />
							</div>
						</spring:bind></td>
						<td class="dataLabel"></td>
						<td class="dataField"></td>
					</tr>







					<tr>
						<td class="dataLabel">Budget Premi :</td>

						<td class="dataField"><spring:bind
							path="quotationForm.budgetPremi">
							<input type="text" size="35"
								name="<c:out value="${status.expression}" />"
								value="<fmt:formatNumber><c:out value="${status.value}" /></fmt:formatNumber>"
								maxlength="22">
							<div id="fredcaption"><c:out value="${status.errorMessage}" />
							</div>
						</spring:bind></td>
						<td class="dataLabel"></td>
						<td class="dataField"></td>
					</tr>







					<tr>
						<td class="dataLabel">Comission :</td>

						<td class="dataField"><spring:bind
							path="quotationForm.comission">
							<input type="text" size="35"
								name="<c:out value="${status.expression}" />"
								value="<fmt:formatNumber><c:out value="${status.value}" /></fmt:formatNumber>"
								maxlength="22">
							<div id="fredcaption"><c:out value="${status.errorMessage}" />
							</div>
						</spring:bind></td>
						<td class="dataLabel"></td>
						<td class="dataField"></td>
					</tr>







					<tr>
						<td class="dataLabel">Claim Ratio :</td>

						<td class="dataField"><spring:bind
							path="quotationForm.claimRatio">
							<input type="text" size="35"
								name="<c:out value="${status.expression}" />"
								value="<fmt:formatNumber><c:out value="${status.value}" /></fmt:formatNumber>"
								maxlength="22">
							<div id="fredcaption"><c:out value="${status.errorMessage}" />
							</div>
						</spring:bind></td>
						<td class="dataLabel"></td>
						<td class="dataField"></td>
					</tr>















					<tr>
						<td class="dataLabel">Remarks :</td>
						<td class="dataField" colspan="3"><spring:bind
							path="quotationForm.remarks">
							<textarea rows="6" cols="50"
								name="<c:out value="${status.expression}"/>"><c:out
								value="${status.value}" /></textarea>
							<div id="fredcaption"><c:out value="${status.errorMessage}" />
							</div>
						</spring:bind></td>

					</tr>




				</tbody>
			</table>
			</td>
		</tr>
	</tbody>
</table>

</form>
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>
		<tr>
			<td style="padding-top: 2px;"><input type="hidden" name="notyet"
				value=""> <input title="Save [Alt+S]" accesskey="S"
				class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
				onClick="javascript:simpan()" name="Save" value=" Save "
				type="submit"> <input title="Cancel [Alt+C]" accesskey="C"
				class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
				onClick="javascript:cancel()" name="Cancel" value=" Cancel "
				type="submit"></td>
			<td align="right"></td>
		</tr>
	</tbody>
</table>
<script language="javascript">
	function simpan (){

		document.form1.method = "POST";
		document.form1.action = "quotation-form";
		document.form1.submit();
	}
	function kembali (){
		document.form1.navigation.value = "back";
		document.form1.action = "quotation";
		document.form1.submit();
	}
	// forreign affairs
		function lookupBroker (){
		window.open ("broker?navigation=lookup&url=quotation-form","Search","width=700, height=550, menubar=no, status=no, toolbar=no, scrollbars=yes, resizable=yes,screenX=200,screenY=100");
	}
	function setBroker (idx){
		document.form1.method = "POST";
		document.form1.notyet.value="true";
		document.form1.action = "quotation-form";
		//document.form1.quotationForm.brokerId.value=idx;
		document.getElementById("brokerId").value = idx;
		document.form1.submit();
		}
			function lookupClient (){
		window.open ("client?navigation=lookup&url=quotation-form","Search","width=700, height=550, menubar=no, status=no, toolbar=no, scrollbars=yes, resizable=yes,screenX=200,screenY=100");
	}
	function setClient (idx){
		document.form1.method = "POST";
		document.form1.notyet.value="true";
		document.form1.action = "quotation-form";
		//document.form1.quotationForm.clientId.value=idx;
		document.getElementById("clientId").value = idx;
		document.form1.submit();
		}
			function lookupMemberGroup (){
		window.open ("membergroup?navigation=lookup&url=quotation-form","Search","width=700, height=550, menubar=no, status=no, toolbar=no, scrollbars=yes, resizable=yes,screenX=200,screenY=100");
	}
	function setMemberGroup (idx){
		document.form1.method = "POST";
		document.form1.notyet.value="true";
		document.form1.action = "quotation-form";
		//document.form1.quotationForm.memberGroupId.value=idx;
		document.getElementById("memberGroupId").value = idx;
		document.form1.submit();
		}
			function lookupCurrency (){
		window.open ("currency?navigation=lookup&url=quotation-form","Search","width=700, height=550, menubar=no, status=no, toolbar=no, scrollbars=yes, resizable=yes,screenX=200,screenY=100");
	}
	function setCurrency (idx){
		document.form1.method = "POST";
		document.form1.notyet.value="true";
		document.form1.action = "quotation-form";
		//document.form1.quotationForm.currencyId.value=idx;
		document.getElementById("currencyId").value = idx;
		document.form1.submit();
		}
			function lookupBranch (){
		window.open ("branch?navigation=lookup&url=quotation-form","Search","width=700, height=550, menubar=no, status=no, toolbar=no, scrollbars=yes, resizable=yes,screenX=200,screenY=100");
	}
	function setBranch (idx){
		document.form1.method = "POST";
		document.form1.notyet.value="true";
		document.form1.action = "quotation-form";
		//document.form1.quotationForm.branchId.value=idx;
		document.getElementById("branchId").value = idx;
		document.form1.submit();
		}
			function lookupBroker (){
		window.open ("broker?navigation=lookup&url=quotation-form","Search","width=700, height=550, menubar=no, status=no, toolbar=no, scrollbars=yes, resizable=yes,screenX=200,screenY=100");
	}
	function setBroker (idx){
		document.form1.method = "POST";
		document.form1.notyet.value="true";
		document.form1.action = "quotation-form";
		//document.form1.quotationForm.brokerId.value=idx;
		document.getElementById("brokerId").value = idx;
		document.form1.submit();
		}
				// forreign affairs end
</script>