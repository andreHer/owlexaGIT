<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<head>


		<script type="text/javascript" src="scripts/sugar_3.js"></script>
		<script type="text/javascript" src="scripts/cookie.js"></script>
		<link rel="stylesheet" type="text/css" media="all" href="css/calendar-win2k-cold-1.css" />
		<link rel="stylesheet" type="text/css" media="all" href="css/style.css" />
	
		
<script src="scripts/prototype.js" type="text/javascript"></script>
  <script src="scripts/scriptaculous.js" type="text/javascript"></script>
  	<link rel="stylesheet" type="text/css" href="css/tabcontent.css" />
<script type="text/javascript" src="scripts/tabcontent.js">
/***********************************************
* Tab Content script- © Dynamic Drive DHTML code library (www.dynamicdrive.com)
* This notice MUST stay intact for legal use
* Visit Dynamic Drive at http://www.dynamicdrive.com/ for full source code
***********************************************/
</script>
<link rel="stylesheet" type="text/css" href="css/reset-fonts-grids.css" />
        <!-- CSS for Menu -->

        <link rel="stylesheet" type="text/css" href="css/menu.css" />
 
 
        <!-- Page-specific styles -->

        <!-- Namespace source file -->
<!-- calendar stylesheet -->
	

		<!-- main calendar program -->
		<script type="text/javascript" src="scripts/calendar.js"></script>

		
        <!-- Menu source file -->
       
		
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Healthcare Management System</title>


		<link href="css/navigation.css" rel="stylesheet" type="text/css" />
		<script language="javascript" src="scripts/menu.js"></script>
		<script language="javascript" src="scripts/cookie_002.js"></script>

	</head>


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
	
	
}
catch (Exception e){
}

if (alert != null && !alert.trim().equals("")) {%>
	<div id="warning" align="center">
		<c:out value="${alert}"></c:out>
	</div>
	<%}%>

<%

%>
<!-- Search Container Start -->

<br />
<form name="form1" action="batchclaim" method="POST">
<input type="hidden" name="navigation" value="gosearch">
<input type="hidden" name="subnavigation" value="list">
<input type="hidden" name="currentnavigation" value="list" >
<input type="hidden" name="providerId" value="<c:out value="${providerId}" />">
<input type="hidden" name="arah" value="">



<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"></td>
      <td width="100%"></td>
    </tr>
  </tbody>
</table>


<br />

 	<table class="listView" style="border-color: 000; border: 1px;" cellspacing="0" cellpadding="0" width="100%">
		<tbody>
			<tr height="20">
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" width="60%" valign="top" colspan=2><center><c:out value="${myCase.providerId.providerName}" /></center></td>				
			</tr>
			<tr height="20">
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" width="60%" valign="top" colspan=2><center><c:out value="${myCase.providerId.address}" /></center></td>				
			</tr>
			<tr height="20">
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" width="60%" valign="top" colspan=2>&nbsp;</td>				
			</tr>
			
			<tr height="20">
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" width="60%" valign="top" colspan=2><b>RUJUKAN</b></td>				
			</tr>
			<tr height="20">
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" width="60%" valign="top" colspan=2><c:out value="${myCase.memberId.currentCardNumber}" /> (*)</td>				
			</tr>
			<tr height="20">
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" width="60%" valign="top" colspan=2><c:out value="${myCase.memberId.customerPolicyNumber}" /></td>				
			</tr>
			<tr height="20">
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" width="60%" valign="top" colspan=2><c:out value="${myCase.memberId.memberGroupId.groupName}" /></td>
				
			</tr>
			<tr height="20">
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" width="60%" valign="top" colspan=2><c:out value="${myCase.memberId.firstName}" /></td>								
			</tr>
			
			<tr height="20">
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" width="60%" valign="top">Expired Date : </td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" align=right width="30%" nowrap="nowrap" valign="top"><c:out value="${myCase.memberId.expireDate}" /></td>				
			</tr>
			<tr height="20">
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" width="60%" valign="top">Date/Time : </td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" align=right width="30%" nowrap="nowrap" valign="top"><c:out value="${myCase.createdTime}" /></td>				
			</tr>
			<tr height="20">
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" width="60%" valign="top">Benefit : </td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" align=right width="30%" nowrap="nowrap" valign="top"><c:out value="${myCase.caseCategoryId.caseCategoryName}" /></td>				
			</tr>
			<tr height="20">
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" width="60%" valign="top">Kode Penyakit</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" align=right width="30%" nowrap="nowrap" valign="top"><c:out value="${myCase.diagnosis1Id.diagnosisCode}" /> <c:out value="${myCase.diagnosis2Id.diagnosisCode}" /> <c:out value="${myCase.diagnosis3Id.diagnosisCode}" />  </td>				
			</tr>   
		</tbody>
		
	</table>
	<br />
	<table class="listView" style="border-color: 000; border: 1px;" cellspacing="0" cellpadding="0" width="100%">
		<tbody>
			
			<tr height="20">			      				
			     <td class="oddListRowS1" bgcolor="#e7f0fe" width="10%" style="border-bottom: 1px dashed #000 ; color: #000; " valign="top" colspan="3"><b>DATA RUJUKAN</b></td>
									
			</tr>   
			
				
			<tr height="20">
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" width="60%" valign="top">No. Rujukan : </td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" align=right width="30%" nowrap="nowrap" valign="top"><b><c:out value="${myCase.caseReferalNumber}" /></b></td>				
			</tr>
			<tr height="20">
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" width="60%" valign="top">Poliklinik : </td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" align=right width="30%" nowrap="nowrap" valign="top"><c:out value="${myCase.poliklinikId.poliklinikName}" /></td>				
			</tr>
			<tr height="20">
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" width="60%" valign="top">Dokter : </td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" align=right width="30%" nowrap="nowrap" valign="top"><c:out value="${myCase.referedDoctorId.doctorName}" /></td>				
			</tr>
			
	
			<tr>
		      	<td colspan="20" class="listViewHRS1">&nbsp;</td>
		 	</tr>
		    <tr>
		      <td colspan="20" class="listViewHRS1">&nbsp;</td>
		    </tr>
			<tr height="20">
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" width="60%" valign="top" colspan=3><center>SEMOGA LEKAS SEMBUH</center></td>
			</tr>
		</tbody>
	</table>
</form>
