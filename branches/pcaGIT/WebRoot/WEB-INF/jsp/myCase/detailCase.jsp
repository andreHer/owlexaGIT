<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
	//Confirmation Letter Dialog
%>
<div id="dialog" title="Choose Print Letter Type">
	Select Language : <select name="langSelect" id="langSelect">
		<option value="ina">INDONESIA</option>
		<option value="en">ENGLISH</option>
	</select> <input type="hidden" name="languageSelect" id="languageSelect">
	<br> <br>
	<c:if test="${theUser.userType eq 2 and (theUser.roleId.roleId eq 0 or theUser.roleId.roleId eq 1 or theUser.roleId.roleId eq 2)}">
		<input style="width:250px" title="Print Letter [Alt+Shift+C]"
			accesskey="C"
			class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
			id="confirmLetterMenuBtn" name="confirmLetter"
			value="Print Confirm Letter" type="button">
		<div id="printConfirmLetterDiv">
			<!-- 	<input title="Print Confirm Letter [Alt+Shift+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:printConfirmLetter('default')" name="confirmLetter" value="      Default      " type="button"> -->
			<ul style="padding:2">
				<li style="list-style-type: none;" class="ui-widget-header"
					onClick="javascript:printConfirmLetter('default')">Default</li>
				<li style="list-style-type: none;" class="ui-widget-header"
					onClick="javascript:printConfirmLetter('maternity')">Maternity</li>
				<li style="list-style-type: none;" class="ui-widget-header"
					onClick="javascript:printConfirmLetter('kemotherapy')">Kemoterapi</li>
				<li style="list-style-type: none;" class="ui-widget-header"
					onClick="javascript:printConfirmLetter('haemodialisa')">Haemodialisa</li>
				<li style="list-style-type: none;" class="ui-widget-header"
					onClick="javascript:printConfirmLetter('surgery')">Surgery</li>
			</ul>
		</div>
		<br>
		<input style="width:250px" title="Print Idemnity [Alt+Shift+C]"
			accesskey="C"
			class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
			onClick="javascript:printIdemnityLetter()" id="idemnityLetterMenuBtn"
			name="indemnityLetter" value="          Print Idemnity         "
			type="button">
		<br>
		<input style="width:250px" title="Print Authorization Letter"
			accesskey=""
			class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
			onClick="javascript:printAuthorizationLetter()"
			name="authorizationLetter" value=" Authorization Letter "
			type="button">
		<br>
		<%-- Add for Check Case Status --%>
		<%-- CASE PRE AUTH, add by aju on 20150824, for preAdmission --%>
		<c:if test="${myCase.caseStatusId.caseStatusId eq 20}">
			<input style="width:250px"
				title="Print Guarantee Letter [Alt+Shift+I]" accesskey="I"
				class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
				onClick="javascript:printGuaranteeLetter()" name="printGL"
				value=" Guarantee Letter " type="button">
			<br>
		</c:if>
		<%-- CASE OPEN --%>
		<c:if test="${myCase.caseStatusId.caseStatusId eq 1}">
			<input style="width:250px" title="Print Investigation Letter"
				accesskey=""
				class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
				onClick="javascript:printInvestigationLetter()"
				name="authorizationLetter" value=" Investigation Letter "
				type="button">
			<br>
			<input style="width:250px" title="Print Medical Form" accesskey=""
				class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
				onClick="javascript:printMedicalForm()" name="medicalForm"
				value=" Medical Form " type="button">
			<br>
		</c:if>
		<%-- CASE APPROVED --%>
		<c:if test="${myCase.caseStatusId.caseStatusId eq 9}">
			<input style="width:250px" title="Print Investigation Letter"
				accesskey=""
				class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
				onClick="javascript:printInvestigationLetter()"
				name="authorizationLetter" value=" Investigation Letter "
				type="button">
			<br>
			<input style="width:250px"
				title="Print Guarantee Letter [Alt+Shift+I]" accesskey="I"
				class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
				onClick="javascript:printGuaranteeLetter()" name="printGL"
				value=" Guarantee Letter " type="button">
			<br>
			<c:if test="${myCase.claimId.claimStatus.caseStatusId eq 19}">
				<input style="width:250px" title="Benefit Calculation [Alt+Shift+C]"
					accesskey="C"
					class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
					onClick="javascript:printBenefitLetterCalculation('notfinalized')"
					id="benefitLetterMenuBtn" name="bcletter"
					value="Benefit Calculation" type="button">
				<br>
			</c:if>
		</c:if>
		<%-- CASE PENDING --%>
		<c:if test="${myCase.caseStatusId.caseStatusId eq 10}">
			<input style="width:250px" title="Print Investigation Letter"
				accesskey=""
				class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
				onClick="javascript:printInvestigationLetter()"
				name="authorizationLetter" value=" Investigation Letter "
				type="button">
			<br>
			<input style="width:250px"
				title="Print Guarantee Letter [Alt+Shift+I]" accesskey="I"
				class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
				onClick="javascript:printGuaranteeLetter()" name="printGL"
				value=" Guarantee Letter " type="button">
			<br>
		</c:if>
		<%-- CASE CLOSED --%>
		<c:if test="${myCase.caseStatusId.caseStatusId eq 5}">
			<input style="width:250px" title="Print Investigation Letter"
				accesskey=""
				class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
				onClick="javascript:printInvestigationLetter()"
				name="authorizationLetter" value=" Investigation Letter "
				type="button">
			<br>
			<input style="width:250px"
				title="Print Guarantee Letter [Alt+Shift+I]" accesskey="I"
				class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
				onClick="javascript:printGuaranteeLetter()" name="printGL"
				value=" Guarantee Letter " type="button">
			<br>
			<c:if test="${myCase.claimId.claimStatus.caseStatusId eq 19}">
				<input style="width:250px" title="Benefit Calculation [Alt+Shift+C]"
					accesskey="C"
					class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
					onClick="javascript:printBenefitLetterCalculation('notfinalized')"
					id="benefitLetterMenuBtn" name="indemnityLetter"
					value="Benefit Calculation" type="button">
				<br>
			</c:if>
		</c:if>
		<%-- CASE FINALIZED --%>
		<c:if test="${myCase.caseStatusId.caseStatusId eq 15}">
			<input style="width:250px" title="Print Investigation Letter"
				accesskey=""
				class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
				onClick="javascript:printInvestigationLetter()"
				name="authorizationLetter" value=" Investigation Letter "
				type="button">
			<br>
			<input title="Finalized Benefit Calculation [Alt+Shift+C]"
				accesskey="C"
				class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
				onClick="javascript:printBenefitLetterCalculation('finalized')"
				id="finalizedBenefitLetterMenuBtn" name="indemnityLetter"
				value="Finalized Benefit Calculation" type="button">
			<br>
			<input style="width:250px"
				title="Print Guarantee Letter [Alt+Shift+I]" accesskey="I"
				class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
				onClick="javascript:printGuaranteeLetter()" name="printGL"
				value=" Guarantee Letter " type="button">
			<br>
		</c:if>
		<%-- CASE PRE REJECTED, add by aju on 20150824 for preAdmission :D --%>
		<c:if test="${myCase.caseStatusId.caseStatusId eq 21}">
			<input style="width:250px"
				title="Print Guarantee Letter [Alt+Shift+I]" accesskey="I"
				class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
				onClick="javascript:printGuaranteeLetter()" name="printGL"
				value=" Guarantee Letter " type="button">
			<br>
			<input style="width:250px"
				title="Print Rejection Letter [Alt+Shift+R]" accesskey="R"
				class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
				onClick="javascript:printRejectedLetter()" name="printRejectLetter"
				value=" Print Rejection Letter " type="button">
			<br>
		</c:if>
		<%-- CASE REJECTED --%>
		<c:if test="${myCase.caseStatusId.caseStatusId eq 4}">
			<input style="width:250px"
				title="Print Guarantee Letter [Alt+Shift+I]" accesskey="I"
				class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
				onClick="javascript:printGuaranteeLetter()" name="printGL"
				value=" Guarantee Letter " type="button">
			<br>
			<input style="width:250px"
				title="Print Rejection Letter [Alt+Shift+R]" accesskey="R"
				class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
				onClick="javascript:printRejectedLetter()" name="printRejectLetter"
				value=" Print Rejection Letter " type="button">
			<br>
		</c:if>
		<input style="width:250px" title="Print Returning Letter" accesskey=""
			class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
			onClick="javascript:printReturningLetter()" name="returningtLetter"
			value=" Returning Letter " type="button">
		<br>
		<br>
		<br>
		<%-- Add 01042015, untuk print Confirm Letter khusus Maternity, Kemotherapy, Haemodialisa, Surgery --%>
		<!-- 	<br><input title="Print CL Maternity [Alt+Shift+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:printConfirmLetter('maternity')" name="confirmLetterMaternity" value="    Maternity    " type="button"> -->
		<!-- 	<br><input title="Print CL Kemotherapy [Alt+Shift+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:printConfirmLetter('kemotherapy')" name="confirmLetterKemotherapy" value=" Kemotherapy " type="button"> -->
		<!-- 	<br><input title="Print CL Haemodialisa [Alt+Shift+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:printConfirmLetter('haemodialisa')" name="confirmLetterHaemodialisa" value=" Haemodialisa " type="button"> -->
		<!-- 	<br><input title="Print CL Surgery [Alt+Shift+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:printConfirmLetter('surgery')" name="confirmLetterSurgery" value="     Surgery      " type="button">    			 -->
	</c:if>
	<br>
