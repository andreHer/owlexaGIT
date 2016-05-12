
<ul id="maintab" class="shadetabs">
	<li class="selected">
		<a href="product?navigation=detail&productId=<c:out value="${product.productId}" />" rel="tcontent1">Detail Product</a>
	</li>
	<li>
		<a href="productbenefit?navigation=list&productId=<c:out value="${product.productId}" />" rel="tcontent2">Product Benefit</a>
	</li>
	<li>
		<a href="productclausul?navigation=list&productId=<c:out value="${product.productId}" />" rel="tcontent3">Product Clausul</a>
	</li>
	<li>
		<a href="productclausul?navigation=listexdiagnosis&productId=<c:out value="${product.productId}" />" rel="tcontent4">Diagnosis Exclusion</a>
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
						<a href="product?navigation=search&searchtext=<c:out value="${searchtext}"/>&searchby=<c:out value="${searchby}" />&rowset=<c:out value="${rowser}" />&index=<c:out value="${index}"/>" class="tabDetailViewDFLink" style="font-weight: normal;">
							<img src="images/back-arrow.png" title="Return to List"/>
						</a>					
					</td>
				</tr>
			</tbody>
		</table>
	  </td>
    </tr>
	
	
		
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Product Name :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${product.productName}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">Product Code :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${product.productCode}"/>&nbsp;&nbsp;(<c:out value="${product.serviceClass}" />)</td>
	    </tr>
	    
	
<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Product Type :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${product.productType.productTypeName}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">Product Limit Type :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${product.productLimitType.productLimit}"/></td>
	    </tr>
	    
			
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="15%">Policy / Client :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><c:out value="${product.policyId.policyNumber}" /> / <c:out value="${product.clientId.clientName}" /></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">Product Status :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${product.productStatus.status}"/></td>
	      
	    </tr>
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="15%">Card Type</td>
	      <td class="tabDetailViewDF" valign="top" width="35%">
	      	<c:if test="${product.edcEnabled eq 1}">ELECTRONIC CARD 
	      		<c:if test="${product.limitBenefitShowedOnEdc eq 1}">(PUBLISHED)</c:if>
	      		<c:if test="${product.limitBenefitShowedOnEdc eq 0}">(NOT PUBLISHED)</c:if>
	      	</c:if>
	      	
	      	<c:if test="${product.edcEnabled eq 0}">SHOW CARD</c:if>
	      	
	      </td>
	      <td class="tabDetailViewDL" valign="top" width="17%">Service Type :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${product.caseCategory.caseCategoryName}"/></td>	      
	    </tr>
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="15%">Shared Product :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%">
	      		<c:forEach items="${sharedPlan}" var="plan" varStatus="status" >
	      			<c:if test="${status.index eq 0 }">
	      				<c:out value="${plan.productName}" />
	      			</c:if>
	      			<c:if test="${status.index > 0 }">
	      				| <c:out value="${plan.productName}" />
	      			</c:if>
	      		</c:forEach>
	      </td>
	      <td class="tabDetailViewDL" valign="top" width="17%">Product Currency :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${product.productCurrencyId.currencyName}"/></td>	      
	    </tr>	
	
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Max Claim Value :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:if test="${product.maxClaimValue eq -1}">unlimited</c:if> <c:if test="${product.maxClaimValue > 0}"><fmt:formatNumber maxFractionDigits="2"><c:out value="${product.maxClaimValue}"/></fmt:formatNumber></c:if></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">Waiting Period :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:if test="${product.waitingPeriode == null}" > immediately</c:if><c:if test="${product.waitingPeriode > 0}" ><c:out value="${product.waitingPeriode}" /> day(s)</c:if></td>
	    </tr>
			
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Annual Limit Value :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:if test="${product.annualLimitValue eq -1}"> unlimited</c:if> <c:if test="${product.annualLimitValue > 0}"><fmt:formatNumber maxFractionDigits="2"><c:out value="${product.annualLimitValue}"/></fmt:formatNumber></c:if>
		  	<c:if test="${product.isUsingSalary eq 1}"><b>using Employee's salary</b></c:if>
		  </td>
	      <td class="tabDetailViewDL" valign="top" width="17%">Dissability Length :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:if test="${product.disabilityLength eq -1}" > unlimited </c:if><c:if test="${product.disabilityLength > 0}" ><c:out value="${product.disabilityLength}" /> day(s)</c:if></td>
	    </tr>
			
	
		
	
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
	
		<tr>
			<td class="tabDetailViewDL" valign="top" width="17%">Reimburse Percentage :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"><fmt:formatNumber maxFractionDigits="2"><c:out value="${product.reimbursementPercentage}" /></fmt:formatNumber> %</td>
	      
			<td class="tabDetailViewDL" valign="top" width="17%">Overseas Percentage :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"><fmt:formatNumber maxFractionDigits="2"><c:out value="${product.overseasPercentage}"/></fmt:formatNumber> %</td>	      
	    </tr>
			
	
			
		<tr>
			<td class="tabDetailViewDL" valign="top" width="17%">Cashless Percentage :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"><fmt:formatNumber maxFractionDigits="2"><c:out value="${product.cashlessPercentage}"/></fmt:formatNumber> %</td>	      
	      <td class="tabDetailViewDL" valign="top" width="17%">Domestic Percentage :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"><fmt:formatNumber maxFractionDigits="2"><c:out value="${product.domesticPercentage}"/></fmt:formatNumber> %</td>
	    </tr>
			
	
		
	
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Created Time :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${product.createdTime}"/> - <c:out value="${product.createdBy}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">Modified Time :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${product.modifiedTime}"/> - <c:out value="${product.modifiedBy}"/></td>

	    </tr>
			
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Product Description :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" colspan="3" valign="top" width="33%"><c:out value="${product.productDescription}"/></td>
	    </tr>

    <tr>
      <td colspan="20"  style="padding: 0px;">
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tbody>
				<tr>
					<td style="text-align: left;" class="tabDetailViewDL">&nbsp;
		
					</td>
<!-- 					<td class="tabDetailViewDL" align="right" nowrap="nowrap"> -->
<!-- 						<a href="product?navigation=search&searchtext=<c:out value="${searchtext}"/>&searchby=<c:out value="${searchby}" />&rowset=<c:out value="${rowser}" />&index=<c:out value="${index}"/>" class="tabDetailViewDFLink" style="font-weight: normal;">Return to List&nbsp;</a>					 -->
<!-- 					</td> -->
				</tr>
			</tbody>
		</table>
	  </td>
    </tr>				
	</tbody>
</table>