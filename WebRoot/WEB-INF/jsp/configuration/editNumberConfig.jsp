



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
			<td nowrap="nowrap">
				<h3>
					<c:choose>
						<c:when test="${navigation eq 'update-number-configuration'}">
							<img src="images/h3Arrow.gif" border="0">&nbsp;Update Number Configuration
						</c:when>
					</c:choose>	
				</h3>
			</td>
			<td width="100%"><img src="images/blank.gif" height="1"
				width="1">
			</td>
		</tr>
	</tbody>
</table>
<form action="configuration-form" method="POST"  name="form1" id="form_layout">
		<spring:bind path="configurationForm.clientId">
			<input type="hidden" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
		</spring:bind>
		<spring:bind path="configurationForm.configurationId">
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
			<td class="dataLabel"> Centralized Cdv Numbering : </td>
			<td class="dataField">
				<spring:bind path="configurationForm.centralizedCdvNumbering">
					<select name="<c:out value="${status.expression}" />">
						<option value="-1">--- PILIH SALAH SATU ---</option>
						<option value="1" <c:if test="${status.value eq 1 }">selected</c:if> >YES</option>
						<option value="0" <c:if test="${status.value eq 0 }">selected</c:if>>NO</option>						
					</select>	
				</spring:bind>
			</td>
	    	<td class="dataLabel"></td>
	    	<td class="dataField"></td>
		</tr>
		<tr>
			<td class="dataLabel"> Centralized Claim Numbering : </td>
			<td class="dataField">
				<spring:bind path="configurationForm.centralizedClaimNumbering">
					<select name="<c:out value="${status.expression}" />">
						<option value="-1">--- PILIH SALAH SATU ---</option>
						<option value="1" <c:if test="${status.value eq 1 }">selected</c:if> >YES</option>
						<option value="0" <c:if test="${status.value eq 0 }">selected</c:if>>NO</option>					
					</select>
				</spring:bind>
			</td>
		    <td class="dataLabel"></td>
		    <td class="dataField"></td>
		</tr>
		<tr>
			<td class="dataLabel"> Centralized Excess Numbering : </td>
			<td class="dataField">
				<spring:bind path="configurationForm.centralizedExcessNumbering">
					<select name="<c:out value="${status.expression}" />">
						<option value="-1">--- PILIH SALAH SATU ---</option>
						<option value="1" <c:if test="${status.value eq 1 }">selected</c:if> >YES</option>
						<option value="0" <c:if test="${status.value eq 0 }">selected</c:if>>NO</option>					
					</select>
				</spring:bind>
			</td>
		    <td class="dataLabel"></td>
		    <td class="dataField"></td>
		</tr>
		<tr>
			<td class="dataLabel"> Centralized Call Numbering : </td>
			<td class="dataField">
				<spring:bind path="configurationForm.centralizedCallNumbering">
					<select name="<c:out value="${status.expression}" />">
						<option value="-1">--- PILIH SALAH SATU ---</option>
						<option value="1" <c:if test="${status.value eq 1 }">selected</c:if> >YES</option>
						<option value="0" <c:if test="${status.value eq 0 }">selected</c:if>>NO</option>						
					</select>
				</spring:bind>
			</td>
		    <td class="dataLabel"></td>
		    <td class="dataField"></td>
		</tr>
		<tr>
			<td class="dataLabel"> Centralized Fund Numbering : </td>
			<td class="dataField">
				<spring:bind path="configurationForm.centralizedFundNumbering">
					<select name="<c:out value="${status.expression}" />">
						<option value="-1">--- PILIH SALAH SATU ---</option>
						<option value="1" <c:if test="${status.value eq 1 }">selected</c:if> >YES</option>
						<option value="0" <c:if test="${status.value eq 0 }">selected</c:if>>NO</option>					
					</select>
				</spring:bind>
				</td>
		    <td class="dataLabel"></td>
		    <td class="dataField"></td>
		</tr>
		<tr>
			<td class="dataLabel"> Centralized Batch Numbering : </td>				
			<td class="dataField">
				<spring:bind path="configurationForm.centralizedBatchNumbering">
					<select name="<c:out value="${status.expression}" />">
						<option value="-1">--- PILIH SALAH SATU ---</option>
						<option value="1" <c:if test="${status.value eq 1 }">selected</c:if> >YES</option>
						<option value="0" <c:if test="${status.value eq 0 }">selected</c:if>>NO</option>						
					</select>
				</spring:bind>
			</td>
		    <td class="dataLabel"></td>
		    <td class="dataField"></td>
		</tr>
		<tr>
			<td class="dataLabel"> Is Using Other Payment Number : </td>				
			<td class="dataField">
				<spring:bind path="configurationForm.isUsingOtherPaymentNumber">
					<select name="<c:out value="${status.expression}" />">
						<option value="-1">--- PILIH SALAH SATU ---</option>
						<option value="1" <c:if test="${status.value eq 1 }">selected</c:if> >YES</option>
						<option value="0" <c:if test="${status.value eq 0 }">selected</c:if>>NO</option>					
					</select>				
				</spring:bind>
			</td>
		    <td class="dataLabel"></td>
		    <td class="dataField"></td>
		</tr>				

		<tr>
			<td class="dataLabel"> Call Number Template : </td>
		    <td class="dataField">
				<spring:bind path="configurationForm.callNumberTemplate">
					<textarea rows="2" cols="40" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>				
				</spring:bind>
			</td>
		    <td class="dataLabel"></td>
		    <td class="dataField"></td>
		</tr>
		<tr>
			<td class="dataLabel"> Claim Number Template : </td>
		    <td class="dataField">
				<spring:bind path="configurationForm.claimNumberTemplate">
					<textarea rows=2 cols=40 name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>				
				</spring:bind>
			</td>
		    <td class="dataLabel"></td>
		    <td class="dataField"></td>
		</tr>
		<tr>
			<td class="dataLabel"> Case Number Template : </td>
		    <td class="dataField">
				<spring:bind path="configurationForm.caseNumberTemplate">
					<textarea rows=2 cols=40 name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>				
				</spring:bind>
			</td>
		    <td class="dataLabel"></td>
		    <td class="dataField"></td>
		</tr>
		<tr>
			<td class="dataLabel"> Payment Number Template : </td>
		    <td class="dataField">
				<spring:bind path="configurationForm.paymentNumberTemplate">
					<textarea rows=2 cols=40 name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
				</spring:bind>
			</td>
		    <td class="dataLabel"></td>
		    <td class="dataField"></td>
		</tr>
		<tr>
			<td class="dataLabel"> Batch Number Template : </td>
		    <td class="dataField">
				<spring:bind path="configurationForm.batchNumberTemplate">
					<textarea rows=2 cols=40 name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>				
				</spring:bind>
			</td>
		    <td class="dataLabel"></td>
		    <td class="dataField"></td>
		</tr>
		<tr>
			<td class="dataLabel"> Claim Rejection Number Template : </td>
		    <td class="dataField">
				<spring:bind path="configurationForm.claimRejectionNumberTemplate">
					<textarea rows=2 cols=40 name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>				
				</spring:bind>
			</td>
		    <td class="dataLabel"></td>
		    <td class="dataField"></td>
		</tr>
		<tr>
			<td class="dataLabel"> Case Rejection Number Template : </td>
			<td class="dataField">
				<spring:bind path="configurationForm.caseRejectionNumberTemplate">
					<textarea rows=2 cols=40 name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>				
				</spring:bind>
			</td>
		    <td class="dataLabel"></td>
		    <td class="dataField"></td>
		</tr>
		<tr>
			<td class="dataLabel"> Claim Pending Number Template : </td>
		    <td class="dataField">
				<spring:bind path="configurationForm.claimPendingNumberTemplate">
					<textarea rows=2 cols=40 name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>				
				</spring:bind>
			</td>
		    <td class="dataLabel"></td>
		    <td class="dataField"></td>
		</tr>
		<tr>
			<td class="dataLabel"> Excess Number Template : </td>
		    <td class="dataField">
				<spring:bind path="configurationForm.excessNumberTemplate">
					<textarea rows=2 cols=40 name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
				</spring:bind>
			</td>
		    <td class="dataLabel"></td>
		    <td class="dataField"></td>
		</tr>
		<tr>
			<td class="dataLabel"> Card Number Prefix : </td>
		    <td class="dataField">
				<spring:bind path="configurationForm.cardNumberPrefix">
					<textarea rows=1 cols=40 name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
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
		document.form1.action = "configuration-form";
		document.form1.submit();
	}
	function kembali (){
		document.form1.navigation.value = "detail";
		document.form1.action = "client";
		document.form1.submit();
	}
</script>