<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<link rel="stylesheet" type="text/css" href="css/autocomplete.css">
<link rel="stylesheet" type="text/css" href="css/button.css">

<link rel="stylesheet" type="text/css" href="css/jquery/autocomplete/jquery.autocomplete.css"/>

<script type='text/javascript' src='dwr/interface/AJAXClaimService.js'></script>
<script type='text/javascript' src='dwr/engine.js'></script>
<script type='text/javascript' src='dwr/util.js'></script>

<script type="text/javascript" src="scripts/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="scripts/jquery/plugin/jquery.autocomplete.pack.js"></script>


<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
	  <c:choose>
		<c:when test="${navigation eq 'update'}">
     		<td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Update Claim</h3></td>
     	</c:when>
		<c:when test="${navigation ne 'update'}">
     		<td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Register Claim</h3></td>
     	</c:when>
	  </c:choose>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>

<%
String benefitLimit = (String) request.getAttribute("benefitLimit");
System.out.println("benefitLimit = " + benefitLimit);
%>

<form action="claim-form" method="POST"  name="form1" id="form_layout">
<input type="hidden" name="navigation" value="${navigation }">
<!-- 
<input type="hidden" name="batchClaimId" value="<c:out value="${claimForm.batchClaimId}" />">
 -->
<input type="hidden" id="paymentRecipient" name="paymentRecipient" value="<c:out value="${claimForm.paymentRecipient}" />">
<input type="hidden" id="caseNavigation" name="caseNavigation" value='<c:out value="${caseNavigation }"></c:out>'>
<input type="hidden" id="benefitLimit" name="benefitLimit" value='<c:out value="${benefitLimit }"></c:out>'>

<!-- <c:if test="${claimForm.claimTypeId eq \"1\"}">
	<c:if test="${claimForm.paymentRecipient eq 1}">
		<input type="hidden" name="memberGroupId" value="<c:out value="${claimForm.memberGroupId }" />" id="memberGroupId">
 </c:if>
</c:if> -->

<input type="hidden" name="memberGroupId" value="<c:out value="${claimForm.memberGroupId }" />" id="memberGroupId">

<%-- <c:if test="${claimForm.claimTypeId eq \"2\"}"> --%>
<%-- 	<c:if test="${claimForm.paymentRecipient eq 3}"> --%>
<%-- 		<input type="hidden" name="clientId" value="<c:out value="${claimForm.clientId }" />" id="clientId"> --%>
<%-- 	</c:if>	 --%>
<%-- </c:if> --%>

<input type="hidden" name="clientId" value="<c:out value="${claimForm.clientId }" />" id="clientId">
<input type="hidden" name="claimNumber" value="<c:out value="${claimForm.claimNumber }" />" id="claimNumber">
<input type="hidden" name="policyId" value="<c:out value="${claimForm.claim.policyId }" />" id="policyId">

<%
String alert = (String) request.getAttribute("alert");
if (alert != null && !alert.trim().equals("")) {%>
	<div id="warning">
		<c:out value="${alert}"></c:out>
	</div>
	<%}%>


