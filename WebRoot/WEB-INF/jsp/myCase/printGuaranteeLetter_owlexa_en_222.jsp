<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page contentType="text/html" import="java.util.*" %>

<HTML>
	<HEAD>
		<!-- Owlexa Guarantee Letter -->
		<TITLE><c:out value="${myCase.memberId.currentCardNumber}" />-<c:out value="${myCase.caseNumber}" />-GL</TITLE>
		
		<% //Owlexa Letter Header Template %>
		<%@ include file="../owlexaLetterHeader.jsp" %>
		
		<DIV align=center>
			<center><br>
			<TABLE id=AutoNumber25 style="BORDER-COLLAPSE: collapse" height=793 cellSpacing=0 cellPadding=0 width=776 bgColor=#ffffff border=0>
				<!--DWLayoutTable-->
				<TBODY>
					<tr align="center" bgcolor="#00B0F0" style="background-color:#00B0F0;" height="30px">
						<td class="letterTitle2" colspan="3" valign="middle" style="background-color:#00B0F0;">
							GUARANTEE LETTER
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
										<br>Hospital <c:out value="${myCase.providerId.providerName}" />
										<br>UP. <input type="text" class="inputuserstyle2" value="[.............................]" size="30">
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td>
							<table style="border:  1px solid #00B0F0;border-collapse: collapse; width: 100%">
								<tr>
									<td colspan="4" class="HeaderName4" bgcolor="#00B7D5"  align="center" width="50%" style="border-right: 1px solid #FFFFFF">
										PARTICIPANT INFORMATION
									</td>
									<td colspan="2" class="HeaderName4" bgcolor="#00B7D5"  align="center">
										INSURANCE
									</td>
								</tr>
								<tr style="border: 1px solid #00B0F0;">
									<td width="100px" class="BOwlexa2"  style="padding-left: 5px;border: 1px solid #00B0F0;" >
										Patient Name
									</td>
									<td colspan="3" width="150px" class="BOwlexa2"  style="padding-left: 5px;border: 1px solid #00B0F0;">
										<c:out value="${myCase.memberId.firstName}" /> 
									</td>
									<td class="BOwlexa2"  style="padding-left: 5px;border: 1px solid #00B0F0;">
										Payor
									</td>
									<td class="BOwlexa2"  style="padding-left: 5px;">
										<c:out value="${myCase.memberId.clientId.clientName}" />
									</td>
								</tr>
								<tr style="border: 1px solid #00B0F0;">
									<td class="BOwlexa2" style="padding-left: 5px;border: 1px solid #00B0F0;">
										Employee Name
									</td>
									<td colspan="3" class="BOwlexa2" style="padding-left: 5px;border: 1px solid #00B0F0;">
										<c:out value="${myCase.memberId.parentMember.firstName}" /> 
									</td>
									<td class="BOwlexa2"  style="padding-left: 5px;border: 1px solid #00B0F0;">
										Corporate
									</td>
									<td class="BOwlexa2"  style="padding-left: 5px">
										<c:out value="${myCase.memberId.memberGroupId.groupName}" /> 
									</td>
								</tr>
								<tr style="border: 1px solid #00B0F0;">
									<td class="BOwlexa2" style="padding-left: 5px;border: 1px solid #00B0F0;">
										Card Number
									</td>
									<td class="BOwlexa2" style="padding-left: 5px;border: 1px solid #00B0F0;">
										<c:out value="${myCase.memberId.currentCardNumber}" /> 
									</td>
									<td width="50px" class="BOwlexa2" style="padding-left: 5px;border: 1px solid #00B0F0;">
										Birth
									</td>
									<td width="50px" class="BOwlexa2" style="padding-left: 5px;border: 1px solid #00B0F0;">
										<c:out value="${myCase.memberId.birthday}" />
									</td>
									<td class="BOwlexa2"  style="padding-left: 5px;border: 1px solid #00B0F0;">
										Policy Number
									</td>
									<td class="BOwlexa2"  style="padding-left: 5px">
										<c:out value="${myCase.memberId.currentPolicyId.policyNumber}" /> 
									</td>
								</tr>
								<tr style="border: 1px solid #00B0F0;">
									<td class="BOwlexa2" style="padding-left: 5px;border: 1px solid #00B0F0;">
										Member Number
									</td>
									<td class="BOwlexa2" style="padding-left: 5px;border: 1px solid #00B0F0;">
										<c:out value="${myCase.memberId.customerNumber}" />
									</td>
									<td class="BOwlexa2" style="padding-left: 5px;border: 1px solid #00B0F0;">
										Sex
									</td>
									<td class="BOwlexa2" style="padding-left: 5px;border: 1px solid #00B0F0;">
										<c:choose>
											<c:when test="${myCase.memberId.gender eq 'L'}">
												M
											</c:when>
											<c:otherwise>
												W
											</c:otherwise>
										</c:choose>
									</td>
									<td class="BOwlexa2"  style="padding-left: 5px;border: 1px solid #00B0F0;">
										Policy Period
									</td>
									<td class="BOwlexa2"  style="padding-left: 5px">
										<c:out value="${myCase.memberId.currentPolicyId.effectiveDate}" /> - <c:out value="${myCase.memberId.currentPolicyId.expireDate}" />
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr align="center" valign="top">
						<td>
							<br>
							<table style="border:  1px solid #00B0F0;border-collapse: collapse; width: 100%">
								<tr bordercolor="#6F6F6F" bgcolor="#00B7D5">
									<td colspan="6" align="center" class="HeaderName4">
										TREATMENT INFORMATION
									</td>
								</tr>
								<tr>
									<td class="BOwlexa2" style="padding-left: 5px;border: 1px solid #00B0F0;">
										Claim Number 
									</td>
									<td class="BOwlexa2" style="padding-left: 5px;border: 1px solid #00B0F0;">
										<c:out value="${myCase.claimId.claimNumber}" />
									</td>
									<td class="BOwlexa2" style="padding-left: 5px;border: 1px solid #00B0F0;">
										Provider
									</td>
									<td colspan="3" class="BOwlexa2" style="padding-left: 5px;border: 1px solid #00B0F0;">
										<c:out value="${myCase.providerId.providerName}" />
									</td>
								</tr>
								<tr>
									<td class="BOwlexa2"  style="padding-left: 5px;border: 1px solid #00B0F0;">
										Admission Date
									</td>
									<td class="BOwlexa2"  style="padding-left: 5px;border: 1px solid #00B0F0;">
										<c:out value="${myCase.caseStartTime}" />
									</td>
									<td class="BOwlexa2"  style="padding-left: 5px;border: 1px solid #00B0F0;">
										ICD Code 
									</td>
									<td colspan="3" class="BOwlexa2" style="padding-left: 5px;border: 1px solid #00B0F0;">
										<b><c:out value="${myCase.diagnosis1Id.diagnosisCode}" /></b>
										&nbsp;-
										&nbsp;<c:out value="${myCase.diagnosis1Id.description}" />
										&nbsp;<c:if test="${diagnosis1Status eq 'disability'}"><b>(RECURRING DIAGNOSIS)</b></c:if>
										&nbsp;<c:if test="${diag1exstat eq 'exclusion'}"><b>(DIAGNOSIS EXCLUSION)</b></c:if>
									</td>
								</tr>
								<tr>									
									<td class="BOwlexa2"  style="padding-left: 5px;border: 1px solid #00B0F0;">
										Benefit
									</td>
									<td class="BOwlexa2"  style="padding-left: 5px;border: 1px solid #00B0F0;">
										<c:out value="${myCase.caseCategoryId.caseCategoryName}" />
									</td>
									<td class="BOwlexa2"  style="padding-left: 5px;border: 1px solid #00B0F0;">
										Room
									</td>
									<td class="BOwlexa2"  style="padding-left: 5px;border: 1px solid #00B0F0;">
										Rp. <c:out value="${roomBenefit }"/>
									</td>
									<td class="BOwlexa2"  style="padding-left: 5px;border: 1px solid #00B0F0;">
										ICU
									</td>
									<td class="BOwlexa2"  style="padding-left: 5px;border: 1px solid #00B0F0;">
										Rp. 
										<c:choose>
											<c:when test="${icuBenefit.benefitLimit eq -1}">
											AS CHARGE
											</c:when>
											<c:otherwise >
												<c:if test="${not empty icuBenefit.benefitLimit }">
													<fmt:formatNumber><c:out value="${icuBenefit.benefitLimit}" /></fmt:formatNumber>
												</c:if>
											</c:otherwise>
										</c:choose>
									</td>
								</tr>
							</table>
						</td>
					</tr>    
					<tr style="width: 100%">
						<td>
							<div style="padding-left: 15px;padding-right:15px; overflow:hidden;border: 1px solid #00B0F0;" class="BOwlexa2">
								<b>Limit Benefits:</b>
								<br>
								<div contenteditable="true">
									1. <br>
									2. <br>
									3. 
								</div>
								<b>Please inform immediately to OWLEXA HEALTHCARE and be concern state below :</b>
								<br>
								<table>
									<tr>
										<td class="BOwlexa2" style="padding-left: 10px">
											1.
										</td>
										<td class="BOwlexa2" style="padding-left: 10px" colspan="2">
											Participant upgrade their class that listed above
										</td>
									</tr>
									<tr>
										<td class="BOwlexa2" style="padding-left: 10px">
											2.
										</td>
										<td class="BOwlexa2" style="padding-left: 10px" colspan="2">
											There are Surgery Planning, CT Scan, MRI, Echocardiograph, Dental Proceeding, Antenatal Care, Drug Costs and
											Medical Treatment more than Rp. 500.000,00
										</td>
									</tr>
									<tr>
										<td class="BOwlexa2" style="padding-left: 10px" valign="top">
											3.
										</td>
										<td class="BOwlexa2" style="padding-left: 10px" colspan="2">
											Check Up, Infertility, Implant/Prothesa, Psychology, Mental Disorder, Congenital Abnormalities, Cosmetics, Circumcision, Suicide, HIV/AIDS,
											Sexually Transmitted Disease, Extreme Sports, Organ Transplantation
										</td>
									</tr>
									<tr>
										<td class="BOwlexa2" style="padding-left: 10px" valign="top">
											4.
										</td>
										<td class="BOwlexa2" style="padding-left: 10px" colspan="2">
											Non-medical or other expenses that are not related with diagnose are billed directly to patient. Example of non-medical costs :
										</td>
									</tr>
									<tr>
										<td>
								 		<td class="BOwlexa2">
								 			<ul>
											 	<li>
											 		<i>Telephone</i>
											 	</li>
											 	<li>
											 		<i>Thermometer</i>
											 	</li>
											 	<li>
											 		<i>Laundry</i>
											 	</li>
											 </ul>
								 		</td>
								 		<td class="BOwlexa2">
								 			<ul>
											 	<li>
											 		<i>Side dish (milk, canteen)</i>
											 	</li>
											 	<li>
											 		<i>Toilet purposes (tissue, washlap, soap, etc)</i>
											 	</li>
											 	<li>
											 		<i>Free drugs (embrocation, baby oil, balm, etc)</i>
											 	</li>
											 </ul>
								 		</td>
								 	</tr>
								 	<tr>
								 		<td class="BOwlexa2" style="padding-left: 10px">
								 			5.
								 		</td>
								 		<td class="BOwlexa2" style="padding-left: 10px" valign="top" colspan="2">
								 			Please confirm for discharge process by phone and send final bill by fax number 021-29830400 or email : cc.owlexa@lintasarta.co.id 
								 		</td>
								 	</tr>
								</table>
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<table>
								<tr>
									<td class="BOwlexa2" style="padding-left: 5px">
										Thus we say, Thank you for your attention.
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr align="center" valign="top">
						<TD height="155" colspan="2">
							<br>
							<table width="95%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td width="60%"></td>
									<td class="BOwlexa2">			  
										<br> 
										Best regards, <br>
										<br>
										<img src="images/owlexa/ma_citra.png"/>
										<br>
										<input type="text" class="inputuserstyle2" value="dr. Citra Andiani P" size="40"><br>
										<%-- <c:out value="${theUser.firstName}" />&nbsp;<c:out value="${theUser.lastName}" />  --%>
										<input type="text" class="inputuserstyle2" value="(Medical Advisor)" size="40">
									</td>
								</tr>	  
							</table>
							<br/>
						</TD>
					</TR>
				</TBODY>
			  </TABLE>
			<% //Owlexa Letter Footer Template %>
			<%@ include file="../owlexaLetterFooterEnV2.jsp" %>
		</DIV>
		<br/>
		<div align="center">
			<input type="button" name="button" value="P   R   I   N   T"  size=10 onClick="window.print()" style="background:#EEF2FC;border:1px #6f6f6f solid;color:#6f6f6f;font:10px Arial;font-weight:bold;">
			<!-- 
			<input type="button" name="button2" value="S A V E   A   C O P Y"  size=10 onClick="document.execCommand('SaveAs',null,'<?php echo $sFileName;?>')" style="background:#EEF2FC;border:1px #6f6f6f solid;color:#6f6f6f;font:10px Arial;font-weight:bold;">
			-->
			<br>
			<div class="Details"><c:out value="${myCase.memberId.currentCardNumber}" />-<c:out value="${myCase.caseNumber}" />-GL</div>
		</div>
	</BODY>
</HTML>