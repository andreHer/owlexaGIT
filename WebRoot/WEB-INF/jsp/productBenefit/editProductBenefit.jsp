<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<script type='text/javascript' src='dwr/interface/AJAXMeasurementService.js'></script>
<script type='text/javascript' src='dwr/engine.js'></script>
<script type='text/javascript' src='dwr/util.js'></script>
<script type="text/javascript" src="scripts/owlexa.function.js"></script>

<link rel="stylesheet" type="text/css" href="css/autocomplete.css">
<link rel="stylesheet" type="text/css" href="css/button.css">

<link rel="stylesheet" type="text/css" href="css/jquery/autocomplete/jquery.autocomplete.css"/>

<script type="text/javascript" src="scripts/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="scripts/jquery/plugin/jquery.autocomplete.pack.js"></script>

<form action="productbenefit-form" method="POST"  name="form1" id="form_layout">

<input type="hidden" name="navigation" value="">

<spring:bind path="productBenefitForm.productBenefitId">
	<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
</spring:bind>

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
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Register Product Benefit <c:if test="${productBenefitForm.productLayerId ne null}"> : Layer <c:out value="${productBenefitForm.layerName}" /></c:if></h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>	
<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>
		<tr>
			<td>
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
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
								<spring:bind path="productBenefitForm.productId">
									<input type="hidden" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
								</spring:bind>
								<spring:bind path="productBenefitForm.productLayerId">
									<input type="hidden" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
								</spring:bind>		
								<spring:bind path="productBenefitForm.productName">
									<input type="text" size=35 name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
								</spring:bind>
							</td>
							<td class="dataLabel"> High Plan : </td>
							<td class="dataField">
								<spring:bind path="productBenefitForm.benefitUpgradeType">
									<input type="checkbox" name="<c:out value="${status.expression}" />" value="1" <c:if test="${status.value eq 1}">checked</c:if> >
									<div id="fredcaption">
										<c:out value="${status.errorMessage}" />
									</div>
								</spring:bind>
							</td>
						</tr>
						<tr>
							<td class="dataLabel">Case Category : </td>		
							<td class="dataField">
								<spring:bind path="productBenefitForm.caseCategoryId">
								<select name="<c:out value="${status.expression}"/>" id="caseCategoryId">
										<c:forEach items="${caseCategories}" var="caseCategory">
											<option value="<c:out value="${caseCategory.caseCategoryId}" />" <c:if test="${status.value eq caseCategory.caseCategoryId}">selected</c:if>><c:out value="${caseCategory.caseCategoryName}" /></option>
										</c:forEach>
									</select>
									<div id="fredcaption">
										<c:out value="${status.errorMessage}" />
									</div>
								</spring:bind>
							</td>
							<td class="dataLabel">Item Category : </td>		
							<td class="dataField">
								<spring:bind path="productBenefitForm.itemCategoryName">
								<input type="text" size=35 name="<c:out value="${status.expression}"/>" id="nameItemCategory" value="<c:out value="${status.value}" />" >
									<div id="fredcaption">
										<c:out value="${status.errorMessage}" />
									</div>
								</spring:bind>	
								<spring:bind path="productBenefitForm.itemCategoryId">
								<input type="hidden" name="<c:out value="${status.expression}"/>"   id="idItemCategory" value="<c:out value="${status.value}" />" >
									<div id="fredcaption">
										<c:out value="${status.errorMessage}" />
									</div>
								</spring:bind>	
							</td>
						</tr>
					<!-- 
					<tr>
							<td class="dataLabel"> </td>		
							<td class="dataField">
								
							</td>
							<td class="dataLabel">Diagnosis : </td>		
							<td class="dataField">
								<spring:bind path="productBenefitForm.diagnosisName">
								<input type="text" size=55 name="<c:out value="${status.expression}"/>" id="nameDiagnosis" value="<c:out value="${status.value}" />" >
									<div id="fredcaption">
										<c:out value="${status.errorMessage}" />
									</div>
								</spring:bind>	
								<spring:bind path="productBenefitForm.diagnosisId">
								<input type="hidden" name="<c:out value="${status.expression}"/>"   id="idDiagnosisId" value="<c:out value="${status.value}" />" >
									<div id="fredcaption">
										<c:out value="${status.errorMessage}" />
									</div>
								</spring:bind>	
							</td>
						</tr>

					 -->
			<tr>
				<td class="dataLabel"> Cashless Benefit Limit : </td>				
			
			<td class="dataField">
			<spring:bind path="productBenefitForm.benefitLimit">
					<input type="text" onkeypress="javascript:return isNumberKey(this,event)" size="35" id="benefitLimit" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="22" >
			</spring:bind>
			
			</td>

		<td class="dataLabel">Benefit Usage Type : </td>		
		<td class="dataField">
		<spring:bind path="productBenefitForm.benefitUsageType">
		<select name="<c:out value="${status.expression}"/>">
				<c:forEach items="${benefitUsageTypes}" var="benefitType">
					<option value="<c:out value="${benefitType.benefitUsageTypeId}" />" <c:if test="${status.value eq benefitType.benefitUsageTypeId}">selected</c:if>><c:out value="${benefitType.benefitUsage}" /></option>
				</c:forEach>
			</select>
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>	
		</td>
	</tr>
	<tr>
		<td class="dataLabel"> Reimbursement Benefit Limit : </td>
		<td class="dataField">
			<spring:bind path="productBenefitForm.reimbursementBenefitLimit">
					<input type="text" onkeypress="javascript:return isNumberKey(this,event)" size="35" id="reimbursementLimit" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="22" >
			</spring:bind>
			
		</td>
		<td class="dataLabel">EDC Enabled : </td>		
		<td class="dataField">
		<spring:bind path="productBenefitForm.isEdcEnabled">
		
				<select name="<c:out value="${status.expression}"/>">
					<option value="-1">--- PILIH SALAH SATU ---</option>				
					<option value="0" <c:if test="${status.value eq 0}">selected</c:if>>NO</option>				
					<option value="1" <c:if test="${status.value eq 1}">selected</c:if>>YES</option>
		
			</select>
		</spring:bind>	
		</td>
	</tr>

			

			<tr>
				<td class="dataLabel"> </td>				
			
			<td class="dataField">
			
			</td>

		<td class="dataLabel">Benefit Show On EDC: </td>		
		<td class="dataField">
		<spring:bind path="productBenefitForm.benefitShowOnEdc">
		
				<select name="<c:out value="${status.expression}"/>">
					<option value="-1">--- PILIH SALAH SATU ---</option>				
					<option value="0" <c:if test="${status.value eq 0}">selected</c:if>>NO</option>				
					<option value="1" <c:if test="${status.value eq 1}">selected</c:if>>YES</option>
			</select>
		
		</spring:bind>	
		</td>
	</tr>


			

			<tr>
				<td class="dataLabel"> Max Occurance / Year : </td>				
			

						<td class="dataField">
			<spring:bind path="productBenefitForm.maxOccurance">
				<input type="text" onkeypress="javascript:return isNumberKey(this,event)" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" maxlength="11">&nbsp; 
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
				<td class="dataLabel"> Limit Escalation: </td>				
				<td class="dataField">
			<spring:bind path="productBenefitForm.benefitUpgradeLimit">
				<input type="text" onkeypress="javascript:return isNumberKey(this,event)" size="10" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" maxlength="11">&nbsp; 
			</spring:bind>
			<spring:bind path="productBenefitForm.isBenefitUpgradable">
				<input type="checkbox" id="checkBenefitUpgrade" value="unlimited" onchange="javascript:upgradeLimitUpdate()"> &nbsp; (upgradable)
			</spring:bind>
				<spring:bind path="productBenefitForm.benefitUpgradeCoverageDaysInterval">
				<input type="text" onkeypress="javascript:return isNumberKey(this,event)" size="5" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" maxlength="11">&nbsp;  day(s)
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
		</tr>
			<tr>
				<td class="dataLabel"> Benefit Limit Per Case / Day: </td>				
			
			<td class="dataField">
			<spring:bind path="productBenefitForm.maxBenefitPerCase">
					<input type="text" onkeypress="javascript:return isNumberKey(this,event)" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="22">
			</spring:bind>
			
			</td>
			<td class="dataLabel"> Cashless Percentage : </td>	
			<td class="dataField">
			<spring:bind path="productBenefitForm.cashlessPercentage">
					<input type="text" onkeypress="javascript:return isNumberKey(this,event)" size="10" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="22"> %
			</spring:bind>
			
			</td>

		
	</tr>
	<tr>
			<td class="dataLabel">Max Occurance Per Case / Day : </td>		
		<td class="dataField">
		<spring:bind path="productBenefitForm.maxOccurancePerCase">
					<input type="text" onkeypress="javascript:return isNumberKey(this,event)" size="10" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="22">
			</spring:bind>
		
		</td>

