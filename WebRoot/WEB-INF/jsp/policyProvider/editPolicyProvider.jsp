
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<link rel="stylesheet" type="text/css" href="css/jquery/autocomplete/jquery.autocomplete.css"/>
<script type="text/javascript" src="scripts/jquery/jquery-1.4.4.min.js"></script>	
<script type="text/javascript" src="scripts/jquery/plugin/jquery.autocomplete.pack.js"></script>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Add Provider</h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>

<form action="policyprovider-form" method="POST"  name="form1" id="form_layout">

<input type="hidden" name="navigation" value="<c:out value="${navigation}" />" >
<input type="hidden" name="policyId" value="<c:out value="${policyId}" />" >

<spring:bind path="policyProviderForm.id">
		<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
</spring:bind>
<spring:bind path="policyProviderForm.policyId">
		<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
</spring:bind>

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


		
			<tr>
				<td class="dataLabel">  </td>
				<td class="dataField">
					
				</td>
			    <td class="dataLabel"></td>
			    <td class="dataField"></td>
			</tr>

			<tr>
				<td class="dataLabel"> Policy Number : </td>
				<td class="dataField">
					<input type="text" size="35" name="policyNumber" value="<c:out value="${policyNumber}" />" width="40">
				</td>
			    <td class="dataLabel"></td>
			    <td class="dataField"></td>
			</tr>

			<tr>
				<td class="dataLabel"> Provider : </td>				
			

				<td class="dataField">
				<spring:bind path="policyProviderForm.providerId">
					<input type="hidden"  id="providerId" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" maxlength="35">				
				</spring:bind>
				<spring:bind path="policyProviderForm.providerName">
					<input type="text" size="35" id="providerName" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" maxlength="35">
				</spring:bind>
			</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
			

			<tr>
				<td class="dataLabel"> Service Type : </td>				
			

						<td class="dataField">
			<spring:bind path="policyProviderForm.serviceType">		
				 	<select name="<c:out value="${status.expression}" />">
				 		<option value="">-- SELECT ONE --</option>
				 		<option value="0" <c:if test="${status.value eq 0 }">selected</c:if>>MANAGED CARE - FEE FOR SERVICE</option>
				 		<option value="1" <c:if test="${status.value eq 1 }">selected</c:if>>MANAGED CARE - CAPITATION</option>
				 		<option value="2" <c:if test="${status.value eq 2 }">selected</c:if>>INDEMNITY</option>
				 	</select>
				
			</spring:bind>
			</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
	<tr>
				<td class="dataLabel"> Total Member : </td>				
			

						<td class="dataField">
			<spring:bind path="policyProviderForm.totalMember">		
				 		<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" >
				 	
				
			</spring:bind>
			</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
	<tr>
				<td class="dataLabel"> Capitation Fee : </td>				
			

						<td class="dataField">
			<spring:bind path="policyProviderForm.capitationFee">		
				 		<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" >
				 	
				
			</spring:bind>
			</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
			<tr>
				<td class="dataLabel"> Outpatient : </td>				
			

						<td class="dataField">
			<spring:bind path="policyProviderForm.outpatient">
			
				<input type="radio" name="<c:out value="${status.expression}" />" value="1" <c:if test="${status.value eq 1 }">checked</c:if> > <font color="#000;">Ya</font> &nbsp;&nbsp;&nbsp;
							<input type="radio" name="<c:out value="${status.expression}" />" value="0" <c:if test="${status.value eq 0 }">checked</c:if> > <font color="#000;">Tidak</font> 
				
			</spring:bind>
			</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
			

		
				
		
		

			<tr>
				<td class="dataLabel"> Inpatient : </td>
				<td class="dataField">
					<spring:bind path="policyProviderForm.inpatient">
						<input type="radio" name="<c:out value="${status.expression}" />" value="1" <c:if test="${status.value eq 1 }">checked</c:if> > <font color="#000;">Ya</font> &nbsp;&nbsp;&nbsp;
							<input type="radio" name="<c:out value="${status.expression}" />" value="0" <c:if test="${status.value eq 0 }">checked</c:if> > <font color="#000;">Tidak</font> 
					</spring:bind>
				</td>
			    <td class="dataLabel"></td>
			    <td class="dataField"></td>
			</tr>
			
			<tr>
				<td class="dataLabel"> Dental : </td>
				<td class="dataField">
					<spring:bind path="policyProviderForm.dental">
						<input type="radio" name="<c:out value="${status.expression}" />" value="1" <c:if test="${status.value eq 1 }">checked</c:if> > <font color="#000;">Ya</font> &nbsp;&nbsp;&nbsp;
							<input type="radio" name="<c:out value="${status.expression}" />" value="0" <c:if test="${status.value eq 0 }">checked</c:if> > <font color="#000;">Tidak</font> 
					</spring:bind>
				</td>
			    <td class="dataLabel"></td>
			    <td class="dataField"></td>
	</tr>
			

		
				
		
		

			<tr>
				<td class="dataLabel"> Maternity : </td>
				<td class="dataField">
					<spring:bind path="policyProviderForm.maternity">
						<input type="radio" name="<c:out value="${status.expression}" />" value="1" <c:if test="${status.value eq 1 }">checked</c:if> > <font color="#000;">Ya</font> &nbsp;&nbsp;&nbsp;
							<input type="radio" name="<c:out value="${status.expression}" />" value="0" <c:if test="${status.value eq 0 }">checked</c:if> > <font color="#000;">Tidak</font> 
					</spring:bind>
				</td>
			    <td class="dataLabel"></td>
			    <td class="dataField"></td>
			</tr>
			

		
				
		
		

			<tr>
				<td class="dataLabel"> Optical : </td>				
				<td class="dataField">
					<spring:bind path="policyProviderForm.optical">					
						<input type="radio" name="<c:out value="${status.expression}" />" value="1" <c:if test="${status.value eq 1 }">checked</c:if> > <font color="#000;">Ya</font> &nbsp;&nbsp;&nbsp;
							<input type="radio" name="<c:out value="${status.expression}" />" value="0" <c:if test="${status.value eq 0 }">checked</c:if> > <font color="#000;">Tidak</font> 						
					</spring:bind>
				</td>
			    <td class="dataLabel"></td>
			    <td class="dataField"></td>
			</tr>
			
			<tr>
				<td class="dataLabel"> Ppk1 : </td>	
				<td class="dataField">
					<spring:bind path="policyProviderForm.ppk1">
							<input type="radio" name="<c:out value="${status.expression}" />" value="1" <c:if test="${status.value eq 1 }">checked</c:if> > <font color="#000;">Ya</font> &nbsp;&nbsp;&nbsp;
							<input type="radio" name="<c:out value="${status.expression}" />" value="0" <c:if test="${status.value eq 0 }">checked</c:if> > <font color="#000;">Tidak</font> 
						
					</spring:bind>
				</td>
			    <td class="dataLabel"></td>
			    <td class="dataField"></td>
			</tr>
			

			<tr>
				<td class="dataLabel"> Ppk2 : </td>				
			

				<td class="dataField">
					<spring:bind path="policyProviderForm.ppk2">
						<input type="radio" name="<c:out value="${status.expression}" />" value="1" <c:if test="${status.value eq 1 }">checked</c:if> > <font color="#000;">Ya</font> &nbsp;&nbsp;&nbsp;
							<input type="radio" name="<c:out value="${status.expression}" />" value="0" <c:if test="${status.value eq 0 }">checked</c:if> > <font color="#000;">Tidak</font> 
					</spring:bind>
				</td>
			    <td class="dataLabel"></td>
			    <td class="dataField"></td>
			</tr>
			
			<tr>
				<td class="dataLabel"> Ppk3 : </td>				
				<td class="dataField">
					<spring:bind path="policyProviderForm.ppk3">
							<input type="radio" name="<c:out value="${status.expression}" />" value="1" <c:if test="${status.value eq 1 }">checked</c:if> > <font color="#000;">Ya</font> &nbsp;&nbsp;&nbsp;
							<input type="radio" name="<c:out value="${status.expression}" />" value="0" <c:if test="${status.value eq 0 }">checked</c:if> > <font color="#000;">Tidak</font> 
					</spring:bind>
				</td>
			    <td class="dataLabel"></td>
			    <td class="dataField"></td>
			</tr>
			

		
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
        <input title="Cancel [Alt+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:cancel()" name="Cancel" value=" Cancel " type="submit">         
	  </td>
      <td align="right"></td>
    </tr>
  </tbody>
