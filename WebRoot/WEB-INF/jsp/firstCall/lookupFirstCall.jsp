<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<%String navigation = WebUtil.getAttributeString(request,
					"navigation", "");

			%>

 <!-- Page Title Start // Should be put on <Title> tag-->

<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%//		// theme
			String theme = (String) request.getParameter("theme");
			if (theme == null || theme.trim().equals("")) {
				theme = (String) request.getSession().getAttribute("theme");
			}
			if (theme == null) {
				theme = "style.css";
			}
			// 	end

		%>


<!DOCTYPE html PUBLIC "-//W3C//DTD html 4.01 Transitional//EN">
<html>
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
			int i=0;
			int indexint = Integer.parseInt(request.getAttribute("index").toString());
			WebUtil.getAttributeInteger(request,"index",0).intValue();

			%>
<!-- Search Container Start -->

<form name="form1" action="firstcall" method="GET">
<input type="hidden" name="navigation" value="<c:out value="${navigation}" />">
<input type="hidden" name="memberId" value="<c:out value="${memberId}" />">
<input type="hidden" name="caseId" value="<c:out value="${caseId}" />">
<input type="hidden" name="claimId" value="<c:out value="${claimId}" />">
<input type="hidden" name="callId" value="<c:out value="${firstCall.callId}" />">
<input type="hidden" name="index" value="<c:out value="${index}" />">
<input type="hidden" name="arah" value="">
<table border="0" cellpadding="0" cellspacing="0" width="98%" align="center">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Search Error Log</h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>
<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="98%" align="center">
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
				 	<option value="callerName" name="callerName" <c:if test="${searchby eq \"callerName\"}">selected="true"</c:if> >Caller Name</option>
				 	<option value="telephone" name="telephone" <c:if test="${searchby eq \"telephone\"}">selected="true"</c:if> >Telephone</option>
				 	<option value="location" name="location" <c:if test="${searchby eq \"location\"}">selected="true"</c:if> >Location</option>
				 	<option value="category" name="category" <c:if test="${searchby eq \"category\"}">selected="true"</c:if> >Category</option>
					<option value="description" name="description" <c:if test="${searchby eq \"description\"}">selected="true"</c:if> >Description</option>
					<option value="remarks" name="remarks" <c:if test="${searchby eq \"remarks\"}">selected="true"</c:if> >Remarks</option>
					<option value="modifiedBy" name="modifiedBy" <c:if test="${searchby eq \"modifiedBy\"}">selected="true"</c:if> >Modified By</option>
				</select>
			</td>
            <td class="dataLabel">
				<input title="Search [Alt+Shift+Q]" accesskey="Q" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" name="button" value="Search" onClick="javascript:findErrorLog()" type="submit">
				<input title="Download" accesskey="Q" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" name="button" value="Download" onClick="javascript:downloadLog()" type="submit">
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

 	
<table class="listView" cellspacing="0" cellpadding="0" width="98%" align="center">
	<tbody>
	<tr>
      <td colspan="20" align="right"><table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tbody>
          <tr>
            <%--<td  align="left">
			<a target="_blank" href="broker?navigation=export&searchby=<c:out value="${searchby}"/>" class="listViewPaginationLinkS1"><img src="images/export.gif" alt="Export" align="absmiddle" border="0" height="7" width="8">&nbsp;Export</a>&nbsp;</td>
             --%>
            <td  align="right" nowrap="nowrap">
				
				<%if (index == 1) {

			%>
				<img src="images/start.gif" alt="Start" align="absmiddle" border="0" height="10" width="9">
					Start&nbsp;
									
				<img src="images/previous.gif" alt="Previous" align="absmiddle" border="0" height="10" width="4">
					Previous&nbsp;&nbsp;					
				<%} else if ((index - 1) > 0) {

			%>
					<a href="javascript:goleftbgt()" class="listViewPaginationLinkS1">
						<img src="images/start_off.gif" alt="Start" align="absmiddle" border="0" height="10" width="9">Start&nbsp;
					</a>				
				
					<a href="javascript:goleft()" class="listViewPaginationLinkS1">
						<img src="images/previous_off.gif" alt="Previous" align="absmiddle" border="0" height="10" width="4">Previous&nbsp;&nbsp;
					</a>
				<%}

			%>
					<span class="pageNumbers">(<c:out value="${minIndex}" /> - <c:out value="${maxIndex}" /> of <c:out value="${count}" />)</span>&nbsp;&nbsp;
				
				<%
					if (totalIndex > index) {
				%>
				
				<a href="javascript:goright()" class="listViewPaginationLinkS1">Next&nbsp;
					<img src="images/next_off.gif" alt="Next" align="absmiddle" border="0" height="10" width="4">
				</a>&nbsp;&nbsp;
				<a href="javascript:gorightbgt()" class="listViewPaginationLinkS1">End&nbsp;
					<img src="images/end_off.gif" alt="End" align="absmiddle" border="0" height="10" width="9">
				</a>
				<%} else {%>
				<a href="javascript:goright()" class="listViewPaginationLinkS1">
				Next&nbsp;
				<img src="images/next.gif" alt="Next" align="absmiddle" border="0" height="10" width="4"></a>
				&nbsp;&nbsp;
				End&nbsp;
					<img src="images/end.gif" alt="End" align="absmiddle" border="0" height="10" width="9">
				<%}

			%>
			</td>
          </tr>
        </tbody>
      </table></td>
    </tr>
		<tr height="20">		
			<td nowrap="nowrap" class="listViewThS1" scope="col">
				<img src="images/blank.gif" alt="asd" height="1" width="1">
				No.
			</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Priority &nbsp;
			</td>
			<td scope="col" class="listViewThS1" width="10%">
				Call Time &nbsp;
			</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">
				Caller Name &nbsp;
			</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">
				Telephone &nbsp;
			</td>
			<td scope="col" class="listViewThS1" width="10%">
				Location &nbsp;
			</td>
			<td scope="col" class="listViewThS1" width="19%">
				Category &nbsp;
			</td>
			<td scope="col" class="listViewThS1" width="19%">
				Description &nbsp;
			</td>
			<td scope="col" class="listViewThS1" width="19%">
				Remarks &nbsp;
			</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap">
				Modified By &nbsp;
			</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap">
				Modified Time &nbsp;
			</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap">
				Follow Up &nbsp;
			</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap">
			</td>
		</tr>
		<c:forEach items="${FirstCalls}" var="firstCall" varStatus="status" >
			<%if (i % 2 == 0) {
				rowclass = "col1";
			} else if (i % 2 != 0) {
				rowclass = "col2";
			}
			i++;

			%>
				<tr onMouseOver="" onMouseOut=""
					onMouseDown="" height="20">
					<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
						<%=(i + ((indexint - 1) * countSet))%>
					</td>
					<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
						<c:out value="${firstCall.priority.priorityCode}" />
					</td>
					<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top">
						<c:out value="${firstCall.callStartTime}" />
					</td>
					<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
						<c:out value="${firstCall.callerName}" />
					</td>
					<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
						<c:out value="${firstCall.telephone}" />
					</td>
					<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top">
						<c:out value="${firstCall.location}" />
					</td>
					<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top">
						<c:out value="${firstCall.callCategoryId.callCategoryName}" />
					</td>
					<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top">
						<c:out value="${firstCall.description}" />
					</td>
					<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top">
						<c:out value="${firstCall.remarks}" />
					</td>
					<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top">
						<c:choose>
							<c:when test="${not empty firstCall.modifiedBy}">
								<c:out value="${firstCall.modifiedBy}" />
							</c:when>
							<c:otherwise>
								<c:out value="${firstCall.createdBy}" />
							</c:otherwise>
						</c:choose>
					</td>
					<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top">
						<c:out value="${firstCall.modifiedTime}" />
					</td>
					<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top">
						<%-- <c:if test="${firstCall.followup eq 'Y'}">
							&#10004;
						</c:if> --%>
						<c:choose>
							<c:when test="${firstCall.followup eq 'Y'}"> &#10004; </c:when>
							<c:otherwise> &#10006; </c:otherwise>
						</c:choose>
					</td>
					<td width="30" align="center" class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top">
						<%-- <c:if test="${firstCall.status.caseStatusId eq 1 }"> --%>
							<a href="javascript:pilih('<c:out value="${firstCall.callId}" />')">
								<img src="images/edit.gif" class="action_icon" alt="Edit" title="Edit"></a>
						<%-- </c:if> --%>
					</td>
				</tr>
				<tr>
			    	<td colspan="5" class="listViewHRS1"></td>
			    </tr>
	</c:forEach>
	
