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
* Tab Content script- � Dynamic Drive DHTML code library (www.dynamicdrive.com)
* This notice MUST stay intact for legal use
* Visit Dynamic Drive at http://www.dynamicdrive.com/ for full source code
***********************************************/
</script>
<link rel="stylesheet" type="text/css" href="css/reset-fonts-grids.css" />
        <!-- CSS for Menu -->
<link rel="stylesheet" type="text/css" href="css/menu.css" />
<script type="text/javascript" src="scripts/calendar.js"></script>
	
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
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px; color: #000;">
		  	<c:if test="${payment.batchClaim.paymentRecipient.paymentRecipientId eq 3}"><c:out value="${payment.batchClaim.providerId.providerName}" /></c:if> 
		  	<c:if test="${payment.batchClaim.paymentRecipient.paymentRecipientId eq 2}"><c:out value="${payment.batchClaim.memberId.memberGroupId.groupName}" /></c:if>
		  	<c:if test="${payment.batchClaim.paymentRecipient.paymentRecipientId eq 1}"><c:out value="${payment.batchClaim.memberGroupId.groupName}" /></c:if> 
		  </td>
	      <td class="tabDetailViewDL" valign="top" width="50%" style="text-align: left; color: #000; font-size: 10px;"> &nbsp;</td>	      
	      <td class="tabDetailViewDL" valign="top" width="50%" style="text-align: left; color: #000; font-size: 10px;" rowspan="10"><img src="images/logo/owlexa.png" width="100" height="60"></td>
	
	    </tr>
	
		
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;">MASA KONTRAK &nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 10px;"><input type="text" value="<c:out value="${insurancePeriode}" />" size="35" style="border: 0px solid #000; color: #000;"></td>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000;">&nbsp; &nbsp;</td>
	      
	
	    </tr>		
	
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;"> &nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 10px;"></td>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;">&nbsp;&nbsp;</td>
	      
	
	    </tr>			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;">DATE OF RECEIVED &nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 10px;"><input type="text" value="<c:out value="${payment.batchClaim.batchClaimDate}" />" size=35 style="border: 0px solid #000; color: #000;"></td>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;">&nbsp;&nbsp;</td>
	    </tr>
	    <tr>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;">DATE OF SUBMISSION &nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 10px;"><input type="text" value="<c:out value="${payment.paymentTime}" />" size=35 style="border: 0px solid #000; color: #000;"></td>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;">&nbsp;&nbsp;</td>
	    </tr>				
	
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;"> &nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 10px;"></td>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;">&nbsp;&nbsp;</td>
	      
	
	    </tr>		
	
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;">Aplikanusa Lintasarta &nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 10px;"></td>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;">&nbsp;&nbsp;</td>	
	    </tr>		
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;">Menara Thamrin Lt. 18, Jakarta Pusat&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 10px;"></td>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;">&nbsp;&nbsp;</td>	
	    </tr>		
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;">&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 10px;"></td>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;">&nbsp;&nbsp;</td>	
	    </tr>		
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;"> &nbsp;</td>
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
<c:if test="${batchClaim.batchClaimType.claimTypeId eq 2}">
 	
