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


<!-- Search Container Start -->

<form name="form1" action="payment" method="POST">
<input type="hidden" name="navigation" value="gosearch">
<input type="hidden" name="sortcolumn" value="<c:out value="${sortcolumn}" />">
<input type="hidden" name="sortorder" value="<c:out value="${sortorder}" />">
<input type="hidden" name="columntosort" value="<c:out value="${columntosort}" />">
<input type="hidden" name="arah" value="">
<input type="hidden" name="index" value="<c:out value="${index}" />">
<input type="hidden" name="paymentId" value="<c:out value="${payment.paymentId}" />">
<input type="hidden" name="clientId" id="clientId" value="<c:out value="${clientId}" />" />
<input type="hidden" name="clientNumber" id="clientNumber" value="<c:out value="${clientNumber}" />" />
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Delivery Letter Monitor</h3></td>
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
	          	<td class="dataLabel" nowrap="nowrap">Client :
	          		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	          		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input size="40" name="clientText" id="clientText"  value="<c:out value="${clientText}"/>" type="text">
	        	</td>
	            <td class="dataLabel">
	              <input title="Search [Alt+Shift+Q]" accesskey="Q" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" name="buttonSearch" value="Search" type="button" onclick="javascript:cari();"> 
	              <input title="Download Recap" accesskey="P" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:printDeliveryLetterGlobal()" name="dlRecap" value=" Global Delivery Letter " type="button">
	                          
				</td>
        	</tr>
			<tr>
	            <td class="dataLabel" nowrap="nowrap"> Date Of Submission :
	              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	              
	                <input name="checkDate" id="jscal_field" tab="1" maxlength="10" size="11" value="<c:out value="${checkDate}" />" type="text">
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
            <td align="right">
            </td>
          </tr> 
        </tbody>
      </table></td>
    </tr>
  </tbody>
</table>

	<!-- Table Toolbar Stop -->
	
	<!-- Table Content Start -->

<br />

 	
<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	<tr>
      <td colspan="20" align="right"><table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tbody>
        </tbody>
      </table></td>
    </tr>
	<tr height="20">
		<td width="2%" nowrap="nowrap" class="listViewThS1" scope="col">No.</td>		

<!-- ini default generated table from database -->   		  
		<td width="2%" nowrap="nowrap" class="listViewThS1" scope="col">Hospital</td>	
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">Invoice</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Payment Number
		</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">Date of Received
		</td>
	   	<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Date of Submission
	   	</td>  			
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">Total Claim
		</td>		
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Total Payment
		</td>
	 	<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">User Modified
	 	</td>
	 	<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">User Created
	 	</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Status
		</td>
		
	</tr>

	<% int i = 0; %>
	<c:set var="claimTotal" value="0"/>
	<c:set var="paymentTotal" value="0"/>
	<c:forEach items="${paymentCollection}" var="payment" varStatus="status" >
	<%	
		i++;
	%>
	 <tr height="20">
     	<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><%=i%></td>
     	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
     		<c:choose>
     			<c:when test="${empty payment[1] }">
     				NPC
     			</c:when>
     			<c:otherwise>
     				<c:out value="${payment[1] }"/>
     			</c:otherwise>
     		</c:choose>
  		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
	  		<c:out value="${payment[2] }"/>
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">	
		  	<c:out value="${payment[3] }"/>
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<c:out value="${payment[4] }"></c:out>
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">	
			<c:out value="${payment[5] }"/>
		</td>	
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
			<c:out value="${payment[6] }"/>
			<c:set var="claimTotal" value="${claimTotal + payment[6] }"/>
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
			<fmt:formatNumber><c:out value="${payment[7] }"/></fmt:formatNumber>	
			<c:set var="paymentTotal" value="${paymentTotal + payment[7] }"/>
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">	
			<c:out value="${payment[8] }"/>	
		</td>	
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">	
			<c:out value="${payment[10] }"/>	
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">	
			<c:choose>
				<c:when test="${payment[14].claimTypeId eq 1 }">
					REIMBURSMENT
				</c:when>
				<c:when test="${payment[14].claimTypeId eq 2 }">
					CASHLESS
				</c:when>
				<c:otherwise>
					REIMBURSMENT KHUSUS
				</c:otherwise>
			</c:choose>
			<%-- <c:out value="${payment[10] }"/> --%>	
		</td>	   		   		   		   		
    </tr>
   
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	
	</c:forEach>
	<tr>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" colspan="5"></td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
			<b>TOTAL</b>
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
			<b><c:out value="${claimTotal }"/></b>
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
			<b><fmt:formatNumber><c:out value="${paymentTotal }"/></fmt:formatNumber></b>
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" colspan="3">
	</tr>
	
<tr>
	</tbody>
	</table>
</form>
	
<!-- Table Content Stop -->
	<!-- Table Toolbar Start -->
	
		<!-- Table Toolbar Stop -->



<!-- Table Container Stop -->
<script language="Javascript">
$(document).ready(function(){
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
            return  "<font color='#000' style='align: left;' >"+  row.name +"</font>" ;
        }     
    }).bind("result", function(data, value){
        $(this).parents("dd").find(".error_message").hide();
        $("#clientId").val (value.id);
        $("#clientText").val (value.name);
        $("#clientNumber").val (value.number);
    });
});

<%
String nav="";
if(request.getAttribute("navigation").equals("gosearch")||request.getAttribute("navigation").equals("golookup")){
	nav = (String)request.getAttribute("navigation");
}
%>
function tambahi (){
	document.form1.navigation.value = "tambah";
	document.form1.action = "payment-form";
	document.form1.method = "GET";
	document.form1.submit();
}
function hapus (idx){
	var delConfirm = window.confirm ("Are You Sure Want To Delete This Entry ?");

	if (delConfirm){
		document.form1.method = "POST";
		document.form1.paymentId.value = idx;
		document.form1.action = "payment";
		document.form1.navigation.value = "delete";
		document.form1.submit();
	}
}
function ubah (idx){
	document.form1.method = "GET";
	document.form1.paymentId.value = idx;
	document.form1.action = "payment-form";
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
	document.form1.navigation.value = "gosearchreportmonitor";
	document.form1.action = "paymentmonitor";
	document.form1.method = "POST";
	document.form1.submit();
}
function detil (idx){
	document.form1.method = "POST";
	document.form1.paymentId.value = idx;
	document.form1.action = "payment";
	document.form1.navigation.value = "detail";
	document.form1.submit();

}
function exportDisposition(){
	document.form1.method = "POST";
	document.form1.navigation.value = "exportdeliver";
	document.form1.action = "payment";
	document.form1.submit();
}
function carisort(param){
	document.form1.navigation.value = "gosearchbysort";
	document.form1.searchtext.value="<c:out value="${searchtext}" />";
	document.form1.searchby.value="<c:out value="${searchby}" />";
	document.form1.sortcolumn.value= param;
	document.form1.method = "POST";
	document.form1.submit();
}
function printDeliveryLetterGlobal(){		
	window.open ("paymentmonitor?navigation=printreportmonitor&clientId=<c:out value="${clientId}" />&clientText=<c:out value="${clientText}" />&clientNumber=<c:out value="${clientNumber}" />&checkDate=<c:out value="${checkDate}" />",
	"Search","width=1024, height=768, menubar=yes, status=no, toolbar=no, scrollbars=yes, resizable=yes");		
}
</script>
