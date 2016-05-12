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

<input title="Save [Alt+Shift+S]" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:printPayment()" name="Save" value=" Print Claim " type="button">
<br />
<br />
<table class="tabDetailView" border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td colspan="20"  style="padding: 0px;">
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tbody>
				<tr>
					<td style="text-align: left;" class="tabDetailViewDL">&nbsp;

					</td>
					<td class="tabDetailViewDL" align="right" nowrap="nowrap">
						<a href="member-rki-form" class="tabDetailViewDFLink" style="font-weight: normal;">Return to Registration&nbsp;</a>					
					</td>
				</tr>
			</tbody>
		</table>
	  </td>
    </tr>
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Claim Number :&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><strong><font size="3"><b><c:out value="${claim.claimNumber}"/></b></font></strong></td>
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
	    </tr>
			
	
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Claim Type :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${claim.caseCategoryId.caseCategoryName}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">Discharge Date :&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${claim.dischargeDate}"/></td>
	    </tr>			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="20%">&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="30%"></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>

    	<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Member Group :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${claim.memberId.memberGroupId.groupName}"/></td>
	      
	      
	      <td class="tabDetailViewDL" valign="top" width="15%">Hospital / Clinic / Dr. :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><c:out value="${claim.providerName}"/></td>
	    </tr>	
	    <tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Member Name :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"><a href="member?navigation=detail&memberId=<c:out value="${claim.memberId.memberId}" />" class="listViewTdLinkS1"><c:out value="${claim.memberId.firstName}"/> <c:out value="${claim.memberId.lastName}"/></a></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>	
	    
	   	<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
	
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Claim Charge :&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="33%">&nbsp;&nbsp;<fmt:formatNumber><c:out default="-" value="${claim.claimChargeValue}"/></fmt:formatNumber></td>
 		  <td class="tabDetailViewDL" valign="top" width="17%">Diagnose 1 :&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${claim.diagnosisId.diagnosisCode}" /> - <c:out value="${claim.diagnosisId.diagnosisName}"/>
		  <b><c:if test="${claimStatus eq 4}"><c:out value= " (Diagnosis Exclusion) " /></c:if></td>
	    </tr>
		
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Benefit Coverage :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%">&nbsp;&nbsp;<fmt:formatNumber><c:out default="-" value="${claim.claimPaidValue}"/></fmt:formatNumber></td>
          <td class="tabDetailViewDL" valign="top" width="17%">Diagnose 2 :&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${claim.diagnosis2Id.diagnosisCode}" /> - <c:out value="${claim.diagnosis2Id.diagnosisName}"/></td>
	    </tr>
		
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Excess Charge :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%">&nbsp;&nbsp;<fmt:formatNumber><c:out default="-" value="${claim.claimExcessValue}"/></fmt:formatNumber></td>
          <td class="tabDetailViewDL" valign="top" width="17%">Diagnose 3 :&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${claim.diagnosis3Id.diagnosisCode}" /> - <c:out value="${claim.diagnosis3Id.diagnosisName}"/></td>
	    </tr>
   
	   <tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Claim Paid :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%">&nbsp;&nbsp;<fmt:formatNumber><c:out default="-" value="${claim.claimPaidValue}"/></fmt:formatNumber></td>
          <td class="tabDetailViewDL" valign="top" width="17%">&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="33%"></td>
	    </tr>
	    <tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Pre-Paid Excess:&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%">&nbsp;&nbsp;<fmt:formatNumber><c:out default="-" value="${claim.prePaidExcess}"/></fmt:formatNumber></td>
          <td class="tabDetailViewDL" valign="top" width="17%">&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="33%"></td>
	    </tr>
	    <tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Refund :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%">&nbsp;&nbsp;<fmt:formatNumber><c:out default="-" value="${claim.refund}"/></fmt:formatNumber></td>
          <td class="tabDetailViewDL" valign="top" width="17%">&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="33%"></td>
	    </tr>
					
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%"></td>
	      <td class="tabDetailViewDF" valign="top" width="33%"></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
					
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Approved Time :&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${claim.approvedTime}"/>  -  <c:out value="${claim.approvedBy}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">Closed Time :&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${claim.closedTime}"/>  -  <c:out value="${claim.closedBy}"/></td>
	    </tr>
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Created Time :&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${claim.createdTime}"/>  -  <c:out value="${claim.createdBy}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">Modified Time :&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${claim.modifiedTime}"/>  -  <c:out value="${claim.modifiedBy}"/></td>

	    </tr>

		

		    <tr>
      <td colspan="20"  style="padding: 0px;">
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tbody>
				<tr>
					<td style="text-align: left;" class="tabDetailViewDL">&nbsp;
					</td>
					<td class="tabDetailViewDL" align="right" nowrap="nowrap">
						<a href="member-rki-form" class="tabDetailViewDFLink" style="font-weight: normal;">Return to Registration&nbsp;</a>				
					</td>
				</tr>
			</tbody>
		</table>
	  </td>
    </tr>		
	</tbody>
</table>




		
	
</form>

<br />