<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>
		<tr>
			<td>
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<tbody>
						<spring:bind path="claimForm.claimId">
							<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
							<div id="fredcaption">
								<c:out value="${status.errorMessage}" />
							</div>
						</spring:bind>
						<tr>
							<td class="dataLabel"> </td>
		    				<td class="dataField" colspan="3">&nbsp;</td>
						</tr>	
						<tr>
							<td class="dataLabel"><c:if test="${claimForm.batchClaimId ne null}">Batch Number : </c:if></td>		
							<td class="dataField">
								<c:if test="${claimForm.batchClaimId ne null}">
								<spring:bind path="claimForm.batchClaimId">
									<input type="hidden"  name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />">									
								</spring:bind>
								<spring:bind path="claimForm.batchClaimNumber">
									<input type="text" size=35 readonly="readonly" disabled="disabled" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />">
								</spring:bind>
								</c:if>						
							</td>							    
						    <td class="dataLabel">Claim Service * : </td>		
							<td class="dataField">
								<spring:bind path="claimForm.caseCategoryId">
									<select tabindex="1" name="${status.expression}" id="caseCategoryId" onchange="javascript:changeService();">
										<option value=""> -- SELECT SERVICE -- </option>
										<c:forEach items="${caseCategory}" var="cc">
											<option value="${cc.caseCategoryId}" <c:if test="${status.value eq cc.caseCategoryId}">selected</c:if>><c:out value="${cc.caseCategoryName}" /></option>
										</c:forEach>
									</select>
									<div id="fredcaption">
										<c:out value="${status.errorMessage}" />
									</div>
								</spring:bind>							
							</td>
						</tr>
						<tr>
							<td class="dataLabel">Customer Number * : </td>		
							<td class="dataField">
								<spring:bind path="claimForm.memberId">
									<input type="hidden" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" id="memberId">
								</spring:bind>
								<spring:bind path="claimForm.policyNumber">
									<input type="text" size=35 id="member_number" tabindex="2"  name="<c:out value="${status.expression}"/>"   value="<c:out value="${status.value}" />" >&nbsp;
									<c:if test="${claimForm.paymentRecipient  ne 2}">
									</c:if>
									<div id="fredcaption">
										<c:out value="${status.errorMessage}" />
									</div>
								</spring:bind>
							</td>
						    <td class="dataLabel"> Location : </td>				
							<td class="dataField">
								<spring:bind path="claimForm.locationId">									
									<select name="<c:out value="${status.expression}" />" tabindex="5">
										<option =""> -- SELECT LOCATION -- </option>
										<c:forEach items="${treatmentLocation}" var="location">
											<option value="<c:out value="${location.locationId}" />" <c:if test="${(location.locationId eq status.value) or (location.isDefault eq 1)}">selected</c:if>><c:out value="${location.location}" /></option>
										</c:forEach>
									</select>
									<div id="fredcaption">
										<c:out value="${status.errorMessage}" />
									</div>
								</spring:bind>
							</td>	
						</tr>
						<tr>
						    <td class="dataLabel">Member Name : </td>		
							<td class="dataField">
								<spring:bind path="claimForm.name">
									<input type="text" readonly="readonly" tabindex="3"  id="name" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" size="35" >
								</spring:bind>								
							</td>	  
							<td class="dataLabel"> Provider Name * : </td>
							<td class="dataField">
							<spring:bind path="claimForm.providerName">
								<input type="text"  size="35" id="providerName" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" >
								<c:if test="${claimForm.claimTypeId eq \"1\"}"> <!--  <input type="button" name="lookupCaseButton" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" value="Lookup" onClick="javascript:lookupProvider()">-->
									
								</c:if>
									<div id="fredcaption">
										<c:out value="${status.errorMessage}" />
									</div>
								</spring:bind>
								<spring:bind path="claimForm.providerId">
								<input type="hidden" readonly="readonly" id="providerId" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" maxlength="35">
									<div id="fredcaption">
										<c:out value="${status.errorMessage}" />
									</div>
								</spring:bind>
							</td>	 		
						</tr>
						<tr>
						<td class="dataLabel"></td>
						<td class="dataField"></td>
						<td class="dataLabel"> Doctor Name * : </td>
							<td class="dataField">
							<spring:bind path="claimForm.doctorName">
								<input type="text"  size="35" id="doctorName" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>"  >
									<div id="fredcaption">
										<c:out value="${status.errorMessage}" />
									</div>
								</spring:bind>
								
								<input type="hidden" readonly="readonly" id="doctorId" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" maxlength="35">
									<div id="fredcaption">
										<c:out value="${status.errorMessage}" />
									</div>
							</td>	 		
						
						</tr>
						<c:if test="${claimForm.claimTypeId eq \"1\" and claimForm.paymentRecipient eq 2}">
						<tr>
							<td class="dataLabel">Patient Name * : </td>		
							<td class="dataField">
					
							<spring:bind path="claimForm.patientName">
								
								<select name="<c:out value="${status.expression}" />" id="patientId"> 
									<option value="-"> -- PILIH PASIEN -- </option>
									<c:forEach items="${dependentCollection}" var="dependent">
										<option value="<c:out value="${dependent.memberId}" />"><c:out value="${dependent.firstName}" /></option>
									</c:forEach>
								</select>
								  
								<div id="fredcaption">
									<c:out value="${status.errorMessage}" />
								</div>
							</spring:bind>	
							</td>
							
						</tr>
						</c:if>
						<c:if test="${claimForm.claimTypeId eq \"2\"}">
							<tr>
								<td class="dataLabel">Case Reference : </td>		
								<td class="dataField">
									<spring:bind path="claimForm.caseId">
										<input type="hidden" name="<c:out value="${status.expression}"/>" id="idCase" value="<c:out value="${status.value}" />" >
									</spring:bind>
									<spring:bind path="claimForm.caseNumber">
										<input type="text" readonly="readonly" size=35 name="<c:out value="${status.expression}"/>" id="numberCase" value="<c:out value="${status.value}" />" >&nbsp;
									</spring:bind>									
								</td>
							    <td class="dataLabel"> </td>				
								<td class="dataField"></td>	    
							</tr>
						</c:if>
						<tr>
							<td class="dataLabel">&nbsp; </td>
							<td class="dataField">&nbsp;</td>
						    <td class="dataLabel"></td>
						    <td class="dataField"></td>
						</tr>
						<tr>
							<td class="dataLabel"><c:if test="${claimForm.batchClaimId ne null}"> Received Date : </c:if></td>
							<td class="dataField">
								<c:if test="${claimForm.batchClaimId ne null}">
								<spring:bind path="claimForm.claimDate">
										<input type="text" readonly="readonly" name="<c:out value="${status.expression}" />" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="10">
										
									<div id="fredcaption">
										<c:out value="${status.errorMessage}" />
									</div>
								</spring:bind>
								</c:if>
							</td>
						    <td class="dataLabel"><c:if test="${theUser.roleId.roleId eq 0 or theUser.roleId.roleId eq 3 or theUser.roleId.roleId eq 4 or theUser.roleId.roleId eq 1 or theUser.roleId.roleId eq 2}">Diagnosis 1 * : </c:if></td>
						    <td class="dataField">
						    	<c:if test="${theUser.roleId.roleId eq 0 or theUser.roleId.roleId eq 3 or theUser.roleId.roleId eq 4 or theUser.roleId.roleId eq 1 or theUser.roleId.roleId eq 2}">
							    	<spring:bind path="claimForm.diagnosisId">			
							    	<input type="hidden" id="diagnosis1Id" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />">
							    		<div id="fredcaption">
										<c:out value="${status.errorMessage}" />
									</div>
									</spring:bind>	
									<spring:bind path="claimForm.diagnosis1Code">
										<input type="hidden" id="diagnosis1Code"   name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />">
									</spring:bind>
						
									<spring:bind path="claimForm.diagnosis1Text">
										<input type="text"  size="40" id="diagnosis1Text" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />">
									</spring:bind>	
								</c:if>				
							</td>
						</tr>
			
						<tr>
							<td class="dataLabel"> Admission Date * : </td>
							<td class="dataField">
								<spring:bind path="claimForm.admissionDate">
										<input type="text" name="<c:out value="${status.expression}" />" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="10" onchange="javascript:changeService();">
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
						    <td class="dataLabel"><c:if test="${theUser.roleId.roleId eq 0 or theUser.roleId.roleId eq 3 or theUser.roleId.roleId eq 4 or theUser.roleId.roleId eq 1 or theUser.roleId.roleId eq 2}">Diagnosis 2 : </c:if></td>
						    <td class="dataField">
						    	<c:if test="${theUser.roleId.roleId eq 0 or theUser.roleId.roleId eq 3 or theUser.roleId.roleId eq 4 or theUser.roleId.roleId eq 1 or theUser.roleId.roleId eq 2}">	
								    <spring:bind path="claimForm.diagnosis2Id">	
											<input type="hidden" id="diagnosis2Id" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />">
											<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>	
									<spring:bind path="claimForm.diagnosis2Code">
										<input type="hidden"  id="diagnosis2Code"  tabindex="1"  name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />">
									</spring:bind>
									
									<spring:bind path="claimForm.diagnosis2Text">
										<input type="text" tabindex="2" id="diagnosis2Text"  size="40"  name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />">
									</spring:bind>
								</c:if>
							</td>
						</tr>
						<tr>
							<td class="dataLabel"> Discharge Date * : </td>		
							<td class="dataField">
								<spring:bind path="claimForm.dischargeDate">
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
				    		<td class="dataLabel"><c:if test="${theUser.roleId.roleId eq 0 or theUser.roleId.roleId eq 3 or theUser.roleId.roleId eq 4 or theUser.roleId.roleId eq 1 or theUser.roleId.roleId eq 2}">Diagnosis 3 : </c:if></td>
				    		<td class="dataField">
				    			<c:if test="${theUser.roleId.roleId eq 0 or theUser.roleId.roleId eq 3 or theUser.roleId.roleId eq 4 or theUser.roleId.roleId eq 1 or theUser.roleId.roleId eq 2}">
							    	<spring:bind path="claimForm.diagnosis3Id">
										<input type="hidden" id="diagnosis3Id" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />">
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
									<spring:bind path="claimForm.diagnosis3Code">
										<input autocomplete="off" id="diagnosis3Code"  type="hidden" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />">
									</spring:bind>
									<spring:bind path="claimForm.diagnosis3Text">
										<input type="text" size="40" id="diagnosis3Text"  name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />">
									</spring:bind>		
								</c:if>	
							</td>
						</tr>		
						<c:if test="${theUser.roleId.roleId eq 0 or theUser.roleId.roleId eq 3 or theUser.roleId.roleId eq 4 or theUser.roleId.roleId eq 1 or theUser.roleId.roleId eq 2}">	
							<tr>
								<td class="dataLabel"> &nbsp; </td>
								<td class="dataField">&nbsp;</td>
							    <td class="dataLabel"></td>
							    <td class="dataField"></td>
							</tr>		
							<tr>
								<td class="dataLabel"> Benefit Upgrade Type : </td>
								<td class="dataField">
									<spring:bind path="claimForm.roomUpgradeType">
										<select name="<c:out value="${status.expression}" />" id="upgradeType" >
											<option value="-2"> -- PILIH KONDISI KAMAR -- </option>
											<c:forEach items="${roomUpgradeType}" var="room">
												<option  value="<c:out value="${room.treatmentUpgradeTypeId}" />" <c:if test="${status.value eq room.treatmentUpgradeTypeId}">selected</c:if> ><c:out value="${room.treatmentUpgradeTypeName}" /> </option>
											</c:forEach>		
										</select>			  
									</spring:bind>
								</td>	    	
					    		<td class="dataLabel">Pembayaran Dimuka : </td>			
								<td class="dataField">
									<spring:bind path="claimForm.pembayaranDimuka">
										<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="22">
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind> 
								</td>
							</tr>	
							<tr>
								<td class="dataLabel"> Benefit Room Rate : </td>
								<td class="dataField">
									<spring:bind path="claimForm.benefitRoomRate">
										<input type="text"  size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="22" id="benefitRoomRate">
									</spring:bind>
								</td>	    	
							    <td class="dataLabel"> Claim Room Rate : </td>
								<td class="dataField">
									<spring:bind path="claimForm.claimRoomRate">
										<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="22">
									</spring:bind>
								</td>	    
							</tr>
							<tr>
								<td class="dataLabel">&nbsp; </td>
								<td class="dataField">&nbsp;</td>
							    <td class="dataLabel"></td>
							    <td class="dataField"></td>
							</tr>
							<tr>							
								<td class="dataLabel"> Is Refered : </td>
								<td class="dataField">
									<spring:bind path="claimForm.isRujukan">
										<select name="<c:out value="${status.expression}"/>">
											<option value=""> -- PILIH SALAH SATU -- </option>
											<option value="1" <c:if test="${status.value eq 1}">selected</c:if>>Ya, Claim Rujukan</option>				
											<option value="0" <c:if test="${status.value eq 0}">selected</c:if>>Tidak</option>
											
										</select>
									</spring:bind>
								</td>	    	
								<td class="dataLabel"> Reference Number : </td>		
								<td class="dataField">
									<spring:bind path="claimForm.nomorRujukan">
										<input type="text" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" >
									</spring:bind>	
								</td>
							</tr>
							<tr>
								<td class="dataLabel"> Iterative : </td>
								<td class="dataField">
									<spring:bind path="claimForm.iterative">
										<input type="text"  name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" size="5">
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
								</td>	    	
								<td class="dataLabel">Surgery : </td>		
								<td class="dataField">
									<spring:bind path="claimForm.isSurgery">
										<input type="checkbox" name="isSurgery" value="1">
									</spring:bind>
									<spring:bind path="claimForm.surgeryLevel">			
										<select name="<c:out value="${status.expression}" />">
											<option value="-1"> -- PILIH LEVEL -- </option>				
											<option value="0" <c:if test="${status.value eq \"0\" }">selected</c:if>>KOMPLEKS</option>
											<option value="1" <c:if test="${status.value eq \"1\" }">selected</c:if>>BESAR</option>
											<option value="2" <c:if test="${status.value eq \"2\" }">selected</c:if>>SEDANG</option>
											<option value="3" <c:if test="${status.value eq \"3\" }">selected</c:if>>KECIL</option>
