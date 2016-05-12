<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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


<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;List Provider Room & Board</h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>

<!-- Search Container Start -->
<%@ include file="../mainContentProvider.jsp" %>
<br/>
<form name="form1" action="provideritem" method="POST">

	<input type="hidden" name="navigation" value="list">
	<input type="hidden" name="arah" value="">
	<input type="hidden" name="index" value="<c:out value="${index}" />">
	<input type="hidden" name="providerId" value="<c:out value="${providerId}" />">
	<input type="hidden" name="providerItemId" value="<c:out value="${providerItem.providerItemId}" />">
				
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Search Provider Room & Board</h3></td>
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
 		   				<option value="itemName" <c:if test="${searchby eq \"itemName\"}">selected="true"</c:if> >Item Name</option>
 		   				<option value="itemCode" <c:if test="${searchby eq \"itemCode\"}">selected="true"</c:if> >Item Code</option>			   			
			   			<option value="paymentMethod" <c:if test="${searchby eq \"paymentMethod\"}">selected="true"</c:if> >Payment Method</option>
			   			<option value="invoiceNumber" <c:if test="${searchby eq \"invoiceNumber\"}">selected="true"</c:if> >Invoice Number</option>
	   					<option value="recipient" <c:if test="${searchby eq \"recipient\"}">selected="true"</c:if> >Payment Recipient</option>
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


<!-- Search Container Stop -->




<br />
<c:if test="${theUser.userType eq 2}">
	<c:if test="${theUser.roleId.roleId eq 0 or theUser.roleId.roleId eq 6}">
	<input type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:tambahItem()" value=" Upload Item ">
	
	<input type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:addItem()" value=" Add Item ">

<br />
</c:if>
</c:if>

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
		<td width="1%" nowrap="nowrap" class="listViewThS1" scope="col">No.</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Item</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Item Code</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Super VIP </td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">VIP </td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Kelas I </td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Kelas II </td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Kelas III </td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">R. Jalan</td>
		<c:if test="${theUser.userType eq 2}">
			<c:if test="${theUser.roleId.roleId eq 0 or theUser.roleId.roleId eq 6}">
	   	   		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">&nbsp;</td>
	   	   	</c:if>
   	   	</c:if>
	</tr>


	<c:forEach items="${ProviderItems}" var="providerItem" varStatus="status" >
	<%	if (i % 2 == 0) {
			rowclass = "col1";
		} else if (i % 2 != 0) {
			rowclass= "col2";
		}
		i++;
	%>
	 <tr height="20">
      <td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><%=(i+((indexint-1)*countSet))%></td>
  
         <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="left" valign="top"><c:out value="${providerItem.itemId.itemName}" /></td>
         
         <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="left" valign="top"><c:out value="${providerItem.itemId.itemCode}" /></td>
         <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="right" valign="top">
         	<fmt:formatNumber><c:out value="${providerItem.svip}" /></fmt:formatNumber>
         </td>
         <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="right" valign="top">
         	<fmt:formatNumber><c:out value="${providerItem.vip}" /></fmt:formatNumber>
         </td>                           
         <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="right" valign="top">
         	<fmt:formatNumber><c:out value="${providerItem.kelas1}" /></fmt:formatNumber>
         </td>                           
         <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="right" valign="top">
         	<fmt:formatNumber><c:out value="${providerItem.kelas2}" /></fmt:formatNumber>
         </td>                           
         <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="right" valign="top">
         	<fmt:formatNumber><c:out value="${providerItem.kelas3}" /></fmt:formatNumber>
         </td>
         <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="right" valign="top">
         	<fmt:formatNumber><c:out value="${providerItem.rawatJalan}" /></fmt:formatNumber>
         </td>                                                                                                            
		 <c:if test="${theUser.userType eq 2}">
			<c:if test="${theUser.roleId.roleId eq 0 or theUser.roleId.roleId eq 6}">	   		   		   		
	     <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top">

			<!-- ini default edit table for each data -->
			<a href="javascript:ubah('<c:out value="${providerItem.providerItemId}" />')">
				<img src="images/edit.gif" class="action_icon" alt="Edit" title="Edit"></a>

			<!-- ini default delete table for each data -->
			<a href="javascript:deleteItem('<c:out value="${providerItem.providerItemId}" />')">
				<img src="images/delete.gif" class="action_icon" alt="Delete" title="Delete"></a>		
		  </td>
		  	</c:if>
		  </c:if>
    </tr>
   
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	
	</c:forEach>
	<tr height="20">



<!-- ini default generated table from database -->
		   		   		   		   		   			

		<td scope="col" class="listViewThS1" nowrap="nowrap" colspan="8" width="10%">
	   </td>
	   		
	   		<c:if test="${theUser.userType eq 2}">
	<c:if test="${theUser.roleId.roleId eq 0 or theUser.roleId.roleId eq 6}">
	   <td scope="col" class="listViewThS1" nowrap="nowrap" width="10%" align="center" colspan=2></td>
	   </c:if>
	   </c:if>
	</tr>
	
<tr>
            <td class="listViewPaginationTdS1" align="left"></td>
            <td class="listViewPaginationTdS1" align="right" nowrap="nowrap" colspan=20>
				
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
<script language="javascript">
<%
String nav="";
if(request.getAttribute("navigation").equals("gosearch")||request.getAttribute("navigation").equals("golookup")){
	nav = (String)request.getAttribute("navigation");
}
%>
function tambahItem(){
	
	document.form1.navigation.value = "upload";
	document.form1.action = "provideritem-upload";
	document.form1.method = "GET";
	document.form1.submit();
}
function addItem(){
	
	document.form1.navigation.value = "tambahitem";
	document.form1.action = "provideritem";
	document.form1.method = "GET";
	document.form1.submit();
}
function deleteItem (idx){
	var delConfirm = window.confirm ("Are You Sure Want To Delete This Entry ?");

	if (delConfirm){
		document.form1.method = "POST";
		document.form1.providerItemId.value = idx;
		document.form1.action = "provideritem";
		document.form1.navigation.value = "delete";
		document.form1.submit();
	}
}
function ubah (idx){
	
		document.form1.method = "GET";
		document.form1.providerItemId.value = idx;
		document.form1.action = "provideritem-form";
		document.form1.navigation.value = "ubah";
		document.form1.submit();
}
function selectSemua(){
	window.alert("test");
	var formArray = document.form1.form; 
	var i = 0;
	for (i = 0; i < formArray.length; i++){
		if (formArray[i].type = 'checkbox'){
			formArray[i].checked = "checked";
		}
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
	document.form1.action = "provideritem";
	document.form1.method = "POST";
	document.form1.submit();
}

</script>
