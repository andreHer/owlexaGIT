<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>

<script type="text/javascript" src="scripts/tinymce/tinymce.min.js"></script>

<link rel="stylesheet" type="text/css" href="css/autocomplete.css">
<link rel="stylesheet" type="text/css" href="css/button.css">

<!-- <link rel="stylesheet" type="text/css" href="css/jquery/autocomplete/jquery.autocomplete.css"/> -->

<!-- <script type="text/javascript" src="scripts/jquery/jquery-1.4.4.min.js"></script> -->
<!-- <script type="text/javascript" src="scripts/jquery/plugin/jquery.autocomplete.pack.js"></script> -->
	
<script type="text/javascript" language="Javascript" src="scripts/clockinput.js"></script>
	<%
		String alert = (String) request.getAttribute("alert");
		if (alert != null && !alert.trim().equals("")) {%>
		<div id="warning">
			<c:out value="${alert}"></c:out>
		</div>
	<%}%>


<form action="firstcall-form" method="POST" name="form1" id="form_layout">


	<input type="hidden" name="navigation" value="<c:out value="${navigation}" />">
	<input type="hidden" name="subnav" value="<c:out value="${navigation}" />" />
	
	<spring:bind path="firstCallForm.caseId">
		<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />">
	</spring:bind>
	<spring:bind path="firstCallForm.claimId">
		<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />">
	</spring:bind>
	<spring:bind path="firstCallForm.callLogType">
		<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />">
	</spring:bind>


	<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
		<tbody>
			<tr>
				<td>
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tbody>
							<spring:bind path="firstCallForm.callId">
								<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />">
								<div id="fredcaption">
									<c:out value="${status.errorMessage}" />
								</div>
							</spring:bind>						
							<tr>
								<td class="dataLabel"> </td>
						    	<td class="dataField" colspan="3">&nbsp;</td>
							</tr>
							<tr>
								<td class="dataLabel">Call Start Time :	</td>
								<td class="dataField">
									<spring:bind path="firstCallForm.date">
										<INPUT type="text" size="10" readonly="readonly" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" id="date" maxlength="19">
									</spring:bind>
									<spring:bind path="firstCallForm.hour">
										<select name="<c:out value="${status.expression}" />" tabindex="1">
											<c:forEach items="${hours}" var="h">
												<option value="<c:out value="${h}" />" <c:if test="${status.value eq h}">selected</c:if>><c:out value="${h}" /></option>
											</c:forEach>
										</select>
									</spring:bind>									
									:
									<spring:bind path="firstCallForm.minute">
										<select name="<c:out value="${status.expression}" />" tabindex="2">
											<c:forEach items="${minutes}" var="m">
												<option value="<c:out value="${m}" />" <c:if test="${status.value eq m}">selected</c:if>><c:out value="${m}" /></option>
											</c:forEach>
										</select>
									</spring:bind>
									<div id="fredcaption">
										<c:out value="${status.errorMessage}" />
									</div>
								</td>
								<td class="dataLabel">Call Category : <span class="required">*</span></td>
								<td class="dataField">
									<spring:bind path="firstCallForm.callCategoryId">
										<select name="<c:out value="${status.expression}"/>" tabindex="3">
											<c:forEach items="${callCategory}" var="cc">
												<option value="<c:out value="${cc.callCategoryId}"/>" <c:if test="${status.value eq cc.callCategoryId}">selected</c:if>>
													<c:out value="${cc.callCategoryName}" />
												</option>
											</c:forEach>
										</select>
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
								</td>
							</tr>							
							<tr>
								<td nowrap="nowrap"></td>
								<td nowrap="nowrap">
									
								</td>
							</tr>
							<tr>
								<td class="dataLabel">Caller Name :	<span class="required">*</span></td>
								<td class="dataField">
									<spring:bind path="firstCallForm.callerName">
										<input type="text" size=35 name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" tabindex="4">
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>						
								</td>
								
								<td class="dataLabel">Perusahaan : </td>
								<td class="dataField">
									<spring:bind path="firstCallForm.groupName">
										<input type="text" size=40 id="city" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" tabindex="5">
									</spring:bind>															
								</td>
							</tr>
							<tr>
								<td nowrap="nowrap"></td>
							</tr>
							<tr>
								<td class="dataLabel">Telephone Number:	<span class="required">*</span></td>
								<td class="dataField">
									<spring:bind path="firstCallForm.telephoneNumber">
										<input type="text" size=35 name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" tabindex="6">
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>									
								</td>
								<td class="dataLabel">Followup Needed :	</td>
								<td class="dataField">
									<spring:bind path="firstCallForm.followup">
										<%-- <input type="checkbox" tabindex="7"  name="<c:out value="${status.expression}" />" value="Y" <c:if test="${status.value eq \"Y\"}" >checked</c:if>> --%>
										<c:choose>
											<c:when test="${status.value eq \"Y\" }">
												<input type="checkbox" tabindex="7"  name="<c:out value="${status.expression}" />" checked="checked" />
											</c:when>
											<c:otherwise>
												<input type="checkbox" tabindex="7"  name="<c:out value="${status.expression}" />" />
											</c:otherwise>
										</c:choose>
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>															
								</td>
							</tr>
							<tr>
								<td class="dataLabel">Location:	<span class="required">*</span></td>
								<td class="dataField">
									<spring:bind path="firstCallForm.location">
										<input type="text" size=35 name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" tabindex="6">
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>									
								</td>
								<td class="dataLabel" width="15%">Priority:	</td>
								<td class="dataField" width="35%">
									<spring:bind path="firstCallForm.priority">
										<select name="<c:out value="${status.expression}"/>">
											<c:forEach items="${priority}" var="p">
												<option value="<c:out value="${p.priorityId}"/>" <c:if test="${status.value eq p.priorityId}">selected</c:if>>
													<c:out value="${p.priorityCode}" />
												</option>
											</c:forEach>
										</select>
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>									
								</td>
								
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td class="dataLabel" width="19%">									
									Member Number : 							
								</td>
								<td class="dataField" width="31%">
									<spring:bind path="firstCallForm.memberId">
										<input type="hidden" id="memberId" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />">
									</spring:bind>
									<input type="text" size=50 id="policyNumber" value="<c:out value="${firstCallForm.customerId.customerPolicyNumber}"/>" size="30">
									<div id="fredcaption">
										<c:out value="${status.errorMessage}" />
									</div>
								</td>
								<td class="dataLabel">Client : </td>
								<td class="dataField">
									<input name="client_name" size=40 id="clientId"  value="${firstCallForm.customerId.clientName}" type="text" readonly="readonly">
								</td>								
							</tr>
							<tr>
								<td class="dataLabel">Member Name :	</td>
								<td class="dataField">									
									<input type="text" size=50 readonly="readonly" id="memberName"	name="member_name" value="<c:out value="${firstCallForm.firstCall.customerId.firstName}" />" size=30>									
								</td>
								<td class="dataLabel">Job Position : </td>
								<td class="dataField">
									<input name="jobPosition" size=40 id="jobPosition"  value="${firstCallForm.firstCall.customerId.jobPosition}" type="text" readonly="readonly">
								</td>
							</tr>
							<tr>
								<td class="dataLabel">Membership Status : </td>
								<td class="dataField" nowrap="nowrap">
									<input type="text" name="member_status" id="memberStatus" value="${firstCallForm.memberStatus}" readonly="readonly">
								</td>
								<td class="dataLabel">Birthday :	</td>
								<td class="dataField">									
									<input type="text" size="10" id="expireDate" name="<c:out value="${status.expression}" />" value="<c:out value="${firstCallForm.firstCall.customerId.expireDate}" />"  readonly="readonly" maxlength="19">										
								</td>
							</tr>
							<tr>
								<td class="dataLabel"></td>
								<td class="dataLabel"></td>
								<td class="dataLabel"></td>
								<td class="dataLabel"></td>
							</tr>
							<tr>
								<td class="dataLabel"></td>
								<td class="dataLabel"></td>
								<td class="dataLabel"></td>
								<td class="dataLabel"></td>
							</tr>
							
							
							<tr>
								<td class="dataLabel">&nbsp;</td>
								<td class="dataLabel"></td>
								<td class="dataLabel"></td>
								<td class="dataLabel"></td>
							</tr>
							<tr>
								<td class="dataLabel">Call Description :</td>
								<td class="dataField" colspan=3>
									<spring:bind path="firstCallForm.description">
										<textarea cols="60" rows="8" name="<c:out value="${status.expression}"/>" tabindex="8"><c:out value="${status.value}" /></textarea>
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
								</td>								
							</tr>			
							<tr>
								<td class="dataLabel"></td>
								<td class="dataField"></td>
								
								<td class="dataLabel"></td>
								<td class="dataField"></td>									
							</tr>
							<tr>
								<td class="dataLabel">Officer Remarks / Solution :	</td>
								<td class="dataField" colspan=3>
									<spring:bind path="firstCallForm.remarks">
										<textarea cols="60" rows="8" name="<c:out value="${status.expression}"/>" tabindex="8"><c:out value="${status.value}" /></textarea>
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
					<input title="Save [Alt+Shift+S]" tabindex="9" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:save()" name="Save" value=" Save " type="submit">
					<input title="Cancel [Alt+Shift+C]" tabindex="10" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:cancel()" name="Cancel" value=" Cancel " type="button">
				</td>
				<td align="right"></td>
			</tr>
		</tbody>
	</table>

