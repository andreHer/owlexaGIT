<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<link rel="stylesheet" type="text/css" href="css/autocomplete.css">
<link rel="stylesheet" type="text/css" href="css/button.css">

<link rel="stylesheet" type="text/css" href="css/jquery/autocomplete/jquery.autocomplete.css"/>

<script type="text/javascript" src="scripts/jquery/plugin/jquery.autocomplete.pack.js"></script>
	
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
	if(request.getAttribute ("countSet") != null){
		countSet = ((Integer) request.getAttribute ("viewCountSet")).intValue();
	}
	else{
		countSet = ((Integer) request.getAttribute ("countSet")).intValue();
	}
	
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
<input type="hidden" name="navigation" value="gosearchmycase">
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
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Search Case</h3></td>
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
            		<td class="dataLabel" nowrap="nowrap">Search Keyword: &nbsp;&nbsp;
						<input size="20" name="searchtext" class="dataField" value="<c:out value="${searchtext}"/>" type="text">
              		</td>
					<td class="dataLabel" nowrap="nowrap">				
						Search Category: &nbsp;&nbsp;
						<select name="searchby" class="inputbox">		 
							<option value="caseNumber" <c:if test="${searchby eq 'caseNumber'}">selected="true"</c:if>>
								Case Number  
							</option>
							<option value="cardNumber" <c:if test="${searchby eq 'cardNumber'}">selected="true"</c:if>>
								Card Number  
							</option>
							<option value="memberName" <c:if test="${searchby eq 'memberName'}">selected="true"</c:if>>
								Member Name  
							</option>
															
							<option value="groupName" <c:if test="${searchby eq 'groupName'}">selected="true"</c:if>>
								Member Group
							</option>
							<option value="diagnosisName" <c:if test="${searchby eq 'diagnosisName'}">selected="true"</c:if>>
								Diagnosis
							</option>
							<option value="clientName" <c:if test="${searchby eq 'clientName'}">selected="true"</c:if>>
								Client
							</option>							
						</select>									
					</td>
					<td class="dataLabel" nowrap="nowrap">Status:
              			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                <select name="status">					
							<option value="-1">-- All Status --</option>
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
						</select>
              		</td>
		            <td class="dataLabel">
		                <input title="Search [Alt+Shift+Q]" accesskey="Q" onClick="javascript:cari();" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" name="button" value="Search" type="submit">
                        <input title="" accesskey="Q" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" name="button" value="Download" type="button" onclick="javascript:downloadExcel()">
					</td>
           		</tr>
  				<tr>
            		<td class="dataLabel" nowrap="nowrap">Provider:
              		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input size="40" name="providerText" id="providerText"  value="<c:out value="${providerText}"/>" type="text">
              		</td>
					<td class="dataLabel" nowrap="nowrap">				
						Case Type:	
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<select name="caseCategoryId" class="inputbox">
<!-- 									 	<option value="-1">-- All Case Type --</option> -->
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
					 <td class="dataLabel" nowrap="nowrap">Refresh Setting:
						<select name="refreshTimerSet" class="inputbox">
							<option value="-1">-- No Refresh --</option>
		<!-- 					<option value="1" <c:if test="${refreshTimerSet eq \"1\"}">selected="true"</c:if>> -->
		<!-- 						1 Minute   -->
		<!-- 					</option> -->
							<option value="2" <c:if test="${refreshTimerSet eq \"2\"}">selected="true"</c:if>>
								2 Minutes  
							</option>
							<option value="3" <c:if test="${refreshTimerSet eq \"3\"}">selected="true"</c:if>>
								3 Minutes
							</option>
							<option value="4" <c:if test="${refreshTimerSet eq \"4\"}">selected="true"</c:if>>
								4 Minutes
							</option>
							<option value="5" <c:if test="${refreshTimerSet eq \"5\"}">selected="true"</c:if>>
								5 Minutes
							</option>
						</select>
		              </td>
		              <td class="dataLabel" nowrap="nowrap">View List:
						<select name="viewCountSet" class="inputbox">
							<option value="-1">-- Default (15 Rows) --</option>
							<option value="20" <c:if test="${viewCountSet eq \"20\"}">selected="true"</c:if>>
								20 Rows  
							</option>
							<option value="40" <c:if test="${viewCountSet eq \"40\"}">selected="true"</c:if>>
								40 Rows  
							</option>
							<option value="60" <c:if test="${viewCountSet eq \"60\"}">selected="true"</c:if>>
								60 Rows  
							</option>
						</select>
		              </td>
            	</tr>		
        	</tbody>
      	</table>
      </td>
    </tr>
  </tbody>
