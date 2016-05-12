<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ page import="com.ametis.cms.util.WebUtil"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>



<%String navigation = WebUtil.getAttributeString(request,
					"navigation", "");

			%>

<!-- Page Title Start // Should be put on <Title> tag-->
	<head>

		<link rel="stylesheet" type="text/css" media="all" href="css/style.css" />
	
		
		<link rel="stylesheet" type="text/css" href="css/reset-fonts-grids.css" />
        		
		<title>Healthcare Management System</title>


		<link href="css/navigation.css" rel="stylesheet" type="text/css" />		

	</head>
<!-- Page Title Stop-->
<%String alert = (String) request.getAttribute("alert");
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

			if (alert != null && !alert.trim().equals("")) {%>
<div id="warning" align="center">
	<c:out value="${alert}"></c:out>
</div>
<%}%>

<%String rowclass = "";
			int i = 0;
			int indexint = Integer.parseInt(request.getAttribute("index")
					.toString());
			WebUtil.getAttributeInteger(request, "index", 0).intValue();

			%>
<!-- Search Container Start -->

<form name="form1" action="product" method="GET">
	<input type="hidden" name="usenumber" value="<c:out value="${usenumber}" />">
	<input type="hidden" name="navigation" value="golookup">
	<input type="hidden" name="arah" value="">
	<input type="hidden" name="url" value="<c:out value="${url}" />" >
	<input type="hidden" name="index" value="<c:out value="${index}" />">
	
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tbody>
			<tr>
				<td nowrap="nowrap">
					<h3>
						<img src="images/h3Arrow.gif" border="0">
						&nbsp;Search Product
					</h3>
				</td>
				<td width="100%">
					<img src="images/blank.gif" height="1" width="1">
				</td>
			</tr>
		</tbody>
	</table>
	<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
		<tbody>
			<tr>
				<td>
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tbody>
							<tr>
								<form>
								</form>
								<td class="dataLabel" nowrap="nowrap">
									
									Search Keyword:
									
									&nbsp;&nbsp;
									
									<input size="20" name="searchtext" class="dataField" value="<c:out value="${searchtext}"/>" type="text">
									
								</td>
								<td class="dataLabel" nowrap="nowrap">
									
									Type:
									
									&nbsp;&nbsp;
									

									<select name="productType" class="inputbox">

										<option value="-1">--- All Type ---</option>
										<c:forEach items="${caseCategories}" var="cc">
											<option value="<c:out value="${cc.caseCategoryId}" />" <c:if test="${productType eq cc.caseCategoryId}">selected="true"</c:if> >
												<c:out value="${cc.caseCategoryName}" />
											</option>
										</c:forEach>
										
									</select>
									
									
									
								</td>
								<td class="dataLabel" nowrap="nowrap">
									
									Category:
									
									&nbsp;&nbsp;
									

									<select name="searchby" class="inputbox">

										<option value="productName" <c:if test="${searchby eq \"productName\"}">selected="true"</c:if>>
											Product Name
										</option>
										<option value="productCode" <c:if test="${searchby eq \"productCode\"}">selected="true"</c:if>>
											Product Code
										</option>										
										
										<option value="clientName" <c:if test="${searchby eq \"clientName\"}">selected="true"</c:if>>
											Client Name
										</option>
									</select>

									
								</td>
															<td class="dataLabel">
									
									
									<input title="Search [Alt+Shift+Q]" accesskey="Q" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" name="button" value="Search" type="submit">
									
								</td>
							</tr>
													</tbody>
					</table>
				</td>
			</tr>
		</tbody>
	</table>


	<!-- Search Container Stop -->


	<!-- Table Container Start -->



	<div class="table_container">
		<!-- Table Toolbar Start -->


		<%String nampak = "";
			if (navigation != null && navigation.equals("golookup")) {
			} else {
				nampak = " style=\"display: none;\"";
			}

			%>

	</div>
	<!-- Table Toolbar Stop -->

	<!-- Table Content Start -->

	
	<br />


	<table class="listView" cellspacing="0" cellpadding="0" width="100%">
		<tbody>
			<tr>
				<td colspan="20" align="right">
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tbody>
							<tr>
								<td  align="left">
									<a target="_blank" href="member?navigation=export&searchby=<c:out value="${searchby}"/>" class="listViewPaginationLinkS1"><img src="images/export.gif" alt="Export" align="absmiddle" border="0" height="7" width="8">&nbsp;Export</a>&nbsp;
								</td>
								<td  align="right" nowrap="nowrap">

									<%if (index == 1) {

			%>
									<img src="images/start_off.gif" alt="Start" align="absmiddle" border="0" height="10" width="9">
									Start&nbsp;

									<img src="images/previous_off.gif" alt="Previous" align="absmiddle" border="0" height="10" width="4">
									Previous&nbsp;&nbsp;
									<%} else if ((index - 1) > 0) {

			%>
									<img src="images/start.gif" alt="Start" align="absmiddle" border="0" height="10" width="9">
									<a href="javascript:goleftbgt()" class="listViewPaginationLinkS1"> Start&nbsp; </a>

									<img src="images/previous.gif" alt="Previous" align="absmiddle" border="0" height="10" width="4">
									<a href="javascript:goleft()" class="listViewPaginationLinkS1"> Previous&nbsp;&nbsp; </a>
									<%}

			%>
									<span class="pageNumbers">(<c:out value="${minIndex}" /> - <c:out value="${maxIndex}" /> of <c:out value="${count}" />)</span>&nbsp;&nbsp;

									<%if (totalIndex > index) {

			%>

									<a href="javascript:goright()" class="listViewPaginationLinkS1">Next&nbsp; <img src="images/next.gif" alt="Next" align="absmiddle" border="0" height="10" width="4"></a>&nbsp;&nbsp; <a href="javascript:gorightbgt()" class="listViewPaginationLinkS1">End&nbsp;
										<img src="images/end.gif" alt="End" align="absmiddle" border="0" height="10" width="9"></a>
									<%} else {

			%>
									Next&nbsp;
									<img src="images/next_off.gif" alt="Next" align="absmiddle" border="0" height="10" width="4">
									&nbsp;&nbsp; End&nbsp;
									<img src="images/end_off.gif" alt="End" align="absmiddle" border="0" height="10" width="9">
									<%}

			%>
								</td>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>
			<tr height="20">
				<td width="6%" nowrap="nowrap" class="listViewThS1" scope="col">
					<img src="images/blank.gif" alt="asd" height="1" width="1">
					No.
				</td>

				<!-- ini default generated table from database -->

				<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">
					
					Product Code &nbsp;
					
				</td>


				<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">
					
					Client &nbsp; 
					
				</td>

				<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">
					
					Product Type &nbsp;
					
				</td>

				<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">
					
					Product Type&nbsp;
					
				</td>


				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
					
					&nbsp;
					
				</td>
			</tr>


			<c:forEach items="${Products}" var="product" varStatus="status">
				<%if (i % 2 == 0) {
				rowclass = "col1";
			} else if (i % 2 != 0) {
				rowclass = "col2";
			}
			i++;

			%>
				<tr onMouseOver="'" onMouseOut=""
					onMouseDown="" height="20">
					<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
						<%=(i + ((indexint - 1) * countSet))%>
					</td>

					<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
						
						<c:out value="${product.productCode}" /> 
						
					</td>
					<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
						
						<c:out value="${product.clientId.clientName}" />
						
					</td>		
					<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
						<c:out value="${product.caseCategory.caseCategoryName}" />
					
					</td>	
				
					<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
						
						<c:out value="${product.serviceClass}" />
						
					</td>	
							
										
					



					<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top">
						<input type="checkbox" name="productSelection" value="<c:out value="${product.productCode}" />">
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

				<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">
					
				</td>


				<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">
					
				</td>

				<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">
					
					
				</td>

				<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">
					
					
				</td>


				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
					
					&nbsp;
					<input type="button" name="pilihButton" value="choose" align="center" onclick="javascript:pilih()" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus">
					
				</td>
			</tr>


			<tr>
				<td  align="left">
					<a target="_blank" href="member?navigation=export&searchby=<c:out value="${searchby}"/>" class="listViewPaginationLinkS1"><img src="images/export.gif" alt="Export" align="absmiddle" border="0" height="7" width="8">&nbsp;Export</a>&nbsp;
				</td>
				<td  align="right" nowrap="nowrap" colspan="20">

					<%if (index == 1) {

			%>
					<img src="images/start_off.gif" alt="Start" align="absmiddle" border="0" height="10" width="9">
					Start&nbsp;

					<img src="images/previous_off.gif" alt="Previous" align="absmiddle" border="0" height="10" width="4">
					Previous&nbsp;&nbsp;
					<%} else if ((index - 1) > 0) {

			%>
					<img src="images/start.gif" alt="Start" align="absmiddle" border="0" height="10" width="9">
					<a href="javascript:goleftbgt()" class="listViewPaginationLinkS1"> Start&nbsp; </a>

					<img src="images/previous.gif" alt="Previous" align="absmiddle" border="0" height="10" width="4">
					<a href="javascript:goleft()" class="listViewPaginationLinkS1"> Previous&nbsp;&nbsp; </a>
					<%}

			%>
					<span class="pageNumbers">(<c:out value="${minIndex}" /> - <c:out value="${maxIndex}" /> of <c:out value="${count}" />)</span>&nbsp;&nbsp;

					<%if (totalIndex > index) {

			%>

					<a href="javascript:goright()" class="listViewPaginationLinkS1">Next&nbsp; <img src="images/next.gif" alt="Next" align="absmiddle" border="0" height="10" width="4"> </a>&nbsp;&nbsp; <a href="javascript:gorightbgt()" class="listViewPaginationLinkS1">End&nbsp;
						<img src="images/end.gif" alt="End" align="absmiddle" border="0" height="10" width="9"> </a>
					<%} else {

			%>
					Next&nbsp;
					<img src="images/next_off.gif" alt="Next" align="absmiddle" border="0" height="10" width="4">
					&nbsp;&nbsp; End&nbsp;
					<img src="images/end_off.gif" alt="End" align="absmiddle" border="0" height="10" width="9">
					<%}

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
if(request.getAttribute("navigation").equals("golookup")||request.getAttribute("navigation").equals("golookup")){
	nav = (String)request.getAttribute("navigation");
}
%>
function pilih (){
	
	var productCode = "";
	
	var productCodeArray = document.form1.productSelection;
	
	for ( var i = 0; i < productCodeArray.length; i++){
		
		if (productCodeArray[i].checked){
			productCode += " " + productCodeArray[i].value;
		}
		
		
	}
	

	
	window.opener.appendProduct(productCode);
	window.close();
}

function goleft(){
	document.form1.navigation.value = "golookup";
	document.form1.arah.value="kiri";
	document.form1.method = "GET";
	document.form1.submit();
}
function goleftbgt(){
	document.form1.navigation.value = "golookup";
	document.form1.arah.value="kiribgt";
	document.form1.method = "GET";
	document.form1.submit();
}
function goright(){
	document.form1.navigation.value = "golookup";
	document.form1.arah.value="kanan";
	document.form1.method = "GET";
	document.form1.submit();
}
function gorightbgt(){
	document.form1.navigation.value = "golookup";
	document.form1.arah.value="kananbgt";
	document.form1.method = "GET";
	document.form1.submit();
}
function go(){
	document.form1.navigation.value = "golookup";
	document.form1.submit();
}
function cari (){
	document.form1.navigation.value = "golookup";
	document.form1.action = "member";
	document.form1.method = "GET";
	document.form1.submit();
}
function detil (idx){
	document.form1.method = "GET";
	document.form1.memberId.value = idx;
	document.form1.action = "member";
	document.form1.navigation.value = "detail";
	document.form1.submit();

}
</script>
