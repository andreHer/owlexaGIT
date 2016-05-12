<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


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



<!-- Search Container Start -->

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;List Claim Benefit</h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
      <td align="right">
      	<input title="Error Log"  name="errorLog" id="errorLog" value=" Error Log " class="errorLog" type="button" onClick="javascript:printErrorLog()">
      </td>
      <td align="right">
      	<input title="Add Error Log"  name="addErrorLog" value=" Add Error Log " class="errorLog" type="button" onClick="javascript:adderrorlog()">
      </td>
    </tr>
  </tbody>
</table>

<%@ include file="../mainContentClaim.jsp" %>

<form name="form1" action="claiminvestigation" method="POST">
<input type="hidden" name="navigation" value="gosearch">
<input type="hidden" name="arah" value="">
<input type="hidden" name="claimId" value="<c:out value="${claimId}" />">
<input type="hidden" name="index" value="<c:out value="${index}" />">
<input type="hidden" name="claimInvestigationId" value="<c:out value="${claimInvestigation.claimInvestigationId}" />">

				<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"></td>
      <td width="100%"></td>
    </tr>
  </tbody>
</table>




<br />




<c:if test="${inpatient ne null}">		
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;List Benefit Inpatient</h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>
<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	<tr height="20">
		<td scope="col" align="center" class="listViewThS1" style="text-align: center; font-size: 15;" nowrap="nowrap" width="100%" colspan=13>
				&nbsp;INPATIENT</td>
	</tr>

	<tr height="20">
		<td width="6%" nowrap="nowrap" class="listViewThS1" scope="col">
			<img src="images/blank.gif" alt="asd" height="1" width="1">No.</td>		
			   			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="20%">
				Item Category &nbsp;</td>
			
			
		
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Calculation Method &nbsp;</td>
			
				
					
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Cashless Benefit &nbsp;</td>
                                        
                                        <td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Reimburse Benefit &nbsp;</td>
                                        
			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Max Ocr &nbsp;</td>
		
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Max Ocr / Case&nbsp;</td>
			
			  	<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Remaining &nbsp;</td>
			   			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Usage &nbsp;</td>
			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="9%">
				Sharing &nbsp;</td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">
				EDC &nbsp;</td>
					
		  <td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Occurs&nbsp;
	   </td>
	   <td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Usage %&nbsp;
	   </td>
	</tr>


	<c:forEach items="${inpatient}" var="benefit" varStatus="status" >
	 <tr height="20">
      	<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${status.index + 1}" />.</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe"  valign="top">
			<a  href="claimitem?claimId=<c:out value="${claimId}" />&navigation=usagetrack&memberBenefitId=<c:out value="${benefit.memberBenefitId}" />&itemCategoryId=<c:out value="${benefit.itemCategoryId.itemCategoryId}" />&prev=listclaim" class="linkDetail" >
				<c:out value="${benefit.itemCategoryId.itemCategoryName}" /> [ <c:out value="${benefit.itemCategoryId.itemCategoryCode}" /> ]
			</a>
			(<c:out value="${benefit.effectiveDate}" /> s/d <c:out value="${benefit.expireDate}" />)			
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
			
					   		
					   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;">
			
				<c:choose>
					<c:when test="${benefit.isAsCharge}">
					AS CHARGE
					</c:when>
					<c:otherwise >
						<fmt:formatNumber><c:out value="${benefit.benefitLimit}" /></fmt:formatNumber>
					</c:otherwise>
				</c:choose>
			
		</td>
                			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;">
			
				<c:choose>
					<c:when test="${benefit.reimbursementAsCharge}">
					AS CHARGE
					</c:when>
					<c:otherwise >
						<fmt:formatNumber><c:out value="${benefit.reimbursementBenefitLimit}" /></fmt:formatNumber>
					</c:otherwise>
				</c:choose>
			
		</td>
			

		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
				
				<c:choose>
					<c:when test="${benefit.maxBenefitOccurance  eq -1}">
					UNLIMITED
					</c:when>
					<c:otherwise >
						<fmt:formatNumber><c:out value="${benefit.maxBenefitOccurance}" /></fmt:formatNumber>
					</c:otherwise>
				</c:choose>
		</td>

		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
			

				<c:choose>
					<c:when test="${benefit.maxOccurancePerCase  eq -1}">
					UNLIMITED
					</c:when>
					<c:otherwise >
						<fmt:formatNumber><c:out value="${benefit.maxOccurancePerCase}" /></fmt:formatNumber>
					</c:otherwise>
				</c:choose>
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
			
				<c:choose>
					<c:when test="${benefit.currentBenefitPosition eq -1}">
					UNLIMITED
					</c:when>
					<c:otherwise >
						<fmt:formatNumber><c:out value="${benefit.currentBenefitPosition}" /></fmt:formatNumber>
					</c:otherwise>
				</c:choose>
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
			
				<c:choose>
					<c:when test="${benefit.benefitUsage eq -1}">
					UNLIMITED
					</c:when>
					<c:otherwise >
						<fmt:formatNumber><c:out value="${benefit.benefitUsage}" /></fmt:formatNumber>
					</c:otherwise>
				</c:choose>
		</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
							<c:choose>
					<c:when test="${benefit.costSharingPercentage < 100}">
						<fmt:formatNumber><c:out value="${benefit.costSharingPercentage}" /></fmt:formatNumber> %
					</c:when>
					<c:otherwise>
					<center>-</center>
					</c:otherwise>
					
				</c:choose>
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
				<c:if test="${benefit.isEDCEnabled}">EDC
					<c:if test="${benefit.benefitLimitPublishedOnEdc eq 1}">(Y)</c:if><c:if test="${benefit.benefitLimitPublishedOnEdc eq 0}">(N)</c:if>
				</c:if>
				<c:if test="${not benefit.isEDCEnabled}">SHOW</c:if>							
			</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
			
				<fmt:formatNumber><c:out value="${benefit.benefitOccuranceUsage}" /></fmt:formatNumber>
			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
			
				<fmt:formatNumber><c:out value="${benefit.benefitUsagePercentage}" /></fmt:formatNumber> %
			
		</td>
    </tr>
   
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	
	</c:forEach>
	
	</tbody>
