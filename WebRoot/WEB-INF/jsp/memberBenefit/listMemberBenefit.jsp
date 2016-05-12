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

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;List Member Benefit</h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
      <c:if test="${theUser.userType eq 2 }">
	      <td align="right">
	      	<input title="Error Log"  name="errorLog" id="errorLog" value=" Error Log " class="errorLog" type="button" onClick="javascript:printErrorLog()">
	      </td>
	      <td align="right">
	      	<input title="Add Error Log"  name="addErrorLog" value=" Add Error Log " class="errorLog" type="button" onClick="javascript:adderrorlog()">
	      </td>
      </c:if>
    </tr>
  </tbody>
</table>

<!-- Search Container Start -->
<%@ include file="../mainContentMember.jsp" %>

<br/>
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

<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	<tr height="20">
		<td scope="col" align="center" class="listViewThS1" style="text-align: center; font-size: 15;" nowrap="nowrap" width="100%" colspan=20>
				&nbsp;INPATIENT &nbsp;&nbsp; 				
				<c:choose>
					<c:when test="${inpatientProduct.annualBenefit ne null}">
						<c:if test="${inpatientProduct.annualBenefit gt -1}">
							| &nbsp;&nbsp; LIMIT = <fmt:formatNumber><c:out value="${inpatientProduct.annualBenefit}" /></fmt:formatNumber>
						</c:if>
						<c:if test="${inpatientProduct.annualBenefit eq -1}">
							| &nbsp;&nbsp; LIMIT = AS CHARGE
						</c:if>
					</c:when>
					<c:otherwise>
						| &nbsp;&nbsp; LIMIT = -
					</c:otherwise>
				</c:choose>
		</td>
	</tr>

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
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="2%" style="text-align: center;">SMM &nbsp;</td>
			<c:if test="${theUser.userType eq 2}">
				<td scope="col" class="listViewThS1" width="5%" style="text-align: center;">
				Max Lim Per Case. / Day &nbsp;</td>
		
				<td scope="col" class="listViewThS1" width="5%" style="text-align: center;">
				Max Occur. / Year &nbsp;</td>				
		
					<td scope="col" class="listViewThS1" width="3%" style="text-align: center;">
				Max Occur. per Case / Day&nbsp;</td>
			   			
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
	<c:forEach items="${inpatient}" var="benefit" varStatus="status" >
		 <tr height="20">
		      <td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${status.index + 1}" />.</td>
		
		      		   		   		   		   		   		   		
							   		   		
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" >
					<c:out value="${benefit.itemCategoryId.itemCategoryCode}" /> <c:if test="${benefit.itemCategoryId.itemCategoryAlternateCode ne null}">/</c:if> <c:out value="${benefit.itemCategoryId.itemCategoryAlternateCode}" />
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" >
					<c:out value="${benefit.clientItemCode}" /> 
				</td>			   					   		
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
					[<c:out value="${benefit.caseCategoryId.caseCategoryCode}" />] <a href="claimitem?memberId=<c:out value="${memberId}" />&navigation=usagetrack&memberBenefitId=<c:out value="${benefit.memberBenefitId}" />&itemCategoryId=<c:out value="${benefit.itemCategoryId.itemCategoryId}" />&prev=listmember" class="linkDetail"><c:out value="${benefit.itemCategoryId.itemCategoryName}" /></a>		
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
									<c:when test="${benefit.benefitLimit ne null and benefit.cashlessPercentage ne null}">
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
										-
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
									<c:when test="${benefit.reimbursementBenefitLimit ne null and benefit.reimbursementPercentage ne null}">
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
										-
									</c:otherwise>						
								</c:choose>
							</c:when>
							<c:otherwise>
								SHARED
							</c:otherwise>
						</c:choose>
					
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top" style="text-align: center;">
					<c:if test="${benefit.memberLimitLayerId ne null and (benefit.memberLimitLayerId.diagnosisId ne null or benefit.memberLimitLayerId.diagnosisSetId ne null)}">Y</c:if>
					<c:if test="${benefit.memberLimitLayerId eq null }">N</c:if>
				</td>
					<c:if test="${theUser.userType eq 2}">
						<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
							<c:choose>
								<c:when test="${benefit.maxBenefitPerCase ne null}">
									<c:if test="${benefit.maxBenefitPerCase ne -1}">			
										<fmt:formatNumber><c:out value="${benefit.maxBenefitPerCase}" /></fmt:formatNumber>
									</c:if>
									<c:if test="${benefit.maxBenefitPerCase eq -1}">			
										AS CHARGE
									</c:if>
								</c:when>
								<c:otherwise>
									-
								</c:otherwise>
							</c:choose>			
						</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
					<c:choose>
						<c:when test="${benefit.maxBenefitOccurance ne null}">
							<c:if test="${benefit.maxBenefitOccurance ne -1}">			
								<fmt:formatNumber><c:out value="${benefit.maxBenefitOccurance}" /></fmt:formatNumber>
							</c:if>
							<c:if test="${benefit.maxBenefitOccurance eq -1}">			
								UNLIMITED
							</c:if>
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>			
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
					<c:choose>
						<c:when test="${benefit.maxOccurancePerCase ne null}">
							<c:if test="${benefit.maxOccurancePerCase ne -1}">			
								<fmt:formatNumber><c:out value="${benefit.maxOccurancePerCase}" /></fmt:formatNumber>
							</c:if>			
							<c:if test="${benefit.maxOccurancePerCase eq -1}">
								UNLIMITED
							</c:if>
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">						
						<c:choose>
							<c:when test="${benefit.benefitUsage ne null and benefit.benefitOccuranceUsage ne null}">
								<fmt:formatNumber><c:out value="${benefit.benefitUsage}" /></fmt:formatNumber> - [<b><fmt:formatNumber><c:out value="${benefit.benefitOccuranceUsage}" /></fmt:formatNumber></b> ]
							</c:when>
							<c:otherwise>
								-
							</c:otherwise>
						</c:choose>			
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">			
						<c:choose>
							<c:when test="${benefit.deductibleLimit ne null}">
								<fmt:formatNumber><c:out value="${benefit.deductibleLimit}" /></fmt:formatNumber>
							</c:when>
							<c:otherwise>
								-
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
					<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;"> 
						<c:choose>
							<c:when test="${benefit.costSharingAmount ne null}">
								<fmt:formatNumber><c:out value="${benefit.costSharingAmount}" /></fmt:formatNumber>
							</c:when>
							<c:otherwise>
								-
							</c:otherwise>
						</c:choose>				
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
<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	<tr height="20">
		<td scope="col" align="center" class="listViewThS1" style="text-align: center; font-size: 15;" nowrap="nowrap" width="100%" colspan=20>
				&nbsp;COVERAGE LIMIT INFORMATION</td>
	</tr>

	<tr height="20">
		<td width="2%" nowrap="nowrap" class="listViewThS1" scope="col">No.</td>						
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%" style="text-align: center;">Product Limit Layer &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%" style="text-align: center;">Diagnosis &nbsp;</td>
		<td scope="col" class="listViewThS1" width="5%" style="text-align: center;">Actual Limit &nbsp;</td>
		<td scope="col" class="listViewThS1" width="5%" style="text-align: center;">Annual Limit &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%" style="text-align: center;">Usage &nbsp;</td>
		<td scope="col" class="listViewThS1" width="3%" style="text-align: center;">Usage Percentage&nbsp;</td>				
		<td scope="col" class="listViewThS1" width="3%" style="text-align: center;">Deductible &nbsp;</td>
		<td scope="col" class="listViewThS1" width="3%" style="text-align: center;">Share (%) &nbsp;</td>				
		<td scope="col" class="listViewThS1" width="3%" style="text-align: center;">Diagnosis List (SMM) &nbsp;</td>
	</tr>
	<tr height="20">
    	<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"></td>				   		   		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" ><b>Layer Utama</b></td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" ></td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">		
			<c:choose>
				<c:when test="${inpatientProduct.actualBenefitLimit ne null}">
					<b><fmt:formatNumber><c:out value="${inpatientProduct.actualBenefitLimit}" /></fmt:formatNumber></b>
				</c:when>
				<c:otherwise>
					-
				</c:otherwise>
			</c:choose>	
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
			<c:choose>
				<c:when test="${inpatientProduct.annualBenefit ne null}">
					<b><fmt:formatNumber><c:out value="${inpatientProduct.annualBenefit}" /></fmt:formatNumber></b>
				</c:when>
				<c:otherwise>
					-
				</c:otherwise>
			</c:choose>						
		</td>			
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;"> 
			<c:choose>
				<c:when test="${inpatientProduct.benefitUsage ne null}">
					<b><fmt:formatNumber><c:out value="${inpatientProduct.benefitUsage}" /> </fmt:formatNumber></b>
				</c:when>
				<c:otherwise>
					-
				</c:otherwise>
			</c:choose>				
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
			<c:choose>
				<c:when test="${inpatientProduct.benefitUsage ne null and inpatientProduct.annualBenefit ne null}">
					<b><fmt:formatNumber><c:out value="${inpatientProduct.benefitUsage / inpatientProduct.annualBenefit * 100}" /> </fmt:formatNumber> %</b>
				</c:when>
				<c:otherwise>
					-
				</c:otherwise>
			</c:choose>			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">			
						
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;"></td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;"></td>		
   	</tr>   
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	<c:forEach items="${inpatientLayer}" var="benefit" varStatus="status" >
	 	<tr height="20">
      		<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${status.index + 1}" />.</td>				   		   		
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" >
				<a href="javascript:popupBenefitList('<c:out value="${benefit.memberLimitLayerId}" />')" class="linkDetail">Layer - <c:out value="${benefit.layer}" /></a>
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" >
				<c:out value="${benefit.diagnosisSetId.diagnosisSetName}" /> 
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
				<c:if test="${benefit.familyLimitLayerId eq null }">
					<c:choose>
						<c:when test="${benefit.actualBenefitLimit ne null}">
							<fmt:formatNumber><c:out value="${benefit.actualBenefitLimit}" /></fmt:formatNumber>
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>		
				</c:if>
				<c:if test="${benefit.familyLimitLayerId ne null }">
					<c:choose>
						<c:when test="${benefit.familyLimitLayerId.actualBenefitLimit ne null}">
							<fmt:formatNumber><c:out value="${benefit.familyLimitLayerId.actualBenefitLimit}" /></fmt:formatNumber>
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>		
				</c:if>		
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
				<c:if test="${benefit.familyLimitLayerId eq null }">
					<c:choose>
						<c:when test="${benefit.annualLimit ne null}">
							<fmt:formatNumber><c:out value="${benefit.annualLimit}" /></fmt:formatNumber>
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>						
				</c:if>
				<c:if test="${benefit.familyLimitLayerId ne null }">
					<c:choose>
						<c:when test="${benefit.familyLimitLayerId.annualLimit ne null}">
							<fmt:formatNumber><c:out value="${benefit.familyLimitLayerId.annualLimit}" /></fmt:formatNumber>
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>						
				</c:if>
			</td>			
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
				<c:if test="${benefit.familyLimitLayerId eq null }">
					<c:choose>
						<c:when test="${benefit.benefitUsage ne null}">
							<fmt:formatNumber><c:out value="${benefit.benefitUsage}" /> </fmt:formatNumber>	
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>			
				</c:if>
				<c:if test="${benefit.familyLimitLayerId ne null }">
					<c:choose>
						<c:when test="${benefit.familyLimitLayerId.benefitUsage ne null}">
							<fmt:formatNumber><c:out value="${benefit.familyLimitLayerId.benefitUsage}" /> </fmt:formatNumber>	
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>			
				</c:if>
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
				<c:if test="${benefit.familyLimitLayerId eq null }">
					<c:choose>
						<c:when test="${benefit.benefitUsage ne null and benefit.annualLimit ne null}">
							<fmt:formatNumber><c:out value="${benefit.benefitUsage / benefit.annualLimit * 100}" /> </fmt:formatNumber> %	
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>				
				</c:if>
				<c:if test="${benefit.familyLimitLayerId ne null }">
					<c:choose>
						<c:when test="${benefit.familyLimitLayerId.benefitUsage ne null and benefit.familyLimitLayerId.annualLimit ne null}">
							<fmt:formatNumber><c:out value="${benefit.familyLimitLayerId.benefitUsage / benefit.familyLimitLayerId.annualLimit * 100}" /> </fmt:formatNumber> %	
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>				
				</c:if>
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
				<c:if test="${benefit.familyLimitLayerId eq null }">
				<c:choose>
					<c:when test="${benefit.deductible ne null}">
						<fmt:formatNumber><c:out value="${benefit.deductible}" /></fmt:formatNumber>	
					</c:when>
					<c:otherwise>
						-
					</c:otherwise>
				</c:choose>			
				</c:if>
				<c:if test="${benefit.familyLimitLayerId ne null }">
				<c:choose>
					<c:when test="${benefit.familyLimitLayerId.deductible ne null}">
						<fmt:formatNumber><c:out value="${benefit.familyLimitLayerId.deductible}" /></fmt:formatNumber>	
					</c:when>
					<c:otherwise>
						-
					</c:otherwise>
				</c:choose>			
				</c:if>
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;">	
				<c:if test="${benefit.familyLimitLayerId eq null }">		
				<c:choose>
					<c:when test="${benefit.coSharePercentage < 100}">
						<fmt:formatNumber><c:out value="${benefit.coSharePercentage}" /></fmt:formatNumber> %
					</c:when>
					<c:otherwise>
						<center>-</center>
					</c:otherwise>
					
				</c:choose>
				</c:if>
				<c:if test="${benefit.familyLimitLayerId ne null }">		
				<c:choose>
					<c:when test="${benefit.familyLimitLayerId.coSharePercentage < 100}">
						<fmt:formatNumber><c:out value="${benefit.familyLimitLayerId.coSharePercentage}" /></fmt:formatNumber> %
					</c:when>
					<c:otherwise>
						<center>-</center>
					</c:otherwise>
					
				</c:choose>
				</c:if>
			</td>	
<!-- 			Andre Munculin SMM -->
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: center;">			
				<c:if test="${benefit.diagnosisSetId ne null}">
					<a href="javascript:popupDiagnosisList('<c:out value="${benefit.diagnosisSetId.diagnosisSetId}" />')" class="linkDetail">DIAGNOSIS LIST</a>
				</c:if>
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
<c:if test="${prePostInpatient ne null}">		

