<%@page import="java.util.Collection"%>
<%@page import="com.ametis.cms.datamodel.Claim"%><%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<head>
	<link rel="stylesheet" type="text/css" media="all" href="css/style.css" />
</head>

<%
String rowclass = "";
int i=0;

%>

<table class="tabDetailView" border="0" cellpadding="0" cellspacing="0" width="100%" >
  <tbody>    
		<tr>
	      <td class="tabDetailViewDF" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;"></td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 10px;">&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="10%" style="text-align: left; color: #000; font-size: 10px;"></td>
	      <td class="tabDetailViewDF" valign="top" width="25%" style="text-align: right; border: 0px solid #000; font-size: 10px;"><img height="70" src="images/logo/<c:out value="${configuration.companyCode}" />.png"></td>
	
	    </tr>
	
		
			
	</tbody>
</table>
<h3 style="font-size: 18px; color: #000000; font-weight: bold; text-align: center">LAPORAN COST CONTAINMENT</h3>

<br />

<br />

<table class="tabDetailView" border="0" cellpadding="0" cellspacing="0" width="100%" >
  <tbody>
		<tr>
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px; text-align: left; color: #000; font-size: 12px;">Nama&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px ; font-size: 12px; color: #000;"><c:out value="${myCase.memberId.firstName}" /></td>
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px; text-align: left; color: #000; font-size: 10px;">&nbsp; &nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="25%" style="border: 0px ; font-size: 10px;"></td>
	
	    </tr>
		<tr>
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px; text-align: left; color: #000; font-size: 12px;">No Guarantee Letter &nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px; font-size: 12px; color: #000;"><c:out value="${myCase.caseNumber}" /></td>
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px; text-align: left; color: #000; font-size: 10px;">&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="25%" style="border: 0px; font-size: 10px;"></td>
	     
	    </tr>		
		
		<tr>
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px; text-align: left; color: #000; font-size: 12px;">Nomor Peserta &nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px; font-size: 12px; color: #000; "><c:out value="${myCase.memberId.customerPolicyNumber}" />  </td>
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px; text-align: left; color: #000;  font-size: 10px;"></td>
	      <td class="tabDetailViewDF" valign="top" width="25%" style="border: 0px; font-size: 10px; color: #000;"></td>
	     
	    </tr>
	    <tr>
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px; text-align: left; color: #000; font-size: 12px;">Perusahaan / Group &nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px; font-size: 12px; color: #000;"><c:out value="${myCase.memberId.memberGroupId.groupName}" /></td>
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px; text-align: left; color: #000;  font-size: 10px;"></td>
	      <td class="tabDetailViewDF" valign="top" width="25%" style="border: 0px; font-size: 10px; color: #000;"></td>
	     
	    </tr>	
	    <tr>
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px; text-align: left; color: #000; font-size: 12px;">Tanggal Perawatan &nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px; font-size: 12px; color: #000;"><c:out value="${myCase.caseStartTime}" /> s/d <c:out value="${myCase.caseEndTime}" /></td>
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px; text-align: left; color: #000;  font-size: 10px;"></td>
	      <td class="tabDetailViewDF" valign="top" width="25%" style="border: 0px; font-size: 10px; color: #000;"></td>
	     
	    </tr>
   	    <tr>
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px; text-align: left; color: #000; font-size: 12px;">Plan &nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px; font-size: 12px; color: #000;"><c:out value="${claim.caseCategoryId.caseCategoryCode}" /><c:out value="${claim.productClass}" /></td>
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px; text-align: left; color: #000;  font-size: 10px;"></td>
	      <td class="tabDetailViewDF" valign="top" width="25%" style="border: 0px; font-size: 10px; color: #000;"></td>
	     
	    </tr>		
	    <tr>
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px; text-align: left; color: #000; font-size: 12px;">Room And Board &nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px; font-size: 12px; color: #000;"><c:out value="${myCase.roomAndBoard}" /></td>
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px; text-align: left; color: #000;  font-size: 10px;"></td>
	      <td class="tabDetailViewDF" valign="top" width="25%" style="border: 0px; font-size: 10px; color: #000;"></td>
	     
	    </tr>			
	    			
	</tbody>
</table>

