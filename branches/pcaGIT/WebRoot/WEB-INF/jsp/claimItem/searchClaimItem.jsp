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
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;List Claim Item</h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
      <c:if test="${theUser.userType eq 2 }">
	      <td align="right">
	      	<input title="Error Log"  name="errorLog" id="errorLog" value=" Error Log " class="errorLog" type="button" onClick="javascript:printErrorLog()">
	      </td>
	      <td align="right">
	      	<input title="Add Error Log"  name="addErrorLog" value=" Add Error Log " class="errorLog" type="button" onClick="javascript:adderrorlog()">
	      </td>
      </c:if>
    </tr>
  </tbody>
</table>

	<c:set var="claimChargeValue" value="0" scope="request" />
	<c:set var="claimApprovedValue" value="0"  scope="request" />
	<c:set var="claimExcessValue" value="0" scope="request" />
	
<!-- Search Container Start -->
<%@ include file="../mainContentClaim.jsp" %>

<br />
<form name="form1" action="claimitem" method="POST">
	<input type="hidden" name="navigation" value="">
	<input type="hidden" name="arah" value="">
	<input type="hidden" name="memberId" value="<c:out value="${memberId}" />" >
	<input type="hidden" name="claimId" value="<c:out value="${claimId}" />" >
	<input type="hidden" name="index" value="<c:out value="${index}" />">
	<input type="hidden" name="claimItemId" value="<c:out value="${claimItem.claimItemId}" />">

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"></td>
      <td width="100%"></td>
    </tr>
  </tbody>
