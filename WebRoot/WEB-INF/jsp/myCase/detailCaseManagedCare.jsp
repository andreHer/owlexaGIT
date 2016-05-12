<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<table class="moduleTitle" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>
		<tr>
			<td valign="top"></td>
			<td width="100%">
				<h2 style="font-size: large; color: #000;">
					Patient Case Handling :
				</h2>					
			</td>
			<td style="padding-top: 3px; padding-left: 5px;" align="right" nowrap="nowrap" valign="top">
			</td>
	</tbody>
</table>

<br />
<%@ include file="../mainContentCase.jsp" %>




<br /> 	
<form action="case" method="GET" name="form1" id="form_layout">

<input type="hidden" name="navigation" value="">
<input type="hidden" name="caseId" value="<c:out value="${myCase.caseId}" />">	
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr> 
    
      <td style="padding-bottom: 2px; padding-top: 2px;">        
       
       	<c:if test="${myCase.caseStatusId.caseStatusId eq 5 or myCase.caseStatusId.caseStatusId eq 15}" >
	       	
	       	<input title="Refer Case [Alt+Shift+R]" accesskey="R" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:referCase()" name="referCaseBtn" value=" Refer Case " type="button">
	       	
			<c:if test="${myCase.claimId ne null}">
			<input title="Cost Report" accesskey="R" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:printCostReport()" name="calcBtn" value=" Print Cost Report " type="button">
			</c:if>
       	</c:if>
       	
       	<c:if test="${theUser.userType eq 4}">
       		
	       	<c:if test="${myCase.caseStatusId.caseStatusId eq 9}">
	       	<input title="Close Case " accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:referCaseManaged()" name="closeManagedCaseBtn" value=" Close Case " type="button">
	       	
	       	<input title="Add Medicine " accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:addMedicine()" name="addMedBtn" value=" Medicine " type="button">
	       	
	       	<input title="Add Procedure" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:addProcedure()" name="addProcedureBtn" value=" Procedure " type="button">
	       	
	       	<input title="Add Diagnostic Test" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:addDiagnostic()" name="addDiagnosticBtn" value=" Diagnostic Test " type="button">
	       	
	       	<input title="Add Diagnostic Test" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:addCost()" name="addCaseItemBtn" value=" Cost Factor " type="button">

	       	</c:if>
       	</c:if>
		</td>
	  	<td align="right"></td>		
      	<td align="right"></td>
    </tr>
  </tbody>
</table>

<br />
<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>

        
	<tr height="20">
		<td width="2%" nowrap="nowrap" class="listViewThS1" scope="col">No.</td>		   		   		   		   		   		   			
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">Medicine</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Total Item</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Reference Price</td>		
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Ref Charge</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="25%">Description</td>
   	   	
	</tr>


	<c:forEach items="${caseMedicineList}" var="caseMedicine" varStatus="status" >
	
	 <tr  height="20">
      <td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${status.index+1}" />.</td>
      <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${caseMedicine.medicineId.medicineName}" /></td>
      <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right"><c:out value="${caseMedicine.totalUsage}" /></td>
      <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right"><fmt:formatNumber><c:out value="${caseMedicine.referencePrice}" /></fmt:formatNumber></td>
      
      <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right"><fmt:formatNumber><c:out value="${caseMedicine.referencePrice * caseMedicine.totalUsage}" /></fmt:formatNumber></td>
      <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${caseMedicine.description}" /></td>
        
    </tr>
   
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	
	</c:forEach>
	
	</tbody>
	</table>
	
<br />

<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>

	<tr height="20">
		<td width="2%" nowrap="nowrap" class="listViewThS1" scope="col">No.</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="20%" align="center">Procedure/Tindakan &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%" align="center">Biaya &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="20%" align="center">Description &nbsp;</td>				   	   	
	</tr>


	<c:forEach items="${caseProcedureList}" var="caseProcedure" varStatus="status" >
	 <tr  height="20">
      	<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${status.index+1}" />.</td>
      	<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top"><c:out value="${caseProcedure.procedureId.procedureName}" /></td>
      	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="right" valign="top"><fmt:formatNumber><c:out value="${caseProcedure.totalCharge}" /></fmt:formatNumber></td>

	    <td class="oddListRowS1" bgcolor="#e7f0fe" valign="top"><c:out value="${caseProcedure.description}" /></td>

    </tr>   
	<tr>
      <td colspan="20" class="listViewHRS1" style="border-bottom: 1px #000 solid;"></td>
    </tr>	
	</c:forEach>
	

	</tbody>
	</table>		
		
		<br />

<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>

	<tr height="20">
		<td width="2%" nowrap="nowrap" class="listViewThS1" scope="col">No.</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="20%" align="center">Cost Factor &nbsp;</td>		
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%" align="center">Total  &nbsp;</td>		
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="20%" align="center">Description &nbsp;</td>				   	   	
	</tr>
	<c:forEach items="${caseItemList}" var="caseItem" varStatus="status" >
	 <tr  height="20">
      	<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${status.index+1}" />.</td>
      	<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top"><c:out value="${caseItem.itemId.itemName}" /></td>
      	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="right" valign="top"><fmt:formatNumber><c:out value="${caseItem.usageValue}" /></fmt:formatNumber></td>
      	
	    <td class="oddListRowS1" bgcolor="#e7f0fe" valign="top"><c:out value="${caseItem.description}" /></td>

    </tr>   
	<tr>
      <td colspan="20" class="listViewHRS1" style="border-bottom: 1px #000 solid;"></td>
    </tr>	
	</c:forEach>
	

	</tbody>
	</table>
</form>
<script language="javascript">
	function addMedicine(){		
		document.form1.navigation.value = "addbulk";
		document.form1.action = "casemedicine";
		document.form1.submit();		
	}
	function addProcedure(){		
		document.form1.navigation.value = "addprocedure";
		document.form1.action = "caseprocedure-form";
		document.form1.submit();		
	}
	function addDiagnostic(){		
		document.form1.navigation.value = "adddiagnostic";
		document.form1.action = "caseitem-form";
		document.form1.submit();		
	}
	function addCost(){		
		document.form1.navigation.value = "addadmin";
		document.form1.action = "caseitem-form";
		document.form1.submit();		
	}
	
	function kembali (){
		document.form1.navigation.value = "back";
		document.form1.submit();
	}
	function popupMember(memberId){
		window.open ("member?navigation=detail&memberId="+memberId);
	}
	function ubah (){
		document.form1.navigation.value = "update";
		document.form1.action = "case-form";
		document.form1.method = "GET";
		document.form1.submit();
	}
	function terminate (){
		var delConfirm = window.confirm ("Are You Sure Want To Terminate This Case ?");
		
		if (delConfirm){
			document.form1.navigation.value = "terminate";
			document.form1.action = "case";
			document.form1.method = "GET";
			document.form1.submit();
		}
	}
	
	
	function printCostReport(){
		window.open ("case?navigation=printcost&url=detailExcessCharge&caseId=<c:out value="${myCase.caseId}" />","Search","width=1024, height=768, menubar=yes, status=no, toolbar=no, scrollbars=yes, resizable=yes");	
	}
	

	function referCaseManaged(){	
		var delConfirm = window.confirm ("Are You Sure Want To Close The Case ?");
		
		if (delConfirm){
			document.form1.navigation.value = "refercase";
			document.form1.action = "refer-form";
			document.form1.method = "GET";
			document.form1.submit();
		}
	
	}
</script>
