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
	String navigation = WebUtil.getAttributeString(request,
			"navigation", "");
%>


<!-- Page Title Stop-->
<%
	String alert = (String) request.getAttribute("alert");
	int index = 0;
	int totalIndex = 0;
	int count = 0;
	int countSet = 0;

	try {
		index = ((Integer) request.getAttribute("index")).intValue();
		count = ((Integer) request.getAttribute("count")).intValue();
		countSet = ((Integer) request.getAttribute("countSet"))
				.intValue();
		totalIndex = ((Integer) request.getAttribute("halAkhir"))
				.intValue();

	} catch (Exception e) {
	}

	if (alert != null && !alert.trim().equals("")) {
%>
<div id="warning" align="center"><c:out value="${alert}"></c:out>
</div>
<%
	}
%>

<%
	String rowclass = "";
	int i = 0;
	int indexint = Integer.parseInt(request.getAttribute("index")
			.toString());
	WebUtil.getAttributeInteger(request, "index", 0).intValue();
%>


<!-- Search Container Start -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;List Policy Provider</h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>

<%@ include file="../mainContentPolicy.jsp" %>

<br />
<form name="form1" action="policyprovider" method="POST">
	<input type="hidden" name="navigation" value="listpolicy"> 
	<input type="hidden" name="arah" value=""> 
	<input type="hidden" name="index" value="<c:out value="${index}" />"> 
	
	<input type="hidden" name="id" value="" />
	<input type="hidden" name="policyId" value="<c:out value="${policyId }" />" />

<table class="tabForm" border="0" cellpadding="0" cellspacing="0"
	width="100%">
	<tbody>
		<tr>
			<td>
			<table border="0" cellpadding="0" cellspacing="0" width="100%">
				<tbody>
					<tr>
						<form></form>
						<td class="dataLabel" nowrap="nowrap">
							Search Keyword: &nbsp;&nbsp;  
							<input size="20" name="searchtext" class="dataField" value="<c:out value="${searchtext}"/>" type="text"> </td>
						<td class="dataLabel" nowrap="nowrap">Search
						Category: &nbsp;&nbsp;  <select name="searchby"
							class="inputbox">

							<option value="createdBy"
								<c:if test="${searchby eq \"providerName\"}">selected="true"</c:if>>Provider
							Name</option>
							<option value="deletedBy"
								<c:if test="${searchby eq \"province\"}">selected="true"</c:if>>Province</option>
							<option value="modifiedBy"
								<c:if test="${searchby eq \"city\"}">selected="true"</c:if>>City</option>
						</select> </td>

						<td class="dataLabel"> <input
							title="Search [Alt+Q]" accesskey="Q"
							class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
							name="button" value="Search" onClick="javascript:cari();" type="submit"></td>
					</tr>

				</tbody>
			</table>
			</td>
		</tr>
	</tbody>
</table>

<br />
<input type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:tambahi()" value="Add Provider"> 
&nbsp; <input type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:upload()" value="Upload Provider">
<br />

<br />

