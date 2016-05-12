<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>



<form action="claim-form" method="POST"  name="form1" id="form_layout">
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td style="padding-bottom: 2px;">
	    <input type="hidden" name="notyet" value="">
        <input title="Save [Alt+Shift+S]" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:simpan()" name="Save" value=" Save " type="submit">
        <input title="Cancel [Alt+Shift+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:cancel()" name="Cancel" value=" Cancel " type="button">         
        <input title="Pending [Alt+Shift+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:pending()" name="pending" value=" Pending Entry " type="submit">         

	  </td>
      <td align="right"></td>
    </tr>
  </tbody>
</table>

<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>
		<tr>
			<td>
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<tbody>


<%
String alert = (String) request.getAttribute("alert");
if (alert != null && !alert.trim().equals("")) {%>
	<div id="warning">
		<c:out value="${alert}"></c:out>
	</div>
	<%}%>


	<spring:bind path="claimForm.claimId">
		<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>

<input type="hidden" name="navigation" value="">


<tr>
		<td class="dataLabel">Batch Number : </td>		
		<td class="dataField">
		<spring:bind path="claimForm.batchClaimId.batchClaimId">
		<input type="hidden" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />">
		<input type="hidden" name="batchClaimId" value="<c:out value="${status.value}" />">		
		</spring:bind>
		<spring:bind path="claimForm.batchClaimId.batchClaimNumber">
		<input type="text" readonly="readonly" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />">
		</spring:bind>

		</td>
	    
	    <td class="dataLabel">Claim Method : </td>		
		<td class="dataField">
		<spring:bind path="claimForm.claimTypeId.claimTypeId">
			<select name="${status.expression}" disabled="disabled">
				<c:forEach items="${claimType}" var="ct">
					<option value="<c:out value="${ct.claimTypeId}" />" <c:if test="${ct.claimTypeId eq status.value}">selected</c:if>><c:out value="${ct.claimTypeName}" /></option>
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
		<spring:bind path="claimForm.name">
		<input type="text" id="member" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" ><input type="button" name="PILIH" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" value="Search" onClick="javascript:lookupMember()">
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>	

		</td>
	    <td class="dataLabel">Claim Service : </td>		
		<td class="dataField">
		<spring:bind path="claimForm.caseCategoryId.caseCategoryId">
			<select name="${status.expression}">
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
			<td class="dataLabel">Member Name : </td>		
		<td class="dataField">
		<spring:bind path="claimForm.memberId.memberId">
			<input type="hidden" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" id="memberId">
		</spring:bind>
		<spring:bind path="claimForm.name">
		<input type="text" id="member" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>	

		</td>
	    <td class="dataLabel"> </td>		
		<td class="dataField">	</td>
	</tr>

			<tr>
		<td class="dataLabel">Patient Name : </td>		
		<td class="dataField">

		<spring:bind path="claimForm.patientName">
			<select name="" >
				<option value="">Nama Dependent Disini </option>
			</select>
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>	
		</td>
	    <td class="dataLabel"> Location : </td>				
				<td class="dataField">
			<spring:bind path="claimForm.locationId.locationId">
				<select name="<c:out value="${status.expression}" />">
					<c:forEach items="${treatmentLocation}" var="location">
						<option value="<c:out value="${location.locationId}" />" <c:if test="${location.locationId eq status.value}">selected</c:if>><c:out value="${location.location}" /></option>
					</c:forEach>
				</select>
					<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
	</tr>
			
	<c:if test="${claimForm.batchClaimId.batchClaimType.claimTypeId eq 1}">
			<tr>
				<td class="dataLabel">Benefit &nbsp; </td>		
		<td class="dataField">
		<input type="text"  size="35" name="benefitInfo" value="" maxlength="35">
		</td>
	    <td class="dataLabel"> Hospital / Clinic / Dr. : </td>				
			
			<td class="dataField">
			<spring:bind path="claimForm.providerName">
			<input type="text"  size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" maxlength="35">
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
		<spring:bind path="claimForm.caseId.caseNumber">
		<input type="text" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" ><input type="button" name="lookupCaseButton" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" value="Lookup" onClick="javascript:lookupCase()">
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>	
		
		</td>
	    <td class="dataLabel"> Provider Name : </td>				
			

			<td class="dataField">
			<spring:bind path="claimForm.providerName">
			<input type="text" readonly="readonly" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" maxlength="35">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
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
			<select name="<c:out value="${status.expression}" />">
				<c:forEach items="${diagnose}" var="d">
					<option value="<c:out value="${d.diagnosisId}" />" <c:if test="${d.diagnosisId eq status.value}" >selected</c:if>><c:out value="${d.diagnosisName}" /></option>
				</c:forEach>
			</select>
					<input type="text" size="25" name="">
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>	
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
	    <td class="dataField">	<spring:bind path="claimForm.diagnosis2Id.diagnosisId">
	
		<select name="<c:out value="${status.expression}" />">
				<c:forEach items="${diagnose}" var="d">
					<option value="<c:out value="${d.diagnosisId}" />" <c:if test="${d.diagnosisId eq status.value}" >selected</c:if>><c:out value="${d.diagnosisName}" /></option>
				</c:forEach>
			</select>

						<input type="text" size="25" name="">
				<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>	</td>

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
	    <td class="dataField"><spring:bind path="claimForm.diagnosis3Id.diagnosisId">
		<select name="<c:out value="${status.expression}" />">
				<c:forEach items="${diagnose}" var="d">
					<option value="<c:out value="${d.diagnosisId}" />" <c:if test="${d.diagnosisId eq status.value}" >selected</c:if>><c:out value="${d.diagnosisName}" /></option>
				</c:forEach>
			</select>
			<input type="text" size="25" name="">

				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind></td>

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
<input type="text"  name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" maxlength="35">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	    	<td class="dataLabel"> Claim Amount : </td>				
			
			<td class="dataField">
			<spring:bind path="claimForm.claimChargeValue">
					<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<fmt:formatNumber><c:out value="${status.value}" /></fmt:formatNumber>" maxlength="22">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
	</tr>
	

			<tr>
				<td class="dataLabel"> Other Number : </td>				
			

			<td class="dataField">
			<spring:bind path="claimForm.claim.otherNumber">
