<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<form action="callcategory-form" method="POST"  name="form1" id="form_layout">
<input type="hidden" name="navigation" value="">
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

<%
String alert = (String) request.getAttribute("alert");
if (alert != null && !alert.trim().equals("")) {%>
	<div id="warning">
		<c:out value="${alert}"></c:out>
	</div>
	<%}%>
	
<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>
		<tr>
			<td>
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<tbody>


	&nbsp;


	<spring:bind path="callCategoryForm.callCategoryId">
		<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>







			<tr>
				<td class="dataLabel"> Call Category Name : </td>				
			

			<td class="dataField">
			<spring:bind path="callCategoryForm.callCategoryName">
			<input type="text" size="35" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" />
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	   	<td class="dataLabel"> Call Category Code : </td>				
			

			<td class="dataField">
			<spring:bind path="callCategoryForm.callCategoryCode">
			<input type="text" size="35" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" />
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	</tr>
	
	
	


			

			<tr>
				<td class="dataLabel"> Description : </td>				
			

		    <td class="dataField" colspan="3">
			<spring:bind path="callCategoryForm.description">
				
			<input type="text" size="35" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" />	
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
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
      <td style="padding-bottom: 2px;">
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
		document.form1.action = "callcategory-form";
		document.form1.submit();
	}
	function kembali (){
		document.form1.navigation.value = "back";
		document.form1.action = "callcategory";
		document.form1.submit();
	}
	// forreign affairs
			// forreign affairs end
</script>