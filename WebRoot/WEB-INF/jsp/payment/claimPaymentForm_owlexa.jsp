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


<center><h2 style="font-size: 20px; color: #000000; font-weight: bold;">PAYMENT VOUCHER</h2></center>

<br />
<table class="tabDetailView" border="0" cellpadding="0" cellspacing="0" width="100%" >
  <tbody>
    
		<tr>
	      
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 10px;" colspan="2"><input type="text" value="<c:out value="${payment.batchClaim.batchClaimNumber}" />" size="63" style="border: 1px solid #000; color: #000;"></td>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;">&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="25%" style="border: 0px solid #000; font-size: 10px;"></td>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;">&nbsp; &nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 10px;"></td>
	
	    </tr>		
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;">User CDV Number&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 10px;"><input type="text" value="<c:out value="${payment.paymentNumber}" />" size="35" style="border: 1px solid #000; color: #000;"></td>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;">&nbsp;Paid &nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="25%" style="border: 0px solid #000; font-size: 10px;"><input type="text" value="<c:out value="${payment.payeeName }" />" size="55" style="border: 1px solid #000; color: #000;"></td>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;">&nbsp;Voucher Number &nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 10px;"><input type="text" value="" width="20"  style="border: 1px solid #000; color: #000;"></td>
	
	    </tr>
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;">Cash / Bank &nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 10px;"><input type="text" value="Bank" size="35" style="border: 1px solid #000; color: #000;"></td>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;">&nbsp;Transfer to Bank</td>
	      <td class="tabDetailViewDF" valign="top" width="25%" style="border: 0px solid #000; font-size: 10px;"><input type="text" value="<c:out value="${payment.bankName }" />" size="55" style="border: 1px solid #000; color: #000;"></td>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;">&nbsp;Accounting Period &nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 10px;"><input type="text" value="" width="20"  style="border: 1px solid #000; color: #000;"></td>
	
	    </tr>		
		
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;">Bank Account &nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 10px;"><input type="text" value="2553011141" size="35" style="border: 1px solid #000; color: #000;"></td>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000;">&nbsp; &nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="25%" style="border: 0px solid #000; font-size: 10px; color: #000;">Branch &nbsp;<input type="text" value="<c:out value="${payment.bankBranch }" />" size="45" style="border: 1px solid #000; color: #000;"> &nbsp;
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000;">&nbsp;&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 10px;"><input type="text" value="" width="20"  style="border: 1px solid #000; color: #000;"></td>
	
	    </tr>		
		
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;">Chart of Account &nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 10px;"><input type="text" value="" size=35 style="border: 1px solid #000; color: #000;"></td>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;">&nbsp;Bank Account &nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="25%" style="border: 0px solid #000; font-size: 10px; color: #000;"><input type="text" value="<c:out value="${payment.accountNumber }" />" size="20" style="border: 1px solid #000; color: #000;"> &nbsp;Currency &nbsp;<input type="text" value="" size="15" style="border: 1px solid #000; color: #000;"> &nbsp;</td>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000;">&nbsp;&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 10px;"></td>
	
	    </tr>		
	
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;">&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 10px;"></td>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;">&nbsp;Process Date &nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="25%" style="border: 0px solid #000; font-size: 10px; color: #000;"><input type="text" value="" size="30" style="border: 1px solid #000; color: #000;"> &nbsp; &nbsp;</td>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000;">&nbsp;&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 10px;"></td>
	
	    </tr>		
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;">&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 10px;"></td>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;">&nbsp;Payment By &nbsp;</td>
	      <td  colspan="2" class="tabDetailViewDF" valign="middle" width="25%" style="border: 0px solid #000; font-size: 10px; color: #000;"><input type="checkbox" b value="" style="border: 1px solid #000; color: #000;" > &nbsp;Cash &nbsp;&nbsp;<input  style="border: 1px solid #000; color: #000;" type="checkbox" value="" > &nbsp;&nbsp; Cheque &nbsp;&nbsp; <input style="border: 1px solid #000; color: #000;" type="checkbox" value="" > &nbsp;&nbsp; BG &nbsp;&nbsp; <input style="border: 1px solid #000; color: #000;" type="checkbox" value="" > &nbsp;&nbsp;TO &nbsp;&nbsp; Bank Code <input type="text" value="" size="15" style="border: 1px solid #000; color: #000;"> &nbsp;</td>
	      
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 10px;"></td>
	
	    </tr>
				
	</tbody>