</form>

<script language="javascript">

	$(document).ready(function(){    
	    $("#policyNumber").autocomplete("member?navigation=lookupjson", {
	        max: 7,
	        dataType: "json",
	        parse: function(data) {
	            return $.map(data, function(row) {
	                return {
	                    data: row,
	                    value: row.name,
	                    result: row.number
	                }
	            });
	        },
	        formatItem: function(row) {
	            return "<font color='#000' >"+ row.number + ' -- ' +row.name +"</font>" ;
	        }
	    }).bind("result", function(data, value){
	        $(this).parents("dd").find(".error_message").hide();
	        $("#clientId").val(value.client);
	        $("#jobPosition").val(value.department);
	        $("#memberName").val(value.name);
	        $("#memberStatus").val(value.status);
	        $("#expireDate").val(value.birthday);
	        $("#memberId").val(value.id);
	        $("#city").val(value.group);
	    });
	 });
    
    $(function() {
 
	 
	    // it is assumed to be the onBeforeLoad event listener
	    $("a[rel]").overlay({
	 
	        mask: 'darkred',
	        effect: 'apple',
	 
	        onBeforeLoad: function() {
	 
	            var wrap = this.getOverlay().find(".contentWrap");
	            var memberId = 1;
	 
	            var ref = 'member?navigation=detail&memberId='+memberId;
	            wrap.load(ref);
	        }
	 
	    });
	});
	
	function save (){

		document.form1.method = "POST";
		document.form1.action = "firstcall-form";
		document.form1.submit();
	}
	<c:if test="${navigation eq 'member'}">
	function cancel (){
		window.close();
	}
	</c:if>
	<c:if test="${navigation eq 'freecall'}">
	function cancel (){
		document.form1.navigation.value = "search";
		document.form1.action = "firstcall";
		document.form1.submit();
	}
	</c:if>
	<c:if test="${navigation eq 'edit'}">
	function cancel (){
		document.form1.navigation.value = "search";
		document.form1.action = "firstcall";
		document.form1.callId.value = <c:out value="${callId}" />
		document.form1.submit();
	}
	</c:if>
	<c:if test="${navigation eq 'update'}">
	function cancel (){
		document.form1.navigation.value = "detail";
		document.form1.action = "firstcall";
		document.form1.submit();
	}
	</c:if>
	<c:if test="${navigation eq 'caseelog' or navigation eq 'updatecaseelog'}">
	function cancel (){
		document.form1.navigation.value = "detail";
		document.form1.action = "case";
		document.form1.caseId.value = <c:out value="${caseId}" />
		document.form1.submit();
	}
	</c:if>
	<c:if test="${navigation eq 'claimelog' or navigation eq 'updateclaimelog'}">
	function cancel (){
		document.form1.navigation.value = "detail";
		document.form1.action = "claim";
		document.form1.claimId.value = <c:out value="${claimId}" />
		document.form1.submit();
	}
	</c:if>
	<c:if test="${navigation eq 'memberelog' or navigation eq 'updatememberelog'}">
	function cancel (){
		document.form1.navigation.value = "detail";
		document.form1.action = "member";
		document.form1.memberId.value = <c:out value="${memberId}" />
		document.form1.submit();
	}
	</c:if>
	
				// forreign affairs end
</script>
