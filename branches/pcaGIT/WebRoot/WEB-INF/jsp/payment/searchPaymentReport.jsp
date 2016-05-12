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

<form name="form1" action="payment" method="POST">
<input type="hidden" name="navigation" value="gosearch">
<input type="hidden" name="arah" value="">
<input type="hidden" name="index" value="<c:out value="${index}" />">
				<input type="hidden" name="paymentId" value="<c:out value="${payment.paymentId}" />">
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Search Payment</h3></td>
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
              &nbsp;&nbsp;              
					<input size="30" name="clientName" id="clientName" class="dataField" value="<c:out value="${clientName}"/>" type="text">
					<input type="hidden" name="clientId" id="clientId" value="<c:out value="${clientId}" />" />
					              
			</td>
            <td class="dataLabel" nowrap="nowrap">Provider :
              &nbsp;&nbsp;
              		
              		<input size="30" name="providerName" id="providerName" class="dataField" value="<c:out value="${providerName}"/>" type="text">
					<input type="hidden" name="providerId" id="providerId" value="<c:out value="${providerId}" />" />
                
			
				
              </td>
              
			  <td class="dataLabel" nowrap="nowrap">Status:
              &nbsp;&nbsp;
              
                <select name="searchstatus">		
					<option value="">-- All Status --</option>
   					<option value="1" <c:if test="${searchstatus eq 1 }">selected</c:if> >OPEN</option>			
					<option value="2" <c:if test="${searchstatus eq 2 }">selected</c:if>>PAID</option>
					<option value="3" <c:if test="${searchstatus eq 3 }">selected</c:if>>APPROVED</option>					
					<option value="4" <c:if test="${searchstatus eq 4 }">selected</c:if>>BAD</option>
					<option value="5" <c:if test="${searchstatus eq 5 }">selected</c:if>>PENDING</option>
					<option value="6" <c:if test="${searchstatus eq 6 }">selected</c:if>>DISPOSITIONED</option>
					<option value="7" <c:if test="${searchstatus eq 7 }">selected</c:if>>PARTIAL PAID</option>
								
				</select>
				 
              </td>
            <td class="dataLabel">
              <input title="Search [Alt+Shift+Q]" accesskey="Q" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" name="buttonSearch" value="Search" type="button" onclick="javascript:cari();"> 
              <input title="Download Report [Alt+Shift+R]" accesskey="R" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" name="buttonDownload" value="Download" type="button" onclick="javascript:download()">
                          
			</td>
            </tr>
            <tr>
            <td class="dataLabel" nowrap="nowrap">Group :
              &nbsp;&nbsp;              
					<input size="30" name="memberGroupName" id="memberGroupName" class="dataField" value="<c:out value="${memberGroupName}"/>" type="text">
					<input type="hidden" name="memberGroupId" id="memberGroupId" value="<c:out value="${memberGroupId}" />" />
              	</td>
            	<td class="dataLabel" nowrap="nowrap">
              	</td>
              
			  		<td class="dataLabel" nowrap="nowrap"></td>
            		<td class="dataLabel">
            			<input title="Download Report [Alt+Shift+R]" accesskey="R" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" name="btnClear" value="Clear Search Parameter" type="button" onclick="javascript:clearParam()">
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
        					align          :    "Tl",           // alignment (defaults to "Bl")
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
        					align          :    "Tl",           // alignment (defaults to "Bl")
        					singleClick    :    true
    					});
				 	</script>
			</td>
            <td class="dataLabel">&nbsp;&nbsp;
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
          <tr>
            <td class="listViewPaginationTdS1" align="left"></td>
            <td class="listViewPaginationTdS1" align="right" nowrap="nowrap" >
				
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

<!-- ini default generated table from database -->
		   		   		   		   		   			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">
				Payment Number &nbsp;</td>
			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Batch Number &nbsp;</td>
			
		
			   			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">
				Bank Name &nbsp;</td>
			
		
			   			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Account Number &nbsp;</td>
			
		
			   			
			   			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">
				Payee Name &nbsp;</td>
			
					
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Due Date &nbsp;</td>
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Payment Date &nbsp;</td>
					
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Status&nbsp;</td>
			
		
	</tr>


	<c:forEach items="${Payments}" var="payment" varStatus="status" >
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
			
				<a href="payment?navigation=detail&index=<c:out value="${index}" />&paymentId=<c:out value="${payment.paymentId}" />" class="linkDetail"><c:out value="${payment.paymentNumber}" /></a>
			
		</td>
		   		   		   		   		   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<a href="batchclaim?navigation=detail&index=<c:out value="${index}" />&batchClaimId=<c:out value="${payment.batchClaim.batchClaimId}" />" class="linkDetail">
				<c:out value="${payment.batchClaim.batchClaimNumber}" />
				</a>
			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${payment.bankName}" />
			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${payment.accountNumber}" />
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${payment.payeeName}" />
			
		</td>
			
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${payment.batchClaim.batchDueDate}" />
			
		</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${payment.paymentConfirmDate}" />
			
		</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				
				<c:if test="${payment.paymentStatus.paymentStatusId eq 1}">OPEN</c:if>
				<c:if test="${payment.paymentStatus.paymentStatusId eq 2}">PAID</c:if>
				<c:if test="${payment.paymentStatus.paymentStatusId eq 3}">APPROVED</c:if>
				<c:if test="${payment.paymentStatus.paymentStatusId eq 4}">BAD</c:if>
				<c:if test="${payment.paymentStatus.paymentStatusId eq 5}">PENDING</c:if>
				<c:if test="${payment.paymentStatus.paymentStatusId eq 6}">DISPOSITIONED</c:if>
				<c:if test="${payment.paymentStatus.paymentStatusId eq 7}">PARTIAL PAID</c:if>
				
			
		</td>		   		   		   		   		
					   		   		
					   		   		
					
    </tr>
   
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	
	</c:forEach>
	
<tr>
            <td class="listViewPaginationTdS1" align="left"></td>
            <td class="listViewPaginationTdS1" align="right" nowrap="nowrap"  colspan=20>
				
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
    
    $("#providerName").autocomplete("provider?navigation=lookupjson", {
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
	    $("#providerId").val(value.id);
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
            return "<font color='#000' >"+ row.name +"</font>" ;
        }
    }).bind("result", function(data, value){
        $(this).parents("dd").find(".error_message").hide();             
	    $("#clientId").val(value.id);
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
            return "<font color='#000' >"+ row.name +"</font>" ;
        }
    }).bind("result", function(data, value){
        $(this).parents("dd").find(".error_message").hide();             
	    $("#memberGroupId").val(value.id);
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
function clearParam (){
	$("#memberGroupId").val("");
	$("#memberGroupName").val("");
	$("#providerId").val("");
	$("#providerName").val("");
	$("#clientId").val("");
	$("#clientName").val("");
	$("#jscal_field").val("");
	$("#jscal_field_max").val("");
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
	document.form1.navigation.value = "gosearchreport";
	document.form1.action = "payment";
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
function download(){
	document.form1.method = "POST";
	document.form1.navigation.value = "downloadreport";
	document.form1.action = "payment";
	document.form1.submit();
}
</script>