<!-- 											<option value="4" <c:if test="${status.value eq \"4\" }">selected</c:if>>MINOR</option> -->
										</select>
									</spring:bind>	
								</td>
							</tr>	
							<tr>
								<%-- Comment 20150811 by FVO, changin from checkbox to combobox
								<td class="dataLabel"> Has Complication : </td>
								<td class="dataField">
									<spring:bind path="claimForm.hasComplication">
										<input type="checkbox" name="<c:out value="${status.expression}" />" value="1" <c:if test="${status.value eq '1' }">checked</c:if>>
									</spring:bind>
								</td>	
								 --%>
								<td class="dataLabel"> Has Complication : </td>
								<td class="dataField">
									<spring:bind path="claimForm.hasComplication">
										<select name="<c:out value="${status.expression}"/>">
											<option value="1" <c:if test="${status.value eq 1}">selected</c:if>>Tanpa Komplikasi</option>				
											<option value="2" <c:if test="${status.value eq 2}">selected</c:if>>Komplikasi Ringan</option>
											<option value="3" <c:if test="${status.value eq 3}">selected</c:if>>Komplikasi Berat</option>
										</select>
									</spring:bind>
								</td>	    	
								<td class="dataLabel">is Accident : </td>		
								<td class="dataField">
									<spring:bind path="claimForm.isAccident">
										<input type="checkbox" name="<c:out value="${status.expression}" />" value="1" <c:if test="${status.value eq '1' }">checked</c:if>>
									</spring:bind>	
								</td>
							</tr>
									
						</c:if>
						<c:if test="${theUser.roleId.roleId eq 15}">
							<tr>
								<td class="dataLabel">Claim Charge Value :</td>
								<td class="dataField">
									<spring:bind path="claimForm.claim.claimChargeValue">
											<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="22">
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
								</td>
					    		<td class="dataLabel"></td>			
								<td class="dataField"></td>
							</tr>
						</c:if>
						<tr>
							<td class="dataLabel"> Description : </td>
					    	<td class="dataField" colspan="3">
								<spring:bind path="claimForm.description">
								<textarea cols="60" rows="8" class="inputbox" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
									<div id="fredcaption">
										<c:out value="${status.errorMessage}" />
									</div>
								</spring:bind>
							</td>
						</tr>			
						<tr>
							<td class="dataLabel"> </td>
					    	<td class="dataField" colspan="3">&nbsp;</td>
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
	      	<td style="padding-top: 2px;padding-left: 1px;">
		    	<input type="hidden" name="notyet" value="">
		    	<c:if test="${theUser.userType eq 2}">
		    		<c:if test="${theUser.roleId.roleId eq 15}">
			    		<input title="Save [Alt+Shift+S]" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="this.disabled='disabled';javascript:register()" name="Save" value=" Register " type="submit">
			    		<input title="Cancel [Alt+Shift+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="this.disabled='disabled';javascript:cancel()" name="Cancel" value=" Cancel " type="button">         

		    		</c:if>
		    		<c:if test="${theUser.roleId.roleId eq 0 or theUser.roleId.roleId eq 3 or theUser.roleId.roleId eq 4  or theUser.roleId.roleId eq 1  or theUser.roleId.roleId eq 2}">
			    		<input title="Save [Alt+Shift+S]" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="this.disabled='disabled';javascript:simpan()" name="Save" value=" Save " type="submit">
			    		<input title="Cancel [Alt+Shift+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="this.disabled='disabled';javascript:cancel()" name="Cancel" value=" Cancel " type="button">         

		    		</c:if>
		    	</c:if>
	        	         
	      	</td>
	      	<td align="right"></td>
    	</tr>
  	</tbody>
