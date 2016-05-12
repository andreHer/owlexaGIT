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

<form name="form1" action="claimreport" method="POST">
<input type="hidden" name="navigation" value="gosearch">
<input type="hidden" name="arah" value="">
<input type="hidden" name="index" value="<c:out value="${index}" />">
<input type="hidden" id="memberGroupId" name="memberGroupId" value="" />
<input type="hidden" name="claimType" value="" />
				<input type="hidden" name="claimId" value="<c:out value="${claim.claimId}" />">
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Search Claim</h3></td>
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
            <br />
            <td class="dataLabel" nowrap="nowrap">Member Group:
              &nbsp;&nbsp;
              
				<input size="30" name="memberGroupName" id="memberGroupName" class="dataField"  value="<c:out value="${searchtext}"/>" type="text"> &nbsp;&nbsp;
              </td>

			  <td class="dataLabel" nowrap="nowrap">
			Report Format &nbsp;&nbsp;:
              &nbsp;&nbsp;&nbsp;
                <select name="formatReport">					
					<option value="PDF" <c:if test="${claimType eq 'PDF' }">selected</c:if> >PDF</option>
					<option value="DOC" <c:if test="${claimType eq 'DOC' }">selected</c:if>>DOC</option>
					
				</select>	
              </td>
              <td >
              
              
             </td>
            </tr>
			<tr>
            
            <td class="dataLabel" nowrap="nowrap"> Minimum Date :
              &nbsp;&nbsp;
              
                <input name="minimum_date" id="jscal_field" tab="1" maxlength="10" size="11" readonly="readonly" value="<c:out value="${minDate}" />" type="text">
				<img src="images/jscalendar.gif" alt="Enter Date" id="jscal_trigger" align="absmiddle" height="18" width="18">
				
				<script type="text/javascript">
    					Calendar.setup({
        					inputField     :    "jscal_field",     // id of the input field
        					ifFormat       :    "%Y-%m-%d",      // format of the input field
        					button         :    "jscal_trigger",  // trigger for the calendar (button ID)
        					align          :    "Tl",           // alignment (defaults to "Bl")
        					singleClick    :    true
    					});
				 	</script>
              
			</td>
            <td class="dataLabel" nowrap="nowrap"> Maximum Date :
              &nbsp;&nbsp;&nbsp;&nbsp;
              
            <input name="maximum_date" id="jscal_field_max" tabindex="1" maxlength="10" size="11" readonly="readonly" value="<c:out value="${maxDate}" />" type="text">
			<img src="images/jscalendar.gif" alt="Enter Date" id="jscal_max_trigger" align="absmiddle" height="18" width="18">
			
					<script type="text/javascript">
    					Calendar.setup({
        					inputField     :    "jscal_field_max",     // id of the input field
        					ifFormat       :    "%Y-%m-%d",      // format of the input field
        					button         :    "jscal_max_trigger",  // trigger for the calendar (button ID)
        					align          :    "Tl",           // alignment (defaults to "Bl")
        					singleClick    :    true
    					});
				 	</script>
			</td>
			
            <td >
				 <input title="Search [Alt+Shift+Q]" accesskey="Q" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" name="button" value="Download Report" type="button" onclick="javascript:bulkReport()">              

            </td>
          </tr>
          
          			<tr>
            
            <td class="dataLabel" nowrap="nowrap"> 
			</td>
            <td class="dataLabel" nowrap="nowrap"> 
			</td>
			
            <td >
				               

            </td>
          </tr>
          
        </tbody>
      </table></td>
    </tr>
  </tbody>
</table>


<!-- Search Container Stop -->


<!-- Table Container Start -->



<div class="table_container">
	<!-- Table Toolbar Start -->
		
		
		<%
		String nampak = "";
		if(navigation!=null&&navigation.equals("gosearch")){
		}else{
			nampak = " style=\"display: none;\"";
		}
		%>
		
</div>
	<!-- Table Toolbar Stop -->
	
	<!-- Table Content Start -->
	
 
<br />

 	
</form>
	
	<br />
<!-- Table Content Stop -->
	<!-- Table Toolbar Start -->
	
		<!-- Table Toolbar Stop -->



<!-- Table Container Stop -->
<script language="Javascript">
<%
String nav="";

%>

$(document).ready(function(){    
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
            return  "<font color='#000' style='align: left;' >"+  row.name +"</font>" ;
        }                 
	    }).bind("result", function(data, value){
	        $(this).parents("dd").find(".error_message").hide();
	        $("#memberGroupId").val (value.id);
	        $("#memberGroupName").val (value.name);
	    });
	});

function go(){
	document.form1.navigation.value = "<%=nav%>";
	document.form1.submit();
}
function cari (){
	document.form1.navigation.value = "gosearch";
	document.form1.action = "claim";
	document.form1.method = "POST";
	document.form1.submit();
}

function bulkReport(){
	var memberGroupId = document.form1.memberGroupId.value;
	var minDate = document.form1.minimum_date.value;
	var maxDate = document.form1.maximum_date.value;
	
		var urlAdd = "&memberGroupId="+memberGroupId+"&minimum_date="+minDate+"&maximum_date="+maxDate;
	
	window.open ("groupclaimreport?navigation=downloadcostcontainment&url=groupclaimreport"+urlAdd,"Search","width=1024, height=768, menubar=no, status=no, toolbar=no, scrollbars=yes, resizable=yes");
	
}

</script>
