

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
<form action="memberlimitlayer-form" method="POST"  name="form1" id="form_layout">
			<spring:bind path="memberLimitLayerForm.memberLimitLayerId">
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
				<td class="dataLabel"> Annual Limit : </td>				
			
			<td class="dataField">
			<spring:bind path="memberLimitLayerForm.annualLimit">
					<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<fmt:formatNumber><c:out value="${status.value}" /></fmt:formatNumber>" maxlength="22">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>


		
				
		
		

			<tr>
				<td class="dataLabel"> Actual Benefit Limit : </td>				
			
			<td class="dataField">
			<spring:bind path="memberLimitLayerForm.actualBenefitLimit">
					<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<fmt:formatNumber><c:out value="${status.value}" /></fmt:formatNumber>" maxlength="22">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>


		
				
		
		

			<tr>
				<td class="dataLabel"> Benefit Usage : </td>				
			
			<td class="dataField">
			<spring:bind path="memberLimitLayerForm.benefitUsage">
					<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<fmt:formatNumber><c:out value="${status.value}" /></fmt:formatNumber>" maxlength="22">
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
			<spring:bind path="memberLimitLayerForm.coSharePercentage">
					<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<fmt:formatNumber><c:out value="${status.value}" /></fmt:formatNumber>" maxlength="22">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>


		
				
		
		

			<tr>
				<td class="dataLabel"> Co Share Amount : </td>				
			
			<td class="dataField">
			<spring:bind path="memberLimitLayerForm.coShareAmount">
					<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<fmt:formatNumber><c:out value="${status.value}" /></fmt:formatNumber>" maxlength="22">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>


		
				
		
		

			<tr>
				<td class="dataLabel"> Status : </td>				
			

						<td class="dataField">
			<spring:bind path="memberLimitLayerForm.status">
	<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<fmt:formatNumber><c:out value="${status.value}"/></fmt:formatNumber>" maxlength="2">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
			

		
				
		
		

			

			
			
		
				
		
		

			
	
		
	

		
				
		
		

			

			
			
		
				
		
		

			
	
		
	

		
				
		
		

			

			
			
		
				
		
		

			
	
		
	

		
				
		
		

			

			
			

		
				
		
		

		
	<tr>
		<td class="dataLabel">Product Layer Limit Id : </td>		
		<td class="dataField">
		<spring:bind path="memberLimitLayerForm.productLimitLayerId">
		<input type="hidden" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>	
			<input type="text" name="productLimitLayerIdText" id="productLimitLayerIdId" value="" size=35>

		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>

		
	<tr>
		<td class="dataLabel">Member Id : </td>		
		<td class="dataField">
		<spring:bind path="memberLimitLayerForm.memberId">
		<input type="hidden" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>	
			<input type="text" name="memberIdText" id="memberIdId" value="" size=35>

		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>

		
	<tr>
		<td class="dataLabel">Product Id : </td>		
		<td class="dataField">
		<spring:bind path="memberLimitLayerForm.productId">
		<input type="hidden" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>	
			<input type="text" name="productIdText" id="productIdId" value="" size=35>

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
	
	
	
		
	function simpan (){

		document.form1.method = "POST";
		document.form1.action = "memberlimitlayer-form";
		document.form1.submit();
	}
	function kembali (){
		document.form1.navigation.value = "back";
		document.form1.action = "memberlimitlayer";
		document.form1.submit();
	}
	// forreign affairs
	
		// forreign affairs end
</script>