<table class="listView" cellspacing="0" cellpadding="0">
	<tbody>
		<tr>
			<td colspan="20" align="right">
			<table border="0" cellpadding="0" cellspacing="0" width="100%">
				<tbody>
					<tr>
						<td class="listViewPaginationTdS1" align="left"></td>
						<td class="listViewPaginationTdS1" align="right" nowrap="nowrap">

						<%
							if (index == 1) {
						%> <img src="images/start_off.gif" alt="Start" align="absmiddle"
							border="0" height="10" width="9"> Start&nbsp; <img
							src="images/previous_off.gif" alt="Previous" align="absmiddle"
							border="0" height="10" width="4"> Previous&nbsp;&nbsp; <%
 	} else if ((index - 1) > 0) {
 %> <img src="images/start.gif" alt="Start" align="absmiddle"
							border="0" height="10" width="9"> <a
							href="javascript:goleftbgt()" class="listViewPaginationLinkS1">
						Start&nbsp; </a> <img src="images/previous.gif" alt="Previous"
							align="absmiddle" border="0" height="10" width="4"> <a
							href="javascript:goleft()" class="listViewPaginationLinkS1">
						Previous&nbsp;&nbsp; </a> <%
 	}
 %> <span class="pageNumbers">(<c:out value="${minIndex}" />
						- <c:out value="${maxIndex}" /> of <c:out value="${count}" />)</span>&nbsp;&nbsp;

						<%
							if (totalIndex > index) {
						%> <a href="javascript:goright()" class="listViewPaginationLinkS1">Next&nbsp;
						<img src="images/next.gif" alt="Next" align="absmiddle" border="0"
							height="10" width="4"></a>&nbsp;&nbsp; <a
							href="javascript:gorightbgt()" class="listViewPaginationLinkS1">End&nbsp;
						<img src="images/end.gif" alt="End" align="absmiddle" border="0"
							height="10" width="9"></a> <%
 	} else {
 %> Next&nbsp; <img src="images/next_off.gif" alt="Next"
							align="absmiddle" border="0" height="10" width="4">
						&nbsp;&nbsp; End&nbsp; <img src="images/end_off.gif" alt="End"
							align="absmiddle" border="0" height="10" width="9"> <%
 	}
 %>
						</td>
					</tr>
				</tbody>
			</table>
			</td>
		</tr>
		<tr height="20">
			<td width="6%" nowrap="nowrap" class="listViewThS1" scope="col">
			<img src="images/blank.gif" alt="asd" height="1" width="1">No.</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">&nbsp;Provider</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">&nbsp;Provider Category</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">&nbsp;City	</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">&nbsp;Province</td>			
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">PPK1</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">PPK2</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">PPK3</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">IP</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">OP</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">MT</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">DE</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">OPT</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">MCU/LAB</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Method</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Total Member</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Capitation Fee</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%"></td>
			
		</tr>


		<c:forEach items="${PolicyProviders}" var="policyProvider"
			varStatus="status">
			<%
				if (i % 2 == 0) {
						rowclass = "col1";
					} else if (i % 2 != 0) {
						rowclass = "col2";
					}
					i++;
			%>
			<tr height="20">
				<td class="oddListRowS1" align="center" bgcolor="#e7f0fe"
					nowrap="nowrap" valign="top"><%=(i + ((indexint - 1) * countSet))%></td>


				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="left" valign="top"><c:out value="${policyProvider.providerId.providerName}" /></td>					   		   		
 	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="left" valign="top"><c:out value="${policyProvider.providerId.providerCategoryId.providerCategoryName}" /></td>					   		   							   		   		
 	   		   		   		
	 <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="left" valign="top"><c:out value="${policyProvider.providerId.city}" /></td>
	 <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="left" valign="top"><c:out value="${policyProvider.providerId.province}" /></td>	 
	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top"><c:if test="${policyProvider.ppk1 eq 1}">Y</c:if><c:if test="${policyProvider.ppk1 eq 0}">N</c:if></td>					   		
	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top"><c:if test="${policyProvider.ppk2 eq 1}">Y</c:if><c:if test="${policyProvider.ppk2 eq 0}">N</c:if></td>
	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top"><c:if test="${policyProvider.ppk3 eq 1}">Y</c:if><c:if test="${policyProvider.ppk3 eq 0}">N</c:if></td>
	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top"><c:if test="${policyProvider.inpatient eq 1}">Y</c:if><c:if test="${policyProvider.inpatient eq 0}">N</c:if></td>
	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top"><c:if test="${policyProvider.outpatient eq 1}">Y</c:if><c:if test="${policyProvider.outpatient eq 0}">N</c:if></td>
	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top"><c:if test="${policyProvider.maternity eq 1}">Y</c:if><c:if test="${policyProvider.maternity eq 0}">N</c:if></td>
	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top"><c:if test="${policyProvider.dental eq 1}">Y</c:if><c:if test="${policyProvider.dental eq 0}">N</c:if></td>
	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top"><c:if test="${policyProvider.optical eq 1}">Y</c:if><c:if test="${policyProvider.optical eq 0}">N</c:if></td>
	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top"><c:if test="${policyProvider.mcuLab eq 1}">Y</c:if><c:if test="${policyProvider.mcuLab eq 0}">N</c:if></td>
	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top"><c:if test="${policyProvider.serviceType eq 1}">Capitation</c:if><c:if test="${policyProvider.serviceType eq 0}">Fee For Service</c:if></td>
	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top"><c:out value="${policyProvider.totalMember}" /></td>
	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top"><fmt:formatNumber><c:out value="${policyProvider.capitationFeePerMonth}" /></fmt:formatNumber></td>
				

				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap"
					align="center" valign="top"> <!-- ini default edit table for each data --> <a
					href="javascript:ubah('<c:out value="${policyProvider.policyProviderId}" />')">
				<img src="images/edit.gif" class="action_icon" alt="Edit"
					title="Edit"></a> <!-- ini default delete table for each data -->
				<a href="javascript:hapus('<c:out value="${policyProvider.policyProviderId}" />')">
				<img src="images/delete.gif" class="action_icon" alt="Delete"
					title="Delete"></a></td>
			</tr>

			<tr>
				<td colspan="20" class="listViewHRS1"></td>
			</tr>

		</c:forEach>

		<tr>
			<td class="listViewPaginationTdS1" align="left"></td>
			<td class="listViewPaginationTdS1" align="right" nowrap="nowrap"
				colspan="20">
			<%
				if (index == 1) {
			%> <img src="images/start_off.gif" alt="Start" align="absmiddle"
				border="0" height="10" width="9"> Start&nbsp; <img
				src="images/previous_off.gif" alt="Previous" align="absmiddle"
				border="0" height="10" width="4"> Previous&nbsp;&nbsp; <%
 	} else if ((index - 1) > 0) {
 %> <img src="images/start.gif" alt="Start" align="absmiddle"
				border="0" height="10" width="9"> <a
				href="javascript:goleftbgt()" class="listViewPaginationLinkS1">
			Start&nbsp; </a> <img src="images/previous.gif" alt="Previous"
				align="absmiddle" border="0" height="10" width="4"> <a
				href="javascript:goleft()" class="listViewPaginationLinkS1">
			Previous&nbsp;&nbsp; </a> <%
 	}
 %> <span class="pageNumbers">(<c:out value="${minIndex}" />
			- <c:out value="${maxIndex}" /> of <c:out value="${count}" />)</span>&nbsp;&nbsp;

			<%
				if (totalIndex > index) {
			%> <a href="javascript:goright()" class="listViewPaginationLinkS1">Next&nbsp;
			<img src="images/next.gif" alt="Next" align="absmiddle" border="0"
				height="10" width="4"> </a>&nbsp;&nbsp; <a
				href="javascript:gorightbgt()" class="listViewPaginationLinkS1">End&nbsp;
			<img src="images/end.gif" alt="End" align="absmiddle" border="0"
				height="10" width="9"> </a> <%
 	} else {
 %> Next&nbsp; <img src="images/next_off.gif" alt="Next"
				align="absmiddle" border="0" height="10" width="4">
			&nbsp;&nbsp; End&nbsp; <img src="images/end_off.gif" alt="End"
				align="absmiddle" border="0" height="10" width="9"> <%
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
	document.form1.navigation.value = "listpolicy";
	document.form1.action = "policyprovider-form";
	document.form1.method = "GET";
	document.form1.submit();
}
function hapus (idx){
	var delConfirm = window.confirm ("Are You Sure Want To Delete This Entry ?");

	if (delConfirm){
		document.form1.method = "POST";
		document.form1.id.value = idx;
		document.form1.action = "policyprovider";
		document.form1.navigation.value = "delete";
		document.form1.submit();
	}
}
function ubah (idx){
	document.form1.method = "GET";
	document.form1.id.value = idx;
	document.form1.action = "policyprovider-form";
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
	document.form1.method = "POST";
	<c:choose>
		<c:when test="${navigation eq 'listpolicy'}">
			document.form1.navigation.value = "listpolicy";
			document.form1.action = "policyprovider";
			document.form1.policyId.value = <c:out value="${policyId}" />
		</c:when>
	</c:choose>
	document.form1.submit();
}
function detil (idx){
	document.form1.method = "POST";
	document.form1.id.value = idx;
	document.form1.action = "policyprovider";
	document.form1.navigation.value = "detail";
	document.form1.submit();

}
</script>