</div>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>
		<tr>
			<td nowrap="nowrap"><h3>
					<img src="images/h3Arrow.gif" border="0">&nbsp;Detail Case
				</h3>
			</td>
			<td width="100%"><img src="images/blank.gif" height="1" width="1"></td>
			<c:if test="${theUser.userType ne 4 }">
				<td align="right"><input title="Case Log" name="errorLog"
					id="errorLog" value=" Case Log " class="errorLog" type="button"
					onClick="javascript:printErrorLog()"></td>
				<td align="right"><input title="Add Case Log"
					name="addErrorLog" value=" Member Log " class="errorLog"
					type="button" onClick="javascript:adderrorlog()"></td>
			</c:if>
		</tr>
	</tbody>
</table>



<form action="case" method="GET" name="form1" id="form_layout">

	<input type="hidden" name="navigation" value=""> <input
		type="hidden" name="caseId" value="<c:out value="${myCase.caseId}" />">
	<input type="hidden" name="memberId"
		value="<c:out value="${myCase.memberId.memberId}" />" /> <input
		type="hidden" name="providerId"
		value="<c:out value="${myCase.providerId.providerId}" />" /> <input
		type="hidden" name="caseCategoryId"
		value="<c:out value="${myCase.caseCategoryId.caseCategoryId}" />" />

	<c:set var="startdate" value="${myCase.caseStartTime }"></c:set>
	<fmt:parseNumber value="${startdate.time / (1000*60*60*24) }"
		integerOnly="true" var="starttime" scope="page" />
	<c:set var="currentdate" value="<%=new java.util.Date()%>"></c:set>
	<fmt:parseNumber value="${currentdate.time / (1000*60*60*24) }"
		integerOnly="true" var="currenttime" scope="request" />

	<c:set var="interval" value="${(currenttime-starttime) }"></c:set>

	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tbody>
			<tr>
				<td style="padding-bottom: 2px; padding-top: 2px;">
					<%
						/*
						     		Modified by aju on 20150329
						     		Make it readable!!
						     		Make rule for button showed depends per CASE STATUS first then per User Type and then per User Role
						 */
					%> 
<%
 	//Modified by aju on 20150824, preAdmission don't have LOA
 	//<input title="Print Register Struct [Alt+Shift+P]" accesskey="M" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:printRegisterStruct()" name="edcManualPrint" value=" Print Register Struct " type="button">
 %> 
 					<c:if test="${myCase.caseStatusId.caseStatusId ne 19 and myCase.caseStatusId.caseStatusId ne 20 and myCase.caseStatusId.caseStatusId ne 21 and myCase.caseStatusId.caseStatusId ne 22}">
						<input title="Print Register Struct [Alt+Shift+P]" accesskey="M"
							class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
							onClick="javascript:printRegisterStruct()" name="edcManualPrint"
							value=" Print Register Struct " type="button">
					</c:if> 
<%
 	//Add by aju on 20150824 CASE PRE AUTH BUTTONS
 %> 
 					<c:if test="${myCase.caseStatusId.caseStatusId eq 20}">
						<%
							//User Type : TPA
						%>
						<c:if test="${theUser.userType eq 2}">
							<%
								//Role Admin
							%>
							<c:if test="${theUser.roleId.roleId eq 0 }">
								<!-- <input title="Edit [Alt+Shift+E]" accesskey="E"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									onClick="javascript:ubah()" name="Edit" value=" Update "
									type="button"> -->
								<input title="Hapus [Alt+Shift+H]" accesskey="H"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									onClick="javascript:hapus()" name="Delete" value=" Delete "
									type="button">
								<input title="Pending Case [Alt+Shift+P]" accesskey="P"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									onClick="javascript:pending()" name="pendingButton"
									value=" Pending Case " type="button">
								<input title="Void Manual [Alt+Shift+V]" accesskey="M"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									onClick="javascript:voidManual()" name="edcManualVoid"
									value=" Void Manual " type="button">
									
								<input title="Print Letter [Alt+Shift+C]" accesskey="C"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									id="clDialog" name="confirmLetter" value=" Print Letter "
									type="button">
							</c:if>
							<%
								//Role (Case Management Head) or (Case Management Staff)
							%>
							<c:if test="${theUser.roleId.roleId eq 1 or theUser.roleId.roleId eq 2}">
								<!-- <input title="Edit [Alt+Shift+E]" accesskey="E"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									onClick="javascript:ubah()" name="Edit" value=" Update "
									type="button"> -->
								<input title="Pending Case [Alt+Shift+P]" accesskey="P"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									onClick="javascript:pending()" name="pendingButton"
									value=" Pending Case " type="button">
								<input title="Approve [Alt+Shift+A]" accesskey="A"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									onClick="javascript:approve()" name="approveButton"
									value=" Approve " type="button">
								<input title="Approve Ex Gratia [Alt+Shift+A]" accesskey="E"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									onClick="javascript:approveExGratia()" name="approveButtonEx"
									value=" Approve Ex Gratia " type="button">
								<input title="Reject [Alt+Shift+R]" accesskey="R"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									onClick="javascript:reject()" name="rejectButton"
									value=" Reject " type="button">
									
								<input title="Print Letter [Alt+Shift+C]" accesskey="C"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									id="clDialog" name="printLetter" value=" Print Letter "
									type="button">
							</c:if>
							<%
								//Role Customer Service
							%>
							<c:if test="${theUser.roleId.roleId eq 5}">
								<input title="Void Manual [Alt+Shift+V]" accesskey="M"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									onClick="javascript:voidManual()" name="edcManualVoid"
									value=" Void Manual " type="button">
							</c:if>
						</c:if>
					</c:if> 
<%
 	//CASE OPEN BUTTONS
 %> 
 					<c:if test="${myCase.caseStatusId.caseStatusId eq 1}">
						<%
							//User Type : TPA
						%>
						<c:if test="${theUser.userType eq 2}">
							<%
								//Role Admin
							%>
							<c:if test="${theUser.roleId.roleId eq 0}">
								<input title="Edit [Alt+Shift+E]" accesskey="E"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									onClick="javascript:ubah()" name="Edit" value=" Update "
									type="button">
								<input title="Hapus [Alt+Shift+H]" accesskey="H"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									onClick="javascript:hapus()" name="Delete" value=" Delete "
									type="button">
								<input title="Pending Case [Alt+Shift+P]" accesskey="P"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									onClick="javascript:pending()" name="pendingButton"
									value=" Pending Case " type="button">
								<input title="Payment Manual [Alt+Shift+M]" accesskey="M"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									onClick="javascript:paymentManual()" name="edcManual"
									value=" Payment Manual " type="button">
								<input title="Void Manual [Alt+Shift+V]" accesskey="M"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									onClick="javascript:voidManual()" name="edcManualVoid"
									value=" Void Manual " type="button">


								<input title="Print Letter [Alt+Shift+C]" accesskey="C"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									id="clDialog" name="confirmLetter" value=" Print Letter "
									type="button">
							</c:if>
							<%
								//Role (Case Management Head) or (Case Management Staff)
							%>
							<c:if test="${theUser.roleId.roleId eq 1 or theUser.roleId.roleId eq 2}">
								<input title="Edit [Alt+Shift+E]" accesskey="E"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									onClick="javascript:ubah()" name="Edit" value=" Update "
									type="button">
								<input title="Pending Case [Alt+Shift+P]" accesskey="P"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									onClick="javascript:pending()" name="pendingButton"
									value=" Pending Case " type="button">
								<input title="Approve [Alt+Shift+A]" accesskey="A"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									onClick="javascript:approve()" name="approveButton"
									value=" Approve " type="button">
								<input title="Approve Ex Gratia [Alt+Shift+A]" accesskey="E"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									onClick="javascript:approveExGratia()" name="approveButtonEx"
									value=" Approve Ex Gratia " type="button">
								<input title="Reject [Alt+Shift+R]" accesskey="R"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									onClick="javascript:reject()" name="rejectButton"
									value=" Reject " type="button">

								<input title="Print Letter [Alt+Shift+C]" accesskey="C"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									id="clDialog" name="printLetter" value=" Print Letter "
									type="button">
							</c:if>
							<%
								//Role Customer Service
							%>
							<c:if test="${theUser.roleId.roleId eq 5}">
								<input title="Payment Manual [Alt+Shift+M]" accesskey="M"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									onClick="javascript:paymentManual()" name="edcManual"
									value=" Payment Manual " type="button">
								<input title="Void Manual [Alt+Shift+V]" accesskey="M"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									onClick="javascript:voidManual()" name="edcManualVoid"
									value=" Void Manual " type="button">
								<!-- removed by aju on 20150824, diatas udah ada, diluar if button per case status zzzz
        			<input title="Print Register Struct [Alt+Shift+P]" accesskey="M" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:printRegisterStruct()" name="edcManualPrint" value=" Print Register Struct " type="button">
        			-->
							</c:if>
						</c:if>
					</c:if> 