</table>
</form>

<script language="javascript">
	$("#providerName").autocomplete("provider?navigation=lookupjsonpolicy&policyId=<c:out value="${policyId}" />", {
        max: 7,
        dataType: "json",
        parse: function(data) {
            return $.map(data, function(row) {
                return {
                    data: row,
                    value: row.name,
                    result: row.name
                }
            });
        },
        formatItem: function(row) {
            return  "<font color='#000' style='align: left;' >"+ row.name +"</font>" ;
        }
    }).bind("result", function(data, value){
        $(this).parents("dd").find(".error_message").hide();
        $("#providerId").val (value.id);
    });
	function simpan (){
		<c:choose>
			<c:when test="${navigation eq 'listpolicy'}">
				document.form1.navigation.value = "listpolicy";
				document.form1.policyId.value = <c:out value="${policyId}" />
				document.form1.action = "policyprovider-form";
			</c:when>
		</c:choose>
		document.form1.method = "POST";
		document.form1.submit();
	}
	function cancel (){
		<c:choose>
			<c:when test="${navigation eq 'listpolicy'}">
				document.form1.navigation.value = "listpolicy";
				document.form1.policyId.value = <c:out value="${policyId}" />
				document.form1.action = "policyprovider";
			</c:when>
		</c:choose>
		document.form1.submit();
	}
	// forreign affairs
			// forreign affairs end
</script>