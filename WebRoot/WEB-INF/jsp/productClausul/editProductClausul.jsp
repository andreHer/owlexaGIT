<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<link rel="stylesheet" type="text/css" href="css/autocomplete.css">

<link rel="stylesheet" type="text/css" href="css/jquery/autocomplete/jquery.autocomplete.css"/>

<script type="text/javascript" src="scripts/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="scripts/jquery/plugin/jquery.autocomplete.pack.js"></script>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
   		<c:choose>
			<c:when test="${navigation eq 'list'}">
      			<td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Add Clausul</h3></td>
			</c:when>
      		<c:when test="${navigation eq 'listexdiagnosis'}">
      			<td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Add Diagnosis Exclusion</h3></td>
			</c:when>
   		</c:choose>
		<td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>

<form action="productclausul-form" method="POST"  name="form1" id="form_layout">

<input type="hidden" name="navigation" value="">
	<spring:bind path="productClausulForm.productClausulId">
		<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>


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
			<td class="dataLabel"></td>
			<td class="dataField"></td>
			<td class="dataLabel"></td>
			<td class="dataField"></td>
		</tr>		
		<tr>
			<td class="dataLabel">Product : </td>		
			<td class="dataField">
			<spring:bind path="productClausulForm.productId">
			<input type="hidden" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="11">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>	
			<spring:bind path="productClausulForm.productName">
			<input readonly="readonly" type="text" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" size=50>
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			
			</td>
		    <td class="dataLabel"></td>
		    <td class="dataField"></td>
		</tr>		
		<tr>
			<td class="dataLabel">Clausul : </td>		
			<td class="dataField">
				<spring:bind path="productClausulForm.clausulId">
					<input type="hidden" name="<c:out value="${status.expression}"/>" id="idClausul" value="<c:out value="${status.value}" />" readonly="readonly">
					<div id="fredcaption">
						<c:out value="${status.errorMessage}" />
					</div>
				</spring:bind>
				<spring:bind path="productClausulForm.clausulName">
					<input type="text" size="35" name="<c:out value="${status.expression}"/>" id="nameClausul" value="<c:out value="${status.value}" />" >
					<div id="fredcaption">
						<c:out value="${status.errorMessage}" />
					</div>
				</spring:bind>
			</td>
		    <td class="dataLabel"></td>
		    <td class="dataField">
		    	
		    </td>
		</tr>
		<tr>
			<td class="dataLabel"></td>		
			<td class="dataField"></td>
		    <td class="dataLabel"></td>
		    <td class="dataField">&nbsp;</td>
		</tr>
		<tr>
			<td class="dataLabel">Age Parameter : </td>		
			<td class="dataField">
			
				<spring:bind path="productClausulForm.umur">
					<input type="text"  name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />">
					<div id="fredcaption">
						<c:out value="${status.errorMessage}" />
					</div>
				</spring:bind>	
			
			</td>
		    <td class="dataLabel">Age Constraint : </td>
		    <td class="dataField">
		    	<spring:bind path="productClausulForm.parameterUmur">
		    		<select name="<c:out value="${status.expression}"/>">
		    			<option value="">--- PILIH SALAH SATU ---</option>
		    			<option value="1">Kurang Dari</option>
		    			<option value="2">Lebih Dari</option>
		    		</select>
				</spring:bind>	
		    </td>
		</tr>
		<tr>
			<td class="dataLabel"></td>		
			<td class="dataField"></td>
		    <td class="dataLabel"></td>
		    <td class="dataField">&nbsp;</td>
		</tr>
		<tr>
			<td class="dataLabel">Benefit Upgrade : </td>		
			<td class="dataField">
			
				<spring:bind path="productClausulForm.treatmentUpgradeType">
					<select name="<c:out value="${status.expression}"/>">
		    			<option value="">--- PILIH SALAH SATU ---</option>						    			
		    			<option value="2" <c:if test="${status.value eq 2}">selected</c:if>>ATAS PERMINTAAN SENDIRI</option>
		    			<option value="3" <c:if test="${status.value eq 3}">selected</c:if>>KAMAR PENUH</option>
		    			<option value="4" <c:if test="${status.value eq 4}">selected</c:if>>KAMAR TIDAK TERSEDIA</option>
		    			<option value="5" <c:if test="${status.value eq 5}">selected</c:if>>REKOMENDASI DOKTER</option>
		    		</select>									
				</spring:bind>
			</td>
		    <td class="dataLabel">Upgrade Calculation : </td>
		    <td class="dataField">
		    	<spring:bind path="productClausulForm.reductionType">
			    	<select name="<c:out value="${status.expression}"/>">
			    			<option value="">--- PILIH SALAH SATU ---</option>
			    			<option value="1" <c:if test="${status.value eq 1}">selected</c:if>>PRO-RATE</option>
			    			<option value="2" <c:if test="${status.value eq 2}">selected</c:if>>PERCENTAGE</option>
			    			<option value="3" <c:if test="${status.value eq 3}">selected</c:if>>FIXED POINT</option>
			    	</select>
		    	</spring:bind>
		    </td>
		</tr>
		<tr>
			<td class="dataLabel">Reduction Subject : </td>		
			<td class="dataField">							
				<spring:bind path="productClausulForm.reductionSubject">
					<select name="<c:out value="${status.expression}"/>">
		    			<option value="">--- PILIH SALAH SATU ---</option>
		    			<option value="1" <c:if test="${status.value eq 1}">selected</c:if>>ALL ITEM</option>
		    			<option value="2" <c:if test="${status.value eq 2}">selected</c:if>>ALL ITEM EXCLUDE ROOM</option>
		    		</select>									
				</spring:bind>
			</td>
		    <td class="dataLabel">Reduction Value : </td>
		    <td class="dataField">
		    	<spring:bind path="productClausulForm.benefitReductionPercentage">
			    	<input type="text"  name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />"> 
		    	</spring:bind>
		    </td>
		    
		</tr>
		<tr>
			<td class="dataLabel"></td>		
			<td class="dataField"></td>
		    <td class="dataLabel"></td>
		    <td class="dataField">&nbsp;</td>
		</tr>
		<tr>
			<td class="dataLabel">Diagnose : </td>		
			<td class="dataField">
			
				<spring:bind path="productClausulForm.diagnosisId">
					<input type="hidden" id="diagnosisId" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />">									
				</spring:bind>
				<input type="text" id="diagnoseName"  name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" size="35">
			
			</td>
		    <td class="dataLabel"></td>
		    <td class="dataField">
		    	
		    </td>
		</tr>
		<tr>
			<td class="dataLabel"></td>		
			<td class="dataField"></td>
		    <td class="dataLabel"></td>
		    <td class="dataField">&nbsp;</td>
		</tr>
		<tr>
			<td class="dataLabel">Procedure : </td>		
			<td class="dataField" colspan="3">
			
				<spring:bind path="productClausulForm.procedureId">
					<input type="hidden" id="procedureId" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />">									
				</spring:bind>
				<input type="text" id="procedureName"  name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" size="65">
			
			</td>
		   
		</tr>
		<tr>
			<td class="dataLabel">Decision : </td>		
			<td class="dataField">
			
				<spring:bind path="productClausulForm.decision">
					<select name="<c:out value="${status.expression}"/>">
		    			<option value="-1">--- PILIH SALAH SATU ---</option>
		    			<option value="1">Dicover</option>
		    			<option value="0">Tidak Dicover</option>
		    		</select>
				</spring:bind>	
			
			</td>
		    <td class="dataLabel"> </td>
		    <td class="dataField">
		    	
		    </td>
		</tr>
		<tr>
			<td class="dataLabel"> Description : </td>
		    <td class="dataField" colspan="3">
				<spring:bind path="productClausulForm.description">
				<textarea cols="60" rows="10" class="inputbox" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
					<div id="fredcaption">
						<c:out value="${status.errorMessage}" />
					</div>
				</spring:bind>
			</td>
		</tr>
		<tr>
			<td class="dataLabel"></td>
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
        <input title="Save [Alt+Shift+S]" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:simpan()" name="Save" value=" Save " type="submit">
        <input title="Cancel [Alt+Shift+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:cancel()" name="Cancel" value=" Cancel " type="button">         
	  </td>
      <td align="right"></td>
    </tr>
  </tbody>
