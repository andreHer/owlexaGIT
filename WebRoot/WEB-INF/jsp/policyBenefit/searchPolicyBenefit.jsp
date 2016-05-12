<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>

<script type="text/javascript" src="scripts/tinymce/tinymce.min.js"></script>
<script type="text/javascript">
tinymce.init({
    selector: "textarea",
    readonly : true,
	toolbar: "false",
	menubar: "false",
	height: '100%'
 });
</script>


<%
String navigation = WebUtil.getAttributeString(request,"navigation","");
%>

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

%>
<!-- Search Container Start -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;List Policy Benefit</h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>

<%@ include file="../mainContentPolicy.jsp" %>

<br />
<form name="form1" action="policybenefit" method="POST">
	<input type="hidden" name="navigation" value="gosearch">
	<input type="hidden" name="arah" value="">
	<input type="hidden" name="policyId" value="<c:out value="${policyId}" />">
	<input type="hidden" name="index" value="<c:out value="${index}" />">
	<input type="hidden" name="policyBenefitId" value="<c:out value="${policyBenefit.policyBenefitId}" />">
		

<c:if test="${theUser.roleId.roleId ne 6}">
	<table>
		<tr>
			<td>
				<input type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:tambahi()" value=" Add Benefit "/>
			</td>
			<td>
				<a href="policybenefit?navigation=export&policyId=<c:out value="${policy.policyId}" />">
				<input title="Search [Alt+Shift+Q]" accesskey="Q" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" name="buttonDownload" value="Download Report" type="button" onclick="javascript:exportData()"/>
				</a>
			</td>
		</tr>
	</table>
</c:if>

