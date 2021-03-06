<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<head>



</head>


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

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;List Client Provider</h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>

<!-- Search Container Start -->

<%@ include file="../mainContentClient.jsp" %>
<br/>
<form name="form1" action="clientprovider" method="POST">
<input type="hidden" name="navigation" value="list">
<input type="hidden" name="arah" value="">
<input type="hidden" name="clientId" value="<c:out value="${clientId}" />" >
<input type="hidden" name="index" value="<c:out value="${index}" />">
				<input type="hidden" name="clientProviderId" value="<c:out value="${clientProvider.clientProviderId}" />">
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"></td>
      <td width="100%"></td>
    </tr>
  </tbody>
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Search Client Provider</h3></td>
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

            <td class="dataLabel" nowrap="nowrap">Search Keyword:
              &nbsp;&nbsp;
              
				<input size="20" name="searchtext" class="dataField" value="<c:out value="${searchtext}"/>" type="text">
              </td>
<td class="dataLabel" nowrap="nowrap" align="right">Search Category:
              &nbsp;&nbsp;
             </td>
			 <td class="dataLabel" nowrap="nowrap">
			 	<select name="searchby">
			 		<option value="">-- SELECT CATEGORY --</option>
			 		<option value="providerName" <c:if test="${searchby eq \"providerName\"}">selected</c:if>>Provider Name</option>
			 		<option value="province" <c:if test="${searchby eq \"province\"}">selected</c:if>>Province / Propinsi</option>
			 		<option value="country" <c:if test="${searchby eq \"country\"}">selected</c:if>>Country / Negara</option>
			 		<option value="city" <c:if test="${searchby eq \"city\"}">selected</c:if>>City / Kota</option>
			 		<option value="providerCategoryName" <c:if test="${searchby eq \"providerCategoryName\"}">selected</c:if>>Provider Category</option>
			 		<option value="providerCode" <c:if test="${searchby eq \"providerCode\"}">selected</c:if>>Provider Code</option>
			 	</select>
			 </td>
            <td class="dataLabel">
              <input title="Search [Alt+Q]" onClick="javascript:cari();" accesskey="Q" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" name="button" value="Search" type="submit">
                          
			</td>
            </tr>
			
        </tbody>
      </table></td>
    </tr>
  </tbody>
</table>


<!-- Search Container Stop -->


<!-- Table Container Start -->


	

 	
<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	<tr>
      <td colspan="20" align="right"><table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tbody>
          <tr>
            <td  align="left">
			</td>
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
		<td width="6%" nowrap="nowrap" class="listViewThS1" scope="col">
			<img src="images/blank.gif" alt="asd" height="1" width="1">No.</td>		

