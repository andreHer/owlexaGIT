<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>




<form action="claimitem" method="GET" name="form1" id="form_layout">
	<input type="hidden" name="navigation" value="">
	<input type="hidden" name="claimItemId" value="<c:out value="${claimItem.claimItemId}" />">
	<input type="hidden" name="claimId" value="<c:out value="${claimId}" />" />
	
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr> 
      <td style="padding-bottom: 2px;">        
        <input title="Approve [Alt+Shift+A]" accesskey="A" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:approveItem()" name="approve" value=" Approve " type="button">
        <input title="Reject [Alt+Shift+R]" accesskey="R" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:rejectItem()" name="reject" value=" Reject " type="button">        		
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
						<a href="#" onclick='' class="listViewPaginationLinkS1" style="font-weight: normal;">View Change Log</a>
					</td>
					<td class="tabDetailViewDL" align="right" nowrap="nowrap">
						<a href="claimitem?navigation=search&searchtext=<c:out value="${searchtext}"/>&searchby=<c:out value="${searchby}" />&index=<c:out value="${index}"/>" class="tabDetailViewDFLink" style="font-weight: normal;">Return to List&nbsp;</a>					
					</td>
				</tr>
			</tbody>
		</table>
	  </td>
    </tr>
	
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Claim Number :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${claimItem.claimId.claimNumber}" /></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Item : &nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><c:out value="${claimItem.itemId.itemName}" /></td>
	    </tr>
	    			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Member :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${claimItem.claimId.memberId.firstName}" /> <c:out value="${claimItem.claimId.memberId.lastName}" />&nbsp;</td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Item Status :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><c:out value="${claimItem.claimItemStatus.caseStatusName}"/></td>
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
	      <td class="tabDetailViewDL" valign="top" width="17%">Item Remarks :&nbsp;</td>
	      <td colspan="3" class="tabDetailViewDF" valign="top" width="33%">
	      <textarea name="claimItemRemarks" cols="60" rows="6"></textarea>
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
						<a href="claimitem?navigation=search&searchtext=<c:out value="${searchtext}"/>&searchby=<c:out value="${searchby}" />&index=<c:out value="${index}"/>" class="tabDetailViewDFLink" style="font-weight: normal;">Return to List&nbsp;</a>					
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
        <input title="Approve [Alt+Shift+A]" accesskey="A" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:approveItem()" name="approve" value=" Approve " type="button">
        <input title="Reject [Alt+Shift+R]" accesskey="R" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:rejectItem()" name="reject" value=" Reject " type="button">        		
		</td>
      <td align="right"></td>
    </tr>
  </tbody>
</table>

</form>
<script language="javascript">
	function approveItem (){
	
		var delConfirm = window.confirm ("Are You Sure Want To Approve This Item ?");
		
		if (delConfirm){
			document.form1.navigation.value = "approve";
			document.form1.action = "claimitem";
			document.form1.method = "POST";
			document.form1.submit();
		}
	}
	
	function rejectItem (){
	
		var delConfirm = window.confirm ("Are You Sure Want To Reject This Item ?");
		if (delConfirm){
		
			document.form1.navigation.value = "reject";
			document.form1.action = "claimitem";
			document.form1.method = "POST";
			document.form1.submit();
		
		}
	}
</script>
