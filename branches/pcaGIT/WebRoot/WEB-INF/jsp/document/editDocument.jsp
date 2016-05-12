



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



<form action="document-form" method="POST" enctype="multipart/form-data"  name="form1" id="form_layout">
<input type="hidden" name="navigation" value="">

<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>
		




	<spring:bind path="documentForm.documentId">
		<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>







	


	
<tr>
		<td class="dataLabel"> </td>		
		<td class="dataField">&nbsp;
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>

		
	<tr>
		<td class="dataLabel">Reference Number : </td>		
		<td class="dataField" colspan="3">
		<spring:bind path="documentForm.claimId">
			<input type="hidden" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >			
		</spring:bind>
		<spring:bind path="documentForm.caseId">
			<input type="hidden" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >			
		</spring:bind>
		<spring:bind path="documentForm.claimNumber">
			<input type="text" size=45 name="<c:out value="${status.expression}" />" id="claimIdId" value="<c:out value="${status.value}" />" >
		</spring:bind>

		
		</td>
	    
	    
	</tr>

	<tr>
		<td class="dataLabel"> Document 1 : </td>
		<td class="dataField">
			<spring:bind path="documentForm.file1">
				<input type="file" name="<c:out value="${status.expression}" />">
			</spring:bind>
		</td>
	    <td class="dataLabel"> Document Type :</td>
	    <td class="dataField">
	    	<spring:bind path="documentForm.documentCategoryId1">
				<select name="<c:out value="${status.expression }" />">
					<option value="-1">--- SELECT ONE ---</option>
					<c:forEach items="${documentCategories}" var="doc">
						<option value="<c:out value="${doc.documentCategoryId}" />"><c:out value="${doc.documentCategoryName}" /></option>
					
					</c:forEach>
				</select>
			</spring:bind>
	    </td>
	</tr>
	<tr>
		<td class="dataLabel"> Document 2 : </td>
		<td class="dataField">
			<spring:bind path="documentForm.file2">
				<input type="file" name="<c:out value="${status.expression}" />">
			</spring:bind>
		</td>
	    <td class="dataLabel"> Document Type :</td>
	    <td class="dataField">
	    	<spring:bind path="documentForm.documentCategoryId2">
				<select name="<c:out value="${status.expression }" />">
					<option value="-1">--- SELECT ONE ---</option>
					<c:forEach items="${documentCategories}" var="doc">
						<option value="<c:out value="${doc.documentCategoryId}" />"><c:out value="${doc.documentCategoryName}" /></option>
					
					</c:forEach>
				</select>
			</spring:bind>
	    </td>
	</tr>
	<tr>
		<td class="dataLabel"> Document 3 : </td>
		<td class="dataField">
			<spring:bind path="documentForm.file3">
				<input type="file" name="<c:out value="${status.expression}" />">
			</spring:bind>
		</td>
	    <td class="dataLabel"> Document Type :</td>
	    <td class="dataField">
	    	<spring:bind path="documentForm.documentCategoryId3">
				<select name="<c:out value="${status.expression }" />">
					<option value="-1">--- SELECT ONE ---</option>
					<c:forEach items="${documentCategories}" var="doc">
						<option value="<c:out value="${doc.documentCategoryId}" />"><c:out value="${doc.documentCategoryName}" /></option>
					
					</c:forEach>
				</select>
			</spring:bind>
	    </td>
	</tr>
	<tr>
		<td class="dataLabel"> Document 4 : </td>
		<td class="dataField">
			<spring:bind path="documentForm.file4">
				<input type="file" name="<c:out value="${status.expression}" />">
			</spring:bind>
		</td>
	    <td class="dataLabel"> Document Type :</td>
	    <td class="dataField">
	    	<spring:bind path="documentForm.documentCategoryId4">
				<select name="<c:out value="${status.expression }" />">
					<option value="-1">--- SELECT ONE ---</option>
					<c:forEach items="${documentCategories}" var="doc">
						<option value="<c:out value="${doc.documentCategoryId}" />"><c:out value="${doc.documentCategoryName}" /></option>
					
					</c:forEach>
				</select>
			</spring:bind>
	    </td>
	</tr>
	<tr>
		<td class="dataLabel"> Document 5 : </td>
		<td class="dataField">
			<spring:bind path="documentForm.file5">
				<input type="file" name="<c:out value="${status.expression}" />">
			</spring:bind>
		</td>
	    <td class="dataLabel"> Document Type :</td>
	    <td class="dataField">
	    	<spring:bind path="documentForm.documentCategoryId5">
				<select name="<c:out value="${status.expression }" />">
					<option value="-1">--- SELECT ONE ---</option>
					<c:forEach items="${documentCategories}" var="doc">
						<option value="<c:out value="${doc.documentCategoryId}" />"><c:out value="${doc.documentCategoryName}" /></option>
					
					</c:forEach>
				</select>
			</spring:bind>
	    </td>
	    
	</tr>
			
	

<tr>
		<td class="dataLabel"> </td>		
		<td class="dataField">&nbsp;
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
		document.form1.action = "document-form";
		document.form1.submit();
	}
	function cancel (){
		<c:choose>
			<c:when test="${navigation eq 'tambah'}">
				document.form1.navigation.value = "listcase";
				document.form1.caseId.value = <c:out value="${caseId}" />
			</c:when>
		</c:choose>
		document.form1.action = "document";
		document.form1.submit();
	}

				// forreign affairs end
</script>