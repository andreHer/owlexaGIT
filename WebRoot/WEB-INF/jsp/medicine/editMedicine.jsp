<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<%
String alert = (String) request.getAttribute("alert");
if (alert != null && !alert.trim().equals("")) {%>
	<div id="warning">
		<c:out value="${alert}"></c:out>
	</div>
	<%}%>

<form action="medicine-form" method="POST"  name="form1" id="form_layout">
		<spring:bind path="medicineForm.medicineId">
			<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
		</spring:bind>
<input type="hidden" name="navigation" value="">


<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>
		<tr>
			<td>
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<tbody>



		
			<tr>
				<td class="dataLabel">  </td>				
			

		    <td class="dataField">
			
		</td>
	    <td class="dataLabel">  </td>				
			

		    <td class="dataField" >
			
		</td>
	</tr>
				
		
				
		
		

			<tr>
				<td class="dataLabel"> Medicine Name : </td>				
			

			<td class="dataField">
			<spring:bind path="medicineForm.medicineName">
				<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" >
			
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	    <td class="dataLabel"> Medicine Type : </td>				
			

						<td class="dataField">
			<spring:bind path="medicineForm.medicineType">
					<select name="<c:out value="${status.expression}" />">
						<option value="ANTI BIOTIK" <c:if test="${status.value eq 'ANTI BIOTIK' }">selected</c:if>>ANTI BIOTIK</option>
						<option value="ANTI VIRUS" <c:if test="${status.value eq 'ANTI VIRUS' }">selected</c:if>>ANTI VIRUS</option>
						<option value="ANTI INFLAMASI" <c:if test="${status.value eq 'ANTI INFLAMASI' }">selected</c:if>>ANTI INFLAMASI</option>
						<option value="ANTI HISTAMIN" <c:if test="${status.value eq 'ANTI HISTAMIN' }">selected</c:if>>ANTI HISTAMIN</option>
						<option value="ANTI PIRETIK" <c:if test="${status.value eq 'ANTI PIRETIK' }">selected</c:if>>ANTI PIRETIK</option>
						<option value="ANTI DEPRESAN" <c:if test="${status.value eq 'ANTI DEPRESAN' }">selected</c:if>>ANTI DEPRESAN</option>
						<option value="ANALGESIK" <c:if test="${status.value eq 'ANALGESIK' }">selected</c:if>>ANALGESIK</option>
						<option value="VITAMIN MINERAL" <c:if test="${status.value eq 'VITAMIN MINERAL' }">selected</c:if>>VITAMIN MINERAL</option>
						<option value="TOPICAL" <c:if test="${status.value eq 'TOPICAL' }">selected</c:if>>TOPICAL</option>
					</select>
	
			</spring:bind>
			</td>
	</tr>
	

		
				
		
		

		
				
		
		

			<tr>
				<td class="dataLabel"> General Diagnose : </td>				
			

		    <td class="dataField">
			<spring:bind path="medicineForm.generalDiagnose">
				<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="22">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	    		<td class="dataLabel"> Medicine Classification : </td>				
			

			<td class="dataField">
			<spring:bind path="medicineForm.medicineClassification">
				<select name="">
					<option value="OBAT KERAS" <c:if test="${status.value eq 'OBAT KERAS' }">selected</c:if>>OBAT KERAS</option>
					<option value="OBAT UMUM" <c:if test="${status.value eq 'OBAT UMUM' }">selected</c:if>>OBAT UMUM</option>
				</select>
			</spring:bind>
		</td>
	</tr>
		

	

		
				
		
		

			<tr>
				<td class="dataLabel"> Medical Dosage : </td>				
			

		    <td class="dataField">
			<spring:bind path="medicineForm.medicalDosage">
				<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="22">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
		
				
		
		

			<tr>
				<td class="dataLabel"> Medicine Code : </td>				
			

			<td class="dataField">
			<spring:bind path="medicineForm.medicineCode">
				<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="22">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>	
			<td class="dataLabel"> Medicine Price : </td>				
			
			<td class="dataField">
			<spring:bind path="medicineForm.medicinePrice">
					<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<fmt:formatNumber><c:out value="${status.value}" /></fmt:formatNumber>" maxlength="22">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
	</tr>
	

		
		
				
		
		

			<tr>
				<td class="dataLabel"> Medicine Factory : </td>				
			

			<td class="dataField">
			<spring:bind path="medicineForm.medicineFactory">
				<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="22">
				
			</spring:bind>
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
	

		
				
		
		

			<tr>
				<td class="dataLabel"> Contra Indication : </td>				
			

		    <td class="dataField">
			<spring:bind path="medicineForm.contraIndication">
			<textarea  cols="60" rows="8" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	    <td class="dataLabel"> Description : </td>				
			

		    <td class="dataField" >
			<spring:bind path="medicineForm.description">
			<textarea  cols="60" rows="8" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	</tr>

	

	

		
			<tr>
				<td class="dataLabel">  </td>				
			

		    <td class="dataField">
			
		</td>
	    <td class="dataLabel">  </td>				
			

		    <td class="dataField" >
			
		</td>
	</tr>
				
		
		

						</tbody>
				</table>
			</td>
		</tr>
	</tbody>
</table>
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr> 
      <td style="padding-top: 2px;">        
        <input title="Save [Alt+Shift+E]" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:simpan()" name="Save" value=" Simpan " type="button">
        <input title="Cancel [Alt+Shift+H]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:kembali()" name="Cancel" value=" Cancel " type="button">        		
		</td>
      <td align="right"></td>
    </tr>
  </tbody>
</table>
</form>

<script language="javascript">
	function simpan (){

		document.form1.method = "POST";
		document.form1.action = "medicine-form";
		document.form1.submit();
	}
	function kembali (){
		document.form1.navigation.value = "back";
		document.form1.action = "medicine";
		document.form1.submit();
	}
	// forreign affairs
			// forreign affairs end
</script>