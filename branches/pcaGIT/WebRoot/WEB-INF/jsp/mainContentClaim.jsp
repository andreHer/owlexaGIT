<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<ul id="maintab" class="shadetabs">

	<li class="selected">
		<a href="claim?navigation=detail&claimId=<c:out value="${claim.claimId}" />" rel="tcontent1">Detail Claim</a>
	</li>
	<li>
		<a href="claimitem?memberId=<c:out value="${claim.memberId.memberId}" />&claimId=<c:out value="${claim.claimId}" />" rel="tcontent2">Claim Item</a>
	</li>	
	
	<c:if test="${theUser.userType eq 2}">
	<li>
		<a href="memberbenefit?memberId=<c:out value="${claim.memberId.memberId}" />&navigation=listclaim&claimId=<c:out value="${claim.claimId}" />" rel="tcontent3">Benefit</a>
	</li>
	<li>
		<a href="memberclausul?memberId=<c:out value="${claim.memberId.memberId}" />&navigation=listclaim&claimId=<c:out value="${claim.claimId}" />" rel="tcontent4">Clausul</a>
	</li>
	</c:if>
	<li>
		<a href="case?navigation=list&memberId=<c:out value="${claim.memberId.memberId}" />&claimId=<c:out value="${claim.claimId}" />" rel="tcontent5">Case History</a>
	</li>	
	
	<li>
		<a href="claiminvestigation?memberId=<c:out value="${claim.memberId.memberId}" />&claimId=<c:out value="${claim.claimId}" />" rel="tcontent6">Investigation </a>
	</li>
	<li>
		<a href="claim?navigation=listdetailclaim&memberId=<c:out value="${claim.memberId.memberId}" />&claimId=<c:out value="${claim.claimId}" />" rel="tcontent6">Claim History </a>
	</li>		
	<li >
		<a href="claim?navigation=checklist&memberId=<c:out value="${claim.memberId.memberId}" />&claimId=<c:out value="${claim.claimId}" />" rel="tcontent6">Doc. Checklist </a>
	</li>
	<li >
		<a href="document?memberId=<c:out value="${claim.memberId.memberId}" />&claimId=<c:out value="${claim.claimId}" />" rel="tcontent6">Document </a>
	</li>			
	<li >
		<a href="claimmedicine?navigation=listclaim&memberId=<c:out value="${claim.memberId.memberId}" />&claimId=<c:out value="${claim.claimId}" />" rel="tcontent6">Medicine </a>
	</li>
	<li>
		<a href="claimprocedure?navigation=listclaimProcedure&memberId=<c:out value="${claim.memberId.memberId}" />&claimId=<c:out value="${claim.claimId}" />" rel="tcontent6">Procedure</a>                
	</li>
	<li>
		<a href="claimhistory?navigation=list&memberId=<c:out value="${claim.memberId.memberId}" />&claimId=<c:out value="${claim.claimId}" />" rel="tcontent6">History</a>                
	</li>