<%
 	//CASE PRE PENDING BUTTONS add by aju on 20150827
 %> 
 					<c:if test="${myCase.caseStatusId.caseStatusId eq 22}">
						<%
							//User Type : TPA
						%>
						<c:if test="${theUser.userType eq 2}">
							<%
								//Role Admin
							%>
							<c:if test="${theUser.roleId.roleId eq 0}">
								<!-- <input title="Edit [Alt+Shift+E]" accesskey="E"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									onClick="javascript:ubah()" name="Edit" value=" Update "
									type="button"> -->
							</c:if>
							
							<c:if test="${theUser.roleId.roleId eq 1 or theUser.roleId.roleId eq 2}">
								<!-- <input title="Edit [Alt+Shift+E]" accesskey="E"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									onClick="javascript:ubah()" name="Edit" value=" Update "
									type="button"> -->
								<input title="Approve [Alt+Shift+A]" accesskey="A"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									onClick="javascript:approve()" name="approveButton"
									value=" Approve " type="button">
								<input title="Reject [Alt+Shift+R]" accesskey="R"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									onClick="javascript:reject()" name="rejectButton"
									value=" Reject " type="button">
							</c:if>
						</c:if>
					</c:if> 
<%
 	//CASE PENDING BUTTONS
 %> 
 					<c:if test="${myCase.caseStatusId.caseStatusId eq 10}">
						<%
							//User Type : TPA
						%>
						<c:if test="${theUser.userType eq 2}">
							<c:if test="${theUser.roleId.roleId eq 0}">
								<!-- <input title="Edit [Alt+Shift+E]" accesskey="E"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									onClick="javascript:ubah()" name="Edit" value=" Update "
									type="button"> -->
								<input title="Print Letter [Alt+Shift+C]" accesskey="C"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									id="clDialog" name="printLetter" value=" Print Letter "
									type="button">
							</c:if>
							
							<c:if test="${theUser.roleId.roleId eq 1 or theUser.roleId.roleId eq 2}">
								<!-- <input title="Edit [Alt+Shift+E]" accesskey="E"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									onClick="javascript:ubah()" name="Edit" value=" Update "
									type="button"> -->
								<input title="Approve [Alt+Shift+A]" accesskey="A"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									onClick="javascript:approve()" name="approveButton"
									value=" Approve " type="button">
								<input title="Approve Ex Gratia [Alt+Shift+A]" accesskey="E"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									onClick="javascript:approveExGratia()" name="approveButtonEx"
									value=" Approve Ex Gratia " type="button">
								<input title="Reject [Alt+Shift+R]" accesskey="R"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									onClick="javascript:reject()" name="rejectButton"
									value=" Reject " type="button">

								<input title="Print Letter [Alt+Shift+C]" accesskey="C"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									id="clDialog" name="printLetter" value=" Print Letter "
									type="button">
							</c:if>
						</c:if>
					</c:if> 
<%
 	//CASE PENDING DOCUMENT BUTTONS
 %> 
 					<c:if test="${myCase.caseStatusId.caseStatusId eq 2}">
						<%
							//User Type : TPA
						%>
						<c:if test="${theUser.userType eq 2}">
							<%
 								//Role Admin
							%>
							<c:if test="${theUser.roleId.roleId eq 0}">
								<!-- <input title="Edit [Alt+Shift+E]" accesskey="E"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									onClick="javascript:ubah()" name="Edit" value=" Update "
									type="button"> -->
								<input title="Print Letter [Alt+Shift+C]" accesskey="C"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									id="clDialog" name="printLetter" value=" Print Letter "
									type="button">
							</c:if>
							
							<c:if test="${theUser.roleId.roleId eq 1 or theUser.roleId.roleId eq 2}">
								<!-- <input title="Edit [Alt+Shift+E]" accesskey="E"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									onClick="javascript:ubah()" name="Edit" value=" Update "
									type="button"> -->
								<input title="Approve [Alt+Shift+A]" accesskey="A"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									onClick="javascript:approve()" name="approveButton"
									value=" Approve " type="button">
								<input title="Approve Ex Gratia [Alt+Shift+A]" accesskey="E"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									onClick="javascript:approveExGratia()" name="approveButtonEx"
									value=" Approve Ex Gratia " type="button">
								<input title="Reject [Alt+Shift+R]" accesskey="R"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									onClick="javascript:reject()" name="rejectButton"
									value=" Reject " type="button">

								<input title="Print Letter [Alt+Shift+C]" accesskey="C"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									id="clDialog" name="printLetter" value=" Print Letter "
									type="button">
							</c:if>
						</c:if>
					</c:if> 
<%
 	//CASE PENDING INVESTIGATION BUTTONS
 %> 
 					<c:if test="${myCase.caseStatusId.caseStatusId eq 7}">
						<%
							//User Type : TPA
						%>
						<c:if test="${theUser.userType eq 2}">
							<%
								//Role Admin
							%>
							<c:if test="${theUser.roleId.roleId eq 0}">
								<!-- <input title="Edit [Alt+Shift+E]" accesskey="E"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									onClick="javascript:ubah()" name="Edit" value=" Update "
									type="button"> -->
								<input title="Print Letter [Alt+Shift+C]" accesskey="C"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									id="clDialog" name="printLetter" value=" Print Letter "
									type="button">
							</c:if>
							
							<c:if test="${theUser.roleId.roleId eq 1 or theUser.roleId.roleId eq 2}">
								<!-- <input title="Edit [Alt+Shift+E]" accesskey="E"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									onClick="javascript:ubah()" name="Edit" value=" Update "
									type="button"> -->
								<input title="Approve [Alt+Shift+A]" accesskey="A"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									onClick="javascript:approve()" name="approveButton"
									value=" Approve " type="button">
								<input title="Approve Ex Gratia [Alt+Shift+A]" accesskey="E"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									onClick="javascript:approveExGratia()" name="approveButtonEx"
									value=" Approve Ex Gratia " type="button">
								<input title="Reject [Alt+Shift+R]" accesskey="R"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									onClick="javascript:reject()" name="rejectButton"
									value=" Reject " type="button">

								<input title="Print Letter [Alt+Shift+C]" accesskey="C"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									id="clDialog" name="printLetter" value=" Print Letter "
									type="button">
							</c:if>
						</c:if>
					</c:if> 
<%
 	//CASE  GREY AREA BUTTONS
 %> 
 					<c:if test="${myCase.caseStatusId.caseStatusId eq 18}">
						<%
							//User Type : TPA
						%>
						<c:if test="${theUser.userType eq 2}">
							<%
								//Role Admin
							%>
							<c:if test="${theUser.roleId.roleId eq 0}">
								<!-- <input title="Edit [Alt+Shift+E]" accesskey="E"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									onClick="javascript:ubah()" name="Edit" value=" Update "
									type="button"> -->
								<input title="Print Letter [Alt+Shift+C]" accesskey="C"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									id="clDialog" name="printLetter" value=" Print Letter "
									type="button">
							</c:if>
							
							<c:if test="${theUser.roleId.roleId eq 1 or theUser.roleId.roleId eq 2}">
								<!-- <input title="Edit [Alt+Shift+E]" accesskey="E"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									onClick="javascript:ubah()" name="Edit" value=" Update "
									type="button"> -->
								<input title="Approve [Alt+Shift+A]" accesskey="A"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									onClick="javascript:approve()" name="approveButton"
									value=" Approve " type="button">
								<input title="Approve Ex Gratia [Alt+Shift+A]" accesskey="E"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									onClick="javascript:approveExGratia()" name="approveButtonEx"
									value=" Approve Ex Gratia " type="button">
								<input title="Reject [Alt+Shift+R]" accesskey="R"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									onClick="javascript:reject()" name="rejectButton"
									value=" Reject " type="button">

								<input title="Print Letter [Alt+Shift+C]" accesskey="C"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									id="clDialog" name="printLetter" value=" Print Letter "
									type="button">
							</c:if>
						</c:if>
					</c:if> 
