<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>



<link rel="stylesheet" type="text/css" href="css/autocomplete.css">
<link rel="stylesheet" type="text/css" href="css/button.css">

<link rel="stylesheet" type="text/css" href="css/jquery/autocomplete/jquery.autocomplete.css"/>

<script type="text/javascript" src="scripts/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="scripts/jquery/plugin/jquery.autocomplete.pack.js"></script>


<%
String navigation = WebUtil.getAttributeString(request,"navigation","");
%>

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

<form name="form1" action="claimreport" method="POST">
<input type="hidden" name="navigation" value="exporttopx">
<input type="hidden" name="arah" value="">
<input type="hidden" name="index" value="<c:out value="${index}" />">

<input type="hidden" name="clientId" id="clientId" value="<c:out value="${clientId}" />">
<input type="hidden" name="memberGroupId" id="memberGroupId" value="<c:out value="${memberGroupId}" />">

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Top X Claim Report</h3></td>
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
            	<td class="dataLabel" nowrap="nowrap">&nbsp;</td>
            	<td class="dataLabel"></td>
            </tr>
          <tr>            
            	<td class="dataLabel" nowrap="nowrap">Client Name  : &nbsp;&nbsp;&nbsp;&nbsp;
					<input size="35" name="clientName" id="clientName" class="dataField" value="<c:out value="${clientName}"/>" type="text">
              	</td>
            	<td class="dataLabel">
	            	Group Name &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	            	<input size="35" name="memberGroupName" id="memberGroupName" class="dataField" value="<c:out value="${memberGroupName}"/>" type="text">
	             
	             </td>
            	
            	<td class="dataLabel">
	            	Top Row &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:
	              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	              
	                <select name="topTotal">					
						<option value="-1" >--- All Type ---</option>
						<option value="10" <c:if test="${topTotal eq 10 }">selected</c:if>>TOP 10</option>
						<option value="15" <c:if test="${topTotal eq 15 }">selected</c:if>>TOP 15</option>
						<option value="20" <c:if test="${topTotal eq 20 }">selected</c:if>>TOP 20</option>
						<option value="25" <c:if test="${topTotal eq 25 }">selected</c:if>>TOP 25</option>
						
					</select>
	             </td>
            </tr>
			<tr>
            
            <td class="dataLabel" nowrap="nowrap"> Minimum &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:
              &nbsp;&nbsp;&nbsp;&nbsp;
              
                <input name="minimum_date" id="jscal_field" tab="1" maxlength="10" size="11" readonly="readonly" value="<c:out value="${minDate}" />" type="text">
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
            <td class="dataLabel" nowrap="nowrap"> Maximum &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:
              &nbsp;&nbsp;&nbsp;&nbsp;
              
            <input name="maximum_date" id="jscal_field_max" tabindex="1" maxlength="10" size="11" readonly="readonly" value="<c:out value="${maxDate}" />" type="text">
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
			  	<td class="dataLabel" nowrap="nowrap"></td>
            		<td>
			              
              <input title="Search [Alt+Shift+Q]" accesskey="Q" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" name="buttonDownload" value="Download Report" type="button" onclick="javascript:exportData()">
			</td>
          </tr>
          	<tr>            
            	<td class="dataLabel" nowrap="nowrap">&nbsp;</td>
            	<td class="dataLabel"></td>
            </tr>
        
        </tbody>
      </table></td>
    </tr>
  </tbody>
</table>


<!-- Search Container Stop -->


	
 
<br />

 	
	
	
</form>
	
	<br />


<script language="Javascript">
	$(document).ready(function(){

    
    $("#policyNumber").autocomplete("policy?navigation=lookupjson", {
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
            return  "<font color='#000' style='align: left;' >"+ row.number + " - " + row.groupName +"</font>" ;
        }
	    }).bind("result", function(data, value){
	        $(this).parents("dd").find(".error_message").hide();
	        $("#policyId").val (value.id);
	        $("#policyNumber").val (value.number + " - " + value.groupName);
	    });
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
            return  "<font color='#000' style='align: left;' >"+ row.number + " - " + row.name +"</font>" ;
        }
	    }).bind("result", function(data, value){
	        $(this).parents("dd").find(".error_message").hide();
	        $("#clientId").val (value.id);
	        $("#clientName").val (value.number + " - " + value.name);
	    });
	    $("#memberGroupName").autocomplete("membergroup?navigation=lookupjson", {
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
	            return  "<font color='#000' style='align: left;' >"+ row.number + " - " + row.name +"</font>" ;
	        },
	        extraParams: {
	       		clientId: function() { return $("#clientId").val(); }
	   		}
		    }).bind("result", function(data, value){
		        $(this).parents("dd").find(".error_message").hide();
		        $("#memberGroupId").val (value.id);
		        $("#memberGroupName").val (value.number + " - " + value.name);
	    });
			     
	});

<%
String nav="";
if(request.getAttribute("navigation").equals("gosearch")||request.getAttribute("navigation").equals("golookup")){
	nav = (String)request.getAttribute("navigation");
}
%>
	
function exportData(){
	document.form1.action = "claimreport";
	document.form1.navigation.value = "exporttopx";
	document.form1.submit();
}

</script>