</ul>
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
						<a href="claim?navigation=search&searchtext=<c:out value="${searchtext}"/>&searchby=<c:out value="${searchby}" />&index=<c:out value="${index}"/>" class="tabDetailViewDFLink" style="font-weight: normal;">
							<img src="images/back-arrow.png" title="Return to List"/>
						</a>				
					</td>
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
	      <td class="tabDetailViewDL" valign="top" width="17%">&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="33%"></td>
         <td class="tabDetailViewDL" valign="top" width="17%">Other Number :&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${claim.otherNumber}"/></td>
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
				<c:if test="${claim.claimStatus.caseStatusId eq 19}">PRE-OPEN</c:if>
		  
		   <c:if test="${claim.isExGratia eq 1}">(<b>EX GRATIA</b>)</c:if> / <c:if test="${claim.isClausulExcluded eq 1}"><b>EXCLUDED CLAUSE</b></c:if></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Location :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%">
	      	<c:if test="${claim.locationId.locationId eq 1}">DOMESTIC</c:if>
	      	<c:if test="${claim.locationId.locationId eq 2}">OVERSEAS</c:if>
	      	<c:if test="${claim.locationId.locationId eq 3}">BOTH</c:if></td>
	    </tr>

    	<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Member Group :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${claim.memberId.groupName}"/> - <c:if test="${claim.memberId.isVIP eq 1}"><b>VIP MEMBER</b></c:if></td>
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
		  <td class="tabDetailViewDF" valign="top" width="33%">
		  <!-- modified by aju on 20150925, as PAk Firman request, for Provider user -->
		  <%-- 
		  <a href="member?navigation=detail&memberId=<c:out value="${claim.memberId.memberId}" />" class="listViewTdLinkS1"><c:out value="${claim.memberId.firstName}"/> <c:out value="${claim.memberId.lastName}"/></a> - <b>[<c:if test="${claim.memberId.gender eq 'L'}">MALE</c:if><c:if test="${claim.memberId.gender eq 'P'}">FEMALE</c:if>]</b>
		   --%>
		   <c:if test="${theUser.userType eq 4}">
				<a href="#" class="listViewTdLinkS1"><c:out value="${claim.memberId.firstName}"/> <c:out value="${claim.memberId.lastName}"/></a> - <b>[<c:if test="${claim.memberId.gender eq 'L'}">MALE</c:if><c:if test="${claim.memberId.gender eq 'P'}">FEMALE</c:if>]</b>
			</c:if>			
			<c:if test="${theUser.userType ne 4}">
				<a href="member?navigation=detail&memberId=<c:out value="${claim.memberId.memberId}" />" class="listViewTdLinkS1"><c:out value="${claim.memberId.firstName}"/> <c:out value="${claim.memberId.lastName}"/></a> - <b>[<c:if test="${claim.memberId.gender eq 'L'}">MALE</c:if><c:if test="${claim.memberId.gender eq 'P'}">FEMALE</c:if>]</b>
			</c:if>	
		  </td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Relationship :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><c:out value="${claim.memberId.relationship}" /></td>
	    </tr>
	    <tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Date of Birth :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${claim.memberId.birthday}" /></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>		
	    
	   	<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>			
   		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Received Date :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%">
