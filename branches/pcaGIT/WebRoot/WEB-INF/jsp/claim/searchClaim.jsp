<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<%
String navigation = WebUtil.getAttributeString(request,"navigation","");
%>

 <!-- Page Title Start // Should be put on <Title> tag-->

<!-- Page Title Stop-->
<%
String alert = (String) request.getAttribute("alert");
int index = 0;
int totalIndex = 0;
int count = 0;
int countSet = 0;

try {
	index = ((Integer) request.getAttribute ("index")).intValue();
	count = ((Integer) request.getAttribute ("count")).intValue();
	countSet = ((Integer) request.getAttribute ("countSet")).intValue();
	totalIndex = ((Integer) request.getAttribute ("halAkhir")).intValue();
	
	
	
}
catch (Exception e){
}

if (alert != null && !alert.trim().equals("")) {%>
	<div id="warning" align="center">
		<c:out value="${alert}"></c:out>
	</div>
	<%}%>

<%
String rowclass = "";
int i=0;
int indexint = Integer.parseInt(request.getAttribute("index").toString());
WebUtil.getAttributeInteger(request,"index",0).intValue();
%>
<!-- Search Container Start -->

<form name="form1" action="claim" method="POST">
<input type="hidden" name="navigation" value="gosearch">
<input type="hidden" name="sortcolumn" value="<c:out value="${sortcolumn}" />">
<input type="hidden" name="sortorder" value="<c:out value="${sortorder}" />">
<input type="hidden" name="columntosort" value="<c:out value="${columntosort}" />">
<input type="hidden" name="arah" value="">
<input type="hidden" name="index" value="<c:out value="${index}" />">
<input type="hidden" name="claimId" value="<c:out value="${claim.claimId}" />">
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
    	<c:choose>
			<c:when test="${theUser.userType eq 4}">
				<td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Search Claim History</h3></td>
			</c:when>
			<c:otherwise>
				<td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Search Claim</h3></td>
			</c:otherwise>
		</c:choose>    
      	<td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>
<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td><table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tbody>
          <tr>
            <form>
            </form>
            <td class="dataLabel" nowrap="nowrap">Search Keyword:
              &nbsp;&nbsp;
              
				<input size="20" name="searchtext" class="dataField" value="<c:out value="${searchtext}"/>" type="text">
              </td>
            <td class="dataLabel" nowrap="nowrap">Search Category:
              &nbsp;&nbsp;
              
                
								 <select name="searchby" class="inputbox">
								 <c:if test="${theUser.userType eq 2 or theUser.userType eq 7}">
 	   		   			<option value="claimNumber" <c:if test="${searchby eq \"claimNumber\"}">selected="true"</c:if> >Claim Number</option>
							   	  		   			
			   	  		   			<option value="providerName" <c:if test="${searchby eq \"providerName\"}">selected="true"</c:if> >Provider Name</option>
			   	  		   			<option value="clientName" <c:if test="${searchby eq \"clientName\"}">selected="true"</c:if> >Client Name</option>
			   	  		   			<option value="memberName" <c:if test="${searchby eq \"memberName\"}">selected="true"</c:if> >Member Name</option>
			   	  		   			<option value="memberGroupName" <c:if test="${searchby eq \"memberGroupName\"}">selected="true"</c:if> >Member Group Name</option>
			   	  		   			<option value="policyNumber" <c:if test="${searchby eq \"policyNumber\"}">selected="true"</c:if> >Member Number</option>
