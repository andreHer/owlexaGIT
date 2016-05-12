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


<form action="reportrequest-form" method="POST"  name="form1" id="form_layout">

	<spring:bind path="reportRequestForm.id">
		<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>

<input type="hidden" name="navigation" value="">
<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>
		<tr>
			<td>
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<tbody>



			<tr>
				<td class="dataLabel">  </td>	
				<td class="dataField">
					
				</td>
			    <td class="dataLabel"></td>
			    <td class="dataField"></td>
			</tr>




			<tr>
				<td class="dataLabel"> Request Number : </td>
				<td class="dataField">
					<spring:bind path="reportRequestForm.requestNumber">
						<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" readonly="readonly">
					</spring:bind>
				</td>
			    <td class="dataLabel"></td>
			    <td class="dataField"></td>
			</tr>
	
			
			<tr>
				<td class="dataLabel"> Report Subject Type : </td>
				<td class="dataField">
					<spring:bind path="reportRequestForm.reportSubjectType">
						<select name="<c:out value="${status.expression }" />">							
							<option value="">-- SELECT ONE --</option>
							<option value="client" <c:if test="${status.value eq 'client' }">selected</c:if>>Client/Insurance</option>
							<option value="group" <c:if test="${status.value eq 'group' }">selected</c:if>>Member Group</option>
							<option value="provider" <c:if test="${status.value eq 'provider' }">selected</c:if>>Provider</option>
							<option value="broker" <c:if test="${status.value eq 'broker' }">selected</c:if>>Broker</option>
							<option value="member" <c:if test="${status.value eq 'member' }">selected</c:if>>Member</option>
						</select>
						
					</spring:bind>
					</td>
			    <td class="dataLabel"></td>
			    <td class="dataField"></td>
			</tr>
			<tr>							
								
				<td class="dataLabel">Report Subject : </td>		
				<td class="dataField">
					<spring:bind path="reportRequestForm.subjectId">
						<input type="hidden" id="subjectId" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />">
					</spring:bind>
				
					<spring:bind path="reportRequestForm.subjectName">
						<input type="text" name="<c:out value="${status.expression}"/>" size="35" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
					</spring:bind>			
				</td>
				<td class="dataLabel"></td>
				<td class="dataField"></td>
			</tr>
		
			<tr>
				<td class="dataLabel"> Report Type : </td>
				<td class="dataField">
					<spring:bind path="reportRequestForm.reportType">
						<select name="<c:out value="${status.expression }" />">							
							<option value="">-- SELECT ONE --</option>
							<option value="1">Group Executive Report</option>
							<option value="2">Group Claim Tabulation Report</option>
							<option value="3">Client Executive Report</option>
							<option value="4">Client Claim Tabulation Report</option>
						</select>
						
					</spring:bind>
					</td>
			    <td class="dataLabel"></td>
			    <td class="dataField"></td>
			</tr>
			<tr>
				<td class="dataLabel"> Start Date : </td>
				<td class="dataField">
					<spring:bind path="reportRequestForm.startDate">
						<input type="text" name="<c:out value="${status.expression}" />" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="10">
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
					</spring:bind>
				</td>
			    <td class="dataLabel"></td>
			    <td class="dataField"></td>
			</tr>
			<tr>
				<td class="dataLabel"> End Date : </td>
				<td class="dataField">
					<spring:bind path="reportRequestForm.endDate">
						<input type="text" name="<c:out value="${status.expression}" />" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="10">
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
					</spring:bind>
				</td>
			    <td class="dataLabel"></td>
			    <td class="dataField"></td>
			</tr>
			<tr>
				<td class="dataLabel"> Table Options : </td>	
				<td class="dataField">
					<spring:bind path="reportRequestForm.tableOptions">
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
				<td class="dataLabel">  </td>	
				<td class="dataField">
					
				</td>
			    <td class="dataLabel"></td>
			    <td class="dataField"></td>
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
        <input title="Save [Alt+S]" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:simpan()" name="Save" value=" Save " type="submit">
        <input title="Cancel [Alt+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:cancel()" name="Cancel" value=" Cancel " type="submit">         
	  </td>
      <td align="right"></td>
    </tr>
  </tbody>
</table>
</form>

<script language="javascript">

	$(document).ready(function(){   
    
	    
	    
	    $("#subjectName").autocomplete("recipient?navigation=lookupjson", {
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
	            return "<font color='#000' >"+ row.number +" - </font>" +    "<font color='#000' >"+ row.name +"</font>" ;
	        },
	        extraParams: {
	       		recipient: function() { return $("#paymentRecipient").val(); }
	   		}
	    }).bind("result", function(data, value){
	        $(this).parents("dd").find(".error_message").hide();
	        $("#claimerId").val(value.id);
	        
	    });
	});
	
	function simpan (){

		document.form1.method = "POST";
		document.form1.action = "reportrequest-form";
		document.form1.submit();
	}
	function kembali (){
		document.form1.navigation.value = "back";
		document.form1.action = "reportrequest";
		document.form1.submit();
	}
	
</script>