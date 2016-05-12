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
				<%
				}
				%>

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
									Member Number : <span class="required">*</span>									
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
									
									<spring:bind path="myCaseForm.priorityId.priorityId">
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
									
									<spring:bind path="myCaseForm.memberId.clientId.clientName">
										<input size="35" type="text" tabindex="" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="50" readonly="readonly">
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
									

								</td>
							</tr>
							<tr>
<!--							MEMBERSHIP STATUS	-->
								<td class="dataLabel">
									Relationship :
								</td>
								<td class="dataField" nowrap="nowrap" width="33%">
									
									<spring:bind path="myCaseForm.memberId.relationshipId.relationshipName">
										<input type="text" tabindex="" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="50" readonly="readonly">
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
									
								</td>
								
								
								
								<!--							SERVICE TYPE	-->
								<td class="dataLabel">
									Member Group :
								</td>
								<td class="dataField">									
									<spring:bind path="myCaseForm.memberId.memberGroupId.groupName">
										<input type="text" size=35 tabindex="" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="50" readonly="readonly">
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
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
									
										<input type="text" size=35 tabindex="" name="groupExpireDate" id="groupExpireDate" value="<c:out value="${myCaseForm.memberId.memberGroupId.expireDate}" />" maxlength="50" readonly="readonly">									
									
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
											        					align          :    "Tl",           // alignment (defaults to "Bl")
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
									
									Initial Diagnosis:
									
								</td>
								<td class="dataField" width="31%">
									<spring:bind path="myCaseForm.initialDiagnosis">										
										<input type="text"  size="35"  name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />">
									</spring:bind>					
								</td>
							</tr>
							
							
							
							
							
							
							
							<tr>
								<td class="dataLabel">									
									<c:if test="${caseType eq \"2\"}" >
									Length Of Stay :
									</c:if>									 
								</td>
								<td class="dataField" nowrap="nowrap">										
									<c:if test="${caseType eq \"2\"}" >								
										<table border="0" cellpadding="0" cellspacing="0">
											<tbody>
												<tr>
													<spring:bind path="myCaseForm.longOfStay">
														<INPUT type="text" size="10" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" id="endDate" maxlength="19"> day(s)
													</spring:bind>
												</tr>											
											</tbody>
										</table>
									</c:if>									 
								</td>
								<td class="dataLabel"></td>
								<td class="dataField" width="31%"></td>
							</tr>
							<tr>
								<td class="dataLabel"></td>
								<td class="dataField" nowrap="nowrap"></td>
								<td class="dataLabel"></td>
								<td class="dataField" width="31%"></td>													
							</tr>
							<tr>
								<td class="dataLabel" width="19%"></td>							
								<td class="dataField" width="31%"></td>
								<td class="dataLabel" width="19%"></td>
								<td class="dataField" width="33%"></td>
							</tr>				

							<c:if test="${caseType ne \"2\"}">
								<tr>
									<td class="dataLabel">									
										Provider :									
									</td>
									<td class="dataField">									
										<spring:bind path="myCaseForm.providerId.providerId">
											<input type="hidden" id="providerId" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />">
										</spring:bind>									
										<spring:bind path="myCaseForm.providerId.providerName">
											<input type="text" size=35 name="<c:out value="${status.expression}"/>" id="providerName" value="<c:out value="${status.value}" />" > &nbsp;
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
									<td class="dataLabel">									
									Provider :									
									</td>
									<td class="dataField">									
										<spring:bind path="myCaseForm.providerId.providerId">
											<input type="hidden" id="providerId" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />">
										</spring:bind>
										
										<spring:bind path="myCaseForm.providerId.providerName">
											<input type="text" size=35 name="<c:out value="${status.expression}"/>" id="providerName" value="<c:out value="${status.value}" />" > &nbsp;
											<div id="fredcaption">
												<c:out value="${status.errorMessage}" />
											</div>
										</spring:bind>																		
									</td>				
							
							
							
<!--								ROOM			-->
								<td class="dataLabel">									
									Room & Board :									
								</td>
								<td class="dataField">
									 <spring:bind path="myCaseForm.roomAndBoard">
									 	<input type="text" size=35 name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />">
									 </spring:bind>																		
								</td>
							</tr>


							<tr>
								<td class="dataLabel"></td>
								<td class="dataField"></td>				
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
							<tr style="padding-top: 2px;">
								<td class="dataLabel" >Initial Symptoms :</td>
								<td class="dataField">
									<spring:bind path="myCaseForm.initialSymptom">
										<textarea 
											name="<c:out value="${status.expression}"/>" 
											id="<c:out value="${status.expression}"/>" 
											tabindex="" 
											cols="35" 
											rows="8"><c:out value="${status.value}" /></textarea>

									</spring:bind>
								</td>			
								<td class="dataLabel" style="padding-top: 2px;">Description :</td>
								<td class="dataField">
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
							<tr style="padding-top: 2px;">
								<td class="dataLabel" >Anamnesa :</td>
								<td class="dataField">
									<spring:bind path="myCaseForm.anamnesa">
										<textarea  name="<c:out value="${status.expression}"/>" 
											id="<c:out value="${status.expression}"/>" 
											tabindex="" 
											cols="35" 
											rows="8"><c:out escapeXml="true" value="${status.value}"  /></textarea>

										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
								</td>			
								<td class="dataLabel" style="padding-top: 2px;"></td>
								<td class="dataField">
									
								</td>
							</tr>
							<tr>
								<td class="dataLabel" width="19%"></td>
								<td class="dataField" width="31%"></td>
								<td class="dataLabel" width="15%"></td>
								<td class="dataField" width="35%"></td>
							</tr>							
						</tbody>

					</table>
		</tbody>
	</table>
	
	
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

	
	
	function cari(){

	}	
				// forreign affairs end
</script>