<br />
<table  cellspacing="0" cellpadding="0" >
	<tbody>
	
	<tr height="20">
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="3%" style="text-align: center; border: 1px solid #000; font-size: 10px;">
			No. &nbsp;</td>
		<td scope="col" class="listViewThS1"  width="10%" style="text-align: center; border: 1px solid #000;font-size: 10px;">
			Date &nbsp;</td>			
		<td scope="col" class="listViewThS1"  width="10%" style="text-align: center; border: 1px solid #000;font-size: 10px;">
			Item Name &nbsp;</td>	
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="7%" style="text-align: center; border: 1px solid #000;font-size: 10px;">
			Item Value&nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="7%" style="text-align: center; border: 1px solid #000;font-size: 10px;">
			Substitution Item&nbsp;</td>		
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="7%" style="text-align: center; border: 1px solid #000;font-size: 10px;">
			Substitution Value &nbsp;</td>	
		<td scope="col" class="listViewThS1" width="25%" style="text-align: center; border: 1px solid #000;font-size: 10px;">
			Cost Containment&nbsp;</td>

		
	</tr>


	<c:forEach items="${costContainments}" var="ci" varStatus="status" >
	<%	if (i % 2 == 0) {
			rowclass = "col1";
		} else if (i % 2 != 0) {
			rowclass= "col2";
		}
		i++;
	%>
	 <tr height="20">
			<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" height="9" style="border-left: 1px solid #000000;font-size: 10px;"> 
			 <c:out value="${status.index+1}" />
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border-left: 1px solid #000000; text-align:left;font-size: 10px;">
				<c:out value="${ci.costContainmentDate}" />
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border-left: 1px solid #000000; text-align:left;font-size: 10px;">
				<c:out value="${ci.itemId.itemName}" />
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border-left: 1px solid #000000;  text-align:right;font-size: 10px;">
				<fmt:formatNumber><c:out value="${ci.initialItemCost}" /></fmt:formatNumber>&nbsp;
			</td>			   		   		   		   		
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border-left: 1px solid #000000;  text-align:right;font-size: 10px;">
				<c:out value="${ci.substitutionItemId.itemName}" />
			</td>		   		   		   		   		
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="border-left: 1px solid #000000;  text-align:right;font-size: 10px;">
				<fmt:formatNumber><c:out value="${ci.totalSubstitutionValue}" /></fmt:formatNumber>&nbsp;
			</td>			   		   		   		   		
			<td class="oddListRowS1" bgcolor="#e7f0fe"  valign="top" style="border-left: 1px solid #000000; border-right: 1px solid #000; text-align:right;font-size: 10px;">
				&nbsp;<fmt:formatNumber><c:out value="${ci.costContainmentValue}" /></fmt:formatNumber>
			</td>			   		   		
			
    	</tr>
	</c:forEach>
	
	
	<tr height="20">
		<td scope="col" class="listViewThS1" colspan=6 width="3%" style="text-align: center; border: 1px solid #000; font-size: 10px;"></td>
			
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="7%" style="text-align: right; border: 1px solid #000;font-size: 10px;">
			<fmt:formatNumber><c:out value="${myCase.costContainmentValue}" /></fmt:formatNumber>&nbsp;</td>	
		

		
	</tr>
	</table>

<table class="tabDetailView" border="0" cellpadding="0" cellspacing="0" width="100%" >
  <tbody>
		<tr>
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px; text-align: left; color: #000; font-size: 12px;">Diagnosa Akhir : &nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px ; font-size: 12px; color: #000;"><c:out value="${myCase.claimId.diagnosisId.diagnosisCode}" /> - <c:out value="${myCase.claimId.diagnosisId.description}" /></td>
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px; text-align: left; color: #000; font-size: 10px;">&nbsp; &nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="25%" style="border: 0px ; font-size: 10px;"></td>
	
	    </tr>	
	    <tr>
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px; text-align: left; color: #000; font-size: 12px;">Catatan : &nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px ; font-size: 12px; color: #000;"></td>
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px; text-align: left; color: #000; font-size: 10px;">&nbsp; &nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="25%" style="border: 0px ; font-size: 10px;"></td>
	
	    </tr>				
	    			
	</tbody>
</table>
<br />

<table class="tabDetailView" border="0" cellpadding="0" cellspacing="0" width="100%" >
  <tbody>
		<tr>
	      <td colspan=2 class="tabDetailViewDF" valign="top" width="10%" style="border: 0px; text-align: left; color: #000; font-size: 12px;">Jakarta, <c:out value="${currentTime}" /> &nbsp;</td>		  
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px; text-align: left; color: #000; font-size: 10px;">&nbsp; &nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="25%" style="border: 0px ; font-size: 10px;"></td>	
	    </tr>    			
	</tbody>
</table>
<br />
<br />
<br />
<br />

<table class="tabDetailView" border="0" cellpadding="0" cellspacing="0" width="100%" >
  <tbody>
		<tr>
	      <td colspan=2 class="tabDetailViewDF" valign="top" width="25%" style="border: 0px; text-align: left; color: #000; font-size: 12px;"><c:out value="${myCase.caseHandler}" /> &nbsp;</td>		  
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px; text-align: left; color: #000; font-size: 10px;">&nbsp; &nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px ; font-size: 10px;"></td>	
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px ; font-size: 10px;"></td>
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px ; font-size: 10px;"></td>
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px ; font-size: 10px;"></td>
	      <td class="tabDetailViewDF" valign="top" width="25%" style="border: 0px ; font-size: 10px;"></td>
	    </tr>  
	    <tr>
	      <td colspan=2 class="tabDetailViewDF" valign="top" width="10%" style="border: 0px; text-align: left; text-decoration: underline; color: #000; font-size: 12px;">Case Management</td>		  
	      <td class="tabDetailViewDF" valign="top" width="5%" style="border: 0px; text-align: left; color: #000; font-size: 10px;">&nbsp; &nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="5%" style="border: 0px ; font-size: 10px;"></td>	
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px ; text-decoration: underline; color: #000; font-size: 12px;"></td>
	      <td class="tabDetailViewDF" valign="top" width="10%" style="border: 0px ; font-size: 10px;"></td>
	      <td class="tabDetailViewDF" valign="top" width="5%" style="border: 0px ; font-size: 10px;"></td>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px ; text-decoration: underline; color: #000; font-size: 12px;"></td>
	      
	    </tr>    			
	</tbody>
</table>
