<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>



<link rel="stylesheet" type="text/css" href="css/jquery/autocomplete/jquery.autocomplete.css"/>
<script type="text/javascript" src="scripts/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="scripts/jquery/plugin/jquery.autocomplete.pack.js"></script>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Register Invoice</h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>
<%
String alert = (String) request.getAttribute("alert");
if (alert != null && !alert.trim().equals("")) {%>
	<div id="warning">
		<c:out value="${alert}"></c:out>
	</div>
	<%}%>

<br />
<form action="invoice-form" method="POST"  name="form1" id="form_layout">

<input type="hidden" name="navigation" value="">
<spring:bind path="invoiceForm.invoiceId">
	<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
	<div id="fredcaption">
		<c:out value="${status.errorMessage}" />
	</div>
</spring:bind>
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
							<td class="dataLabel"> Member Group : * </td>
							<td class="dataField">
								<spring:bind path="invoiceForm.memberGroupId">
										<input type="hidden" name="<c:out value="${status.expression}" />" id="memberGroupId" value="<c:out value="${status.value}" />" maxlength="10">									
								</spring:bind>
								<spring:bind path="invoiceForm.memberGroupName">
										<input type="text" size="35" name="<c:out value="${status.expression}" />" id="memberGroupName" value="<c:out value="${status.value}" />" >									
								</spring:bind>
							</td>
						    <td class="dataLabel"></td>
						    <td class="dataField"></td>
						</tr>
						<tr>
							<td class="dataLabel"> Invoice Date : * </td>
							<td class="dataField">
								<spring:bind path="invoiceForm.invoiceDate">
										<input type="text" size="35" name="<c:out value="${status.expression}" />" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="10">
										<img src="images/jscalendar.gif" alt="Enter Date" id="<c:out value="${status.expression}"/>_trigger" align="absmiddle" height="20" width="20">
										<script type="text/javascript">
					    					Calendar.setup({
					        					inputField     :    "<c:out value="${status.expression}"/>",     // id of the input field
					        					ifFormat       :    "%Y-%m-%d",      // format of the input field
					        					button         :    "<c:out value="${status.expression}"/>_trigger",  // trigger for the calendar (button ID)
					        					align          :    "Tl",           // alignment (defaults to "Bl")
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
							<td class="dataLabel"> Invoice Due Date : * </td>		
							<td class="dataField">
								<spring:bind path="invoiceForm.invoiceDueDate">
										<input type="text" size="35" name="<c:out value="${status.expression}" />" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="10">
										<img src="images/jscalendar.gif" alt="Enter Date" id="<c:out value="${status.expression}"/>_trigger" align="absmiddle" height="20" width="20">
										<script type="text/javascript">
					    					Calendar.setup({
					        					inputField     :    "<c:out value="${status.expression}"/>",     // id of the input field
					        					ifFormat       :    "%Y-%m-%d",      // format of the input field
					        					button         :    "<c:out value="${status.expression}"/>_trigger",  // trigger for the calendar (button ID)
					        					align          :    "Tl",           // alignment (defaults to "Bl")
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
							<td class="dataLabel"> Description : </td>		
							<td class="dataField" colspan="2">
								<spring:bind path="invoiceForm.description">
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
							<td class="dataField"></td>
						    <td class="dataLabel"></td>
						    <td class="dataField"></td>
						</tr>
					</tbody>
				</table>
			</td>
		</tr>
	</tbody>
</table>

</form>

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
<script language="javascript">

	$(document).ready(function(){    
	    $("#memberGroupName").autocomplete("membergroup?navigation=lookupinvoicejson", {
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
	        $("#memberGroupId").val(value.id);	    
	    });
	 });
	function simpan (){

		document.form1.method = "POST";
		document.form1.action = "invoice-form";
		document.form1.submit();
	}
	function kembali (){
		document.form1.navigation.value = "back";
		document.form1.action = "invoice";
		document.form1.submit();
	}
	// forreign affairs
			// forreign affairs end
</script>