<%@page import="com.ametis.cms.datamodel.ExcessCharge"%>
<%@page import="com.ametis.cms.util.Converter"%>
<%@page import="com.ametis.cms.datamodel.Case"%><%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%
	Case caseKu = (Case) request.getAttribute("myCase") == null ? new Case() : (Case) request.getAttribute("myCase");
	String tanggalMasuk = 	Converter.getDateddMMMMYYYY(caseKu.getCaseStartTime());
	

 %>
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
		<title>Aplikanusa Lintasarta - Healthcare Management System</title>


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


if (alert != null && !alert.trim().equals("")) {%>
	<div id="warning" align="center">
		<c:out value="${alert}"></c:out>
	</div>
	<%}%>

<%
String rowclass = "";
int i=0;

%>

<c:set var="insurancePeriod" value="" scope="request" />
<!-- Search Container Start -->
<center>
<table class="tabDetailView" border="0" cellpadding="0" cellspacing="0" width="90%" >
  <tbody>    
		<tr>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;">
	      <img src="images/logo/<c:out value="${member.clientId.clientLogo}" />" width="160" height="80"> &nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;"></td>
	      <td class="tabDetailViewDF" align="right" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;">
	      <img src="images/logo/lintasarta.png" width="220" height="80"> &nbsp;&nbsp;</td>	
	    </tr>		
	    
				
		<tr>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;"></td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px; solid #000; font-size: 12px;"></td>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;">
	       &nbsp;
	      &nbsp;&nbsp;</td>	
	    </tr>					
		<tr>
	      <td class="tabDetailViewDF" valign="top" colspan="3" width="15%" style="border: 0px solid #000; font-weight:bolder; font-size: 12px;" align="center"></td>		  
	    </tr>		
		<tr>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;"></td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px; solid #000; font-size: 12px;"></td>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;">
	       &nbsp;
	      &nbsp;&nbsp;</td>	
	    </tr>			
	</tbody>
</table>
</center>
<form name="form1" action="batchclaimreport" method="POST">
<input type="hidden" name="navigation" value="report">
<input type="hidden" name="arah" value="">
<input type="hidden" name="index" value="<c:out value="${index}" />">
<input type="hidden" name="batchClaimId" value="<c:out value="${batchClaim.batchClaimId}" />">

<c:set var="claimChargeValue" value="0" scope="request" />
<c:set var="claimApprovedValue" value="0"  scope="request" />
<c:set var="claimExcessValue" value="0" scope="request" />
	

<center>
<table class="tabDetailView" border="0" cellpadding="0" cellspacing="0" width="90%" >
  <tbody>
    <tr style="border: 1px solid; border-color: #000; color: #000; solid #000; ">
		<td class="tabDetailViewDF" align=center  style="border-bottom: 1px; color: #000; solid #000; font-size: 17px; font-weight: bold;" colspan=12>SURAT PENOLAKAN RAWAT INAP</td>
		
	</tr>	
	<tr style="border: 1px solid; border-color: #000; color: #000; solid #000; ">
		<td class="tabDetailViewDF" align=center  style="border-bottom: 1px; color: #000; solid #000; font-size: 11px; font-weight: bold;" colspan=12>Nomor : <c:out value="${rejectedCase.rejectionNumber}" /></td>
		
	</tr>	
	<tr>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;&nbsp;</td>
		
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;"><font color="#000">Nama Provider: </font></td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;"><font color="#000"><c:out value="${rejectedCase.caseId.providerId.providerName}" /></font></td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;"><font color="#000">Pengirim: </font></td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;"><font color="#000"><c:out value="${rejectedCase.caseId.caseHandler}" /></font></td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
	</tr>		
	
		<tr>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;&nbsp;</td>
		
		<td align="left" class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;"><font color="#000">Kepada : </font></td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td align="left" class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;"><font color="#000"><c:out value="${rejectedCase.caseId.providerId.contactPerson}" /></font></td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;"><font color="#000">Tanggal : </font></td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;"><font color="#000"><c:out value="${rejectedCase.caseId.caseStartTime}" /></font></td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
	</tr>
		<tr>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;&nbsp;</td>
		
		<td class="tabDetailViewDF"  align="left" style="border: 0px; color: #000; solid #000; font-size: 12px;"><font color="#000">Fax No.: </font></td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF"  align="left" style="border: 0px; color: #000; solid #000; font-size: 12px;"><font color="#000"><c:out value="${rejectedCase.caseId.providerId.faximile}" /></font></td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;"><font color="#000"> Hal : </font></td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;"><font color="#000"> </font></td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
	</tr>				
			
	<tr>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;&nbsp;</td>		
		<td class="tabDetailViewDF"  align="left" style="border: 0px; color: #000; solid #000; font-size: 12px;"></td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF"  align="left" style="border: 0px; color: #000; solid #000; font-size: 12px;"></td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>		
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>		
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>		
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>		
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>		
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
	</tr>
	<tr>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;&nbsp;</td>		
		<td class="tabDetailViewDF"  align="left" style="border: 0px; color: #000; solid #000; font-size: 12px;"></td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF"  align="left" style="border: 0px; color: #000; solid #000; font-size: 12px;"></td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>		
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>		
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>		
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>		
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>		
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
	</tr>
