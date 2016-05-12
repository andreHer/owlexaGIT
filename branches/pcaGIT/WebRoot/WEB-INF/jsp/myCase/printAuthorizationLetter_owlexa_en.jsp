<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page contentType="text/html" import="java.util.*" %>

<HTML>
	<HEAD>
		<!-- Owlexa Authorization Letter -->
		<TITLE><c:out value="${myCase.memberId.currentCardNumber}" />-<c:out value="${myCase.caseNumber}" />-AL</TITLE>
		<% //Owlexa Letter Header Template %>
		<%@ include file="../owlexaLetterHeader.jsp" %>
		<DIV align=center>
			<center><br>
			<TABLE id=AutoNumber25 style="BORDER-COLLAPSE: collapse" cellSpacing=0 cellPadding=0 width=776  height=793 bgColor=#ffffff border=0>
				<!--DWLayoutTable-->
				<TBODY>
					<tr align="center" bgcolor="#00B0F0" style="background-color:#00B0F0;" height="30px">
						<td class="letterTitle2" colspan="3" valign="middle" style="background-color:#00B0F0;">
							AUTHORIZATION LETTER
						</td>
					</tr>
					<TR valign="top">
						<TD colspan="2" class="BOwlexa3">
							<br>
							&nbsp;Hereby i undersigned :&nbsp;:
						</TD>
					</TR>
					<TR>
						<TD width="387" align="center" valign="top">
							<table width="98%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td width="28%" class="BOwlexa2">
										&nbsp;Name
									</td>
									<td class="BOwlexa2">
										&nbsp;:
									</td>
								</tr>
								<tr bordercolor="#FFFFFF">
									<td width="28%" class="BOwlexa2">
										&nbsp;Date of Birth
									</td>
									<td class="BOwlexa2">
										&nbsp;:
									</td>
								</tr>
								<tr bordercolor="#FFFFFF">
									<td width="28%" class="BOwlexa2">
										&nbsp;Address
									</td>
									<td class="BOwlexa2">
										&nbsp;:
									</td>
								</tr>
								<tr bordercolor="#FFFFFF">
									<td width="28%" class="BOwlexa2">
										&nbsp;Phone No./ HP
									</td>
									<td class="BOwlexa2">
										&nbsp;:
									</td>
								</tr>
							</table>
						</TD>
					</TR>
					<TR valign="top">
						<TD colspan="2" class="BOwlexa3">
							&nbsp;I would like to register hospital admission :&nbsp;:
						</TD>
					</TR>
					<TR>
						<TD width="387" align="center" valign="top">
							<table width="98%" border="0" cellpadding="0" cellspacing="0">
								<tr bordercolor="#FFFFFF">
									<td width="28%" class="BOwlexa2">
										&nbsp;Date
									</td>
									<td class="BOwlexa2">
										&nbsp;:
									</td>
									<td class="BOwlexa2">
										<fmt:formatDate pattern="EEE" value="<%= new java.util.Date() %>" var="daylabel"/>
										/<fmt:formatDate  type="date" value="<%= new java.util.Date() %>" pattern="dd-MMM-yyyy"/> 
									</td>
								</tr>
								<tr bordercolor="#FFFFFF">
									<td width="28%" class="BOwlexa2">
										&nbsp;Hospital
									</td>
									<td class="BOwlexa2">
										&nbsp;:
									</td>
									<td class="BOwlexa2">
										<c:out value="${myCase.providerId.providerName}" />
									</td>
								</tr>
								<tr bordercolor="#FFFFFF">
									<td width="28%" class="BOwlexa2">
										&nbsp;Room and Board
									</td>
									<td class="BOwlexa2">
										&nbsp;:
									</td>
									<td  class="BOwlexa2">
										<input name="text3" type="text" class="inputuserstyle2" value="${myCase.roomAndBoard }" size="60">
										<%--&nbsp;<c:out value="${myCase.roomAndBoardUsage }"></c:out>--%>
									</td>
								</tr>
								<tr bordercolor="#FFFFFF">
									<td width="28%" class="BOwlexa2">
										&nbsp;Room and Board Entitlement 
									</td>
									<td class="BOwlexa2">
										&nbsp;:
									</td>
									<td  class="BOwlexa2">
										&nbsp;<c:out value="${roomBenefit }"></c:out>
									</td>
								</tr>
								<tr bordercolor="#FFFFFF">
									<td width="28%" class="BOwlexa2">
										&nbsp;Patient Name  / Membership Card No.
									</td>
									<td class="BOwlexa2">
										&nbsp;:
									</td>
									<td class="BOwlexa2">
										&nbsp;<c:out value="${myCase.memberId.firstName}" />
									</td>
								</tr>
								<tr bordercolor="#FFFFFF">
									<td width="28%" class="BOwlexa2">
										&nbsp;Insurance / Company
									</td>
									<td class="BOwlexa2">
										&nbsp;:
									</td>
									<td class="BOwlexa2">
										&nbsp;<c:out value="${myCase.memberId.clientId.clientName}" />
									</td>
								</tr>
								<tr bordercolor="#FFFFFF">
									<td width="28%" class="BOwlexa2">
										&nbsp;Relationship to patient
									</td>
									<td class="BOwlexa2">
										&nbsp;:
									</td>
									<td class="BOwlexa2">
										&nbsp;<b>self/husband/wife/son/daughter/other *) </b>
									</td>
								</tr>
							</table>
						</TD>
					</TR>   
					<tr align="left" valign="middle">
						<TD colspan="2" class="BOwlexa3">
							&nbsp;I hereby declare that :&nbsp;:
						</TD>
					</TR>
					<TR>
						<TD width="387" align="center" valign="top">
							<table width="98%" border="0" cellpadding="0" cellspacing="0">
								<tr bordercolor="#FFFFFF">
									<td width="5%" valign="top" align="right" style="padding:0 10px 0 0;" class="BOwlexa2">
										&nbsp;1.
									</td>
									<td align="justify" class="BOwlexa2">
										Occupied at room and board = <input name="text3" type="text" class="inputuserstyle2" value="__________/Rp__________ per day, with reason as below  **)" size="50">
										<br>
										<table>
											<tr>
												<td class="BOwlexa2"><input type="checkbox">As per room and board entitlement</td>
												<td class="BOwlexa2"><input type="checkbox">Room and board not available</td>
												<td class="BOwlexa2"><input type="checkbox">On own / patient's request</td>
											</tr>
											<tr>
												<td class="BOwlexa2"><input type="checkbox">Room and Board fully occupied</td>
												<td class="BOwlexa2"><input type="checkbox">Others : <input name="text3" type="text" class="inputuserstyle2" value="(please specify)" size="50"></td>
											</tr>
										</table>
										If there is any differences of medical expenses (room and board, physician fee, laboratory/radiology, etc) based on patient's plan benefit and terms and condition, 
										i hereby undertake to settle the differences of medical expense directly to the hospital.
									</td>
								</tr>
								<tr bordercolor="#FFFFFF">
									<td width="5%" valign="top" align="right" style="padding:0 10px 0 0;" class="BOwlexa2">
										&nbsp;2.
									</td>
									<td align="justify"  class="BOwlexa2">
										I am fully authorizing OWLEXA healthcare to obtain the confidential report of my (self/husband/wife/son/daughter/other *)  
										health condition from the hospitals/specialist/physician/institution or individual that has any record or knowledge 
										of health and medical history or treatment or advice that has been or may hereafter consulted, 
										other personal information or details of accident/injury.
									</td>
								</tr>
								<tr bordercolor="#FFFFFF">
									<td width="5%" valign="top" align="right" style="padding:0 10px 0 0;" class="BOwlexa2">
										&nbsp;3.
									</td>
									<td align="justify" class="BOwlexa2">
										I am fully aware of the terms and conditions and limits of my (self/husband/wife/son/daughter/other*) 
										health insurance and/or medical coverage scheme. 
										I hereby undertake to settle all medical expenses directly to the hospital if the medical condition 
										is falling under exclusion and/or not payable under health insurance policy and/or medical coverage scheme.
									</td>
								</tr>
								<tr bordercolor="#FFFFFF">
									<td width="5%" valign="top" align="right" style="padding:0 10px 0 0;" class="BOwlexa2">
										&nbsp;4.
									</td>
									<td align="justify" class="BOwlexa2">
										3.	I hereby undertake to settle (directly to hospital upon discharge or after a notification from 
										OWLEXA Healthcare/Insurance/Company) for  any medical expenses exceeding patient's entitlement under 
										the health insurance policy / medical coverage scheme contract or if medical expense is not payable by the same
									</td>
								</tr>
								<tr>
									<td colspan="2" align="justify" class="BOwlexa2">
										<br>
										I Certify that above statement is truest, correct and complete to the best of my knowledge and belief, 
										without any pressures from other parties. I  acknowledge that I am fully responsible for my statement, 
										which further can be used by whom who may.<br>
									</td>
								</tr>
							</table>
						</TD>
					</TR>  
					<tr align="center" valign="top">
						<TD colspan="2">
							<table width="95%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td valign="top" class="BOwlexa2" width="60%">
										<br><i>*) Cross out whichever does not apply</i>
										<br><i>*) &#10004; please tick</i>
									</td>
									<td class="BOwlexa2">			  
										<br> 
										Jakarta, <fmt:formatDate type="date" value="<%= new java.util.Date() %>" pattern="dd, MMMM, yyyy"/><br>
										The Declarer,
										<br>
										<br>
										<br>													  
										<br>
										<br>
										<input type="text" class="inputuserstyle2" value="(                                     )" size="60">
										<br>
										Stamp duty&nbsp;Rp.&nbsp;6000,- 
									</td>									
								</tr>	
							</table>
						</TD>
					</TR>
				</TBODY>
			  </TABLE>
			  <%@ include file="../owlexaLetterFooterEnV2.jsp" %>
		</DIV>
		<% //Owlexa Letter Header Template %>
		<div align="center">
		<br/>
		<input type="button" name="button" value="P   R   I   N   T"  size=10 onClick="window.print()" style="background:#EEF2FC;border:1px #6f6f6f solid;color:#6f6f6f;font:10px Arial;font-weight:bold;">
		<!-- 
		<input type="button" name="button2" value="S A V E   A   C O P Y"  size=10 onClick="document.execCommand('SaveAs',null,'<?php echo $sFileName;?>')" style="background:#EEF2FC;border:1px #6f6f6f solid;color:#6f6f6f;font:10px Arial;font-weight:bold;">
		-->
		<br>
		<div class="Details"><c:out value="${myCase.memberId.currentCardNumber}" />-<c:out value="${myCase.caseNumber}" />-AL</div>
		</div>
	</BODY>
</HTML>