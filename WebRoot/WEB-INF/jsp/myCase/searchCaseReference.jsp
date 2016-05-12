<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collection"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<link rel="stylesheet" type="text/css" href="css/autocomplete.css">
<link rel="stylesheet" type="text/css" href="css/button.css">

<!-- <link rel="stylesheet" type="text/css" href="css/jquery/autocomplete/jquery.autocomplete.css"/> -->

<!-- <script type="text/javascript" src="scripts/jquery/jquery-1.4.4.min.js"></script> -->
<!-- <script type="text/javascript" src="scripts/jquery/plugin/jquery.autocomplete.pack.js"></script> -->
	
<% /* Edit 30032015, Pengaturan Style untuk Tiap warna Case */ %>
<style type="text/css">
    tr.caseopen td{ background-color : #fbf314; }
    tr.other td{ background-color : #e7f0fe; }
    tr.level1 td{ background-color: #a5fff7; }
    tr.level2 td{ background-color: #21d0ff; }
    tr.level3 td{ background-color: #009fff; }
    legend.other {background-color: #e7f0fe; display: block;padding-left: 2px; padding-right: 2px; border-style: outset; border-color: #e7f0fe; height: 12px;}
    legend.level1 { background-color: #a5fff7; display: block; padding-left: 2px; padding-right: 2px; border-style: outset; border-color: #A9F5F2; height: 12px;}
   	legend.level2 { background-color: #21d0ff; display: block; padding-left: 2px; padding-right: 2px; border-style: outset; border-color: #58D3F7; height: 12px;}
    legend.level3 { background-color: #009fff; display: block; padding-left: 2px; padding-right: 2px; border-style: outset; border-color: #01A9DB; height: 12px;}
    legend.caseopen { background-color : #fdf200;  display: block; padding-left: 2px; padding-right: 2px; border-style: outset; border-color: #fdf200; height: 12px;}
</style>

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

// boolean sortCaseNumber = (Boolean) request.getAttribute("sortCaseNumber");
// boolean sortMemberName = (Boolean) request.getAttribute("sortMemberName");
// boolean sortProvider = (Boolean) request.getAttribute("sortProvider");
// boolean sortAdmissionDate = (Boolean) request.getAttribute("sortAdmissionDate");
// boolean sortDischargeDate = (Boolean) request.getAttribute("sortDischargeDate");
// boolean sortType = (Boolean) request.getAttribute("sortType");
// boolean sortLimit = (Boolean) request.getAttribute("sortLimit");
// boolean sortDiagnosis = (Boolean) request.getAttribute("sortDiagnosis");
// boolean sortCharge = (Boolean) request.getAttribute("sortCharge");
// boolean sortAprrove = (Boolean) request.getAttribute("sortAprrove");
// boolean sortExcess = (Boolean) request.getAttribute("sortExcess");
// boolean sortStatus = (Boolean) request.getAttribute("sortStatus");
// boolean sortModifiedTime = (Boolean) request.getAttribute("sortModifiedTime");

try {
	index = ((Integer) request.getAttribute ("index")).intValue();
	count = ((Integer) request.getAttribute ("count")).intValue();
	//modified by aju on 20150420, for getting the user viewCount settings :D
	//countSet = ((Integer) request.getAttribute ("countSet")).intValue();
	/* if(request.getAttribute ("countSet") != null){
		countSet = ((Integer) request.getAttribute ("viewCountSet")).intValue();
	} 
	else{ */
		countSet = ((Integer) request.getAttribute ("countSet")).intValue();
	/* } */
	
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


<form name="form1" action="case" method="POST">
<input type="hidden" name="navigation" value="gosearchReference">
<input type="hidden" name="arah" value="">
<input type="hidden" name="sortcolumn" value="<c:out value="${sortcolumn}" />">
<input type="hidden" name="sortorder" value="<c:out value="${sortorder}" />">
<input type="hidden" name="columntosort" value="<c:out value="${columntosort}" />">
<input type="hidden" name="index" value="<c:out value="${index}" />">
<input type="hidden" name="caseId" value="<c:out value="${myCase.caseId}" />">
<input type="hidden" name="providerId" id="providerId" value="<c:out value="${providerId}" />" />



<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Search Case Reference</h3></td>
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
				<div id="timeDiv" align="center" style="color:#FCECD3;border-width: 4px;border-style: outset;border-color:black;">Refresh Page in &nbsp;<span id="time"></span></div>
            		<td class="dataLabel" nowrap="nowrap">Client Name*: 
            		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input size="20" name="clientName" id = "clientName" class="dataField" tab="1" value="<c:out value="${clientName}"/>" type="text"  >
              		</td>
              		<td class="dataLabel" nowrap="nowrap">Group Name:				
						 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input size="20" name="groupName" class="dataField" value="<c:out value="${groupName}"/>" type="text">						
					</td>
              		<td class="dataLabel" nowrap="nowrap">Service Name*: 
              		&nbsp;&nbsp;
<!-- 						<input size="20" name="serviceName" id="serviceName" class="dataField" value="<c:out value="${serviceName}"/>" type="text" > -->
						
						<select name="serviceName" id="serviceName" class="inputbox">		 
							<option <c:if test="${serviceName eq \"INPATIENT\"}">selected="true"</c:if>>
								INPATIENT  
							</option>
							<option <c:if test="${serviceName eq \"OUTPATIENT\"}">selected="true"</c:if>>
								OUTPATIENT  
							</option>
							<option <c:if test="${serviceName eq \"MATERNITY\"}">selected="true"</c:if>>
								MATERNITY
							</option>
							<option <c:if test="${serviceName eq \"DENTAL\"}">selected="true"</c:if>>
								DENTAL
							</option>
							<option <c:if test="${serviceName eq \"OPTICAL\"}">selected="true"</c:if>>
								OPTICAL
							</option>
							<option <c:if test="${serviceName eq \"PRE INPATIENT SERVICE\"}">selected="true"</c:if>>
								PRE INPATIENT SERVICE
							</option>
							<option <c:if test="${serviceName eq \"DR SPECIALIST\"}">selected="true"</c:if>>
								DR SPECIALIST
							</option>
							<option <c:if test="${serviceName eq \"POST INPATIENT SERVICE\"}">selected="true"</c:if>>
								POST INPATIENT SERVICE
							</option>
							<option <c:if test="${serviceName eq \"PRE AND POST INPATIENT SERVICE\"}">selected="true"</c:if>>
								PRE AND POST INPATIENT SERVICE
							</option>
							<option <c:if test="${serviceName eq \"MCU\"}">selected="true"</c:if>>
								MCU
							</option>
							<option <c:if test="${serviceName eq \"SURGERY SERVICE\"}">selected="true"</c:if>>
								SURGERY SERVICE
							</option>
							<option <c:if test="${serviceName eq \"LAYANAN KHUSUS\"}">selected="true"</c:if>>
								LAYANAN KHUSUS
							</option>
							<option <c:if test="${serviceName eq \"ONE DAY CARE\"}">selected="true"</c:if>>
								ONE DAY CARE
							</option>
							<option <c:if test="${serviceName eq \"DR UMUM PPK1\"}">selected="true"</c:if>>
								DR UMUM PPK1
							</option>
							<option <c:if test="${serviceName eq \"DR GIGI PPK1\"}">selected="true"</c:if>>
								DR GIGI PPK1
							</option>					
						</select>					
              		</td>
              		 <td class="dataLabel" nowrap="nowrap">Provider Name:
					  &nbsp;&nbsp;
						<input size="20" name="providerName" id="providerName" value="<c:out value="${providerName}"/>" type="text">
              		</td> 
              		
              		</td>
		            <td class="dataLabel">
		                <input title="Search [Alt+Shift+Q]" accesskey="Q" onClick="javascript:cari();" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" name="button" value="Search" type="button" id="search">
                        
					</td>
              </tr>
             
              <tr height="32">
              		<td class="dataLabel" nowrap="nowrap">Diagnosis Name:
              		 &nbsp;
						<input size="20" name="diagnosisName" class="dataField" value="<c:out value="${diagnosisName}"/>" type="text">
              		</td>
              		
              		 <td class="dataLabel" nowrap="nowrap">Country:
					 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input size="20" name="countryName" id="countryName" tab="10" value="<c:out value="${countryName}"/>" type="text">
						<input type="hidden" id="countryId" name="countryId" value="" />
              		</td> 
              		
              		</td>
              		<td class="dataLabel" nowrap="nowrap">Province: 
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input size="20" name="provinceName" tab="1" id="provinceName" class="dataField" value="<c:out value="${provinceName}"/>" type="text">
						<input type="hidden" id="provinceId" name="provinceId" value="" />
              		</td>
              		
              		<td class="dataLabel" nowrap="nowrap">City:
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input size="20" name="cityName" id="cityName" tab="20"  value="<c:out value="${cityName}"/>" type="text" >
						<input type="hidden" id="cityId" name="cityId" value="" />
              		</td>
               </tr>
               <tr >
                <td class="dataLabel" nowrap="nowrap"> Minimum Date :
             		 &nbsp;&nbsp;&nbsp;&nbsp;
              
                	<input name="minimum_date" id="jscal_field" tab="1" maxlength="10" size="11" readonly="readonly" value="<c:out value="${minDate}" />" type="text">
					<img src="images/jscalendar.gif" alt="Enter Date" id="jscal_trigger" align="absmiddle" height="18" width="18">
				
					<script type="text/javascript">
    					Calendar.setup({
        					inputField     :    "jscal_field",     // id of the input field
        					ifFormat       :    "%Y-%m-%d",      // format of the input field
        					button         :    "jscal_trigger",  // trigger for the calendar (button ID)
        					align          :    "Bl",//"Tl",           // alignment (defaults to "Bl")
        					singleClick    :    true
    					});
				 	</script>
              </td>
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
        					align          :    "Bl",//"Tl",           // alignment (defaults to "Bl")
        					singleClick    :    true
    					});
				 	</script>
				</td>
            	
				<td class="dataLabel" nowrap="nowrap">
				Date Category  &nbsp;&nbsp;:
              		&nbsp;&nbsp;
              
                	<select name="dateBy">					
					<option value="admissionDate" <c:if test="${dateBy eq 'admissionDate' }">selected</c:if> >ADMISSION DATE</option>
					<option value="dischargeDate" <c:if test="${dateBy eq 'dischargeDate' }">selected</c:if>>DISCHARGE DATE</option>
					
				</select>				
              </td>
            <td align="left">
            	</td>	
           		 <td class="dataLabel">&nbsp;&nbsp;
             	 </td>
           		 <td align="right">
           		 </td>
          </tr>
              		
        	</tbody>
      	</table>
      </td>
    </tr>
  </tbody>
</table>

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
		<td width="1%" nowrap="nowrap" class="listViewThS1" scope="col">No.</td>
		
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="35%">Diagnosis Name &nbsp;
		<%-- 	<input type="hidden" name="sortByOrderDiagnosis" value="<c:out value="${sortDiagnosis?'true':'false'}" />">
			<a href="javascript:carisort('diagnosis')" class="listViewThLinkS1">
			<img src="images/arrow_<c:out value="${sortDiagnosis?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
		 --%></td>	
		
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="20%">Avarage Cost &nbsp;
		<%-- 	<input type="hidden" name="sortByOrderAvarageCost" value="<c:out value="${sortAvarageCost?'true':'false'}" />">
			<a href="javascript:carisort('avaragecost')" class="listViewThLinkS1">
			<img src="images/arrow_<c:out value="${sortAvarageCost?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
		 --%></td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="20%">Maximum Cost &nbsp;
		<%-- 	<input type="hidden" name="sortByOrderMaxCost" value="<c:out value="${sortMaxCost?'true':'false'}" />">
			<a href="javascript:carisort('maxcost')" class="listViewThLinkS1">
			<img src="images/arrow_<c:out value="${sortMaxCost?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
		 --%></td>
 		<td scope="col" class="listViewThS1" nowrap="nowrap" width="20%">Minimum Cost &nbsp;
		<%-- 	<input type="hidden" name="sortByOrderMinCost" value="<c:out value="${sortMinCost?'true':'false'}" />">
			<a href="javascript:carisort('mincost')" class="listViewThLinkS1">
			<img src="images/arrow_<c:out value="${sortMinCost?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
		 --%></td>
 		<td scope="col" class="listViewThS1" nowrap="nowrap" width="20%">Avarage LOS &nbsp;
		<%-- 	<input type="hidden" name="sortByOrderAvarageLOS" value="<c:out value="${sortAvarageLOS?'true':'false'}" />">
			<a href="javascript:carisort('avaragelos')" class="listViewThLinkS1">
			<img src="images/arrow_<c:out value="${sortAvarageLOS?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
		 --%></td>
		
		
	</tr>

	<%-- <c:set var="casecolorset"></c:set>
	<c:forEach items="${Cases}" var="myCase" varStatus="status" >
	<%	if (i % 2 == 0) {
			rowclass = "col1";
		} else if (i % 2 != 0) {
			rowclass= "col2";
		}
		i++;
	%> --%>
	<c:set var="casecolorset"></c:set>
	<c:set var="totalClaim" value="0"/>
	<c:set var="claimValue"/>
	
	<% int io = 0;
								Double totalClaim = 0.0;
								Double totalClaimPerGroup = 0.0;
								//Long countClaim = 0L;
								Collection<Object[]> claimCollection = (Collection<Object[]>) request.getAttribute("Cases");
								if (claimCollection != null && claimCollection.size() > 0){
									Iterator<Object[]> claimIterator = claimCollection.iterator();
									if (claimIterator != null){
										while (claimIterator.hasNext()){
										Object[] claim = claimIterator.next();
										/* //totalClaim += (Double)claim[2]; */
										//countClaim += (Long)claim[4];
							 %>
	
	 <tr height="20" style="border-top: 1px solid #000;" class="<c:out value="${casecolorset}" />">
	   <%--  <td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><%=(i+((indexint-1)*countSet))%></td>
		<td class="oddListRowS1" bgcolor="#e7f0fe"  valign="top">						
			<c:out value="${myCase.diagnosisId.description}" />						
		</td>
		
		<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top">
			<c:out value="${avaragecost}" /> 
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top">
			<c:out value="${maxcost}" />
		</td>
		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">	
			<c:out value="${mincost}" />
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top">
 			<c:out value="${avaragelos}" />  
 		
		 <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<c:out value="${myCase.caseEndTime}" />						
		</td>  --%>
		
												<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
													<c:out value="<%= ++io %>"/>
												</td>
												<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
													&nbsp;<c:out value="<%= (String)claim[0] %>" />
												</td>
												<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
												<% DecimalFormat formatter = new DecimalFormat("#0.00"); %>
													&nbsp;<c:out value="<%= formatter.format((Double)claim[1]) %>" />
												</td>
												<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
													&nbsp;<c:out value="<%= formatter.format((Double)claim[2]) %>" />
												</td>
												<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
													&nbsp;<c:out value="<%= formatter.format((Double)claim[3]) %>" />
												</td>
												<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
													&nbsp;<c:out value="<%= (Double)claim[4] %>" />
												</td>
		
    </tr>
    
    <%
										}
									}
								}
							 %>
	<tr>
    	<td colspan="20" class="listViewHRS1"></td>
    </tr>
	<%-- </c:forEach> --%>
	
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
	document.getElementById('timeDiv').style.visibility = 'hidden';
// 	startTimer();
	//diffInDays('2015-03-24');
        $("#providerName").autocomplete({
			source: function( request, response ) {
			$.ajax({
				url: "provider?navigation=lookupjson",
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
		        $("#providerId").val (ui.item.id);
		        $("#providerName").val (ui.item.label);
			}
		})
		.autocomplete( "instance" )._renderItem = function( ul, item ) {
	      return $( "<a>" )
	        .append( item.label )
	        .appendTo( ul );
	    };
      
     $("#countryName").autocomplete({
			source: function( request, response ) {
			$.ajax({
				url: "refcountry?navigation=lookupjson",
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
		        //include the countryId for filtering
		        //alert("CountryName : " + ui.item.label + "\nCountryID : " + ui.item.id + "\nCountryCode : " + ui.item.number);
		        $("#countryId").val(ui.item.id);
		        $("#countryName").val(ui.item.label);
		        temp=false;
		        //alert(value.id);
			}
		})
		.autocomplete( "instance" )._renderItem = function( ul, item ) {
	      return $( "<a>" )
	        .append( item.label )
	        .appendTo( ul );
	    };
	    
	    $("#provinceName").autocomplete({
			source: function( request, response ) {
			$.ajax({
				url: "refprovince?navigation=lookupjson",
				dataType: "json",
				data: {
					q: request.term,
					countryId : $("#countryId").val()
				},
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
		        //alert("ProvinceName : " + ui.item.label + "\nProvinceID : " + ui.item.id + "\nProvinceCode : " + ui.item.number);
		        $("#provinceId").val(ui.item.id);
		        $("#provinceName").val (ui.item.label);
			}
		})
		.autocomplete( "instance" )._renderItem = function( ul, item ) {
	      return $( "<a>" )
	        .append( item.label )
	        .appendTo( ul );
	    };
	    
	    $("#cityName").autocomplete({
			source: function( request, response ) {
			$.ajax({
				url: "refcity?navigation=lookupjson",
				dataType: "json",
				data: {
					q: request.term,
					provinceId : $("#provinceId").val()
				},
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
		      	//alert("CityName : " + ui.item.label + "\nCityID : " + ui.item.id + "\nCityCode : " + ui.item.number);
		        $("#cityId").val(ui.item.id);
		        $("#cityCode").val(ui.item.number);
		         $("#cityName").val(ui.item.label);
		      	var a=$("#cityCode").val();
		        var b=a.substring(1);
		     //   alert("test"+b);
		        temp=true;
			}
		})
		.autocomplete( "instance" )._renderItem = function( ul, item ) {
	      return $( "<a>" )
	        .append( item.label )
	        .appendTo( ul );
	    };
	    
	    $("#search")
	    
	    
	    
});
<%
String nav="";
if(request.getAttribute("navigation").equals("gosearchReference")||request.getAttribute("navigation").equals("golookupReference")){
	nav = (String)request.getAttribute("navigation");
}
 

%>


function goleft(){
	document.form1.navigation.value = "gosearchReference";
	document.form1.arah.value="kiri";
	document.form1.method = "POST";
	document.form1.submit();
}
function goleftbgt(){
	document.form1.navigation.value = "gosearchReference";
	document.form1.arah.value="kiribgt";
	document.form1.method = "POST";
	document.form1.submit();
}
function goright(){
	document.form1.navigation.value = "gosearchReference";
	document.form1.arah.value="kanan";
	document.form1.method = "POST";
	document.form1.submit();
}
function gorightbgt(){
	document.form1.navigation.value = "gosearchReference";
	document.form1.arah.value="kananbgt";
	document.form1.method = "POST";
	document.form1.submit();
}
function go(){
	document.form1.navigation.value = "gosearchReference";
	document.form1.submit();
}
function cari (){
 	var clintNameValid = document.getElementById("clientName").value;
	var serviceNameValid = document.getElementById("serviceName").value;
		
 	if(clintNameValid == ""){
		alert('Client Name is Required');
	}else if(serviceNameValid == ""){
		alert('Service Name is Required');
	}else{
		document.form1.navigation.value = "gosearchReference";
		document.form1.action = "case";
		document.form1.method = "POST";
		document.form1.submit();
	}
}
function downloadExcel (){
	document.form1.navigation.value = "downloadExcelReference";
	document.form1.action = "case";
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
function carisort(param){
	document.form1.navigation.value = "gosearchbysortReference";
	document.form1.sortcolumn.value= param;
	document.form1.method = "POST";
	document.form1.submit();
}
//     setTimeout(function () { 
//       location.reload();
//     }, 60 * 1000);

// if(document.form1.refreshTimerSet.value > 0){
// 	setTimeout(function(){
// 	     window.location.href = window.location.href;
// 	    document.form1.navigation.value = "<%=nav%>";
// 		document.form1.submit();
// 	},document.form1.refreshTimerSet.value*60*1000);
// }
// function startTimer() {
// 	if(document.form1.refreshTimerSet.value > 0){
// 		document.querySelector('#timeDiv').style.fontSize = "14px";
// 		document.getElementById('timeDiv').style.visibility = 'visible';
// 	    var timer = document.form1.refreshTimerSet.value*60, minutes, seconds;
// 	    setInterval(function () {
// 	        minutes = parseInt(timer / 60, 10);
// 	        seconds = parseInt(timer % 60, 10);
	
// 	        minutes = minutes < 10 ? "0" + minutes : minutes;
// 	        seconds = seconds < 10 ? "0" + seconds : seconds;
	
// 	        document.querySelector('#time').textContent = minutes + ":" + seconds;
	        
	
// 	        if (--timer < 0) {
// 	            timer = document.form1.refreshTimerSet.value;
// 	            document.form1.navigation.value = "<%=nav%>";
// 				document.form1.submit();
// 	        }


// 	    }, 1000);
//     }else{
//     	document.getElementById('timeDiv').style.visibility = 'hidden';
//     }
// }




// if(document.form1.refreshTimerSet.value > 0){
// 	window.onload = function () {
// 	    var fiveMinutes = 60 * document.form1.refreshTimerSet.value;
// 	    var display = document.querySelector('#time');
// 	    startTimer(fiveMinutes, display);
// 	};
// }


</script>
	