</table>
</c:if>

<br /> <br />

<c:if test="${outpatient ne null }">
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;List Benefit Outpatient</h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>
<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	<tr height="20">
		<td scope="col" align="center" class="listViewThS1" style="text-align: center; font-size: 15;" nowrap="nowrap" width="100%" colspan=13>
				&nbsp;OUTPATIENT</td>
	</tr>

	<tr height="20">
		<td width="6%" nowrap="nowrap" class="listViewThS1" scope="col">
			<img src="images/blank.gif" alt="asd" height="1" width="1">No.</td>		
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="20%">
				Item Category &nbsp;</td>
				
		
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Calculation Method &nbsp;</td>
			
		
			   				
				
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Cashless Benefit &nbsp;</td>
                                        
                                        <td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Reimburse Benefit &nbsp;</td>
			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Max Ocr &nbsp;</td>
		
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Max Ocr / Case&nbsp;</td>
			
			  	<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Remaining &nbsp;</td>
			   			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Usage &nbsp;</td>
			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="9%">
				Sharing &nbsp;</td>
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">
				EDC &nbsp;</td>
		  <td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Occurs&nbsp;
	   </td>
	   <td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Usage %&nbsp;
	   </td>
	</tr>


	<c:forEach items="${outpatient}" var="benefit" varStatus="status" >
	 <tr height="20">
      <td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${status.index + 1}" />.</td>

      		   		   		   		   		   		   		
					   		   		
					   		
		<td class="oddListRowS1" bgcolor="#e7f0fe"  valign="top">
			
			<a  href="claimitem?claimId=<c:out value="${claimId}" />&navigation=usagetrack&memberBenefitId=<c:out value="${benefit.memberBenefitId}" />&itemCategoryId=<c:out value="${benefit.itemCategoryId.itemCategoryId}" />&prev=listclaim" class="linkDetail" >
				<c:out value="${benefit.itemCategoryId.itemCategoryName}" /> [ <c:out value="${benefit.itemCategoryId.itemCategoryCode}" /> ]
			</a>
			(<c:out value="${benefit.effectiveDate}" /> s/d <c:out value="${benefit.expireDate}" />)
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
			
					   		
					   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;">
			
				<c:choose>
					<c:when test="${benefit.isAsCharge}">
					AS CHARGE
					</c:when>
					<c:otherwise >
						<fmt:formatNumber><c:out value="${benefit.benefitLimit}" /></fmt:formatNumber>
					</c:otherwise>
				</c:choose>
			
		</td>
                			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;">
			
				<c:choose>
					<c:when test="${benefit.reimbursementAsCharge}">
					AS CHARGE
					</c:when>
					<c:otherwise >
						<fmt:formatNumber><c:out value="${benefit.reimbursementBenefitLimit}" /></fmt:formatNumber>
					</c:otherwise>
				</c:choose>
			
		</td>
			

		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
		
				<c:choose>
					<c:when test="${benefit.maxBenefitOccurance eq -1}">
					UNLIMITED
					</c:when>
					<c:otherwise >
						<fmt:formatNumber><c:out value="${benefit.maxBenefitOccurance}" /></fmt:formatNumber>
					</c:otherwise>
				</c:choose>

		</td>

		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">

				<c:choose>
					<c:when test="${benefit.maxOccurancePerCase eq -1}">
					UNLIMITED
					</c:when>
					<c:otherwise >
						<fmt:formatNumber><c:out value="${benefit.maxOccurancePerCase}" /></fmt:formatNumber>
					</c:otherwise>
				</c:choose>
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
			
				<c:choose>
					<c:when test="${benefit.currentBenefitPosition eq -1}">
					UNLIMITED
					</c:when>
					<c:otherwise >
						<fmt:formatNumber><c:out value="${benefit.currentBenefitPosition}" /></fmt:formatNumber>
					</c:otherwise>
				</c:choose>
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
			
				<c:choose>
					<c:when test="${benefit.benefitUsage eq -1}">
					UNLIMITED
					</c:when>
					<c:otherwise >
						<fmt:formatNumber><c:out value="${benefit.benefitUsage}" /></fmt:formatNumber>
					</c:otherwise>
				</c:choose>
			
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
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
				<c:if test="${benefit.isEDCEnabled}">EDC
					<c:if test="${benefit.benefitLimitPublishedOnEdc eq 1}">(Y)</c:if><c:if test="${benefit.benefitLimitPublishedOnEdc eq 0}">(N)</c:if>
				</c:if>
				<c:if test="${not benefit.isEDCEnabled}">SHOW</c:if>							
			</td>
