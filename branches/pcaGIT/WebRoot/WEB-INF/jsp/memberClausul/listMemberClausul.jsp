<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<script type="text/javascript" src="scripts/tinymce/tinymce.min.js"></script>
<script type="text/javascript">
tinymce.init({
    selector: "textarea",
    readonly : true,
	toolbar: "false",
	menubar: "false",
	plugins : 'autoresize',
	height: '100%'
 });
</script>


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
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;List Member Clausul</h3></td>
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
<input type="hidden" name="policyId" value="">
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

<table cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	<tr height="20">
		<td scope="col" align="center" style="text-align: center; font-size: 15; " nowrap="nowrap" width="100%" colspan=11>
				&nbsp;SUMMARY POLICY </td>
	</tr>

	<tr height="20">
		<td width="100%" colspan=11 style="color: #000;">
			<textarea cols="50" rows="8" ><c:out value="${member.currentPolicyId.policyTcNotes}" escapeXml="false" /></textarea>			
		</td>
	</tr>
	
	</tbody>
</table>
<br />
<br />
<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	<tr height="20">
		<td scope="col" align="center" class="listViewThS1" style="text-align: center; font-size: 15;" nowrap="nowrap" width="100%" colspan=11>
				&nbsp;POLICY T&C DOCUMENT </td>
	</tr>

	<tr height="20">
		<td width="30%" colspan=1 class="oddListRowS1" style="border-right: 1px solid #000;"  scope="col">
			<c:out value="${member.currentPolicyId.policyTcFile1}" /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   
			<c:if test="${member.currentPolicyId.policyTcFile1 ne null}">
				<input type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"  name="downloadTC1" value="Download" onclick="javascript:viewTC1('<c:out value="${member.currentPolicyId.policyId}" />');"/>
			</c:if>
		</td>
		<td width="30%" colspan=1 class="oddListRowS1" style="border-right: 1px solid #000;" scope="col">
			<c:out value="${member.currentPolicyId.policyTcFile2}" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
			<c:if test="${member.currentPolicyId.policyTcFile2 ne null}">
				<input type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"  name="downloadTC2" value="Download" onclick="javascript:viewTC2('<c:out value="${member.currentPolicyId.policyId}" />');"/>
			</c:if>
		</td>
		<td width="30%" colspan=1 class="oddListRowS1"  scope="col">
			<c:out value="${member.currentPolicyId.policyTcFile3}" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
			<c:if test="${member.currentPolicyId.policyTcFile1 ne null}">
				<input type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"  name="downloadTC3" value="Download" onclick="javascript:viewTC3('<c:out value="${member.currentPolicyId.policyId}" />');"/>
			</c:if>
		</td>
	</tr>
	
	</tbody>
</table>
<br />
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

<br />
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

<br />
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

<br />

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
		<td width="2%" nowrap="nowrap" class="listViewThS1" scope="col">No.</td>		
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Clausul Name</td>
		<td scope="col" class="listViewThS1" width="10%">Diagnosis</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Benefit Upgrade</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Benefit Calculation</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Decision</td>
		
	</tr>


	<c:forEach items="${MemberClausuls}" var="clausul" varStatus="status" >
	<%	if (i % 2 == 0) {
			rowclass = "col1";
		} else if (i % 2 != 0) {
			rowclass= "col2";
		}
		i++;
	%>
	 <tr height="20">
      	<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><%=(i+((indexint-1)*countSet))%></td>
      	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${clausul.clausulId.clausulName}" /></td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${clausul.diagnosisId.diagnosisName}" />
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<c:out value="${clausul.treatmentUpgradeType.treatmentUpgradeTypeName}" />			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<c:if test="${clausul.reductionType eq 1}">
				PRO-RATE
			</c:if>
			<c:if test="${clausul.reductionType eq 2}">
				PERCENTAGE
			</c:if>			
			<c:if test="${clausul.reductionType eq 3}">
				FIXED POINT
			</c:if>						
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<c:if test="${clausul.decision eq 1}">
				Approved
			</c:if>
			<c:if test="${clausul.decision eq 0}">
				Rejected
			</c:if>			
			
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
</form>
	

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
function viewTC1(id){
	document.form1.policyId.value = id;
	document.form1.navigation.value = "downloadtc1";
	document.form1.action = "policy";
	document.form1.submit();
}
function viewTC2(idx){
	document.form1.policyId.value = idx;
	document.form1.navigation.value = "downloadtc2";
	document.form1.action = "policy";
	document.form1.submit();
}
function viewTC3(idx){
	document.form1.policyId.value = idx;
	document.form1.navigation.value = "downloadtc3";
	document.form1.action = "policy";
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
	window.open ("firstcall?navigation=searchmembererrorlog&memberId=<c:out value="${member.memberId}" />","Search","width=1024, height=768, menubar=yes, status=no, toolbar=no, scrollbars=yes, resizable=yes");		
}

function adderrorlog (){ 
	window.location.href = "firstcall-form?navigation=memberelog&memberId=<c:out value="${member.memberId}" />";
}
</script>
