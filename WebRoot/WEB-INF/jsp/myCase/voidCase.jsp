<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<ul id="maintab" class="shadetabs">

<form action="case" method="POST"  name="form1" id="form_layout">

<input type="hidden" name="navigation" value="dovoid">
	
	<input type="hidden" name="caseId" value="<c:out value="${myCase.caseId	}" />">
	
	
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
						<a href="case?navigation=search&searchtext=<c:out value="${searchtext}"/>&searchby=<c:out value="${searchby}" />&index=<c:out value="${index}"/>" class="tabDetailViewDFLink" style="font-weight: normal;">Return to List&nbsp;</a>					
					</td>
				</tr>
			</tbody>
		</table>
	  </td>
    </tr>
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Case Number :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${myCase.caseNumber}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Case Category :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><c:out value="${myCase.caseCategoryId.caseCategoryName}" /></td>
	    </tr>
	
	
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Member Number :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="33%"><a class="listViewTdLinkS1" href="javascript:popupMember(<c:out value="${myCase.memberId.memberId}" />)"><c:out value="${myCase.memberId.customerPolicyNumber}" /></a></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Case Status :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><b>
	      <c:if test="${myCase.caseStatusId.caseStatusId eq 1}">OPEN</c:if>
	      <c:if test="${myCase.caseStatusId.caseStatusId eq 2}">PENDING DOCUMENT</c:if>
	      <c:if test="${myCase.caseStatusId.caseStatusId eq 3}">VERIFIED</c:if>
	      <c:if test="${myCase.caseStatusId.caseStatusId eq 4}">REJECTED</c:if>
	      <c:if test="${myCase.caseStatusId.caseStatusId eq 5}">CLOSED</c:if>
	      <c:if test="${myCase.caseStatusId.caseStatusId eq 9}">APPROVED</c:if>
	      <c:if test="${myCase.caseStatusId.caseStatusId eq 10}">PENDING</c:if>
	      <c:if test="${myCase.caseStatusId.caseStatusId eq 15}">FINALIZED</c:if>
	      <c:if test="${myCase.caseStatusId.caseStatusId eq 17}">REFERED</c:if>
	      </b></td>
	    </tr>
	    
	    <tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Member Name :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${myCase.memberId.firstName}" /> &nbsp; <c:out value="${myCase.memberId.lastName}" /></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Provider :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><c:out value="${myCase.providerId.providerName}"/></td>
	    </tr>
	  
		<c:if test="${myCase.caseCategoryId.caseCategoryId eq 1}">			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Physician :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${myCase.physician}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Room Benefit :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><c:out value="${myCase.roomAndBoard}"/></td>
	    </tr>
	    <tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Status :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="33%">
	      	<c:if test="${myCase.roomAndBoardStatus eq 1}">SESUAI BENEFIT</c:if>
	      	<c:if test="${myCase.roomAndBoardStatus eq 2}">NAIK PERMINTAAN SENDIRI</c:if>
	      	<c:if test="${myCase.roomAndBoardStatus eq 3}">KAMAR PENUH</c:if>
	      	<c:if test="${myCase.roomAndBoardStatus eq 4}">KAMAR TIDAK TERSEDIA</c:if>
	      	<c:if test="${myCase.roomAndBoardStatus eq 5}">REKOMENDASI DOKTER</c:if>	      	
	      </td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Room Usage:&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><c:out value="${myCase.roomAndBoardUsage}"/></td>
	    </tr>
	  
	    <c:if test="${myCase.claimId ne null}">
			<tr>
		      <td class="tabDetailViewDL" valign="top" width="17%">&nbsp;</td>		  
			  <td class="tabDetailViewDF" valign="top" width="33%"></td>
			  <td class="tabDetailViewDL" valign="top" width="15%"><c:if test="${myCase.claimId ne null}">Claim Reference :&nbsp;</c:if></td>
		      <td class="tabDetailViewDF" valign="top" width="35%"><c:if test="${myCase.claimId ne null}"><a class="listViewTdLinkS1" href="claim?navigation=detail&claimId=<c:out value="${myCase.claimId.claimId}" />"><c:out value="${myCase.claimId.claimNumber}" /></a></c:if></td>	  
		    </tr>
	    </c:if>
	
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
		
	
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Case Handler :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${myCase.caseHandler}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Initial Diagnosis :</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><c:out value="${myCase.initialDiagnosis}" /></td>
	    </tr>
	
			
	
		
	
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Case Start Time :&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${myCase.caseStartTime}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Diagnosis 1 :</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><c:out value="${myCase.diagnosis1Id.diagnosisCode}" /> - <c:out value="${myCase.diagnosis1Id.description}" /> <c:if test="${diagnosis1Status eq 'disability'}"><b>(RECURRING DIAGNOSIS)</b></c:if>&nbsp;&nbsp;<c:if test="${diag3exstat eq 'exclusion'}"><b>(DIAGNOSIS EXCLUSION)</b></c:if></td>
	    </tr>
			
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Case End Time :&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${myCase.caseEndTime}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Diagnosis 2 :</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><c:out value="${myCase.diagnosis2Id.diagnosisCode}" /> - <c:out value="${myCase.diagnosis2Id.description}" />&nbsp;&nbsp;<c:if test="${diag2exstat eq 'exclusion'}"><b>(DIAGNOSIS EXCLUSION)</b></c:if></td>
	    </tr>
			
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Length Of Stay :&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${myCase.longOfStay}"/> &nbsp; hari</td>
		  <td class="tabDetailViewDL" valign="top" width="15%">Diagnosis 3 :</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><c:out value="${myCase.diagnosis3Id.diagnosisCode}" /> - <c:out value="${myCase.diagnosis3Id.description}" />&nbsp;&nbsp;<c:if test="${diag3exstat eq 'exclusion'}"><b>(DIAGNOSIS EXCLUSION)</b></c:if></td>
	    </tr>
	    
	    <tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Average LoS :&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${inaCbgRef.alos}"/> &nbsp; hari</td>
		  <td class="tabDetailViewDL" valign="top" width="15%">Costing Reference :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><fmt:formatNumber><c:out value="${inaCbgRef.treatmentRate}"/></fmt:formatNumber></td>
	    </tr>
	
	    </c:if>
	   
			
		
			
		<c:if test="${myCase.caseCategoryId.caseCategoryId eq 1}">			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="15%">Initial Symptom :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%" colspan="3"><c:out value="${myCase.initialSymptom}"/></td>
	    </tr>
			
		</c:if>
		
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Description :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="33%" colspan="3"><c:out value="${myCase.description}"/></td>	      
	    </tr>
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Void Notes :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="33%" colspan="3">	      	
	      		<textarea rows="8" cols="60" name="note" style="border: 1px #000 solid;"><c:out value="${status.value}" /></textarea>	      		      
	      </td>	      
	    </tr>
		
	    <tr>
      <td colspan="20"  style="padding: 0px;">
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tbody>
				<tr>
					<td style="text-align: left;" class="tabDetailViewDL">&nbsp;
						
					</td>
					<td class="tabDetailViewDL" align="right" nowrap="nowrap">
						<a href="case?navigation=search&searchtext=<c:out value="${searchtext}"/>&searchby=<c:out value="${searchby}" />&index=<c:out value="${index}"/>" class="tabDetailViewDFLink" style="font-weight: normal;">Return to List&nbsp;</a>					
					</td>
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
      <td style="padding-top: 2px;">
	    <input type="hidden" name="notyet" value="">
        <input title="Save [Alt+Shift+S]" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:approveExGratia()" name="Save" value=" Void Case " type="submit">
        <input title="Cancel [Alt+Shift+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:cancel()" name="Cancel" value=" Cancel " type="button">         
	  </td>
      <td align="right"></td>
    </tr>
  </tbody>
</table>
		
		
</form>
<script language="javascript">
	
	
	function cancel (){
		document.form1.navigation.value = "detail";
		document.form1.submit();
	}
	
	function approveExGratia (){
	
		var delConfirm = window.confirm ("Are You Sure Want To Void This Case ?");
		if (delConfirm){
			document.form1.navigation.value = "dovoid";
			document.form1.action = "case";
			document.form1.method = "POST";
			document.form1.submit();	
		}
	}
	
</script>
