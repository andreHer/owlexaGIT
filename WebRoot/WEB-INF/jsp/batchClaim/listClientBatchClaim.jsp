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

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;List Client Batch Claim</h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>

<!-- Search Container Start -->

<%@ include file="../mainContentClient.jsp" %>

<br />

<form name="form1" action="batchclaim" method="POST">

<input type="hidden" name="clientId" value="<c:out value="${clientId}" />" />
<input type="hidden" name="navigation" value="listclient">

<input type="hidden" name="subnavigation" value="<c:out value="${subnavigation}" />" />
<input type="hidden" name="currentnavigation" value="listclient" />
<input type="hidden" name="arah" value="">
<input type="hidden" name="index" value="<c:out value="${index}" />">
<input type="hidden" name="batchClaimId" value="<c:out value="${batchClaim.batchClaimId}" />">

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"></td>
      <td width="100%"></td>
    </tr>
  </tbody>
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Search Client Batch Claim</h3></td>
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
              <input title="Search [Alt+Shift+Q]" accesskey="Q" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" name="button" value="Search" type="button" onclick="javascript:go()">
                          
			</td>
            </tr>
			<tr>
            <td class="dataLabel" nowrap="nowrap">Date Category:
              &nbsp;&nbsp;
              
                
 				  <select name="dateby" class="inputbox">
 				  		<option value="">-- SELECT CATEGORY --</option>
 		   				<option value="batchNumberPsea" <c:if test="${searchby eq \"batchNumberPsea\"}">selected="true"</c:if> >Batch Number Psea</option>
			   			<option value="batchClaimNumber" <c:if test="${searchby eq \"batchClaimNumber\"}">selected="true"</c:if> >Batch Claim Number</option>
			   			<option value="paymentMethod" <c:if test="${searchby eq \"paymentMethod\"}">selected="true"</c:if> >Payment Method</option>
			   			<option value="invoiceNumber" <c:if test="${searchby eq \"invoiceNumber\"}">selected="true"</c:if> >Invoice Number</option>
	   					<option value="recipient" <c:if test="${searchby eq \"recipient\"}">selected="true"</c:if> >Payment Recipient</option>
	   					<option value="description" <c:if test="${searchby eq \"description\"}">selected="true"</c:if> >Description</option>
		   		   </select>
				
              </td> 
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

	
<br />

 	
<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	<tr>
      <td colspan="20" align="right"><table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tbody>
          <tr>
            <td  align="left">
			</td>
            <td  align="right" nowrap="nowrap">
				
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
		
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">
				Batch Claim Number</td>
			
		
		   			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">
				Received Date </td>
			
	   		   		   		   		   		   		   		   		   		   		   		   		   		   			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">
				Payment Recipient </td>
	
		
			   		   			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">
				Batch Status </td>
			
		
		   		   	   <td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">&nbsp;
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
			
				<a href="batchclaim?navigation=detail&clientId=<c:out value="${clientId}" />&batchClaimId=<c:out value="${batchClaim.batchClaimId}" />" class="linkDetail" ><c:out value="${batchClaim.batchClaimNumber}" /></a>
			
		</td>
			
      		   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${batchClaim.batchClaimDate}" />
			
		</td>

 		   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${batchClaim.paymentRecipient.paymentRecipientName}" />
			
		</td>
					   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${batchClaim.batchClaimStatus.caseStatusName}" />
			
		</td>
					   		         <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top">
				
	  </td>
    </tr>
   
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	
	</c:forEach>
	
<tr>
            <td  align="left">
            </td>
            <td  align="right" nowrap="nowrap" colspan="20">
				
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
<%
String nav="";
if(request.getAttribute("navigation").equals("gosearch")||request.getAttribute("navigation").equals("golookup")){
	nav = (String)request.getAttribute("navigation");
}
%>

function goleft(){
	document.form1.action = "batchclaim";
	document.form1.navigation.value = "gosearch";
	document.form1.arah.value="kiri";
	document.form1.method = "POST";
	document.form1.submit();
}
function goleftbgt(){
	document.form1.action = "batchclaim";
	document.form1.navigation.value = "gosearch";
	document.form1.arah.value="kiribgt";
	document.form1.method = "POST";
	document.form1.submit();
}
function goright(){
	document.form1.action = "batchclaim";
	document.form1.navigation.value = "gosearch";
	document.form1.arah.value="kanan";
	document.form1.method = "POST";
	document.form1.submit();
}
function gorightbgt(){
	document.form1.action = "batchclaim";
	document.form1.navigation.value = "gosearch";
	document.form1.arah.value="kananbgt";
	document.form1.method = "POST";
	document.form1.submit();
}
function go(){
	document.form1.action = "batchclaim";
	<c:choose>
		<c:when test="${navigation eq 'listclient'}">
			document.form1.navigation.value = "listclient";
		</c:when>
	</c:choose>
	document.form1.submit();
}
function cari (){
	document.form1.action = "batchclaim";
	document.form1.navigation.value = "gosearch";
	document.form1.action = "batchclaim";
	document.form1.method = "POST";
	document.form1.submit();
}

</script>
