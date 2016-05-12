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
	  	<c:when test="${(navigation eq 'addpolicyfund') or (navigation eq 'addcoveragefund')}">
      		<td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Add Fund</h3></td>
      	</c:when>
		<c:when test="${(navigation eq 'addpolicyexcessfund') or (navigation eq 'addcoverageexcess')}">
      		<td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Add Excess Fund</h3></td>
      	</c:when>
      	<c:when test="${(navigation eq 'tambah')}">
      		<td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Request Floating Fund</h3></td>
      	</c:when>
      </c:choose>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>	
<form action="fund-form" method="POST"  name="form1" id="form_layout">

<input type="hidden" name="navigation" value="<c:out value="${navigation}" />">
<input type="hidden" name="invoiceId" value="<c:out value="${invoiceId}" />">
<input type="hidden" name="excessChargeId" value="<c:out value="${excessChargeId}" />">

<spring:bind path="fundForm.fundId">
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
							<td class="dataLabel"> </td>
		    				<td class="dataField" colspan="3">&nbsp;</td>
						</tr>
						<tr>
							<c:if test="${navigation eq 'addprovider'}">
								<td class="dataLabel"> Provider : </td>
								<td class="dataField">
									<spring:bind path="fundForm.providerId">
										<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />">				
									</spring:bind>
									<spring:bind path="fundForm.providerName">
										<input type="text" size=35 name="<c:out value="${status.expression}"/>"  value="<c:out value="${status.value}" />" > 
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>	
								</td>
							</c:if>
							<c:if test="${navigation eq 'addfund'}">
								<td class="dataLabel"> Client : </td>
								<td class="dataField">
									<spring:bind path="fundForm.clientId">
										<input type="hidden" name="<c:out value="${status.expression}"/>" id="clientId" value="<c:out value="${status.value}" />">				
									</spring:bind>
									<spring:bind path="fundForm.clientName">
										<input type="text" size=35 name="<c:out value="${status.expression}"/>" id="clientName" value="<c:out value="${status.value}" />" > 
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>	
								</td>
							</c:if>
							<td class="dataLabel"> Fund Type : </td>
							<td class="dataField">
								<spring:bind path="fundForm.fundType">
									<select name="<c:out value="${status.expression}" />">
										<c:forEach items="${fundCategory}" var="fc">
											<option value="<c:out value="${fc.fundCategoryId}" />" <c:if test="${status.value eq fc.fundCategoryId}">selected</c:if>><c:out value="${fc.fundCategoryName}" /></option>
										</c:forEach>
									</select>
									<div id="fredcaption">
										<c:out value="${status.errorMessage}" />
									</div>
								</spring:bind>
							</td>
						</tr>
						<c:if test="${navigation eq 'addprovider'}">
						<tr>
							<td class="dataLabel"> Client : </td>
							<td class="dataField">
								<spring:bind path="fundForm.clientId">
									<input type="hidden" name="<c:out value="${status.expression}"/>" id="clientId" value="<c:out value="${status.value}" />">				
								</spring:bind>
								<spring:bind path="fundForm.clientName">
									<input type="text" size=35 name="<c:out value="${status.expression}"/>" id="clientName" value="<c:out value="${status.value}" />" > 
									<div id="fredcaption">
										<c:out value="${status.errorMessage}" />
									</div>
								</spring:bind>	
							</td>
							
							<td class="dataLabel">&nbsp; </td>
							<td class="dataField">&nbsp;
							</td>
						</tr>
						</c:if>
						<c:if test="${navigation eq 'addpolicyfund' or navigation eq 'addpolicyexcessfund' or navigation eq 'editpolicyfund' or navigation eq 'editpolicyexcessfund'}">
						<tr>
							<td class="dataLabel"> Policy Number : </td>
							<td class="dataField">
								<spring:bind path="fundForm.policyId">
									<input type="hidden" name="<c:out value="${status.expression}"/>" id="policyId" value="<c:out value="${status.value}" />">				
								</spring:bind>
								<spring:bind path="fundForm.policyNumber">
									<input type="text" size=35 name="<c:out value="${status.expression}"/>" id="clientName" value="<c:out value="${status.value}" />" > 
									<div id="fredcaption">
										<c:out value="${status.errorMessage}" />
									</div>
								</spring:bind>	
							</td>
							
							<td class="dataLabel">&nbsp; </td>
							<td class="dataField">&nbsp;
							</td>
						</tr>
						</c:if>
						<c:if test="${navigation eq 'addcoveragefund' or navigation eq 'addcoverageexcess'}">
						<tr>
							<td class="dataLabel"> Coverage Name : </td>
							<td class="dataField">
								
								<spring:bind path="fundForm.policyCoverageId">
									<select name="<c:out value="${status.expression}" />">
										<option value="" > -- SELECT ONE -- </option>
										<c:forEach items="${coverageList}" var="coverage">
											<option value="<c:out value="${coverage.policyCoverageFundId }" />"><c:out value="${coverage.caseCategoryId.caseCategoryName}" /></option>
										</c:forEach>
									</select> 
									<div id="fredcaption">
										<c:out value="${status.errorMessage}" />
									</div>
								</spring:bind>	
							</td>
							
							<td class="dataLabel">&nbsp; </td>
							<td class="dataField">&nbsp;
							</td>
						</tr>
						</c:if>
						<tr>
							<td class="dataLabel"> Fund Value : </td>
							<td class="dataField">
								<spring:bind path="fundForm.fundValue">
										<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="22">
									<div id="fredcaption">
										<c:out value="${status.errorMessage}" />
									</div>
								</spring:bind>
							</td>
						    <td class="dataLabel">Fund Date : </td>		
							<td class="dataField">
								<spring:bind path="fundForm.fundTime">
										<input type="text" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" id="<c:out value="${status.expression}"/>" maxlength="19">
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
							<td class="dataLabel"> Fund Currency : </td>	
							<td class="dataField">
								<spring:bind path="fundForm.fundCurrency">
									<select name="<c:out value="${status.expression}" />">
										<c:forEach items="${fundCurrency}" var="fc">
											<option value="<c:out value="${fc.currencyId}" />" <c:if test="${status.value eq fc.currencyId}">selected</c:if>><c:out value="${fc.currencyName}" /></option>
										</c:forEach>
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
							<td class="dataLabel"> Description : </td>	
						    <td class="dataField" colspan="3">
								<spring:bind path="fundForm.description">
									<textarea cols="60" rows="8" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
									<div id="fredcaption">
										<c:out value="${status.errorMessage}" />
									</div>
								</spring:bind>
							</td>
						</tr>
						<tr>
							<td class="dataLabel"> </td>	
					   	 	<td class="dataField" colspan="3">&nbsp;</td>
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
      <td style="padding-top: 2px; padding-left: 1px;">
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
    });
	function simpan (){
		<c:if test="${navigation eq 'addpolicyfund'}">
			document.form1.navigation.value = "addpolicyfund";
			document.form1.action = "fund-form";
		</c:if>
		<c:if test="${navigation eq 'addpolicyexcessfund'}">
			document.form1.navigation.value = "addpolicyexcessfund";
			document.form1.action = "fund-form";
		</c:if>
		<c:if test="${navigation eq 'addcoveragefund'}">
			document.form1.navigation.value = "addcoveragefund";
			document.form1.action = "fund-form";
		</c:if>
		<c:if test="${navigation eq 'addcoverageexcess'}">
			document.form1.navigation.value = "addcoverageexcess";
			document.form1.action = "fund-form";
		</c:if>
		document.form1.submit();
	}
	
	function cancel (){
		<c:if test="${navigation eq 'update'}">
			document.form1.navigation.value = "detail";
			document.form1.action = "fund";
			document.form1.submit();
		</c:if>
		<c:if test="${navigation eq 'addprovider'}">
			document.form1.navigation.value = "listprovider";
			document.form1.action = "fund";
			document.form1.submit();
		</c:if>
		<c:if test="${navigation eq 'addpolicyfund'}">
			document.form1.navigation.value = "listpolicy";
			document.form1.action = "fund";
			document.form1.policyId.value = <c:out value="${policyId}" />
			document.form1.submit();
		</c:if>
		<c:if test="${navigation eq 'addpolicyexcessfund'}">
			document.form1.navigation.value = "listpolicy";
			document.form1.action = "fund";
			document.form1.policyId.value = <c:out value="${policyId}" />
			document.form1.submit();
		</c:if>
		<c:if test="${navigation eq 'addcoveragefund'}">
			document.form1.navigation.value = "listpolicy";
			document.form1.action = "policycoveragefund";
			document.form1.policyId.value = <c:out value="${policyId}" />
			document.form1.submit();
		</c:if>
		<c:if test="${navigation eq 'addcoverageexcess'}">
			document.form1.navigation.value = "listpolicy";
			document.form1.action = "policycoveragefund";
			document.form1.policyId.value = <c:out value="${policyId}" />
			document.form1.submit();
		</c:if>
		<c:if test="${navigation eq 'tambah'}">
			document.form1.navigation.value = "tambah";
			document.form1.action = "fund";
			document.form1.submit();
		</c:if>
	}

</script>