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


<br />
<center>
<table class="tabDetailView" border="0" cellpadding="0" cellspacing="0" width="90%" >
  <tbody>
    
		<tr>
	         	      <td class="tabDetailViewDL" valign="top" width="40%" style="text-align: left; color: #000; font-size: 10px;" colspan="2">Tanggal : &nbsp;&nbsp;<c:out value="${invoice.invoiceDate }" /></td>

	      <td class="tabDetailViewDL" valign="top" width="25%" style="text-align: left; color: #000; font-size: 10px;">&nbsp;No Tagihan / Invoice No. &nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="25%" style="border: 0px solid #000; font-size: 10px; color: #000;"><c:out value="${invoice.invoiceNumber }" /></td>
	     
	
	    </tr>
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;" colspan="2">Kepada Yth,&nbsp;</td>
		  
	      <td class="tabDetailViewDL" valign="top" width="25%" style="text-align: left; color: #000; font-size: 10px;">&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="25%" style="border: 0px solid #000; font-size: 10px; color: #000;"></td>
	
	    </tr>		
		
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;" colspan="2"><c:out value="${invoice.memberGroupId.groupName}" /></td>
	      <td class="tabDetailViewDL" valign="top" width="25%" style="text-align: left; color: #000;  font-size: 10px;">&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="25%" style="border: 0px solid #000; font-size: 10px; color: #000;"></td>
	
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
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;" colspan="2"></td>
		  
	      <td class="tabDetailViewDL" valign="top" width="25%" style="text-align: left; color: #000; font-size: 10px;">&nbsp; &nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="25%" style="border: 0px solid #000; font-size: 10px; color: #000;"></td>
	    </tr>	
	    	<tr>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;" colspan="2"></td>
		  
	      <td class="tabDetailViewDL" valign="top" width="25%" style="text-align: left; color: #000; font-size: 10px;">&nbsp; &nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="25%" style="border: 0px solid #000; font-size: 10px; color: #000;"></td>
	    </tr>	
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 13px; font-weight: bold;" colspan="2">UP:  <c:out value="${invoice.memberGroupId.contactPerson}" />&nbsp;</td>
		  
	      <td class="tabDetailViewDL" valign="top" width="25%" style="text-align: left; color: #000; font-size: 10px;">&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="25%" style="border: 0px solid #000; font-size: 10px;"></td>
	
	    </tr>	
	    	<tr>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;" colspan="2"></td>
		  
	      <td class="tabDetailViewDL" valign="top" width="25%" style="text-align: left; color: #000; font-size: 10px;">&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="25%" style="border: 0px solid #000; font-size: 10px;"></td>
	
	    </tr>		
					
	    	<tr>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 13px; font-weight: bold;" colspan="2">Hal:  Tagihan Kelebihan Klaim / Excess of Claim (EOC)&nbsp;</td>
		  
	      <td class="tabDetailViewDL" valign="top" width="25%" style="text-align: left; color: #000; font-size: 10px;">&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="25%" style="border: 0px solid #000; font-size: 10px;"></td>
	
	    </tr>		
				
				
		
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;" colspan="2"></td>
		  
	      <td class="tabDetailViewDL" valign="top" width="25%" style="text-align: left; color: #000; font-size: 10px;">&nbsp; &nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="25%" style="border: 0px solid #000; font-size: 10px; color: #000;"></td>
	    </tr>	
	    		
	</tbody>
</table>
</center>

<br />