<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
			
				<fmt:formatNumber><c:out value="${benefit.benefitOccuranceUsage}" /></fmt:formatNumber>
			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
			
				<fmt:formatNumber><c:out value="${benefit.benefitUsagePercentage}" /></fmt:formatNumber> %
			
		</td>
    </tr>
   
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	
	</c:forEach>
	
	</tbody>
</table>

<br /> <br />
</c:if>

<c:if test="${maternity ne null}">
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;List Benefit Maternity</h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>
<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	<tr height="20">
		<td scope="col" align="center" class="listViewThS1" style="text-align: center; font-size: 15;" nowrap="nowrap" width="100%" colspan=13>
				&nbsp;MATERNITY</td>
	</tr>

	<tr height="20">
		<td width="6%" nowrap="nowrap" class="listViewThS1" scope="col">
			<img src="images/blank.gif" alt="asd" height="1" width="1">No.</td>		

<!-- ini default generated table from database -->
		   		   		   		   		   		  
			   			
		
		
			   			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="20%">
				Item Category &nbsp;</td>
			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Calculation Method &nbsp;</td>
			
		
			   			
							
				
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Cashless Benefit &nbsp;</td>
                                        
                                        <td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Reimburse Benefit &nbsp;</td>
			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Max Ocr &nbsp;</td>
		
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Max Ocr / Case&nbsp;</td>
			
			  	<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Remaining &nbsp;</td>
			   			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Usage &nbsp;</td>
			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="9%">
				Sharing &nbsp;</td>
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">
				EDC &nbsp;</td>
		  <td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Occurs&nbsp;
	   </td>
	   <td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Usage %&nbsp;
	   </td>
	</tr>


	<c:forEach items="${maternity}" var="benefit" varStatus="status" >
	<%	if (i % 2 == 0) {
			rowclass = "col1";
		} else if (i % 2 != 0) {
			rowclass= "col2";
		}
		i++;
	%>
	 <tr height="20">
      <td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${status.index + 1}" />.</td>

      		   		   		   		   		   		   		
					   		   		
					   		
		<td class="oddListRowS1" bgcolor="#e7f0fe"  valign="top">
			<a  href="claimitem?claimId=<c:out value="${claimId}" />&navigation=usagetrack&memberBenefitId=<c:out value="${benefit.memberBenefitId}" />&itemCategoryId=<c:out value="${benefit.itemCategoryId.itemCategoryId}" />&prev=listclaim" class="linkDetail" >
				<c:out value="${benefit.itemCategoryId.itemCategoryName}" /> [ <c:out value="${benefit.itemCategoryId.itemCategoryCode}" /> ]
				</a>
			(<c:out value="${benefit.effectiveDate}" /> s/d <c:out value="${benefit.expireDate}" />)
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
			
					   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;">
			
				<c:choose>
					<c:when test="${benefit.isAsCharge}">
					AS CHARGE
					</c:when>
					<c:otherwise >
						<fmt:formatNumber><c:out value="${benefit.benefitLimit}" /></fmt:formatNumber>
					</c:otherwise>
				</c:choose>
			
		</td>
                			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;">
			
				<c:choose>
					<c:when test="${benefit.reimbursementAsCharge}">
					AS CHARGE
					</c:when>
					<c:otherwise >
						<fmt:formatNumber><c:out value="${benefit.reimbursementBenefitLimit}" /></fmt:formatNumber>
					</c:otherwise>
				</c:choose>
			
		</td>
			

		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
			
				<c:choose>
					<c:when test="${benefit.maxBenefitOccurance eq -1}">
					UNLIMITED
					</c:when>
					<c:otherwise >
						<fmt:formatNumber><c:out value="${benefit.maxBenefitOccurance}" /></fmt:formatNumber>
					</c:otherwise>
				</c:choose>
				
		</td>

		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
				<c:choose>
					<c:when test="${benefit.maxOccurancePerCase eq -1}">
					UNLIMITED
					</c:when>
					<c:otherwise >
						<fmt:formatNumber><c:out value="${benefit.maxOccurancePerCase}" /></fmt:formatNumber>
					</c:otherwise>
				</c:choose>
			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
			
				<c:choose>
					<c:when test="${benefit.currentBenefitPosition eq -1}">
					UNLIMITED
					</c:when>
					<c:otherwise >
						<fmt:formatNumber><c:out value="${benefit.currentBenefitPosition}" /></fmt:formatNumber>
					</c:otherwise>
				</c:choose>
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
			
				<c:choose>
					<c:when test="${benefit.benefitUsage eq -1}">
					UNLIMITED
					</c:when>
					<c:otherwise >
						<fmt:formatNumber><c:out value="${benefit.benefitUsage}" /></fmt:formatNumber>
					</c:otherwise>
				</c:choose>
			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
					<c:choose>
					<c:when test="${benefit.costSharingPercentage < 100}">
						<fmt:formatNumber><c:out value="${benefit.costSharingPercentage}" /></fmt:formatNumber> %
					</c:when>
					<c:otherwise>
					<center>-</center>
					</c:otherwise>
					
				</c:choose>
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
				<c:if test="${benefit.isEDCEnabled}">EDC
					<c:if test="${benefit.benefitLimitPublishedOnEdc eq 1}">(Y)</c:if><c:if test="${benefit.benefitLimitPublishedOnEdc eq 0}">(N)</c:if>
				</c:if>
				<c:if test="${not benefit.isEDCEnabled}">SHOW</c:if>							
			</td>