</table>

<br />
<table  cellspacing="0" cellpadding="0" >
	<tbody>
	<tr>
      <td colspan="20" align="right">  
            
            </td>
    </tr>
	<tr height="20">
<!-- ini default generated table from database -->
  			
				   		   		   			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%" style="text-align: center; border: 1px solid #000;">
				Acc. Number &nbsp;</td>

	<td scope="col" class="listViewThS1" nowrap="nowrap" width="3%" style="text-align: center; border: 1px solid #000;" >
				T1 &nbsp;</td>


					<td scope="col" class="listViewThS1" nowrap="nowrap" width="3%" style="text-align: center; border: 1px solid #000;">
				T2 &nbsp;</td>
			   		   		   		   		   		   			
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="3%" style="text-align: center; border: 1px solid #000;">
				T3 &nbsp;</td>
			
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="3%" style="text-align: center; border: 1px solid #000;">
				T4 &nbsp;</td>
			
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="3%" style="text-align: center; border: 1px solid #000;">
				T5 &nbsp;</td>
			
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%" style="text-align: center; border: 1px solid #000;">
				Account Name&nbsp;</td>
		
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%" style="text-align: center; border: 1px solid #000;">
				Category&nbsp;</td>
		
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="50%" style="text-align: center; border: 1px solid #000;">
				Description&nbsp;</td>
		
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%" style="text-align: center; border: 1px solid #000;">
				Orig Amount &nbsp;</td>
		
		
	</tr>


	<c:forEach items="${Claims}" var="claim" varStatus="status" >
	<%	if (i % 2 == 0) {
			rowclass = "col1";
		} else if (i % 2 != 0) {
			rowclass= "col2";
		}
		i++;
	%>
	 <tr height="20">
      <td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" height="9" style="border-left: 1px solid #000000;"> </td>
		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border-left: 1px solid #000000;">
			
				
			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border-left: 1px solid #000000;">
			
	
			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border-left: 1px solid #000000;" >
			
	
			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border-left: 1px solid #000000;">
			
				
			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="border-left: 1px solid #000000;">
			
				
			
		</td>
					   		   		   		   		   		
		<td  class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"  style="border-left: 1px solid #000000;">
			
				
			
		</td>		
		   		   	   		   		   		   		   		
					   		   		   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: center; border-left: 1px solid #000000; border-right: 1px solid #000;">
			
			<font style="font-size: 10px; color: #000;">	CLM</font>
			
		</td>		
		   		
		   			   		   		   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<font style="font-size: 10px; color: #000;">Pembayaran Claim atas nama <c:out value="${claim.memberId.firstName}" /> dari <c:out value="${claim.memberId.memberGroupId.groupName}" /></font>
			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border-left: 1px solid #000000; border-right: 1px solid #000; text-align:right;">
			
				<font style="font-size: 10px; color: #000;"><fmt:formatNumber><c:out value="${claim.claimApprovedValue}" /></fmt:formatNumber></font>
			
		</td>		   		   		   		   		
					   		   		   		   		
					   		   		
			
    </tr>
	 <tr height="20">
      <td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" height="9" style="border-left: 1px solid #000000;"> </td>
		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border-left: 1px solid #000000;">
			
				
			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border-left: 1px solid #000000;">
			
	
			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border-left: 1px solid #000000;">
			
	
			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border-left: 1px solid #000000;">
			
				
			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="border-left: 1px solid #000000;">
			
				
			
		</td>
					   		   		   		   		   		
		<td  class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border-left: 1px solid #000000;">
			
				
			
		</td>		
		   		   	   		   		   		   		   		
					   		   		   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align:center; border-left: 1px solid #000000;">
			
				
			
		</td>		
		   		
		   			   		   		   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border-left: 1px solid #000000; border-right: 1px solid #000;">
			
				<font style="font-size: 10px; color: #000;">Nomor Claim <c:out value="${claim.claimNumber}" /> tanggal <c:out value="${claim.claimDate}" /></font>
			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border-left: 1px solid #000000; border-right: 1px solid #000; text-align:right;">
			
				
			
		</td>		   		   		   		   		
					   		   		   		   		
					   		   		
			
    </tr>
      
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	
	</c:forEach>
	<c:if test="${payment.batchClaim.batchExcessValue gt 0 and payment.batchClaim.batchClaimType.claimTypeId eq 2}">
	 <tr height="20">
      <td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" height="9" style="border-left: 1px solid #000000;"> </td>
		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border-left: 1px solid #000000;">
			
				
			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border-left: 1px solid #000000;">
			
	
			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border-left: 1px solid #000000;">
			
	
			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border-left: 1px solid #000000;">
			
				
			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="border-left: 1px solid #000000;">
			
				
			
		</td>
					   		   		   		   		   		
		<td  class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border-left: 1px solid #000000;">
			
				
			
		</td>		
		   		   	   		   		   		   		   		
					   		   		   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align:center; border-left: 1px solid #000000;">
			
				
			
		</td>		
		   		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border-left: 1px solid #000000; border-right: 1px solid #000;">
			
				<font style="font-size: 10px; color: #000;">Excess Tanggungan Peserta</font>
			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border-left: 1px solid #000000; border-right: 1px solid #000; text-align:right;">
			<font style="font-size: 10px; color: #000;"><fmt:formatNumber><c:out value="${payment.batchClaim.batchExcessValue}" /></fmt:formatNumber></font>
		</td>		   		   		   		   		
					   		   		   		   		
					   		   		
			
    </tr>
      
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>

	</c:if>
	<c:if test="${payment.batchClaim.batchExcessValue gt 0 and payment.batchClaim.batchClaimType.claimTypeId eq 3}">
	 <tr height="20">
      <td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" height="9" style="border-left: 1px solid #000000;"> </td>
		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border-left: 1px solid #000000;">
			
				
			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border-left: 1px solid #000000;">
			
	
			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border-left: 1px solid #000000;">
			
	
			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border-left: 1px solid #000000;">
			
				
			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="border-left: 1px solid #000000;">
			
				
			
		</td>
					   		   		   		   		   		
		<td  class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border-left: 1px solid #000000;">
			
				
			
		</td>		
		   		   	   		   		   		   		   		
					   		   		   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align:center; border-left: 1px solid #000000;">
			
				
			
		</td>		
		   		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border-left: 1px solid #000000; border-right: 1px solid #000;">
			
				<font style="font-size: 10px; color: #000;">Excess Tanggungan Perusahaan</font>
			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border-left: 1px solid #000000; border-right: 1px solid #000; text-align:right;">
			<font style="font-size: 10px; color: #000;"><fmt:formatNumber><c:out value="${payment.batchClaim.batchExcessValue}" /></fmt:formatNumber></font>
		</td>		   		   		   		   		
					   		   		   		   		
					   		   		
			
    </tr>
      
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>

	</c:if>
	<c:if test="${payment.batchClaim.biayaMaterai ne null}">
	 <tr height="20">
      <td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" height="9" style="border-left: 1px solid #000000;"> </td>
		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border-left: 1px solid #000000;">
			
				
			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border-left: 1px solid #000000;">
			
	
			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border-left: 1px solid #000000;">
			
	
			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border-left: 1px solid #000000;">
			
				
			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="border-left: 1px solid #000000;">
			
				
			
		</td>
					   		   		   		   		   		
		<td  class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border-left: 1px solid #000000;">
			
				
			
		</td>		
		   		   	   		   		   		   		   		
					   		   		   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align:center; border-left: 1px solid #000000;">
			
				
			
		</td>		
		   		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border-left: 1px solid #000000; border-right: 1px solid #000;">
			
				<font style="font-size: 10px; color: #000;">Biaya Materai</font>
			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border-left: 1px solid #000000; border-right: 1px solid #000; text-align:right;">
			<font style="font-size: 10px; color: #000;"><fmt:formatNumber><c:out value="${payment.batchClaim.biayaMaterai}" /></fmt:formatNumber></font>
		</td>		   		   		   		   		
					   		   		   		   		
					   		   		
			
    </tr>
      
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>

	</c:if>
	<c:if test="${payment.administrationCost ne null}">
	 <tr height="20">
      <td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" height="9" style="border-left: 1px solid #000000;"> </td>
		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border-left: 1px solid #000000;">
			
				
			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border-left: 1px solid #000000;">
			
	
			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border-left: 1px solid #000000;">
			
	
			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border-left: 1px solid #000000;">
			
				
			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="border-left: 1px solid #000000;">
			
				
			
		</td>
					   		   		   		   		   		
		<td  class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border-left: 1px solid #000000;">
			
				
			
		</td>		
		   		   	   		   		   		   		   		
					   		   		   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align:center; border-left: 1px solid #000000;">
			
				
			
		</td>		
		   		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border-left: 1px solid #000000; border-right: 1px solid #000;">
			
				<font style="font-size: 10px; color: #000;">Biaya Administrasi</font>
			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border-left: 1px solid #000000; border-right: 1px solid #000; text-align:right;">
			<font style="font-size: 10px; color: #000;"><fmt:formatNumber><c:out value="${payment.administrationCost}" /></fmt:formatNumber></font>
		</td>		   		   		   		   		
					   		   		   		   		
					   		   		
			
    </tr>
      
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>

	</c:if>
	 <%
	 if (totalClaim == 0){
	 	for (int j = 0 ; j < 10; j++){
	  %>
	 
	 <tr height="20">
      <td  align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border-left: 1px solid #000000;"></td>
		
		<td  bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border-left: 1px solid #000000;">
			
				<c:out value="${claim.claimNumber}" />
			
		</td>
		<td bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border-left: 1px solid #000000;">
			
				
			
		</td>
		<td bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border-left: 1px solid #000000;">
			
		</td>
		<td  bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border-left: 1px solid #000000;">
			
				
			
		</td>
		<td  bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="border-left: 1px solid #000000;">
			
			
			
		</td>
					   		   		   		   		   		
		<td  bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border-left: 1px solid #000000;">
			
			
			
		</td>		
		   		   	   		   		   		   		   		
		<td bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border-left: 1px solid #000000;">
			
			
			
		</td>		
		 <td  bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border-left: 1px solid #000000;">
			
				
			
		</td>		
		 <td  bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border-left: 1px solid #000000; border-right: 1px solid #000;">
			
			
			
		</td>	   		
					   		   		
					   		   		
			
    </tr>
   
	<tr>
      <td colspan="20" class="listViewHRS1" ></td>
    </tr>
	 <%
	 	}
	 }
	  %>
	<tr>
      <td colspan="20" class="listViewHRS1" style="border-bottom: 1px solid #000000;"></td>
    </tr> 	
	</table>
