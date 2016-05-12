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

<form name="form1" action="batchclaim" method="POST">
<input type="hidden" name="navigation" value="gosearch">
<input type="hidden" name="sortcolumn" value="<c:out value="${sortcolumn}" />">
<input type="hidden" name="sortorder" value="<c:out value="${sortorder}" />">
<input type="hidden" name="columntosort" value="<c:out value="${columntosort}" />">
<input type="hidden" name="arah" value="">
<input type="hidden" name="index" value="<c:out value="${index}" />">
<input type="hidden" name="batchClaimId" value="<c:out value="${batchClaim.batchClaimId}" />">

<%
int navStatus= 0;
if(request.getAttribute("status") !=null && request.getAttribute("navigation").equals("gosearch")){
    if(request.getAttribute("status").equals(1)){
		navStatus = 1;
	}
}
%>
				
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
    	<c:if test="${theUser.userType eq 2}">
      		<td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Search Batch Claim</h3></td>
      	</c:if>
      	<c:if test="${theUser.userType eq 4}">				
      			<%
				if (navStatus == 1){
				%>
				<td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;My Unpaid Invoice</h3></td>
				<%
				} else {				
				%>
				<td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;My Invoice History</h3></td>
				<%
				}
				%>				    		
      	</c:if>
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
 				  	<c:if test="${theUser.userType eq 2}"> 		   				
			   			<option value="clientName" <c:if test="${searchby eq \"clientName\"}">selected="true"</c:if> >Client Name</option>
			   			<option value="providerName" <c:if test="${searchby eq \"providerName\"}">selected="true"</c:if> >Provider Name</option>
			   			<option value="memberGroupName" <c:if test="${searchby eq \"memberGroupName\"}">selected="true"</c:if> >Member Group Name</option>
			   			<option value="memberName" <c:if test="${searchby eq \"memberName\"}">selected="true"</c:if> >Member Name</option>
			   		</c:if>			   		
			   		<option value="invoiceNumber" <c:if test="${searchby eq \"invoiceNumber\"}">selected="true"</c:if> >Invoice Number</option>
			   		<option value="batchClaimNumber" <c:if test="${searchby eq \"batchClaimNumber\"}">selected="true"</c:if> >Batch Claim Number</option>
		   		   </select>
				
              </td>
			  <td class="dataLabel" nowrap="nowrap">Status:
              &nbsp;&nbsp;
              
                <select name="status">
                    <%
					if (navStatus != 1){
					%>					
					<option value="-1">-- All Status --</option>
					<%
					}
					%>				
					<option value="1" <c:if test="${status eq 1 }">selected = true</c:if> >OPEN</option>								
	      			<%
					if (navStatus != 1){
					%>
						<option value="12" <c:if test="${status eq 12 }">selected</c:if>>COMPLETE</option>
						<option value="6" <c:if test="${status eq 6 }">selected</c:if>>PAID</option>
						<option value="5" <c:if test="${status eq 5 }">selected</c:if>>CLOSED</option>
						<option value="13" <c:if test="${status eq 13 }">selected</c:if>>CDV ISSUED</option>
					<%
					}
					%>				    											
				</select>
				
              </td>
            <td class="dataLabel">
              <input title="Search [Alt+Shift+Q]" accesskey="Q" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" name="button" value="Search" onclick="javascript:cari();" type="button">
              <c:if test="${theUser.userType eq 2}">
              <input title="Download Report [Alt+Shift+Q]" accesskey="Q" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" name="button" value="Download" type="button" onclick="javascript:exportBatch()">
            </c:if>
			</td>
            </tr>
			<tr>
            
            <td class="dataLabel" nowrap="nowrap"> Minimum Date :
              &nbsp;&nbsp;&nbsp;&nbsp;
              
                <input name="minimum_date" id="jscal_field" tab="1" maxlength="10" size="11"  value="<c:out value="${minimum_date}" />" type="text">
				<img src="images/jscalendar.gif" alt="Enter Date" id="jscal_trigger" align="absmiddle" height="18" width="18">
				
				<script type="text/javascript">
    					Calendar.setup({
        					inputField     :    "jscal_field",     // id of the input field
        					ifFormat       :    "%Y-%m-%d",      // format of the input field
        					button         :    "jscal_trigger",  // trigger for the calendar (button ID)
        					align          :    "Bl",           // alignment (defaults to "Bl")
        					singleClick    :    true
    					});
				 	</script>
              
			</td>
            <td class="dataLabel" nowrap="nowrap"> Maximum Date :
              &nbsp;&nbsp;&nbsp;&nbsp;
              
            <input name="maximum_date" id="jscal_field_max" tabindex="1" maxlength="10" size="11" value="<c:out value="${maximum_date}" />" type="text">
			<img src="images/jscalendar.gif" alt="Enter Date" id="jscal_max_trigger" align="absmiddle" height="18" width="18">
			
					<script type="text/javascript">
    					Calendar.setup({
        					inputField     :    "jscal_field_max",     // id of the input field
        					ifFormat       :    "%Y-%m-%d",      // format of the input field
        					button         :    "jscal_max_trigger",  // trigger for the calendar (button ID)
        					align          :    "Bl",           // alignment (defaults to "Bl")
        					singleClick    :    true
    					});
				 	</script>
			</td>
            <td class="dataLabel" nowrap="nowrap">Date &nbsp;&nbsp;:
              &nbsp;&nbsp;
              
                <select name="dateBy">					
					<option value="batchClaimDate" <c:if test="${dateBy eq 'batchClaimDate'}">selected</c:if>>Received Date</option>
					<option value="batchClaimCloseDate" <c:if test="${dateBy eq 'batchClaimCloseDate' }">selected</c:if>>Close Date</option>					
					<option value="invoiceDate" <c:if test="${dateBy eq 'invoiceDate'}">selected</c:if>>Invoice Date</option>
					<option value="paymentDate" <c:if test="${dateBy eq 'paymentDate'}">selected</c:if>>Payment Date</option>
				</select>
				
              </td>
            <td align="right" class="dataLabel" >
            </td>
          </tr>
        </tbody>
      </table></td>
    </tr>
  </tbody>