<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
			
				<fmt:formatNumber><c:out value="${benefit.benefitOccuranceUsage}" /></fmt:formatNumber>
			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
			
				<fmt:formatNumber><c:out value="${benefit.benefitUsagePercentage}" /></fmt:formatNumber> %
			
		</td>
    </tr>
   
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	
	</c:forEach>
	
	</tbody>
</table>
</c:if>


<c:if test="${optical ne null}">
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;List Benefit Optical</h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>
<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	<tr height="20">
		<td scope="col" align="center" class="listViewThS1" style="text-align: center; font-size: 15;" nowrap="nowrap" width="100%" colspan=13>
				&nbsp;OPTICAL</td>
	</tr>

	<tr height="20">
		<td width="6%" nowrap="nowrap" class="listViewThS1" scope="col">
			<img src="images/blank.gif" alt="asd" height="1" width="1">No.</td>		
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="20%">
				Item Category &nbsp;</td>
			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Calculation Method &nbsp;</td>
			
		
			   			
					
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Cashless Benefit &nbsp;</td>
                                        
                                        <td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Reimburse Benefit &nbsp;</td>
			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Max Ocr &nbsp;</td>
		
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Max Ocr / Case&nbsp;</td>
			
			  	<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Remaining &nbsp;</td>
			   			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Usage &nbsp;</td>
			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="9%">
				Sharing &nbsp;</td>
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">
				EDC &nbsp;</td>
		  <td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Occurs&nbsp;
	   </td>
	   <td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Usage %&nbsp;
	   </td>
	</tr>


	<c:forEach items="${optical}" var="benefit" varStatus="status" >

	 <tr height="20">
      <td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${status.index + 1}" />.</td>

      		   		   		   		   		   		   		
					   		   		
					   		
		<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top">
			<a  href="claimitem?claimId=<c:out value="${claimId}" />&navigation=usagetrack&memberBenefitId=<c:out value="${benefit.memberBenefitId}" />&itemCategoryId=<c:out value="${benefit.itemCategoryId.itemCategoryId}" />&prev=listclaim" class="linkDetail" >
				<c:out value="${benefit.itemCategoryId.itemCategoryName}" /> [ <c:out value="${benefit.itemCategoryId.itemCategoryCode}" /> ]
			</a>
			(<c:out value="${benefit.effectiveDate}" /> s/d <c:out value="${benefit.expireDate}" />)
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
			
					   			
					   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;">
			
				<c:choose>
					<c:when test="${benefit.isAsCharge}">
					AS CHARGE
					</c:when>
					<c:otherwise >
						<fmt:formatNumber><c:out value="${benefit.benefitLimit}" /></fmt:formatNumber>
					</c:otherwise>
				</c:choose>
			
		</td>
                			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;">
			
				<c:choose>
					<c:when test="${benefit.reimbursementAsCharge}">
					AS CHARGE
					</c:when>
					<c:otherwise >
						<fmt:formatNumber><c:out value="${benefit.reimbursementBenefitLimit}" /></fmt:formatNumber>
					</c:otherwise>
				</c:choose>
			
		</td>
			
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
				<c:choose>
					<c:when test="${benefit.maxBenefitOccurance eq -1}">
					UNLIMITED
					</c:when>
					<c:otherwise >
						<fmt:formatNumber><c:out value="${benefit.maxBenefitOccurance}" /></fmt:formatNumber>
					</c:otherwise>
				</c:choose>
		</td>

		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
				<c:choose>
					<c:when test="${benefit.maxOccurancePerCase eq -1}">
					UNLIMITED
					</c:when>
					<c:otherwise >
						<fmt:formatNumber><c:out value="${benefit.maxOccurancePerCase}" /></fmt:formatNumber>
					</c:otherwise>
				</c:choose>
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
			
				<c:choose>
					<c:when test="${benefit.currentBenefitPosition eq -1}">
					UNLIMITED
					</c:when>
					<c:otherwise >
						<fmt:formatNumber><c:out value="${benefit.currentBenefitPosition}" /></fmt:formatNumber>
					</c:otherwise>
				</c:choose>
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
			
				<c:choose>
					<c:when test="${benefit.benefitUsage eq -1}">
					UNLIMITED
					</c:when>
					<c:otherwise >
						<fmt:formatNumber><c:out value="${benefit.benefitUsage}" /></fmt:formatNumber>
					</c:otherwise>
				</c:choose>
			
		</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
							<c:choose>
					<c:when test="${benefit.costSharingPercentage < 100}">
						<fmt:formatNumber><c:out value="${benefit.costSharingPercentage}" /></fmt:formatNumber> %
					</c:when>
					<c:otherwise>
					<center>-</center>
					</c:otherwise>
					
				</c:choose>
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
				<c:if test="${benefit.isEDCEnabled}">EDC
					<c:if test="${benefit.benefitLimitPublishedOnEdc eq 1}">(Y)</c:if><c:if test="${benefit.benefitLimitPublishedOnEdc eq 0}">(N)</c:if>
				</c:if>
				<c:if test="${not benefit.isEDCEnabled}">SHOW</c:if>							
			</td>
