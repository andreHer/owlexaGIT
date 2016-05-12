<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<%
String navigation = WebUtil.getAttributeString(request,"navigation","");
%>

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

<form name="form1" action="policyproduct" method="POST">
	<input type="hidden" name="navigation" value="gosearch">
	<input type="hidden" name="arah" value="">
	<input type="hidden" name="policyId" value="<c:out value="${policyId}" />">
	<input type="hidden" name="index" value="<c:out value="${index}" />">
	<input type="hidden" name="policyProductId" value="<c:out value="${policyProduct.policyProductId}" />">
		

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;List Policy Product</h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>
<ul id="maintab" class="shadetabs">

	<li >
		<a href="policy?navigation=detail&policyId=<c:out value="${policyId}" />" rel="tcontent7">Policy</a>
	</li>	
    
    <li >
		<a href="policybenefit?navigation=list&policyId=<c:out value="${policyId}" />" rel="tcontent7">Policy Benefit</a>
	</li>    
	
	<li >
		<a href="policyclausul?navigation=list&policyId=<c:out value="${policyId}" />" rel="tcontent7">Policy Clausul</a>
	</li>
	<li class="selected" >
		<a href="policyproduct?navigation=list&policyId=<c:out value="${policyId}" />" rel="tcontent7">Product List</a>
	</li>
	<li >
		<a href="member?navigation=listpolicy&policyId=<c:out value="${policyId}" />" rel="tcontent1">Member</a>
	</li>
	
	<li>
		<a href="claim?navigation=listpolicy&policyId=<c:out value="${policyId}" />" rel="tcontent4">Claim</a>
	</li>	
	<li>
		<a href="case?navigation=listpolicy&policyId=<c:out value="${policyId}" />" rel="tcontent5">Case</a>
	</li>	
	<li>
		<a href="policyprovider?navigation=listpolicy&policyId=<c:out value="${policyId}" />" rel="tcontent6">Provider</a>
	</li>	
	<li >
		<a href="policymembermovement?navigation=listpolicy&policyId=<c:out value="${policyId}" />" rel="tcontent1">Member Movement</a>
	</li>
	<li >
		<a href="policymedicine?navigation=listpolicy&policyId=<c:out value="${policyId}" />"	rel="tcontent1">Medicine</a>
	</li>
	<li ><a	href="fund?navigation=listpolicy&policyId=<c:out value="${policyId}" />" rel="tcontent1">Fund History</a></li>
	
	<li><a	href="policycoveragefund?navigation=listpolicy&policyId=<c:out value="${policyId}" />" rel="tcontent1">Policy Coverage</a></li>
</ul>
<br />
	
	<!-- Table Content Start -->
	
<input type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:tambahi()" value="Create">
<br />

 	
<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	<tr>
      <td colspan="20" align="right"><table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tbody>
          <tr>
            <td  align="left"></td>
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
		<td width="2%" nowrap="nowrap" class="listViewThS1" scope="col">No.</td>		
   	   	<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Policy Number</td>
   	   	<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Product Code</td>
   	   	<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Relationship</td>
   	   	<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Gender</td>
   	   	<td scope="col" class="listViewThS1" nowrap="nowrap" width="6%">Price</td>
   	   	<td scope="col" class="listViewThS1" nowrap="nowrap" width="3%">Qty</td>
   	   	<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Total</td>
   	   	<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%"></td>
	</tr>


	<c:forEach items="${PolicyProducts}" var="policyProduct" varStatus="status" >
	<%	if (i % 2 == 0) {
			rowclass = "col1";
		} else if (i % 2 != 0) {
			rowclass= "col2";
		}
		i++;
	%>
	 <tr height="20">
      		<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><%=(i+((indexint-1)*countSet))%></td>
      		<td class="oddListRowS1" align="left" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
      			<c:out value="${policyProduct.policyId.policyNumber}" />
      		</td>
      		<td class="oddListRowS1" align="left" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
      			<c:out value="${policyProduct.productId.productCode}" />
      		</td>
      		<td class="oddListRowS1" align="left" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
      			<c:out value="${policyProduct.relationshipId.relationshipName}" />
      		</td>
      		<td class="oddListRowS1" align="left" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
      			<c:if test="${policyProduct.sex eq 1}">FEMALE</c:if>
      			<c:if test="${policyProduct.sex eq 2}">MALE</c:if>      			
      		</td>
      		<td class="oddListRowS1" align="right" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
      			<fmt:formatNumber><c:out value="${policyProduct.price}" /></fmt:formatNumber>
      		</td>
      		<td class="oddListRowS1" align="right" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
      			<c:out value="${policyProduct.total}" />
      		</td>
      		<td class="oddListRowS1" align="right" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
      			<fmt:formatNumber><c:out value="${policyProduct.total * policyProduct.price}" /></fmt:formatNumber>
      		</td>

      		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top">
		<a href="javascript:detil('<c:out value="${policyProduct.policyProductId}" />')">
				<img src="images/view.gif" class="action_icon" alt="View" title="View"></a>

			<!-- ini default edit table for each data -->
			<a href="javascript:ubah('<c:out value="${policyProduct.policyProductId}" />')">
				<img src="images/edit.gif" class="action_icon" alt="Edit" title="Edit"></a>

			<!-- ini default delete table for each data -->
			<a href="javascript:hapus('<c:out value="${policyProduct.policyProductId}" />')">
				<img src="images/delete.gif" class="action_icon" alt="Delete" title="Delete"></a>
		
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
if(request.getAttribute("navigation").equals("gosearch")||request.getAttribute("navigation").equals("golookup")){
	nav = (String)request.getAttribute("navigation");
}
%>
function tambahi (){
	document.form1.navigation.value = "tambah";
	document.form1.action = "policyproduct-form";
	document.form1.method = "GET";
	document.form1.submit();
}
function hapus (idx){
	var delConfirm = window.confirm ("Are You Sure Want To Delete This Entry ?");

	if (delConfirm){
		document.form1.method = "POST";
		document.form1.policyProductId.value = idx;
		document.form1.action = "policyproduct";
		document.form1.navigation.value = "delete";
		document.form1.submit();
	}
}
function ubah (idx){
	document.form1.method = "GET";
	document.form1.policyProductId.value = idx;
	document.form1.action = "policyproduct-form";
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
	document.form1.action = "policyproduct";
	document.form1.method = "POST";
	document.form1.submit();
}
function detil (idx){
	document.form1.method = "POST";
	document.form1.policyProductId.value = idx;
	document.form1.action = "policyproduct";
	document.form1.navigation.value = "detail";
	document.form1.submit();

}
</script>
