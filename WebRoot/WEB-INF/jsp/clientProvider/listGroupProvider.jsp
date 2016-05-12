<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


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
<!-- Search Container Start -->
<ul id="maintab" class="shadetabs">

	<li >
		<a href="membergroup?navigation=detail&memberGroupId=<c:out value="${memberGroupId}" />" rel="tcontent1">Member Group</a>
	</li>
	
	<li >
		<a href="claim?navigation=listgroup&memberGroupId=<c:out value="${memberGroupId}" />" rel="tcontent4">Claim</a>
	</li>	
	<li >
		<a href="case?navigation=listgroup&memberGroupId=<c:out value="${memberGroupId}" />" rel="tcontent5">Case</a>
	</li>	
	<li class="selected"> 
		<a href="clientprovider?navigation=listgroup&memberGroupId=<c:out value="${memberGroupId}" />" rel="tcontent6">Provider</a>
	</li>	
	<li>
		<a href="costcontainment?navigation=listgroup&memberGroupId=<c:out value="${memberGroupId}" />" rel="tcontent6">Cost Containment</a>
	</li>
	<li>
		<a href="excesscharge?navigation=listgroup&memberGroupId=<c:out value="${memberGroupId}" />" rel="tcontent7">Excess Charge</a>
	</li>	

<li >
		<a href="policy?navigation=listgroup&memberGroupId=<c:out value="${memberGroupId}" />" rel="tcontent7">Policy</a>
	</li>
	<li >
		<a href="quotation?navigation=listgroup&memberGroupId=<c:out value="${memberGroupId}" />" rel="tcontent7">Quotation</a>
	</li>
</ul><br />
<form name="form1" action="clientprovider" method="POST">
<input type="hidden" name="navigation" value="gosearch">
<input type="hidden" name="memberGroupId" value="<c:out value="${memberGroupId}" />" >
<input type="hidden" name="subnavigation" value="<c:out value="${subnavigation}" />" />
<input type="hidden" name="currentnavigation" value="listgroup" />
<input type="hidden" name="arah" value="">
<input type="hidden" name="index" value="<c:out value="${index}" />">
				<input type="hidden" name="clientProviderId" value="<c:out value="${clientProvider.clientProviderId}" />">
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
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
            <form>
            </form>
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
			 	</select>
			 </td>
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
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="25%">&nbsp;Country
		
		</td>
		
	</tr>


	<c:forEach items="${ClientProviders}" var="clientProvider" varStatus="status" >
	<%	if (i % 2 == 0) {
			rowclass = "col1";
		} else if (i % 2 != 0) {
			rowclass= "col2";
		}
		i++;
	%>
	 <tr height="20">
      <td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><%=(i+((indexint-1)*countSet))%></td>
	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="left" valign="top"><c:out value="${clientProvider.providerId.providerName}" /></td>					   		   		
 	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="left" valign="top"><c:out value="${clientProvider.providerId.providerCategoryId.providerCategoryName}" /></td>					   		   							   		   		
 	   		   		   		
	 <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="left" valign="top"><c:out value="${clientProvider.providerId.city}" /></td>
	 <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="left" valign="top"><c:out value="${clientProvider.providerId.province}" /></td>
	 <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="left" valign="top"><c:out value="${clientProvider.providerId.country}" /></td>
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
		<td scope="col" class="listViewThS1" align="center" nowrap="nowrap" width="10%">&nbsp;
	    </td>
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
	document.form1.action = "clientprovider-form";
	document.form1.method = "GET";
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
function ubah (idx){
	document.form1.method = "GET";
	document.form1.clientProviderId.value = idx;
	document.form1.action = "clientprovider-form";
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
