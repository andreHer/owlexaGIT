<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>


<script type='text/javascript' src='dwr/interface/AJAXRemoteController.js'></script>
<script type='text/javascript' src='dwr/engine.js'></script>
<script type='text/javascript' src='dwr/util.js'></script>

<!-- <link rel="stylesheet" type="text/css" href="css/autocomplete.css"> -->
<link rel="stylesheet" type="text/css" href="css/button.css">

<!-- <link rel="stylesheet" type="text/css" href="css/jquery/autocomplete/jquery.autocomplete.css"/> -->

<!-- <script type="text/javascript" src="scripts/jquery/jquery-1.4.4.min.js"></script> -->
<!-- <script type="text/javascript" src="scripts/jquery/plugin/jquery.autocomplete.pack.js"></script> -->

<script type="text/javascript" src="scripts/owlexa.function.js"></script>	

	<style>
		.ui-autocomplete-loading {
		  background: white url("images/owlexa/ui-anim_basic_16x16.gif") right center no-repeat;
		}
	</style>


<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Register Provider</h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>

<form action="provider-form" method="POST" name="form1" id="form_layout">
	<input type="hidden" name="navigation" value="${navigation}">
	<input type="hidden" name="memberId" value="<c:out value="${memberId}" />" />

	

	<spring:bind path="providerForm.providerId">
		<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />">
	</spring:bind>

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
							<tr>
								<td class="dataLabel">
									Provider Name * :
								</td>
								<td class="dataField">
									<spring:bind path="providerForm.providerName">
										<input size="35" type="text"
											name="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}" />" />
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
								</td>
								<td class="dataLabel">
									Provider Category * :
								</td>
								<td class="dataField">
									<spring:bind path="providerForm.providerCategoryId">
										<select name="<c:out value="${status.expression}"/>" id="providerCategoryId">
											<c:forEach items="${providerCategory}" var="category">
												<option
													value="<c:out value="${category.providerCategoryId}" />"
													<c:if test="${category.providerCategoryId eq status.value}">selected</c:if>>
													<c:out value="${category.providerCategoryName}" />
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
								<td class="dataLabel">
									Contract Number *:
								</td>


								<td class="dataField">
									<spring:bind path="providerForm.contractNumber">
										<input size="35" type="text" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" />
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
								</td>
								<td class="dataLabel">
									Contract Renewal Type :
								</td>
								<td class="dataField">
									<spring:bind path="providerForm.contractRenewalType">
										<select name="<c:out value="${status.expression}"/>">
											<option value="1"
												<c:if test="${1 eq status.value}">selected</c:if>>
												AUTO
											</option>
											<option value="0"
												<c:if test="${0 eq status.value}">selected</c:if>>
												NEW CONTRACT
											</option>
										</select>

										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>

								</td>
							</tr>
							<tr>
								<td class="dataLabel">
									Provider Group :
								</td>


								<td class="dataField">
									<spring:bind path="providerForm.groupId">
										<select name="<c:out value="${status.expression}" />"  id="providerGroupId">
											<option value="">-- TIDAK ADA GROUP -- </option>
											<c:forEach items="${providerGroupList}" var="group">
												<option value="<c:out value="${group.providerGroupId}" />" <c:if test="${status.value eq group.providerGroupId}">selected</c:if>><c:out value="${group.providerGroupCode}" /> - <c:out value="${group.providerGroupName }" /></option>
											</c:forEach>
										</select>
									</spring:bind>
									
									<!-- 
									<spring:bind path="providerForm.parentProviderName">
										<input size="35" type="text" id="parentProviderName" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" />
									</spring:bind>
									<spring:bind path="providerForm.parentProviderId">
										<input type="hidden" id="parentProviderId" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" />
									</spring:bind>
									 -->
								</td>
								<td class="dataLabel">
									Is Using Capitation :
								</td>
								<td class="dataField">
									<spring:bind path="providerForm.isUsingCapitation">
										<select name="<c:out value="${status.expression}"/>">
											<option value=""> -- PILIH SALAH SATU -- </option>
											<option value="1" <c:if test="${1 eq status.value}">selected</c:if>>
												YA, Menggunakan Kapitasi
											</option>
											<option value="0"<c:if test="${0 eq status.value}">selected</c:if>>
												Tidak
											</option>
										</select>
									</spring:bind>

								</td>
							</tr>
							<tr>
								<td class="dataLabel">
									Currency :
								</td>


								<td class="dataField">
									<spring:bind path="providerForm.providerCurrencyId">
										
										<select name="<c:out value="${status.expression }" />">
											<option value="">--- SELECT ONE ---</option>
											<c:forEach items="${currencyList}" var="currency">
												<option value="<c:out value="${currency.currencyId }" />" <c:if test="${status.value eq currency.currencyId }">selected</c:if>><c:out value="${currency.currencyName}" /></option>
											</c:forEach>
										</select>
									</spring:bind>
								</td>
								<td class="dataLabel">
									Specialization :
								</td>
								<td class="dataField">
									<spring:bind path="providerForm.providerSpecializationId">
										
										<select name="<c:out value="${status.expression }" />">
											<option value="">--- SELECT ONE ---</option>
											<c:forEach items="${poliList}" var="poli">
												<option value="<c:out value="${poli.poliklinikId }" />" <c:if test="${status.value eq poli.poliklinikId }">selected</c:if>><c:out value="${poli.poliklinikName}" /></option>
											</c:forEach>
										</select>
									</spring:bind>
								</td>
							</tr>							

							<tr>
								<td class="dataLabel">
									Provider Code :
								</td>


								<td class="dataField">
									<spring:bind path="providerForm.providerCode">
										<input size="35" type="text" name="<c:out value="${status.expression}"/>" id="providerCode" value="<c:out value="${status.value}" />" />
									</spring:bind>
								</td>
								<td class="dataLabel"></td>
								<td class="dataField"></td>
							</tr>	
							<tr>
								<td class="dataLabel">&nbsp;</td>
								<td class="dataField"></td>
								<td class="dataLabel"></td>
								<td class="dataField"></td>
							</tr>
							<tr>
								<td class="dataLabel">
									Bank * :
								</td>

								<td class="dataField">
									<spring:bind path="providerForm.bank">
										<input size="35" type="text" id="bankName"	name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" />
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
								</td>
								<td class="dataLabel">
									Bank Account * :
								</td>
								<td class="dataField">
									<spring:bind path="providerForm.bankAccount">
										<input type="text" size="35" onkeypress="return isNumberKey(this,event)"
											name="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}" />" />
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
								</td>
							</tr>
							<tr>
								<td class="dataLabel">
									Bank Branch :
								</td>


								<td class="dataField">
									<spring:bind path="providerForm.bankBranch">
										<input size="35" type="text"
											name="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}" />" />

										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
								</td>
								<td class="dataLabel">
									Account Name * :
								</td>
								<td class="dataField">
									<spring:bind path="providerForm.accountName">
										<input size="35" type="text"
											name="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}" />" />
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
								</td>
							</tr>
							<tr>
								<td class="dataLabel">
									&nbsp;
								</td>


								<td class="dataField">

								</td>
								<td class="dataLabel"></td>
								<td class="dataField"></td>
							</tr>
							<tr>
								<td class="dataLabel">
									Start Date * :
								</td>
								<td class="dataField">
									<spring:bind path="providerForm.contractStartDate">
										<input type="text" readonly="readonly"
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
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
								</td>

								<td class="dataLabel">
									Telephone :
								</td>
								<td class="dataField">
									<spring:bind path="providerForm.telephone">
									<input type="hidden" id="countryCode" 
											name=""
											value="" />
										<input type="hidden" id="cityCode" 
											name=""
											value="" />
										<input type="text" id="telephone" onkeypress="return isBackspace(this,event,$('#countryCode').val(),$('#cityCode').val(),temp)"
											name="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}" />" />
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
								</td>

							</tr>
							<tr>
								<td class="dataLabel">
									End Date * :
								</td>


								<td class="dataField">
									<spring:bind path="providerForm.contractEndDate">
										<input type="text" readonly="readonly"
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
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
								</td>
								<td class="dataLabel">
									Faximile :
								</td>
								<td class="dataField">
									<spring:bind path="providerForm.faximile">
										<input type="hidden" id="countryCode" 
											name=""
											value="" />
										<input type="hidden" id="cityCode" 
											name=""
											value="" />
										<input type="text" id="faximile" onkeypress="return isBackspace(this,event,$('#countryCode').val(),$('#cityCode').val(),temp)"
											name="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}" />" />
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
								</td>
							</tr>
							<tr>
								<td class="dataLabel">
									&nbsp;
								</td>


								<td class="dataField">

								</td>
								<td class="dataLabel"></td>
								<td class="dataField"></td>
							</tr>
							<tr>
							<td class="dataLabel">
									Country * :
								</td>


								<td class="dataField">
								<spring:bind path="providerForm.countryId">
										<input type="hidden" id="countryId" name="<c:out value="${status.expression}"/>"
												value="<c:out value="${status.value}" />" />
								</spring:bind>
								<spring:bind path="providerForm.country">
										<input size="35" type="text" id="countryName"
											name="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}" />" />
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
									
								</td>
																<td class="dataLabel">
									Email :
								</td>


								<td class="dataField">
									<spring:bind path="providerForm.email">
										<input size="35" type="text"
											name="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}" />" />
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
								</td>
							</tr>
														<tr>
							<td class="dataLabel">
									Region * :
								</td>


								<td class="dataField">
								<spring:bind path="providerForm.regionId">
										<input type="hidden" id="regionId" name="<c:out value="${status.expression}"/>"
												value="<c:out value="${status.value}" />" />
								</spring:bind>
								<spring:bind path="providerForm.region">
										<input size="35" type="text" id="regionName"
											name="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}" />" />
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
									
								</td>
																<td class="dataLabel">
								
								</td>


								<td class="dataField">
								
								</td>
							</tr>
							<tr>
								<td class="dataLabel">
									Province * :
								</td>


								<td class="dataField">
								<spring:bind path="providerForm.provinceId">
										<input type="hidden" id="provinceId" name="<c:out value="${status.expression}"/>"
												value="<c:out value="${status.value}" />" />
									</spring:bind>
									<spring:bind path="providerForm.province">
										<input size="35" type="text" id="provinceName"  readonly="readonly"
											name="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}" />" />
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
									
								</td>
								<td class="dataLabel">
									Website :
								</td>


								<td class="dataField">
									<spring:bind path="providerForm.website">
										<input size="35" type="text"
											name="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}" />" />
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
								</td>
							</tr>
							<tr>
								<td class="dataLabel">
									City * :
								</td>


								<td class="dataField">
								<spring:bind path="providerForm.cityId">
										<input type="hidden" id="cityId" name="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}" />" /> 
									</spring:bind>
									<spring:bind path="providerForm.city">
										<input size="35" type="text" id="cityName" readonly="readonly"
											name="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}" />" />
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
									
								</td>
								
								<td class="dataLabel">
									Contact Person : &nbsp;
								</td>
								<td class="dataField">
									<spring:bind path="providerForm.contactPerson">
										<input size="35" type="text"
											name="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}" />" />
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
								</td>
							</tr>
							<tr>
								<td class="dataLabel">
									Postal Code :
								</td>


								<td class="dataField">
									<spring:bind path="providerForm.postalCode">
										<input type="text" onkeypress="return isNumberKey(this,event)"
											name="<c:out value="${status.expression}"/>" id="postalCode" readonly="readonly"
											value="<c:out value="${status.value}" />" />
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
									&nbsp;
								</td>


								<td class="dataField">

								</td>
								<td class="dataLabel"></td>
								<td class="dataField"></td>
							</tr>



							<tr>
								<td class="dataLabel">
									Confirmation :
								</td>


								<td class="dataField">
									<spring:bind path="providerForm.confirmation">
										<input type="checkbox"
											name="<c:out value="${status.expression}" />" value="Y"
											<c:if test="${status.value eq \"Y\"}" >checked</c:if>>
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
								</td>
								<td class="dataLabel">Longitude : </td>
								<td class="dataField">
								<spring:bind path="providerForm.longitude">
										<input size="35" onkeypress="return isNumberKey(this,event)" type="text"
											name="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}" />" id="savegps2"/>										
									</spring:bind>
									&nbsp;&nbsp;<input title="Lookup Location" accesskey="C"
						
						id="mapButton" value="Pilih Peta"
						type="button">
								</td>
							</tr>
							<tr>
								<td class="dataLabel">
									Provider EDC Code :
								</td>


								<td class="dataField">
									<spring:bind path="providerForm.providerEdcCode">
										<input size="35" onkeypress="return isNumberKey(this,event)" type="text"
											name="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}" />" />
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
										<%
											String alertEdc = (String) request.getAttribute("alertEdc");
											if (alertEdc != null && !alertEdc.trim().equals("")) {
										%>
										<div id="warning">
											<input type="hidden" id="alertEdcProv" name="alertEdcProv" value="<c:out value="${alertEdcTerminal}" />">
											<c:out value="${alertEdc}"></c:out>
										</div>
										<%
											}
										%>
									</spring:bind>
								</td>
								<td class="dataLabel">Latitude : </td>
								<td class="dataField">
								<spring:bind path="providerForm.latitude">
										<input size="35" onkeypress="return isNumberKey(this,event)" type="text"
											name="<c:out value="${status.expression}"/>"
											value="<c:out value="${status.value}" />" id="savegps1" />
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
								</td>
							</tr>
							<tr>
							
							<td class="dataLabel">
									Status Prospek :
								</td>
								<td class="dataField">
									<spring:bind path="providerForm.statusProspek">
										<select name="<c:out value="${status.expression}"/>">
											<option value=""> -- PILIH SALAH SATU -- </option>
											<option value="1" <c:if test="${1 eq status.value}">selected</c:if>>
												NON PROVIDER
											</option>
											<option value="2"<c:if test="${2 eq status.value}">selected</c:if>>
												PROVIDER
											</option>
										</select>
									</spring:bind>
								</td>
								
								<!-- <td class="dataLabel"></td>
								<td class="dataField"></td> -->
								<td class="dataLabel">Payment Periode : </td>
								<td class="dataField">
									<spring:bind path="providerForm.paymentPeriode">
										<input size="35" type="text" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" />										
									</spring:bind>
								</td>
							</tr>
							<tr>
								<td class="dataLabel">
									Provider Type :
								</td>
								<td class="dataField">
									<spring:bind path="providerForm.providerTypeId">
										
										<select name="<c:out value="${status.expression }" />">
											<option value="">--- SELECT ONE ---</option>
											<c:forEach items="${providerTypeList}" var="provtype">
												<option value="<c:out value="${provtype.providerTypeId }" />" <c:if test="${status.value eq provtype.providerTypeId }">selected</c:if>><c:out value="${provtype.providerTypeName}" /></option>
											</c:forEach>
										</select>
									</spring:bind>
								</td>
								<td class="dataLabel">Discount : </td>
								<td class="dataField">
									<spring:bind path="providerForm.discount">
										<input size="35" onkeypress="return isNumberKey(this,event)" type="text" 
											name="<c:out value="${status.expression}"/>" 
											value="<c:out value="${status.value}" />" />										
									</spring:bind>
								</td>
							</tr>
							<tr>
								<td class="dataLabel">EDC Alert (day/s) : </td>
								<td class="dataField">
									<spring:bind path="providerForm.edcAlert">
										<input size="35" onkeypress="return isNumberKey(this,event)" type="text" 
											name="<c:out value="${status.expression}"/>" 
											value="<c:out value="${status.value}" />" />										
									</spring:bind>
								</td>
								<td class="dataLabel"></td>
								<td class="dataField"></td>
							</tr>
							<tr>
								<td class="dataLabel">&nbsp;</td>
								<td class="dataField">&nbsp;</td>
								<td class="dataLabel">&nbsp;</td>
								<td class="dataField">&nbsp;</td>
							</tr>
							<tr>
								<td class="dataLabel">Total Roll Paper : </td>
								<td class="dataField">
									<spring:bind path="providerForm.rollPaperAmount">
										<input size="35" onkeypress="return isNumberKey(this,event)" type="text"  
											readonly="readonly" disabled="disabled"
											name="<c:out value="${status.expression}"/>" 
											value="<c:out value="${status.value}" />" />										
									</spring:bind>
								</td>
								<td class="dataLabel">Register Roll Paper Limit (cm)</td>
								<td class="dataField">
									<spring:bind path="providerForm.rollPaperRegisterCustomize">
										<input size="35" onkeypress="return isNumberKey(this,event)" type="text"
											name="<c:out value="${status.expression}"/>" 
											value="<c:out value="${status.value}" />" />										
									</spring:bind>
								</td>
							</tr>
							<tr>
								<td class="dataLabel">Limit Roll Paper Alert : </td>
								<td class="dataField">
									<spring:bind path="providerForm.rollPaperLimitCustomize">
										<input size="35" onkeypress="return isNumberKey(this,event)" type="text" 
											name="<c:out value="${status.expression}"/>" 
											value="<c:out value="${status.value}" />" />										
									</spring:bind>
								</td>
								<td class="dataLabel">Payment Roll Paper Limit (cm)</td>
								<td class="dataField">
									<spring:bind path="providerForm.rollPaperPaymentCustomize">
										<input size="35" onkeypress="return isNumberKey(this,event)" type="text" name="<c:out value="${status.expression}"/>" 
											value="<c:out value="${status.value}" />" />										
									</spring:bind>
								</td>
							</tr>
							<tr>
								<td class="dataLabel">&nbsp;</td>
								<td class="dataField"></td>
								<td class="dataLabel"></td>
								<td class="dataField"></td>
							</tr>

							<tr>
								<td class="dataLabel">
									Address :
								</td>

								<td class="dataField" >
									<spring:bind path="providerForm.address">
										<textarea cols="50" rows="8" class="inputbox"
											name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
								</td>
								<td class="dataLabel">
									Description :
								</td>

								<td class="dataField" >
									<spring:bind path="providerForm.description">
										<textarea cols="50" rows="8" class="inputbox"
											name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
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
				</td>
			</tr>
		</tbody>
	</table>
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tbody>
			<tr>
				<td style="padding-top: 2px;">
					<input type="hidden" name="notyet" value="">
					<input title="Save [Alt+Shift+S]" accesskey="S"	class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"	onClick="javascript:simpan()" name="Save" value=" Save " type="submit">
					<input title="Cancel [Alt+Shift+C]" accesskey="C"	class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"	onClick="javascript:cancel()" name="Cancel" value=" Cancel " type="button">
				</td>
				<td align="right"></td>
			</tr>
		</tbody>
	</table>
