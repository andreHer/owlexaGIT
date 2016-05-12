
<%@page import="com.ametis.cms.datamodel.ExcessCharge"%>
<%@page import="com.ametis.cms.util.Converter"%>
<%@page import="com.ametis.cms.datamodel.Fund"%><%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%
	Fund fund = (Fund) request.getAttribute("fund") ;
	
	String tanggalFund = "";
	
	if (fund != null){
		System.out.println("EXCESS TIDAK NULL");
		tanggalFund = Converter.getDateddMMMMYYYY(fund.getFundTime());
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

<c:set var="insurancePeriod" value="" scope="request" />

<table class="tabDetailView" border="0" cellpadding="0" cellspacing="0" width="100%" >
  <tbody>
    
		<tr>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;">
	      <img src="images/logo/bumida.jpg" width="220" height="80"> &nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;"></td>
	      <td class="tabDetailViewDF" align="right" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;">
	      <img src="images/logo/lintasarta.png" width="220" height="80"> &nbsp;&nbsp;</td>	
	    </tr>		
		
	   
	    <tr>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;"> &nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 17px;"></td>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </td>	
	    </tr>
		<tr>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000;  color: #000; font-size: 12px;">KEPADA: &nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 17px;"></td>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000;  color: #000; font-size: 12px;">Jakarta, <%=tanggalFund%></td>	
	    </tr>		
		<tr>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; color: #000;  font-size: 12px;">YTH. <c:out value="${policyHolder.firstName}" /> &nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;"></td>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000;  color: #000; font-size: 12px;">No: <c:out value="${fund.fundCode}" /></td>	
	    </tr>		
		<tr>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000;  color: #000; font-size: 12px;"><c:out value="${fund.clientId.clientName}" /></td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;"></td>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;">&nbsp;&nbsp;</td>	
	    </tr>		
		<tr>
	      <td class="tabDetailViewDF" valign="top" width="20%" style="border: 0px solid #000; color: #000; font-size: 12px;"><c:out value="${fund.clientId.address}" /></td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px; solid #000; font-size: 12px;"></td>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;">&nbsp;&nbsp;</td>	
	    </tr>					
		<tr>
	      <td class="tabDetailViewDF" valign="top" width="20%" style="border: 0px solid #000; color: #000; font-size: 12px;"><c:out value="${fund.clientId.city}" /> </td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px; solid #000; font-size: 12px;"></td>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;">&nbsp;&nbsp;</td>	
	    </tr>	
	</tbody>
</table>
<br />
<br />

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
			Bersama surat ini, kami mengirimkan kuitansi tagihan Floating Fund dengan informasi sebagai berikut:
			
			</td>
			
		</tr>		


		
		
		<tr>
			<td class="tabDetailViewDF" align="justify" valign="top" width="15%" style="border: 0px; color: #000; solid #000; font-size: 17px;">
			&nbsp;</td>
			
		</tr>	
	</tbody>
</table>		

<center>
<table class="tabDetailView" border="1" cellpadding="0" cellspacing="0" width="80%" >
	<thead>
	
		<tr >
			<td style="border-bottom: thin 1px;" align="center" width="5%"><font color="#000"><b>No</b></font></td>			
			<td align="center" width="75%"><font color="#000"><b>Nomor Kuitansi</b></font></td>
			<td align="center"><font color="#000"><b>Jumlah</b></font></td>
			
			
		</tr>	
	</thead>

  <tbody>
	<tr>
		<td align="center">&nbsp;&nbsp;&nbsp;<font color="#000" style="font-size: small; font-weight: bold">1.</font></td>
		<td align="left">&nbsp;&nbsp;<font color="#000" style="font-size: small; font-weight: bold"><c:out value="${fund.fundCode}" /></font></td>
		<td align="right"><font color="#000" style="font-size: small; font-weight: bold">Rp. <fmt:formatNumber><c:out value="${fund.fundValue}" /></fmt:formatNumber></font>&nbsp;&nbsp;</td>
		
	</tr>	
	
	<tr>
		<td>&nbsp;&nbsp;</td>
		<td align="left"></td>
		<td>&nbsp;</td>
	</tr>	
</tbody>
<tfoot>
<tr>
		<td>&nbsp;&nbsp;</td>
		<td align="center"><font color="#000" style="font-style: italic; font-weight: bold"><c:out value="${valueDescription}" /></font></td>
		<td align="right"></td>
		
		
	</tr>
</tfoot>
</table>
</center>
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
			Pembayaran melalui bank harap ditransfer selambat-lambatnya dalam waktu 7 hari dari tanggal tagihan melalui rekening berikut:</td>
			
		</tr>	
			<tr>
			<td class="tabDetailViewDF" align="justify" valign="top" width="15%" style="border: 0px solid #000; font-size: 17px;">
			&nbsp;</td>
			
		</tr>
		<tr>
			<td class="tabDetailViewDF" align="justify" valign="top" width="15%" style="border: 0px solid #000; color: #000; font-weight: bold; font-size: 14px;">
			PT. NAMA TPA</td>
			
		</tr>
		<tr>
			<td class="tabDetailViewDF" align="justify" valign="top" width="15%" style="border: 0px; color: #000; solid #000; font-weight: bold; font-size: 14px;">
			Bank Mandiri - Cabang KCU Jakarta Gedung Bidakara</td>
			
		</tr>	
		<tr>
			<td class="tabDetailViewDF" align="justify" valign="top" width="15%" style="border: 0px solid #000; color: #000; font-weight: bold; font-size: 14px;">
			No. Rek. </td>
			
		</tr>	
		<tr>
			<td class="tabDetailViewDF" align="justify" valign="top" width="15%" style="border: 0px solid #000; color: #000; font-weight: bold; font-size: 17px;">
			&nbsp;</td>
			
		</tr>	
		<tr>
			<td class="tabDetailViewDF" align="justify" valign="top" width="15%" style="border: 0px solid #000; color: #000; font-size: 12px;">
			Mohon kesediaan Bapak/Ibu untuk melakukan konfirmasi pembayaran tersebut kepada kami melalui Fax di nomor 021-7990847 atau Telepon 021-7975328</td>
			
		</tr>
	<tr>
			<td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 13px;">
			</td>
			
		</tr>
		<tr>
			<td class="tabDetailViewDF" align="justify" valign="top" width="15%" style="border: 0px solid #000; color: #000; font-size: 12px;">
			Demikian kami sampaikan atas perhatian serta kerjasamanya kami mengucapkan terima kasih.</td>
			
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
		<td style="font-size: 12px; text-align: left;"><font color="#000">Hormat Kami,</font></td>
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
		<td align="left" style="font-size: 12px; text-align: left;"><font color="#000">Officer </font></td>
		
	</tr>		
	<tr>
		<td align="left"><br><br></td>
		
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		
		<td>&nbsp;</td>
		<td  align="left" style="font-size: 12px;"><font color="#000"> Finance Director </font></td>
	</tr>		
	
	
			
		
	
			
	
				
	</tbody>
</table>

	
</form>
	
<!-- Table Content Stop -->
	<!-- Table Toolbar Start -->
	
		<!-- Table Toolbar Stop -->



<!-- Table Container Stop -->