<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	<tr height="20">
		<td scope="col" align="center" class="listViewThS1" style="text-align: center; font-size: 15;" nowrap="nowrap" width="100%" colspan=20>
				&nbsp;PRE & POST INPATIENT &nbsp;&nbsp; 				
				<c:choose>
					<c:when test="${prePostInpatientProduct.annualBenefit ne null}">
						<c:if test="${prePostInpatientProduct.annualBenefit gt -1}">
							| &nbsp;&nbsp; LIMIT = <fmt:formatNumber><c:out value="${prePostInpatientProduct.annualBenefit}" /></fmt:formatNumber>
						</c:if>
						<c:if test="${prePostInpatientProduct.annualBenefit eq -1}">
							| &nbsp;&nbsp; LIMIT = AS CHARGE
						</c:if>
					</c:when>
					<c:otherwise>
						| &nbsp;&nbsp; LIMIT = -
					</c:otherwise>
				</c:choose>
		</td>
	</tr>

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
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="2%" style="text-align: center;">SMM &nbsp;</td>
			<c:if test="${theUser.userType eq 2}">
				<td scope="col" class="listViewThS1" width="5%" style="text-align: center;">
				Max Lim Per Case. / Day &nbsp;</td>
		
				<td scope="col" class="listViewThS1" width="5%" style="text-align: center;">
				Max Occur. / Year &nbsp;</td>				
		
					<td scope="col" class="listViewThS1" width="3%" style="text-align: center;">
				Max Occur. per Case / Day&nbsp;</td>
			   			
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
	<c:forEach items="${prePostInpatient}" var="benefit" varStatus="status" >
		 <tr height="20">
		      <td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${status.index + 1}" />.</td>
		
		      		   		   		   		   		   		   		
							   		   		
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" >
					<c:out value="${benefit.itemCategoryId.itemCategoryCode}" /> <c:if test="${benefit.itemCategoryId.itemCategoryAlternateCode ne null}">/</c:if> <c:out value="${benefit.itemCategoryId.itemCategoryAlternateCode}" />
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" >
					<c:out value="${benefit.clientItemCode}" /> 
				</td>			   					   		
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
					[<c:out value="${benefit.caseCategoryId.caseCategoryCode}" />] <a href="claimitem?memberId=<c:out value="${memberId}" />&navigation=usagetrack&memberBenefitId=<c:out value="${benefit.memberBenefitId}" />&itemCategoryId=<c:out value="${benefit.itemCategoryId.itemCategoryId}" />&prev=listmember" class="linkDetail"><c:out value="${benefit.itemCategoryId.itemCategoryName}" /></a>		
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
									<c:when test="${benefit.benefitLimit ne null and benefit.cashlessPercentage ne null}">
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
										-
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
									<c:when test="${benefit.reimbursementBenefitLimit ne null and benefit.reimbursementPercentage ne null}">
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
										-
									</c:otherwise>						
								</c:choose>
							</c:when>
							<c:otherwise>
								SHARED
							</c:otherwise>
						</c:choose>
					
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top" style="text-align: center;">
					<c:if test="${benefit.memberLimitLayerId ne null and (benefit.memberLimitLayerId.diagnosisId ne null or benefit.memberLimitLayerId.diagnosisSetId ne null)}">Y</c:if>
					<c:if test="${benefit.memberLimitLayerId eq null }">N</c:if>
				</td>
					<c:if test="${theUser.userType eq 2}">
						<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
							<c:choose>
								<c:when test="${benefit.maxBenefitPerCase ne null}">
									<c:if test="${benefit.maxBenefitPerCase ne -1}">			
										<fmt:formatNumber><c:out value="${benefit.maxBenefitPerCase}" /></fmt:formatNumber>
									</c:if>
									<c:if test="${benefit.maxBenefitPerCase eq -1}">			
										AS CHARGE
									</c:if>
								</c:when>
								<c:otherwise>
									-
								</c:otherwise>
							</c:choose>			
						</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
					<c:choose>
						<c:when test="${benefit.maxBenefitOccurance ne null}">
							<c:if test="${benefit.maxBenefitOccurance ne -1}">			
								<fmt:formatNumber><c:out value="${benefit.maxBenefitOccurance}" /></fmt:formatNumber>
							</c:if>
							<c:if test="${benefit.maxBenefitOccurance eq -1}">			
								UNLIMITED
							</c:if>
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>			
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
					<c:choose>
						<c:when test="${benefit.maxOccurancePerCase ne null}">
							<c:if test="${benefit.maxOccurancePerCase ne -1}">			
								<fmt:formatNumber><c:out value="${benefit.maxOccurancePerCase}" /></fmt:formatNumber>
							</c:if>			
							<c:if test="${benefit.maxOccurancePerCase eq -1}">
								UNLIMITED
							</c:if>
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">						
						<c:choose>
							<c:when test="${benefit.benefitUsage ne null and benefit.benefitOccuranceUsage ne null}">
								<fmt:formatNumber><c:out value="${benefit.benefitUsage}" /></fmt:formatNumber> - [<b><fmt:formatNumber><c:out value="${benefit.benefitOccuranceUsage}" /></fmt:formatNumber></b> ]
							</c:when>
							<c:otherwise>
								-
							</c:otherwise>
						</c:choose>			
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">			
						<c:choose>
							<c:when test="${benefit.deductibleLimit ne null}">
								<fmt:formatNumber><c:out value="${benefit.deductibleLimit}" /></fmt:formatNumber>
							</c:when>
							<c:otherwise>
								-
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
					<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;"> 
						<c:choose>
							<c:when test="${benefit.costSharingAmount ne null}">
								<fmt:formatNumber><c:out value="${benefit.costSharingAmount}" /></fmt:formatNumber>
							</c:when>
							<c:otherwise>
								-
							</c:otherwise>
						</c:choose>				
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
<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	<tr height="20">
		<td scope="col" align="center" class="listViewThS1" style="text-align: center; font-size: 15;" nowrap="nowrap" width="100%" colspan=20>
				&nbsp;COVERAGE LIMIT INFORMATION</td>
	</tr>

	<tr height="20">
		<td width="2%" nowrap="nowrap" class="listViewThS1" scope="col">No.</td>						
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%" style="text-align: center;">Product Limit Layer &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%" style="text-align: center;">Diagnosis &nbsp;</td>
		<td scope="col" class="listViewThS1" width="5%" style="text-align: center;">Actual Limit &nbsp;</td>
		<td scope="col" class="listViewThS1" width="5%" style="text-align: center;">Annual Limit &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%" style="text-align: center;">Usage &nbsp;</td>
		<td scope="col" class="listViewThS1" width="3%" style="text-align: center;">Usage Percentage&nbsp;</td>				
		<td scope="col" class="listViewThS1" width="3%" style="text-align: center;">Deductible &nbsp;</td>
		<td scope="col" class="listViewThS1" width="3%" style="text-align: center;">Share (%) &nbsp;</td>				
		<td scope="col" class="listViewThS1" width="3%" style="text-align: center;">Diagnosis List (SMM) &nbsp;</td>
	</tr>
	<tr height="20">
    	<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"></td>				   		   		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" ><b>Layer Utama</b></td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" ></td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">		
			<c:choose>
				<c:when test="${prePostInpatientProduct.actualBenefitLimit ne null}">
					<b><fmt:formatNumber><c:out value="${prePostInpatientProduct.actualBenefitLimit}" /></fmt:formatNumber></b>
				</c:when>
				<c:otherwise>
					-
				</c:otherwise>
			</c:choose>	
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
			<c:choose>
				<c:when test="${prePostInpatientProduct.annualBenefit ne null}">
					<b><fmt:formatNumber><c:out value="${prePostInpatientProduct.annualBenefit}" /></fmt:formatNumber></b>
				</c:when>
				<c:otherwise>
					-
				</c:otherwise>
			</c:choose>						
		</td>			
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;"> 
			<c:choose>
				<c:when test="${prePostInpatientProduct.benefitUsage ne null}">
					<b><fmt:formatNumber><c:out value="${prePostInpatientProduct.benefitUsage}" /> </fmt:formatNumber></b>
				</c:when>
				<c:otherwise>
					-
				</c:otherwise>
			</c:choose>				
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
			<c:choose>
				<c:when test="${prePostInpatientProduct.benefitUsage ne null and prePostInpatientProduct.annualBenefit ne null}">
					<b><fmt:formatNumber><c:out value="${prePostInpatientProduct.benefitUsage / prePostInpatientProduct.annualBenefit * 100}" /> </fmt:formatNumber> %</b>
				</c:when>
				<c:otherwise>
					-
				</c:otherwise>
			</c:choose>			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">			
						
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;"></td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;"></td>		
   	</tr>   
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	<c:forEach items="${prePostInpatientLayer}" var="benefit" varStatus="status" >
	 	<tr height="20">
      		<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${status.index + 1}" />.</td>				   		   		
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" >
				<a href="javascript:popupBenefitList('<c:out value="${benefit.memberLimitLayerId}" />')" class="linkDetail">Layer - <c:out value="${benefit.layer}" /></a>
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" >
				<c:out value="${benefit.diagnosisSetId.diagnosisSetName}" /> 
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
				<c:if test="${benefit.familyLimitLayerId eq null }">
					<c:choose>
						<c:when test="${benefit.actualBenefitLimit ne null}">
							<fmt:formatNumber><c:out value="${benefit.actualBenefitLimit}" /></fmt:formatNumber>
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>		
				</c:if>
				<c:if test="${benefit.familyLimitLayerId ne null }">
					<c:choose>
						<c:when test="${benefit.familyLimitLayerId.actualBenefitLimit ne null}">
							<fmt:formatNumber><c:out value="${benefit.familyLimitLayerId.actualBenefitLimit}" /></fmt:formatNumber>
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>		
				</c:if>		
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
				<c:if test="${benefit.familyLimitLayerId eq null }">
					<c:choose>
						<c:when test="${benefit.annualLimit ne null}">
							<fmt:formatNumber><c:out value="${benefit.annualLimit}" /></fmt:formatNumber>
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>						
				</c:if>
				<c:if test="${benefit.familyLimitLayerId ne null }">
					<c:choose>
						<c:when test="${benefit.familyLimitLayerId.annualLimit ne null}">
							<fmt:formatNumber><c:out value="${benefit.familyLimitLayerId.annualLimit}" /></fmt:formatNumber>
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>						
				</c:if>
			</td>			
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
				<c:if test="${benefit.familyLimitLayerId eq null }">
					<c:choose>
						<c:when test="${benefit.benefitUsage ne null}">
							<fmt:formatNumber><c:out value="${benefit.benefitUsage}" /> </fmt:formatNumber>	
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>			
				</c:if>
				<c:if test="${benefit.familyLimitLayerId ne null }">
					<c:choose>
						<c:when test="${benefit.familyLimitLayerId.benefitUsage ne null}">
							<fmt:formatNumber><c:out value="${benefit.familyLimitLayerId.benefitUsage}" /> </fmt:formatNumber>	
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>			
				</c:if>
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
				<c:if test="${benefit.familyLimitLayerId eq null }">
					<c:choose>
						<c:when test="${benefit.benefitUsage ne null and benefit.annualLimit ne null}">
							<fmt:formatNumber><c:out value="${benefit.benefitUsage / benefit.annualLimit * 100}" /> </fmt:formatNumber> %	
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>				
				</c:if>
				<c:if test="${benefit.familyLimitLayerId ne null }">
					<c:choose>
						<c:when test="${benefit.familyLimitLayerId.benefitUsage ne null and benefit.familyLimitLayerId.annualLimit ne null}">
							<fmt:formatNumber><c:out value="${benefit.familyLimitLayerId.benefitUsage / benefit.familyLimitLayerId.annualLimit * 100}" /> </fmt:formatNumber> %	
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>				
				</c:if>
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
				<c:if test="${benefit.familyLimitLayerId eq null }">
				<c:choose>
					<c:when test="${benefit.deductible ne null}">
						<fmt:formatNumber><c:out value="${benefit.deductible}" /></fmt:formatNumber>	
					</c:when>
					<c:otherwise>
						-
					</c:otherwise>
				</c:choose>			
				</c:if>
				<c:if test="${benefit.familyLimitLayerId ne null }">
				<c:choose>
					<c:when test="${benefit.familyLimitLayerId.deductible ne null}">
						<fmt:formatNumber><c:out value="${benefit.familyLimitLayerId.deductible}" /></fmt:formatNumber>	
					</c:when>
					<c:otherwise>
						-
					</c:otherwise>
				</c:choose>			
				</c:if>
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;">	
				<c:if test="${benefit.familyLimitLayerId eq null }">		
				<c:choose>
					<c:when test="${benefit.coSharePercentage < 100}">
						<fmt:formatNumber><c:out value="${benefit.coSharePercentage}" /></fmt:formatNumber> %
					</c:when>
					<c:otherwise>
						<center>-</center>
					</c:otherwise>
					
				</c:choose>
				</c:if>
				<c:if test="${benefit.familyLimitLayerId ne null }">		
				<c:choose>
					<c:when test="${benefit.familyLimitLayerId.coSharePercentage < 100}">
						<fmt:formatNumber><c:out value="${benefit.familyLimitLayerId.coSharePercentage}" /></fmt:formatNumber> %
					</c:when>
					<c:otherwise>
						<center>-</center>
					</c:otherwise>
					
				</c:choose>
				</c:if>
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: center;">			
				<c:if test="${benefit.diagnosisSetId ne null}">
					<a href="javascript:popupDiagnosisList('<c:out value="${benefit.diagnosisSetId.diagnosisSetId}" />')" class="linkDetail">DIAGNOSIS LIST</a>
				</c:if>
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
<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	<tr height="20">
		<td scope="col" align="center" class="listViewThS1" style="text-align: center; font-size: 15;" nowrap="nowrap" width="100%" colspan=20>
				&nbsp;OUTPATIENT &nbsp;&nbsp; 				
				<c:choose>
					<c:when test="${outpatientProduct.annualBenefit ne null}">
						<c:if test="${outpatientProduct.annualBenefit gt -1}">
							| &nbsp;&nbsp; LIMIT = <fmt:formatNumber><c:out value="${outpatientProduct.annualBenefit}" /></fmt:formatNumber>
						</c:if>
						<c:if test="${outpatientProduct.annualBenefit eq -1}">
							| &nbsp;&nbsp; LIMIT = AS CHARGE
						</c:if>
					</c:when>
					<c:otherwise>
						| &nbsp;&nbsp; LIMIT = -
					</c:otherwise>
				</c:choose>
		</td>
	</tr>

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
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="2%" style="text-align: center;">SMM &nbsp;</td>
			<c:if test="${theUser.userType eq 2}">
				<td scope="col" class="listV iewThS1" width="5%" style="text-align: center;">
				Max Lim Per Case. / Day &nbsp;</td>
		
				<td scope="col" class="listViewThS1" width="5%" style="text-align: center;">
				Max Occur. / Year &nbsp;</td>				
		
					<td scope="col" class="listViewThS1" width="3%" style="text-align: center;">
				Max Occur. per Case / Day&nbsp;</td>
			   			
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

	<c:forEach items="${outpatient}" var="benefit" varStatus="status" >
			 <tr height="20">
		      <td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${status.index + 1}" />.</td>
		
		      		   		   		   		   		   		   		
							   		   		
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" >
					<c:out value="${benefit.itemCategoryId.itemCategoryCode}" /> <c:if test="${benefit.itemCategoryId.itemCategoryAlternateCode ne null}">/</c:if> <c:out value="${benefit.itemCategoryId.itemCategoryAlternateCode}" />
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" >
					<c:out value="${benefit.clientItemCode}" /> 
				</td>			   					   					   		
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
					[<c:out value="${benefit.caseCategoryId.caseCategoryCode}" />] <a href="claimitem?memberId=<c:out value="${memberId}" />&navigation=usagetrack&memberBenefitId=<c:out value="${benefit.memberBenefitId}" />&itemCategoryId=<c:out value="${benefit.itemCategoryId.itemCategoryId}" />&prev=listmember" class="linkDetail"><c:out value="${benefit.itemCategoryId.itemCategoryName}" /></a>		
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
									<c:when test="${benefit.benefitLimit ne null and benefit.cashlessPercentage ne null}">
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
										-
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
									<c:when test="${benefit.reimbursementBenefitLimit ne null and benefit.reimbursementPercentage ne null}">
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
										-
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
								SHARED
							</c:otherwise>
						</c:choose>
					
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top" style="text-align: center;">
					<c:if test="${benefit.memberLimitLayerId ne null and (benefit.memberLimitLayerId.diagnosisId ne null or benefit.memberLimitLayerId.diagnosisSetId ne null)}">Y</c:if>
					<c:if test="${benefit.memberLimitLayerId eq null }">N</c:if>
				</td>
					<c:if test="${theUser.userType eq 2}">
						<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
							<c:choose>
								<c:when test="${benefit.maxBenefitPerCase ne null}">
									<c:if test="${benefit.maxBenefitPerCase ne -1}">			
										<fmt:formatNumber><c:out value="${benefit.maxBenefitPerCase}" /></fmt:formatNumber>
									</c:if>
									<c:if test="${benefit.maxBenefitPerCase eq -1}">			
										AS CHARGE
									</c:if>
								</c:when>
								<c:otherwise>
									-
								</c:otherwise>
							</c:choose>			
						</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
					<c:choose>
						<c:when test="${benefit.maxBenefitOccurance ne null}">
							<c:if test="${benefit.maxBenefitOccurance ne -1}">			
								<fmt:formatNumber><c:out value="${benefit.maxBenefitOccurance}" /></fmt:formatNumber>
							</c:if>
							<c:if test="${benefit.maxBenefitOccurance eq -1}">			
								UNLIMITED
							</c:if>
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>			
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
					<c:choose>
						<c:when test="${benefit.maxOccurancePerCase ne null}">
							<c:if test="${benefit.maxOccurancePerCase ne -1}">			
								<fmt:formatNumber><c:out value="${benefit.maxOccurancePerCase}" /></fmt:formatNumber>
							</c:if>			
							<c:if test="${benefit.maxOccurancePerCase eq -1}">
								UNLIMITED
							</c:if>
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
					<c:choose>
						<c:when test="${benefit.benefitUsage ne null and benefit.benefitOccuranceUsage ne null}">
							<fmt:formatNumber><c:out value="${benefit.benefitUsage}" /> </fmt:formatNumber> - [ <b><fmt:formatNumber><c:out value="${benefit.benefitOccuranceUsage}" /></fmt:formatNumber></b> ]
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>			
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">						
					<c:choose>
						<c:when test="${benefit.deductibleLimit ne null}">
							<fmt:formatNumber><c:out value="${benefit.deductibleLimit}" /></fmt:formatNumber>
						</c:when>
						<c:otherwise>
							-
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
					<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;">	
						<c:choose>
							<c:when test="${benefit.costSharingAmount ne null}">
								<fmt:formatNumber><c:out value="${benefit.costSharingAmount}" /></fmt:formatNumber>
							</c:when>
							<c:otherwise>
								-
							</c:otherwise>
						</c:choose>
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
<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	<tr height="20">
		<td scope="col" align="center" class="listViewThS1" style="text-align: center; font-size: 15;" nowrap="nowrap" width="100%" colspan=20>
				&nbsp;COVERAGE LIMIT INFORMATION</td>
	</tr>

	<tr height="20">
		<td width="2%" nowrap="nowrap" class="listViewThS1" scope="col">No.</td>						
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%" style="text-align: center;">Product Limit Layer &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%" style="text-align: center;">Diagnosis &nbsp;</td>
		<td scope="col" class="listViewThS1" width="5%" style="text-align: center;">Actual Limit &nbsp;</td>
		<td scope="col" class="listViewThS1" width="5%" style="text-align: center;">Annual Limit &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%" style="text-align: center;">Usage &nbsp;</td>
		<td scope="col" class="listViewThS1" width="3%" style="text-align: center;">Usage Percentage&nbsp;</td>				
		<td scope="col" class="listViewThS1" width="3%" style="text-align: center;">Deductible &nbsp;</td>
		<td scope="col" class="listViewThS1" width="3%" style="text-align: center;">Share (%) &nbsp;</td>				
	</tr>
	<tr height="20">
    	<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"></td>				   		   		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" ><b>Layer Utama</b></td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" ></td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
			<c:choose>
				<c:when test="${outpatientProduct.actualBenefitLimit ne null}">
					<b><fmt:formatNumber><c:out value="${outpatientProduct.actualBenefitLimit}" /></fmt:formatNumber></b>
				</c:when>
				<c:otherwise>
					-
				</c:otherwise>
			</c:choose>			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
			<c:choose>
				<c:when test="${outpatientProduct.annualBenefit ne null}">
					<b><fmt:formatNumber><c:out value="${outpatientProduct.annualBenefit}" /></fmt:formatNumber></b>
				</c:when>
				<c:otherwise>
					-
				</c:otherwise>
			</c:choose>						
		</td>			
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
			<c:choose>
				<c:when test="${outpatientProduct.benefitUsage ne null}">
					<b><fmt:formatNumber><c:out value="${outpatientProduct.benefitUsage}" /> </fmt:formatNumber></b>
				</c:when>
				<c:otherwise>
					-
				</c:otherwise>
			</c:choose> 			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
			<c:choose>
				<c:when test="${outpatientProduct.benefitUsage ne null and outpatientProduct.annualBenefit ne null}">
					<b><fmt:formatNumber><c:out value="${outpatientProduct.benefitUsage / outpatientProduct.annualBenefit * 100}" /> </fmt:formatNumber> %</b>
				</c:when>
				<c:otherwise>
					-
				</c:otherwise>
			</c:choose>			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">			
						
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;"></td>		
   	</tr>   
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	<c:forEach items="${outpatientLayer}" var="benefit" varStatus="status" >
	 	<tr height="20">
      		<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${status.index + 1}" />.</td>				   		   		
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" >
				<a href="javascript:popupBenefitList('<c:out value="${benefit.memberLimitLayerId}" />')" class="linkDetail">Layer - <c:out value="${benefit.layer}" /></a>
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" >
				<c:out value="${benefit.diagnosisSetId.diagnosisSetName}" /> 
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
				<c:if test="${benefit.familyLimitLayerId eq null }">
					<c:choose>
						<c:when test="${benefit.actualBenefitLimit ne null}">
							<fmt:formatNumber><c:out value="${benefit.actualBenefitLimit}" /></fmt:formatNumber>
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>		
				</c:if>
				<c:if test="${benefit.familyLimitLayerId ne null }">
					<c:choose>
						<c:when test="${benefit.familyLimitLayerId.actualBenefitLimit ne null}">
							<fmt:formatNumber><c:out value="${benefit.familyLimitLayerId.actualBenefitLimit}" /></fmt:formatNumber>
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>		
				</c:if>		
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
				<c:if test="${benefit.familyLimitLayerId eq null }">
					<c:choose>
						<c:when test="${benefit.annualLimit ne null}">
							<fmt:formatNumber><c:out value="${benefit.annualLimit}" /></fmt:formatNumber>
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>						
				</c:if>
				<c:if test="${benefit.familyLimitLayerId ne null }">
					<c:choose>
						<c:when test="${benefit.familyLimitLayerId.annualLimit ne null}">
							<fmt:formatNumber><c:out value="${benefit.familyLimitLayerId.annualLimit}" /></fmt:formatNumber>
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>						
				</c:if>
			</td>			
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
				<c:if test="${benefit.familyLimitLayerId eq null }">
					<c:choose>
						<c:when test="${benefit.benefitUsage ne null}">
							<fmt:formatNumber><c:out value="${benefit.benefitUsage}" /> </fmt:formatNumber>	
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>			
				</c:if>
				<c:if test="${benefit.familyLimitLayerId ne null }">
					<c:choose>
						<c:when test="${benefit.familyLimitLayerId.benefitUsage ne null}">
							<fmt:formatNumber><c:out value="${benefit.familyLimitLayerId.benefitUsage}" /> </fmt:formatNumber>	
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>			
				</c:if>
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
				<c:if test="${benefit.familyLimitLayerId eq null }">
					<c:choose>
						<c:when test="${benefit.benefitUsage ne null and benefit.annualLimit ne null}">
							<fmt:formatNumber><c:out value="${benefit.benefitUsage / benefit.annualLimit * 100}" /> </fmt:formatNumber> %	
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>				
				</c:if>
				<c:if test="${benefit.familyLimitLayerId ne null }">
					<c:choose>
						<c:when test="${benefit.familyLimitLayerId.benefitUsage ne null and benefit.familyLimitLayerId.annualLimit ne null}">
							<fmt:formatNumber><c:out value="${benefit.familyLimitLayerId.benefitUsage / benefit.familyLimitLayerId.annualLimit * 100}" /> </fmt:formatNumber> %	
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>				
				</c:if>
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
				<c:if test="${benefit.familyLimitLayerId eq null }">
				<c:choose>
					<c:when test="${benefit.deductible ne null}">
						<fmt:formatNumber><c:out value="${benefit.deductible}" /></fmt:formatNumber>	
					</c:when>
					<c:otherwise>
						-
					</c:otherwise>
				</c:choose>			
				</c:if>
				<c:if test="${benefit.familyLimitLayerId ne null }">
				<c:choose>
					<c:when test="${benefit.familyLimitLayerId.deductible ne null}">
						<fmt:formatNumber><c:out value="${benefit.familyLimitLayerId.deductible}" /></fmt:formatNumber>	
					</c:when>
					<c:otherwise>
						-
					</c:otherwise>
				</c:choose>			
				</c:if>
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;">	
				<c:if test="${benefit.familyLimitLayerId eq null }">		
				<c:choose>
					<c:when test="${benefit.coSharePercentage < 100}">
						<fmt:formatNumber><c:out value="${benefit.coSharePercentage}" /></fmt:formatNumber> %
					</c:when>
					<c:otherwise>
						<center>-</center>
					</c:otherwise>
					
				</c:choose>
				</c:if>
				<c:if test="${benefit.familyLimitLayerId ne null }">		
				<c:choose>
					<c:when test="${benefit.familyLimitLayerId.coSharePercentage < 100}">
						<fmt:formatNumber><c:out value="${benefit.familyLimitLayerId.coSharePercentage}" /></fmt:formatNumber> %
					</c:when>
					<c:otherwise>
						<center>-</center>
					</c:otherwise>
					
				</c:choose>
				</c:if>
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
<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	<tr height="20">
		<td scope="col" align="center" class="listViewThS1" style="text-align: center; font-size: 15;" nowrap="nowrap" width="100%" colspan=20>
				&nbsp;MATERNITY &nbsp;&nbsp; 				
				<c:choose>
					<c:when test="${maternityProduct.annualBenefit ne null}">
						<c:if test="${maternityProduct.annualBenefit gt -1}">
							| &nbsp;&nbsp; LIMIT = <fmt:formatNumber><c:out value="${maternityProduct.annualBenefit}" /></fmt:formatNumber>
						</c:if>
						<c:if test="${maternityProduct.annualBenefit eq -1}">
							| &nbsp;&nbsp; LIMIT = AS CHARGE
						</c:if>
					</c:when>
					<c:otherwise>
						| &nbsp;&nbsp; LIMIT = -
					</c:otherwise>
				</c:choose>
		</td>
	</tr>

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
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="2%" style="text-align: center;">SMM &nbsp;</td>
			<c:if test="${theUser.userType eq 2}">
				<td scope="col" class="listViewThS1" width="5%" style="text-align: center;">
				Max Lim Per Case. / Day &nbsp;</td>
		
				<td scope="col" class="listViewThS1" width="5%" style="text-align: center;">
				Max Occur. / Year &nbsp;</td>				
		
					<td scope="col" class="listViewThS1" width="3%" style="text-align: center;">
				Max Occur. per Case / Day&nbsp;</td>
			   			
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


	<c:forEach items="${maternity}" var="benefit" varStatus="status" >
		
			 <tr height="20">
		      	<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${status.index + 1}" />.</td>
		      	
		      	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" >
					<c:out value="${benefit.itemCategoryId.itemCategoryCode}" /> <c:if test="${benefit.itemCategoryId.itemCategoryAlternateCode ne null}">/</c:if> <c:out value="${benefit.itemCategoryId.itemCategoryAlternateCode}" />
				</td>			   		
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" >
					<c:out value="${benefit.clientItemCode}" /> 
				</td>			   					   		
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
					[<c:out value="${benefit.caseCategoryId.caseCategoryCode}" />] <a href="claimitem?memberId=<c:out value="${memberId}" />&navigation=usagetrack&memberBenefitId=<c:out value="${benefit.memberBenefitId}" />&itemCategoryId=<c:out value="${benefit.itemCategoryId.itemCategoryId}" />&prev=listmember" class="linkDetail"><c:out value="${benefit.itemCategoryId.itemCategoryName}" /></a>		
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
									<c:when test="${benefit.benefitLimit ne null and benefit.cashlessPercentage ne null}">
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
										-
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
									<c:when test="${benefit.reimbursementBenefitLimit ne null and benefit.reimbursementPercentage ne null}">
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
										-
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
								SHARED
							</c:otherwise>
						</c:choose>
					
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top" style="text-align: center;">
					<c:if test="${benefit.memberLimitLayerId ne null and (benefit.memberLimitLayerId.diagnosisId ne null or benefit.memberLimitLayerId.diagnosisSetId ne null)}">Y</c:if>
					<c:if test="${benefit.memberLimitLayerId eq null }">N</c:if>
				</td>
				<c:if test="${theUser.userType eq 2}">
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
					<c:choose>
						<c:when test="${benefit.maxBenefitPerCase ne null}">
							<c:if test="${benefit.maxBenefitPerCase ne -1}">			
								<fmt:formatNumber><c:out value="${benefit.maxBenefitPerCase}" /></fmt:formatNumber>
							</c:if>
							<c:if test="${benefit.maxBenefitPerCase eq -1}">			
								AS CHARGE
							</c:if>
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>			
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
					<c:choose>
						<c:when test="${benefit.maxBenefitOccurance ne null}">
							<c:if test="${benefit.maxBenefitOccurance ne -1}">			
								<fmt:formatNumber><c:out value="${benefit.maxBenefitOccurance}" /></fmt:formatNumber>
							</c:if>
							<c:if test="${benefit.maxBenefitOccurance eq -1}">			
								UNLIMITED
							</c:if>
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>			
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
					<c:choose>
						<c:when test="${benefit.maxOccurancePerCase ne null}">
							<c:if test="${benefit.maxOccurancePerCase ne -1}">			
								<fmt:formatNumber><c:out value="${benefit.maxOccurancePerCase}" /></fmt:formatNumber>
							</c:if>			
							<c:if test="${benefit.maxOccurancePerCase eq -1}">
								UNLIMITED
							</c:if>
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">								
					<c:choose>
						<c:when test="${benefit.benefitUsage ne null and benefit.benefitOccuranceUsage ne null}">
							<fmt:formatNumber><c:out value="${benefit.benefitUsage}" /> </fmt:formatNumber> - [ <b><fmt:formatNumber><c:out value="${benefit.benefitOccuranceUsage}" /></fmt:formatNumber></b> ]
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>			
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
					<c:choose>
						<c:when test="${benefit.deductibleLimit ne null}">
							<fmt:formatNumber><c:out value="${benefit.deductibleLimit}" /></fmt:formatNumber>
						</c:when>
						<c:otherwise>
							-
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
					<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;">
						<c:choose>
							<c:when test="${benefit.costSharingAmount ne null}">
								<fmt:formatNumber><c:out value="${benefit.costSharingAmount}" /></fmt:formatNumber>
							</c:when>
							<c:otherwise>
								-
							</c:otherwise>
						</c:choose>							
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
<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	<tr height="20">
		<td scope="col" align="center" class="listViewThS1" style="text-align: center; font-size: 15;" nowrap="nowrap" width="100%" colspan=20>
				&nbsp;COVERAGE LIMIT INFORMATION</td>
	</tr>

	<tr height="20">
		<td width="2%" nowrap="nowrap" class="listViewThS1" scope="col">No.</td>						
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%" style="text-align: center;">Product Limit Layer &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%" style="text-align: center;">Diagnosis &nbsp;</td>
		<td scope="col" class="listViewThS1" width="5%" style="text-align: center;">Actual Limit &nbsp;</td>
		<td scope="col" class="listViewThS1" width="5%" style="text-align: center;">Annual Limit &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%" style="text-align: center;">Usage &nbsp;</td>
		<td scope="col" class="listViewThS1" width="3%" style="text-align: center;">Usage Percentage&nbsp;</td>				
		<td scope="col" class="listViewThS1" width="3%" style="text-align: center;">Deductible &nbsp;</td>
		<td scope="col" class="listViewThS1" width="3%" style="text-align: center;">Share (%) &nbsp;</td>				
	</tr>
	<tr height="20">
    	<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"></td>				   		   		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" ><b>Layer Utama</b></td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" ></td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
			<c:choose>
				<c:when test="${maternityProduct.actualBenefitLimit ne null}">
					<b><fmt:formatNumber><c:out value="${maternityProduct.actualBenefitLimit}" /></fmt:formatNumber></b>
				</c:when>
				<c:otherwise>
					-
				</c:otherwise>
			</c:choose>			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
			<c:choose>
				<c:when test="${maternityProduct.annualBenefit ne null}">
					<b><fmt:formatNumber><c:out value="${maternityProduct.annualBenefit}" /></fmt:formatNumber></b>
				</c:when>
				<c:otherwise>
					-
				</c:otherwise>
			</c:choose>						
		</td>			
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
			<c:choose>
				<c:when test="${maternityProduct.benefitUsage ne null}">
					<b><fmt:formatNumber><c:out value="${maternityProduct.benefitUsage}" /> </fmt:formatNumber></b>
				</c:when>
				<c:otherwise>
					-
				</c:otherwise>
			</c:choose> 			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
			<c:choose>
				<c:when test="${maternityProduct.benefitUsage ne null and maternityProduct.annualBenefit ne null}">
					<b><fmt:formatNumber><c:out value="${maternityProduct.benefitUsage / maternityProduct.annualBenefit * 100}" /> </fmt:formatNumber> %</b>
				</c:when>
				<c:otherwise>
					-
				</c:otherwise>
			</c:choose>			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">			
						
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;"></td>		
   	</tr>   
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	<c:forEach items="${maternityLayer}" var="benefit" varStatus="status" >
	 	<tr height="20">
      		<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${status.index + 1}" />.</td>				   		   		
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" >
				<a href="javascript:popupBenefitList('<c:out value="${benefit.memberLimitLayerId}" />')" class="linkDetail">Layer - <c:out value="${benefit.layer}" /></a>
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" >
				<c:out value="${benefit.diagnosisSetId.diagnosisSetName}" /> 
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
				<c:if test="${benefit.familyLimitLayerId eq null }">
					<c:choose>
						<c:when test="${benefit.actualBenefitLimit ne null}">
							<fmt:formatNumber><c:out value="${benefit.actualBenefitLimit}" /></fmt:formatNumber>
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>		
				</c:if>
				<c:if test="${benefit.familyLimitLayerId ne null }">
					<c:choose>
						<c:when test="${benefit.familyLimitLayerId.actualBenefitLimit ne null}">
							<fmt:formatNumber><c:out value="${benefit.familyLimitLayerId.actualBenefitLimit}" /></fmt:formatNumber>
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>		
				</c:if>		
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
				<c:if test="${benefit.familyLimitLayerId eq null }">
					<c:choose>
						<c:when test="${benefit.annualLimit ne null}">
							<fmt:formatNumber><c:out value="${benefit.annualLimit}" /></fmt:formatNumber>
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>						
				</c:if>
				<c:if test="${benefit.familyLimitLayerId ne null }">
					<c:choose>
						<c:when test="${benefit.familyLimitLayerId.annualLimit ne null}">
							<fmt:formatNumber><c:out value="${benefit.familyLimitLayerId.annualLimit}" /></fmt:formatNumber>
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>						
				</c:if>
			</td>			
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
				<c:if test="${benefit.familyLimitLayerId eq null }">
					<c:choose>
						<c:when test="${benefit.benefitUsage ne null}">
							<fmt:formatNumber><c:out value="${benefit.benefitUsage}" /> </fmt:formatNumber>	
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>			
				</c:if>
				<c:if test="${benefit.familyLimitLayerId ne null }">
					<c:choose>
						<c:when test="${benefit.familyLimitLayerId.benefitUsage ne null}">
							<fmt:formatNumber><c:out value="${benefit.familyLimitLayerId.benefitUsage}" /> </fmt:formatNumber>	
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>			
				</c:if>
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
				<c:if test="${benefit.familyLimitLayerId eq null }">
					<c:choose>
						<c:when test="${benefit.benefitUsage ne null and benefit.annualLimit ne null}">
							<fmt:formatNumber><c:out value="${benefit.benefitUsage / benefit.annualLimit * 100}" /> </fmt:formatNumber> %	
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>				
				</c:if>
				<c:if test="${benefit.familyLimitLayerId ne null }">
					<c:choose>
						<c:when test="${benefit.familyLimitLayerId.benefitUsage ne null and benefit.familyLimitLayerId.annualLimit ne null}">
							<fmt:formatNumber><c:out value="${benefit.familyLimitLayerId.benefitUsage / benefit.familyLimitLayerId.annualLimit * 100}" /> </fmt:formatNumber> %	
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>				
				</c:if>
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
				<c:if test="${benefit.familyLimitLayerId eq null }">
				<c:choose>
					<c:when test="${benefit.deductible ne null}">
						<fmt:formatNumber><c:out value="${benefit.deductible}" /></fmt:formatNumber>	
					</c:when>
					<c:otherwise>
						-
					</c:otherwise>
				</c:choose>			
				</c:if>
				<c:if test="${benefit.familyLimitLayerId ne null }">
				<c:choose>
					<c:when test="${benefit.familyLimitLayerId.deductible ne null}">
						<fmt:formatNumber><c:out value="${benefit.familyLimitLayerId.deductible}" /></fmt:formatNumber>	
					</c:when>
					<c:otherwise>
						-
					</c:otherwise>
				</c:choose>			
				</c:if>
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;">	
				<c:if test="${benefit.familyLimitLayerId eq null }">		
				<c:choose>
					<c:when test="${benefit.coSharePercentage < 100}">
						<fmt:formatNumber><c:out value="${benefit.coSharePercentage}" /></fmt:formatNumber> %
					</c:when>
					<c:otherwise>
						<center>-</center>
					</c:otherwise>
					
				</c:choose>
				</c:if>
				<c:if test="${benefit.familyLimitLayerId ne null }">		
				<c:choose>
					<c:when test="${benefit.familyLimitLayerId.coSharePercentage < 100}">
						<fmt:formatNumber><c:out value="${benefit.familyLimitLayerId.coSharePercentage}" /></fmt:formatNumber> %
					</c:when>
					<c:otherwise>
						<center>-</center>
					</c:otherwise>
					
				</c:choose>
				</c:if>
			</td>		
    	</tr>   
		<tr>
	      <td colspan="20" class="listViewHRS1"></td>
	    </tr>
	
	</c:forEach>
	
	
	</tbody>
