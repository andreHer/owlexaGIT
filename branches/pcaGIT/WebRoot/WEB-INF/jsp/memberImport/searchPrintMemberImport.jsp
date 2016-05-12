<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<link rel="stylesheet" type="text/css" href="css/jquery/autocomplete/jquery.autocomplete.css"/>

<script type="text/javascript" src="scripts/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="scripts/jquery/plugin/jquery.autocomplete.pack.js"></script>


<%
String navigation = WebUtil.getAttributeString(request,"navigation","");
%>

<!-- Page Title Stop-->
<%
String alert = (String) request.getAttribute("alert");


if (alert != null && !alert.trim().equals("")) {%>
	<div id="warning" align="center">
		<c:out value="${alert}"></c:out>
	</div>
	<%}%>

<%

%>
<!-- Search Container Start -->

<br />
<form name="form1" action="memberimport" method="POST">
<input type="hidden" name="navigation" value="gosearchprint">
<input type="hidden" name="arah" value="">

				<input type="hidden" name="id" value="<c:out value="${memberImport.id}" />">
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Search Member Import</h3></td>
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
            <td class="dataLabel" nowrap="nowrap">Client Name:
              &nbsp;&nbsp;
              	<input type="hidden" name="clientId" id="clientId" value="">
				<input size="40" name="searchtext" id="clientName" class="dataField" value="<c:out value="${searchtext}"/>" type="text">
              </td>

			  <td class="dataLabel" nowrap="nowrap"></td>
            <td class="dataLabel">
              <input title="Search [Alt+Q]" accesskey="Q" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" name="button" value="Search" type="submit">
              
              <input title="Search [Alt+Q]" accesskey="Q" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" name="button" value=" Print Card " type="button" onclick="javascript:printCard();">
            
			</td>
            </tr>
			
        </tbody>
      </table></td>
    </tr>
  </tbody>
</table>
	

<br />

 	
<table class="listView" cellspacing="0" cellpadding="0">
	<tbody>
	
	<tr height="20">
		<td width="2%" nowrap="nowrap" class="listViewThS1" scope="col">
			<img src="images/blank.gif" alt="asd" height="1" width="1">No.</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Member Name</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Member Number</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Employee Number</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Employee Name</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Swipe Card Number</td>			   		   		   			
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Group Name</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Client Name</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Policy Number</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Time</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Action</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="2%">Print</td>
	</tr>


	<c:forEach items="${MemberImports}" var="memberImport" varStatus="status" >
	
	 <tr  height="20">
      	<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${status.index+1}" />.</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${memberImport.memberName}" />
			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${memberImport.memberNumber}" />
			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${memberImport.parentNumber}" />
			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${memberImport.parentName}" />
			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<c:if test="${memberImport.actionType eq 'RENEWAL' }">
				<c:if test="${memberImport.nextCardNumber eq null or memberImport.nextCardNumber eq '' }">
					<c:out value="${memberImport.swipeCardNumber}" />
				</c:if>
				<c:if test="${memberImport.nextCardNumber ne null or memberImport.nextCardNumber ne '' }">
					<c:out value="${memberImport.nextCardNumber}" />
				</c:if>
			</c:if>
			
			<c:if test="${memberImport.actionType ne 'RENEWAL' }">
				<c:out value="${memberImport.swipeCardNumber}" />
			</c:if>
			
		</td>	  		   		   		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${memberImport.memberId.clientName}" />
			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<c:out value="${memberImport.memberId.groupName}" />
		</td>
					   		   		
					   		   		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top"><c:out value="${memberImport.policyNumber}" /></td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top"><c:out value="${memberImport.createdTime}" /></td>
		<td class="oddListRowS1" bgcolor="#e7f0fe"  align="center" valign="top"><c:out value="${memberImport.actionType}" /></td>
		<td class="oddListRowS1" bgcolor="#e7f0fe"  align="center" valign="top"><input type="checkbox" name="memberImportId" value="<c:out value="${memberImport.id}" />" /></td>
    </tr>
   
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	
	</c:forEach>
	
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

$("#clientName").autocomplete("client?navigation=lookupjson", {
        max: 7,
        dataType: "json",
        parse: function(data) {
            return $.map(data, function(row) {
                return {
                    data: row,
                    value: row.name,
                    result: row.name
                }
            });
        },
        formatItem: function(row) {
            return "<font color='#000' >"+ row.name +"</font>" ;
        }
    }).bind("result", function(data, value){
        $(this).parents("dd").find(".error_message").hide();
        $("#clientId").val(value.id);
    });
function printCard (){
	document.form1.navigation.value = "tambah";
	document.form1.action = "cardprinting-form";
	document.form1.method = "GET";
	document.form1.submit();
}

function cari (){
	document.form1.navigation.value = "gosearch";
	document.form1.action = "memberimport";
	document.form1.method = "POST";
	document.form1.submit();
}
</script>
