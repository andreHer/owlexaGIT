<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


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
		<title>Healthcare Management System</title>


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
String rowclass = "";
int i=0;

%>

<c:set var="claimChargeValue" value="0" scope="request" />
<c:set var="claimApprovedValue" value="0"  scope="request" />
<c:set var="claimExcessValue" value="0" scope="request" />
<c:set var="claimExGratiaValue" value="0" scope="request" />

<c:set var="insurancePeriod" value="" scope="request" />
<!-- Search Container Start -->
<table class="tabDetailView" border="0" cellpadding="0" cellspacing="0" width="100%" >
  <tbody>
    
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="20%" style="text-align: left; color: #000; font-size: 10px;">PERUSAHAAN / GROUP&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px; color: #000;"><c:if test="${payment.batchClaim.paymentRecipient.paymentRecipientId eq 3}"><c:out value="${payment.batchClaim.providerId.providerName}" /></c:if> 
		  <c:if test="${payment.batchClaim.paymentRecipient.paymentRecipientId eq 2}"><c:out value="${payment.batchClaim.memberId.memberGroupId.groupName}" /></c:if><c:if test="${payment.batchClaim.paymentRecipient.paymentRecipientId eq 1}"><c:out value="${payment.batchClaim.memberGroupId.groupName}" /></c:if> </td>
	      <td class="tabDetailViewDL" valign="top" width="50%" style="text-align: left; color: #000; font-size: 10px;"> &nbsp;</td>	      
	      <td class="tabDetailViewDL" valign="top" width="50%" style="text-align: left; color: #000; font-size: 10px;" rowspan="10"><img src="images/logo/owlexa.png" width="180" height="60"></td>
	
	    </tr>
	
		
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;">MASA KONTRAK &nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 10px;"><input type="text" value="<c:out value="${insurancePeriode}" />" size="35" style="border: 0px solid #000; color: #000;"></td>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000;">&nbsp; &nbsp;</td>
	      
	
	    </tr>		
		
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;">&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 10px;"><input type="text" value="" size=35 style="border: 0px solid #000; color: #000;"></td>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;">&nbsp;&nbsp;</td>
	      
	
	    </tr>		
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;"> &nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 10px;"></td>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;">&nbsp;&nbsp;</td>
	      
	
	    </tr>		
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;"> &nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 10px;"></td>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;">&nbsp;&nbsp;</td>
	      
	
	    </tr>		
	
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;">Owlexa Healthcare &nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 10px;"></td>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;">&nbsp;&nbsp;</td>	
	    </tr>		
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;">Gedung MH. Thamrin Lt 15 &nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 10px;"></td>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;">&nbsp;&nbsp;</td>	
	    </tr>		
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;">Jakarta Pusat &nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 10px;"></td>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;">&nbsp;&nbsp;</td>	
	    </tr>		
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;">Telp:  &nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 10px;"></td>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;">&nbsp;&nbsp;</td>	
	    </tr>		
					
		
					
		
	
			
	
				
	</tbody>
</table>


<form name="form1" action="batchclaimreport" method="POST">
<input type="hidden" name="navigation" value="report">
<input type="hidden" name="arah" value="">
<input type="hidden" name="index" value="<c:out value="${index}" />">
<input type="hidden" name="batchClaimId" value="<c:out value="${batchClaim.batchClaimId}" />">
	


