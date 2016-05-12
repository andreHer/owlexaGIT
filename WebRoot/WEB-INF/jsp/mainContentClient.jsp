<ul id="maintab" class="shadetabs">

	<li class="selected">
		<a href="client?navigation=detail&clientId=<c:out value="${client.clientId}" />" rel="tcontent1">Detail Client</a>
	</li>
	<li>
		<a href="policy?navigation=listclient&clientId=<c:out value="${client.clientId}" />" rel="tcontent2">Policy</a>
	</li>
	<li>
		<a href="product?navigation=listclient&clientId=<c:out value="${client.clientId}" />" rel="tcontent3">Product</a>
	</li>
	<li>
		<a href="clientprovider?navigation=list&clientId=<c:out value="${client.clientId}" />" rel="tcontent4">Provider </a>
	</li>	
	<li>
		<a href="batchclaim?navigation=listclient&clientId=<c:out value="${client.clientId}" />" rel="tcontent5">Batch Claim </a>
	</li>		
	<li>
		<a href="costcontainment?navigation=listclient&clientId=<c:out value="${client.clientId}" />" rel="tcontent6">Cost Containment</a>
	</li>			
	<li>
		<a href="fund?navigation=listclient&clientId=<c:out value="${client.clientId}" />" rel="tcontent7">Floating Fund</a>
	</li>
	 <li>
		<a href="clientcontract?navigation=listclient&clientId=<c:out value="${client.clientId}" />" rel="tcontent8">Contract</a>
	</li>		
        <li>
		<a href="contactperson?navigation=listclient&clientId=<c:out value="${client.clientId}" />" rel="tcontent8">Contact Person</a>
	</li>	
    <li >
		<a href="bankaccount?navigation=listclient&clientId=<c:out value="${client.clientId}" />" rel="tcontent8">Bank Account</a>
	</li>
	<li >
		<a href="clientmedicine?navigation=listclient&clientId=<c:out value="${client.clientId}" />" rel="tcontent8">Medicine</a>
	</li>
	<li >
		<a href="cardstock?navigation=listclient&clientId=<c:out value="${client.clientId}" />" rel="tcontent8">Card Stock</a>
	</li>
	<li >
		<a href="cardstockusage?navigation=listclient&clientId=<c:out value="${client.clientId}" />" rel="tcontent8">Card Delivery</a>
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
										<td style="text-align: left;" class="tabDetailViewDL">
											
										</td>
										<td class="tabDetailViewDL" align="right" nowrap="nowrap">
											<a href="client?navigation=search&searchtext=<c:out value="${searchtext}"/>&searchby=<c:out value="${searchby}" />&index=<c:out value="${index}"/>" class="tabDetailViewDFLink" style="font-weight: normal;">
												<img src="images/back-arrow.png" title="Return to List"/>
											</a>
										</td>
									</tr>
								</tbody>
							</table>
						</td>
					</tr>
					<tr>
						<td class="tabDetailViewDL" valign="top" width="15%">Client Name :&nbsp;</td>
						<td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${client.clientName}" /></td>
						<td class="tabDetailViewDL" valign="top" width="15%">Registration Date :&nbsp;</td>
						<td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${client.registrationDate}" /></td>
						<td class="tabDetailViewDL" valign="top" width="15%">Bank Name :&nbsp;</td>
						<td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${client.bankName}" /></td>
					</tr>

					<tr>
						<td class="tabDetailViewDL" valign="top" width="15%">Client Number :&nbsp;</td>
						<td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${client.clientNumber}" /></td>
						<td class="tabDetailViewDL" valign="top" width="15%">Contact End Date :&nbsp;</td>
						<td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${client.expireDate}" /></td>
						<td class="tabDetailViewDL" valign="top" width="15%">Bank Account :&nbsp;</td>
						<td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${client.clientBankAccount}" /></td>
					</tr>
					<tr>
						<td class="tabDetailViewDL" valign="top" width="15%">Client Category :&nbsp;</td>						
						<td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${client.clientCategoryId.clientCategoryName}" /></td>
						<td class="tabDetailViewDL" valign="top" width="15%">Renewal Date :&nbsp;</td>
						<td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${client.renewalDate}" /></td>
						<td class="tabDetailViewDL" valign="top" width="15%">Account Name :&nbsp;</td>
						<td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${client.accountName}" /></td>
					</tr>
					<tr>
						<td class="tabDetailViewDL" valign="top" width="15%">Status :&nbsp;</td>
						<td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${client.statusId.status}" /></td>
						<td class="tabDetailViewDL" valign="top" width="15%">Resign Date :&nbsp;</td>
						<td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${client.resignDate}" /></td>
						
						<td class="tabDetailViewDL" valign="top" width="15%">Bank Branch :&nbsp;</td>
						<td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${client.bankBranch}" /></td>
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
						<td class="tabDetailViewDL" valign="top" width="15%">Country :&nbsp;</td>
						<td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${client.country}" /></td>
						<td class="tabDetailViewDL" valign="top" width="15%">Faximile :&nbsp;</td>
						<td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${client.faximile}" /></td>
						<td class="tabDetailViewDL" valign="top" width="15%">Fund Currency :&nbsp;</td>
						<td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${client.fundCurrency.currencyName}" /></td>
					</tr>
					<tr>
						
						<td class="tabDetailViewDL" valign="top" width="15%">Province :&nbsp;</td>
						<td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${client.province}" /></td>
						<td class="tabDetailViewDL" valign="top" width="15%">Telephone :&nbsp;</td>
						<td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${client.telephone}" /></td>
						<td class="tabDetailViewDL" valign="top" width="15%">Payment Currency :&nbsp;</td>
						<td class="tabDetailViewDF" valign="top" width="20%">							
							<c:out value="${client.paymentCurrency.currencyName}" />
						</td>
					</tr>
					
					
					<tr>
						<td class="tabDetailViewDL" valign="top" width="15%">City :&nbsp;</td>
						<td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${client.city}" /></td>
						<td class="tabDetailViewDL" valign="top" width="15%">Email :&nbsp;</td>
						<td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${client.email}" /></td>
						<td class="tabDetailViewDL" valign="top" width="15%">Minimum Fund :&nbsp;</td>
						<td class="tabDetailViewDF" valign="top" width="20%">							
							<fmt:formatNumber><c:out value="${client.minimumFundValue}" /></fmt:formatNumber>							
						</td>
					</tr>
					<tr>
						<td class="tabDetailViewDL" valign="top" width="15%"></td>
						<td class="tabDetailViewDF" valign="top" width="20%"></td>
						<td class="tabDetailViewDL" valign="top" width="15%">Client Website :&nbsp;</td>
						<td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${client.clientWebsite}" /></td>
						<td class="tabDetailViewDL" valign="top" width="15%">Client Fund :&nbsp;</td>
						<td class="tabDetailViewDF" valign="top" width="20%"><fmt:formatNumber><c:out value="${client.clientFundValue}" /></fmt:formatNumber></td>
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
						<td class="tabDetailViewDL" valign="top" width="15%">Created Time :&nbsp;</td>
						<td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${client.createdTime}" />
							by
							<c:out value="${client.createdBy}" />							
						</td>
						<td class="tabDetailViewDL" valign="top" width="15%">
							Modified Time :&nbsp;
						</td>
						<td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${client.modifiedTime}" /> by <c:out value="${client.modifiedBy}" />							
						</td>
						<td class="tabDetailViewDL" valign="top" width="15%">&nbsp;</td>
						<td class="tabDetailViewDF" valign="top" width="20%">&nbsp;</td>
					</tr>



					<tr>
						<td class="tabDetailViewDL" valign="top" width="15%">
							
							Address :&nbsp;
							
						</td>


						<td class="tabDetailViewDF" valign="top" width="20%" colspan="5">
							
							<c:out value="${client.address}" />
							
						</td>

					</tr>

				</tbody>
			</table>