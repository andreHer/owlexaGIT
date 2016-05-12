

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<link rel="stylesheet" type="text/css" href="css/autocomplete.css">
<link rel="stylesheet" type="text/css" href="css/button.css">

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
<form action="productlayerlimit-form" method="POST"  name="form1" id="form_layout">
			<spring:bind path="productLayerLimitForm.productLayerLimitId">
			<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
		</spring:bind>

		<input type="hidden" name="navigation" value="">
<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>

		<tr>
			<td class="dataLabel">&nbsp;</td>
			<td class="dataField">&nbsp;</td>
		    <td class="dataLabel"></td>
		    <td class="dataField"></td>
		</tr>

		<tr>
		<td class="dataLabel">Product Name : </td>		
		<td class="dataField">
		<spring:bind path="productLayerLimitForm.productId">
			<input type="hidden" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >			
		</spring:bind>	
		<spring:bind path="productLayerLimitForm.productName">
			<input type="text" name="<c:out value="${status.expression}"/>" id="productIdId" value="<c:out value="${status.value }" />" size=35>
			</spring:bind>

		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
			<tr>
		<td class="dataLabel">Product Type : </td>		
		<td class="dataField">
		<spring:bind path="productLayerLimitForm.productTypeId">
			<select name="<c:out value="${status.expression}" />">
				<c:forEach items="${productTypeList}" var="productType">
					<option value="<c:out value="${productType.productTypeId}" />" <c:if test="${productType.productTypeId eq status.value }">selected</c:if>><c:out value="${productType.productTypeName}" /></option>
				</c:forEach>
			</select>						
		</spring:bind>	
		

		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
	

		<tr>
		<td class="dataLabel">Diagnosis Set : </td>		
		<td class="dataField">
		<spring:bind path="productLayerLimitForm.diagnosisSetId">
			<input type="hidden" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >			
		</spring:bind>	
		<spring:bind path="productLayerLimitForm.diagnosisSetName">
			<input type="text" size=45 name="<c:out value="${status.expression}"/>" id="diagnosisSetName" value="<c:out value="${status.value }" />" >
			</spring:bind>

		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
	
		

			<tr>
				<td class="dataLabel"> Layer Level : </td>				
			

						<td class="dataField">
			<spring:bind path="productLayerLimitForm.layerLevel">
				<select name="<c:out value="${status.expression}" />">
					<option value="1" <c:if test="${status.value eq 1 }">selected</c:if>>Layer 1</option>
					<option value="2" <c:if test="${status.value eq 2 }">selected</c:if>>Layer 2</option>
					<option value="3" <c:if test="${status.value eq 3 }">selected</c:if>>Layer 3</option>
					
					
				</select>	
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
			



			<tr>
				<td class="dataLabel"> Annual Limit : </td>				
			
			<td class="dataField">
			<spring:bind path="productLayerLimitForm.annualLimit">
				<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="22">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>

	<tr>
				<td class="dataLabel"> Deductible : </td>				
			
			<td class="dataField">
			<spring:bind path="productLayerLimitForm.deductible">
				<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="22">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>

			<tr>
				<td class="dataLabel"> Co Share Percentage : </td>				
			
			<td class="dataField">
			<spring:bind path="productLayerLimitForm.coSharePercentage">
					<input type="text" size="5" name="<c:out value="${status.expression}" />" value="<fmt:formatNumber><c:out value="${status.value}" /></fmt:formatNumber>" maxlength="22"> %
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
			<tr>
				<td class="dataLabel"> Description : </td>				
			
	
						<td class="dataField">
				<spring:bind path="productLayerLimitForm.description">
				<textarea  cols="60" rows="8" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
					<div id="fredcaption">
						<c:out value="${status.errorMessage}" />
					</div>
				</spring:bind>
			</td>
			<td class="dataLabel"></td>
			<td class="dataField"></td>
		</tr>

	
		<tr>
			<td class="dataLabel">&nbsp;</td>
			<td class="dataField">&nbsp;</td>
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
	
	
	$(document).ready(function(){

    
    	$("#nameItemCategory").autocomplete("itemcategory?navigation=lookupjson", {
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
	            return  "<font color='#000' style='align: left;' >"+ row.code + " - " + row.name +"</font>" ;
	        },
	         extraParams: {
	       		caseCategoryId: function() { return $("#caseCategoryId").val(); }
	   		}        
		    }).bind("result", function(data, value){
		        $(this).parents("dd").find(".error_message").hide();
		        $("#idItemCategory").val (value.id);
		        $("#nameItemCategory").val (value.name);
	    });
	    
	        	$("#diagnosisSetName").autocomplete("diagnosisset?navigation=lookupjson", {
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
	            return  "<font color='#000' style='align: left;' >"+ row.number + " - " + row.name +"</font>" ;
	        }      
		    }).bind("result", function(data, value){
		        $(this).parents("dd").find(".error_message").hide();
		        $("#diagnosisSetId").val (value.id);
		        $("#diagnosisSetName").val (value.name);
	    });
	     
	     
	});
	
	function simpan (){

		document.form1.method = "POST";
		document.form1.action = "productlayerlimit-form";
		document.form1.submit();
	}
	function kembali (){
		document.form1.navigation.value = "back";
		document.form1.action = "productlayerlimit";
		document.form1.submit();
	}
	function cancel(){
		document.form1.navigation.value = "detail";
		document.form1.action = "product";
		document.form1.submit();
	}
	// forreign affairs
	
		// forreign affairs end
</script>