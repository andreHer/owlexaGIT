



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
<form action="policyproduct-form" method="POST"  name="form1" id="form_layout">
	<input type="hidden" name="navigation" value="">

	<spring:bind path="policyProductForm.policyProductId">
		<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
	</spring:bind>

<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>
		<tr>
			<td class="dataLabel">&nbsp;</td>		
			<td class="dataField">&nbsp;</td>
		    <td class="dataLabel">&nbsp;</td>
		    <td class="dataField">&nbsp;</td>
		</tr>
	
		<tr>
			<td class="dataLabel">Policy Number : </td>		
			<td class="dataField">
				
				<input type="hidden" name="clientId" id="clientId" value="<c:out value="${clientId}" />" >
				
				<spring:bind path="policyProductForm.policyId">
					<input type="hidden" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
				</spring:bind>
				<spring:bind path="policyProductForm.policyNumber">
					<input type="text" size="35" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
				</spring:bind>
			</td>
		    <td class="dataLabel"></td>
		    <td class="dataField"></td>
		</tr>
	
			
		<tr>
			<td class="dataLabel">Product Code : </td>		
			<td class="dataField">
				<spring:bind path="policyProductForm.productId">
					<input type="hidden" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="11">			
				</spring:bind>
				<spring:bind path="policyProductForm.productCode">
					<input type="text" size="35" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="11">			
				</spring:bind>
			</td>
		    <td class="dataLabel"></td>
		    <td class="dataField"></td>
		</tr>
		<tr>
			<td class="dataLabel">Relationship : </td>		
			<td class="dataField">
				<spring:bind path="policyProductForm.relationshipId">
					<select name="<c:out value="${status.expression}" />">
						<option value="">--- PILIH SALAH SATU ---</option>
						<option value="3" <c:if test="${status.value eq 3 }">selected</c:if>>EMPLOYEE</option>
						<option value="1" <c:if test="${status.value eq 1 }">selected</c:if>>SPOUSE</option>
						<option value="2" <c:if test="${status.value eq 2 }">selected</c:if>>CHILDREN</option>
					</select>			
				</spring:bind>
			</td>
		    <td class="dataLabel"></td>
		    <td class="dataField"></td>
		</tr>
		<tr>
			<td class="dataLabel">Sex : </td>		
			<td class="dataField">
				<spring:bind path="policyProductForm.sex">
					<select name="<c:out value="${status.expression}" />">
						<option value="">--- PILIH SALAH SATU ---</option>
						<option value="1" <c:if test="${status.value eq 1 }">selected</c:if>>FEMALE</option>
						<option value="2" <c:if test="${status.value eq 2 }">selected</c:if>>MALE</option>
					</select>			
				</spring:bind>
			</td>
		    <td class="dataLabel"></td>
		    <td class="dataField"></td>
		</tr>
		<tr>
			<td class="dataLabel">Price : </td>		
			<td class="dataField">
				<spring:bind path="policyProductForm.price">
					<input type="text" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >					
				</spring:bind>
			</td>
		    <td class="dataLabel"></td>
		    <td class="dataField"></td>
		</tr>
		<tr>
			<td class="dataLabel">&nbsp;</td>		
			<td class="dataField">&nbsp;</td>
		    <td class="dataLabel">&nbsp;</td>
		    <td class="dataField">&nbsp;</td>
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

	$("#productCode").autocomplete("product?navigation=lookupjson", {
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
            return "<font color='#000' >"+ row.name +"</font>" ;
        },
        extraParams: {       		
       		clientId: function() { return $("#clientId").val(); }
   		}
    }).bind("result", function(data, value){
        $(this).parents("dd").find(".error_message").hide();
        $("#productId").val(value.id);
    });
	function simpan (){

		document.form1.method = "POST";
		document.form1.action = "policyproduct-form";
		document.form1.submit();
	}
	function kembali (){
		document.form1.navigation.value = "back";
		document.form1.action = "policyproduct";
		document.form1.submit();
	}
	
</script>