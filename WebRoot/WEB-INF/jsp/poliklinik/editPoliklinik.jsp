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
<form action="poliklinik-form" method="POST"  name="form1" id="form_layout">
	<spring:bind path="poliklinikForm.poliklinikId">
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
				<td class="dataLabel"> Parent Poliklinik : </td>				
				<td class="dataField">
					<spring:bind path="poliklinikForm.parentId">
						<input type="hidden" id="parentId" name="<c:out value="${status.expression}" />" value="<fmt:formatNumber><c:out value="${status.value}"/></fmt:formatNumber>" maxlength="10">
					</spring:bind>
					<spring:bind path="poliklinikForm.parentName">
						<input type="text" size="35" id="parentName" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>">
					</spring:bind>
				</td>
			    <td class="dataLabel"></td>
			    <td class="dataField"></td>
			</tr>

			<tr>
				<td class="dataLabel"> Poliklinik Name : </td>
				<td class="dataField">
					<spring:bind path="poliklinikForm.poliklinikName">
						<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>">
					</spring:bind>
				</td>
			    <td class="dataLabel"></td>
			    <td class="dataField"></td>
			</tr>
			<tr>
				<td class="dataLabel"> Poliklinik Code : </td>				
				<td class="dataField">
					<spring:bind path="poliklinikForm.poliklinikCode">
						<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>">
					</spring:bind>
				</td>
			    <td class="dataLabel"></td>
			    <td class="dataField"></td>
			</tr>
			<tr>
				<td class="dataLabel"> EDC Code : </td>				
				<td class="dataField">
					<spring:bind path="poliklinikForm.poliklinikEdcCode">
						<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>">
					</spring:bind>
				</td>
			    <td class="dataLabel"></td>
			    <td class="dataField"></td>
			</tr>
	
			<tr>
				<td class="dataLabel"> Description : </td>				
			

			    <td class="dataField">
					<spring:bind path="poliklinikForm.description">
						<textarea cols="50" rows="8" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
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
		document.form1.action = "poliklinik-form";
		document.form1.submit();
	}
	function kembali (){
		document.form1.navigation.value = "back";
		document.form1.action = "poliklinik";
		document.form1.submit();
	}
	// forreign affairs
			// forreign affairs end
</script>