</table>
</c:if>

<br />

<c:if test="${anc ne null}">
<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	<tr height="20">
		<td scope="col" align="center" class="listViewThS1" style="text-align: center; font-size: 15;" nowrap="nowrap" width="100%" colspan=20>
				&nbsp;ANC &nbsp;&nbsp; 				
				<c:choose>
					<c:when test="${ancProduct.annualBenefit ne null}">
						<c:if test="${ancProduct.annualBenefit gt -1}">
							| &nbsp;&nbsp; LIMIT = <fmt:formatNumber><c:out value="${ancProduct.annualBenefit}" /></fmt:formatNumber>
						</c:if>
						<c:if test="${ancProduct.annualBenefit eq -1}">
							| &nbsp;&nbsp; LIMIT = AS CHARGE
						</c:if>
					</c:when>
					<c:otherwise>
						| &nbsp;&nbsp; LIMIT = -
					</c:otherwise>
				</c:choose>
		</td>
	</tr>

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
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="2%" style="text-align: center;">SMM &nbsp;</td>
			<c:if test="${theUser.userType eq 2}">
				<td scope="col" class="listViewThS1" width="5%" style="text-align: center;">
				Max Lim Per Case. / Day &nbsp;</td>
		
				<td scope="col" class="listViewThS1" width="5%" style="text-align: center;">
				Max Occur. / Year &nbsp;</td>				
		
					<td scope="col" class="listViewThS1" width="3%" style="text-align: center;">
				Max Occur. per Case / Day&nbsp;</td>
			   			
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


	<c:forEach items="${anc}" var="benefit" varStatus="status" >
		
			 <tr height="20">
		      	<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${status.index + 1}" />.</td>
		      	
		      	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" >
					<c:out value="${benefit.itemCategoryId.itemCategoryCode}" /> <c:if test="${benefit.itemCategoryId.itemCategoryAlternateCode ne null}">/</c:if> <c:out value="${benefit.itemCategoryId.itemCategoryAlternateCode}" />
				</td>			   		
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" >
					<c:out value="${benefit.clientItemCode}" /> 
				</td>			   					   		
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
					[<c:out value="${benefit.caseCategoryId.caseCategoryCode}" />] <a href="claimitem?memberId=<c:out value="${memberId}" />&navigation=usagetrack&memberBenefitId=<c:out value="${benefit.memberBenefitId}" />&itemCategoryId=<c:out value="${benefit.itemCategoryId.itemCategoryId}" />&prev=listmember" class="linkDetail"><c:out value="${benefit.itemCategoryId.itemCategoryName}" /></a>		
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
									<c:when test="${benefit.benefitLimit ne null and benefit.cashlessPercentage ne null}">
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
										-
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
									<c:when test="${benefit.reimbursementBenefitLimit ne null and benefit.reimbursementPercentage ne null}">
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
										-
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
								SHARED
							</c:otherwise>
						</c:choose>
					
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top" style="text-align: center;">
					<c:if test="${benefit.memberLimitLayerId ne null and (benefit.memberLimitLayerId.diagnosisId ne null or benefit.memberLimitLayerId.diagnosisSetId ne null)}">Y</c:if>
					<c:if test="${benefit.memberLimitLayerId eq null }">N</c:if>
				</td>
				<c:if test="${theUser.userType eq 2}">
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
					<c:choose>
						<c:when test="${benefit.maxBenefitPerCase ne null}">
							<c:if test="${benefit.maxBenefitPerCase ne -1}">			
								<fmt:formatNumber><c:out value="${benefit.maxBenefitPerCase}" /></fmt:formatNumber>
							</c:if>
							<c:if test="${benefit.maxBenefitPerCase eq -1}">			
								AS CHARGE
							</c:if>
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>			
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
					<c:choose>
						<c:when test="${benefit.maxBenefitOccurance ne null}">
							<c:if test="${benefit.maxBenefitOccurance ne -1}">			
								<fmt:formatNumber><c:out value="${benefit.maxBenefitOccurance}" /></fmt:formatNumber>
							</c:if>
							<c:if test="${benefit.maxBenefitOccurance eq -1}">			
								UNLIMITED
							</c:if>
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>			
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
					<c:choose>
						<c:when test="${benefit.maxOccurancePerCase ne null}">
							<c:if test="${benefit.maxOccurancePerCase ne -1}">			
								<fmt:formatNumber><c:out value="${benefit.maxOccurancePerCase}" /></fmt:formatNumber>
							</c:if>			
							<c:if test="${benefit.maxOccurancePerCase eq -1}">
								UNLIMITED
							</c:if>
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">								
					<c:choose>
						<c:when test="${benefit.benefitUsage ne null and benefit.benefitOccuranceUsage ne null}">
							<fmt:formatNumber><c:out value="${benefit.benefitUsage}" /> </fmt:formatNumber> - [ <b><fmt:formatNumber><c:out value="${benefit.benefitOccuranceUsage}" /></fmt:formatNumber></b> ]
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>			
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
					<c:choose>
						<c:when test="${benefit.deductibleLimit ne null}">
							<fmt:formatNumber><c:out value="${benefit.deductibleLimit}" /></fmt:formatNumber>
						</c:when>
						<c:otherwise>
							-
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
					<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;">
						<c:choose>
							<c:when test="${benefit.costSharingAmount ne null}">
								<fmt:formatNumber><c:out value="${benefit.costSharingAmount}" /></fmt:formatNumber>
							</c:when>
							<c:otherwise>
								-
							</c:otherwise>
						</c:choose>							
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
<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	<tr height="20">
		<td scope="col" align="center" class="listViewThS1" style="text-align: center; font-size: 15;" nowrap="nowrap" width="100%" colspan=20>
				&nbsp;COVERAGE LIMIT INFORMATION</td>
	</tr>

	<tr height="20">
		<td width="2%" nowrap="nowrap" class="listViewThS1" scope="col">No.</td>						
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%" style="text-align: center;">Product Limit Layer &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%" style="text-align: center;">Diagnosis &nbsp;</td>
		<td scope="col" class="listViewThS1" width="5%" style="text-align: center;">Actual Limit &nbsp;</td>
		<td scope="col" class="listViewThS1" width="5%" style="text-align: center;">Annual Limit &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%" style="text-align: center;">Usage &nbsp;</td>
		<td scope="col" class="listViewThS1" width="3%" style="text-align: center;">Usage Percentage&nbsp;</td>				
		<td scope="col" class="listViewThS1" width="3%" style="text-align: center;">Deductible &nbsp;</td>
		<td scope="col" class="listViewThS1" width="3%" style="text-align: center;">Share (%) &nbsp;</td>				
	</tr>
	<tr height="20">
    	<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"></td>				   		   		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" ><b>Layer Utama</b></td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" ></td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
			<c:choose>
				<c:when test="${ancProduct.actualBenefitLimit ne null}">
					<b><fmt:formatNumber><c:out value="${ancProduct.actualBenefitLimit}" /></fmt:formatNumber></b>
				</c:when>
				<c:otherwise>
					-
				</c:otherwise>
			</c:choose>			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
			<c:choose>
				<c:when test="${ancProduct.annualBenefit ne null}">
					<b><fmt:formatNumber><c:out value="${ancProduct.annualBenefit}" /></fmt:formatNumber></b>
				</c:when>
				<c:otherwise>
					-
				</c:otherwise>
			</c:choose>						
		</td>			
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
			<c:choose>
				<c:when test="${ancProduct.benefitUsage ne null}">
					<b><fmt:formatNumber><c:out value="${ancProduct.benefitUsage}" /> </fmt:formatNumber></b>
				</c:when>
				<c:otherwise>
					-
				</c:otherwise>
			</c:choose> 			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
			<c:choose>
				<c:when test="${ancProduct.benefitUsage ne null and ancProduct.annualBenefit ne null}">
					<b><fmt:formatNumber><c:out value="${ancProduct.benefitUsage / ancProduct.annualBenefit * 100}" /> </fmt:formatNumber> %</b>
				</c:when>
				<c:otherwise>
					-
				</c:otherwise>
			</c:choose>			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">			
						
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;"></td>		
   	</tr>   
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	<c:forEach items="${ancLayer}" var="benefit" varStatus="status" >
	 	<tr height="20">
      		<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${status.index + 1}" />.</td>				   		   		
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" >
				<a href="javascript:popupBenefitList('<c:out value="${benefit.memberLimitLayerId}" />')" class="linkDetail">Layer - <c:out value="${benefit.layer}" /></a>
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" >
				<c:out value="${benefit.diagnosisSetId.diagnosisSetName}" /> 
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
				<c:if test="${benefit.familyLimitLayerId eq null }">
					<c:choose>
						<c:when test="${benefit.actualBenefitLimit ne null}">
							<fmt:formatNumber><c:out value="${benefit.actualBenefitLimit}" /></fmt:formatNumber>
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>		
				</c:if>
				<c:if test="${benefit.familyLimitLayerId ne null }">
					<c:choose>
						<c:when test="${benefit.familyLimitLayerId.actualBenefitLimit ne null}">
							<fmt:formatNumber><c:out value="${benefit.familyLimitLayerId.actualBenefitLimit}" /></fmt:formatNumber>
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>		
				</c:if>		
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
				<c:if test="${benefit.familyLimitLayerId eq null }">
					<c:choose>
						<c:when test="${benefit.annualLimit ne null}">
							<fmt:formatNumber><c:out value="${benefit.annualLimit}" /></fmt:formatNumber>
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>						
				</c:if>
				<c:if test="${benefit.familyLimitLayerId ne null }">
					<c:choose>
						<c:when test="${benefit.familyLimitLayerId.annualLimit ne null}">
							<fmt:formatNumber><c:out value="${benefit.familyLimitLayerId.annualLimit}" /></fmt:formatNumber>
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>						
				</c:if>
			</td>			
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
				<c:if test="${benefit.familyLimitLayerId eq null }">
					<c:choose>
						<c:when test="${benefit.benefitUsage ne null}">
							<fmt:formatNumber><c:out value="${benefit.benefitUsage}" /> </fmt:formatNumber>	
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>			
				</c:if>
				<c:if test="${benefit.familyLimitLayerId ne null }">
					<c:choose>
						<c:when test="${benefit.familyLimitLayerId.benefitUsage ne null}">
							<fmt:formatNumber><c:out value="${benefit.familyLimitLayerId.benefitUsage}" /> </fmt:formatNumber>	
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>			
				</c:if>
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
				<c:if test="${benefit.familyLimitLayerId eq null }">
					<c:choose>
						<c:when test="${benefit.benefitUsage ne null and benefit.annualLimit ne null}">
							<fmt:formatNumber><c:out value="${benefit.benefitUsage / benefit.annualLimit * 100}" /> </fmt:formatNumber> %	
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>				
				</c:if>
				<c:if test="${benefit.familyLimitLayerId ne null }">
					<c:choose>
						<c:when test="${benefit.familyLimitLayerId.benefitUsage ne null and benefit.familyLimitLayerId.annualLimit ne null}">
							<fmt:formatNumber><c:out value="${benefit.familyLimitLayerId.benefitUsage / benefit.familyLimitLayerId.annualLimit * 100}" /> </fmt:formatNumber> %	
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>				
				</c:if>
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
				<c:if test="${benefit.familyLimitLayerId eq null }">
				<c:choose>
					<c:when test="${benefit.deductible ne null}">
						<fmt:formatNumber><c:out value="${benefit.deductible}" /></fmt:formatNumber>	
					</c:when>
					<c:otherwise>
						-
					</c:otherwise>
				</c:choose>			
				</c:if>
				<c:if test="${benefit.familyLimitLayerId ne null }">
				<c:choose>
					<c:when test="${benefit.familyLimitLayerId.deductible ne null}">
						<fmt:formatNumber><c:out value="${benefit.familyLimitLayerId.deductible}" /></fmt:formatNumber>	
					</c:when>
					<c:otherwise>
						-
					</c:otherwise>
				</c:choose>			
				</c:if>
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;">	
				<c:if test="${benefit.familyLimitLayerId eq null }">		
				<c:choose>
					<c:when test="${benefit.coSharePercentage < 100}">
						<fmt:formatNumber><c:out value="${benefit.coSharePercentage}" /></fmt:formatNumber> %
					</c:when>
					<c:otherwise>
						<center>-</center>
					</c:otherwise>
					
				</c:choose>
				</c:if>
				<c:if test="${benefit.familyLimitLayerId ne null }">		
				<c:choose>
					<c:when test="${benefit.familyLimitLayerId.coSharePercentage < 100}">
						<fmt:formatNumber><c:out value="${benefit.familyLimitLayerId.coSharePercentage}" /></fmt:formatNumber> %
					</c:when>
					<c:otherwise>
						<center>-</center>
					</c:otherwise>
					
				</c:choose>
				</c:if>
			</td>		
    	</tr>   
		<tr>
	      <td colspan="20" class="listViewHRS1"></td>
	    </tr>
	
	</c:forEach>
	
	
	</tbody>
