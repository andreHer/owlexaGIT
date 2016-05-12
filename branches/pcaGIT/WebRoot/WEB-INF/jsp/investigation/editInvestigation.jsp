<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<form action="investigation-form" method="POST"  name="form1" id="form_layout">
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td style="padding-bottom: 2px;">
	    <input type="hidden" name="notyet" value="">
        <input title="Save [Alt+Shift+S]" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:simpan()" name="Save" value=" Save " type="submit">
        <input title="Cancel [Alt+Shift+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:cancel()" name="Cancel" value=" Cancel " type="button">         
	  </td>
      <td align="right"></td>
    </tr>
  </tbody>
</table>

<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>
		<tr>
			<td>
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<tbody>

<H1>
Investigation
</H1>
<%
String alert = (String) request.getAttribute("alert");
if (alert != null && !alert.trim().equals("")) {%>
	<div id="warning">
		<c:out value="${alert}"></c:out>
	</div>
	<%}%>


	<spring:bind path="investigationForm.investigationId">
		<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>

<input type="hidden" name="navigation" value="">


			<tr>
				<td class="dataLabel"> Investigation Subject : </td>				
			

			<td class="dataField">
			<spring:bind path="investigationForm.investigationSubject">
		<input type="text" size="35" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" />	
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
				<td class="dataLabel"> Investigation Category : </td>				
			

						<td class="dataField">
			<spring:bind path="investigationForm.investigationCategory">
						<select name="<c:out value="${status.expression}" />">
							<c:forEach items="${investigationCategories}" var="ic">
								<option value="${ic.investigationCategoryId}" <c:if test="${ic.investigationCategoryId eq status.value}">selected</c:if>><c:out value="${ic.investigationCategoryName}"/> </option>
							</c:forEach>
						</select>
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>

	</tr>
	

<tr>
				<td class="dataLabel"> Hospital : </td>				
			

			<td class="dataField">
			<spring:bind path="investigationForm.hospital">
		<input type="text" size="35" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" />	
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
				<td class="dataLabel"> Investigation Time : </td>				
			

			<td class="dataField">
			<spring:bind path="investigationForm.consultDoctor">
		<input type="text" size="35" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" />	
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>

	</tr>
			
		


			

			<tr>
				<td class="dataLabel"> Head Doctor : </td>				
			

			<td class="dataField">
			<spring:bind path="investigationForm.headDoctor">
		<input type="text" size="35" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" />	
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
				<td class="dataLabel"> Consult Doctor : </td>				
			

			<td class="dataField">
			<spring:bind path="investigationForm.consultDoctor">
		<input type="text" size="35" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" />	
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>

	</tr>
	



			<tr>
				<td class="dataLabel"> Description : </td>				
			

		    <td class="dataField" colspan="2">
			<spring:bind path="investigationForm.description">
			<textarea cols="60" rows="8" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
		

	



			

			
			


			

						</tbody>
				</table>
			</td>
		</tr>
	</tbody>
</table>

</form>

<script language="javascript">
	function simpan (){

		document.form1.method = "POST";
		document.form1.action = "investigation-form";
		document.form1.submit();
	}
	function kembali (){
		document.form1.navigation.value = "back";
		document.form1.action = "investigation";
		document.form1.submit();
	}
	// forreign affairs
			// forreign affairs end
</script>