</form>
<div id="mapDialog" style="display:none">
	<div class="mapCanvas" style="height:100%"></div>
</div>

<script type="text/javascript">
var temp=false;
$(document).ready(function(){


		$("#bankName").autocomplete({
			source: function( request, response ) {
			$.ajax({
				url: "bank?navigation=lookupjson",
				dataType: "json",
				data: {q: request.term},
				success: function(data) {
							response($.map(data, function(item) {
							return {
								label: item.name,
								id: item.id,
								number: item.number
								};
						}));
					}
				});
			},
			minLength: 2,
			select: function(event, ui) {
				$(this).parents("dd").find(".error_message").hide();
				//alert("BankName : " + ui.item.label + "\nBankId : " + ui.item.id);
			}
		})
		.autocomplete( "instance" )._renderItem = function( ul, item ) {
	      return $( "<a>" )
	        .append( item.label )
	        .appendTo( ul );
	    };
    
    /* remove by aju on 20150508, upgrade from autocomplete pulgin to jQueryUI above :D
    $("#bankName").autocomplete("bank?navigation=lookupjson", {
        max: 7,
        dataType: "json",
        parse: function(data) {
            return $.map(data, function(row) {
                return {
                    data: row,
                    value: row.name,
                    result: row.name
                }
            });
        },
        formatItem: function(row) {
            return "<font color='#000' >"+ row.name +"</font>" ;
        }
    }).bind("result", function(data, value){
        $(this).parents("dd").find(".error_message").hide();
    });
    */
    
    $("#parentProviderName").autocomplete({
			source: function( request, response ) {
			$.ajax({
				url: "provider?navigation=lookupjson",
				dataType: "json",
				data: {q: request.term},
				success: function(data) {
							response($.map(data, function(item) {
							return {
								label: item.name,
								id: item.id,
								number: item.number
								};
						}));
					}
				});
			},
			minLength: 2,
			select: function(event, ui) {
				$(this).parents("dd").find(".error_message").hide();        
				//alert("parentProviderName : " + ui.item.label + "\nparentProviderId : " + ui.item.id);
	    		$("#parentProviderId").val(ui.item.id);
	    		
			}
		})
		.autocomplete( "instance" )._renderItem = function( ul, item ) {
	      return $( "<a>" )
	        .append( item.label )
	        .appendTo( ul );
	    };
    
    /* remove by aju on 20150508, upgrade from autocomplete pulgin to jQueryUI above :D
    $("#parentProviderName").autocomplete("provider?navigation=lookupjson", {
        max: 7,
        dataType: "json",
        parse: function(data) {
            return $.map(data, function(row) {
                return {
                    data: row,
                    value: row.name,
                    result: row.name
                }
            });
        },
        formatItem: function(row) {
            return "<font color='#000' >"+ row.name +"</font>" ;
        }
    }).bind("result", function(data, value){
        $(this).parents("dd").find(".error_message").hide();        
	    $("#parentProviderId").val(value.id);
    });
    */
    
    $("#countryName").autocomplete({
			source: function( request, response ) {
			$.ajax({
				url: "refcountry?navigation=lookupjson",
				dataType: "json",
				data: {q: request.term},
				success: function(data) {
							response($.map(data, function(item) {
							return {
								label: item.name,
								id: item.id,
								number: item.number
								};
						}));
					}
				});
			},
			minLength: 2,
			select: function(event, ui) {
				$(this).parents("dd").find(".error_message").hide();
		        //include the countryId for filtering
		        //alert("CountryName : " + ui.item.label + "\nCountryID : " + ui.item.id + "\nCountryCode : " + ui.item.number);
		        $("#countryId").val(ui.item.id);
		        $("#countryCode").val(ui.item.number);
		        $("#countryName").val(ui.item.label);
		        $("#telephone").val($("#countryCode").val()+"-");
		        
		        
		        
		        $("#faximile").val($("#countryCode").val()+"-");
		        temp=false;
		   
		   		if($(this).val() != '') {
          				 $("#provinceName").removeAttr('readonly');
        		}else{
        	 	 		$("#provinceName").attr('readonly','readonly');
        	 	 		$("#telephone").val("");
		       			$("#faximile").val("");
        		}
		   
        		$("#countryName").keyup(function() {
        			if($(this).val() != '') {
          				 $("#provinceName").removeAttr('readonly');
        			}else{
        				$("#provinceName").attr('readonly','readonly');
        	 	 		$("#telephone").val("");
		       			$("#faximile").val("");
        		}
     			});
     			
		        //alert(value.id);
			}
		})
		.autocomplete( "instance" )._renderItem = function( ul, item ) {
	      return $( "<a>" )
	        .append( item.label )
	        .appendTo( ul );
	    };
    
    /* remove by aju on 20150508, upgrade from autocomplete pulgin to jQueryUI above :D
    $("#countryName").autocomplete("refcountry?navigation=lookupjson", {
        max: 7,
        dataType: "json",
        parse: function(data) {
            return $.map(data, function(row) {
                return {
                    data: row,
                    value: row.name,
                    result: row.name
                }
            });
        },
        formatItem: function(row) {
            return "<font color='#000' >"+ row.name +"</font>" ;
        }
    }).bind("result", function(data, value){
        $(this).parents("dd").find(".error_message").hide();
        //include the countryId for filtering
        //alert(value.toSource());
        $("#countryId").val(value.id);
        $("#countryCode").val(value.number);
        $("#telephone").val($("#countryCode").val()+"-");
        
        $("#faximile").val($("#countryCode").val()+"-");
        temp=false;
        //alert(value.id);
    });
    */
    
    $("#provinceName").autocomplete({
			source: function( request, response ) {
			$.ajax({
				url: "refprovince?navigation=lookupjson",
				dataType: "json",
				data: {
					q: request.term,
					countryId : $("#countryId").val()
				},
				success: function(data) {
							response($.map(data, function(item) {
							return {
								label: item.name,
								id: item.id,
								number: item.number
								};
						}));
					}
				});
			},
			minLength: 2,
			select: function(event, ui) {
				$(this).parents("dd").find(".error_message").hide();
		        //alert("ProvinceName : " + ui.item.label + "\nProvinceID : " + ui.item.id + "\nProvinceCode : " + ui.item.number);
		        $("#provinceId").val(ui.item.id);
		        $("#provinceName").val(ui.item.label);
		        
		        if($(this).val() != '') {
        				$("#cityName").removeAttr('readonly');
          				 $("#countryName").attr('readonly','readonly');
        			}else{
        				$("#cityName").attr('readonly','readonly');
        	 	 		$("#countryName").removeAttr('readonly');
        		}
		        
		        $("#provinceName").keyup(function() {
        			if($(this).val() != '') {
        				$("#cityName").removeAttr('readonly');
          				 $("#countryName").attr('readonly','readonly');
        			}else{
        				$("#cityName").attr('readonly','readonly');
        	 	 		$("#countryName").removeAttr('readonly');
        		}
     			});
			}
		})
		.autocomplete( "instance" )._renderItem = function( ul, item ) {
	      return $( "<a>" )
	        .append( item.label )
	        .appendTo( ul );
	    };
	    
	    $("#regionName").autocomplete({
			source: function( request, response ) {
			$.ajax({
				url: "refregion?navigation=lookupjson",
				dataType: "json",
				data: {
					q: request.term,
					countryId : $("#countryId").val()
				},
				success: function(data) {
							response($.map(data, function(item) {
							return {
								label: item.name,
								id: item.id,
								number: item.number
								};
						}));
					}
				});
			},
			minLength: 2,
			select: function(event, ui) {
				$(this).parents("dd").find(".error_message").hide();
		        //alert("ProvinceName : " + ui.item.label + "\nProvinceID : " + ui.item.id + "\nProvinceCode : " + ui.item.number);
		        $("#regionId").val(ui.item.id);
		        $("#regionName").val(ui.item.label);
		        
		        if($(this).val() != '') {
        				$("#provinceName").removeAttr('readonly');
          				 $("#countryName").attr('readonly','readonly');
        			}else{
        				$("#provinceName").attr('readonly','readonly');
        	 	 		$("#countryName").removeAttr('readonly');
        		}
		        
		        $("#regionName").keyup(function() {
        			if($(this).val() != '') {
        				$("#provinceName").removeAttr('readonly');
          				 $("#countryName").attr('readonly','readonly');
        			}else{
        				$("#provinceName").attr('readonly','readonly');
        	 	 		$("#countryName").removeAttr('readonly');
        		}
     			});
			}
		})
		.autocomplete( "instance" )._renderItem = function( ul, item ) {
	      return $( "<a>" )
	        .append( item.label )
	        .appendTo( ul );
	    };
    /* remove by aju on 20150508, upgrade from autocomplete pulgin to jQueryUI above :D
    $("#provinceName").autocomplete("refprovince?navigation=lookupjson", {
        max: 7,
        dataType: "json",
        parse: function(data) {
            return $.map(data, function(row) {
                return {
                    data: row,
                    value: row.name,
                    result: row.name
                }
            });
        },
        formatItem: function(row) {
            return "<font color='#000' >"+ row.name +"</font>" ;
        },
        extraParams: {
       		countryId: function() { return $("#countryId").val(); }
   		}
    }).bind("result", function(data, value){
        $(this).parents("dd").find(".error_message").hide();
        //alert(value.toSource());
        $("#provinceId").val(value.id);
    });
    */
    
    $("#cityName").autocomplete({
			source: function( request, response ) {
			$.ajax({
				url: "refcity?navigation=lookupjson",
				dataType: "json",
				data: {
					q: request.term,
					provinceId : $("#provinceId").val()
				},
				success: function(data) {
							response($.map(data, function(item) {
							return {
								label: item.name,
								id: item.id,
								number: item.number
								};
						}));
					}
				});
			},
			minLength: 2,
			select: function(event, ui) {		        
		        $(this).parents("dd").find(".error_message").hide();
		      	//alert("CityName : " + ui.item.label + "\nCityID : " + ui.item.id + "\nCityCode : " + ui.item.number);
		        $("#cityId").val(ui.item.id);
		        $("#cityCode").val(ui.item.number);
		        $("#cityName").val(ui.item.label);
		        
				var provCategory = $('#providerCategoryId option:selected').val();
				
				var provGroup = $('#providerGroupId option:selected').val();
				var cityId = $("#cityId").val();
		        
		        AJAXRemoteController.generateProviderCode (provCategory,cityId,provGroup, {
                    callback:function (provCode){
                            $("#providerCode").val(provCode);
                    }
	            });
		        
		      	var a=$("#cityCode").val();
		        var b=a.substring(1);
		     //   alert("test"+b);
		        $("#telephone").val($("#countryCode").val()+"-"+b+"-");
		        
		        $("#faximile").val($("#countryCode").val()+"-"+b+"-");
		        
		        if($(this).val() != '') {
        				$("#postalCode").removeAttr('readonly');
          				 $("#provinceName").attr('readonly','readonly');
        			}else{
        				$("#postalCode").attr('disabled','readonly');
        	 	 		$("#provinceName").removeAttr('readonly');
        	 	 		$("#telephone").val($("#countryCode").val()+"-");
		        
		        		$("#faximile").val($("#countryCode").val()+"-");
        		}
		        
		        $("#cityName").keyup(function() {
        			if($(this).val() != '') {
        				$("#postalCode").removeAttr('readonly');
          				 $("#provinceName").attr('readonly','readonly');
        			}else{
        				$("#postalCode").attr('readonly','readonly');
        	 	 		$("#provinceName").removeAttr('readonly');
        	 	 		$("#telephone").val($("#countryCode").val()+"-");
		        
		        		$("#faximile").val($("#countryCode").val()+"-");
        		}
     			});
		        temp=true;
			}
		})
		.autocomplete( "instance" )._renderItem = function( ul, item ) {
	      return $( "<a>" )
	        .append( item.label )
	        .appendTo( ul );
	    };
	    
	    $("#postalCode").keyup(function() {
        	if($(this).val() != '') {
          				 $("#cityName").attr('readonly','readonly');
        			}else{
        	 	 		 $("#cityName").removeAttr('readonly');
        		}
     			});
	    
    
    /* remove by aju on 20150508, upgrade from autocomplete pulgin to jQueryUI above :D
    $("#cityName").autocomplete("refcity?navigation=lookupjson", {
        max: 7,
        dataType: "json",
        parse: function(data) {
            return $.map(data, function(row) {
                return {
                    data: row,
                    value: row.name,
                    result: row.name
                }
            });
        },
        formatItem: function(row) {
            return "<font color='#000' >"+ row.name +"</font>" ;
        },
        extraParams: {
       		provinceId: function() { return $("#provinceId").val(); }
   		}
    }).bind("result", function(data, value){
        $(this).parents("dd").find(".error_message").hide();
      // alert(value.toSource());
        $("#cityId").val(value.id);
        $("#cityCode").val(value.number);
      	var a=$("#cityCode").val();
        var b=a.substring(1);
     //   alert("test"+b);
        $("#telephone").val($("#countryCode").val()+"-"+b+"-");
        
        $("#faximile").val($("#countryCode").val()+"-"+b+"-");
        temp=true;
    });
    */
    
      var tempor = $("#alertEdcProv").val();
		if($("#alertEdcProv").val() != null || tempor.length > 0)
			alert($("#alertEdcProv").val());
 });
 
	function simpan (){
	<c:choose>
	<c:when test="${navigation eq 'tambah'}">
		document.form1.method = "POST";
		document.form1.action = "provider-form";
		document.form1.submit();
	</c:when>
	<c:when test="${navigation eq 'tambahClaim'}">
		document.form1.method = "POST";
		document.form1.action = "providerclaim-form";
		document.form1.submit();
	</c:when>
	</c:choose>
	}
	
	function simpanProviderClaim (){

		document.form1.method = "POST";
		document.form1.action = "providerclaim-form";
		document.form1.submit();
	}
	function cancel(){
		<c:choose>
			<c:when test="${navigation eq 'update'}">
					document.form1.action = "provider";
					document.form1.navigation.value = "detail";		
			</c:when>
			<c:when test="${navigation eq 'listpolicy'}">
					document.form1.action = "provider";
					document.form1.policyId.value = <c:out value="${policyId}" />				
					document.form1.navigation.value = "listpolicy";		
			</c:when>
			<c:when test="${navigation eq 'tambahprovider'}">
					document.form1.action = "provider";
					document.form1.navigation.value = "listmember";		
					document.form1.memberId.value = <c:out value="${memberId}" />
			</c:when>
			<c:otherwise>
					document.form1.action = "provider";
					document.form1.memberId.value = <c:out value="${policyId}" />				
					document.form1.navigation.value = "listpolicy";		
			</c:otherwise>
		</c:choose>
		document.form1.submit();
		
		
	}

</script>
