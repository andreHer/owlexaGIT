<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<form action="diagnosis-form" method="POST"  name="form1" id="form_layout">
<input type="hidden" name="navigation" value="">

<%
String alert = (String) request.getAttribute("alert");
if (alert != null && !alert.trim().equals("")) {%>
	<div id="warning">
		<c:out value="${alert}"></c:out>
	</div>
	<%}%>

<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>



	<spring:bind path="diagnosisForm.diagnosisId">
		<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>





			

			<tr>
				<td class="dataLabel">  </td>				
			

		    <td class="dataField">
			
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
		

		<tr>
				<td class="dataLabel">Diagnosis Name: </td>				
			

		    <td class="dataField">
			<spring:bind path="diagnosisForm.diagnosisName">
		<input type="text" size="35" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" />	
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	    <td class="dataLabel">Diagnosis Code: </td>
	    <td class="dataField">
	    <spring:bind path="diagnosisForm.diagnosisCode">
		<input type="text" size="35" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" />	
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
	    </td>
	</tr>
		
	


			

			<tr>
				<td class="dataLabel"> </td>				
			

			<td class="dataField">
			
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
	


			

	
		

	


			

			<tr>
				<td class="dataLabel"> Initial Symptom : </td>				
			

		    <td class="dataField">
			<spring:bind path="diagnosisForm.initialSymptom">
			<textarea cols="40" rows="6" class="inputbox" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	    <td class="dataLabel">Vital Sign :</td>
	    <td class="dataField">
	    <spring:bind path="diagnosisForm.vitalSign">
			<textarea cols="40" rows="6" class="inputbox" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
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
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
		
			<tr>
				<td class="dataLabel"> Description : </td>				
			

		    <td class="dataField">
			<spring:bind path="diagnosisForm.description">
			<textarea cols="40" rows="6" class="inputbox" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
		

			

			<tr>
				<td class="dataLabel">  </td>				
			

		    <td class="dataField">
			
		</td>
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
		document.form1.action = "diagnosis-form";
		document.form1.submit();
	}
	function cancel (){
	
		<c:if test="${navigation eq 'tambah'}">
		document.form1.navigation.value = "back";
		</c:if>
		<c:if test="${navigation eq 'update'}">
		document.form1.navigation.value = "detail";
		</c:if>
		document.form1.action = "diagnosis";
		document.form1.submit();
	}
	// forreign affairs
			// forreign affairs end
</script>