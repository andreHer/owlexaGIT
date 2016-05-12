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
int index = 0;
int totalIndex = 0;
int count = 0;
int countSet = 0;

try {
	index = ((Integer) request.getAttribute ("index")).intValue();
	count = ((Integer) request.getAttribute ("count")).intValue();
	countSet = ((Integer) request.getAttribute ("countSet")).intValue();
	totalIndex = ((Integer) request.getAttribute ("halAkhir")).intValue();
	
	
	
}
catch (Exception e){
}

if (alert != null && !alert.trim().equals("")) {%>
	<div id="warning" align="center">
		<c:out value="${alert}"></c:out>
	</div>
	<%}%>

<%
String rowclass = "";
int i=0;
int indexint = Integer.parseInt(request.getAttribute("index").toString());
WebUtil.getAttributeInteger(request,"index",0).intValue();
%>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;List Member Product</h3></td>
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

<!-- Search Container Start -->
<%@ include file="../mainContentMember.jsp" %>

<br />

<form name="form1" action="memberproduct" method="POST">
<input type="hidden" name="navigation" value="gosearch">
<input type="hidden" name="arah" value="">
<input type="hidden" name="index" value="<c:out value="${index}" />">
				<input type="hidden" name="memberProductId" id="memberProductId" value="<c:out value="${memberProduct.memberProductId}" />">
<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	<tr>
      <td colspan="20" align="right"><table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tbody>
          <tr>
            
            <td class="listViewPaginationTdS1" align="left"></td>
            <td class="listViewPaginationTdS1" align="right" nowrap="nowrap" >
				
				<%
				if (index == 1){
				%>
				<img src="images/start_off.gif" alt="Start" align="absmiddle" border="0" height="10" width="9">
					Start&nbsp;
									
				<img src="images/previous_off.gif" alt="Previous" align="absmiddle" border="0" height="10" width="4">
					Previous&nbsp;&nbsp;					
				<%
				}
				else if ((index-1) > 0){
				
				%>
				<img src="images/start.gif" alt="Start" align="absmiddle" border="0" height="10" width="9">
					<a href="javascript:goleftbgt()" class="listViewPaginationLinkS1">
						Start&nbsp;
					</a>				
				
				<img src="images/previous.gif" alt="Previous" align="absmiddle" border="0" height="10" width="4">
					<a href="javascript:goleft()" class="listViewPaginationLinkS1">
					Previous&nbsp;&nbsp;
					</a>
				<%
				}
				%>
					<span class="pageNumbers">(<c:out value="${minIndex}" /> - <c:out value="${maxIndex}" /> of <c:out value="${count}" />)</span>&nbsp;&nbsp;
				
				<%
				if (totalIndex > index){
				%>
				
				<a href="javascript:goright()" class="listViewPaginationLinkS1">Next&nbsp;
				<img src="images/next.gif" alt="Next" align="absmiddle" border="0" height="10" width="4"></a>&nbsp;&nbsp;
				<a href="javascript:gorightbgt()" class="listViewPaginationLinkS1">End&nbsp;
					<img src="images/end.gif" alt="End" align="absmiddle" border="0" height="10" width="9"></a>
				<%
				} else {
				
				%>
				Next&nbsp;
				<img src="images/next_off.gif" alt="Next" align="absmiddle" border="0" height="10" width="4">
				&nbsp;&nbsp;
				End&nbsp;
					<img src="images/end_off.gif" alt="End" align="absmiddle" border="0" height="10" width="9">
				<%
				}
				%>
			</td>
          </tr>
        </tbody>
      </table></td>
    </tr>
	<tr height="20">
		   <td width="2%" nowrap="nowrap" class="listViewThS1" scope="col">
			<img src="images/blank.gif" alt="asd" height="1" width="1">No.</td>
		   <td scope="col" class="listViewThS1" nowrap="nowrap" width="8%">Product Code&nbsp;</td>
		   <td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Product Type&nbsp;</td>
		   <td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Service&nbsp;</td>
		   <td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Shared Plan&nbsp;</td>
		   <td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Client&nbsp;</td>
		   <td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Effective&nbsp;</td>
		   <td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Expire&nbsp;		</td>   
		   <td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Benefit Usage&nbsp;</td>
		   		   
		   <td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Actual Benefit Limit&nbsp;		   </td>
		   <td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Annual Benefit&nbsp;		   </td>
		   <td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Usage %&nbsp;		   </td>
		   		   
		      
		   <td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Status&nbsp;	</td>
