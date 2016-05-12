

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
<form action="clientcontract-form" method="POST"  name="form1" id="form_layout">
			<spring:bind path="clientContractForm.clientContractId">
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
		<td class="dataLabel">Client : </td>		
		<td class="dataField">
		<spring:bind path="clientContractForm.clientId">
			<input type="hidden" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
		</spring:bind>	
		<spring:bind path="clientContractForm.clientName">
			<input type="text" size=40 name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
		</spring:bind>
			

		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>

			<tr>
				<td class="dataLabel"> Client Contract Number : </td>				
			
	
						<td class="dataField">
				<spring:bind path="clientContractForm.clientContractNumber">
				<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" >
					<div id="fredcaption">
						<c:out value="${status.errorMessage}" />
					</div>
				</spring:bind>
			</td>
				    		<td class="dataLabel">Currency : </td>		
		<td class="dataField">
		<spring:bind path="clientContractForm.currencyId">
			<select name="<c:out value="${status.expression}" />">
				<option value=""> -- SELECT ONE -- </option>
				<c:forEach items="${currencyList}" var="currency">
					<option value="<c:out value="${currency.currencyId}" />" <c:if test="${currency.currencyId eq status.value}">selected</c:if>><c:out value="${currency.currencyName}" /></option>
				</c:forEach>
			</select>
		</spring:bind>				

		</td>
		</tr>
			
	<tr>
		<td class="dataLabel">Contract Type : </td>		
		<td class="dataField">
		<spring:bind path="clientContractForm.contractTypeId">
			<select name="<c:out value="${status.expression}" />">
				<option value=""> -- SELECT ONE -- </option>
				<c:forEach items="${typeList}" var="type">
					<option value="<c:out value="${type.contractTypeId}" />" <c:if test="${type.contractTypeId eq status.value}">selected</c:if>><c:out value="${type.contractTypeName}" /></option>
				</c:forEach>
			</select>
		</spring:bind>				

		</td>
	    		<td class="dataLabel">Product Type : </td>		
		<td class="dataField">
		<spring:bind path="clientContractForm.productTypeId">
			<select name="<c:out value="${status.expression}" />">
				<option value=""> -- SELECT ONE -- </option>
				<c:forEach items="${productTypeList}" var="type">
					<option value="<c:out value="${type.productTypeId}" />" <c:if test="${type.productTypeId eq status.value}">selected</c:if>><c:out value="${type.productTypeName}" /></option>
				</c:forEach>
			</select>
		</spring:bind>				

		</td>
	</tr>
		
			<tr>
				<td class="dataLabel"> Contract Value Per Member : </td>				
			
			<td class="dataField">
			<spring:bind path="clientContractForm.contractValuePerMember">
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
			<td class="dataLabel"> Is Using Proration : </td>				
			<td class="dataField">
				<spring:bind path="clientContractForm.isUsingProration">
					<select name="<c:out value="${status.expression}" />">
						<option value=""> -- SELECT ONE -- </option>
						<option value="0" <c:if test="${status.value eq 0}">selected</c:if> > NO, Flat Rate </option>
						<option value="1" <c:if test="${status.value eq 1}">selected</c:if>> YES, Pro Rate </option>
					</select>
				</spring:bind>
			</td>
	    	
			<td class="dataLabel"> Pro-Rate Method : </td>				
			<td class="dataField">
				<spring:bind path="clientContractForm.prorateType">
					<select name="<c:out value="${status.expression}" />">
						<option value="-1" <c:if test="${status.value eq -1 }">selected</c:if>> NO PRORATE </option>
						<option value="1" <c:if test="${status.value eq 1}">selected</c:if> > MONTHLY </option>
						<option value="2" <c:if test="${status.value eq 2}">selected</c:if>> QUARTERLY </option>
						<option value="3" <c:if test="${status.value eq 3}">selected</c:if>> SEMESTER </option>
					</select>
				</spring:bind>
			</td>
	</tr>
			
	<tr>
				<td class="dataLabel"> Is Using Pricing Volume : </td>				
			

						<td class="dataField">
			<spring:bind path="clientContractForm.isUsingVolumeLevel">
				<select name="<c:out value="${status.expression}" />">
					<option value="" >-- PILIH SALAH SATU --</option>
					<option value="1" <c:if test="${status.value eq 1 }">selected</c:if>>YA</option>
					<option value="0" <c:if test="${status.value eq 0 }">selected</c:if>>TIDAK</option>					
				</select>
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
	    <td class="dataLabel"> Is Using Refund : </td>				
			

						<td class="dataField">
			<spring:bind path="clientContractForm.isRefundAvailable">
				<select name="<c:out value="${status.expression}" />">
					<option value="" >-- PILIH SALAH SATU --</option>
					<option value="1" <c:if test="${status.value eq 1 }">selected</c:if>>YA</option>
					<option value="0" <c:if test="${status.value eq 0 }">selected</c:if>>TIDAK</option>					
				</select>
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
	</tr>
		
				
		
		

			<tr>
				<td class="dataLabel"> Payment Periode : </td>				
			

						<td class="dataField">
			<spring:bind path="clientContractForm.paymentPeriode">
				<select name="<c:out value="${status.expression}" />">
					<option value="1" <c:if test="${status.value eq 1 }">selected</c:if>>MONTHLY</option>
					<option value="2" <c:if test="${status.value eq 2 }">selected</c:if>>QUARTERLY</option>
					<option value="3" <c:if test="${status.value eq 3 }">selected</c:if>>SEMESTER</option>
					<option value="4" <c:if test="${status.value eq 4 }">selected</c:if>>ANNUALY</option>
				</select>
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
	    				<td class="dataLabel"> Membership Periode : </td>				
			

						<td class="dataField">
			<spring:bind path="clientContractForm.membershipPeriode">
				<select name="<c:out value="${status.expression}" />">
					<option value="1" <c:if test="${status.value eq 1 }">selected</c:if>>MONTHLY</option>
					<option value="2" <c:if test="${status.value eq 2 }">selected</c:if>>QUARTERLY</option>
					<option value="3" <c:if test="${status.value eq 3 }">selected</c:if>>SEMESTER</option>
					<option value="4" <c:if test="${status.value eq 4 }">selected</c:if>>ANNUALY</option>
				</select>
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
	</tr>
			<tr>
				<td class="dataLabel"> Billing Cut Off Date : </td>				
				<td class="dataField">
					<spring:bind path="clientContractForm.billingCutOffDate">				
					<input type="text" size="5" name="<c:out value="${status.expression}" />" value="<fmt:formatNumber><c:out value="${status.value}"/></fmt:formatNumber>" maxlength="5">
						<div id="fredcaption">
							<c:out value="${status.errorMessage}" />
						</div>
					</spring:bind>
				</td>
		    <td class="dataLabel"> Invoice Due Length (days) : </td>				
				<td class="dataField">
					<spring:bind path="clientContractForm.invoiceDueLength">				
					<input type="text" size="5" name="<c:out value="${status.expression}" />" value="<fmt:formatNumber><c:out value="${status.value}"/></fmt:formatNumber>" maxlength="5">
						<div id="fredcaption">
							<c:out value="${status.errorMessage}" />
						</div>
					</spring:bind>
				</td>
		</tr>			
		<tr>
				<td class="dataLabel"> Discount (%) : </td>				
				<td class="dataField">
					<spring:bind path="clientContractForm.discountPercentage">				
					<input type="text" size="5" name="<c:out value="${status.expression}" />" value="<fmt:formatNumber><c:out value="${status.value}"/></fmt:formatNumber>" maxlength="5">
						<div id="fredcaption">
							<c:out value="${status.errorMessage}" />
						</div>
					</spring:bind>
				</td>
		    <td class="dataLabel"> Discount (amount) : </td>				
				<td class="dataField">
					<spring:bind path="clientContractForm.discountAmountPerQuantity">				
					<input type="text" size="5" name="<c:out value="${status.expression}" />" value="<fmt:formatNumber><c:out value="${status.value}"/></fmt:formatNumber>" maxlength="5">
						<div id="fredcaption">
							<c:out value="${status.errorMessage}" />
						</div>
					</spring:bind>
				</td>
		</tr>			
		<tr>
			<td class="dataLabel"> Contract Start Date : </td>				
			<td class="dataField">
				<spring:bind path="clientContractForm.contractStartDate">
					<input type="text" size="25" name="<c:out value="${status.expression}" />" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="10">
					<img src="images/jscalendar.gif" alt="Enter Date" id="<c:out value="${status.expression}"/>_trigger" align="absmiddle" height="20" width="20">
					<script type="text/javascript">
    					Calendar.setup({
        					inputField     :    "<c:out value="${status.expression}"/>",     // id of the input field
        					ifFormat       :    "%Y-%m-%d",      // format of the input field
        					button         :    "<c:out value="${status.expression}"/>_trigger",  // trigger for the calendar (button ID)
        					align          :    "Bl",           // alignment (defaults to "Bl")
        					singleClick    :    true
    					});
				 	</script>
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
				<td class="dataLabel"> Contract End Date : </td>				
				<td class="dataField">
			<spring:bind path="clientContractForm.contractEndDate">
					<input type="text" size="25" name="<c:out value="${status.expression}" />" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="10">
					<img src="images/jscalendar.gif" alt="Enter Date" id="<c:out value="${status.expression}"/>_trigger" align="absmiddle" height="20" width="20">
					<script type="text/javascript">
    					Calendar.setup({
        					inputField     :    "<c:out value="${status.expression}"/>",     // id of the input field
        					ifFormat       :    "%Y-%m-%d",      // format of the input field
        					button         :    "<c:out value="${status.expression}"/>_trigger",  // trigger for the calendar (button ID)
        					align          :    "Bl",           // alignment (defaults to "Bl")
        					singleClick    :    true
    					});
				 	</script>
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
	</tr>
		<tr>
				<td class="dataLabel"> Description : </td>				
			
	
						<td class="dataField" colspan=3>
				<spring:bind path="clientContractForm.description">
				<textarea  cols="60" rows="8" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
					<div id="fredcaption">
						<c:out value="${status.errorMessage}" />
					</div>
				</spring:bind>
			</td>
			
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
	
	
		
	function simpan (){

		document.form1.method = "POST";
		document.form1.action = "clientcontract-form";
		document.form1.submit();
	}
	function kembali (){
		document.form1.navigation.value = "back";
		document.form1.action = "clientcontract";
		document.form1.submit();
	}
	// forreign affairs
	
		// forreign affairs end
</script>