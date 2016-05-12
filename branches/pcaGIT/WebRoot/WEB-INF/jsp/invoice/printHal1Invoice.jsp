
<%@page import="com.ametis.cms.datamodel.ExcessCharge"%>
<%@page import="com.ametis.cms.util.Converter"%>
<%@page import="com.ametis.cms.datamodel.Invoice"%><%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%
	Invoice invoice = (Invoice) request.getAttribute("invoice") ;
	
	String tanggalExcess = "";
	
	if (invoice != null){
		System.out.println("EXCESS TIDAK NULL");
		tanggalExcess = Converter.getDateddMMMMYYYY(new java.sql.Date(System.currentTimeMillis()));
	}
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
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;"><img src="images/relife.gif" width="100" height="80"> &nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;"></td>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;">&nbsp;&nbsp;</td>	
	    </tr>		
	    <tr>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;"> &nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 17px;"></td>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px; color: #000;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </td>	
	    </tr>
	    <tr>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;"> &nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 17px;"></td>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px; color: #000;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </td>	
	    </tr>
		<tr>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px; color: #000;">Kepada Yth, &nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 17px;"></td>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px; color: #000;">Jakarta, <%=tanggalExcess%></td>	
	    </tr>		
		<tr>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px; color: #000;"><b><c:out value="${invoice.clientId.clientName}" /></b> &nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;"></td>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px; color: #000;">No: <c:out value="${invoice.invoiceNumber}" /></td>	
	    </tr>		
		<tr>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px; color: #000;"><c:out value="${invoice.clientId.address}" /></td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;"></td>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;">&nbsp;&nbsp;</td>	
	    </tr>		
		<tr>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;"></td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px; solid #000; font-size: 12px;"></td>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;">&nbsp;&nbsp;</td>	
	    </tr>					
	    <tr>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px; color: #000;">UP:  <c:out value="${invoice.memberGroupId.contactPerson}" /> &nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 17px;"></td>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;"></td>	
	    </tr>		
		<tr>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;">&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;"></td>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;"></td>	
	    </tr>		
		<tr>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px; color: #000;" colspan="2">Hal : Tagihan Layanan</td>
		  
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;">&nbsp;&nbsp;</td>	
	    </tr>		
		<tr>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px; color: #000;" colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${invoice.memberGroupId.groupName}" /> - <c:out value="${invoice.memberGroupId.policyNumber}" /></td>
		  
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
			Bersama ini kami informasikan bahwa dengan adanya peserta asuransi dari <c:out value="${invoice.memberGroupId.groupName}" /> yang 
			menggunakan manfaat kesehatan di Rumah Sakit jaringan PT. Asuransi Jiwa Recapital, telah timbul kelebihan klaim (Excess of Claim)
			yang harus dibayarkan oleh <c:out value="${invoice.memberGroupId.groupName}" /> kepada PT. Asuransi Jiwa Recapital.
			<br>
			<br>
			Total tagihan Excess of Claim tersebut adalah Rp. <fmt:formatNumber><c:out value="${invoice.invoiceValue}" /></fmt:formatNumber> (<c:out value="${totalValueString}" />) 
			<br />
			<br />
			Adapun perinciannya dapat dilihat pada lampiran.
			
			Berkenaan dengan itu mohon agar <c:out value="${invoice.memberGroupId.groupName}" />  dapat segera melakukan pembayaran 
			Excess of Claim sesuai dengan ketentuan pembayaran yang tercantum dalam tagihan tersebut.
			
			<br/>
			<br />
			Demikian informasi ini kami sampaikan. Atas perhatian dan kerjasamanya, kami mengucapkan terima kasih.
			
			
			
			</td>
			
		</tr>		


	<tr>
			<td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;">
			</td>
			
		</tr>	
		<tr>
			<td class="tabDetailViewDF" align="justify" valign="top" width="15%" style="border: 0px; color: #000; solid #000; font-size: 12px;">
			Bersama surat ini, mohon dibayarkan tagihan sesuai dengan daftar terlampir ke PT. APLIKANUSA LINTASARTA qq OWLEXA HEALTHCARE</td>
			
			
		</tr>	
		<tr>
			<td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 17px;">&nbsp;
			</td>
			
			
		</tr>	
			<tr>
			<td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 17px;">
			</td>
			
			
		</tr>	
		
		
		<tr>
			<td class="tabDetailViewDF" align="justify" valign="top" width="15%" style="border: 0px; color: #000; solid #000; font-size: 17px;">
			&nbsp;</td>
			
		</tr>	
	</tbody>
</table>		
<table class="tabDetailView" border="0" cellpadding="0" cellspacing="0" width="100%" >
  <tbody>
    
	<tr>
		<td>&nbsp;&nbsp;</td>
		
		<td><font color="#000">Hormat Kami, </font></td>
		<td>&nbsp;</td>
		
	</tr>		
	
	<tr>
		<td>&nbsp;&nbsp;</td>
		
		<td><font color="#000"></font></td>
		<td>&nbsp;</td>
		
	</tr>		
	

	<tr>
		<td>&nbsp;&nbsp;</td>
		
		<td><img src="images/relife-fin-head.jpg" ></td>
		<td>&nbsp;</td>
		
	</tr>	
	<tr>
		<td>&nbsp;&nbsp;</td>
		<td><font color="#000">................. </font></td>
		<td>&nbsp;</td>
	</tr>		
	<tr>
		<td>&nbsp;&nbsp;</td>
		
		<td><font color="#000">Finance & Accounting Head </font></td>
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
	</tbody>
</table>

	
</form>
