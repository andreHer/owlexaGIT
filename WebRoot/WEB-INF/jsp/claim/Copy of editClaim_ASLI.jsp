<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<script type='text/javascript' src='dwr/interface/AJAXDiagnosisService.js'></script>
<script type='text/javascript' src='dwr/engine.js'></script>
<script type='text/javascript' src='dwr/util.js'></script>
<link rel="stylesheet" type="text/css" href="css/yui.css">
<link rel="stylesheet" type="text/css" href="css/dpSyntaxHighlighter.css">
<link rel="stylesheet" type="text/css" href="css/autocomplete.css">
<link rel="stylesheet" type="text/css" href="css/button.css">
<script type="text/javascript" src="scripts/yahoo-min.js"></script>
<script type="text/javascript" src="scripts/dom-min.js"></script>
<script type="text/javascript" src="scripts/event-min.js"></script>
<script type="text/javascript" src="scripts/animation-min.js"></script>
<script type="text/javascript" src="scripts/connection-min.js"></script>
<script type="text/javascript" src="scripts/autocomplete-min.js"></script>
<script type="text/javascript" src="scripts/element-beta-min.js"></script>
<script type="text/javascript" src="scripts/button-beta-min.js"></script>



<form action="claim-form" method="POST"  name="form1" id="form_layout">
<input type="hidden" name="navigation" value="">
<input type="hidden" id="paymentRecipient" name="paymentRecipient" value="<c:out value="${claimForm.batchClaimId.paymentRecipient.paymentRecipientId}" />">
<c:if test="${claimForm.batchClaimId.batchClaimType.claimTypeId eq 1}">

	<c:if test="${claimForm.batchClaimId.paymentRecipient.paymentRecipientId eq 1}">
<input type="hidden" name="memberGroupId" value="<c:out value="${claimForm.batchClaimId.memberGroupId.memberGroupId }" />" id="memberGroupId">
	</c:if>
	
	
</c:if>

<c:if test="${claimForm.batchClaimId.batchClaimType.claimTypeId eq 2}">

	<c:if test="${claimForm.batchClaimId.paymentRecipient.paymentRecipientId eq 3}">
<input type="hidden" name="clientId" value="<c:out value="${claimForm.batchClaimId.clientId.clientId }" />" id="clientId">
	</c:if>
	
	
</c:if>


<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td style="padding-bottom: 2px; padding-left: 1px;">
	    <input type="hidden" name="notyet" value="">
        <input title="Save [Alt+Shift+S]" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:simpan()" name="Save" value=" Save " type="submit">
        <input title="Cancel [Alt+Shift+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:cancel()" name="Cancel" value=" Cancel " type="button">         
        <input title="Pending [Alt+Shift+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:pendingClaim()" name="pending" value=" Pending Entry " type="button">         

	  </td>
      <td align="right"></td>
    </tr>
  </tbody>
</table>

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
		<td class="dataLabel">Batch Number : </td>		
		<td class="dataField">
		<spring:bind path="claimForm.batchClaimId.batchClaimId">
		<input type="hidden" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />">
			
		</spring:bind>
		<spring:bind path="claimForm.batchClaimId.batchClaimNumber">
		<input type="text" readonly="readonly" disabled="disabled" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />">
		</spring:bind>

		</td>
	    
	    <td class="dataLabel">Claim Service : </td>		
		<td class="dataField">
		<spring:bind path="claimForm.caseCategoryId.caseCategoryId">
			<select tabindex="1" name="${status.expression}">
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
			<td class="dataLabel">Policy Number : </td>		
		<td class="dataField">
		<spring:bind path="claimForm.memberId.memberId">
			<input type="hidden" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" id="memberId">
		</spring:bind>
		<spring:bind path="claimForm.policyNumber">
		<input type="text" id="member_number" tabindex="2" onchange="javascript:changeMemberNumber()" onkeypress="javascript:searchMemberNumber();" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >&nbsp;