<br />
<br />
<br />
<table class="tabDetailView" border="0" cellpadding="0" cellspacing="0" width="100%" >
  <tbody>
    
    	<tr>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;">Total in Words &nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="20%" colspan=3 style="border: 0px solid #000; font-size: 10px;"><input type="text" value="#<c:out value="${payment.valueDescription}" />#" size="135" style="border: 1px solid #000; color: #000;">&nbsp;</td>
	      
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;">&nbsp; &nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px solid #000; font-size: 10px;"><input type="text" value="Rp. <fmt:formatNumber><c:out value="${payment.paymentValue}" /></fmt:formatNumber> " width="20"  style="border: 1px solid #000; color: #000;"></td>
	
	    </tr>
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;">Prepared by &nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="20%" style="border: 0px solid #000; font-size: 10px;"><input type="text" value="<c:out value="${currentDate}" />" size="15" style="border: 1px solid #000; color: #000;">&nbsp;<input type="text" value="<c:out value="${preparedBy}" />" size="15" style="border: 1px solid #000; color: #000;"></td>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;">&nbsp;&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="45%" style="border: 0px solid #000; font-size: 10px;"><input type="text" value="" size="30" style="border: 0px solid #000; color: #000;"></td>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;">&nbsp;Rate &nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px solid #000; font-size: 10px;"><input type="text" value="-" width="20"  style="border: 1px solid #000; color: #000;"></td>
	
	    </tr>
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;">Checked by &nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="25%" style="border: 0px solid #000; font-size: 10px;"><input type="text" value="" size="15" style="border: 1px solid #000; color: #000;">&nbsp;<input type="text" value="" size="15" style="border: 1px solid #000; color: #000;"></td>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;">&nbsp;Approved by</td>
	      <td class="tabDetailViewDF" valign="top" width="45%" style="border: 0px solid #000; font-size: 10px;"><input type="text" value="Direktur" size="25" style="border: 1px solid #000; color: #000;"></td>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;">&nbsp;Eq. IDR &nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px solid #000; font-size: 10px;"><input type="text" value="Rp. <fmt:formatNumber><c:out value="${payment.paymentValue}" /></fmt:formatNumber>" width="20"  style="border: 1px solid #000; color: #000;"></td>
	
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
