<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;List Case Event</h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
      <td align="right">
      	<input title="Error Log"  name="errorLog" id="errorLog" value=" Error Log " class="errorLog" type="button" onClick="javascript:printErrorLog()">
      </td>
      <td align="right">
      	<input title="Add Error Log"  name="addErrorLog" value=" Add Error Log " class="errorLog" type="button" onClick="javascript:adderrorlog()">
      </td>
    </tr>
  </tbody>
</table>

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

<%@ include file="../mainContentCase.jsp" %>
<br/>

<form name="form1" action="caseevent" method="POST">
<input type="hidden" name="navigation" value="gosearch">
<input type="hidden" name="subnavigation" value="<c:out value="${subnavigation}" />" />
<input type="hidden" name="arah" value="">
<input type="hidden" name="index" value="<c:out value="${index}" />">
<input type="hidden" name="caseEventId" value="<c:out value="${caseEvent.caseEventId}" />">
<input type="hidden" name="caseId" value="<c:out value="${caseId}" />">
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"></td>
      <td width="100%"></td>
    </tr>
  </tbody>
</table>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Search Case Event</h3></td>
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
            <td class="dataLabel" nowrap="nowrap">Search Category:
              &nbsp;&nbsp;
              
                
								 <select name="searchby" class="inputbox">

<!--  		   		   		<option value="description" <c:if test="${searchby eq \"description\"}">selected="true"</c:if> >Description</option> -->
			   		   	<option value="category" <c:if test="${searchby eq \"category\"}">selected="true"</c:if> >Category</option>
			   		   	<option value="diagnostic" <c:if test="${searchby eq \"diagnostic\"}">selected="true"</c:if> >Diagnostic Test</option>
			   		   	<option value="vitalSign" <c:if test="${searchby eq \"vitalSign\"}">selected="true"</c:if> >Vital Sign</option>
			   		   	<option value="procedure" <c:if test="${searchby eq \"procedure\"}">selected="true"</c:if> >Procedure</option>
			   		   	<option value="therapy" <c:if test="${searchby eq \"therapy\"}">selected="true"</c:if> >Therapy</option>
			   			<option value="remarks" <c:if test="${searchby eq \"remarks\"}">selected="true"</c:if> >Remarks</option>	   			
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