<tr>
            <td  align="right" nowrap="nowrap" colspan="20">
				
				<%if (index == 1) {

			%>
				<img src="images/start.gif" alt="Start" align="absmiddle" border="0" height="10" width="9">
					Start&nbsp;
									
				<img src="images/previous.gif" alt="Previous" align="absmiddle" border="0" height="10" width="4">
					Previous&nbsp;&nbsp;					
				<%} else if ((index - 1) > 0) {

			%>
				<a href="javascript:goleftbgt()" class="listViewPaginationLinkS1">
					<img src="images/start_off.gif" alt="Start" align="absmiddle" border="0" height="10" width="9">Start&nbsp;
				</a>				
				
				<a href="javascript:goleft()" class="listViewPaginationLinkS1">
					<img src="images/previous_off.gif" alt="Previous" align="absmiddle" border="0" height="10" width="4">Previous&nbsp;&nbsp;
				</a>
				<%}

			%>
					<span class="pageNumbers">(<c:out value="${minIndex}" /> - <c:out value="${maxIndex}" /> of <c:out value="${count}" />)</span>&nbsp;&nbsp;
				
				<%
					if (totalIndex > index) {
				%>
				
				<a href="javascript:goright()" class="listViewPaginationLinkS1">Next&nbsp;
					<img src="images/next_off.gif" alt="Next" align="absmiddle" border="0" height="10" width="4">
				</a>&nbsp;&nbsp;
				<a href="javascript:gorightbgt()" class="listViewPaginationLinkS1">End&nbsp;
					<img src="images/end_off.gif" alt="End" align="absmiddle" border="0" height="10" width="9">
				</a>
				<%} else {%>
				<a href="javascript:goright()" class="listViewPaginationLinkS1">Next&nbsp;
					<img src="images/next.gif" alt="Next" align="absmiddle" border="0" height="10" width="4"></a>
				&nbsp;&nbsp;
				End&nbsp;
					<img src="images/end.gif" alt="End" align="absmiddle" border="0" height="10" width="9">
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
if(request.getAttribute("navigation").equals("gosearchcaseerrorlog")||request.getAttribute("navigation").equals("gosearchmembererrorlog")||
request.getAttribute("navigation").equals("gosearchclaimerrorlog")||request.getAttribute("navigation").equals("searchcaseerrorlog")||request.getAttribute("navigation").equals("searchmembererrorlog")||
request.getAttribute("navigation").equals("searchclaimerrorlog")){
	nav = (String)request.getAttribute("navigation");
}
%>

function pilih (idx){
	//window.opener.detilErrorLog(idx);
	detilErrorLog(idx);
	window.close();
}
function goleft(){
	document.form1.navigation.value = "<%=nav%>";
	document.form1.arah.value="kiri";
	document.form1.method = "GET";
	document.form1.submit();
}
function goleftbgt(){
	document.form1.navigation.value = "<%=nav%>";
	document.form1.arah.value="kiribgt";
	document.form1.method = "GET";
	document.form1.submit();
}
function goright(){
	document.form1.navigation.value = "<%=nav%>";
	document.form1.arah.value="kanan";
	document.form1.method = "GET";
	document.form1.submit();
}
function gorightbgt(){
	document.form1.navigation.value = "<%=nav%>";
	document.form1.arah.value="kananbgt";
	document.form1.method = "GET";
	document.form1.submit();
}
function go(){
	document.form1.navigation.value = "<%=nav%>";
	document.form1.submit();
}
function cari (){
	document.form1.navigation.value = "golookup";
	document.form1.action = "firstcall";
	document.form1.method = "GET";
	document.form1.submit();
}
function detil (idx){
	document.form1.method = "GET";
	document.form1.callId.value = idx;
	document.form1.action = "firstcall";
	document.form1.navigation.value = "detail";
	document.form1.submit();

}
function findErrorLog(){
	<c:choose>
		<c:when test="${navigation eq 'searchcaseerrorlog' }">
			document.form1.navigation.value = "gosearchcaseerrorlog";
		</c:when>
		<c:when test="${navigation eq 'searchmembererrorlog' }">
			document.form1.navigation.value = "gosearchmembererrorlog";
		</c:when>
		<c:when test="${navigation eq 'searchclaimerrorlog' }">
			document.form1.navigation.value = "gosearchclaimerrorlog";
		</c:when>
		<c:otherwise>
			document.form1.navigation.value = <c:out value="${navigation }"/>;
		</c:otherwise>
	</c:choose>

	document.form1.action = "firstcall";
	document.form1.method = "GET";
	document.form1.submit();
}
function downloadLog(){
	<c:choose>
		<c:when test="${navigation eq 'searchcaseerrorlog' }">
			document.form1.navigation.value = "downloadcaseerrorlog";
		</c:when>
		<c:when test="${navigation eq 'searchmembererrorlog' }">
			document.form1.navigation.value = "downloadmembererrorlog";
		</c:when>
		<c:when test="${navigation eq 'searchclaimerrorlog' }">
			document.form1.navigation.value = "downloadclaimerrorlog";
		</c:when>
		<c:otherwise>
			document.form1.navigation.value = <c:out value="${navigation }"/>;
		</c:otherwise>
	</c:choose>

	document.form1.action = "firstcall";
	document.form1.submit();
}
function detilErrorLog (idx){
	var updatetype;
	<c:choose>
		<c:when test="${navigation eq 'searchcaseerrorlog' or  navigation eq 'gosearchcaseerrorlog'}">
			updatetype = "caseelog";
		</c:when>
		<c:when test="${navigation eq 'searchmembererrorlog' or  navigation eq 'gosearchmembererrorlog'}">
			updatetype = "memberelog";
		</c:when>
		<c:when test="${navigation eq 'searchclaimerrorlog' or navigation eq 'gosearchclaimerrorlog'}">
			updatetype = "claimelog";
		</c:when>
	</c:choose>
	window.opener.location.href = "firstcall-form?navigation=update"+updatetype+"&memberId=<c:out value="${memberId}" />&callId="+idx;
}
</script>
