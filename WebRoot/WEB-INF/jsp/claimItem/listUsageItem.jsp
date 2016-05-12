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
	<c:set var="claimChargeValue" value="0" scope="request" />
	<c:set var="claimApprovedValue" value="0"  scope="request" />
	<c:set var="claimExcessValue" value="0" scope="request" />
	
<!-- Search Container Start -->

<br />
<form name="form1" action="claimitem" method="POST">
	<input type="hidden" name="navigation" value="<c:out value="${navigation}" />" >
	<input type="hidden" name="prev" value="<c:out value="${prev}" />" >
	<input type="hidden" name="memberId" value="<c:out value="${claimId}" />" >
	<input type="hidden" name="claimId" value="<c:out value="${claimId}" />" >
	<input type="hidden" name="index" value="<c:out value="${index}" />">
	<input type="hidden" name="claimItemId" value="<c:out value="${claimItem.claimItemId}" />">
	<input type="hidden" name="memberBenefitId" value="<c:out value="${memberBenefitId}" />">
	<input type="hidden" name="itemCategoryId" value="<c:out value="${itemCategoryId}" />">

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"></td>
      <td width="100%"></td>
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

 		   		   		<option value="claimItemRemarks" <c:if test="${searchby eq \"claimItemRemarks\"}">selected="true"</c:if> >Claim Item Remarks</option>
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
              <input title="Search [Alt+Shift+Q]" accesskey="Q" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:cari();" name="button" value="Search" type="submit">

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
		<td width="2%" nowrap="nowrap" class="listViewThS1" scope="col"  style="text-align: center;">
			<img src="images/blank.gif" alt="asd" height="1" width="1">No.</td>		

<!-- ini default generated table from database -->
		   		  			
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%"  style="text-align: center;">
				Item Name &nbsp;</td>
			
		
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%"  style="text-align: center;">
				Claim Value &nbsp;
				</td>

	<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%" style="text-align: center;">
				Benefit Paid &nbsp;</td>
	
	<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%"  style="text-align: center;">
				Excess Charges &nbsp;</td>
					
					
	<td scope="col" class="listViewThS1" nowrap="nowrap" width="25%"  style="text-align: center;">
				Description &nbsp;</td>
					
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%"  style="text-align: center;">
				Status &nbsp;
				</td>
			   		   		   		   		   				   		   			
			   		   			
			
		
			   		   	   <td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">&nbsp;
	   </td>
	</tr>


	<c:forEach items="${ClaimItems}" var="claimItem" varStatus="status" >
	<c:set var="claimChargeValue" value="${claimChargeValue+claimItem.claimItemValue}"></c:set>
	<c:set var="claimApprovedValue" value="${claimApprovedValue+claimItem.claimItemApprovedValue}"></c:set>
	<c:set var="claimExcessValue" value="${claimhExcessValue+claimItem.excessValue}"></c:set>
	
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
			
				<fmt:formatNumber><c:out value="${claimItem.claimItemValue}" /></fmt:formatNumber>
			
		</td>  		
		   		   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;">
			
				<fmt:formatNumber><c:out value="${claimItem.claimItemApprovedValue}" /></fmt:formatNumber>
			
		</td>		   		   		
		   		   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;">
			
				<fmt:formatNumber><c:out value="${claimItem.excessValue}" /></fmt:formatNumber>
			
		</td>		   		   		   	
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${claimItem.claimId.claimNumber}" />
			
		</td>  			   		   		   		   		   		   		   		
	
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${claimItem.claimItemStatus.caseStatusName}" />
			
		</td>  			   		   		   		   		   		   		   		
			   		
					   		   		
					   		         <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top">
					   		         
		<a href="javascript:detil('<c:out value="${claimItem.claimItemId}" />')">
				<img src="images/view.gif" class="action_icon" alt="View" title="View"></a>

			<c:if test="${claimItem.claimItemStatus.caseStatusId eq 1}">
			<!-- ini default edit table for each data -->
			<a href="javascript:ubah('<c:out value="${claimItem.claimItemId}" />')">
				<img src="images/edit.gif" class="action_icon" alt="Edit" title="Edit"></a>

			<!-- ini default delete table for each data -->
			<a href="javascript:hapus('<c:out value="${claimItem.claimItemId}" />')">
				<img src="images/delete.gif" class="action_icon" alt="Delete" title="Delete"></a>
			</c:if>
	  </td>
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
			
		
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%"  style="text-align: right">
				<fmt:formatNumber > <c:out value="${claimChargeValue}" /></fmt:formatNumber>
				</td>

	<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%" style="text-align: right">
				<fmt:formatNumber > <c:out value="${claimApprovedValue}" /></fmt:formatNumber>&nbsp;</td>
	
	<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%"  style="text-align: right">
				 <fmt:formatNumber > <c:out value="${claimExcessValue}" /></fmt:formatNumber>&nbsp;</td>
					
					
	<td scope="col" class="listViewThS1" nowrap="nowrap" width="25%"  style="text-align: center;">
				 &nbsp;</td>
					
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%"  style="text-align: center;">
				 &nbsp;
				</td>
			   		   		   		   		   				   		   			
			   		   			
			
		
			   		   	   <td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">&nbsp;
	   </td>
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
<%
String nav="";
if(request.getAttribute("navigation").equals("gosearch")||request.getAttribute("navigation").equals("golookup") 
|| request.getAttribute("navigation").equals("usagetrack")){
	nav = (String)request.getAttribute("navigation");
}
%>
function tambahi (){
	document.form1.navigation.value = "tambah";
	document.form1.action = "claimitem-form";
	document.form1.method = "GET";
	document.form1.submit();
}
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
	<c:choose>
		<c:when test="${navigation eq 'usagetrack'}">
			document.form1.navigation.value = "usagetrack";
			document.form1.memberBenefitId.value = <c:out value="${memberBenefitId}" />;	
		</c:when>
	</c:choose>
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
</script>
