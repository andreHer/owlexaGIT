<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>

	<head>


	<script type="text/javascript" src="scripts/sugar_3.js"></script>
		<script type="text/javascript" src="scripts/cookie.js"></script>
		<link rel="stylesheet" type="text/css" media="all" href="css/calendar-win2k-cold-1.css" />
		<link rel="stylesheet" type="text/css" media="all" href="css/style.css" />
	
		
<script src="scripts/prototype.js" type="text/javascript"></script>
  <script src="scripts/scriptaculous.js" type="text/javascript"></script>
  	<link rel="stylesheet" type="text/css" href="css/tabcontent.css" />
<script type="text/javascript" src="scripts/tabcontent.js">
/***********************************************
* Tab Content script- © Dynamic Drive DHTML code library (www.dynamicdrive.com)
* This notice MUST stay intact for legal use
* Visit Dynamic Drive at http://www.dynamicdrive.com/ for full source code
***********************************************/
</script>
<link rel="stylesheet" type="text/css" href="css/reset-fonts-grids.css" />
        <!-- CSS for Menu -->

        <link rel="stylesheet" type="text/css" href="css/menu.css" />
 
 
        <!-- Page-specific styles -->

        <!-- Namespace source file -->
<!-- calendar stylesheet -->
	

		<!-- main calendar program -->
		<script type="text/javascript" src="scripts/calendar.js"></script>

		
        <!-- Menu source file -->
       
		
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Aplikanusa Lintasarta - Healthcare Management System</title>


		<link href="css/navigation.css" rel="stylesheet" type="text/css" />
		<script language="javascript" src="scripts/menu.js"></script>
		<script language="javascript" src="scripts/cookie_002.js"></script>

	</head>
	
<script type="text/javascript" language="Javascript" src="scripts/clockinput.js">
</script>

<form action="firstcall-form" method="POST" name="form1" id="form_layout">


	<p>
	<table class="moduleTitle" border="0" cellpadding="0" cellspacing="0" width="100%">
		<tbody>
			<tr>
				<td valign="top">
					<img src="images/Tasks.gif" style="margin-top: 3px;" alt="Tasks: " border="0" height="16" width="16">					
						First Call :					
				</td>				
				<td style="padding-top: 3px; padding-left: 5px;" align="right" nowrap="nowrap" valign="top">
					&nbsp;<a href="" target="_blank" class="utilsLink"><img src="images/help.gif" alt="Help" align="absmiddle" border="0" height="13" width="13"></a>&nbsp;<a href="" target="_blank" class="utilsLink">Help</a>
				</td>
			</tr>
		</tbody>
	</table>

		<%String alert = (String) request.getAttribute("alert");
			if (alert != null && !alert.trim().equals("")) {%>
							<div id="warning">
								<c:out value="${alert}"></c:out>
							</div>
							<%}%>


	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tbody>
			<tr>
				<td style="padding-bottom: 2px;">
					<input type="hidden" name="notyet" value="">
					<input title="Save [Alt+Shift+S]" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:save()" name="Save" value=" Save " type="submit">
					<input title="Cancel [Alt+Shift+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:cancel()" name="Cancel" value=" Cancel " type="button">
				
					<td align="right" nowrap="nowrap">
						<span class="required">*</span> Indicates required field
					</td>
				
				<td align="right">
				</td>
			</tr>
		</tbody>
	</table>

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

							<input type="hidden" name="navigation" value="">
						<tbody>
						
			<tr>
				<td class="dataLabel"> </td>				
			

		    <td class="dataField" colspan="3">&nbsp;</td>

	</tr>						
						
							<tr>
							
							
							
								<td class="dataLabel" width="19%">
									
									Policy Number : <span class="required">*</span>
									
								</td>
								<td class="dataField" width="31%">
									

									<input type="text" readonly="readonly" value="<c:out value="${firstCallForm.firstCall.customerId.customerPolicyNumber}"/>" size=30>
									<div id="fredcaption">
										<c:out value="${status.errorMessage}" />
									</div>

									

								</td>



								<td class="dataLabel" width="15%">
									
									Priority: <span class="required">*</span>
									
								</td>
								<td class="dataField" width="35%">
									
													
									<spring:bind path="firstCallForm.priority.priorityId">
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
								<td class="dataLabel">
									
									Member Name :
									
								</td>
								<td>
									
									<input type="text" readonly="readonly" name="member_name" value="<c:out value="${firstCallForm.firstCall.customerId.firstName}" /> <c:out value="${firstCallForm.firstCall.customerId.lastName}" />" maxlength="30">
									
								</td>



								<td class="dataLabel">
									Client :
								</td>
								<td class="dataField">
									<input name="client_name"  value="${firstCallForm.firstCall.customerId.clientId.clientName}" type="text" readonly="readonly">
								</td>
							</tr>

							<tr>
