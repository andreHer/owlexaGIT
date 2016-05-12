

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
	

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <c:choose>
		<c:when test="${navigation eq 'tambah'}">
      		<td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Create Claim Receiving</h3></td>
      	</c:when>
		<c:when test="${navigation eq 'edit'}">
      		<td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Edit Claim Receiving</h3></td>
      	</c:when>
      </c:choose>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>

<form action="claimreceiving-form" method="POST"  name="form1" id="form_layout">
<input type="hidden" name="navigation" value="${navigation }">

<spring:bind path="claimReceivingForm.claimReceivingId">
	<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
</spring:bind>

<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>

		<tr>
			<td class="dataLabel">&nbsp;</td>
			<td class="dataField">&nbsp;</td>
		    <td class="dataLabel"></td>
		    <td class="dataField"></td>
		</tr>

			<tr>
				<td class="dataLabel"> Receive Date : </td>				
			

				<td class="dataField">
					<spring:bind path="claimReceivingForm.receiveDate">
							<input type="text" size="15" name="<c:out value="${status.expression}" />" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="10">
							<img src="images/jscalendar.gif" alt="Enter Date" id="<c:out value="${status.expression}"/>_trigger" align="absmiddle" height="20" width="20">
							<script type="text/javascript">
		    					Calendar.setup({
		        					inputField     :    "<c:out value="${status.expression}"/>",     // id of the input field
		        					ifFormat       :    "%Y-%m-%d",      // format of the input field
		        					button         :    "<c:out value="${status.expression}"/>_trigger",  // trigger for the calendar (button ID)
		        					align          :    "Br",           // alignment (defaults to "Bl")
		        					singleClick    :    true
		    					});
						 	</script>
					</spring:bind>
					&nbsp;&nbsp;&nbsp;
					<spring:bind path="claimReceivingForm.receiveTime">
						<input type="text" size=4 name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" />
					</spring:bind>
			</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
			

		
				
		
		

			<tr>
				<td class="dataLabel"> Total Document : </td>				
			

						<td class="dataField">
			<spring:bind path="claimReceivingForm.totalReceiving">
	<input type="text" size="5" name="<c:out value="${status.expression}" />" value="<fmt:formatNumber><c:out value="${status.value}"/></fmt:formatNumber>" maxlength="5">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
			

		
				
		
		

			<tr>
				<td class="dataLabel"> Courier : </td>				
			
	
						<td class="dataField">
				<spring:bind path="claimReceivingForm.courier">
					<select name="<c:out value="${status.expression }" />">
						<option value="">-- SELECT ONE --</option>
						<option value="TIKI JNE" <c:if test="${status.value eq 'TIKI JNE'}">selected</c:if>>TIKI JNE</option>
						<option value="JNE" <c:if test="${status.value eq 'JNE'}">selected</c:if>>JNE</option>
						<option value="POS INDONESIA" <c:if test="${status.value eq 'POS INDONESIA'}">selected</c:if>>POS INDONESIA</option>
						<option value="OTHER" <c:if test="${status.value eq 'OTHER'}">selected</c:if>>OTHER</option>
					</select>
					<div id="fredcaption">
						<c:out value="${status.errorMessage}" />
					</div>
				</spring:bind>
			</td>
			<td class="dataLabel"></td>
			<td class="dataField"></td>
		</tr>
			

		
				
		
		

	
		
	<tr>
		<td class="dataLabel">Client : </td>		
		<td class="dataField">
		<spring:bind path="claimReceivingForm.clientId">
		<input type="hidden" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>	
		<spring:bind path="claimReceivingForm.clientName">	
			<input type="text" name="<c:out value="${status.expression}" />" id="clientName" value="<c:out value="${status.value}" />" size=35>
		</spring:bind>

		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>

		
	<tr>
		<td class="dataLabel">Provider * : </td>		
		<td class="dataField">
		<spring:bind path="claimReceivingForm.providerId">
		<input type="hidden" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>
		<spring:bind path="claimReceivingForm.providerName">	
			<input type="text" name="<c:out value="${status.expression}" />" id="providerName" value="<c:out value="${status.value}" />" size=35>
		</spring:bind>

		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>

		
	

			<tr>
				<td class="dataLabel"> Description : </td>				
			
	
						<td class="dataField">
				<spring:bind path="claimReceivingForm.description">
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
        <input title="Cancel [Alt+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:cancel()" name="Cancel" value=" Cancel " type="button">         
	  </td>
      <td align="right"></td>
    </tr>
  </tbody>
</table>
</form>

<script language="javascript">
	
	$("#memberGroupIdId").autocomplete("membergroup?navigation=lookupjson", {
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
    
    $("#clientName").autocomplete("client?navigation=lookupjson", {
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
        $("#clientId").val(value.id);
    });
    
    $("#providerName").autocomplete("provider?navigation=lookupjson", {
        max: 20,
        dataType: "json",
        scroll: true,
        scrollHeight: 180,
        scrollWeight: 180,
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
        },
        cacheLength: 0
    }).bind("result", function(data, value){
        $(this).parents("dd").find(".error_message").hide();
        $("#providerId").val(value.id);
    });
	
		
	function simpan (){

		document.form1.method = "POST";
		document.form1.action = "claimreceiving-form";
		document.form1.submit();
	}
	function cancel (){
		document.form1.navigation.value = "";
		document.form1.action = "claimreceiving";
		document.form1.submit();
	}
	// forreign affairs
	
		// forreign affairs end
</script>