<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
			
				<fmt:formatNumber><c:out value="${benefit.benefitOccuranceUsage}" /></fmt:formatNumber>
			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
			
				<fmt:formatNumber><c:out value="${benefit.benefitUsagePercentage}" /></fmt:formatNumber> %
			
		</td>
    </tr>
   
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	
	</c:forEach>
	
	</tbody>
</table>
<br /> <br />
</c:if>

<c:if test="${dental ne null}">
<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	<tr height="20">
		<td scope="col" align="center" class="listViewThS1" style="text-align: center; font-size: 15;" nowrap="nowrap" width="100%" colspan=11>
				&nbsp;DENTAL</td>
	</tr>

	<tr height="20">
		<td width="6%" nowrap="nowrap" class="listViewThS1" scope="col">
			<img src="images/blank.gif" alt="asd" height="1" width="1">No.</td>		

<!-- ini default generated table from database -->
		   		   		   		   		   		  
			   			
		
		
			   			
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="20%">
				Item Category &nbsp;</td>
			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Calculation Method &nbsp;</td>
			
		
			   			
				
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Cashless Benefit &nbsp;</td>
                                        
                                        <td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Reimburse Benefit &nbsp;</td>
			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Max Ocr &nbsp;</td>
		
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Max Ocr / Case&nbsp;</td>
			   	
			   	<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Remaining &nbsp;</td>
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Usage &nbsp;</td>
			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="9%">
				Sharing &nbsp;</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">
				EDC &nbsp;</td>
			  <td scope="col" class="listViewThS1" nowrap="nowrap" width="8%">Occurs&nbsp;
	   </td>
	    <td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Usage %&nbsp;
	   </td>
	</tr>


	<c:forEach items="${dental}" var="benefit" varStatus="status" >
	
	 <tr height="20">
      <td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${status.index + 1}" />.</td>

      		   		   		   		   		   		   		
					   		   		
					   		
		<td class="oddListRowS1" bgcolor="#e7f0fe"  valign="top">
			<a  href="claimitem?claimId=<c:out value="${claimId}" />&navigation=usagetrack&memberBenefitId=<c:out value="${benefit.memberBenefitId}" />&itemCategoryId=<c:out value="${benefit.itemCategoryId.itemCategoryId}" />&prev=listclaim" class="linkDetail" >
				<c:out value="${benefit.itemCategoryId.itemCategoryName}" /> [ <c:out value="${benefit.itemCategoryId.itemCategoryCode}" /> ]
			</a>
			(<c:out value="${benefit.effectiveDate}" /> s/d <c:out value="${benefit.expireDate}" />)
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
			
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
			
					   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;">
			
				<c:choose>
					<c:when test="${benefit.isAsCharge}">
					AS CHARGE
					</c:when>
					<c:otherwise >
						<fmt:formatNumber><c:out value="${benefit.benefitLimit}" /></fmt:formatNumber>
					</c:otherwise>
				</c:choose>
			
		</td>
                			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;">
			
				<c:choose>
					<c:when test="${benefit.reimbursementAsCharge}">
					AS CHARGE
					</c:when>
					<c:otherwise >
						<fmt:formatNumber><c:out value="${benefit.reimbursementBenefitLimit}" /></fmt:formatNumber>
					</c:otherwise>
				</c:choose>
			
		</td>
			

		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
				<c:choose>
					<c:when test="${benefit.maxBenefitOccurance eq -1}">
					UNLIMITED
					</c:when>
					<c:otherwise >
						<fmt:formatNumber><c:out value="${benefit.maxBenefitOccurance}" /></fmt:formatNumber>
					</c:otherwise>
				</c:choose>
						
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
				<c:choose>
					<c:when test="${benefit.maxOccurancePerCase eq -1}">
					UNLIMITED
					</c:when>
					<c:otherwise >
						<fmt:formatNumber><c:out value="${benefit.maxOccurancePerCase}" /></fmt:formatNumber>
					</c:otherwise>
				</c:choose>
			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
			
				<c:choose>
					<c:when test="${benefit.currentBenefitPosition eq -1}">
					UNLIMITED
					</c:when>
					<c:otherwise >
						<fmt:formatNumber><c:out value="${benefit.currentBenefitPosition}" /></fmt:formatNumber>
					</c:otherwise>
				</c:choose>
			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
			
				<c:choose>
					<c:when test="${benefit.benefitUsage eq -1}">
					UNLIMITED
					</c:when>
					<c:otherwise >
						<fmt:formatNumber><c:out value="${benefit.benefitUsage}" /></fmt:formatNumber>
					</c:otherwise>
				</c:choose>
			
		</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<c:choose>
					<c:when test="${benefit.costSharingPercentage < 100}">
						<fmt:formatNumber><c:out value="${benefit.costSharingPercentage}" /></fmt:formatNumber> %
					</c:when>
					<c:otherwise>
					<center>-</center>
					</c:otherwise>
					
				</c:choose>
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
				<c:if test="${benefit.isEDCEnabled}">EDC
					<c:if test="${benefit.benefitLimitPublishedOnEdc eq 1}">(Y)</c:if><c:if test="${benefit.benefitLimitPublishedOnEdc eq 0}">(N)</c:if>
				</c:if>
				<c:if test="${not benefit.isEDCEnabled}">SHOW</c:if>							
			</td>
	   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
			
				<fmt:formatNumber><c:out value="${benefit.benefitOccuranceUsage}" /></fmt:formatNumber>
			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
			
				<fmt:formatNumber><c:out value="${benefit.benefitUsagePercentage}" /></fmt:formatNumber> %
			
		</td>
    </tr>
   
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	
	</c:forEach>
	
	</tbody>
