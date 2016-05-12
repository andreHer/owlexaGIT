<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Upload Poliklinik</h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>

<%
String alert = (String) request.getAttribute("alert");
if (alert != null && !alert.trim().equals("")) {%>
	<div id="warning">
		<c:out value="${alert}"></c:out>
	</div>
	<%}%>

<form action="providerpoliklinik-upload" enctype="multipart/form-data" method="POST"  name="form1" id="form_layout">


<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>


<input type="hidden" name="navigation" value="upload">

		<tr>
		<td class="dataLabel">&nbsp; </td>		
		<td class="dataField">&nbsp;		
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
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
			<input type="text" name="<c:out value="${status.expression}" />" size=50 id="providerIdId" value="<c:out value="${status.value }" />" readonly="readonly" >
		</spring:bind>

		
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
		
				
		
		
		
	<tr>
		<td class="dataLabel">Upload File : </td>		
		<td class="dataField">
			<spring:bind path="providerPoliklinikForm.multipartFile">
				<input type="file" id="file" name="<c:out value="${status.expression}" />"  value="<c:out value="${status.value }" />" >
			</spring:bind>
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
	
		<tr>
		<td class="dataLabel">&nbsp; </td>		
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
        <input title="Save [Alt+S]" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:simpan()" name="Save" value=" Save " type="button">
        <input title="Cancel [Alt+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:cancel()" name="Cancel" value=" Cancel " type="button">         
	  </td>
      <td align="right"></td>
    </tr>
  </tbody>
</table>
</form>

<script language="javascript">
	function simpan (){
	
		var fileName = $("#file").val();	     
	    if (fileName == ''){
	    	window.alert("Please Specify The CSV / XLS File");
	    }
	    else {	
	
			document.form1.method = "POST";
			document.form1.action = "providerpoliklinik-upload";
			document.form1.submit();
		}
	}
	function cancel (){
		document.form1.navigation.value = "back";
		document.form1.action = "providerpoliklinik";
		document.form1.submit();
	}
</script>