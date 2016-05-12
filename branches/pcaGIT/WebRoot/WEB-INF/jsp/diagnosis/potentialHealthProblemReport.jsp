<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collection"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<link rel="stylesheet" type="text/css" href="css/jquery/autocomplete/jquery.autocomplete.css"/>
<script type="text/javascript" src="scripts/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="scripts/jquery/plugin/jquery.autocomplete.pack.js"></script>

<%
String navigation = WebUtil.getAttributeString(request,"navigation","");
%>

 <!-- Page Title Start // Should be put on <Title> tag-->

<!-- Page Title Stop-->
<%
String alert = (String) request.getAttribute("alert");

if (alert != null && !alert.trim().equals("")) {%>
	<div id="warning" align="center">
		<c:out value="${alert}"></c:out>
	</div>
	<%}%>

<%
String rowclass = "";
int i=0;

%>


<!-- Search Container Start -->

<form name="form1" action="diagnosishealthreport" method="POST">
<input type="hidden" name="navigation" value="gosearchhealthreport">
<input type="hidden" name="arah" value="">
<input type="hidden" name="index" value="<c:out value="${index}" />">
<input type="hidden" name="memberGroupId" id="memberGroupId" value="<c:out value="${memberGroupId}" />" />
<input type="hidden" name="clientId" id="clientId" value="<c:out value="${clientId}" />" />
<input type="hidden" name="memberSize" value="<c:out value="${memberSize}" />" />
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Search Potential Health Problem</h3></td>
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
	        	<td>&nbsp;</td>
        	</tr>
          <tr>
        		 <td class="dataLabel" nowrap="nowrap">
        		 	Client * : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        		 	<input size="40" name="clientText" id="clientText"  value="<c:out value="${clientText}"/>" type="text">
        		 </td>
        		 <td class="dataLabel">
					Group : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
					<input size="40" name="memberGroupText" id="memberGroupText"  value="<c:out value="${memberGroupText}"/>" type="text">
				</td>
          </tr>
          <tr>
			<tr>
            
            <td class="dataLabel" nowrap="nowrap"> Minimum Admission Date *:
              &nbsp;&nbsp;&nbsp;&nbsp;
              
                <input name="minimum_date" id="jscal_field" tab="1" maxlength="10" size="11" readonly="readonly" value="<c:out value="${minimum_date}" />" type="text">
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
            <td class="dataLabel" nowrap="nowrap"> Maximum Admission Date * :
              &nbsp;&nbsp;&nbsp;&nbsp;
              
            <input name="maximum_date" id="jscal_field_max" tabindex="1" maxlength="10" size="11" readonly="readonly" value="<c:out value="${maximum_date}" />" type="text">
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
			<td>
				<input title="Search [Alt+Shift+Q]" accesskey="Q" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" name="buttonSearch" value="Search" type="button" onclick="javascript:cari()">              
             	<input title="Download Report" accesskey="Q" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" name="buttonDownload" value="Download Report" type="button" onclick="javascript:exportData()">
			</td>
          </tr>
          <%--<tr>
            
         	<td class="dataLabel" nowrap="nowrap">Format Value &nbsp;&nbsp;:
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              <select name="reportFormat">					
					<option value="4" <c:if test="${reportFormat eq 4 }">selected</c:if> >Excel</option>
					<option value="1" <c:if test="${reportFormat eq 1 }">selected</c:if> >CSV - All</option>
					<option value="2" <c:if test="${reportFormat eq 2 }">selected</c:if> >Text File - Header</option>
					<option value="3" <c:if test="${reportFormat eq 3 }">selected</c:if> >Text File - Detail</option>
				</select>
			</td>
			<td></td>
			<td></td>
          </tr> --%>
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
      <td colspan="20" align="right">  
            
            </td>
    </tr>
	<tr height="20">
		<td width="6%" nowrap="nowrap" class="listViewThS1" scope="col">
			<img src="images/blank.gif" alt="asd" height="1" width="1">No.</td>		

