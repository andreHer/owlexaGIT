<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<link rel="stylesheet" type="text/css" href="css/jquery/autocomplete/jquery.autocomplete.css"/>

<script type="text/javascript" src="scripts/owlexa.function.js"></script>
<script type="text/javascript" src="scripts/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="scripts/jquery/plugin/jquery.autocomplete.pack.js"></script>
<script type="text/javascript" src="scripts/owlexa.function.js"></script>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Register Product</h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>
<form action="product-form" method="POST"  name="form1" id="form_layout">
<input type="hidden" name="navigation" value="">
	<spring:bind path="productForm.productId">
		<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>
		<spring:bind path="productForm.productReference">
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
				<td class="dataLabel"> Product Name : </td>				
			

			<td class="dataField">
			<spring:bind path="productForm.productName">
				<input type="text" size="35" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" />
				<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
			</spring:bind>
		</td>
		<td class="dataLabel">Policy Number : </td>		
		<td class="dataField">

		<spring:bind path="productForm.policyNumber">
			<input type="text" name="<c:out value="${status.expression}"/>" id="policyNumber" value="<c:out value="${status.value}" />" size=35>
		</spring:bind>	
		<spring:bind path="productForm.policyId">
		<input type="hidden" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="11"> 
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>	
		</td>
	</tr>

			<tr>
				<td class="dataLabel"> Product Code : </td>				
			

			<td class="dataField">
			<spring:bind path="productForm.productCode">
		<input type="text" size="25" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" />	
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
		<td class="dataLabel">Product Type : </td>		
		<td class="dataField">
		<spring:bind path="productForm.productType">
			<select name="<c:out value="${status.expression}"/>">
				<c:forEach items="${productType}" var="productType">
					<option value="<c:out value="${productType.productTypeId}" />" <c:if test="${status.value eq productType.productTypeId}">selected</c:if>><c:out value="${productType.productTypeName}" /></option>
				</c:forEach>
			</select>
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>	

		</td>
	</tr>
	

	<tr>
		<td class="dataLabel">Service Type : </td>		
		<td class="dataField">
		<spring:bind path="productForm.caseCategory">
		<select name="<c:out value="${status.expression}"/>">
				<c:forEach items="${caseCategories}" var="cc">
					<option value="<c:out value="${cc.caseCategoryId}" />" <c:if test="${status.value eq cc.caseCategoryId}">selected</c:if>><c:out value="${cc.caseCategoryName}" /></option>
				</c:forEach>
			</select>
		
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>	
		</td>
		<td class="dataLabel">Product Limit Type : </td>		
		<td class="dataField">
		<spring:bind path="productForm.productLimitType">
		<select name="<c:out value="${status.expression}"/>">
				<c:forEach items="${productLimitType}" var="limitType">
					<option value="<c:out value="${limitType.productLimitTypeId}" />" <c:if test="${status.value eq limitType.productLimitTypeId}">selected</c:if>><c:out value="${limitType.productLimit}" /></option>
				</c:forEach>
			</select>
		
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>	
		</td>
	   
	</tr>
		<tr>
		<td class="dataLabel">Shared Product : </td>		
		<td class="dataField">
			<spring:bind path="productForm.sharedProductId">
			<input type="hidden" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="11"> 
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>	
			
			<spring:bind path="productForm.sharedProductName">
				<input type="text" name="<c:out value="${status.expression}"/>" id="sharedProductName" value="<c:out value="${status.value}" />" size=35>
			</spring:bind>	
		
		</td>	   
		<td class="dataLabel">Is Using Prorate Limit : </td>		
		<td class="dataField">
		
			<spring:bind path="productForm.isUsingProrateLimit">
				<select name="<c:out value="${status.expression}" />">
					<option value="" > -- PILIH SALAH SATU -- </option>					
					<option value="1" <c:if test="${status.value eq 1 }">selected</c:if>>YA, Menggunakan Pro Rate</option>
					<option value="0" <c:if test="${status.value eq 0 }">selected</c:if>>Tidak</option>					
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
		<td class="dataLabel">Service Class : </td>		
		<td class="dataField">
		<spring:bind path="productForm.productClass">
		<input type="text" size="25" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" />
		
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>	
		</td>
		<td class="dataLabel">Card Type : </td>		
		<td class="dataField">
		<spring:bind path="productForm.isEdcEnabled">
			<select name="<c:out value="${status.expression }" />">
						<option value="-1">--- PILIH SALAH SATU ---</option>
						<c:forEach items="${cardTypeList}" var="cardType">
							<option value="<c:out value="${cardType.cardTypeId}" />" <c:if test="${status.value eq cardType.cardTypeId }">selected</c:if>><c:out value="${fn:toUpperCase(cardType.cardTypeName)}" /></option>											
						</c:forEach>									
					</select>
		</spring:bind>	
		</td>
	   
	</tr>
	
	<tr>
		<td class="dataLabel">Room Class : </td>		
		<td class="dataField">
		<spring:bind path="productForm.roomRate">
		<input type="text" size="25" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" />
		
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>	
		</td>
		<td class="dataLabel">Benefit Show on EDC : </td>		
		<td class="dataField">
		<spring:bind path="productForm.benefitShowOnEdc">
			<select name="<c:out value="${status.expression}"/>">
					<option value="-1">--- PILIH SALAH SATU ---</option>				
					<option value="0" <c:if test="${status.value eq 0}">selected</c:if>>NO</option>				
					<option value="1" <c:if test="${status.value eq 1}">selected</c:if>>YES</option>
			</select>
		</spring:bind>	
		</td>
	   
	</tr>
		<tr>
		<td class="dataLabel">Treatment Class : </td>		
		<td class="dataField">
			<spring:bind path="productForm.treatmentClassId">
				<select name="<c:out value="${status.expression}" />">
					<option value="">--- PILIH SALAH SATU ---</option>
					<option value="5" <c:if test="${status.value eq 5 }">selected</c:if>>VVIP / Super VIP</option>
					<option value="4" <c:if test="${status.value eq 4 }">selected</c:if>>VIP</option>
					<option value="3" <c:if test="${status.value eq 3 }">selected</c:if>>Kelas I</option>
					<option value="2" <c:if test="${status.value eq 2 }">selected</c:if>>Kelas II</option>
					<option value="1" <c:if test="${status.value eq 1 }">selected</c:if>>Kelas III</option>
				</select>		
			</spring:bind>	
		</td>
		<td class="dataLabel">Limit Using Salary : </td>		
		<td class="dataField">
		
			<spring:bind path="productForm.isUsingSalary">
				<select name="<c:out value="${status.expression}" />">
					<option value="">--- PILIH SALAH SATU ---</option>					
					<option value="0" <c:if test="${status.value eq 0 }">selected</c:if>>TIDAK</option>
					<option value="1" <c:if test="${status.value eq 1 }">selected</c:if>>YA, Menggunakan Salary</option>
				</select>		
			</spring:bind>	
		</td>	   
	</tr>
	<tr>
		<td class="dataLabel">Product Currency : </td>		
		<td class="dataField">
		
			<spring:bind path="productForm.productCurrencyId">
				<select name="<c:out value="${status.expression}" />">
					<c:forEach items="${currencyList}" var="currency">
						<option value="<c:out value="${currency.currencyId }" />" <c:if test="${status.value eq currency.currencyId }">selected</c:if>><c:out value="${currency.currencyName}" /></option>
					</c:forEach>
				</select>		
			</spring:bind>	
		</td>	   
		<td class="dataLabel">Salary Multiplier : </td>		
		<td class="dataField">
		
			<spring:bind path="productForm.salaryMultiplier">
				<input type="text" onkeypress="return isNumberKey(this,event)" size="5" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="22">&nbsp;&nbsp; 	
			</spring:bind>	
		</td>	   
	</tr>		
			<tr>
				<td class="dataLabel">&nbsp;</td>				
			

			<td class="dataField">

		</td>
		<td class="dataLabel">&nbsp;</td>		
		<td class="dataField">
		</td>
	</tr>					


			<tr>
				<td class="dataLabel"> Per Disability Limit : </td>				
			
			<td class="dataField">
			<spring:bind path="productForm.maxClaimValue">
					<input type="text" onkeypress="return isNumberKey(this,event)" size="25" id="maxClaimValueText" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="22">&nbsp;&nbsp; 
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			
				
			</td>
	    <td class="dataLabel"> Waiting Period : </td>				
			
			<td class="dataField">
			<spring:bind path="productForm.waitingPeriodeUndefined">
					<input type="text" size="25" onkeypress="return isNumberKey(this,event)"  id="waitingPeriodText" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="22">&nbsp;&nbsp;
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind> 

			</td>
	</tr>

			<tr>
				<td class="dataLabel"> Annual Limit Value : </td>				
			
			<td class="dataField">
			<spring:bind path="productForm.annualLimitValue">
					<input type="text" size="25" onkeypress="return isNumberKey(this,event)" id="maxAnnualValueText" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="22">&nbsp;&nbsp;
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind> 

			</td>
	   <td class="dataLabel"> Disability Length : </td>				
			
			<td class="dataField">
			<spring:bind path="productForm.disabilityLengthUndefined">
					<input type="text" size="25" onkeypress="return isNumberKey(this,event)"  id="disabilityLengthText" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="22">&nbsp;&nbsp;
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind> 

			</td>
	</tr>
			<tr>
				<td class="dataLabel"> Reimbursement Limit : </td>
				<td class="dataField">
					<spring:bind path="productForm.reimbursementAnnualLimit">
							<input type="text" size="25" onkeypress="return isNumberKey(this,event)" id="maxAnnualValueText" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="22">&nbsp;&nbsp;
						<div id="fredcaption">
							<c:out value="${status.errorMessage}" />
						</div>
					</spring:bind>
				</td>
		   		<td class="dataLabel"> Setting Overlimit : </td>				
				
				<td class="dataField">
					<spring:bind path="productForm.excessPaymentType">
						<select name="<c:out value="${status.expression}"/>">
							<option value="-1">--- PILIH SALAH SATU ---</option>				
							<option value="1" <c:if test="${status.value eq 1}">selected</c:if>>DIBAYAR DITEMPAT</option>				
							<option value="2" <c:if test="${status.value eq 2}">selected</c:if>>DIBAYAR KEMUDIAN</option>
						</select>
					</spring:bind> 
	
				</td>
			</tr>
	
			<tr>
				<td class="dataLabel">&nbsp;</td>				
			

			<td class="dataField">

		</td>
		<td class="dataLabel">&nbsp;</td>		
		<td class="dataField">
		</td>
	</tr>					
	

	<tr>
				<td class="dataLabel"> Reimbursement Percentage : </td>				
			
			<td class="dataField">
			<spring:bind path="productForm.reimbursementPercentage">
					<input type="text" size="25"  onkeypress="return isNumberKey(this,event)" id="maxAnnualValueText" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="22">&nbsp;&nbsp;
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind> 

			</td>
	   <td class="dataLabel"> Overseas Percentage : </td>				
			
			<td class="dataField">
			<spring:bind path="productForm.overseasPercentage">
					<input type="text" size="25" onkeypress="return isNumberKey(this,event)" id="disabilityLengthText" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="22">&nbsp;&nbsp;
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind> 

			</td>
	</tr>

	<tr>
				<td class="dataLabel"> Cashless Percentage : </td>				
			
			<td class="dataField">
			<spring:bind path="productForm.cashlessPercentage">
					<input type="text" size="25" onkeypress="return isNumberKey(this,event)" id="maxAnnualValueText" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="22">&nbsp;&nbsp;
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind> 

			</td>
	   <td class="dataLabel"> Domestic Percentage : </td>				
			
			<td class="dataField">
			<spring:bind path="productForm.domesticPercentage">
					<input type="text" size="25" onkeypress="return isNumberKey(this,event)"  id="disabilityLengthText" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="22">&nbsp;&nbsp;
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind> 

			</td>
	</tr>