</table>
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Search Claim Item</h3></td>
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

 		   		   		<option value="itemName" <c:if test="${searchby eq \"itemName\"}">selected="true"</c:if> >Item Name</option>
			   			<option value="claimItemDescription" <c:if test="${searchby eq \"claimItemDescription\"}">selected="true"</c:if> >Claim Item Description</option>
			   		   		 
			   		   </select>
				
              </td>
			  <td class="dataLabel" nowrap="nowrap">Status:
              &nbsp;&nbsp;
              
                <select name="searchstatus">		
					<option value="-1">-- All Status --</option>
   					<option value="1" <c:if test="${searchstatus eq 1 }">selected</c:if> >OPEN</option>			
					<option value="4" <c:if test="${searchstatus eq 4 }">selected</c:if>>REJECT</option>
					<option value="9" <c:if test="${searchstatus eq 9 }">selected</c:if>>APPROVED</option>					
					<option value="8" <c:if test="${searchstatus eq 8 }">selected</c:if>>CHECKED</option>					
				</select>
				
              </td>
            <td class="dataLabel">
              <input title="Search [Alt+Shift+Q]" onClick="javascript:cari();" accesskey="Q" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" name="button" value="Search" type="submit">

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
	<div id="DOWN">
	<tr height="20">
		<td width="2%" nowrap="nowrap" class="listViewThS1" scope="col"  style="text-align: center;">No.</td>		
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%"  style="text-align: center;">Item Name &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%"  style="text-align: center;">Qty &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%"  style="text-align: center;">Claim Value &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%" style="text-align: center;">Benefit Paid &nbsp;</td>	
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%"  style="text-align: center;">Excess Charges &nbsp;</td>				
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%"  style="text-align: center;">Pre Paid Excess &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%"  style="text-align: center;">Refund &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%"  style="text-align: center;">Paid To Provider &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%"  style="text-align: center;">Fault Excess Provider &nbsp;</td>
		<td scope="col" class="listViewThS1" width="20%"  style="text-align: center;">Description &nbsp;</td>
		<td scope="col" class="listViewThS1" width="20%"  style="text-align: center;">Check Remarks &nbsp;</td>					
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%"  style="text-align: center;">Status &nbsp;</td>			   		   		   		   		   				   		   			
			   		   			
			
					   	<c:if test="${ theUser.roleId.roleId eq 4 or theUser.roleId.roleId eq 3 or theUser.roleId.roleId eq 0 }">
		
			   		   	   <td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">&nbsp;
	   </td>
	   </c:if>
	</tr>
    </div>

	<c:forEach items="${ClaimItems}" var="claimItem" varStatus="status" >
	<c:set var="claimChargeValue" value="${claimChargeValue+claimItem.claimItemValue}"></c:set>
	<c:set var="claimApprovedValue" value="${claimApprovedValue+claimItem.claimItemApprovedValue}"></c:set>
	<c:set var="claimExcessValue" value="${claimExcessValue+claimItem.excessValue}"></c:set>
	<c:set var="prePaidValue" value="${prePaidValue+claimItem.prePaidExcess}"></c:set>
	<c:set var="refundValue" value="${refundValue+claimItem.refund}"></c:set>
	
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
			
				<c:out value="${claimItem.itemId.itemName}" />
			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;">			
				<fmt:formatNumber><c:out value="${claimItem.claimItemAmount}" /></fmt:formatNumber>			
		</td>  		 
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;">			
				<fmt:formatNumber><c:out value="${claimItem.claimItemValue}" /></fmt:formatNumber>			
		</td>
		 		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;">
			<fmt:formatNumber><c:out value="${claimItem.claimItemApprovedValue}" /></fmt:formatNumber>			
		</td>		   		   		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;">
			<fmt:formatNumber><c:out value="${claimItem.excessValue}" /></fmt:formatNumber>			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;">
			<fmt:formatNumber><c:out value="${claimItem.prePaidExcess}" /></fmt:formatNumber>			
		</td>		   
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;">
			<fmt:formatNumber><c:out value="${claimItem.refund}" /></fmt:formatNumber>			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;">
			<fmt:formatNumber><c:out value="${claimItem.paidToProvider}" /></fmt:formatNumber>			
		</td>	
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;">
			<fmt:formatNumber><c:out value="${claimItem.faultExcessProvider}" /></fmt:formatNumber>			
		</td>		   		   		   		   			   		   		   		   	
		<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top">
			
				<c:out value="${claimItem.claimItemDescription}" />
			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top">
			
				<c:out value="${claimItem.benefitCheckRemarks}" />
			
		</td>  			   		   		   	  			   		   		   		   		   		   		   		
	
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:if test="${claimItem.claimItemStatus.caseStatusId eq -1}">VOID</c:if>
				<c:if test="${claimItem.claimItemStatus.caseStatusId eq 1}">OPEN</c:if>
				<c:if test="${claimItem.claimItemStatus.caseStatusId eq 2}">PENDING DOCUMENT</c:if>
				<c:if test="${claimItem.claimItemStatus.caseStatusId eq 3}">VERIFIED</c:if>
				<c:if test="${claimItem.claimItemStatus.caseStatusId eq 4}">REJECTED</c:if>
				<c:if test="${claimItem.claimItemStatus.caseStatusId eq 5}">CLOSED</c:if>
				<c:if test="${claimItem.claimItemStatus.caseStatusId eq 6}">PAID</c:if>
				<c:if test="${claimItem.claimItemStatus.caseStatusId eq 7}">PENDING INVESTIGATION</c:if>
				<c:if test="${claimItem.claimItemStatus.caseStatusId eq 8}">CHECKED</c:if>
				<c:if test="${claimItem.claimItemStatus.caseStatusId eq 9}">APPROVED</c:if>
				<c:if test="${claimItem.claimItemStatus.caseStatusId eq 10}">PENDING</c:if>
				<c:if test="${claimItem.claimItemStatus.caseStatusId eq 11}">PROCESS OK</c:if>
				<c:if test="${claimItem.claimItemStatus.caseStatusId eq 12}">COMPLETE</c:if>
				<c:if test="${claimItem.claimItemStatus.caseStatusId eq 13}">CDV ISSUED</c:if>
				<c:if test="${claimItem.claimItemStatus.caseStatusId eq 14}">UNREGISTERED</c:if>
				<c:if test="${claimItem.claimItemStatus.caseStatusId eq 15}">FINALIZED</c:if>
				<c:if test="${claimItem.claimItemStatus.caseStatusId eq 16}">INSTALLMENT PAYMENT</c:if>
				<c:if test="${claimItem.claimItemStatus.caseStatusId eq 19}">PRE-OPEN</c:if>
			
		</td>  			   		   		   		   		   		   		   		
			   		
					   		   		
					   	<c:if test="${ theUser.roleId.roleId eq 4 or theUser.roleId.roleId eq 3 or theUser.roleId.roleId eq 0 }">
					   		         <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top">
					   		         
		<a href="javascript:detil('<c:out value="${claimItem.claimItemId}" />')">
				<img src="images/view.gif" class="action_icon" alt="View" title="View"></a>
			<%-- Edit 20150414 by FVO, penambahan pengecekan untuk claim item dengan status Sementara/Tentative agar tidak bisa dihapus/ditambah/diedit --%>
			<c:if test="${(claimItem.claimId.claimStatus.caseStatusId ne 19) and (claimItem.claimItemStatus.caseStatusId ne 19)}">
				<c:if test="${claimItem.claimItemStatus.caseStatusId eq 1}">
				<!-- ini default edit table for each data -->
				<a href="javascript:ubah('<c:out value="${claimItem.claimItemId}" />')">
					<img src="images/edit.gif" class="action_icon" alt="Edit" title="Edit"></a>
	
				<!-- ini default delete table for each data -->
				<a href="javascript:hapus('<c:out value="${claimItem.claimItemId}" />')">
					<img src="images/delete.gif" class="action_icon" alt="Delete" title="Delete"></a>
				</c:if>
			</c:if>
	  </td>
	  </c:if>
    </tr>
   
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	
	</c:forEach>
	<tr height="20">
		<td width="2%" nowrap="nowrap" class="listViewThS1" scope="col"  style="text-align: center;">
			<img src="images/blank.gif" alt="asd" height="1" width="1"></td>		

