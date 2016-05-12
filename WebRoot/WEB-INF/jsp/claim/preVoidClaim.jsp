<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<%
String alert = (String) request.getAttribute("alert");
int index = 0;
int totalIndex = 0;
int count = 0;
int countSet = 0;

try {
	index = ((Integer) request.getAttribute ("index")).intValue();
	count = ((Integer) request.getAttribute ("count")).intValue();
	countSet = ((Integer) request.getAttribute ("countSet")).intValue();
	totalIndex = ((Integer) request.getAttribute ("halAkhir")).intValue();
	
	
	
}
catch (Exception e){
}
String rowclass = "";
int i=0;
%>
<br />
<form action="claim" method="GET" name="form1" id="form_layout">
<input type="hidden" name="navigation" value="">
<input type="hidden" name="claimId" value="<c:out value="${claim.claimId}" />">
<input type="hidden" name="pendingClaimId" value="<c:out value="${pendingClaimId}" />">
<input type="hidden" name="batchClaimId" value="<c:out value="${claim.batchClaimId.batchClaimId}" />" >
<input type="hidden" name="correctionType" value="claim" />

<table class="tabDetailView" border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td colspan="20"  style="padding: 0px;">
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tbody>
				<tr>
					<td style="text-align: left;" class="tabDetailViewDL">&nbsp;</td>
					<td class="tabDetailViewDL" align="right" nowrap="nowrap"></td>
				</tr>
			</tbody>
		</table>
	  </td>
    </tr>
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Claim Number :&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><strong><font size="3"><b><c:out value="${claim.claimNumber}"/></b></font></strong></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Batch Number :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><a href="batchclaim?navigation=detail&batchClaimId=<c:out value="${claim.batchClaimId.batchClaimId}" />" class="listViewTdLinkS1"><strong><font size="3"><b><c:out value="${claim.batchClaimId.batchClaimNumber}"/></b></font></strong></a></td>
	    </tr>

		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Claim Type :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%">
		  	<c:if test="${claim.caseCategoryId.caseCategoryId eq 1}">INPATIENT</c:if>
		  	<c:if test="${claim.caseCategoryId.caseCategoryId eq 2}">OUTPATIENT</c:if>
		  	<c:if test="${claim.caseCategoryId.caseCategoryId eq 3}">MATERNITY</c:if>
		  	<c:if test="${claim.caseCategoryId.caseCategoryId eq 4}">DENTAL</c:if>
		  	<c:if test="${claim.caseCategoryId.caseCategoryId eq 5}">OPTICAL</c:if>
		  	<c:if test="${claim.caseCategoryId.caseCategoryId eq 6}">SPECIALIST</c:if>
		  	<c:if test="${claim.caseCategoryId.caseCategoryId eq 9}">MCU</c:if>
		  </td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Claim Method :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%">
	      	<c:if test="${claim.claimTypeId.claimTypeId eq 1}">REIMBURSEMENT</c:if>
	      	<c:if test="${claim.claimTypeId.claimTypeId eq 2}">CASHLESS</c:if></td>
	    </tr>			
	    	
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="20%">Claim Status :&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="30%">
		  	<c:if test="${claim.claimStatus.caseStatusId eq -1}">VOID</c:if>
				<c:if test="${claim.claimStatus.caseStatusId eq 1}">OPEN</c:if>
				<c:if test="${claim.claimStatus.caseStatusId eq 2}">PENDING DOCUMENT</c:if>
				<c:if test="${claim.claimStatus.caseStatusId eq 3}">VERIFIED</c:if>
				<c:if test="${claim.claimStatus.caseStatusId eq 4}">REJECTED</c:if>
				<c:if test="${claim.claimStatus.caseStatusId eq 5}">CLOSED</c:if>
				<c:if test="${claim.claimStatus.caseStatusId eq 6}">PAID</c:if>
				<c:if test="${claim.claimStatus.caseStatusId eq 7}">PENDING INVESTIGATION</c:if>
				<c:if test="${claim.claimStatus.caseStatusId eq 8}">CHECKED</c:if>
				<c:if test="${claim.claimStatus.caseStatusId eq 9}">APPROVED</c:if>
				<c:if test="${claim.claimStatus.caseStatusId eq 10}">PENDING</c:if>
				<c:if test="${claim.claimStatus.caseStatusId eq 11}">PROCESS OK</c:if>
				<c:if test="${claim.claimStatus.caseStatusId eq 12}">COMPLETE</c:if>
				<c:if test="${claim.claimStatus.caseStatusId eq 13}">CDV ISSUED</c:if>
				<c:if test="${claim.claimStatus.caseStatusId eq 14}">UNREGISTERED</c:if>
				<c:if test="${claim.claimStatus.caseStatusId eq 15}">FINALIZED</c:if>
				<c:if test="${claim.claimStatus.caseStatusId eq 16}">INSTALLMENT PAYMENT</c:if>
		  
		   <c:if test="${claim.isExGratia eq 1}">(<b>EX GRATIA</b>)</c:if> / <c:if test="${claim.isClausulExcluded eq 1}"><b>EXCLUDED CLAUSE</b></c:if></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Location :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%">
	      	<c:if test="${claim.locationId.locationId eq 1}">DOMESTIC</c:if>
	      	<c:if test="${claim.locationId.locationId eq 2}">OVERSEAS</c:if>
	      	<c:if test="${claim.locationId.locationId eq 3}">BOTH</c:if></td>
	    </tr>

    	<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Member Group :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${claim.memberId.groupName}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Member Status :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%">
	      			<c:if test="${claim.memberId.status eq -1}">PENDING</c:if>
						<c:if test="${claim.memberId.status eq 1}">ACTIVE</c:if>
						<c:if test="${claim.memberId.status eq -3}">PENDING CHANGEPLAN</c:if>
						<c:if test="${claim.memberId.status eq -2}">BLOCKED</c:if>
						<c:if test="${claim.memberId.status eq 2}">TERMINATED</c:if>
						<c:if test="${claim.memberId.status eq 3}">RESIGNED</c:if>
						<c:if test="${claim.memberId.status eq 4}">INACTIVE</c:if>
						<c:if test="${claim.memberId.status eq 5}">INITIALIZED</c:if>
	      </td>
	    </tr>	
	    <tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Member Name :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"><a href="member?navigation=detail&memberId=<c:out value="${claim.memberId.memberId}" />" class="listViewTdLinkS1"><c:out value="${claim.memberId.firstName}"/> <c:out value="${claim.memberId.lastName}"/></a></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Relationship :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><c:out value="${claim.memberId.relationship}" /></td>
	    </tr>	
	    
	   	<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>			
   		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Received Date :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${claim.claimDate}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Hospital / Clinic / Dr. :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><c:out value="${claim.providerName}"/></td>
	    </tr>			
	
	
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Admission Date :&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${claim.admissionDate}"/>
		   
		  <c:if test="${claim.admissionDate < claim.memberId.effectiveDate}">(<b>NOT ACTIVE</b>)</c:if>
		  <c:if test="${claim.memberId.expireDate eq claim.memberId.resignedDate}">
			  <c:if test="${claim.admissionDate > claim.memberId.expireDate}">(<b>TERMINATED</b>)</c:if>
		  </c:if>
		  <c:if test="${claim.memberId.expireDate ne claim.memberId.resignedDate}">
			  <c:if test="${claim.admissionDate > claim.memberId.expireDate}">(<b>TERMINATED</b>)</c:if>
			  <c:if test="${claim.admissionDate > claim.memberId.resignedDate}">(<b>RESIGNED</b>)</c:if>
		  </c:if>
		  </td>
	      <td class="tabDetailViewDL" valign="top" width="15%">&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
			
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Discharge Date :&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${claim.dischargeDate}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Effective Date :&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${claim.memberId.effectiveDate}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="33%"></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Claim Charge :&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${claim.batchClaimId.claimCurrency.currencyCode}" />&nbsp;&nbsp;<fmt:formatNumber><c:out default="-" value="${claim.claimChargeValue}"/></fmt:formatNumber></td>
 		  <td class="tabDetailViewDL" valign="top" width="17%">Diagnose 1 :&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${claim.diagnosisId.diagnosisCode}" /> - <c:out value="${claim.diagnosisId.diagnosisName}"/></td>
	    </tr>
				
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Benefit Coverage :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${claim.batchClaimId.paymentCurrency.currencyCode}" />&nbsp;&nbsp;<fmt:formatNumber><c:out default="-" value="${claim.claimPaidValue}"/></fmt:formatNumber></td>
          <td class="tabDetailViewDL" valign="top" width="17%">Diagnose 2 :&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${claim.diagnosis2Id.diagnosisCode}" /> - <c:out value="${claim.diagnosis2Id.diagnosisName}"/></td>
	    </tr>

			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Excess Charge :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${claim.batchClaimId.paymentCurrency.currencyCode}" />&nbsp;&nbsp;<fmt:formatNumber><c:out default="-" value="${claim.claimExcessValue}"/></fmt:formatNumber></td>
          <td class="tabDetailViewDL" valign="top" width="17%">Diagnose 3 :&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${claim.diagnosis3Id.diagnosisCode}" /> - <c:out value="${claim.diagnosis3Id.diagnosisName}"/></td>

	    </tr>
	    
	   <tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Claim Paid :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${claim.batchClaimId.paymentCurrency.currencyCode}" />&nbsp;&nbsp;<fmt:formatNumber><c:out default="-" value="${claim.claimPaidValue}"/></fmt:formatNumber></td>
          <td class="tabDetailViewDL" valign="top" width="17%">&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="33%"></td>
	    </tr>
			
		<c:if test="${claim.claimTypeId.claimTypeId eq \"2\"}">
			<tr>
		      <td class="tabDetailViewDL" valign="top" width="17%">Claim Provider :&nbsp;</td>
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${claim.claimProviderStatus}"/></td>
	  	      <td class="tabDetailViewDL" valign="top" width="15%">Case Reference: &nbsp;</td>
		      <td class="tabDetailViewDF" valign="top" width="35%"><c:out value="${claim.caseId.caseNumber}" /></td>

		    </tr>
		    
		</c:if>
			
	
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Void Notes :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="33%" colspan="3">	      	
	      	<textarea rows="8" cols="60" name="voidNote" style="border: 1px #000 solid;"><c:out value="${voidNote}" /></textarea>
	      	
	      </td>	      
	    </tr>
	</tbody>
</table>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td style="padding-top: 2px;">
	    <input type="hidden" name="notyet" value="">
        <input title="Save [Alt+Shift+S]" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:voidClaim()" name="Save" value=" Void Claim " type="submit">
        <input title="Cancel [Alt+Shift+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:cancel()" name="Cancel" value=" Cancel " type="button">         
	  </td>
      <td align="right"></td>
    </tr>
  </tbody>
</table>
		
	
</form>
<script language="javascript">

	
    
	function voidClaim (){
	
		document.form1.action = "claim";
		document.form1.navigation.value = "approvevoid";	
		document.form1.method = "GET";		
		document.form1.submit();
		
	}
    
    function cancel (){		
		document.form1.action = "claim";
		document.form1.navigation.value = "detail";	
		document.form1.method = "GET";		
		document.form1.submit();			
	}
	
</script>
