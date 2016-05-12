<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>



<form action="claimitem" method="GET" name="form1" id="form_layout">
<input type="hidden" name="navigation" value="">
	<input type="hidden" name="claimItemId" value="<c:out value="${claimItem.claimItemId}" />">
	
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr> 
      <td style="padding-bottom: 2px;">        
        <c:if test="${claimItem.claimItemStatus.caseStatusId eq 1}">
        <input title="Edit [Alt+Shift+E]" accesskey="E" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:ubah()" name="Edit" value=" Update " type="button">
        <input title="Hapus [Alt+Shift+H]" accesskey="H" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:hapus()" name="Delete" value=" Delete " type="button">        		
		</c:if>
	   </td>
      <td align="right"></td>
    </tr>
  </tbody>
</table>
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
						<a href="claimitem?navigation=search&memberId=<c:out value="${memberId}" />&claimId=<c:out value="${claimItem.claimId.claimId}" />&searchtext=<c:out value="${searchtext}"/>&searchby=<c:out value="${searchby}" />&index=<c:out value="${index}"/>" class="tabDetailViewDFLink" style="font-weight: normal;">Return to List&nbsp;</a>					
					</td>
				</tr>
			</tbody>
		</table>
	  </td>
    </tr>
	
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Claim Number :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${claim.claimNumber}" /></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Item : &nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><c:out value="${claimItem.itemId.itemName}" /></td>
	    </tr>
	    			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Member :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${claim.memberId.firstName}" /> <c:out value="${claim.memberId.lastName}" />&nbsp;</td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Item Status :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%">				
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
	      <td class="tabDetailViewDL" valign="top" width="17%">&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>

	
				
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Item Value :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"><fmt:formatNumber><c:out value="${claimItem.claimItemValue}"/></fmt:formatNumber></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">Item Amount :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${claimItem.claimItemAmount}"/>  <c:out value="${claimItem.measurementUnitId.measurementUnitName}" /></td>

	    </tr>
			
		<tr>
		  <td class="tabDetailViewDL" valign="top" width="15%">Item Covered Value :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><fmt:formatNumber><c:out value="${claimItem.claimItemCoveredValue}"/></fmt:formatNumber></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">Deduction Value :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"></td>		
	    
	    </tr>
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="15%">Item Approved Value :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><fmt:formatNumber><c:out value="${claimItem.claimItemApprovedValue}"/></fmt:formatNumber></td>	      
	      <td class="tabDetailViewDL" valign="top" width="17%"></td>
		  <td class="tabDetailViewDF" valign="top" width="33%"></td>
	    </tr>
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="15%">Excess Charge :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><fmt:formatNumber><c:out value="${claimItem.excessValue}"/></fmt:formatNumber></td>	      
	      <td class="tabDetailViewDL" valign="top" width="17%"></td>
		  <td class="tabDetailViewDF" valign="top" width="33%"></td>
	    </tr>			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="15%">&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%">&nbsp;</td>
	      

	      <td class="tabDetailViewDL" valign="top" width="17%"></td>
		  <td class="tabDetailViewDF" valign="top" width="33%"></td>
	    </tr>						
   		<tr>
	      <td class="tabDetailViewDL" valign="top" width="15%">Verified Time :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><c:out value="${claimItem.verifiedDate}" />   -   <c:out value="${claimItem.verifiedBy}" /> &nbsp;</td>
	      

	      <td class="tabDetailViewDL" valign="top" width="17%">Check Time :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${claimItem.benefitCheckedDate}" />  -  <c:out value="${claimItem.benefitCheckedBy}" /> </td>
	    </tr>						
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Created Time :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${claimItem.createdTime}"/>  -  <c:out value="${claimItem.createdBy}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Modified Time :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><c:out value="${claimItem.modifiedTime}"/>  -   <c:out value="${claimItem.modifiedBy}"/></td>
	    </tr>
			
			

		<tr>
	      <td class="tabDetailViewDL" valign="top" width="20%">Item Description :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%" colspan="3"><c:out value="${claimItem.claimItemDescription}"/></td>
	    </tr>
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Medical Remarks :&nbsp;</td>
	      <td colspan="3" class="tabDetailViewDF" valign="top" width="33%"><c:out value="${claimItem.medicalRemarks}"/></td>

	    </tr>
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Benefit Checker Remarks :&nbsp;</td>
	      <td colspan="3" class="tabDetailViewDF" valign="top" width="33%"><c:out value="${claimItem.claimItemRemarks}"/></td>

	    </tr>
	
			
	
	<tr>
      <td colspan="20"  style="padding: 0px;">
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tbody>
				<tr>
					<td style="text-align: left;" class="tabDetailViewDL">&nbsp;
						
					</td>
					<td class="tabDetailViewDL" align="right" nowrap="nowrap">
						<a href="claimitem?navigation=search&memberId=<c:out value="${memberId}" />&claimId=<c:out value="${claimItem.claimId.claimId }" />&searchtext=<c:out value="${searchtext}"/>&searchby=<c:out value="${searchby}" />&index=<c:out value="${index}"/>" class="tabDetailViewDFLink" style="font-weight: normal;">Return to List&nbsp;</a>					
					</td>
				</tr>
			</tbody>
		</table>
	  </td>
    </tr>			
	</tbody>
</table>


</form>
<script language="javascript">
	function hapus (){
	var delConfirm = window.confirm ("Are You Sure Want To Delete This Entry ?");
		if (delConfirm){
			document.form1.navigation.value = "delete";
			document.form1.submit();
		}
	}
	function kembali (){
		document.form1.navigation.value = "back";
		document.form1.submit();
	}
	function ubah (){
		document.form1.navigation.value = "update";
		document.form1.action = "claimitem-form";
		document.form1.method = "GET";
		document.form1.submit();
	}
</script>