<!-- 		  andre if claimDate null take createdDate -->
		  <c:choose>
				<c:when test="${claim.claimDate eq null}">
						<c:set value="${claim.createdTime}" var="modifDate" />
						<c:set var="dateParts" value="${fn:split(modifDate, ' ')}" />
						<c:out value="${dateParts[0]}"></c:out>
				</c:when>
				<c:otherwise>
					<c:out value="${claim.claimDate}" />
				</c:otherwise>
			</c:choose>
			</td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Hospital / Clinic / Dr. :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><c:out value="${claim.providerName}"/></td>
	    </tr>			
	
	
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Admission Date :&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${claim.admissionDate}"/>
		   
		  <c:if test="${claim.admissionDate < claim.memberId.effectiveDate}">(<b>NOT ACTIVE</b>)</c:if>
		  <c:if test="${claim.admissionDate >= claim.memberId.noClaimStartDate and claim.dischargeDate <= claim.memberId.noClaimEndDate}">(<b>NO CLAIM</b>)</c:if>
		  <c:if test="${claim.admissionDate >= claim.memberId.suspendStartDate and claim.dischargeDate <= claim.memberId.suspendEndDate}">(<b>SUSPEND/GRACE PERIODE</b>)</c:if>
		  <c:if test="${claim.memberId.expireDate eq claim.memberId.resignedDate}">
			  <c:if test="${claim.admissionDate > claim.memberId.expireDate}">(<b>TERMINATED</b>)</c:if>
		  </c:if>
		  <c:if test="${claim.memberId.expireDate ne claim.memberId.resignedDate}">
			  <c:if test="${claim.admissionDate > claim.memberId.expireDate}">(<b>TERMINATED</b>)</c:if>
			  <c:if test="${claim.admissionDate > claim.memberId.resignedDate}">(<b>RESIGNED</b>)</c:if>
		  </c:if>
		  </td>
	      <td class="tabDetailViewDL" valign="top" width="15%"> Referred Claim : &nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"> <c:if test="${claim.isRujukan eq 0 }">TIDAK</c:if><c:if test="${claim.isRujukan eq 1 }">YA, <c:out value="${claim.nomorSuratRujukan }" /></c:if></td>
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
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${claim.batchClaimId.claimCurrency.currencyCode}" />&nbsp;&nbsp;<fmt:formatNumber><c:out value="${claim.claimChargeValue}"/></fmt:formatNumber></td>
 		  <td class="tabDetailViewDL" valign="top" width="17%">Diagnose 1 :&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="33%">
		  	<c:out value="${claim.diagnosisId.diagnosisCode}" /> - <c:out value="${claim.diagnosisId.description}"/> <c:if test="${diagnosis1Status eq 'disability'}"><b>(RECURRING DIAGNOSIS)</b></c:if>&nbsp;&nbsp;<c:if test="${diag1exstat eq 'exclusion'}"><b>(DIAGNOSIS EXCLUSION)</b></c:if></td>
	    </tr>
				
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Benefit Coverage :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${claim.batchClaimId.paymentCurrency.currencyCode}" />&nbsp;&nbsp;<fmt:formatNumber><c:out value="${claim.claimPaidValue}"/></fmt:formatNumber></td>
          <td class="tabDetailViewDL" valign="top" width="17%">Diagnose 2 :&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${claim.diagnosis2Id.diagnosisCode}" /> - <c:out value="${claim.diagnosis2Id.description}"/> <c:if test="${diag2exstat eq 'exclusion'}"><b>(DIAGNOSIS EXCLUSION)</b></c:if></td>
	    </tr>

			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Excess Charge :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${claim.batchClaimId.paymentCurrency.currencyCode}" />&nbsp;&nbsp;<fmt:formatNumber><c:out value="${claim.claimExcessValue}"/></fmt:formatNumber></td>
          <td class="tabDetailViewDL" valign="top" width="17%">Diagnose 3 :&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${claim.diagnosis3Id.diagnosisCode}" /> - <c:out value="${claim.diagnosis3Id.description}"/> <c:if test="${diag3exstat eq 'exclusion'}"><b>(DIAGNOSIS EXCLUSION)</b></c:if></td>

	    </tr>
	    
	   <tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Claim Paid :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${claim.batchClaimId.paymentCurrency.currencyCode}" />&nbsp;&nbsp;<fmt:formatNumber><c:out  value="${claim.claimPaidValue}"/></fmt:formatNumber></td>
          <td class="tabDetailViewDL" valign="top" width="17%">Pre-Paid Excess :&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="33%"><fmt:formatNumber><c:out value="${claim.prePaidExcess}" /></fmt:formatNumber></td>
	    </tr>
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Pembayaran Dimuka :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out default="-" value="${claim.pembayaranDimuka}"/></td>
          <td class="tabDetailViewDL" valign="top" width="17%">Linked CoB Claim :&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="33%"><a href="claim?navigation=detail&claimId=<c:out value="${claim.cobClaimReferenceId.claimId}" />" class="listViewTdLinkS1"><c:out value="${claim.cobClaimReferenceId.claimNumber}" /></a></td>
	    </tr>
		   <tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Pembulatan :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${claim.pembulatan}" /></td>
          <td class="tabDetailViewDL" valign="top" width="17%">&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="33%"></td>
	    </tr>
				
			
		<c:if test="${claim.claimTypeId.claimTypeId eq 2}">
			<tr>
		      <td class="tabDetailViewDL" valign="top" width="17%">Claim Provider :&nbsp;</td>
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${claim.claimProviderStatus}"/></td>
	  	      <td class="tabDetailViewDL" valign="top" width="15%">Case Reference: &nbsp;</td>
		      <td class="tabDetailViewDF" valign="top" width="35%"><c:out value="${claim.caseId.caseNumber}"/></td>

		    </tr>
		    
		</c:if>
			
	
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Iterative :&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${claim.iterative}"/></td>
         <td class="tabDetailViewDL" valign="top" width="17%">Number of Item&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${claim.totalItem}" /></td>
	    </tr>
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Approved Time :&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${claim.benefitCheckedTime}"/>  -  <c:out value="${claim.approvedBy}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">Closed Time :&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${claim.closedTime}"/>  -  <c:out value="${claim.closedBy}"/></td>
	    </tr>
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Created Time :&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${claim.createdTime}"/>  -  <c:out value="${claim.createdBy}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">Modified Time :&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${claim.modifiedTime}"/>  -  <c:out value="${claim.modifiedBy}"/></td>

	    </tr>
		  <td class="tabDetailViewDL" valign="top" width="17%">&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="33%" ></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="33%" ></td>
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Claim Remarks :&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="33%" ><c:out value="${claim.claimRemarks}"/></td>
 			<td class="tabDetailViewDL" valign="top" width="17%">Medical Remarks :&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="33%" ><c:out value="${claim.medicalRemarks}"/></td>	      
	    </tr>
	
	    <tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Checker Remarks :&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${claim.checkerRemarks}"/></td>
		  <td class="tabDetailViewDL" valign="top" width="17%">Payment Remarks :&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="33%" ><c:out value="${claim.paymentRemarks}"/></td>	      
	    </tr>
	    <tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Rejection Remarks :&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${claim.rejectionRemarks}"/></td>
		  <td class="tabDetailViewDL" valign="top" width="17%">&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="33%" ></td>	      
	    </tr>
			
	</tbody>
</table>