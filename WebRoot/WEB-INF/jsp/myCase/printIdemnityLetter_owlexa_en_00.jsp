<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page contentType="text/html" import="java.util.*" %>

<HTML>
	<HEAD>
		<!-- Owlexa Idemnity Letter -->
		<TITLE><c:out value="${myCase.memberId.currentCardNumber}" />-<c:out value="${myCase.caseNumber}" />-IF</TITLE>
		<% //Owlexa Letter Header Template %>
		<%@ include file="../owlexaLetterHeader.jsp" %>
		<DIV align="center">
			<TABLE id=AutoNumber25 style="BORDER-COLLAPSE: collapse" cellSpacing=0 cellPadding=0 width=776 bgColor=#ffffff border=0>
				<!--DWLayoutTable-->
				<TBODY>
					<tr align="center" bgcolor="#00B0F0" style="background-color:#00B0F0;" height="30px">
						<td class="letterTitle2" colspan="3" valign="middle" style="background-color:#00B0F0;">
							INDEMNITY FORM
						</td>
					</tr>
				</TBODY>
			  </TABLE>
		</DIV>
		<div align="center">
			<br/>
			<table style="BORDER-COLLAPSE: collapse" cellSpacing=0 cellPadding=0 width=776 bgColor=#ffffff border=0>
				<tr>
					<td>
						<table style="border:  1px solid #00B0F0;border-collapse: collapse; width: 100%">
							<tr>
								<td width="60%"></td>
								<td class="BOwlexa2">
									Jakarta, <fmt:formatDate type="date" value="<%= new java.util.Date() %>" pattern="dd, MMMM, yyyy"/><br>
									<br>Attention To
									<br>Insurance <c:out value="${myCase.memberId.clientId.clientName }"/>
									<br>To. <input type="text" class="inputuserstyle2" value="[.............................] on Behalf" size="40"> 
									<br>&nbsp;
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<br>
						<table style="border:  1px solid #00B0F0;border-collapse: collapse; width: 100%">
							<tr>
								<td width="30%" class="BOwlexa2">
									Policy Number
								</td>
								<td class="BOwlexa2">
									: <c:out value="${myCase.memberId.customerNumber }"/>/<c:out value="${myCase.memberId.currentPolicyId.policyNumber}"/>
								</td>
							</tr>
							<tr style="border: 1px solid #00B0F0;">
								<td class="BOwlexa2">
									Card Number
								</td>
								<td class="BOwlexa2">
									: <c:out value="${myCase.memberId.currentCardNumber }"/>
								</td>
							</tr>
							<tr style="border: 1px solid #00B0F0;">
								<td class="BOwlexa2">
									Fullname of Patient
								</td>
								<td class="BOwlexa2">
									: <c:out value="${myCase.memberId.firstName }"/>
								</td>
							</tr>
							<tr style="border: 1px solid #00B0F0;">
								<td class="BOwlexa2">
									Employee Name
								</td>
								<td class="BOwlexa2">
									: <c:out value="${myCase.memberId.parentMember.firstName }"/>
								</td>
							</tr>
							<tr style="border: 1px solid #00B0F0;">
								<td class="BOwlexa2">
									Date of Birth
								</td>
								<td class="BOwlexa2">
									: <c:out value="${myCase.memberId.birthday }"/>
								</td>
							</tr>
							<tr style="border: 1px solid #00B0F0;">
								<td class="BOwlexa2">
									Gender
								</td>
								<td class="BOwlexa2">
									: <c:out value="${myCase.memberId.gender }"/>
								</td>
							</tr>
							<tr style="border: 1px solid #00B0F0;">
								<td class="BOwlexa2">
									Company
								</td>
								<td class="BOwlexa2">
									: <c:out value="${myCase.memberId.memberGroupId.groupName }"/>
								</td>
							</tr>
							<tr style="border: 1px solid #00B0F0;">
								<td class="BOwlexa2">
									Date of Admission
								</td>
								<td class="BOwlexa2">
									: <c:out value="${myCase.caseStartTime }"/>
								</td>
							</tr>
							<tr style="border: 1px solid #00B0F0;">
								<td class="BOwlexa2">
									Occupied Room
								</td>
								<td class="BOwlexa2">
									: <input name="text2" type="text" class="inputuserstyle2" value="${myCase.roomAndBoard}" size="50"/>
								</td>
							</tr>
							<tr style="border: 1px solid #00B0F0;">
								<td class="BOwlexa2">
									Benefit's Room
								</td>
								<td class="BOwlexa2">
									: 
									 <c:choose>
									 	<c:when test="${roomBenefit.benefitLimit eq -1}">
										AS CHARGE
										</c:when>
										<c:otherwise >
											<c:if test="${not empty roomBenefit.benefitLimit }">
												<fmt:formatNumber><c:out value="${roomBenefit.benefitLimit}" /></fmt:formatNumber>
											</c:if>
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
							<tr style="border: 1px solid #00B0F0;">
								<td class="BOwlexa2">
									Diagnosis
								</td>
								<td class="BOwlexa2">
									: <c:out value="${myCase.diagnosis1Id.diagnosisName }"/>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<br>
						<table style="border:  1px solid #00B0F0;border-collapse: collapse; width: 100%">
							<tr style="border: 1px solid #00B0F0;" bgcolor="#00B0F0">
								<td class="HeaderName3">
									&nbsp;IF ISSUE
								</td>
							</tr>
							<tr style="border: 1px solid #00B0F0;">
								<td class="BOwlexa2">
									<input name="text2" type="text" class="inputuserstyle2" value="1. " size="140"/>
								</td>
							</tr>
							<tr style="border: 1px solid #00B0F0;">
								<td class="BOwlexa2">
									<input name="text2" type="text" class="inputuserstyle2" value="2. " size="140"/>
								</td>
							</tr>
							<tr style="border: 1px solid #00B0F0;">
								<td class="BOwlexa2">
									<input name="text2" type="text" class="inputuserstyle2" value="3. " size="140"/>
								</td>
							</tr>
							<tr style="border: 1px solid #00B0F0;" bgcolor="#00B0F0">
								<td class="HeaderName3">
									&nbsp;ANSWER OF IF ISSUE
								</td>
							</tr>
							<tr style="border: 1px solid #00B0F0;">
								<td class="BOwlexa2">
									1.
								</td>
							</tr>
							<tr style="border: 1px solid #00B0F0;">
								<td class="BOwlexa2">
									2.
								</td>
							</tr>
							<tr style="border: 1px solid #00B0F0;">
								<td class="BOwlexa2">
									3.
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<br>
						<table style="border:  1px solid #00B0F0;border-collapse: collapse; width: 100%">
							<tr style="border: 1px solid #00B0F0;border-bottom: 0px">
								<td align="center" width="50%"  class="BOwlexa2">
									Best Regards,
								</td>
								<td align="center" width="50%" style="border: 1px solid #00B0F0;border-bottom: 0px"  class="BOwlexa2">
									Insurance
								</td>
							</tr>
							<tr >
								<td align="center" style="border: 1px solid #00B0F0;border-bottom: 0px;border-top: 0px">
									<br>
									<img src="images/owlexa/ma_citra.png"/>
								</td>
								<td></td>
							</tr>
							<tr>
								<td align="center" style="border: 1px solid #00B0F0;border-bottom: 0px;border-top: 0px"  class="BOwlexa2">
									<input name="text332" type="text" class="inputuserstyle3" value="(                     dr. Citra Andiani P                    )" size="40" style="text-align: center">
								</td>
								<td align="center"  class="BOwlexa2">
									<input name="text332" type="text" class="inputuserstyle3" value="(                                              )" size="40" style="text-align: center">
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</div>
		<div align="center">
		<%--
		<br/><br/>
			<table style="BORDER-COLLAPSE: collapse;border: 1px solid black;" cellSpacing=0 cellPadding=0 width=776>
				<tr>
					<td width="200px" class="BOwlexa">
						<p style="font-size:14px;"><u><i>Issue IF</i></u></p>
						<textarea rows="8" cols="100" style="resize:none;border:none;width:100%;" class="BOwlexa">1.</textarea>
					</td>
				</tr>
				<tr>
					<td width="200px" class="BOwlexa">
						<p style="font-size:14px;"><u><i>Jawaban Issue IF</i></u></p>
						<textarea rows="8" cols="100" style="resize:none;border:none;width:100%;" class="BOwlexa">1.</textarea>
					</td>
				</tr>
			</table>
		 --%>
		 <%--
			<table style="BORDER-COLLAPSE: collapse" cellSpacing=0 cellPadding=0 width=776 bgColor=#ffffff border=0>
				<tr>
					<td width="50%" class="BOwlexa">			  
						<br> 
						Hormat kami, <br>
						<br>
						Jakarta,<input name="text33" type="text" style="background-color: #FFFFFF; color: #6f6f6f; border:0px #a8c0f0 solid; rgb(0,0,0);font:12px DinLight" size="20" value=""><br>
						<br>
						&emsp;&emsp;&emsp;<img src="images/owlexa/ma_citra.png"/>
						<br>
						<input name="text33" type="text" style="background-color: #FFFFFF; color: #6f6f6f; border:0px #a8c0f0 solid; rgb(0,0,0);font:12px DinLight" size="30" value="              dr. Citra Andiani P">			  
						<br>
						--------------------------------------
						<br>
						&emsp;&emsp;&emsp;&nbsp;[ Medical Advisor ] 
					</td>
					<td width="50%" align="center" class="BOwlexa">			  
						<br> 
						<br>
						<br>
						<br>
						<br>
						Asuransi<br>
						<br>
						<br>
						<br>
						&emsp;&emsp;&emsp;&emsp;<input name="text332" type="text" style="background-color: #FFFFFF; color: #6f6f6f; border:0px #a8c0f0 solid; rgb(0,0,0);font:12px DinLight" value="[..............................................................]" size="38">
						<br>
						-------------------------------------------------------
						<br>
						[&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;]
					</td>
				</tr>
			</table>
			 --%>
			 <br>
			 <br>
			  <%@ include file="../owlexaLetterFooterEnV2.jsp" %>
		</div>
		<div align="center">
		<input type="button" name="button" value="P   R   I   N   T"  size=10 onClick="window.print()" style="background:#EEF2FC;border:1px #6f6f6f solid;color:#6f6f6f;font:10px Arial;font-weight:bold;">
		<!-- 
		<input type="button" name="button2" value="S A V E   A   C O P Y"  size=10 onClick="document.execCommand('SaveAs',null,'<?php echo $sFileName;?>')" style="background:#EEF2FC;border:1px #6f6f6f solid;color:#6f6f6f;font:10px Arial;font-weight:bold;">
		-->
		<br>
		<div class="Details"><c:out value="${myCase.memberId.currentCardNumber}" />-<c:out value="${myCase.caseNumber}" />-IF</div>
	</div>
</HTML>
	</BODY>