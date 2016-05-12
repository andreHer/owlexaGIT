



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

<form action="casemedicine-form" method="POST"  name="form1" id="form_layout">

<input type="hidden" name="navigation" value="">


<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>


			<tr>
				<td class="dataLabel">&nbsp;</td>
				<td class="dataField"></td>
			    <td class="dataLabel"></td>
			    <td class="dataField"></td>
			</tr>

	<spring:bind path="caseMedicineForm.caseMedicineId">
		<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>

			<spring:bind path="caseMedicineForm.medicineId">
	<input type="hidden" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>


		
				
		
		

			<tr>
				<td class="dataLabel"> Medicine  : </td>				
			

						<td class="dataField">
			<spring:bind path="caseMedicineForm.medicineName">
				<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
			

		
				
		
		

			<tr>
				<td class="dataLabel"> Total Usage : </td>				
			
			<td class="dataField">
			<spring:bind path="caseMedicineForm.totalUsage">
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
				<td class="dataLabel"> Total Charge : </td>				
			
			<td class="dataField">
			<spring:bind path="caseMedicineForm.totalCharge">
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
				<td class="dataLabel"> Reference Price : </td>				
			
			<td class="dataField">
			<spring:bind path="caseMedicineForm.referencePrice">
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
				<td class="dataLabel"> Description : </td>				
			

		    <td class="dataField">
			<spring:bind path="caseMedicineForm.description">
			<textarea rows=8 cols=45 name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
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

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td style="padding-top: 2px;">
	    <input type="hidden" name="notyet" value="">
        <input title="Save [Alt+Shift+S]" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:simpan()" name="Save" value=" Save " type="submit">
        <input title="Cancel [Alt+Shift+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:cancel()" name="Cancel" value=" Cancel " type="button">         
	  </td>
      <td align="right"></td>
    </tr>
  </tbody>
</table>
</form>

<script language="javascript">
	function simpan (){

		document.form1.method = "POST";
		document.form1.action = "casemedicine-form";
		document.form1.submit();
	}
	function kembali (){
		document.form1.navigation.value = "back";
		document.form1.action = "casemedicine";
		document.form1.submit();
	}
	// forreign affairs
			// forreign affairs end
</script>