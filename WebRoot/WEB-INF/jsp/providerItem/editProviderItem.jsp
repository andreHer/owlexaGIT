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
<form action="provideritem-form" method="POST"  name="form1" id="form_layout">

<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>

	
			<tr>
				<td class="dataLabel">  </td>				
			
			<td class="dataField">&nbsp;
			</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
		


	<spring:bind path="providerItemForm.providerItemId">
		<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>

<input type="hidden" name="navigation" value="">



		<tr>
		<td class="dataLabel">Provider : </td>		
		<td class="dataField">
			<spring:bind path="providerItemForm.providerId">
				<input type="hidden" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="11">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			<spring:bind path="providerItemForm.providerName">
				<input type="text" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" size="50">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>	
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>



			<tr>
				<td class="dataLabel"> Item : </td>				
			

						<td class="dataField">
			<spring:bind path="providerItemForm.itemId">
				<input type="hidden"  name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			<spring:bind path="providerItemForm.itemName">
				<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
			


			

			
		<tr>
				<td class="dataLabel"> Super VIP : </td>				
			
			<td class="dataField">
			<spring:bind path="providerItemForm.superVIP">
					<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="22">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
	<tr>
				<td class="dataLabel"> VIP : </td>				
			
			<td class="dataField">
			<spring:bind path="providerItemForm.VIP">
					<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="22">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
	<tr>
				<td class="dataLabel"> Kelas 1 : </td>				
			
			<td class="dataField">
			<spring:bind path="providerItemForm.kelas1">
					<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="22">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
	<tr>
				<td class="dataLabel"> Kelas 2 : </td>				
			
			<td class="dataField">
			<spring:bind path="providerItemForm.kelas2">
					<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="22">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
	<tr>
				<td class="dataLabel"> Kelas 3: </td>				
			
			<td class="dataField">
			<spring:bind path="providerItemForm.kelas3">
					<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="22">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
		<tr>
				<td class="dataLabel"> Rawat Jalan: </td>				
			
			<td class="dataField">
			<spring:bind path="providerItemForm.rawatJalan">
					<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="22">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
	
			<tr>
				<td class="dataLabel">  </td>				
			
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
        <input title="Cancel [Alt+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:kembali()" name="Cancel" value=" Cancel " type="button">         
	  </td>
      <td align="right"></td>
    </tr>
  </tbody>
</table>
</form>

<script language="javascript">
	function simpan (){

		document.form1.method = "POST";
		document.form1.action = "provideritem-form";
		document.form1.submit();
	}
	function kembali (){
		document.form1.navigation.value = "list";
		document.form1.action = "provideritem";
		document.form1.submit();
	}

</script>