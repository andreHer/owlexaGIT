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
// boolean sortPolicyNumber = (Boolean) request.getAttribute("sortPolicyNumber");
// boolean sortGroupName = (Boolean) request.getAttribute("sortGroupName");
// boolean sortClientName = (Boolean) request.getAttribute("sortClientName");
// boolean sortEffectiveDate = (Boolean) request.getAttribute("sortEffectiveDate");
// boolean sortExpireDate = (Boolean) request.getAttribute("sortExpireDate");
// boolean sortStatus = (Boolean) request.getAttribute("sortStatus");
// boolean sortRenewalDate = (Boolean) request.getAttribute("sortRenewalDate");
// boolean sortModifiedTime = (Boolean) request.getAttribute("sortModifiedTime");

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

<form name="form1" action="policy" method="POST">
<input type="hidden" name="navigation" value="gosearch">
<input type="hidden" name="sortcolumn" value="<c:out value="${sortcolumn}" />">
<input type="hidden" name="sortorder" value="<c:out value="${sortorder}" />">
<input type="hidden" name="columntosort" value="<c:out value="${columntosort}" />">
<input type="hidden" name="arah" value="">
<input type="hidden" name="index" value="<c:out value="${index}" />">
<input type="hidden" name="policyId" value="<c:out value="${policy.policyId}" />">
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Search Policy</h3></td>
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
 		   				<option value="policyNumber" <c:if test="${searchby eq \"policyNumber\"}">selected="true"</c:if> >Policy Number</option>			   		   		   		   		   		   		   		
			   			<option value="groupName" <c:if test="${searchby eq \"groupName\"}">selected="true"</c:if> >Group Name</option>
			   			<option value="clientName" <c:if test="${searchby eq \"clientName\"}">selected="true"</c:if> >Client</option>
			   		</select>
              </td>
			  <td class="dataLabel" nowrap="nowrap">Status:
              &nbsp;&nbsp;
              
                <select name="searchstatus">
                	<option value="">-- ALL STATUS --</option>					
					<option value="-1" <c:if test="${searchstatus eq -1 }">selected</c:if>>PENDING</option>
					<option value="1" <c:if test="${searchstatus eq 1 }">selected</c:if>>ACTIVE</option>
					<option value="2" <c:if test="${searchstatus eq 2 }">selected</c:if>>TERMINATED</option>
				</select>
				
              </td>
            <td class="dataLabel">
              <input title="Search [Alt+Q]" accesskey="Q" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" name="button" value="Search" type="submit">
                          
			</td>
            </tr>
			<tr>
            
             <td class="dataLabel" nowrap="nowrap"> Minimum Date :
              &nbsp;&nbsp;&nbsp;&nbsp;
              
                <input name="minimum_date" id="jscal_field" tab="1" maxlength="10" size="11" value="<c:out value="${minimumDate}" />" type="text">
				<img src="images/jscalendar.gif" alt="Enter Date" id="jscal_trigger" align="absmiddle" height="18" width="18">
				
				<script type="text/javascript">
    					Calendar.setup({
        					inputField     :    "jscal_field",     // id of the input field
        					ifFormat       :    "%Y-%m-%d",      // format of the input field
        					button         :    "jscal_trigger",  // trigger for the calendar (button ID)
        					align          :    "Bl",           // alignment (defaults to "Bl")
        					singleClick    :    true
    					});
				 	</script>
              
			</td>
            <td class="dataLabel" nowrap="nowrap"> Maximum Date :
              &nbsp;&nbsp;&nbsp;&nbsp;
              
            <input name="maximum_date" id="jscal_field_max" tabindex="1" maxlength="10" size="11" value="<c:out value="${maximumDate}" />" type="text">
			<img src="images/jscalendar.gif" alt="Enter Date" id="jscal_max_trigger" align="absmiddle" height="18" width="18">
			
					<script type="text/javascript">
    					Calendar.setup({
        					inputField     :    "jscal_field_max",     // id of the input field
        					ifFormat       :    "%Y-%m-%d",      // format of the input field
        					button         :    "jscal_max_trigger",  // trigger for the calendar (button ID)
        					align          :    "Bl",           // alignment (defaults to "Bl")
        					singleClick    :    true
    					});
				 	</script>
			</td>
            <td class="dataLabel" nowrap="nowrap">Date:
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<select name="dateby" class="inputbox">
					<option value="">-- ALL CATEGORY --</option>
					<option value="effectiveDate" <c:if test="${dateby eq \"effectiveDate\"}">selected="true"</c:if> >Effective Date</option>
					<option value="expireDate" <c:if test="${dateby eq \"expireDate\"}">selected="true"</c:if> >Expire Date</option>
					<option value="renewalDate" <c:if test="${dateby eq \"renewalDate\"}">selected="true"</c:if> >Renewal Date</option>			   	
			   </select>
				
              </td>
            <td align="right">
            </td>
          </tr>
        </tbody>
      </table></td>
    </tr>
  </tbody>
