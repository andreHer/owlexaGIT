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
<table class="tabDetailView" border="0" cellpadding="0" cellspacing="0" width="100%" >
  <tbody>    
		<tr>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;">
	      <img src="images/logo/<c:out value="${member.clientId.clientLogo}" />" width="160" height="80"> &nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;"></td>
	      <td class="tabDetailViewDF" align="right" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;">
	      <img src="images/logo/lintasarta.png" width="220" height="80"> &nbsp;&nbsp;</td>	
	    </tr>		
	    
		<tr>
	      <td class="tabDetailViewDF" valign="top" colspan="3" width="15%" style="border: 0px solid #000; font-weight:bolder; font-size: 16px;" align="center">SURAT JAMINAN</td>		  
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
<form name="form1" action="batchclaimreport" method="POST">
<input type="hidden" name="navigation" value="report">
<input type="hidden" name="arah" value="">
<input type="hidden" name="index" value="<c:out value="${index}" />">
<input type="hidden" name="batchClaimId" value="<c:out value="${batchClaim.batchClaimId}" />">

<c:set var="claimChargeValue" value="0" scope="request" />
<c:set var="claimApprovedValue" value="0"  scope="request" />
<c:set var="claimExcessValue" value="0" scope="request" />
	
<table class="listView" cellspacing="0" cellpadding="0" width="100%" border="0" style="border: 0px;">
	<tbody>

		<tr style="border: thick;">
			<td class="tabDetailViewDF" valign="top" width="15%" style="border-bottom: 2px; border-top: 2px; color: #000; solid #000; font-size: 12px;">
			&nbsp;&nbsp;<font style="font-weight: bold;">INFORMASI RAWAT INAP : &nbsp;<c:out value="${myCase.providerId.providerName}" /></font> </td>	
			<td class="tabDetailViewDF" valign="top" width="15%" style="border-bottom: 2px; border-top: 2px; color: #000; solid #000; font-size: 12px;">
			&nbsp;&nbsp;<font style="font-weight: bold;">NO SURAT : <c:out value="${myCase.caseNumber}" /></font> </td>		
		</tr>		
		
	</tbody>
</table>
<hr>		

<table class="tabDetailView" border="0" cellpadding="0" cellspacing="0" width="100%" >
  <tbody>
    
	<tr>
		<td>&nbsp;&nbsp;</td>
		
		<td><font color="#000">Nama Pasien: </font></td>
		<td>&nbsp;</td>
		<td><font color="#000"><c:out value="${member.firstName}" /></font></td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td><font color="#000">Benefit: </font></td>
		<td>&nbsp;</td>
		<td><font color="#000"><c:out value="${benefit}" /></font></td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	</tr>		
	
		<tr>
		<td>&nbsp;&nbsp;</td>
		
		<td align="left"><font color="#000">No. NIK/Polis: </font></td>
		<td>&nbsp;</td>
		<td align="left"><font color="#000"><c:out value="${member.customerPolicyNumber}" /></font></td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td><font color="#000">Perusahaan: </font></td>
		<td>&nbsp;</td>
		<td><font color="#000"><c:out value="${member.memberGroupId.groupName}" /></font></td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
		<tr>
		<td>&nbsp;&nbsp;</td>
		
		<td align="left"><font color="#000">Masuk Rawat: </font></td>
		<td>&nbsp;</td>
		<td align="left"><font color="#000"><%=tanggalMasuk%></font></td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td><font color="#000">Diagnosa Masuk: </font></td>
		<td>&nbsp;</td>
		<td><font color="#000"><c:out value="${myCase.diagnosis1Id.description}" /></font></td>
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
<table class="listView" cellspacing="0" cellpadding="0" width="100%" border="0" style="border: 0px;">
	<tbody>

		<tr style="border: thick;">
			<td class="tabDetailViewDF" valign="top" width="15%" style="border-bottom: 2px; border-top: 2px; color: #000; solid #000; font-size: 12px;">
			&nbsp;&nbsp;<font style="font-weight: bold;">LIMIT JAMINAN :</font> </td>			
		</tr>		
		
	</tbody>