<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>

		<tr height="20">
			<td width="6%" nowrap="nowrap" class="listViewThS1" scope="col"><img src="images/blank.gif" alt="asd" height="1" width="1"></td>
			<td scope="col" colspan="2" class="listViewThS1" nowrap="nowrap" width="20%"></td>
			<td scope="col" colspan="12" class="listViewThS1" nowrap="nowrap" width="70%">&nbsp;</td>
		</tr>
		<tr height="20">
			<td width="6%" nowrap="nowrap" class="listViewThS1" scope="col">
				<img src="images/blank.gif" alt="asd" height="1" width="1">No.</td>		
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Nomor Peserta</td>		   			
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="20%">Nama Peserta&nbsp;</td>				
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Nomor Klaim &nbsp;</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Diagnose &nbsp;</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Admission &nbsp;</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Discharge &nbsp;</td>			
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="6%">Nilai Klaim &nbsp;</td>	   		   		   		   		   		   		   		   		   		   		   		   		   			
		   	<td scope="col" class="listViewThS1" nowrap="nowrap" width="6%">Ditolak &nbsp;</td>			
		   	<td scope="col" class="listViewThS1" nowrap="nowrap" width="6%">Disetujui &nbsp;</td>		   	
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="6%">Ex Gratia &nbsp;</td>				
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="6%">Excess &nbsp;</td>
			<td scope="col" class="listViewThS1" width="6%">Pre-Paid Excess &nbsp;</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="6%">Refund &nbsp;</td>
			<td scope="col" class="listViewThS1" width="6%">Paid To Provider &nbsp;</td>
		</tr>

			<c:set var="claimInitialValue" value="0" scope="request" />
			<c:set var="claimFinalValue" value="0"  scope="request" />
			<c:set var="excessValue" value="0" scope="request" />
			<c:set var="claimPaidValue" value="0"  scope="request" />
			
			<c:forEach items="${reports}" var="claim" varStatus="status" >
				<c:set var="claimItem" value="${claim.claimItems}" />
				
				<tr height="20">
				      	<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border: 0px solid #000;"><c:out value="${status.index+1}" />.</td>
				      	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">					
							<font style="font-size: 10px"><c:out value="${claim.claim.memberId.customerPolicyNumber}" /></font>					
						</td>
						<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">					
							<font style="font-size: 10px"><c:out value="${claim.claim.memberId.firstName}" /></font>					
						</td>
						<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">					
							<font style="font-size: 10px"><c:out value="${claim.claim.claimNumber}" /></font>					
						</td>
				      	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">					
							<font style="font-size: 10px"><c:out value="${claim.claim.diagnosis1Code}" /></font>					
						</td>		
				      	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
							<font style="font-size: 10px"><c:out value="${claim.claim.admissionDate}" /></font>
						</td>		
				      	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
							<font style="font-size: 10px"><c:out value="${claim.claim.dischargeDate}" /></font>
						</td>
						<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right"></td>		
				      	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right"></td>		
				      	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right"></td>
				 		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right"></td>		
				      	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right"></td>		 		   	
				      	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right"></td>
				      	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right"></td>					
				      	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right"></td>
				    </tr>
				     
					<tr>
				      <td colspan="20" class="listViewHRS1"></td>
				    </tr>
					<c:forEach items="${claimItem}" var="ci" varStatus="ciStatus">
					<c:set var="claimChargeValue" value="${claimChargeValue+ci.claimItemValue}"></c:set>
					<c:set var="claimApprovedValue" value="${claimApprovedValue+ci.claimItemApprovedValue}"></c:set>
					<c:set var="claimExcessValue" value="${claimExcessValue+ci.excessValue}"></c:set>
					<c:set var="claimRefundValue" value="${claimRefundValue+ci.refund}"></c:set>
					<c:set var="claimPrePaidExcessValue" value="${claimPrePaidExcessValue+ci.prePaidExcess}"></c:set>
					<c:set var="claimExGratiaValue" value="${claimExGratiaValue+ci.claimExGratia}"></c:set>
					<c:set var="claimPaidToProviderValue" value="${claimPaidToProviderValue+ci.paidToProvider}"></c:set>
				
					<tr height="20">
				      	<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"></td>		      	
						<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" colspan="2">					
							&nbsp;&nbsp;&nbsp;<font style="font-size: 10px"><c:out value="${ciStatus.index + 1}" />.</font> &nbsp;<font style="font-size: 10px;font-weight: bold;"><b><c:out value="${ci.itemId.itemName}" /></b> &nbsp;&nbsp; <c:out value="${ci.claimItemDescription}" /> </font>					
						</td>
		   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"></td>
						<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"></td>
						<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"></td>
						<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"></td>
						<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">					
							<font style="font-size: 10px"><fmt:formatNumber><c:out value="${ci.claimItemValue }" /></fmt:formatNumber></font>					
						</td>		
				      	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">					
							<font style="font-size: 10px"><fmt:formatNumber><c:out value="${ci.claimItemValue - ci.claimItemApprovedValue }" /></fmt:formatNumber></font>					
						</td>		
				      	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">					
							<font style="font-size: 10px"><fmt:formatNumber><c:out value="${ci.claimItemApprovedValue}" /></fmt:formatNumber></font>					
						</td>
						<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">					
							<font style="font-size: 10px"><fmt:formatNumber><c:out value="${ci.claimExGratia}" /></fmt:formatNumber></font>					
						</td>
						<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">					
							<font style="font-size: 10px">
								<c:if test="${ci.claimId.claimTypeId.claimTypeId eq 2}">
									<fmt:formatNumber><c:out value="${ci.excessValue}" /></fmt:formatNumber>
								</c:if>
							</font>					
						</td>
						<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">					
							<font style="font-size: 10px">
								<c:if test="${ci.claimId.claimTypeId.claimTypeId eq 2}">
									<fmt:formatNumber><c:out value="${ci.prePaidExcess}" /></fmt:formatNumber>
								</c:if>
							</font>					
						</td>
						<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">					
							<font style="font-size: 10px">
								<c:if test="${ci.claimId.claimTypeId.claimTypeId eq 2}">
									<fmt:formatNumber><c:out value="${ci.refund}" /></fmt:formatNumber>
								</c:if>
							</font>					
						</td>
						<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">					
							<font style="font-size: 10px">
								<c:if test="${ci.claimId.claimTypeId.claimTypeId eq 2}">
									<fmt:formatNumber><c:out value="${ci.paidToProvider}" /></fmt:formatNumber>
								</c:if>
							</font>					
						</td>
				    </tr>
				   <tr height="20">
				      	<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"></td>		      	
						<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" colspan="14">					
							&nbsp;&nbsp;&nbsp;<font style="font-size: 10px"><c:if test="${ci.claimItemStatus.caseStatusId eq 4}"> <c:out value="${ci.medicalRemarks}" /></c:if><c:if test="${ci.claimItemStatus.caseStatusId eq 8}"> <c:out value="${ci.benefitCheckRemarks}" /></c:if></font>
						</td>			
				    </tr>		   
					<tr>
				      <td colspan="20" class="listViewHRS1"></td>
				    </tr>
			
				</c:forEach>
			</c:forEach>
			<tr height="20">
				<td width="6%" nowrap="nowrap" class="listViewThS1" scope="col"></td>	
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%"></td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%"></td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%"></td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%"></td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%"></td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%"></td>			
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%" style="text-align: right;">
					<fmt:formatNumber ><c:out value="${claimChargeValue}" /></fmt:formatNumber>
				</td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%" style="text-align: right;">
					<fmt:formatNumber ><c:out value="${ claimChargeValue - claimApprovedValue}" /></fmt:formatNumber>
				</td> 		   		   		   		   		   		   		   		   		   		   		   		   			
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%" style="text-align: right;">
					<fmt:formatNumber><c:out value="${claimApprovedValue}" /></fmt:formatNumber>
				</td>			   		   		   		   		   		   		   		   		   		   		   		   		   			
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%"  style="text-align: right;">
						<fmt:formatNumber><c:out value="${claimExGratiaValue}" /></fmt:formatNumber>
				</td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%"  style="text-align: right;">					
						<fmt:formatNumber><c:out value="${claimExcessValue}" /></fmt:formatNumber>					
				</td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%"  style="text-align: right;">						
					<fmt:formatNumber><c:out value="${claimPrePaidExcessValue}" /></fmt:formatNumber>
						
				</td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%"  style="text-align: right;">						
					<fmt:formatNumber><c:out value="${claimRefundValue}" /></fmt:formatNumber>						
				</td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%"  style="text-align: right;">						
					<fmt:formatNumber><c:out value="${claimPaidToProviderValue}" /></fmt:formatNumber>						
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
	      <td class="tabDetailViewDF" valign="top" width="65%" colspan=3 style="border: 0px solid #000; font-size: 10px;"></td>	      
	      <td class="tabDetailViewDL" valign="top" width="15%" style="text-align: left; color: #000; font-size: 10px;">&nbsp;Jumlah Diajukan &nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%" style="border: 0px solid #000; font-size: 10px;">Rp. <input type="text" value="<fmt:formatNumber ><c:out value="${claimChargeValue}" /></fmt:formatNumber> " width="20"  style="border: 0px solid #000; color: #000; text-align: right;"></td>	
	    </tr>
		<tr>	      
		  <td class="tabDetailViewDF" valign="top" width="65%" colspan=3 style="border: 0px solid #000; font-size: 10px;"></td>	      
	      <td class="tabDetailViewDL" valign="top" width="15%" style="text-align: left; color: #000; font-size: 10px;">&nbsp;Biaya Materai &nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%" style="border: 0px solid #000; font-size: 10px;">Rp. <input type="text" value="<fmt:formatNumber ><c:out value="${payment.batchClaim.biayaMaterai}" /></fmt:formatNumber> " width="20"  style="border: 0px solid #000; color: #000; text-align: right;"></td>
	
	    </tr>

    	<tr>
	      
		  <td class="tabDetailViewDF" valign="top" width="65%" colspan=3 style="border: 0px solid #000; font-size: 10px;"></td>
	      
	      <td class="tabDetailViewDL" valign="top" width="15%" style="text-align: left; color: #000; font-size: 10px;">&nbsp;Pembulatan &nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%" style="border: 0px solid #000; font-size: 10px;">Rp. <input type="text" value="<fmt:formatNumber ><c:out value="${pembulatan}" /></fmt:formatNumber> " width="20"  style="border: 0px solid #000; color: #000; text-align: right;"></td>
	
	    </tr>
    	<tr>
	      
		  <td class="tabDetailViewDF" valign="top" width="65%" colspan=3 style="border: 0px solid #000; font-size: 10px;"></td>
	      
	      <td class="tabDetailViewDL" valign="top" width="15%" style="text-align: left; color: #000; font-size: 10px;">&nbsp;Jumlah Disetujui &nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%" style="border: 0px solid #000; font-size: 10px;">Rp. <input type="text" value="<fmt:formatNumber><c:out value="${claimApprovedValue+payment.batchClaim.biayaMaterai}" /></fmt:formatNumber> " width="20"  style="border: 0px solid #000; color: #000; text-align: right;"></td>
	
	    </tr>
		<tr>      
		  <td class="tabDetailViewDF" valign="top" width="65%" colspan=3 style="border: 0px solid #000; font-size: 10px;"></td>	      
	      <td class="tabDetailViewDL" valign="top" width="15%" style="text-align: left; color: #000; font-size: 10px;">&nbsp;Jumlah Ditolak &nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%" style="border: 0px solid #000; font-size: 10px;">Rp. <input type="text" value="<fmt:formatNumber><c:out value="${claimChargeValue - claimApprovedValue }" /></fmt:formatNumber> " width="20"  style="border: 0px solid #000; color: #000; text-align: right;"></td>	
	    </tr>
	    <tr>      
		  <td class="tabDetailViewDF" valign="top" width="65%" colspan=3 style="border: 0px solid #000; font-size: 10px;"></td>	      
	      <td class="tabDetailViewDL" valign="top" width="15%" style="text-align: left; color: #000; font-size: 10px;">&nbsp;Jumlah Pre Paid Excess &nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%" style="border: 0px solid #000; font-size: 10px;">Rp. <input type="text" value="<fmt:formatNumber><c:out value="${claimPrePaidExcessValue}" /></fmt:formatNumber> " width="20"  style="border: 0px solid #000; color: #000; text-align: right;"></td>	
	    </tr>
		<tr>      
		  <td class="tabDetailViewDF" valign="top" width="65%" colspan=3 style="border: 0px solid #000; font-size: 10px;"></td>	      
	      <td class="tabDetailViewDL" valign="top" width="15%" style="text-align: left; color: #000; font-size: 10px;">&nbsp;Jumlah Refund &nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%" style="border: 0px solid #000; font-size: 10px;">Rp. <input type="text" value="<fmt:formatNumber><c:out value="${claimRefundValue}" /></fmt:formatNumber> " width="20"  style="border: 0px solid #000; color: #000; text-align: right;"></td>	
	    </tr>
	    <tr>      
		  <td class="tabDetailViewDF" valign="top" width="65%" colspan=3 style="border: 0px solid #000; font-size: 10px;"></td>	      
	      <td class="tabDetailViewDL" valign="top" width="15%" style="text-align: left; color: #000; font-size: 10px;">&nbsp;Jumlah Paid To Provider &nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%" style="border: 0px solid #000; font-size: 10px;">Rp. <input type="text" value="<fmt:formatNumber><c:out value="${claimPaidToProviderValue}" /></fmt:formatNumber> " width="20"  style="border: 0px solid #000; color: #000; text-align: right;"></td>	
	    </tr>
					
	</tbody>