</table>



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
		<td width="3%" nowrap="nowrap" class="listViewThS1" scope="col">No. &nbsp;</td>
		<c:choose>
			<c:when test="${sortPolicyNumber}">
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Policy Number &nbsp;
					<input type="hidden" name="sortByOrderPolicyNo" value="true">
					<a href="javascript:carisortpolicyno()" class="listViewThLinkS1">
					<img src="images/arrow_up.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
				</td>
			</c:when>
			<c:otherwise >
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Policy Number &nbsp;
					<input type="hidden" name="sortByOrderPolicyNo" value="false">
					<a href="javascript:carisortpolicyno()" class="listViewThLinkS1">
					<img src="images/arrow_down.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
				</td>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${sortGroupName}">
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">Group Name &nbsp;
					<input type="hidden" name="sortByOrderGroup" value="true">
					<a href="javascript:carisortgroup()" class="listViewThLinkS1">
					<img src="images/arrow_up.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
				</td>
			</c:when>
			<c:otherwise>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">Group Name &nbsp;
					<input type="hidden" name="sortByOrderGroup" value="false">
					<a href="javascript:carisortgroup()" class="listViewThLinkS1">
					<img src="images/arrow_down.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
				</td>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${sortClientName}">
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Client Name &nbsp;
					<input type="hidden" name="sortByOrderClient" value="true">
					<a href="javascript:carisortclient()" class="listViewThLinkS1">
					<img src="images/arrow_up.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
				</td>	
			</c:when>
			<c:otherwise>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Client Name &nbsp;
					<input type="hidden" name="sortByOrderClient" value="false">
					<a href="javascript:carisortclient()" class="listViewThLinkS1">
					<img src="images/arrow_down.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
				</td>
			</c:otherwise>
		</c:choose>
		
		<c:choose>
			<c:when test="${sortEffectiveDate}">
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="6%">Effective Date &nbsp;
					<input type="hidden" name="sortByOrderEffectiveDate" value="true">
					<a href="javascript:carisorteffectivedate()" class="listViewThLinkS1">
					<img src="images/arrow_up.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
				</td>
			</c:when>
			<c:otherwise>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="6%">Effective Date &nbsp;
					<input type="hidden" name="sortByOrderEffectiveDate" value="false">
					<a href="javascript:carisorteffectivedate()" class="listViewThLinkS1">
					<img src="images/arrow_down.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
				</td>
			</c:otherwise>
		</c:choose>
		
		<c:choose>
			<c:when test="${sortExpireDate}">
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="6%">Expire Date &nbsp;
					<input type="hidden" name="sortByOrderExpireDate" value="true">
					<a href="javascript:carisortexpiredate()" class="listViewThLinkS1">
					<img src="images/arrow_up.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
				</td>
			</c:when>
			<c:otherwise>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="6%">Expire Date &nbsp;
					<input type="hidden" name="sortByOrderExpireDate" value="false">
					<a href="javascript:carisortexpiredate()" class="listViewThLinkS1">
					<img src="images/arrow_down.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
				</td>
			</c:otherwise>
		</c:choose>
		 
		<c:choose>
			<c:when test="${sortStatus}">
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="6%">Status &nbsp;
					<input type="hidden" name="sortByOrderStatus" value="true">
					<a href="javascript:carisortstatus()" class="listViewThLinkS1">
					<img src="images/arrow_up.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
				</td>
			</c:when>
			<c:otherwise>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="6%">Status &nbsp;
					<input type="hidden" name="sortByOrderStatus" value="false">
					<a href="javascript:carisortstatus()" class="listViewThLinkS1">
					<img src="images/arrow_down.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
				</td>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${sortRenewalDate}">
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Renewal Date &nbsp;
					<input type="hidden" name="sortByOrderRenewalDate" value="true">
					<a href="javascript:carisortrenewaldate()" class="listViewThLinkS1">
					<img src="images/arrow_up.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
				</td>
			</c:when>
			<c:otherwise>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Renewal Date &nbsp;
					<input type="hidden" name="sortByOrderRenewalDate" value="false">
					<a href="javascript:carisortrenewaldate()" class="listViewThLinkS1">
					<img src="images/arrow_down.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
				</td>
			</c:otherwise>
		</c:choose>
		
		<c:choose>
			<c:when test="${sortModifiedTime}">
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Modified Time &nbsp;
					<input type="hidden" name="sortByOrderModified" value="true">
					<a href="javascript:carisortmodified()" class="listViewThLinkS1">
					<img src="images/arrow_up.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
				</td>
			</c:when>
			<c:otherwise>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Modified Time &nbsp;
					<input type="hidden" name="sortByOrderModified" value="false">
					<a href="javascript:carisortmodified()" class="listViewThLinkS1">
					<img src="images/arrow_down.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
				</td>
			</c:otherwise>
		</c:choose>
		
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="2%"> </td>
	</tr>


	<c:forEach items="${Policys}" var="policy" varStatus="status" >
	<%	if (i % 2 == 0) {
			rowclass = "col1";
		} else if (i % 2 != 0) {
			rowclass= "col2";
		}
		i++;
	%>
	 <tr  height="20">
      <td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><%=(i+((indexint-1)*countSet))%></td>

		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<a href="policy?navigation=detail&policyId=<c:out value="${policy.policyId}" />" class="linkDetail"><c:out value="${policy.policyNumber}" /></a>
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<c:out value="${policy.memberGroupId.groupName}" />
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<c:out value="${policy.clientId.clientName}" />
		</td>
		
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<c:out value="${policy.effectiveDate}" />
		</td>
		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<c:out value="${policy.expireDate}" />
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				
					<c:if test="${policy.policyStatus.statusId eq -1}">PENDING</c:if>
						<c:if test="${policy.policyStatus.statusId eq 1}">ACTIVE</c:if>
						<c:if test="${policy.policyStatus.statusId eq -3}">PENDING CHANGEPLAN</c:if>
						<c:if test="${policy.policyStatus.statusId eq -2}">BLOCKED</c:if>
						<c:if test="${policy.policyStatus.statusId eq 2}">TERMINATED</c:if>
						<c:if test="${policy.policyStatus.statusId eq 3}">RESIGNED</c:if>
						<c:if test="${policy.policyStatus.statusId eq 4}">INACTIVE</c:if>
						<c:if test="${policy.policyStatus.statusId eq 5}">INITIALIZED</c:if>
						<c:if test="${policy.policyStatus.statusId eq 8}">ACTIVE EXTEND</c:if>
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<c:out value="${policy.renewalDate}" />
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<c:out value="${policy.modifiedTime}" />
		</td>
					   		   		   		
        <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top">
				
		<a href="javascript:detil('<c:out value="${policy.policyId}" />')">
			<img src="images/view.gif" class="action_icon" alt="View" title="View"></a>
				
        <c:if test="${theUser.roleId.roleId ne 6}">
			<a href="javascript:ubah('<c:out value="${policy.policyId}" />')">
				<img src="images/edit.gif" class="action_icon" alt="Edit" title="Edit"></a>

			<a href="javascript:hapus('<c:out value="${policy.policyId}" />')">
				<img src="images/delete.gif" class="action_icon" alt="Delete" title="Delete"></a>
		</c:if>
					
	  </td>
    </tr>
   
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	
	</c:forEach>
	
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
	