</table>
<br /> <br />
</c:if>


<c:if test="${mcu ne null}">
<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	<tr height="20">
		<td scope="col" align="center" class="listViewThS1" style="text-align: center; font-size: 15;" nowrap="nowrap" width="100%" colspan=10>
				&nbsp;MEDICAL CHECK UP</td>
	</tr>

	<tr height="20">
		<td width="6%" nowrap="nowrap" class="listViewThS1" scope="col">
			<img src="images/blank.gif" alt="asd" height="1" width="1">No.</td>		

<!-- ini default generated table from database -->
		   		   		   		   		   		  
			   			
		
		
			   			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="20%">
				Item Category &nbsp;</td>
			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Calculation Method &nbsp;</td>
			
		
			   	
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Cashless Benefit &nbsp;</td>
                                        
                                        <td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Reimburse Benefit &nbsp;</td>
			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Max Ocr &nbsp;</td>
		
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Max Ocr / Case&nbsp;</td>
			
			  	<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Remaining &nbsp;</td>
			   			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Usage &nbsp;</td>
			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="9%">
				Sharing &nbsp;</td>
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">
				EDC &nbsp;</td>
		  <td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Occurs&nbsp;
	   </td>
	   <td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Usage %&nbsp;
	   </td>
	</tr>


	<c:forEach items="${mcu}" var="benefit" varStatus="status" >

	 <tr height="20">
      <td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${status.index + 1}" />.</td>

      		   		   		   		   		   		   		
					   		   		
					   		
		<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top">
			<a  href="claimitem?claimId=<c:out value="${claimId}" />&navigation=usagetrack&memberBenefitId=<c:out value="${benefit.memberBenefitId}" />&itemCategoryId=<c:out value="${benefit.itemCategoryId.itemCategoryId}" />&prev=listclaim" class="linkDetail" >
				<c:out value="${benefit.itemCategoryId.itemCategoryName}" /> [ <c:out value="${benefit.itemCategoryId.itemCategoryCode}" /> ]
			</a>
			(<c:out value="${benefit.effectiveDate}" /> s/d <c:out value="${benefit.expireDate}" />)
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
			
					   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;">
			
				<c:choose>
					<c:when test="${benefit.isAsCharge}">
					AS CHARGE
					</c:when>
					<c:otherwise >
						<fmt:formatNumber><c:out value="${benefit.benefitLimit}" /></fmt:formatNumber>
					</c:otherwise>
				</c:choose>
			
		</td>
                			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;">
			
				<c:choose>
					<c:when test="${benefit.reimbursementAsCharge}">
					AS CHARGE
					</c:when>
					<c:otherwise >
						<fmt:formatNumber><c:out value="${benefit.reimbursementBenefitLimit}" /></fmt:formatNumber>
					</c:otherwise>
				</c:choose>
			
		</td>

			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">

				<c:choose>
					<c:when test="${benefit.maxBenefitOccurance eq -1}">
					UNLIMITED
					</c:when>
					<c:otherwise >
						<fmt:formatNumber><c:out value="${benefit.maxBenefitOccurance}" /></fmt:formatNumber>
					</c:otherwise>
				</c:choose>
						
		</td>

		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
				<c:choose>
					<c:when test="${benefit.maxOccurancePerCase eq -1}">
					UNLIMITED
					</c:when>
					<c:otherwise >
						<fmt:formatNumber><c:out value="${benefit.maxOccurancePerCase}" /></fmt:formatNumber>
					</c:otherwise>
				</c:choose>
			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
			
				<c:choose>
					<c:when test="${benefit.currentBenefitPosition eq -1}">
					UNLIMITED
					</c:when>
					<c:otherwise >
						<fmt:formatNumber><c:out value="${benefit.currentBenefitPosition}" /></fmt:formatNumber>
					</c:otherwise>
				</c:choose>
			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
			
				<c:choose>
					<c:when test="${benefit.benefitUsage eq -1}">
					UNLIMITED
					</c:when>
					<c:otherwise >
						<fmt:formatNumber><c:out value="${benefit.benefitUsage}" /></fmt:formatNumber>
					</c:otherwise>
				</c:choose>
			
		</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
			<c:choose>
					<c:when test="${benefit.costSharingPercentage < 100}">
						<fmt:formatNumber><c:out value="${benefit.costSharingPercentage}" /></fmt:formatNumber> %
					</c:when>
					<c:otherwise>
					<center>-</center>
					</c:otherwise>
					
				</c:choose>
				
			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
				<c:if test="${benefit.isEDCEnabled}">EDC
					<c:if test="${benefit.benefitLimitPublishedOnEdc eq 1}">(Y)</c:if><c:if test="${benefit.benefitLimitPublishedOnEdc eq 0}">(N)</c:if>
				</c:if>
				<c:if test="${not benefit.isEDCEnabled}">SHOW</c:if>							
			</td>
	   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
			
				<fmt:formatNumber><c:out value="${benefit.benefitOccuranceUsage}" /></fmt:formatNumber>
			
		</td>
		
					<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
			
				<fmt:formatNumber><c:out value="${benefit.benefitUsagePercentage}" /></fmt:formatNumber> %
			
		</td>
		
    </tr>
   
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	
	</c:forEach>
	
	</tbody>
