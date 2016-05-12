
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

<table>
  <tbody>
   <tr>
     <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;List Member Provider</h3></td>
     <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
     <c:if test="${theUser.userType eq 2 }">
	     <td align="right">
	      	<input title="Error Log"  name="errorLog" id="errorLog" value=" Error Log " class="errorLog" type="button" onClick="javascript:printErrorLog()">
	      </td>
	      <td align="right">
	      	<input title="Add Error Log"  name="addErrorLog" value=" Add Error Log " class="errorLog" type="button" onClick="javascript:adderrorlog()">
	      </td>
      </c:if>
   </tr>
  </tbody>
</table>

<!-- Search Container Start -->
<%@ include file="../mainContentMember.jsp" %>

<br />
<form name="form1" action="provider" method="POST">
<input type="hidden" name="navigation" value="golistmember">
<input type="hidden" name="arah" value="">
<input type="hidden" name="memberId" value="<c:out value="${memberId}" />">
<input type="hidden" name="index" value="<c:out value="${index}" />">
				<input type="hidden" name="providerId" value="<c:out value="${provider.providerId}" />">
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
			   			<option value="bank" <c:if test="${searchby eq \"bank\"}">selected="true"</c:if> >Bank</option>
			   			<option value="address" <c:if test="${searchby eq \"address\"}">selected="true"</c:if> >Address</option>
			   			<option value="city" <c:if test="${searchby eq \"city\"}">selected="true"</c:if> >City</option>
			   			<option value="province" <c:if test="${searchby eq \"province\"}">selected="true"</c:if> >Province</option>
			   			<option value="country" <c:if test="${searchby eq \"country\"}">selected="true"</c:if> >Country</option>
			   			<option value="postalCode" <c:if test="${searchby eq \"postalCode\"}">selected="true"</c:if> >Postal Code</option>
			   			<option value="contactPerson" <c:if test="${searchby eq \"contactPerson\"}">selected="true"</c:if> >Contact Person</option>
			   </select>
				
              </td>
			  <td class="dataLabel" nowrap="nowrap">Status:
              &nbsp;&nbsp;
              
                <select name="status">					
					<option value="-1">-- All Status --</option>
					<option value="1" <c:if test="${status eq 1 }">selected</c:if>>Active</option>
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
 
<c:if test="${(theUser.roleId.roleId eq 0 )or(theUser.roleId.roleId eq 6 ) }">
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
		<td width="2%" nowrap="nowrap" class="listViewThS1" scope="col"><img src="images/blank.gif" alt="asd" height="1" width="1">No.</td>		
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%"><a href="provider?navigation=search&sortby=provider_name&searchby=<c:out value="${searchby}" />" class="listViewThLinkS1">Provider Name &nbsp;<img src="images/arrow.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a></td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Provider Category &nbsp;</td>   			
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">City &nbsp;</td>	   			
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Province &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%">Status &nbsp;</td>	   		   			
		
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">IP &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">OP &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">MT &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">DE &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">OPT &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">LAB/MCU &nbsp;</td>
		
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">PPK1 &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">PPK2 &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">PPK3 &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">EDC  &nbsp;</td>
	</tr>

