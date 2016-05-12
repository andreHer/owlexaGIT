
<%@page import="java.util.Collection"%>
<%@page import="com.ametis.cms.datamodel.ClaimItem"%>
<%@page import="java.util.Iterator"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ page import="java.util.Vector" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<script type='text/javascript' src='dwr/interface/AJAXClaimItemService.js'></script>
<script type='text/javascript' src='dwr/engine.js'></script>
<script type='text/javascript' src='dwr/util.js'></script>

<%
String navigation = WebUtil.getAttributeString(request,"navigation","");
%>

<%

String alert = (String) request.getAttribute("alert");
int index = 0;
int totalIndex = 0;
int totalItem = WebUtil.getAttributeInteger(request,"totalItem",0).intValue();


Vector verifyDescVector = (Vector)request.getAttribute ("claimDescVector") == null ? new Vector() : (Vector) request.getAttribute("claimDescVector");


int count = 0;
int countSet = 0;
int i = 0;



if (alert != null && !alert.trim().equals("")) {%>
	<div id="warning" align="center">
		<c:out value="${alert}"></c:out>
	</div>
	<%}%>

<%

%>
<!-- Search Container Start -->

<form name="form1" action="claimitem" method="POST">
<input type="hidden" name="navigation" value="saveclaimitem">
<input type="hidden" name="arah" value="">
<input type="hidden" name="claimId" value="<c:out value="${claimId}" />">
<input type="hidden" name="index" value="<c:out value="${index}" />">


