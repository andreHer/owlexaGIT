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
						<c:choose>
							<c:when test="${not empty param.subnav }">
								<c:if test="${param.subnav eq 'memberelog' or param.subnav eq 'updatememberelog'}">
									<a href="member?navigation=detail&memberId=<c:out value="${param.memberId}" />" class="tabDetailViewDFLink" style="font-weight: normal;">
										<img src="images/back-arrow.png" title="Return to List"/>
									</a>
								</c:if>
								<c:if test="${param.subnav eq 'caseelog' or param.subnav eq 'updatecaseelog' }">
									<a href="case?navigation=detail&caseId=<c:out value="${param.caseId}" />" class="tabDetailViewDFLink" style="font-weight: normal;">
										<img src="images/back-arrow.png" title="Return to List"/>
									</a>
								</c:if>
								<c:if test="${param.subnav eq 'claimelog' or param.subnav eq 'updateclaimelog' }">
									<a href="claim?navigation=detail&claimId=<c:out value="${param.claimId}" />" class="tabDetailViewDFLink" style="font-weight: normal;">
										<img src="images/back-arrow.png" title="Return to List"/>
									</a>
								</c:if>
								<c:if test="${param.subnav eq 'edit' or param.subnav eq 'detail' }">
									<a href="firstcall?navigation=search" class="tabDetailViewDFLink" style="font-weight: normal;">
										<img src="images/back-arrow.png" title="Return to List"/>
									</a>
								</c:if>
								<c:if test="${param.subnav eq 'update' }">
									<a href="firstcall?navigation=search" class="tabDetailViewDFLink" style="font-weight: normal;">
										<img src="images/back-arrow.png" title="Return to List"/>
									</a>
								</c:if>
							</c:when>
							<c:otherwise>
								
								<a href="firstcall?navigation=search&searchtext=<c:out value="${searchtext}"/>&searchby=<c:out value="${searchby}" />&index=<c:out value="${index}"/>" class="tabDetailViewDFLink" style="font-weight: normal;">
									<img src="images/back-arrow.png" title="Return to List"/>
								</a>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</tbody>
		</table>
	  </td>
    </tr>
	
	
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Call Number :&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${firstCall.callNumber}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Call Category:&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><c:out value="${firstCall.callCategoryId.callCategoryName}"/></td>
	    </tr>
							
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Member Number :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${firstCall.customerId.customerPolicyNumber}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Call Status :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><c:out value="${firstCall.status.caseStatusName}"/></td>
	    </tr>

		<tr>
	      <td class="tabDetailViewDL" valign="top" width="15%">Caller Name:&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><c:out value="${firstCall.callerName}"/></td>
	      <td class="tabDetailViewDL" valign="top"  width="15%">Location :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><c:out value="${firstCall.city}"/></td>	      
	    </tr>
	

		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="33%"></td>

	    </tr>

			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Call Start Time :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${firstCall.callStartTime}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">Call End Time :&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${firstCall.callEndTime}"/></td>

	    </tr>
			
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Telephone Number :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${firstCall.telephone}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Followup Require :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%">
	      <c:if test="${firstCall.followup eq \"Y\"}">Yes</c:if><c:if test="${firstCall.followup eq \"N\"}">No</c:if>

	      </td>
	    </tr>			
	
				
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Created Time :&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${firstCall.createdTime}"/>  -  <c:out value="${firstCall.createdBy}"/></td>
	       <td class="tabDetailViewDL" valign="top" width="17%">Modified Time :&nbsp;</td>	  
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${firstCall.modifiedTime}"/>  -  <c:out value="${firstCall.modifiedBy}"/></td>
	    </tr>
			
	

			

	
			
	

	
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Description :&nbsp;</td> 
			 
	      <td class="tabDetailViewDF" valign="top" width="33%" colspan="3"><c:out value="${firstCall.description}" escapeXml="false"/></td>

	    </tr>
	
			
	
		
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Remarks :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%" colspan="3"> <c:out value="${firstCall.remarks}" escapeXml="false"/></td>

	    </tr>
				
	</tbody>
</table>