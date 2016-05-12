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
	
<form action="providermedicine-form" method="POST"   name="form1" id="form_layout">

<input type="hidden" name="navigation" value="">

<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>
		<tr>
			<td class="dataLabel">&nbsp; </td>		
			<td class="dataField">&nbsp;</td>
		    <td class="dataLabel"></td>
		    <td class="dataField"></td>
		</tr>	
		<tr>
			<td class="dataLabel">Provider  : </td>		
			<td class="dataField">
				<spring:bind path="providerMedicineForm.providerId">
					<input type="hidden" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >			
				</spring:bind>
				<spring:bind path="providerMedicineForm.providerName">	
					<input type="text" name="<c:out value="${status.expression}" />"  value="<c:out value="${status.value }" />"  size=40>
				</spring:bind>	
			</td>
		    <td class="dataLabel">Medicine  : </td>		
			<td class="dataField">
				<spring:bind path="providerMedicineForm.medicineId">
					<input type="hidden" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >			
				</spring:bind>
				<spring:bind path="providerMedicineForm.medicineName">	
					<input type="text" name="<c:out value="${status.expression}" />"  value="<c:out value="${status.value }" />" size="40" >
				</spring:bind>	
			</td>
		</tr>
		<tr>
			<td class="dataLabel">Medicine Price  : </td>		
			<td class="dataField">
				
				<spring:bind path="providerMedicineForm.itemValue">	
					<input type="text" name="<c:out value="${status.expression}" />"  value="<c:out value="${status.value }" />" >
				</spring:bind>	
			</td>
		    <td class="dataLabel"></td>
		    <td class="dataField"></td>
		</tr>
		
		<tr>
			<td class="dataLabel">Super VIP Price  : </td>		
			<td class="dataField">
				
				<spring:bind path="providerMedicineForm.svip">	
					<input type="text" name="<c:out value="${status.expression}" />"  value="<c:out value="${status.value }" />" >
				</spring:bind>	
			</td>
		    <td class="dataLabel">VIP Price  : </td>		
			<td class="dataField">
				
				<spring:bind path="providerMedicineForm.vip">	
					<input type="text" name="<c:out value="${status.expression}" />"  value="<c:out value="${status.value }" />" >
				</spring:bind>	
			</td>
		</tr>
		<tr>
			<td class="dataLabel">Kelas 1 : </td>		
			<td class="dataField">
				
				<spring:bind path="providerMedicineForm.kelas1">	
					<input type="text" name="<c:out value="${status.expression}" />"  value="<c:out value="${status.value }" />" >
				</spring:bind>	
			</td>
		    <td class="dataLabel">Kelas 2 : </td>		
			<td class="dataField">
				
				<spring:bind path="providerMedicineForm.kelas2">	
					<input type="text" name="<c:out value="${status.expression}" />"  value="<c:out value="${status.value }" />" >
				</spring:bind>	
			</td>
		</tr>
		<tr>
			<td class="dataLabel">Kelas 3 : </td>		
			<td class="dataField">
				
				<spring:bind path="providerMedicineForm.kelas3">	
					<input type="text" name="<c:out value="${status.expression}" />"  value="<c:out value="${status.value }" />" >
				</spring:bind>	
			</td>
		    <td class="dataLabel"></td>
		    <td class="dataField"></td>
		</tr>
		
		<tr>
			<td class="dataLabel">&nbsp; </td>		
			<td class="dataField">&nbsp;</td>
		    <td class="dataLabel"></td>
		    <td class="dataField"></td>
		</tr>

	</tbody>
</table>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td style="padding-top: 2px;">
	    <input type="hidden" name="notyet" value="">
        <input title="Save [Alt+S]" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:simpan()" name="Save" value=" Save " type="submit">
        <input title="Cancel [Alt+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:cancel()" name="Cancel" value=" Cancel " type="button">         
	  </td>
      <td align="right"></td>
    </tr>
  </tbody>
</table>
</form>

<script language="javascript">
	function simpan (){

		document.form1.method = "POST";
		document.form1.action = "providermedicine-form";
		document.form1.submit();
	}
	function cancel (){
		document.form1.navigation.value = "listprovider";
		document.form1.action = "providermedicine";
		document.form1.submit();
	}
</script>