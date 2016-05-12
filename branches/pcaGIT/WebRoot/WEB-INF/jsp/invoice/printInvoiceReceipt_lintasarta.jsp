<%@page import="java.util.Collection"%>
<%@page import="com.ametis.cms.datamodel.Claim"%><%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%

Collection claims = (Collection) request.getAttribute("Claims");

int totalClaim = 0;

if (claims != null){
	totalClaim = claims.size();
}

%>

<head>
		<link rel="stylesheet" type="text/css" media="all" href="css/style.css" />
		<link rel="stylesheet" type="text/css" href="css/reset-fonts-grids.css" />
        <link rel="stylesheet" type="text/css" href="css/menu.css" /> 
		<link href="css/navigation.css" rel="stylesheet" type="text/css" />
</head>

<%
String rowclass = "";
int i=0;

%>

<table width="95%" border="1" align="center">
<tr >
<td width="90%" >

<br />
<br />
<center>
<table class="tabDetailView" border="0" cellpadding="0" cellspacing="0" width="95%" >
  <tbody>
		<tr>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000;text-align: left; color: #000; font-size: 12px;">&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="55%" style="border: 0px solid #000; color: #000; font-size: 12px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="25%" style="border: 0px solid #000; color: #000; font-size: 12px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${invoice.invoiceDate}" /></td>
	      	
	    </tr>
		<tr>
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px solid #000;text-align: left; color: #000; font-size: 12px;"> &nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; color: #000; font-size: 12px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="25%" style="border: 0px solid #000; color: #000; font-size: 12px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${invoice.invoiceNumber}" /></td>
	    </tr>		
		<tr>
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px solid #000;text-align: left; color: #000; font-size: 12px;"> &nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; color: #000; font-size: 12px;">&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px solid #000;text-align: left; color: #000; font-size: 12px;"> &nbsp;</td>      
	    </tr>	
	
	</tbody>
</table>
</center>
<br />
<br />
<br />

<center>
<table class="tabDetailView" border="0" cellpadding="0" cellspacing="0" width="95%" >
  <tbody>  
		<tr>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000;text-align: left; color: #000; font-size: 12px;"></td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; color: #000; font-size: 12px; text-align: right; padding-right: 180px;">&nbsp;&nbsp;&nbsp;</td>
	      	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; color: #000; font-size: 15px; text-align: right; padding-right: 80px;"><b>NOTA DEBIT - EXCESS KLAIM</b></td>
	    </tr>
	    <tr>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000;text-align: left; color: #000; font-size: 12px;"></td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; color: #000; font-size: 12px; text-align: right; padding-right: 180px;">&nbsp;&nbsp;&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; color: #000; font-size: 12px; text-align: left; padding-right: 180px;"></td>	
	    </tr>
		<tr>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000;text-align: left; color: #000; font-size: 12px;">NAMA TERTANGGUNG :</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; color: #000; font-size: 12px; text-align: right; padding-right: 180px;">&nbsp;&nbsp;&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; color: #000; font-size: 12px; text-align: left; padding-right: 180px;">Tagihan Ekses &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; : &nbsp;&nbsp;<fmt:formatNumber><c:out value="${invoice.invoiceValue}" /></fmt:formatNumber></td>	
	    </tr>
		
		<tr>
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px solid #000;text-align: left; color: #000; font-size: 12px;"><c:out value="${invoice.memberGroupId.groupName}" /> &nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; color: #000; font-size: 12px;"></td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; color: #000; font-size: 12px; text-align: right; padding-right: 180px;">&nbsp;&nbsp;&nbsp;</td>
	    </tr>		
		
		<tr>
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px solid #000;text-align: left; color: #000; font-size: 12px;" colspan=3></td>  

	    </tr>		
		<tr>
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px solid #000;text-align: left; color: #000; font-size: 12px;" colspan=3></td>  

	    </tr>		
	
		<tr>
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px solid #000;text-align: left; color: #000; font-size: 12px;">&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; color: #000; font-size: 12px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		  		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; color: #000; font-size: 12px; text-align: right; padding-right: 180px;">&nbsp;&nbsp;&nbsp;</td>	    
	    </tr>		
	    <tr>
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px solid #000;text-align: left; color: #000; font-size: 12px;"></td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; color: #000; font-size: 12px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>	
		  		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; color: #000; font-size: 12px; text-align: right; padding-right: 180px;">&nbsp;&nbsp;&nbsp;</td>
	    </tr>		
	    <tr>
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px solid #000;text-align: left; color: #000; font-size: 12px;">No. Polis &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; color: #000; font-size: 12px;">&nbsp;&nbsp;&nbsp;</td>	
		  		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; color: #000; font-size: 12px; text-align: right; padding-right: 180px;">&nbsp;&nbsp;&nbsp;</td>
	    </tr>		
		<tr>
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px solid #000;text-align: left; color: #000; font-size: 12px;">Jangka Waktu  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000;color: #000; font-size: 12px;">&nbsp;&nbsp;&nbsp;</td>
			<td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; color: #000; font-size: 12px; text-align: left; padding-right: 80px;">Jumlah untuk kami &nbsp;&nbsp;&nbsp; : &nbsp;&nbsp;<fmt:formatNumber><c:out value="${invoice.invoiceValue}" /></fmt:formatNumber></td>	      
	
	    </tr>
   		<tr>
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px solid #000;text-align: left; color: #000; font-size: 12px;">Jenis Pertanggungan  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; color: #000; font-size: 12px;"></td>
	    </tr>		
		<tr>
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px solid #000;text-align: left; color: #000; font-size: 12px;"></td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; color: #000; font-size: 12px;"></td>
	    </tr>		
			    		
   		<tr>
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px solid #000;text-align: left; color: #000; font-size: 12px;"></td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; color: #000; font-size: 12px;"></td>
	    </tr>
	    <tr>
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px solid #000;text-align: left; color: #000; font-size: 12px;"></td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; color: #000; font-size: 12px;"></td>
	    </tr>		
	    <tr>
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px solid #000;text-align: left; color: #000; font-size: 12px;">&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; color: #000; font-size: 12px;"></td>
	    </tr>				
 	    
	</tbody>