<%
 	//CASE APPROVED BUTTONS
 %> 
 					<c:if test="${myCase.caseStatusId.caseStatusId eq 9}">
						<%
							//User Type : TPA
						%>
						<c:if test="${theUser.userType eq 2}">
							<%
								//Role Admin
							%>
							<c:if test="${theUser.roleId.roleId eq 0 }">
								<!-- <input title="Edit [Alt+Shift+E]" accesskey="E"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									onClick="javascript:ubah()" name="Edit" value=" Update "
									type="button"> -->
							</c:if>
							
							<c:if test="${theUser.roleId.roleId eq 1 or theUser.roleId.roleId eq 2 }">
								<!-- <input title="Edit [Alt+Shift+E]" accesskey="E"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									onClick="javascript:ubah()" name="Edit" value=" Update "
									type="button"> -->
							</c:if>
							
							<input title="Extend [Alt+Shift+X]" accesskey="X"
								class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
								onClick="javascript:extendCase()" name="extendButton"
								value=" Extend Case " type="button">
							<input title="Terminate [Alt+Shift+T]" accesskey="T"
								class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
								onClick="javascript:terminate()" name="terminateButton"
								value=" Terminate Case " type="button">

							<input title="Print Letter [Alt+Shift+C]" accesskey="C"
								class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
								id="clDialog" name="printLetter" value=" Print Letter "
								type="button">
						</c:if>
						
						<c:if test="${theUser.userType eq 4}">
							<input title="Close Case " accesskey="C"
								class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
								onClick="javascript:referCaseManaged()"
								name="closeManagedCaseBtn" value=" Close Case " type="button">
							<input title="Add Medicine " accesskey="C"
								class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
								onClick="javascript:addMedicine()" name="addMedBtn"
								value=" Medicine " type="button">
							<input title="Add Procedure" accesskey="C"
								class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
								onClick="javascript:addProcedure()" name="addProcedureBtn"
								value=" Procedure " type="button">
							<input title="Add Diagnostic Test" accesskey="C"
								class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
								onClick="javascript:addDiagnostic()" name="addDiagnosticBtn"
								value=" Diagnostic Test " type="button">
						</c:if>
					</c:if> 
<%
 	//CASE PRE REJECT BUTTONS, add by aju on 20150824, for preAdmission :D
 %>
					<c:if test="${myCase.caseStatusId.caseStatusId eq 21}">
						<%
							//User Type : TPA
						%>
						<c:if test="${theUser.userType eq 2}">
							<c:if test="${theUser.roleId.roleId eq 0 }">
								<!-- <input title="Edit [Alt+Shift+E]" accesskey="E"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									onClick="javascript:ubah()" name="Edit" value=" Update "
									type="button"> -->
							</c:if>
							
							<c:if test="${theUser.roleId.roleId eq 1 or theUser.roleId.roleId eq 2 }">
								<!-- <input title="Edit [Alt+Shift+E]" accesskey="E"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									onClick="javascript:ubah()" name="Edit" value=" Update "
									type="button"> -->
							</c:if>
							
							<input title="Print Letter [Alt+Shift+C]" accesskey="C"
								class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
								id="clDialog" name="printLetter" value=" Print Letter "
								type="button">


						</c:if>
					</c:if> 
<%
 	//CASE REJECT BUTTONS
 %> 
 					<c:if test="${myCase.caseStatusId.caseStatusId eq 4}">
						<%
							//User Type : TPA
						%>
						<c:if test="${theUser.userType eq 2}">
							<c:if test="${theUser.roleId.roleId eq 0 }">
								<!-- <input title="Edit [Alt+Shift+E]" accesskey="E"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									onClick="javascript:ubah()" name="Edit" value=" Update "
									type="button"> -->
							</c:if>
							
							<c:if test="${theUser.roleId.roleId eq 1 or theUser.roleId.roleId eq 2 }">
								<!-- <input title="Edit [Alt+Shift+E]" accesskey="E"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									onClick="javascript:ubah()" name="Edit" value=" Update "
									type="button"> -->
							</c:if>
						
							<input title="Print Letter [Alt+Shift+C]" accesskey="C"
								class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
								id="clDialog" name="printLetter" value=" Print Letter "
								type="button">
						</c:if>
					</c:if> 
<%
 	//CASE CLOSED BUTTONS
 %> 
 					<c:if test="${myCase.caseStatusId.caseStatusId eq 5}">
						<%
							//User Type : TPA
						%>
						<c:if test="${theUser.userType eq 2}">
							<c:if test="${theUser.roleId.roleId eq 0 }">
								<!-- <input title="Edit [Alt+Shift+E]" accesskey="E"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									onClick="javascript:ubah()" name="Edit" value=" Update "
									type="button"> -->
							</c:if>
							
							<c:if test="${theUser.roleId.roleId eq 1 or theUser.roleId.roleId eq 2 }">
								<!-- <input title="Edit [Alt+Shift+E]" accesskey="E"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									onClick="javascript:ubah()" name="Edit" value=" Update "
									type="button"> -->
							</c:if>
						
							<input title="Print Closing Letter [Alt+Shift+C]" accesskey="C"
								class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
								onClick="javascript:printClosingLetter()" name="printClosing"
								value=" Print Closing Letter " type="button">
							<input title="Download Report [Alt+Shift+D]" accesskey="D"
								class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
								onClick="javascript:downloadMonitoring()" name="DownloadMonitor"
								value=" Download Report " type="button">
							<input title="Download Conversation [Alt+Shift+A]" accesskey="A"
								class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
								onClick="javascript:downloadConversation()" name="DownloadConv"
								value=" Download Conversation " type="button">
							<input title="Refer Case [Alt+Shift+R]" accesskey="R"
								class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
								onClick="javascript:referCase()" name="referCaseBtn"
								value=" Refer Case " type="button">
							
							<c:if test="${theUser.roleId.roleId eq 0 or theUser.roleId.roleId eq 1 or theUser.roleId.roleId eq 2}">
								<c:if test="${myCase.claimId.claimStatus.caseStatusId eq 19 or myCase.claimId.claimStatus.caseStatusId eq 1}">
									<input title="Case Calculation" accesskey="J"
										class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
										onClick="javascript:calculateCase()" name="calcBtn"
										value=" Cost Calculation " type="button">
									<input title="Cost Report" accesskey="R"
										class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
										onClick="javascript:printCostReport()" name="calcBtn"
										value=" Print Cost Report " type="button">
									<input title="Print Cost Containment [Alt+Shift+A]"
										accesskey="T"
										class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
										onClick="javascript:printCostContaintment()"
										name="printContain" value=" Print Cost Containment "
										type="button">
								</c:if>
								
								<c:if test="${myCase.claimId eq null}"></c:if>
							</c:if>
							
							<input title="Print Letter [Alt+Shift+C]" accesskey="C"
								class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
								id="clDialog" name="printLetter" value=" Print Letter "
								type="button">

							<c:if test="${interval < 14  }">
								<input title="Cancel Discharge [Alt+Shift+X]" accesskey="X"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									id="canDischarge" name="btnCancelDischarge"
									onClick="javascript:cancelDischarge()"
									value=" Cancel Discharge " type="button">
							</c:if>
						</c:if>
					</c:if> 
<%
 	//CASE FINALIZED BUTTONS
 %> 
 					<c:if test="${myCase.caseStatusId.caseStatusId eq 15}">
						<%
							//User Type : TPA
						%>
						<c:if test="${theUser.userType eq 2}">
							<c:if test="${theUser.roleId.roleId eq 0 }">
								<!-- <input title="Edit [Alt+Shift+E]" accesskey="E"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									onClick="javascript:ubah()" name="Edit" value=" Update "
									type="button"> -->
							</c:if>
							
							<c:if test="${theUser.roleId.roleId eq 1 or theUser.roleId.roleId eq 2 }">
								<!-- <input title="Edit [Alt+Shift+E]" accesskey="E"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									onClick="javascript:ubah()" name="Edit" value=" Update "
									type="button"> -->
							</c:if>
						
							<c:if test="${theUser.roleId.roleId ne 6}">
								<input title="Print Closing Letter [Alt+Shift+C]" accesskey="C"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									onClick="javascript:printClosingLetter()" name="printClosing"
									value=" Print Closing Letter " type="button">
								<input title="Download Report [Alt+Shift+D]" accesskey="D"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									onClick="javascript:downloadMonitoring()"
									name="DownloadMonitor" value=" Download Report " type="button">
								<input title="Download Conversation [Alt+Shift+A]" accesskey="A"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									onClick="javascript:downloadConversation()" name="DownloadConv"
									value=" Download Conversation " type="button">
								<input title="Refer Case [Alt+Shift+R]" accesskey="R"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									onClick="javascript:referCase()" name="referCaseBtn"
									value=" Refer Case " type="button">
								
								<c:if test="${theUser.roleId.roleId eq 0 or theUser.roleId.roleId eq 1 or theUser.roleId.roleId eq 2}">
									<c:if test="${myCase.claimId.claimStatus.caseStatusId eq 19}">
										<input title="Case Calculation" accesskey="J"
											class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
											onClick="javascript:calculateCase()" name="calcBtn"
											value=" Cost Calculation " type="button">
										<input title="Cost Report" accesskey="R"
											class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
											onClick="javascript:printCostReport()" name="calcBtn"
											value=" Print Cost Report " type="button">
										<input title="Print Cost Containment [Alt+Shift+A]"
											accesskey="T"
											class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
											onClick="javascript:printCostContaintment()"
											name="printContain" value=" Print Cost Containment "
											type="button">
									</c:if>
								</c:if>
								
								<input title="Print Letter [Alt+Shift+C]" accesskey="C"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									id="clDialog" name="printLetter" value=" Print Letter "
									type="button">

								<c:if test="${interval < 14  }">
									<input title="Cancel Discharge [Alt+Shift+X]" accesskey="X"
										class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
										id="canDischarge" name="btnCancelDischarge"
										onClick="javascript:cancelDischarge()"
										value=" Cancel Discharge " type="button">
								</c:if>
							</c:if>
						</c:if>
					</c:if> 