<td class="dataLabel"> Reimbursement Percentage : </td>	
			<td class="dataField">
			<spring:bind path="productBenefitForm.reimbursementPercentage">
					<input type="text" onkeypress="javascript:return isNumberKey(this,event)" size="10" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="22"> %
			</spring:bind>
			
			</td>
	</tr>

			<tr>
				<td class="dataLabel"> Principal Cost Sharing : </td>				
			
			<td class="dataField">
			<spring:bind path="productBenefitForm.costSharingAmount">
					<input type="text" onkeypress="javascript:return isNumberKey(this,event)" size="25" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="22">&nbsp; 
			</spring:bind>
			</td>
			<td class="dataLabel"> Principal Cost Sharing Percentage : </td>				
			
			<td class="dataField">
			<spring:bind path="productBenefitForm.costSharingPercentage">
					<input type="text" onkeypress="javascript:return isNumberKey(this,event)" size="15" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="22"> %
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>

	</tr>
	<tr>
				<td class="dataLabel"> Dependent Cost Sharing : </td>				
			
			<td class="dataField">
			<spring:bind path="productBenefitForm.dependentCostSharingAmount">
					<input type="text" onkeypress="javascript:return isNumberKey(this,event)" size="25" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="22">&nbsp; 
			</spring:bind>
			</td>
			<td class="dataLabel"> Dependent Cost Sharing Percentage : </td>				
			
			<td class="dataField">
			<spring:bind path="productBenefitForm.dependentCostSharingPercentage">
					<input type="text" onkeypress="javascript:return isNumberKey(this,event)" size="15" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="22"> %
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>

	</tr>
				<tr>
				<td class="dataLabel"> &nbsp;</td>				
			
			<td class="dataField">
			</td>
		<td class="dataLabel"></td>		
		<td class="dataField">
		</td>

	</tr>
			<tr>
				<td class="dataLabel"> Benefit Sharing : </td>				
			
			<td class="dataField">
			<spring:bind path="productBenefitForm.sharedBenefitId">
					<input type="hidden" id="<c:out value="${status.expression}" />" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />"> 
			</spring:bind>
			<spring:bind path="productBenefitForm.sharedBenefitName">
					<input type="text" size="35" id="<c:out value="${status.expression}" />" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="22">&nbsp; 
			</spring:bind>
			</td>
			<td class="dataLabel"> Deductible Limit : </td>				
			
			<td class="dataField">
			<spring:bind path="productBenefitForm.deductibleLimit">
					<input type="text" onkeypress="javascript:return isNumberKey(this,event)" size="25" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="22">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
		</tr>

		

	


		
	<tr>
		<td class="dataLabel">Measurement Unit : </td>		
		<td class="dataField">
		<spring:bind path="productBenefitForm.measurementUnit">
		<select name="<c:out value="${status.expression}"/>" id="measurement">
			<option value=''> -- SELECT MEASUREMENT -- </option>
				<c:forEach items="${measurementUnits}" var="mu">
					<option value="<c:out value="${mu.measurementUnitId}" />" <c:if test="${status.value eq mu.measurementUnitId}">selected</c:if>><c:out value="${mu.measurementUnitName}" /></option>
				</c:forEach>
			</select>
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>
		</td>
	    <td class="dataLabel"><!-- Treatment Level :  --></td>		
		<td class="dataField">
		<!-- 
		<spring:bind path="productBenefitForm.treatmentLevel">
		<select name="<c:out value="${status.expression}"/>" id="treatment">
			<option value='-1'> -- SELECT LEVEL -- </option>
			<option value='0' <c:if test="${status.value eq 0}">selected</c:if>> KOMPLEKS </option>
			<option value='1' <c:if test="${status.value eq 1}">selected</c:if>> BESAR </option>
			<option value='2' <c:if test="${status.value eq 2}">selected</c:if>> SEDANG </option>
			<option value='3' <c:if test="${status.value eq 3}">selected</c:if>> KECIL </option>	
		</select>
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>
		 -->
		</td>
	</tr>

		
	<tr>
		<td class="dataLabel">Location : </td>		
		<td class="dataField">
		
		<spring:bind path="productBenefitForm.treatmentLocation">
		<select name="<c:out value="${status.expression}"/>">
				<c:forEach items="${locations}" var="location">
					<option value="<c:out value="${location.location}" />" <c:if test="${status.value eq location.locationId}">selected</c:if>><c:out value="${location.location}" /></option>
				</c:forEach>
			</select>
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>		
 	    </td> 
		<td class="dataLabel">Coverage Day Interval : </td>		
		<td class="dataField">
		
		<spring:bind path="productBenefitForm.preCoverageDaysInterval">
			<input type="text" onkeypress="javascript:return isNumberKey(this,event)" size="5" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="22"> day(s)			
		</spring:bind>		
		<spring:bind path="productBenefitForm.coverageDaysInterval">
			<input type="text" onkeypress="javascript:return isNumberKey(this,event)" size="5" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="22"> day(s)			
		</spring:bind>
		
 	    </td> 
	</tr>
	<tr>
		<td class="dataLabel"> Exclude Plan Limit : </td>
		<td class="dataField">
			<spring:bind path="productBenefitForm.isExcludePlanLimit">
				<select name="<c:out value="${status.expression}"/>" id="dependent">					
					<option value='1' <c:if test="${status.value eq 1}">selected</c:if>> NO </option>
					<option value='0' <c:if test="${status.value eq 0}">selected</c:if>> YES </option>
				</select>
			</spring:bind>
		</td> 
		<td class="dataLabel"><!-- Dependent Coverage Method :  --></td>		
		<td class="dataField">
		<!-- 
		<spring:bind path="productBenefitForm.dependentSharingMethod">
		<select name="<c:out value="${status.expression}"/>" id="dependent">
			<option value='-1'> -- SELECT DEPENDENT METHOD -- </option>
			<option value='0' <c:if test="${status.value eq 0}">selected</c:if>> UNCOVERED </option>
			<option value='1' <c:if test="${status.value eq 1}">selected</c:if>> SHARING </option>
			<option value='2' <c:if test="${status.value eq 2}">selected</c:if>> EXCLUSIVE </option>
				
		</select>
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>
		 -->		
 	    </td> 
	</tr>
	<tr>
		<td class="dataLabel"> Is SMM Benefit : </td>
		<td class="dataField">
			<spring:bind path="productBenefitForm.isSMMBenefit">
				<select name="<c:out value="${status.expression}"/>" id="smm">					
					<option value='0' <c:if test="${status.value eq 0}">selected</c:if>> NOT SMM </option>
					<option value='1' <c:if test="${status.value eq 1}">selected</c:if>> YES, SMM BENEFIT </option>								
				</select>
			</spring:bind>
		</td> 
		<td class="dataLabel"> SMM Benefit Limit : </td>		
		<td class="dataField">
			<spring:bind path="productBenefitForm.SMMLimit">
				<input type="text" onkeypress="javascript:return isNumberKey(this,event)" size="35" id="smmLimit" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="22" >
			</spring:bind>
		</td> 
	</tr>
	<tr>
		<td class="dataLabel"> SMM Effective Day : </td>
		<td class="dataField">
			<spring:bind path="productBenefitForm.effectiveSMMDay">
				<input type="text" onkeypress="javascript:return isNumberKey(this,event)" size="35" id="smmEffectiveDay" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="22" >
			</spring:bind>
		</td> 
		<td class="dataLabel"> </td>		
		<td class="dataField">
			
		</td> 
	</tr>
	<tr>
		<td class="dataLabel">Description : </td>		
		<td class="dataField" colspan="3">
			<spring:bind path="productBenefitForm.information">
				<textarea name="${status.expression}" cols="60" rows="8"><c:out value="${status.value}" /></textarea>
			</spring:bind>
		</td> 
		
	</tr>
	<tr>
		<td class="dataLabel">								
		</td><td class="dataField">
									
			</td>
			<td class="dataLabel">
			
			</td>
			<td class="dataField">							
				
			</td>
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
	    
	        	$("#nameDiagnosis").autocomplete("diagnosis?navigation=lookupjson", {
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
		        $("#idDiagnosisId").val (value.id);
		        $("#nameDiagnosis").val (value.name);
	    });
	     $("#sharedBenefitName").autocomplete("productbenefit?navigation=lookupjson", {
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
	        },
	         extraParams: {
	       		productId: function() { return $("#productId").val(); },
	       		caseCategoryId: function() { return $("#caseCategoryId").val(); }
	   		}        
		    }).bind("result", function(data, value){
		        $(this).parents("dd").find(".error_message").hide();
		        $("#sharedBenefitId").val (value.id);
		        $("#sharedBenefitName").val (value.name);
		        $("#benefitLimit").val (value.limit);
		        $("#reimbursementLimit").val (value.limit);
	    });
	     
	});
	function simpan (){

		document.form1.method = "POST";
		document.form1.action = "productbenefit-form";
		document.form1.submit();
	}
	function cancel(){
		document.form1.navigation.value = "list";
		document.form1.action = "productbenefit";
		document.form1.submit();
	}
	
	function costSharingUpdate(){
		var checkBoxValue = document.getElementById("checkCostSharing").value;
		
		if (checkBoxValue == ''){
		
		}
	}
	function checkLimitAsCharge(){
		var checkBoxValue = document.getElementById("benefitLimit").value;
		
		if (checkBoxValue == ''){
		
		}
	}
	function checkReimburseLimitAsCharge(){
		var checkBoxValue = document.getElementById("reimbursementLimit").value;
		
		if (checkBoxValue == ''){
		
		}
	}
	function upgradeLimitUpdate(){
		var checkBoxValue = document.getElementById("checkBenefitUpgrade").value;
		
		
	}
	function changeMeasurement(){
		
		var itemCategoryId = document.getElementById("idItemCategory").value;
	
		var mu = document.getElementById("measurement");
		
		AJAXMeasurementService.changeMeasurement(itemCategoryId,{
			callback: function (measurementRes){
				mu.innerHTML = measurementRes;	
				
			}
		});
	}
	
	
</script>