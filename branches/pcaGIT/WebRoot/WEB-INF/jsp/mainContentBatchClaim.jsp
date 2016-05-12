<ul id="maintab" class="shadetabs">

	<li class="selected">
		<a href="batchclaim?navigation=detail&batchClaimId=<c:out value="${batchClaim.batchClaimId}" />" rel="tcontent1">Detail Batch Claim</a>
	</li>
	<li>
		<a href="claim?navigation=list&batchClaimId=<c:out value="${batchClaim.batchClaimId}" />" rel="tcontent2">Claim</a>
	</li>	
	<li>
		<a href="#/>" rel="tcontent3">Attachment</a>
	</li>
	<li>
		<a href="pendingclaim?navigation=list&batchClaimId=<c:out value="${batchClaim.batchClaimId}" />" rel="tcontent4">Pending Claim</a>
	</li>
	<li>
		<a href="rejectedclaim?navigation=list&batchClaimId=<c:out value="${batchClaim.batchClaimId}" />" rel="tcontent5">Rejected Claim</a>
	</li>	
	 
	<li>
		<a href="excesscharge?navigation=listbatch&batchClaimId=<c:out value="${batchClaim.batchClaimId}" />" rel="tcontent5">Excess Charge</a>
	</li>
	 	
	  
	<li>
		<a href="payment?navigation=listbatch&batchClaimId=<c:out value="${batchClaim.batchClaimId}" />" rel="tcontent5">Claim Payment</a>
	</li>
	 
</ul>

<br/>

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
						<a href="batchclaim?navigation=search&searchtext=<c:out value="${searchtext}"/>&searchby=<c:out value="${searchby}" />&index=<c:out value="${index}"/>" class="tabDetailViewDFLink" style="font-weight: normal;">
							<img src="images/back-arrow.png" title="Return to List"/>
						</a>				
					</td>
				</tr>
			</tbody>
		</table>
	  </td>
    </tr>
		
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="15%">Batch Number :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${batchClaim.batchClaimNumber}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Received Date :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${batchClaim.batchClaimDate}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Batch Amount :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="20%"><fmt:formatNumber><c:out value="${batchClaim.batchClaimInitialValue}"/></fmt:formatNumber></td>
	    </tr>	
			
		
		<tr>
	      
	      <td class="tabDetailViewDL" valign="top" width="15%">Client :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${batchClaim.clientId.clientName}" /></td>
		  <td class="tabDetailViewDL" valign="top" width="15%">Due Date :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${batchClaim.batchDueDate}" /></td>
		  <td class="tabDetailViewDL" valign="top" width="15%">Batch Approved Value :&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="20%"><fmt:formatNumber><c:out value="${batchClaim.batchClaimFinalValue}"/></fmt:formatNumber></td>	      
	    </tr>
		<tr>
	      
	      <td class="tabDetailViewDL" valign="top" width="15%">Payment Recipient :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${batchClaim.paymentRecipient.paymentRecipientName}" /></td>
		  <td class="tabDetailViewDL" valign="top" width="15%">Batch Close Date :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${batchClaim.batchClaimCloseDate}"/></td>
		  <td class="tabDetailViewDL" valign="top" width="15%">Batch Paid :&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="20%"><fmt:formatNumber><c:out value="${batchClaim.batchClaimPaidValue}"/></fmt:formatNumber></td>	      
	    </tr>

		<tr>
	      
	      <td class="tabDetailViewDL" valign="top" width="15%">Recipient :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%">	      
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
		  <td class="tabDetailViewDL" valign="top" width="15%">Batch Status :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${batchClaim.batchClaimStatus.caseStatusName}"/>
		  <c:if test="${paymentbean ne null}">
		  	( 
<!-- 		  	<a href="payment?navigation=detail&index=<c:out value="${index}" />&paymentId=<c:out value="${paymentbean.paymentId}" />" class="linkDetail"> -->
<!-- 		  	 <c:out value="${paymentbean.paymentNumber}" />  -->
<!-- 		  	 </a> -->
		  	
		  <c:forEach items="${paymentCollection}" var="paymentCollection" varStatus="status" >
		  	 <a href="payment?navigation=detail&index=<c:out value="${index}" />&paymentId=<c:out value="${paymentCollection.paymentId} " />" class="linkDetail">
		  	 <c:out value="${paymentCollection.paymentNumber} " /> 
		  	 </a>
		  </c:forEach>
		  	)
		  </c:if>
		  </td>	      
	      <td class="tabDetailViewDL" valign="top" width="15%">Payment Method :&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${batchClaim.paymentMethod.paymentMethod}"/></td>
	    </tr>
	
		<tr>
	      
	      <td class="tabDetailViewDL" valign="top" width="15%">&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%">&nbsp;</td>
		  <td class="tabDetailViewDL" valign="top" width="15%">&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="20%">&nbsp;</td>	 
		  <td class="tabDetailViewDL" valign="top" width="15%">&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="20%">&nbsp;</td>
	    </tr>
			

		<tr>
		  <td class="tabDetailViewDL" valign="top" width="15%">Invoice Number :&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${batchClaim.invoiceNumber}"/> / <c:out value="${batchClaim.invoiceDate}" /></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Claim Currency :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${batchClaim.claimCurrency.currencyCode}" /></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Number of Claim :&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="20%"><fmt:formatNumber><c:out value="${batchClaim.totalClaim}"/></fmt:formatNumber></td>
	    </tr>	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="15%">Exchange Rate :&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="20%">    to   </td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Payment Currency :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${batchClaim.paymentCurrency.currencyCode}" /></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Input Claim :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${batchClaim.incompleteClaim}"/></td>
	    </tr>			
	
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="15%">Created Time :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${batchClaim.createdTime}"/>   -   <c:out value="${batchClaim.createdBy}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Modified Time :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${batchClaim.modifiedTime}"/>  -  <c:out value="${batchClaim.modifiedBy}"/></td>
		  <td class="tabDetailViewDL" valign="top" width="15%">&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="20%">&nbsp;</td>
	    </tr>
	
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Description :&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="33%" colspan="5"><c:out value="${batchClaim.description}"/></td>
	    </tr>
	</tbody>
</table>

