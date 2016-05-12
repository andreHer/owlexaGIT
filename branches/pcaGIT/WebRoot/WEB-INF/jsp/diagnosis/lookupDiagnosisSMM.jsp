<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

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
	
<%String navigation = WebUtil.getAttributeString(request,
					"navigation", "");

			%>

 <!-- Page Title Start // Should be put on <Title> tag-->

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

<form name="form1" action="diagnosis" method="GET">
<input type="hidden" name="navigation" value="golookup">
<input type="hidden" name="parentElem" value="<c:out value="${parentElem}" />">
<input type="hidden" name="arah" value="">
<input type="hidden" name="index" value="<c:out value="${index}" />">
				<input type="hidden" name="diagnosisId" value="<c:out value="${diagnosis.diagnosisId}" />">
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Search Diagnosis</h3></td>
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
            <!-- 
            <td class="dataLabel" nowrap="nowrap">Search Category:
              &nbsp;&nbsp;
              
                
								 <select name="searchby" class="inputbox">

 		   			<option value="description" <c:if test="${searchby eq \"description\"}">selected="true"</c:if> >Description</option>
			   			<option value="diagnosisCode" <c:if test="${searchby eq \"diagnosisCode\"}">selected="true"</c:if> >Diagnosis Code</option>
			   			<option value="diagnosisName" <c:if test="${searchby eq \"diagnosisName\"}">selected="true"</c:if> >Diagnosis Name</option>
			   			<option value="initialSymptom" <c:if test="${searchby eq \"initialSymptom\"}">selected="true"</c:if> >Initial Symptom</option>
			   			<option value="vitalSign" <c:if test="${searchby eq \"vitalSign\"}">selected="true"</c:if> >Vital Sign</option>
			   		   </select>
				
              </td> -->
            <td class="dataLabel">
              <input title="Search [Alt+Shift+Q]" accesskey="Q" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" name="button" value="Search" type="submit">
                          
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
	



 	
<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	<tr>
      <td colspan="20" align="right"><table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tbody>
          <tr>
            <td  align="left">
			<a target="_blank" href="diagnosis?navigation=export&searchby=<c:out value="${searchby}"/>" class="listViewPaginationLinkS1"><img src="images/export.gif" alt="Export" align="absmiddle" border="0" height="7" width="8">&nbsp;Export</a>&nbsp;</td>
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
					<a href="javascript:goleftbgt()" class="listViewPaginationLinkS1">
						Start&nbsp;
					</a>				
				
				<img src="images/previous.gif" alt="Previous" align="absmiddle" border="0" height="10" width="4">
					<a href="javascript:goleft()" class="listViewPaginationLinkS1">
					Previous&nbsp;&nbsp;
					</a>
				<%}

			%>
					<span class="pageNumbers">(<c:out value="${minIndex}" /> - <c:out value="${maxIndex}" /> of <c:out value="${count}" />)</span>&nbsp;&nbsp;
				
				<%if (totalIndex > index) {

			%>
				
				<a href="javascript:goright()" class="listViewPaginationLinkS1">Next&nbsp;
				<img src="images/next.gif" alt="Next" align="absmiddle" border="0" height="10" width="4"></a>&nbsp;&nbsp;
				<a href="javascript:gorightbgt()" class="listViewPaginationLinkS1">End&nbsp;
					<img src="images/end.gif" alt="End" align="absmiddle" border="0" height="10" width="9"></a>
				<%} else {

			%>
				Next&nbsp;
				<img src="images/next_off.gif" alt="Next" align="absmiddle" border="0" height="10" width="4">
				&nbsp;&nbsp;
				End&nbsp;
					<img src="images/end_off.gif" alt="End" align="absmiddle" border="0" height="10" width="9">
				<%}

			%>
			</td>
          </tr>
        </tbody>
      </table></td>
    </tr>
	<tr height="20">
		<td width="5%" nowrap="nowrap" class="listViewThS1" scope="col">
			<img src="images/blank.gif" alt="asd" height="1" width="1">No.</td>		

<!-- ini default generated table from database -->
		   		
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="12%">
				ICD 10 Code &nbsp;</td>
			
		
			   			
							   			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">
				Description &nbsp;</td>
			
			   		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">&nbsp;
	   </td>
	</tr>


			<c:forEach items="${Diagnosiss}" var="diagnosis" varStatus="status">
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
						
						<c:out value="${diagnosis.diagnosisCode}" />
						
					</td>
				
					<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
						
						<a class="linkDetail" href="javascript:pilih('<c:out value="${diagnosis.diagnosisCode}" />','<c:out value="${diagnosis.diagnosisName}" />','<c:out value="${diagnosis.diagnosisId}" />')"><c:out value="${diagnosis.description}" /></a>
						
					</td>
					
					<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top">
						<input type="button" name="pilihButton" value="choose" align="center" onclick="javascript:pilih('<c:out value="${diagnosis.diagnosisCode}" />','<c:out value="${diagnosis.diagnosisName}" />','<c:out value="${diagnosis.diagnosisId}" />')" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus">
					</td>

					
				</tr>

				<tr>
					<td colspan="20" class="listViewHRS1"></td>
				</tr>

			</c:forEach>

			<tr>
            <td  align="left"><a target="_blank" href="diagnosis?navigation=export&searchby=<c:out value="${searchby}"/>" class="listViewPaginationLinkS1"><img src="images/export.gif" alt="Export" align="absmiddle" border="0" height="7" width="8">&nbsp;Export</a>&nbsp;</td>
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
					<a href="javascript:goleftbgt()" class="listViewPaginationLinkS1">
						Start&nbsp;
					</a>				
				
				<img src="images/previous.gif" alt="Previous" align="absmiddle" border="0" height="10" width="4">
					<a href="javascript:goleft()" class="listViewPaginationLinkS1">
					Previous&nbsp;&nbsp;
					</a>
				<%}

			%>
					<span class="pageNumbers">(<c:out value="${minIndex}" /> - <c:out value="${maxIndex}" /> of <c:out value="${count}" />)</span>&nbsp;&nbsp;
				
				<%if (totalIndex > index) {

			%>
				
				<a href="javascript:goright()" class="listViewPaginationLinkS1">Next&nbsp;
				<img src="images/next.gif" alt="Next" align="absmiddle" border="0" height="10" width="4">
				</a>&nbsp;&nbsp;
				<a href="javascript:gorightbgt()" class="listViewPaginationLinkS1">End&nbsp;
					<img src="images/end.gif" alt="End" align="absmiddle" border="0" height="10" width="9">
				</a>
				<%} else {

			%>
				Next&nbsp;
				<img src="images/next_off.gif" alt="Next" align="absmiddle" border="0" height="10" width="4">
				&nbsp;&nbsp;
				End&nbsp;
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

function pilih (code,cname,idx){
	var elem = document.form1.parentElem.value;
	window.opener.setDiagnosis (elem,code,cname,idx);
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
	document.form1.action = "diagnosis";
	document.form1.method = "GET";
	document.form1.submit();
}
function detil (idx){
	document.form1.method = "GET";
	document.form1.clientId.value = idx;
	document.form1.action = "diagnosis";
	document.form1.navigation.value = "detail";
	document.form1.submit();

}
</script>
