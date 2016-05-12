
<ul id="maintab" class="shadetabs">
	<c:if test="${listnavigation eq 'searchcapitation'}"><!-- Detail Provider -->
		<li class="selected">
			<a href="provider?navigation=detail&providerId=<c:out value="${provider.providerId}" />&listnavigation=<c:out value="${listnavigation}"/>" rel="tcontent1">Detail Provider</a>
		</li>
	</c:if>
	<c:if test="${listnavigation ne 'searchcapitation'}"><!-- Detail Provider -->
		<li class="selected">
			<a href="provider?navigation=detail&providerId=<c:out value="${provider.providerId}" />" rel="tcontent1">Detail Provider</a>
		</li>
	</c:if>
	
	<c:if test="${listnavigation eq 'searchcapitation'}"><!-- Batch Claim -->
		<li>
			<a href="batchclaim?navigation=list&providerId=<c:out value="${provider.providerId}" />&listnavigation=<c:out value="${listnavigation}"/>" rel="tcontent2">Batch Claim</a>
		</li>
	</c:if>
	<c:if test="${theUser.userType eq 2}">
		<c:if test="${listnavigation ne 'searchcapitation'}"><!-- Batch Claim -->
			<li>
				<a href="batchclaim?navigation=list&providerId=<c:out value="${provider.providerId}" />" rel="tcontent2">Batch Claim</a>
			</li>
		</c:if>
	</c:if>
	
	<c:if test="${listnavigation eq 'searchcapitation'}"><!-- Poliklinik -->
		<li>
			<a href="providerpoliklinik?providerId=<c:out value="${provider.providerId}" />&listnavigation=<c:out value="${listnavigation}"/>" rel="tcontent3">Poliklinik</a>
		</li>
	</c:if>
	<c:if test="${listnavigation ne 'searchcapitation'}"><!-- Poliklinik -->
		<li>
			<a href="providerpoliklinik?providerId=<c:out value="${provider.providerId}" />" rel="tcontent3">Poliklinik</a>
		</li>
	</c:if>
	
	<c:if test="${listnavigation eq 'searchcapitation'}"><!-- Dokter -->
		<li>
			<a href="providerdoctor?providerId=<c:out value="${provider.providerId}" />&listnavigation=<c:out value="${listnavigation}"/>" rel="tcontent3">Dokter</a>
		</li>
	</c:if>
	<c:if test="${listnavigation ne 'searchcapitation'}"><!-- Dokter -->
		<li>
			<a href="providerdoctor?providerId=<c:out value="${provider.providerId}" />" rel="tcontent3">Dokter</a>
		</li>
	</c:if>
	
	<c:if test="${listnavigation eq 'searchcapitation'}"><!-- Room & Board -->
		<li>
			<a href="provideritem?navigation=list&providerId=<c:out value="${provider.providerId}" />&listnavigation=<c:out value="${listnavigation}"/>" rel="tcontent4">Room & Board </a>
		</li>
	</c:if>
	<c:if test="${listnavigation ne 'searchcapitation'}"><!-- Room & Board -->
		<li>
			<a href="provideritem?navigation=list&providerId=<c:out value="${provider.providerId}" />" rel="tcontent4">Room & Board </a>
		</li>
	</c:if>
		
	<c:if test="${theUser.userType eq 2}">
		<c:if test="${listnavigation eq 'searchcapitation'}"><!-- Contact Person -->
		    <li>
				<a href="contactperson?navigation=listprovider&providerId=<c:out value="${provider.providerId}" />&listnavigation=<c:out value="${listnavigation}"/>" rel="tcontent5">Contact Person </a>                
			</li>
		</c:if>
		<c:if test="${listnavigation ne 'searchcapitation'}"><!-- Contact Person -->
		    <li>
				<a href="contactperson?navigation=listprovider&providerId=<c:out value="${provider.providerId}" />" rel="tcontent5">Contact Person </a>                
			</li>
		</c:if>
			
		<c:if test="${listnavigation eq 'searchcapitation'}"><!-- Bank Account -->
		    <li>
				<a href="bankaccount?navigation=listprovider&providerId=<c:out value="${provider.providerId}" />&listnavigation=<c:out value="${listnavigation}"/>" rel="tcontent6">Bank Account</a>                
			</li>
		</c:if>
		<c:if test="${listnavigation ne 'searchcapitation'}"><!-- Bank Account -->
		    <li>
				<a href="bankaccount?navigation=listprovider&providerId=<c:out value="${provider.providerId}" />" rel="tcontent6">Bank Account</a>                
			</li>
		</c:if>
		
		<c:if test="${provider.statusProspek eq 2}">
		<c:if test="${listnavigation eq 'searchcapitation'}"><!-- EDC Terminal -->
		   	<li>
				<a href="edcterminal?navigation=listprovider&providerId=<c:out value="${provider.providerId}" />&listnavigation=<c:out value="${listnavigation}"/>" rel="tcontent7">EDC Terminal</a>                
			</li>	
		</c:if>
		<c:if test="${listnavigation ne 'searchcapitation'}"><!-- EDC Terminal -->
		   	<li>
				<a href="edcterminal?navigation=listprovider&providerId=<c:out value="${provider.providerId}" />" rel="tcontent7">EDC Terminal</a>                
			</li>	
		</c:if>
		</c:if>
		
		<c:if test="${listnavigation eq 'searchcapitation'}"><!-- Procedure -->
			<li>
				<a href="providerprocedure?navigation=listprovider&providerId=<c:out value="${provider.providerId}" />&listnavigation=<c:out value="${listnavigation}"/>" rel="tcontent8">Procedure</a>                
			</li>
		</c:if>
		<c:if test="${listnavigation ne 'searchcapitation'}"><!-- Procedure -->
			<li>
				<a href="providerprocedure?navigation=listprovider&providerId=<c:out value="${provider.providerId}" />" rel="tcontent8">Procedure</a>                
			</li>
		</c:if>
		
		<c:if test="${listnavigation eq 'searchcapitation'}"><!-- Medicine -->
			<li>
				<a href="providermedicine?navigation=listprovider&providerId=<c:out value="${provider.providerId}" />&listnavigation=<c:out value="${listnavigation}"/>" rel="tcontent9">Medicine</a>                
			</li>
		</c:if>	
		<c:if test="${listnavigation ne 'searchcapitation'}"><!-- Medicine -->
			<li>
				<a href="providermedicine?navigation=listprovider&providerId=<c:out value="${provider.providerId}" />" rel="tcontent9">Medicine</a>                
			</li>
		</c:if>	
			
		<c:if test="${listnavigation eq 'searchcapitation'}"><!-- Fund -->
			<li>
				<a href="fund?navigation=listprovider&providerId=<c:out value="${provider.providerId}" />&listnavigation=<c:out value="${listnavigation}"/>" rel="tcontent9">Fund</a>                
			</li>	
		</c:if>
		<c:if test="${listnavigation ne 'searchcapitation'}"><!-- Fund -->
			<li>
				<a href="fund?navigation=listprovider&providerId=<c:out value="${provider.providerId}" />" rel="tcontent9">Fund</a>                
			</li>	
		</c:if>
		
		<c:if test="${listnavigation eq 'searchcapitation'}"><!-- Services -->
			<li>
				<a href="providerservice?navigation=listprovider&providerId=<c:out value="${provider.providerId}" />&listnavigation=<c:out value="${listnavigation}"/>" rel="tcontent9">Services</a>                
			</li>
		</c:if>
		<c:if test="${listnavigation ne 'searchcapitation'}"><!-- Services -->
			<li>
				<a href="providerservice?navigation=listprovider&providerId=<c:out value="${provider.providerId}" />" rel="tcontent9">Services</a>                
			</li>
		</c:if>
		<c:if test="${listnavigation ne 'searchcapitation'}"><!-- Services -->
			<li>
				<a href="providerhistory?navigation=listprovider&providerId=<c:out value="${provider.providerId}" />" rel="tcontent9">History</a>                
			</li>
		</c:if>
		<c:if test="${listnavigation ne 'searchcapitation'}"><!-- Services -->
			<li>
				<a href="providerrollhistory?navigation=listprovider&providerId=<c:out value="${provider.providerId}" />" rel="tcontent9">Roll Paper</a>                
			</li>
		</c:if>
	</c:if>
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
					<c:if test="${listnavigation eq 'searchcapitation'}">
					<td class="tabDetailViewDL" align="right" nowrap="nowrap">
						<a href="provider?navigation=<c:out value="${listnavigation}"/>" class="tabDetailViewDFLink" style="font-weight: normal;">
							<img src="images/back-arrow.png" title="Return to List"/>
						</a>			
					</td>
					</c:if>
					<c:if test="${listnavigation ne 'searchcapitation'}">
					<td class="tabDetailViewDL" align="right" nowrap="nowrap">
						<a href="provider?navigation=search&searchtext=<c:out value="${searchtext}"/>&searchby=<c:out value="${searchby}" />&index=<c:out value="${index}"/>" class="tabDetailViewDFLink" style="font-weight: normal;">
							<img src="images/back-arrow.png" title="Return to List"/>
						</a>			
					</td>
					</c:if>
				</tr>
			</tbody>
		</table>
	  </td>
    </tr>
	
		
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="15%">Provider Name :&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${provider.providerName}"/> (<b><c:out value="${provider.statusId.status}" /></b>)</td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Telephone :&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${provider.telephone}"/> - <c:out value="${provider.contactPerson}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Contract Number :&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${provider.contractNumber}"/></td>
	      
	    </tr>
		<tr>
		<td class="tabDetailViewDL" valign="top" width="15%">Provider Category :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${provider.providerCategoryId.providerCategoryName}"/></td>
	      
	      <td class="tabDetailViewDL" valign="top" width="15%">Faximile :&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${provider.faximile}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Contract Start Date :&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${provider.contractStartDate}"/></td>
	      
	    </tr>
	    <tr>
	      <td class="tabDetailViewDL" valign="top" width="15%">Group Name :&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:if test="${provider.providerGroupId eq null}">NONE</c:if> <c:if test="${provider.providerGroupId ne null}"><c:out value="${provider.providerGroupId.providerName}"/></c:if></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Email :&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${provider.email}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Contract End Date :&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${provider.contractEndDate}"/></td>
	      
	    </tr>
		<tr>
			<td class="tabDetailViewDL" valign="top" width="15%">Specialization :&nbsp;</td>
	      	<td class="tabDetailViewDF" valign="top" width="20%"><c:if test="${provider.providerSpecId eq null}">NONE</c:if><c:if test="${provider.providerSpecId ne null}"><c:out value="${provider.providerSpecId.poliklinikName}" /></c:if></td>
	      
	       	<td class="tabDetailViewDL" valign="top" width="15%">Website :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${provider.website}"/></td>
		  <td class="tabDetailViewDL" valign="top" width="15%">Contract Renewal Type :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:if test="${provider.renewalType eq 1}">AUTO</c:if><c:if test="${provider.renewalType eq 0}">NEW CONTRACT</c:if></td>
	    </tr>
	    <tr>
			<td class="tabDetailViewDL" valign="top" width="15%">Provider Code :&nbsp;</td>
	      	<td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${provider.providerCode}"/></td>
		  	<td class="tabDetailViewDL" valign="top" width="15%">Maps Coord. :&nbsp;</td>
	      	<td class="tabDetailViewDF" valign="top" width="20%">(<c:out value="${provider.latitude}"/>,<c:out value="${provider.longitude}"/>)</td>
	      	<td class="tabDetailViewDL" valign="top" width="15%">Status Prospek :&nbsp;</td>
	      	<td class="tabDetailViewDF" valign="top" width="20%"><c:if test="${provider.statusProspek eq 1}">NON PROVIDER </c:if>
	      														 <c:if test="${provider.statusProspek eq 2}">PROVIDER </c:if></td>
	    </tr>
	

		<tr>
	      <td class="tabDetailViewDL" valign="top" width="15%">&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%"></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="20%"></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%"></td>
	    </tr>
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="15%">Bank Account :&nbsp;</td> 			 
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${provider.bankAccount}"/></td>
		  <td class="tabDetailViewDL" valign="top" width="15%">Country :&nbsp;</td>		 	
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${provider.country}"/></td>      
		  <td class="tabDetailViewDL" valign="top" width="15%">Provider EDC Code :&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${provider.edcCode}" /></td>
	    </tr>
	
			
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="15%">Account Name :&nbsp;</td>		 
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${provider.accountName}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Province :&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${provider.province}"/></td>
		  <td class="tabDetailViewDL" valign="top" width="15%">Discount :&nbsp;</td> 
	      <td class="tabDetailViewDF" valign="top" width="20%"><b><fmt:formatNumber><c:out value="${provider.discount}"/></fmt:formatNumber></b> %</td>
	    </tr>
	
					
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="15%">Bank :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${provider.bank}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">City :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${provider.city}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Payment Periode :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${provider.paymentPeriode}" /> hari</td>

	    </tr>
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="15%">Bank Branch :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${provider.bankBranch}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Confirmation :&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:if test="${provider.confirmation eq \'Y\'}" >Confirmation</c:if><c:if test="${provider.confirmation eq \'N\'}" >No Confirmation</c:if></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Capitation Fund :&nbsp;</td> 
	      <td class="tabDetailViewDF" valign="top" width="20%"><b><fmt:formatNumber><c:out value="${provider.currentCapitationFund}"/></fmt:formatNumber></b></td>
	    </tr>
	    <tr>
	      <td class="tabDetailViewDL" valign="top" width="15%">Payment Currency :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${provider.providerCurrencyId.currencyName}" /> </td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Created Time :&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${provider.createdTime}"/>  -  <c:out value="${provider.createdBy}"/></td>	      
	      <td class="tabDetailViewDL" valign="top" width="15%">Modified Time :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${provider.modifiedTime}"/> - <c:out value="${provider.modifiedBy}"/></td>
	    </tr>

		<tr>
	      <td class="tabDetailViewDL" valign="top" width="15%">&nbsp;</td> 
	      <td class="tabDetailViewDF" valign="top" width="20%"></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="20%"></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%"></td>
	    </tr>
		<tr>
			<td class="tabDetailViewDL" valign="top" width="15%">Total Roll Paper</td>
	      	<td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${provider.rollPaperAmount}" /></td>
			<td class="tabDetailViewDL" valign="top" width="15%">Limit Roll Alert :&nbsp;</td>
	      	<td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${provider.rollPaperLimitCustomize}" /></td>
	      	<td class="tabDetailViewDL" valign="top" width="15%">Roll Paper Register / Payment :&nbsp;</td>
	      	<td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${provider.rollPaperRegisterCustomize} cm" />/<c:out value="${provider.rollPaperPaymentCustomize} cm" /></td>
		</tr>
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="15%">Address :&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="33%" colspan="5"><c:out value="${provider.address}"/></td>
	    </tr>					
	</tbody>
</table>