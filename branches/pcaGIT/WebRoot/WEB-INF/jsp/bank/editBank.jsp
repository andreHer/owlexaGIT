



<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

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
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Register Bank</h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>

<form action="bank-form" method="POST"  name="form1" id="form_layout">

<input type="hidden" name="navigation" value="">

<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>


	<spring:bind path="bankForm.bankId">
		<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>





			<tr>
				<td class="dataLabel"></td>				
			

			<td class="dataField">
		
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>


			<tr>
				<td class="dataLabel"> Bank Name : </td>				
			

			<td class="dataField">
			<spring:bind path="bankForm.bankName">
			
				<input type="text" size="50" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
	

		
				
		
		

			<tr>
				<td class="dataLabel"> Swift Code : </td>				
			

			<td class="dataField">
			<spring:bind path="bankForm.swiftCode">
				<input type="text" size="15" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
	

		
				
		
		

	

		
				
		
		

			<tr>
				<td class="dataLabel"> Clearing Code : </td>				
			

			<td class="dataField">
			<spring:bind path="bankForm.bankCode">
							<input type="text" size="15" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />">
			
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
			<spring:bind path="bankForm.description">
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
				<td class="dataLabel"></td>				
			

			<td class="dataField">
		
		</td>
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
	function simpan (){

		document.form1.method = "POST";
		document.form1.action = "bank-form";
		document.form1.submit();
	}
	function cancel (){
		document.form1.navigation.value = "back";
		document.form1.action = "bank";
		document.form1.submit();
	}
	// forreign affairs
			// forreign affairs end
</script>