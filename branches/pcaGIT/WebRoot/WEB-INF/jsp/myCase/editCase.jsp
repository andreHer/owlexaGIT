<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>

<link rel="stylesheet" type="text/css" href="css/autocomplete.css">
<link rel="stylesheet" type="text/css" href="css/button.css">

<link rel="stylesheet" type="text/css" href="css/jquery/autocomplete/jquery.autocomplete.css"/>

<script type="text/javascript" src="scripts/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="scripts/jquery/plugin/jquery.autocomplete.pack.js"></script>

<%String alert = (String) request.getAttribute("alert");
			if (alert != null && !alert.trim().equals("")) {%>
							<div id="warning">
								<c:out value="${alert}"></c:out>
							</div>
							<%}%>

<form action="case-form" method="POST" name="form1" id="form_layout">

	<input type="hidden" name="navigation" value="">
	<table class="moduleTitle" border="0" cellpadding="0" cellspacing="0" width="100%">
		<tbody>
			<tr>
				<td valign="top">
				
				</td>
				<td width="100%">
					<h2 style="font-size: large; color: #000;">
						Guarantee Letter / Guarantee Number :
					</h2>
					<br />
				</td>
				<td style="padding-top: 3px; padding-left: 5px;" align="right" nowrap="nowrap" valign="top">
					
				</td>
		</tbody>
	</table>

	<br /> 	

	<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
		<tbody>
			<tr>
				<td>
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tbody>

							

							<spring:bind path="myCaseForm.caseId">
								<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />">
								<div id="fredcaption">
									<c:out value="${status.errorMessage}" />
								</div>
							</spring:bind>




						<tbody>
							<tr>
								<td class="dataLabel" width="19%"></td>
								<td class="dataField" width="31%"></td>
								<td class="dataLabel" width="15%"></td>
								<td class="dataField" width="35%"></td>
							</tr>
							
							<tr>
								<td class="dataLabel" width="19%">									
									Policy Number : <span class="required">*</span>									
								</td>
								<td class="dataField" width="31%">
									
									<spring:bind path="myCaseForm.memberId.customerPolicyNumber">
										<input type="text" size=35 tabindex="" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="50" readonly="readonly">
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>									
								</td>								
								<td class="dataLabel" width="15%">
									
									Priority: 
									
								</td>
								<td class="dataField" width="35%">
									
									<spring:bind path="myCaseForm.priorityId">
										<select name="<c:out value="${status.expression}"/>" id="priorityId">
											<c:forEach items="${priorities}" var="prio">
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
									
										<input type="text" size=35 tabindex="" name="<c:out value="${status.expression}"/>" id="memberName" value="<c:out value="${myCaseForm.memberId.firstName}" /> <c:out value="${myCaseForm.memberId.lastName}" />" maxlength="50" readonly="readonly">
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									
								</td>
								
								
								
								<td class="dataLabel" width="19%">
									Client :
								</td>
								<td class="dataField" width="33%">
									
									<spring:bind path="myCaseForm.clientName">
										<input size="35" type="text" tabindex="" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="50" readonly="readonly">
									</spring:bind>							

								</td>
							</tr>
							<tr>
<!--							MEMBERSHIP STATUS	-->
								<td class="dataLabel">
									Relationship :
								</td>
								<td class="dataField" nowrap="nowrap" width="33%">
									
									<spring:bind path="myCaseForm.relationshipName">
										<input type="text" tabindex="" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="50" readonly="readonly">										
									</spring:bind>
									
								</td>
								
								
								
								<!--							SERVICE TYPE	-->
								<td class="dataLabel">
									Member Group :
								</td>
								<td class="dataField">									
									<spring:bind path="myCaseForm.groupName">
										<input type="text" size=35 tabindex="" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="50" readonly="readonly">
									</spring:bind>
								</td>
							</tr>
							<tr>
<!--							MEMBERSHIP STATUS	-->
								<td class="dataLabel">
									Member Expire Date :
								</td>
								<td class="dataField" nowrap="nowrap" width="33%">									
									<input type="text" tabindex="" name="expDate" id="expDate" value="<c:out value="${myCaseForm.memberId.expireDate}" />" maxlength="50" readonly="readonly">									
								</td>
								
								
								
								<!--							SERVICE TYPE	-->
								<td class="dataLabel">
									Group Expire Date :
								</td>
								<td class="dataField">									
									
										<input type="text" size=35 tabindex="" name="groupExpireDate" id="groupExpireDate" value="<c:out value="${myCaseForm.groupExpireDate}" />" maxlength="50" readonly="readonly">									
									
								</td>
							</tr>
							<tr>