<!--							MEMBERSHIP STATUS	-->
								<td class="dataLabel">
									Membership Status :
								</td>
								<td class="dataField" nowrap="nowrap">
									<input type="text" name="member_status"  value="${firstCallForm.firstCall.customerId.status.status}"  readonly="readonly">
								</td>




<!--							Expire Date :-->
								<td class="dataLabel">
									Expire Date :
								</td>
								<td class="dataField">									
									<input type="text" size="10" name="<c:out value="${status.expression}" />" value="<c:out value="${firstCallForm.firstCall.customerId.expireDate}" />"  readonly="readonly" maxlength="19">										
								</td>
							</tr>
							<tr>
								<td nowrap="nowrap"></td>
								<td nowrap="nowrap">
									&nbsp;
								</td>
								<td nowrap="nowrap">
									&nbsp;
								</td>
								<td nowrap="nowrap">
									<span class="dateFormat">(dd-MM-yyyy)</span>
								</td>

							</tr>






							<tr>

								<td class="dataLabel">
									Call Start Time :
								</td>
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
	

									<DIV id="fredcaption">
										<c:out value="${status.errorMessage}" />
									</DIV>

								</td>


								<!--							SERVICE TYPE	-->
								<td class="dataLabel">
									Call Category :
								</td>
								<td class="dataField">

									<spring:bind path="firstCallForm.firstCall.callCategoryId.callCategoryId">
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

								<td nowrap="nowrap">
									&nbsp;
								</td>

								<td nowrap="nowrap">
									<span class="dateFormat">(dd-MM-yyyy) &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;hour &nbsp;&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;&nbsp;minute</span>
								</td>

							</tr>

							<tr>
								<td class="dataLabel">
							Caller Name :
								</td>
								<td class="dataField">

									<spring:bind path="firstCallForm.callerName">
										<input type="text" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" tabindex="4">
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
									
									
									
															
								</td>



								<!--							SERVICE TYPE	-->
								<td class="dataLabel">
									Location :
								</td>
								<td class="dataField">

									<spring:bind path="firstCallForm.city">
										<input type="text" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" tabindex="5">
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
									
									
									
															
								</td>
							</tr>
	<tr>

								<td nowrap="nowrap">
									&nbsp;
								</td>


							</tr>

							<tr>
								<td class="dataLabel">&nbsp;Telephone Number:
								</td>
								<td class="dataField">

									<spring:bind path="firstCallForm.telephoneNumber">
										<input type="text" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" tabindex="6">
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
									
									
									
															
								</td>



								<!--							SERVICE TYPE	-->
								<td class="dataLabel">
									Followup Needed :
								</td>
								<td class="dataField">

									<spring:bind path="firstCallForm.followup">
										<input type="checkbox" tabindex="7"  name="<c:out value="${status.expression}" />" value="Y" <c:if test="${status.value eq \"Y\"}" >checked</c:if>>

										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
									
									
									
															
								</td>
							</tr>
							<tr>
								<td class="dataLabel">

								</td>


								<td class="dataField">
								
								</td>
								<td class="dataLabel"></td>
								<td class="dataField"></td>
							</tr>
							<tr>

								<td nowrap="nowrap">
									&nbsp;
								</td>
								<td nowrap="nowrap">
								</td>

							</tr>




							<tr>
								<td class="dataLabel">
									Call Description :
								</td>
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
								<td class="dataLabel">
								</td>
								<td class="dataField">
								</td>
								
							</tr>
							<tr>
								<td class="dataLabel">
									Officer Remarks / Solution :
								</td>
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
	
	function save (){

		document.form1.method = "POST";
		document.form1.action = "firstcall-form";
		document.form1.submit();
	}
	function cancel (){
		document.form1.navigation.value = "detail";
		document.form1.action = "member";
		document.form1.submit();
	}
	
				// forreign affairs end
</script>
