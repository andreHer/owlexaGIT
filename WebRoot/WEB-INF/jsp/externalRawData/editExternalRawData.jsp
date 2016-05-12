	
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
<form action="externalrawdata-form" method="POST"  name="form1" id="form_layout">
	<input type="hidden" name="navigation" value="">
	<spring:bind path="externalRawDataForm.id">
		<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
	</spring:bind>

<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>
		<tr>
			<td class="dataLabel"> &nbsp;</td>
		    <td class="dataField"> &nbsp;</td>
		   	<td class="dataLabel"></td>
	    	<td class="dataField"></td>
		</tr>
		<tr>
			<td class="dataLabel"> Raw Data : </td>
		    <td class="dataField" colspan=3>
				<spring:bind path="externalRawDataForm.rawData">
					<textarea rows=15 cols=100 name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
				</spring:bind>
			</td>
		</tr>
		<tr>
			<td class="dataLabel"> &nbsp;</td>
		    <td class="dataField"> &nbsp;</td>
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
        <input title="Save [Alt+S]" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:simpan()" name="Save" value=" Save " type="button">
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
		document.form1.action = "externalrawdata-form";
		document.form1.submit();
	}
	function kembali (){
		document.form1.navigation.value = "back";
		document.form1.action = "externalrawdata";
		document.form1.submit();
	}
</script>