</table>
</c:if>

<br />

<c:if test="${optical ne null}">
<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	<tr height="20">
		<td scope="col" align="center" class="listViewThS1" style="text-align: center; font-size: 15;" nowrap="nowrap" width="100%" colspan=20>
				&nbsp;OPTICAL &nbsp;&nbsp; 				
				<c:choose>
					<c:when test="${opticalProduct.annualBenefit ne null}">
						<c:if test="${opticalProduct.annualBenefit gt -1}">
							| &nbsp;&nbsp; LIMIT = <fmt:formatNumber><c:out value="${opticalProduct.annualBenefit}" /></fmt:formatNumber>
						</c:if>
						<c:if test="${opticalProduct.annualBenefit eq -1}">
							| &nbsp;&nbsp; LIMIT = AS CHARGE
						</c:if>
					</c:when>
					<c:otherwise>
						| &nbsp;&nbsp; LIMIT = -
					</c:otherwise>
				</c:choose>
		</td>
	</tr>

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
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="2%" style="text-align: center;">SMM &nbsp;</td>
			<c:if test="${theUser.userType eq 2}">
				<td scope="col" class="listViewThS1" width="5%" style="text-align: center;">
				Max Lim Per Case. / Day &nbsp;</td>
		
				<td scope="col" class="listViewThS1" width="5%" style="text-align: center;">
				Max Occur. / Year &nbsp;</td>				
		
					<td scope="col" class="listViewThS1" width="3%" style="text-align: center;">
				Max Occur. per Case / Day&nbsp;</td>
			   			
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

	<c:forEach items="${optical}" var="benefit" varStatus="status" >
		
			 <tr height="20">
		      	<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${status.index + 1}" />.</td>					   		   		
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" >
					<c:out value="${benefit.itemCategoryId.itemCategoryCode}" /> <c:if test="${benefit.itemCategoryId.itemCategoryAlternateCode ne null}">/</c:if> <c:out value="${benefit.itemCategoryId.itemCategoryAlternateCode}" />
				</td>			   		
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" >
					<c:out value="${benefit.clientItemCode}" /> 
				</td>			   					   		
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
					[<c:out value="${benefit.caseCategoryId.caseCategoryCode}" />] <a href="claimitem?memberId=<c:out value="${memberId}" />&navigation=usagetrack&memberBenefitId=<c:out value="${benefit.memberBenefitId}" />&itemCategoryId=<c:out value="${benefit.itemCategoryId.itemCategoryId}" />&prev=listmember" class="linkDetail"><c:out value="${benefit.itemCategoryId.itemCategoryName}" /></a>		
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
									<c:when test="${benefit.benefitLimit ne null and benefit.cashlessPercentage ne null}">
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
										-
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
									<c:when test="${benefit.reimbursementBenefitLimit ne null and benefit.reimbursementPercentage ne null}">
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
										-
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
								SHARED
							</c:otherwise>
						</c:choose>
					
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top" style="text-align: center;">
					<c:if test="${benefit.memberLimitLayerId ne null and (benefit.memberLimitLayerId.diagnosisId ne null or benefit.memberLimitLayerId.diagnosisSetId ne null)}">Y</c:if>
					<c:if test="${benefit.memberLimitLayerId eq null }">N</c:if>
				</td>
					<c:if test="${theUser.userType eq 2}">
						<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
							<c:choose>
								<c:when test="${benefit.maxBenefitPerCase ne null}">
									<c:if test="${benefit.maxBenefitPerCase ne -1}">			
										<fmt:formatNumber><c:out value="${benefit.maxBenefitPerCase}" /></fmt:formatNumber>
									</c:if>
									<c:if test="${benefit.maxBenefitPerCase eq -1}">			
										AS CHARGE
									</c:if>		
								</c:when>
								<c:otherwise>
									-
								</c:otherwise>
							</c:choose>	
						</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
					<c:choose>
						<c:when test="${benefit.maxBenefitOccurance ne null}">
							<c:if test="${benefit.maxBenefitOccurance ne -1}">			
								<fmt:formatNumber><c:out value="${benefit.maxBenefitOccurance}" /></fmt:formatNumber>
							</c:if>
							<c:if test="${benefit.maxBenefitOccurance eq -1}">			
								UNLIMITED
							</c:if>
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>			
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
					<c:choose>
						<c:when test="${benefit.maxOccurancePerCase ne null}">
							<c:if test="${benefit.maxOccurancePerCase ne -1}">			
								<fmt:formatNumber><c:out value="${benefit.maxOccurancePerCase}" /></fmt:formatNumber>
							</c:if>			
							<c:if test="${benefit.maxOccurancePerCase eq -1}">
								UNLIMITED
							</c:if>
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
					<c:choose>
						<c:when test="${benefit.benefitUsage ne null and benefit.benefitOccuranceUsage ne null}">
							<fmt:formatNumber><c:out value="${benefit.benefitUsage}" /> </fmt:formatNumber> - [ <b><fmt:formatNumber><c:out value="${benefit.benefitOccuranceUsage}" /></fmt:formatNumber></b> ]
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>			
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">						
					<c:choose>
						<c:when test="${benefit.deductibleLimit ne null}">
							<fmt:formatNumber><c:out value="${benefit.deductibleLimit}" /></fmt:formatNumber>
						</c:when>
						<c:otherwise>
							-
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
					<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;"> 
						<c:choose>
							<c:when test="${benefit.costSharingAmount ne null}">
								<fmt:formatNumber><c:out value="${benefit.costSharingAmount}" /></fmt:formatNumber>
							</c:when>
							<c:otherwise>
								-
							</c:otherwise>
						</c:choose>
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
<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	<tr height="20">
		<td scope="col" align="center" class="listViewThS1" style="text-align: center; font-size: 15;" nowrap="nowrap" width="100%" colspan=20>
				&nbsp;COVERAGE LIMIT INFORMATION</td>
	</tr>

	<tr height="20">
		<td width="2%" nowrap="nowrap" class="listViewThS1" scope="col">No.</td>						
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%" style="text-align: center;">Product Limit Layer &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%" style="text-align: center;">Diagnosis &nbsp;</td>
		<td scope="col" class="listViewThS1" width="5%" style="text-align: center;">Actual Limit &nbsp;</td>
		<td scope="col" class="listViewThS1" width="5%" style="text-align: center;">Annual Limit &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%" style="text-align: center;">Usage &nbsp;</td>
		<td scope="col" class="listViewThS1" width="3%" style="text-align: center;">Usage Percentage&nbsp;</td>				
		<td scope="col" class="listViewThS1" width="3%" style="text-align: center;">Deductible &nbsp;</td>
		<td scope="col" class="listViewThS1" width="3%" style="text-align: center;">Share (%) &nbsp;</td>				
	</tr>
	<tr height="20">
    	<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"></td>				   		   		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" ><b>Layer Utama</b></td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" ></td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">	
			<c:choose>
				<c:when test="${opticalProduct.actualBenefitLimit ne null}">
					<b><fmt:formatNumber><c:out value="${opticalProduct.actualBenefitLimit}" /></fmt:formatNumber></b>
				</c:when>
				<c:otherwise>
					-
				</c:otherwise>
			</c:choose>		
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
			<c:choose>
				<c:when test="${opticalProduct.annualBenefit ne null}">
					<b><fmt:formatNumber><c:out value="${opticalProduct.annualBenefit}" /></fmt:formatNumber></b>
				</c:when>
				<c:otherwise>
					-
				</c:otherwise>
			</c:choose>								
		</td>			
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">	
			<c:choose>
				<c:when test="${opticalProduct.benefitUsage ne null}">
					<b><fmt:formatNumber><c:out value="${opticalProduct.benefitUsage}" /> </fmt:formatNumber></b> 
				</c:when>
				<c:otherwise>
					-
				</c:otherwise>
			</c:choose>					
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
			<c:choose>
				<c:when test="${opticalProduct.benefitUsage ne null and opticalProduct.annualBenefit ne null}">
					<b><fmt:formatNumber><c:out value="${opticalProduct.benefitUsage / opticalProduct.annualBenefit * 100}" /> </fmt:formatNumber> %</b> 
				</c:when>
				<c:otherwise>
					-
				</c:otherwise>
			</c:choose>						
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">			
						
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;"></td>		
   	</tr>   
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	<c:forEach items="${opticalLayer}" var="benefit" varStatus="status" >
	 	<tr height="20">
      		<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${status.index + 1}" />.</td>				   		   		
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" >
				<a href="javascript:popupBenefitList('<c:out value="${benefit.memberLimitLayerId}" />')" class="linkDetail">Layer - <c:out value="${benefit.layer}" /></a>
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" >
				<c:out value="${benefit.diagnosisSetId.diagnosisSetName}" /> 
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
				<c:if test="${benefit.familyLimitLayerId eq null }">
					<c:choose>
						<c:when test="${benefit.actualBenefitLimit ne null}">
							<fmt:formatNumber><c:out value="${benefit.actualBenefitLimit}" /></fmt:formatNumber>
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>		
				</c:if>
				<c:if test="${benefit.familyLimitLayerId ne null }">
					<c:choose>
						<c:when test="${benefit.familyLimitLayerId.actualBenefitLimit ne null}">
							<fmt:formatNumber><c:out value="${benefit.familyLimitLayerId.actualBenefitLimit}" /></fmt:formatNumber>
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>		
				</c:if>		
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
				<c:if test="${benefit.familyLimitLayerId eq null }">
					<c:choose>
						<c:when test="${benefit.annualLimit ne null}">
							<fmt:formatNumber><c:out value="${benefit.annualLimit}" /></fmt:formatNumber>
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>						
				</c:if>
				<c:if test="${benefit.familyLimitLayerId ne null }">
					<c:choose>
						<c:when test="${benefit.familyLimitLayerId.annualLimit ne null}">
							<fmt:formatNumber><c:out value="${benefit.familyLimitLayerId.annualLimit}" /></fmt:formatNumber>
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>						
				</c:if>
			</td>			
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
				<c:if test="${benefit.familyLimitLayerId eq null }">
					<c:choose>
						<c:when test="${benefit.benefitUsage ne null}">
							<fmt:formatNumber><c:out value="${benefit.benefitUsage}" /> </fmt:formatNumber>	
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>			
				</c:if>
				<c:if test="${benefit.familyLimitLayerId ne null }">
					<c:choose>
						<c:when test="${benefit.familyLimitLayerId.benefitUsage ne null}">
							<fmt:formatNumber><c:out value="${benefit.familyLimitLayerId.benefitUsage}" /> </fmt:formatNumber>	
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>			
				</c:if>
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
				<c:if test="${benefit.familyLimitLayerId eq null }">
					<c:choose>
						<c:when test="${benefit.benefitUsage ne null and benefit.annualLimit ne null}">
							<fmt:formatNumber><c:out value="${benefit.benefitUsage / benefit.annualLimit * 100}" /> </fmt:formatNumber> %	
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>				
				</c:if>
				<c:if test="${benefit.familyLimitLayerId ne null }">
					<c:choose>
						<c:when test="${benefit.familyLimitLayerId.benefitUsage ne null and benefit.familyLimitLayerId.annualLimit ne null}">
							<fmt:formatNumber><c:out value="${benefit.familyLimitLayerId.benefitUsage / benefit.familyLimitLayerId.annualLimit * 100}" /> </fmt:formatNumber> %	
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>				
				</c:if>
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
				<c:if test="${benefit.familyLimitLayerId eq null }">
				<c:choose>
					<c:when test="${benefit.deductible ne null}">
						<fmt:formatNumber><c:out value="${benefit.deductible}" /></fmt:formatNumber>	
					</c:when>
					<c:otherwise>
						-
					</c:otherwise>
				</c:choose>			
				</c:if>
				<c:if test="${benefit.familyLimitLayerId ne null }">
				<c:choose>
					<c:when test="${benefit.familyLimitLayerId.deductible ne null}">
						<fmt:formatNumber><c:out value="${benefit.familyLimitLayerId.deductible}" /></fmt:formatNumber>	
					</c:when>
					<c:otherwise>
						-
					</c:otherwise>
				</c:choose>			
				</c:if>
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;">	
				<c:if test="${benefit.familyLimitLayerId eq null }">		
				<c:choose>
					<c:when test="${benefit.coSharePercentage < 100}">
						<fmt:formatNumber><c:out value="${benefit.coSharePercentage}" /></fmt:formatNumber> %
					</c:when>
					<c:otherwise>
						<center>-</center>
					</c:otherwise>
					
				</c:choose>
				</c:if>
				<c:if test="${benefit.familyLimitLayerId ne null }">		
				<c:choose>
					<c:when test="${benefit.familyLimitLayerId.coSharePercentage < 100}">
						<fmt:formatNumber><c:out value="${benefit.familyLimitLayerId.coSharePercentage}" /></fmt:formatNumber> %
					</c:when>
					<c:otherwise>
						<center>-</center>
					</c:otherwise>
					
				</c:choose>
				</c:if>
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
		<td scope="col" align="center" class="listViewThS1" style="text-align: center; font-size: 15;" nowrap="nowrap" width="100%" colspan=20>
				&nbsp;DENTAL &nbsp;&nbsp; 				
				<c:choose>
					<c:when test="${dentalProduct.annualBenefit ne null}">
						<c:if test="${dentalProduct.annualBenefit gt -1}">
							| &nbsp;&nbsp; LIMIT = <fmt:formatNumber><c:out value="${dentalProduct.annualBenefit}" /></fmt:formatNumber>
						</c:if>
						<c:if test="${dentalProduct.annualBenefit eq -1}">
							| &nbsp;&nbsp; LIMIT = AS CHARGE
						</c:if>
					</c:when>
					<c:otherwise>
						| &nbsp;&nbsp; LIMIT = -
					</c:otherwise>
				</c:choose>		
		</td>
	</tr>

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
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="2%" style="text-align: center;">SMM &nbsp;</td>
			<c:if test="${theUser.userType eq 2}">
				<td scope="col" class="listViewThS1" width="5%" style="text-align: center;">
				Max Lim Per Case. / Day &nbsp;</td>
		
				<td scope="col" class="listViewThS1" width="5%" style="text-align: center;">
				Max Occur. / Year &nbsp;</td>				
		
					<td scope="col" class="listViewThS1" width="3%" style="text-align: center;">
				Max Occur. per Case / Day&nbsp;</td>
			   			
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


	<c:forEach items="${dental}" var="benefit" varStatus="status" >
		
			 <tr height="20">
		      	<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${status.index + 1}" />.</td>				   		   		
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" >
					<c:out value="${benefit.itemCategoryId.itemCategoryCode}" /> <c:if test="${benefit.itemCategoryId.itemCategoryAlternateCode ne null}">/</c:if> <c:out value="${benefit.itemCategoryId.itemCategoryAlternateCode}" />
				</td>			   		
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" >
					<c:out value="${benefit.clientItemCode}" /> 
				</td>			   					   		
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
					[<c:out value="${benefit.caseCategoryId.caseCategoryCode}" />] <a href="claimitem?memberId=<c:out value="${memberId}" />&navigation=usagetrack&memberBenefitId=<c:out value="${benefit.memberBenefitId}" />&itemCategoryId=<c:out value="${benefit.itemCategoryId.itemCategoryId}" />&prev=listmember" class="linkDetail"><c:out value="${benefit.itemCategoryId.itemCategoryName}" /></a>		
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
									<c:when test="${benefit.benefitLimit ne null and benefit.cashlessPercentage ne null}">
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
										-
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
									<c:when test="${benefit.reimbursementBenefitLimit eq null and benefit.reimbursementPercentage ne null}">
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
										-
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
								SHARED
							</c:otherwise>
						</c:choose>
					
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top" style="text-align: center;">
					<c:if test="${benefit.memberLimitLayerId ne null and (benefit.memberLimitLayerId.diagnosisId ne null or benefit.memberLimitLayerId.diagnosisSetId ne null)}">Y</c:if>
					<c:if test="${benefit.memberLimitLayerId eq null }">N</c:if>
				</td>
					<c:if test="${theUser.userType eq 2}">
							<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
							<c:choose>
								<c:when test="${benefit.maxBenefitPerCase ne null}">
									<c:if test="${benefit.maxBenefitPerCase ne -1}">			
										<fmt:formatNumber><c:out value="${benefit.maxBenefitPerCase}" /></fmt:formatNumber>
									</c:if>
									<c:if test="${benefit.maxBenefitPerCase eq -1}">			
										AS CHARGE
									</c:if>
								</c:when>
								<c:otherwise>
									-
								</c:otherwise>
							</c:choose>			
						</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
					<c:choose>
						<c:when test="${benefit.maxBenefitOccurance ne null}">
							<c:if test="${benefit.maxBenefitOccurance ne -1}">			
								<fmt:formatNumber><c:out value="${benefit.maxBenefitOccurance}" /></fmt:formatNumber>
							</c:if>
							<c:if test="${benefit.maxBenefitOccurance eq -1}">			
								UNLIMITED
							</c:if>
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>			
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
					<c:choose>
						<c:when test="${benefit.maxOccurancePerCase ne null}">
							<c:if test="${benefit.maxOccurancePerCase ne -1}">			
								<fmt:formatNumber><c:out value="${benefit.maxOccurancePerCase}" /></fmt:formatNumber>
							</c:if>			
							<c:if test="${benefit.maxOccurancePerCase eq -1}">
								UNLIMITED
							</c:if>
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
					<c:choose>
						<c:when test="${benefit.benefitUsage ne null and benefit.benefitOccuranceUsage ne null}">
							<fmt:formatNumber><c:out value="${benefit.benefitUsage}" /> </fmt:formatNumber> - [ <b><fmt:formatNumber><c:out value="${benefit.benefitOccuranceUsage}" /></fmt:formatNumber></b> ]
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>			
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
					<c:choose>
						<c:when test="${benefit.deductibleLimit ne null}">
							<fmt:formatNumber><c:out value="${benefit.deductibleLimit}" /></fmt:formatNumber>
						</c:when>
						<c:otherwise>
							-
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
					<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;"> 
						<c:choose>
							<c:when test="${benefit.costSharingAmount ne null}">
								<fmt:formatNumber><c:out value="${benefit.costSharingAmount}" /></fmt:formatNumber>
							</c:when>
							<c:otherwise>
								-
							</c:otherwise>
						</c:choose>
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
<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	<tr height="20">
		<td scope="col" align="center" class="listViewThS1" style="text-align: center; font-size: 15;" nowrap="nowrap" width="100%" colspan=20>
				&nbsp;COVERAGE LIMIT INFORMATION</td>
	</tr>

	<tr height="20">
		<td width="2%" nowrap="nowrap" class="listViewThS1" scope="col">No.</td>						
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%" style="text-align: center;">Product Limit Layer &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%" style="text-align: center;">Diagnosis &nbsp;</td>
		<td scope="col" class="listViewThS1" width="5%" style="text-align: center;">Actual Limit &nbsp;</td>
		<td scope="col" class="listViewThS1" width="5%" style="text-align: center;">Annual Limit &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%" style="text-align: center;">Usage &nbsp;</td>
		<td scope="col" class="listViewThS1" width="3%" style="text-align: center;">Usage Percentage&nbsp;</td>				
		<td scope="col" class="listViewThS1" width="3%" style="text-align: center;">Deductible &nbsp;</td>
		<td scope="col" class="listViewThS1" width="3%" style="text-align: center;">Share (%) &nbsp;</td>				
	</tr>
	<tr height="20">
    	<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"></td>				   		   		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" ><b>Layer Utama</b></td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" ></td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
			<c:choose>
				<c:when test="${dentalProduct.actualBenefitLimit ne null}">
					<b><fmt:formatNumber><c:out value="${dentalProduct.actualBenefitLimit}" /></fmt:formatNumber></b>
				</c:when>
				<c:otherwise>
					-
				</c:otherwise>
			</c:choose>			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
			<c:choose>
				<c:when test="${dentalProduct.annualBenefit ne null}">
					<b><fmt:formatNumber><c:out value="${dentalProduct.annualBenefit}" /></fmt:formatNumber></b>
				</c:when>
				<c:otherwise>
					-
				</c:otherwise>
			</c:choose>									
		</td>			
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
			<c:choose>
				<c:when test="${dentalProduct.benefitUsage ne null}">
					<b><fmt:formatNumber><c:out value="${dentalProduct.benefitUsage}" /> </fmt:formatNumber></b>
				</c:when>
				<c:otherwise>
					-
				</c:otherwise>
			</c:choose>								 			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
			<c:choose>
				<c:when test="${dentalProduct.benefitUsage ne null and dentalProduct.annualBenefit ne null}">
					<b><fmt:formatNumber><c:out value="${dentalProduct.benefitUsage / dentalProduct.annualBenefit * 100}" /> </fmt:formatNumber> %</b>
				</c:when>
				<c:otherwise>
					-
				</c:otherwise>
			</c:choose>								 					
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">			
						
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;"></td>		
   	</tr>   
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	<c:forEach items="${dentalLayer}" var="benefit" varStatus="status" >
	 	<tr height="20">
      		<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${status.index + 1}" />.</td>				   		   		
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" >
				<a href="javascript:popupBenefitList('<c:out value="${benefit.memberLimitLayerId}" />')" class="linkDetail">Layer - <c:out value="${benefit.layer}" /></a>
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" >
				<c:out value="${benefit.diagnosisSetId.diagnosisSetName}" /> 
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
				<c:if test="${benefit.familyLimitLayerId eq null }">
					<c:choose>
						<c:when test="${benefit.actualBenefitLimit ne null}">
							<fmt:formatNumber><c:out value="${benefit.actualBenefitLimit}" /></fmt:formatNumber>
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>		
				</c:if>
				<c:if test="${benefit.familyLimitLayerId ne null }">
					<c:choose>
						<c:when test="${benefit.familyLimitLayerId.actualBenefitLimit ne null}">
							<fmt:formatNumber><c:out value="${benefit.familyLimitLayerId.actualBenefitLimit}" /></fmt:formatNumber>
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>		
				</c:if>		
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
				<c:if test="${benefit.familyLimitLayerId eq null }">
					<c:choose>
						<c:when test="${benefit.annualLimit ne null}">
							<fmt:formatNumber><c:out value="${benefit.annualLimit}" /></fmt:formatNumber>
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>						
				</c:if>
				<c:if test="${benefit.familyLimitLayerId ne null }">
					<c:choose>
						<c:when test="${benefit.familyLimitLayerId.annualLimit ne null}">
							<fmt:formatNumber><c:out value="${benefit.familyLimitLayerId.annualLimit}" /></fmt:formatNumber>
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>						
				</c:if>
			</td>			
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
				<c:if test="${benefit.familyLimitLayerId eq null }">
					<c:choose>
						<c:when test="${benefit.benefitUsage ne null}">
							<fmt:formatNumber><c:out value="${benefit.benefitUsage}" /> </fmt:formatNumber>	
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>			
				</c:if>
				<c:if test="${benefit.familyLimitLayerId ne null }">
					<c:choose>
						<c:when test="${benefit.familyLimitLayerId.benefitUsage ne null}">
							<fmt:formatNumber><c:out value="${benefit.familyLimitLayerId.benefitUsage}" /> </fmt:formatNumber>	
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>			
				</c:if>
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
				<c:if test="${benefit.familyLimitLayerId eq null }">
					<c:choose>
						<c:when test="${benefit.benefitUsage ne null and benefit.annualLimit ne null}">
							<fmt:formatNumber><c:out value="${benefit.benefitUsage / benefit.annualLimit * 100}" /> </fmt:formatNumber> %	
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>				
				</c:if>
				<c:if test="${benefit.familyLimitLayerId ne null }">
					<c:choose>
						<c:when test="${benefit.familyLimitLayerId.benefitUsage ne null and benefit.familyLimitLayerId.annualLimit ne null}">
							<fmt:formatNumber><c:out value="${benefit.familyLimitLayerId.benefitUsage / benefit.familyLimitLayerId.annualLimit * 100}" /> </fmt:formatNumber> %	
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>				
				</c:if>
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
				<c:if test="${benefit.familyLimitLayerId eq null }">
				<c:choose>
					<c:when test="${benefit.deductible ne null}">
						<fmt:formatNumber><c:out value="${benefit.deductible}" /></fmt:formatNumber>	
					</c:when>
					<c:otherwise>
						-
					</c:otherwise>
				</c:choose>			
				</c:if>
				<c:if test="${benefit.familyLimitLayerId ne null }">
				<c:choose>
					<c:when test="${benefit.familyLimitLayerId.deductible ne null}">
						<fmt:formatNumber><c:out value="${benefit.familyLimitLayerId.deductible}" /></fmt:formatNumber>	
					</c:when>
					<c:otherwise>
						-
					</c:otherwise>
				</c:choose>			
				</c:if>
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;">	
				<c:if test="${benefit.familyLimitLayerId eq null }">		
				<c:choose>
					<c:when test="${benefit.coSharePercentage < 100}">
						<fmt:formatNumber><c:out value="${benefit.coSharePercentage}" /></fmt:formatNumber> %
					</c:when>
					<c:otherwise>
						<center>-</center>
					</c:otherwise>
					
				</c:choose>
				</c:if>
				<c:if test="${benefit.familyLimitLayerId ne null }">		
				<c:choose>
					<c:when test="${benefit.familyLimitLayerId.coSharePercentage < 100}">
						<fmt:formatNumber><c:out value="${benefit.familyLimitLayerId.coSharePercentage}" /></fmt:formatNumber> %
					</c:when>
					<c:otherwise>
						<center>-</center>
					</c:otherwise>
					
				</c:choose>
				</c:if>
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