<script language="Javascript">
<%
String nav="";
if(request.getAttribute("navigation").equals("gosearch")||request.getAttribute("navigation").equals("golookup")){
	nav = (String)request.getAttribute("navigation");
}
%>
function tambahi (){
	document.form1.navigation.value = "tambah";
	document.form1.action = "policy-form";
	document.form1.method = "GET";
	document.form1.submit();
}
function hapus (idx){
	var delConfirm = window.confirm ("Are You Sure Want To Delete This Entry ?");

	if (delConfirm){
		document.form1.method = "POST";
		document.form1.policyId.value = idx;
		document.form1.action = "policy";
		document.form1.navigation.value = "delete";
		document.form1.submit();
	}
}
function ubah (idx){
	document.form1.method = "GET";
	document.form1.policyId.value = idx;
	document.form1.action = "policy-form";
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
	document.form1.action = "policy";
	document.form1.method = "POST";
	document.form1.submit();
}
function detil (idx){
	document.form1.method = "POST";
	document.form1.policyId.value = idx;
	document.form1.action = "policy";
	document.form1.navigation.value = "detail";
	document.form1.submit();

}
function carisortpolicyno(){
	document.form1.navigation.value = "gosearchbysort";
	document.form1.searchtext.value="<c:out value="${searchtext}" />";
	document.form1.searchby.value="<c:out value="${searchby}" />";
	document.form1.sortcolumn.value="policynumber";
	document.form1.method = "POST";
	document.form1.submit();
}
function carisortgroup(){
	document.form1.navigation.value = "gosearchbysort";
	document.form1.sortcolumn.value="groupname";
	document.form1.method = "POST";
	document.form1.submit();
}
function carisortclient(){
	document.form1.navigation.value = "gosearchbysort";
	document.form1.sortcolumn.value="clientname";
	document.form1.method = "POST";
	document.form1.submit();
}
function carisorteffectivedate(){
	document.form1.navigation.value = "gosearchbysort";
	document.form1.sortcolumn.value="effectivedate";
	document.form1.method = "POST";
	document.form1.submit();
}
function carisortexpiredate(){
	document.form1.navigation.value = "gosearchbysort";
	document.form1.sortcolumn.value="expiredate";
	document.form1.method = "POST";
	document.form1.submit();
}
function carisortstatus(){
	document.form1.navigation.value = "gosearchbysort";
	document.form1.sortcolumn.value="status";
	document.form1.method = "POST";
	document.form1.submit();
}
function carisortrenewaldate(){
	document.form1.navigation.value = "gosearchbysort";
	document.form1.sortcolumn.value="renewaldate";
	document.form1.method = "POST";
	document.form1.submit();
}
function carisortmodified(){
	document.form1.navigation.value = "gosearchbysort";
	document.form1.sortcolumn.value="modifiedtime";
	document.form1.method = "POST";
	document.form1.submit();
}
</script>
