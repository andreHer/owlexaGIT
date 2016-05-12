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

<br />
<form action="medicinecategory-form" method="POST"  name="form1" id="form_layout">
	<spring:bind path="medicineCategoryForm.medicineCategoryId">
		<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
	</spring:bind>

<input type="hidden" name="navigation" value="">


<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>



	



			<tr>
				<td class="dataLabel">&nbsp;</td>
				<td class="dataField"></td>
	    		<td class="dataLabel"></td>
	    		<td class="dataField"></td>
			</tr>
	

			<tr>
				<td class="dataLabel"> Medicine Category Name : </td>				
			

			<td class="dataField">
			<spring:bind path="medicineCategoryForm.medicineCategoryName">
				<input size=40 type="text" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value }" />">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
	

		
				
		
		

			<tr>
				<td class="dataLabel"> Medicine Category Code : </td>				
			

			<td class="dataField">
			<spring:bind path="medicineCategoryForm.medicineCategoryCode">
				<input type="text" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value }" />">			
			</spring:bind>
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
	

		
				
		
		

			<tr>
				<td class="dataLabel"> Description : </td>				
			

		    <td class="dataField">
			<spring:bind path="medicineCategoryForm.description">
			<textarea cols=40 rows=5 name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
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
		document.form1.action = "medicinecategory-form";
		document.form1.submit();
	}
	function kembali (){
		document.form1.navigation.value = "back";
		document.form1.action = "medicinecategory";
		document.form1.submit();
	}
	// forreign affairs
			// forreign affairs end
</script>