<c:set var="claimChargeValue" value="0" scope="request" />
<c:set var="claimApprovedValue" value="0"  scope="request" />
<c:set var="claimExcessValue" value="0" scope="request" />
<c:set var="claimExGratiaValue" value="0" scope="request" />
<br />
<table class="tabForm" cellspacing="0" cellpadding="0">
	<tbody>
	
	<tr height="20">
		<td width="4%" nowrap="nowrap" class="listViewThS1" scope="col">
			<img src="images/blank.gif" alt="asd" height="1" width="1">No.</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">Item Name&nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">Benefit Rule &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="2%">Qty &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%">Charges  &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%">Covered  &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%">Excess  &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%">Approved  &nbsp;</td>
		<td scope="col" class="listViewThS1" width="8%">Pre-Paid Excess  &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%">Refund  &nbsp;</td>
		<td scope="col" class="listViewThS1" width="8%">Paid To Provider  &nbsp;</td>
		<td scope="col" class="listViewThS1" width="8%">Fault Excess Provider  &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">Description &nbsp;</td>
	</tr>

	<c:set var="checkItemsLength" value="${fn:length(checkItems)}"/>
	<c:forEach items="${checkItems}" var="item" varStatus="status">
		<input type="hidden" id="item<%=i%>Id" name="claimItemId" value="<c:out value="${item.claimItemId}" />">
		 <tr height="20">
	      <td class="oddListRowS1" align="center" bgcolor="#4f85c6" nowrap="nowrap" valign="center"><c:out value="${status.index+1}" /></td>
	
			<td class="oddListRowS1" bgcolor="#4f85c6" nowrap="nowrap" valign="center">
	      		
	      		<c:out value="${item.itemId.itemName }" />
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe"  valign="center">
				<c:if test="${item.memberBenefitId.benefitLimit eq -1.0 or item.memberBenefitId.isAsCharge}">AS CHARGE</c:if>
				<c:if test="${item.memberBenefitId.benefitLimit ne -1.0 and not(item.memberBenefitId.isAsCharge)}">
					<c:if test="${item.memberBenefitId.benefitLimit gt 0.0 }">
						<fmt:formatNumber><c:out value="${item.memberBenefitId.benefitLimit}" /></fmt:formatNumber>
					</c:if>
					<c:if test="${item.memberBenefitId.benefitLimit eq 0.0 }">
						<fmt:formatNumber><c:out value="${item.memberBenefitId.reimbursementBenefitLimit}" /></fmt:formatNumber>
					</c:if>
				</c:if> &nbsp; / &nbsp;
				<c:if test="${item.memberBenefitId.benefitCalculationMethod.benefitUsageTypeId eq 1}">
				OCCURANCE
			</c:if>
			<c:if test="${item.memberBenefitId.benefitCalculationMethod.benefitUsageTypeId eq 2}">
				PER DISABILITY
			</c:if>			
			<c:if test="${item.memberBenefitId.benefitCalculationMethod.benefitUsageTypeId eq 3}">
				ANNUAL LIMIT
			</c:if>
			<c:if test="${item.memberBenefitId.benefitCalculationMethod.benefitUsageTypeId eq 4}">
				MAX OCCUR PER CASE
			</c:if>
			<c:if test="${item.memberBenefitId.benefitCalculationMethod.benefitUsageTypeId eq 5}">
				MAX DAILY
			</c:if>		
			</td>  		 	   		   		
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="center" align="right">
				<fmt:formatNumber><c:out value="${item.claimItemAmount }" /></fmt:formatNumber>
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="center" align="right">
				<fmt:formatNumber> <c:out value="${item.claimItemValue}" /> </fmt:formatNumber>
			</td>  		   	
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="center" align="right">
				<fmt:formatNumber> <c:out value="${item.claimItemCoveredValue}" /> </fmt:formatNumber>
			</td>  	 
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="center" align="right">
				<fmt:formatNumber> <c:out value="${item.excessValue}" /> </fmt:formatNumber>
			</td>  	 
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="center" align="right" >
				<input type="text" size="12" id="approvedValue<c:out value="${item.claimItemId}" />" name="approvedValues" onchange="javascript:hitungBenefit()" style="text-align: right;" class="input2" value="<fmt:formatNumber pattern="#.00"><c:out value="${item.claimItemCoveredValue }" /></fmt:formatNumber>" />
				<input type="hidden"  id="initApprovedValue<c:out value="${item.claimItemId}" />" name="initApprovedValues"  value="<fmt:formatNumber pattern="#.00"><c:out value="${item.claimItemCoveredValue }" /></fmt:formatNumber>" />
				<input type="hidden"  id="initChargeValue<c:out value="${item.claimItemId}" />" name="initChargeValues"  value="<fmt:formatNumber pattern="#.00"><c:out value="${item.claimItemValue }" /></fmt:formatNumber>" />
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="center" align="right">								
				<c:choose>
				   <c:when test="${item.prePaidExcess != null && item.prePaidExcess != 0}">
						<input type="text" size="12" id="prePaidExcessValue<c:out value="${status.index}" />" name="prePaidExcessValues"  onchange="javascript:autoHitungTotal('prepaid')" style="text-align: right;" class="input2" value="<fmt:formatNumber pattern="#.00"><c:out value="${item.prePaidExcess}" /></fmt:formatNumber>" />
				   </c:when>							
				   <c:otherwise> 
				   		<input type="text" size="12" id="prePaidExcessValue<c:out value="${status.index}" />" name="prePaidExcessValues"  onchange="javascript:autoHitungTotal('prepaid')" style="text-align: right;" class="input2" value="<fmt:formatNumber ><c:out value="0" /></fmt:formatNumber>" />
				   </c:otherwise>
				</c:choose>
			</td>  	 
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="center" align="right">
				<c:choose>
				   <c:when test="${item.refund != null}">
						<input type="text" size="12" id="refundValue<c:out value="${status.index}" />" name="refundValues" onchange="javascript:autoHitungTotal('refund')"  style="text-align: right;" class="input2" value="<fmt:formatNumber pattern="#.00"><c:out value="${item.refund }" /></fmt:formatNumber>" />
				   </c:when>							
				   <c:otherwise>
				   		<input type="text" size="12" id="refundValue<c:out value="${status.index}" />" name="refundValues" onchange="javascript:autoHitungTotal('refund')"  style="text-align: right;" class="input2" value="<fmt:formatNumber ><c:out value="0" /></fmt:formatNumber>" />
				   </c:otherwise>
				</c:choose>
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="center" align="right">
				<c:choose>
				   <c:when test="${item.paidToProvider != null}">
						<input type="text" size="12" id="paidToProviderValue<c:out value="${status.index}" />" name="paidToProviderValues"  onchange="javascript:autoHitungTotal('paidtoprovider')" style="text-align: right;" class="input2" value="<fmt:formatNumber pattern="#.00"><c:out value="${item.paidToProvider }" /></fmt:formatNumber>" />
				   </c:when>							
				   <c:otherwise>
				   		<input type="text" size="12" id="paidToProviderValue<c:out value="${status.index}" />" name="paidToProviderValues"  onchange="javascript:autoHitungTotal('paidtoprovider')" style="text-align: right;" class="input2" value="<fmt:formatNumber ><c:out value="0" /></fmt:formatNumber>" />
				   </c:otherwise>
				</c:choose>
			</td>  	 
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="center" align="right">
				<c:choose>
				   <c:when test="${item.faultExcessProvider != null}">
						<input type="text" size="12" id="faultExcessProviderValue<c:out value="${status.index}" />" name="faultExcessProviderValues"  onchange="javascript:autoHitungTotal('faultexcess')" style="text-align: right;" class="input2" value="<fmt:formatNumber pattern="#.00"><c:out value="${item.faultExcessProvider }" /></fmt:formatNumber>" />
				   </c:when>							
				   <c:otherwise>
				   		<input type="text" size="12" id="faultExcessProviderValue<c:out value="${status.index}" />" name="faultExcessProviderValues"  onchange="javascript:autoHitungTotal('faultexcess')" style="text-align: right;" class="input2" value="<fmt:formatNumber ><c:out value="0" /></fmt:formatNumber>" />
				   </c:otherwise>
				</c:choose>
			</td>  	  	   	   	 
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<textarea class="textarea2" cols="30" rows="2" name="description"><c:out value="${item.benefitCheckRemarks}" /></textarea>
			</td>
	    </tr>
	   
		<tr>
	      <td colspan="20" class="listViewHRS1"></td>
	    </tr>
	</c:forEach>
	<tr height="20">
		<td width="4%" nowrap="nowrap" class="listViewThS1" scope="col"></td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">Outer Benefit Limit &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="16%" colspan=2 align="right" style="text-align: right;">
			<input type="text" id="totalOuterLimitId" value="<fmt:formatNumber><c:out value="${outerBenefitLimit}" /></fmt:formatNumber>" readonly="readonly" style="text-align: right;">
		 </td>
		 		
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">
		     <input type="text" name="totalChargesBenefit" id="totalChargesLimit" value="<fmt:formatNumber><c:out value="${totalCharges}" /></fmt:formatNumber>" readonly="readonly" style="text-align: right;">
		</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%" align="right" style="text-align: right;">
			 <input type="text" name="totalCoveredBenefit" id="totalCoveredLimit" value="<fmt:formatNumber><c:out value="${totalCovered}" /></fmt:formatNumber>" readonly="readonly" style="text-align: right;">
		</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%" align="right" style="text-align: right;">
			 <input type="text" name="totalExcessBenefit" id="totalExcessLimit" value="<fmt:formatNumber><c:out value="${totalExcess}" /></fmt:formatNumber>" readonly="readonly" style="text-align: right;">
		</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%" align="right" style="text-align: right;">
			 <input type="text" name="totalApprovedBenefit" id="totalApprovedLimit" value="<fmt:formatNumber><c:out value="${totalApproved}" /></fmt:formatNumber>" readonly="readonly" style="text-align: right;">
		</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%" align="right" style="text-align: right;">
			 <input type="text" name="totalPrePaidExcesss" id="totalPrePaidExcess" value="<fmt:formatNumber><c:out value="${totalPrepaidExcess}" /></fmt:formatNumber>" readonly="readonly" style="text-align: right;">
		</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%" align="right" style="text-align: right;">
			 <input type="text" name="totalRefunds" id="totalRefund" value="<fmt:formatNumber><c:out value="${totalRefund}" /></fmt:formatNumber>" readonly="readonly" style="text-align: right;">
		</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%" align="right" style="text-align: right;">
			 <input type="text" name="totalPaidToProviders" id="totalPaidToProvider" value="<fmt:formatNumber><c:out value="${totalPaidToProvider}" /></fmt:formatNumber>" readonly="readonly" style="text-align: right;">
		</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%" align="right" style="text-align: right;">
			 <input type="text" name="totalFaultExcessProviders" id="totalFaultExcessProvider" value="<fmt:formatNumber><c:out value="${totalFaultExcessProvider}" /></fmt:formatNumber>" readonly="readonly" style="text-align: right;">
		</td>
		<!--  
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">&nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">&nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">&nbsp;</td>
		-->
		
	</tr>
	</tbody>
	</table>
	<br />
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td style="padding-top: 2px;">
	    <input type="hidden" name="notyet" value="">
        <input title="Save [Alt+Shift+S]" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="this.disabled='disabled';javascript:simpan()" name="Save" value=" Save " type="button"> <% //add this.disable kegunaan untuk mendisable button setelah di klik by adq %>
        <input title="Cancel [Alt+Shift+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:cancel()" name="Cancel" value=" Cancel " type="button">         
                         
	  </td>
      <td align="right"></td>
    </tr>
  </tbody>
