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
<br />
<form name="form1" action="caseglapproved" method="POST">
<input type="hidden" name="navigation" value="golistcaseglapprovedreport">
<input type="hidden" name="arah" value="">
<input type="hidden" name="index" value="<c:out value="${index}" />">
<input type="hidden" name="clientId" id="clientId" value="<c:out value="${clientId}" />" />
<input type="hidden" name="providerId" id="providerId" value="<c:out value="${providerId}" />" />
<input type="hidden" name="diagnosisId" id="diagnosisId" value="<c:out value="${diagnosisId}" />" />

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Case GL Approved Report</h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>
<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td>
      	<table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tbody>
        	<tr>
        		 <td class="dataLabel" nowrap="nowrap">
        		 	Client : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        		 	<input size="40" name="clientText" id="clientText"  value="<c:out value="${clientText}"/>" type="text">
        		 </td>
        		 <td class="dataLabel">
					Hospital : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input size="40" name="providerText" id="providerText"  value="<c:out value="${providerText}"/>" type="text">
				</td>
				<td class="dataLabel" nowrap="nowrap">Case Type:
	              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                <select name="caseCategoryId">
	                	<option value="" <c:if test="${empty caseCategoryId }">selected="true"</c:if>>
							ALL CASE TYPE  
						</option>
	                	<option value="1" <c:if test="${caseCategoryId eq \"1\"}">selected="true"</c:if>>
							INPATIENT  
						</option>
						<option value="2" <c:if test="${caseCategoryId eq \"2\"}">selected="true"</c:if>>
							OUTPATIENT  
						</option>
						<option value="3" <c:if test="${caseCategoryId eq \"3\"}">selected="true"</c:if>>
							MATERNITY
						</option>
						<option value="4" <c:if test="${caseCategoryId eq \"4\"}">selected="true"</c:if>>
							DENTAL
						</option>
						<option value="5" <c:if test="${caseCategoryId eq \"5\"}">selected="true"</c:if>>
							OPTICAL
						</option>
						<option value="7" <c:if test="${caseCategoryId eq \"7\"}">selected="true"</c:if>>
							PRE INPATIENT SERVICE
						</option>
						<option value="6" <c:if test="${caseCategoryId eq \"6\"}">selected="true"</c:if>>
							DR SPECIALIST
						</option>
						<option value="8" <c:if test="${caseCategoryId eq \"8\"}">selected="true"</c:if>>
							POST INPATIENT SERVICE
						</option>
						<option value="10" <c:if test="${caseCategoryId eq \"10\"}">selected="true"</c:if>>
							PRE AND POST INPATIENT SERVICE
						</option>
						<option value="9" <c:if test="${caseCategoryId eq \"9\"}">selected="true"</c:if>>
							MCU
						</option>
						<option value="11" <c:if test="${caseCategoryId eq \"11\"}">selected="true"</c:if>>
							SURGERY SERVICE
						</option>
						<option value="12" <c:if test="${caseCategoryId eq \"12\"}">selected="true"</c:if>>
							LAYANAN KHUSUS
						</option>
						<option value="13" <c:if test="${caseCategoryId eq \"13\"}">selected="true"</c:if>>
							ONE DAY CARE
						</option>
						<option value="14" <c:if test="${caseCategoryId eq \"14\"}">selected="true"</c:if>>
							DR UMUM PPK1
						</option>
						<option value="15" <c:if test="${caseCategoryId eq \"15\"}">selected="true"</c:if>>
							DR GIGI PPK1
						</option>															
					</select>
	              </td>
	              <td class="dataLabel"></td>
          	</tr>
          	<tr>
        		 <td class="dataLabel" nowrap="nowrap">
        		 	Diagnosis : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        		 	<input size="40" name="diagnosisText" id="diagnosisText"  value="<c:out value="${diagnosisText}"/>" type="text">
        		 </td>
        		 <td class="dataLabel" nowrap="nowrap">Status:
	              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                <select name="status">
	                	<option value="" <c:if test="${empty status }">selected</c:if>>ALL STATUS</option>					
						<option value="-1" <c:if test="${status eq -1 }">selected</c:if>>VOID</option>
						<option value="1" <c:if test="${status eq 1 }">selected</c:if>>OPEN</option>					
						<option value="9" <c:if test="${status eq 9 }">selected</c:if>>APPROVED</option>
						<option value="4" <c:if test="${status eq 4 }">selected</c:if>>REJECTED</option>
						<option value="5" <c:if test="${status eq 5 }">selected</c:if>>CLOSED</option>
						<option value="10" <c:if test="${status eq 10 }">selected</c:if>>PENDING</option>
						<option value="2" <c:if test="${status eq 2 }">selected</c:if>>PENDING DOCUMENT</option>
						<option value="7" <c:if test="${status eq 7 }">selected</c:if>>PENDING INVESTIGATION</option>
						<option value="15" <c:if test="${status eq 15 }">selected</c:if>>FINALIZED</option>
						<option value="17" <c:if test="${status eq 17 }">selected</c:if>>REFERED</option>															
						<option value="18" <c:if test="${status eq 18 }">selected</c:if>>GREY AREA</option>
						<option value="19" <c:if test="${status eq 19 }">selected</c:if>>PRE OPEN</option>
						<option value="20" <c:if test="${status eq 20 }">selected</c:if>>PRE AUTH</option>
						<option value="21" <c:if test="${status eq 21 }">selected</c:if>>PRE REJECT</option>														
					</select>
	              </td>
				<td class="dataLabel">
				</td>
				<td class="dataLabel">
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
              
            <input name="maximum_date" id="jscal_field_max" tabindex="1" maxlength="10" size="11"  value="<c:out value="${maximumDate}" />" type="text">
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
            <td class="dataLabel">
              	<input title="Search [Alt+Shift+Q]" accesskey="Q" onClick="javascript:cari();" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" name="button" value="Search" type="button">
				<input title="" accesskey="Q" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" name="button" value="Download" type="button" onclick="javascript:downloadExcel()">
			</td>
			<td class="dataLabel">
				</td>
          </tr>
		
        </tbody>
      	</table>
      </td>
    </tr>
  </tbody>
