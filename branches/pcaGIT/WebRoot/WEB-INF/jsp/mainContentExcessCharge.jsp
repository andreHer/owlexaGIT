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
						<a href="excesscharge?navigation=search&searchtext=<c:out value="${searchtext}"/>&searchby=<c:out value="${searchby}" />&index=<c:out value="${index}"/>" class="tabDetailViewDFLink" style="font-weight: normal;">
							<img src="images/back-arrow.png" title="Return to List"/>
						</a>					
					</td>
				</tr>
			</tbody>
		</table>
	  </td>
    </tr>
		
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Member :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><a href="member?navigation=detail&memberId=<c:out value="${excessCharge.memberId.memberId}" />" class="linkDetail"><c:out value="${excessCharge.memberId.firstName}"/> <c:out value="${excessCharge.memberId.lastName}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Claim Number :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><a href="claim?navigation=detail&claimId=<c:out value="${excessCharge.claimId.claimId}" />" class="linkDetail"><c:out value="${excessCharge.claimId.claimNumber}"/></td>
	    </tr>
	
		
		<c:if test="${excessCharge.invoiceId ne null}">
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Invoice Number :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><a href="invoice?navigation=detail&invoiceId=<c:out value="${excessCharge.invoiceId.invoiceId}" />" class="linkDetail"><c:out value="${excessCharge.invoiceId.invoiceNumber}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
	    </c:if>
		
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Excess Charge Value :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"> <fmt:formatNumber><c:out value="${excessCharge.excessChargeValue}"/></fmt:formatNumber></td>
	      <td class="tabDetailViewDL" valign="top" width="17%"> Status :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%">
		  	
			<c:if test="${excessCharge.excessChargeStatus.paymentStatusId eq 1}">OPEN</c:if>
			<c:if test="${excessCharge.excessChargeStatus.paymentStatusId eq 2}">PAID</c:if>
			<c:if test="${excessCharge.excessChargeStatus.paymentStatusId eq 3}">APPROVED</c:if>
			<c:if test="${excessCharge.excessChargeStatus.paymentStatusId eq 4}">BAD</c:if>
			<c:if test="${excessCharge.excessChargeStatus.paymentStatusId eq 5}">PENDING</c:if>
			<c:if test="${excessCharge.excessChargeStatus.paymentStatusId eq 7}">PARTIAL PAID</c:if>
		   - 
		  	<c:if test="${excessCharge.excessLetterSent eq 1}">LETTER SENT</c:if>
		  	<c:if test="${excessCharge.excessLetterSent eq 0}">LETTER NOT SENT</c:if>
		  </td>
	    </tr>
			
		
			
		<tr>
<td class="tabDetailViewDL" valign="top" width="17%">Excess Charge Time :&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${excessCharge.excessChargeTime}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">Last Reminder Time :&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${excessCharge.lastReminderTime}"/></td>

	    </tr>
			
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Reminder Counter :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${excessCharge.reminderCounter}"/></td>
		  <td class="tabDetailViewDL" valign="top" width="17%">Next Reminder Time :&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${excessCharge.nextReminderTime}"/></td>
	      
	    </tr>
			
	
		
	    <tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Created Time :&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${excessCharge.createdTime}"/>  -   <c:out value="${excessCharge.createdBy}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Modified Time :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><c:out value="${excessCharge.modifiedTime}"/>  -   <c:out value="${excessCharge.modifiedBy}"/></td>
	    </tr>
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Remarks :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%" colspan="3"><c:out value="${excessCharge.remarks}"/></td>

	    </tr>				
	</tbody>
</table>