<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	<tr height="20">
		<td width="2%" nowrap="nowrap" class="listViewThS1" scope="col"  style="text-align: center;">
				<img src="images/blank.gif" alt="asd" height="1" width="1">No.</td>				   		  			
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%"  style="text-align: center;">
				Item Name &nbsp;</td>
			
		
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%"  style="text-align: center;">
				Claim Value &nbsp;
				</td>

	<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%" style="text-align: center;">
				Benefit Paid &nbsp;</td>
	
	<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%"  style="text-align: center;">Excess Charges &nbsp;</td>
				
	<td scope="col" class="listViewThS1" width="5%" >Pre Paid Excess &nbsp;</td>
	
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%"  style="text-align: center;">Refund&nbsp;</td>
					
					
	<td scope="col" class="listViewThS1" nowrap="nowrap" width="25%"  style="text-align: center;">
				Description &nbsp;</td>
					
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%"  style="text-align: center;">
				Status &nbsp;
				</td>
			   		   		   		   		   				   		   			
			   		   			
			
		
	
	</tr>


	<c:forEach items="${ClaimItems}" var="claimItem" varStatus="status" >
	<c:set var="claimChargeValue" value="${claimChargeValue+claimItem.claimItemValue}"></c:set>
	<c:set var="claimApprovedValue" value="${claimApprovedValue+claimItem.claimItemApprovedValue}"></c:set>
	<c:set var="claimExcessValue" value="${claimExcessValue+claimItem.excessValue}"></c:set>
	<c:set var="claimPrePaidExcess" value="${claimPrePaidExcess+claimItem.prePaidExcess}"></c:set>
	<c:set var="claimRefundValue" value="${claimRefundValue+claimItem.refund}"></c:set>
	
	 <tr height="20">
      <td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${status.index+1}" /></td>

      		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${claimItem.itemId.itemName}" />
			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;">			
				<fmt:formatNumber><c:out value="${claimItem.claimItemValue}" /></fmt:formatNumber>			
		</td>  		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;">
			<fmt:formatNumber><c:out value="${claimItem.claimItemApprovedValue}" /></fmt:formatNumber>			
		</td>		   		   		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;">
			<fmt:formatNumber><c:out value="${claimItem.excessValue}" /></fmt:formatNumber>			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;">
			<fmt:formatNumber><c:out value="${claimItem.prePaidExcess}" /></fmt:formatNumber>			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;">
			<fmt:formatNumber><c:out value="${claimItem.refund}" /></fmt:formatNumber>			
		</td>		   		   		   			   		   		   	
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${claimItem.claimItemDescription}" />			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:if test="${claimItem.claimItemStatus.caseStatusId eq -1}">VOID</c:if>
			<c:if test="${claimItem.claimItemStatus.caseStatusId eq 1}">OPEN</c:if>
			<c:if test="${claimItem.claimItemStatus.caseStatusId eq 2}">PENDING DOCUMENT</c:if>
			<c:if test="${claimItem.claimItemStatus.caseStatusId eq 3}">VERIFIED</c:if>
			<c:if test="${claimItem.claimItemStatus.caseStatusId eq 4}">REJECTED</c:if>
			<c:if test="${claimItem.claimItemStatus.caseStatusId eq 5}">CLOSED</c:if>
			<c:if test="${claimItem.claimItemStatus.caseStatusId eq 6}">PAID</c:if>
			<c:if test="${claimItem.claimItemStatus.caseStatusId eq 7}">PENDING INVESTIGATION</c:if>
			<c:if test="${claimItem.claimItemStatus.caseStatusId eq 8}">CHECKED</c:if>
			<c:if test="${claimItem.claimItemStatus.caseStatusId eq 9}">APPROVED</c:if>
			<c:if test="${claimItem.claimItemStatus.caseStatusId eq 10}">PENDING</c:if>
			<c:if test="${claimItem.claimItemStatus.caseStatusId eq 11}">PROCESS OK</c:if>
			<c:if test="${claimItem.claimItemStatus.caseStatusId eq 12}">COMPLETE</c:if>
			<c:if test="${claimItem.claimItemStatus.caseStatusId eq 13}">CDV ISSUED</c:if>
			<c:if test="${claimItem.claimItemStatus.caseStatusId eq 14}">UNREGISTERED</c:if>
			<c:if test="${claimItem.claimItemStatus.caseStatusId eq 15}">FINALIZED</c:if>
			<c:if test="${claimItem.claimItemStatus.caseStatusId eq 16}">INSTALLMENT PAYMENT</c:if>		  
		</td>  			   		   		   		   		   		   		   		
			   		
					   		   		
	
    </tr>
    
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	
	</c:forEach>
	<tr height="20">
		<td width="2%" nowrap="nowrap" class="listViewThS1" scope="col"  style="text-align: center;">
			<img src="images/blank.gif" alt="asd" height="1" width="1"></td>		

<!-- ini default generated table from database -->
		   		  			
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%"  style="text-align: center;">
				 &nbsp;</td>
			
		
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%"  style="text-align: right">
				<fmt:formatNumber > <c:out value="${claimChargeValue}" /></fmt:formatNumber>
				</td>

	<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%" style="text-align: right">
				<fmt:formatNumber > <c:out value="${claimApprovedValue}" /></fmt:formatNumber>&nbsp;</td>
	
	<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%"  style="text-align: right">
				 <fmt:formatNumber > <c:out value="${claimExcessValue}" /></fmt:formatNumber>&nbsp;</td>
					
					
	<td scope="col" class="listViewThS1" nowrap="nowrap" width="25%"  style="text-align: center;">
				 
				 <fmt:formatNumber > <c:out value="${claimPrePaidExcess}" /></fmt:formatNumber>&nbsp;</td>
					
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%"  style="text-align: right;">
				
				 <fmt:formatNumber > <c:out value="${claimRefundValue}" /></fmt:formatNumber>
				 &nbsp;
				</td>
			   	<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%"  style="text-align: center;">
				 &nbsp;
				</td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%"  style="text-align: center;">
				 &nbsp;
				</td>	   		   		   		   				   		   			
			   		   			
			
		
	
	</tr>

	</tbody>
	</table>
<script language="javascript">

	function printPayment(){
		window.open ("claim?navigation=printclaimrki&claimId=<c:out value="${claim.claimId}" />&url=claim-form","Search","width=1024, height=768, menubar=yes, status=no, toolbar=no, scrollbars=yes, resizable=yes");
	}
	
</script>