</table>
</center>
	
	<br />
	<center>
	<table  cellspacing="0" cellpadding="0" border="0" width="95%" >		
	 <tr height="20">
		      <td class="oddListRowS1" align="left" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" height="9" style="border: 0px solid #000000;">
		      	Pembayaran ditransfer ke rekening kami <c:out value="${configuration.bankName}" /> - <c:out value="${configuration.bankBranch}" /> <br />
		      	No. Rekening : <c:out value="${configuration.bankAccount}" /> - A/N <c:out value="${configuration.bankAccountName}" />
		      </td>
				
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border: 0px solid #000000;">			
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border: 0px solid #000000;">			
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border: 0px solid #000000;">			
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border: 0px solid #000000;">
				</td>
				<td class="oddListRowS1" align=left rowspan="5" bgcolor="#e7f0fe"  valign="top" style="border: 0px solid #000000;" >
				Mohon pembayaran dapat dilakukan maksimum <c:out value="${configuration.excessExpireDay}" /> hari kerja dari tanggal tagihan
				dan kirimkan bukti transfer serta tagihan ini. Apabila sampai tanggal jatuh tempo belum dibayarkan, maka pelayanan klaim peserta
				secara otomatis kami hentikan sampai excess dibayarkan.
				</td>
		    </tr>	 
		     <tr height="20">
		      <td class="oddListRowS1" align="left" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" height="9" style="border: 0px solid #000000;">
		      	
		      </td>
				
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border: 0px solid #000000;">			
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border: 0px solid #000000;">			
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border: 0px solid #000000;">			
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border: 0px solid #000000;">
				</td>
				<td class="oddListRowS1" align=center bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border: 0px solid #000000;" >
				
				</td>
		    </tr>	 
	</table>
	</center>
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	
<br />
<br />
<br />

</td>
</tr>
</table>

<script language="javascript">
	
	function kembali (){
		document.form1.navigation.value = "back";
		document.form1.submit();
	}
	function ubah (){
		document.form1.navigation.value = "update";
		document.form1.action = "payment-form";
		document.form1.method = "GET";
		document.form1.submit();
	}
	function printPayment(){
		document.form1.navigation.value = "printpayment";
		
		document.form1.action = "payment";
		document.form1.method = "GET";
		document.form1.submit();
	}
</script>