<!-- ini default generated table from database -->
		   		  			
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%"  style="text-align: center;">
				 &nbsp;</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%"  style="text-align: center;" >
				 &nbsp;
				</td>
		
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%"  style="text-align: right">
				<fmt:formatNumber > <c:out value="${claimChargeValue}" /></fmt:formatNumber>
				</td>

	<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%" style="text-align: right">
				<fmt:formatNumber > <c:out value="${claimApprovedValue}" /></fmt:formatNumber>&nbsp;</td>
	
	<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%"  style="text-align: right">
				 <fmt:formatNumber > <c:out value="${claimExcessValue}" /></fmt:formatNumber>&nbsp;</td>
					
	
	<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%"  style="text-align: right">
				 <fmt:formatNumber > <c:out value="${prePaidValue}" /></fmt:formatNumber>&nbsp;</td>
				 
	 <td scope="col" class="listViewThS1" nowrap="nowrap" width="8%"  style="text-align: right">
	 <fmt:formatNumber > <c:out value="${refundValue}" /></fmt:formatNumber></td>				
	<td scope="col" class="listViewThS1" nowrap="nowrap" width="25%"  style="text-align: center;">
				 &nbsp;</td>
					
				
			   	
			   	<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%"  style="text-align: center;" >
				 &nbsp;
				</td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%"  style="text-align: center;" >
				 &nbsp;
				</td>	   		   		   		   				   		   			
			   		   			
			
		
					   	<c:if test="${ theUser.roleId.roleId eq 4 or theUser.roleId.roleId eq 3 or theUser.roleId.roleId eq 0 }">
			   		   	   <td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">&nbsp;
	   </td>
	   </c:if>
	</tr>
<tr>
            
            <td class="listViewPaginationTdS1" align="left"></td>
            <td class="listViewPaginationTdS1" align="right" nowrap="nowrap" colspan=20 >
				
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
	$.get("firstcall?navigation=jsontotalclaimelog&claimId=<c:out value="${claim.claimId }"/>", function(data){
	  //alert("Data: " + data);
	  if(data>0){
	  	blinker();
	  }
	});
});
function blinker(){
	document.getElementById("errorLog").style.backgroundColor="red";
	setTimeout("document.getElementById('errorLog').style.backgroundColor=''", 500);
	setTimeout("blinker()",1500);
}
<%
String nav="";
if(request.getAttribute("navigation").equals("gosearch")||request.getAttribute("navigation").equals("golookup")){
	nav = (String)request.getAttribute("navigation");
}
%>
function tambahi (){
	document.form1.navigation.value = "tambah";
	document.form1.action = "claimitem-form";
	document.form1.method = "GET";
	document.form1.submit();
}
#back-top {
  position: fixed;
  bottom:20px;
  right: 2%;
  z-index: 100; }
 
#up-to-down {
  position: fixed;
  top:20px;
  right: 2%;
  z-index: 0; }
function hapus (idx){
	var delConfirm = window.confirm ("Are You Sure Want To Delete This Entry ?");

	if (delConfirm){
		document.form1.method = "POST";
		document.form1.claimItemId.value = idx;
		document.form1.action = "claimitem";
		document.form1.navigation.value = "delete";
		document.form1.submit();
	}
}
function ubah (idx){
	document.form1.method = "GET";
	document.form1.claimItemId.value = idx;
	document.form1.action = "claimitem-form";
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
	document.form1.action = "claimitem";
	document.form1.method = "POST";
	document.form1.submit();
}
function detil (idx){
	document.form1.method = "POST";
	document.form1.claimItemId.value = idx;
	document.form1.action = "claimitem";
	document.form1.navigation.value = "detail";
	document.form1.submit();

}
function printErrorLog(){
	window.open ("firstcall?navigation=searchclaimerrorlog&claimId=<c:out value="${claim.claimId}" />","Search","width=1024, height=768, menubar=yes, status=no, toolbar=no, scrollbars=yes, resizable=yes");		
}

function adderrorlog (){ 
	window.location.href = "firstcall-form?navigation=claimelog&claimId=<c:out value="${claim.claimId}" />&&memberId=<c:out value="${claim.memberId.memberId}" />";
}
</script>
