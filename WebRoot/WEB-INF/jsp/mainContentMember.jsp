
<c:set var="now" value="<%=new java.util.Date()%>"/>
<ul id="maintab" class="shadetabs">

	<li class="selected">
		<a href="member?navigation=detail&memberId=<c:out value="${member.memberId}" />" rel="tcontent1">Current Benefit</a>
	</li>
	<li>
		<a href="member?navigation=listdependant&memberId=<c:out value="${member.memberId}" />" rel="tcontent2">Dependant</a>
	</li>
	
	<c:if test="${theUser.userType eq 2}">
	<li>
		<a href="memberproduct?navigation=list&memberId=<c:out value="${member.memberId}" />" rel="tcontent3">Product History</a>
	</li>
	<li>
		<a href="memberbenefit?navigation=listmember&memberId=<c:out value="${member.memberId}" />" rel="tcontent3">Benefit</a>
	</li>
	<li>
		<a href="memberclausul?navigation=listmember&memberId=<c:out value="${member.memberId}" />" rel="tcontent3">Clausul</a>
	</li>	
	</c:if>
	<c:if test="${theUser.userType eq 1}">
	
		<li>
			<a href="memberbenefit?navigation=listmember&memberId=<c:out value="${member.memberId}" />" rel="tcontent3">Benefit</a>
		</li>
		<li>
			<a href="memberclausul?navigation=listmember&memberId=<c:out value="${member.memberId}" />" rel="tcontent3">Clausul</a>
		</li>
	</c:if>
	<li>
		<a href="claim?navigation=listmember&memberId=<c:out value="${member.memberId}" />" rel="tcontent4">Claim</a>
	</li>	
	<li>
		<a href="case?navigation=listmember&memberId=<c:out value="${member.memberId}" />" rel="tcontent5">Case</a>
	</li>	
	<li>
		<a href="provider?navigation=listmember&memberId=<c:out value="${member.memberId}" />" rel="tcontent6">Provider</a>
	</li>	
	<li>
		<a href="excesscharge?navigation=list&memberId=<c:out value="${member.memberId}" />" rel="tcontent7">Excess</a>
	</li>
	<li>
		<a href="memberelectroniccard?navigation=list&memberId=<c:out value="${member.memberId}" />" rel="tcontent8">Member Card</a>
	</li>		
	<li >
		<a href="memberdiagnosisexclusion?navigation=list&memberId=<c:out value="${member.memberId}" />" rel="tcontent8">Diagnosis Excl.</a>
	</li>
	<li >
		<a href="policydiagnosisexclusion?navigation=listmember&memberId=<c:out value="${member.memberId}" />" rel="tcontent8">Policy Diag. Excl.</a>
	</li>
	<li >
		<a href="memberimport?navigation=listmember&memberId=<c:out value="${member.memberId}" />" rel="tcontent8">Movement History</a>
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
						<a href="member?navigation=search&searchtext=<c:out value="${searchtext}"/>&searchby=<c:out value="${searchby}" />&index=<c:out value="${index}"/>" class="tabDetailViewDFLink" style="font-weight: normal;">
							<img src="images/back-arrow.png" title="Return to List"/>
						</a>
					</td>
				</tr>
			</tbody>
		</table>
	  </td>
    </tr>
	
			
		<tr>
	      	<td class="tabDetailViewDL" valign="top" width="15%">Customer Number :&nbsp;</td>
	     	<td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${member.customerPolicyNumber}"/></td>	      	      
			<td class="tabDetailViewDL" valign="top" width="15%">Client :&nbsp;</td>
	      	<td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${member.clientName}"/></td>
	      	<td class="tabDetailViewDL" valign="top" width="15%">Bank :&nbsp;</td>
	      	<td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${member.bank}"/> - <c:out value="${member.bankBranch}" /></td>
	    </tr>
	
			
		<tr>
		  <td class="tabDetailViewDL" valign="top" width="15%">Customer Name :&nbsp;</td>  
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${member.firstName}"/>&nbsp;<c:out value="${member.lastName}"/> - <b>[<c:if test="${member.gender eq 'L' or member.gender eq 'M'}">MALE</c:if><c:if test="${member.gender eq 'P' or member.gender eq 'F'}">FEMALE</c:if>]</b></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Member Group :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${member.groupName}"/>  - <c:if test="${member.isVIP eq 1}"><b>VIP MEMBER</b></c:if></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Bank Account :&nbsp;</td>  
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${member.bankAccount}"/></td>
	    </tr>    

		<tr>	      
			 <td class="tabDetailViewDL" valign="top" width="15%">Current Card Number :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${member.currentCardNumber}" /></td>
	     	 <td class="tabDetailViewDL" valign="top" width="15%">Status :&nbsp;</td>		  
			  <td class="tabDetailViewDF" valign="top" width="20%">
			  	<b>
			  	<c:if test="${member.status eq -1}">PENDING</c:if>
				<c:if test="${member.status eq 1}">ACTIVE</c:if>
				<c:if test="${member.status eq -3}">PENDING CHANGEPLAN</c:if>
				<c:if test="${member.status eq -2}">BLOCKED</c:if>
				<c:if test="${member.status eq 2}">
					<c:if test="${now < member.resignedDate}">
						ACTIVE
					</c:if>
					<c:if test="${now >= member.resignedDate}">
						TERMINATED
					</c:if>
				</c:if>
				<c:if test="${member.status eq 3}">RESIGNED</c:if>
				<c:if test="${member.status eq 4}">INACTIVE</c:if>
				<c:if test="${member.status eq 5}">INITIALIZED</c:if>
				<c:if test="${member.status eq 7}">GRACE</c:if>
				
				<c:if test="${member.status eq 8}">EXTEND ACTIVE</c:if>
				<c:if test="${member.status eq -4}">PENDING RENEW</c:if>
				<c:if test="${member.status eq 10}">ADVANCED RENEW</c:if>
				<c:if test="${member.status eq -6}">EXPIRED</c:if>
				
				</b>
				
				<c:if test="${member.status eq -2 or member.status eq 7}"> <c:out value="${member.suspendStartDate}" /> s/d <c:out value="${member.suspendEndDate}" /></c:if>
			  </td>	
	      	
	      <td class="tabDetailViewDL" valign="top" width="15%">Bank Account Name :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${member.bankAccountName}"/></td>
	    </tr>
	    <tr>	      
			<td class="tabDetailViewDL" valign="top" width="15%">Other Number :&nbsp;</td>		  
			<td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${member.otherMemberNumber}"/></td>
	     	<td class="tabDetailViewDL" valign="top" width="15%">Current Policy Number :&nbsp;</td>		  
			<td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${member.currentPolicyNumber}"/></td>
	      	
			<td class="tabDetailViewDL" valign="top" width="15%">Salary :&nbsp;</td>
	      	<td class="tabDetailViewDF" valign="top" width="20%"><fmt:formatNumber><c:out value="${member.currentSalary}" /></fmt:formatNumber></td>   
	    </tr>
	    <tr>	      
			<td class="tabDetailViewDL" valign="top" width="15%">Annual Limit :&nbsp;</td>		  
			<td class="tabDetailViewDF" valign="top" width="20%"><fmt:formatNumber><c:out value="${member.customerLimit}"/></fmt:formatNumber></td>
	     	<td class="tabDetailViewDL" valign="top" width="15%">Actual Benefit Limit :&nbsp;</td>		  
			<td class="tabDetailViewDF" valign="top" width="20%"><fmt:formatNumber><c:out value="${member.actualCustomerLimit}"/></fmt:formatNumber></td>
	     
	     <c:choose>
	     <c:when test="${(theUser.userType eq 2 && theUser.roleId.roleId eq 0) or (theUser.userType eq 2 && theUser.roleId.roleId eq 22) or(theUser.userType eq 2  && theUser.roleId.roleId eq 8) }">
			<td class="tabDetailViewDL" valign="top" width="15%">ASO Fund :&nbsp;</td>
	      	<td class="tabDetailViewDF" valign="top" width="20%"><fmt:formatNumber><c:out value="${member.currentPolicyId.currentPolicyFund}" /></fmt:formatNumber></td>  
	     </c:when>
	     <c:otherwise>
	     	<td class="tabDetailViewDL" valign="top" width="15%"></td>
	      	<td class="tabDetailViewDF" valign="top" width="20%"></td>
	     </c:otherwise>
	     </c:choose>
	      
	    </tr>
	    <tr>	      
			<td class="tabDetailViewDL" valign="top" width="15%"></td>		  
			<td class="tabDetailViewDF" valign="top" width="20%"></td>
	     	<td class="tabDetailViewDL" valign="top" width="15%">&nbsp;</td>
	      	<td class="tabDetailViewDF" valign="top" width="20%"></td>
	      	
			<td class="tabDetailViewDL" valign="top" width="15%">&nbsp;</td>
	      	<td class="tabDetailViewDF" valign="top" width="20%"></td>   
	    </tr>
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="15%">Date of Birth :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${member.birthplace}"/>, <c:out value="${member.birthday}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Join Date :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${member.joinDate}"/></td>
	      
			<td class="tabDetailViewDL" valign="top" width="15%">Email :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${member.email}"/></td>

	    </tr>
	    
	    <tr>
	     <td class="tabDetailViewDL" valign="top" width="15%">Age :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${age}"/></td>
		  <td class="tabDetailViewDL" valign="top" width="15%">&nbsp;</td>
	      	<td class="tabDetailViewDF" valign="top" width="20%"></td>  
	      	<td class="tabDetailViewDL" valign="top" width="15%">&nbsp;</td>
	      	<td class="tabDetailViewDF" valign="top" width="20%"></td>  
	</tr>
			
		<tr>
	       	<td class="tabDetailViewDL" valign="top" width="15%">Policy Holder :&nbsp;</td>
		    <td class="tabDetailViewDF" valign="top" width="20%"><a href="member?navigation=detail&memberId=<c:out value="${member.parentMember.memberId}" />" class="listViewTdLinkS1"><c:out value="${member.parentName}"/></a></td>
	      	<td class="tabDetailViewDL" valign="top" width="15%">Effective Date :&nbsp;</td>
	      	<td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${member.effectiveDate}"/> s/d <c:out value="${member.expireDate}"/></td>
	      
			<td class="tabDetailViewDL" valign="top" width="15%">Telephone :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${member.telephone}"/></td>	  

	    </tr>
	
		<tr>
	      	<td class="tabDetailViewDL" valign="top" width="15%">Department :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${member.department}"/></td>
	     	<td class="tabDetailViewDL" valign="top" width="15%">Resigned Date :&nbsp;</td>
	      	<td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${member.resignedDate}"/></td>
	      	
			 <td class="tabDetailViewDL" valign="top" width="15%">Faximile :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${member.faximile}"/></td>	 

	    </tr>
			
			
		
		<tr>
	       <td class="tabDetailViewDL" valign="top" width="15%">Job Position :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${member.jobPosition}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Renewal Date :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${member.renewalDate}"/></td>
	      
			 <td class="tabDetailViewDL" valign="top" width="15%">Mobile Phone :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${member.mobilePhone}"/></td>
	    </tr>
		
		<tr>
	       <td class="tabDetailViewDL" valign="top" width="15%">CoB Number :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${member.otherMemberNumber}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Linked Card Number :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%">
	      <!-- modified by aju on 20150803, for linkedcard COB :D 
	      	<c:out value="${member.linkedCardNumber}"/>
	      	-->
	      	<c:if test="${member.linkedCardNumber ne null}">
	      		<a class="listViewTdLinkS1" href="javascript:popupMember(<c:out value="${MemberLinkedCard.memberId.memberId}" />)"><c:out value="${member.linkedCardNumber}" /></a>
	      	</c:if>
	      	</td>	      
			<td class="tabDetailViewDL" valign="top" width="15%">Sub Policy Number :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${member.subPolicyNumber}"/></td>
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
	      	<td class="tabDetailViewDL" valign="top" width="15%">Country :&nbsp;</td>
	      	<td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${member.country}"/></td>
	     	<td class="tabDetailViewDL" valign="top" width="15%">Province :&nbsp;</td>
	      	<td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${member.province}"/></td>	      
			<td class="tabDetailViewDL" valign="top" width="15%">City :&nbsp;</td>
	      	<td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${member.city}"/></td>
	    </tr>
	
		<tr>
	      	<td class="tabDetailViewDL" valign="top" width="15%">Postal Code :&nbsp;</td>	 
	      	<td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${member.postalCode}"/></td>    
	     	<td class="tabDetailViewDL" valign="top" width="15%">Address :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%" colspan="5"><c:out value="${member.address}"/></td>   
	    </tr>
		
	
	
		</tbody>
		
</table>

