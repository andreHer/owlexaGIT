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
						<c:when test="${navigation eq 'update-configuration'}">
							<img src="images/h3Arrow.gif" border="0">&nbsp;Update Configuration
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
			<td class="dataLabel"> Is Active : </td>
			<td class="dataField">
				<spring:bind path="configurationForm.isActive">
					<select name="<c:out value="${status.expression}" />">
						<option value="-1">--- PILIH SALAH SATU ---</option>
						<option value="1" <c:if test="${status.value eq 1 }">selected</c:if> >YES</option>
						<option value="0" <c:if test="${status.value eq 0 }">selected</c:if>>NO</option>						
					</select>
				</spring:bind>
			</td>
			<td class="dataLabel"> Is Using Branch : </td>				
			<td class="dataField">
				<spring:bind path="configurationForm.isUsingBranch">
					<select name="<c:out value="${status.expression}" />">
						<option value="-1">--- PILIH SALAH SATU ---</option>
						<option value="1" <c:if test="${status.value eq 1 }">selected</c:if> >YES</option>
						<option value="0" <c:if test="${status.value eq 0 }">selected</c:if>>NO</option>					
					</select>
				</spring:bind>
			</td>
		</tr>
		<tr>
			<td class="dataLabel"> Bank Account : </td>				
			<td class="dataField">
				<spring:bind path="configurationForm.bankAccount">
					<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" />	
				</spring:bind>
			</td>
			<td class="dataLabel"> Bank Name : </td>				
			<td class="dataField">
				<spring:bind path="configurationForm.bankName">
					<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" />
				</spring:bind>
			</td>
		</tr>
		<tr>
			<td class="dataLabel"> Bank Account Name : </td>				
			<td class="dataField">
				<spring:bind path="configurationForm.bankAccountName">
					<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" />	
				</spring:bind>
			</td>
			<td class="dataLabel"> Bank Branch : </td>				
			<td class="dataField">
				<spring:bind path="configurationForm.bankBranch">
					<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" />	
				</spring:bind>
			</td>
		</tr>
		<tr>
			<td class="dataLabel">&nbsp;</td>
			<td class="dataField"></td>
		    <td class="dataLabel"></td>
		    <td class="dataField"></td>
		</tr>
		<tr>
			<td class="dataLabel"> General Manager : </td>				
			<td class="dataField">
				<spring:bind path="configurationForm.generalManager">
					<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" />	
				</spring:bind>
			</td>
			<td class="dataLabel"> Director : </td>				
			<td class="dataField">
				<spring:bind path="configurationForm.director">
					<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" />	
				</spring:bind>
			</td>
		</tr>
	

		
				
		
		

			<tr>
				<td class="dataLabel"> General Manager Label : </td>				
			

			<td class="dataField">
			<spring:bind path="configurationForm.generalManagerLabel">
				<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" />				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
				<td class="dataLabel"> Director Label : </td>				
			

			<td class="dataField">
			<spring:bind path="configurationForm.directorLabel">
				<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" />				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>

	</tr>

			<tr>
				<td class="dataLabel">&nbsp;</td>
				<td class="dataField"></td>
			    <td class="dataLabel"></td>
			    <td class="dataField"></td>
			</tr>
	

	<tr>
		<td class="dataLabel"> Company Name : </td>				
		<td class="dataField">
			<spring:bind path="configurationForm.companyName">
				<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" />	
			</spring:bind>
		</td>
		<td class="dataLabel"> Finance Manager : </td>				
		<td class="dataField">
			<spring:bind path="configurationForm.financeManager">
				<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" />	
			</spring:bind>
		</td>
	</tr>
	<tr>
		<td class="dataLabel"> Company Code : </td>				
		<td class="dataField">
			<spring:bind path="configurationForm.companyCode">
				<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" />	
			</spring:bind>
		</td>
	    <td class="dataLabel"> Finance Manager Label : </td>				
		<td class="dataField">
			<spring:bind path="configurationForm.financeManagerLabel">
				<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" />	
			</spring:bind>
		</td>
	</tr>
	
			<tr>
				<td class="dataLabel"> Company : </td>				
			

			<td class="dataField">
			<spring:bind path="configurationForm.company">
				<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" />				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
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
	<tr>
		<td class="dataLabel"> Excess Expire Day : </td>				
		<td class="dataField">
			<spring:bind path="configurationForm.excessExpireDay">
				<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<fmt:formatNumber><c:out value="${status.value}"/></fmt:formatNumber>" maxlength="11">			
			</spring:bind>
		</td>
		<td class="dataLabel"> Excess Reminder Day : </td>				
		<td class="dataField">
			<spring:bind path="configurationForm.excessReminderDay">
				<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<fmt:formatNumber><c:out value="${status.value}"/></fmt:formatNumber>" maxlength="5">
			</spring:bind>
		</td>
	</tr>
	<tr>
		<td class="dataLabel"> Theme : </td>				
		<td class="dataField">
			<spring:bind path="configurationForm.theme">
				<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" />	
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
	<tr>
		<td class="dataLabel"> Report Storage Path : </td>				
	    <td class="dataField">
			<spring:bind path="configurationForm.reportStoragePath">
				<textarea rows=2 cols=40 name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
			</spring:bind>
		</td>
		<td class="dataLabel"> Benefit Term Condition Path : </td>
		<td class="dataField">
			<spring:bind path="configurationForm.benefitTermConditionPath">
				<textarea rows=2 cols=40 name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
			</spring:bind>
		</td>
	</tr>
	<tr>
		<td class="dataLabel"> Member Storage Path : </td>				
	    <td class="dataField">
			<spring:bind path="configurationForm.memberStoragePath">
				<textarea rows=2 cols=40 name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
			</spring:bind>
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
	<tr>
		<td class="dataLabel"> Upload Base Path : </td>				
	    <td class="dataField">
			<spring:bind path="configurationForm.uploadStoragePath">
				<textarea rows=2 cols=40 name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
			</spring:bind>
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
		
	<tr>
		<td class="dataLabel">Template Member</td>
		<td class="dataField">
			<spring:bind path="configurationForm.uploadMemberTemplateId">
				<select name="<c:out value="${status.expression}" />">
					<c:forEach items="${templateList}" var="template">
						<option value="<c:out value="${template.id }" />" <c:if test="${template.id eq status.value }">selected</c:if>> <c:out value="${template.templateName}" /></option>
					</c:forEach>
				</select>
			</spring:bind>
		</td>
	    <td class="dataLabel">Template Provider</td>
	    <td class="dataField">
	    	<spring:bind path="configurationForm.uploadProviderTemplateId">
				<select name="<c:out value="${status.expression}" />">
					<c:forEach items="${templateList}" var="template">
						<option value="<c:out value="${template.id }" />" <c:if test="${template.id eq status.value }">selected</c:if>> <c:out value="${template.templateName}" /></option>
					</c:forEach>
				</select>
			</spring:bind>
	    </td>
	</tr>
	<tr>
		<td class="dataLabel">&nbsp;</td>
		<td class="dataField"></td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
	<tr>
		<td class="dataLabel">&nbsp;</td>
		<td class="dataField"></td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
		<tr>
			<td class="dataLabel"> Company Address : </td>
			<td class="dataField" colspan="3">
				<spring:bind path="configurationForm.companyAddress">
				<textarea  cols="60" rows="8" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
					<div id="fredcaption">
						<c:out value="${status.errorMessage}" />
					</div>
				</spring:bind>
			</td>
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
		document.form1.action = "configuration-form";
		document.form1.submit();
	}
	function kembali (){
		document.form1.navigation.value = "back";
		document.form1.action = "configuration";
		document.form1.submit();
	}
	// forreign affairs
		function lookupClient (){
		window.open ("client?navigation=lookup&url=configuration-form","Search","width=700, height=550, menubar=no, status=no, toolbar=no, scrollbars=yes, resizable=yes,screenX=200,screenY=100");
	}
	function setClient (idx,val){
		document.form1.method = "POST";
		document.form1.notyet.value="true";
		document.form1.action = "configuration-form";
		//document.form1.configurationForm.clientId.value=idx;
		document.getElementById("clientId").value = idx;
		document.getElementById("clientIdId").value = val;
		//document.form1.submit();
		}
				// forreign affairs end
</script>