</table>
	
	<br />
	<table class="tabForm" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	
	<tr height="20">
		<td width="4%" nowrap="nowrap" class="listViewThS1" scope="col">
			<img src="images/blank.gif" alt="asd" height="1" width="1">No.</td>		

<!-- ini default generated table from database -->
		   		   		   			
			
				  		
				 <td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">
				
				Claim Number&nbsp;
				</td>	
				 <td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">
				
				Claim Date&nbsp;
				</td>	
						
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%">
				Charges  &nbsp;
				</td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Excess  &nbsp;
				</td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Approved  &nbsp;
				</td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Ex Gratia  &nbsp;
				</td>
				
			
			   		   		   		   		   				   		   						   		   	  
	   
	</tr>

	
	<c:forEach items="${claimList}" var="claimList" varStatus="status">
	
	
	
			<c:set var="claimChargeValue" value="${claimChargeValue+claimList.claimChargeValue}"></c:set>
			<c:set var="claimApprovedValue" value="${claimApprovedValue+claimList.claimApprovedValue}"></c:set>
			<c:set var="claimExcessValue" value="${claimExcessValue+claimList.claimExcessValue}"></c:set>
			<c:set var="claimExGratiaValue" value="${claimExGratiaValue+claimList.exGratiaValue}"></c:set>
		
		 <tr height="20">
	      <td class="oddListRowS1" align="center" bgcolor="#4f85c6" nowrap="nowrap" valign="center"><c:out value="${status.index+1}" /></td>
	
			<td class="oddListRowS1" bgcolor="#4f85c6" nowrap="nowrap" valign="center">
	      		
	      		<c:out value="${claimList.claimNumber }" />
			</td>
			<td class="oddListRowS1" bgcolor="#4f85c6" nowrap="nowrap" valign="center">
	      		
	      		<c:out value="${claimList.claimDate }" />
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="center" align="right">
				<fmt:formatNumber><c:out value="${claimList.claimChargeValue }" /></fmt:formatNumber>
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="center" align="right">
				<fmt:formatNumber> <c:out value="${claimList.claimExcessValue}" /> </fmt:formatNumber>
			</td>  		   	
			 	 
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="center" align="right">
				<fmt:formatNumber> <c:out value="${claimList.claimApprovedValue}" /> </fmt:formatNumber>
			</td>  	 
			<td align="center" class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="center">
				<fmt:formatNumber> <c:out value="${claimList.exGratiaValue}" /> </fmt:formatNumber>
			</td>  	 
   		   		
	    </tr>
	   
		<tr>
	      <td colspan="20" class="listViewHRS1"></td>
	    </tr>
	</c:forEach>
<tr height="20">
		<td width="4%" nowrap="nowrap" class="listViewThS1" scope="col">
			<img src="images/blank.gif" alt="asd" height="1" width="1"></td>		