</table>


<br />

 	
<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	<tr>
      <td colspan="20" align="right"><table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tbody>
          <tr>
            <td class="listViewPaginationTdS1" align="left"></td>
            <td class="listViewPaginationTdS1" align="right" nowrap="nowrap"  >
				
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
		<td width="4%" nowrap="nowrap" class="listViewThS1" scope="col">
			<img src="images/blank.gif" alt="asd" height="1" width="1">No.</td>		

<!-- ini default generated table from database -->
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">
		Case Number </td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">
		Member Name </td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">
		Card Number </td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">
		Client </td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">
		Group </td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">
		Hospital </td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">
		Type </td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">
		Diagnosis </td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
		Case Date </td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
		Limit Benefit </td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
		Charge </td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
		Approved </td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
		Excess </td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
		Status </td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
		Description </td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
		Long of Stay </td>
	</tr>


	<c:forEach items="${Cases}" var="myCase" varStatus="status" >
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
			<c:out value="${myCase.caseNumber}" />
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<c:out value="${myCase.memberId.firstName}" />
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<c:out value="${myCase.memberId.currentCardNumber}" />
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<c:out value="${myCase.memberId.clientName}" /> 
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<c:out value="${myCase.memberId.groupName}" /> 
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<c:out value="${myCase.providerId.providerName}" /> 
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<c:out value="${myCase.caseCategoryId.caseCategoryCode}" />
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe"  valign="top">						
			<c:out value="${myCase.diagnosis1Id.description}" />						
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<c:out value="${myCase.caseStartTime}" /> to <c:out value="${myCase.caseEndTime}" />
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe"  valign="top">						
			<fmt:formatNumber><c:out value="${myCase.preRemainingLimit}" /></fmt:formatNumber>						
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe"  valign="top" align="right">						
			<fmt:formatNumber><c:out value="${myCase.caseClaimValue}" /></fmt:formatNumber>						
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe"  valign="top" align="right">						
			<fmt:formatNumber><c:out value="${myCase.caseApprovedValue}" /></fmt:formatNumber>						
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe"  valign="top" align="right">						
			<fmt:formatNumber><c:out value="${myCase.caseExcessValue}" /></fmt:formatNumber>						
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:if test="${myCase.caseStatusId.caseStatusId eq 1}">OPEN</c:if>
		      <c:if test="${myCase.caseStatusId.caseStatusId eq 2}">PENDING DOCUMENT</c:if>
		      <c:if test="${myCase.caseStatusId.caseStatusId eq 3}">VERIFIED</c:if>
		      <c:if test="${myCase.caseStatusId.caseStatusId eq 4}">REJECTED</c:if>
		      <c:if test="${myCase.caseStatusId.caseStatusId eq 5}">CLOSED</c:if>
		      <c:if test="${myCase.caseStatusId.caseStatusId eq 9}">APPROVED</c:if>
		      <c:if test="${myCase.caseStatusId.caseStatusId eq 10}">PENDING</c:if>
		      <c:if test="${myCase.caseStatusId.caseStatusId eq 15}">FINALIZED</c:if>
		      <c:if test="${myCase.caseStatusId.caseStatusId eq 17}">REFERED</c:if>
		      <c:if test="${myCase.caseStatusId.caseStatusId eq 18}">GREY AREA</c:if>
		      <c:if test="${myCase.caseStatusId.caseStatusId eq 19}">PRE OPEN</c:if>
		      <c:if test="${myCase.caseStatusId.caseStatusId eq 20}">PRE AUTH</c:if>
		      <c:if test="${myCase.caseStatusId.caseStatusId eq 21}">PRE REJECT</c:if>
		      <c:if test="${myCase.caseStatusId.caseStatusId eq -1}">VOID</c:if>
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe"  valign="top">						
			<c:out value="${myCase.description}" />						
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe"  valign="top">						
			<c:out value="${myCase.longOfStay}" />						
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
	