<c:if test="${claimForm.batchClaimId.paymentRecipient.paymentRecipientId  ne 2}">
<input type="button" name="PILIH" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" value="Lookup" onClick="javascript:lookupMember()"></c:if>
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
				<input type="text" tabindex="3"  id="<c:out value="${status.expression}" />" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" size="35" >
			</spring:bind>

			
		</td>
	  
		<td class="dataLabel"> Provider Name : </td>				
			

			<td class="dataField">
			<spring:bind path="claimForm.providerName">
			<input type="text"  size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" maxlength="35">
			<c:if test="${claimForm.batchClaimId.batchClaimType.claimTypeId eq 1}"><input type="button" name="lookupCaseButton" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" value="Lookup" onClick="javascript:lookupProvider()">
				
			</c:if>
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			<spring:bind path="claimForm.providerId.providerId">
			<input type="hidden" readonly="readonly" id="providerId" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" maxlength="35">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>	 
		
	</tr>
	<c:if test="${claimForm.batchClaimId.batchClaimType.claimTypeId eq 1 and claimForm.batchClaimId.paymentRecipient.paymentRecipientId eq 2}">
	<tr>
		<td class="dataLabel">Patient Name : </td>		
		<td class="dataField">

		<spring:bind path="claimForm.patientName">
			
			<select name="<c:out value="${status.expression}" />">
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
	

	<c:if test="${claimForm.batchClaimId.batchClaimType.claimTypeId eq 2}">
			<tr>
				<td class="dataLabel">Case Reference : </td>		
		<td class="dataField">
		<spring:bind path="claimForm.caseId">
			<input type="hidden" name="<c:out value="${status.expression}"/>" id="idCase" value="<c:out value="${status.value}" />" >
		</spring:bind>
		<spring:bind path="claimForm.caseNumber">
		<input type="text" name="<c:out value="${status.expression}"/>" id="numberCase" value="<c:out value="${status.value}" />" >&nbsp;<input type="button" name="lookupCaseButton" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" value="Lookup" onClick="javascript:lookupCase()">
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>	
		
		</td>
	    <td class="dataLabel"> </td>				
			

			<td class="dataField">
			
		</td>	    
	</tr>
	</c:if>

			<tr>
				<td class="dataLabel">&nbsp; </td>				
			
			<td class="dataField">&nbsp;</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>



			<tr>
				<td class="dataLabel"> Received Date : </td>				
			

						<td class="dataField">
			<spring:bind path="claimForm.claimDate">
					<input type="text" readonly="readonly" name="<c:out value="${status.expression}" />" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="10">
					
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
	    <td class="dataLabel">Diagnosis 1 : </td>
	    <td class="dataField">
	    	<spring:bind path="claimForm.diagnosisId.diagnosisId">			
	    	<input type="hidden" id="diagnosis1Id" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />">
	    		<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>	
			<spring:bind path="claimForm.diagnosis1Code">
				<input type="text" id="diagnosis1Code" size="7"  onchange="javascript:changeDiagnosis1()" onkeydown="javascript:searchDiagnosisByCode(event);" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />">
			</spring:bind>

			<spring:bind path="claimForm.diagnosis1Text">
				<input type="text"  size="20" id="diagnosis1Text" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />">
			</spring:bind>
			<input type="button" name="PILIH" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" value="Lookup" onClick="javascript:lookupDiagnosis('diagnosis1')">

			
		</td>
	</tr>
			
			<tr>
				<td class="dataLabel"> Admission Date : </td>				
			

						<td class="dataField">
			<spring:bind path="claimForm.admissionDate">
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
	    <td class="dataLabel">Diagnosis 2 : </td>
	    <td class="dataField">	
	    <spring:bind path="claimForm.diagnosis2">	
				<input type="hidden" id="diagnosis2Id" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />">
				<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>	
			<spring:bind path="claimForm.diagnosis2Code">
				<input type="text" size="7" id="diagnosis2Code"  tabindex="1" onkeypress="javascript:testDiag2()" onchange="javascript:changeDiag2()" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />">
			</spring:bind>
			
			<spring:bind path="claimForm.diagnosis2Text">
				<input type="text" tabindex="2" id="diagnosis2Text"  size="20" onkeypress="javascript:testDiag2()" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />">
			</spring:bind>
			<input type="button" name="PILIH" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" value="Lookup" onClick="javascript:lookupDiagnosis('diagnosis2')">
		</td>

	</tr>
						

			<tr>
				<td class="dataLabel"> Discharge Date : </td>				
			

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
	    <td class="dataLabel">Diagnosis 3 :</td>
	    <td class="dataField">
	    	<spring:bind path="claimForm.diagnosis3">
				<input type="hidden" id="diagnosis3Id" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			<spring:bind path="claimForm.diagnosis3Code">
				<input autocomplete="off" id="diagnosis3Code"  type="text" size="7" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />">
			</spring:bind>
			<spring:bind path="claimForm.diagnosis3Text">
				<input type="text" size="20" id="diagnosis3Text"  disabled="disabled" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />">
			</spring:bind>
			<input type="button" name="PILIH" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" value="Lookup" onClick="javascript:lookupDiagnosis('diagnosis3')">
		</td>

	</tr>
			
	<tr>
				<td class="dataLabel"> &nbsp; </td>				
			
			<td class="dataField">&nbsp;
			</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
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
				<option value="-"> -- PILIH LEVEL -- </option>
				
				<option value="KOMPLEKS" <c:if test="${status.value eq \"KOMPLEKS\" }">selected</c:if>>KOMPLEKS</option>
				<option value="BESAR" <c:if test="${status.value eq \"BESAR\" }">selected</c:if>>BESAR</option>
				<option value="SEDANG" <c:if test="${status.value eq \"SEDANG\" }">selected</c:if>>SEDANG</option>
				<option value="KECIL" <c:if test="${status.value eq \"KECIL\" }">selected</c:if>>KECIL</option>
				<option value="MINOR" <c:if test="${status.value eq \"MINOR\" }">selected</c:if>>MINOR</option>
			</select>
			  
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>	
		</td>
	</tr>
	
	<tr>
				<td class="dataLabel"> Has Complication : </td>				
			

			<td class="dataField">
			<spring:bind path="claimForm.hasComplication">
			<input type="checkbox" name="<c:out value="${status.expression}" />" value="1" <c:if test="${status.value eq '1' }">checked</c:if>>
		</spring:bind>
		</td>
	    	
			<td class="dataLabel">is Accident : </td>		
		<td class="dataField">
		<spring:bind path="claimForm.isAccident">
			<input type="checkbox" name="<c:out value="${status.expression}" />" value="1" <c:if test="${status.value eq '1' }">checked</c:if>>
		</spring:bind>
	
		</td>
	</tr>

	<tr>
				<td class="dataLabel"> Room is Not Available : </td>				
			

			<td class="dataField">
			<spring:bind path="claimForm.roomNotAvailable">
			<input type="checkbox" name="<c:out value="${status.expression}" />" value="1" <c:if test="${status.value eq '1' }">checked</c:if>>
		</spring:bind>
		</td>
	    	
			<td class="dataLabel"> Room is Full : </td>		
		<td class="dataField">
		<spring:bind path="claimForm.roomIsFull">
			<input type="checkbox" name="<c:out value="${status.expression}" />" value="1" <c:if test="${status.value eq '1' }">checked</c:if>>
		</spring:bind>
	
		</td>
	</tr>
			
				<tr>
				<td class="dataLabel"></td>				
			

			<td class="dataField"></td>
	    	<td class="dataLabel"> <!-- Pembayaran Dimuka :  --></td>				
			
			<td class="dataField"> <!-- 
			<spring:bind path="claimForm.pembayaranDimuka">
					<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="22">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind> -->
			</td>
	</tr>
	
	

			<tr>
				<td class="dataLabel"></td>				
			

			<td class="dataField">

		</td>
	    <td class="dataLabel"> <!-- 
	    	Claim Amount :  --></td>				
			
			<td class="dataField"> <!-- 
			<spring:bind path="claimForm.claim.claimChargeValue">
					<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="22">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind> -->
			</td>
	</tr>

		


			
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
     
             <input title="Save [Alt+Shift+S]" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:simpan()" name="Save" value=" Save " type="submit">
        <input title="Cancel [Alt+Shift+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:cancel()" name="Cancel" value=" Cancel " type="button">         
        <input title="Pending [Alt+Shift+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:pendingClaim()" name="pending" value=" Pending Entry " type="button">         
         </td>
      <td align="right"></td>
    </tr>
  </tbody>