</table>
<hr>
<table class="listView" cellspacing="0" cellpadding="0" width="100%" border="0" style="border: 0px;">
	<tbody>
		<tr>
			<td class="tabDetailViewDF" align="justify" valign="top" width="15%" style="border: 0px; color: #000; solid #000; font-size: 12px;">
			Surat jaminan ini berlaku apabila pasien menggunakan kamar Rumah Sakit sesuai dengan Plan yang dimilikinya atau ada pemberitahuan lain dari Pihak Aplikanusa Lintasarta.
Apabila Pasien memilih kamar yang melebihi plan, maka pasien harus membayar seluruh selisih biaya perawatan tersebut pada saat pulang di rumah sakit, atau ada pemberitahuan lain dari Pihak Aplikanusa Lintasarta.
<br><br>Mohon bantuan dari pihak RS agar apabila biaya perawatan telah mencapai Rp. 2 juta atau kelipatannya
dapat diinformasikan kepada kami melalui Telp Customer Service 24 jam : +6221 797 5328
			
		
			</td>
			
		</tr>	
			<tr>
			<td class="tabDetailViewDF" align="justify" valign="top" width="15%" style="border: 0px solid #000; font-size: 17px;">
			&nbsp;</td>
			
		</tr>
		
		<tr>
			<td class="tabDetailViewDF" align="justify" valign="top" width="15%" style="border: 0px solid #000; color: #000; font-size: 12px;">
			Atas perhatian dan kerjasamanya, kami ucapkan terimakasih
			</td>
			
		</tr>
		<tr>
			<td class="tabDetailViewDF" align="justify" valign="top" width="15%" style="border: 0px solid #000; color: #000; font-size: 12px;">
			Hormat Kami,</td>
			
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
		<td></td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td style="border-bottom: 0px solid; border-color: #000;">&nbsp;</td>
	</tr>		
	<tr>
		<td>&nbsp;</td>
		<td><img src="images/medical_doctor.jpg" > </td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td><img src="images/provider_relation.jpg"></td>
	</tr>		
	
	


	<tr>
		<td width="5%">&nbsp;</td>
		<td align="left" style="font-size: 13px; text-align: left;"><font color="#000" style="text-decoration: underline; font-weight: bold;"> dr. Bagus Satria  </font></td>
		<td align="left" width="25%"></td>
		
		
		<td>&nbsp;</td>
		
		<td>&nbsp;</td>
		<td align="left" style="font-size: 13px; text-align: left;"><font color="#000" style="text-decoration: underline; font-weight: bold;"> Prio Ananda </font></td>
		
	</tr>		
	<tr>
		<td align="left"><br><br></td>
		
		<td><font color="#000" style="font-size: 13px;"> Medical Doctor </font></td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		
		<td>&nbsp;</td>
		<td  align="left" style="font-size: 13px;"><font color="#000"> Provider Relation </font></td>
	</tr>		
	
	
	</tbody>