</tbody>
</table>
</center>
<center>
<table class="tabDetailView"  border="0" cellpadding="0" cellspacing="0" width="90%" >
  <tbody>
    
    <tr style="border: 1px solid; border-color: #000; color: #000; solid #000; ">
		<td class="tabDetailViewDF" align=center  style="border-bottom: 1px; color: #000; solid #000; font-size: 15px; font-weight: bold;" colspan=12>INFORMASI PESERTA</td>
		
	</tr>	
	
	<tr style="border-left: 1px solid;border-right: 1px solid; border-color: #000; color: #000; solid #000; ">
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;&nbsp;</td>
		
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;"><font color="#000">Nama Peserta: </font></td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;"><font color="#000"><c:out value="${member.firstName}" /></font></td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;"><font color="#000">No Polis: </font></td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;"><font color="#000"><c:out value="${member.memberGroupId.policyNumber}" /></font></td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
	</tr>		
	
		<tr style="border-left: 1px solid;border-right: 1px solid; border-color: #000; color: #000; solid #000; ">
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;&nbsp;</td>
		
		<td align="left" class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;"><font color="#000">Nama Karyawan: </font></td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td align="left" class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;"><font color="#000"><c:out value="${member.parentMember.firstName}" /></font></td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;"><font color="#000">Perusahaan: </font></td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;"><font color="#000"><c:out value="${member.memberGroupId.groupName}" /></font></td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
	</tr>
	<tr style="border-left: 1px solid;border-right: 1px solid; border-color: #000; color: #000; solid #000; ">
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;&nbsp;</td>
		
		<td class="tabDetailViewDF"  align="left" style="border: 0px; color: #000; solid #000; font-size: 12px;"><font color="#000">Tanggal Lahir: </font></td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF"  align="left" style="border: 0px; color: #000; solid #000; font-size: 12px;"><font color="#000"><c:out value="${member.birthday}" /></font></td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;"><font color="#000">No. Peserta: </font></td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;"><font color="#000"><c:out value="${member.customerPolicyNumber}" /></font></td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
	</tr>				
	<tr style="border-left: 1px solid;border-right: 1px solid; border-color: #000; color: #000; solid #000; ">
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;&nbsp;</td>
		
		<td class="tabDetailViewDF"  align="left" style="border: 0px; color: #000; solid #000; font-size: 12px;"><font color="#000">Jenis Kelamin: </font></td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF"  align="left" style="border: 0px; color: #000; solid #000; font-size: 12px;"><font color="#000"><c:out value="${member.gender}" /></font></td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;"><font color="#000">Periode Polis: </font></td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;"><font color="#000"><c:out value="${member.effectiveDate}" /> s/d <c:out value="${member.expireDate}" /></font></td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
	</tr>			
	<tr style="border-left: 1px solid;border-right: 1px solid; border-bottom: 1px solid; border-color: #000; color: #000; solid #000; ">
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;&nbsp;</td>		
		<td class="tabDetailViewDF"  align="left" style="border: 0px; color: #000; solid #000; font-size: 12px;"></td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF"  align="left" style="border: 0px; color: #000; solid #000; font-size: 12px;"></td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>		
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>		
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>		
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>		
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>		
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
	</tr>
