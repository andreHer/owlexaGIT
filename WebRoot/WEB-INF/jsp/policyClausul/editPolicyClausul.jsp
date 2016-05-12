
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<!-- <link rel="stylesheet" type="text/css" href="css/autocomplete.css"> -->

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

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Register / Update Policy Clausul</h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>
<form action="policyclausul-form" method="POST"  name="form1" id="form_layout">
<input type="hidden" name="navigation" value="">

<br />
	<spring:bind path="policyClausulForm.id">
		<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
	</spring:bind>

<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>
		
		<tr>
			<td class="dataLabel"></td>
			<td class="dataField"></td>
			<td class="dataLabel"></td>
			<td class="dataField"></td>
		</tr>		
		<tr>
			<td class="dataLabel">Policy Number : </td>		
			<td class="dataField">
			<spring:bind path="policyClausulForm.policyId">
				<input type="hidden" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="11">				
			</spring:bind>	
			<spring:bind path="policyClausulForm.policyNumber">
				<input readonly="readonly" size=45 type="text" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />">				
			</spring:bind>
			
			</td>
		    <td class="dataLabel">Case Category *: </td>
		    <td class="dataField">
		    	<spring:bind path="policyClausulForm.caseCategoryId">
		    		<select name="<c:out value="${status.expression}"/>" id="caseCategoryId">
		    			<option value="-1">-- PILIH SALAH SATU --</option>
		    			<c:forEach items="${caseCategories}" var="cc">
		    				<option value="<c:out value="${cc.caseCategoryId}" />" <c:if test="${cc.caseCategoryId eq status.value }">selected</c:if>><c:out value="${cc.caseCategoryName}" /></option>
		    			</c:forEach>
		    		</select>
		    			<%
		    			String alertCase = (String) request.getAttribute("alertCase");
						if (alertCase != null && !alertCase.trim().equals("")) {%>
						<div id="warning">
							<c:out value="${alertCase}"></c:out>
						</div>
						<%}%>
				</spring:bind>	
		    </td>
		</tr>		
		<tr>
			<td class="dataLabel">Clausul * : </td>		
			<td class="dataField">
				<spring:bind path="policyClausulForm.clausulId">
					<input type="hidden" name="<c:out value="${status.expression}"/>" id="idClausul" value="<c:out value="${status.value}" />" readonly="readonly">					
				</spring:bind>
				<spring:bind path="policyClausulForm.clausulName">
					<input type="text" size="45" name="<c:out value="${status.expression}"/>" id="nameClausul" value="<c:out value="${status.value}" />" >					
					<div id="fredcaption">
						<c:out value="${status.errorMessage}" />
					</div>
				</spring:bind>
			</td>
		    <td class="dataLabel">Item Category : </td>		
			<td class="dataField">
			<spring:bind path="policyClausulForm.itemCategoryId">
				<input type="hidden" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="11">				
			</spring:bind>	
			<spring:bind path="policyClausulForm.itemCategoryName">
				<input  size=45 type="text" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />">				
			</spring:bind>
			
			</td>
		</tr>
		<tr>
			<td class="dataLabel">Treatment Location : </td>		
			<td class="dataField">
				
				<spring:bind path="policyClausulForm.locationId">
					<select name="<c:out value="${status.expression}" />">
						<option value="3"<c:if test="${status.value eq 3 }">selected</c:if>>ANY LOCATION (DOMESTIC/OVERSEAS)</option>
						<option value="1"<c:if test="${status.value eq 1 }">selected</c:if>>DOMESTIC</option>
						<option value="2"<c:if test="${status.value eq 2 }">selected</c:if>>OVERSEAS</option>
					</select>					
				</spring:bind>
			</td>
		    <td class="dataLabel">Is EDC : </td>		
			<td class="dataField">
				<spring:bind path="policyClausulForm.isEdc">
					<input type="checkbox" name="<c:out value="${status.expression}" />" value="1" <c:if test="${status.value eq 1 }">checked</c:if>>
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
			<td class="dataLabel">Age Parameter : </td>		
			<td class="dataField">
			
				<spring:bind path="policyClausulForm.age">
					<input type="text"  name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />">
				</spring:bind>	
			
			</td>
		    <td class="dataLabel">Age Constraint : </td>
		    <td class="dataField">
		    	<spring:bind path="policyClausulForm.ageParameter">
		    		<select name="<c:out value="${status.expression}"/>">
		    			<option value="">--- PILIH SALAH SATU ---</option>
		    			<option value="1" <c:if test="${status.value eq 1 }">selected</c:if>>Kurang Dari</option>
		    			<option value="2" <c:if test="${status.value eq 2 }">selected</c:if>>Lebih Dari</option>
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
			
				<spring:bind path="policyClausulForm.treatmentUpgradeType">
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
		    	<spring:bind path="policyClausulForm.benefitReductionType">
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
				<spring:bind path="policyClausulForm.benefitReductionSubject">
					<select name="<c:out value="${status.expression}"/>">
		    			<option value="">--- PILIH SALAH SATU ---</option>
		    			<option value="1" <c:if test="${status.value eq 1}">selected</c:if>>ALL ITEM</option>
		    			<option value="2" <c:if test="${status.value eq 2}">selected</c:if>>ALL ITEM EXCLUDE ROOM</option>
		    		</select>									
				</spring:bind>
			</td>
		    <td class="dataLabel">Reduction Value : </td>
		    <td class="dataField">
		    	<spring:bind path="policyClausulForm.benefitReductionPercentage">
			    	<input type="text"  name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />"> 
		    	</spring:bind>
		    </td>
		    
		</tr>
		<tr>
			<td class="dataLabel">Benefit Iteration : </td>
			<td class="dataField">							
				<spring:bind path="policyClausulForm.iterationType">
					<select name="<c:out value="${status.expression}"/>">
		    			<option value="">--- PILIH SALAH SATU ---</option>
		    			<option value="1" <c:if test="${status.value eq 1}">selected</c:if>>MAX CHILDREN</option>		    			
		    		</select>									
				</spring:bind>
			</td>
		    <td class="dataLabel">Iteration Value : </td>
		    <td class="dataField">
		    	<spring:bind path="policyClausulForm.benefitIteration">
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
			
				<spring:bind path="policyClausulForm.diagnosisId">
					<input type="hidden" id="diagnosisId" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />">									
				</spring:bind>
				<spring:bind path="policyClausulForm.diagnosisName">
					<input type="text" id="diagnoseName"  name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" size="45">									
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
			<td class="dataLabel">Procedure : </td>		
			<td class="dataField" colspan="3">
			
				<spring:bind path="policyClausulForm.procedureId">
					<input type="hidden" id="procedureId" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />">									
				</spring:bind>
				<spring:bind path="policyClausulForm.procedureName">
					<input type="text" id="procedureName"  name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" size="45">									
				</spring:bind>			
			</td>
		   
		</tr>
		<tr>
			<td class="dataLabel">Decision : </td>		
			<td class="dataField">
			
				<spring:bind path="policyClausulForm.decision">
					<select name="<c:out value="${status.expression}"/>">
		    			<option value="">--- PILIH SALAH SATU ---</option>
		    			<option value="1" <c:if test="${status.value eq 1}">selected</c:if>>Dicover</option>
		    			<option value="0" <c:if test="${status.value eq 0}">selected</c:if>>Tidak Dicover</option>
		    		</select>
				</spring:bind>	
			
			</td>
		   <td class="dataLabel">Claim Treatment Type : </td>		
			<td class="dataField">
			
				<spring:bind path="policyClausulForm.claimTreatmentTypeId">
					<select name="<c:out value="${status.expression}"/>">
		    			<option value="">--- PILIH SALAH SATU ---</option>
		    			<option value="1" <c:if test="${status.value eq 1}">selected</c:if>>Reimbursement</option>
		    			<option value="2" <c:if test="${status.value eq 2}">selected</c:if>>Cashless</option>
		    		</select>
				</spring:bind>	
			
			</td>
		</tr>
		<tr>
			<td class="dataLabel"> Description : </td>
		    <td class="dataField" colspan="3">
				<spring:bind path="policyClausulForm.description">
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
        <input title="Save [Alt+S]" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:simpan()" name="Save" value=" Save " type="submit">
        <input title="Cancel [Alt+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:cancel()" name="Cancel" value=" Cancel " type="button">         
	  </td>
      <td align="right"></td>
    </tr>
  </tbody>
</table>
</form>

<script language="javascript">

	$(document).ready(function(){
    	$("#nameClausul").autocomplete("clausul?navigation=lookupjson", {
	        min : 2,
	        max: 7,
	        dataType: "json",
	        cacheLength: 0,
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
	       // $("#nameClausul").val(value.name);
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
	    
	    $("#itemCategoryName").autocomplete("itemcategory?navigation=lookupjson", {
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
		        $("#itemCategoryId").val (value.id);
		        $("#itemCategoryName").val (value.name);
	    });
	    
	});


	function simpan (){

		document.form1.method = "POST";
		document.form1.action = "policyclausul-form";
		document.form1.submit();
	}
	function cancel (){
		document.form1.navigation.value = "back";
		document.form1.action = "policyclausul";
		document.form1.submit();
	}
	// forreign affairs
			// forreign affairs end
</script>