<!-- ini default generated table from database -->
		   		   		   			
			
				  		
				 <td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">
				
				
				</td>	
				
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="16%" colspan=1 align="right" style="text-align: right;">
					Annual Benefit : 					<fmt:formatNumber><c:out value="${benefit}" /></fmt:formatNumber>
					
				 </td>
			
		
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%"  style="text-align: right;">
					<fmt:formatNumber><c:out value="${claimChargeValue}" /></fmt:formatNumber>
					
				</td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%" colspan=1 align="right"  style="text-align: right;">
				<fmt:formatNumber><c:out value="${claimExcessValue}" /></fmt:formatNumber>
				
				</td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%" colspan=1 align="right" style="text-align: right;">
				<fmt:formatNumber><c:out value="${claimApprovedValue}" /></fmt:formatNumber>
				</td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%" colspan=1 align="right" style="text-align: right;">
				<fmt:formatNumber><c:out value="${claimExGratiaValue}" /></fmt:formatNumber>
				</td>	   		   		   				   		   						   		   	  
	   
	</tr>
	</tbody>
	</table>

<br />
<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>


	<tr height="20">
		<td width="2%" nowrap="nowrap" class="listViewThS1" scope="col">
			<img src="images/blank.gif" alt="asd" height="1" width="1">No.</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%" style="text-align: center;">Code &nbsp;</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%" style="text-align: center;">Client Code &nbsp;</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%" style="text-align: center;">Item Category &nbsp;</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%" style="text-align: center;">Method &nbsp;</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%" style="text-align: center;">Shared Benefit &nbsp;</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%" style="text-align: center;">Cashless &nbsp;</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%" style="text-align: center;">Reimburse &nbsp;</td>
			<c:if test="${theUser.userType eq 2}">
					<td scope="col" class="listViewThS1" width="5%" style="text-align: center;">
				Max Occur. &nbsp;</td>
		
					<td scope="col" class="listViewThS1" width="3%" style="text-align: center;">
				Max Occur. per Case&nbsp;</td>
			   			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%" style="text-align: center;">
				Usage &nbsp;</td>
				<td scope="col" class="listViewThS1" width="3%" style="text-align: center;">
				Deductible &nbsp;</td>
					<td scope="col" class="listViewThS1" width="3%" style="text-align: center;">
				Share (%) &nbsp;</td>
				<td scope="col" class="listViewThS1" width="5%" style="text-align: center;">
				Share (Amt) &nbsp;</td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%" style="text-align: center;">
				EDC &nbsp;</td>
				
			</c:if>		
	</tr>
	<c:forEach items="${memberBenefitList}" var="benefit" varStatus="status" >
	
	 <tr height="20">
      <td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${status.index + 1}" />.</td>

      		   		   		   		   		   		   		
					   		   		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" >
			<c:out value="${benefit.itemCategoryId.itemCategoryCode}" /> <c:if test="${benefit.itemCategoryId.itemCategoryAlternateCode ne null}">/</c:if> <c:out value="${benefit.itemCategoryId.itemCategoryAlternateCode}" />
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" >
			<c:out value="${benefit.clientItemCode}" /> 
		</td>			   					   		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<a href="claimitem?memberId=<c:out value="${memberId}" />&navigation=usagetrack&memberBenefitId=<c:out value="${benefit.memberBenefitId}" />&itemCategoryId=<c:out value="${benefit.itemCategoryId.itemCategoryId}" />&prev=listmember" class="linkDetail"><c:out value="${benefit.itemCategoryId.itemCategoryName}" /></a>		
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" >
			<c:if test="${benefit.benefitCalculationMethod.benefitUsageTypeId eq 1}">
				OCCURANCE
			</c:if>
			<c:if test="${benefit.benefitCalculationMethod.benefitUsageTypeId eq 2}">
				PER DISABILITY
			</c:if>			
			<c:if test="${benefit.benefitCalculationMethod.benefitUsageTypeId eq 3}">
				ANNUAL LIMIT
			</c:if>
			<c:if test="${benefit.benefitCalculationMethod.benefitUsageTypeId eq 4}">
				MAX OCCUR PER CASE
			</c:if>
			<c:if test="${benefit.benefitCalculationMethod.benefitUsageTypeId eq 5}">
				MAX DAILY
			</c:if>
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" >
			<c:out value="${benefit.sharedBenefitId.itemCategoryId.itemCategoryName}" />
		
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top" style="text-align: right;">
				<c:choose>			
					<c:when test="${benefit.sharedBenefitId eq null}">
						<c:choose>
							<c:when test="${benefit.benefitLimit eq -1}">
							AS CHARGE - [<b><c:out value="${benefit.cashlessPercentage}" /> %</b>]
							</c:when>
							<c:otherwise >
								<fmt:formatNumber><c:out value="${benefit.benefitLimit}" /></fmt:formatNumber>
								- [<b><c:out value="${benefit.cashlessPercentage}" /> %</b>]
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						SHARED
					</c:otherwise>
				</c:choose>				
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top" style="text-align: right;">			
				<c:choose>			
					<c:when test="${benefit.sharedBenefitId eq null}">
						<c:choose>
							<c:when test="${benefit.reimbursementBenefitLimit eq -1}">
							AS CHARGE - [<b><c:out value="${benefit.reimbursementPercentage}" /> %</b>]
							</c:when>
							<c:otherwise >
								<fmt:formatNumber><c:out value="${benefit.reimbursementBenefitLimit}" /></fmt:formatNumber>
								- [<b><c:out value="${benefit.reimbursementPercentage}" /> %</b>]
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						SHARED
					</c:otherwise>
				</c:choose>
			
		</td>
			<c:if test="${theUser.userType eq 2}">
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
			<c:if test="${benefit.maxBenefitOccurance ne -1}">			
				<fmt:formatNumber><c:out value="${benefit.maxBenefitOccurance}" /></fmt:formatNumber>
			</c:if>
			<c:if test="${benefit.maxBenefitOccurance eq -1}">			
				UNLIMITED
			</c:if>			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
			<c:if test="${benefit.maxOccurancePerCase ne -1}">			
				<fmt:formatNumber><c:out value="${benefit.maxOccurancePerCase}" /></fmt:formatNumber>
			</c:if>			
			<c:if test="${benefit.maxOccurancePerCase eq -1}">
				UNLIMITED
			</c:if>
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">			
				<fmt:formatNumber><c:out value="${benefit.benefitUsage}" /> </fmt:formatNumber> - [<b><fmt:formatNumber><c:out value="${benefit.benefitOccuranceUsage}" /></fmt:formatNumber></b> ]			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">			
				<fmt:formatNumber><c:out value="${benefit.deductibleLimit}" /></fmt:formatNumber>			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;">			
				<c:choose>
					<c:when test="${benefit.costSharingPercentage < 100}">
						<fmt:formatNumber><c:out value="${benefit.costSharingPercentage}" /></fmt:formatNumber> %
					</c:when>
					<c:otherwise>
					<center>-</center>
					</c:otherwise>
					
				</c:choose>
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;">			
				
						<fmt:formatNumber><c:out value="${benefit.costSharingAmount}" /></fmt:formatNumber> 
					
			</td>	
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
				<c:if test="${benefit.isEDCEnabled}">EDC
					<c:if test="${benefit.benefitLimitPublishedOnEdc eq 1}">(Y)</c:if>
					<c:if test="${benefit.benefitLimitPublishedOnEdc eq 0}">(N)</c:if>
					|<c:if test="${benefit.itemCategoryId.itemCategoryEDCCode ne null}"><c:out value="${benefit.itemCategoryId.itemCategoryEDCCode}" /></c:if>
				</c:if>
				<c:if test="${not benefit.isEDCEnabled}">SHOW</c:if>							
			</td>
			
		
		</c:if>
    </tr>
   
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	
	</c:forEach>
	
	</tbody>
