<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>



<form action="caseinvestigation" method="GET" name="form1" id="form_layout">

	<input type="hidden" name="navigation" value="">
	<input type="hidden" name="caseInvestigationId" value="<c:out value="${caseInvestigation.caseInvestigationId}" />">
	
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr> 
      <td style="padding-bottom: 2px;">        
        <c:if test="${theUser.roleId.roleId eq 0}">
			<input title="Edit [Alt+Shift+E]" accesskey="E" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:ubah()" name="Edit" value=" Update " type="button">
		
        	<input title="Hapus [Alt+Shift+H]" accesskey="H" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:hapus()" name="Delete" value=" Delete " type="button">        		
        	<c:if test="${caseInvestigation.decision eq null}">
        	<input title="Approve [Alt+Shift+A]" accesskey="H" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:approve()" name="appr" value=" Approve " type="button">
        	
        	 <input title="Reject [Alt+Shift+R]" accesskey="H" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:reject()" name="rec" value=" Reject " type="button">
        	 </c:if>
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
						<a href="caseinvestigation?navigation=list&searchtext=<c:out value="${searchtext}"/>&searchby=<c:out value="${searchby}" />&rowset=<c:out value="${rowset}" />&caseId=<c:out value="${caseInvestigation.caseId.caseId}" />&index=<c:out value="${index}"/>" class="tabDetailViewDFLink" style="font-weight: normal;">
							<img src="images/back-arrow.png" title="Return to List"/>
						</a>					
					</td>
				</tr>
			</tbody>
		</table>
	  </td>
    </tr>	
	
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Case :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${caseInvestigation.caseId.caseNumber}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">Investigation Category :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${caseInvestigation.investigationCategoryId.investigationCategoryName}"/></td>
	    </tr>
			
		
		
			
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Investigation Subject :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${caseInvestigation.investigationSubject}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">Investigation Date :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${caseInvestigation.investigationDate}"/></td>
	    </tr>
	
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Head Doctor :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${caseInvestigation.headDoctor}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">Consult Doctor :&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${caseInvestigation.consultDoctor}"/></td>
	    </tr>
	
			
			
	
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Nurse :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${caseInvestigation.nurse}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">Decision :&nbsp;</td>		  
			  <td class="tabDetailViewDF" valign="top" width="33%">
			  <c:if test="${caseInvestigation.decision eq 1}"><b>Approved</b></c:if>
			  <c:if test="${caseInvestigation.decision eq 0}"><b>Rejected</b></c:if>
			  </td>
	    </tr>
	    
	<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Total Days :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${caseInvestigation.totalDays}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
	

		
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%">&nbsp;</td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>


		<tr>
	      	<td class="tabDetailViewDL" valign="top" width="17%">Procedure :&nbsp;</td>		  
			 
	      	<td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${caseInvestigation.procedureId.procedureName}"/></td>
	      	<td class="tabDetailViewDL" valign="top" width="17%">Cost :&nbsp;</td>		  
			<td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${caseInvestigation.procedureCostEstimation}"/> <c:if test="${caseInvestigation.procedureCostReference ne null}">(ref: <c:out value="${caseInvestigation.procedureCostReference}"/>)</c:if> </td>
	    </tr>
	    <tr>
	      	<td class="tabDetailViewDL" valign="top" width="17%">Medicine :&nbsp;</td>		  
			 
	      	<td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${caseInvestigation.medicineId.medicineName}"/></td>
	      	<td class="tabDetailViewDL" valign="top" width="17%">Cost :&nbsp;</td>		  
			<td class="tabDetailViewDF" valign="top" width="33%"><fmt:formatNumber><c:out value="${caseInvestigation.medicineCostEstimation}"/></fmt:formatNumber> <c:if test="${caseInvestigation.medicineCostReference ne null}">(ref: <fmt:formatNumber><c:out value="${caseInvestigation.medicineCostReference}"/></fmt:formatNumber>)</c:if> </td>
	    </tr>
		
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%">&nbsp;</td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
		
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Conciousness :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${caseInvestigation.conciousness}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">GCS Eye :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${caseInvestigation.gcsE}"/></td>
	    </tr>
	
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Blood Pressure :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${caseInvestigation.bloodPressure}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">GCS Motor :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${caseInvestigation.gcsM}"/></td>
	    </tr>
	
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Artery :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${caseInvestigation.artery}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">GCS Verbal :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${caseInvestigation.gcvV}"/></td>
	    </tr>
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%">&nbsp;</td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Temperature :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${caseInvestigation.temperature}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">Pulse :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${caseInvestigation.pulse}"/></td>
	
	    </tr>
	
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Respiratory :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${caseInvestigation.respiratory}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">IPPV Status :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${caseInvestigation.ippvStatus}"/></td>
	
	    </tr>
	
			
			
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Oxygen Saturation :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${caseInvestigation.satOxygen}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">SIPPV Status :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${caseInvestigation.sippvStatus}"/></td>
	
	    </tr>
	
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Ventilator Status :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${caseInvestigation.ventilatorStatus}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
	
	
	
			
	
	
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%">&nbsp;</td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Tracheostomy :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${caseInvestigation.tracheostomy}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">PH :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${caseInvestigation.ph}"/></td>
	    </tr>
	
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Peep :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${caseInvestigation.peep}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">PO2 :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${caseInvestigation.po2}"/></td>
	    </tr>
	
	
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%">&nbsp;</td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Percentage PCO2 :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${caseInvestigation.percentagePcO2}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">IV Line 1 :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${caseInvestigation.ivLine1}"/></td>
	    </tr>
	
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Percentage HCO3 :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${caseInvestigation.percentageHcO3}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">IV Line 2 :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${caseInvestigation.ivLine2}"/></td>
	    </tr>
	
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%">&nbsp;</td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Created Time :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${caseInvestigation.createdTime}"/> - <c:out value="${caseInvestigation.createdBy}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">Modified Time :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${caseInvestigation.modifiedTime}"/> - <c:out value="${caseInvestigation.modifiedBy}"/></td>
	    </tr>
			
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Description :&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="33%" colspan=3><c:out value="${caseInvestigation.description}"/></td>	     
	    </tr>

<tr>
      <td colspan="20"  style="padding: 0px;">
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tbody>
				<tr>
					<td style="text-align: left;" class="tabDetailViewDL">&nbsp;
		
					</td>
<!-- 					<td class="tabDetailViewDL" align="right" nowrap="nowrap"> -->
<!-- 						<a href="caseinvestigation?navigation=list&searchtext=<c:out value="${searchtext}"/>&searchby=<c:out value="${searchby}" />&rowset=<c:out value="${rowset}" />&caseId=<c:out value="${caseInvestigation.caseId.caseId}" />&index=<c:out value="${index}"/>" class="tabDetailViewDFLink" style="font-weight: normal;">Return to List&nbsp;</a>					 -->
<!-- 					</td> -->
				</tr>
			</tbody>
		</table>
	  </td>
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
	function approve (){
		var delConfirm = window.confirm ("Are You Sure Want To Approve This Request ?");
		if (delConfirm){
			document.form1.navigation.value = "approve";
			document.form1.submit();
		}
	}
	function reject (){
		var delConfirm = window.confirm ("Are You Sure Want To Reject This Request ?");
		if (delConfirm){
			document.form1.navigation.value = "reject";
			document.form1.submit();
		}
	}
	function kembali (){
		document.form1.navigation.value = "back";
		document.form1.submit();
	}
	function ubah (){
		document.form1.navigation.value = "update";
		document.form1.action = "caseinvestigation-form";
		document.form1.method = "GET";
		document.form1.submit();
	}
</script>
