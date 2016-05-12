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

<form action="documentcategory-form" method="POST"  name="form1" id="form_layout">

<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>



	<spring:bind path="documentCategoryForm.documentCategoryId">
		<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>

<input type="hidden" name="navigation" value="">


			<tr>
				<td class="dataLabel"> &nbsp;</td>
				<td class="dataField">
					&nbsp;
				</td>
			    <td class="dataLabel"></td>
			    <td class="dataField"></td>
			</tr>


			<tr>
				<td class="dataLabel"> Document Category Name : </td>
				<td class="dataField">
					<spring:bind path="documentCategoryForm.documentCategoryName">
						<input type="text" size="35" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" />
						<div id="fredcaption">
							<c:out value="${status.errorMessage}" />
						</div>
					</spring:bind>
				</td>
			    <td class="dataLabel"></td>
			    <td class="dataField"></td>
			</tr>
			<tr>
				<td class="dataLabel"> Document Category Code : </td>
				<td class="dataField">
					<spring:bind path="documentCategoryForm.documentCategoryCode">
					<input type="text" size="35" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" />
						<div id="fredcaption">
							<c:out value="${status.errorMessage}" />
						</div>
					</spring:bind>
				</td>
			    <td class="dataLabel"></td>
			    <td class="dataField"></td>
			</tr>
			<tr>
				<td class="dataLabel"> Accessible To Client : </td>
				<td class="dataField">
					<spring:bind path="documentCategoryForm.accessibleToClient">
						<input type="checkbox"  name="<c:out value="${status.expression}"/>" value="1" <c:if test="${status.value eq 1 }">checked</c:if> />
						<div id="fredcaption">
							<c:out value="${status.errorMessage}" />
						</div>
					</spring:bind>
				</td>
			    <td class="dataLabel"> Accessible To Provider : </td>
			    <td class="dataField">
			    	<spring:bind path="documentCategoryForm.accessibleToProvider">
						<input type="checkbox"  name="<c:out value="${status.expression}"/>" value="1" <c:if test="${status.value eq 1 }">checked</c:if> />
						<div id="fredcaption">
							<c:out value="${status.errorMessage}" />
						</div>
					</spring:bind>
			    </td>
			</tr>
			<tr>
				<td class="dataLabel"> Accessible To HRD : </td>
				<td class="dataField">
					<spring:bind path="documentCategoryForm.accessibleToHRD">
						<input type="checkbox"  name="<c:out value="${status.expression}"/>" value="1" <c:if test="${status.value eq 1 }">checked</c:if> />
						<div id="fredcaption">
							<c:out value="${status.errorMessage}" />
						</div>
					</spring:bind>
				</td>
			    <td class="dataLabel"> Accessible To Broker : </td>
			    <td class="dataField">
			    	<spring:bind path="documentCategoryForm.accessibleToBroker">
						<input type="checkbox"  name="<c:out value="${status.expression}"/>" value="1" <c:if test="${status.value eq 1 }">checked</c:if> />
						<div id="fredcaption">
							<c:out value="${status.errorMessage}" />
						</div>
					</spring:bind>
			    </td>
			</tr>
			

			<tr>
				<td class="dataLabel"> Description : </td>				
			

		    <td class="dataField" colspan=3>
			<spring:bind path="documentCategoryForm.description">
			<textarea rows=8 cols=50 name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	</tr>
			
			<tr>
				<td class="dataLabel"> &nbsp;</td>
				<td class="dataField">
					&nbsp;
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
        <input title="Cancel [Alt+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:kembali()" name="Cancel" value=" Cancel " type="submit">         
	  </td>
      <td align="right"></td>
    </tr>
  </tbody>
</table>
</form>

<script language="javascript">
	function simpan (){

		document.form1.method = "POST";
		document.form1.action = "documentcategory-form";
		document.form1.submit();
	}
	function kembali (){
		document.form1.navigation.value = "back";
		document.form1.action = "documentcategory";
		document.form1.submit();
	}
	// forreign affairs
			// forreign affairs end
</script>