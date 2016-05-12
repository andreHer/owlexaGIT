<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
 
		<link rel="stylesheet" type="text/css" href="css/jquery/autocomplete/jquery.autocomplete.css"/>
		
		<script type="text/javascript" src="scripts/jquery/jquery-1.4.4.min.js"></script>
		<script type="text/javascript" src="scripts/jquery/plugin/jquery.autocomplete.pack.js"></script>
		
		<script type="text/javascript" src="scripts/owlexa.function.js"></script>
		

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
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Register / Update Policy Benefit</h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>
<form action="policybenefit-form" method="POST"  name="form1" id="form_layout">
<input type="hidden" name="navigation" value="">
	<spring:bind path="policyBenefitForm.policyBenefitId">
		<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
	</spring:bind>
	<spring:bind path="policyBenefitForm.policyId">
		<input type="hidden" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" maxlength="11">
	</spring:bind>


<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>
		
			<tr>
				<td class="dataLabel">&nbsp; </td>
				<td class="dataField">&nbsp;</td>
			    <td class="dataLabel"></td>
			    <td class="dataField"></td>
			</tr>


			<tr>
				<td class="dataLabel"> Policy Number : </td>
				<td class="dataField">
					<spring:bind path="policyBenefitForm.policyNumber">
						<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" >
					</spring:bind>
				</td>
			    <td class="dataLabel"> Member Group : </td>
				<td class="dataField">
					<spring:bind path="policyBenefitForm.groupName">
						<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>">
					</spring:bind>
				</td>
			</tr>
			

			<tr>
				<td class="dataLabel"> Benefit Type : </td>
				<td class="dataField">
					<spring:bind path="policyBenefitForm.benefitType">
						<select name="<c:out value="${status.expression }" />" >
							<option value=""> -- SELECT ONE -- </option>
							<option value="1" <c:if test="${status.value eq 1 }">selected</c:if> > ITEM BENEFIT </option>
							<option value="2" <c:if test="${status.value eq 2 }">selected</c:if>> DIAGNOSIS BENEFIT </option>
							<option value="5" <c:if test="${status.value eq 5 }">selected</c:if>> COVERAGE BENEFIT </option>
							<option value="3" <c:if test="${status.value eq 3 }">selected</c:if>> DIAGNOSIS SET BENEFIT </option>
							<option value="4" <c:if test="${status.value eq 4 }">selected</c:if>> PROCEDURE BENEFIT </option>
							
						</select>
					</spring:bind>
				</td>
			    <td class="dataLabel"> </td>				
				<td class="dataField">

				</td>
			</tr>
			<tr>
				<td class="dataLabel"> Case Category : </td>
				<td class="dataField">
					<spring:bind path="policyBenefitForm.caseCategoryId">
						<select name="<c:out value="${status.expression }" />" id="caseCategoryId">
							<c:forEach items="${ccList}" var="cc">
								<option value="<c:out value="${cc.caseCategoryId}" />" <c:if test="${status.value eq cc.caseCategoryId}">selected</c:if>><c:out value="${cc.caseCategoryName}" /></option>
							</c:forEach>
						</select>
					</spring:bind>
				</td>
			    <td class="dataLabel"> Item Category : </td>				
				<td class="dataField">
					<spring:bind path="policyBenefitForm.itemCategoryId">
						<input type="hidden" size="35" id="itemCategoryId" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" >
					</spring:bind>
					
					<spring:bind path="policyBenefitForm.itemCategoryName">
						<input type="text" size="35" id="<c:out value="${status.expression}" />" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>">
					</spring:bind>
				</td>
			</tr>
			<tr>
				<td class="dataLabel"> Procedure : </td>
				<td class="dataField">
					<spring:bind path="policyBenefitForm.procedureId">
						<input type="hidden" size="35" id="procedureId" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" >
					</spring:bind>
					
					<spring:bind path="policyBenefitForm.procedureName">
						<input type="text" size="35" id="<c:out value="${status.expression}" />" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>">
					</spring:bind>
				</td>
			    <td class="dataLabel"> Diagnosis : </td>				
				<td class="dataField">
					<spring:bind path="policyBenefitForm.diagnosisId">
						<input type="hidden" size="35" id="diagnosisId" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" >
					</spring:bind>
					
					<spring:bind path="policyBenefitForm.diagnosisName">
						<input type="text" size="35" id="<c:out value="${status.expression}" />" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>">
					</spring:bind>
				</td>
			</tr>
			<tr>
				<td class="dataLabel"> </td>
				<td class="dataField">
				</td>
			    <td class="dataLabel"> Diagnosis Set : </td>				
				<td class="dataField">
					<spring:bind path="policyBenefitForm.diagnosisSetId">
						<input type="hidden" size="35" id="diagnosisSetId" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" >
					</spring:bind>
					
					<spring:bind path="policyBenefitForm.diagnosisSetName">
						<input type="text" size="35" id="<c:out value="${status.expression}" />" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>">
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
				<td class="dataLabel"> Benefit Limit : </td>				
				<td class="dataField">
					<spring:bind path="policyBenefitForm.benefitLimit">
						<input type="text" size="35" onkeypress="return isNumberKey(this,event);" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="22">				
					</spring:bind>
				</td>
					<td class="dataLabel"> Annual Benefit : </td>				
			
			<td class="dataField">
			<spring:bind path="policyBenefitForm.annualBenefit">
					<input type="text" size="35" onkeypress="return isNumberKey(this,event);" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="22">
			
			</spring:bind>
			    </td>
			</tr>

			<tr>
				<td class="dataLabel"> Max Occurance Per Member : </td>
				<td class="dataField">
					<spring:bind path="policyBenefitForm.maxOccurancePerMember">
						<input type="text" size="35" onkeypress="return isNumberKey(this,event);" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" maxlength="11">
				
					</spring:bind>
				</td>
			    <td class="dataLabel"> Max Usage Per Member : </td>
				<td class="dataField">
					<spring:bind path="policyBenefitForm.maxUsagePerMember">
						<input type="text" size="35" onkeypress="return isNumberKey(this,event);" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" maxlength="11">
					</spring:bind>
				</td>
			</tr>
			
			
			
			<tr>
				<td class="dataLabel"> Provider Contract : </td>				
				<td class="dataField">
					<spring:bind path="policyBenefitForm.providerContract">
						<select name="<c:out value="${status.expression }" />">
							<option value="">-- PILIH SALAH SATU --</option>
							<option value="1" <c:if test="${status.value eq 1 }">selected</c:if>>YES</option>
							<option value="0" <c:if test="${status.value eq 0 }">selected</c:if>>NO</option>							
						</select>
					</spring:bind>
				</td>
			    <td class="dataLabel"></td>
			    <td class="dataField"></td>
			</tr>
			<tr>
				<td class="dataLabel"> Cost Sharing : </td>
				<td class="dataField">
					<spring:bind path="policyBenefitForm.isCostSharing">
						<select name="<c:out value="${status.expression }" />">
							<option value="">-- PILIH SALAH SATU --</option>
							<option value="1" <c:if test="${status.value eq 1 }">selected</c:if>>YES</option>
							<option value="0" <c:if test="${status.value eq 0 }">selected</c:if>>NO</option>							
						</select>

					</spring:bind>
				</td>
				<td class="dataLabel"> Treatment Location : </td>				
				<td class="dataField">
					<spring:bind path="policyBenefitForm.treatmentLocation">
						<select name="<c:out value="${status.expression }" />">
							<option value="">-- PILIH SALAH SATU --</option>
							<option value="1" <c:if test="${status.value eq 1 }">selected</c:if>>DOMESTIC</option>
							<option value="2" <c:if test="${status.value eq 2 }">selected</c:if>>OVERSEAS</option>
							<option value="3" <c:if test="${status.value eq 3 }">selected</c:if>>BOTH</option>
						</select>
						
					</spring:bind>
				</td>	    
			</tr>
			
			

			<tr>
				<td class="dataLabel"> Reimbursement Percentage : </td>
				<td class="dataField">
					<spring:bind path="policyBenefitForm.reimbursementPercentage">
						<input type="text" size="15" onkeypress="return isNumberKey(this,event);" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="22">
					</spring:bind>
				</td>
				<td class="dataLabel"> Cashless Percentage : </td>
				<td class="dataField">
					<spring:bind path="policyBenefitForm.cashlessPercentage">
						<input type="text" size="15" onkeypress="return isNumberKey(this,event);" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="22">
					</spring:bind>
				</td>
	    
			</tr>


		

			<tr>
				<td class="dataLabel"> Description : </td>				
			

		    <td class="dataField" colspan=3>
				<spring:bind path="policyBenefitForm.description">
					<textarea rows="5" cols="50" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>				
				</spring:bind>
			</td>
	    
		</tr>
		

			
		<tr>
			<td class="dataLabel">&nbsp; </td>
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
        <input title="Cancel [Alt+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:kembali()" name="Cancel" value=" Cancel " type="button">         
	  </td>
      <td align="right"></td>
    </tr>
  </tbody>
</table>
</form>

<script language="javascript">

		$(document).ready(function(){

    
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
	            return  "<font color='#000' style='align: left;' >"+ row.code + " - " + row.name +"</font>" ;
	        }
		    }).bind("result", function(data, value){
		        $(this).parents("dd").find(".error_message").hide();
		        $("#procedureId").val (value.id);
		        $("#procedureName").val (value.name);
	    });
	    
	    $("#diagnosisName").autocomplete("diagnosis?navigation=lookupjson", {
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
		        $("#diagnosisId").val (value.id);
		        $("#diagnosisName").val (value.name);
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
		document.form1.action = "policybenefit-form";
		document.form1.submit();
	}
	function kembali(){
		//window.alert("hola");
		document.form1.navigation.value = "back";
		document.form1.action = "policybenefit";
		document.form1.submit();
	}	
</script>