<c:if test="${ppk1Umum ne null}">		

<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	<tr height="20">
		<td scope="col" align="center" class="listViewThS1" style="text-align: center; font-size: 15;" nowrap="nowrap" width="100%" colspan=20>
				&nbsp;PPK 1 - UMUM</td>
	</tr>

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
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="2%" style="text-align: center;">SMM &nbsp;</td>
			<c:if test="${theUser.userType eq 2}">
				<td scope="col" class="listViewThS1" width="5%" style="text-align: center;">
				Max Lim Per Case. / Day &nbsp;</td>
		
				<td scope="col" class="listViewThS1" width="5%" style="text-align: center;">
				Max Occur. / Year &nbsp;</td>				
		
					<td scope="col" class="listViewThS1" width="3%" style="text-align: center;">
				Max Occur. per Case / Day&nbsp;</td>
			   			
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

	<c:forEach items="${ppk1Umum}" var="benefit" varStatus="status" >
		
			 <tr height="20">
		      	<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${status.index + 1}" />.</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" >
					<c:out value="${benefit.itemCategoryId.itemCategoryCode}" /> <c:if test="${benefit.itemCategoryId.itemCategoryAlternateCode ne null}">/</c:if> <c:out value="${benefit.itemCategoryId.itemCategoryAlternateCode}" />
				</td>			   		
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" >
					<c:out value="${benefit.clientItemCode}" /> 
				</td>			   					   		
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
					[<c:out value="${benefit.caseCategoryId.caseCategoryCode}" />] <a href="claimitem?memberId=<c:out value="${memberId}" />&navigation=usagetrack&memberBenefitId=<c:out value="${benefit.memberBenefitId}" />&itemCategoryId=<c:out value="${benefit.itemCategoryId.itemCategoryId}" />&prev=listmember" class="linkDetail"><c:out value="${benefit.itemCategoryId.itemCategoryName}" /></a>		
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
									<c:when test="${benefit.benefitLimit ne null and benefit.cashlessPercentage ne null}">
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
										-
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
									<c:when test="${benefit.reimbursementBenefitLimit ne null and benefit.reimbursementPercentage ne null}">
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
										-
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
								SHARED
							</c:otherwise>
						</c:choose>
					
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top" style="text-align: center;">
					<c:if test="${benefit.memberLimitLayerId ne null and (benefit.memberLimitLayerId.diagnosisId ne null or benefit.memberLimitLayerId.diagnosisSetId ne null)}">Y</c:if>
					<c:if test="${benefit.memberLimitLayerId eq null }">N</c:if>
				</td>
					<c:if test="${theUser.userType eq 2}">
						<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
							<c:choose>
								<c:when test="${benefit.maxBenefitPerCase ne null}">
									<c:if test="${benefit.maxBenefitPerCase ne -1}">			
										<fmt:formatNumber><c:out value="${benefit.maxBenefitPerCase}" /></fmt:formatNumber>
									</c:if>
									<c:if test="${benefit.maxBenefitPerCase eq -1}">			
										AS CHARGE
									</c:if>
								</c:when>
								<c:otherwise>
									-
								</c:otherwise>
							</c:choose>			
						</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
					<c:choose>
						<c:when test="${benefit.maxBenefitOccurance ne null}">
							<c:if test="${benefit.maxBenefitOccurance ne -1}">			
								<fmt:formatNumber><c:out value="${benefit.maxBenefitOccurance}" /></fmt:formatNumber>
							</c:if>
							<c:if test="${benefit.maxBenefitOccurance eq -1}">			
								UNLIMITED
							</c:if>
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>			
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
					<c:choose>
						<c:when test="${benefit.maxOccurancePerCase ne null}">
							<c:if test="${benefit.maxOccurancePerCase ne -1}">			
								<fmt:formatNumber><c:out value="${benefit.maxOccurancePerCase}" /></fmt:formatNumber>
							</c:if>			
							<c:if test="${benefit.maxOccurancePerCase eq -1}">
								UNLIMITED
							</c:if>
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
					<c:choose>
						<c:when test="${benefit.benefitUsage ne null and benefit.benefitOccuranceUsage ne null}">
							<fmt:formatNumber><c:out value="${benefit.benefitUsage}" /> </fmt:formatNumber> - [ <b><fmt:formatNumber><c:out value="${benefit.benefitOccuranceUsage}" /></fmt:formatNumber></b> ]
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>			
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
						<c:choose>
							<c:when test="${benefit.deductibleLimit ne null}">
								<fmt:formatNumber><c:out value="${benefit.deductibleLimit}" /></fmt:formatNumber>
							</c:when>
							<c:otherwise>
								-
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
					<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;"> 
						<c:choose>
							<c:when test="${benefit.costSharingAmount ne null}">
								<fmt:formatNumber><c:out value="${benefit.costSharingAmount}" /></fmt:formatNumber>
							</c:when>
							<c:otherwise>
								-
							</c:otherwise>
						</c:choose>	
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
</c:if>


