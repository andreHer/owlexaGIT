



<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<form action="rejectcategory-form" method="POST"  name="form1" id="form_layout">
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
		<tr>
			<td>
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<tbody>


	<spring:bind path="rejectCategoryForm.rejectCategoryId">
		<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>







			<tr>
				<td class="dataLabel"> Reject Category Name : </td>				
			

			<td class="dataField">
			<spring:bind path="rejectCategoryForm.rejectCategoryName">
			
				<input type="text" class="inputbox" size=35 name="<c:out value="${status.expression}" />" value="<c:out value="${status.value }" />"/>
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
	

		
			<tr>
				<td class="dataLabel"> Reject Category Code : </td>				
			

			<td class="dataField">
			<spring:bind path="rejectCategoryForm.rejectCategoryCode">
			<input type="text" class="inputbox"  name="<c:out value="${status.expression}" />" value="<c:out value="${status.value }" />"/>
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
	
				
			<tr>
				<td class="dataLabel"> Category Type : </td>				
			

			<td class="dataField">
			<spring:bind path="rejectCategoryForm.tipe">
				<select name="<c:out value="${status.expression}" />">
					<option value="-1">-- SELECT ONE --</option>
					<option value="1" <c:if test="${status.value eq 1 }">selected</c:if>>CLAIM CATEGORY</option>
					<option value="2" <c:if test="${status.value eq 2 }">selected</c:if>>CASE CATEGORY</option>
				</select>
			
			</spring:bind>
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
	
		
		

			<tr>
				<td class="dataLabel"> Description : </td>				
			

		    <td class="dataField">
			<spring:bind path="rejectCategoryForm.description">
			<textarea rows="8" cols="40" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
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
<br />
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
		document.form1.action = "rejectcategory-form";
		document.form1.submit();
	}
	function kembali (){
		document.form1.navigation.value = "back";
		document.form1.action = "rejectcategory";
		document.form1.submit();
	}
	// forreign affairs
			// forreign affairs end
</script>