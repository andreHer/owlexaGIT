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
WebUtil.getAttributeInteger(request,"index",0).intValue();
%>

<br />
<form name="form1" action="claim" method="POST">
<input type="hidden" name="navigation" value="saveattachswipe">
<input type="hidden" name="currentnavigation" value="list" />
<input type="hidden" name="arah" value="">
<input type="hidden" name="index" value="<c:out value="${index}" />">
<input type="hidden" name="batchClaimId" value="${batchClaimId}" />
		


<input title="Add Claim [Alt+Shift+C]" accesskey="H" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:addClaim()" name="tambahClaim" value=" Add Claim " type="button">
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
		<td width="3%" nowrap="nowrap" class="listViewThS1" scope="col" align="center">
			<img src="images/blank.gif" alt="asd" height="1" width="1">No.</td>		

				   		   		   			
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Number &nbsp;</td>

				<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">
				Member &nbsp;</td>
				
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Card Number </td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Client </td>
			   		   		   		   		   		   			
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">
				Provider &nbsp;</td>
			
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Received Date &nbsp;</td>

				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Charges &nbsp;</td>

				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Benefit Paid &nbsp;</td>

				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Excess &nbsp;</td>
		
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">
				Status &nbsp;</td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">
				 &nbsp;</td>
			   			
			   		   		   		   		   			
			  
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
      <td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${status.index + 1}" />.</td>
	  <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
		<a href="claim?navigation=detail&url=batch&claimId=<c:out value="${claim.claimId}" />" class="linkDetail"> <c:out value="${claim.claimNumber}" /></a>
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top">			
			<c:out value="${claim.memberId.firstName}" /> <c:out value="${claim.memberId.lastName}" />			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<c:out value="${claim.cardNumber}" />
			</td>
			
			<td class="oddListRowS1" bgcolor="#e7f0fe"  valign="top">			
			<c:out value="${claim.client.clientName}" />			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<c:out value="${claim.providerName}" />			
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
				<fmt:formatNumber><c:out value="${claim.claimExcessValue}" /></fmt:formatNumber>
		</td>		   		   				   		   		   		   		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
				<c:out value="${claim.claimStatus.caseStatusName}" />			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
				<center><input type="checkbox" name="attach_claim_id" value="<c:out value="${claim.claimId}" />"></center>		
		</td>
    </tr>
   
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	
	</c:forEach>
		
	<tr>
            <td  align="left"></td>
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
%>
<c:if test="${batchClaim.batchClaimStatus.caseStatusId eq 1}">
function addClaim (){
		document.form1.navigation.value = "saveattachswipe";
		document.form1.action = "claim";
		document.form1.method = "GET";
		document.form1.submit();
	}
</c:if>
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
</script>
