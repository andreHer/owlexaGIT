<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ page import="com.ametis.cms.util.WebUtil"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>



<%String navigation = WebUtil.getAttributeString(request,
					"navigation", "");

			%>

<!-- Page Title Start // Should be put on <Title> tag-->
	<head>


		<script type="text/javascript" src="scripts/sugar_3.js"></script>
		<script type="text/javascript" src="scripts/cookie.js"></script>
		<link rel="stylesheet" type="text/css" media="all" href="css/calendar-win2k-cold-1.css" />
		<link rel="stylesheet" type="text/css" media="all" href="css/style.css" />
	
		
<script src="scripts/prototype.js" type="text/javascript"></script>
  <script src="scripts/scriptaculous.js" type="text/javascript"></script>
  	<link rel="stylesheet" type="text/css" href="css/tabcontent.css" />
<script type="text/javascript" src="scripts/tabcontent.js">
/***********************************************
* Tab Content script- © Dynamic Drive DHTML code library (www.dynamicdrive.com)
* This notice MUST stay intact for legal use
* Visit Dynamic Drive at http://www.dynamicdrive.com/ for full source code
***********************************************/
</script>
<link rel="stylesheet" type="text/css" href="css/reset-fonts-grids.css" />
        <!-- CSS for Menu -->

        <link rel="stylesheet" type="text/css" href="css/menu.css" />
 
 
        <!-- Page-specific styles -->

        <!-- Namespace source file -->
<!-- calendar stylesheet -->
	

		<!-- main calendar program -->
		<script type="text/javascript" src="scripts/calendar.js"></script>

		
        <!-- Menu source file -->
       
		
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Aplikanusa Lintasarta - Healthcare Management System</title>


		<link href="css/navigation.css" rel="stylesheet" type="text/css" />
		<script language="javascript" src="scripts/menu.js"></script>
		<script language="javascript" src="scripts/cookie_002.js"></script>

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

<form name="form1" action="member" method="GET">
	<input type="hidden" name="usenumber" value="<c:out value="${usenumber}" />">
	<input type="hidden" name="navigation" value="golookup">
	<input type="hidden" name="arah" value="">
	<input type="hidden" name="url" value="<c:out value="${url}" />" >
	<input type="hidden" name="index" value="<c:out value="${index}" />">
	<input type="hidden" name="memberId" value="<c:out value="${member.memberId}" />">
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tbody>
			<tr>
				<td nowrap="nowrap">
					<h3>
						<img src="images/h3Arrow.gif" border="0">
						&nbsp;Search Member
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
									
									Search Category:
									
									&nbsp;&nbsp;
									

									<select name="searchby" class="inputbox">

										<option value="firstName" <c:if test="${searchby eq \"firstName\"}">selected="true"</c:if>>
											First Name
										</option>
										<option value="lastName" <c:if test="${searchby eq \"lastName\"}">selected="true"</c:if>>
											Last Name
										</option>										
										<option value="customerNumber" <c:if test="${searchby eq \"customerNumber\"}">selected="true"</c:if>>
											Customer Number
										</option>										
										<option value="email" <c:if test="${searchby eq \"email\"}">selected="true"</c:if>>
											Email
										</option>
										<option value="customerPolicyNumber" <c:if test="${searchby eq \"customerPolicyNumber\"}">selected="true"</c:if>>
											Customer Policy Number
										</option>
										<option value="policyAgent" <c:if test="${searchby eq \"policyAgent\"}">selected="true"</c:if>>
											Policy Agent
										</option>
									</select>

									
								</td>
															<td class="dataLabel">
									
									
									<input title="Search [Alt+Shift+Q]" accesskey="Q" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" name="button" value="Search" type="submit">
									
								</td>
							</tr>
							<tr>

								<!--<td class="dataLabel" nowrap="nowrap">
									
									Minimum Date :
									
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
								--><!--<td class="dataLabel" nowrap="nowrap">
									
									Maximum Date :
									
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
								--><!--<td class="dataLabel">
									
									&nbsp;&nbsp;
									
								</td>
								<td align="right">
								</td>
							--></tr>
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
					
					<a href="member?navigation=search&sortby=first_name&searchby=<c:out value="${searchby}" />" class="listViewThLinkS1">Name &nbsp;<img src="images/arrow.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
					
				</td>


				<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">
					
					<a href="member?navigation=search&sortby=customer_number&searchby=<c:out value="${searchby}" />" class="listViewThLinkS1">Number &nbsp; <img src="images/arrow.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"> </a>
					
				</td>


				<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">
					
					<a href="member?navigation=search&sortby=policy_agent&searchby=<c:out value="${searchby}" />" class="listViewThLinkS1">Group &nbsp;<img src="images/arrow.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
					
				</td>

			
				
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">
					
					<a href="member?navigation=search&sortby=email&searchby=<c:out value="${searchby}" />" class="listViewThLinkS1">Status &nbsp;<img src="images/arrow.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
					
				</td>				
			

				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
					
					&nbsp;
					
				</td>
			</tr>


			<c:forEach items="${Members}" var="member" varStatus="status">
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
						
						<c:out value="${member.firstName}" /> &nbsp; <c:out value="${member.lastName}" />
						
					</td>
					<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
						
						<c:out value="${member.customerPolicyNumber}" />
						
					</td>		
			
					<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
						
						<c:out value="${member.memberGroupId.groupName}" />
						
					</td>	
					
					<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
						
						<c:out value="${member.status.status}" />
						
					</td>	
						
										
										
					



					<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top">
					 <c:if test="${url == 'batchclaim-form'}">
						<input type="button" name="pilihButton" value="choose" align="center" onclick="javascript:pilih('<c:out value="${member.parentMember.memberId}" />','<c:out value="${member.parentMember.firstName}" /> <c:out value="${member.parentMember.lastName}" />','<c:out value="${member.parentMember.customerNumber }" />','<c:out value="${member.tipe}" />')" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus">
					 </c:if>
					<c:if test="${url == 'claim-form'}">
						<input type="button" name="pilihButton" value="choose" align="center" onclick="javascript:pilih('<c:out value="${member.memberId}" />','<c:out value="${member.firstName}" /> <c:out value="${member.lastName}" />','<c:out value="${member.customerNumber }" />','<c:out value="${member.tipe}" />')" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus">
					 </c:if>
					</td>
				</tr>

				<tr>
					<td colspan="20" class="listViewHRS1"></td>
				</tr>

			</c:forEach>

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
function pilih (idx,mname,number,jenis){
	
	if (jenis == 'swipe'){
		window.alert("Member Ini adalah Peserta Swipe Card");
	}
	
	var useNumber = document.form1.usenumber.value;
	if (useNumber == 'yes'){
		window.opener.setMember(idx,mname,number);
	}
	else {
		window.opener.setMember(idx,mname);
		}
	window.close();
}
function tambahi (){
	document.form1.navigation.value = "tambah";
	document.form1.action = "member-form";
	document.form1.method = "GET";
	document.form1.submit();
}
function hapus (idx){
	var delConfirm = window.confirm ("Are You Sure Want To Delete This Entry ?");

	if (delConfirm){
		document.form1.method = "GET";
		document.form1.memberId.value = idx;
		document.form1.action = "member";
		document.form1.navigation.value = "delete";
		document.form1.submit();
	}
}
function ubah (idx){
	document.form1.method = "GET";
	document.form1.memberId.value = idx;
	document.form1.action = "member-form";
	document.form1.navigation.value = "edit";
	document.form1.submit();
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
