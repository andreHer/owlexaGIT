<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>



<form action="caseevent" method="GET" name="form1" id="form_layout">
<input type="hidden" name="navigation" value="">
	<input type="hidden" name="caseEventId" value="<c:out value="${caseEvent.caseEventId}" />">
	
	
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr> 
      <td style="padding-bottom: 2px;">       
      	<c:if test="${theUser.roleId.roleId eq 0}"> 
      
        <input title="Edit [Alt+Shift+E]" accesskey="E" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:ubah()" name="Edit" value=" Update " type="button">
        <input title="Hapus [Alt+Shift+H]" accesskey="H" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:hapus()" name="Delete" value=" Delete " type="button">        		
		</c:if>
		</td>
      <td align="right"></td>
    </tr>
  </tbody>
</table>
<table class="tabDetailView" border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td colspan="20"  style="padding: 0px;">
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tbody>
				<tr>
					<td style="text-align: left;" class="tabDetailViewDL">&nbsp;
		
					</td>
					<td class="tabDetailViewDL" align="right" nowrap="nowrap">
						<a href="caseevent?navigation=list&searchtext=<c:out value="${searchtext}"/>&searchby=<c:out value="${searchby}" />&rowset=<c:out value="${rowset}" />&caseId=<c:out value="${caseEvent.caseId.caseId}" />&index=<c:out value="${index}"/>" class="tabDetailViewDFLink" style="font-weight: normal;">Return to List&nbsp;</a>					
					</td>
				</tr>
			</tbody>
		</table>
	  </td>
    </tr>
	
		
	
		
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Description :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${caseEvent.description}" escapeXml="false"/></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">Event Category :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${caseEvent.eventCategoryId.eventCategoryName}"/></td>
	    </tr>
	
			
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Therapy :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${caseEvent.therapy}" escapeXml="false"/></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">Doctor Consult :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${caseEvent.doctorConsult}"/></td>
	    </tr>
	
			
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Vital Sign :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${caseEvent.vitalSign}" escapeXml="false"/></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">Nurse :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${caseEvent.nurse}"/></td>
	    </tr>
	
			
	
			
	
			
	
	
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Procedure Plan :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${caseEvent.procedurePlan}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
	
			
	
		
	
		
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Ekg :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${caseEvent.ekg}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">Impression :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${caseEvent.impression}"/></td>
	    </tr>
	
			
	
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	      
	      <td class="tabDetailViewDL" valign="top" width="15%">&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%">&nbsp;</td>
	    </tr>
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Subjective :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${caseEvent.subjective}"/></td>
	       <td class="tabDetailViewDL" valign="top" width="17%">Assesment :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${caseEvent.assesment}"/></td>
	
	    </tr>
	
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Objective :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${caseEvent.objective}"/></td>
	      
	      <td class="tabDetailViewDL" valign="top" width="17%">Plan :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${caseEvent.plan}"/></td>
	    </tr>
	
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	      
	      <td class="tabDetailViewDL" valign="top" width="15%">&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%">&nbsp;</td>
	    </tr>
	
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Chief Complaint :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${caseEvent.chiefComplaint}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
	
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Current Medication :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${caseEvent.currentMedication}"/></td>
	     <td class="tabDetailViewDL" valign="top" width="17%">Allergies :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${caseEvent.allergies}"/></td>
	    </tr>
	
			
	
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	      
	      <td class="tabDetailViewDL" valign="top" width="15%">&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%">&nbsp;</td>
	    </tr>
	
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Physical Examination :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${caseEvent.physicalExamination}"  escapeXml="false"/></td>
	      
	      <td class="tabDetailViewDL" valign="top" width="17%">Diagnostic Testing :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${caseEvent.diagnosticTesting}" escapeXml="false"/></td>
	    </tr>
	
	
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Family History :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${caseEvent.familyHistory}" escapeXml="false"/></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">Social History :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${caseEvent.socialHistory}" escapeXml="false"/></td>
	    </tr>
	
			
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Past Medical History :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${caseEvent.pastMedicalHistory}" escapeXml="false"/></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">Past Surgical History :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${caseEvent.pastSurgicalHistory}" escapeXml="false"/></td>
	    </tr>
	
			
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	      
	      <td class="tabDetailViewDL" valign="top" width="15%">&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%">&nbsp;</td>
	    </tr>
	
			
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Created Time :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${caseEvent.createdTime}"/> - <c:out value="${caseEvent.createdBy}"/></td>
<td class="tabDetailViewDL" valign="top" width="17%">Modified Time :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${caseEvent.modifiedTime}"/> - <c:out value="${caseEvent.modifiedBy}"/></td>	    
			  </tr>
			
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Monitored By :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${caseEvent.monitoredBy}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
	    	<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Remarks :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%" colspan=3><c:out value="${caseEvent.remarks}" escapeXml="false"/></td>
	    </tr>
	
					
	</tbody>
</table>

</form>
<script language="javascript">
	function hapus (){
	var delConfirm = window.confirm ("Are You Sure Want To Delete This Entry ?");
		if (delConfirm){
			document.form1.navigation.value = "delete";
			document.form1.submit();
		}
	}
	function kembali (){
		document.form1.navigation.value = "back";
		document.form1.submit();
	}
	function ubah (){
		document.form1.navigation.value = "update";
		document.form1.action = "caseevent-form";
		document.form1.method = "GET";
		document.form1.submit();
	}
</script>
