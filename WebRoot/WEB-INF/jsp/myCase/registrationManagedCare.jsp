<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

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

<form action="register-form" method="POST" name="form1" id="form_layout">

	<spring:bind path="myCaseForm.caseId">
		<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />">
	</spring:bind>

	<input type="hidden" name="navigation" value="">
	<table class="moduleTitle" border="0" cellpadding="0" cellspacing="0" width="100%">
		<tbody>
			<tr>
				<td valign="top"></td>
				<td width="100%">
					<h2 style="font-size: large; color: #000;">
						Patient Admission :
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
				<td class="dataLabel" width="19%"></td>
				<td class="dataField" width="31%"></td>
				<td class="dataLabel" width="15%"></td>
				<td class="dataField" width="35%"></td>
			</tr>
			
			<tr>
				<td class="dataLabel" width="19%">									
					Nomor Peserta : <span class="required">*</span>									
				</td>
				<td class="dataField" width="31%">					
					<spring:bind path="myCaseForm.memberId.customerPolicyNumber">
						<input type="text" size=35 tabindex="" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="50" readonly="readonly">
					</spring:bind>									
				</td>								
				<td class="dataLabel">Membership Status :</td>
				<td class="dataField" nowrap="nowrap" width="33%">
					<spring:bind path="myCaseForm.memberStatus">
					<input type="text" tabindex="" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="50" readonly="readonly">
					</spring:bind>								
				</td>
			</tr>
			<tr>
				<td class="dataLabel" width="19%">Member Name :</td>							
				<td class="dataField" width="31%">					
					<input type="text" size=35 tabindex="" name="<c:out value="${status.expression}"/>" id="memberName" value="<c:out value="${myCaseForm.memberId.firstName}" /> <c:out value="${myCaseForm.memberId.lastName}" />" maxlength="50" readonly="readonly">				
				</td>
				<td class="dataLabel" width="19%">Client :</td>
				<td class="dataField" width="33%">				
						
					<spring:bind path="myCaseForm.clientName">
						<input size="35" type="text" tabindex="" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="50" readonly="readonly">
					</spring:bind>
				</td>
			</tr>
			<tr>
				<td class="dataLabel">Relationship :</td>
				<td class="dataField" nowrap="nowrap" width="33%">
					<spring:bind path="myCaseForm.relationshipName">
					<input type="text" tabindex="" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="50" readonly="readonly">
					</spring:bind>									
				</td>
				<td class="dataLabel">Member Group :</td>
				<td class="dataField">									
					<spring:bind path="myCaseForm.groupName">
						<input type="text" size=35 tabindex="" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="50" readonly="readonly">
					</spring:bind>
				</td>
			</tr>
			<tr>
				<td class="dataLabel">Member Expire Date :</td>
				<td class="dataField" nowrap="nowrap" width="33%">									
					<input type="text" tabindex="" name="expDate" id="expDate" value="<c:out value="${myCaseForm.memberId.expireDate}" />" maxlength="50" readonly="readonly">									
				</td>				
				<td class="dataLabel">Group Expire Date :</td>
				<td class="dataField">					
					<input type="text" size=35 tabindex="" name="groupExpireDate" id="groupExpireDate" value="<c:out value="${myCaseForm.groupExpireDate}" />" maxlength="50" readonly="readonly">							
				</td>
			</tr>
			<tr>
				<td class="dataLabel"></td>
				<td class="dataField" nowrap="nowrap" width="33%">									
					&nbsp;									
				</td>				
				<td class="dataLabel"></td>
				<td class="dataField">					
												
				</td>
			</tr>	
			<tr>
				<td class="dataLabel">Initial Diagnosis :</td>
				<td class="dataField" nowrap="nowrap" width="33%">	
					<spring:bind path="myCaseForm.diagnosis1Text">								
						<input type="text" tabindex="" name="<c:out value="${status.expression}" />" id="diagnosis1Text" value="<c:out value="${status.value}" />" maxlength="50" size="50">
						
					</spring:bind>
					<spring:bind path="myCaseForm.diagnosis1Id">								
						<input type="hidden" tabindex="" name="<c:out value="${status.expression}" />" id="diagnosis1Id" value="<c:out value="${status.value}" />" maxlength="50" size="50">
					</spring:bind>									
				</td>				
				<td class="dataLabel"></td>
				<td class="dataField">					
												
				</td>
			</tr>	
			<tr>
				<td class="dataLabel">&nbsp;</td>
				<td class="dataField" nowrap="nowrap" width="33%">									
					&nbsp;									
				</td>				
				<td class="dataLabel"></td>
				<td class="dataField">					
												
				</td>
			</tr>	
				
		</table>
	
	<br /> 	

