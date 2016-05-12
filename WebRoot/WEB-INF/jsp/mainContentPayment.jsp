<ul id="maintab" class="shadetabs">

	<li class="selected">
		<a href="payment?navigation=detail&paymentId=<c:out value="${payment.paymentId}" />" rel="tcontent1">Detail Payment</a>
	</li>
	<li>
		<a href="excesscharge?navigation=listpayment&batchClaimId=<c:out value="${payment.batchClaim.batchClaimId}" />&paymentId=<c:out value="${payment.paymentId}" />" rel="tcontent2">Excess Charge</a>
	</li>
	<li>
		<a href="paymentinstallment?navigation=listpayment&paymentId=<c:out value="${payment.paymentId}" />">Payment Installment</a>
	</li>	
	<li>
		<a href="#">Payment Refund</a>
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
						<a href="payment?navigation=search&searchtext=<c:out value="${searchtext}"/>&searchby=<c:out value="${searchby}" />&index=<c:out value="${index}"/>" class="tabDetailViewDFLink" style="font-weight: normal;">
							<img src="images/back-arrow.png" title="Return to List"/>
						</a>					
					</td>
					
				</tr>
			</tbody>
		</table>
	  </td>
	  
    </tr>
	
	
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Payment Number :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${payment.paymentNumber}"/></td>
	       <td class="tabDetailViewDL" valign="top" width="17%">Batch Number :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><a href="batchclaim?navigation=detail&batchClaimId=<c:out value="${payment.batchClaim.batchClaimId}" />" class="linkDetail"><c:out value="${payment.batchClaim.batchClaimNumber}"/></a></td>
	    </tr>
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Payment Value :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><fmt:formatNumber><c:out value="${payment.paymentValue}"/></fmt:formatNumber></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">Bank Name :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${payment.bankName}"/></td>
	    </tr>
			
		
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Account Number :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${payment.accountNumber}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
	
			
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Giro Number :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${payment.giroNumber}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
	
			
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Payee Name :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${payment.payeeName}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
	
			
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Submission Date :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${payment.paymentTime}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
			
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Payment Status :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%">
			  
			<c:if test="${payment.paymentStatus.paymentStatusId eq 2}">PAID</c:if>
			<c:if test="${payment.paymentStatus.paymentStatusId eq 3}">APPROVED</c:if>
			<c:if test="${payment.paymentStatus.paymentStatusId eq 4}">BAD</c:if>
			<c:if test="${payment.paymentStatus.paymentStatusId eq 5}">PENDING</c:if>
			<c:if test="${payment.paymentStatus.paymentStatusId eq 6}">DISPOSITIONED</c:if>
			<c:if test="${payment.paymentStatus.paymentStatusId eq 7}">PARTIAL PAID</c:if>
			<c:if test="${payment.paymentStatus.paymentStatusId eq 8}">PARTIAL DISPOSITIONED</c:if>
			  
		  </td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>

		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Created Time :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${payment.createdTime}"/> - <c:out value="${payment.createdBy}"/></td>
		  <td class="tabDetailViewDL" valign="top" width="17%">Modified Time :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${payment.modifiedTime}"/> - <c:out value="${payment.modifiedBy}"/></td>
	   </tr>
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Remarks :&nbsp;</td> 
	      <td class="tabDetailViewDF" valign="top" width="33%" colspan="3"><c:out value="${payment.remarks}"/></td>
	    </tr>	
				
	</tbody>
</table>