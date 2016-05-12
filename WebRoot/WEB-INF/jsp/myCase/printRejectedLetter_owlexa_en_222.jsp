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
							DECLINE LETTER
						</td>
					</tr>
					<tr>
						<td>
							<table style="width: 100%">
								<tr>
									<td width="70%"></td>
									<td class="BOwlexa2">
										Jakarta, <fmt:formatDate type="date" value="<%= new java.util.Date() %>" pattern="dd, MMMM, yyyy"/>
										<br>Dear,
										<br>Hospital <c:out value="${myCase.providerId.providerName}" />
										<br>UP. <input type="text" class="inputuserstyle2" value="[.............................]" size="30">
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td class="BOwlexa2" style="padding-left: 5px">
							<table>
								<tr>
									<td class="BOwlexa2">
										Concern
									</td>
									<td class="BOwlexa2">
										: <input type="text" class="inputuserstyle2" value="[.............................]" size="40">
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td class="BOwlexa2" style="padding-left: 5px">
							<br>
							Respectfully,
							<br>
							<br>Thank you to Hospital <c:out value="${myCase.providerId.providerName}" />, who has given us the confidence to keep working together
							to provide health care services to our clients.
							<br>
							<br>In connection with the treatment of patients following hospitalization :
						</td>
					</tr>
					<tr>
						<td>
							<table style="border:  1px solid #00B0F0;border-collapse: collapse; width: 100%">
								<tr>
									<td colspan="2" class="HeaderName4" bgcolor="#00B7D5"  align="center" width="50%" style="border-right: 1px solid #FFFFFF">
										PATIENT INFORMATION
									</td>
								</tr>
								<tr>
									<td class="BOwlexa2" style="padding-left: 5px;border: 1px solid #00B0F0;">
										Card Number
									</td>
									<td class="BOwlexa2" style="padding-left: 5px;border: 1px solid #00B0F0;">
										<c:out value="${myCase.memberId.currentCardNumber}" /> 
									</td>
								</tr>
								<tr>
									<td width="100px" class="BOwlexa2"  style="padding-left: 5px;border: 1px solid #00B0F0;" >
										Patient Name
									</td>
									<td colspan="3" width="150px" class="BOwlexa2"  style="padding-left: 5px;border: 1px solid #00B0F0;">
										<c:out value="${myCase.memberId.firstName}" /> 
									</td>
								</tr>
								<tr>
									<td class="BOwlexa2"  style="padding-left: 5px;border: 1px solid #00B0F0;">
										Corporate
									</td>
									<td class="BOwlexa2"  style="padding-left: 5px;border: 1px solid #00B0F0;">
										<c:out value="${myCase.memberId.memberGroupId.groupName}" /> 
									</td>
								</tr>
								<tr>
									<td class="BOwlexa2"  style="padding-left: 5px;border: 1px solid #00B0F0;">
										Insurance
									</td>
									<td class="BOwlexa2"  style="padding-left: 5px;border: 1px solid #00B0F0;">
										<c:out value="${myCase.memberId.clientId.clientName }"/>
									</td>
								</tr>
								<tr>
									<td class="BOwlexa2"  style="padding-left: 5px;border: 1px solid #00B0F0;">
										Case Start Date
									</td>
									<td class="BOwlexa2"  style="padding-left: 5px;border: 1px solid #00B0F0;">
										<c:out value="${myCase.caseStartTime}" />
									</td>
								</tr>
								<tr>
									<td class="BOwlexa2"  style="padding-left: 5px;border: 1px solid #00B0F0;">
										Diagnose
									</td>
									<td class="BOwlexa2"  style="padding-left: 5px;border: 1px solid #00B0F0;">
										<c:out value="${myCase.diagnosis1Id.description}" />
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr style="width: 100%">
						<td class="BOwlexa2" style="padding-left: 5px">
							Base on medical information we have got and other provisions agreed, then we REFUSE give assurance on patient care aforesaid, because :
						</td>
					</tr>
					<tr>
						<td>
							<table>
								<tr>
									<td>
										<input type="checkbox">
									</td>
									<td colspan="3">
										<input type="text" class="inputuserstyle2" value="Including Imbalance Hormonal/Congenital/Herediter/PHS/(.......)(*) and Policy Exclusion" size="140">
									</td>
								</tr>
								<tr>
									<td>
										<input type="checkbox">
									</td>
									<td>
										<input type="text" class="inputuserstyle2" value="Not appropriate cooperation agreement" size="80">
									</td>
									<td>
										<input type="checkbox">
									</td>
									<td class="BOwlexa2">
										etc : <input type="text" class="inputuserstyle2" value="......................" size="40">
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td class="BOwlexa2" style="padding-left: 5px">
							Thus we can be delivered, for your attention and cooperation we thank you.
						</td>
					</tr>
					<tr>
						<td class="BOwlexa2" style="padding-left: 5px">
							<i>(*) Streak is not after</i>
						</td>
					</tr>
					<tr align="center" valign="top">
						<td colspan="2">
							<br>
							<table width="95%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td width="60%"></td>
									<td class="BOwlexa2" align="center">			  
										<br> 
										Aware, <br>
										Best Regards
										<br>
										<img src="images/owlexa/ma_citra.png"/>
										<br>
										<input type="text" style="text-align: center;" class="inputuserstyle2" value="dr. Citra Andiani P" size="40"><br>
										<%-- <c:out value="${theUser.firstName}" />&nbsp;<c:out value="${theUser.lastName}" />  --%>
										<input type="text" style="text-align: center;" class="inputuserstyle2" value="(Medical Advisor)" size="40">
									</td>
								</tr>	  
							</table>
							<br/>
						</TD>
					</tr>
					<tr>
						<td>
							<table>
								<tr>
									<td class="BOWlexa2" style="padding-left: 5px" width="20px">
										CC :
									</td>
									<td class="BOWlexa2" style="padding-left: 5px">
										1. Insurance/Corporate <input type="text" class="inputuserstyle2" value="................." size="40">
									</td>
								</tr>
								<tr>
									<td></td>
									<td class="BOWlexa2" style="padding-left: 5px">
										2. Claim Dept.
									</td>
								</tr>
								<tr>
									<td></td>
									<td class="BOWlexa2" style="padding-left: 5px">
										3. File
									</td>
								</tr>
							</table>
						</td>
					</tr>
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