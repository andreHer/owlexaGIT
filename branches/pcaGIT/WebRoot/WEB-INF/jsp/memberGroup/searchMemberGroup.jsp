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
// boolean sortGroupName = (Boolean) request.getAttribute("sortGroupName");
// boolean sortPolicyNo = (Boolean) request.getAttribute("sortPolicyNo");
// boolean sortProvince = (Boolean) request.getAttribute("sortProvince");
// boolean sortCity = (Boolean) request.getAttribute("sortCity");
// boolean sortPolicyAgent = (Boolean) request.getAttribute("sortPolicyAgent");
// boolean sortStatus = (Boolean) request.getAttribute("sortStatus");

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

<form name="form1" action="membergroup" method="POST">
<input type="hidden" name="navigation" value="gosearch">
<input type="hidden" name="sortcolumn" value="<c:out value="${sortcolumn}" />">
<input type="hidden" name="sortorder" value="<c:out value="${sortorder}" />">
<input type="hidden" name="columntosort" value="<c:out value="${columntosort}" />">
<input type="hidden" name="arah" value="">
<input type="hidden" name="index" value="<c:out value="${index}" />">
<input type="hidden" name="memberGroupId" value="<c:out value="${memberGroup.memberGroupId}" />">
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Search Member Group</h3></td>
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
	   		   			<option value="groupName" <c:if test="${searchby eq \"groupName\"}">selected="true"</c:if> >Group Name</option>
			   			<option value="province" <c:if test="${searchby eq \"province\"}">selected="true"</c:if> >Province</option>
			   			<option value="city" <c:if test="${searchby eq \"city\"}">selected="true"</c:if> >City</option>
			   			<option value="country" <c:if test="${searchby eq \"country\"}">selected="true"</c:if> >Country</option>
	   		   			<option value="policyAgent" <c:if test="${searchby eq \"policyAgent\"}">selected="true"</c:if> >Policy Agent</option>
			   			<option value="policyNumber" <c:if test="${searchby eq \"policyNumber\"}">selected="true"</c:if> >Policy Number</option>
  	   		     </select>
				
              </td>
			  <td class="dataLabel" nowrap="nowrap">Status:
              &nbsp;&nbsp;
              
                <select name="status">					
					<option value="-1">-- All Status --</option>
					<option value="1" <c:if test="${status eq 1 }" >selected</c:if>>Active</option>
					<option value="2" <c:if test="${status eq 2 }" >selected</c:if>>Terminated</option>									

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


<!-- Search Container Stop -->