</table>
	
	<br />
	<%-- Edit 20150511 by Feiby Veronika for showing Clausul in Benefit Check Items --%>
	<c:if test="${ipPolicyClausul ne null}">		
		<table class="listView" cellspacing="0" cellpadding="0" width="100%">
			<tbody>
			<tr height="20">
				<td scope="col" align="center" class="listViewThS1" style="text-align: center; font-size: 15;" nowrap="nowrap" width="100%" colspan=16>
						&nbsp;INPATIENT POLICY CLAUSUL</td>
			</tr>
			<tr height="20">
				<td width="2%" nowrap="nowrap" class="listViewThS1" scope="col">No.</td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Clausul Name</td>
		    	<td scope="col" class="listViewThS1" width="8%">Description</td>
		    	<td scope="col" class="listViewThS1" width="8%">Procedure</td>
		    	<td scope="col" class="listViewThS1" width="8%">Diagnosis</td>
		    	<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Item</td>
		    	<td scope="col" class="listViewThS1" nowrap="nowrap" width="2%">Is EDC</td>    	
		    	<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Calculation</td>
		    	<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Status</td>
		    	
			</tr>
			<c:forEach items="${ipPolicyClausul}" var="policyClausul" varStatus="status" >
			<tr  height="20">
				<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${status.index+1}" />.</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe"  valign="top">
					<c:out value="${policyClausul.clausulId.clausulName}" />			
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top">
					<c:out value="${policyClausul.description}" />			
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top">
					<c:out value="${policyClausul.procedureId.procedureName}" /> - <c:out value="${policyClausul.procedureId.procedureCode}" />			
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top">
					<c:out value="${policyClausul.diagnosisId.description}" /> - <c:out value="${policyClausul.diagnosisId.diagnosisCode}" />
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe"  valign="top">
					<c:out value="${policyClausul.itemCategoryId.itemCategoryName}" />					
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
					<c:if test="${policyClausul.isEdc eq 1}">
						EDC
					</c:if>
					<c:if test="${policyClausul.isEdc eq 0}">
						NON EDC
					</c:if>							
				</td>
				
				<td class="oddListRowS1" bgcolor="#e7f0fe"  valign="top">
					<c:out value="${policyClausul.treatmentUpgradeType.treatmentUpgradeTypeName}" />					
				</td>		
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
					<c:if test="${policyClausul.decision eq 1}">
						approved
					</c:if>
					<c:if test="${policyClausul.decision eq 0}">
						rejected
					</c:if>							
				</td>		   		   			   		   						   	
			</tr>
			</c:forEach>		
			</tbody>
		</table>
	</c:if>
	<br><br>
	<c:if test="${opPolicyClausul ne null}">		
		<table class="listView" cellspacing="0" cellpadding="0" width="100%">
		<tbody>
			<tr height="20">
				<td scope="col" align="center" class="listViewThS1" style="text-align: center; font-size: 15;" nowrap="nowrap" width="100%" colspan=16>
						&nbsp;OUTPATIENT POLICY CLAUSUL</td>
			</tr>
			<tr height="20">
				<td width="2%" nowrap="nowrap" class="listViewThS1" scope="col">No.</td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Clausul Name</td>
		    	<td scope="col" class="listViewThS1" width="8%">Description</td>
		    	<td scope="col" class="listViewThS1" width="8%">Procedure</td>
		    	<td scope="col" class="listViewThS1" width="8%">Diagnosis</td>
		    	<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Item</td>
		    	<td scope="col" class="listViewThS1" nowrap="nowrap" width="2%">Is EDC</td>    	
		    	<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Calculation</td>
		    	<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Status</td>	
			</tr>		
			<c:forEach items="${opPolicyClausul}" var="policyClausul" varStatus="status" >
			<tr  height="20">
				<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${status.index+1}" />.</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe"  valign="top">
					<c:out value="${policyClausul.clausulId.clausulName}" />			
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top">
					<c:out value="${policyClausul.description}" />			
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top">
					<c:out value="${policyClausul.procedureId.procedureName}" /> - <c:out value="${policyClausul.procedureId.procedureCode}" />			
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top">
					<c:out value="${policyClausul.diagnosisId.description}" /> - <c:out value="${policyClausul.diagnosisId.diagnosisCode}" />
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe"  valign="top">
					<c:out value="${policyClausul.itemCategoryId.itemCategoryName}" />					
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
					<c:if test="${policyClausul.isEdc eq 1}">
						EDC
					</c:if>
					<c:if test="${policyClausul.isEdc eq 0}">
						NON EDC
					</c:if>							
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe"  valign="top">
					<c:out value="${policyClausul.treatmentUpgradeType.treatmentUpgradeTypeName}" />					
				</td>		
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
					<c:if test="${policyClausul.decision eq 1}">
						approved
					</c:if>
					<c:if test="${policyClausul.decision eq 0}">
						rejected
					</c:if>							
				</td>		   		   			   		   		
			</tr>			
			</c:forEach>
		</tbody>
		</table>
	</c:if>
	<br>
	<c:if test="${maPolicyClausul ne null}">		
		<table class="listView" cellspacing="0" cellpadding="0" width="100%">
		<tbody>
			<tr height="20">
				<td scope="col" align="center" class="listViewThS1" style="text-align: center; font-size: 15;" nowrap="nowrap" width="100%" colspan=16>
						&nbsp;MATERNITY POLICY CLAUSUL</td>
			</tr>
			<tr height="20">
				<td width="2%" nowrap="nowrap" class="listViewThS1" scope="col">No.</td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Clausul Name</td>
		    	<td scope="col" class="listViewThS1" width="8%">Description</td>
		    	<td scope="col" class="listViewThS1" width="8%">Procedure</td>
		    	<td scope="col" class="listViewThS1" width="8%">Diagnosis</td>
		    	<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Item</td>
		    	<td scope="col" class="listViewThS1" nowrap="nowrap" width="2%">Is EDC</td>    	
		    	<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Calculation</td>
		    	<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Status</td>
			</tr>		
			<c:forEach items="${maPolicyClausul}" var="policyClausul" varStatus="status" >
			 <tr  height="20">
				<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${status.index+1}" />.</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe"  valign="top">
					<c:out value="${policyClausul.clausulId.clausulName}" />			
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top">
					<c:out value="${policyClausul.description}" />			
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top">
					<c:out value="${policyClausul.procedureId.procedureName}" /> - <c:out value="${policyClausul.procedureId.procedureCode}" />			
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top">
					<c:out value="${policyClausul.diagnosisId.description}" /> - <c:out value="${policyClausul.diagnosisId.diagnosisCode}" />
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe"  valign="top">
					<c:out value="${policyClausul.itemCategoryId.itemCategoryName}" />					
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
					<c:if test="${policyClausul.isEdc eq 1}">
						EDC
					</c:if>
					<c:if test="${policyClausul.isEdc eq 0}">
						NON EDC
					</c:if>							
				</td>			
				<td class="oddListRowS1" bgcolor="#e7f0fe"  valign="top">
					<c:out value="${policyClausul.treatmentUpgradeType.treatmentUpgradeTypeName}" />					
				</td>		
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
					<c:if test="${policyClausul.decision eq 1}">
						approved
					</c:if>
					<c:if test="${policyClausul.decision eq 0}">
						rejected
					</c:if>							
				</td>		   		   			   		   		
		  	</tr>
			</c:forEach>
		</tbody>
		</table>
	</c:if>
	<br />
	<c:if test="${dentalPolicyClausul ne null}">		
		<table class="listView" cellspacing="0" cellpadding="0" width="100%">
		<tbody>
			<tr height="20">
				<td scope="col" align="center" class="listViewThS1" style="text-align: center; font-size: 15;" nowrap="nowrap" width="100%" colspan=16>
						&nbsp;DENTAL POLICY CLAUSUL</td>
			</tr>
			<tr height="20">
				<td width="2%" nowrap="nowrap" class="listViewThS1" scope="col">No.</td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Clausul Name</td>
		    	<td scope="col" class="listViewThS1" width="8%">Description</td>
		    	<td scope="col" class="listViewThS1" width="8%">Procedure</td>
		    	<td scope="col" class="listViewThS1" width="8%">Diagnosis</td>
		    	<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Item</td>
		    	<td scope="col" class="listViewThS1" nowrap="nowrap" width="2%">Is EDC</td>    	
		    	<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Calculation</td>
		    	<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Status</td>
			</tr>
			<c:forEach items="${dentalPolicyClausul}" var="policyClausul" varStatus="status" >
			<tr  height="20">
				<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${status.index+1}" />.</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe"  valign="top">
					<c:out value="${policyClausul.clausulId.clausulName}" />			
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top">
					<c:out value="${policyClausul.description}" />			
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top">
					<c:out value="${policyClausul.procedureId.procedureName}" /> - <c:out value="${policyClausul.procedureId.procedureCode}" />			
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top">
					<c:out value="${policyClausul.diagnosisId.description}" /> - <c:out value="${policyClausul.diagnosisId.diagnosisCode}" />
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe"  valign="top">
					<c:out value="${policyClausul.itemCategoryId.itemCategoryName}" />					
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
					<c:if test="${policyClausul.isEdc eq 1}">
						EDC
					</c:if>
					<c:if test="${policyClausul.isEdc eq 0}">
						NON EDC
					</c:if>							
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe"  valign="top">
					<c:out value="${policyClausul.treatmentUpgradeType.treatmentUpgradeTypeName}" />					
				</td>		
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
					<c:if test="${policyClausul.decision eq 1}">
						approved
					</c:if>
					<c:if test="${policyClausul.decision eq 0}">
						rejected
					</c:if>							
				</td>		   		   			   		   		
		  	</tr>
			</c:forEach>
		</tbody>
		</table>
	</c:if>
	<br />
	<c:if test="${opticalPolicyClausul ne null}">		
	<table class="listView" cellspacing="0" cellpadding="0" width="100%">
		<tbody>
			<tr height="20">
				<td scope="col" align="center" class="listViewThS1" style="text-align: center; font-size: 15;" nowrap="nowrap" width="100%" colspan=16>
						&nbsp;OPTICAL POLICY CLAUSUL</td>
			</tr>	
			<tr height="20">
				<td width="2%" nowrap="nowrap" class="listViewThS1" scope="col">No.</td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Clausul Name</td>
		    	<td scope="col" class="listViewThS1" width="8%">Description</td>
		    	<td scope="col" class="listViewThS1" width="8%">Procedure</td>
		    	<td scope="col" class="listViewThS1" width="8%">Diagnosis</td>
		    	<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Item</td>
		    	<td scope="col" class="listViewThS1" nowrap="nowrap" width="2%">Is EDC</td>    	
		    	<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Calculation</td>
		    	<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Status</td>
			</tr>	
			<c:forEach items="${opticalPolicyClausul}" var="policyClausul" varStatus="status" >
			<tr  height="20">
				<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${status.index+1}" />.</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe"  valign="top">
					<c:out value="${policyClausul.clausulId.clausulName}" />			
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top">
					<c:out value="${policyClausul.description}" />			
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top">
					<c:out value="${policyClausul.procedureId.procedureName}" /> - <c:out value="${policyClausul.procedureId.procedureCode}" />			
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top">
					<c:out value="${policyClausul.diagnosisId.description}" /> - <c:out value="${policyClausul.diagnosisId.diagnosisCode}" />
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe"  valign="top">
					<c:out value="${policyClausul.itemCategoryId.itemCategoryName}" />					
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
					<c:if test="${policyClausul.isEdc eq 1}">
						EDC
					</c:if>
					<c:if test="${policyClausul.isEdc eq 0}">
						NON EDC
					</c:if>							
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe"  valign="top">
					<c:out value="${policyClausul.treatmentUpgradeType.treatmentUpgradeTypeName}" />					
				</td>		
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
					<c:if test="${policyClausul.decision eq 1}">
						approved
					</c:if>
					<c:if test="${policyClausul.decision eq 0}">
						rejected
					</c:if>							
				</td>		   		   			   		   			   	
		  	</tr>		
		</c:forEach>		
		</tbody>
	</table>
	</c:if>
	<br>
	
	<table class="tabForm" cellspacing="0" cellpadding="0" width="100%">
		<tbody>
		<tr height="20">
					<td scope="col" align="center" class="listViewThS1" style="text-align: center; font-size: 15;" nowrap="nowrap" width="100%" colspan=16>
							&nbsp;PRODUCT CLAUSUL</td>
				</tr>	
		<tr height="20">
			<td width="4%" nowrap="nowrap" class="listViewThS1" scope="col">
				<img src="images/blank.gif" alt="asd" height="1" width="1">No.</td>		
	
	<!-- ini default generated table from database -->	
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">
					Clausul &nbsp;</td>		
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
					Clausul Category&nbsp;</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="40%">
					Detail Info&nbsp;</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
					Age Limitation &nbsp;</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
					Coverage Status &nbsp;</td>
			</tr>
		<c:forEach items="${productClausulList}" var="clausul" varStatus="status">
		<tr height="20">
	    	<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${status.index + 1}" />.</td>			   		
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<a href="" class="linkDetail"><c:out value="${clausul.clausulId.clausulName}" /></a>		
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" >
					<c:out value="${clausul.clausulId.clausulCategoryId.clausulCategoryName}" />
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: left;">
				<c:if test="${clausul.diagnosisCode ne ''}">
					<c:out value="${clausul.diagnosisCode}" />
				</c:if>
			</td>		
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: left;">
				<c:if test="${clausul.parameterUmur ne -1}">
					<c:if test="${clausul.parameterUmur eq 2}">dibawah</c:if>
					<c:if test="${clausul.parameterUmur eq 1}">diatas</c:if> &nbsp;<c:out value="${clausul.umur}" /> tahun
				</c:if>
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: left;">
				<c:if test="${clausul.decision ne -1}">
					<c:if test="${clausul.decision eq 1}">
					dicover
					</c:if>
					<c:if test="${clausul.decision eq 2}">
					tidak dicover
					</c:if>
				</c:if>
			</td>		
	    </tr>
		<tr>
	      <td colspan="20" class="listViewHRS1"></td>
	    </tr>
		</c:forEach>
		<tr height="20">
			<td width="4%" nowrap="nowrap" class="listViewThS1" scope="col">
				<img src="images/blank.gif" alt="asd" height="1" width="1"></td>
	<!-- ini default generated table from database -->			  		
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%"></td>	
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="16%" colspan=1 align="right" style="text-align: right;"></td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
			</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%" colspan=1 align="right" style="text-align: right;">
			</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%" colspan=1 align="right" style="text-align: right;">
			</td>	   
		</tr>
		</tbody>
	</table>
	
