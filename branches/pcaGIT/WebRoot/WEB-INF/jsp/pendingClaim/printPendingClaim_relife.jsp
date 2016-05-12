
<%@page import="com.ametis.cms.datamodel.ExcessCharge"%>
<%@page import="com.ametis.cms.util.Converter"%><%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%
	
	String tanggalExcess = 	Converter.getDateddMMMMYYYY(new java.sql.Date(System.currentTimeMillis()));;
	

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

<br />

<c:set var="insurancePeriod" value="" scope="request" />
<!-- Search Container Start -->
<table class="tabDetailView" border="0" cellpadding="0" cellspacing="0" width="100%" >
  <tbody>
    
		<tr>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;"><img src="images/relife.gif" width="120" height="80"> &nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;"></td>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;">&nbsp;&nbsp;</td>	
	    </tr>		
	    <tr>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;"> &nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 17px;"></td>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </td>	
	    </tr>
	    <tr>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;"> &nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 17px;"></td>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </td>	
	    </tr>
		<tr>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;">KEPADA: &nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 17px;"></td>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;">Jakarta, <%=tanggalExcess%></td>	
	    </tr>		
		<tr>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;">YTH. <c:out value="${policyHolder.firstName}" /> &nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;"></td>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;">No: <c:out value="${rejectedClaim.rejectionNumber}" /></td>	
	    </tr>		
		<tr>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;"><c:out value="${policyHolder.memberGroupId.groupName}" /></td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;"></td>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;">&nbsp;&nbsp;</td>	
	    </tr>		
		<tr>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;"><c:out value="${policyHolder.memberGroupId.address}" /></td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px; solid #000; font-size: 12px;"></td>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;">&nbsp;&nbsp;</td>	
	    </tr>					
		
	</tbody>
</table>

<form name="form1" action="batchclaimreport" method="POST">
<input type="hidden" name="navigation" value="report">
<input type="hidden" name="arah" value="">
<input type="hidden" name="index" value="<c:out value="${index}" />">
<input type="hidden" name="batchClaimId" value="<c:out value="${batchClaim.batchClaimId}" />">

<c:set var="claimChargeValue" value="0" scope="request" />
<c:set var="claimApprovedValue" value="0"  scope="request" />
<c:set var="claimExcessValue" value="0" scope="request" />

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


 	
<table class="listView" cellspacing="0" cellpadding="0" width="100%" border="0" style="border: 0px;">
	<tbody>

		<tr>
			<td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px; color: #000; solid #000; font-size: 12px;">
			Dengan Hormat,</td>
			
		</tr>		
		<tr>
			<td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 17px;">
			</td>
			
		</tr>		
		<tr>
			<td class="tabDetailViewDF" align="justify" valign="top" width="15%" style="border: 0px; color: #000; solid #000; font-size: 12px;">
			Surat ini dibuat berdasarkan polis Asuransi Kesehatan Perusahaan Anda untuk biaya pengobatan bagi karyawan
			perusahaan dengan informasi seperti berikut:.
			
			</td>
			
		</tr>		
	</tbody>
</table>		