<%
 	//CASE REFERRED BUTTONS
 %> 
 					<c:if test="${myCase.caseStatusId.caseStatusId eq 17}">
						<%
							//User Type : TPA
						%>
						<c:if test="${theUser.userType eq 2}">
							<c:if test="${theUser.roleId.roleId eq 0 }">
								<!-- <input title="Edit [Alt+Shift+E]" accesskey="E"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									onClick="javascript:ubah()" name="Edit" value=" Update "
									type="button"> -->
							</c:if>
							
							<c:if test="${theUser.roleId.roleId eq 1 or theUser.roleId.roleId eq 2 }">
								<!-- <input title="Edit [Alt+Shift+E]" accesskey="E"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									onClick="javascript:ubah()" name="Edit" value=" Update "
									type="button"> -->
							</c:if>
						
							<input title="Cancel Refer" accesskey="C"
								class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
								onClick="javascript:reverseRefer()" name="cancelRef"
								value=" Cancel Refer " type="button">
							<input title="Print Struct Refer" accesskey="C"
								class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
								onClick="javascript:printStructRefer()" name="printStructRef"
								value=" Print Struct Refer " type="button">

							<input title="Print Letter [Alt+Shift+C]" accesskey="C"
								class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
								id="clDialog" name="printLetter" value=" Print Letter "
								type="button">
						</c:if>
					</c:if> 

<%
 	//CASE PRE OPEN BUTTONS
 %> 
 					<c:if test="${myCase.caseStatusId.caseStatusId eq 19}">
						<%
							//User Type : TPA
						%>
						<c:if test="${theUser.userType eq 2}">
							<c:if test="${theUser.roleId.roleId eq 0 }">
								<!-- <input title="Edit [Alt+Shift+E]" accesskey="E"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									onClick="javascript:ubah()" name="Edit" value=" Update "
									type="button"> -->
							</c:if>
							
							<c:if test="${theUser.roleId.roleId eq 1 or theUser.roleId.roleId eq 2 }">
								<!-- <input title="Edit [Alt+Shift+E]" accesskey="E"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									onClick="javascript:ubah()" name="Edit" value=" Update "
									type="button"> -->
							</c:if>
						</c:if>
					</c:if> 

<%
 	//CASE VOID BUTTONS
 %> 
 					<c:if test="${myCase.caseStatusId.caseStatusId eq -1}">
						<%
							//User Type : TPA
						%>
						<c:if test="${theUser.userType eq 2}">
							<c:if test="${theUser.roleId.roleId eq 0 }">
								<!-- <input title="Edit [Alt+Shift+E]" accesskey="E"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									onClick="javascript:ubah()" name="Edit" value=" Update "
									type="button"> -->
							</c:if>
							
							<c:if test="${theUser.roleId.roleId eq 1 or theUser.roleId.roleId eq 2 }">
								<!-- <input title="Edit [Alt+Shift+E]" accesskey="E"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									onClick="javascript:ubah()" name="Edit" value=" Update "
									type="button"> -->
							</c:if>
						</c:if>
					</c:if>

<%
 	//CASE GREY AREA BUTTONS
 %> 
 <%
 	//Remarks by adq
 %> <%-- <c:if test="${myCase.caseStatusId.caseStatusId eq 18}">
			<% //User Type : TPA %>
			<c:if test="${theUser.userType eq 2}">	
	       		<input title="Print Letter [Alt+Shift+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" id="clDialog" name="printLetter" value=" Print Letter " type="button">
	       		
	       	</c:if>
       	</c:if>
	    --%> 

<%
 	//On User Type TPA
 %> <c:if test="${theUser.userType eq 2}">
						<%
							//Print Sruct Button
						%>
						<c:if test="${myCase.claimId ne null}">
							<%
								// modified by aju on 20150824, for preAdmission, there's no LOA :D
										//<input title="Print Struct" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:printStruct()" name="struct" value=" Print Struct " type="button">
							%>
							<c:if
								test="${myCase.caseStatusId.caseStatusId ne 19 and myCase.caseStatusId.caseStatusId ne 20 and myCase.caseStatusId.caseStatusId ne 21 and myCase.caseStatusId.caseStatusId ne 22}">
								<input title="Print Struct" accesskey="C"
									class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
									onClick="javascript:printStruct()" name="struct"
									value=" Print Struct " type="button">
							</c:if>

						</c:if>
						<%
							//Assign Case Button
						%>
						<%-- <c:if test="${myCase.assigneeId eq null and ( myCase.caseStatusId.caseStatusId eq 1 
	       	 				or myCase.caseStatusId.caseStatusId eq  9 )}"> --%>
						<c:if test="${theUser.roleId.roleId ne 6}">
							<input title="Assign Case" accesskey="C"
								class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
								onClick="javascript:assignCase()" name="assCase"
								value=" Assign Case " type="button">
						</c:if>
						<%-- 	</c:if> --%>
					</c:if> <%
 	//On User Type Provider
 %> <c:if test="${theUser.userType eq 4}">
						<%
							//Refer Case Button
						%>

						<c:if test="${myCase.caseStatusId.caseStatusId eq 1}">
							<input title="Payment Manual [Alt+Shift+M]" accesskey="M"
								class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
								onClick="javascript:paymentManual()" name="edcManual"
								value=" Payment Manual " type="button">
							<!-- <input title="Void Manual [Alt+Shift+V]" accesskey="M"
								class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
								onClick="javascript:voidManual()" name="edcManualVoid"
								value=" Void Manual " type="button"> -->
						</c:if>

						<c:if test="${myCase.caseStatusId.caseStatusId eq 15}">
							<input title="Refer Case [Alt+Shift+R]" accesskey="R"
								class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
								onClick="javascript:referCase()" name="referCaseBtn"
								value=" Refer Case " type="button">
							<input title="Void Manual [Alt+Shift+V]" accesskey="M"
								class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
								onClick="javascript:voidManual()" name="edcManualVoid"
								value=" Void Manual " type="button">
							<input title="Save [Alt+Shift+S]" accesskey="S" 
								class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" 
								onClick="javascript:printPayment()" name="Save" 
								value=" Reprint Claim " type="button">
						</c:if>
					</c:if></td>
				<td align="right"></td>

				<td align="right"></td>
			</tr>
		</tbody>
	</table>

	<%
		String navStatus = "";
		if(session.getAttribute("nav")!=null){
			navStatus = session.getAttribute("nav").toString();
		}
	%>

	<%@ include file="../mainContentCase.jsp"%>

	<%
		if (navStatus.equals("1")) {
			session.setAttribute("nav", "1");

		} else if (navStatus.equals("2")) {
			session.setAttribute("nav", "2");
		}
	%>


</form>

<br />
<c:if test="${claimItemList ne null}">

	<table class="listView" cellspacing="0" cellpadding="0" width="100%">
		<tbody>


			<tr height="20">
				<td width="2%" nowrap="nowrap" class="listViewThS1" scope="col">
					<img src="images/blank.gif" alt="asd" height="1" width="1">No.</td>

				<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%"
					style="text-align: center;">Claim Item Name &nbsp;</td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%"
					style="text-align: center;">Amount &nbsp;</td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%"
					style="text-align: center;">Charge &nbsp;</td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%"
					style="text-align: center;">Approved &nbsp;</td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%"
					style="text-align: center;">Excess &nbsp;</td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%"
					style="text-align: center;">Ex Gratia&nbsp;</td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="25%"
					style="text-align: center;">Remarks &nbsp;</td>
			</tr>
			<c:forEach items="${claimItemList}" var="claimItem"
				varStatus="status">
				<tr height="20">
					<td class="oddListRowS1" align="center" bgcolor="#e7f0fe"
						nowrap="nowrap" valign="top"><c:out
							value="${status.index + 1}" />.</td>


					<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap"
						valign="top"><c:out value="${claimItem.itemId.itemName}" />
					</td>
					<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap"
						valign="top"><fmt:formatNumber>
							<c:out value="${claimItem.claimItemAmount}" />
						</fmt:formatNumber></td>
					<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap"
						valign="top" style="text-align: right;"><fmt:formatNumber>
							<c:out value="${claimItem.claimItemValue}" />
						</fmt:formatNumber></td>
					<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap"
						valign="top" style="text-align: right;"><fmt:formatNumber>
							<c:out value="${claimItem.claimItemApprovedValue}" />
						</fmt:formatNumber></td>
					<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap"
						valign="top" style="text-align: right;"><fmt:formatNumber>
							<c:out value="${claimItem.excessValue}" />
						</fmt:formatNumber></td>
					<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap"
						valign="top" style="text-align: right;"><fmt:formatNumber>
							<c:out value="${claimItem.claimExGratia}" />
						</fmt:formatNumber></td>
					<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap"
						valign="top" style="text-align: right;"><c:out
							value="${claimItem.benefitCheckRemarks}" /></td>

				</tr>

				<tr>
					<td colspan="20" class="listViewHRS1"></td>
				</tr>

			</c:forEach>

		</tbody>
	</table>