</tbody>
</table>
</center>
<br />
<center>
<table class="tabDetailView"  border="0" cellpadding="0" cellspacing="0" width="90%" >
  <tbody>
    
    <tr style="border: 1px solid; border-color: #000; color: #000; solid #000; ">
		<td class="tabDetailViewDF" align=center  style="border-bottom: 1px; color: #000; solid #000; font-size: 15px; font-weight: bold;" colspan=12>INFORMASI PERAWATAN</td>
		
	</tr>	

	<tr style="border-left: 1px solid;border-right: 1px solid; border-color: #000; color: #000; solid #000; ">
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;&nbsp;</td>
		
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;"><font color="#000">Tanggal Rawat: </font></td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;"><font color="#000"><c:out value="${rejectedCase.caseId.caseStartTime}" /></font></td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;"><font color="#000">Manfaat: </font></td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;"><font color="#000"><c:out value="${rejectedCase.caseId.caseCategoryId.caseCategoryCode}" /></font></td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
	</tr>		
	
		<tr style="border-left: 1px solid;border-right: 1px solid; border-color: #000; color: #000; solid #000; ">
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;&nbsp;</td>
		
		<td align="left" class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;"><font color="#000">Nama Dokter : </font></td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td align="left" class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;"><font color="#000"><c:out value="${rejectedCase.caseId.physician}" /></font></td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;"><font color="#000">Plan: </font></td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;"><font color="#000"><c:out value="${benefitPlan}" /></font></td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
	</tr>
	<tr style="border-left: 1px solid;border-right: 1px solid; border-color: #000; color: #000; solid #000; ">
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;&nbsp;</td>
		
		<td class="tabDetailViewDF"  align="left" style="border: 0px; color: #000; solid #000; font-size: 12px;"><font color="#000">Diagnosa : </font></td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF"  align="left" style="border: 0px; color: #000; solid #000; font-size: 12px;"><font color="#000"><c:out value="${rejectedCase.caseId.diagnosis1Id.diagnosisName}" /></font></td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;"><font color="#000">R & B : </font></td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;"><font color="#000"><c:out value="${rejectedCase.caseId.roomAndBoard}" /></font></td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
	</tr>				
			
	<tr style="border-left: 1px solid;border-right: 1px solid; border-bottom: 1px solid; border-color: #000; color: #000; solid #000; ">
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;&nbsp;</td>		
		<td class="tabDetailViewDF"  align="left" style="border: 0px; color: #000; solid #000; font-size: 12px;"></td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF"  align="left" style="border: 0px; color: #000; solid #000; font-size: 12px;"></td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>		
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>		
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>		
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>		
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>		
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
	</tr>
</tbody>
</table>
</center>

