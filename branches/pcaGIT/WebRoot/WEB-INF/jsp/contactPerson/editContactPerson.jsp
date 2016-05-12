<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Create Contact Person</h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>

<form action="contactperson-form" method="POST"  name="form1" id="form_layout">
    
<input type="hidden" name="navigation" value="${navigation }">
<%-- <input type="hidden" name="refId" value="${refId}">--%>

<spring:bind path="contactPersonForm.tipe">
<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
</spring:bind>
<spring:bind path="contactPersonForm.refId">
<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
</spring:bind>
<spring:bind path="contactPersonForm.previousURL">
<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
</spring:bind>

<c:if test="${contactPersonForm.tipe eq 'provider'}">
	<input type="hidden" name="providerId" value="<c:out value="${contactPersonForm.refId}" />" />
</c:if>
<c:if test="${contactPersonForm.tipe eq 'client'}">
	<input type="hidden" name="clientId" value="<c:out value="${contactPersonForm.refId}" />" />
</c:if>
<c:if test="${contactPersonForm.tipe eq 'group'}">
	<input type="hidden" name="memberGroupId" value="<c:out value="${contactPersonForm.refId}" />" />
</c:if>

<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>
		<tr>
			<td>
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<tbody>


<%
String alert = (String) request.getAttribute("alert");
if (alert != null && !alert.trim().equals("")) {%>
	<div id="warning">
		<c:out value="${alert}"></c:out>
	</div>
	<%}%>


	<spring:bind path="contactPersonForm.contactPersonId">
		<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>





			<tr>
				<td class="dataLabel">  </td>				
			

			<td class="dataField">
			
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
	
			<tr>
				<td class="dataLabel"> Name : </td>				
			

			<td class="dataField">
			<spring:bind path="contactPersonForm.name">
                            <input type="text" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" size="40" />
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
	

		
				
		
		

			<tr>
				<td class="dataLabel"> Telephone : </td>				
			

			<td class="dataField">
			<spring:bind path="contactPersonForm.telephone">
			                            <input type="text" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" size="40" />
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
	

		
				
		
		

			<tr>
				<td class="dataLabel"> Handphone : </td>				
			

			<td class="dataField">
			<spring:bind path="contactPersonForm.handphone">
			                            <input type="text" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" size="40" />
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
	

		
				
		
		

			<tr>
				<td class="dataLabel"> Email : </td>				
			

			<td class="dataField">
			<spring:bind path="contactPersonForm.email">
			                            <input type="text" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" size="40" />
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
	

		
				
		
		

			<tr>
				<td class="dataLabel"> Department : </td>				
			

			<td class="dataField">
			<spring:bind path="contactPersonForm.department">
			                            <input type="text" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" size="40" />
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
	

		
				
		
		

			<tr>
				<td class="dataLabel"> Job Position : </td>				
			

			<td class="dataField">
			<spring:bind path="contactPersonForm.jobPosition">
			<input type="text" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" size="40" />
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
	<c:if test="${tipe eq 'client' or param.tipe eq 'client' }">
		<tr>
			<td class="dataLabel">Payment Officer:</td>
			<td class="dataField">
				<spring:bind path="contactPersonForm.paymentOfficer">
					<select name="<c:out value="${status.expression}" />">
						<option value="0" <c:if test="${status.value eq 0 }">selected</c:if>>TIDAK</option>
						<option value="1" <c:if test="${status.value eq 1 }">selected</c:if>>YA</option>
					</select>		
				</spring:bind>	
			</td>
		</tr>
	</c:if>
	

		
				
		
		<tr>
				<td class="dataLabel">  </td>				
			

			<td class="dataField">
			
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
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
        <input title="Save [Alt+S]" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:simpan()" name="Save" value=" Save " type="submit">
        <input title="Cancel [Alt+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:cancel()" name="Cancel" value=" Cancel " type="button">         
	  </td>
      <td align="right"></td>
    </tr>
  </tbody>
</table>
</form>

<script language="javascript">
	function simpan (){

		document.form1.method = "POST";
		document.form1.action = "contactperson-form";
		<c:choose>
			<c:when test="${contactPersonForm.tipe eq 'provider'}">
				document.form1.providerId.value = <c:out value="${providerId}" />;
			</c:when>
			<c:when test="${contactPersonForm.tipe eq 'client'}">
				document.form1.clientId.value = <c:out value="${clientId}" />;
			</c:when>
			<c:when test="${contactPersonForm.tipe eq 'group'}">
				document.form1.memberGroupId.value = <c:out value="${memberGroupId}" />;
			</c:when>
		</c:choose>
		document.form1.submit();	
	}
	function cancel(){
		<c:choose>
			<c:when test="${contactPersonForm.tipe eq 'provider'}">
				<c:choose>
					<c:when test="${navigation eq 'listprovider'}">
						document.form1.navigation.value = "listprovider";	
						document.form1.action = "contactperson";
						document.form1.providerId.value = <c:out value="${providerId}" />
					</c:when>
				</c:choose>
			</c:when>
			<c:when test="${contactPersonForm.tipe eq 'client'}">
				<c:choose>
					<c:when test="${navigation eq 'listclient'}">
						document.form1.navigation.value = "listclient";	
						document.form1.action = "contactperson";
						document.form1.clientId.value = <c:out value="${clientId}" />
					</c:when>
				</c:choose>			
			</c:when>
			<c:when test="${contactPersonForm.tipe eq 'group'}">
				<c:choose>
					<c:when test="${navigation eq 'listgroup'}">
						document.form1.navigation.value = "listgroup";	
						document.form1.action = "contactperson";
						document.form1.memberGroupId.value = <c:out value="${memberGroupId}" />
					</c:when>
				</c:choose>
			</c:when>

		</c:choose>
		document.form1.submit();
	}	

</script>