</table>
<br /> <br />
</c:if>

<c:if test="${misc ne null}">
<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	<tr height="20">
		<td scope="col" align="center" class="listViewThS1" style="text-align: center; font-size: 15;" nowrap="nowrap" width="100%" colspan=10>
				&nbsp;PELAYANAN LAIN LAIN</td>
	</tr>

	<tr height="20">
		<td width="6%" nowrap="nowrap" class="listViewThS1" scope="col">
			<img src="images/blank.gif" alt="asd" height="1" width="1">No.</td>		

<!-- ini default generated table from database -->
		   		   		   		   		   		  
			   			
		
		
			   			
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="20%">
				Item Category &nbsp;</td>
			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Calculation Method &nbsp;</td>
			
		
			   			
				
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Cashless Benefit &nbsp;</td>
                                        
                                        <td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Reimburse Benefit &nbsp;</td>
			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Max Ocr &nbsp;</td>
		
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Max Ocr / Case&nbsp;</td>
			   	
			   	<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Benefit Remaining &nbsp;</td>
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Benefit Usage &nbsp;</td>
			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="9%">
				Sharing &nbsp;</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">
				EDC &nbsp;</td>
			  <td scope="col" class="listViewThS1" nowrap="nowrap" width="8%">Occurs&nbsp;
	   </td>
	    <td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Usage %&nbsp;
	   </td>
	</tr>


	<c:forEach items="${misc}" var="benefit" varStatus="status" >

	 <tr height="20">
      <td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${status.index + 1}" />.</td>

      		   		   		   		   		   		   		
					   		   		
					   		
		<td class="oddListRowS1" bgcolor="#e7f0fe"  valign="top">
			<a href="claimitem?memberId=<c:out value="${memberId}" />&navigation=usagetrack&memberBenefitId=<c:out value="${benefit.memberBenefitId}" />&itemCategoryId=<c:out value="${benefit.itemCategoryId.itemCategoryId}" />&prev=listmember" class="linkDetail">
				<c:out value="${benefit.itemCategoryId.itemCategoryName}" /> [ <c:out value="${benefit.itemCategoryId.itemCategoryCode}" /> ]
			</a>
			(<c:out value="${benefit.effectiveDate}" /> s/d <c:out value="${benefit.expireDate}" />)
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
			
					   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;">
			
				<c:choose>
					<c:when test="${benefit.isAsCharge}">
					AS CHARGE
					</c:when>
					<c:otherwise >
						<fmt:formatNumber><c:out value="${benefit.benefitLimit}" /></fmt:formatNumber>
					</c:otherwise>
				</c:choose>
			
		</td>
                			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;">
			
				<c:choose>
					<c:when test="${benefit.reimbursementAsCharge}">
					AS CHARGE
					</c:when>
					<c:otherwise >
						<fmt:formatNumber><c:out value="${benefit.reimbursementBenefitLimit}" /></fmt:formatNumber>
					</c:otherwise>
				</c:choose>
			
		</td>
			
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
			
				<c:choose>
					<c:when test="${benefit.maxBenefitOccurance eq -1}">
					UNLIMITED
					</c:when>
					<c:otherwise >
						<fmt:formatNumber><c:out value="${benefit.maxBenefitOccurance}" /></fmt:formatNumber>
					</c:otherwise>
				</c:choose>
			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
			
				<c:choose>
					<c:when test="${benefit.maxOccurancePerCase eq -1}">
					UNLIMITED
					</c:when>
					<c:otherwise >
						<fmt:formatNumber><c:out value="${benefit.maxOccurancePerCase}" /></fmt:formatNumber>
					</c:otherwise>
				</c:choose>
			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
			
				<c:choose>
					<c:when test="${benefit.currentBenefitPosition eq -1}">
					UNLIMITED
					</c:when>
					<c:otherwise >
						<fmt:formatNumber><c:out value="${benefit.currentBenefitPosition}" /></fmt:formatNumber>
					</c:otherwise>
				</c:choose>
			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
			
				<c:choose>
					<c:when test="${benefit.benefitUsage eq -1}">
					UNLIMITED
					</c:when>
					<c:otherwise >
						<fmt:formatNumber><c:out value="${benefit.benefitUsage}" /></fmt:formatNumber>
					</c:otherwise>
				</c:choose>
			
		</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
					<c:choose>
					<c:when test="${benefit.costSharingPercentage < 100}">
						<fmt:formatNumber><c:out value="${benefit.costSharingPercentage}" /></fmt:formatNumber> %
					</c:when>
					<c:otherwise>
					<center>-</center>
					</c:otherwise>
					
				</c:choose>
			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
				<c:if test="${benefit.isEDCEnabled}">EDC
					<c:if test="${benefit.benefitLimitPublishedOnEdc eq 1}">(Y)</c:if><c:if test="${benefit.benefitLimitPublishedOnEdc eq 0}">(N)</c:if>
				</c:if>
				<c:if test="${not benefit.isEDCEnabled}">SHOW</c:if>							
			</td>
	   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
			
				<fmt:formatNumber><c:out value="${benefit.benefitOccuranceUsage}" /></fmt:formatNumber>
			
		</td>
		
    </tr>
   
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	
	</c:forEach>
	
	</tbody>
