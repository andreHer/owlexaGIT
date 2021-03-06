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


<br />
<form name="form1" action="memberprovider" method="POST">
<input type="hidden" name="navigation" value="gosearchmyprovider">
<input type="hidden" name="arah" value="">
<input type="hidden" name="index" value="<c:out value="${index}" />">
<input type="hidden" name="memberProviderId" value="<c:out value="${memberProvider.memberProviderId}" />">
<input type="hidden" name="memberId" value="<c:out value="${memberId}" />">
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Search Member Provider</h3></td>
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

 		   		   		   			<option value="providerName" <c:if test="${searchby eq \"providerName\"}">selected="true"</c:if> >Provider Name</option>
			   			<option value="province" <c:if test="${searchby eq \"province\"}">selected="true"</c:if> >Province</option>
			   		   		   			<option value="city" <c:if test="${searchby eq \"city\"}">selected="true"</c:if> >City</option>
			   		   		   		   		   		   		   		   		   		   		   </select>
				
              </td>
			
            <td class="dataLabel">
              <input title="Search [Alt+Q]" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" accesskey="Q" class="button" name="button" value="Search" type="submit">
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
		
		
		<%
		String nampak = "";
		if(navigation!=null&&navigation.equals("gosearch")){
		}else{
			nampak = " style=\"display: none;\"";
		}
		%>
		
</div>
	<!-- Table Toolbar Stop -->
	

<br />
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

<!-- ini default generated table from database -->
		   		   		   			
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">&nbsp;Provider
		
		</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">&nbsp;Provider Category
		
		</td>
		<td scope="col" class="listViewThS1" width="10%">&nbsp;City
		
		</td>
		<td scope="col" class="listViewThS1" width="10%">&nbsp;Province
		
		</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">&nbsp;Country
		
		</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">PPK1</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">PPK2</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">PPK3</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%"></td>
	</tr>


	<c:forEach items="${MemberProviders}" var="memberProvider" varStatus="status" >
	<%	if (i % 2 == 0) {
			rowclass = "col1";
		} else if (i % 2 != 0) {
			rowclass= "col2";
		}
		i++;
	%>
	 <tr onMouseOver="setPointer(this, '910cbdf4-ffae-5c1a-db84-4434fa305b0a', 'over', '#e7f0fe', '#FFFFFF', '');" onMouseOut="setPointer(this, '910cbdf4-ffae-5c1a-db84-4434fa305b0a', 'out', '#e7f0fe', '#FFFFFF', '');" onMouseDown="setPointer(this, '910cbdf4-ffae-5c1a-db84-4434fa305b0a', 'click', '#e7f0fe', '#FFFFFF', '');" height="20">
      <td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><%=(i+((indexint-1)*countSet))%></td>

	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="left" valign="top"><c:out value="${memberProvider.providerId.providerName}" /></td>					   		   		
 	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="left" valign="top"><c:out value="${memberProvider.providerId.providerCategoryId.providerCategoryName}" /></td>					   		   							   		   		
 	   		   		   		
	 <td class="oddListRowS1" bgcolor="#e7f0fe" align="left" valign="top"><c:out value="${memberProvider.providerId.city}" /></td>
	 <td class="oddListRowS1" bgcolor="#e7f0fe" align="left" valign="top"><c:out value="${memberProvider.providerId.province}" /></td>
	 <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="left" valign="top"><c:out value="${memberProvider.providerId.country}" /></td>
	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top"><c:if test="${memberProvider.ppk1 eq 1}">Y</c:if><c:if test="${memberProvider.ppk1 eq 0}">N</c:if></td>					   		
	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top"><c:if test="${memberProvider.ppk2 eq 1}">Y</c:if><c:if test="${memberProvider.ppk2 eq 0}">N</c:if></td>
	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top"><c:if test="${memberProvider.ppk3 eq 1}">Y</c:if><c:if test="${memberProvider.ppk3 eq 0}">N</c:if></td>
					   		   		   		
	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top">
		<input title="Survey [Alt+Shift+T]" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:survey(<c:out value="${memberProvider.providerId.providerId}" />)" name="btnSurvey" value=" Survey Kepuasan " type="button">		
		
	  </td>
    </tr>
   
	<tr>
      <td colspan="20" class="listViewHRS1" style="border-bottom: 1px solid #000;"></td>
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
if(request.getAttribute("navigation").equals("gosearchmyprovider")||request.getAttribute("navigation").equals("golookup")){
	nav = (String)request.getAttribute("navigation");
}
%>
function survey (idx){
	var delConfirm = window.confirm ("Are You Sure Want To Review This Provider ?");

	if (delConfirm){
		document.form1.method = "POST";
		document.form1.memberProviderId.value = idx;
		document.form1.action = "memberprovider";
		document.form1.navigation.value = "presurvey";
		document.form1.submit();
	}
}
function ubah (idx){
	document.form1.method = "GET";
	document.form1.memberProviderId.value = idx;
	document.form1.action = "memberprovider-form";
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
	document.form1.navigation.value = "gosearchmyprovider";
	document.form1.action = "memberprovider";
	document.form1.method = "POST";
	document.form1.submit();
}
function detil (idx){
	document.form1.method = "POST";
	document.form1.memberProviderId.value = idx;
	document.form1.action = "memberprovider";
	document.form1.navigation.value = "detail";
	document.form1.submit();

}
</script>
