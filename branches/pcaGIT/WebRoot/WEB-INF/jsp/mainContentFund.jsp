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
						<a href="fund?navigation=search&searchtext=<c:out value="${searchtext}"/>&searchby=<c:out value="${searchby}" />&index=<c:out value="${index}" />&index=<c:out value="${index}"/>" class="tabDetailViewDFLink" style="font-weight: normal;">
							<img src="images/back-arrow.png" title="Return to List"/>
						</a>
					</td>
				</tr>
			</tbody>
		</table>
	  </td>
    </tr>
	
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Fund Code :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${fund.fundCode}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Fund Category :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><c:out value="${fund.fundType.fundCategoryName}"/></td>
	    </tr>
		
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Client :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"><a href="client?navigation=detail&clientId=<c:out value="${fund.clientId.clientId}" />" class="listViewTdLinkS1"> <c:out value="${fund.clientId.clientName}"/></a></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Approved By :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><c:out value="${fund.approvedBy}"/></td>
	    </tr>
	

		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%">&nbsp;</td>
	      <td class="tabDetailViewDL" valign="top" width="15%">&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%">&nbsp;</td>
	    </tr>
			
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Fund Value :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"><fmt:formatNumber > <c:out value="${fund.fundValue}"/></fmt:formatNumber></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Fund Status :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%">
	      	<c:if test="${fund.fundStatus.paymentStatusId eq 1}">OPEN</c:if>
			<c:if test="${fund.fundStatus.paymentStatusId eq 2}">PAID</c:if>
			<c:if test="${fund.fundStatus.paymentStatusId eq 3}">APPROVED</c:if>
			<c:if test="${fund.fundStatus.paymentStatusId eq 4}">BAD</c:if>
			<c:if test="${fund.fundStatus.paymentStatusId eq 5}">PENDING</c:if>
			<c:if test="${fund.fundStatus.paymentStatusId eq 7}">PARTIAL PAID</c:if>
	      </td>
	    </tr>
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Fund Date :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${fund.fundTime}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>

		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>			
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Created Time :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${fund.createdTime}"/>  -  <c:out value="${fund.createdBy}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Modified Time :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><c:out value="${fund.modifiedTime}"/>  -  <c:out value="${fund.modifiedBy}"/></td>
	    </tr>
			
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Description :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%" colspan="3"><c:out value="${fund.description}"/></td>
	    </tr>			
	</tbody>
</table>