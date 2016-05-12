<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<form action="clausul-form" method="POST"  name="form1" id="form_layout">
<input type="hidden" name="navigation" value="">

	<spring:bind path="clausulForm.clausulId">
		<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>

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




<tr>
								<td class="dataLabel">
									
								</td>


								<td class="dataField">
									
								</td>
								<td class="dataLabel">
								
								</td>
								<td class="dataField">
									
									
								</td>
							</tr>





			<tr>
				<td class="dataLabel"> Clausul Name : </td>				
			

			<td class="dataField">
			<spring:bind path="clausulForm.clausulName">
				<input type="text" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" size="35">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
	


			

			<tr>
				<td class="dataLabel"> Clausul Code : </td>				
			

			<td class="dataField">
			<spring:bind path="clausulForm.clausulCode">
				<input type="text" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" size="25">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
	

		
	<tr>
		<td class="dataLabel">Clausul Category : </td>		
		<td class="dataField">
		<spring:bind path="clausulForm.clausulCategoryId">
			<select name="<c:out value="${status.expression}" />">
				<c:forEach items="${clausulCategories}" var="categories">
					<option value="<c:out value="${categories.clausulCategoryId }" />" <c:if test="${status.value eq categories.clausulCategoryId }">selected</c:if>><c:out value="${categories.clausulCategoryName}" /></option>
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
				<td class="dataLabel"> Description : </td>				
			

		    <td class="dataField" colspan="3">
			<spring:bind path="clausulForm.description">
			<textarea cols="60" rows="10" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	    
	</tr>
		

	


			




<tr>
								<td class="dataLabel">
									
								</td>


								<td class="dataField">
									
								</td>
								<td class="dataLabel">
								
								</td>
								<td class="dataField">
									
									
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
		document.form1.action = "clausul-form";
		document.form1.submit();
	}
	function kembali (){
		document.form1.navigation.value = "back";
		document.form1.action = "clausul";
		document.form1.submit();
	}

				// forreign affairs end
</script>