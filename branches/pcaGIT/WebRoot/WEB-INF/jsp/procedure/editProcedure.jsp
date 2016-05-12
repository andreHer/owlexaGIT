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


<form action="procedure-form" method="POST"  name="form1" id="form_layout">
	<input type="hidden" name="navigation" value="">
	<spring:bind path="procedureForm.procedureId">
		<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >			
	</spring:bind>
<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>
		<tr>
			<td class="dataLabel"></td>
			<td class="dataField" colspan=3></td>
		</tr>	
		<tr>
			<td>
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<tbody>
			<tr>
				<td class="dataLabel"> Procedure Name : </td>				
			

			<td class="dataField">
			<spring:bind path="procedureForm.procedureName">
				<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" >
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
				<td class="dataLabel"> Procedure Code : </td>				
			

			<td class="dataField">
			<spring:bind path="procedureForm.procedureCode">
			<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" >
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>

	</tr>
		
			<tr>
				<td class="dataLabel"> Description : </td>				
			

			<td class="dataField" colspan=3>
			<spring:bind path="procedureForm.description">
			<textarea  cols="60" rows="8" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
				

	</tr>	

		<tr>
			<td class="dataLabel"></td>
			<td class="dataField" colspan=3></td>
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
        <input title="Save [Alt+Shift+E]" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:simpan()" name="Save" value=" Simpan " type="button">
        <input title="Cancel [Alt+Shift+H]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:kembali()" name="Cancel" value=" Cancel " type="button">        		
		</td>
      <td align="right"></td>
    </tr>
  </tbody>
</table>
</form>

<script language="javascript">
	function simpan (){

		document.form1.method = "POST";
		document.form1.action = "procedure-form";
		document.form1.submit();
	}
	function kembali (){
		document.form1.navigation.value = "back";
		document.form1.action = "procedure";
		document.form1.submit();
	}
	// forreign affairs
			// forreign affairs end
</script>