</c:if>

<br />
<c:if test="${theUser.userType eq 2}">
	<c:if test="${benefitList ne null}">

		<table class="listView" cellspacing="0" cellpadding="0" width="100%">
			<tbody>
				<tr height="20">
					<td scope="col" align="center" class="listViewThS1"
						style="text-align: center; font-size: 15;" nowrap="nowrap"
						width="100%" colspan=16>&nbsp;<c:out
							value="${myCase.caseCategoryId.caseCategoryName}" />
						&nbsp;&nbsp; <c:if test="${benefitLimit gt -1}">| &nbsp;&nbsp; LIMIT = <fmt:formatNumber>
								<c:out value="${benefitLimit}" />
							</fmt:formatNumber>
						</c:if>
					</td>
				</tr>

				<tr height="20">
					<td width="2%" nowrap="nowrap" class="listViewThS1" scope="col">
						<img src="images/blank.gif" alt="asd" height="1" width="1">No.</td>
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%"
						style="text-align: center;">Code &nbsp;</td>
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%"
						style="text-align: center;">Item Category &nbsp;</td>
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%"
						style="text-align: center;">Method &nbsp;</td>
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%"
						style="text-align: center;">Shared Benefit &nbsp;</td>
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%"
						style="text-align: center;">Cashless &nbsp;</td>
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%"
						style="text-align: center;">Reimburse &nbsp;</td>
					<c:if test="${theUser.userType eq 2}">
						<td scope="col" class="listViewThS1" width="5%"
							style="text-align: center;">Max Occur. &nbsp;</td>

						<td scope="col" class="listViewThS1" width="3%"
							style="text-align: center;">Max Occur. per Case&nbsp;</td>

						<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%"
							style="text-align: center;">Usage &nbsp;</td>
						<td scope="col" class="listViewThS1" width="3%"
							style="text-align: center;">Deductible &nbsp;</td>
						<td scope="col" class="listViewThS1" width="3%"
							style="text-align: center;">Share (%) &nbsp;</td>
						<td scope="col" class="listViewThS1" width="5%"
							style="text-align: center;">Share (Amt) &nbsp;</td>
						<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%"
							style="text-align: center;">EDC &nbsp;</td>
						<td scope="col" class="listViewThS1" nowrap="nowrap" width="3%"
							style="text-align: center;">Divert &nbsp;</td>
					</c:if>
				</tr>
				<c:forEach items="${benefitList}" var="benefit" varStatus="status">
					<tr height="20">
						<td class="oddListRowS1" align="center" bgcolor="#e7f0fe"
							nowrap="nowrap" valign="top"><c:out
								value="${status.index + 1}" />.</td>



						<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap"
							valign="top"><c:out
								value="${benefit.itemCategoryId.itemCategoryCode}" /> <c:if
								test="${benefit.itemCategoryId.itemCategoryAlternateCode ne null}">/</c:if>
							<c:out
								value="${benefit.itemCategoryId.itemCategoryAlternateCode}" />
						</td>
						<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap"
							valign="top"><a
							href="claimitem?memberId=<c:out value="${memberId}" />&navigation=usagetrack&memberBenefitId=<c:out value="${benefit.memberBenefitId}" />&itemCategoryId=<c:out value="${benefit.itemCategoryId.itemCategoryId}" />&prev=listmember"
							class="linkDetail"><c:out
									value="${benefit.itemCategoryId.itemCategoryName}" />
						</a></td>
						<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap"
							valign="top"><c:if
								test="${benefit.benefitCalculationMethod.benefitUsageTypeId eq 1}">
				OCCURANCE
			</c:if> <c:if
								test="${benefit.benefitCalculationMethod.benefitUsageTypeId eq 2}">
				PER DISABILITY
			</c:if> <c:if
								test="${benefit.benefitCalculationMethod.benefitUsageTypeId eq 3}">
				ANNUAL LIMIT
			</c:if> <c:if
								test="${benefit.benefitCalculationMethod.benefitUsageTypeId eq 4}">
				MAX OCCUR PER CASE
			</c:if> <c:if
								test="${benefit.benefitCalculationMethod.benefitUsageTypeId eq 5}">
				MAX DAILY
			</c:if></td>
						<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap"
							valign="top"><c:out
								value="${benefit.sharedBenefitId.itemCategoryId.itemCategoryName}" />

						</td>
						<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap"
							valign="top" style="text-align: right;"><c:choose>
								<c:when test="${benefit.sharedBenefitId eq null}">
									<c:choose>
										<c:when test="${benefit.benefitLimit eq -1}">
							AS CHARGE
							</c:when>
										<c:otherwise>
											<fmt:formatNumber>
												<c:out value="${benefit.benefitLimit}" />
											</fmt:formatNumber>
										</c:otherwise>
									</c:choose>
								</c:when>
								<c:otherwise>
						SHARED
					</c:otherwise>
							</c:choose></td>
						<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap"
							valign="top" style="text-align: right;"><c:choose>
								<c:when test="${benefit.sharedBenefitId eq null}">
									<c:choose>
										<c:when test="${benefit.reimbursementBenefitLimit eq -1}">
							AS CHARGE
							</c:when>
										<c:otherwise>
											<fmt:formatNumber>
												<c:out value="${benefit.reimbursementBenefitLimit}" />
											</fmt:formatNumber>
										</c:otherwise>
									</c:choose>
								</c:when>
								<c:otherwise>
						SHARED
					</c:otherwise>
							</c:choose></td>
						<c:if test="${theUser.userType eq 2}">
							<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap"
								valign="top" align="right" style="text-align: right;"><fmt:formatNumber>
									<c:out value="${benefit.maxBenefitOccurance}" />
								</fmt:formatNumber></td>
							<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap"
								valign="top" align="right" style="text-align: right;"><fmt:formatNumber>
									<c:out value="${benefit.maxOccurancePerCase}" />
								</fmt:formatNumber></td>
							<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap"
								valign="top" align="right" style="text-align: right;"><fmt:formatNumber>
									<c:out value="${benefit.benefitUsage}" />
								</fmt:formatNumber></td>
							<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap"
								valign="top" align="right" style="text-align: right;"><fmt:formatNumber>
									<c:out value="${benefit.deductibleLimit}" />
								</fmt:formatNumber></td>
							<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap"
								valign="top" style="text-align: right;"><c:choose>
									<c:when test="${benefit.costSharingPercentage < 100}">
										<fmt:formatNumber>
											<c:out value="${benefit.costSharingPercentage}" />
										</fmt:formatNumber> %
					</c:when>
									<c:otherwise>
										<center>-</center>
									</c:otherwise>

								</c:choose></td>
							<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap"
								valign="top" style="text-align: right;"><fmt:formatNumber>
									<c:out value="${benefit.costSharingAmount}" />
								</fmt:formatNumber></td>
							<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap"
								valign="top" align="right" style="text-align: right;"><c:if
									test="${benefit.isEDCEnabled}">EDC
					<c:if test="${benefit.benefitLimitPublishedOnEdc eq 1}">(Y)</c:if>
									<c:if test="${benefit.benefitLimitPublishedOnEdc eq 0}">(N)</c:if>
					|<c:if test="${benefit.itemCategoryId.itemCategoryEDCCode ne null}">
										<c:out value="${benefit.itemCategoryId.itemCategoryEDCCode}" />
									</c:if>
								</c:if> <c:if test="${not benefit.isEDCEnabled}">SHOW</c:if></td>
							<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap"
								valign="top" align="right" style="text-align: right;"><c:if
									test="${benefit.divertBenefit eq true}">YES</c:if> <c:if
									test="${(benefit.divertBenefit eq false) or (benefit.divertBenefit eq null) }">NO</c:if>
							</td>

						</c:if>
					</tr>

					<tr>
						<td colspan="20" class="listViewHRS1"></td>
					</tr>

				</c:forEach>

			</tbody>
		</table>
	</c:if>