<!-- 		   <td scope="col" class="listViewThS1" width="5%">Modified Time&nbsp;	</td> -->
		   <td scope="col" class="listViewThS1" width="5%">Created Time&nbsp;	</td>
		   	
	</tr>


	<c:forEach items="${MemberProducts}" var="memberProduct" varStatus="status" >
	<%	if (i % 2 == 0) {
			rowclass = "col1";
		} else if (i % 2 != 0) {
			rowclass= "col2";
		}
		i++;
	%>
	 <tr height="20">
      <td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><%=(i+((indexint-1)*countSet))%></td>
     		   		   		   		   		   		   		   		   		
       <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="left" valign="top" >
			
			<%-- [<c:out value="${memberProduct.productId.productCode}" />]  --%><a href="memberproduct?memberId=<c:out value="${memberId}" />&navigation=list&memberProductId=<c:out value="${memberProduct.memberProductId}" />&prev=list" class="linkDetail" id="productCode"><c:out value="${memberProduct.productId.productCode}" /></a>
	  </td>
	  <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="left" valign="top">
			<c:out value="${memberProduct.productId.productType.productTypeName}" />
	  </td>
	  <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="left" valign="top">
			<c:out value="${memberProduct.productId.caseCategory.caseCategoryCode}" />
	  </td>
	  <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="left" valign="top">
			<c:out value="${memberProduct.secondaryCoverageId.productId.productName}" />
	  </td>
	  <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="left" valign="top">
			<c:out value="${memberProduct.memberId.clientId.clientName}" />
	  </td>
       <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="left" valign="top">
			<c:out value="${memberProduct.registerDate}" />
	  </td>					   		   		
       <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="left" valign="top">
			<c:out value="${memberProduct.expireDate}" />
	  </td>					   		   							   		   		
       <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="left" valign="top" style="text-align: right;">
			<fmt:formatNumber><c:out value="${memberProduct.benefitUsage}" /></fmt:formatNumber>
	  </td>
	  <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="left" valign="top"  style="text-align: right;">
	  		<c:if test="${memberProduct.secondaryCoverageId eq null and memberProduct.familyProductId eq null}">
		  		<c:if test="${memberProduct.actualBenefitLimit eq -1.0}">
	      			UNLIMITED
	      		</c:if>
	      		<c:if test="${memberProduct.actualBenefitLimit > -1.0}">      		
					<fmt:formatNumber><c:out value="${memberProduct.actualBenefitLimit}" /></fmt:formatNumber>
				</c:if>
			</c:if>
			<c:if test="${memberProduct.secondaryCoverageId ne null}">
		  		<c:if test="${memberProduct.secondaryCoverageId.actualBenefitLimit eq -1.0}">
	      			UNLIMITED
	      		</c:if>
	      		<c:if test="${memberProduct.secondaryCoverageId.actualBenefitLimit > -1.0}">      		
					<fmt:formatNumber><c:out value="${memberProduct.secondaryCoverageId.actualBenefitLimit}" /></fmt:formatNumber>
				</c:if>
			</c:if>
			
			<c:if test="${memberProduct.familyProductId ne null}">
		  		<c:if test="${memberProduct.familyProductId.actualBenefitLimit eq -1.0}">
	      			UNLIMITED
	      		</c:if>
	      		<c:if test="${memberProduct.familyProductId.actualBenefitLimit > -1.0}">      		
					<fmt:formatNumber><c:out value="${memberProduct.familyProductId.actualBenefitLimit}" /></fmt:formatNumber>
				</c:if>
			</c:if>
	  </td>
      <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="left" valign="top"  style="text-align: right;">
      		
			
			<c:if test="${memberProduct.secondaryCoverageId eq null and memberProduct.familyProductId eq null}">
		  		<c:if test="${memberProduct.annualBenefit eq -1.0}">
	      			UNLIMITED
	      		</c:if>
	      		<c:if test="${memberProduct.annualBenefit > -1.0}">      		
					<fmt:formatNumber><c:out value="${memberProduct.annualBenefit}" /></fmt:formatNumber>
				</c:if>
			</c:if>
			<c:if test="${memberProduct.secondaryCoverageId ne null}">
		  		<c:if test="${memberProduct.secondaryCoverageId.annualBenefit eq -1.0}">
	      			UNLIMITED
	      		</c:if>
	      		<c:if test="${memberProduct.secondaryCoverageId.annualBenefit > -1.0}">      		
					<fmt:formatNumber><c:out value="${memberProduct.secondaryCoverageId.annualBenefit}" /></fmt:formatNumber>
				</c:if>
			</c:if>			
			<c:if test="${memberProduct.familyProductId ne null}">
		  		<c:if test="${memberProduct.familyProductId.annualBenefit eq -1.0}">
	      			UNLIMITED
	      		</c:if>
	      		<c:if test="${memberProduct.familyProductId.annualBenefit > -1.0}">      		
					<fmt:formatNumber><c:out value="${memberProduct.familyProductId.annualBenefit}" /></fmt:formatNumber>
				</c:if>
			</c:if>
	  </td>
	  <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="left" valign="top"  style="text-align: right;">
	  		
	  		<c:if test="${memberProduct.secondaryCoverageId eq null and memberProduct.familyProductId eq null}">
		  		<c:if test="${memberProduct.annualBenefit eq -1.0}">
	      			-
	      		</c:if>
	      		<c:if test="${memberProduct.annualBenefit > -1.0}">      		
					<fmt:formatNumber><c:out value="${memberProduct.benefitUsagePercentage}" /></fmt:formatNumber> %
				</c:if>
			</c:if>
			<c:if test="${memberProduct.secondaryCoverageId ne null}">
		  		<c:if test="${memberProduct.secondaryCoverageId.annualBenefit eq -1.0}">
	      			-
	      		</c:if>
	      		<c:if test="${memberProduct.secondaryCoverageId.annualBenefit > -1.0}">      		
					<fmt:formatNumber><c:out value="${memberProduct.benefitUsagePercentage}" /></fmt:formatNumber> %
				</c:if>
			</c:if>			
			<c:if test="${memberProduct.familyProductId ne null}">
		  		<c:if test="${memberProduct.familyProductId.annualBenefit eq -1.0}">
	      			-
	      		</c:if>
	      		<c:if test="${memberProduct.familyProductId.annualBenefit > -1.0}">      		
					<fmt:formatNumber><c:out value="${memberProduct.benefitUsagePercentage}" /></fmt:formatNumber> %
				</c:if>
			</c:if>
			
	  </td>
      <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="left" valign="top">
			<c:out value="${memberProduct.memberProductStatus.status}" />
	  </td>