<!--							MEMBERSHIP STATUS	-->
								<td class="dataLabel">
									Membership Status :
								</td>
								<td class="dataField" nowrap="nowrap" width="33%">
									
									<spring:bind path="myCaseForm.memberStatus">
										<input type="text" tabindex="" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="50" readonly="readonly">
									</spring:bind>
									
								</td>
								
								
								
								<!--							SERVICE TYPE	-->
								<td class="dataLabel">
									Service Type :
								</td>
								<td class="dataField">									
									<spring:bind path="myCaseForm.caseCategoryId">
										<select name="<c:out value="${status.expression}"/>" id="caseCategoryId" onchange="javascript:changeCaseCategory()">
											<c:forEach items="${caseCategoryId}" var="ccat">
												<option value="<c:out value="${ccat.caseCategoryId}" />" <c:if test="${status.value eq ccat.caseCategoryId}">selected</c:if>>
													<c:out value="${ccat.caseCategoryName}" />
												</option>
											</c:forEach>
										</select>
										<input type="text" size=15 id="productClass" readonly="readonly">
									</spring:bind>
								</td>
							</tr>
							<tr>
								<td class="dataLabel" width="19%"></td>							
								<td class="dataField" width="31%"></td>
								<td class="dataLabel" width="19%"></td>
								<td class="dataField" width="33%"></td>
							</tr>
							<tr>
								<td class="dataLabel">
									
									Admission Date :
									
								</td>
								<td class="dataField" nowrap="nowrap">
									<table border="0" cellpadding="0" cellspacing="0">
										<tbody>
											<tr>
												<td nowrap="nowrap">
													<spring:bind path="myCaseForm.caseStartTime">
														<INPUT type="text" size="10" readonly="readonly" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" id="date" maxlength="19">
													</spring:bind>&nbsp;
													<img src="images/jscalendar.gif" alt="Enter Date" id="date_trigger" align="absmiddle" height="20" width="20">
													<script type="text/javascript">
											    					Calendar.setup({
											        					inputField     :    "date",     // id of the input field
											        					ifFormat       :    "%Y-%m-%d",      // format of the input field
											        					button         :    "date_trigger",  // trigger for the calendar (button ID)
											        					align          :    "Bl",           // alignment (defaults to "Bl")
											        					singleClick    :    true
											    					});
													 	</script>
													<div id="fredcaption">
														<c:out value="${status.errorMessage}" />
													</div>



													
												</td>
											</tr>
											
											
																					</tbody>
									</table>
								</td>





								<td class="dataLabel">
									
									Diagnosis 1: *
									
								</td>
								<td class="dataField" width="31%">
									<spring:bind path="myCaseForm.diagnosis1Code">
										<input type="hidden" id="diagnosis1Code" size="7"  onchange="javascript:changeDiagnosis1()" onkeydown="javascript:searchDiagnosisByCode(event);" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />">
									</spring:bind>
						
									<spring:bind path="myCaseForm.diagnosis1Text">
										<input type="text"  size="35" id="diagnosis1Text" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />">
									</spring:bind>	
									<spring:bind path="myCaseForm.diagnosis1Id">
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
										<input type="hidden" id="diagnosis1Id" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />">
									</spring:bind>										
								</td>
							</tr>
							
							
							
							
							
							
							
							<tr>
