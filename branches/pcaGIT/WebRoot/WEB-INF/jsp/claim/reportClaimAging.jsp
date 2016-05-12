<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<link rel="stylesheet" type="text/css" href="css/autocomplete.css">
<link rel="stylesheet" type="text/css" href="css/button.css">

<!-- 
<link rel="stylesheet" type="text/css" href="css/jquery/autocomplete/jquery.autocomplete.css"/>

<script type="text/javascript" src="scripts/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="scripts/jquery/plugin/jquery.autocomplete.pack.js"></script>
 -->

<link rel="stylesheet" type="text/css" href="scripts/jquery/jquery.multiselect.min.js">

<link rel="stylesheet" type="text/css" href="css/jquery/jquery.multiselect.css"/>

<script type="text/javascript" src="scripts/jquery/jquery.multiselect.min.js"></script>


	<style>
		.ui-autocomplete-loading {
		  background: white url("images/owlexa/ui-anim_basic_16x16.gif") right center no-repeat;
		}
	</style>
	
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

<form name="form1" action="claimreport" method="POST">
<input type="hidden" name="navigation" value="gosearchclaimaging">
<input type="hidden" name="arah" value="">
<input type="hidden" name="index" value="<c:out value="${index}" />">
<input type="hidden" name="claimId" value="<c:out value="${claim.claimId}" />">
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Search Report Claim Aging</h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>
<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td><table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tbody>
          
<!-- 			<tr> -->
            
<!--             <td class="dataLabel" nowrap="nowrap"> Minimum Date : -->
<!--               &nbsp;&nbsp;&nbsp;&nbsp; -->
              
<!--                 <input name="minimum_date" id="jscal_field" tab="1" maxlength="10" size="11" value="<c:out value="${minimumDate}" />" type="text"> -->
<!-- 				<img src="images/jscalendar.gif" alt="Enter Date" id="jscal_trigger" align="absmiddle" height="18" width="18"> -->
				
<!-- 				<script type="text/javascript"> -->
<!--     					Calendar.setup({ -->
<!--         					inputField     :    "jscal_field",     // id of the input field -->
<!--         					ifFormat       :    "%Y-%m-%d",      // format of the input field -->
<!--         					button         :    "jscal_trigger",  // trigger for the calendar (button ID) -->
<!--         					align          :    "Bl",           // alignment (defaults to "Bl") -->
<!--         					singleClick    :    true -->
<!--     					}); -->
<!-- 				 	</script> -->
              
<!-- 			</td> -->
<!--             <td class="dataLabel" nowrap="nowrap"> Maximum Date : -->
<!--               &nbsp;&nbsp;&nbsp;&nbsp; -->
              
<!--             <input name="maximum_date" id="jscal_field_max" tabindex="1" maxlength="10" size="11"  value="<c:out value="${maximumDate}" />" type="text"> -->
<!-- 			<img src="images/jscalendar.gif" alt="Enter Date" id="jscal_max_trigger" align="absmiddle" height="18" width="18"> -->
			
<!-- 					<script type="text/javascript"> -->
<!--     					Calendar.setup({ -->
<!--         					inputField     :    "jscal_field_max",     // id of the input field -->
<!--         					ifFormat       :    "%Y-%m-%d",      // format of the input field -->
<!--         					button         :    "jscal_max_trigger",  // trigger for the calendar (button ID) -->
<!--         					align          :    "Bl",           // alignment (defaults to "Bl") -->
<!--         					singleClick    :    true -->
<!--     					}); -->
<!-- 				 	</script> -->
<!-- 			</td> -->
<!--             <td align="left"> -->
              
<!--             </td> -->
<!--           </tr> -->
          <tr>
			  	<td class="dataLabel" nowrap="nowrap">Status:
              	&nbsp;&nbsp;
              
                <select name="status" id="status"  multiple="multiple">
                  	<%
					   String[] statusSearch = (String[]) request.getAttribute("statusMulti");
					   Integer status2 = null;
					   Integer openStatus = 0;
					   Integer checkedStatus = 0;
					   Integer cdvStatus = 0;
					   if(statusSearch.length >= 1){ 
					   		String panjangStatus[] = new String[statusSearch.length];
							   for (int h = 0 ; h < statusSearch.length ; h++){
							   
							   		status2 = Integer.parseInt(statusSearch[h]);
							   		if(status2 == 1){
							   			openStatus = 1;
							   		}else if(status2 == 8){
							   			checkedStatus = 8;
							   		}else if(status2 == 13){
							   			cdvStatus = 9;
							   		}
								}
							
					 %>
					<c:set var="openStatus" value="<%= openStatus%>"> </c:set>
					<c:set var="checkedStatus" value="<%= checkedStatus%>"> </c:set> 
					<c:set var="cdvStatus" value="<%= cdvStatus%>"> </c:set> 
					
<!-- 					<option value="">--- All Status ---</option> -->
					<option value="1" <c:if test="${openStatus eq 1 }">selected</c:if> >OPEN</option>	
					<option value="8" <c:if test="${checkedStatus eq 8 }">selected</c:if>>CHECKED</option>				
					<option value="13" <c:if test="${cdvStatus eq 13 }">selected</c:if>>CDV ISSUED</option>
					<% }else{ %>
<!-- 					<option value="">--- All Status ---</option> -->
					<option value="1" <c:if test="${openStatus eq 1 }">selected</c:if> >OPEN</option>	
					<option value="8" <c:if test="${checkedStatus eq 8 }">selected</c:if>>CHECKED</option>				
					<option value="13" <c:if test="${cdvStatus eq 13 }">selected</c:if>>CDV ISSUED</option>
					<%
						}
					 %>
				</select>
				
              </td>
              <td class="dataLabel" nowrap="nowrap">Claim Aging:
              &nbsp;&nbsp;
					<select name="agingInterval">	
						<option value="0" <c:if test="${agingInterval eq 0 }">selected</c:if>>All Claim Aging</option>
						<option value="1" <c:if test="${agingInterval eq 1 }">selected</c:if>><c:out value="<30"/></option>					
						<option value="2" <c:if test="${agingInterval eq 2 }">selected</c:if>><c:out value="30 - 40"/></option>
						<option value="3" <c:if test="${agingInterval eq 3 }">selected</c:if>><c:out value="41 - 60"/> </option>
						<option value="4" <c:if test="${agingInterval eq 4 }">selected</c:if>><c:out value=">60"/> </option>
					</select>
					
	            </td>
              	<td>
				<input title="Search [Alt+Shift+Q]" accesskey="Q" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" name="buttonSearch" value="Search" type="button" onclick="javascript:cari()">              
	              <input title="Download Report" accesskey="Q" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" name="buttonDownload" value="Download Report" type="button" onclick="javascript:exportData()">
				</td>
            </tr>
          <tr>
