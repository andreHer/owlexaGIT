<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page contentType="text/html" import="java.util.*" %>

<HTML>
	<HEAD>
		<!-- Owlexa Investigation Letter -->
		<TITLE><c:out value="${myCase.memberId.currentCardNumber}" />-<c:out value="${myCase.caseNumber}" />-IL</TITLE>
		<% //Owlexa Letter Header Template %>
		<%@ include file="../owlexaLetterHeader.jsp" %>
		<DIV align=center>
			<center><br>
			<TABLE id=AutoNumber25 style="BORDER-COLLAPSE: collapse" height=776 cellSpacing=0 cellPadding=0 width=776 bgColor=#ffffff border=0>
				<!--DWLayoutTable-->
				<TBODY>
					<tr align="center" bgcolor="#00B0F0" style="background-color:#00B0F0;" height="30px">
						<td class="letterTitle2" colspan="3" valign="middle" style="background-color:#00B0F0;">
							Medical Investigation Letter
						</td>
					</tr>
					<tr><TD>&nbsp;</td></tr>
					<TR>
						<td width="60%"></td>
						<td class="BOwlexa2" colspan="2">
							Jakarta, <fmt:formatDate type="date" value="<%= new java.util.Date() %>" pattern="dd, MMMM, yyyy"/><br>
							Attention To:
						</td>
					</TR>	
					<tr>
						<td width="60%"></td>
						<td class="BOwlexa2" width="300px" colspan="2">
								Dr(Name of Doctor)&nbsp;&nbsp;<input type="text" class="inputuserstyle2" value="[.............................]" size="30"></td>
					</tr>
					<tr>
						<td width="60%"></td>
						<td class="BOwlexa2" width="300px" colspan="2">
								At(Hospital's Name)&nbsp;<input name="text2" type="text" class="inputuserstyle2" value="[.............................]" size="30"></td>
					</tr>
					<TR valign="top">
						<TD class="BOwlexa2" colspan="3">
							<table>
								<tr>
									<td class="BOwlexa2">
										<b>Subject : <u>Confirmation of Patient Care</u></b>  
									</td>
								</tr>
								<tr>
									<td class="BOwlexa2">
										<br>
										Dear Colleague,
									</td>
								</tr>
								<tr>
									<td class="BOwlexa2" align="justify">
										Thank you for <input type="text" class="inputuserstyle2" value="${myCase.providerId.providerName }" size="30"> 
										who has given us the confidence to keep working together in providing health care service to our patients.
									</td>
								</tr>
								<tr>
									<td class="BOwlexa2">
										<br>
										The following patient's details as below&nbsp;:
									</td>
								</tr>
							</table>
						</TD>
					</TR>
					<TR>
						<TD width="387" align="center" valign="top">
							<table width="98%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td width="30%" class="BOwlexa2">
										&nbsp;Card Number
									</td>
									<td class="BOwlexa2">
										&nbsp;:<c:out value="${myCase.memberId.currentCardNumber}" /> 
									</td>
								</tr>
								<tr>
									<td width="30%" class="BOwlexa2">
										&nbsp;Fullname of Patient
									</td>
									<td class="BOwlexa2">
										&nbsp;:<c:out value="${myCase.memberId.firstName}" /> 
									</td>
								</tr>
								<tr>
									<td class="BOwlexa2">
										&nbsp;Company
									</td>
									<td class="BOwlexa2">
										&nbsp;:<c:out value="${myCase.memberId.memberGroupId.groupName}" /> 
									</td>
								</tr>
								<tr>
									<td class="BOwlexa2">
										&nbsp;Insurance 
									</td>
									<td class="BOwlexa2">
										&nbsp;:<c:out value="${myCase.memberId.clientId.clientName}" /> 
									</td>
								</tr>
								<tr>
									<td class="BOwlexa2">
										&nbsp;Date of Admission
									</td>
									<td class="BOwlexa2">
										&nbsp;:<c:out value="${myCase.caseStartTime}" />
									</td>
								</tr>
								<tr>
									<td class="BOwlexa2">
										&nbsp;Diagnose
									</td>
									<td class="BOwlexa2">
										&nbsp;:<b><c:out value="${myCase.diagnosis1Id.diagnosisCode}" /></b>
										&nbsp;-
										&nbsp;<c:out value="${myCase.diagnosis1Id.description}" />
										&nbsp;<c:if test="${diagnosis1Status eq 'disability'}"><b>(RECURRING DIAGNOSIS)</b></c:if>
										&nbsp;<c:if test="${diag1exstat eq 'exclusion'}"><b>(DIAGNOSIS EXCLUSION)</b></c:if>
									</td>
								</tr>
							</table>
						</TD>
					</TR>
					
					<TR valign="top">
						<TD height="30" colspan="2" class="BOwlexa2">
							&nbsp;Questions arised as below&nbsp;:
						</TD>
					</TR>
					<TR>
						<TD width="350" align="center" valign="top" colspan="3">
							<table width="100%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td class="BOwlexa2">
										<textarea name="txtRemarks" style="width:100%; height:150; resize:none;background-color: transparent; color: #000000; border:0px;font:10px Calibri;overflow:hidden">
	1.	What is the current chief of complaint?
										
	2.	Since when the complaint is occurred?
										
	3.	What is the diagnosed?
										
	4.	Therapy has been given?
										
	5.	Please mention if the illness related to Hormonal/Congenital/and other Sexually Transmitted Disease?

</textarea>
										This action is being as part of a standard investigatory procedure and does not constitute a disciplinary action or
										determination of wrong doing on you part.<br><br>
										Thank you for your kind cooperation. I am grateful for your help.
									</td>
								</tr>
							</table>
						</TD>
					</TR>
					  
					<tr align="justify" valign="middle">
						<td class="BOwlexa5" width="60%">			  
							<br>
							&nbsp;<b>Respectfully,</b>
							<br>
							<img src="images/owlexa/ma_citra.png"/>
							<br>
							&nbsp;dr. Citra Andiani P
							<br>
							_________________________
							<br>&nbsp;[ Medical Advisor ] 
						</td>
						<td class="BOwlexa5">		
						  	<br>
							Treating Physician/Doctor&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<br>
							<br>
							<br>
							<br>
							<br>
							<input name="text332" type="text" class="inputuserstyle3" value="(Signature over Written Name & Stamp)" size="40">
							<br>
							<br>
						</td>
					</TR>
					<tr align="justify" valign="middle">
						<TD height="10" colspan="2" class="BOwlexa">
							&nbsp;
						</TD>
					</TR>
				</TBODY>
			  </TABLE>
			   <%@ include file="../owlexaLetterFooterEnV2.jsp" %>
		</DIV>
		<% //Owlexa Letter Footer Template %>
		<div align="center">
		<input type="button" name="button" value="P   R   I   N   T"  size=10 onClick="window.print()" style="background:#EEF2FC;border:1px #6f6f6f solid;color:#6f6f6f;font:10px Arial;font-weight:bold;">
		<!-- 
		<input type="button" name="button2" value="S A V E   A   C O P Y"  size=10 onClick="document.execCommand('SaveAs',null,'<?php echo $sFileName;?>')" style="background:#EEF2FC;border:1px #6f6f6f solid;color:#6f6f6f;font:10px Arial;font-weight:bold;">
		-->
		<br>
		<div class="Details"><c:out value="${myCase.memberId.currentCardNumber}" />-<c:out value="${myCase.caseNumber}" />-IL</div></div>
	</BODY>
</HTML>