</table>
<table>
	<tbody >
		 <tr>
		 	<td width="50px" title="Case Open [IP & MA] &#13Pasien Baru">
	    		<legend class="caseopen"></legend>
	    	</td>
	    	<td>
	    		Case Open [IP & MA] - Pasien Baru &nbsp;
	    	</td>
	    	<td width="50px" title="Case [IP & MA] &#13Perawatan 2-5 Hari">
	    		 <legend class="level1"></legend>
	    	</td>
	    	<td>
	    		Case [IP & MA] - Perawatan 2-5 Hari&nbsp;
	    	</td>
	    	<td width="50px" title="Case [IP & MA] &#13Perawatan 6-8 Hari">
	    		 <legend class="level2"></legend>
	    	</td>
	    	<td>
	    		Case [IP & MA] - Perawatan 6-8 Hari &nbsp;
	    	</td>
	    	<td width="50px" title="Case [IP & MA] &#13Perawatan > 8 Hari">
	    		 <legend class="level3"></legend>
	    	</td>
	    	<td>
	    		Case [IP & MA] - Perawatan > 8 Hari &nbsp;
	    	</td>
	    	<td width="50px" title="Case Bukan [IP & MA]">
	    		 <legend class="other"></legend>
	    	</td>
	    	<td>
	    		Case Bukan [IP & MA]
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
							
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%">Case Number &nbsp;
			<input type="hidden" name="sortByOrderCaseNo" value="<c:out value="${sortCaseNumber?'true':'false'}" />">
			<a href="javascript:carisort('casenumber')" class="listViewThLinkS1">
			<img src="images/arrow_<c:out value="${sortCaseNumber?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
		</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Member Name &nbsp;
			<input type="hidden" name="sortByOrderMember" value="<c:out value="${sortMemberName?'true':'false'}" />">
			<a href="javascript:carisort('membername')" class="listViewThLinkS1">
			<img src="images/arrow_<c:out value="${sortMemberName?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
		</td>
 		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Client &nbsp;
			<input type="hidden" name="sortByOrderClient" value="<c:out value="${sortClient?'true':'false'}" />">
			<a href="javascript:carisort('clientname')" class="listViewThLinkS1">
			<img src="images/arrow_<c:out value="${sortClient?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
		</td>
 		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Group &nbsp;
			<input type="hidden" name="sortByOrderGroup" value="<c:out value="${sortGroup?'true':'false'}" />">
			<a href="javascript:carisort('groupname')" class="listViewThLinkS1">
			<img src="images/arrow_<c:out value="${sortGroup?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
		</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Hospital &nbsp;
			<input type="hidden" name="sortByOrderProvider" value="<c:out value="${sortProvider?'true':'false'}" />">
			<a href="javascript:carisort('providername')" class="listViewThLinkS1">
			<img src="images/arrow_<c:out value="${sortProvider?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
		</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Admission &nbsp;
			<input type="hidden" name="sortByOrderAdmission" value="<c:out value="${sortAdmissionDate?'true':'false'}" />">
			<a href="javascript:carisort('admissiondate')" class="listViewThLinkS1">
			<img src="images/arrow_<c:out value="${sortAdmissionDate?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
		</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Discharge &nbsp;
			<input type="hidden" name="sortByOrderDischarge" value="<c:out value="${sortDischargeDate?'true':'false'}" />">
			<a href="javascript:carisort('dischargedate')" class="listViewThLinkS1">
			<img src="images/arrow_<c:out value="${sortDischargeDate?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
		</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="2%">Type &nbsp;
			<input type="hidden" name="sortByOrderType" value="<c:out value="${sortType?'true':'false'}" />">
			<a href="javascript:carisort('type')" class="listViewThLinkS1">
			<img src="images/arrow_<c:out value="${sortType?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
		</td>		 
		<td scope="col" class="listViewThS1" width="5%">Limit Benefit &nbsp;
			<input type="hidden" name="sortByOrderLimit" value="<c:out value="${sortLimit?'true':'false'}" />">
			<a href="javascript:carisort('limit')" class="listViewThLinkS1">
			<img src="images/arrow_<c:out value="${sortLimit?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
		</td>			    		   			
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="3%">Diagnosis &nbsp;
			<input type="hidden" name="sortByOrderDiagnosis" value="<c:out value="${sortDiagnosis?'true':'false'}" />">
			<a href="javascript:carisort('diagnosis')" class="listViewThLinkS1">
			<img src="images/arrow_<c:out value="${sortDiagnosis?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
		</td>		
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Charge &nbsp;
			<input type="hidden" name="sortByOrderCharge" value="<c:out value="${sortCharge?'true':'false'}" />">
			<a href="javascript:carisort('charge')" class="listViewThLinkS1">
			<img src="images/arrow_<c:out value="${sortCharge?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
		</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Approved &nbsp;
			<input type="hidden" name="sortByOrderApprove" value="<c:out value="${sortAprrove?'true':'false'}" />">
			<a href="javascript:carisort('approve')" class="listViewThLinkS1">
			<img src="images/arrow_<c:out value="${sortAprrove?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
		</td>	
		<td scope="col" class="listViewThS1" width="5%">Excess &nbsp;
			<input type="hidden" name="sortByOrderExcess" value="<c:out value="${sortExcess?'true':'false'}" />">
			<a href="javascript:carisort('excess')" class="listViewThLinkS1">
			<img src="images/arrow_<c:out value="${sortExcess?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
		</td>			   			
		<td scope="col" class="listViewThS1" width="5%">Status &nbsp;
			<input type="hidden" name="sortByOrderStatus" value="<c:out value="${sortStatus?'true':'false'}" />">
			<a href="javascript:carisort('status')" class="listViewThLinkS1">
			<img src="images/arrow_<c:out value="${sortStatus?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
		</td>
		<td scope="col" class="listViewThS1" width="5%">Modified Time &nbsp;
			<input type="hidden" name="sortByOrderModifTime" value="<c:out value="${sortModifiedTime?'true':'false'}" />">
			<a href="javascript:carisort('modifiedtime')" class="listViewThLinkS1">
			<img src="images/arrow_<c:out value="${sortModifiedTime?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
		</td>
	</tr>

	<c:set var="casecolorset"></c:set>
	<c:forEach items="${Cases}" var="myCase" varStatus="status" >
	<%	if (i % 2 == 0) {
			rowclass = "col1";
		} else if (i % 2 != 0) {
			rowclass= "col2";
		}
		i++;
	%>
	<% /* Edit 30032015, Penambahan Legend warna untuk tiap Case */ %>
	 <c:choose>
	 	<c:when test="${myCase.caseCategoryId.caseCategoryCode eq 'IP' or myCase.caseCategoryId.caseCategoryCode eq 'MA' }">
	 		<c:choose>
		 		<c:when test="${(myCase.caseStatusId.caseStatusId ne 4) and (myCase.caseStatusId.caseStatusId ne 15) }">
					<c:set var="startdate" value="${myCase.caseStartTime }"></c:set>
					<fmt:parseNumber value="${startdate.time / (1000*60*60*24) }" integerOnly="true" var="starttime" scope="page"/>
					<c:set var="currentdate" value="<%=new java.util.Date()%>"></c:set>
					<fmt:parseNumber value="${currentdate.time / (1000*60*60*24) }" integerOnly="true" var="currenttime" scope="request"/>
					<c:set var="fontcardcolorset" value="#bdffbd"></c:set>
					<c:set var="interval" value="${(currenttime-starttime) }"></c:set>
					<c:set var="fontcardcolorset" value="#691065"></c:set>
			 		<c:choose>
			 			<c:when test="${(interval eq 1) and (myCase.caseStatusId.caseStatusId eq 1)}">
			 				<c:set var="casecolorset" value="caseopen"></c:set>
			 				<c:set var="fontcolorset" value="#141cfb"></c:set>
			 			</c:when>
			 			<c:when test="${(interval >= 2) and (interval <= 5)}">
			 				<c:set var="casecolorset" value="level1"></c:set>
			 				<c:set var="fontcolorset" value="#0000b2"></c:set>
			 			</c:when>
			 			<c:when test="${(interval > 5) and (interval <= 8)}">
			 				<c:set var="casecolorset" value="level2"></c:set>
			 				<c:set var="fontcolorset" value="#0000b2"></c:set>
			 			</c:when>
			 			<c:when test="${interval > 8}">
			 				<c:set var="casecolorset" value="level3"></c:set>
			 				<c:set var="fontcolorset" value="#FFFFFF"></c:set>
			 			</c:when>
			 			<c:otherwise>
			 				<c:set var="casecolorset" value="other"></c:set>
			 				<c:set var="fontcolorset" value="default"></c:set>
			 				<c:set var="fontcardcolorset" value="default"></c:set>
			 			</c:otherwise>
			 		</c:choose>
			 	</c:when>
			 	<c:otherwise>
			 		<c:set var="casecolorset" value="other"></c:set>
			 		<c:set var="fontcolorset" value="default"></c:set>
			 		<c:set var="fontcardcolorset" value="default"></c:set>
			 	</c:otherwise>
		 	</c:choose>
	 	</c:when>
	 	<c:otherwise>
	 		<c:set var="casecolorset" value="other"></c:set>
	 	</c:otherwise>
	 </c:choose>
	 <tr height="20" style="border-top: 1px solid #000;" class="<c:out value="${casecolorset}" />">
	    <td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><%=(i+((indexint-1)*countSet))%></td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<a href="case?navigation=detail&caseId=<c:out value="${myCase.caseId}" />" class="linkDetail" style="color:<c:out value="${fontcolorset eq 'default'? '' : fontcolorset}" />"><c:out value="${myCase.caseNumber}" /></a>
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top">
			<a class="linkDetail" style="color:<c:out value="${fontcolorset eq 'default'? '' : fontcolorset}" />" href="member?navigation=detail&memberId=<c:out value="${myCase.memberId.memberId}" />"><c:out value="${myCase.memberId.firstName}" /> <c:out value="${myCase.memberId.lastName}" /> 
			<c:if test="${myCase.memberId.isVIP eq 1}">[VIP]</c:if>
			</a>
			<div style="color:<c:out value="${fontcardcolorset eq 'default'? '' : fontcardcolorset}" />">
			(<c:out value="${myCase.memberId.currentCardNumber}" />)
			</div>
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top">
			<c:out value="${myCase.memberId.clientName}" /> 
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top">
			<c:out value="${myCase.memberId.groupName}" />
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top">
			<a class="linkDetail" style="color:<c:out value="${fontcolorset eq 'default'? '' : fontcolorset}" />" href="provider?navigation=detail&providerId=<c:out value="${myCase.providerId.providerId}" />" ><c:out value="${myCase.providerId.providerName}" /></a>
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">	
			<c:out value="${myCase.caseStartTime}" />
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<c:out value="${myCase.caseEndTime}" />
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<c:out value="${myCase.caseCategoryId.caseCategoryCode}" />
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe"  valign="top">						
			<fmt:formatNumber><c:out value="${myCase.preRemainingLimit}" /></fmt:formatNumber>						
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe"  valign="top">						
			<c:out value="${myCase.diagnosis1Id.diagnosisCode}" />						
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
		<td class="oddListRowS1" bgcolor="#e7f0fe"  valign="top" style="font-weight: bold;">
			<c:if test="${myCase.caseStatusId.caseStatusId eq 1}">OPEN</c:if>
			<c:if test="${myCase.caseStatusId.caseStatusId eq 2}">PENDING DOCUMENT</c:if>
			<c:if test="${myCase.caseStatusId.caseStatusId eq 3}">VERIFIED</c:if>
			<c:if test="${myCase.caseStatusId.caseStatusId eq 4}">REJECTED</c:if>
			<c:if test="${myCase.caseStatusId.caseStatusId eq 5}">CLOSED</c:if>
			<c:if test="${myCase.caseStatusId.caseStatusId eq 6}">PAID</c:if>
			<c:if test="${myCase.caseStatusId.caseStatusId eq 7}">PENDING INVESTIGATION</c:if>
			<c:if test="${myCase.caseStatusId.caseStatusId eq 9}">APPROVED</c:if>
			<c:if test="${myCase.caseStatusId.caseStatusId eq 10}">PENDING</c:if>
			<c:if test="${myCase.caseStatusId.caseStatusId eq 12}">COMPLETE</c:if>
			<c:if test="${myCase.caseStatusId.caseStatusId eq 15}">FINALIZED</c:if>
			<c:if test="${myCase.caseStatusId.caseStatusId eq 17}">REFERED</c:if>						
			<c:if test="${myCase.caseStatusId.caseStatusId eq 18}">GREY AREA</c:if>
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe"  valign="top">						
			<c:out value="${myCase.modifiedTime}" />						
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
	document.getElementById('timeDiv').style.visibility = 'hidden';
	startTimer();
	//diffInDays('2015-03-24');
	    
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
            return  "<font color='#000' style='align: left;' >"+  row.name +"</font>" ;
        }     
    }).bind("result", function(data, value){
        $(this).parents("dd").find(".error_message").hide();
        $("#providerId").val (value.id);
        $("#providerText").val (value.name);
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
	document.form1.navigation.value = "gosearchmycase";
	document.form1.action = "case";
	document.form1.method = "POST";
	document.form1.submit();
}
function downloadExcel (){
	document.form1.navigation.value = "downloadExcel";
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
	document.form1.navigation.value = "gosearchbysort";
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
function startTimer() {
	if(document.form1.refreshTimerSet.value > 0){
		document.querySelector('#timeDiv').style.fontSize = "14px";
		document.getElementById('timeDiv').style.visibility = 'visible';
	    var timer = document.form1.refreshTimerSet.value*60, minutes, seconds;
	    setInterval(function () {
	        minutes = parseInt(timer / 60, 10);
	        seconds = parseInt(timer % 60, 10);
	
	        minutes = minutes < 10 ? "0" + minutes : minutes;
	        seconds = seconds < 10 ? "0" + seconds : seconds;
	
	        document.querySelector('#time').textContent = minutes + ":" + seconds;
	        
	
	        if (--timer < 0) {
	            timer = document.form1.refreshTimerSet.value;
	            document.form1.navigation.value = "<%=nav%>";
				document.form1.submit();
	        }
	    }, 1000);
    }else{
    	document.getElementById('timeDiv').style.visibility = 'hidden';
    }
}

// if(document.form1.refreshTimerSet.value > 0){
// 	window.onload = function () {
// 	    var fiveMinutes = 60 * document.form1.refreshTimerSet.value;
// 	    var display = document.querySelector('#time');
// 	    startTimer(fiveMinutes, display);
// 	};
// }


</script>
	