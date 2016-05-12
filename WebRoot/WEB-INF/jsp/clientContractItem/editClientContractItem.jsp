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
<form action="clientcontractitem-form" method="POST"  name="form1" id="form_layout">
			<spring:bind path="clientContractItemForm.clientContractItemId">
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
		<td class="dataLabel">Client Contract : </td>		
		<td class="dataField">
		<spring:bind path="clientContractItemForm.clientContractId">
			<input type="hidden" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >			
		</spring:bind>	
		<spring:bind path="clientContractItemForm.contractNumber">
			<input type="text" name="clientContractIdText" id="clientContractIdId" value="<c:out value="${status.value}" />" size=35 readonly="readonly">
		</spring:bind>

		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>

		
	<tr>
		<td class="dataLabel">Item : </td>		
		<td class="dataField">
		<spring:bind path="clientContractItemForm.itemId">
			<select name="<c:out value="${status.expression}" />">
				<c:forEach items="${itemList}" var="item">
					<option value="<c:out value="${item.itemId}" />" <c:if test="${status.value eq item.itemId}">selected</c:if>><c:out value="${item.itemName}" /></option>
				</c:forEach>
			</select>
		</spring:bind>	
			

		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
		<tr>
		<td class="dataLabel">Contract Unit : </td>		
		<td class="dataField">
		<spring:bind path="clientContractItemForm.contractUnit">
			<select name="<c:out value="${status.expression}" />">
				<option value=""> --- SELECT ONE --- </option>				
				<option value="1" <c:if test="${status.value eq '1'}">selected</c:if>>PER CLAIM</option>
				<option value="2" <c:if test="${status.value eq '2'}">selected</c:if>>PER MEMBER</option>
				<option value="3" <c:if test="${status.value eq '3'}">selected</c:if>>MONTHLY FIXED FEE</option>
				<option value="4" <c:if test="${status.value eq '4'}">selected</c:if>>QUARTER FIXED FEE</option>
				<option value="5" <c:if test="${status.value eq '5'}">selected</c:if>>SEMESTER FIXED FEE</option>
				<option value="6" <c:if test="${status.value eq '6'}">selected</c:if>>ANNUAL FIXED FEE</option>				
			</select>
		</spring:bind>
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>

	<tr>
		<td class="dataLabel">Claim Type : </td>		
		<td class="dataField">
		<spring:bind path="clientContractItemForm.claimType">
			<select name="<c:out value="${status.expression}" />">
				<option value=""> --- SELECT ONE --- </option>				
				<option value="1" <c:if test="${status.value eq '1'}">selected</c:if>>REIMBURSEMENT</option>
				<option value="2" <c:if test="${status.value eq '2'}">selected</c:if>>CASHLESS</option>
				<option value="3" <c:if test="${status.value eq '3'}">selected</c:if>>ALL</option>
			</select>
		</spring:bind>	
			

		</td>
	    <td class="dataLabel">Case Category : </td>		
		<td class="dataField">
		<spring:bind path="clientContractItemForm.caseCategoryId">
			<select name="<c:out value="${status.expression}" />">
				<option value="">ALL CASE CATEGORY</option>
				<c:forEach items="${caseCategoryList}" var="cc">
					<option value="<c:out value="${cc.caseCategoryId}" />" <c:if test="${status.value eq cc.caseCategoryId}">selected</c:if>><c:out value="${cc.caseCategoryName}" /></option>
				</c:forEach>
			</select>
		</spring:bind>	
	</tr>
	<tr>
		<td class="dataLabel">Claim Status Billing : </td>		
		<td class="dataField">
		<spring:bind path="clientContractItemForm.claimStatusProcess">
			<select name="<c:out value="${status.expression}" />">
				<option value=""> -- PILIH SALAH SATU -- </option>
				<option value="13" <c:if test="${status.value eq '13'}">selected</c:if>>CDV ISSUED</option>
				<option value="6" <c:if test="${status.value eq '6'}">selected</c:if>>PAID</option>
				<option value="4" <c:if test="${status.value eq '4'}">selected</c:if>>REJECTED</option>
						
			</select>
		</spring:bind>	
			

		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>

	

		<tr>
				<td class="dataLabel"> Volume Start : </td>				
			
			<td class="dataField">
			<spring:bind path="clientContractItemForm.volumeStart">
					<input type="text" size="15" name="<c:out value="${status.expression}" />" value="<fmt:formatNumber><c:out value="${status.value}" /></fmt:formatNumber>" maxlength="22">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
				<td class="dataLabel"> Volume End : </td>				
			
			<td class="dataField">
			<spring:bind path="clientContractItemForm.volumeEnd">
					<input type="text" size="15" name="<c:out value="${status.expression}" />" value="<fmt:formatNumber><c:out value="${status.value}" /></fmt:formatNumber>" maxlength="22">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>

	</tr>



			<tr>
				<td class="dataLabel"> Item Price : </td>				
			
			<td class="dataField">
			<spring:bind path="clientContractItemForm.itemPrice">
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
				<td class="dataLabel"> Discount (%) : </td>				
			
			<td class="dataField">
			<spring:bind path="clientContractItemForm.discountPercentage">
					<input type="text" size="15" name="<c:out value="${status.expression}" />" value="<fmt:formatNumber><c:out value="${status.value}" /></fmt:formatNumber>" maxlength="22">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
				<td class="dataLabel"> Discount (amount) : </td>				
			
			<td class="dataField">
			<spring:bind path="clientContractItemForm.discountAmountPerQuantity">
					<input type="text" size="15" name="<c:out value="${status.expression}" />" value="<fmt:formatNumber><c:out value="${status.value}" /></fmt:formatNumber>" maxlength="22">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
	</tr>
				
		


			<tr>
				<td class="dataLabel"> Description : </td>				
			
	
						<td class="dataField">
				<spring:bind path="clientContractItemForm.description">
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
        <a href="clientcontract?navigation=detail&clientContractId=<c:out value="${clientContractItemForm.clientContractId}" />"><input title="Cancel [Alt+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" name="Cancel" value=" Cancel " type="button"></a>         
	  </td>
      <td align="right"></td>
    </tr>
  </tbody>
</table>
</form>

<script language="javascript">
	
	
		
	function simpan (){

		document.form1.method = "POST";
		document.form1.action = "clientcontractitem-form";
		document.form1.submit();
	}
	
</script>