<br />
<br />
<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	<tr height="20">
		<td scope="col" align="center" class="listViewThS1" style="text-align: center; font-size: 15;" nowrap="nowrap" width="100%" colspan=12>
				&nbsp;INPATIENT</td>
	</tr>
	<tr height="20">
		<td width="2%" nowrap="nowrap" class="listViewThS1" scope="col">No.</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Benefit Name</td>    	
    	<td scope="col" class="listViewThS1" width="10%">Procedure</td>
    	<td scope="col" class="listViewThS1" width="10%">Diagnosis</td>
    	<td scope="col" class="listViewThS1" width="10%">Diagnosis Set</td>    	
    	<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Benefit Limit</td>    	
    	<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Annual Limit</td>    	
    	<td scope="col" class="listViewThS1"  width="5%">Max Occur Per member</td>
    	<td scope="col" class="listViewThS1"  width="5%">Max Usage Per member</td>
    	<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%"></td>
	</tr>


	<c:forEach items="${inpatient}" var="benefit" varStatus="status" >
	
	 <tr  height="20">
		<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${status.index+1}" />.</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe"  valign="top">
				<c:out value="${benefit.itemCategoryId.itemCategoryName}" />			
			</td>
			
			<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top">
				<c:out value="${benefit.procedureId.procedureName}" /> - <c:out value="${benefit.procedureId.procedureCode}" />			
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top">
				<c:out value="${benefit.diagnosisId.description}" /> - <c:out value="${benefit.diagnosisId.diagnosisCode}" />
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top">
				<c:out value="${benefit.diagnosisSetId.diagnosisSetName}" /> - <c:out value="${benefit.diagnosisSetId.diagnosisSetCode}" />
			</td>
			
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
				<c:choose>
					<c:when test="${benefit.benefitLimit eq -1.0}">
					AS CHARGE
					</c:when>
					<c:otherwise >
						<fmt:formatNumber><c:out value="${benefit.benefitLimit}" /></fmt:formatNumber>
					</c:otherwise>
				</c:choose>
			</td>
			
			<td class="oddListRowS1" bgcolor="#e7f0fe"  valign="top" align="right">
				<c:choose>
					<c:when test="${benefit.annualBenefit eq -1.0}">
					AS CHARGE
					</c:when>
					<c:otherwise >
						<fmt:formatNumber><c:out value="${benefit.annualBenefit}" /></fmt:formatNumber>
					</c:otherwise>
				</c:choose>
			</td>		
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
				<fmt:formatNumber><c:out value="${benefit.maxOccurancePerMember}" /></fmt:formatNumber>
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
				<fmt:formatNumber><c:out value="${benefit.maxUsagePerMember}" /></fmt:formatNumber>
			</td>		   		   			   		   				   		   			   		   		
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top">
			
				<a href="javascript:ubah('<c:out value="${benefit.policyBenefitId}" />')">
					<img src="images/edit.gif" class="action_icon" alt="Edit" title="Edit"></a>
			<!-- ini default delete table for each data -->
				<a href="javascript:hapus('<c:out value="${benefit.policyBenefitId}" />')">
					<img src="images/delete.gif" class="action_icon" alt="Delete" title="Delete"></a>
	  		</td>   	
	  	</tr>
   
	
	</c:forEach>
	
	</tbody>
	</table>
	<br />
	<br />
	<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	<tr height="20">
		<td scope="col" align="center" class="listViewThS1" style="text-align: center; font-size: 15;" nowrap="nowrap" width="100%" colspan=12>
				&nbsp;OUTPATIENT</td>
	</tr>
	<tr height="20">
		<td width="2%" nowrap="nowrap" class="listViewThS1" scope="col">No.</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Benefit Name</td>    	
    	<td scope="col" class="listViewThS1" width="10%">Procedure</td>
    	<td scope="col" class="listViewThS1" width="10%">Diagnosis</td>
    	<td scope="col" class="listViewThS1" width="10%">Diagnosis Set</td>    	
    	<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Benefit Limit</td>    	
    	<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Annual Limit</td>    	
    	<td scope="col" class="listViewThS1"  width="5%">Max Occur Per member</td>
    	<td scope="col" class="listViewThS1"  width="5%">Max Usage Per member</td>
    	<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%"></td>
	</tr>


	<c:forEach items="${outpatient}" var="benefit" varStatus="status" >
	
	 <tr  height="20">
		<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${status.index+1}" />.</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe"  valign="top">
				<c:out value="${benefit.itemCategoryId.itemCategoryName}" />			
			</td>
			
			<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top">
				<c:out value="${benefit.procedureId.procedureName}" /> - <c:out value="${benefit.procedureId.procedureCode}" />			
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top">
				<c:out value="${benefit.diagnosisId.description}" /> - <c:out value="${benefit.diagnosisId.diagnosisCode}" />
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top">
				<c:out value="${benefit.diagnosisSetId.diagnosisSetName}" /> - <c:out value="${benefit.diagnosisSetId.diagnosisSetCode}" />
			</td>
			
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
				<fmt:formatNumber><c:out value="${benefit.benefitLimit}" /></fmt:formatNumber>					
			</td>
			
			<td class="oddListRowS1" bgcolor="#e7f0fe"  valign="top" align="right">
				<fmt:formatNumber><c:out value="${benefit.annualBenefit}" /></fmt:formatNumber>					
			</td>		
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
				<fmt:formatNumber><c:out value="${benefit.maxOccurancePerMember}" /></fmt:formatNumber>
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
				<fmt:formatNumber><c:out value="${benefit.maxUsagePerMember}" /></fmt:formatNumber>
			</td>		   		   			   		   				   		   			   		   		
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top">
			
				<a href="javascript:ubah('<c:out value="${benefit.policyBenefitId}" />')">
					<img src="images/edit.gif" class="action_icon" alt="Edit" title="Edit"></a>
			<!-- ini default delete table for each data -->
				<a href="javascript:hapus('<c:out value="${benefit.policyBenefitId}" />')">
					<img src="images/delete.gif" class="action_icon" alt="Delete" title="Delete"></a>
	  		</td>   	
	  	</tr>
   
	
	</c:forEach>
	
	</tbody>
	</table>	
	<br />
	<br />
	<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	<tr height="20">
		<td scope="col" align="center" class="listViewThS1" style="text-align: center; font-size: 15;" nowrap="nowrap" width="100%" colspan=12>
				&nbsp;MATERNITY</td>
	</tr>
	<tr height="20">
		<td width="2%" nowrap="nowrap" class="listViewThS1" scope="col">No.</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Benefit Name</td>    	
    	<td scope="col" class="listViewThS1" width="10%">Procedure</td>
    	<td scope="col" class="listViewThS1" width="10%">Diagnosis</td>
    	<td scope="col" class="listViewThS1" width="10%">Diagnosis Set</td>    	
    	<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Benefit Limit</td>    	
    	<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Annual Limit</td>    	
    	<td scope="col" class="listViewThS1"  width="5%">Max Occur Per member</td>
    	<td scope="col" class="listViewThS1"  width="5%">Max Usage Per member</td>
    	<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%"></td>
	</tr>


	<c:forEach items="${maternity}" var="benefit" varStatus="status" >
	
	 <tr  height="20">
		<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${status.index+1}" />.</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe"  valign="top">
				<c:out value="${benefit.itemCategoryId.itemCategoryName}" />			
			</td>
			
			<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top">
				<c:out value="${benefit.procedureId.procedureName}" /> - <c:out value="${benefit.procedureId.procedureCode}" />			
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top">
				<c:out value="${benefit.diagnosisId.description}" /> - <c:out value="${benefit.diagnosisId.diagnosisCode}" />
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top">
				<c:out value="${benefit.diagnosisSetId.diagnosisSetName}" /> - <c:out value="${benefit.diagnosisSetId.diagnosisSetCode}" />
			</td>
			
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
				<fmt:formatNumber><c:out value="${benefit.benefitLimit}" /></fmt:formatNumber>					
			</td>
			
			<td class="oddListRowS1" bgcolor="#e7f0fe"  valign="top" align="right">
				<fmt:formatNumber><c:out value="${benefit.annualBenefit}" /></fmt:formatNumber>					
			</td>		
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
				<fmt:formatNumber><c:out value="${benefit.maxOccurancePerMember}" /></fmt:formatNumber>
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
				<fmt:formatNumber><c:out value="${benefit.maxUsagePerMember}" /></fmt:formatNumber>
			</td>		   		   			   		   				   		   			   		   		
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top">
			
				
				<a href="javascript:ubah('<c:out value="${benefit.policyBenefitId}" />')">
					<img src="images/edit.gif" class="action_icon" alt="Edit" title="Edit"></a>
			<!-- ini default delete table for each data -->
				<a href="javascript:hapus('<c:out value="${benefit.policyBenefitId}" />')">
					<img src="images/delete.gif" class="action_icon" alt="Delete" title="Delete"></a>
	  		</td>   	
	  	</tr>
   
	
	</c:forEach>
	
	</tbody>
	</table>
	<br />
	<br />
	<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	<tr height="20">
		<td scope="col" align="center" class="listViewThS1" style="text-align: center; font-size: 15;" nowrap="nowrap" width="100%" colspan=12>
				&nbsp;DENTAL</td>
	</tr>
	<tr height="20">
		<td width="2%" nowrap="nowrap" class="listViewThS1" scope="col">No.</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Benefit Name</td>    	
    	<td scope="col" class="listViewThS1" width="10%">Procedure</td>
    	<td scope="col" class="listViewThS1" width="10%">Diagnosis</td>
    	<td scope="col" class="listViewThS1" width="10%">Diagnosis Set</td>    	
    	<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Benefit Limit</td>    	
    	<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Annual Limit</td>    	
    	<td scope="col" class="listViewThS1"  width="5%">Max Occur Per member</td>
    	<td scope="col" class="listViewThS1"  width="5%">Max Usage Per member</td>
    	<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%"></td>
	</tr>


	<c:forEach items="${dental}" var="benefit" varStatus="status" >
	
	 <tr  height="20">
		<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${status.index+1}" />.</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe"  valign="top">
				<c:out value="${benefit.itemCategoryId.itemCategoryName}" />			
			</td>
			
			<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top">
				<c:out value="${benefit.procedureId.procedureName}" /> - <c:out value="${benefit.procedureId.procedureCode}" />			
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top">
				<c:out value="${benefit.diagnosisId.description}" /> - <c:out value="${benefit.diagnosisId.diagnosisCode}" />
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top">
				<c:out value="${benefit.diagnosisSetId.diagnosisSetName}" /> - <c:out value="${benefit.diagnosisSetId.diagnosisSetCode}" />
			</td>
			
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
				<fmt:formatNumber><c:out value="${benefit.benefitLimit}" /></fmt:formatNumber>					
			</td>
			
			<td class="oddListRowS1" bgcolor="#e7f0fe"  valign="top" align="right">
				<fmt:formatNumber><c:out value="${benefit.annualBenefit}" /></fmt:formatNumber>					
			</td>		
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
				<fmt:formatNumber><c:out value="${benefit.maxOccurancePerMember}" /></fmt:formatNumber>
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
				<fmt:formatNumber><c:out value="${benefit.maxUsagePerMember}" /></fmt:formatNumber>
			</td>		   		   			   		   				   		   			   		   		
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top">
			
				
				<a href="javascript:ubah('<c:out value="${benefit.policyBenefitId}" />')">
					<img src="images/edit.gif" class="action_icon" alt="Edit" title="Edit"></a>
			<!-- ini default delete table for each data -->
				<a href="javascript:hapus('<c:out value="${benefit.policyBenefitId}" />')">
					<img src="images/delete.gif" class="action_icon" alt="Delete" title="Delete"></a>
	  		</td>   	
	  	</tr>
   
	
	</c:forEach>
	
	</tbody>
	</table>
	<br />
	<br />
	<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	<tr height="20">
		<td scope="col" align="center" class="listViewThS1" style="text-align: center; font-size: 15;" nowrap="nowrap" width="100%" colspan=12>
				&nbsp;OPTICAL</td>
	</tr>
	<tr height="20">
		<td width="2%" nowrap="nowrap" class="listViewThS1" scope="col">No.</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Benefit Name</td>    	
    	<td scope="col" class="listViewThS1" width="10%">Procedure</td>
    	<td scope="col" class="listViewThS1" width="10%">Diagnosis</td>
    	<td scope="col" class="listViewThS1" width="10%">Diagnosis Set</td>    	
    	<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Benefit Limit</td>    	
    	<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Annual Limit</td>    	
    	<td scope="col" class="listViewThS1"  width="5%">Max Occur Per member</td>
    	<td scope="col" class="listViewThS1"  width="5%">Max Usage Per member</td>
    	<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%"></td>
	</tr>


	<c:forEach items="${optical}" var="benefit" varStatus="status" >
	
	 <tr  height="20">
		<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${status.index+1}" />.</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe"  valign="top">
				<c:out value="${benefit.itemCategoryId.itemCategoryName}" />			
			</td>
			
			<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top">
				<c:out value="${benefit.procedureId.procedureName}" /> - <c:out value="${benefit.procedureId.procedureCode}" />			
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top">
				<c:out value="${benefit.diagnosisId.description}" /> - <c:out value="${benefit.diagnosisId.diagnosisCode}" />
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top">
				<c:out value="${benefit.diagnosisSetId.diagnosisSetName}" /> - <c:out value="${benefit.diagnosisSetId.diagnosisSetCode}" />
			</td>
			
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
				<fmt:formatNumber><c:out value="${benefit.benefitLimit}" /></fmt:formatNumber>					
			</td>
			
			<td class="oddListRowS1" bgcolor="#e7f0fe"  valign="top" align="right">
				<fmt:formatNumber><c:out value="${benefit.annualBenefit}" /></fmt:formatNumber>					
			</td>		
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
				<fmt:formatNumber><c:out value="${benefit.maxOccurancePerMember}" /></fmt:formatNumber>
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
				<fmt:formatNumber><c:out value="${benefit.maxUsagePerMember}" /></fmt:formatNumber>
			</td>		   		   			   		   				   		   			   		   		
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top">
			
				<a href="javascript:ubah('<c:out value="${benefit.policyBenefitId}" />')">
					<img src="images/edit.gif" class="action_icon" alt="Edit" title="Edit"></a>
			<!-- ini default delete table for each data -->
				<a href="javascript:hapus('<c:out value="${benefit.policyBenefitId}" />')">
					<img src="images/delete.gif" class="action_icon" alt="Delete" title="Delete"></a>
	  		</td>   	
	  	</tr>
   
	
	</c:forEach>
	
	</tbody>
	</table>
	<br />
	<br />
	<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	<tr height="20">
		<td scope="col" align="center" class="listViewThS1" style="text-align: center; font-size: 15;" nowrap="nowrap" width="100%" colspan=12>
				&nbsp;MEDICAL CHECK UP</td>
	</tr>
	<tr height="20">
		<td width="2%" nowrap="nowrap" class="listViewThS1" scope="col">No.</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Benefit Name</td>    	
    	<td scope="col" class="listViewThS1" width="10%">Procedure</td>
    	<td scope="col" class="listViewThS1" width="10%">Diagnosis</td>
    	<td scope="col" class="listViewThS1" width="10%">Diagnosis Set</td>    	
    	<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Benefit Limit</td>    	
    	<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Annual Limit</td>    	
    	<td scope="col" class="listViewThS1"  width="5%">Max Occur Per member</td>
    	<td scope="col" class="listViewThS1"  width="5%">Max Usage Per member</td>
    	<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%"></td>
	</tr>


	<c:forEach items="${mcu}" var="benefit" varStatus="status" >
	
	 <tr  height="20">
		<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${status.index+1}" />.</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe"  valign="top">
				<c:out value="${benefit.itemCategoryId.itemCategoryName}" />			
			</td>
			
			<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top">
				<c:out value="${benefit.procedureId.procedureName}" /> - <c:out value="${benefit.procedureId.procedureCode}" />			
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top">
				<c:out value="${benefit.diagnosisId.description}" /> - <c:out value="${benefit.diagnosisId.diagnosisCode}" />
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top">
				<c:out value="${benefit.diagnosisSetId.diagnosisSetName}" /> - <c:out value="${benefit.diagnosisSetId.diagnosisSetCode}" />
			</td>
			
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
				<fmt:formatNumber><c:out value="${benefit.benefitLimit}" /></fmt:formatNumber>					
			</td>
			
			<td class="oddListRowS1" bgcolor="#e7f0fe"  valign="top" align="right">
				<fmt:formatNumber><c:out value="${benefit.annualBenefit}" /></fmt:formatNumber>					
			</td>		
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
				<fmt:formatNumber><c:out value="${benefit.maxOccurancePerMember}" /></fmt:formatNumber>
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
				<fmt:formatNumber><c:out value="${benefit.maxUsagePerMember}" /></fmt:formatNumber>
			</td>		   		   			   		   				   		   			   		   		
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top">
			
				
				<a href="javascript:ubah('<c:out value="${benefit.policyBenefitId}" />')">
					<img src="images/edit.gif" class="action_icon" alt="Edit" title="Edit"></a>
			<!-- ini default delete table for each data -->
				<a href="javascript:hapus('<c:out value="${benefit.policyBenefitId}" />')">
					<img src="images/delete.gif" class="action_icon" alt="Delete" title="Delete"></a>
	  		</td>   	
	  	</tr>
   
	
	</c:forEach>
	
	</tbody>
	</table>
	

