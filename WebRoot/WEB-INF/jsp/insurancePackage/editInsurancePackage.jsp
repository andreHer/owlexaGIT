<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<form action="insurancepackage-form" method="POST"  name="form1" id="form_layout">
	<spring:bind path="insurancePackageForm.packageId">
		<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>

<input type="hidden" name="navigation" value="">


<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td style="padding-bottom: 2px;">
	    <input type="hidden" name="notyet" value="">
        <input title="Save [Alt+Shift+S]" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:simpan()" name="Save" value=" Save " type="submit">
        <input title="Cancel [Alt+Shift+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:cancel()" name="Cancel" value=" Cancel " type="button">         
	  </td>
      <td align="right"></td>
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
				<td class="dataLabel"> Package Name : </td>				
			

			<td class="dataField">
			<spring:bind path="insurancePackageForm.packageName">
<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />">			
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
<td class="dataLabel">Client : </td>		
		<td class="dataField">
		<spring:bind path="insurancePackageForm.clientId">
			<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}"/>" id="clientId" />
		</spring:bind>
		<spring:bind path="insurancePackageForm.clientName">
		<input type="text" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >&nbsp;<input type="button" name="PILIH" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" value=" Lookup " onClick="javascript:lookupClient()">
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>	
		
		</td>
	</tr>
	


			

			<tr>
				<td class="dataLabel"> Package Code : </td>				
			

			<td class="dataField">
			<spring:bind path="insurancePackageForm.packageCode">
			<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	    <td class="dataLabel">Product Type : </td>		
		<td class="dataField">
		<spring:bind path="insurancePackageForm.packageType">
			<select name="<c:out value="${status.expression}"/>">
				<option value="-1"> -- PILIH TIPE PAKET -- </option>
				<c:forEach items="${productTypes}" var="types">
					<option value="<c:out value="${types.productTypeId}" />" <c:if test="${types.productTypeId eq status.value}" >selected</c:if> ><c:out value="${types.productTypeName}" /></option>
				</c:forEach>	
			</select>
			
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>	
		
		</td>
	</tr>
	


			

			<tr>
				<td class="dataLabel"> Annual Benefit Value : </td>				
			
			<td class="dataField">
			<spring:bind path="insurancePackageForm.annualBenefitValue">
					<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<fmt:formatNumber><c:out value="${status.value}" /></fmt:formatNumber>" maxlength="22">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>



			

			<tr>
				<td class="dataLabel"> Package Description : </td>				
			

		    <td class="dataField" colspan=3>
			<spring:bind path="insurancePackageForm.packageDescription">
			<textarea class="inputbox" name="<c:out value="${status.expression}"/>" cols="60" rows="8"><c:out value="${status.value}" /></textarea>
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
		document.form1.action = "insurancepackage-form";
		document.form1.submit();
	}
	function kembali (){
		document.form1.navigation.value = "back";
		document.form1.action = "insurancepackage";
		document.form1.submit();
	}
	// forreign affairs
		function lookupClient (){
		window.open ("client?navigation=lookup&url=insurancepackage-form","Search","width=700, height=550, menubar=no, status=no, toolbar=no, scrollbars=yes, resizable=yes,screenX=200,screenY=100");
	}
	function setClient (idx, cname){
		document.getElementById("clientId").value = idx;
		document.getElementById("clientName").value = cname;
	}
			function lookupProductType (){
		window.open ("producttype?navigation=lookup&url=insurancepackage-form","Search","width=700, height=550, menubar=no, status=no, toolbar=no, scrollbars=yes, resizable=yes,screenX=200,screenY=100");
	}
	function setProductType (idx){
		document.form1.method = "POST";
		document.form1.notyet.value="true";
		document.form1.action = "insurancepackage-form";
		//document.form1.insurancePackageForm.productTypeId.value=idx;
		document.getElementById("productTypeId").value = idx;
		document.form1.submit();
		}
				// forreign affairs end
</script>