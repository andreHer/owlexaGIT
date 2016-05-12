<%@page import="com.ametis.cms.datamodel.CaseItem"%>
<%@page import="com.ametis.cms.datamodel.Case"%>
<%@page import="com.ametis.cms.datamodel.MemberBenefit"%>
<%@page import="com.ametis.cms.service.MemberBenefitService"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page contentType="text/html" import="java.util.*" %>

<HTML>
	<HEAD>
		<!-- Owlexa Confirmation Letter -->
		<TITLE><c:out value="${myCase.memberId.currentCardNumber}" />-<c:out value="${myCase.caseNumber}" />-BC</TITLE>
		<% //Owlexa Letter Header Template %>
		<%@ include file="../owlexaLetterHeader.jsp" %>
		<DIV align="center">
			<TABLE id=AutoNumber25 style="BORDER-COLLAPSE: collapse" cellSpacing=0 cellPadding=0 width=776 bgColor=#ffffff border=0>
				<!--DWLayoutTable-->
				<TBODY>
					<tr align="center" bgcolor="#00B0F0" style="background-color:#00B0F0;" height="30px">
						<td class="letterTitle2" colspan="3" valign="middle" style="background-color:#00B0F0;">
							<c:out value="${param.type eq 'finalized'? 'FINAL CALCULATION FORM' : 'INTERIM GUARANTEE LETTER' }"/>
						</td>
					</tr>
					<tr>
						<td>
							<table style="width: 100%">
								<tr>
									<td width="70%"></td>
									<td class="BOwlexa2">
										Jakarta, <fmt:formatDate type="date" value="<%= new java.util.Date() %>" pattern="dd, MMMM, yyyy"/><br>
										<br>Dear,
										<br>Hospital :<c:out value="${myCase.providerId.providerName}" />
										<br>UP. <input type="text" class="inputuserstyle2" value="[.............................]" size="30"><br>&nbsp;
									</td>
								</tr>
							</table>
						</td>
					</tr>
				<tr>
					<td>
						<table style="border:  1px solid #00B0F0;border-collapse: collapse; width: 100%">
							<tr>
								<td colspan="4" class="HeaderName4" bgcolor="00B0F0"  align="center" width="50%">
									PATIENT DATA
								</td>
							</tr>
							<tr>
								<td width="200px" class="BOwlexa2" style="padding-left: 5px;border: 1px solid #00B0F0;">
									Patient Name
								</td>
								<td class="BOwlexa2" width="200px" style="padding-left: 5px;border: 1px solid #00B0F0;">
									<c:out value="${myCase.memberId.firstName }"/>
								</td>
								<td class="BOwlexa2" style="padding-left: 5px;border: 1px solid #00B0F0;">
									Admission Date
								</td>
								<td class="BOwlexa2" width="150px" style="padding-left: 5px;border: 1px solid #00B0F0;">
									<fmt:formatDate value="${myCase.caseStartTime }" pattern="dd-MMM-yyyy"/>
								</td>
							<tr>
								<td class="BOwlexa2" style="padding-left: 5px;border: 1px solid #00B0F0;">
									Card Number
								</td>
								<td class="BOwlexa2" style="padding-left: 5px;border: 1px solid #00B0F0;">
									<c:out value="${myCase.memberId.customerNumber}" />
								</td>
								<c:choose>
									<c:when test="${param.type eq 'finalized' }">
										<td class="BOwlexa2" style="padding-left: 5px;border: 1px solid #00B0F0;">
											Discharge Date
										</td>
										<td class="BOwlexa2" style="padding-left: 5px;border: 1px solid #00B0F0;">
											<fmt:formatDate value="${myCase.caseEndTime}" pattern="dd-MMM-yyyy"/>
										</td>
									</c:when>
									<c:otherwise>
										<td class="BOwlexa" bgcolor="#cccccc">
										</td>
										<td class="BOwlexa" bgcolor="#cccccc">
										</td>
									</c:otherwise>
								</c:choose>
							</tr>
							<tr>
								<td class="BOwlexa2" style="padding-left: 5px;border: 1px solid #00B0F0;">
									Sex
								</td>
								<td class="BOwlexa2" style="padding-left: 5px;border: 1px solid #00B0F0;">
									<c:out value="${myCase.memberId.gender}" />
								</td>
								<c:choose>
									<c:when test="${param.type eq 'finalized' }">
										<td class="BOwlexa2" style="padding-left: 5px;border: 1px solid #00B0F0;">
											Days
										</td>
										<td class="BOwlexa2" style="padding-left: 5px;border: 1px solid #00B0F0;">
											<input type="text" class="inputuserstyle2" value="${myCase.longOfStay } Hari" size="20">
										</td>
									</c:when>
									<c:otherwise>
										<td class="BOwlexa" bgcolor="#cccccc">
										</td>
										<td class="BOwlexa" bgcolor="#cccccc">
										</td>
									</c:otherwise>
								</c:choose>
							</tr>
							<tr>
								<td class="BOwlexa2" style="padding-left: 5px;border: 1px solid #00B0F0;">
									Date of Birth
								</td>
								<td class="BOwlexa2" style="padding-left: 5px;border: 1px solid #00B0F0;">
									<fmt:formatDate value="${myCase.memberId.birthday}" pattern="dd-MMM-yyyy"/>
								</td>
								<td class="BOwlexa2" style="padding-left: 5px;border: 1px solid #00B0F0;">
									Room Occupied
								</td>
								<td class="BOwlexa2" style="padding-left: 5px;border: 1px solid #00B0F0;">
									<input type="text" class="inputuserstyle2" value="${myCase.roomAndBoardUsage}" size="20">
								</td>
							</tr>
							<tr>
								<td class="BOwlexa2" style="padding-left: 5px;border: 1px solid #00B0F0;">
									Insurance/Corporate
								</td>
								<td class="BOwlexa2" style="padding-left: 5px;border: 1px solid #00B0F0;" width="200px">
									<c:out value="${myCase.memberId.clientId.clientName}" /> 
									<c:if test="${not empty myCase.memberId.memberGroupId}">
										/ <c:out value="${myCase.memberId.memberGroupId.groupName}" />
									</c:if>
								</td>
								<td class="BOwlexa2" style="padding-left: 5px;border: 1px solid #00B0F0;">
									Diagnose
								</td>
								<td class="BOwlexa2" style="padding-left: 5px;border: 1px solid #00B0F0;" width="150px">
									<c:out value="${myCase.diagnosis1Id.description}" />
								</td>
							</tr>
						</table>
					</td>
				</tr>
			<tr>
				<td>
					<br>
					<c:if test="${param.type eq 'notfinalized' }">
					<table style="border:  1px solid #00B0F0;border-collapse: collapse; width: 100%">
						<tr>
							<td class="HeaderName4" bgcolor="#00B0F0"  align="center" style="border-right: 1px solid #FFFFFF">
								BENEFIT
							</td>
							<td class="HeaderName4" bgcolor="#00B0F0"  align="center" width="15%" style="border-right: 1px solid #FFFFFF">
								LIMIT
							</td>
							<td class="HeaderName4" bgcolor="#00B0F0"  align="center" width="16%" style="border-right: 1px solid #FFFFFF">
								HOSPITAL EXPENSES
							</td>
							<td class="HeaderName4" bgcolor="#00B0F0"  align="center" width="16%" style="border-right: 1px solid #FFFFFF">
								APPROVAL
							</td>
							<td class="HeaderName4" bgcolor="#00B0F0"  align="center" width="16%" style="border-right: 1px solid #FFFFFF">
								EXCESS CLAIM
							</td>
						</tr>
						<c:forEach items="${claimItemList}" var="claimItem" varStatus="status" >
							<tr>
								<td class="BOwlexa2" style="border: 1px solid #00B0F0">
									&nbsp;<c:out value="${claimItem.itemId.itemName }"></c:out>
								</td>
								<td class="BOwlexa3" style="border: 1px solid #00B0F0">
									&nbsp;
									<c:if test="${not empty claimItem.memberBenefitId }">
									<c:choose>
										<c:when test="${claimItem.claimId.claimTypeId.claimTypeId eq 1 }">
											<c:choose>
												<c:when test="${claimItem.memberBenefitId.reimbursementAsCharge}">
												AS CHARGE
												</c:when>
												<c:otherwise >
													<fmt:formatNumber><c:out value="${claimItem.memberBenefitId.reimbursementBenefitLimit}" /></fmt:formatNumber>
												</c:otherwise>
											</c:choose>
										</c:when>
										<c:otherwise>
											<c:choose>
												<c:when test="${claimItem.memberBenefitId.isAsCharge}">
													AS CHARGE
												</c:when>
												<c:otherwise >
													<fmt:formatNumber><c:out value="${claimItem.memberBenefitId.benefitLimit}" /></fmt:formatNumber>
												</c:otherwise>
											</c:choose>
										</c:otherwise>
									</c:choose>
									</c:if>
								</td>
								<td class="BOwlexa2" align="right" style="border: 1px solid #00B0F0">
									<fmt:formatNumber><c:out value="${claimItem.claimItemValue}" /></fmt:formatNumber>&nbsp;
								</td>
								<td class="BOwlexa2" align="right" style="border: 1px solid #00B0F0">
									<c:choose>
										<c:when test="${not empty claimItem.claimItemCoveredValue }">
											<fmt:formatNumber><c:out value="${claimItem.claimItemCoveredValue}" /></fmt:formatNumber>&nbsp;
										</c:when>
										<c:otherwise>
											<fmt:formatNumber>0</fmt:formatNumber>&nbsp;
										</c:otherwise>
									</c:choose>
								</td>
								<td class="BOwlexa2" align="right" style="border: 1px solid #00B0F0">
									<fmt:formatNumber><c:out value="${claimItem.excessValue}" /></fmt:formatNumber>&nbsp;
								</td>
							</tr>	
						</c:forEach>
						<tr>
							<td class="BOwlexa5" style="border: 1px solid #00B0F0">
								&nbsp;TOTAL
							</td>
							<td class="BOwlexa5" align="center" style="border: 1px solid #00B0F0">
							</td>
							<td class="BOwlexa5" align="right" style="border: 1px solid #00B0F0">
								<fmt:formatNumber ><c:out value="${totalCharge}" /></fmt:formatNumber>&nbsp;
							</td>
							<td class="BOwlexa5" align="right" style="border: 1px solid #00B0F0">
								<fmt:formatNumber > <c:out value="${totalApproved}" /></fmt:formatNumber>&nbsp;
							</td>
							<td class="BOwlexa5" align="right" style="border: 1px solid #00B0F0">
								<fmt:formatNumber ><c:out value="${totalExcess}" /></fmt:formatNumber>&nbsp;
							</td>
						</tr>
					</table>
					</c:if>
					<c:if test="${param.type eq 'finalized' }">
						<table style="border:  1px solid #00B0F0;border-collapse: collapse; width: 100%">
							<tr>
								<td class="HeaderName4" bgcolor="#00B0F0"  align="center" style="border-right: 1px solid #FFFFFF">
									BENEFIT
								</td>
								<td class="HeaderName4" bgcolor="#00B0F0"  align="center" width="15%" style="border-right: 1px solid #FFFFFF">
									LIMIT
								</td>
								<td class="HeaderName4" bgcolor="#00B0F0"  align="center" width="16%" style="border-right: 1px solid #FFFFFF">
									PROPOSED FEE
								</td>
								<td class="HeaderName4" bgcolor="#00B0F0"  align="center" width="16%" style="border-right: 1px solid #FFFFFF">
									APPROVE
								</td>
								<td class="HeaderName4" bgcolor="#00B0F0"  align="center" width="16%" style="border-right: 1px solid #FFFFFF">
									EXCESS CLAIM
								</td>
							</tr>
							<c:forEach items="${claimItemList}" var="claimItemfin" varStatus="status" >
								<tr>
										<td class="BOwlexa2" style="border: 1px solid #00B0F0">
											&nbsp;<c:out value="${claimItemfin.itemId.itemName }"></c:out>
										</td>
										<td class="BOwlexa3" style="border: 1px solid #00B0F0">
											&nbsp;
											<c:if test="${not empty claimItemfin.memberBenefitId }">
											<c:choose>
												<c:when test="${claimItemfin.claimId.claimTypeId.claimTypeId eq 1 }">
													<c:choose>
														<c:when test="${claimItemfin.memberBenefitId.reimbursementAsCharge}">
														AS CHARGE
														</c:when>
														<c:otherwise >
															<fmt:formatNumber><c:out value="${claimItemfin.memberBenefitId.reimbursementBenefitLimit}" /></fmt:formatNumber>
														</c:otherwise>
													</c:choose>
												</c:when>
												<c:otherwise>
													<c:choose>
														<c:when test="${claimItemfin.memberBenefitId.isAsCharge}">
															AS CHARGE
														</c:when>
														<c:otherwise >
															<fmt:formatNumber><c:out value="${claimItemfin.memberBenefitId.benefitLimit}" /></fmt:formatNumber>
														</c:otherwise>
													</c:choose>
												</c:otherwise>
											</c:choose>
											</c:if>
										</td>
										<td class="BOwlexa2" align="right" style="border: 1px solid #00B0F0">
											<fmt:formatNumber><c:out value="${claimItemfin.claimItemValue}" /></fmt:formatNumber>&nbsp;
										</td>
										<td class="BOwlexa2" align="right" style="border: 1px solid #00B0F0">
											<fmt:formatNumber><c:out value="${claimItemfin.claimItemApprovedValue}" /></fmt:formatNumber>&nbsp;
										</td>
										<td class="BOwlexa2" align="right" style="border: 1px solid #00B0F0">
											<fmt:formatNumber><c:out value="${claimItemfin.excessValue}" /></fmt:formatNumber>&nbsp;
										</td>
								</tr>	
							</c:forEach>
							<tr>
								<td class="BOwlexa5" style="border: 1px solid #00B0F0">
									&nbsp;TOTAL
								</td>
								<td class="BOwlexa5" align="right" style="border: 1px solid #00B0F0">
								</td>
								<td class="BOwlexa5" align="right" style="border: 1px solid #00B0F0">
									<fmt:formatNumber ><c:out value="${checkClaim.claimChargeValue}" /></fmt:formatNumber>&nbsp;
								</td>
								<td class="BOwlexa5" align="right" style="border: 1px solid #00B0F0">
									<fmt:formatNumber > <c:out value="${checkClaim.claimApprovedValue}" /></fmt:formatNumber>&nbsp;
								</td>
								<td class="BOwlexa5" align="right" style="border: 1px solid #00B0F0">
									<fmt:formatNumber ><c:out value="${checkClaim.claimExcessValue}" /></fmt:formatNumber>&nbsp;
								</td>
							</tr>
						</table>
					</c:if>
				</td>
			</tr>
			<tr>
				<td>
					<br>
					<table style="border:  1px solid #00B0F0;border-collapse: collapse; width: 100%">
						<tr>
							<td class="BOwlexa2">
								<c:if test="${param.type eq 'finalized' }">
									<div contenteditable="true">
										Explanation : <br>
										&emsp;&emsp;1. &emsp; Excess claim total Rp. <fmt:formatNumber ><c:out value="${param.type eq 'finalized'? checkClaim.claimExcessValue : totalExcess}" /></fmt:formatNumber>, pledge in advance by the insurance.<br>
										&emsp;&emsp;2. &emsp; Non medical and food supplement pledged in advance <br><br>
									</div>
								</c:if>
								<c:if test="${param.type eq 'notfinalized' }">
									<div contenteditable="true">
										Explanation : <br>
										&emsp;&emsp;1. &emsp; This form is not FINAL GUARANTEE LETTER. This form is made to show the current excess at Hospital.<br>
										&emsp;&emsp;2. &emsp; Please inform excess to patient. <br>
										&emsp;&emsp;3. &emsp; This estimation is temporary. May change anytime. <br>
									</div>
								</c:if>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<br>
					<table style="BORDER-COLLAPSE: collapse" cellSpacing=0 cellPadding=0 width=776 bgColor=#ffffff border=0>
					<tr>
						<td class="BOwlexa2">
							Jakarta, <fmt:formatDate type="date" value="<%= new java.util.Date() %>" pattern="dd MMMM yyyy"/>
						</td>
					</tr>
					<tr align="center">
						<td class="BOwlexa2" width="30%">
						</td>
						<td width="30%"></td>
						<td class="BOwlexa2" align="center">Aware,</td>
					</tr>
					<tr>
						<td class="BOwlexa2">			  
							Officer Name,
						</td>
							<td class="BOwlexa2" align="center">Family,</td>
							<td class="BOwlexa2" align="center">			  
							Hospital
						</td>
					</tr>
					<tr>
						<td class="BOwlexa2" colspan="1">
							<br>
							<br>
							<br>
							<br>&nbsp;
						</td>
						<td class="BOwlexa2" colspan="1">
							&nbsp;
						</td>
						<td class="BOwlexa2" colspan="1">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td class="BOwlexa2">
							(<c:out value="${theUser.firstName }"></c:out>)
						</td>
						<td align="center">
							<input type="text" class="inputuserstyle2" value="(Clear Name)" size="40" style="text-align: center;">
						</td>
						<td class="BOwlexa2" align="center">
							<input type="text" class="inputuserstyle2" value="(Name and Stamp Clearly)" size="40" style="text-align: center;">
						</td>
					</tr>
				</table>
				</td>
			</tr>
				</TBODY>
			</TABLE>
		</DIV>
		<div align="center">
		<br><br>
		<% //Owlexa Letter Footer Template %>
		<%@ include file="../owlexaLetterFooterEnV2.jsp" %>
		<br/>
		<input type="button" name="button" value="P   R   I   N   T"  size=10 onClick="window.print()" style="background:#EEF2FC;border:1px #6f6f6f solid;color:#6f6f6f;font:10px Arial;font-weight:bold;">
		<!-- 
		<input type="button" name="button2" value="S A V E   A   C O P Y"  size=10 onClick="document.execCommand('SaveAs',null,'<?php echo $sFileName;?>')" style="background:#EEF2FC;border:1px #6f6f6f solid;color:#6f6f6f;font:10px Arial;font-weight:bold;">
		-->
		<br>
		<div class="Details"><c:out value="${myCase.memberId.currentCardNumber}" />-<c:out value="${myCase.caseNumber}" />-BC</div>
	</div>
</HTML>
	</BODY>