<c:if test="${theUser.userType eq 2 and theUser.roleId.roleId eq 0 || theUser.userType eq 2 and theUser.roleId.roleId eq 22 }" >
<input type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:tambahi()" value="Create">
</c:if>	
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
		<td width="2%" nowrap="nowrap" class="listViewThS1" scope="col">No.</td>
		<c:choose>
			<c:when test="${sortGroupName }">
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">Group Name &nbsp;
					<input type="hidden" name="sortByOrderName" value="true">
					<a href="javascript:carisortname()" class="listViewThLinkS1">
					<img src="images/arrow_up.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
				</td>
			</c:when>
			<c:otherwise>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">Group Name &nbsp;
					<input type="hidden" name="sortByOrderName" value="false">
					<a href="javascript:carisortname()" class="listViewThLinkS1">
					<img src="images/arrow_down.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
				</td>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${sortGroupCode}">
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">Group Code &nbsp;
					<input type="hidden" name="sortByOrderCode" value="true">
					<a href="javascript:carisortcode()" class="listViewThLinkS1">
					<img src="images/arrow_up.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
				</td>
			</c:when>
			<c:otherwise>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">Group Code &nbsp;
					<input type="hidden" name="sortByOrderCode" value="false">
					<a href="javascript:carisortcode()" class="listViewThLinkS1">
					<img src="images/arrow_down.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
				</td>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${sortPolicyNo }">
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">Policy Number &nbsp;
					<input type="hidden" name="sortByOrderPolicyNo" value="true">
					<a href="javascript:carisortpolicyno()" class="listViewThLinkS1">
					<img src="images/arrow_up.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
				</td>
			</c:when>
			<c:otherwise>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">Policy Number &nbsp;
					<input type="hidden" name="sortByOrderPolicyNo" value="false">
					<a href="javascript:carisortpolicyno()" class="listViewThLinkS1">
					<img src="images/arrow_down.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
				</td>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${sortProvince}">
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">Province &nbsp;
					<input type="hidden" name="sortByOrderProvince" value="true">
					<a href="javascript:carisortprovince()" class="listViewThLinkS1">
					<img src="images/arrow_up.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
				</td>
			</c:when>
			<c:otherwise>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">Province &nbsp;
					<input type="hidden" name="sortByOrderProvince" value="false">
					<a href="javascript:carisortprovince()" class="listViewThLinkS1">
					<img src="images/arrow_down.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
				</td>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${sortCity}">
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">City &nbsp;
					<input type="hidden" name="sortByOrderCity" value="true">
					<a href="javascript:carisortcity()" class="listViewThLinkS1">
					<img src="images/arrow_up.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
				</td>
			</c:when>
			<c:otherwise>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">City &nbsp;
					<input type="hidden" name="sortByOrderCity" value="false">
					<a href="javascript:carisortcity()" class="listViewThLinkS1">
					<img src="images/arrow_down.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
				</td>
			</c:otherwise>
		</c:choose>		
		<c:choose>
			<c:when test="${sortPolicyAgent}">
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">Policy Agent &nbsp;
					<input type="hidden" name="sortByOrderPolicyAgent" value="true">
					<a href="javascript:carisortpolicyagent()" class="listViewThLinkS1">
					<img src="images/arrow_up.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
				</td>
			</c:when>
			<c:otherwise>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">Policy Agent &nbsp;
					<input type="hidden" name="sortByOrderPolicyAgent" value="false">
					<a href="javascript:carisortpolicyagent()" class="listViewThLinkS1">
					<img src="images/arrow_down.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
				</td>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${sortStatus }">
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">Status &nbsp;
					<input type="hidden" name="sortByOrderStatus" value="true">
					<a href="javascript:carisortstatus()" class="listViewThLinkS1">
					<img src="images/arrow_up.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
				</td>
			</c:when>
			<c:otherwise>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">Status &nbsp;
					<input type="hidden" name="sortByOrderStatus" value="false">
					<a href="javascript:carisortstatus()" class="listViewThLinkS1">
					<img src="images/arrow_down.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
				</td>
			</c:otherwise>
		</c:choose>		
	</tr>
	<c:forEach items="${MemberGroups}" var="memberGroup" varStatus="status" >
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
			<a href="membergroup?navigation=detail&memberGroupId=<c:out value="${memberGroup.memberGroupId}" />" class="linkDetail"><c:out value="${memberGroup.groupName}" /></a>			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${memberGroup.memberGroupCode}" />			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${memberGroup.policyNumber}" />			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${memberGroup.province}" />			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${memberGroup.city}" />			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
				<c:out value="${memberGroup.policyAgent}" />			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
				<c:if test="${memberGroup.status.statusId eq -1}">PENDING</c:if>
						<c:if test="${memberGroup.status.statusId eq 1}">ACTIVE</c:if>
						<c:if test="${memberGroup.status.statusId eq -3}">PENDING CHANGEPLAN</c:if>
						<c:if test="${memberGroup.status.statusId eq -2}">BLOCKED</c:if>
						<c:if test="${memberGroup.status.statusId eq 2}">TERMINATED</c:if>
						<c:if test="${memberGroup.status.statusId eq 3}">RESIGNED</c:if>
						<c:if test="${memberGroup.status.statusId eq 4}">INACTIVE</c:if>
						<c:if test="${memberGroup.status.statusId eq 5}">INITIALIZED</c:if>			
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
	document.form1.action = "membergroup-form";
	document.form1.method = "GET";
	document.form1.submit();
}
function hapus (idx){
	var delConfirm = window.confirm ("Are You Sure Want To Delete This Entry ?");

	if (delConfirm){
		document.form1.method = "POST";
		document.form1.memberGroupId.value = idx;
		document.form1.action = "membergroup";
		document.form1.navigation.value = "delete";
		document.form1.submit();
	}
}
function ubah (idx){
	document.form1.method = "GET";
	document.form1.memberGroupId.value = idx;
	document.form1.action = "membergroup-form";
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
	document.form1.action = "membergroup";
	document.form1.method = "POST";
	document.form1.submit();
}
function detil (idx){
	document.form1.method = "POST";
	document.form1.memberGroupId.value = idx;
	document.form1.action = "membergroup";
	document.form1.navigation.value = "detail";
	document.form1.submit();

}
function carisortname(){
	document.form1.navigation.value = "gosearchbysort";
	document.form1.sortcolumn.value="groupname";
	document.form1.method = "POST";
	document.form1.submit();
}
function carisortcode(){
	document.form1.navigation.value = "gosearchbysort";
	document.form1.sortcolumn.value="groupcode";
	document.form1.method = "POST";
	document.form1.submit();
}
function carisortpolicyno(){
	document.form1.navigation.value = "gosearchbysort";
	document.form1.sortcolumn.value="policynumber";
	document.form1.method = "POST";
	document.form1.submit();
}
function carisortprovince(){
	document.form1.navigation.value = "gosearchbysort";
	document.form1.sortcolumn.value="province";
	document.form1.method = "POST";
	document.form1.submit();
}
function carisortcity(){
	document.form1.navigation.value = "gosearchbysort";
	document.form1.sortcolumn.value="city";
	document.form1.method = "POST";
	document.form1.submit();
}
function carisortpolicyagent(){
	document.form1.navigation.value = "gosearchbysort";
	document.form1.sortcolumn.value="policyagent";
	document.form1.method = "POST";
	document.form1.submit();
}
function carisortstatus(){
	document.form1.navigation.value = "gosearchbysort";
	document.form1.sortcolumn.value="status";
	document.form1.method = "POST";
	document.form1.submit();
}
</script>