<!--       		<td class="dataLabel" nowrap="nowrap">Service Type &nbsp;&nbsp;: -->
<!--               &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
              
<!--                 <select name="serviceType" id="serviceType"  multiple="multiple">					 -->
<!-- 					<option value="-1" >--- All Type ---</option> -->
<!-- 					<option value="1" <c:if test="${serviceType eq 1 }">selected</c:if> >INPATIENT</option> -->
<!-- 					<option value="2" <c:if test="${serviceType eq 2 }">selected</c:if> >OUTPATIENT</option> -->
<!-- 					<option value="3" <c:if test="${serviceType eq 3 }">selected</c:if> >MATERNITY</option> -->
<!-- 					<option value="4" <c:if test="${serviceType eq 4 }">selected</c:if> >DENTAL</option> -->
<!-- 					<option value="5" <c:if test="${serviceType eq 5 }">selected</c:if> >OPTICAL</option> -->
<!-- 				</select> -->
				
<!--               </td> -->
      		<td>
      		</td>
      		<td>
      		</td>
          </tr>
        </tbody>
      </table></td>
    </tr>
  </tbody>
</table>


<!-- Search Container Stop -->


	
 
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
		<td width="6%" nowrap="nowrap" class="listViewThS1" scope="col" align="center">
			<img src="images/blank.gif" alt="asd" height="1" width="1" >No.</td>		

<!-- ini default generated table from database -->
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">
				Claim Number &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">
				Member Name  &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">
				Claim Date  &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Status &nbsp;</td>
		<td scope="col" class="listViewThS1"  width="10%">
				Date Status &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">
				Created By &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">
				Duration &nbsp;</td>
	</tr>
	<c:forEach items="${ClaimsActive}" var="claim" varStatus="status" >
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
				<c:out value="${claim.claimId.claimNumber}" />
			</td>
	      	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<c:out value="${claim.memberName}" />		
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<c:out value="${claim.claimId.claimDate}" />
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<c:out value="${claim.claimStatus.caseStatusName}" />
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top">
				<c:out value="${claim.createdTime}" />
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top">
				<c:out value="${claim.createdBy}" />
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top">
				<c:choose>
					<c:when test="${empty claim.durationString and  claim.claimStatus.caseStatusId eq 1}">
						00:00:00
					</c:when>
					<c:otherwise>
						<c:out value="${claim.durationString}" />
					</c:otherwise>
				</c:choose>
			</td>
	    </tr>
		<tr>
	      <td colspan="20" class="listViewHRS1"></td>
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
	
	</table>
</form>
	
	<br />
<!-- Table Content Stop -->
	<!-- Table Toolbar Start -->
	
		<!-- Table Toolbar Stop -->



<!-- Table Container Stop -->
<script language="Javascript">
$(document).ready(function(){
	$("#status").multiselect();
	$("#serviceType").multiselect();
	
       $("#clientName").autocomplete({
			source: function( request, response ) {
			$.ajax({
				url: "client?navigation=lookupjson",
				dataType: "json",
				data: {q: request.term},
				success: function(data) {
							response($.map(data, function(item) {
							return {
								label: item.name,
								id: item.id,
								number: item.number
								};
						}));
					}
				});
			},
			minLength: 2,
			select: function(event, ui) {
				$(this).parents("dd").find(".error_message").hide();
		        $("#clientId").val (ui.item.id);
		        $("#clientName").val (ui.item.label);
			}
		})
		.autocomplete( "instance" )._renderItem = function( ul, item ) {
	      return $( "<a>" )
	        .append( item.label )
	        .appendTo( ul );
	    };
	    
	    /**
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
            return  "<font color='#000' style='align: left;' >"+  row.name +"</font>" ;
        }    
    }).bind("result", function(data, value){
        $(this).parents("dd").find(".error_message").hide();
        $("#clientId").val (value.id);
        $("#clientName").val (value.name);
    });
    */
});
<%
String nav="";
if(request.getAttribute("navigation").equals("gosearchclaimaging") || request.getAttribute("navigation").equals("claimagingreport")){
	nav = (String)request.getAttribute("navigation");
}
%>
function printOut(){
	window.open ("claimreport?navigation=printoutreport&url=claimreport","Search","width=1024, height=768, menubar=yes, status=no, toolbar=no, scrollbars=yes, resizable=yes");
}

function exportData(){
	document.form1.action = "claimreport";
	document.form1.navigation.value = "downloadclaimagingreport";
	document.form1.submit();
}
function go(){
	document.form1.navigation.value = "<%=nav%>";
	document.form1.submit();
}
function cari (){
	document.form1.navigation.value = "gosearchclaimaging";
	document.form1.action = "claimreport";
	document.form1.method = "POST";
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
</script>
