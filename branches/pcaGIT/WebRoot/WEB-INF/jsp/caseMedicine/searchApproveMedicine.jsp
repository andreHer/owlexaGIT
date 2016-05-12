<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>



<%
String navigation = WebUtil.getAttributeString(request,"navigation","");
%>

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

<form name="form1" action="casemedicine" method="POST">
<input type="hidden" name="navigation" value="gosearch">
<input type="hidden" name="subnavigation" value="${subnavigation }">
<input type="hidden" name="arah" value="">
<input type="hidden" name="index" value="<c:out value="${index}" />">
<input type="hidden" name="caseMedicineId" value="<c:out value="${caseMedicine.caseMedicineId}" />">
<input type="hidden" name="caseId" value="<c:out value="${caseId}" />">
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Search Case Medicine</h3></td>
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

 		   		   		   		   		   		   			<option value="medicine" <c:if test="${searchby eq \"medicine\"}">selected="true"</c:if> >Medicine</option>
			   		   										<option value="caseNumber" <c:if test="${searchby eq \"caseNumber\"}">selected="true"</c:if> >Case Number</option>
			   		   										<option value="memberName" <c:if test="${searchby eq \"memberName\"}">selected="true"</c:if> >Member Name</option>
			   		   										<option value="diagnosis" <c:if test="${searchby eq \"diagnosis\"}">selected="true"</c:if> >Diagnosis</option>
			   		   										<option value="createdBy" <c:if test="${searchby eq \"createdBy\"}">selected="true"</c:if> >Created By</option>
			   		   										<option value="description" <c:if test="${searchby eq \"description\"}">selected="true"</c:if> >Description</option>
			   		   			
			   		   </select>
				
              </td>
			  <td class="dataLabel" nowrap="nowrap"></td>
            <td class="dataLabel">
              <input title="Search [Alt+Q]" accesskey="Q" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" name="button" value="Search" type="submit">                        
			</td>
            </tr>
			        </tbody>
      </table></td>
    </tr>
  </tbody>
</table>


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
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">Medicine</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Case Number</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Member Name</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Diagnosis</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Created Date</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Created By</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Total Item</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Reference Price</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Total Charge</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Status</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="25%">Description</td>
   	   	<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">&nbsp;</td>
	</tr>


	<c:forEach items="${CaseMedicines}" var="caseMedicine" varStatus="status" >
	<%	if (i % 2 == 0) {
			rowclass = "col1";
		} else if (i % 2 != 0) {
			rowclass= "col2";
		}
		i++;
	%>
	 <tr  height="20">
      <td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><%=(i+((indexint-1)*countSet))%></td>
      <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${caseMedicine.medicineId.medicineName}" /></td>
      <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${caseMedicine.caseId.caseNumber}" /></td>
      <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${caseMedicine.caseId.memberId.firstName}" /></td>
      <td class="oddListRowS1" bgcolor="#e7f0fe" valign="top"><c:out value="${caseMedicine.caseId.diagnosis1Id.description}" /></td>
      <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${caseMedicine.createdTime}" /></td>
      <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${caseMedicine.createdBy}" /></td>
      <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right"><c:out value="${caseMedicine.totalUsage}" /></td>
      <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right"><fmt:formatNumber><c:out value="${caseMedicine.referencePrice}" /></fmt:formatNumber></td>
      <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right"><fmt:formatNumber><c:out value="${caseMedicine.totalCharge}" /></fmt:formatNumber></td>
      <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
      	<c:if test="${caseMedicine.status eq 0}">WAIT OF AUTHORIZATION</c:if>
      	<c:if test="${caseMedicine.status eq 1}">APPROVED</c:if>
      	<c:if test="${caseMedicine.status eq -1}">REJECTED</c:if>
      </td>
      <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${caseMedicine.description}" /></td>
        <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top">
        	<input type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:approve(<c:out value="${caseMedicine.caseMedicineId}" />)" value=" Approval "></td>
    </tr>
   
	<tr>
      <td colspan="20" class="listViewHRS1" style="border-bottom: 1px #000 solid;"></td>
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
function approve (idx){
	var delConfirm = window.confirm ("Are You Sure Want To Approve This Medicine ?");

	if (delConfirm){
		document.form1.method = "POST";
		document.form1.caseMedicineId.value = idx;
		document.form1.action = "casemedicine";
		document.form1.navigation.value = "detail";
		document.form1.submit();
	}
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
	document.form1.action = "casemedicine";
	document.form1.method = "POST";
	document.form1.submit();
}
function detil (idx){
	document.form1.method = "POST";
	document.form1.caseMedicineId.value = idx;
	document.form1.action = "casemedicine";
	document.form1.navigation.value = "detail";
	document.form1.submit();

}
</script>
