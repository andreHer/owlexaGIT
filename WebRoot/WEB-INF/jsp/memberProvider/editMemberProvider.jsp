



<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<link rel="stylesheet" type="text/css" href="css/jquery/autocomplete/jquery.autocomplete.css"/>
<script type="text/javascript" src="scripts/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="scripts/jquery/plugin/jquery.autocomplete.pack.js"></script>

<%
String alert = (String) request.getAttribute("alert");
if (alert != null && !alert.trim().equals("")) {%>
	<div id="warning">
		<c:out value="${alert}"></c:out>
	</div>
	<%}%>
<br />

<form action="memberprovider-form" method="POST"  name="form1" id="form_layout">


	<spring:bind path="memberProviderForm.memberProviderId">
		<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
	
		</spring:bind>
		<spring:bind path="memberProviderForm.memberId">
		<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
</spring:bind>
		
<input type="hidden" name="navigation" value="">
		
<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>
		<tr>
			<td>
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<tbody>

	<tr>
				<td class="dataLabel">  </td>
				<td class="dataField">
					
				</td>
			    <td class="dataLabel"></td>
			    <td class="dataField"></td>
			</tr>

			<tr>
				<td class="dataLabel"> Member Number : </td>
				<td class="dataField">
					<input type="text" size="35" name="memberNumber" value="<c:out value="${memberNumber}" />" width="40">
				</td>
			    <td class="dataLabel"></td>
			    <td class="dataField"></td>
			</tr>

			<tr>
				<td class="dataLabel"> Provider : </td>				
			

				<td class="dataField">
				<spring:bind path="memberProviderForm.providerId">
					<input type="hidden"  id="providerId" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" maxlength="35">				
				</spring:bind>
				<spring:bind path="memberProviderForm.providerName">
					<input type="text" size="35" id="providerName" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" maxlength="35">
				</spring:bind>
			</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
			
			<tr>
				<td class="dataLabel"> Outpatient : </td>				
			

						<td class="dataField">
			<spring:bind path="memberProviderForm.outpatient">
			
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
					<spring:bind path="memberProviderForm.inpatient">
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
					<spring:bind path="memberProviderForm.dental">
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
					<spring:bind path="memberProviderForm.maternity">
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
					<spring:bind path="memberProviderForm.optical">					
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
					<spring:bind path="memberProviderForm.ppk1">
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
					<spring:bind path="memberProviderForm.ppk2">
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
					<spring:bind path="memberProviderForm.ppk3">
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

$("#providerName").autocomplete("policyprovider?navigation=lookupjson&policyId=<c:out value="${policyId}" />&memberId=<c:out value="${memberId}" />", {
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

		document.form1.method = "POST";
		document.form1.action = "memberprovider-form";
		document.form1.submit();
	}
	function kembali (){
		document.form1.navigation.value = "back";
		document.form1.action = "memberprovider";
		document.form1.submit();
	}
	
				// forreign affairs end
</script>