</table>

</form>

<script language="javascript">

	$(document).ready(function(){
    	$("#nameClausul").autocomplete("clausul?navigation=lookupjson", {
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
	        }
	    }).bind("result", function(data, value){
	        $(this).parents("dd").find(".error_message").hide();
	        $("#idClausul").val(value.id);
	    });	  
   
	    $("#diagnoseName").autocomplete("diagnosis?navigation=lookupjson", {
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
	        }
	    }).bind("result", function(data, value){
	        $(this).parents("dd").find(".error_message").hide();
	        $("#diagnosisId").val(value.id);
	    });
	    
	    $("#procedureName").autocomplete("procedure?navigation=lookupjson", {
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
	        }
	    }).bind("result", function(data, value){
	        $(this).parents("dd").find(".error_message").hide();
	        $("#procedureId").val(value.id);
	    });	   
	});

	function simpan (){

		document.form1.method = "POST";
		<c:choose>
			<c:when test="${navigation eq 'list'}">
				document.form1.action = "productclausul-form";
				document.form1.navigation.value = "list";
				document.form1.productId.value = <c:out value="${productId}" />
			</c:when>
			<c:when test="${navigation eq 'listexdiagnosis'}">
				document.form1.action = "productclausul-form";
				document.form1.navigation.value = "listexdiagnosis";
				document.form1.productId.value = <c:out value="${productId}" />
			</c:when>
		</c:choose>
		document.form1.submit();
	}
	function cancel(){
		<c:choose>
			<c:when test="${navigation eq 'list'}">
				document.form1.navigation.value = "list";
				document.form1.action = "productclausul";
				document.form1.productId.value = <c:out value="${productId}" />
			</c:when>
			<c:when test="${navigation eq 'listexdiagnosis'}">
				document.form1.navigation.value = "listexdiagnosis";
				document.form1.action = "productclausul";
				document.form1.productId.value = <c:out value="${productId}" />
			</c:when>
		</c:choose>
		document.form1.submit();
	}


</script>