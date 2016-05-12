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


if (alert != null && !alert.trim().equals("")) {%>
	<div id="warning" align="center">
		<c:out value="${alert}"></c:out>
	</div>
	<%}%>

<%
String rowclass = "";
int i=0;

%>
<!-- Search Container Start -->

<form name="form1" action="batchclaimreport" method="POST">
<input type="hidden" name="navigation" value="report">
<input type="hidden" name="arah" value="">
<input type="hidden" name="index" value="<c:out value="${index}" />">
				<input type="hidden" name="batchClaimId" value="<c:out value="${batchClaim.batchClaimId}" />">
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Search Batch Claim</h3></td>
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
 		   				<option value="batchNumberPsea" <c:if test="${searchby eq \"batchNumberPsea\"}">selected="true"</c:if> >Batch Number Psea</option>
			   			<option value="batchClaimNumber" <c:if test="${searchby eq \"batchClaimNumber\"}">selected="true"</c:if> >Batch Claim Number</option>
			   			<option value="clientName" <c:if test="${searchby eq \"clientName\"}">selected="true"</c:if> >Client Name</option>
			   			<option value="providerName" <c:if test="${searchby eq \"providerName\"}">selected="true"</c:if> >Provider Name</option>
			   			<option value="memberGroupName" <c:if test="${searchby eq \"memberGroupName\"}">selected="true"</c:if> >Member Group Name</option>
			   			<option value="memberName" <c:if test="${searchby eq \"memberName\"}">selected="true"</c:if> >Member Name</option>
			   			<option value="paymentMethod" <c:if test="${searchby eq \"paymentMethod\"}">selected="true"</c:if> >Payment Method</option>
			   			<option value="invoiceNumber" <c:if test="${searchby eq \"invoiceNumber\"}">selected="true"</c:if> >Invoice Number</option>
	   					<option value="recipient" <c:if test="${searchby eq \"recipient\"}">selected="true"</c:if> >Payment Recipient</option>
	   					<option value="description" <c:if test="${searchby eq \"description\"}">selected="true"</c:if> >Description</option>
		   		   </select>
				
              </td>
			  <td class="dataLabel" nowrap="nowrap">Status:
              &nbsp;&nbsp;
              
                <select name="status">					
					<option value="-1">-- All Status --</option>
					<option value="1" <c:if test="${status eq 1 }">selected</c:if>>Open</option>
					<option value="12" <c:if test="${status eq 12 }">selected</c:if>>Finalized</option>					
					<option value="6" <c:if test="${status eq 6 }">selected</c:if>>Paid</option>					
					<option value="14" <c:if test="${status eq 14 }">selected</c:if>>Unregistered</option>							
					
				</select>
				
              </td>
            <td class="dataLabel">
              <input title="Search [Alt+Shift+Q]" accesskey="Q" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" name="button" value="Search" type="submit">
                          
			</td>
            </tr>
			<tr>
            
            <td class="dataLabel" nowrap="nowrap"> Minimum Date :
              &nbsp;&nbsp;&nbsp;&nbsp;
              
                <input name="minimum_date" id="jscal_field" tab="1" maxlength="10" size="11" readonly="readonly" value="" type="text">
				<img src="images/jscalendar.gif" alt="Enter Date" id="jscal_trigger" align="absmiddle" height="18" width="18">
				
				<script type="text/javascript">
    					Calendar.setup({
        					inputField     :    "jscal_field",     // id of the input field
        					ifFormat       :    "%Y-%m-%d",      // format of the input field
        					button         :    "jscal_trigger",  // trigger for the calendar (button ID)
        					align          :    "Tl",           // alignment (defaults to "Bl")
        					singleClick    :    true
    					});
				 	</script>
              
			</td>
            <td class="dataLabel" nowrap="nowrap"> Maximum Date :
              &nbsp;&nbsp;&nbsp;&nbsp;
              
            <input name="maximum_date" id="jscal_field_max" tabindex="1" maxlength="10" size="11" readonly="readonly" value="" type="text">
			<img src="images/jscalendar.gif" alt="Enter Date" id="jscal_max_trigger" align="absmiddle" height="18" width="18">
			
					<script type="text/javascript">
    					Calendar.setup({
        					inputField     :    "jscal_field_max",     // id of the input field
        					ifFormat       :    "%Y-%m-%d",      // format of the input field
        					button         :    "jscal_max_trigger",  // trigger for the calendar (button ID)
        					align          :    "Tl",           // alignment (defaults to "Bl")
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


<!-- Table Container Start -->



<div class="table_container">
	<!-- Table Toolbar Start -->
		
		
		<%
		String nampak = "";
		if(navigation!=null&&navigation.equals("gosearch")){
		}else{
			nampak = " style=\"display: none;\"";
		}
		%>
		
</div>
	<!-- Table Toolbar Stop -->
	
	<!-- Table Content Start -->
	

<br />

 	
<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>

	<tr height="20">
		<td width="6%" nowrap="nowrap" class="listViewThS1" scope="col">
			<img src="images/blank.gif" alt="asd" height="1" width="1">No.</td>		

<!-- ini default generated table from database -->
		
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">
				<a href="batchclaim?navigation=search&sortby=batch_claim_number&searchby=<c:out value="${searchby}" />" class="listViewThLinkS1">Batch Claim Number &nbsp;<img src="images/arrow.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a></td>
			
		
		   			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">
				<a href="batchclaim?navigation=search&sortby=batch_claim_date&searchby=<c:out value="${searchby}" />" class="listViewThLinkS1">Received Date &nbsp;<img src="images/arrow.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a></td>
				
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">
				<a href="batchclaim?navigation=search&sortby=batch_claim_date&searchby=<c:out value="${searchby}" />" class="listViewThLinkS1">Client Name &nbsp;<img src="images/arrow.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a></td>
			
	   				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Batch Value &nbsp;</td>
			
			
			
	   				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Approved Value &nbsp;</td>
			   		   		   		   		   		   		   		   		   		   		   		   		   			
			   		   		   		   		   		   		   		   		   		   		   		   		   			
			   		   		   		   		   		   		   		   		   		   		   		   		   			
	   				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Paid Value &nbsp;</td>
			   		   		   		   		   		   		   		   		   		   		   		   		   			
			
	   				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Excess Charge &nbsp;</td>
			   		   		   		   		   		   		   		   		   		   		   		   		   			
			
	   				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Paid Excess Charge &nbsp;</td>
			   		   		   		   		   		   		   		   		   		   		   		   		   			
			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">
				Payment Recipient &nbsp;</td>
	
		
			   		   			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Batch Status &nbsp;</td>
			
		
		
	</tr>

	<c:set var="batchClaimInitialValue" value="0" scope="request" />
	<c:set var="batchClaimFinalValue" value="0"  scope="request" />
	<c:set var="batchExcessValue" value="0" scope="request" />
	<c:set var="batchPaidValue" value="0"  scope="request" />
	<c:set var="batchPaidExcessValue" value="0" scope="request" />
	

	
	<c:forEach items="${BatchClaims}" var="batchClaim" varStatus="status" >
	<c:set var="batchClaimInitialValue" value="${batchClaimInitialValue+batchClaim.batchClaimInitialValue}"></c:set>
	<c:set var="batchClaimFinalValue" value="${batchClaimFinalValue+batchClaim.batchClaimFinalValue}"></c:set>
	<c:set var="batchExcessValue" value="${batchExcessValue+batchClaim.batchExcessValue}"></c:set>
	<c:set var="batchPaidValue" value="${batchPaidValue+batchClaim.batchClaimPaidValue}"></c:set>
	<c:set var="batchPaidExcessValue" value="${batchPaidExcessValue+batchClaim.batchPaidExcessValue}"></c:set>
	<%	if (i % 2 == 0) {
			rowclass = "col1";
		} else if (i % 2 != 0) {
			rowclass= "col2";
		}
		i++;
	%>
	 <tr height="20">
      <td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><%=i%>.</td>

      		
      				   		   		   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<a class="linkDetail" href="batchclaim?navigation=detail&batchClaimId=<c:out value="${batchClaim.batchClaimId}" />"><c:out value="${batchClaim.batchClaimNumber}" /></a>
			
		</td>
			
      		   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${batchClaim.batchClaimDate}" />
			
		</td>
      		   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${batchClaim.clientId.clientName}" />
			
		</td>
      		   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
			
				<fmt:formatNumber><c:out value="${batchClaim.batchClaimInitialValue}" /></fmt:formatNumber>
			
		</td>

      		   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
			
				<fmt:formatNumber><c:out value="${batchClaim.batchClaimFinalValue}" /></fmt:formatNumber>
			
		</td>

      		   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
			
				<fmt:formatNumber><c:out value="${batchClaim.batchClaimPaidValue}" /></fmt:formatNumber>
			
		</td>
		      		   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
			
				<fmt:formatNumber ><c:out value="${batchClaim.batchExcessValue}" /></fmt:formatNumber>
			
		</td>
		

      		   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
			
				<fmt:formatNumber><c:out value="${batchClaim.batchPaidExcessValue}" /></fmt:formatNumber>
			
		</td>

 		   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${batchClaim.paymentRecipient.paymentRecipientName}" />
			
		</td>
					   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${batchClaim.batchClaimStatus.caseStatusName}" />
			
		</td>
		
    </tr>
   
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	
	</c:forEach>
	<tr height="20">
		<td width="6%" nowrap="nowrap" class="listViewThS1" scope="col">
			</td>		

<!-- ini default generated table from database -->
		
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">
				</td>
			
		
		   			<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">
				</td>
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">
				</td>
			
	   				<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%" style="text-align: right;">
				<fmt:formatNumber > <c:out value="${batchClaimInitialValue}" /></fmt:formatNumber></td>
			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%" style="text-align: right;">
				<fmt:formatNumber > <c:out value="${batchClaimFinalValue}" /></fmt:formatNumber></td>
			   		   		   		   		   		   		   		   		   		   		   		   		   			
			   		   		   		   		   		   		   		   		   		   		   		   		   			
			   		   		   		   		   		   		   		   		   		   		   		   		   			
	   				<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%" style="text-align: right;">
				<fmt:formatNumber > <c:out value="${batchPaidValue}" /></fmt:formatNumber></td>
			   		   		   		   		   		   		   		   		   		   		   		   		   			
			
	   				<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%" style="text-align: right;">
				<fmt:formatNumber > <c:out value="${batchExcessValue}" /></fmt:formatNumber></td>
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%" style="text-align: right;">
				<fmt:formatNumber > <c:out value="${batchPaidExcessValue}" /></fmt:formatNumber></td>
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">
				</td>
			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">
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
<%
String nav="";
if(request.getAttribute("navigation").equals("gosearch")||request.getAttribute("navigation").equals("golookup")){
	nav = (String)request.getAttribute("navigation");
}
%>

function go(){
	document.form1.navigation.value = "<%=nav%>";
	document.form1.submit();
}
function cari (){
	document.form1.navigation.value = "report";
	document.form1.action = "batchclaimreport";
	document.form1.method = "POST";
	document.form1.submit();
}

</script>