<br />

 	
<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>

	<tr height="20">
		<td width="6%" nowrap="nowrap" class="listViewThS1" scope="col"></td>
		<td scope="col" colspan="2" class="listViewThS1" nowrap="nowrap" width="20%"></td>
		<td scope="col" colspan="6" class="listViewThS1" nowrap="nowrap" width="70%">&nbsp;</td>
	</tr>
	<tr height="20">
		<td width="6%" nowrap="nowrap" class="listViewThS1" scope="col">No.</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Nomor Peserta</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Nomor CoB Peserta</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="20%">Nama Peserta&nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Nomor Klaim &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Diagnosis &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Claim &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Excess &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">COB Klaim &nbsp;</td>
	</tr>

	<c:set var="claimChargeValue" value="0" scope="request" />	
	<c:set var="claimCobValue" value="0" scope="request" />
	<c:set var="claimPaidValue" value="0"  scope="request" />
	<c:set var="claimExcessValue" value="0"  scope="request" />
	
	<c:forEach items="${reports}" var="claim" varStatus="status" >
		<c:set var="claimChargeValue" value="${claimChargeValue+claim.claimChargeValue}"></c:set>			
		<c:set var="claimCobValue" value="${claimCobValue+claim.cobClaimCharge}"></c:set>
		<c:set var="claimExcessValue" value="${claimExcessValue+claim.claimExcessValue}"></c:set>
		
		<tr height="20">
		      	<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" >
		      		<font style="font-size: 10px"><c:out value="${status.index+1}" />.</font>
		      	</td>      		
		      	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
					<font style="font-size: 10px"><c:out value="${claim.memberId.customerPolicyNumber}" /></font>					
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
					<font style="font-size: 10px"><c:out value="${claim.memberId.otherMemberNumber}" /></font>					
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">					
					<font style="font-size: 10px"><c:out value="${claim.memberId.firstName}" /></font>					
				</td>
		      	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">					
					<font style="font-size: 10px"><c:out value="${claim.claimNumber}" /></font>					
				</td>
		      	<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top" align="left">
					<font style="font-size: 10px"><c:out value="${claim.diagnosisId.description}" /> &nbsp; [ <c:out value="${claim.diagnosisId.diagnosisCode}" />]</font>				
				</td>		
		      	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
					<font style="font-size: 10px"><fmt:formatNumber><c:out value="${claim.claimChargeValue}" /></fmt:formatNumber></font>
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
					<font style="font-size: 10px"><fmt:formatNumber><c:out value="${claim.claimExcessValue}" /></fmt:formatNumber></font>
				</td>				
		      	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
					<font style="font-size: 10px"><fmt:formatNumber><c:out value="${claim.cobClaimCharge}" /></fmt:formatNumber></font>
				</td>	
		    </tr>
		   
			<tr>
		      <td colspan="20" class="listViewHRS1"></td>
		    </tr>
			
				
		
			<tr>
		      <td colspan="20" class="listViewHRS1"></td>
		    </tr>
		    
			<tr>
		      <td colspan="20" class="listViewHRS1"></td>
		    </tr>
	</c:forEach>
	<tr height="20">
		<td width="6%" nowrap="nowrap" class="listViewThS1" scope="col">
			</td>		

<!-- ini default generated table from database -->
		
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">
				</td>
				
		   			<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">
				</td>
		
		   			<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">
				</td>
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">
				</td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">
				</td>			
	   				<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%" style="text-align: right;"></td>
			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%" style="text-align: right;">
				<fmt:formatNumber ><c:out value="${ claimChargeValue}" /></fmt:formatNumber></td>
			   		   		   		   		   		   		   		   		   		   		   		   		   			
			   		   		   		   		   		   		   		   		   		   		   		   		   			
			   		   		   		   		   		   		   		   		   		   		   		   		   			
	   				<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%" style="text-align: right;">
				<fmt:formatNumber><c:out value="${claimCobValue}" /></fmt:formatNumber></td>
			   		   		   		   		   		   		   		   		   		   		   		   		   			
				
	   				
			
		
			   		   			
		
	   
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
	      
		  <td class="tabDetailViewDF" valign="top" width="65%" colspan=3 style="border: 0px solid #000; font-size: 10px;"></td>
	      
	      <td class="tabDetailViewDL" valign="top" width="15%" style="text-align: left; color: #000; font-size: 10px;">&nbsp;Jumlah Diajukan &nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%" style="border: 0px solid #000; font-size: 10px;">Rp. <input type="text" value="<fmt:formatNumber ><c:out value="${claimChargeValue}" /></fmt:formatNumber> " width="20"  style="border: 0px solid #000; color: #000; text-align: right;"></td>
	
	    </tr>
		<tr>	      
		  <td class="tabDetailViewDF" valign="top" width="65%" colspan=3 style="border: 0px solid #000; font-size: 10px;"></td>	      
	      <td class="tabDetailViewDL" valign="top" width="15%" style="text-align: left; color: #000; font-size: 10px;">&nbsp;Jumlah Excess &nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%" style="border: 0px solid #000; font-size: 10px;">Rp. <input type="text" value="<fmt:formatNumber><c:out value="${claimExcessValue}" /></fmt:formatNumber> " width="20"  style="border: 0px solid #000; color: #000; text-align: right;"></td>	
	    </tr>
    	<tr>	      
		  <td class="tabDetailViewDF" valign="top" width="65%" colspan=3 style="border: 0px solid #000; font-size: 10px;"></td>	      
	      <td class="tabDetailViewDL" valign="top" width="15%" style="text-align: left; color: #000; font-size: 10px;">&nbsp;Jumlah Klaim COB &nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%" style="border: 0px solid #000; font-size: 10px;">Rp. <input type="text" value="<fmt:formatNumber><c:out value="${claimCobValue}" /></fmt:formatNumber> " width="20"  style="border: 0px solid #000; color: #000; text-align: right;"></td>	
	    </tr>					
	</tbody>
</table>

</form>
	
<!-- Table Content Stop -->
	<!-- Table Toolbar Start -->
	
		<!-- Table Toolbar Stop -->



<!-- Table Container Stop -->