</table>
<hr>
<table class="listView" cellspacing="0" cellpadding="0" width="100%" border="0" style="border: 0px;">
	<tbody>
	<tr>
			<td class="tabDetailViewDF" colspan=2 align="justify" valign="top" width="15%" style="border: 0px; color: #000; solid #000; font-size: 12px;">
			Surat Jaminan tidak sah <b>KECUALI</b> apabila Pasien menandatangani pernyataan dibawah ini.		
		
			</td>
			
		</tr>	
		<tr>
			<td class="tabDetailViewDF" align="justify" valign="top" width="15%" style="border: 0px; color: #000; solid #000; font-size: 12px; font-weight: bold;">
			Nama Pasien &nbsp;&nbsp;&nbsp; 		
			</td>
			<td>
			<b><c:out value="${member.firstName}" /></b>
			</td>
			
		</tr>	
		<tr>
			<td class="tabDetailViewDF" align="justify" valign="top" width="15%" style="border: 0px; color: #000; solid #000; font-size: 12px;  font-weight: bold;">
			No. Pasien&nbsp;&nbsp;&nbsp;		
			
		
			</td>
			<td>
				<b><c:out value="${member.customerPolicyNumber}" /></b>
			</td>
			
		</tr>	
		<tr>
			<td class="tabDetailViewDF" colspan=2 align="justify" valign="top" width="15%" style="border: 0px solid #000; font-size: 17px;">
			&nbsp;</td>
			
		</tr>
		<tr>
			<td class="tabDetailViewDF" colspan=2 align="justify" valign="top" width="15%" style="border: 0px; color: #000; solid #000; font-size: 12px;">
			<p>
			Dengan ini Saya bertanggung jawab untuk membayar biaya-biaya non medis dan kelebihan biaya perawatan dari maksimum biaya perawatan 
			yang sudah ditentukan oleh Aplikanusa Lintasarta sebesar pada saat Saya keluar dari Rumah Sakit.
			</p>
		
			</td>
			
		</tr>	
		
		
		<tr>
			<td class="tabDetailViewDF" colspan=2 align="justify" valign="top" width="15%" style="border: 0px solid #000; color: #000; font-size: 12px;">
			<p>
			Saya menyetujui bahwa Surat Jaminan ini berlaku sampai diagnosa akhir yang dikonfirmasikan oleh Dokter yang merawat Saya, 
			dan dengan ini Saya memberi kuasa kepada Dokter, Rumah Sakit untuk memberikan informasi tentang riwayat kesehatan Saya, 
			riwayat perawatan Rumah Sakit dan hasil pemeriksaan lainnya kepada Aplikanusa Lintasarta.
			</p>
			</td>
			
		</tr>
		<tr>
			<td class="tabDetailViewDF" colspan=2 align="justify" valign="top" width="15%" style="border: 0px solid #000; font-size: 17px;">
			&nbsp;</td>
			
		</tr>	
		<tr>
			<td class="tabDetailViewDF" colspan=2 align="justify" valign="top" width="15%" style="border: 0px solid #000; color: #000; font-size: 12px;">
			Jakarta,   <c:out value="${tanggal}" />            </td>
			
		</tr>		
		<tr>
			<td class="tabDetailViewDF" colspan=2 align="justify" valign="top" width="15%" style="border: 0px solid #000; color: #000; font-size: 12px;">
			
			&nbsp;
			 </td>
			
		</tr>		
		<tr>
			<td class="tabDetailViewDF" colspan=2 align="justify" valign="top" width="15%" style="border: 0px solid #000; color: #000; font-size: 12px;">
			&nbsp;
			</td>
			
		</tr>		
		<tr>
			<td class="tabDetailViewDF" colspan=2 align="justify" valign="top" width="15%" style="border: 0px solid #000; color: #000; font-size: 12px;">
			         &nbsp;    </td>
			
		</tr>		
			<tr>
			<td class="tabDetailViewDF" colspan=2 align="justify" valign="top" width="15%" style="border: 0px solid #000; color: #000; font-size: 12px;">
			( <c:out value="${member.firstName}" /> )
			             </td>
			
		</tr>
			
	<tr>
			<td class="tabDetailViewDF" colspan=2 valign="top" width="15%" style="border: 0px solid #000; color: #000; font-size: 11px;">
			<b>Catatan</b> :
Apabila pasien karena kondisi kesehatannya tidak memungkinkan, atau untuk Pasien Anak, atau pasien yang menderita cacat sehingga tidak dapat menandatangani Surat Pernyataan ini, maka dapat diwakilkan oleh anggota keluarga terdekat dan melampirkan fotocopy KTP.
			
			</td>			
		</tr>
	</tbody>
</table>

</form>
	
<!-- Table Content Stop -->
	<!-- Table Toolbar Start -->
	
		<!-- Table Toolbar Stop -->



<!-- Table Container Stop -->