<!-- 	  <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="left" valign="top"> -->
<!-- 			<c:out value="${memberProduct.modifiedTime}" /> -->
<!-- 	  </td>		   		   								   		   							   		   		 -->

	 <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="left" valign="top">
		<c:out value="${memberProduct.createdTime}" />
	 </td>	
    </tr>
   
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	
	</c:forEach>
	
<tr>
            <td class="listViewPaginationTdS1" align="left"></td>
            <td class="listViewPaginationTdS1" align="right" nowrap="nowrap" colspan=20>
				
				<%
				if (index == 1){
				%>
				<img src="images/start_off.gif" alt="Start" align="absmiddle" border="0" height="10" width="9">
					Start&nbsp;
									
				<img src="images/previous_off.gif" alt="Previous" align="absmiddle" border="0" height="10" width="4">
					Previous&nbsp;&nbsp;					
				<%
				}
				else if ((index-1) > 0){
				
				%>
				<img src="images/start.gif" alt="Start" align="absmiddle" border="0" height="10" width="9">
					<a href="javascript:goleftbgt()" class="listViewPaginationLinkS1">
						Start&nbsp;
					</a>				
				
				<img src="images/previous.gif" alt="Previous" align="absmiddle" border="0" height="10" width="4">
					<a href="javascript:goleft()" class="listViewPaginationLinkS1">
					Previous&nbsp;&nbsp;
					</a>
				<%
				}
				%>
					<span class="pageNumbers">(<c:out value="${minIndex}" /> - <c:out value="${maxIndex}" /> of <c:out value="${count}" />)</span>&nbsp;&nbsp;
				
				<%
				if (totalIndex > index){
				%>
				
				<a href="javascript:goright()" class="listViewPaginationLinkS1">Next&nbsp;
				<img src="images/next.gif" alt="Next" align="absmiddle" border="0" height="10" width="4">
				</a>&nbsp;&nbsp;
				<a href="javascript:gorightbgt()" class="listViewPaginationLinkS1">End&nbsp;
					<img src="images/end.gif" alt="End" align="absmiddle" border="0" height="10" width="9">
				</a>
				<%
				} else {
				
				%>
				Next&nbsp;
				<img src="images/next_off.gif" alt="Next" align="absmiddle" border="0" height="10" width="4">
				&nbsp;&nbsp;
				End&nbsp;
					<img src="images/end_off.gif" alt="End" align="absmiddle" border="0" height="10" width="9">
				<%
				}
				%>
			</td>
          </tr>
	</tbody>
	</table>
	
	

