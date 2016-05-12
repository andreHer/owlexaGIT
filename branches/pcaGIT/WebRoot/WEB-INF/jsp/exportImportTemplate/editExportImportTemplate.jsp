



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

<form action="exportimporttemplate-form" method="POST"  name="form1" id="form_layout">
	<spring:bind path="exportImportTemplateForm.id">
		<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>
		
<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>





<input type="hidden" name="navigation" value="">





			<tr>
				<td class="dataLabel">  </td>				
			

						<td class="dataField">
			
			</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>

			<tr>
				<td class="dataLabel"> Template Name : </td>				
			

			<td class="dataField">
			<spring:bind path="exportImportTemplateForm.templateName">
			<input size="40" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" />
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
				<tr>
				<td class="dataLabel"> Delimiter : </td>				
			

			<td class="dataField">
			<spring:bind path="exportImportTemplateForm.delimiter">
				<input  name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" />	
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>	
			</spring:bind>
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
	

			<tr>
				<td class="dataLabel"> Tipe Transfer: </td>				
			

						<td class="dataField">
			<spring:bind path="exportImportTemplateForm.jenisTransfer">
                            <select name="<c:out value="${status.expression}" />">
                                <option value="1" <c:if test="${status.value eq 1}">selected</c:if>>IMPORT DATA</option>
                                <option value="2" <c:if test="${status.value eq 2}">selected</c:if>>EXPORT DATA</option>
                            </select>
	
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
			<tr>
				<td class="dataLabel"> Jenis Mapping: </td>				
			

						<td class="dataField">
			<spring:bind path="exportImportTemplateForm.tipe">
                            <select name="<c:out value="${status.expression}" />">
                                <option value="1" <c:if test="${status.value eq 1}">selected</c:if>>CLAIM HEADER</option>
                                <option value="2" <c:if test="${status.value eq 2}">selected</c:if>>CLAIM DETAIL</option>
                                <option value="3" <c:if test="${status.value eq 3}">selected</c:if>>MEMBER</option>
                                <option value="4" <c:if test="${status.value eq 4}">selected</c:if>>PROVIDER</option>
                                <option value="5" <c:if test="${status.value eq 5}">selected</c:if>>POLICY PROVIDER</option>
                                <option value="6" <c:if test="${status.value eq 6}">selected</c:if>>MEMBER PROVIDER</option>
                                <option value="7" <c:if test="${status.value eq 7}">selected</c:if>>PROVIDER TYPE PROCEDURE - INA CBG</option>
                                <option value="8" <c:if test="${status.value eq 8}">selected</c:if>>PROVIDER TYPE DIAGNOSIS - INA CBG</option>
                                <option value="9" <c:if test="${status.value eq 9}">selected</c:if>>PROVIDER PROCEDURE - INA CBG</option>
                                <option value="10" <c:if test="${status.value eq 10}">selected</c:if>>PROVIDER DIAGNOSIS - INA CBG</option>
                            </select>
	
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
				<spring:bind path="exportImportTemplateForm.description">
                            <textarea rows="6" cols="40" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
                       <div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>     
			
			</spring:bind>
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
		
				<tr>
				<td class="dataLabel"> Template Mapping  : </td>				
			

		    <td class="dataField">
			<spring:bind path="exportImportTemplateForm.templateMapping">
                            <textarea rows="6" cols="40" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
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
		document.form1.action = "exportimporttemplate-form";
		document.form1.submit();
	}
	function cancel (){
		document.form1.navigation.value = "back";
		document.form1.action = "exportimporttemplate";
		document.form1.submit();
	}
	// forreign affairs
			// forreign affairs end
</script>