</form>
	
<!-- Table Content Stop -->
	<!-- Table Toolbar Start -->
	
		<!-- Table Toolbar Stop -->


<!-- Table Container Stop -->

<script language="JavaScript">
	

	function simpan(){
	    //add by adq, kondisi buat check actual benefit limit = 0 OR benefit limit < -1
	    var actualBenefit;
	    actualBenefit = ${outerBenefitLimit};
	    if((actualBenefit==0.0) || (actualBenefit<-1.0)){
	    window.alert("Sisa benefit kurang dari sama dengan 0. Mohon segera lakukan top up dengan cara menghubungi bagian HCM. ");
	    }
	    else{	    	    	   	    
		document.form1.navigation.value = "approvecheckbulk";
		document.form1.submit();
		}
	}

	function checkData(){
		var hasil = false;
		
		
		if (!hasil){
			window.alert("terjadi kesalahan dalam proses check, silakan memeriksa ulang data verifikasi");
		}	
		
	
		return hasil;
	}
	function cancel(){
		document.form1.navigation.value = "detail";
		document.form1.action = "claim";
		document.form1.submit();
	}
	function hitungBenefit (){
	
		var itemValueArrayInput = document.form1.approvedValues;
		
		var entryValue = 0;
		var pembulatan = 0;
		
	//	if (totalItem > 0){
		//	for (i = 1; i <= totalItem; i=i+1){
			for (var i = 0; i < itemValueArrayInput.length; i++){				
				//var currentValue = parseFloat(document.getElementById("idItemValue"+i).value);
				var currentValue = parseFloat(itemValueArrayInput[i].value);
				if (currentValue == '' || currentValue == null || currentValue == NaN){
					currentValue = 0;
				}
				entryValue += currentValue;
			}
		//}
		//document.getElementById("totalClaimItem").value = entryValue;
		document.form1.totalApprovedBenefit.value = entryValue;
		
	}
	function hitungApproved (idx){
	
		var initChargeValueElem = document.getElementById("initChargeValue"+idx);
		var initChargeValueVal = document.getElementById("initChargeValue"+idx).value;
		var initChargeValueEnd = parseFloat (initChargeValueVal);
		
		
		var initApproveValueElem = document.getElementById("initApprovedValue"+idx);
		var initApproveValueVal = document.getElementById("initApprovedValue"+idx).value;
		var initApproveValueEnd = parseFloat (initApproveValueVal);
		
		
		var approvedValueElem = document.getElementById("approvedValue"+idx);
		var approvedValueVal = document.getElementById("approvedValue"+idx).value;
		var approvedValueEnd = parseFloat(approvedValueVal);
		
		
		
		var refundValueElem = document.getElementById("refundValue"+idx);
		var refundValueVal = document.getElementById("refundValue"+idx).value;
		var refundValueValEnd = parseFloat(refundValueVal);
		
		
		approvedValueElem.value = initApproveValueEnd + refundValueValEnd;
		
		
	}
	
	function autoHitungTotal(name){
		var totalCheckItems = "<c:out value='${checkItemsLength}'/>";
		var hasil = 0;

		for (var i = 0; i < totalCheckItems; i++){
			
			if(name == "prepaid")		
			var currentValue = document.getElementById("prePaidExcessValue"+[i]).value;
			
			if(name == "refund")		
			var currentValue = document.getElementById("refundValue"+[i]).value;
			
			if(name == "paidtoprovider")		
			var currentValue = document.getElementById("paidToProviderValue"+[i]).value;
			
			if(name == "faultexcess")		
			var currentValue = document.getElementById("faultExcessProviderValue"+[i]).value;
			
			if (currentValue == '' || currentValue == null || isNaN(currentValue)){
				currentValue = 0;				
			}else {				
				currentValue = parseFloat(currentValue);
			}
	
	        hasil += currentValue;
		}
		
		if(name == "prepaid")
		document.form1.totalPrePaidExcess.value = hasil;
		
		if(name == "refund")
		document.form1.totalRefund.value = hasil;
		
		if(name == "paidtoprovider")
		document.form1.totalPaidToProvider.value = hasil;
		
		if(name == "faultexcess")
		document.form1.totalFaultExcessProvider.value = hasil;
	}
	
	function approveAll(idx){
		//window.alert(idx);
		
		
			AJAXClaimItemService.getClaimItemCharge (idx,{
			callback: function (res){
				var elem = document.getElementById("approvedValue"+idx);
				elem.value = res;
			}
		});
	
	}

</script>
