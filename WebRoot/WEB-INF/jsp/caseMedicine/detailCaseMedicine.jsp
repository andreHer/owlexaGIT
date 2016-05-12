<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>



<form action="casemedicine" method="GET" name="form1" id="form_layout">

	<input type="hidden" name="navigation" value="">
	<input type="hidden" name="caseMedicineId" value="<c:out value="${caseMedicine.caseMedicineId}" />">

<table class="tabDetailView" border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td colspan="20" class="listViewPaginationTdS1" style="padding: 0px;">
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tbody>
				<tr>
					<td style="text-align: left;" class="tabDetailViewDL">
					</td>
					<td class="tabDetailViewDL" align="right" nowrap="nowrap">
						<a href="casemedicine?navigation=search&searchtext=<c:out value="${searchtext}"/>&searchby=<c:out value="${searchby}" />&index=<c:out value="${index}"/>" class="tabDetailViewDFLink" style="font-weight: normal;">Return to List&nbsp;</a>					
					</td>
				</tr>
			</tbody>
		</table>
	  </td>
    </tr>
	
	
		
			<tr>
	      		<td class="tabDetailViewDL" valign="top" width="17%">Case :&nbsp;</td>		  
			  	<td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${caseMedicine.caseId.caseNumber}"/></td>
	      		<td class="tabDetailViewDL" valign="top" width="17%">Total Usage :&nbsp;</td>		  
			  	<td class="tabDetailViewDF" valign="top" width="33%"><fmt:formatNumber><c:out value="${caseMedicine.totalUsage}"/></fmt:formatNumber></td>
	    	</tr>
			
			<tr>
	      		<td class="tabDetailViewDL" valign="top" width="17%">Medicine :&nbsp;</td>		  
			  	<td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${caseMedicine.medicineId.medicineName}"/></td>
		      	<td class="tabDetailViewDL" valign="top" width="17%">Total Charge :&nbsp;</td>		  
			  	<td class="tabDetailViewDF" valign="top" width="33%"><fmt:formatNumber><c:out value="${caseMedicine.totalCharge}"/></fmt:formatNumber></td>
		    </tr>
			
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Reference Price :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><fmt:formatNumber><c:out value="${caseMedicine.referencePrice}"/></fmt:formatNumber></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
			
		
	
			
			<tr>
	      		<td class="tabDetailViewDL" valign="top" width="17%">Created Time :&nbsp;</td>		  
			  	<td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${caseMedicine.createdTime}"/> - <c:out value="${caseMedicine.createdBy}"/></td>
	      		<td class="tabDetailViewDL" valign="top" width="17%">Modified Time :&nbsp;</td>		  
			  	<td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${caseMedicine.modifiedTime}"/> - <c:out value="${caseMedicine.modifiedBy}"/></td>
	    	</tr>
	    	<tr>
	      		<td class="tabDetailViewDL" valign="top" width="17%">Diagnosis :&nbsp;</td>		  
			  	<td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${caseMedicine.caseId.diagnosis1Id.description}"/> </td>
	      		<td class="tabDetailViewDL" valign="top" width="17%"></td>		  
			  	<td class="tabDetailViewDF" valign="top" width="33%"></td>
	    	</tr>
	    	<tr>
	      		<td class="tabDetailViewDL" valign="top" width="17%">Anamnesis :&nbsp;</td>
	      		<td class="tabDetailViewDF" valign="top" width="33%" colspan=3><c:out value="${caseMedicine.caseId.anamnesa}"/></td>
	    	</tr>
			<tr>
	      		<td class="tabDetailViewDL" valign="top" width="17%">Description :&nbsp;</td>
	      		<td class="tabDetailViewDF" valign="top" width="33%" colspan=3><c:out value="${caseMedicine.description}"/></td>
	    	</tr>
		
	
	</tbody>
</table>
<br />

<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	
	<tr height="20">
		<td width="2%" nowrap="nowrap" class="listViewThS1" scope="col">No.</td>		
	   	<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Monitoring Time &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Diagnostic Test&nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Therapy &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Anamnesa &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Procedure&nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Medication&nbsp;</td>
	</tr>


	<c:forEach items="${CaseEvents}" var="caseEvent" varStatus="status" >
	 <tr height="20" style="border-top: 1px solid #000;">
      <td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${status.index+1}" /></td>

      	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
				<a href="caseevent?navigation=detail&index=<c:out value="${index}" />&rowset=<c:out value="${rowset}" />&caseId=<c:out value="${caseEvent.caseId.caseId}" />&caseEventId=<c:out value="${caseEvent.caseEventId}" />" class="linkDetail"><c:out value="${caseEvent.monitoringTime}" /></a>			
		</td>				
		<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top">
				<c:out value="${caseEvent.diagnosticTesting}" />
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top">
				<c:out value="${caseEvent.therapy}" />
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe"  valign="top">
				<c:out value="${caseEvent.vitalSign}" />
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top">
				<c:out value="${caseEvent.procedurePlan}" />
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top">
				<c:out value="${caseEvent.currentMedication}" />
		</td>
		
		
    </tr>
   
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	
	</c:forEach>
	
	
	</tbody>
</table>
<br />
<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>

		<tr>
			<td class="dataLabel">&nbsp;</td>
			<td class="dataField"></td>
    		<td class="dataLabel"></td>
    		<td class="dataField"></td>
		</tr>
		<tr>
			<td class="dataLabel"> Approval/Rejection Reason : </td>
		    <td class="dataField">
					<textarea rows="5" cols="60" name="reason"></textarea>					
			</td>
		    <td class="dataLabel"></td>
		    <td class="dataField"></td>
		</tr>
		<tr>
			<td class="dataLabel">&nbsp;</td>
			<td class="dataField"></td>
    		<td class="dataLabel"></td>
    		<td class="dataField"></td>
		</tr>
	</tbody>
</table>
<br />
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr> 
      <td style="padding-bottom: 2px;">        
        
		<input title="Edit [Alt+E]" accesskey="E" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:approve()" name="Edit" value=" Approve " type="button">
		
        <input title="Hapus [Alt+H]" accesskey="H" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:reject()" name="Delete" value=" Reject " type="button">        		
		
		</td>
      <td align="right"></td>
    </tr>
  </tbody>
</table>
</form>

<script language="javascript">
	function approve (){
		var delConfirm = window.confirm ("Are You Sure Want To Approve This Medicine ?");
		if (delConfirm){
			document.form1.navigation.value = "approve";
			document.form1.submit();
		}
	}
	
	function reject (){
		var delConfirm = window.confirm ("Are You Sure Want To Reject This Medicine ?");
		if (delConfirm){
			document.form1.navigation.value = "reject";
			document.form1.action = "casemedicine";
			document.form1.method = "GET";
			document.form1.submit();
		}
	}
</script>
