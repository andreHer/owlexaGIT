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


<h3 style="font-size: 18px; color: #000000; font-weight: bold; text-align: left;">Rincian Tagihan Kelebihan Klaim / Detail of Excess of Claim (EOC) Invoice</h3>

<br />
<table class="tabDetailView" border="0" cellpadding="0" cellspacing="0" width="100%" >
  <tbody>
    
	
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;">No Polis&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 10px;"><input type="text" value="<c:out value="${invoice.memberGroupId.policyNumber}" />" size="35" style="border: 1px solid #000; color: #000;"></td>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;">&nbsp;Invoice Ref No. &nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="25%" style="border: 0px solid #000; font-size: 10px;"><input type="text" value="<c:out value="${invoice.invoiceNumber}" />" size="55" style="border: 1px solid #000; color: #000;"></td>
	
	    </tr>
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;">Nama Polis &nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 10px;"><input type="text" value="<c:out value="${invoice.memberGroupId.groupName}" />" size="35" style="border: 1px solid #000; color: #000;"></td>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;">&nbsp;Currency</td>
	      <td class="tabDetailViewDF" valign="top" width="25%" style="border: 0px solid #000; font-size: 10px;"><input type="text" value="<c:out value="${currency}" />" size="15" style="border: 1px solid #000; color: #000;"></td>
	     
	    </tr>		
		
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;">Periode Polis &nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 10px;"><input type="text" value="<c:out value="${invoice.memberGroupId.effectiveDate}" /> s/d <c:out value="${invoice.memberGroupId.expireDate}" />" size="35" style="border: 1px solid #000; color: #000;"></td>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000;  font-size: 10px;">&nbsp;Periode Approve &nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="25%" style="border: 0px solid #000; font-size: 10px; color: #000;"><input type="text" value="<c:out value="${approvePeriode}" />" size="35" style="border: 1px solid #000; color: #000;"> &nbsp;</td>
	     
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
  			
				   		   		   			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="3%" style="text-align: center; border: 1px solid #000;">
				No. &nbsp;</td>

	<td scope="col" class="listViewThS1" nowrap="nowrap" width="13%" style="text-align: center; border: 1px solid #000;" >
				Nama Peserta &nbsp;</td>

				<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%" style="text-align: center; border: 1px solid #000;">
				No. Peserta &nbsp;</td>
					
			
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%" style="text-align: center; border: 1px solid #000;">
				Nama Pasien &nbsp;</td>
				
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%" style="text-align: center; border: 1px solid #000;">
				No Pasien &nbsp;</td>
		
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%" style="text-align: center; border: 1px solid #000;">
				No. Batch&nbsp;</td>
		
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="50%" style="text-align: center; border: 1px solid #000;">
				No. Klaim&nbsp;</td>
		
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%" style="text-align: center; border: 1px solid #000;">
				Excess of Claim (Rp.) &nbsp;</td>
		
		
	</tr>


	<c:forEach items="${excessCollection}" var="excess" varStatus="status" >
	<%	if (i % 2 == 0) {
			rowclass = "col1";
		} else if (i % 2 != 0) {
			rowclass= "col2";
		}
		i++;
	%>
	 <tr height="20">
      <td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" height="9" style="border-left: 1px solid #000000;"> 
     	 <c:out value="${status.index+1}" />
      </td>
		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border-left: 1px solid #000000;">
			<c:out value="${excess.claimId.memberId.parentMember.firstName}" />
				
			
		</td>
		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="border-left: 1px solid #000000;">			
			<c:out value="${excess.claimId.memberId.parentMember.customerPolicyNumber}" />
			
		</td>
					   		   		   		   		   		
		<td  class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"  style="border-left: 1px solid #000000;">
			<c:out value="${excess.claimId.memberId.firstName}" />
				
			
		</td>		
		   		   	   		   		   		   		   		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: center; border-left: 1px solid #000000; border-right: 1px solid #000;">
			<c:out value="${excess.claimId.memberId.customerPolicyNumber}" />

			
		</td>		
		   		
		   			   		   		   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${excess.claimId.batchClaimId.batchClaimNumber}" />
				
			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border-left: 1px solid #000000; border-right: 1px solid #000; text-align:right;">
			<c:out value="${excess.claimId.claimNumber}" />
				
			
		</td>		   		   		   		   		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border-left: 1px solid #000000; border-right: 1px solid #000; text-align:right;">
			<fmt:formatNumber><c:out value="${excess.excessChargeValue}" /></fmt:formatNumber>
				
			
		</td>			   		   		   		   		
					   		   		
			
    </tr>
	
      
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	
	</c:forEach>
	
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
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;"></td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 10px;"></td>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;"></td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 10px;"></td>
	      <td class="tabDetailViewDL" valign="top" width="10%" style="text-align: left; color: #000;  font-size: 12px; font-weight: bold;">&nbsp;Grand Total &nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="25%" style="border: 0px solid #000; font-size: 10px; color: #000; text-align: right;"><input type="text" style="border: 1px solid; text-align: right; font-size: 13px; font-weight: bold; color: #000;" value="<fmt:formatNumber><c:out value="${invoice.invoiceValue}" /></fmt:formatNumber>" size="35" style="border: 1px solid #000; color: #000;"> &nbsp;</td>
	     
	    </tr>		

			
		
	
			
	
				
	</tbody>
</table>