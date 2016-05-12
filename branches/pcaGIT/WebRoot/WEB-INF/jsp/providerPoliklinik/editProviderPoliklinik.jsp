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

<form action="providerpoliklinik-form" method="POST"  name="form1" id="form_layout">


<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>



	<spring:bind path="providerPoliklinikForm.providerPoliklinikId">
		<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>

<input type="hidden" name="navigation" value="">

	<tr>
		<td class="dataLabel">Provider : </td>		
		<td class="dataField">
		<spring:bind path="providerPoliklinikForm.providerId">
		<input type="hidden" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>	
		<spring:bind path="providerPoliklinikForm.providerName">
		<input type="text" name="<c:out value="${status.expression}" />" id="providerIdId" value="<c:out value="${status.value }" />" >
		</spring:bind>

		
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
		
				
		
		
		
	<tr>
		<td class="dataLabel">Poliklinik : </td>		
		<td class="dataField">
		<spring:bind path="providerPoliklinikForm.poliklinikId">
		<input type="hidden" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>	
		<spring:bind path="providerPoliklinikForm.poliklinikName">
			<input type="text" name="<c:out value="${status.expression }" />" id="poliklinikIdId" value="<c:out value="${status.value}" />" >
		</spring:bind>

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
        <input title="Save [Alt+S]" accesskey="S" class="button" onClick="javascript:simpan()" name="Save" value=" Save " type="submit">
        <input title="Cancel [Alt+C]" accesskey="C" class="button" onClick="javascript:cancel()" name="Cancel" value=" Cancel " type="button">         
	  </td>
      <td align="right"></td>
    </tr>
  </tbody>
</table>
</form>

<script language="javascript">
	function simpan (){

		document.form1.method = "POST";
		document.form1.action = "providerpoliklinik-form";
		document.form1.submit();
	}
	function kembali (){
		document.form1.navigation.value = "back";
		document.form1.action = "providerpoliklinik";
		document.form1.submit();
	}
</script>