</table>
</c:if>
<c:if test="${(batchClaim.batchClaimType.claimTypeId eq 1 )or (batchClaim.batchClaimType.claimTypeId eq 3)}">

<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>

		<tr height="20">
			<td width="6%" nowrap="nowrap" class="listViewThS1" scope="col">
				<img src="images/blank.gif" alt="asd" height="1" width="1">
			</td>		
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				<c:out value="${reimbursementPolicyGroupName}" />
			</td>
		
	
	   			
			<td scope="col" class="listViewThS1" colspan="2" nowrap="nowrap" width="20%">
				<c:out value="${reimbursementPolicyGroupNumber}" />
			</td>
			<td scope="col" class="listViewThS1" colspan="8" nowrap="nowrap" width="20%">
				<c:out value="${reimbursementKantorCabang}" />
			</td>		
		</tr>

		<tr height="20">
			<td width="6%" nowrap="nowrap" class="listViewThS1" scope="col">No.</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Nomor Peserta</td>   			
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="20%">Nama Peserta&nbsp;</td>					
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Nomor Klaim &nbsp;</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Diagnose &nbsp;</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Admission &nbsp;</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Discharge &nbsp;</td>				
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%">Nilai Klaim &nbsp;</td>	   		   		   		   		   		   		   		   		   		   		   		   		   			
		   	<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%">Ditolak &nbsp;</td>				
		   	<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%">Disetujui &nbsp;</td>					
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%">Ex Gratia &nbsp;</td>					
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%">Excess &nbsp;</td>
			<td scope="col" class="listViewThS1" width="8%">Pre Paid Excess &nbsp;</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%">Refund &nbsp;</td>
		</tr>
		
			<c:set var="claimInitialValue" value="0" scope="request" />
			<c:set var="claimFinalValue" value="0"  scope="request" />
			<c:set var="excessValue" value="0" scope="request" />
			<c:set var="claimPaidValue" value="0"  scope="request" />
	
	

	
	<c:forEach items="${reports}" var="claim" varStatus="status" >
		<c:set var="claimItem" value="${claim.claimItems}" />
		
		<tr height="20">
		      <td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border: 0px solid #000;"><c:out value="${status.index+1}" />.</td>
		
		      		
		      	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">					
					<font style="font-size: 10px"><c:out value="${claim.claim.memberId.customerPolicyNumber}" /></font>					
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">					
					<font style="font-size: 10px"><c:out value="${claim.claim.memberId.firstName}" /></font>					
				</td>
		      	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">					
					<font style="font-size: 10px"><c:out value="${claim.claim.claimNumber}" /></font>					
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">					
					<font style="font-size: 10px"><c:out value="${claim.claim.diagnosis1Code}" /></font>					
				</td>		
		      	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
					<font style="font-size: 10px"><c:out value="${claim.claim.admissionDate}" /></font>
				</td>		
		      	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
					<font style="font-size: 10px"><c:out value="${claim.claim.dischargeDate}" /></font>					
				</td>
		      	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right"></td>		
		      	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right"></td>		
		      	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right"></td>
		 		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right"></td>		
		      	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right"></td>
		      	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right"></td>
		    </tr>
			<tr>
		      <td colspan="20" class="listViewHRS1"></td>
		    </tr>
		    
			<tr>
		      <td colspan="20" class="listViewHRS1"></td>
		    </tr>
			<c:forEach items="${claimItem}" var="ci" varStatus="ciStatus">
			<c:set var="claimChargeValue" value="${claimChargeValue+ci.claimItemValue}"></c:set>
			<c:set var="claimApprovedValue" value="${claimApprovedValue+ci.claimItemApprovedValue}"></c:set>
			<c:set var="claimExcessValue" value="${claimExcessValue+ci.excessValue}"></c:set>
			<c:set var="claimRefundValue" value="${claimRefundValue+ci.refund}"></c:set>
			<c:set var="claimPrePaidExcessValue" value="${claimPrePaidExcessValue+ci.prePaidExcess}"></c:set>
			<c:set var="claimExGratiaValue" value="${claimExGratiaValue+ci.claimExGratia}"></c:set>
		
			 <tr height="20">
		      	<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"></td>		      	
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" colspan="2">					
					&nbsp;&nbsp;&nbsp;<font style="font-size: 10px"><c:out value="${ciStatus.index + 1}" />.</font> &nbsp;<font style="font-size: 10px;font-weight: bold;"><b><c:out value="${ci.itemId.itemName}" /></b> &nbsp;&nbsp; <c:out value="${ci.claimItemDescription}" /> </font>					
				</td>
		      	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"></td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"></td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"></td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"></td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
					<font style="font-size: 10px"><fmt:formatNumber><c:out value="${ci.claimItemValue }" /></fmt:formatNumber></font>					
				</td>		
		      	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">					
					<font style="font-size: 10px"><fmt:formatNumber><c:out value="${ci.claimItemValue - ci.claimItemApprovedValue }" /></fmt:formatNumber></font>					
				</td>		
		      	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">					
					<font style="font-size: 10px"><fmt:formatNumber><c:out value="${ci.claimItemApprovedValue}" /></fmt:formatNumber></font>					
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">					
					<font style="font-size: 10px"><fmt:formatNumber><c:out value="${ci.claimExGratia}" /></fmt:formatNumber></font>					
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">					
					<font style="font-size: 10px">
						<c:if test="${ci.claimId.claimTypeId.claimTypeId eq 2}">
							<fmt:formatNumber><c:out value="${ci.excessValue}" /></fmt:formatNumber>
						</c:if>
					</font>					
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">					
					<font style="font-size: 10px">
						<c:if test="${ci.claimId.claimTypeId.claimTypeId eq 2}">
							<fmt:formatNumber><c:out value="${ci.prePaidExcess}" /></fmt:formatNumber>
						</c:if>
					</font>					
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">					
					<font style="font-size: 10px">
						<c:if test="${ci.claimId.claimTypeId.claimTypeId eq 2}">
							<fmt:formatNumber><c:out value="${ci.refund}" /></fmt:formatNumber>
						</c:if>
					</font>					
				</td>
		 		   	
				
		    </tr>
		   <tr height="20">
		      <td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"></td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" colspan="14">					
						&nbsp;&nbsp;&nbsp;<font style="font-size: 10px"><c:if test="${ci.claimItemStatus.caseStatusId eq 4}"> <c:out value="${ci.medicalRemarks}" /></c:if><c:if test="${ci.claimItemStatus.caseStatusId eq 8}"> <c:out value="${ci.benefitCheckRemarks}" /></c:if></font>					
				</td>				
		    </tr>		   
			<tr>
		      <td colspan="20" class="listViewHRS1"></td>
		    </tr>
	
		</c:forEach>
	</c:forEach>
	<tr height="20">
			<td width="6%" nowrap="nowrap" class="listViewThS1" scope="col"></td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%"></td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%"></td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%"></td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%"></td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%"></td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%"></td>
	   		<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%" style="text-align: right;">
				<fmt:formatNumber ><c:out value="${claimChargeValue }" /></fmt:formatNumber>
			</td>			
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%" style="text-align: right;">
				<fmt:formatNumber ><c:out value="${ claimChargeValue - claimApprovedValue}" /></fmt:formatNumber>
			</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%" style="text-align: right;">
				<fmt:formatNumber><c:out value="${claimApprovedValue}" /></fmt:formatNumber>
			</td>			   		   		   		   		   		   		   		   		   		   		   		   		   			
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%"  style="text-align: right;">
				<fmt:formatNumber><c:out value="${claimExGratiaValue}" /></fmt:formatNumber>
			</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%"  style="text-align: right;">				
				<fmt:formatNumber><c:out value="${claimExcessValue}" /></fmt:formatNumber>				
			</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%"  style="text-align: right;">				
				<fmt:formatNumber><c:out value="${claimPrePaidExcessValue}" /></fmt:formatNumber>				
			</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%"  style="text-align: right;">				
				<fmt:formatNumber><c:out value="${claimRefundValue}" /></fmt:formatNumber>
				
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
				  <td class="tabDetailViewDF" valign="top" width="65%" colspan=3 style="border: 0px solid #000; font-size: 10px;"></td>		      
			      <td class="tabDetailViewDL" valign="top" width="15%" style="text-align: left; color: #000; font-size: 10px;">&nbsp;Jumlah Diajukan &nbsp;</td>
			      <td class="tabDetailViewDF" valign="top" width="20%" style="border: 0px solid #000; font-size: 10px;">Rp. <input type="text" value="<fmt:formatNumber ><c:out value="${claimChargeValue}" /></fmt:formatNumber> " width="20"  style="border: 0px solid #000; color: #000; text-align: right;"></td>		
		    </tr>
			<tr>		      
				  <td class="tabDetailViewDF" valign="top" width="65%" colspan=3 style="border: 0px solid #000; font-size: 10px;"></td>		      
			      <td class="tabDetailViewDL" valign="top" width="15%" style="text-align: left; color: #000; font-size: 10px;">&nbsp;Biaya Materai &nbsp;</td>
			      <td class="tabDetailViewDF" valign="top" width="20%" style="border: 0px solid #000; font-size: 10px;">Rp. <input type="text" value="<fmt:formatNumber ><c:out value="${payment.batchClaim.biayaMaterai}" /></fmt:formatNumber> " width="20"  style="border: 0px solid #000; color: #000; text-align: right;"></td>		
		    </tr>	
	    	<tr>		      
				  <td class="tabDetailViewDF" valign="top" width="65%" colspan=3 style="border: 0px solid #000; font-size: 10px;"></td>		      
			      <td class="tabDetailViewDL" valign="top" width="15%" style="text-align: left; color: #000; font-size: 10px;">&nbsp;Pembulatan &nbsp;</td>
			      <td class="tabDetailViewDF" valign="top" width="20%" style="border: 0px solid #000; font-size: 10px;">Rp. <input type="text" value="<fmt:formatNumber ><c:out value="${pembulatan}" /></fmt:formatNumber> " width="20"  style="border: 0px solid #000; color: #000; text-align: right;"></td>		
		    </tr>
	    	<tr>		      
		  		  <td class="tabDetailViewDF" valign="top" width="65%" colspan=3 style="border: 0px solid #000; font-size: 10px;"></td>		      
			      <td class="tabDetailViewDL" valign="top" width="15%" style="text-align: left; color: #000; font-size: 10px;">&nbsp;Jumlah Disetujui &nbsp;</td>
		      	  <td class="tabDetailViewDF" valign="top" width="20%" style="border: 0px solid #000; font-size: 10px;">Rp. <input type="text" value="<fmt:formatNumber><c:out value="${claimApprovedValue+payment.batchClaim.biayaMaterai}" /></fmt:formatNumber> " width="20"  style="border: 0px solid #000; color: #000; text-align: right;"></td>		
		    </tr>
			<tr>		      
			  	  <td class="tabDetailViewDF" valign="top" width="65%" colspan=3 style="border: 0px solid #000; font-size: 10px;"></td>		      
			      <td class="tabDetailViewDL" valign="top" width="15%" style="text-align: left; color: #000; font-size: 10px;">&nbsp;Jumlah Ditolak &nbsp;</td>
			      <td class="tabDetailViewDF" valign="top" width="20%" style="border: 0px solid #000; font-size: 10px;">Rp. <input type="text" value="<fmt:formatNumber><c:out value="${claimChargeValue - claimApprovedValue }" /></fmt:formatNumber> " width="20"  style="border: 0px solid #000; color: #000; text-align: right;"></td>d		
		    </tr>		
		    <tr>      
			  	  <td class="tabDetailViewDF" valign="top" width="65%" colspan=3 style="border: 0px solid #000; font-size: 10px;"></td>	      
		      	  <td class="tabDetailViewDL" valign="top" width="15%" style="text-align: left; color: #000; font-size: 10px;">&nbsp;Jumlah Pre Paid Excecss &nbsp;</td>
		          <td class="tabDetailViewDF" valign="top" width="20%" style="border: 0px solid #000; font-size: 10px;">Rp. <input type="text" value="<fmt:formatNumber><c:out value="${claimPrePaidExcessValue}" /></fmt:formatNumber> " width="20"  style="border: 0px solid #000; color: #000; text-align: right;"></td>	
		    </tr>				
		    <tr>      
			  	  <td class="tabDetailViewDF" valign="top" width="65%" colspan=3 style="border: 0px solid #000; font-size: 10px;"></td>	      
		      	  <td class="tabDetailViewDL" valign="top" width="15%" style="text-align: left; color: #000; font-size: 10px;">&nbsp;Jumlah Refund &nbsp;</td>
		          <td class="tabDetailViewDF" valign="top" width="20%" style="border: 0px solid #000; font-size: 10px;">Rp. <input type="text" value="<fmt:formatNumber><c:out value="${claimRefundValue}" /></fmt:formatNumber> " width="20"  style="border: 0px solid #000; color: #000; text-align: right;"></td>	
		    </tr>
		</tbody>
	</table>
</c:if>	
</form>
	
