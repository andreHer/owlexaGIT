<ul id="maintab" class="shadetabs">

	<li>
		<a href="policy?navigation=detail&policyId=<c:out value="${policy.policyId}" />" rel="tcontent7">Policy</a>
	</li>	
    
    <li >
		<a href="policybenefit?navigation=list&policyId=<c:out value="${policy.policyId}" />" rel="tcontent7">Benefit</a>
	</li>    
	
	<li >
		<a href="policyclausul?navigation=list&policyId=<c:out value="${policy.policyId}" />" rel="tcontent7">Clausul</a>
	</li>
	<li >
		<a href="policydiagnosisexclusion?navigation=list&policyId=<c:out value="${policy.policyId}" />" rel="tcontent7">Exclusion</a>
	</li>
	<li >
		<a href="product?navigation=listpolicy&policyId=<c:out value="${policy.policyId}" />" rel="tcontent7">Product List</a>
	</li>
	<li >
		<a href="member?navigation=listpolicy&policyId=<c:out value="${policy.policyId}" />" rel="tcontent1">Member</a>
	</li>
	
	<li>
		<a href="claim?navigation=listpolicy&policyId=<c:out value="${policy.policyId}" />" rel="tcontent4">Claim</a>
	</li>	
	<li>
		<a href="case?navigation=listpolicy&policyId=<c:out value="${policy.policyId}" />" rel="tcontent5">Case</a>
	</li>	
	<li>
		<a href="policyprovider?navigation=listpolicy&policyId=<c:out value="${policy.policyId}" />" rel="tcontent6">Provider</a>
	</li>	
	<li >
		<a href="policymembermovement?navigation=listpolicy&policyId=<c:out value="${policy.policyId}" />" rel="tcontent1">Member Movement</a>
	</li>

	<li >
		<a href="policymedicine?navigation=listpolicy&policyId=<c:out value="${policy.policyId}" />" rel="tcontent1">Medicine</a>
	</li>
	
	
	<li><a	href="fund?navigation=listpolicy&policyId=<c:out value="${policy.policyId}" />" rel="tcontent1">Fund History</a></li>
	
	<li><a	href="policycoveragefund?navigation=listpolicy&policyId=<c:out value="${policy.policyId}" />" rel="tcontent1">Coverage ASO Fund</a></li>
	
	
	
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
						<a href="policy?navigation=search&searchtext=<c:out value="${searchtext}"/>&searchby=<c:out value="${searchby}" />&index=<c:out value="${index}"/>" class="tabDetailViewDFLink" style="font-weight: normal;">
							<img src="images/back-arrow.png" title="Return to List"/>
						</a>					
					</td>
				</tr>
			</tbody>
		</table>
	  </td>
    </tr>

		
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Policy Number :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${policy.policyNumber}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Client :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><c:out value="${policy.clientId.clientName}" /></td>
	    </tr>
	
			
	
		
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Effective Date :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><fmt:formatDate value="${policy.effectiveDate}"/>   s/d   <fmt:formatDate value="${policy.expireDate}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Group Name :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><c:out value="${policy.memberGroupId.groupName}" /></td>
	    </tr>
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Request Date :&nbsp;</td>
		  
			 <td class="tabDetailViewDF" valign="top" width="33%"><fmt:formatDate value="${policy.requestDate}" /></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Status :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%">
	      
					<c:if test="${policy.policyStatus.statusId eq -1}">PENDING</c:if>
						<c:if test="${policy.policyStatus.statusId eq 1}">ACTIVE</c:if>
						<c:if test="${policy.policyStatus.statusId eq -3}">PENDING CHANGEPLAN</c:if>
						<c:if test="${policy.policyStatus.statusId eq -2}">BLOCKED</c:if>
						<c:if test="${policy.policyStatus.statusId eq 2}">TERMINATED</c:if>
						<c:if test="${policy.policyStatus.statusId eq 3}">RESIGNED</c:if>
						<c:if test="${policy.policyStatus.statusId eq 4}">INACTIVE</c:if>
						<c:if test="${policy.policyStatus.statusId eq 5}">INITIALIZED</c:if>
						<c:if test="${policy.policyStatus.statusId eq 8}">ACTIVE EXTEND</c:if>
	      </td>
	    </tr>
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Expire Date :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><fmt:formatDate value="${policy.expireDate}"/> </td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Policy Type : </td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><c:if test="${policy.policyType eq 1}">INDEMNITY</c:if><c:if test="${policy.policyType eq 2}">MANAGED CARE </c:if></td>
	    </tr>
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Policy Date :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><fmt:formatDate value="${policy.policyDate}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Total Premium :</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><fmt:formatNumber><c:out value="${policy.totalPremiumValue}" /></fmt:formatNumber></td>
	    </tr>
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%">&nbsp;</td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
	    
	    <tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Card Type :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${policy.cardTypeId.cardTypeName}" /></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Swipe Card Prefix :</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><c:out value="${policy.swipeCardPrefix}" /></td>
	    </tr>
	    
	    <tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Product Type :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${policy.productType.productTypeName}" /></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Provider Allocation :</td>
	      <td class="tabDetailViewDF" valign="top" width="35%">
   				 <c:if test="${policy.providerAllocationType eq 0 }" >ALL PROVIDER</c:if>
			 <c:if test="${policy.providerAllocationType eq 1 }" >CLIENT LEVEL</c:if>
			 <c:if test="${policy.providerAllocationType eq 2 }" >GROUP LEVEL</c:if>				
			 <c:if test="${policy.providerAllocationType eq 3 }" >MEMBER LEVEL</c:if>
			 <c:if test="${policy.providerAllocationType eq 4 }" >POLICY LEVEL</c:if>
	      
	      </td>
	    </tr>
	    
	    <tr>
	      	<td class="tabDetailViewDL" valign="top" width="17%">CoB Status :&nbsp;</td>		  
			<td class="tabDetailViewDF" valign="top" width="33%"><c:if test="${policy.isCobEnabled eq 1}">YA, Menggunakan CoB</c:if><c:if test="${policy.isCobEnabled eq 0}">TIDAK</c:if></td>
	      	<td class="tabDetailViewDL" valign="top" width="17%">Is Using Floating Fund :&nbsp;</td>		  
			<td class="tabDetailViewDF" valign="top" width="33%"><c:if test="${policy.isUsingFloatingFund eq 1}">YA, Policy Fund</c:if><c:if test="${policy.isUsingFloatingFund eq 2}">YA, Per Coverage Fund</c:if><c:if test="${policy.isUsingFloatingFund eq 0}">TIDAK</c:if></td>
	    </tr>
	    <tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%">&nbsp;</td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
	    <tr>
	      	<td class="tabDetailViewDL" valign="top" width="17%">Current Fund Value :&nbsp;</td>		  
			<td class="tabDetailViewDF" valign="top" width="33%"><font style="font-weight: bolder;"><fmt:formatNumber><c:out value="${policy.currentPolicyFund}" /></fmt:formatNumber></font></td>
	      	<td class="tabDetailViewDL" valign="top" width="17%">Warning Fund Limit :&nbsp;</td>		  
			<td class="tabDetailViewDF" valign="top" width="33%"><font style="font-weight: bolder;"><fmt:formatNumber><c:out value="${policy.minimumPolicyFund}" /></fmt:formatNumber></font> ( <c:out value="${policy.fundWarningPercentage }" /> %)</td>
	    </tr>
	    <tr>
	      	<td class="tabDetailViewDL" valign="top" width="17%">Current Excess Fund :&nbsp;</td>		  
			<td class="tabDetailViewDF" valign="top" width="33%"><fmt:formatNumber><c:out value="${policy.currentExcessFund}" /></fmt:formatNumber></td>
	      	<td class="tabDetailViewDL" valign="top" width="17%">Blocking Fund Limit :&nbsp;</td>		  
			<td class="tabDetailViewDF" valign="top" width="33%"><font style="font-weight: bolder;"><fmt:formatNumber><c:out value="${policy.fundBlockingLimit}" /></fmt:formatNumber></font> ( <c:out value="${policy.blockFundPercentage }" /> %)</td>
	    </tr>
	    <td class="tabDetailViewDL" valign="top" width="17%">Reimbursement Expire Length :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><fmt:formatNumber><c:out value="${policy.reimburseExpireDay}"/></fmt:formatNumber></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Excess Payment :</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><c:if test="${policy.excessSelfCovered eq 0}">EXCESS DIJAMIN DAHULU</c:if><c:if test="${policy.excessSelfCovered eq 1}">EXCESS DIBAYAR DI TEMPAT</c:if></td>
	    </tr>
			 <td class="tabDetailViewDL" valign="top" width="17%">Reimbursement Max Acceptance :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><fmt:formatNumber><c:out  value="${policy.reimburseMaxReceiveDay}"/></fmt:formatNumber></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Excess Expire Length :</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><fmt:formatNumber><c:out  value="${policy.excessChargeExpireDay}"/></fmt:formatNumber></td>
	    </tr>
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%">&nbsp;</td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
		<tr>
	      	<td class="tabDetailViewDL" valign="top" width="17%">Created Time :&nbsp;</td>		  
			<td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${policy.createdTime}"/> - <c:out value="${policy.createdBy}"/></td>
			<td class="tabDetailViewDL" valign="top" width="17%">Modified Time :&nbsp;</td>		  
			<td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${policy.modifiedTime}"/> - <c:out value="${policy.modifiedBy}"/></td>
	    </tr>
		<tr>
	      	<td class="tabDetailViewDL" valign="top" width="17%">Policy Notes :&nbsp;</td>		  
			<td class="tabDetailViewDF" valign="top" width="33%" colspan="3">
				<textarea cols="50" rows="8" ><c:out value="${policy.policyTcNotes}" escapeXml="false"/></textarea>
			</td>
			
	    </tr>			
			
	    <tr>
      <td colspan="20"  style="padding: 0px;">
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tbody>
				<tr>
					<td style="text-align: left;" class="tabDetailViewDL">&nbsp;
						
					</td>
<!-- 					<td class="tabDetailViewDL" align="right" nowrap="nowrap"> -->
<!-- 						<a href="policy?navigation=search&searchtext=<c:out value="${searchtext}"/>&searchby=<c:out value="${searchby}" />&index=<c:out value="${index}"/>" class="tabDetailViewDFLink" style="font-weight: normal;">Return to List&nbsp;</a>					 -->
<!-- 					</td> -->
				</tr>
			</tbody>
		</table>
	  </td>
    </tr>
		
	
			
	</tbody>
</table>