<tr>
				<td class="dataLabel"> Product Description : </td>				
			

		    <td class="dataField" colspan="3">
			<spring:bind path="productForm.productDescription">
			<textarea cols="60" rows="8" class="inputbox" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
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
    
    
    $("#policyNumber").autocomplete("policy?navigation=lookupjson", {
        max: 7,
        dataType: "json",
        parse: function(data) {
            return $.map(data, function(row) {
                return {
                    data: row,
                    value: row.number,
                    result: row.number
                }
            });
        },
        formatItem: function(row) {
            return "<font color='#000' >"+ row.name + "-" + row.number+"</font>" ;
        }
    }).bind("result", function(data, value){
        $(this).parents("dd").find(".error_message").hide();
        $("#policyId").val(value.id);
        $("#clientId").val(value.clientId);
    });
    
    $("#sharedProductName").autocomplete("product?navigation=lookuppolicyjson", {
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
            return "<font color='#000' >"+ row.name + "-" + row.code+"</font>" ;
        }
        ,
	         extraParams: {
	       		
	       		policyId: function() { return $("#policyId").val(); }
	   		}
    }).bind("result", function(data, value){
        $(this).parents("dd").find(".error_message").hide();
        $("#sharedProductId").val(value.id);
    });
    
});
	

	function unlimitedClaimValue(){
		var maxClaimElem = 	document.getElementById("maxClaimValueText");

		if (maxClaimElem.disabled == true){
			maxClaimElem.disabled = false;
		}
		else {
			maxClaimElem.disabled = true;
			
		}
	}
	function unlimitedAnnualValue (){
		var maxAnnualElem = 	document.getElementById("maxAnnualValueText");
		if (maxAnnualElem.disabled == true){
			maxAnnualElem.disabled = false;
		}
		else {
			maxAnnualElem.disabled = true;
		}
	}
	function undefinedDisabilityLength(){
	
		var disabilityLength = 	document.getElementById("disabilityLengthText");
		if (disabilityLength.disabled == true){
			disabilityLength.disabled = false;
		}
		else {
			disabilityLength.disabled = true;
		}
	}
	function undefinedWaitingPeriod(){
		var waitingPeriod = 	document.getElementById("waitingPeriodText");
		if (waitingPeriod.disabled == true){
			waitingPeriod.disabled = false;
		}
		else {
			waitingPeriod.disabled = true;
		}
	}
	function simpan (){

		document.form1.method = "POST";
		document.form1.action = "product-form";
		<c:choose>
			<c:when test="${navigation eq 'listclient'}">
				document.form1.navigation.value = "listclient";
				document.form1.clientId.value = <c:out value="${clientId}" />
			</c:when>
			<c:when test="${navigation eq 'listpolicy'}">
				document.form1.navigation.value = "listpolicy";
				document.form1.policyId.value = <c:out value="${policyId}" />
			</c:when>
			<c:when test="${navigation eq 'tambah'}">
				document.form1.navigation.value = "tambah";
			</c:when>
		</c:choose>
		document.form1.submit();
	}
	function cancel (){	
		<c:choose>
			<c:when test="${navigation eq 'update'}">
				document.form1.navigation.value = "detail";
			</c:when>
			<c:when test="${navigation eq 'listclient'}">
				document.form1.navigation.value = "listclient";
				document.form1.clientId.value = <c:out value="${clientId}" />
			</c:when>
			<c:when test="${navigation eq 'listpolicy'}">
				document.form1.navigation.value = "listpolicy";
				document.form1.policyId.value = <c:out value="${policyId}" />
			</c:when>
		</c:choose>
		document.form1.action = "product";
		document.form1.submit();
	}
	
</script>