<!-- 									<option value="receivedDate" <c:if test="${searchby eq \"receivedDate\"}">selected="true"</c:if> >Received Date</option>			   	  		   			 -->
			   	  		   			<option value="paymentNumber" <c:if test="${searchby eq \"paymentNumber\"}">selected="true"</c:if> >Payment Number</option>
			   	
			   	  		   			<option value="officer" <c:if test="${searchby eq \"officer\"}">selected="true"</c:if> >Officer</option>
			   	  		   	</c:if>
			   	  		   	<c:if test="${theUser.userType eq 4}">
			   	  		   			<option value="invoiceNumber" <c:if test="${searchby eq \"invoiceNumber\"}">selected="true"</c:if> >Invoice Number</option>
			   	  		   		
			   	  		   	</c:if>
			   	   			<c:if test="${theUser.userType eq 1 or theUser.userType eq 3}">
			   	  		   			<option value="providerName" <c:if test="${searchby eq \"providerName\"}">selected="true"</c:if> >Provider Name</option>
			   	  		   			<option value="clientName" <c:if test="${searchby eq \"clientName\"}">selected="true"</c:if> >Client Name</option>
			   	  		   			<option value="memberName" <c:if test="${searchby eq \"memberName\"}">selected="true"</c:if> >Member Name</option>
			   	  		   			<option value="memberGroupName" <c:if test="${searchby eq \"memberGroupName\"}">selected="true"</c:if> >Member Group Name</option>
			   	  		   			<option value="policyNumber" <c:if test="${searchby eq \"policyNumber\"}">selected="true"</c:if> >Member Number</option>