<!-- ini default generated table from database -->
		   			
				   		   		   			
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">
				Potential Health Problem &nbsp;</td>

				<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">
				Total Case  &nbsp;</td>
				
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">
				Total Claimant  &nbsp;</td>


					<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				% Potential Health Problem &nbsp;</td>
	</tr>


	<%--	<c:forEach items="${potentialHealths}" var="claim" varStatus="status" > --%>
	<%	if (i % 2 == 0) {
			rowclass = "col1";
		} else if (i % 2 != 0) {
			rowclass= "col2";
		}
		Integer memberSize = (Integer) request.getAttribute("memberSize");
		Integer totalCase = 0;
		Integer totalClaimant = 0;
		Double percentage = 0.0;
		Collection<Object[]> potentialHealths = (Collection<Object[]>) request.getAttribute("potentialHealths");
		if (potentialHealths != null && potentialHealths.size() > 0){
			Iterator<Object[]> potentialHealthsIterator = potentialHealths.iterator();
			if (potentialHealthsIterator != null){
				while (potentialHealthsIterator.hasNext()){
				i++;
				Object[] phealth = potentialHealthsIterator.next();
				Long claimant1 = (Long)phealth[2];
				Integer claimant2 = claimant1.intValue();
				Integer case1 = ((Long)phealth[1]).intValue();
				if(memberSize>0)
					percentage = (claimant2.doubleValue()/memberSize.doubleValue())*100;
				else
					percentage = 0.0;
				totalCase = totalCase + case1;
				totalClaimant = totalClaimant + claimant2;
	%>
	 <tr height="20">
     	<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><%=i%></td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<c:out value="<%=phealth[0] %>" />
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">	
			<c:out value="<%=phealth[1] %>" />
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<c:out value="<%=phealth[2] %>" />
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe"  valign="top" align="right">
			<fmt:formatNumber value="<%=percentage %>" pattern="#.##"></fmt:formatNumber>
		</td>
    </tr>
   
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	
	<%
				}
			}
		}
	 %>
<tr>
                     </tr>
                     <tr height="20">
		<td width="6%" nowrap="nowrap" class="listViewThS1" scope="col">
			<img src="images/blank.gif" alt="asd" height="1" width="1"></td>		

<!-- ini default generated table from database -->
		   			
				   		   		   			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">
				Jumlah</td>

				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
					<c:out value="<%=totalCase %>" />
				</td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
					<c:out value="<%=totalClaimant %>" />
				</td>
				 <td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
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
 $("#memberGroupText").autocomplete("membergroup?navigation=lookupjson", {
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
            return "<font color='#000' >"+ row.number +" - </font>" +    "<font color='#000' >"+ row.name +"</font>" ;
        }
    }).bind("result", function(data, value){
        $(this).parents("dd").find(".error_message").hide();
        $("#memberGroupId").val(value.id);   
        $("#memberGroupText").val(value.name);     
    });
   
   $("#clientText").autocomplete("client?navigation=lookupjson", {
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
            return "<font color='#000' >"+ row.number +" - </font>" +    "<font color='#000' >"+ row.name +"</font>" ;
        }
    }).bind("result", function(data, value){
        $(this).parents("dd").find(".error_message").hide();
        $("#clientId").val(value.id);   
        $("#clientText").val(value.name);     
    });
});
<%
String nav="";
if(request.getAttribute("navigation").equals("gosearch")||request.getAttribute("navigation").equals("golookup")){
	nav = (String)request.getAttribute("navigation");
}
%>
	function printOut(){
		window.open ("claimreport?navigation=printoutreport&url=claimreport","Search","width=1024, height=768, menubar=yes, status=no, toolbar=no, scrollbars=yes, resizable=yes");
	}
function exportData(){
	document.form1.action = "diagnosis";
	document.form1.navigation.value = "printouthealthreport";
	document.form1.submit();
}
function go(){
	document.form1.navigation.value = "<%=nav%>";
	document.form1.submit();
}
function cari (){
	document.form1.navigation.value = "gosearchhealthreport";
	document.form1.action = "diagnosis";
	document.form1.method = "POST";
	document.form1.submit();
}
function detil (idx){
	document.form1.method = "GET";
	document.form1.claimId.value = idx;
	document.form1.action = "claim";
	document.form1.navigation.value = "detail";
	document.form1.submit();

}
function printPopup(){
	var claimType = document.form1.claimType.value;
	var searchtext = document.form1.searchtext.value;
	var searchby = document.form1.searchby.value;
	var status = document.form1.status.value;
	var minDate = document.form1.minimum_date.value;
	var maxDate = document.form1.maximum_date.value;
	var serviceType = document.form1.serviceType.value;
	var claimValue = document.form1.claimValue.value;
	
	var urlAdd = "&claimValue="+claimValue+"&claimType="+claimType+"&searchtext="+searchtext+"&status="+status+"&searchby="+searchby+"&minimum_date="+minDate+"&maximum_date="+maxDate+"&serviceType="+serviceType;
	window.open ("claimreport?navigation=printpopup&url=claimreport"+urlAdd,"Search","width=1024, height=768, menubar=yes, status=no, toolbar=no, scrollbars=yes, resizable=yes");
}
</script>
