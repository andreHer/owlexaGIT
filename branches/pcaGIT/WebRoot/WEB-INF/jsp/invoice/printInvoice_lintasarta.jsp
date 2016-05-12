
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
		<link rel="stylesheet" type="text/css" media="all" href="css/style.css" />  		  	
		<link rel="stylesheet" type="text/css" href="css/reset-fonts-grids.css" />
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
<table class="tabDetailView" border="0" cellpadding="0" cellspacing="0" width="100%" >
  <tbody>
    
		<tr>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;"><img height="80" src="images/logo/<c:out value="${invoice.memberGroupId.clientId.clientLogo }" />"></td>
		  <td class="tabDetailViewDF" valign="top" width="50%" style="border: 0px solid #000; font-size: 12px;"></td>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;"><img height="70" src="images/logo/<c:out value="${configuration.companyCode }" />.png"></td>	
	    </tr>	
	</tbody>
</table>

<c:set var="insurancePeriod" value="" scope="request" />
<!-- Search Container Start -->
<table class="tabDetailView" border="0" cellpadding="0" cellspacing="0" width="100%" >
  <tbody>
    
		<tr>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;"></td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;"></td>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;">&nbsp;&nbsp;</td>	
	    </tr>		
	    <tr>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;"> No: <c:out value="${invoice.invoiceNumber}" />&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 17px;"></td>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px; color: #000;">&nbsp;&nbsp;&nbsp; </td>	
	    </tr>
	    <tr>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;">Jakarta, <%=tanggalExcess%> &nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 17px;"></td>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px; color: #000;">&nbsp;&nbsp;&nbsp; </td>	
	    </tr>
	    <tr>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px; color: #000;"> &nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 17px;"></td>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px; color: #000;"></td>	
	    </tr>		
		<tr>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px; color: #000;">Kepada Yth, &nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 17px;"></td>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px; color: #000;"></td>	
	    </tr>		
		<tr>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px; color: #000;"><b><c:out value="${invoice.memberGroupId.groupName}" /></b> &nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;"></td>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px; color: #000;"></td>	
	    </tr>		
		<tr>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px; color: #000;"><c:out value="${invoice.memberGroupId.address}" /></td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;"></td>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;">&nbsp;&nbsp;</td>	
	    </tr>		
		<tr>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;"><c:out value="${invoice.memberGroupId.city}" /> - <c:out value="${invoice.memberGroupId.province}" /></td>
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
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px; color: #000;" colspan="2">Hal : Tagihan Kelebihan Klaim (Excess of Claim)</td>
		  
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;">&nbsp;&nbsp;</td>	
	    </tr>		
		<tr>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px; color: #000;" colspan="2"></td>
		  
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
			Bersama ini kami sampaikan Ekses klaim yang terjadi sehubungan dengan pengobatan yang dilakukan oleh karyawan/i dan anggota keluarganya 
			di Provider kami dari  <c:out value="${invoice.memberGroupId.groupName}" /> (<b>masa pertanggungan <c:out value="${invoice.memberGroupId.effectiveDate}" /> s/d <c:out value="${invoice.memberGroupId.expireDate}" /></b>) dimana  
			terdapat pengobatan yang merupakan pengecualian dan klaim yang diluar cakupan manfaat peserta yang dijaminkan terlebih dahulu oleh pihak Asuransi.
			<br />
			 
			
			<br>
			Ekses klaim tersebut sejumlah <b>Rp. <fmt:formatNumber><c:out value="${invoice.invoiceValue}" /></fmt:formatNumber></b>, Rekapitulasi Ekses Klaim kami sertakan berikut perinciannya. 
			<br />
			<br />
			Mohon konfirmasi atas jumlah tagihan tersebut dan apabila benar maka kami harapkan pembayarannya dapat diselesaikan selambat-lambatnya <c:out value="${configuration.excessExpireDay}" /> hari kerja
			setelah tagihan diterima, melalui rekening:
			<br />
			<br />
			<center><b><c:out value="${configuration.bankName}" /> - <c:out value="${configuration.bankBranch}" /></b></center>			
			<center><b>ACC : <c:out value="${configuration.bankAccount}" /></b></center>			
			<center><b>A/N : <c:out value="${configuration.bankAccountName}" /></b></center>
			
			<br />
			<br />
			Pada lembaran transfer mohon mencantumkan nama jelas tertanggung, nomor peserta, nama perusahaan dan bukti transfer harap dikirimkan ke no. Fax : (021) 7205664 / 7205662 
			UP. GHS Bagian Excess (a/n Iswahyudin)
			<br/>
			<br />
			<c:if test="${invoice.invoiceValue < 1000000}">
			Apabila fotocopy dokumen klaim dibutuhkan, dapat menghubungi sdr. Azmi/Wahyu di no telepon 082817020017 atau email ke iswahyudin@ciuinsurance.com
			</c:if>
			<br />
			<br />
			Demikian  kami sampaikan. Atas perhatian dan kerjasamanya, kami ucapkan terima kasih.
			
			
			
			</td>
			
		</tr>		


	<tr>
			<td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;">
			</td>
			
		</tr>	
		
		<tr>
			<td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 17px;">&nbsp;
			</td>
			
			
		</tr>	
			
	</tbody>
</table>		
<table class="tabDetailView" border="0" cellpadding="0" cellspacing="0" width="100%" >
  <tbody>
    
	<tr>
		<td>&nbsp;&nbsp;<font color="#000">Hormat Kami, </font></td>		
		<td></td>
		<td>&nbsp;</td>		
	</tr>		
	
	<tr>
		<td>&nbsp;&nbsp;<font color="#000"><c:out value="${configuration.company}" /></font></td>
		
		<td><font color="#000"></font></td>
		<td>&nbsp;</td>
		
	</tr>		
	
<tr>
		<td>&nbsp;&nbsp;</td>
		
		<td></td>
		<td>&nbsp;</td>
		
	</tr>	
	<tr>
		<td>&nbsp;&nbsp;</td>
		
		<td></td>
		<td>&nbsp;</td>
		
	</tr>	
	<tr>
		<td>&nbsp;&nbsp;</td>
		
		<td></td>
		<td>&nbsp;</td>
		
	</tr>	
	<tr>
		<td>&nbsp;&nbsp;</td>
		
		<td></td>
		<td>&nbsp;</td>
		
	</tr>	
	<tr>
		<td>&nbsp;&nbsp;</td>
		
		<td></td>
		<td>&nbsp;</td>
		
	</tr>	
	<tr>
		<td>&nbsp;&nbsp;<font color="#000"><c:out value="${configuration.director}" /></font></td>		
		<td></td>
		<td>&nbsp;</td>
		
	</tr>		
	<tr>
		<td>&nbsp;&nbsp;<font color="#000"><c:out value="${configuration.directorLabel}" /> </font></td>
		
		<td></td>
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
	