</form>
	
<!-- Table Content Stop -->
	<!-- Table Toolbar Start -->
	
		<!-- Table Toolbar Stop -->



<!-- Table Container Stop -->
<script language="Javascript">
<%
String nav="";
if(request.getAttribute("navigation").equals("gosearch")||request.getAttribute("navigation").equals("golookup")){
	nav = (String)request.getAttribute("navigation");
}
%>
function tambahi (){
	document.form1.navigation.value = "list";
	document.form1.action = "policybenefit-form";
	document.form1.method = "GET";
	document.form1.submit();
}
function hapus (idx){
	var delConfirm = window.confirm ("Are You Sure Want To Delete This Entry ?");

	if (delConfirm){
		document.form1.method = "POST";
		document.form1.policyBenefitId.value = idx;
		document.form1.action = "policybenefit";
		document.form1.navigation.value = "delete";
		document.form1.submit();
	}
}
function ubah (idx){
	document.form1.method = "GET";
	document.form1.policyBenefitId.value = idx;
	document.form1.action = "policybenefit-form";
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
	document.form1.action = "policybenefit";
	document.form1.method = "POST";
	document.form1.submit();
}
function detil (idx){
	document.form1.method = "POST";
	document.form1.policyBenefitId.value = idx;
	document.form1.action = "policybenefit";
	document.form1.navigation.value = "detail";
	document.form1.submit();

}
function exportData(){
	document.form1.action = "policybenefit";
	document.form1.navigation.value = "downloadPolicyBenefit";
	document.form1.submit();
}
</script>