<script language="Javascript">
$(document).ready(function(){
 	$("#providerText").autocomplete("provider?navigation=lookupjson", {
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
        $("#providerId").val(value.id);   
        $("#providerText").val(value.name);     
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
    
    $("#diagnosisText").autocomplete("diagnosis?navigation=lookupjson", {
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
        $("#diagnosisId").val(value.id);   
        $("#diagnosisText").val(value.name);     
    });
});
<%
String nav="";
if(request.getAttribute("navigation").equals("golist")||request.getAttribute("navigation").equals("golookup")||request.getAttribute("navigation").equals("golistcaseglapprovedreport")
	||request.getAttribute("navigation").equals("glapprovedreport")){
	nav = (String)request.getAttribute("navigation");
}
%>
function tambahi (){
	document.form1.navigation.value = "tambah";
	document.form1.action = "case-form";
	document.form1.method = "GET";
	document.form1.submit();
}
function hapus (idx){
	var delConfirm = window.confirm ("Are You Sure Want To Delete This Entry ?");

	if (delConfirm){
		document.form1.method = "POST";
		document.form1.caseId.value = idx;
		document.form1.action = "case";
		document.form1.navigation.value = "delete";
		document.form1.submit();
	}
}
function ubah (idx){
	document.form1.method = "GET";
	document.form1.caseId.value = idx;
	document.form1.action = "case-form";
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
	document.form1.navigation.value = "golistcaseglapprovedreport";
	document.form1.action = "caseglapproved";
	document.form1.method = "POST";
	document.form1.submit();
}
function detil (idx){
	document.form1.method = "POST";
	document.form1.caseId.value = idx;
	document.form1.action = "case";
	document.form1.navigation.value = "detail";
	document.form1.submit();

}
function downloadExcel (){
	document.form1.navigation.value = "downloadExcelCaseGLApproved";
	document.form1.action = "caseglapproved";
	document.form1.method = "POST";
	document.form1.submit();
}
</script>
