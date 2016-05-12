

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<!-- <link rel="stylesheet" type="text/css" href="css/jquery/autocomplete/jquery.autocomplete.css"/> -->

<!-- <script type="text/javascript" src="scripts/jquery/jquery-1.4.4.min.js"></script> -->
<!-- <script type="text/javascript" src="scripts/jquery/plugin/jquery.autocomplete.pack.js"></script> -->

	<style>
		.ui-autocomplete-loading {
		  background: white url("images/owlexa/ui-anim_basic_16x16.gif") right center no-repeat;
		}
	</style>


<%
String alert = (String) request.getAttribute("alert");
if (alert != null && !alert.trim().equals("")) {%>
	<div id="warning">
		<c:out value="${alert}"></c:out>
	</div>
	<%}%>
<form action="cardstock-form" method="POST"  name="form1" id="form_layout">
			<spring:bind path="cardStockForm.id">
			<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
		</spring:bind>

		<input type="hidden" name="navigation" value="listclient">
<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>

		<tr>
			<td class="dataLabel">&nbsp;</td>
			<td class="dataField">&nbsp;</td>
		    <td class="dataLabel"></td>
		    <td class="dataField"></td>
		</tr>

		<tr>
			<td class="dataLabel"> Client : </td>
			<td class="dataField">
				<spring:bind path="cardStockForm.clientId">
					<input type="hidden" name="<c:out value="${status.expression}" />" value="<fmt:formatNumber><c:out value="${status.value}"/></fmt:formatNumber>" >					
				</spring:bind>
				<spring:bind path="cardStockForm.clientName">
					<input type="text" size=45 name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" >					
				</spring:bind>
			</td>
		    <td class="dataLabel"></td>
		    <td class="dataField"></td>
		</tr>







			<tr>
				<td class="dataLabel"> Card Stock Batch Number : </td>				
			
	
						<td class="dataField">
				<spring:bind path="cardStockForm.cardStockBatchNumber">
				<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" >
					<div id="fredcaption">
						<c:out value="${status.errorMessage}" />
					</div>
				</spring:bind>
			</td>
			<td class="dataLabel"></td>
			<td class="dataField"></td>
		</tr>
			

		
				
		
		

			<tr>
				<td class="dataLabel"> Batch Date : </td>				
			

						<td class="dataField">
			<spring:bind path="cardStockForm.batchDate">
					<input type="text" size="15" name="<c:out value="${status.expression}" />" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="10">
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
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
		<tr>
			<td class="dataLabel"> Additional Stock : </td>				
		<td class="dataField">
		<spring:bind path="cardStockForm.additionalStock">
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
		document.form1.action = "cardstock-form";
		document.form1.submit();
	}
	function cancel (){
		document.form1.navigation.value = "listclient";
		document.form1.action = "cardstock";				
		document.form1.clientId.value = <c:out value="${clientId}" />
		document.form1.submit();
		
	}
	// forreign affairs
	
		// forreign affairs end
</script>