<c:if test="${(theUser.roleId.roleId eq 1 or theUser.roleId.roleId eq 2 or theUser.roleId.roleId eq 5 )  }">	
<input type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:tambahi()" value=" Record Monitoring ">
</c:if>
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
		<td width="3%" nowrap="nowrap" class="listViewThS1" scope="col">No.</td>		
	   	<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Monitoring Time &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Category &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Vital Sign/Anamnesa &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Remarks&nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Diagnostic Test &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Therapy/Medication&nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Description&nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Procedure&nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Modified&nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Modified Time&nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="2%">&nbsp;</td>
	</tr>


	<c:forEach items="${CaseEvents}" var="caseEvent" varStatus="status" >
	<%	if (i % 2 == 0) {
			rowclass = "col1";
		} else if (i % 2 != 0) {
			rowclass= "col2";
		}
		i++;
	%>
	 <tr height="20" style="border-top: 1px solid #000;">
      <td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><%=(i+((indexint-1)*countSet))%></td>

      	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
				<a href="caseevent?navigation=detail&index=<c:out value="${index}" />&rowset=<c:out value="${rowset}" />&caseId=<c:out value="${caseEvent.caseId.caseId}" />&caseEventId=<c:out value="${caseEvent.caseEventId}" />" class="linkDetail"><c:out value="${caseEvent.monitoringTime}" /></a>			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<c:out value="${caseEvent.eventCategoryId.eventCategoryName}" />
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe"  valign="top">
				<pre style="width:150px;
							white-space: pre-wrap;
							white-space: -moz-pre-wrap;
							white-space: -pre-wrap;
 							white-space: -o-pre-wrap;
 							word-wrap: break-word; "><c:out value="${caseEvent.vitalSign}" escapeXml="false"/></pre>
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe"  valign="top">
				<pre style="width:150px;
							white-space: pre-wrap;
							white-space: -moz-pre-wrap;
							white-space: -pre-wrap;
 							white-space: -o-pre-wrap;
 							word-wrap: break-word; "><c:out value="${caseEvent.remarks}" escapeXml="false" /></pre>
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top">
				<pre style="width:150px;
							white-space: pre-wrap;
							white-space: -moz-pre-wrap;
							white-space: -pre-wrap;
 							white-space: -o-pre-wrap;
 							word-wrap: break-word;"><c:out value="${caseEvent.diagnosticTesting}" escapeXml="false"/></pre>
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top">
				<pre style="width:150px;
							white-space: pre-wrap;
							white-space: -moz-pre-wrap;
							white-space: -pre-wrap;
 							white-space: -o-pre-wrap;
 							word-wrap: break-word; "><c:out value="${caseEvent.therapy}" escapeXml="false"/></pre>
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe"  valign="top">
				<pre style="width:150px;
							white-space: pre-wrap;
							white-space: -moz-pre-wrap;
							white-space: -pre-wrap;
 							white-space: -o-pre-wrap;
 							word-wrap: break-word; "><c:out value="${caseEvent.description}" escapeXml="false" /></pre>
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top">
				<pre style="width:150px;
							white-space: pre-wrap;
							white-space: -moz-pre-wrap;
							white-space: -pre-wrap;
 							white-space: -o-pre-wrap;
 							word-wrap: break-word; "><c:out value="${caseEvent.procedurePlan}" escapeXml="false" /></pre>
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<c:choose>
					<c:when test="${not empty caseEvent.modifiedBy }">
						<c:out value="${caseEvent.modifiedBy}" />
					</c:when>
					<c:otherwise>
						<c:out value="${caseEvent.createdBy}" />
					</c:otherwise>
				</c:choose>
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<c:choose>
					<c:when test="${not empty caseEvent.modifiedTime }">
						<c:out value="${caseEvent.modifiedTime}" />
					</c:when>
					<c:otherwise>
						<c:out value="${caseEvent.createdTime}" />
					</c:otherwise>
				</c:choose>
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top">

			<!-- ini default edit table for each data -->
			<a href="javascript:ubah('<c:out value="${caseEvent.caseEventId}" />')">
				<img src="images/edit.gif" class="action_icon" alt="Edit" title="Edit"></a>

			<!-- ini default delete table for each data -->
			<a href="javascript:hapus('<c:out value="${caseEvent.caseEventId}" />')">
				<img src="images/delete.gif" class="action_icon" alt="Delete" title="Delete"></a>
		
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
	
<!-- Table Content Stop -->
	<!-- Table Toolbar Start -->
	
		<!-- Table Toolbar Stop -->



<!-- Table Container Stop -->
<script language="Javascript">
$(document).ready(function(){
	$.get("firstcall?navigation=jsontotalcaseelog&caseId=<c:out value="${myCase.caseId }"/>", function(data){
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
if(request.getAttribute("navigation").equals("gosearch")||request.getAttribute("navigation").equals("golookup")){
	nav = (String)request.getAttribute("navigation");
}
%>
function tambahi (){
	document.form1.navigation.value = "tambah";
	document.form1.action = "caseevent-form";
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
	document.form1.navigation.value = "gosearch";
	document.form1.action = "caseevent";
	document.form1.method = "POST";
	document.form1.submit();
}
function hapus (idx){
	var delConfirm = window.confirm ("Are You Sure Want To Delete This Entry ?");

	if (delConfirm){
		document.form1.method = "POST";
		document.form1.caseEventId.value = idx;
		document.form1.action = "caseevent";
		document.form1.navigation.value = "delete";
		document.form1.submit();
	}
}
function ubah (idx){
	document.form1.method = "GET";
	document.form1.caseEventId.value = idx;
	document.form1.action = "caseevent-form";
	document.form1.navigation.value = "edit";
	document.form1.submit();
}
</script>