<!-- ini default generated table from database -->
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="25%">&nbsp;Provider
		
		</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="25%">&nbsp;Provider Category
		
		</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="25%">&nbsp;City
		
		</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="25%">&nbsp;Province
		
		</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">&nbsp;IP</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">&nbsp;OP</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">&nbsp;DE</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">&nbsp;MT</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">&nbsp;OPTIC</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">&nbsp;PPK1</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">&nbsp;PPK2</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">&nbsp;PPK3</td>
	</tr>


	<c:forEach items="${ClientProviders}" var="clientProvider" varStatus="status" >
	<%	if (i % 2 == 0) {
			rowclass = "col1";
		} else if (i % 2 != 0) {
			rowclass= "col2";
		}
		i++;
	%>
	 <tr onMouseOver="" onMouseOut="" onMouseDown="" height="20">
     <td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><%=(i+((indexint-1)*countSet))%></td>
	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="left" valign="top"><c:out value="${clientProvider.providerId.providerName}" /></td>					   		   		
 	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="left" valign="top"><c:out value="${clientProvider.providerId.providerCategoryId.providerCategoryName}" /></td>					   		   							   		   		
 	   		   		   		
	 <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="left" valign="top"><c:out value="${clientProvider.providerId.city}" /></td>
	 <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="left" valign="top"><c:out value="${clientProvider.providerId.province}" /></td>
	 
      	 
	 	<td  class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top">&nbsp;<c:if test="${clientProvider.inpatient eq 1}">Y</c:if><c:if test="${clientProvider.inpatient eq 0 or clientProvider.outpatient eq null}">N</c:if></td>
		<td  class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top">&nbsp;<c:if test="${clientProvider.outpatient eq 1}">Y</c:if><c:if test="${clientProvider.outpatient eq 0 or clientProvider.outpatient eq null}">N</c:if></td>
		<td  class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top">&nbsp;<c:if test="${clientProvider.dental eq 1}">Y</c:if><c:if test="${clientProvider.dental eq 0 or clientProvider.outpatient eq null}">N</c:if></td>
		<td  class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top">&nbsp;<c:if test="${clientProvider.maternity eq 1}">Y</c:if><c:if test="${clientProvider.maternity eq 0 or clientProvider.outpatient eq null}">N</c:if></td>
		<td  class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top">&nbsp;<c:if test="${clientProvider.optical eq 1}">Y</c:if><c:if test="${clientProvider.optical eq 0 or clientProvider.outpatient eq null}">N</c:if></td>
		<td  class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top">&nbsp;<c:if test="${clientProvider.ppk1 eq 1}">Y</c:if><c:if test="${clientProvider.ppk1 eq 0 or clientProvider.outpatient eq null}">N</c:if></td>
		<td  class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top">&nbsp;<c:if test="${clientProvider.ppk2 eq 1}">Y</c:if><c:if test="${clientProvider.ppk2 eq 0 or clientProvider.outpatient eq null}">N</c:if></td>
		<td  class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top">&nbsp;<c:if test="${clientProvider.ppk3 eq 1}">Y</c:if><c:if test="${clientProvider.ppk3 eq 0 or clientProvider.outpatient eq null}">N</c:if></td>
    </tr>
   
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	
	</c:forEach>
<tr height="20">
		<td width="6%" nowrap="nowrap" class="listViewThS1" scope="col">
			<img src="images/blank.gif" alt="asd" height="1" width="1"></td>		

<!-- ini default generated table from database -->
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="25%">&nbsp;
		
		</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="25%">&nbsp;
		
		</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="25%">&nbsp;
		
		</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="25%">&nbsp;
		
		</td>
		
		<td scope="col" class="listViewThS1" align="center" nowrap="nowrap" width="10%" colspan=10></td>
	</tr>
	
<tr>
            <td  align="left">
            </td>
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
	

<script language="javascript">

<%

String nav="";
if(request.getAttribute("navigation").equals("gosearch")||request.getAttribute("navigation").equals("golookup")){
	nav = (String)request.getAttribute("navigation");
}
%>
function ubah (idx){
	document.form1.method = "GET";
	document.form1.clientProviderId.value = idx;
	document.form1.action = "clientprovider-form";
	document.form1.navigation.value = "edit";
	document.form1.submit();
}
function hapus (idx){
	var delConfirm = window.confirm ("Are You Sure Want To Delete This Entry ?");

	if (delConfirm){
		document.form1.method = "POST";
		document.form1.clientProviderId.value = idx;
		document.form1.action = "clientprovider";
		document.form1.navigation.value = "delete";
		document.form1.submit();
	}
}
function tambahi (){
	document.form1.navigation.value = "addclientprovider";
	document.form1.action = "provider";
	document.form1.method = "GET";
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
		<c:when test="${navigation eq 'list'}">
			document.form1.navigation.value = "list";
			document.form1.clientId.value = <c:out value="${clientId}" />
		</c:when>
	</c:choose>
	document.form1.action = "clientprovider";
	document.form1.method = "POST";
	document.form1.submit();
}
function detil (idx){
	document.form1.method = "POST";
	document.form1.clientProviderId.value = idx;
	document.form1.action = "clientprovider";
	document.form1.navigation.value = "detail";
	document.form1.submit();

}
</script>