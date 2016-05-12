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



<form action="casecategory-form" method="POST"  name="form1" id="form_layout">
	<input type="hidden" name="navigation" value="">
	<spring:bind path="caseCategoryForm.caseCategoryId">
		<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
	</spring:bind>

<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>



			<tr>
				<td class="dataLabel">&nbsp; </td>
				<td class="dataField"></td>
			    <td class="dataLabel"></td>
			    <td class="dataField"></td>
			</tr>


			

			<tr>
				<td class="dataLabel"> Case Category Name : </td>				
			

			<td class="dataField">
			<spring:bind path="caseCategoryForm.caseCategoryName">
			<input type="text" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" size="35">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
				<td class="dataLabel"> Case Category Code : </td>				
			

			<td class="dataField">
			<spring:bind path="caseCategoryForm.caseCategoryCode">
			<input type="text" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" size="35">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	</tr>
	


			

			<tr>
				<td class="dataLabel"> Description : </td>				
			

		    <td class="dataField" colspan="2">
			<spring:bind path="caseCategoryForm.description">
			<textarea cols="60" rows="8" class="inputbox" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>

	</tr>

	</tbody>
</table>


</form>
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

<script language="javascript">
	function simpan (){

		document.form1.method = "POST";
		document.form1.action = "casecategory-form";
		document.form1.submit();
	}
	function cancel(){
		document.form1.navigation.value = "back";
		document.form1.action = "casecategory";
		document.form1.submit();
	}
	// forreign affairs
			// forreign affairs end
</script>