<!--							END DATE	-->
								<td class="dataLabel">
									
									<c:if test="${caseType eq \"2\"}" >
									End Date :
									</c:if>
									 
								</td>
								<td class="dataField" nowrap="nowrap">
										
									<c:if test="${caseType eq \"2\"}" >								
									<table border="0" cellpadding="0" cellspacing="0">
										<tbody>
											<tr>
													<spring:bind path="myCaseForm.caseEndTime">
														<INPUT type="text" size="10" readonly="readonly" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" id="endDate" maxlength="19">
													</spring:bind> &nbsp;	
													<img src="images/jscalendar.gif" alt="Enter Date" id="date_trigger2" align="absmiddle" height="20" width="20">
													<script type="text/javascript">
											    					Calendar.setup({
											        					inputField     :    "endDate",     // id of the input field
											        					ifFormat       :    "%Y-%m-%d",      // format of the input field
											        					button         :    "date_trigger2",  // trigger for the calendar (button ID)
											        					align          :    "Bl",           // alignment (defaults to "Bl")
											        					singleClick    :    true
											    					});
													 	</script>
													<div id="fredcaption">
														<c:out value="${status.errorMessage}" />
													</div>
												
												
											</tr>
											
										</tbody>
									</table>
									</c:if>
									 
								</td>


								<td class="dataLabel">
									
									Diagnosis 2:
									
								</td>
								<td class="dataField" width="31%">
									<spring:bind path="myCaseForm.diagnosis2Id">
			<input type="hidden" id="diagnosis2Id" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />">
	    		<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>	
			<spring:bind path="myCaseForm.diagnosis2Code">
				<input type="hidden" id="diagnosis2Code" size="7"  onchange="javascript:changeDiagnosis1()" onkeydown="javascript:searchDiagnosisByCode(event);" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />">
			</spring:bind>

			<spring:bind path="myCaseForm.diagnosis2Text">
				<input type="text"  size="35" id="diagnosis2Text" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />">
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
									<spring:bind path="myCaseForm.diagnosis3Id">
													<input type="hidden" id="diagnosis3Id" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />">
	    		<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>	
			<spring:bind path="myCaseForm.diagnosis3Code">
				<input type="hidden" id="diagnosis3Code" size="7"  onchange="javascript:changeDiagnosis1()" onkeydown="javascript:searchDiagnosisByCode(event);" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />">
			</spring:bind>

			<spring:bind path="myCaseForm.diagnosis3Text">
				<input type="text"  size="35" id="diagnosis3Text" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />">
			</spring:bind>
			
								</td>
													
							</tr>
										<tr>
								<td class="dataLabel" width="19%">
									
								</td>							
								<td class="dataField" width="31%">
									
					
								</td>
								
								
								
								<td class="dataLabel" width="19%">
								
								</td>
								<td class="dataField" width="33%">
									&nbsp;

								</td>
							</tr>				

			<tr>
				<td class="dataLabel">	
					Provider : *
				</td>
				<td class="dataField">
					<spring:bind path="myCaseForm.providerName">
						<input type="text" size=35 name="<c:out value="${status.expression}"/>" id="providerName" value="<c:out value="${status.value}" />" > &nbsp;
					</spring:bind>		
					<spring:bind path="myCaseForm.providerId">
						<input type="hidden" id="providerId" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />">
						<div id="fredcaption">
							<c:out value="${status.errorMessage}" />
						</div>
					</spring:bind>							
				</td>
				<td class="dataLabel"> Has Complication : </td>
				<td class="dataField">
					<spring:bind path="myCaseForm.hasComplication">
						<select name="<c:out value="${status.expression}"/>">
							<option value="1" <c:if test="${status.value eq 1}">selected</c:if>>Tanpa Komplikasi</option>				
							<option value="2" <c:if test="${status.value eq 2}">selected</c:if>>Komplikasi Ringan</option>
							<option value="3" <c:if test="${status.value eq 3}">selected</c:if>>Komplikasi Berat</option>
						</select>
					</spring:bind>
				</td>
			</tr>

							<tr>
<!--								PROVIDER		-->
<!-- 								<td class="dataLabel"> -->
									
<!-- 									Provider : -->
									
<!-- 								</td> -->
<!-- 								<td class="dataField"> -->
									
<!-- 									<spring:bind path="myCaseForm.providerId"> -->
<!-- 										<input type="hidden" id="providerId" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />"> -->
<!-- 									</spring:bind> -->
									
<!-- 									<spring:bind path="myCaseForm.providerName"> -->
<!-- 										<input type="text" size=35 name="<c:out value="${status.expression}"/>" id="providerName" value="<c:out value="${status.value}" />" > &nbsp; -->
<!-- 										<div id="fredcaption"> -->
<!-- 											<c:out value="${status.errorMessage}" /> -->
<!-- 										</div> -->
<!-- 									</spring:bind> -->
																		
