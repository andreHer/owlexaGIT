<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>

<script type="text/javascript" src="scripts/tinymce/tinymce.min.js"></script>
<script type="text/javascript">
tinymce.init({
    selector: "textarea",
    readonly : true,
	toolbar: "false",
	menubar: "false",
	height: '100%'
 });
</script>



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

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Search Policy Coverage Fund</h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>
<!-- Search Container Start -->

<%@ include file="../mainContentPolicy.jsp" %>

<br />
<form name="form1" action="policycoveragefund" method="POST">
	<input type="hidden" name="navigation" value="gosearch">
	<input type="hidden" name="arah" value="">
	<input type="hidden" name="policyId" value="<c:out value="${policyId}" />">
	<input type="hidden" name="index" value="<c:out value="${index}" />">
	<input type="hidden" name="policyCoverageFundId" value="<c:out value="${policyCoverageFund.policyCoverageFundId}" />">
		



<!-- Search Container Stop -->

<br />
<input title="Complete [Alt+Shift+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:tambahi()" name="complete" value=" Add Coverage " type="button">

<input title="Complete [Alt+Shift+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:addFund()" name="btnFund" value=" Add Fund " type="button">

<input title="Complete [Alt+Shift+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:addExcessFund()" name="btnExcess" value=" Add Excess Fund " type="button">
<br />
 	
<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	<tr>
      <td colspan="20" align="right"><table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tbody>
          <tr>
            <td class="listViewPaginationTdS1" align="left"></td>
            <td class="listViewPaginationTdS1" align="right" nowrap="nowrap">
				
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
		<td scope="col" class="listViewThS1" nowrap="nowrap" align="center" width="10%">Policy Number</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" align="center" width="10%">Case Category</td>		
		<td scope="col" class="listViewThS1" align="center" width="8%">Current ASO Fund</td>
		<td scope="col" class="listViewThS1" align="center" width="8%">Total ASO Fund</td>
		<td scope="col" class="listViewThS1" align="center" width="8%">Initial ASO Fund</td>
		<td scope="col" class="listViewThS1" align="center" width="8%">Current Excess Fund</td>
		<td scope="col" class="listViewThS1" align="center" width="8%">Total Excess Fund</td>
		<td scope="col" class="listViewThS1" align="center" width="8%">Initial Excess Fund</td>
		<td scope="col" class="listViewThS1" align="center" width="8%">Warning Fund</td>
		<td scope="col" class="listViewThS1" align="center" width="8%">Block Fund</td>
		<td scope="col" class="listViewThS1" align="center" width="8%">Warning Excess</td>
		<td scope="col" class="listViewThS1" align="center" width="8%">Block Excess</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" align="center" width="5%">Warning %</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap"  align="center" width="5%">Block %</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap"  align="center" width="3%">&nbsp;</td>
	</tr>


	<c:forEach items="${PolicyCoverageFunds}" var="policyCoverageFund" varStatus="status" >
	<%	if (i % 2 == 0) {
			rowclass = "col1";
		} else if (i % 2 != 0) {
			rowclass= "col2";
		}
		i++;
	%>
	 <tr height="20">
      	<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><%=(i+((indexint-1)*countSet))%></td>					   		   		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="left" valign="top"><c:out value="${policyCoverageFund.policyId.policyNumber}" /></td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="left" valign="top"><c:out value="${policyCoverageFund.caseCategoryId.caseCategoryName}" /></td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="right" valign="top"><fmt:formatNumber><c:out value="${policyCoverageFund.currentFundValue}" /></fmt:formatNumber></td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="right" valign="top"><fmt:formatNumber><c:out value="${policyCoverageFund.totalAsoValue}" /></fmt:formatNumber></td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="right" valign="top"><fmt:formatNumber><c:out value="${policyCoverageFund.initialAsoFundValue}" /></fmt:formatNumber></td>
		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="right" valign="top"><fmt:formatNumber><c:out value="${policyCoverageFund.excessFundValue}" /></fmt:formatNumber></td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="right" valign="top"><fmt:formatNumber><c:out value="${policyCoverageFund.totalExcessValue}" /></fmt:formatNumber></td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="right" valign="top"><fmt:formatNumber><c:out value="${policyCoverageFund.initialExcessFundValue}" /></fmt:formatNumber></td>
		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="right" valign="top"><fmt:formatNumber><c:out value="${policyCoverageFund.warningAsoValue}" /></fmt:formatNumber></td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="right" valign="top"><fmt:formatNumber><c:out value="${policyCoverageFund.minimumAsoValue}" /></fmt:formatNumber></td>
		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="right" valign="top"><fmt:formatNumber><c:out value="${policyCoverageFund.warningExcessValue}" /></fmt:formatNumber></td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="right" valign="top"><fmt:formatNumber><c:out value="${policyCoverageFund.minimumExcessValue}" /></fmt:formatNumber></td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="right" valign="top"><fmt:formatNumber><c:out value="${policyCoverageFund.fundWarningPercentage}" /></fmt:formatNumber> %</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="right" valign="top"><fmt:formatNumber><c:out value="${policyCoverageFund.blockWarningPercentage}" /></fmt:formatNumber> %</td>
		
		
				
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top">
				<a href="javascript:ubah('<c:out value="${policyCoverageFund.policyCoverageFundId}" />')">
								<img src="images/edit.gif" class="action_icon" alt="Edit" title="Edit"></a>			
		</td>
		
    </tr>
   
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	
	</c:forEach>
	
<tr>
            <td class="listViewPaginationTdS1" align="left"></td>
            <td class="listViewPaginationTdS1" align="right" nowrap="nowrap" colspan="20">
				
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
	document.form1.action = "policycoveragefund-form";
	document.form1.method = "GET";
	document.form1.submit();
}
function addFund (){
	document.form1.navigation.value = "addcoveragefund";
	document.form1.action = "fund-form";
	document.form1.method = "GET";
	document.form1.submit();
}
function addExcessFund (){
	document.form1.navigation.value = "addcoverageexcess";
	document.form1.action = "fund-form";
	document.form1.method = "GET";
	document.form1.submit();
}
function hapus (idx){
	var delConfirm = window.confirm ("Are You Sure Want To Delete This Entry ?");

	if (delConfirm){
		document.form1.method = "POST";
		document.form1.policyCoverageFundId.value = idx;
		document.form1.action = "policycoveragefund";
		document.form1.navigation.value = "delete";
		document.form1.submit();
	}
}
function ubah (idx){
	document.form1.method = "GET";
	document.form1.policyCoverageFundId.value = idx;
	document.form1.action = "policycoveragefund-form";
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
	document.form1.action = "policycoveragefund";
	document.form1.method = "POST";
	document.form1.submit();
}
function detil (idx){
	document.form1.method = "POST";
	document.form1.policyCoverageFundId.value = idx;
	document.form1.action = "policycoveragefund";
	document.form1.navigation.value = "detail";
	document.form1.submit();

}
</script>