</c:if>
<script language="javascript">
	$(document)
			.ready(
					function() {
						//alert('jquery ready');
						$('li').css('cursor', 'pointer');

						$("#dialog").dialog({
							autoOpen : false,
							height : 350,
							width : 400,
							show : {
								effect : "blind",
								duration : 200
							},
							hide : {
								effect : "blind",
								duration : 200
							},
							position : {
								at : "center center"
							}
						});
						$("#printConfirmLetterDiv").hide();

						$("#clDialog").click(function() {
							$("#langSelect").val("ina");
							$("#dialog").dialog("open");
						});

						$("#confirmLetterMenuBtn").click(function() {
							if ($('#printConfirmLetterDiv').is(":hidden")) {
								$("#printConfirmLetterDiv").show();
							} else {
								$("#printConfirmLetterDiv").hide();
							}
						});

						$("#idemnityLetterMenuBtn").click(function() {
							$("#printConfirmLetterDiv").hide();
						});
						$("#benefitLetterMenuBtn").click(function() {
							$("#printConfirmLetterDiv").hide();
						});
						$("#dialog").dialog({
							beforeClose : function(event, ui) {
								$("#printConfirmLetterDiv").hide();
							}
						});

						$("#langSelect").change(
								function() {
									//alert($(this).val());
									document.form1.languageSelect.value = $(
											this).val();
								});
						$
								.get(
										"firstcall?navigation=jsontotalcaseelog&caseId=<c:out value="${myCase.caseId }"/>",
										function(data) {
											//alert("Data: " + data);
											if (data > 0) {
												blinker();
											}
										});

					});
	function blinker() {
		document.getElementById("errorLog").style.backgroundColor = "red";
		setTimeout(
				"document.getElementById('errorLog').style.backgroundColor=''",
				500);
		setTimeout("blinker()", 1500);
	}

	function hapus() {
		var delConfirm = window
				.confirm("Are You Sure Want To Delete This Entry ?");
		if (delConfirm) {
			document.form1.navigation.value = "delete";
			document.form1.submit();
		}
	}
	<c:if test="${myCase.claimId ne null}">
	function printStruct() {
		window
				.open(
						"claim?navigation=printclaimrki&claimId=<c:out value="${myCase.claimId.claimId}" />&url=claim-form",
						"Search",
						"width=300, height=500, menubar=yes, status=no, toolbar=no, scrollbars=yes, resizable=yes");
	}
	</c:if>

	function printRegisterStruct() {
		window
				.open(
						"member?navigation=printrki&memberId=<c:out value="${myCase.memberId.memberId}" />&caseCategoryId=<c:out value="${myCase.caseCategoryId.caseCategoryId}" />&providerId=<c:out value="${myCase.providerId.providerId}" />&url=claim-form",
						"width=300, height=500, menubar=yes, status=no, toolbar=no, scrollbars=yes, resizable=yes");
	}
	<c:if test="${myCase.caseStatusId.caseStatusId eq 4}">
	function printRejectionLetter() {
		window
				.open(
						"rejectedcase?navigation=print&url=detailcase&caseId=<c:out value="${myCase.caseId}" />",
						"Search",
						"width=1024, height=768, menubar=yes, status=no, toolbar=no, scrollbars=yes, resizable=yes");
		$("#dialog").dialog("close");
	}
	</c:if>
	<c:if test="${myCase.caseStatusId.caseStatusId eq 9}">

	function addConversation() {
		document.form1.navigation.value = "tambah";
		document.form1.action = "caseconversation-form";

		document.form1.method = "GET";

		document.form1.submit();
	}

	function addMonitoring() {
		document.form1.navigation.value = "tambah";
		document.form1.action = "caseevent-form";
		document.form1.method = "GET";
		document.form1.submit();
	}
	function addInvestigation() {
		document.form1.navigation.value = "tambah";
		document.form1.action = "caseinvestigation-form";
		document.form1.method = "GET";
		document.form1.submit();
	}
	function addCostContainment() {
		document.form1.navigation.value = "tambah";
		document.form1.action = "costcontainment-form";
		document.form1.method = "GET";
		document.form1.submit();
	}

	</c:if>
	<c:if test="${myCase.caseStatusId.caseStatusId eq 17}">
	function reverseRefer() {
		var delConfirm = window
				.confirm("Are You Sure Want To Cancel This Case Refer ?");
		if (delConfirm) {
			document.form1.navigation.value = "prereverserefer";
			document.form1.action = "case";
			document.form1.submit();
		}
	}
	</c:if>
	function kembali() {
		document.form1.navigation.value = "back";
		document.form1.submit();
	}
	function popupMember(memberId) {
		window.open("member?navigation=detail&memberId=" + memberId);
	}
	function ubah() {
		document.form1.navigation.value = "update";
		document.form1.action = "case-form";
		document.form1.method = "GET";
		document.form1.submit();
	}
	function approve() {

		var delConfirm = window
				.confirm("Are You Sure Want To Approve This Case ?");
		if (delConfirm) {
			document.form1.navigation.value = "approvecase";
			document.form1.action = "claim-form";
			document.form1.method = "GET";
			document.form1.submit();
			// 			document.form1.navigation.value = "approve";
			// 			document.form1.action = "case";
			// 			document.form1.method = "GET";
			// 			document.form1.submit();	
		}
	}
	function assignCase() {
		var delConfirm = window
				.confirm("Are You Sure Want To Assign This Case ?");
		if (delConfirm) {
			document.form1.navigation.value = "preassign";
			document.form1.action = "case";
			document.form1.method = "GET";
			document.form1.submit();
		}
	}
	function approveExGratia() {

		var delConfirm = window
				.confirm("Are You Sure Want To Approve This Case ?");
		if (delConfirm) {
			document.form1.navigation.value = "preexgratia";
			document.form1.action = "exgratia-form";
			document.form1.method = "GET";
			document.form1.submit();
		}
	}
	function reject() {
		var delConfirm = window
				.confirm("Are You Sure Want To Reject This Case ?");

		if (delConfirm) {
			document.form1.navigation.value = "reject";
			document.form1.action = "rejectedcase-form";
			document.form1.method = "GET";
			document.form1.submit();
		}

	}
	function voidManual() {
		var delConfirm = window
				.confirm("Are You Sure Want To Void This Case ?");

		if (delConfirm) {
			document.form1.navigation.value = "void";
			document.form1.action = "case";
			document.form1.method = "GET";
			document.form1.submit();
		}

	}
	function pending() {
		var delConfirm = window
				.confirm("Are You Sure Want To Pending This Case ?");

		if (delConfirm) {
			document.form1.navigation.value = "prepending";
			document.form1.action = "case";
			document.form1.method = "GET";
			document.form1.submit();
		}

	}
	function terminate() {
		var delConfirm = window
				.confirm("Are You Sure Want To Terminate This Case ?");

		if (delConfirm) {
			document.form1.navigation.value = "terminate";
			document.form1.action = "case";
			document.form1.method = "GET";
			document.form1.submit();
		}
	}
	function extendCase() {
		var delConfirm = window
				.confirm("Are You Sure Want To Extend This Case ?");

		if (delConfirm) {
			document.form1.navigation.value = "extend";
			document.form1.action = "caseinvestigation-form";
			document.form1.method = "GET";
			document.form1.submit();
		}

	}
	function paymentManual() {
		var delConfirm = window
				.confirm("Are You Sure Want To Payment Manual This Case ?");

		if (delConfirm) {
			document.form1.navigation.value = "claimrki";
			document.form1.action = "claim";
			document.form1.method = "GET";
			document.form1.submit();
		}

	}

	function printAuthorizationLetter() {
		var langOption = $("#langSelect").val();
		window
				.open(
						"case?navigation=printAuthorizationLetter&url=detailcase&caseId=<c:out value="${myCase.caseId}" />&langselect="
								+ langOption,
						"Search",
						"width=1024, height=768, menubar=yes, status=no, toolbar=no, scrollbars=yes, resizable=yes");
		$("#dialog").dialog("close");
	}
	function printInvestigationLetter() {
		var langOption = $("#langSelect").val();
		window
				.open(
						"case?navigation=printInvestigationLetter&url=detailcase&caseId=<c:out value="${myCase.caseId}" />&langselect="
								+ langOption,
						"Search",
						"width=1024, height=768, menubar=yes, status=no, toolbar=no, scrollbars=yes, resizable=yes");
		$("#dialog").dialog("close");
	}
	//Add 20150422 by FVO, for print Medical Form (Form Medis awal)
	function printMedicalForm() {
		var langOption = $("#langSelect").val();
		window
				.open(
						"case?navigation=printMedicalForm&url=detailcase&caseId=<c:out value="${myCase.caseId}" />&langselect="
								+ langOption,
						"Search",
						"width=1024, height=768, menubar=yes, status=no, toolbar=no, scrollbars=yes, resizable=yes");
		$("#dialog").dialog("close");
	}

	//Edit 01042015, function untuk Print Surat sesuai dengan tipe Maternity, Kemotherapy, Haemodialisa, dan Pembedahan(Surgery), Default
	function printConfirmLetter(param) {
		//alert('aaa'+param);		
		//alert($("#langSelect").val());
		//var sel = document.getElementsByName('langSelect');
		var langOption = $("#langSelect").val();
		if (param == 'default') {
			window
					.open(
							"case?navigation=printConfirmLetter&url=detailcase&caseId=<c:out value="${myCase.caseId}" />&langselect="
									+ langOption,
							"Search",
							"width=1024, height=768, menubar=yes, status=no, toolbar=no, scrollbars=yes, resizable=yes");
		} else if (param == 'maternity') {
			window
					.open(
							"case?navigation=printConfirmLetterWithType&url=detailcase&caseId=<c:out value="${myCase.caseId}" />&lettertype=Maternity&langselect="
									+ langOption,
							"Search",
							"width=1024, height=768, menubar=yes, status=no, toolbar=no, scrollbars=yes, resizable=yes");
		} else if (param == 'kemotherapy') {
			window
					.open(
							"case?navigation=printConfirmLetterWithType&url=detailcase&caseId=<c:out value="${myCase.caseId}" />&lettertype=Kemotherapy&langselect="
									+ langOption,
							"Search",
							"width=1024, height=768, menubar=yes, status=no, toolbar=no, scrollbars=yes, resizable=yes");
		} else if (param == 'haemodialisa') {
			window
					.open(
							"case?navigation=printConfirmLetterWithType&url=detailcase&caseId=<c:out value="${myCase.caseId}" />&lettertype=Haemodialisa&langselect="
									+ langOption,
							"Search",
							"width=1024, height=768, menubar=yes, status=no, toolbar=no, scrollbars=yes, resizable=yes");
		} else {
			window
					.open(
							"case?navigation=printConfirmLetterWithType&url=detailcase&caseId=<c:out value="${myCase.caseId}" />&lettertype=Surgery&langselect="
									+ langOption,
							"Search",
							"width=1024, height=768, menubar=yes, status=no, toolbar=no, scrollbars=yes, resizable=yes");
		}

		$("#dialog").dialog("close");

	}

	//Add 06042015, for print Idemnity Letter
	function printIdemnityLetter() {
		var langOption = $("#langSelect").val();
		window
				.open(
						"case?navigation=printIdemnityLetter&url=detailcase&caseId=<c:out value="${myCase.caseId}" />&langselect="
								+ langOption,
						"Search",
						"width=1024, height=768, menubar=yes, status=no, toolbar=no, scrollbars=yes, resizable=yes");
		$("#dialog").dialog("close");
	}

	//Add 07042015, for print Benefit Calculation Letter Patient when Checkout
	function printBenefitLetterCalculation(param) {
		var langOption = $("#langSelect").val();
		if (param == 'finalized') {
			window
					.open(
							"case?navigation=printBenefitLetterFinalized&url=detailcase&caseId=<c:out value="${myCase.caseId}" />&type=finalized&langselect="
									+ langOption,
							"Search",
							"width=1024, height=768, menubar=yes, status=no, toolbar=no, scrollbars=yes, resizable=yes");
		} else {
			window
					.open(
							"case?navigation=printBenefitLetterFinalized&url=detailcase&caseId=<c:out value="${myCase.caseId}" />&type=notfinalized&langselect="
									+ langOption,
							"Search",
							"width=1024, height=768, menubar=yes, status=no, toolbar=no, scrollbars=yes, resizable=yes");
		}
		$("#dialog").dialog("close");
	}

	function printGuaranteeLetter() {
		var langOption = $("#langSelect").val();
		window
				.open(
						"case?navigation=printGL&url=detailcase&caseId=<c:out value="${myCase.caseId}" />&langselect="
								+ langOption,
						"Search",
						"width=1024, height=768, menubar=yes, status=no, toolbar=no, scrollbars=yes, resizable=yes");
		$("#dialog").dialog("close");
	}
	//Add 20150619, for print Rejection Letter
	function printRejectedLetter() {
		var langOption = $("#langSelect").val();
		window
				.open(
						"case?navigation=printRejectedLetter&url=detailcase&caseId=<c:out value="${myCase.caseId}" />&langselect="
								+ langOption,
						"Search",
						"width=1024, height=768, menubar=yes, status=no, toolbar=no, scrollbars=yes, resizable=yes");
		$("#dialog").dialog("close");
	}
	//Add 20150814 by FVO, for print Returning Letter
	function printReturningLetter() {
		var langOption = $("#langSelect").val();
		window
				.open(
						"case?navigation=printReturningLetter&url=detailcase&caseId=<c:out value="${myCase.caseId}" />&langselect="
								+ langOption,
						"Search",
						"width=1024, height=768, menubar=yes, status=no, toolbar=no, scrollbars=yes, resizable=yes");
		$("#dialog").dialog("close");
	}
	<c:if test="${myCase.caseStatusId.caseStatusId eq 5 or myCase.caseStatusId.caseStatusId eq 15}">
	function printClosingLetter() {
		var langOption = $("#langSelect").val();
		window
				.open(
						"case?navigation=printClosing&url=detailcase&caseId=<c:out value="${myCase.caseId}" />&langselect="
								+ langOption,
						"Search",
						"width=1024, height=768, menubar=yes, status=no, toolbar=no, scrollbars=yes, resizable=yes");
	}

	function downloadConversation() {
		var delConfirm = window
				.confirm("Are You Sure Want To Download The Conversation Report ?");

		if (delConfirm) {
			document.form1.navigation.value = "download";
			document.form1.action = "caseconversation";
			document.form1.method = "GET";
			document.form1.submit();
		}

	}
	function downloadMonitoring() {
		var delConfirm = window
				.confirm("Are You Sure Want To Download The Report ?");

		if (delConfirm) {
			document.form1.navigation.value = "downloadmonitor";
			document.form1.action = "caseevent";
			document.form1.method = "GET";
			document.form1.submit();
		}

	}
	function calculateCase() {
		var delConfirm = window
				.confirm("Are You Sure Want To Calculate and Finalize Case ?");

		if (delConfirm) {
			document.form1.navigation.value = "calculate";
			document.form1.action = "claim-form";
			document.form1.method = "GET";
			document.form1.caseId.value = <c:out value="${myCase.caseId}" />;
			document.form1.submit();
		}
	}
	function printCostReport() {
		window
				.open(
						"case?navigation=printcost&url=detailExcessCharge&caseId=<c:out value="${myCase.caseId}" />",
						"Search",
						"width=1024, height=768, menubar=yes, status=no, toolbar=no, scrollbars=yes, resizable=yes");
	}
	function printCostContaintment() {
		window
				.open(
						"costcontainment?navigation=printcase&url=detailExcessCharge&caseId=<c:out value="${myCase.caseId}" />",
						"Search",
						"width=1024, height=768, menubar=yes, status=no, toolbar=no, scrollbars=yes, resizable=yes");

	}
	function referCase() {
		var delConfirm = window
				.confirm("Are You Sure Want To Refer The Case ?");

		if (delConfirm) {
			document.form1.navigation.value = "refercase";
			document.form1.action = "refer-form";
			document.form1.method = "GET";
			document.form1.submit();
		}

	}

	</c:if>
	<c:if test="${myCase.caseStatusId.caseStatusId eq 15 or myCase.caseStatusId.caseStatusId eq 5}">
	function cancelDischarge() {
		var delConfirm = window
				.confirm("Are You Sure Want To Cancel Discharge The Case ?");

		if (delConfirm) {
			document.form1.navigation.value = "canceldischarge";
			document.form1.action = "case";
			document.form1.method = "GET";
			document.form1.submit();
		}

	}
	</c:if>
	function printRKI() {
		window
				.open(
						"member?navigation=printrki&memberId=<c:out value="${memberId}" />&caseCategoryId=<c:out value="${caseCategoryId}" />&providerId=<c:out value="${providerId}" />&url=claim-form",
						"Search",
						"width=1024, height=768, menubar=yes, status=no, toolbar=no, scrollbars=yes, resizable=yes");
	}
	function referCaseManaged() {
		var delConfirm = window
				.confirm("Are You Sure Want To Close The Case ?");

		if (delConfirm) {
			document.form1.navigation.value = "refercase";
			document.form1.action = "refer-form";
			document.form1.method = "GET";
			document.form1.submit();
		}

	}
	function printStructRefer() {
		window
				.open(
						"case?navigation=printrefer&caseId=<c:out value="${myCase.caseId}" />&url=claim-form",
						"Search",
						"width=300, height=500, menubar=yes, status=no, toolbar=no, scrollbars=yes, resizable=yes");
	}

	function printErrorLog() {
		window
				.open(
						"firstcall?navigation=searchcaseerrorlog&caseId=<c:out value="${myCase.caseId}" />",
						"Search",
						"width=1024, height=768, menubar=yes, status=no, toolbar=no, scrollbars=yes, resizable=yes");
	}

	function adderrorlog() {
		window.location.href = "firstcall-form?navigation=caseelog&caseId=<c:out value="${myCase.caseId}" />&memberId=<c:out value="${myCase.memberId.memberId}" />&providerId=<c:out value="${myCase.providerId.providerId}" />";
		// 		document.form1.navigation.value = "list";
		// 		document.form1.action = "firstcall-form";
		// 		document.form1.method = "GET";
		// 		document.form1.submit();
	}
	
	function printPayment(){
		window.open ("claim?navigation=printclaimrki&claimId=<c:out value="${myCase.claimId.claimId}" />&url=claim-form","Search","width=1024, height=768, menubar=yes, status=no, toolbar=no, scrollbars=yes, resizable=yes");
	}
</script>