</table>

	<!-- Table Toolbar Stop -->
	
	<!-- Table Content Start -->
	
	<c:if test="${theUser.roleId.roleId eq 0 or theUser.roleId.roleId eq 3 or theUser.roleId.roleId eq 4 or theUser.roleId.roleId eq 15}">
<input type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:tambahi()" value="Create">
</c:if>
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
		<td width="2%" nowrap="nowrap" class="listViewThS1" scope="col">
			<img src="images/blank.gif" alt="asd" height="1" width="1">No.</td>		

<!-- ini default generated table from database -->

			<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Number &nbsp;
				<input type="hidden" name="sortOrderBatchNo" value="<c:out value="${sortBatchNo?'true':'false'}" />">
				<a href="javascript:carisort('batchnumber')" class="listViewThLinkS1">
				<img src="images/arrow_<c:out value="${sortBatchNo?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
			</td>		   			
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Received Date &nbsp;
				<input type="hidden" name="sortOrderBatchDate" value="<c:out value="${sortReceiveDate?'true':'false'}" />">
				<a href="javascript:carisort('receiveddate')" class="listViewThLinkS1">
				<img src="images/arrow_<c:out value="${sortReceiveDate?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
			</td>
			<c:if test="${theUser.userType eq 2}">
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Client Name &nbsp;
					<input type="hidden" name="sortOrderClient" value="<c:out value="${sortClient?'true':'false'}" />">
					<a href="javascript:carisort('client')" class="listViewThLinkS1">
					<img src="images/arrow_<c:out value="${sortClient?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
				</td>
			</c:if>
		 	<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Provider Name &nbsp;
				<input type="hidden" name="sortOrderProvider" value="<c:out value="${sortProvider?'true':'false'}" />">
				<a href="javascript:carisort('provider')" class="listViewThLinkS1">
				<img src="images/arrow_<c:out value="${sortProvider?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
			</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Group Name &nbsp;
				<input type="hidden" name="sortOrderGroup" value="<c:out value="${sortGroup?'true':'false'}" />">
				<a href="javascript:carisort('groupname')" class="listViewThLinkS1">
				<img src="images/arrow_<c:out value="${sortGroup?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
			</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Member Name &nbsp;
				<input type="hidden" name="sortOrderMember" value="<c:out value="${sortMember?'true':'false'}" />">
				<a href="javascript:carisort('membername')" class="listViewThLinkS1">
				<img src="images/arrow_<c:out value="${sortMember?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
			</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Invoice Number &nbsp;
				<input type="hidden" name="sortOrderInvoiceNo" value="<c:out value="${sortInvoiceNo?'true':'false'}" />">
				<a href="javascript:carisort('invoicenumber')" class="listViewThLinkS1">
				<img src="images/arrow_<c:out value="${sortInvoiceNo?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
			</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Invoice Date &nbsp;
				<input type="hidden" name="sortOrderInvoiceDate" value="<c:out value="${sortInvoiceDate?'true':'false'}" />">
				<a href="javascript:carisort('invoicedate')" class="listViewThLinkS1">
				<img src="images/arrow_<c:out value="${sortInvoiceDate?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
			</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Payment Date &nbsp;
				<input type="hidden" name="sortOrderPaymentDate" value="<c:out value="${sortPaymentDate?'true':'false'}" />">
				<a href="javascript:carisort('paymentdate')" class="listViewThLinkS1">
				<img src="images/arrow_<c:out value="${sortPaymentDate?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
			</td>	
   			<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Charge &nbsp;
   				<input type="hidden" name="sortOrderCharge" value="<c:out value="${sortCharge?'true':'false'}" />">
				<a href="javascript:carisort('charge')" class="listViewThLinkS1">
				<img src="images/arrow_<c:out value="${sortCharge?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
   			</td>
			<c:if test="${theUser.userType eq 2}">
   				<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Approved&nbsp;
   					<input type="hidden" name="sortOrderApproved" value="<c:out value="${sortApproved?'true':'false'}" />">
					<a href="javascript:carisort('approved')" class="listViewThLinkS1">
					<img src="images/arrow_<c:out value="${sortApproved?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
   				</td>  		   		   		   		   		   		   		   			
   				<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Excess  &nbsp;
   					<input type="hidden" name="sortOrderExcess" value="<c:out value="${sortExcess?'true':'false'}" />">
					<a href="javascript:carisort('excess')" class="listViewThLinkS1">
					<img src="images/arrow_<c:out value="${sortExcess?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
   				</td>		   		   		   		   			
			</c:if>			   		   			
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Status &nbsp;
  				<input type="hidden" name="sortOrderStatus" value="<c:out value="${sortStatus?'true':'false'}" />">
				<a href="javascript:carisort('status')" class="listViewThLinkS1">
				<img src="images/arrow_<c:out value="${sortStatus?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
			</td>
			
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Claim Type &nbsp;
  				<input type="hidden" name="sortOrderClaimType" value="<c:out value="${sortClaimType?'true':'false'}" />">
				<a href="javascript:carisort('claimType')" class="listViewThLinkS1">
				<img src="images/arrow_<c:out value="${sortClaimType?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
			</td>
	</tr>


	<c:forEach items="${BatchClaims}" var="batchClaim" varStatus="status" >
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
			
				<c:if test="${theUser.userType eq 2}">
				<a class="linkDetail" href="batchclaim?navigation=detail&batchClaimId=<c:out value="${batchClaim.batchClaimId}" />"><c:out value="${batchClaim.batchClaimNumber}" /></a>
				</c:if>
				<c:if test="${theUser.userType eq 4}">
				<c:out value="${batchClaim.batchClaimNumber}" />
				</c:if>
		</td>
			
      		   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${batchClaim.batchClaimDate}" />
			
		</td>
		<c:if test="${theUser.userType eq 2}">
      		   				<td class="oddListRowS1" bgcolor="#e7f0fe"  valign="top">
			
				<c:out value="${batchClaim.clientId.clientName}" />
			
		</td>
		   	
		</c:if>
					<td class="oddListRowS1" bgcolor="#e7f0fe"  valign="top">
			
				<c:out value="${batchClaim.providerName}" />
			
		</td>
					<td class="oddListRowS1" bgcolor="#e7f0fe"  valign="top">
			
				<c:out value="${batchClaim.groupName}" />
			
		</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe"  valign="top">
			
				<c:out value="${batchClaim.memberName}" />
			
		</td>
		   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${batchClaim.invoiceNumber}" />
			
		</td>
		   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${batchClaim.invoiceDate}" />
			
		</td>
					<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${batchClaim.paymentDate}" />
			
		</td>
      		   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
			
				<fmt:formatNumber><c:out value="${batchClaim.batchClaimInitialValue}" /></fmt:formatNumber>
			
		</td>
<c:if test="${theUser.userType eq 2}">
      		   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
			
				<fmt:formatNumber><c:out value="${batchClaim.batchClaimFinalValue}" /></fmt:formatNumber>
			
		</td>

      		   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
			
				<fmt:formatNumber><c:out value="${batchClaim.batchExcessValue}" /></fmt:formatNumber>
			
		</td>
		      	

      		
		</c:if>

					   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${batchClaim.batchClaimStatus.caseStatusName}" />
			
		</td>
		
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${batchClaim.batchClaimType.claimTypeName}" />
			
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
		/*Add by aju on 20150407, for active menu...fiuuuhhhhhh*/
		var mnuName = ($("select[name=status]").val()=="1"?"mnuClaimInput":($("select[name=status]").val()=="12"?"mnuClaimVerification":($("select[name=status]").val()=="5"?"mnuClaimPayment":null)));
	    //alert($("select[name=status]").val());
	    if(mnuName != null){
	    	var nav = $("#mnuMainClaim");
			nav.addClass("active");
			
			var nav = $("#"+mnuName);
			nav.addClass("active");
	    }
	    
	});
	
	
<%
String nav="";
if(request.getAttribute("navigation").equals("gosearch")||request.getAttribute("navigation").equals("golookup")){
	nav = (String)request.getAttribute("navigation");
}
%>
function tambahi (){
	document.form1.navigation.value = "tambah";
	document.form1.action = "batchclaim-form";
	document.form1.method = "GET";
	document.form1.submit();
}
function hapus (idx){
	var delConfirm = window.confirm ("Are You Sure Want To Delete This Entry ?");

	if (delConfirm){
		document.form1.method = "POST";
		document.form1.batchClaimId.value = idx;
		document.form1.action = "batchclaim";
		document.form1.navigation.value = "delete";
		document.form1.submit();
	}
}
function ubah (idx){
	document.form1.method = "GET";
	document.form1.batchClaimId.value = idx;
	document.form1.action = "batchclaim-form";
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
	document.form1.action = "batchclaim";
	document.form1.method = "POST";
	document.form1.submit();
}
function detil (idx){
	document.form1.method = "POST";
	document.form1.batchClaimId.value = idx;
	document.form1.action = "batchclaim";
	document.form1.navigation.value = "detail";
	document.form1.submit();

}
function exportBatch (){
	document.form1.method = "POST";
	document.form1.navigation.value = "download";
	document.form1.action = "batchclaim";
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
