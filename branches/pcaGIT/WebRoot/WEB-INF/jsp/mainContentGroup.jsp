<ul id="maintab" class="shadetabs">

	<li>
		<a href="membergroup?navigation=detail&memberGroupId=<c:out value="${memberGroup.memberGroupId}" />" rel="tcontent1">Member Group</a>
	</li>
	
	<li>
		<a href="member?navigation=listgroup&memberGroupId=<c:out value="${memberGroup.memberGroupId}" />" rel="tcontent1">Member List</a>
	</li>
	
	<li>
		<a href="claim?navigation=listgroup&memberGroupId=<c:out value="${memberGroup.memberGroupId}" />" rel="tcontent4">Claim</a>
	</li>	
	<li>
		<a href="case?navigation=listgroup&memberGroupId=<c:out value="${memberGroup.memberGroupId}" />" rel="tcontent5">Case</a>
	</li>	
	<li>
		<a href="membergroupprovider?navigation=listgroup&memberGroupId=<c:out value="${memberGroup.memberGroupId}" />" rel="tcontent6">Provider</a>
	</li>	
	<li>
		<a href="costcontainment?navigation=listgroup&memberGroupId=<c:out value="${memberGroup.memberGroupId}" />" rel="tcontent6">Cost Containment</a>
	</li>
	<li>
		<a href="excesscharge?navigation=listgroup&memberGroupId=<c:out value="${memberGroup.memberGroupId}" />" rel="tcontent7">Excess Charge</a>
	</li>	
	<li>
		<a href="policy?navigation=listgroup&memberGroupId=<c:out value="${memberGroup.memberGroupId}" />" rel="tcontent7">Policy</a>
	</li>	
	
    <li >
		<a href="contactperson?navigation=listgroup&memberGroupId=<c:out value="${memberGroup.memberGroupId}" />" rel="tcontent8">Contact Person</a>
	</li>

	<li >
		<a href="bankaccount?navigation=listgroup&memberGroupId=<c:out value="${memberGroup.memberGroupId}" />" rel="tcontent9">Bank Acount</a>
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
						<a href="membergroup?navigation=search&searchtext=<c:out value="${searchtext}"/>&searchby=<c:out value="${searchby}" />&index=<c:out value="${index}"/>" class="tabDetailViewDFLink" style="font-weight: normal;">
							<img src="images/back-arrow.png" title="Return to List"/>
						</a>					
					</td>
				</tr>
			</tbody>
		</table>
	  </td>
    </tr>
	

			
	
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="15%">Group Name :&nbsp;</td>		 
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${memberGroup.groupName}"/>
	       				(<b>
	      				<c:if test="${memberGroup.status.statusId eq -1}">PENDING</c:if>
						<c:if test="${memberGroup.status.statusId eq 1}">ACTIVE</c:if>
						<c:if test="${memberGroup.status.statusId eq -3}">PENDING UPGRADE</c:if>
						<c:if test="${memberGroup.status.statusId eq -2}">BLOCKED</c:if>
						<c:if test="${memberGroup.status.statusId eq 2}">TERMINATED</c:if>
						<c:if test="${memberGroup.status.statusId eq 3}">RESIGNED</c:if>
						<c:if test="${memberGroup.status.statusId eq 4}">INACTIVE</c:if>
						<c:if test="${memberGroup.status.statusId eq 5}">INITIALIZED</c:if>
						</b>
						)
	      </td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Join Date :&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${memberGroup.joinDate}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Bank :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${memberGroup.bank}"/></td>
	    </tr>
			
			
	
			
		<tr>
		  <td class="tabDetailViewDL" valign="top" width="15%">Group Code :&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${memberGroup.memberGroupCode}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Effective Date :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${memberGroup.effectiveDate}"/></td>
		  <td class="tabDetailViewDL" valign="top" width="15%">Bank Account Name :&nbsp;</td> 
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${memberGroup.bankAccountName}"/></td>	      
	    </tr>
			
	
			
		<tr>
	     <td class="tabDetailViewDL" valign="top" width="15%">Policy Number :&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${memberGroup.policyNumber}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Renewal Date :&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${memberGroup.renewalDate}"/></td>
		  <td class="tabDetailViewDL" valign="top" width="15%">Bank Account :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${memberGroup.bankAccount}"/></td>	      
	    </tr>
			
	
			
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="15%">Business Category :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${memberGroup.businessCategoryId.businessCategoryName}" /></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Resigned Date :&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${memberGroup.resignedDate}"/></td>
		  <td class="tabDetailViewDL" valign="top" width="15%">Bank Branch :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${memberGroup.bankBranch}"/></td>    
	    </tr>
			
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="15%">Client :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${memberGroup.clientId.clientName}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Expire Date :&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${memberGroup.expireDate}"/></td>
		  <td class="tabDetailViewDL" valign="top" width="15%">&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="20%"></td>
	      
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
	      <td class="tabDetailViewDL" valign="top" width="15%">City :&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${memberGroup.city}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Telephone :&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${memberGroup.telephone}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="20%"></td>	      
	    </tr>
	
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="15%">Province :&nbsp;</td>		 
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${memberGroup.province}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Faximile :&nbsp;</td>		 
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${memberGroup.faximile}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="20%"></td>
	    </tr>
	    
	    
	    
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="15%">Country :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${memberGroup.country}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Website :&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${memberGroup.website}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="20%"></td>	      
	    </tr>		
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="15%">Postal Code :&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${memberGroup.postalCode}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Email :&nbsp;</td>		 
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${memberGroup.email}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="20%"></td>
	      
	    </tr>
				
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="15%">Created Time :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${memberGroup.createdTime}"/>  -   <c:out value="${memberGroup.createdBy}"/></td>
  	      <td class="tabDetailViewDL" valign="top" width="15%">Modified Time :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${memberGroup.modifiedTime}"/>  -  <c:out value="${memberGroup.modifiedBy}"/></td>
		  <td class="tabDetailViewDL" valign="top" width="15%">&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="20%"></td>
	    </tr>
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="15%">Address :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" colspan="5" valign="top" width="20%"><c:out value="${memberGroup.address}"/></td>
	      
	    </tr>
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="15%">Suspend Reason :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" colspan="5" valign="top" width="20%">
	      	<c:forTokens items="${memberGroup.suspendReason}" var="reason" delims="|">
        		<c:out value="${reason}" /> <br /><br />
        	</c:forTokens>
        </td>
	      
	    </tr>
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="15%">Unsuspend Reason :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" colspan="5" valign="top" width="20%">
	      	<c:forTokens items="${memberGroup.unsuspendReason}" var="reason" delims="|">
        		<c:out value="${reason}" /> <br /><br />
        	</c:forTokens>
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
<!-- 						<a href="membergroup?navigation=search&searchtext=<c:out value="${searchtext}"/>&searchby=<c:out value="${searchby}" />&index=<c:out value="${index}"/>" class="tabDetailViewDFLink" style="font-weight: normal;">Return to List&nbsp;</a>					 -->
<!-- 					</td> -->
				</tr>
			</tbody>
		</table>
	  </td>
    </tr>
				
	</tbody>
</table>


