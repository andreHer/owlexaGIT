

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
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Register Provider Roll</h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>
	
<form action="providerrollhistory-form" method="POST"  name="form1" id="form_layout">
<spring:bind path="providerRollHistoryForm.providerRollHistoryId">
<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
</spring:bind>

<input type="hidden" name="navigation" value="${navigation }">

<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>

		<tr>
			<td class="dataLabel">&nbsp;</td>
			<td class="dataField">&nbsp;</td>
		    <td class="dataLabel"></td>
		    <td class="dataField"></td>
		</tr>
		<tr>
			<td class="dataLabel"> Provider : </td>				
				<spring:bind path="providerRollHistoryForm.providerId">
					<input type="hidden"  name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>">									
				</spring:bind>
			
			<td class="dataField">
				<spring:bind path="providerRollHistoryForm.providerName">
					<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" 
						disabled="disabled">
					<div id="fredcaption">
						<c:out value="${status.errorMessage}" />
					</div>
				</spring:bind>
			</td>
		    <td class="dataLabel"></td>
		    <td class="dataField"></td>
		</tr>
		<tr>
			<td class="dataLabel"> Delivery Date : *</td>	
			<td class="dataField">
				<spring:bind path="providerRollHistoryForm.deliveryDate">
					<input type="text" readonly="readonly"
						name="<c:out value="${status.expression}" />"
						id="<c:out value="${status.expression}"/>"
						value="<c:out value="${status.value}" />" maxlength="10">
					<img src="images/jscalendar.gif" alt="Enter Date"
						id="<c:out value="${status.expression}"/>_trigger"
						align="absmiddle" height="20" width="20">
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
		    <td class="dataLabel"></td>
		    <td class="dataField"></td>
		</tr>
		<tr>
			<td class="dataLabel"> Roll Stock Amount : *</td>				
			<td class="dataField">
				<spring:bind path="providerRollHistoryForm.rollStockAmount">
				<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" 
					 onkeypress="return isNumberKey(this,event)"  maxlength="10">
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
        <input title="Cancel [Alt+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:kembali()" name="Cancel" value=" Cancel " type="submit">         
	  </td>
      <td align="right"></td>
    </tr>
  </tbody>
</table>
</form>

<script language="javascript">
		
	function simpan (){

		document.form1.method = "POST";
		document.form1.action = "providerrollhistory-form";
		document.form1.submit();
	}
	function kembali (){
		document.form1.navigation.value = "back";
		document.form1.action = "providerrollhistory";
		document.form1.submit();
	}
	// forreign affairs
	
		// forreign affairs end
</script>