</table>
</form>

<script language="javascript">
	
	 var benefitLimit = $("#benefitLimit").val();
	
	$(document).ready(function(){

    $("#member_number").autocomplete("member?navigation=lookupjson", {
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
            return  "<font color='#000' style='align: left;' >"+ row.number + " - " + row.name +"</font>" ;
        },
        extraParams: {
       		memberGroupId: function() { 
       		return $("#memberGroupId").val(); 
       		}, 
       		clientId: function() { 
       		return $("#clientId").val(); 
       		},
       		policyId: function() { 
       		return $("#policyId").val(); 
       		},
       		claimNumber: function() { 
       		return $("#claimNumber").val(); 
       		}
   		}        
	    }).bind("result", function(data, value){
	        $(this).parents("dd").find(".error_message").hide();
	        $("#memberId").val (value.id);
	        $("#name").val (value.name);
	        $("#member_number").val (value.number);
	        
	        
	        $.get("memberbenefit?navigation=jsonbenefitroomrate&memberId="+ value.id+"&caseCategoryId="+$("#caseCategoryId").val(), 
			function(data) {
				$("#benefitRoomRate").val(data == 0.0 ? "": data);		 
			});	
        	
	    });
	    
	    $("#providerName").autocomplete("provider?navigation=lookupjson", {
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
	            return  "<font color='#000' style='align: left;' >"+ row.name +"</font>" ;
	        },
	        extraParams: {
	       		memberId: function() { return $("#memberId").val(); }
	   		}
	    }).bind("result", function(data, value){
	        $(this).parents("dd").find(".error_message").hide();
	        $("#providerId").val (value.id);
	    });
	    
	    $("#diagnosis1Text").autocomplete("diagnosis?navigation=lookupjson", {
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
	            return "<font color='#000'  >"+ row.number +" - </font>" + "<font color='#000' >"+ row.name +"</font>" ;
	        }
	    }).bind("result", function(data, value){
	        $(this).parents("dd").find(".error_message").hide();
	        $("#diagnosis1Id").val(value.id);
	    });
	    
	    $("#diagnosis2Text").autocomplete("diagnosis?navigation=lookupjson", {
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
	            return "<font color='#000'  >"+ row.number +" - </font>" + "<font color='#000' >"+ row.name +"</font>" ;
	        }
	    }).bind("result", function(data, value){
	        $(this).parents("dd").find(".error_message").hide();
	        $("#diagnosis2Id").val(value.id);
	    });
	    
	    $("#diagnosis3Text").autocomplete("diagnosis?navigation=lookupjson", {
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
	            return "<font color='#000'  >"+ row.number +" - </font>" + "<font color='#000' >"+ row.name +"</font>" ;
	        }
	    }).bind("result", function(data, value){
	        $(this).parents("dd").find(".error_message").hide();
	        $("#diagnosis3Id").val(value.id);
	    });	    

		$("#numberCase").autocomplete("case?navigation=lookupclaimjson", {
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
	            return "<font color='#000'  >"+ row.number +" </font>"  ;
	        },
	        extraParams: {
	       		memberId: function() { return $("#memberId").val(); },
	       		providerId: function() { return $("#providerId").val(); }
	   		}
	    }).bind("result", function(data, value){
	        $(this).parents("dd").find(".error_message").hide();
	        $("#idCase").val(value.id);
	        $("#admissionDate").val(value.admissionDate);
	        $("#dischargeDate").val(value.dischargeDate);
	    });	  
	    
	    
	     if($('input[name=navigation]').val() != 'update') {
     	 	$("#doctorName").attr('disabled','disabled');
    	 }
    	 
    	 $("#providerName").keyup(function() {
        	if($(this).val() != '') {
          		 $("#doctorName").removeAttr('disabled');
        	}else{
        	 	 $("#doctorName").attr('disabled','disabled');
        	}
     	});
     	
	    
	    $("#doctorName").autocomplete("providerdoctor?navigation=lookupjson", {
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
	            return "<font color='#000'  >"+ row.name +" </font>"  ;
	        },
	        extraParams: {
	       		providerId: function() { return $("#providerId").val(); }
	   		}
	    }).bind("result", function(data, value){
	        $(this).parents("dd").find(".error_message").hide();
	        $("#doctorId").val(value.id);
	    });	  
	    
	    
	    
			     
	});


	function pendingClaim(){
		document.form1.method = "POST";
		document.form1.navigation.value = "pending";
		document.form1.submit();
	}
	function register (){
		document.form1.method = "POST";
		document.form1.navigation.value = "register";
		document.form1.action = "claim-form";
		document.form1.submit();
	}
	function changeService (){
        var ccId = $("#caseCategoryId").val();
        var admDate = $("#admissionDate").val();
        var memberId = $("#memberId").val();
        var patientId = $("#patientId").val();
        
        


        AJAXClaimService.getRoomRate (ccId,admDate,memberId,patientId, {
                callback:function (res){
                    $("#benefitRoomRate").val(res);
                }
        });

	}
	function simpan (){
		document.form1.method = "POST";
		document.form1.action = "claim-form";
		<c:if test="${param.navigation eq 'approvecase'}">
			document.form1.caseNavigation.value = "approvecase";
		</c:if>
		<c:if test="${param.navigation eq 'calculate'}">
			document.form1.caseNavigation.value = "calculate";
		</c:if>
		document.form1.submit();
	}
	function kembali (){
		document.form1.navigation.value = "back";
		document.form1.action = "claim";
		document.form1.submit();
	}
	function pending (){
		document.form1.navigation.value = "pending";
		document.form1.action = "claim-form";
		document.form1.submit();
	}