<c:if test="${ppk1Dental ne null}">		

<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	<tr height="20">
		<td scope="col" align="center" class="listViewThS1" style="text-align: center; font-size: 15;" nowrap="nowrap" width="100%" colspan=20>
				&nbsp;PPK 1 - GIGI</td>
	</tr>

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
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="2%" style="text-align: center;">SMM &nbsp;</td>
			<c:if test="${theUser.userType eq 2}">
				<td scope="col" class="listViewThS1" width="5%" style="text-align: center;">
				Max Lim Per Case. / Day &nbsp;</td>
		
				<td scope="col" class="listViewThS1" width="5%" style="text-align: center;">
				Max Occur. / Year &nbsp;</td>				
		
					<td scope="col" class="listViewThS1" width="3%" style="text-align: center;">
				Max Occur. per Case / Day&nbsp;</td>
			   			
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

	<c:forEach items="${ppk1Dental}" var="benefit" varStatus="status" >
		
			 <tr height="20">
		      	<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${status.index + 1}" />.</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" >
					<c:out value="${benefit.itemCategoryId.itemCategoryCode}" /> <c:if test="${benefit.itemCategoryId.itemCategoryAlternateCode ne null}">/</c:if> <c:out value="${benefit.itemCategoryId.itemCategoryAlternateCode}" />
				</td>			   		
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" >
					<c:out value="${benefit.clientItemCode}" /> 
				</td>			   					   		
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
					[<c:out value="${benefit.caseCategoryId.caseCategoryCode}" />] <a href="claimitem?memberId=<c:out value="${memberId}" />&navigation=usagetrack&memberBenefitId=<c:out value="${benefit.memberBenefitId}" />&itemCategoryId=<c:out value="${benefit.itemCategoryId.itemCategoryId}" />&prev=listmember" class="linkDetail"><c:out value="${benefit.itemCategoryId.itemCategoryName}" /></a>		
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
									<c:when test="${benefit.benefitLimit ne null and benefit.cashlessPercentage ne null}">
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
										-
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
									<c:when test="${benefit.reimbursementBenefitLimit ne null and benefit.reimbursementPercentage ne null}">
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
										-
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
								SHARED
							</c:otherwise>
						</c:choose>
					
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top" style="text-align: center;">
					<c:if test="${benefit.memberLimitLayerId ne null and (benefit.memberLimitLayerId.diagnosisId ne null or benefit.memberLimitLayerId.diagnosisSetId ne null)}">Y</c:if>
					<c:if test="${benefit.memberLimitLayerId eq null }">N</c:if>
				</td>
					<c:if test="${theUser.userType eq 2}">
						<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
							<c:choose>
								<c:when test="${benefit.maxBenefitPerCase ne null}">
									<c:if test="${benefit.maxBenefitPerCase ne -1}">			
										<fmt:formatNumber><c:out value="${benefit.maxBenefitPerCase}" /></fmt:formatNumber>
									</c:if>
									<c:if test="${benefit.maxBenefitPerCase eq -1}">			
										AS CHARGE
									</c:if>
								</c:when>
								<c:otherwise>
									-
								</c:otherwise>
							</c:choose>			
						</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
					<c:choose>
						<c:when test="${benefit.maxBenefitOccurance ne null}">
							<c:if test="${benefit.maxBenefitOccurance ne -1}">			
								<fmt:formatNumber><c:out value="${benefit.maxBenefitOccurance}" /></fmt:formatNumber>
							</c:if>
							<c:if test="${benefit.maxBenefitOccurance eq -1}">			
								UNLIMITED
							</c:if>
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>			
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
					<c:choose>
						<c:when test="${benefit.maxOccurancePerCase ne null}">
							<c:if test="${benefit.maxOccurancePerCase ne -1}">			
								<fmt:formatNumber><c:out value="${benefit.maxOccurancePerCase}" /></fmt:formatNumber>
							</c:if>			
							<c:if test="${benefit.maxOccurancePerCase eq -1}">
								UNLIMITED
							</c:if>
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
					<c:choose>
						<c:when test="${benefit.benefitUsage ne null and benefit.benefitOccuranceUsage ne null}">
							<fmt:formatNumber><c:out value="${benefit.benefitUsage}" /> </fmt:formatNumber> - [ <b><fmt:formatNumber><c:out value="${benefit.benefitOccuranceUsage}" /></fmt:formatNumber></b> ]
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>			
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
					<c:choose>
						<c:when test="${benefit.deductibleLimit ne null}">
							<fmt:formatNumber><c:out value="${benefit.deductibleLimit}" /></fmt:formatNumber>
						</c:when>
						<c:otherwise>
							-
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
					<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;"> 
						<c:choose>
							<c:when test="${benefit.costSharingAmount ne null}">
								<fmt:formatNumber><c:out value="${benefit.costSharingAmount}" /></fmt:formatNumber>
							</c:when>
							<c:otherwise>
								-
							</c:otherwise>
						</c:choose>
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
</c:if>


