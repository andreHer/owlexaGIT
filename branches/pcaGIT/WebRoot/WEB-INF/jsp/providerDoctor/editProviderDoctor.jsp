<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<form action="providerdoctor-form" method="POST"   name="form1" id="form_layout">

	<spring:bind path="providerDoctorForm.providerDoctorId">
		<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
	</spring:bind>
	
<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>
		<input type="hidden" name="navigation" value="">
		
				<tr>
			<td class="dataLabel"> Doctor Name : </td>
			<td class="dataField">
				<spring:bind path="providerDoctorForm.doctorName">
					<input type="text" name="<c:out value="${status.expression }" />" value="<c:out value="${status.value }" />" />
					<div id="fredcaption">
						<c:out value="${status.errorMessage}" />
					</div>
				</spring:bind>
			</td>
		    <td class="dataLabel"> Poliklinik Name : </td>
		    <td class="dataField">
		    	<spring:bind path="providerDoctorForm.poliklinikId">
		    		<select name="">
		    			<option value="">-- SELECT ONE --</option>
		    			<c:forEach items="${poliklinikList}" var="poli">
		    				<option value="<c:out value="${poli.poliklinikId}" />" <c:if test="${status.value eq poli.poliklinikId}">selected</c:if>><c:out value="${poli.poliklinikName}" /></option>
		    			</c:forEach>
		    		</select>
		    	</spring:bind>
		    </td>
		</tr>
		
		<tr>
			<td class="dataLabel"> Monday : </td>
			<td class="dataField">
				<spring:bind path="providerDoctorForm.monday">
					<input type="text" name="<c:out value="${status.expression }" />" value="<c:out value="${status.value }" />" />
					<div id="fredcaption">
						<c:out value="${status.errorMessage}" />
					</div>
				</spring:bind>
			</td>
		    <td class="dataLabel"> Tuesday : </td>
		    <td class="dataField">
		    	<spring:bind path="providerDoctorForm.tuesday">
		    		<input type="text" name="<c:out value="${status.expression }" />" value="<c:out value="${status.value }" />" />
		    	</spring:bind>
		    </td>
		</tr>
		
				<tr>
			<td class="dataLabel"> Wednesday : </td>
			<td class="dataField">
				<spring:bind path="providerDoctorForm.wednesday">
					<input type="text" name="<c:out value="${status.expression }" />" value="<c:out value="${status.value }" />" />					
					<div id="fredcaption">
						<c:out value="${status.errorMessage}" />
					</div>
				</spring:bind>
			</td>
		    <td class="dataLabel"> Thursday : </td>
		    <td class="dataField">
		    	<spring:bind path="providerDoctorForm.thursday">
		    		<input type="text" name="<c:out value="${status.expression }" />" value="<c:out value="${status.value }" />" />
		    	</spring:bind>
		    </td>
		</tr>
				<tr>
			<td class="dataLabel"> Friday : </td>
			<td class="dataField">
				<spring:bind path="providerDoctorForm.friday">
					<input type="text" name="<c:out value="${status.expression }" />" value="<c:out value="${status.value }" />" />					
					<div id="fredcaption">
						<c:out value="${status.errorMessage}" />
					</div>
				</spring:bind>
			</td>
		    <td class="dataLabel"> Saturday : </td>
		    <td class="dataField">
		    	<spring:bind path="providerDoctorForm.saturday">
		    		<input type="text" name="<c:out value="${status.expression }" />" value="<c:out value="${status.value }" />" />
		    	</spring:bind>
		    </td>
		</tr>
				<tr>
			<td class="dataLabel"> Sunday : </td>
			<td class="dataField">
				<spring:bind path="providerDoctorForm.sunday">
					<input type="text" name="<c:out value="${status.expression }" />" value="<c:out value="${status.value }" />" />					
					<div id="fredcaption">
						<c:out value="${status.errorMessage}" />
					</div>
				</spring:bind>
			</td>
		    <td class="dataLabel"></td>
		    <td class="dataField">&nbsp;</td>
		</tr>
		
		
			
	</tbody>
</table>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td style="padding-top: 2px;">
	    <input type="hidden" name="notyet" value="">
        <input title="Save [Alt+S]" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:simpan()" name="Save" value=" Save " type="submit">
        <input title="Cancel [Alt+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:cancel()" name="Cancel" value=" Cancel " type="submit">         
	  </td>
      <td align="right"></td>
    </tr>
  </tbody>
</table>
</form>

<script language="javascript">
	function simpan (){

		document.form1.method = "POST";
		document.form1.action = "providerdoctor-form";
		document.form1.submit();
	}
	function cancel(){
		document.form1.navigation.value = "back";
		document.form1.action = "providerdoctor";
		document.form1.submit();
	}
			// forreign affairs end
</script>