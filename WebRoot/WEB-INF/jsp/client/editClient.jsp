<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>


<script type="text/javascript" src="scripts/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript"
	src="scripts/jquery/plugin/jquery.autocomplete.pack.js"></script>
<link rel="stylesheet" type="text/css"
	href="css/jquery/autocomplete/jquery.autocomplete.css" />

<script type="text/javascript" src="scripts/owlexa.function.js"></script>
<script type="text/javascript" src="scripts/tinymce/themes/numeric.js"></script>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>
		<tr>
			<td nowrap="nowrap">
				<h3>
					<c:choose>
						<c:when test="${navigation eq 'update'}">
							<img src="images/h3Arrow.gif" border="0">&nbsp;Update Client
						</c:when>
						<c:otherwise>
							<img src="images/h3Arrow.gif" border="0">&nbsp;Register Client
						</c:otherwise>
					</c:choose>	
				</h3>
			</td>
			<td width="100%"><img src="images/blank.gif" height="1"
				width="1">
			</td>
		</tr>
	</tbody>
</table>
	
<form action="client-form" method="POST" name="form1" id="form_layout">

	<input type="hidden" name="navigation" value="">

	<%
		String alert = (String) request.getAttribute("alert");
		if (alert != null && !alert.trim().equals("")) {
	%>
	<div id="warning">
		<c:out value="${alert}"></c:out>
	</div>
	<%
		}
	%>
	<%
		String alertExist = (String) request.getAttribute("alertExist");
		if (alertExist != null && !alertExist.trim().equals("")) {
	%>
	<div id="warning">
		<input type="hidden" id="alertClient" name="alertClient" value="<c:out value="${alertExist}" />">
		<c:out value="${alertExist}"></c:out>
	</div>
	<%
		}
	%>

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
								<td class="dataLabel"></td>
								<td class="dataField"></td>
							</tr>
							<spring:bind path="clientForm.clientId">
								<input type="hidden"
									name="<c:out value="${status.expression}"/>"
									value="<c:out value="${status.value}" />">
								<div id="fredcaption">
									<c:out value="${status.errorMessage}" />
								</div>
							</spring:bind>
							<tr>
								<td class="dataLabel">Client Name * :</td>
								<td class="dataField"><spring:bind
										path="clientForm.clientName">
										<input type="text" size="35"
											name="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}" />" />

										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
								</td>
								<td class="dataLabel">Client Category * :</td>
								<td class="dataField"><spring:bind
										path="clientForm.clientCategoryId">
										<select name="<c:out value="${status.expression}"/>">
											<c:forEach items="${clientCategory}" var="cc">
												<option value="<c:out value="${cc.clientCategoryId}"/>"
													<c:if test="${status.value eq cc.clientCategoryId}">selected</c:if>>
													<c:out value="${cc.clientCategoryName}" />
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
								<td class="dataLabel">Client Website :</td>
								<td class="dataField"><spring:bind
										path="clientForm.clientWebsite">
										<input type="text" onkeypress="javascript:return notContainSpace(this,event)" size="35"
											name="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}" />" />
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
								</td>
								<td class="dataLabel">Client Number * :</td>
								<td class="dataField"><spring:bind
										path="clientForm.clientNumber">
										<input type="text" size="35"
											name="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}" />" />

										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
								</td>
							</tr>
							<tr>
								<td class="dataLabel">&nbsp;</td>
								<td class="dataField">&nbsp;</td>
								<td class="dataLabel">Client Code * :</td>
								<td class="dataField"><spring:bind
										path="clientForm.clientCode">
										<input type="text" size=5
											name="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}" />" />
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
								</td>
							</tr>
							<tr>
								<td class="dataLabel"></td>
								<td class="dataField"></td>
								<td class="dataLabel"></td>
								<td class="dataField"></td>
							</tr>
							<tr>
								<td class="dataLabel">Registration Date * :</td>
								<td class="dataField"><spring:bind
										path="clientForm.registrationDate">
										<input type="text" readonly="readonly"
											name="<c:out value="${status.expression}" />"
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
								<td class="dataLabel">Renewal Date :</td>
								<td class="dataField"><spring:bind
										path="clientForm.renewalDate">
										<input type="text" readonly="readonly"
											name="<c:out value="${status.expression}" />"
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
							<tr>
								<td class="dataLabel">Expire Date * :</td>
								<td class="dataField"><spring:bind
										path="clientForm.expireDate">
										<input type="text" readonly="readonly"
											name="<c:out value="${status.expression}" />"
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
								<td class="dataLabel">Resigned Date :</td>
								<td class="dataField"><spring:bind
										path="clientForm.resignDate">
										<input type="text" readonly="readonly"
											name="<c:out value="${status.expression}" />"
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
									</spring:bind>
								</td>
							<tr>
								<td class="dataLabel">&nbsp;</td>
								<td class="dataField"></td>
								<td class="dataLabel"></td>
								<td class="dataField"></td>
							</tr>
							<tr>
								<td class="dataLabel"></td>
								<td class="dataField"></td>
								<td class="dataLabel">Fund Usage Type :</td>
								<td class="dataField"><spring:bind
										path="clientForm.floatingFundUsage">
										<select name="<c:out value="${status.expression}" />">
											<option value="">-- PILIH SALAH SATU --</option>
											<option value="1" <c:if test="${status.value eq 1 }">selected</c:if>>CASHLESS</option>
											<option value="2" <c:if test="${status.value eq 2 }">selected</c:if>>REIMBURSEMENT</option>
											<option value="3" <c:if test="${status.value eq 3 }">selected</c:if>>BOTH</option>
											
										</select>
									</spring:bind> <spring:bind path="clientForm.isUsingFloatingFund">
										<input type="checkbox"
											name="<c:out value="${status.expression}" />" value="1"
											<c:if test="${status.value eq 1}">checked</c:if>>

										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
								</td>
							</tr>
							<tr>
								<td class="dataLabel">Bank Account * :</td>
								<td class="dataField"><spring:bind
										path="clientForm.clientBankAccount">
										<input type="text"
											onkeypress="javascript:return isNumberKey(this,event)"
											size="35" name="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}" />" />
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind></td>

								</td>
								<td class="dataLabel">Fund Currency :</td>
								<td class="dataField"><spring:bind
										path="clientForm.fundCurrency">
										<select name="<c:out value="${status.expression}" />">
											<c:forEach items="${currencies}" var="cur">
												<option value="<c:out value="${cur.currencyId}" />"
													<c:if test="${status.value eq cur.currencyId}">selected</c:if>>
													<c:out value="${cur.currencyName}" />
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
								<td class="dataLabel">Account Name * :</td>
								<td class="dataField"><spring:bind
										path="clientForm.accountName">
										<input type="text" size="35"
											name="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}" />" />
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
								</td>
								<td class="dataLabel">Payment Currency :</td>
								<td class="dataField"><spring:bind
										path="clientForm.paymentCurrency">
										<select name="<c:out value="${status.expression}" />">
											<c:forEach items="${currencies}" var="cur">
												<option value="<c:out value="${cur.currencyId}" />"
													<c:if test="${status.value eq cur.currencyId}">selected</c:if>>
													<c:out value="${cur.currencyName}" />
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
								<td class="dataLabel">Bank Name * :</td>
								<td class="dataField"><spring:bind
										path="clientForm.bankName">
										<input type="text" size="35" id="bankName"
											name="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}" />" />
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind></td>
								<td class="dataLabel"></td>
								<td class="dataField"></td>
							</tr>
							<tr>
								<td class="dataLabel">Bank Branch :</td>
								<td class="dataField"><spring:bind
										path="clientForm.bankBranch">
										<input type="text" size="35"
											name="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}" />" />
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind></td>
								<td class="dataLabel">Minimum Fund Value * :</td>
								<td class="dataField"><spring:bind
										path="clientForm.minimumFundValue">
										<input type="text" size="35"
											onkeypress="return isNumberKey(this,event)"
											name="<c:out value="${status.expression}" />"
											value="<c:out value="${status.value}" />"
											maxlength="22">
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind></td>
							</tr>
							<tr>
								<td class="dataLabel">&nbsp;</td>
								<td class="dataField"></td>
								<td class="dataLabel"></td>
								<td class="dataField"></td>
							</tr>
							<tr>
								<td class="dataLabel">Country :</td>
								<td class="dataField"><spring:bind
										path="clientForm.country">
										<input type="text" size="35" id="countryName"
											name="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}" />" />
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
										<input type="hidden" id="countryId" name="countryId" value="" />
									</spring:bind></td>
								<td class="dataLabel">Telephone :</td>
								<td class="dataField"><spring:bind
										path="clientForm.telephone">
										<input type="hidden" id="countryCode" name="" value="" />
										<input type="hidden" id="cityCode" name="" value="" />
										<input type="text" id="telephone"
											onkeypress="return isBackspace(this,event,$('#countryCode').val(),$('#cityCode').val(),temp)"
											name="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}" />" />
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind></td>
							</tr>
							<tr>
								<td class="dataLabel">Province :</td>
								<td class="dataField"><spring:bind
										path="clientForm.province">
										<input type="text" size="35" id="provinceName"
											name="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}" />" />
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
										<input type="hidden" id="provinceId" name="provinceId"
											value="" />
									</spring:bind></td>
								<td class="dataLabel">Faximile :</td>
								<td class="dataField"><spring:bind
										path="clientForm.faximile">
										<input type="hidden" id="countryCode" name="" value="" />
										<input type="hidden" id="cityCode" name="" value="" />
										<input type="text" id="faximile"
											onkeypress="return isBackspace(this,event,$('#countryCode').val(),$('#cityCode').val(),temp)"
											name="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}" />" />
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind></td>
							</tr>
							<tr>
								<td class="dataLabel">City :</td>
								<td class="dataField"><spring:bind path="clientForm.city">
										<input type="text" size="35" id="cityName"
											name="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}" />" />
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
										<input type="hidden" id="cityId" name="cityId" value="" />
									</spring:bind></td>
								<td class="dataLabel">Email :</td>
								<td class="dataField"><spring:bind path="clientForm.email">
										<input type="text" onkeypress="javascript:return notContainSpace(this,event)" size="35"
											name="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}" />" />
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind></td>
							</tr>
							<tr>
								<td class="dataLabel">&nbsp;</td>
								<td class="dataField"></td>
								<td class="dataLabel"></td>
								<td class="dataField"></td>
							</tr>
							<tr>
								<td class="dataLabel">Address :</td>
								<td class="dataField" colspan="3"><spring:bind
										path="clientForm.address">
										<textarea cols="60" rows="8" class="inputbox" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind></td>
							</tr>
							<tr>
								<td class="dataLabel"></td>
								<td class="dataField"></td>
								<td class="dataLabel"></td>
								<td class="dataField"></td>
							</tr>
						</tbody>
					</table></td>
			</tr>
		</tbody>
	</table>

	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tbody>
			<tr>
				<td style="padding-top: 2px;"><input type="hidden"
					name="notyet" value=""> <input title="Save [Alt+Shift+S]"
					accesskey="S"
					class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
					onClick="javascript:simpan()" name="Save" value=" Save "
					type="submit"> <input title="Cancel [Alt+Shift+C]"
					accesskey="C"
					class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
					onClick="javascript:cancel()" name="Cancel" value=" Cancel "
					type="button"></td>
				<td align="right"></td>
			</tr>
		</tbody>
	</table>