</table>
</form>

<script language="javascript">
	
	function pendingClaim(){
		document.form1.method = "POST";
		document.form1.navigation.value = "pending";
		document.form1.submit();
	}
	function simpan (){

		document.form1.method = "POST";
		document.form1.action = "claim-form";
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
	function cancel (){

		document.form1.method = "GET";
		document.form1.action = "batchclaim";
		document.form1.navigation.value = "detail";
		document.form1.submit();
	}	
	function lookupMember(){
		//document.getElementById("searchMemberPanel").style = "display: yes;";
	
		var recipient = document.getElementById("paymentRecipient").value;
		
		if (recipient == 1){
			var memberGroupId = document.getElementById("memberGroupId").value;
			window.open ("member?navigation=lookup&url=claim-form&usenumber=yes&memberGroupId="+memberGroupId,"Search","width=700, height=550, menubar=no, status=no, toolbar=no, scrollbars=yes, resizable=yes,screenX=200,screenY=100");		
		}
		else if (recipient == 3 || recipient == 2){
			var clientId = document.getElementById("clientId").value;
			window.open ("member?navigation=lookup&url=claim-form&usenumber=yes&clientId="+clientId,"Search","width=700, height=550, menubar=no, status=no, toolbar=no, scrollbars=yes, resizable=yes,screenX=200,screenY=100");
		}
		
	}
	function lookupMember1 (){
		window.open ("member?navigation=lookup&url=claim-form","Search","width=700, height=550, menubar=no, status=no, toolbar=no, scrollbars=yes, resizable=yes,screenX=200,screenY=100");
	}
	function setMember (idx,nama,number){
		document.form1.method = "GET";
		document.form1.notyet.value="true";
		document.form1.action = "claim-form";
		//document.form1.claimForm.memberId.value=idx;
		document.getElementById("memberId").value = idx;
		document.getElementById("name").value = nama;
		document.getElementById("member_number").value = number;
	}
	function lookupDiagnosis (diagId){
		window.open ("diagnosis?navigation=lookup&url=claim-form&parentElem="+diagId,"Search","width=700, height=550, menubar=no, status=no, toolbar=no, scrollbars=yes, resizable=yes,screenX=200,screenY=100");
	}
	
	function lookupCase (){
		var member = document.getElementById("memberId").value;
		window.open ("case?navigation=lookup&url=claim-form&memberId="+member,"Search","width=700, height=550, menubar=no, status=no, toolbar=no, scrollbars=yes, resizable=yes,screenX=200,screenY=100");
	}
	function setDiagnosis(elem,diagCode,diagName,diagId){
		document.getElementById(elem+"Code").value = diagCode;
		document.getElementById(elem+"Text").value = diagName;
		document.getElementById(elem+"Id").value = diagId;
	}
	function setCase (idx, nama){

		document.form1.notyet.value="true";
		document.form1.action = "claim-form";

		document.getElementById("idCase").value = idx;
		document.getElementById("numberCase").value = nama;
	}
	
	function lookupProvider (){
		window.open ("provider?navigation=lookup&url=claim-form","Search","width=700, height=550, menubar=no, status=no, toolbar=no, scrollbars=yes, resizable=yes,screenX=200,screenY=100");
	}
	function cariDiag3(){
//		document.form1.diagnosis3Text.value = "test";
	}
	function changeDiag1Code(){

		document.form1.diagnosis1Text.value = "test";
	}
	function testing(){
//		window.alert("testing");
		document.getElementById("diagnosis1_model").style.display = "block";
		DWRUtil.setValue("diagnosis1_model","HLOA");
	}
	
	function searchMemberNumber(){
		document.getElementById("customer_model").style.display = "block";
		document.getElementById("customerNumber_model").style.display = "block";		
		document.getElementById("customerName_model").style.display = "block";
		
		var keyword = document.getElementById("member_number").value;
		
//		AJAXMemberController.	
	}
	function searchMemberNumberHandler (members){
	
	
	}
	function changeMemberNumber(){

		document.getElementById("customer_model").style.display = "none";
		document.getElementById("customerNumber_model").style.display = "none";		
		document.getElementById("customerName_model").style.display = "none";
		
		

		var innerHTML = '<ul><li>test1</li></ul>';
		document.getElementById("diagnosis2_model").innerHTML = innerHTML;
		document.getElementById("diagnosis2_model").style.display = "block";

//		window.alert("OI INNER HTML : " + innerHTML);

	}
	
	function testDiag2(){
//		window.alert("testing");
		document.getElementById("diagnosis2_model").style.display = "block";
		document.getElementById("diagnosis2_model1").style.display = "block";		
		document.getElementById("diagnosis2_model_name").style.display = "block";
		
		var keyword = document.getElementByName("diagnosis2Code").value;
		AJAXDiagnosisController.searchDiagnosisByCode (testHandler);

	}
	function changeDiag2(){
//		window.alert("testing");
		document.getElementById("diagnosis2_model").style.display = "none";
		document.getElementById("diagnosis2_model_name").style.display = "none";
	}
	function changeDiagnosis1(){
		document.getElementById("diagnosis1_model").style.display = "none";
	}
	
	function searchDiagnosisByCode (e){
		
		document.getElementById("diagnosis1_code").focus = true;
	
		var keyword = document.getElementById("diagnosis1_code").value;
		
//		window.alert(e.keyCode);
		
		if (e.keyCode == 8){

			var len = keyword.length;
			keyword = keyword.substring(0,len-1);
		}
		else if (e.keyCode == 40){
			// arrow bawah nih
			
		}
		else if (e.keyCode == 15 || e.keyCode == 14){
		
		}
		else {
			keyword = keyword + String.fromCharCode(e.keyCode);		
		}
		
		
		
		document.getElementById("diagnosis1_model").style.display = "block";
		document.getElementById("diagnosis1_model1").style.display = "block";		
		document.getElementById("diagnosis1_model_name").style.display = "block";
		
		
		AJAXDiagnosisService.searchDiagnosisByCode (keyword,{
			callback:function(memberArray){
//				window.alert("KEYWORD : " + keyword);			
				document.getElementById("diagnosis1_model1").innerHTML = memberArray[0];		
				document.getElementById("diagnosis1_model_name").innerHTML = memberArray[1];
				
//				window.alert("Array 1  : " + memberArray[0]);
//				window.alert("Array 2  : " + memberArray[1]);
			}
		});
		
	}
	
	function testDWR(){
		AJAXRemoteController.getMember(testHandler);
	}	
	function testHandler (testResult){
		document.form1.diagnosis1Text.value = testResult.firstName;
//		window.alert ("RESULT NAME : " + testResult.firstName + " RESULT NO : " + testResult.customerPolicyNumber);
	}
	function setProvider (idx){
		document.form1.method = "POST";
		document.form1.notyet.value="true";
		document.form1.action = "claim-form";
		//document.form1.claimForm.providerId.value=idx;
		document.getElementById("providerId").value = idx;
		document.form1.submit();
	}
	function setProvider (idx,providerName){
		document.form1.providerId.value = idx;
		document.form1.providerName.value = providerName;
	}
	function lookupPayment (){
		window.open ("payment?navigation=lookup&url=claim-form","Search","width=700, height=550, menubar=no, status=no, toolbar=no, scrollbars=yes, resizable=yes,screenX=200,screenY=100");
	}
	function searchDiagByCode (inbox,container){
		window.alert("INBOX : " + inbox + " | " + container);
//		YAHOO.example.ACFlickr = new function() { 
		    // Instantiate an XHR DataSource and define schema as an array: 
		    //     ["ResultNodeName", 
		    //     "QueryKeyAttributeOrNodeName", 
		    //     "AdditionalParamAttributeOrNodeName1", 
		    //     ... 
		    //     "AdditionalParamAttributeOrNodeNameN"] 
		    this.oACDS = new YAHOO.widget.DS_XHR("diagnosis-lookup", 
	        ["diagnosis",  "id", "code", "name"]); 
		    this.oACDS.scriptQueryParam = "keyword"; 
		    this.oACDS.responseType = YAHOO.widget.DS_XHR.TYPE_XML; 
		    this.oACDS.maxCacheEntries = 0; 
		    this.oACDS.scriptQueryAppend = "navigation=searchbycode"; 

		    // Instantiate AutoComplete 
		    this.oAutoComp = new YAHOO.widget.AutoComplete(inbox,container, this.oACDS); 
   		 	window.alert ("OI SETELAH GET QUERY");
		    this.oAutoComp.autoHighlight = false; 
		   
		    this.oAutoComp.formatResult = function(oResultItem, sQuery) { 
		        var id = oResultItem[0]; 
		        var code = oResultItem[1]; 
		        var sKeyQuery = sKey.substr(0, sQuery.length); 
		        var sKeyRemainder = sKey.substr(sQuery.length); 
		        var aMarkup = ["<div class='ac-result'><div class='code-result'>", 
		            nQuantity, 
		            "</div><span class='name-result'>", 
		            sKeyQuery, 
		            "</span>", 
		            sKeyRemainder, 
		            "</div>"]; 
		         window.alert("MARKUP : " + aMarkup);
		        return (aMarkup.join("")); 
		    }; 
//		}; 
	}
				// forreign affairs end
</script>