<br />
<center>
<table class="tabDetailView"  border="0" cellpadding="0" cellspacing="0" width="90%" >
  <tbody>
    
    <tr style="border: 1px solid; border-color: #000; color: #000; solid #000; ">
		<td class="tabDetailViewDF" align=center  style="border-bottom: 1px; color: #000; solid #000; font-size: 15px; font-weight: bold;" colspan=12>KETERANGAN</td>
		
	</tr>	

	<tr style="border-left: 1px solid;border-right: 1px solid; border-color: #000; color: #000; solid #000; ">
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;&nbsp;</td>
		
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;" colspan=11>1. Perawatan tidak dapat dijaminkan karena <c:out value="${rejectedCase.rejectionDescription}" /></td>
		
		
	</tr>		
	
	<tr style="border-left: 1px solid;border-right: 1px solid; border-color: #000; color: #000; solid #000; ">
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;&nbsp;</td>
		
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;" colspan=11>2. Pasien berlaku sebagai Pasien umum.</td>
		
		
	</tr>		
	
	<tr style="border-left: 1px solid;border-right: 1px solid; border-bottom: 1px solid; border-color: #000; color: #000; solid #000; ">
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;&nbsp;</td>		
		<td class="tabDetailViewDF"  align="left" style="border: 0px; color: #000; solid #000; font-size: 12px;"></td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF"  align="left" style="border: 0px; color: #000; solid #000; font-size: 12px;"></td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>		
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>		
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>		
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>		
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>		
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
	</tr>
</tbody>
</table>
</center>
<br />
<center>
<table class="tabDetailView"  border="0" cellpadding="0" cellspacing="0" width="90%" >
  <tbody>
    
 
	<tr >
		
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;" colspan=12>
		Demikian kami sampaikan, atas perhatiannya kami ucapkan terima kasih</td>
		
		
	</tr>		

	
	<tr >
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;&nbsp;</td>		
		<td class="tabDetailViewDF"  align="left" style="border: 0px; color: #000; solid #000; font-size: 12px;"></td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF"  align="left" style="border: 0px; color: #000; solid #000; font-size: 12px;"></td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>		
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>		
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>		
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>		
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>		
		<td class="tabDetailViewDF" style="border: 0px; color: #000; solid #000; font-size: 12px;">&nbsp;</td>
	</tr>
</tbody>
</table>
</center>
<table class="tabDetailView" border="0" cellpadding="0" cellspacing="0" width="100%" >
  <tbody>
  
	<tr>
		<td width="5%">&nbsp;</td>
		<td align="left" style="font-size: 13px; text-align: left;"><font color="#000"> Hormat Kami,</font></td>
		<td align="left" width="25%"></td>
		
		
		<td>&nbsp;</td>
		
		<td>&nbsp;</td>
		<td align="left" style="font-size: 13px; text-align: left;"><font color="#000">Diperiksa dan Disetujui Oleh, </font></td>
		
	</tr>		
   	<tr>
		<td></td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td style="border-bottom: 0px solid; border-color: #000;">&nbsp;</td>
	</tr>		
	<tr>
		<td>&nbsp;</td>
		<td><img src="images/provider_relation.jpg"></td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td><img src="images/medical_doctor.jpg" > </td>
	</tr>		
	
	


	<tr>
		<td width="5%">&nbsp;</td>
		<td align="left" style="font-size: 13px; text-align: left;"><font color="#000" style="text-decoration: underline; font-weight: bold;"> Prio Ananda  </font></td>
		<td align="left" width="25%"></td>
		
		
		<td>&nbsp;</td>
		
		<td>&nbsp;</td>
		<td align="left" style="font-size: 13px; text-align: left;"><font color="#000" style="text-decoration: underline; font-weight: bold;"> dr. Bagus Satria </font></td>
		
	</tr>		
	<tr>
		<td align="left"><br><br></td>
		
		<td><font color="#000" style="font-size: 13px;"> Provider Relation  </font></td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		
		<td>&nbsp;</td>
		<td  align="left" style="font-size: 13px;"><font color="#000"> Medical Doctor </font></td>
	</tr>		
	
	
	</tbody>
</table>


</form>
	
<!-- Table Content Stop -->
	<!-- Table Toolbar Start -->
	
		<!-- Table Toolbar Stop -->



<!-- Table Container Stop -->