<table class="tabDetailView" border="0" cellpadding="0" cellspacing="0" width="100%" >
  <tbody>
    
	<tr>
		<td>&nbsp;&nbsp;</td>
		
		<td><font color="#000">Nama Pasien: </font></td>
		<td>&nbsp;</td>
		<td><font color="#000"><c:out value="${member.firstName}" /></font></td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td><font color="#000">Nomor Peserta: </font></td>
		<td>&nbsp;</td>
		<td><font color="#000"><c:out value="${member.customerPolicyNumber}" /></font></td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	</tr>		
	
		<tr>
		<td>&nbsp;&nbsp;</td>
		
		<td align="left"><font color="#000">Nomor Klaim: </font></td>
		<td>&nbsp;</td>
		<td align="left"><font color="#000"><c:out value="${claim.claimNumber}" /></font></td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td><font color="#000">Tanggal Rawat: </font></td>
		<td>&nbsp;</td>
		<td><font color="#000"><c:out value="${claim.admissionDate}" /></font></td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
		<tr>
		<td>&nbsp;&nbsp;</td>
		
		<td align="left"><font color="#000">Klinik/Rumah Sakit: </font></td>
		<td>&nbsp;</td>
		<td align="left"><font color="#000"><c:out value="${claim.providerName}" /></font></td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td><font color="#000">Tanggal Klaim: </font></td>
		<td>&nbsp;</td>
		<td><font color="#000"><c:out value="${claim.claimDate}" /></font></td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	</tr>				
		<tr>
		<td>&nbsp;&nbsp;</td>
		
		<td align="left"><font color="#000">Diagnosis Name: </font></td>
		<td>&nbsp;</td>
		<td align="left"><font color="#000"><c:out value="${claim.diagnosisId.description}" /></font></td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	<td align="left"><font color="#000"> </font></td>
		<td>&nbsp;</td>
		<td align="left"><font color="#000"></font></td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	</tr>		
	<tr>
		<td>&nbsp;&nbsp;</td>
		
		<td align="left"></td>
		<td>&nbsp;</td>
		<td align="left"></td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
</tbody>
</table>

<table class="tabDetailView" border="0" cellpadding="0" cellspacing="0" width="100%" >
  <tbody>
    <tr>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	</tr>	
	<tr>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	</tr>	
</tbody>
</table>
<table class="listView" cellspacing="0" cellpadding="0" width="100%" border="0" style="border: 0px;">
	<tbody>
		<tr>
			<td class="tabDetailViewDF" align="justify" valign="top" width="15%" style="border: 0px; color: #000; solid #000; font-size: 12px;">
			Terima kasih atas kepercayaan Anda Kepada kami sebagai peserta Asuransi Kesehatan xxx yang telah memberikan kepercayaan
			kepada Kami untuk mengelola klaim Anda. 
			Dengan ini diinformasikan bahwa untuk proses selanjutnya kami membutuhkan keterangan tambahan seperti tercantum di bawah ini:
			<br />
			<br />
			<b>
			<c:out value="${pendingClaim.remarks}" /></b>
			<br />
			</td>
			
		</tr>	
			<tr>
			<td class="tabDetailViewDF" align="justify" valign="top" width="15%" style="border: 0px solid #000; font-size: 17px;">
			&nbsp;</td>
			
		</tr>
		
		<tr>
			<td class="tabDetailViewDF" align="justify" valign="top" width="15%" style="border: 0px solid #000; color: #000; font-size: 12px;">
			Mohon kiranya kelengkapan klaim tersebut dapat dikirimkan secepatnya.
			</td>
			
		</tr>
		<tr>
			<td class="tabDetailViewDF" align="justify" valign="top" width="15%" style="border: 0px solid #000; color: #000; font-size: 12px;">
			Demikian informasi yang dapat kami sampaikan. Atas perhatian dan kerjasamanya, kami ucapkan terima kasih</td>
			
		</tr>		
	<tr>
			<td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 13px;">
			</td>
			
		</tr>
	</tbody>
</table>
<table class="tabDetailView" border="0" cellpadding="0" cellspacing="0" width="100%" >
  <tbody>
    
	<tr>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	</tr>		
	
	<tr>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	</tr>		
		
	
	<tr>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	
	<tr>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	</tr>	
	
	<tr>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	</tr>	
	
	<tr>
		<td></td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	</tr>		
	<tr>
		<td></td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
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
		<td align="left" width="65%"></td>
		
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td align="left" style="font-size: 13px; text-align: left;"><font color="#000"> dra. Iswara Maya, Apt </font></td>
		
	</tr>		
	<tr>
		<td align="left"><br><br></td>
		
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		
		<td>&nbsp;</td>
		<td  align="left" style="font-size: 13px;"><font color="#000"> CFO Healthcare Group </font></td>
	</tr>		
	
	
			
		
	
			
	
				
	</tbody>
</table>

	
</form>
	
<!-- Table Content Stop -->
	<!-- Table Toolbar Start -->
	
		<!-- Table Toolbar Stop -->



<!-- Table Container Stop -->
