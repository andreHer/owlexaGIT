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


<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Upload Procedure</h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>

<form action="providerprocedure-upload" method="POST" enctype="multipart/form-data"  name="form1" id="form_layout">
	<input type="hidden" name="navigation" value="upload">

	<spring:bind path="providerProcedureForm.providerProcedureId">
		<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >	
	</spring:bind>
<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>
		
			<tr>
				<td class="dataLabel">
					&nbsp;
				</td>


				<td class="dataField">
					
				</td>
				<td class="dataLabel">
				
				</td>
				<td class="dataField">
					
					
				</td>
			</tr>
		<tr>
			<td class="dataLabel"> Provider : </td>
			<td class="dataField">
					<spring:bind path="providerProcedureForm.providerId">
							<input type="hidden" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>">					
					</spring:bind>
					<spring:bind path="providerProcedureForm.providerName">
							<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" readonly="readonly">					
					</spring:bind>
				</td>
		    <td class="dataLabel"></td>
		    <td class="dataField"></td>
		</tr>
				

		<tr>
			<td class="dataLabel"> Upload File : </td>
			<td class="dataField">
				<spring:bind path="providerProcedureForm.procedureFile">
					<input type="file" id="file" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" >
					<div id="fredcaption">
						<c:out value="${status.errorMessage}" />
					</div>
				</spring:bind>
			</td>
		    <td class="dataLabel"></td>
		    <td class="dataField"></td>
		</tr>
		
			<tr>
				<td class="dataLabel">
					&nbsp;
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
		var fileName = $("#file").val();	     
	    if (fileName == ''){
	    	window.alert("Please Specify The CSV / XLS File");
	    }
	    else {	
			document.form1.method = "POST";
			document.form1.action = "providerprocedure-upload";
			document.form1.submit();
		}
	}
	function kembali (){
		document.form1.navigation.value = "listprovider";
		document.form1.action = "providerprocedure";
		document.form1.providerId.value = <c:out value="${providerId}" />
		document.form1.submit();
	}
	// forreign affairs
			// forreign affairs end
</script>