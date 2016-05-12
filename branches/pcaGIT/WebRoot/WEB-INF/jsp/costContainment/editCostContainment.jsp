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


	<table class="moduleTitle" border="0" cellpadding="0" cellspacing="0" width="100%">
		<tbody>
			<tr>
				<td valign="top">
				
				</td>
				<td width="100%">
					<h2 style="font-size: large; color: #000;">
						Cost Containment Report
					</h2>
					<br />
				</td>
				<td style="padding-top: 3px; padding-left: 5px;" align="right" nowrap="nowrap" valign="top">
					
				</td>
		</tbody>
	</table>
	<br />
<form action="costcontainment-form" method="POST"  name="form1" id="form_layout">
	<spring:bind path="costContainmentForm.costContainmentId">
		<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>
<spring:bind path="costContainmentForm.caseId">
		<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>
		<input type="hidden" name="navigation" value="<c:out value="${navigation}" />" />


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
							<td class="dataLabel"> Case  : </td>	
							<td class="dataField">
								<spring:bind path="costContainmentForm.caseNumber">
									<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" readonly="readonly">
									<div id="fredcaption">
										<c:out value="${status.errorMessage}" />
									</div>
								</spring:bind>
							</td>
						    <td class="dataLabel"></td>
						    <td class="dataField"></td>
						</tr>
						<tr>
							<td class="dataLabel">Item  : </td>		
							<td class="dataField">
								<spring:bind path="costContainmentForm.itemId">
									<input type="hidden"  name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >								
								</spring:bind>	
								<spring:bind path="costContainmentForm.itemName">
									<input type="text" size="35" id="itemName" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />">
									<div id="fredcaption">
										<c:out value="${status.errorMessage}" />
									</div>
								</spring:bind>
								
							</td>
						    <td class="dataLabel"></td>
						    <td class="dataField"></td>
						</tr>
						
						<tr>
							<td class="dataLabel"> Initial Item Cost : </td>
							<td class="dataField">
								<spring:bind path="costContainmentForm.initialItemCost">
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
							<td class="dataLabel">Substitution Item : </td>	
							<td class="dataField">
								<spring:bind path="costContainmentForm.substitutionItemId">
									<input type="hidden" id="substitutionItemId"  name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" >									
								</spring:bind>
								<spring:bind path="costContainmentForm.substitutionItemName">
									<input type="text" size="35" id="substitutionItemName" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />">
									<div id="fredcaption">
										<c:out value="${status.errorMessage}" />
									</div>
								</spring:bind>
							</td>
						    <td class="dataLabel"></td>
						    <td class="dataField"></td>
						</tr>			
						<tr>
							<td class="dataLabel"> Total Substitution Value : </td>				
						
							<td class="dataField">
								<spring:bind path="costContainmentForm.totalSubstitutionValue">
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
							<td class="dataLabel"> Total Item : </td>
							<td class="dataField">
								<spring:bind path="costContainmentForm.totalItem">
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
							<td class="dataLabel"> Description : </td>
							<td class="dataField">
								<spring:bind path="costContainmentForm.description">
								<textarea rows="8" cols="60" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
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
<br />
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
	    $("#itemName").autocomplete("item?navigation=lookupjson", {
	    	min: 2,
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
	        $("#itemId").val(value.id);	    
	    });
	    
	    $("#substitutionItemName").autocomplete("item?navigation=lookupjson", {
	    	min:2,
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
	        $("#substitutionItemId").val(value.id);
	    });
	 });
	function simpan (){

		document.form1.method = "POST";
		document.form1.action = "costcontainment-form";
		document.form1.submit();
	}
	function cancel (){
		document.form1.navigation.value = "list";
		document.form1.action = "costcontainment";
		document.form1.submit();
	}

</script>