<input type="text"  name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" maxlength="35">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	    <td class="dataLabel"> Number of Item : </td>				
			

						<td class="dataField">
			<spring:bind path="claimForm.totalItem">
	<input type="text" name="<c:out value="${status.expression}" />" value="<fmt:formatNumber><c:out value="${status.value}"/></fmt:formatNumber>" maxlength="11">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
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
      <td style="padding-bottom: 2px;">
	    <input type="hidden" name="notyet" value="">
     
             <input title="Save [Alt+Shift+S]" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:simpan()" name="Save" value=" Save " type="submit">
        <input title="Cancel [Alt+Shift+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:cancel()" name="Cancel" value=" Cancel " type="button">         
        <input title="Pending [Alt+Shift+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:pending()" name="pending" value=" Pending Entry " type="submit">         
         </td>
      <td align="right"></td>
    </tr>
  </tbody>
</table>
</form>

<script language="javascript">
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
	function cancel (){

		document.form1.method = "GET";
		document.form1.action = "batchclaim";
		document.form1.navigation.value = "detail";
		document.form1.submit();
	}	
	function lookupMember (){
		window.open ("member?navigation=lookup&url=claim-form","Search","width=700, height=550, menubar=no, status=no, toolbar=no, scrollbars=yes, resizable=yes,screenX=200,screenY=100");
	}
	function setMember (idx,nama){
		document.form1.method = "GET";
		document.form1.notyet.value="true";
		document.form1.action = "claim-form";
		//document.form1.claimForm.memberId.value=idx;
		document.getElementById("memberId").value = idx;
		document.getElementById("member").value = nama;

		}
	function lookupDiagnosis (){
		window.open ("diagnosis?navigation=lookup&url=claim-form","Search","width=700, height=550, menubar=no, status=no, toolbar=no, scrollbars=yes, resizable=yes,screenX=200,screenY=100");
	}
	function lookupCase (){
		window.open ("case?navigation=lookup&url=claim-form","Search","width=700, height=550, menubar=no, status=no, toolbar=no, scrollbars=yes, resizable=yes,screenX=200,screenY=100");
	}
	function setCase (idx, nama){

		document.form1.notyet.value="true";
		document.form1.action = "claim-form";

		document.getElementById("caseId").value = idx;

	}
	
	function lookupProvider (){
		window.open ("provider?navigation=lookup&url=claim-form","Search","width=700, height=550, menubar=no, status=no, toolbar=no, scrollbars=yes, resizable=yes,screenX=200,screenY=100");
	}
	function setProvider (idx){
		document.form1.method = "POST";
		document.form1.notyet.value="true";
		document.form1.action = "claim-form";
		//document.form1.claimForm.providerId.value=idx;
		document.getElementById("providerId").value = idx;
		document.form1.submit();
		}
			function lookupPayment (){
		window.open ("payment?navigation=lookup&url=claim-form","Search","width=700, height=550, menubar=no, status=no, toolbar=no, scrollbars=yes, resizable=yes,screenX=200,screenY=100");
	}

				// forreign affairs end
</script>