<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>

<link rel="stylesheet" type="text/css" href="css/autocomplete.css">
<link rel="stylesheet" type="text/css" href="css/button.css">

<link rel="stylesheet" type="text/css" href="css/jquery/autocomplete/jquery.autocomplete.css"/>

<script type="text/javascript" src="scripts/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="scripts/jquery/plugin/jquery.autocomplete.pack.js"></script>


<script type='text/javascript' src='dwr/interface/AJAXCaseService.js'></script>
<script type='text/javascript' src='dwr/engine.js'></script>
<script type='text/javascript' src='dwr/util.js'></script>

<%String alert = (String) request.getAttribute("alert");
			if (alert != null && !alert.trim().equals("")) {%>
							<div id="warning">
								<c:out value="${alert}"></c:out>
							</div>
							<%}%>

<form action="refer-form" method="POST" name="form1" id="form_layout">
	<input type="hidden" name="navigation" value="<c:out value="${navigation}" />">
	<table class="moduleTitle" border="0" cellpadding="0" cellspacing="0" width="100%">
		<tbody>
			<tr>
				<td valign="top">
				
				</td>
				<td width="100%">
					<h2 style="font-size: large; color: #000;">
						Guarantee Letter:
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
								<td class="dataLabel" width="19%">
									Member Name :
								</td>							
								<td class="dataField" width="31%">
									
										<input type="text" size=35 tabindex="" name="<c:out value="${status.expression}"/>" id="memberName" value="<c:out value="${myCaseForm.memberId.firstName}" /> <c:out value="${myCaseForm.memberId.lastName}" />" maxlength="50" readonly="readonly">
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									
								</td>
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
								<td class="dataLabel"></td>
								<td class="dataField" nowrap="nowrap" width="33%"></td>
								<td class="dataLabel"></td>
								<td class="dataField" nowrap="nowrap" width="33%"></td>
								
								
								<!--							SERVICE TYPE	-->
								
							</tr>
							<tr>
							




								<td class="dataLabel">
									
									Diagnosis 1:
									
								</td>
								<td class="dataField" width="31%">
									<spring:bind path="myCaseForm.diagnosis1Id">
										<input type="hidden" id="diagnosis1Id" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />">
	    		<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>	
			<spring:bind path="myCaseForm.diagnosis1Code">
				<input type="hidden" id="diagnosis1Code" size="7"  onchange="javascript:changeDiagnosis1()" onkeydown="javascript:searchDiagnosisByCode(event);" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />">
			</spring:bind>

			<spring:bind path="myCaseForm.diagnosis1Text">
				<input type="text"  size="35" id="diagnosis1Text" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />">
			</spring:bind>
			
																		
								</td>
								<td class="dataLabel"></td>
								<td class="dataField" nowrap="nowrap"></td>
								
							</tr>
							
							
							
							
							
							
							
							<tr>
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
								<td class="dataLabel"></td>
								<td class="dataField" nowrap="nowrap"></td>


							</tr>



							<tr>
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
								<td class="dataLabel"></td>
								<td class="dataField" nowrap="nowrap"></td>
													
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

		
														<tr style="padding-top: 2px;">
								<td class="dataLabel" >Anamnesa :</td>
								<td class="dataField" colspan="3">
									<spring:bind path="myCaseForm.anamnesa">
										<textarea  name="<c:out value="${status.expression}"/>" 
											id="<c:out value="${status.expression}"/>" 
											tabindex="" 
											cols="80" 
											rows="8"><c:out escapeXml="true" value="${status.value}"  /></textarea>

										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
								</td>			
									
							</tr>
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
	<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
		<tbody>
			<tr>
				<td class="dataLabel" width="19%"></td>
				<td class="dataField" width="31%"></td>
				<td class="dataLabel" width="15%"></td>
				<td class="dataField" width="35%"></td>
			</tr>

			<tr>
				<td class="dataLabel">Status Rujukan :</td>
				<td class="dataField">										
					<spring:bind path="myCaseForm.isRefered">
						<select name="<c:out value="${status.expression}" />" onchange="javascript:checkPoliklinik();">
							<option value="" >-- PILIH SALAH SATU --</option>
							<option value="1" <c:if test="${status.value eq \"1\" }">selected</c:if>>YA, PERLU RUJUKAN SPESIALIS</option>
							<c:if test="${provider.providerCategoryId.providerCategoryId eq 1 or provider.providerCategoryId.providerCategoryId eq 11}">							
							<option value="2" <c:if test="${status.value eq \"2\" }">selected</c:if>>YA, PERLU PEMBEDAHAN / RAWAT INAP</option>
							</c:if>
							<option value="0" <c:if test="${status.value eq \"0\" }">selected</c:if>>TIDAK</option>
						</select>
						<div id="fredcaption">
							<c:out value="${status.errorMessage}" />
						</div>
					</spring:bind>															
				</td>
				<td class="dataLabel"></td>
				<td class="dataField"></td>
			</tr>
			
			<tr>
				<td class="dataLabel">Provider :</td>
				<td class="dataField">
					<spring:bind path="myCaseForm.referedProviderId">
						<input type="hidden" id="referedProviderId" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" >
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
					<spring:bind path="myCaseForm.referedPoliklinikId">
						<select name="<c:out value="${status.expression}"/>" id="poliId" onchange="javascript:changePoliklinik();">
							<option value="">--- PILIH SALAH SATU ---</option>
							<c:forEach items="${referedPoliklinikList}" var="poli">
								<option value="<c:out value="${poli.poliklinikId}" />" <c:if test="${status.value eq poli.poliklinikId}">selected</c:if>>
									<c:out value="${poli.poliklinikName}" />
								</option>
							</c:forEach>
						</select>
						<div id="fredcaption">
							<c:out value="${status.errorMessage}" />
						</div>
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


$(document).ready(function(){
	
    
    
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
        changeReferedProvider(value.id);
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
		document.form1.action = "refer-form";
		document.form1.submit();
	}
	
	function changePoliklinik(){

		var providerId = document.getElementById("referedProviderId").value;
		var poliId = document.getElementById("poliId").value;
        var doctorList = document.getElementById("doctorId");
        		
        AJAXCaseService.getDoctorListByProvider (poliId, providerId, {
                        callback:function (doctorListRes){
                                doctorList.innerHTML = doctorListRes;
                        }
                  });	
	}
	function changeReferedProvider(providerId){

		var poliId = document.getElementById("poliId").value;		
        var doctorList = document.getElementById("doctorId");

        AJAXCaseService.getDoctorListByProvider (poliId,providerId, {
                        callback:function (doctorListRes){
                                doctorList.innerHTML = doctorListRes;
                        }
                });	
	}	
	 function checkPoliklinik(){
                //window.alert("CHANGE CLAIM TYPE");
                var diagnosisId = document.getElementById("diagnosis1Id").value;
                var providerId = document.getElementById("referedProviderId").value;
                var poliList = document.getElementById("poliId");

                AJAXCaseService.getPoliklinikByDiagnosis (diagnosisId,providerId, {
                        callback:function (poliListRes){
                                poliList.innerHTML = poliListRes;
                        }
                });
        }
	function kembali (){		
		window.history.back();
	}		
	
</script>
