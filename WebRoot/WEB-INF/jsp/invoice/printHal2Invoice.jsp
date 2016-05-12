<%@page import="java.util.Collection"%>
<%@page import="com.ametis.cms.datamodel.Claim"%><%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%

Collection claims = (Collection) request.getAttribute("excessCharge");

int totalClaim = 0;

if (claims != null){
	totalClaim = claims.size();
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
String rowclass = "";
int i=0;

%>

<center><h2 style="font-size: 18px; color: #000000; font-weight: bold; ">TAGIHAN KELEBIHAN KLAIM</h2></center>
<center><h2 style="font-size: 18px; color: #000000; font-weight: bold;">EXCESS OF CLAIM (EOC) INVOICE</h2></center>

<br />
<center>
<table class="tabDetailView" border="0" cellpadding="0" cellspacing="0" width="90%" >
  <tbody>
    
		<tr>
	         	      <td class="tabDetailViewDL" valign="top" width="40%" style="text-align: left; color: #000; font-size: 10px;" colspan="2">&nbsp;</td>

	      <td class="tabDetailViewDL" valign="top" width="25%" style="text-align: left; color: #000; font-size: 10px;">&nbsp;No Tagihan / Invoice No. &nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="25%" style="border: 0px solid #000; font-size: 10px; color: #000;"><c:out value="${invoice.invoiceNumber }" /></td>
	     
	
	    </tr>
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;" colspan="2">Kepada Yth,&nbsp;</td>
		  
	      <td class="tabDetailViewDL" valign="top" width="25%" style="text-align: left; color: #000; font-size: 10px;">&nbsp;Tanggal Tagihan / Invoice Date</td>
	      <td class="tabDetailViewDF" valign="top" width="25%" style="border: 0px solid #000; font-size: 10px; color: #000;"><c:out value="${invoice.invoiceDate }" /></td>
	
	    </tr>		
		
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;" colspan="2"><c:out value="${invoice.memberGroupId.groupName}" /></td>
	      <td class="tabDetailViewDL" valign="top" width="25%" style="text-align: left; color: #000;  font-size: 10px;">&nbsp;Tanggal Jatuh Tempo / Billing Due Date &nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="25%" style="border: 0px solid #000; font-size: 10px; color: #000;"><c:out value="${invoice.invoiceDueDate }" /></td>
	
	    </tr>		
		
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;" colspan="2"><c:out value="${invoice.memberGroupId.address}" /> &nbsp;</td>
		  
	      <td class="tabDetailViewDL" valign="top" width="25%" style="text-align: left; color: #000; font-size: 10px;">&nbsp;No. Polis / Policy No. &nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="25%" style="border: 0px solid #000; font-size: 10px; color: #000;"><c:out value="${invoice.memberGroupId.policyNumber }" /></td>
	    </tr>	
	    <tr>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;" colspan="2">&nbsp;</td>
		  
	      <td class="tabDetailViewDL" valign="top" width="25%" style="text-align: left; color: #000; font-size: 10px;">&nbsp;Periode Pertanggungan / Coverage Periode. &nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="25%" style="border: 0px solid #000; font-size: 10px; color: #000;"><c:out value="${invoice.memberGroupId.effectiveDate }" /> s/d <c:out value="${invoice.memberGroupId.expireDate }" /> &nbsp;</td>
	    </tr>	
	    <tr>
   	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;" colspan="2">&nbsp;</td>
	      <td class="tabDetailViewDL" valign="top" width="25%" style="text-align: left; color: #000; font-size: 10px;">&nbsp;Mata Uang / Currency &nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="25%" style="border: 0px solid #000; font-size: 10px; color: #000;">IDR &nbsp;</td>
	    </tr>			
	</tbody>
</table>
<table class="tabDetailView" border="0" cellpadding="0" cellspacing="0" width="90%" >
  <tbody>
    
		<tr>
	         	      <td class="tabDetailViewDL" valign="top" width="40%" style="text-align: left; color: #000; font-size: 10px;" colspan="2">&nbsp;</td>

	      <td class="tabDetailViewDL" valign="top" width="25%" style="text-align: left; color: #000; font-size: 10px;"></td>
	      <td class="tabDetailViewDF" valign="top" width="25%" style="border: 0px solid #000; font-size: 10px;"></td>
	     
	
	    </tr>
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;" colspan="2">UP:  <c:out value="${invoice.memberGroupId.groupName}" />&nbsp;</td>
		  
	      <td class="tabDetailViewDL" valign="top" width="25%" style="text-align: left; color: #000; font-size: 10px;">&nbsp;Total Tagihan</td>
	      <td class="tabDetailViewDF" valign="top" width="25%" style="border: 0px solid #000; font-size: 10px;"><textarea rows="1" cols="15" style="text-align: right; font-size: 15px; font-weight: bold; border: 1px solid;"><fmt:formatNumber><c:out value="${invoice.outstanding }" /></fmt:formatNumber></textarea> </td>
	
	    </tr>		
				
		
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;" colspan="2"></td>
		  
	      <td class="tabDetailViewDL" valign="top" width="25%" style="text-align: left; color: #000; font-size: 10px;">&nbsp;Terbilang &nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="25%" style="border: 0px solid #000; font-size: 10px; color: #000;"><c:out value="${invoiceValueStr}" /></td>
	    </tr>	
	    		
	</tbody>
</table>
</center>
<br />
<center>
<table  cellspacing="0" cellpadding="0" width="85%">
	<tbody>
	


	 <tr height="20" align="left">
    	<td class="oddListRowS1" align="left" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" height="9" style="border: 1px solid #000000; font-size: 10px;"> 
      			1.	Mohon tagihan ini diteliti kembali dan akan dianggap benar jika tidak ada pemberitahuan keberatan terhadap tagihan ini dalam waktu 5 (lima) hari kalender<br/>
      			2.  Transfer melalui bank ke rekening :<br/><br/>
      				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>PT. APLIKANUSA LINTASARTA qq OWLEXA HEALTHCARE</b><br/>
      				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>BCA - Cabang Wisma BCA</b><br />
      				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>No. Rekening  (IDR)</b><br/><br/>
      			3.  Mohon tidak melakukan pembayaran secara tunai<br/>
      			4.  Biaya yang timbul dari proses transfer yang dilakukan harus ditanggung oleh Pemegang Polis.<br />
      			5.  Mohon mencantumkan keterangan "Pembayaran No. Tagihan <b><c:out value="${invoice.invoiceNumber}" /></b> di slip pembayaran pada saat melakukan transfer<br/>
      			6.  Apabila ada pertanyaan lebih lanjut, mohon untuk dapat menghubungi kami di No. Telepon :  No. Fax : <br />	
      			
      			
      			
      			
      			
      </td>
	   		   		
					   		   		
			
    </tr>
	
      
	
	
	
	 	
	</table>
</center>
<br />
<br />
<br />



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