<%-- <c:choose>
	 <c:when test="${provider.statusId.status eq 1}" >  --%>

	<c:forEach items="${Providers}" var="provider" varStatus="status" >
	<%	if (i % 2 == 0) {
			rowclass = "col1";
		} else if (i % 2 != 0) {
			rowclass= "col2";
		}
		i++;
	%>
	
	 <c:choose>
	 <c:when test="${provider.statusId.status eq 'ACTIVE'}" > 
	 <tr height="20">
      	<td class="oddListRowS1StatusActive" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><%=(i+((indexint-1)*countSet))%></td>
		<td class="oddListRowS1StatusActive" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<a href="provider?navigation=detail&index=<c:out value="${index}" />&providerId=<c:out value="${provider.providerId}" />" class="linkDetail"><c:out value="${provider.providerName}" /></a>			
		</td>
		<td class="oddListRowS1StatusActive" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${provider.providerCategoryId.providerCategoryName}" />			
		</td>
		<td class="oddListRowS1StatusActive" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${provider.city}" />			
		</td>
		<td class="oddListRowS1StatusActive" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${provider.province}" />			
		</td>
		<td class="oddListRowS1StatusActive" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${provider.statusId.status}" />			
		</td>
		<td class="oddListRowS1StatusActive" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${provider.inpatient}" />			
		</td>
		<td class="oddListRowS1StatusActive" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${provider.outpatient}" />			
		</td>
		<td class="oddListRowS1StatusActive" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${provider.maternity}" />			
		</td>
		<td class="oddListRowS1StatusActive" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${provider.dental}" />			
		</td>
		<td class="oddListRowS1StatusActive" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${provider.optical}" />			
		</td>
		<td class="oddListRowS1StatusActive" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${provider.lab}" />			
		</td>
		<td class="oddListRowS1StatusActive" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${provider.ppk1}" />			
		</td>
		<td class="oddListRowS1StatusActive" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${provider.ppk2}" />			
		</td>
		<td class="oddListRowS1StatusActive" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${provider.ppk3}" />			
		</td>		   	
		<td class="oddListRowS1StatusActive" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:if test="${provider.edcCode eq null}">
				TIDAK
			</c:if>
			<c:if test="${provider.edcCode ne null}">
				YA
			</c:if>
		</td>	

    </tr>
    
 	 </c:when>
 	 <c:when test="${provider.statusId.status eq 'TERMINATED'}" > 
	 <tr height="20">
      	<td class="oddListRowS1StatusTerminated" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><%=(i+((indexint-1)*countSet))%></td>
		<td class="oddListRowS1StatusTerminated" bgcolor="#ffffff" nowrap="nowrap" valign="top">
			<a href="provider?navigation=detail&index=<c:out value="${index}" />&providerId=<c:out value="${provider.providerId}" />" ><c:out value="${provider.providerName}" /></a>			
		</td>
		<td class="oddListRowS1StatusTerminated" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${provider.providerCategoryId.providerCategoryName}" />			
		</td>
		<td class="oddListRowS1StatusTerminated" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${provider.city}" />			
		</td>
		<td class="oddListRowS1StatusTerminated" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${provider.province}" />			
		</td>
		<td class=oddListRowS1StatusTerminated bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${provider.statusId.status}" />			
		</td>
		<td class="oddListRowS1StatusTerminated" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${provider.inpatient}" />			
		</td>
		<td class="oddListRowS1StatusTerminated" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${provider.outpatient}" />			
		</td>
		<td class="oddListRowS1StatusTerminated" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${provider.maternity}" />			
		</td>
		<td class="oddListRowS1StatusTerminated" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${provider.dental}" />			
		</td>
		<td class="oddListRowS1StatusTerminated" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${provider.optical}" />			
		</td>
		<td class="oddListRowS1StatusTerminated" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${provider.lab}" />			
		</td>
		<td class="oddListRowS1StatusTerminated" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${provider.ppk1}" />			
		</td>
		<td class="oddListRowS1StatusTerminated" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${provider.ppk2}" />			
		</td>
		<td class="oddListRowS1StatusTerminated" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${provider.ppk3}" />			
		</td>		   	
		<td class="oddListRowS1StatusTerminated" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:if test="${provider.edcCode eq null}">
				TIDAK
			</c:if>
			<c:if test="${provider.edcCode ne null}">
				YA
			</c:if>
		</td>	

    </tr>
 	 </c:when>
     <c:otherwise>
     <tr height="20">
      	<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><%=(i+((indexint-1)*countSet))%></td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<a href="provider?navigation=detail&index=<c:out value="${index}" />&providerId=<c:out value="${provider.providerId}" />" class="linkDetail"><c:out value="${provider.providerName}" /></a>			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${provider.providerCategoryId.providerCategoryName}" />			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${provider.city}" />			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${provider.province}" />			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			
			<c:out value="${provider.statusId.status}" />			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${provider.inpatient}" />			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${provider.outpatient}" />			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${provider.maternity}" />			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${provider.dental}" />			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${provider.optical}" />			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${provider.lab}" />			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${provider.ppk1}" />			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${provider.ppk2}" />			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${provider.ppk3}" />			
		</td>		   	
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:if test="${provider.edcCode eq null}">
				TIDAK
			</c:if>
			<c:if test="${provider.edcCode ne null}">
				YA
			</c:if>
		</td>	
    </tr>
    </c:otherwise>
    </c:choose>  
    
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
          
          <tr>
 <td colspan="20" align="left">
 NB&nbsp; - <font color="#00ff00"> HIJAU </font>&nbsp;&nbsp;:  ACTIVE
</td>
</tr>

<tr>
<td colspan="20" align="left">
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; - <font color="red">MERAH </font>: TERMINATED
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
$(document).ready(function(){
	$.get("firstcall?navigation=jsontotalmemberelog&memberId=<c:out value="${member.memberId }"/>", function(data){
	  //alert("Data: " + data);
	  if(data>0){
	  	blinker();
	  }
	});
});
function blinker(){
	document.getElementById("errorLog").style.backgroundColor="red";
	setTimeout("document.getElementById('errorLog').style.backgroundColor=''", 500);
	setTimeout("blinker()",1500);
}
<%
String nav="";
if(request.getAttribute("navigation").equals("gosearch")||request.getAttribute("navigation").equals("golookup") || request.getAttribute("navigation").equals("golistmember") || request.getAttribute("navigation").equals("listmember")){
	nav = (String)request.getAttribute("navigation");
}
%>
function tambahi (){
	document.form1.navigation.value = "tambahprovider";
	document.form1.action = "provider-form";
	document.form1.method = "GET";
	document.form1.submit();
}
function hapus (idx){
	var delConfirm = window.confirm ("Are You Sure Want To Delete This Entry ?");

	if (delConfirm){
		document.form1.method = "POST";
		document.form1.providerId.value = idx;
		document.form1.action = "provider";
		document.form1.navigation.value = "delete";
		document.form1.submit();
	}
}
function ubah (idx){
	document.form1.method = "GET";
	document.form1.providerId.value = idx;
	document.form1.action = "provider-form";
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
	document.form1.navigation.value = "golistmember";
	document.form1.action = "provider";
	document.form1.method = "POST";
	document.form1.submit();
}
function detil (idx){
	document.form1.method = "POST";
	document.form1.providerId.value = idx;
	document.form1.action = "provider";
	document.form1.navigation.value = "detail";
	document.form1.submit();

}
function printErrorLog(){
	window.open ("firstcall?navigation=searchmembererrorlog&memberId=<c:out value="${member.memberId}" />","Search","width=1024, height=768, menubar=yes, status=no, toolbar=no, scrollbars=yes, resizable=yes");		
}

function adderrorlog (){ 
	window.location.href = "firstcall-form?navigation=memberelog&memberId=<c:out value="${member.memberId}" />";
}
</script>
