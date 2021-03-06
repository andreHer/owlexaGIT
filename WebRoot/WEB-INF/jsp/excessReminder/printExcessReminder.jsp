
<%@page import="com.ametis.cms.datamodel.ExcessCharge"%>
<%@page import="com.ametis.cms.util.Converter"%><%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%
	ExcessCharge excess = (ExcessCharge) request.getAttribute("excessCharge") ;
	
	String tanggalExcess = "";
	
	if (excess != null){
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
* Tab Content script- � Dynamic Drive DHTML code library (www.dynamicdrive.com)
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
	      <img src="images/logo/<c:out value="${member.clientId.clientLogo}" />" width="160" height="80"> &nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;"></td>
	      <td class="tabDetailViewDF" align="right" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;">
	      <img src="images/logo/media.png" width="220" height="80"> &nbsp;&nbsp;</td>	
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
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;">No: <c:out value="${excessCharge.excessChargeNumber}" /></td>	
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
			perusahaan, dimana biaya yang telah dibayar oleh <c:out value="${member.clientId.clientName}" /> melebihi batasan Manfaat atau tidak di Cover.
			
			</td>
			
		</tr>		


	<tr>
			<td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;">
			</td>
			
		</tr>	
		<tr>
			<td class="tabDetailViewDF" align="justify" valign="top" width="15%" style="border: 0px; color: #000; solid #000; font-size: 12px;">
			Bersama surat ini, mohon dibayarkan kembali kelebihan tagihan sesuai dengan daftar nama terlampir ke <c:out value="${member.clientId.clientName}" /></td>
			
			
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
		<td align="left"><font color="#000"><c:out value="${excessCharge.claimId.claimNumber}" /></font></td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td><font color="#000">Tanggal Rawat: </font></td>
		<td>&nbsp;</td>
		<td><font color="#000"><c:out value="${excessCharge.claimId.admissionDate}" /></font></td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
		<tr>
		<td>&nbsp;&nbsp;</td>
		
		<td align="left"><font color="#000">Klinik/Rumah Sakit: </font></td>
		<td>&nbsp;</td>
		<td align="left"><font color="#000"><c:out value="${excessCharge.claimId.providerName}" /></font></td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td><font color="#000">Tanggal Klaim: </font></td>
		<td>&nbsp;</td>
		<td><font color="#000"><c:out value="${excessCharge.claimId.claimDate}" /></font></td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	</tr>				
		<tr>
		<td>&nbsp;&nbsp;</td>
		
		<td align="left"><font color="#000">Diagnosis Name: </font></td>
		<td>&nbsp;</td>
		<td align="left"><font color="#000"><c:out value="${excessCharge.claimId.diagnosisId.description}" /></font></td>
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
			Pembayaran melalui bank harap ditransfer selambat-lambatnya dalam waktu 14 hari dari tanggal tagihan ini.</td>
			
		</tr>	
			<tr>
			<td class="tabDetailViewDF" align="justify" valign="top" width="15%" style="border: 0px solid #000; font-size: 17px;">
			&nbsp;</td>
			
		</tr>
		<tr>
			<td class="tabDetailViewDF" align="justify" valign="top" width="15%" style="border: 0px solid #000; color: #000; font-weight: bold; font-size: 14px;">
			<c:out value="${member.clientId.clientName}" />,</td>
			
		</tr>
		<tr>
			<td class="tabDetailViewDF" align="justify" valign="top" width="15%" style="border: 0px; color: #000; solid #000; font-weight: bold; font-size: 14px;">
			<c:out value="${member.clientId.bankName}" /> - <c:out value="${member.clientId.bankBranch}" /></td>
			
		</tr>	
		<tr>
			<td class="tabDetailViewDF" align="justify" valign="top" width="15%" style="border: 0px solid #000; color: #000; font-weight: bold; font-size: 14px;">
			No. Rek. <c:out value="${member.clientId.clientBankAccount}" /></td>
			
		</tr>	
		<tr>
			<td class="tabDetailViewDF" align="justify" valign="top" width="15%" style="border: 0px solid #000; color: #000; font-weight: bold; font-size: 17px;">
			&nbsp;</td>
			
		</tr>	
		<tr>
			<td class="tabDetailViewDF" align="justify" valign="top" width="15%" style="border: 0px solid #000; color: #000; font-size: 12px;">
			Mohon diberikan catatan/keterangan "Pembayaran Kelebihan Biaya " a.n. Peserta tertagih, pada saat melakukan transfer.</td>
			
		</tr>
		<tr>
			<td class="tabDetailViewDF" align="justify" valign="top" width="15%" style="border: 0px; color: #000; solid #000; font-size: 12px;">
			Apabila ada pertanyaan sehubungan dengan tagihan ini, mohon hubungi Aplikanusa Lintasarta di (021) 797 5328
			</td>
			
			
		</tr>
		<tr>
			<td class="tabDetailViewDF" align="justify" valign="top" width="15%" style="border: 0px solid #000; color: #000; font-size: 12px;">
			Demikianlah, atas bantuan serta kerjasamanya kami ucapkan terima kasih.</td>
			
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
		<td align="left" style="font-size: 12px; text-align: left;"><font color="#000">AYU INTAN </font></td>
		
	</tr>		
	<tr>
		<td align="left"><br><br></td>
		
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		
		<td>&nbsp;</td>
		<td  align="left" style="font-size: 12px;"><font color="#000"> FINANCE COLLECTION OFFICER</font></td>
	</tr>		
	
	
			
		
	
			
	
				
	</tbody>
</table>

	
</form>
	
<!-- Table Content Stop -->
	<!-- Table Toolbar Start -->
	
		<!-- Table Toolbar Stop -->



<!-- Table Container Stop -->