<c:forEach items="${excessCollection}" var="excess">
<center>
<br />
<table  cellspacing="0" cellpadding="0" width="95%" >
	<tbody>
	<tr>
      <td colspan="20" align="right">  
            
            </td>
    </tr>



	 <tr onMouseOver="" onMouseOut="" onMouseDown="" height="20">						   		   		   		   		   		
		<td  class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" width="20%" style="border-left: 1px solid #000000; border-top: 1px solid #000000;">
			Nama Karyawan						
		</td>				   		   	   		   		   		   		   		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" width="30%" style="text-align: left; border-top: 1px solid #000000;">
			<c:out value="${excess.excessCharge.claimId.memberId.parentMember.firstName}" />		
		</td>		
		   		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" width="15%" style="border-top: 1px solid #000000;">			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" width="35%" style="text-align: left; border-top: 1px solid #000000; border-right: 1px solid #000000;">
		</td>
    </tr>	
	 <tr onMouseOver="" onMouseOut="" onMouseDown="" height="20">						   		   		   		   		   		
		<td  class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" width="20%" style="border-left: 1px solid #000000; border-top: 1px solid #000000;">
			Nama Pasien						
		</td>				   		   	   		   		   		   		   		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" width="30%" style="text-align: left; border-top: 1px solid #000000;">
			<c:out value="${excess.excessCharge.claimId.memberId.firstName}" />		
		</td>		
		   		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" width="15%" style="border-top: 1px solid #000000;">			
			No. Klaim						
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" width="35%" style="text-align: left; border-top: 1px solid #000000; border-right: 1px solid #000000;">
			<c:out value="${excess.excessCharge.claimId.claimNumber}" />						
		</td>
    </tr>     
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	<tr onMouseOver="" onMouseOut="" onMouseDown="" height="20">						   		   		   		   		   		
		<td  class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" width="20%" style="border-left: 1px solid #000000;">
			Kode & Nama Rumah Sakit / Klinik						
		</td>				   		   	   		   		   		   		   		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" width="30%" style="text-align: left; ">
			<c:out value="${excess.excessCharge.claimId.providerId.providerName}" />		
		</td>		
		   		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" width="15%">			
			No. Batch						
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" width="35%" style="text-align: left; border-right: 1px solid #000000;">
			<c:out value="${excess.excessCharge.claimId.batchClaimId.batchClaimNumber}" />						
		</td>
    </tr>     
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	<tr onMouseOver="" onMouseOut="" onMouseDown="" height="20">						   		   		   		   		   		
		<td  class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" width="20%" style="border-left: 1px solid #000000;">
			Tanggal Berobat						
		</td>				   		   	   		   		   		   		   		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" width="30%" style="text-align: left; ">
			<c:out value="${excess.excessCharge.claimId.admissionDate}" />		
		</td>		
		   		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" width="15%">			
			No. Peserta						
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" width="35%" style="text-align: left; border-right: 1px solid #000000;">
			<c:out value="${excess.excessCharge.claimId.memberId.customerPolicyNumber}" />						
		</td>
    </tr>     
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	<tr onMouseOver="" onMouseOut="" onMouseDown="" height="20">						   		   		   		   		   		
		<td  class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" width="20%" style="border-left: 1px solid #000000; border-bottom: 1px solid #000000;">
			Tanggal Terima di Asuransi						
		</td>				   		   	   		   		   		   		   		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" width="30%" style="text-align: left; border-bottom: 1px solid #000000; ">
			<c:out value="${excess.excessCharge.claimId.batchClaimId.batchClaimDate}" />		
		</td>		
		   		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" width="15%" style="border-bottom: 1px solid #000000;">			
			Kode Diagnosis						
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" width="35%" style="text-align: left;  border-right: 1px solid #000000; border-bottom: 1px solid #000000;">
			<textarea rows="3" cols="50"><c:out value="${excess.excessCharge.claimId.diagnosisId.diagnosisCode}" /> - <c:out value="${excess.excessCharge.claimId.diagnosisId.diagnosisName}" /></textarea>
		</td>
    </tr>     
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	</table>
	<br />
	<table  cellspacing="0" cellpadding="0" width="95%">
	<tbody>
	<tr>
      <td colspan="20" align="right">  
            
            </td>
    </tr>



	 <tr onMouseOver="" onMouseOut="" onMouseDown="" height="20">						   		   		   		   		   		
		<td  class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" width="30%" style="border: 1px solid #000000; text-align: center;">
			Uraian					
		</td>				   		   	   		   		   		   		   		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" width="15%" style="border: 1px solid #000000; text-align: center; ">
			Biaya (Rp.)	
		</td>		
		   		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" width="15%" style="border: 1px solid #000000; text-align: center";>			
			Manfaat (Rp.)						
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" width="15%" style="border: 1px solid #000000; text-align: center;">	
			Ekses (Rp.)						
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" width="35%" style="border: 1px solid #000000; text-align: center;">	
			Keterangan						
		</td>
    </tr>    
    
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	<c:forEach items="${excess.claimItemList}" var="item"> 
	 <tr onMouseOver="" onMouseOut="" onMouseDown="" height="20">						   		   		   		   		   		
		<td  class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" width="30%" style="border: 1px solid #000000; text-align: left;">
			${item.itemId.itemName}					
		</td>				   		   	   		   		   		   		   		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" width="15%" style="border: 1px solid #000000; text-align: center; ">
			<fmt:formatNumber>${item.claimItemValue }</fmt:formatNumber>	
		</td>		
		   		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" width="15%" style="border: 1px solid #000000; text-align: center";>			
			<fmt:formatNumber>${item.claimItemApprovedValue }</fmt:formatNumber>						
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" width="15%" style="border: 1px solid #000000; text-align: center;">	
			<fmt:formatNumber>${item.excessValue }</fmt:formatNumber>
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" width="35%" style="border: 1px solid #000000; text-align: left; table-layout: fixed;">	
			${item.benefitCheckRemarks }						
		</td>
    </tr>     
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
    </c:forEach>
	</table>
</center>
</c:forEach>




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