<c:if test="${specialist ne null}">		

<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	<tr height="20">
		<td scope="col" align="center" class="listViewThS1" style="text-align: center; font-size: 15;" nowrap="nowrap" width="100%" colspan=20>
				&nbsp;SPECIALIST &nbsp;&nbsp; 				
				<c:choose>
					<c:when test="${specialistProduct.annualBenefit ne null}">
						<c:if test="${specialistProduct.annualBenefit gt -1}">
							| &nbsp;&nbsp; LIMIT = <fmt:formatNumber><c:out value="${specialistProduct.annualBenefit}" /></fmt:formatNumber>
						</c:if>
						<c:if test="${specialistProduct.annualBenefit eq -1}">
							| &nbsp;&nbsp; LIMIT = AS CHARGE
						</c:if>
					</c:when>
					<c:otherwise>
						| &nbsp;&nbsp; LIMIT = -
					</c:otherwise>
				</c:choose>		
		</td>
	</tr>

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
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="2%" style="text-align: center;">SMM &nbsp;</td>
			<c:if test="${theUser.userType eq 2}">
				<td scope="col" class="listViewThS1" width="5%" style="text-align: center;">
				Max Lim Per Case. / Day &nbsp;</td>
		
				<td scope="col" class="listViewThS1" width="5%" style="text-align: center;">
				Max Occur. / Year &nbsp;</td>				
		
					<td scope="col" class="listViewThS1" width="3%" style="text-align: center;">
				Max Occur. per Case / Day&nbsp;</td>
			   			
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

	<c:forEach items="${specialist}" var="benefit" varStatus="status" >
		
			 <tr height="20">
		      	<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${status.index + 1}" />.</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" >
					<c:out value="${benefit.itemCategoryId.itemCategoryCode}" /> <c:if test="${benefit.itemCategoryId.itemCategoryAlternateCode ne null}">/</c:if> <c:out value="${benefit.itemCategoryId.itemCategoryAlternateCode}" />
				</td>			   		
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" >
					<c:out value="${benefit.clientItemCode}" /> 
				</td>			   					   		
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
					[<c:out value="${benefit.caseCategoryId.caseCategoryCode}" />] <a href="claimitem?memberId=<c:out value="${memberId}" />&navigation=usagetrack&memberBenefitId=<c:out value="${benefit.memberBenefitId}" />&itemCategoryId=<c:out value="${benefit.itemCategoryId.itemCategoryId}" />&prev=listmember" class="linkDetail"><c:out value="${benefit.itemCategoryId.itemCategoryName}" /></a>		
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
									<c:when test="${benefit.benefitLimit ne null and benefit.cashlessPercentage ne null}">
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
										-
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
									<c:when test="${benefit.reimbursementBenefitLimit ne null and benefit.reimbursementPercentage ne null}">
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
										-
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
								SHARED
							</c:otherwise>
						</c:choose>
					
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top" style="text-align: center;">
					<c:if test="${benefit.memberLimitLayerId ne null and (benefit.memberLimitLayerId.diagnosisId ne null or benefit.memberLimitLayerId.diagnosisSetId ne null)}">Y</c:if>
					<c:if test="${benefit.memberLimitLayerId eq null }">N</c:if>
				</td>
					<c:if test="${theUser.userType eq 2}">
						<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
							<c:choose>
								<c:when test="${benefit.maxBenefitPerCase ne null}">
									<c:if test="${benefit.maxBenefitPerCase ne -1}">			
										<fmt:formatNumber><c:out value="${benefit.maxBenefitPerCase}" /></fmt:formatNumber>
									</c:if>
									<c:if test="${benefit.maxBenefitPerCase eq -1}">			
										AS CHARGE
									</c:if>
								</c:when>
								<c:otherwise>
									-
								</c:otherwise>
							</c:choose>			
						</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
					<c:choose>
						<c:when test="${benefit.maxBenefitOccurance ne null}">
							<c:if test="${benefit.maxBenefitOccurance ne -1}">			
								<fmt:formatNumber><c:out value="${benefit.maxBenefitOccurance}" /></fmt:formatNumber>
							</c:if>
							<c:if test="${benefit.maxBenefitOccurance eq -1}">			
								UNLIMITED
							</c:if>
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>			
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
					<c:choose>
						<c:when test="${benefit.maxOccurancePerCase ne null}">
							<c:if test="${benefit.maxOccurancePerCase ne -1}">			
								<fmt:formatNumber><c:out value="${benefit.maxOccurancePerCase}" /></fmt:formatNumber>
							</c:if>			
							<c:if test="${benefit.maxOccurancePerCase eq -1}">
								UNLIMITED
							</c:if>
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
					<c:choose>
						<c:when test="${benefit.benefitUsage ne null and benefit.benefitOccuranceUsage ne null}">
							<fmt:formatNumber><c:out value="${benefit.benefitUsage}" /> </fmt:formatNumber> - [ <b><fmt:formatNumber><c:out value="${benefit.benefitOccuranceUsage}" /></fmt:formatNumber></b> ]
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>			
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
					<c:choose>
						<c:when test="${benefit.deductibleLimit ne null}">
							<fmt:formatNumber><c:out value="${benefit.deductibleLimit}" /></fmt:formatNumber>
						</c:when>
						<c:otherwise>
							-
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
					<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;"> 
						<c:choose>
							<c:when test="${benefit.costSharingAmount ne null}">
								<fmt:formatNumber><c:out value="${benefit.costSharingAmount}" /></fmt:formatNumber>
							</c:when>
							<c:otherwise>
								-
							</c:otherwise>
						</c:choose>
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
<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	<tr height="20">
		<td scope="col" align="center" class="listViewThS1" style="text-align: center; font-size: 15;" nowrap="nowrap" width="100%" colspan=20>
				&nbsp;COVERAGE LIMIT INFORMATION</td>
	</tr>

	<tr height="20">
		<td width="2%" nowrap="nowrap" class="listViewThS1" scope="col">No.</td>						
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%" style="text-align: center;">Product Limit Layer &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%" style="text-align: center;">Diagnosis &nbsp;</td>
		<td scope="col" class="listViewThS1" width="5%" style="text-align: center;">Actual Limit &nbsp;</td>
		<td scope="col" class="listViewThS1" width="5%" style="text-align: center;">Annual Limit &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%" style="text-align: center;">Usage &nbsp;</td>
		<td scope="col" class="listViewThS1" width="3%" style="text-align: center;">Usage Percentage&nbsp;</td>				
		<td scope="col" class="listViewThS1" width="3%" style="text-align: center;">Deductible &nbsp;</td>
		<td scope="col" class="listViewThS1" width="3%" style="text-align: center;">Share (%) &nbsp;</td>				
		
	</tr>
	<tr height="20">
    	<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"></td>				   		   		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" ><b>Layer Utama</b></td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" ></td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
			<c:choose>
				<c:when test="${specialistProduct.actualBenefitLimit ne null}">
					<b><fmt:formatNumber><c:out value="${specialistProduct.actualBenefitLimit}" /></fmt:formatNumber></b>
				</c:when>
				<c:otherwise>
					-
				</c:otherwise>
			</c:choose>			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
			<c:choose>
				<c:when test="${specialistProduct.annualBenefit ne null}">
					<b><fmt:formatNumber><c:out value="${specialistProduct.annualBenefit}" /></fmt:formatNumber></b>
				</c:when>
				<c:otherwise>
					-
				</c:otherwise>
			</c:choose>							
		</td>			
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
			<c:choose>
				<c:when test="${specialistProduct.benefitUsage ne null}">
					<b><fmt:formatNumber><c:out value="${specialistProduct.benefitUsage}" /> </fmt:formatNumber></b>
				</c:when>
				<c:otherwise>
					-
				</c:otherwise>
			</c:choose>	 			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
			<c:choose>
				<c:when test="${specialistProduct.benefitUsage ne null and specialistProduct.annualBenefit ne null}">
					<b><fmt:formatNumber><c:out value="${specialistProduct.benefitUsage / specialistProduct.annualBenefit * 100}" /> </fmt:formatNumber> %</b>
				</c:when>
				<c:otherwise>
					-
				</c:otherwise>
			</c:choose>				
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">			
						
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;"></td>				
   	</tr>   
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	<c:forEach items="${specialistLayer}" var="benefit" varStatus="status" >
	 	<tr height="20">
      		<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${status.index + 1}" />.</td>				   		   		
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" >
				<a href="javascript:popupBenefitList('<c:out value="${benefit.memberLimitLayerId}" />')" class="linkDetail">Layer - <c:out value="${benefit.layer}" /></a>
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" >
				<c:if test="${benefit.diagnosisSetId ne null}"><b><a href="javascript:popupDiagnosisList('<c:out value="${benefit.diagnosisSetId.diagnosisSetId}" />')" class="linkDetail">[SMM] <c:out value="${benefit.diagnosisSetId.diagnosisSetName}" /></a></b></c:if> 
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
				<c:choose>
					<c:when test="${benefit.actualBenefitLimit ne null}">
						<fmt:formatNumber><c:out value="${benefit.actualBenefitLimit}" /></fmt:formatNumber>
					</c:when>
					<c:otherwise>
						-
					</c:otherwise>
				</c:choose>			
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">	
				<c:choose>
					<c:when test="${benefit.annualLimit ne null}">
						<fmt:formatNumber><c:out value="${benefit.annualLimit}" /></fmt:formatNumber>
					</c:when>
					<c:otherwise>
						-
					</c:otherwise>
				</c:choose>						
			</td>			
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
				<c:choose>
					<c:when test="${benefit.benefitUsage ne null}">
						<fmt:formatNumber><c:out value="${benefit.benefitUsage}" /> </fmt:formatNumber>	
					</c:when>
					<c:otherwise>
						-
					</c:otherwise>
				</c:choose>		
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
				<c:choose>
					<c:when test="${benefit.benefitUsage ne null and benefit.annualLimit ne null}">
						<fmt:formatNumber><c:out value="${benefit.benefitUsage / benefit.annualLimit * 100}" /> </fmt:formatNumber> %	
					</c:when>
					<c:otherwise>
						-
					</c:otherwise>
				</c:choose>					
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
				<c:choose>
					<c:when test="${benefit.deductible ne null}">
						<fmt:formatNumber><c:out value="${benefit.deductible}" /></fmt:formatNumber>	
					</c:when>
					<c:otherwise>
						-
					</c:otherwise>
				</c:choose>					
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;">			
				<c:choose>
					<c:when test="${benefit.coSharePercentage < 100}">
						<fmt:formatNumber><c:out value="${benefit.coSharePercentage}" /></fmt:formatNumber> %
					</c:when>
					<c:otherwise>
						<center>-</center>
					</c:otherwise>
					
				</c:choose>
			</td>
				
    	</tr>   
		<tr>
	      <td colspan="20" class="listViewHRS1"></td>
	    </tr>
	
	</c:forEach>
	
	</tbody>