</form>

<script language="javascript">
var temp=false;
	$(document).ready(function() {

		$("#bankName").autocomplete("bank?navigation=lookupjson", {
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
				return "<font color='#000' >" + row.name + "</font>";
			}
		}).bind("result", function(data, value) {
			$(this).parents("dd").find(".error_message").hide();
		});

		$("#countryName").autocomplete("refcountry?navigation=lookupjson", {
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
				return "<font color='#000' >" + row.name + "</font>";
			}
		}).bind("result", function(data, value) {
			$(this).parents("dd").find(".error_message").hide();
			//include the countryId for filtering
			//alert(value.toSource());
			$("#countryId").val(value.id);

			$("#countryCode").val(value.number);
			$("#telephone").val($("#countryCode").val() + "-");

			$("#faximile").val($("#countryCode").val() + "-");
			temp=false;
			//alert(value.id);
		});

		$("#provinceName").autocomplete("refprovince?navigation=lookupjson", {
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
				return "<font color='#000' >" + row.name + "</font>";
			},
			extraParams : {
				countryId : function() {
					return $("#countryId").val();
				}
			}
		}).bind("result", function(data, value) {
			$(this).parents("dd").find(".error_message").hide();
			//alert(value.toSource());
			$("#provinceId").val(value.id);
		});

		$("#cityName").autocomplete("refcity?navigation=lookupjson", {
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
				return "<font color='#000' >" + row.name + "</font>";
			},
			extraParams : {
				provinceId : function() {
					return $("#provinceId").val();
				}
			}
		}).bind("result", function(data, value) {
			$(this).parents("dd").find(".error_message").hide();
			//alert(value.toSource());
			$("#cityId").val(value.id);
			$("#cityCode").val(value.number);
			var a = $("#cityCode").val();
			var b = a.substring(1);
			//   alert("test"+b);
			$("#telephone").val($("#countryCode").val() + "-" + b + "-");

			$("#faximile").val($("#countryCode").val() + "-" + b + "-");
			temp=true;
		});

	});

	function simpan() {

		document.form1.method = "POST";
		document.form1.action = "client-form";
		document.form1.submit();
	}
	function kembali() {
		document.form1.navigation.value = "back";
		document.form1.action = "client";
		document.form1.submit();
	}
	function cancel() {
		<c:if test="${navigation eq 'update'}">
		document.form1.navigation.value = "detail";
		</c:if>
		document.form1.action = "client";
		document.form1.submit();
	}

	// forreign affairs end
</script>