<!-- 								</td> -->
							
							
							
							
<!--								ROOM			-->
								<td class="dataLabel">
									
									Room & Board :
									
								</td>
								<td class="dataField">
									
									
									 <spring:bind path="myCaseForm.roomAndBoard">
									 	<select name="<c:out value="${status.expression}" />">
									 		<option value="">-- SELECT ONE --</option>
									 		<option value="SVIP" <c:if test="${status.value eq 'SVIP' }">selected</c:if>>SUPER VIP</option>
									 		<option value="VIP" <c:if test="${status.value eq 'VIP' }">selected</c:if>>VIP</option>
									 		<option value="KELAS 1" <c:if test="${status.value eq 'KELAS 1' }">selected</c:if>>KELAS 1</option>
									 		<option value="KELAS 2" <c:if test="${status.value eq 'KELAS 2' }">selected</c:if>>KELAS 2</option>
									 		<option value="KELAS 3" <c:if test="${status.value eq 'KELAS 3' }">selected</c:if>>KELAS 3</option>
									 		
									 	</select>									 	
									 </spring:bind>
																		
								</td>
								<td class="dataLabel">									
									Room Status :									
								</td>
								<td class="dataField">
									 <spring:bind path="myCaseForm.roomAndBoardStatus">
									 	<select name="<c:out value="${status.expression}" />">
									 		<c:forEach items="${treatmentUpgradeList}" var="treatment">
									 			<option value="<c:out value="${treatment.treatmentUpgradeTypeId}" />" <c:if test="${treatment.treatmentUpgradeTypeId eq status.value}">selected</c:if>><c:out value="${treatment.treatmentUpgradeTypeName}" /></option>
									 		</c:forEach>
									 	</select>
									 	
									 </spring:bind>																		
								</td>
							</tr>
							<tr>
								

