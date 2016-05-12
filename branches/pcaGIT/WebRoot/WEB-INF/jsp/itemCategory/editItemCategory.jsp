<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<form action="itemcategory-form" method="POST"  name="form1" id="form_layout">
	<spring:bind path="itemCategoryForm.itemCategoryId">
		<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>

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
			<td class="dataLabel"></td>
			<td class="dataField"></td>
			<td class="dataLabel"></td>
			<td class="dataField"></td>
		</tr>
		<tr>
			<td class="dataLabel"> Item Category Name : </td>
			<td class="dataField">
				<spring:bind path="itemCategoryForm.itemCategoryName">
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
			<td class="dataLabel"> Item Category Code : </td>				
		
		
		<td class="dataField">
		<spring:bind path="itemCategoryForm.itemCategoryCode">
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
			<td class="dataLabel"> Case Category : </td>
			<td class="dataField">
				<spring:bind path="itemCategoryForm.caseCategoryId">
					<select name="<c:out value="${status.expression}" />">
						<option value="">-- SELECT ONE --</option>
						<c:forEach items="${caseCategoryList}" var="cc" >
							<option value="<c:out value="${cc.caseCategoryId}" />" <c:if test="${status.value eq cc.caseCategoryId}">selected</c:if>><c:out value="${cc.caseCategoryName}" /></option>
						</c:forEach>
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
			<td class="dataLabel"> Is Client Invoice Item : </td>
			<td class="dataField">
				<spring:bind path="itemCategoryForm.isClientInvoiceItem">
					<input type="checkbox" name="<c:out value="${status.expression}" />" value="1" <c:if test="${status.value eq 1 }">checked</c:if>>			
				</spring:bind>
			</td>
		    <td class="dataLabel"></td>
		    <td class="dataField"></td>
		</tr>
		<tr>
			<td class="dataLabel"> Item Category EDC Code : </td>				
		

		<td class="dataField">
		<spring:bind path="itemCategoryForm.itemEDCCode">
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
			<td class="dataLabel"> Item Category EDC Name : </td>				
		

		<td class="dataField">
		<spring:bind path="itemCategoryForm.itemCategoryEDCName">
<input type="text" size="25" maxlength="20" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" />
	<div id="fredcaption">
		<c:out value="${status.errorMessage}" />
	</div>
</spring:bind>
	</td>
    <td class="dataLabel"></td>
    <td class="dataField"></td>
</tr>
					<tr>
						<td class="dataLabel"> Item Category Alternate Code : </td>
						<td class="dataField">
							<spring:bind path="itemCategoryForm.itemCategoryAlternateCode">
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
				<td class="dataLabel"> Description : </td>	
				<td class="dataField" colspan="3" >
					<spring:bind path="itemCategoryForm.description">
					<textarea  cols="60" rows="8" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
						<div id="fredcaption">
							<c:out value="${status.errorMessage}" />
						</div>
					</spring:bind>
				</td>
	    
			</tr>
			<tr>
				<td class="dataLabel"></td>
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
        <input title="Save [Alt+Shift+S]" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="this.disabled='disabled';javascript:simpan()" name="Save" value=" Save " type="submit">
        <input title="Cancel [Alt+Shift+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="this.disabled='disabled';javascript:cancel()" name="Cancel" value=" Cancel " type="button">         
	  </td>
      <td align="right"></td>
    </tr>
  </tbody>
</table>

</form>

<script language="javascript">
	function simpan (){

		document.form1.method = "POST";
		document.form1.action = "itemcategory-form";
		document.form1.submit();
	}
	function cancel(){
		document.form1.navigation.value = "back";
		document.form1.action = "itemcategory";
		document.form1.submit();
	}
	// forreign affairs
			// forreign affairs end
</script>