</table>

</c:if>


<c:if test="${mcu ne null}">
<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	<tr height="20">
		<td scope="col" align="center" class="listViewThS1" style="text-align: center; font-size: 15;" nowrap="nowrap" width="100%" colspan=20>
				&nbsp;MEDICAL CHECK UP</td>
	</tr>

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
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="2%" style="text-align: center;">SMM &nbsp;</td>
			<c:if test="${theUser.userType eq 2}">
				<td scope="col" class="listViewThS1" width="5%" style="text-align: center;">
				Max Lim Per Case. / Day &nbsp;</td>
		
				<td scope="col" class="listViewThS1" width="5%" style="text-align: center;">
				Max Occur. / Year &nbsp;</td>				
		
					<td scope="col" class="listViewThS1" width="3%" style="text-align: center;">
				Max Occur. per Case / Day&nbsp;</td>
			   			
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


	<c:forEach items="${mcu}" var="benefit" varStatus="status" >
	
		
		 	<tr height="20">
		      	<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${status.index + 1}" />.</td>					   		   		
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" >
					<c:out value="${benefit.itemCategoryId.itemCategoryCode}" /> <c:if test="${benefit.itemCategoryId.itemCategoryAlternateCode ne null}">/</c:if> <c:out value="${benefit.itemCategoryId.itemCategoryAlternateCode}" />
				</td>			   		
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" >
					<c:out value="${benefit.clientItemCode}" /> 
				</td>			   					   		
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
					[<c:out value="${benefit.caseCategoryId.caseCategoryCode}" />] <a href="claimitem?memberId=<c:out value="${memberId}" />&navigation=usagetrack&memberBenefitId=<c:out value="${benefit.memberBenefitId}" />&itemCategoryId=<c:out value="${benefit.itemCategoryId.itemCategoryId}" />&prev=listmember" class="linkDetail"><c:out value="${benefit.itemCategoryId.itemCategoryName}" /></a>		
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
									<c:when test="${benefit.benefitLimit ne null and benefit.cashlessPercentage ne null}">
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
										-
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
									<c:when test="${benefit.reimbursementBenefitLimit ne null and benefit.reimbursementPercentage ne null}">
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
										-
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
								SHARED
							</c:otherwise>
						</c:choose>
					
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top" style="text-align: center;">
					<c:if test="${benefit.memberLimitLayerId ne null and (benefit.memberLimitLayerId.diagnosisId ne null or benefit.memberLimitLayerId.diagnosisSetId ne null)}">Y</c:if>
					<c:if test="${benefit.memberLimitLayerId eq null }">N</c:if>
				</td>
					<c:if test="${theUser.userType eq 2}">
						<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
							<c:choose>
								<c:when test="${benefit.maxBenefitPerCase ne null}">
									<c:if test="${benefit.maxBenefitPerCase ne -1}">			
										<fmt:formatNumber><c:out value="${benefit.maxBenefitPerCase}" /></fmt:formatNumber>
									</c:if>
									<c:if test="${benefit.maxBenefitPerCase eq -1}">			
										AS CHARGE
									</c:if>
								</c:when>
								<c:otherwise>
									-
								</c:otherwise>
							</c:choose>			
						</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
					<c:choose>
						<c:when test="${benefit.maxBenefitOccurance ne null}">
							<c:if test="${benefit.maxBenefitOccurance ne -1}">			
								<fmt:formatNumber><c:out value="${benefit.maxBenefitOccurance}" /></fmt:formatNumber>
							</c:if>
							<c:if test="${benefit.maxBenefitOccurance eq -1}">			
								UNLIMITED
							</c:if>
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>			
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
					<c:choose>
						<c:when test="${benefit.maxOccurancePerCase ne null}">
							<c:if test="${benefit.maxOccurancePerCase ne -1}">			
								<fmt:formatNumber><c:out value="${benefit.maxOccurancePerCase}" /></fmt:formatNumber>
							</c:if>			
							<c:if test="${benefit.maxOccurancePerCase eq -1}">
								UNLIMITED
							</c:if>
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
					<c:choose>
						<c:when test="${benefit.benefitUsage ne null and benefit.benefitOccuranceUsage ne null}">
							<fmt:formatNumber><c:out value="${benefit.benefitUsage}" /> </fmt:formatNumber> - [ <b><fmt:formatNumber><c:out value="${benefit.benefitOccuranceUsage}" /></fmt:formatNumber></b> ]
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>			
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
					<c:choose>
						<c:when test="${benefit.deductibleLimit ne null}">
							<fmt:formatNumber><c:out value="${benefit.deductibleLimit}" /></fmt:formatNumber>
						</c:when>
						<c:otherwise>
							-
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
					<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;"> 
						<c:choose>
							<c:when test="${benefit.costSharingAmount ne null}">
								<fmt:formatNumber><c:out value="${benefit.costSharingAmount}" /></fmt:formatNumber>
							</c:when>
							<c:otherwise>
								-
							</c:otherwise>
						</c:choose>	
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
<br /> <br />
</c:if>


<c:if test="${misc ne null}">
<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	<tr height="20">
		<td scope="col" align="center" class="listViewThS1" style="text-align: center; font-size: 15;" nowrap="nowrap" width="100%" colspan=20>
				&nbsp;PELAYANAN LAIN LAIN</td>
	</tr>

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
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="2%" style="text-align: center;">SMM&nbsp;</td>
			<c:if test="${theUser.userType eq 2}">
				<td scope="col" class="listViewThS1" width="5%" style="text-align: center;">
				Max Lim Per Case. / Day &nbsp;</td>
		
				<td scope="col" class="listViewThS1" width="5%" style="text-align: center;">
				Max Occur. / Year &nbsp;</td>				
		
					<td scope="col" class="listViewThS1" width="3%" style="text-align: center;">
				Max Occur. per Case / Day&nbsp;</td>
			   			
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


	<c:forEach items="${misc}" var="benefit" varStatus="status" >
	
		
			 <tr height="20">
		      	<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${status.index + 1}" />.</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" >
					<c:out value="${benefit.itemCategoryId.itemCategoryCode}" /> <c:if test="${benefit.itemCategoryId.itemCategoryAlternateCode ne null}">/</c:if> <c:out value="${benefit.itemCategoryId.itemCategoryAlternateCode}" />
				</td>			   		
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" >
					<c:out value="${benefit.clientItemCode}" /> 
				</td>			   					   		
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
					[<c:out value="${benefit.caseCategoryId.caseCategoryCode}" />] <a href="claimitem?memberId=<c:out value="${memberId}" />&navigation=usagetrack&memberBenefitId=<c:out value="${benefit.memberBenefitId}" />&itemCategoryId=<c:out value="${benefit.itemCategoryId.itemCategoryId}" />&prev=listmember" class="linkDetail"><c:out value="${benefit.itemCategoryId.itemCategoryName}" /></a>		
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
									<c:when test="${benefit.benefitLimit ne null and benefit.cashlessPercentage ne null}">
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
										-
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
									<c:when test="${benefit.reimbursementBenefitLimit ne null and benefit.reimbursementPercentage ne null}">
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
										-
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
								SHARED
							</c:otherwise>
						</c:choose>
					
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top" style="text-align: center;">
					<c:if test="${benefit.memberLimitLayerId ne null and (benefit.memberLimitLayerId.diagnosisId ne null or benefit.memberLimitLayerId.diagnosisSetId ne null)}">Y</c:if>
					<c:if test="${benefit.memberLimitLayerId eq null }">N</c:if>
				</td>
					<c:if test="${theUser.userType eq 2}">
						<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
							<c:choose>
								<c:when test="${benefit.maxBenefitPerCase ne null}">
									<c:if test="${benefit.maxBenefitPerCase ne -1}">			
										<fmt:formatNumber><c:out value="${benefit.maxBenefitPerCase}" /></fmt:formatNumber>
									</c:if>
									<c:if test="${benefit.maxBenefitPerCase eq -1}">			
										AS CHARGE
									</c:if>
								</c:when>
								<c:otherwise>
									-
								</c:otherwise>
							</c:choose>			
						</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
					<c:choose>
						<c:when test="${benefit.maxBenefitOccurance ne null}">
							<c:if test="${benefit.maxBenefitOccurance ne -1}">			
								<fmt:formatNumber><c:out value="${benefit.maxBenefitOccurance}" /></fmt:formatNumber>
							</c:if>
							<c:if test="${benefit.maxBenefitOccurance eq -1}">			
								UNLIMITED
							</c:if>	
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>	
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
					<c:choose>
						<c:when test="${benefit.maxOccurancePerCase ne null}">
							<c:if test="${benefit.maxOccurancePerCase ne -1}">			
								<fmt:formatNumber><c:out value="${benefit.maxOccurancePerCase}" /></fmt:formatNumber>
							</c:if>			
							<c:if test="${benefit.maxOccurancePerCase eq -1}">
								UNLIMITED
							</c:if>
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>	
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
					<c:choose>
						<c:when test="${benefit.benefitUsage ne null and benefit.benefitOccuranceUsage ne null}">
							<fmt:formatNumber><c:out value="${benefit.benefitUsage}" /> </fmt:formatNumber> - [ <b><fmt:formatNumber><c:out value="${benefit.benefitOccuranceUsage}" /></fmt:formatNumber></b> ]
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>				
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
					<c:choose>
						<c:when test="${benefit.deductibleLimit ne null}">
							<fmt:formatNumber><c:out value="${benefit.deductibleLimit}" /></fmt:formatNumber>
						</c:when>
						<c:otherwise>
							-
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
					<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;"> 
						<c:choose>
							<c:when test="${benefit.costSharingAmount ne null}">
								<fmt:formatNumber><c:out value="${benefit.costSharingAmount}" /></fmt:formatNumber>
							</c:when>
							<c:otherwise>
								-
							</c:otherwise>
						</c:choose>					
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
<br /> <br />
</c:if>
</form>
	
<!-- Table Content Stop -->
	<!-- Table Toolbar Start -->
	
		<!-- Table Toolbar Stop -->



<!-- Table Container Stop -->
<script language="Javascript">
$(document).ready(function(){
	$.get("firstcall?navigation=jsontotalmemberelog&memberId=<c:out value="${member.memberId }"/>", function(data){
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
function popupDiagnosisList(diagSetId){
	window.open ("diagnosissetdetail?navigation=lookup&diagnosisSetId="+diagSetId+"&url=member-form","Search","width=700, height=550, menubar=no, status=no, toolbar=no, scrollbars=yes, resizable=yes,screenX=200,screenY=100");
}
function detil (idx){
	document.form1.method = "POST";
	document.form1.claimInvestigationId.value = idx;
	document.form1.action = "claiminvestigation";
	document.form1.navigation.value = "detail";
	document.form1.submit();

}
function printErrorLog(){
	window.open ("firstcall?navigation=searchmembererrorlog&memberId=<c:out value="${member.memberId}" />","Search","width=1024, height=768, menubar=yes, status=no, toolbar=no, scrollbars=yes, resizable=yes");		
}

function adderrorlog (){ 
	window.location.href = "firstcall-form?navigation=memberelog&memberId=<c:out value="${member.memberId}" />";
}
</script>