<% Integer memberProductBenefits = (Integer) request.getAttribute("memberProductIdBenefit") ;
if(memberProductBenefits != null  ){
%>
<c:out value="${memberProductBenefits}" />
<div id="productBenefit">	
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
<%-- <c:if test="${inpatient ne null}">		 --%>

<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
<!-- 	<tr height="20"> -->
<!-- 		<td scope="col" align="center" class="listViewThS1" style="text-align: center; font-size: 15;" nowrap="nowrap" width="100%" colspan=16> -->
<!-- 				&nbsp;INPATIENT &nbsp;&nbsp; <c:if test="${inpatientLimit gt -1}">| &nbsp;&nbsp; LIMIT = <fmt:formatNumber><c:out value="${inpatientLimit}" /></fmt:formatNumber></c:if></td> -->
<!-- 	</tr> -->

<%   Integer caseCategory = (Integer) request.getAttribute("caseCategory"); %>

<c:set var="caseCategory" value="<%= caseCategory%>"> </c:set> 
	<tr height="20"> 
 		<td scope="col" align="center" class="listViewThS1" style="text-align: center; font-size: 15;" nowrap="nowrap" width="100%" colspan=16> 
 				&nbsp; <c:out value="${productCode}" />   -   
			<c:if test="${caseCategory eq 1}">INPATIENT</c:if>
			<c:if test="${caseCategory eq 2}">OUTPATIENT</c:if>
			<c:if test="${caseCategory eq 3}">MATERNITY</c:if>
			<c:if test="${caseCategory  eq 4}">DENTAL</c:if>
			<c:if test="${caseCategory eq 5}">OPTICAL</c:if>
			<c:if test="${caseCategory eq 6}">DR SPECIALIST</c:if>
			<c:if test="${caseCategory eq 7}">PRE INPATIENT SERVICE</c:if>
			<c:if test="${caseCategory eq 8}">POST INPATIENT SERVICE</c:if>
			<c:if test="${caseCategory eq 9}">MCU</c:if>
			<c:if test="${caseCategory eq 10}">PRE AND POST INPATIENT SERVICE</c:if>
			<c:if test="${caseCategory eq 11}">SURGERY SERVICE</c:if>
			<c:if test="${caseCategory eq 12}">MISC</c:if>
			<c:if test="${caseCategory eq 13}">ONE DAY CARE</c:if>						
			<c:if test="${caseCategory eq 14}">DR UMUM PPK1</c:if>  
			<c:if test="${caseCategory eq 15}">DR GIGI PPK1</c:if> - 
			<c:if test="${statusBenefit eq -1}">PENDING</c:if>
			<c:if test="${statusBenefit eq 1}">ACTIVE</c:if>
			<c:if test="${statusBenefit eq -3}">PENDING CHANGEPLAN</c:if>
			<c:if test="${statusBenefit eq -2}">BLOCKED</c:if>
			<c:if test="${statusBenefit eq 2}">TERMINATED</c:if>
			<c:if test="${statusBenefit eq 3}">RESIGNED</c:if>
			<c:if test="${statusBenefit eq 4}">INACTIVE</c:if>
			<c:if test="${statusBenefit eq 5}">INITIALIZED</c:if>
			<c:if test="${statusBenefit eq 6}">UPGRADED</c:if> - <c:out value="${modifiedDateBenefit}" />   &nbsp;&nbsp;</td> 
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
	<c:forEach items="${Benefit}" var="benefit" varStatus="status" >

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
					<c:if test="${benefit.maxBenefitPerCase ne -1}">			
						<fmt:formatNumber><c:out value="${benefit.maxBenefitPerCase}" /></fmt:formatNumber>
					</c:if>
					<c:if test="${benefit.maxBenefitPerCase eq -1}">			
						AS CHARGE
					</c:if>			
				</td>
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
<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	<tr height="20">
		<td scope="col" align="center" class="listViewThS1" style="text-align: center; font-size: 15;" nowrap="nowrap" width="100%" colspan=16>
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
			<b><fmt:formatNumber><c:out value="${serviceProduct.actualBenefitLimit}" /></fmt:formatNumber></b>			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
			<b><fmt:formatNumber><c:out value="${serviceProduct.annualBenefit}" /></fmt:formatNumber></b>						
		</td>			
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">			
			<b><fmt:formatNumber><c:out value="${serviceProduct.benefitUsage}" /> </fmt:formatNumber></b> 			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">			
			<b><fmt:formatNumber><c:out value="${serviceProduct.benefitUsage / serviceProduct.annualBenefit * 100}" /> </fmt:formatNumber> %</b>			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">			
						
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;"></td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;"></td>		
   	</tr>   
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	<c:forEach items="${serviceLayer}" var="benefit" varStatus="status" >
	 	<tr height="20">
      		<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${status.index + 1}" />.</td>				   		   		
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" >Layer - <c:out value="${benefit.layer}" /></td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" >
								<c:out value="${benefit.diagnosisSetId.diagnosisSetName}" /> 
				
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
				<c:if test="${benefit.familyLimitLayerId eq null }">
					<fmt:formatNumber><c:out value="${benefit.actualBenefitLimit}" /></fmt:formatNumber>
				</c:if>
				<c:if test="${benefit.familyLimitLayerId ne null }">
					<fmt:formatNumber><c:out value="${benefit.familyLimitLayerId.actualBenefitLimit}" /></fmt:formatNumber>
				</c:if>						
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
				<c:if test="${benefit.familyLimitLayerId eq null }">
					<fmt:formatNumber><c:out value="${benefit.annualLimit}" /></fmt:formatNumber>
				</c:if>
				<c:if test="${benefit.familyLimitLayerId ne null }">
					<fmt:formatNumber><c:out value="${benefit.familyLimitLayerId.annualLimit}" /></fmt:formatNumber>
				</c:if>												
			</td>			
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">			
				<c:if test="${benefit.familyLimitLayerId eq null }">
					<fmt:formatNumber><c:out value="${benefit.benefitUsage}" /></fmt:formatNumber>
				</c:if>
				<c:if test="${benefit.familyLimitLayerId ne null }">
					<fmt:formatNumber><c:out value="${benefit.familyLimitLayerId.benefitUsage}" /></fmt:formatNumber>
				</c:if>			
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">			
				<fmt:formatNumber><c:out value="${benefit.benefitUsage / benefit.annualLimit * 100}" /> </fmt:formatNumber> %
				
				<c:if test="${benefit.familyLimitLayerId eq null }">
					<fmt:formatNumber><c:out value="${benefit.benefitUsage / benefit.annualLimit * 100}" /> </fmt:formatNumber> %
				</c:if>
				<c:if test="${benefit.familyLimitLayerId ne null }">
					<fmt:formatNumber><c:out value="${benefit.familyLimitLayerId.benefitUsage / benefit.familyLimitLayerId.annualLimit * 100}" /> </fmt:formatNumber> %
				</c:if>						
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">			
				<c:if test="${benefit.familyLimitLayerId eq null }">
					<fmt:formatNumber><c:out value="${benefit.deductible}" /></fmt:formatNumber>
				</c:if>
				<c:if test="${benefit.familyLimitLayerId ne null }">
					<fmt:formatNumber><c:out value="${benefit.familyLimitLayerId.deductible}" /></fmt:formatNumber>
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


<br /> <br />
</div>
	
<% } %>
	
	
	
	
	
	
	
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
function tambahi (){
	document.form1.navigation.value = "tambah";
	document.form1.action = "memberproduct-form";
	document.form1.method = "GET";
	document.form1.submit();
}
function hapus (idx){
	var delConfirm = window.confirm ("Are You Sure Want To Delete This Entry ?");

	if (delConfirm){
		document.form1.method = "POST";
		document.form1.memberProductId.value = idx;
		document.form1.action = "memberproduct";
		document.form1.navigation.value = "delete";
		document.form1.submit();
	}
}
function ubah (idx){
	document.form1.method = "GET";
	document.form1.memberProductId.value = idx;
	document.form1.action = "memberproduct-form";
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
	document.form1.action = "memberproduct";
	document.form1.method = "POST";
	document.form1.submit();
}
function detil (idx){
	document.form1.method = "POST";
	document.form1.memberProductId.value = idx;
	document.form1.action = "memberproduct";
	document.form1.navigation.value = "detail";
	document.form1.submit();

}
function popupDiagnosisList(dxSetId){
	
	window.open ("diagnosissetdetail?navigation=lookup&diagnosisSetId="+dxSetId+"&url=member-form","Search","width=700, height=550, menubar=no, status=no, toolbar=no, scrollbars=yes, resizable=yes,screenX=200,screenY=100");
}
function printErrorLog(){
	window.open ("firstcall?navigation=searchmembererrorlog&memberId=<c:out value="${member.memberId}" />","Search","width=1024, height=768, menubar=yes, status=no, toolbar=no, scrollbars=yes, resizable=yes");		
}

function adderrorlog (){ 
	window.location.href = "firstcall-form?navigation=memberelog&memberId=<c:out value="${member.memberId}" />";
}
</script>