<!--									HANDLER				-->
								<td class="dataLabel">
									Case Handler :
								</td>
								<td class="dataField">
									
									<spring:bind path="myCaseForm.caseHandler">
										<input type="text" size=35 tabindex="" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="50">
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
															
								</td>
								
								
								<!--	PHYSICIAN							-->
								<td class="dataLabel">
									Physician:
								</td>
								<td class="dataField">
									
									<spring:bind path="myCaseForm.physician">
										<input type="text" size=35 tabindex="" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="50">
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
									
								</td>
							</tr>


		<c:if test="${navigation ne 'refercase'}">

							<tr style="padding-top: 2px;">
								<td class="dataLabel" >
									
									Anamnesa :
									
								</td>
								<td >
									<spring:bind path="myCaseForm.anamnesa">
										<textarea 
											name="<c:out value="${status.expression}"/>" 
											id="<c:out value="${status.expression}"/>" 
											tabindex="" 
											cols="35" 
											rows="8"><c:out escapeXml="true" value="${status.value}"  /></textarea>

										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
								</td>
								
								
								
											
								<td class="dataLabel" style="padding-top: 2px;">
									
									Description :
									
								</td>
								<td >
									<spring:bind path="myCaseForm.description">
										<textarea 
											name="<c:out value="${status.expression}"/>" 
											id="<c:out value="${status.expression}"/>" 
											tabindex="" 
											cols="35" 
											rows="8"><c:out value="${status.value}" /></textarea>
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>

								</td>
								
					
							</tr>
			</c:if>
																		<tr>
								<td class="dataLabel" width="19%">
									
									&nbsp;
									
								</td>
								<td class="dataField" width="31%">
									&nbsp;									
								</td>
														
								
								<td class="dataLabel" width="15%">
									&nbsp;							
									
								</td>
								<td class="dataField" width="35%">
									&nbsp;					
								</td>
							</tr>							
						</tbody>

					</table>
		</tbody>
	</table>

		<br /> 	
	<c:if test="${navigation eq 'refercase'}">

		<h2 style="font-size: large; color: #000;">
						Refer Case :
					</h2>
	<br />
	<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
		<tbody>
			<tr>
				<td class="dataLabel" width="19%"></td>
				<td class="dataField" width="31%"></td>
				<td class="dataLabel" width="15%"></td>
				<td class="dataField" width="35%"></td>
			</tr>
			
			<tr>
				<td class="dataLabel">Provider :</td>
				<td class="dataField">
					<spring:bind path="myCaseForm.referedProviderId">
						<input type="hidden" id="referedProviderId" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />">
					</spring:bind>						
					<spring:bind path="myCaseForm.referedProviderName">
						<input type="text" size=35 name="<c:out value="${status.expression}"/>" id="referedProviderName" value="<c:out value="${status.value}" />" > &nbsp;
					</spring:bind>															
				</td>
				<td class="dataLabel"></td>
				<td class="dataField"></td>
			</tr>
			<tr>
				<td class="dataLabel" width="19%">
					Poliklinik :
				</td>							
				<td class="dataField" width="31%">					
					<spring:bind path="myCaseForm.providerItemId">
						<select name="<c:out value="${status.expression}"/>" id="poliId" onchange="javascript:changePoliklinik();">
							<option value="">--- PILIH SALAH SATU ---</option>
							<c:forEach items="${poliklinikList}" var="poli">
								<option value="<c:out value="${poli.providerItemId}" />" <c:if test="${status.value eq poli.providerItemId}">selected</c:if>>
									<c:out value="${poli.itemId.itemName}" />
								</option>
							</c:forEach>
						</select>
					</spring:bind>
			
				</td>
				<td class="dataLabel" width="19%"></td>
				<td class="dataField" width="33%"></td>
			</tr>
			<tr>
				<td class="dataLabel">
					Dokter :
				</td>
				<td class="dataField" nowrap="nowrap" width="33%">
					<spring:bind path="myCaseForm.doctorId">
						<select name="<c:out value="${status.expression}"/>" id="doctorId">
							<option value="">--- PILIH SALAH SATU ---</option>
							<c:forEach items="${doctorList}" var="doctor">
								<option value="<c:out value="${doctor.providerDoctorId}" />" <c:if test="${status.value eq doctor.providerDoctorId}">selected</c:if>>
									<c:out value="${doctor.doctorName}" />
								</option>
							</c:forEach>
						</select>
					</spring:bind>														
				</td>
				<td class="dataLabel"></td>
				<td class="dataField"></td>
			</tr>			
			<tr>
				<td class="dataLabel" width="19%">&nbsp;</td>
				<td class="dataLabel" width="19%">&nbsp;</td>
				<td class="dataLabel" width="19%">&nbsp;</td>
				<td class="dataLabel" width="19%">&nbsp;</td>
			</tr>							
		</table>
		
	</c:if>
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tbody>
			<tr>
				<td style="padding-top: 2px; padding-left: 1px;">
					<input type="hidden" name="notyet" value="">
					<input title="Save [Alt+Shift+S]" accesskey="S" tabindex="" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:simpan()" name="Save" value=" Save " type="submit">
					<input title="Cancel [Alt+Shift+C]" accesskey="C" tabindex="" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:kembali()" name="Cancel" value=" Cancel " type="button">
					<c:if test="${caseType eq \"5\"}" >					
					<input title="Save & Approve [Alt+Shift+A]" accesskey="A" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:saveAndApprove()" name="SaveAndApprove" value=" Save & Approve " type="button">
					</c:if>
				</td>
				<td align="right"></td>
			</tr>
		</tbody>
	</table>
</form>


<script language="javascript">
changeCaseCategory();

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
       		memberGroupId: function() { return $("#memberGroupId").val(); }
   		}        
    }).bind("result", function(data, value){
        $(this).parents("dd").find(".error_message").hide();
        $("#memberId").val (value.id);
        $("#name").val (value.name);
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
        }
    }).bind("result", function(data, value){
        $(this).parents("dd").find(".error_message").hide();
        $("#providerId").val (value.id);
    });
    
     $("#referedProviderName").autocomplete("provider?navigation=lookupjson", {
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
        }
    }).bind("result", function(data, value){
        $(this).parents("dd").find(".error_message").hide();
        $("#referedProviderId").val (value.id);
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
    
     
});
	function simpan (){
		document.form1.method = "POST";
		document.form1.action = "case-form";
		document.form1.submit();
	}
	function saveAndApprove (){
		document.form1.method = "POST";
		document.form1.action = "case-form";
		document.form1.navigation.value = "saveandapprove";
		document.form1.submit();
	}
	
	function kembali (){
		
		window.history.back();
	}
	function changeCaseCategory (){
				
		var ccId = document.getElementById("caseCategoryId").value;
		
		$.get("memberproduct?navigation=jsonproductinfo&caseCategoryId="+ccId+"&memberId=<c:out value="${myCaseForm.memberId.memberId}"/>", 
			function(data) {
				$("#productClass").val(data);		 
			}
		);		
	}

</script>
