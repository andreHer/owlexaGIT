<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<ul id="maintab" class="shadetabs">

	<li class="selected">
		<a href="batchclaim?navigation=detail&batchClaimId=<c:out value="${batchClaim.batchClaimId}" />" rel="tcontent1">Detail Batch Claim</a>
	</li>
	<li>
		<a href="claim?navigation=list&batchClaimId=<c:out value="${batchClaim.batchClaimId}" />" rel="tcontent2">Claim</a>
	</li>	
	<li>
		<a href="#" rel="tcontent3">Attachment</a>
	</li>
	<li>
		<a href="pendingclaim?navigation=list&batchClaimId=<c:out value="${batchClaim.batchClaimId}" />" rel="tcontent4">Pending Claim</a>
	</li>
	<li>
		<a href="rejectedclaim?navigation=list&batchClaimId=<c:out value="${batchClaim.batchClaimId}" />" rel="tcontent5">Rejected Claim</a>
	</li>	
	<!-- 
	<li>
		<a href="excesscharge?navigation=listbatch&batchClaimId=<c:out value="${batchClaim.batchClaimId}" />" rel="tcontent5">Excess Charge</a>
	</li>
	 -->	
</ul>

<form action="batchclaim" method="GET" name="form1" id="form_layout">

	<input type="hidden" name="navigation" value="">
	<input type="hidden" name="batchClaimId" value="<c:out value="${batchClaim.batchClaimId}" />">
	
	
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr> 
      <td style="padding-bottom: 2px; padding-top: 8px;">        
      
      	<c:if test="${batchClaim.batchClaimStatus.caseStatusId eq 1}">
      		 <c:if test="${theUser.roleId.roleId eq 0}">
		        <input title="Edit [Alt+Shift+E]" accesskey="E" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:ubah()" name="Edit" value=" Update " type="button">
		        <input title="Hapus [Alt+Shift+H]" accesskey="H" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:hapus()" name="Delete" value=" Delete " type="button">
		        
	        </c:if>        		
	        <input title="Add Claim [Alt+Shift+C]" accesskey="H" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:addClaim()" name="tambahClaim" value=" Add Claim " type="button">        		
			<input title="Complete[Alt+Shift+E]" accesskey="P" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:completeBatch()" name="completBatch" value=" Complete Batch " type="button">
        </c:if>

      	<c:if test="${batchClaim.batchClaimStatus.caseStatusId eq 5}">
	        <input title="Pay Batch Claim [Alt+Shift+E]" accesskey="P" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:paymentBatch()" name="batchPayment" value=" Pay Batch " type="button">
        </c:if>

      	<c:if test="${(batchClaim.batchClaimStatus.caseStatusId eq 12)  or (batchClaim.batchClaimStatus.caseStatusId eq 5) or (batchClaim.batchClaimStatus.caseStatusId eq 15)}">
	        <input title="Open Batch [Alt+Shift+E]" accesskey="P" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:openBatch()" name="opBatch" value=" Open Batch " type="button">
	        
	        <c:if test="${(theUser.roleId.roleId eq 0) and (openClaim > 0) }">
	        	<input title="Verify Bulk [Alt+Shift+V]" accesskey="V" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:verifyBulk()" name="verify" value=" Verify Bulk " type="button">
	        </c:if>
	        
        </c:if>
        
        <c:if test="${(batchClaim.batchClaimStatus.caseStatusId eq 12)  or (batchClaim.batchClaimStatus.caseStatusId eq 15)}">
        	<input title="Close Batch [Alt+Shift+C]" accesskey="P" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:closeBatch()" name="clBatch" value=" Close Batch " type="button">
	        
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
						<a href="#" onclick='' class="listViewPaginationLinkS1" style="font-weight: normal;">View Change Log</a>
					</td>
					<td class="tabDetailViewDL" align="right" nowrap="nowrap">
						<a href="batchclaim?navigation=search&searchtext=<c:out value="${searchtext}"/>&searchby=<c:out value="${searchby}" />&index=<c:out value="${index}"/>" class="tabDetailViewDFLink" style="font-weight: normal;">Return to List&nbsp;</a>					
					</td>
				</tr>
			</tbody>
		</table>
	  </td>
    </tr>
		
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Batch Number :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${batchClaim.batchClaimNumber}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Received Date :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><c:out value="${batchClaim.batchClaimDate}"/></td>
	    </tr>	
			
		
		<tr>
	      
	      <td class="tabDetailViewDL" valign="top" width="15%">Client :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><c:out value="${batchClaim.clientId.clientName}" /></td>
		  <td class="tabDetailViewDL" valign="top" width="20%">Due Date :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${batchClaim.batchDueDate}" /></td>	      
	    </tr>
		<tr>
	      
	      <td class="tabDetailViewDL" valign="top" width="15%">Payment Recipient :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><c:out value="${batchClaim.paymentRecipient.paymentRecipientName}" /></td>
		  <td class="tabDetailViewDL" valign="top" width="20%">Batch Close Date :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${batchClaim.batchClaimCloseDate}"/></td>	      
	    </tr>

		<tr>
	      
	      <td class="tabDetailViewDL" valign="top" width="15%">Recipient :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%">	      
	      <c:if test="${batchClaim.paymentRecipient.paymentRecipientId eq 3}">
	      <c:out value="${batchClaim.providerId.providerName}" />
	      </c:if>
	      <c:if test="${batchClaim.paymentRecipient.paymentRecipientId eq 2}">
	      <c:out value="${batchClaim.memberId.firstName}" />
	      </c:if>
	      <c:if test="${batchClaim.paymentRecipient.paymentRecipientId eq 1}">
	      <c:out value="${batchClaim.memberGroupId.groupName}" />
	      </c:if>
	      
	      
	      </td>
		  <td class="tabDetailViewDL" valign="top" width="20%">Batch Status :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${batchClaim.batchClaimStatus.caseStatusName}"/>
		  <c:if test="${paymentbean ne null}">
		  	(<a href="payment?navigation=detail&index=<c:out value="${index}" />&paymentId=<c:out value="${paymentbean.paymentId}" />" class="linkDetail">
		  	 <c:out value="${paymentbean.paymentNumber}" /> 
		  	</a>)
		  </c:if>
		  </td>	      
	    </tr>
	
		<tr>
	      
	      <td class="tabDetailViewDL" valign="top" width="15%">&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%">&nbsp;</td>
		  <td class="tabDetailViewDL" valign="top" width="20%">&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%">&nbsp;</td>	      
	    </tr>
			

		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Payment Method :&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${batchClaim.paymentMethod.paymentMethod}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Claim Currency :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><c:out value="${batchClaim.claimCurrency.currencyCode}" /></td>
	    </tr>	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Exchange Rate :&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="33%">    to   </td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Payment Currency :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><c:out value="${batchClaim.paymentCurrency.currencyCode}" /></td>
	    </tr>			
	
		<tr>
	      
	      <td class="tabDetailViewDL" valign="top" width="15%">&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%">&nbsp;</td>
		  <td class="tabDetailViewDL" valign="top" width="20%">&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%">&nbsp;</td>	      
	    </tr>			
			
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Batch Amount :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"><fmt:formatNumber><c:out value="${batchClaim.batchClaimInitialValue}"/></fmt:formatNumber></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">Number of Claim :&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="33%"><fmt:formatNumber><c:out value="${batchClaim.totalClaim}"/></fmt:formatNumber></td>
	    </tr>

		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Batch Approved Value :&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="33%"><fmt:formatNumber><c:out value="${batchClaim.batchClaimFinalValue}"/></fmt:formatNumber></td>

	      <td class="tabDetailViewDL" valign="top" width="15%">Input Claim :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><c:out value="${batchClaim.incompleteClaim}"/></td>
	    </tr>
			    
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Batch Paid :&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="33%"><fmt:formatNumber><c:out value="${batchClaim.batchClaimPaidValue}"/></fmt:formatNumber></td>

	      <td class="tabDetailViewDL" valign="top" width="15%">&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%">&nbsp;</td>
	    </tr>
			
	
			
		
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Invoice Number :&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${batchClaim.invoiceNumber}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
	
			
	
			

			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"></td>

	    </tr>

			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Created Time :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${batchClaim.createdTime}"/>   -   <c:out value="${batchClaim.createdBy}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">Modified Time :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${batchClaim.modifiedTime}"/>  -  <c:out value="${batchClaim.modifiedBy}"/></td>

	    </tr>
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Description :&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="33%" colspan="3"><c:out value="${batchClaim.description}"/></td>

	    </tr>
			
	  <tr>
      <td colspan="20"  style="padding: 0px;">
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tbody>
				<tr>
					<td style="text-align: left;" class="tabDetailViewDL">&nbsp;

					</td>
					<td class="tabDetailViewDL" align="right" nowrap="nowrap">
						<a href="batchclaim?navigation=search&searchtext=<c:out value="${searchtext}"/>&searchby=<c:out value="${searchby}" />&index=<c:out value="${index}"/>" class="tabDetailViewDFLink" style="font-weight: normal;">Return to List&nbsp;</a>					
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
      
      	<c:if test="${batchClaim.batchClaimStatus.caseStatusId eq 1}">
      		<c:if test="${theUser.roleId.roleId eq 0}">
		        <input title="Edit [Alt+Shift+E]" accesskey="E" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:ubah()" name="Edit" value=" Update " type="button">
		        <input title="Hapus [Alt+Shift+H]" accesskey="H" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:hapus()" name="Delete" value=" Delete " type="button">
	        </c:if>
	        <input title="Add Claim [Alt+Shift+C]" accesskey="H" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:addClaim()" name="tambahClaim" value=" Add Claim " type="button">        		
			<input title="Complete[Alt+Shift+E]" accesskey="P" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:completeBatch()" name="completBatch" value=" Complete Batch " type="button">
        </c:if>

      	<c:if test="${batchClaim.batchClaimStatus.caseStatusId eq 5}">
	        <input title="Pay Batch Claim [Alt+Shift+E]" accesskey="P" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:paymentBatch()" name="batchPayment" value=" Pay Batch " type="button">
        </c:if>

      	<c:if test="${(batchClaim.batchClaimStatus.caseStatusId eq 12)  or (batchClaim.batchClaimStatus.caseStatusId eq 5) or (batchClaim.batchClaimStatus.caseStatusId eq 15)}">
	        <input title="Open Batch [Alt+Shift+E]" accesskey="P" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:openBatch()" name="opBatch" value=" Open Batch " type="button">
	        
	        <c:if test="${(theUser.roleId.roleId eq 0) and (openClaim > 0)}">
	        	<input title="Verify Bulk [Alt+Shift+V]" accesskey="V" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:verifyBulk()" name="verify" value=" Verify Bulk " type="button">
	        </c:if>
        </c:if>
        
        <c:if test="${(batchClaim.batchClaimStatus.caseStatusId eq 12)  or (batchClaim.batchClaimStatus.caseStatusId eq 15)}">
        	<input title="Close Batch [Alt+Shift+C]" accesskey="P" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:closeBatch()" name="clBatch" value=" Close Batch " type="button">
	        
        </c:if>
        
		</td>
		
      <td align="right"></td>
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
	function ubah (){
		document.form1.navigation.value = "update";
		document.form1.action = "batchclaim-form";
		document.form1.method = "GET";
		document.form1.submit();
	}
	function verifyBulk(){
		var delConfirm = window.confirm ("Are You Sure Want To Do Bulk Verification over this Batch Claim ?");
		if (delConfirm){
			document.form1.navigation.value = "verifybulk";
			document.form1.submit();
		}
	}
	function kembali (){
		document.form1.navigation.value = "back";
		document.form1.submit();
	}
	function paymentBatch(){
		document.form1.navigation.value = "paybatch";
		document.form1.action = "payment-form";
		document.form1.submit();
	}
	
	function addClaim (){
		document.form1.navigation.value = "tambah";
		document.form1.action = "claim-form";
		document.form1.method = "GET";
		document.form1.submit();
	}
	function completeBatch (){
		var delConfirm = window.confirm ("Are You Sure Want To Complete This Batch ?");
	
		if (delConfirm){
			document.form1.navigation.value = "complete";
			document.form1.action = "batchclaim";
			document.form1.method = "GET";
			document.form1.submit();
		}
	}
	function openBatch (){
		var delConfirm = window.confirm ("Are You Sure Want To ReOpen This Batch ?");
	
		if (delConfirm){
			document.form1.navigation.value = "open";
			document.form1.action = "batchclaim";
			document.form1.method = "GET";
			document.form1.submit();
		}
	}
	function closeBatch (){
	
		var delConfirm = window.confirm ("Are You Sure Want To Close This Batch ?");
	
		if (delConfirm){
			document.form1.navigation.value = "close";
			document.form1.action = "batchclaim";
			document.form1.method = "GET";
			document.form1.submit();
		}
	}
</script>
