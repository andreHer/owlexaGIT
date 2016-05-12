<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>


<form action="caseconversation-form" method="POST" name="form1" id="form_layout">
	<input type="hidden" name="navigation" value="">	
	<%String alert = (String) request.getAttribute("alert");
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
	      			<td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Register Case Conversation</h3></td>
	      		</c:when>
	      	</c:choose>
	      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
	    </tr>
	  </tbody>
	</table>

	<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
		<tbody>
			<tr>
				<td>
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tbody>
<tr>
								<td class="dataLabel">
									
								</td>


								<td class="dataField">
									
								</td>
								<td class="dataLabel">
								
								</td>
								<td class="dataField">
									
									
								</td>
							</tr>

							<spring:bind path="caseConversationForm.conversationId">
								<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />">
								<div id="fredcaption">
									<c:out value="${status.errorMessage}" />
								</div>
							</spring:bind>




							<tr>
								<td class="dataLabel">
									Case :
								</td>
								<spring:bind path="caseConversationForm.case.caseId">
									<input type="hidden" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />">
									<input type="hidden" name="caseId" value="<c:out value="${status.value}" />">
								</spring:bind>

								<td class="dataField">
									<spring:bind path="caseConversationForm.case.caseNumber">
										<input type="text" size="35" name="caseNumber" value="<c:out value="${status.value}" />">
									</spring:bind>
									<div id="fredcaption">
										<c:out value="${status.errorMessage}" />
									</div>
								</td>

								<td class="dataLabel">
									Conversation Category :
								</td>

								<td class="dataField">
									<spring:bind path="caseConversationForm.conversationCategory.conversationCategoryId">
										<select name="<c:out value="${status.expression}" />">
											<c:forEach items="${conversationCategory}" var="cc">
												<option value="<c:out value="${cc.conversationCategoryId}" />" <c:if test="${cc.conversationCategoryId eq status.value}">selected</c:if>>
													<c:out value="${cc.conversationCategoryName}" />
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
									Conversation Subject :
								</td>


								<td class="dataField">
									<spring:bind path="caseConversationForm.conversationSubject">
										<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />">
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
								</td>
								<td class="dataLabel">
									Conversation Time :
								</td>


								<td class="dataField">
									<spring:bind path="caseConversationForm.date">
										<input type="text"  size="10" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" id="<c:out value="${status.expression}"/>" maxlength="19">
									</spring:bind>

									&nbsp;
									<img src="images/jscalendar.gif" alt="Enter Date" id="date_trigger" align="absmiddle" height="20" width="20">
									&nbsp;

										<spring:bind path="caseConversationForm.hour">
											<select name="<c:out value="${status.expression}" />">
												<c:forEach items="${hours}" var="h">
													<option value="<c:out value="${h}" />" <c:if test="${status.value eq h}">selected</c:if>><c:out value="${h}" /></option>
												</c:forEach>
											</select>
										</spring:bind>									
										:
										<spring:bind path="caseConversationForm.minute">
											<select name="<c:out value="${status.expression}" />">
												<c:forEach items="${minutes}" var="m">
													<option value="<c:out value="${m}" />" <c:if test="${status.value eq m}">selected</c:if>><c:out value="${m}" /></option>
												</c:forEach>
											</select>
										</spring:bind>										

									<script type="text/javascript">
				    					Calendar.setup({
				        					inputField     :    "date",     // id of the input field
				        					ifFormat       :    "%Y-%m-%d",      // format of the input field
				        					button         :    "date_trigger",  // trigger for the calendar (button ID)
				        					align          :    "Bl",           // alignment (defaults to "Bl")
				        					singleClick    :    true
				    					});
								 	</script>
														<!-- 
									<div id="fredcaption">
										<c:out value="${status.errorMessage}" />
									</div>-->



								</td>
							</tr>
							
							
							<tr>
								<td class="dataLabel">
									Priority :
								</td>


								<td class="dataField">
									<spring:bind path="caseConversationForm.priority">
										<select name="<c:out value="${status.expression}" />">
											<c:forEach items="${priorityList}" var="prio">
												<option value="<c:out value="${prio.priorityId}" />" <c:if test="${prio.priorityId eq status.value}">selected</c:if>>
													<c:out value="${prio.priorityCode}" />
												</option>
											</c:forEach>
										</select>
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
								</td>
								<td class="dataLabel">
									
								</td>


								<td class="dataField">
									

								</td>
							</tr>

							<tr>

								<td class="dataLabel"></td>
								<td class="dataField"></td>

								<td class="dataLabel"></td>
								<td class="dataField"></td>
							</tr>




							<tr>
								<td class="dataLabel">
									Conversation Description :
								</td>


								<td class="dataField" colspan="3">
									<spring:bind path="caseConversationForm.conversationDescription">
										<textarea cols="60" rows="8" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
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
								<td class="dataLabel">
								
								</td>
								<td class="dataField">
									
									
								</td>
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
					<input title="Save [Alt+Shift+S]" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:simpan()" name="Save" value=" Save " type="submit">
					<input title="Cancel [Alt+Shift+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:kembali()" name="Cancel" value=" Cancel " type="button">
				</td>
				<td align="right"></td>
			</tr>
		</tbody>
	</table>
</form>

<script language="javascript">
	function simpan (){

		document.form1.method = "POST";
		document.form1.action = "caseconversation-form";
		document.form1.submit();
		document.form1.navigation.value = "tambah";
	}
	function cancel (){
		document.form1.navigation.value = "detail";
		document.form1.action = "case";
		document.form1.submit();
	}
	function kembali (){
		document.form1.navigation.value = "list";
		document.form1.action = "caseconversation";
		document.form1.submit();
	}
	// forreign affairs
			// forreign affairs end
</script>
