<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>



<form action="medicine" method="GET" name="form1" id="form_layout">

	<input type="hidden" name="navigation" value="">
	<input type="hidden" name="medicineId" value="<c:out value="${medicine.medicineId}" />">
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr> 
      <td style="padding-bottom: 2px;">   
        <c:if test="${theUser.roleId.roleId ne 6}">
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
      <td colspan="20" class="listViewPaginationTdS1" style="padding: 0px;">
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tbody>
				<tr>
					<td style="text-align: left;" class="tabDetailViewDL">&nbsp;
						
					</td>
					<td class="tabDetailViewDL" align="right" nowrap="nowrap">
						<a href="medicine?navigation=search&searchtext=<c:out value="${searchtext}"/>&searchby=<c:out value="${searchby}" />&index=<c:out value="${index}"/>" class="tabDetailViewDFLink" style="font-weight: normal;">
							<img src="images/back-arrow.png" title="Return to List"/>
						</a>
					</td>
				</tr>
			</tbody>
		</table>
	  </td>
    </tr>
	
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Medicine Name :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${medicine.medicineName}"/></td>	      
	      <td class="tabDetailViewDL" valign="top" width="17%">Medicine Code :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${medicine.medicineCode}"/></td>
	    </tr>
	
		
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Medicine Type :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${medicine.medicineType}"/></td>
			  
	      <td class="tabDetailViewDL" valign="top" width="17%">Medicine Factory :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${medicine.medicineFactory}"/></td>
	    </tr>
			
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Modified Time :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${medicine.modifiedTime}"/> - <c:out value="${medicine.modifiedBy}"/></td>
<td class="tabDetailViewDL" valign="top" width="17%">Created Time :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${medicine.createdTime}"/> - <c:out value="${medicine.createdBy}"/></td>
	    </tr>
			
			
			
	
			
		
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">General Diagnose :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${medicine.generalDiagnose}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">Medicine Classification :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${medicine.medicineClassification}"/></td>
	    </tr>
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Medical Dosage :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${medicine.medicalDosage}"/></td>	      
	      <td class="tabDetailViewDL" valign="top" width="17%">Contra Indication :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${medicine.contraIndication}"/></td>
	    </tr>
	
			
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Medicine Price :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><fmt:formatNumber><c:out value="${medicine.medicinePrice}"/></fmt:formatNumber></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
			
			
	
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Description :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%" colspan=3><c:out value="${medicine.description}"/></td>
	     
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
		document.form1.action = "medicine-form";
		document.form1.method = "GET";
		document.form1.submit();
	}
</script>