<!-- 									<option value="receivedDate" <c:if test="${searchby eq \"receivedDate\"}">selected="true"</c:if> >Received Date</option>			   	  		   			 -->
			   	  		   			<option value="paymentNumber" <c:if test="${searchby eq \"paymentNumber\"}">selected="true"</c:if> >Payment Number</option>
			   	
			   	  		   			<option value="officer" <c:if test="${searchby eq \"officer\"}">selected="true"</c:if> >Officer</option>
			   	  		   	</c:if>
			   	  		   	<c:if test="${theUser.userType eq 5}">
			   	  		   			<option value="memberName" <c:if test="${searchby eq \"memberName\"}">selected="true"</c:if> >Member Name</option>
			   	  		   			<option value="policyNumber" <c:if test="${searchby eq \"policyNumber\"}">selected="true"</c:if> >Member Number</option>
			   	  		   			
			   	  		   			<option value="customerNumber" <c:if test="${searchby eq \"customerNumber\"}">selected="true"</c:if> >Customer Number</option>
			   	  		   	</c:if>
			   </select>
				
              </td>
			  <td class="dataLabel" nowrap="nowrap">Status:
              &nbsp;&nbsp;
              
                <select name="status">					
					<option value="">--- All Status ---</option>
					<option value="-1" <c:if test="${status eq -1 }">selected</c:if> >VOID</option>
					<option value="1" <c:if test="${status eq 1 }">selected</c:if> >OPEN</option>					
					<option value="3" <c:if test="${status eq 3 }">selected</c:if>>VERIFIED</option>
					<option value="9" <c:if test="${status eq 9 }">selected</c:if>>APPROVED</option>
					<option value="8" <c:if test="${status eq 8 }">selected</c:if>>CHECKED</option>
					<option value="6" <c:if test="${status eq 6 }">selected</c:if>>PAID</option>
					<option value="4" <c:if test="${status eq 4 }">selected</c:if>>REJECTED</option>
					<option value="10" <c:if test="${status eq 10 }">selected</c:if>>PENDING</option>
					<option value="13" <c:if test="${status eq 13 }">selected</c:if>>CDV ISSUED</option>
			
				</select>
				
              </td>
            <td class="dataLabel">
              <input title="Search [Alt+Shift+Q]" accesskey="Q" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" name="button" value="Search" type="submit">
             </td>
            </tr>
			<tr>
            <td class="dataLabel" nowrap="nowrap">Date Category:
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              
                
				<select name="dateby" class="inputbox">

					<option value="">-- ALL CATEGORY --</option>
					
					<c:if test="${(theUser.userType eq 1 ) or (theUser.userType eq 5) or (theUser.userType eq 3)}">
						<option value="admissionDate" <c:if test="${dateby eq \"admissionDate\"}">selected="true"</c:if> >Admission Date</option>
						<option value="invoiceDate" <c:if test="${dateby eq \"invoiceDate\"}">selected="true"</c:if> >Invoice Date</option>
						<option value="claimDate" <c:if test="${dateby eq \"claimDate\"}">selected="true"</c:if> >Received Date</option>
						
						
					</c:if>
					
					<c:if test="${theUser.userType eq 2}">
						<option value="admissionDate" <c:if test="${dateby eq \"admissionDate\"}">selected="true"</c:if> >Admission Date</option>
						<option value="claimDate" <c:if test="${dateby eq \"claimDate\"}">selected="true"</c:if> >Received Date</option>
						<option value="claimClosedDate" <c:if test="${dateby eq \"claimClosedDate\"}">selected="true"</c:if> >Closed Date</option>
				   	  	<option value="paidTime" <c:if test="${dateby eq \"paidTime\"}">selected="true"</c:if> >Payment Date</option>
				   	  	<option value="verifiedTime" <c:if test="${dateby eq \"verifiedTime\"}">selected="true"</c:if> >Verification Date</option>
				   	  	<option value="approvedTime" <c:if test="${dateby eq \"approvedTime\"}">selected="true"</c:if> >Approval Date</option>
				   	  	<option value="createdTime" <c:if test="${dateby eq \"createdTime\"}">selected="true"</c:if> >Create Date</option>
				   	  	<option value="modifiedTime" <c:if test="${dateby eq \"modifiedTime\"}">selected="true"</c:if> >Modification Date</option>
				   	  	
				   	  	<option value="effectiveDate" <c:if test="${dateby eq \"effectiveDate\"}">selected="true"</c:if> > Effective Date</option>
				   	  	
				   	  	<option value="createdTime" <c:if test="${dateby eq \"createdTime\"}">selected="true"</c:if> >Join Date</option>
				   	  	<option value="birthday" <c:if test="${dateby eq \"birthday\"}">selected="true"</c:if> >Date Of Birth</option>
			   	  	</c:if>
			   	
			   </select>
				
              </td>
            
            <td class="dataLabel" nowrap="nowrap"> Minimum Date :
              &nbsp;&nbsp;&nbsp;&nbsp;
              
                <input name="minimum_date" id="jscal_field" tab="1" maxlength="10" size="11" readonly="readonly" value="<c:out value="${minimumDate}" />" type="text">
				<img src="images/jscalendar.gif" alt="Enter Date" id="jscal_trigger" align="absmiddle" height="18" width="18">
				
				<script type="text/javascript">
    					Calendar.setup({
        					inputField     :    "jscal_field",     // id of the input field
        					ifFormat       :    "%Y-%m-%d",      // format of the input field
        					button         :    "jscal_trigger",  // trigger for the calendar (button ID)
        					align          :    "Bl",//"Tl",           // alignment (defaults to "Bl")
        					singleClick    :    true
    					});
				 	</script>
              
			</td>
            <td class="dataLabel" nowrap="nowrap"> Maximum Date :
              &nbsp;&nbsp;&nbsp;&nbsp;
              
            <input name="maximum_date" id="jscal_field_max" tabindex="1" maxlength="10" size="11" readonly="readonly" value="<c:out value="${maximumDate}" />" type="text">
			<img src="images/jscalendar.gif" alt="Enter Date" id="jscal_max_trigger" align="absmiddle" height="18" width="18">
			
					<script type="text/javascript">
    					Calendar.setup({
        					inputField     :    "jscal_field_max",     // id of the input field
        					ifFormat       :    "%Y-%m-%d",      // format of the input field
        					button         :    "jscal_max_trigger",  // trigger for the calendar (button ID)
        					align          :    "Bl",//"Tl",           // alignment (defaults to "Bl")
        					singleClick    :    true
    					});
				 	</script>
			</td>
            <td class="dataLabel">&nbsp;&nbsp;
              </td>
            <td align="right">
            </td>
          </tr>
        </tbody>
      </table></td>
    </tr>
  </tbody>
