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
							<div id="warning" style="text-align: center; color: #FFF; font-size: 13px; font-weight: bold;">
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

<form name="form1" action="user" method="POST">
<input type="hidden" name="navigation" value="gosearch">
<input type="hidden" name="sortcolumn" value="<c:out value="${sortcolumn}" />">
<input type="hidden" name="sortorder" value="<c:out value="${sortorder}" />">
<input type="hidden" name="columntosort" value="<c:out value="${columntosort}" />">
<input type="hidden" name="arah" value="">
<input type="hidden" name="index" value="<c:out value="${index}" />">
<input type="hidden" name="userId" value="<c:out value="${user.userId}" />">
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Search User</h3></td>
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

 		   			<option value="username" <c:if test="${searchby eq \"username\"}">selected="true"</c:if> >Username</option>
			   		   			<option value="firstName" <c:if test="${searchby eq \"firstName\"}">selected="true"</c:if> >First Name</option>
			   			<option value="lastName" <c:if test="${searchby eq \"lastName\"}">selected="true"</c:if> >Last Name</option>
			   			<option value="email" <c:if test="${searchby eq \"email\"}">selected="true"</c:if> >Email</option>
  		   		   		   </select>
				
              </td>
			  <td class="dataLabel" nowrap="nowrap">Status:
              &nbsp;&nbsp;
              
                <select name="status">		
   					<option value="-1">-- All Status --</option>			
					<option value="1">Active</option>
					<option value="2">Blocked</option>					
				</select>
				
              </td>
            <td class="dataLabel">
              <input title="Search [Alt+Shift+Q]" accesskey="Q" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" name="button" value="Search" type="submit">
                          
			</td>
            </tr>
        </tbody>
      </table></td>
    </tr>
  </tbody>
</table>

<br />	
<input type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:tambahi()" value="Create">
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
		<td width="2%" nowrap="nowrap" class="listViewThS1" scope="col">
			<img src="images/blank.gif" alt="asd" height="1" width="1">No.</td>
	
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Username &nbsp;
			<input type="hidden" name="sortByOrderUserName" value="<c:out value="${sortUserName?'true':'false'}" />">
			<a href="javascript:carisort('username')" class="listViewThLinkS1">
			<img src="images/arrow_<c:out value="${sortUserName?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
		</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">Name &nbsp;
			<input type="hidden" name="sortByOrderName" value="<c:out value="${sortName?'true':'false'}" />">
			<a href="javascript:carisort('user')" class="listViewThLinkS1">
			<img src="images/arrow_<c:out value="${sortName?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
		</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">User Type &nbsp;
			<input type="hidden" name="sortByOrderType" value="<c:out value="${sortType?'true':'false'}" />">
			<a href="javascript:carisort('usertype')" class="listViewThLinkS1">
			<img src="images/arrow_<c:out value="${sortType?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
		</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Role &nbsp;
			<input type="hidden" name="sortByOrderRole" value="<c:out value="${sortRole?'true':'false'}" />">
			<a href="javascript:carisort('userrole')" class="listViewThLinkS1">
			<img src="images/arrow_<c:out value="${sortRole?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
		</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">Email &nbsp;
			<input type="hidden" name="sortByOrderEmail" value="<c:out value="${sortEmail?'true':'false'}" />">
			<a href="javascript:carisort('email')" class="listViewThLinkS1">
			<img src="images/arrow_<c:out value="${sortEmail?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
		</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Status &nbsp;
			<input type="hidden" name="sortByOrderStatus" value="<c:out value="${sortStatus?'true':'false'}" />">
			<a href="javascript:carisort('status')" class="listViewThLinkS1">
			<img src="images/arrow_<c:out value="${sortStatus?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
		</td>
   	   	<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">&nbsp;</td>
	</tr>


	<c:forEach items="${Users}" var="user" varStatus="status" >
	<%	if (i % 2 == 0) {
			rowclass = "col1";
		} else if (i % 2 != 0) {
			rowclass= "col2";
		}
		i++;
	%>
	 <tr height="20">
      	<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><%=(i+((indexint-1)*countSet))%></td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<a href="user?navigation=detail&userId=<c:out value="${user.userId}" />" class="linkDetail"><c:out value="${user.username}" /></a>			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${user.firstName}" /> <c:out value="${user.lastName}" />			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:if test="${user.userType eq 1}">PERUSAHAAN/GROUP</c:if>
			<c:if test="${user.userType eq 2}">TPA</c:if>
			<c:if test="${user.userType eq 3}">CLIENT/INSURANCE</c:if>
			<c:if test="${user.userType eq 4}">PROVIDER</c:if>
			<c:if test="${user.userType eq 5}">BROKER</c:if>
			<c:if test="${user.userType eq 6}">MANAGEMENT</c:if>
			<c:if test="${user.userType eq 7}">MEMBER</c:if>
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${user.roleId.roleName}" />		
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${user.email}" />			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
						
			<c:if test="${user.status.statusId eq -1}">PENDING</c:if>
			<c:if test="${user.status.statusId eq 1}">ACTIVE</c:if>
			<c:if test="${user.status.statusId eq -3}">PENDING CHANGEPLAN</c:if>
			<c:if test="${user.status.statusId eq -2}">BLOCKED</c:if>
			<c:if test="${user.status.statusId eq 2}">TERMINATED</c:if>
			<c:if test="${user.status.statusId eq 3}">RESIGNED</c:if>
			<c:if test="${user.status.statusId eq 4}">INACTIVE</c:if>
			<c:if test="${user.status.statusId eq 5}">INITIALIZED</c:if>
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top">
			<a href="javascript:detil('<c:out value="${user.userId}" />')">
				<img src="images/view.gif" class="action_icon" alt="View" title="View"></a>

			<!-- ini default edit table for each data -->
			<a href="javascript:ubah('<c:out value="${user.userId}" />')">
				<img src="images/edit.gif" class="action_icon" alt="Edit" title="Edit"></a>

			<!-- ini default delete table for each data -->
			<!-- 
			<a href="javascript:hapus('<c:out value="${user.userId}" />')">
				<img src="images/delete.gif" class="action_icon" alt="Delete" title="Delete"></a>
			-->
	  </td>
    </tr>
   
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	
	</c:forEach>
	
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
	document.form1.action = "user-form";
	document.form1.method = "GET";
	document.form1.submit();
}
function hapus (idx){
	var delConfirm = window.confirm ("Are You Sure Want To Delete This Entry ?");

	if (delConfirm){
		document.form1.method = "POST";
		document.form1.userId.value = idx;
		document.form1.action = "user";
		document.form1.navigation.value = "delete";
		document.form1.submit();
	}
}
function ubah (idx){
	document.form1.method = "GET";
	document.form1.userId.value = idx;
	document.form1.action = "user-form";
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
	document.form1.action = "user";
	document.form1.method = "POST";
	document.form1.submit();
}
function detil (idx){
	document.form1.method = "POST";
	document.form1.userId.value = idx;
	document.form1.action = "user";
	document.form1.navigation.value = "detail";
	document.form1.submit();

}
function carisort(param){
	document.form1.navigation.value = "gosearchbysort";
	document.form1.sortcolumn.value=param;
	document.form1.method = "POST";
	document.form1.submit();
}
</script>