// 	function benefitRoom(){
// 		var memberId = document.getElementById("memberId").value;
// 		if(memberId != ""){
		
// 			$.get("memberbenefit?navigation=jsonbenefitroomrate&memberId="+memberId, 
// 				function(data) {
// 					$("#benefitRoomRate").val(data == 0.0 ? "": data);		 
// 				}
// 			);	
// 	        	}else{
// 	        		document.getElementById("benefitRoomRate").value = "";
// 	        	}
// 	}
	function cancel(){

		document.form1.method = "GET";
		//Add 20150413 by FVO, change the logic by using CHOOSE TAG
		<c:choose>
			<c:when test="${navigation eq 'calculate'}">
				document.form1.action = "case";
				document.form1.navigation.value = "detail";
			</c:when>
			<c:when test="${navigation eq 'approvecase' }">
				document.form1.action = "case";
				document.form1.navigation.value = "detail";
			</c:when>
			<c:otherwise>
				<c:if test="${empty claimForm.batchClaimId}">
					document.form1.action = "claim";
					document.form1.navigation.value = "detail";
					document.form1.claimId.value = <c:out value="${claimId}" />
				</c:if>
				<c:if test="${not empty claimForm.batchClaimId}">
					document.form1.action = "batchclaim";
					document.form1.navigation.value = "detail";
					document.form1.batchClaimId.value = <c:out value="${batchClaimId}" />
				</c:if>
			</c:otherwise>
		</c:choose>
		//Edit 20150413 by FVO, coba pakai choose agar hanya pilih 1
		/*<c:if test="${navigation ne 'calculate'}">
			//alert(document.form1.batchClaimId.value);
			<c:if test="${empty claimForm.batchClaimId}">
			document.form1.action = "claim";
			document.form1.navigation.value = "detail";
			document.form1.claimId.value = <c:out value="${claimId}" />
			//alert('Batch Claim Kosong');
			</c:if>
			<c:if test="${not empty claimForm.batchClaimId}">
			document.form1.action = "batchclaim";
			document.form1.navigation.value = "detail";
			document.form1.batchClaimId.value = <c:out value="${batchClaimId}" />
			//alert('Batch Claim Ada');
			</c:if>
		</c:if>
		<c:if test="${navigation eq 'calculate'}">
		document.form1.action = "case";
		document.form1.navigation.value = "detail";
		</c:if>*/
		document.form1.submit();
	}	
</script>