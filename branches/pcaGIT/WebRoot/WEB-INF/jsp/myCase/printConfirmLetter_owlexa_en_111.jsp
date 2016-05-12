<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@page contentType="text/html" import="java.util.*" %>

<HTML>
	<HEAD>
		<!-- Owlexa Confirmation Letter -->
		<TITLE><c:out value="${myCase.memberId.currentCardNumber}" />-<c:out value="${myCase.caseNumber}" />-CL</TITLE>
		<% //Owlexa Letter Header Template %>
		<%@ include file="../owlexaLetterHeader.jsp" %>	
		<DIV align=center>
			<center><br>
			<TABLE id=AutoNumber25 style="BORDER-COLLAPSE: collapse" height=793 cellSpacing=0 cellPadding=0 width=776 bgColor=#ffffff border=0>
				<!--DWLayoutTable-->
				<TBODY>
					<tr align="right">
						<TD height="14" colspan="2" class="style55">
							Date : <%= new java.util.Date() %>
						</td>
					</tr>
					<tr align="center">
						<TD class="letterTitle" height="55" colspan="3" valign="bottom">
							<u>Confirmation Letter</u>
						</TD>
					</tr>
					<tr><TD>&nbsp;</td></tr>
					<tr><TD>&nbsp;</td></tr>
					<TR>
						<TD height="141" colspan="2"  class="BOwlexa">
							&nbsp;To &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;: 
							<input name="text" type="text" style="background-color: #FFFFFF; color: #6f6f6f; border:0px #a8c0f0 solid; rgb(0,0,0);font:13px DinLight" value="<c:out value="${myCase.providerId.providerName}" />" size="70">
							<br>
							&nbsp;UP&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;: <input name="text2" type="text" style="background-color: #FFFFFF; color: #6f6f6f; border:0px #a8c0f0 solid; rgb(0,0,0);font:13px DinLight" value="[.....................................]" size="70">
							<br>
							<br>
							<br>
							&nbsp;Subject : Confirmation &nbsp;: 
							<input name="text2" type="text" style="background-color: #FFFFFF; color: #6f6f6f; border:0px #a8c0f0 solid; rgb(0,0,0);font:13px DinLight" value="[.....................................]" size="70">
							<br>
							<br>          
							&nbsp;Dear Sir or Madam,
							<br>      
						</TD>
					</TR>	
					<TR valign="top">
						<TD height="34" colspan="2" class="BOwlexa">
							&nbsp;Thank you for your support and for the confidence that you have placed in us to provide best-in-class health care service for our costumers.
						</TD>
					</TR>
					<TR>
						<TD width="387" height=98 align="center" valign="top">
							<table width="98%" border="1" cellpadding="0" cellspacing="0">
								<tr align="center" bordercolor="#6F6F6F" bgcolor="#00B7D5">
									<td colspan="4" class="style43">PATIENT INFORMATION</td>
								</tr>
								<tr bordercolor="#FFFFFF">
									<td width="28%" class="HeaderName">
										&nbsp;Patient Name
									</td>
									<td colspan="3" class="HeaderName">
										&nbsp;<c:out value="${myCase.memberId.firstName}" /> 
									</td>
								</tr>
								<tr bordercolor="#FFFFFF">
									<td class="HeaderName">
										&nbsp;Employee Name
									</td>
									<td colspan="3" class="Details">
										&nbsp;<c:out value="${myCase.memberId.parentMember.firstName}" /> 
									</td>
								</tr>
								<tr bordercolor="#FFFFFF">
									<td class="HeaderName">
										&nbsp;Card Number 
									</td>
									<td width="35%" class="Details">
										&nbsp;<c:out value="${myCase.memberId.currentCardNumber}" /> 
									</td>
									<td width="14%" class="HeaderName">
										&nbsp;Date of Birth 
									</td>
									<td width="23%" class="Details">
										&nbsp;<c:out value="${myCase.memberId.birthday}" />
									</td>
								</tr>
								<tr bordercolor="#FFFFFF">
									<td class="HeaderName">
										&nbsp;Customer Number
									</td>
									<td class="Details">
										&nbsp;<c:out value="${myCase.memberId.customerNumber}" />
									</td>
									<td class="HeaderName">
										&nbsp;Sex
									</td>
									<td class="Details">
										&nbsp;<c:out value="${myCase.memberId.gender}" />
									</td>
								</tr>
							</table>
						</TD>
						<TD width="389" align="center" valign="top" bordercolor="#F9F9F9">
							<table width="99%" border="1" cellpadding="0" cellspacing="0">
								<tr align="center" bordercolor="#6F6F6F" bgcolor="#00B7D5">
									<td colspan="4" class="HeaderName">
										<span class="style43">
											INSURANCE
										</span>
									</td>
								</tr>
								<tr bordercolor="#FFFFFF">
									<td width="22%" align="left" class="HeaderName">
										&nbsp;Client
									</td>
									<td colspan="3" class="Details">
										&nbsp;<c:out value="${myCase.memberId.clientId.clientName}" />
									</td>
								</tr>
								<tr bordercolor="#FFFFFF">
									<td align="left" class="HeaderName"> 
										&nbsp;Group
									</td>
									<td colspan="3" class="Details">
										&nbsp;<c:out value="${myCase.memberId.memberGroupId.groupName}" /> 
									</td>
								</tr>
								<tr bordercolor="#FFFFFF">
									<td align="left" class="HeaderName">
										&nbsp;Policy Number
									</td>
									<td colspan="3" class="Details">
										&nbsp;<c:out value="${myCase.memberId.currentPolicyId.policyNumber}" />
									</td>
								</tr>
								<tr bordercolor="#FFFFFF">
									<td align="left" class="HeaderName">
										&nbsp;Policy Period 
									</td>
									<td width="35%"  class="Details">
										&nbsp;<c:out value="${myCase.memberId.currentPolicyId.effectiveDate}" />
									</td>
									<td width="5%" class="Details">
										&nbsp;<span class="HeaderName">To</span>
									</td>
									<td class="Details">
										&nbsp;<c:out value="${myCase.memberId.currentPolicyId.expireDate}" />
									</td>
								</tr>
							</table>
						</TD>
					</TR>
					<tr align="center" valign="top">
						<td height="77" colspan="2">
							<table width="768" border="1" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF">
								<tr bordercolor="#6F6F6F" bgcolor="#00B7D5">
									<td colspan="6" align="center" class="style43">
										CARE INFORMATION
									</td>
								</tr>
								<tr bordercolor="#FFFFFF">
									<td width="98" class="HeaderName">
										&nbsp;Case No 
									</td>
									<td width="90" class="BOwlexa">
										&nbsp;<c:out value="${myCase.caseNumber}" />
									</td>
									<td width="69" class="HeaderName">
										&nbsp;Hospital
									</td>
									<td colspan="3" class="Details">
										&nbsp;<c:out value="${myCase.providerId.providerName}" />
									</td>
								</tr>
								<tr bordercolor="#FFFFFF">
									<td width="98" class="HeaderName">
										&nbsp;Claim Number 
									</td>
									<td width="90" class="BOwlexa">
										&nbsp;<c:out value="${myCase.claimId.claimNumber}" />
									</td>
									<td width="69" rowspan="2" class="HeaderName">
										&nbsp;ICD Code 
									</td>
									<td colspan="3" rowspan="2" class="Details">
										&nbsp;<b><c:out value="${myCase.diagnosis1Id.diagnosisCode}" /></b>
										&nbsp;-
										&nbsp;<c:out value="${myCase.diagnosis1Id.description}" />
										&nbsp;<c:if test="${diagnosis1Status eq 'disability'}"><b>(RECURRING DIAGNOSIS)</b></c:if>
										&nbsp;<c:if test="${diag1exstat eq 'exclusion'}"><b>(DIAGNOSIS EXCLUSION)</b></c:if>
									</td>
								</tr>
								<tr bordercolor="#FFFFFF">
									<td width="98" class="HeaderName">
										&nbsp;Admission Date 
									</td>
									<td width="90" class="Details">
										&nbsp;<c:out value="${myCase.caseStartTime}" />
									</td>
								</tr>
								<tr bordercolor="#FFFFFF">									
									<td width="98" class="HeaderName">
										&nbsp;Room Benefit
									</td>
									<td width="90" class="Details">
										&nbsp;<c:out value="${roomBenefit }"></c:out>
									</td>
									<td width="69" class="HeaderName">
										&nbsp;ICU
									</td>
									<td width="313" class="Details">
										&nbsp;
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
									<td width="61" class="HeaderName">
										&nbsp;Benefit
									</td>
									<td width="123" class="Details">
										&nbsp;<c:out value="${myCase.caseCategoryId.caseCategoryName}" />
									</td>
								</tr>
							</table>
						</td>
					</tr>    
					<tr align="left" valign="middle">
						<TD height="14" colspan="2" class="BOwlexa">
							<br>&nbsp;Please confirm details information correspond to the patient care :
						</TD>
					</TR>
					<tr align="center" valign="top">
						<TD height="185" colspan="2">
							<table width="98%" border="0" cellpadding="0" cellspacing="0" bordercolor="#6f6f6f">
								<tr >
									<td width="96%" height="185" align="center" valign="bottom" bordercolor="#6f6f6f" class="BOwlexa">
										<span class="Details">
											<textarea name="txtRemarks" cols="148" rows="14" wrap="VIRTUAL" style="background-color: #FFFFFF; color: #6f6f6f; border:1px #6f6f6f solid; rgb(0,0,0);font:10px DinLight"></textarea>
										</span>
									</td>
								</tr>
							</table>
						</TD>
					</TR>
					<tr align="center" valign="top">
						<TD height="155" colspan="2">
							<table width="95%" border="0" cellpadding="0" cellspacing="0">
								<tr valign="middle">
									<td height="47" colspan="2" class="BOwlexa">
										We kindly ask you to send the confirmation via fax : (021) 
										<input name="text3" type="text" style="background-color: #FFFFFF; color: #6f6f6f; border:0px #a8c0f0 solid; rgb(0,0,0);font:12px DinLight" value="[..................]" size="15">
										, Up :	
										<input name="text32" type="text" style="background-color: #FFFFFF; color: #6f6f6f; border:0px #a8c0f0 solid; rgb(0,0,0);font:12px DinLight" value="[..................]" size="15">
										<br>
										<br>
										Thanks again, for your support and cooperation.
									</td>
								</tr>
								<tr>
									<td width="50%" class="BOwlexa">			  
										<br> 
										Yours sincerely, <br>
										<br>
										<img src="images/owlexa/ma_citra.png">
										<input name="text33" type="text" style="background-color: #FFFFFF; color: #6f6f6f; border:0px #a8c0f0 solid; rgb(0,0,0);font:12px DinLight" value="dr. Citra Andiani P" size="50">			  
										<br>
										--------------------------------------
										<br>
										[Medical Advisor] 
									</td>
									<td width="50%" align="right" class="BOwlexa">			  
										<br> 
										Doctor<br>
										<br>
										<br>
										<br>
										<br>
										<input name="text332" type="text" style="background-color: #FFFFFF; color: #6f6f6f; border:0px #a8c0f0 solid; rgb(0,0,0);font:12px DinLight" value="[.................................................................]" size="38">
										<br>
										-----------------------------------------------------
										<br>
										[Doctor Stamp / Hospital] 
									</td>
								</tr>
								<tr>
									<td colspan="2" class="BOwlexa"><br><br><br>
									  Note : <span class="style55">This letter is not a medical resume, when processing billing please attach a medical resume and insurance claim forms.<br>
									  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Owlexa Officer signature is not required. This letter is generated automatically.</span></td>
								</tr>		  
							</table>
							<br/>
							
							<% //Owlexa Letter Footer Template %>
							<%@ include file="../owlexaLetterFooter.jsp" %>	
							
							<br/>
							<input type="button" name="button" value="P   R   I   N   T"  size=10 onClick="window.print()" style="background:#EEF2FC;border:1px #6f6f6f solid;color:#6f6f6f;font:10px Arial;font-weight:bold;">
							<!-- 
							<input type="button" name="button2" value="S A V E   A   C O P Y"  size=10 onClick="document.execCommand('SaveAs',null,'<?php echo $sFileName;?>')" style="background:#EEF2FC;border:1px #6f6f6f solid;color:#6f6f6f;font:10px Arial;font-weight:bold;">
							-->
							<br>
							<div class="Details"><c:out value="${myCase.memberId.currentCardNumber}" />-<c:out value="${myCase.caseNumber}" />-CL</div>
						</TD>
					</TR>
				</TBODY>
			  </TABLE>
		</DIV>
	</BODY>
</HTML>