<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	<tr height="20">
		<td scope="col" align="center" class="listViewThS1" style="text-align: center; font-size: 15;" nowrap="nowrap" width="100%" colspan=4>
				&nbsp;BENEFIT</td>
	</tr>
	<tr height="20">
		<td width="2%" nowrap="nowrap" class="listViewThS1" scope="col">No.</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%" style="text-align: center;">Benefit Name &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%" style="text-align: center;">Benefit Value &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="45%" style="text-align: center;">Description &nbsp;</td>			
	</tr>

	<c:forEach items="${benefitList}" var="benefit" varStatus="status" >	
	 	<tr height="20">
      		<td class="oddListRowS1" align="center" style="border: 1px #000 solid;" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${status.index + 1}" />.</td>						   		
			<td class="oddListRowS1" bgcolor="#e7f0fe" style="border: 1px #000 solid;" nowrap="nowrap" valign="top">
				<c:out value="${benefit.itemCategoryId.itemCategoryName}" />		
			</td>			
			<td class="oddListRowS1" bgcolor="#e7f0fe"  nowrap="nowrap" valign="top" style="border: 1px #000 solid;">
				<c:choose>
					<c:when test="${benefit.isAsCharge}">
					AS CHARGE
					</c:when>
					<c:otherwise >
						<fmt:formatNumber><c:out value="${benefit.benefitLimit}" /></fmt:formatNumber>
					</c:otherwise>
				</c:choose>			
			</td>			
			<td class="oddListRowS1" bgcolor="#e7f0fe"  valign="top" style="border: 1px #000 solid;"  >			
					<c:out value="${benefit.information}" />			
			</td>
    	</tr>   
		<tr>
	      <td colspan="20" class="listViewHRS1"></td>
	    </tr>	
	</c:forEach>
	
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
				<td class="dataLabel" width="15%">
					<c:if test="${theUser.userType eq 4 }">
						<c:if test="${provider.providerCategoryId.providerCategoryId ne 10}">																			
					Surat Rujukan : 
						</c:if>
					</c:if>
				</td>
				<td class="dataField" width="35%">
														
					<c:if test="${theUser.userType eq 4 }">
						<c:if test="${provider.providerCategoryId.providerCategoryId ne 10}">																			
							<spring:bind path="myCaseForm.suratRujukanId">
								<select name="<c:out value="${status.expression}"/>" id="priorityId">
									<option value="">--- PILIH SALAH SATU ---</option>
									<c:forEach items="${referalList}" var="referal">
										<option value="<c:out value="${referal.caseId}" />" <c:if test="${status.value eq referal.caseId}">selected</c:if>>
											<c:out value="${referal.caseNumber}" />
										</option>
									</c:forEach>
								</select>
							</spring:bind>
						</c:if>
					</c:if>									
				</td>
				<td class="dataLabel" width="19%"></td>
				<td class="dataField" width="31%"></td>	
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
	
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tbody>
			<tr>
				<td style="padding-top: 2px; padding-left: 1px;">
					<input type="hidden" name="notyet" value="">
					<input title="Save [Alt+Shift+S]" accesskey="S" tabindex="" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:simpan()" name="Save" value=" Register " type="submit">
					<input title="Cancel [Alt+Shift+C]" accesskey="C" tabindex="" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:kembali()" name="Cancel" value=" Cancel " type="button">
					
				</td>
				<td align="right"></td>
			</tr>
		</tbody>
	</table>


<script language="javascript">


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
    
    
    
    
     
});
	function simpan (){
		document.form1.method = "POST";
		document.form1.action = "register-form";
		document.form1.submit();
	}	
	function kembali (){
		
		window.history.back();
	}
	 function changePoliklinik(){
                //window.alert("CHANGE CLAIM TYPE");
                var poliId = document.getElementById("poliId").value;
                var doctorList = document.getElementById("doctorId");


                AJAXCaseService.getDoctorList (poliId, {
                        callback:function (doctorListRes){
                                doctorList.innerHTML = doctorListRes;


                        }


                });

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
