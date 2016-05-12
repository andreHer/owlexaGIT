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

<br />

<table class="tabDetailView" border="0" cellpadding="0" cellspacing="0" width="100%" >
  <tbody>
    
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="15%" style="text-align: left; color: #000; font-size: 10px;">&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="85%" style="border: 0px solid #000; font-size: 13px;">&nbsp;&nbsp;Kantor Pusat / NKB</td>
	    
	
	    </tr>
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: right; color: #000; font-size: 13px;">100&nbsp;&nbsp;&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 10px;"></td>
	
	    </tr>		
					
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;">&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 10px;"></td>
	    </tr>	
	    	<tr>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;">&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 13px;">&nbsp;&nbsp;<c:out value="${payment.payeeName}" /></td>
	    </tr>
	    		
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;">&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 10px;"></td>
	    </tr>		
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;">&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 13px;">&nbsp;&nbsp;#<c:out value="${payment.valueDescription}" />#</td>
	    </tr>		
	
			
		
	
			
	
				
	</tbody>
</table>


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
<br />
<br />
<br />
<br />
<br />
<br />
<table  border="0" cellpadding="0" cellspacing="0" width="100%" >
  <tbody>
    
    	
	<tr>
		<td class="tabDetailViewDL"  width="20%" style="border: 0px solid #000; font-size: 13px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;155.779.000&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:formatNumber> <c:out value="${payment.paymentValue}" /></fmt:formatNumber></td>
		<td class="tabDetailViewDL" align="left"  width="35%" style="border: 0px solid #000; font-size: 13px; align:left;">&nbsp;&nbsp;&nbsp;</td>
		<td class="tabDetailViewDL" width="65%">&nbsp;</td>
		
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
		<td class="tabDetailViewDL"  width="15%" style="border: 0px solid #000; font-size: 13px;">100.100.000&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<fmt:formatNumber> <c:out value="${payment.paymentValue}" /></fmt:formatNumber></td>
		<td class="tabDetailViewDL" align="left"  width="35%" style="border: 0px solid #000; font-size: 13px; align:left;">&nbsp;&nbsp;&nbsp;</td>
		<td class="tabDetailViewDL" width="65%">&nbsp;</td>
		
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
			
	
				
	</tbody>
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
