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

 <!-- Page Title Start // Should be put on <Title> tag-->

<!-- Page Title Stop-->
<%
String alert = (String) request.getAttribute("alert");


if (alert != null && !alert.trim().equals("")) {%>
	<div id="warning" align="center">
		<c:out value="${alert}"></c:out>
	</div>
	<%}%>
	
	
	
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;List Policy Clausul</h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>

<%@ include file="../mainContentPolicy.jsp" %>

<br />

<!-- Search Container Start -->

<form name="form1" action="policyclausul" method="POST">
<input type="hidden" name="navigation" value="list">
<input type="hidden" name="policyId" value="<c:out value="${policyId}" />">

				<input type="hidden" name="id" value="<c:out value="${policyClausul.id}" />">
		<!-- Table Content Start -->

<c:if test="${theUser.roleId.roleId ne 6}">
	<input type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:tambahi()" value=" Add Clausul ">
</c:if>
<br />
<br />
 	
<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	<tr height="20">
		<td scope="col" align="center" class="listViewThS1" style="text-align: center; font-size: 15;" nowrap="nowrap" width="100%" colspan=10>
				&nbsp;INPATIENT</td>
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
    	<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%"></td>
	</tr>


	<c:forEach items="${inpatientClausuls}" var="policyClausul" varStatus="status" >
	
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
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top">
			
				<a href="javascript:ubah('<c:out value="${policyClausul.id}" />')">
					<img src="images/edit.gif" class="action_icon" alt="Edit" title="Edit"></a>
			<!-- ini default delete table for each data -->
				<a href="javascript:hapus('<c:out value="${policyClausul.id}" />')">
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
		<td scope="col" align="center" class="listViewThS1" style="text-align: center; font-size: 15;" nowrap="nowrap" width="100%" colspan=10>
				&nbsp;OUTPATIENT</td>
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
    	<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%"></td>
	</tr>

	<c:forEach items="${outpatientClausuls}" var="policyClausul" varStatus="status" >
	
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
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top">
			
				<a href="javascript:ubah('<c:out value="${policyClausul.id}" />')">
					<img src="images/edit.gif" class="action_icon" alt="Edit" title="Edit"></a>
			<!-- ini default delete table for each data -->
				<a href="javascript:hapus('<c:out value="${policyClausul.id}" />')">
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
		<td scope="col" align="center" class="listViewThS1" style="text-align: center; font-size: 15;" nowrap="nowrap" width="100%" colspan=10>
				&nbsp;MATERNITY</td>
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
    	<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%"></td>
	</tr>


	<c:forEach items="${maternityClausuls}" var="policyClausul" varStatus="status" >
	
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
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top">
			
				<a href="javascript:ubah('<c:out value="${policyClausul.id}" />')">
					<img src="images/edit.gif" class="action_icon" alt="Edit" title="Edit"></a>
			<!-- ini default delete table for each data -->
				<a href="javascript:hapus('<c:out value="${policyClausul.id}" />')">
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
		<td scope="col" align="center" class="listViewThS1" style="text-align: center; font-size: 15;" nowrap="nowrap" width="100%" colspan=10>
				&nbsp;DENTAL</td>
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
    	<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%"></td>
	</tr>

	<c:forEach items="${dentalClausuls}" var="policyClausul" varStatus="status" >
	
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
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top">
			
				<a href="javascript:ubah('<c:out value="${policyClausul.id}" />')">
					<img src="images/edit.gif" class="action_icon" alt="Edit" title="Edit"></a>
			<!-- ini default delete table for each data -->
				<a href="javascript:hapus('<c:out value="${policyClausul.id}" />')">
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
		<td scope="col" align="center" class="listViewThS1" style="text-align: center; font-size: 15;" nowrap="nowrap" width="100%" colspan=10>
				&nbsp;OPTICAL</td>
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
    	<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%"></td>
	</tr>


	<c:forEach items="${opticalClausuls}" var="policyClausul" varStatus="status" >
	
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
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top">
			
				<a href="javascript:ubah('<c:out value="${policyClausul.id}" />')">
					<img src="images/edit.gif" class="action_icon" alt="Edit" title="Edit"></a>
			<!-- ini default delete table for each data -->
				<a href="javascript:hapus('<c:out value="${policyClausul.id}" />')">
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
		<td scope="col" align="center" class="listViewThS1" style="text-align: center; font-size: 15;" nowrap="nowrap" width="100%" colspan=10>
				&nbsp;MEDICAL CHECK UP</td>
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
    	<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%"></td>
	</tr>

	<c:forEach items="${mcuClausuls}" var="policyClausul" varStatus="status" >
	
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
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top">
			
				<a href="javascript:ubah('<c:out value="${policyClausul.id}" />')">
					<img src="images/edit.gif" class="action_icon" alt="Edit" title="Edit"></a>
			<!-- ini default delete table for each data -->
				<a href="javascript:hapus('<c:out value="${policyClausul.id}" />')">
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
	document.form1.action = "policyclausul-form";
	document.form1.method = "GET";
	document.form1.submit();
}
function hapus (idx){
	var delConfirm = window.confirm ("Are You Sure Want To Delete This Entry ?");

	if (delConfirm){
		document.form1.method = "POST";
		document.form1.id.value = idx;
		document.form1.action = "policyclausul";
		document.form1.navigation.value = "delete";
		document.form1.submit();
	}
}
function ubah (idx){
	document.form1.method = "GET";
	document.form1.id.value = idx;
	document.form1.action = "policyclausul-form";
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
	document.form1.action = "policyclausul";
	document.form1.method = "POST";
	document.form1.submit();
}
function detil (idx){
	document.form1.method = "POST";
	document.form1.id.value = idx;
	document.form1.action = "policyclausul";
	document.form1.navigation.value = "detail";
	document.form1.submit();

}
</script>