</table>


<!-- Search Container Stop -->

	
 
<br />

 	
<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	<tr>
      <td colspan="20" align="right"><table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tbody>
          <tr>
            	<td class="listViewPaginationTdS1" align="left"></td>
            	<td class="listViewPaginationTdS1" align="right" nowrap="nowrap" >
				
				<%
				if (index == 1){
				%>
				<img src="images/start_off.gif" alt="Start" align="absmiddle" border="0" height="10" width="9">
					Start&nbsp;
									
				<img src="images/previous_off.gif" alt="Previous" align="absmiddle" border="0" height="10" width="4">
					Previous&nbsp;&nbsp;					
				<%
				}
				else if ((index-1) > 0){
				
				%>
				<img src="images/start.gif" alt="Start" align="absmiddle" border="0" height="10" width="9">
					<a href="javascript:goleftbgt()" class="listViewPaginationLinkS1">
						Start&nbsp;
					</a>				
				
				<img src="images/previous.gif" alt="Previous" align="absmiddle" border="0" height="10" width="4">
					<a href="javascript:goleft()" class="listViewPaginationLinkS1">
					Previous&nbsp;&nbsp;
					</a>
				<%
				}
				%>
					<span class="pageNumbers">(<c:out value="${minIndex}" /> - <c:out value="${maxIndex}" /> of <c:out value="${count}" />)</span>&nbsp;&nbsp;
				
				<%
				if (totalIndex > index){
				%>
				
				<a href="javascript:goright()" class="listViewPaginationLinkS1">Next&nbsp;
				<img src="images/next.gif" alt="Next" align="absmiddle" border="0" height="10" width="4"></a>&nbsp;&nbsp;
				<a href="javascript:gorightbgt()" class="listViewPaginationLinkS1">End&nbsp;
					<img src="images/end.gif" alt="End" align="absmiddle" border="0" height="10" width="9"></a>
				<%
				} else {
				
				%>
				Next&nbsp;
				<img src="images/next_off.gif" alt="Next" align="absmiddle" border="0" height="10" width="4">
				&nbsp;&nbsp;
				End&nbsp;
					<img src="images/end_off.gif" alt="End" align="absmiddle" border="0" height="10" width="9">
				<%
				}
				%>
			</td>
          </tr>
        </tbody>
      </table></td>
    </tr>


	<tr height="20">
		<td width="2%" nowrap="nowrap" class="listViewThS1" scope="col">No.</td>		
		
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Nomor Klaim&nbsp;
			<input type="hidden" name="sortOrderClaimNo" value="<c:out value="${sortClaimNo?'true':'false'}" />">
			<a href="javascript:carisort('claimnumber')" class="listViewThLinkS1">
			<img src="images/arrow_<c:out value="${sortClaimNo?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
		</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Nama Peserta&nbsp;
			<input type="hidden" name="sortOrderMemberName" value="<c:out value="${sortMember?'true':'false'}" />">
			<a href="javascript:carisort('membername')" class="listViewThLinkS1">
			<img src="images/arrow_<c:out value="${sortMember?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
		</td> 		   		   		   		   		   			
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Card Number&nbsp;
			<input type="hidden" name="sortOrderCardNo" value="<c:out value="${sortCardNo?'true':'false'}" />">
			<a href="javascript:carisort('cardnumber')" class="listViewThLinkS1">
			<img src="images/arrow_<c:out value="${sortCardNo?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
		</td>
		<td scope="col" class="listViewThS1" width="15%">Nama Provider&nbsp;
			<input type="hidden" name="sortOrderProvider" value="<c:out value="${sortProvider?'true':'false'}" />">
			<a href="javascript:carisort('providername')" class="listViewThLinkS1">
			<img src="images/arrow_<c:out value="${sortProvider?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
		</td>	
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Tgl. Berobat &nbsp;
			<input type="hidden" name="sortOrderAdmission" value="<c:out value="${sortAdmissionDate?'true':'false'}" />">
			<a href="javascript:carisort('admissiondate')" class="listViewThLinkS1">
			<img src="images/arrow_<c:out value="${sortAdmissionDate?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
		</td>	
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Tgl. Diterima &nbsp;
			<input type="hidden" name="sortOrderClaim" value="<c:out value="${sortClaimDate?'true':'false'}" />">
			<a href="javascript:carisort('claimdate')" class="listViewThLinkS1">
			<img src="images/arrow_<c:out value="${sortClaimDate?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
		</td>	
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Claim Diajukan &nbsp;
			<input type="hidden" name="sortOrderCharge" value="<c:out value="${sortClaimCharge?'true':'false'}" />">
			<a href="javascript:carisort('claimcharge')" class="listViewThLinkS1">
			<img src="images/arrow_<c:out value="${sortClaimCharge?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
		</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Claim Disetujui &nbsp;
			<input type="hidden" name="sortOrderApprove" value="<c:out value="${sortClaimApprove?'true':'false'}" />">
			<a href="javascript:carisort('claimapprove')" class="listViewThLinkS1">
			<img src="images/arrow_<c:out value="${sortClaimApprove?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
		</td>	
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Tgl. Bayar &nbsp;
			<input type="hidden" name="sortOrderConfirmDate" value="<c:out value="${sortConfirmDate?'true':'false'}" />">
			<a href="javascript:carisort('paymentconfirm')" class="listViewThLinkS1">
			<img src="images/arrow_<c:out value="${sortConfirmDate?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
		</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="7%">Status &nbsp;
			<input type="hidden" name="sortOrderStatus" value="<c:out value="${sortStatus?'true':'false'}" />">
			<a href="javascript:carisort('status')" class="listViewThLinkS1">
			<img src="images/arrow_<c:out value="${sortStatus?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
		</td>	
	</tr>
	<tr height="20">
		<td width="2%" nowrap="nowrap" class="listViewThS1" scope="col"></td>				   		   		   			
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%"></td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%"></td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%"></td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%"></td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%"></td>		
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%"></td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%" align="right" style="text-align: right; ">
			&nbsp;<fmt:formatNumber><c:out value="${totalClaimValue}" /></fmt:formatNumber> 
		</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%" align="right" style="text-align: right;">&nbsp;<fmt:formatNumber><c:out value="${totalClaimApprovedValue}" /></fmt:formatNumber></td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%"></td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="7%">&nbsp;</td>
	</tr>

	<c:forEach items="${Claims}" var="claim" varStatus="status" >
		<%	if (i % 2 == 0) {
				rowclass = "col1";
			} else if (i % 2 != 0) {
				rowclass= "col2";
			}
			i++;
		%>
		 <tr height="20">
	      	<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><%=(i+((indexint-1)*countSet))%></td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<a href="claim?navigation=detail&claimId=<c:out value="${claim.claimId}" />" class="linkDetail"><c:out value="${claim.claimNumber}" /></a>			
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">	
				<!-- modified by aju on 20150925, as Pak Firman request, for Provider User -->
				<%-- 		
				<a href="member?navigation=detail&memberId=<c:out value="${claim.memberId.memberId}" />" class="linkDetail"><c:out value="${claim.memberId.firstName}" /> <c:out value="${claim.memberId.lastName}" /></a>
				 --%>
				<c:if test="${theUser.userType eq 4}">
					<a href="#" class="linkDetail"><c:out value="${claim.memberId.firstName}" /> <c:out value="${claim.memberId.lastName}" /></a>
				</c:if>			
				<c:if test="${theUser.userType ne 4}">
					<a href="member?navigation=detail&memberId=<c:out value="${claim.memberId.memberId}" />" class="linkDetail"><c:out value="${claim.memberId.firstName}" /> <c:out value="${claim.memberId.lastName}" /></a>
				</c:if>	
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<c:out value="${claim.cardNumber}" />
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe"  valign="top">
				<c:if test="${claim.providerName != null}">
					<c:out value="${claim.providerName}" />
				</c:if>
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
				<c:out value="${claim.admissionDate}" />			
			</td>				   		   	   		   		   		   		
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<c:out value="${claim.claimDate}" />			
			</td>		
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">			
				<fmt:formatNumber><c:out value="${claim.claimChargeValue}" /></fmt:formatNumber>			
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
				<fmt:formatNumber><c:out value="${claim.claimApprovedValue}" /></fmt:formatNumber>			
			</td>		   		   		   		   		
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">			
				<c:out value="${claim.paymentConfirmDate}" />			
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<c:if test="${claim.claimStatus.caseStatusId eq -1}">VOID</c:if>
				<c:if test="${claim.claimStatus.caseStatusId eq 1}">OPEN</c:if>
				<c:if test="${claim.claimStatus.caseStatusId eq 2}">PENDING DOCUMENT</c:if>
				<c:if test="${claim.claimStatus.caseStatusId eq 3}">VERIFIED</c:if>
				<c:if test="${claim.claimStatus.caseStatusId eq 4}">REJECTED</c:if>
				<c:if test="${claim.claimStatus.caseStatusId eq 5}">CLOSED</c:if>
				<c:if test="${claim.claimStatus.caseStatusId eq 6}">PAID</c:if>
				<c:if test="${claim.claimStatus.caseStatusId eq 7}">PENDING INVESTIGATION</c:if>
				<c:if test="${claim.claimStatus.caseStatusId eq 8}">CHECKED</c:if>
				<c:if test="${claim.claimStatus.caseStatusId eq 9}">APPROVED</c:if>
				<c:if test="${claim.claimStatus.caseStatusId eq 10}">PENDING</c:if>
				<c:if test="${claim.claimStatus.caseStatusId eq 11}">PROCESS OK</c:if>
				<c:if test="${claim.claimStatus.caseStatusId eq 12}">COMPLETE</c:if>
				<c:if test="${claim.claimStatus.caseStatusId eq 13}">CDV ISSUED</c:if>
				<c:if test="${claim.claimStatus.caseStatusId eq 14}">UNREGISTERED</c:if>
				<c:if test="${claim.claimStatus.caseStatusId eq 15}">FINALIZED</c:if>
				<c:if test="${claim.claimStatus.caseStatusId eq 16}">INSTALLMENT PAYMENT</c:if>	
				<c:if test="${claim.claimStatus.caseStatusId eq 19}">PRE-OPEN</c:if>		
							
			</td>		
	    </tr>   
		<tr>
	      <td colspan="20" class="listViewHRS1"></td>
	    </tr>	
	</c:forEach>	
		<tr>
            <td class="listViewPaginationTdS1" align="left"></td>
            	<td class="listViewPaginationTdS1" align="right" nowrap="nowrap" colspan=20>
				
				<%
				if (index == 1){
				%>
				<img src="images/start_off.gif" alt="Start" align="absmiddle" border="0" height="10" width="9">
					Start&nbsp;
									
				<img src="images/previous_off.gif" alt="Previous" align="absmiddle" border="0" height="10" width="4">
					Previous&nbsp;&nbsp;					
				<%
				}
				else if ((index-1) > 0){
				
				%>
				<img src="images/start.gif" alt="Start" align="absmiddle" border="0" height="10" width="9">
					<a href="javascript:goleftbgt()" class="listViewPaginationLinkS1">
						Start&nbsp;
					</a>				
				
				<img src="images/previous.gif" alt="Previous" align="absmiddle" border="0" height="10" width="4">
					<a href="javascript:goleft()" class="listViewPaginationLinkS1">
					Previous&nbsp;&nbsp;
					</a>
				<%
				}
				%>
					<span class="pageNumbers">(<c:out value="${minIndex}" /> - <c:out value="${maxIndex}" /> of <c:out value="${count}" />)</span>&nbsp;&nbsp;
				
				<%
				if (totalIndex > index){
				%>
				
				<a href="javascript:goright()" class="listViewPaginationLinkS1">Next&nbsp;
				<img src="images/next.gif" alt="Next" align="absmiddle" border="0" height="10" width="4">
				</a>&nbsp;&nbsp;
				<a href="javascript:gorightbgt()" class="listViewPaginationLinkS1">End&nbsp;
					<img src="images/end.gif" alt="End" align="absmiddle" border="0" height="10" width="9">
				</a>
				<%
				} else {
				
				%>
				Next&nbsp;
				<img src="images/next_off.gif" alt="Next" align="absmiddle" border="0" height="10" width="4">
				&nbsp;&nbsp;
				End&nbsp;
					<img src="images/end_off.gif" alt="End" align="absmiddle" border="0" height="10" width="9">
				<%
				}
				%>
			</td>
          </tr>
	</tbody>
	</table>
</form>
	
<!-- Table Content Stop -->
	<!-- Table Toolbar Start -->
	
		<!-- Table Toolbar Stop -->



<!-- Table Container Stop -->
<script language="Javascript">
	$(document).ready(function(){
		//alert("jQuery Ready");
		/*Add by aju on 20150407, for active menu...fiuuuhhhhhh*/
    	var nav = $("#mnuMainClaim");
		nav.addClass("active");
		
		var nav = $("#mnuClaimMonitor");
		nav.addClass("active");
		
		var nav = $("#mnuMainSearch");
		nav.addClass("active");
		
		var nav = $("#mnuSearchClaim");
		nav.addClass("active");
		
	});

<%
String nav="";
if(request.getAttribute("navigation").equals("gosearch")||request.getAttribute("navigation").equals("golookup")){
	nav = (String)request.getAttribute("navigation");
}
%>
function tambahi (){
	document.form1.navigation.value = "tambah";
	document.form1.action = "claim-form";
	document.form1.method = "GET";
	document.form1.submit();
}
function hapus (idx){
	var delConfirm = window.confirm ("Are You Sure Want To Delete This Entry ?");

	if (delConfirm){
		document.form1.method = "POST";
		document.form1.claimId.value = idx;
		document.form1.action = "claim";
		document.form1.navigation.value = "delete";
		document.form1.submit();
	}
}
function ubah (idx){
	document.form1.method = "GET";
	document.form1.claimId.value = idx;
	document.form1.action = "claim-form";
	document.form1.navigation.value = "edit";
	document.form1.submit();
}
function goleft(){
	document.form1.navigation.value = "<%=nav%>";
	document.form1.arah.value="kiri";
	document.form1.method = "POST";
	document.form1.submit();
}
function goleftbgt(){
	document.form1.navigation.value = "<%=nav%>";
	document.form1.arah.value="kiribgt";
	document.form1.method = "POST";
	document.form1.submit();
}
function goright(){
	document.form1.navigation.value = "<%=nav%>";
	document.form1.arah.value="kanan";
	document.form1.method = "POST";
	document.form1.submit();
}
function gorightbgt(){
	document.form1.navigation.value = "<%=nav%>";
	document.form1.arah.value="kananbgt";
	document.form1.method = "POST";
	document.form1.submit();
}
function go(){
	document.form1.navigation.value = "<%=nav%>";
	document.form1.submit();
}
function cari (){
	document.form1.navigation.value = "gosearch";
	document.form1.action = "claim";
	document.form1.method = "POST";
	document.form1.submit();
}
function detil (idx){
	document.form1.method = "GET";
	document.form1.claimId.value = idx;
	document.form1.action = "claim";
	document.form1.navigation.value = "detail";
	document.form1.submit();

}
function carisort(param){
	document.form1.navigation.value = "gosearchbysort";
	document.form1.searchtext.value="<c:out value="${searchtext}" />";
	document.form1.searchby.value="<c:out value="${searchby}" />";
	document.form1.sortcolumn.value= param;
	document.form1.method = "POST";
	document.form1.submit();
}
</script>