</table>
<br /> <br />
</c:if>

</form>
<!-- Table Content Stop -->
	<!-- Table Toolbar Start -->
	
		<!-- Table Toolbar Stop -->



<!-- Table Container Stop -->
<script language="Javascript">
$(document).ready(function(){
	$.get("firstcall?navigation=jsontotalclaimelog&claimId=<c:out value="${claim.claimId }"/>", function(data){
	  //alert("Data: " + data);
	  if(data>0){
	  	blinker();
	  }
	});
});
function blinker(){
	document.getElementById("errorLog").style.backgroundColor="red";
	setTimeout("document.getElementById('errorLog').style.backgroundColor=''", 500);
	setTimeout("blinker()",1500);
}
<%
String nav="";
if(request.getAttribute("navigation").equals("gosearch")||request.getAttribute("navigation").equals("golookup")){
	nav = (String)request.getAttribute("navigation");
}
%>
function tambahi (){
	document.form1.navigation.value = "tambah";
	document.form1.action = "claiminvestigation-form";
	document.form1.method = "GET";
	document.form1.submit();
}
function hapus (idx){
	var delConfirm = window.confirm ("Are You Sure Want To Delete This Entry ?");

	if (delConfirm){
		document.form1.method = "POST";
		document.form1.claimInvestigationId.value = idx;
		document.form1.action = "claiminvestigation";
		document.form1.navigation.value = "delete";
		document.form1.submit();
	}
}
function ubah (idx){
	document.form1.method = "GET";
	document.form1.claimInvestigationId.value = idx;
	document.form1.action = "claiminvestigation-form";
	document.form1.navigation.value = "edit";
	document.form1.submit();
}
function goleft(){
	document.form1.navigation.value = "<%=nav%>";
	document.form1.arah.value="kiri";
	document.form1.method = "POST";
	document.form1.submit();
}
function goleftbgt(){
	document.form1.navigation.value = "<%=nav%>";
	document.form1.arah.value="kiribgt";
	document.form1.method = "POST";
	document.form1.submit();
}
function goright(){
	document.form1.navigation.value = "<%=nav%>";
	document.form1.arah.value="kanan";
	document.form1.method = "POST";
	document.form1.submit();
}
function gorightbgt(){
	document.form1.navigation.value = "<%=nav%>";
	document.form1.arah.value="kananbgt";
	document.form1.method = "POST";
	document.form1.submit();
}
function go(){
	document.form1.navigation.value = "<%=nav%>";
	document.form1.submit();
}
function cari (){
	document.form1.navigation.value = "gosearch";
	document.form1.action = "claiminvestigation";
	document.form1.method = "POST";
	document.form1.submit();
}
function detil (idx){
	document.form1.method = "POST";
	document.form1.claimInvestigationId.value = idx;
	document.form1.action = "claiminvestigation";
	document.form1.navigation.value = "detail";
	document.form1.submit();

}
function printErrorLog(){
	window.open ("firstcall?navigation=searchclaimerrorlog&claimId=<c:out value="${claim.claimId}" />","Search","width=1024, height=768, menubar=yes, status=no, toolbar=no, scrollbars=yes, resizable=yes");		
}

function adderrorlog (){ 
	window.location.href = "firstcall-form?navigation=claimelog&claimId=<c:out value="${claim.claimId}" />&&memberId=<c:out value="${claim.memberId.memberId}" />";
}
</script>
