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
							SURAT INVESTIGASI DOKTER
						</td>
					</tr>
					<tr><TD>&nbsp;</td></tr>
					<TR>
						<td width="60%"></td>
						<td  class="BOwlexa2" colspan="2">
							<fmt:formatDate pattern="MMM" value="<%= new java.util.Date() %>" var="monthlabel"/>
							<c:choose>
								<c:when test="${monthlabel eq 'Jan' }">
									<c:set var="mlabel" value="Januari"/>
								</c:when>
								<c:when test="${monthlabel eq 'Feb' }">
									<c:set var="mlabel" value="Pebruari"/>
								</c:when>
								<c:when test="${monthlabel eq 'Mar' }">
									<c:set var="mlabel" value="Maret"/>
								</c:when>
								<c:when test="${monthlabel eq 'Apr' }">
									<c:set var="mlabel" value="April"/>
								</c:when>
								<c:when test="${monthlabel eq 'May' }">
									<c:set var="mlabel" value="Mei"/>
								</c:when>
								<c:when test="${monthlabel eq 'Jun' }">
									<c:set var="mlabel" value="Juni"/>
								</c:when>
								<c:when test="${monthlabel eq 'Jul' }">
									<c:set var="mlabel" value="Juli"/>
								</c:when>
								<c:when test="${monthlabel eq 'Aug' }">
									<c:set var="mlabel" value="Agustus"/>
								</c:when>
								<c:when test="${monthlabel eq 'Sep' }">
									<c:set var="mlabel" value="September"/>
								</c:when>
								<c:when test="${monthlabel eq 'Oct' }">
									<c:set var="mlabel" value="Oktober"/>
								</c:when>
								<c:when test="${monthlabel eq 'Nov' }">
									<c:set var="mlabel" value="November"/>
								</c:when>
								<c:otherwise>
									<c:set var="mlabel" value="Desember"/>
								</c:otherwise>
							</c:choose>
							Jakarta, <fmt:formatDate type="date" value="<%= new java.util.Date() %>" pattern="dd"/>, <c:out value="${mlabel }"/>, <fmt:formatDate type="date" value="<%= new java.util.Date() %>" pattern="yyyy"/><br>
							Kepada Yth,
						</td>
					</TR>	
					<tr>
						<td width="60%"></td>
						<td class="BOwlexa2" width="300px" colspan="2">
								dr&nbsp;&nbsp;<input type="text" class="inputuserstyle2" value="[.............................]" size="30"></td>
					</tr>
					<tr>
						<td width="60%"></td>
						<td class="BOwlexa2" width="300px" colspan="2">
								RS&nbsp;<input name="text2" type="text" class="inputuserstyle2" value="[.............................]" size="30"></td>
					</tr>
					<tr>
						<td width="60%"></td>
						<td class="BOwlexa2" width="300px" colspan="2">di.<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tempat</td>
					</tr>
					<TR valign="top">
						<TD class="BOwlexa2" colspan="3">
							<table>
								<tr>
									<td class="BOwlexa2">
										<b>Perihal : <u>Konfirmasi Perawatan Pasien</u></b>  
									</td>
								</tr>
								<tr>
									<td class="BOwlexa2">
										<br>
										Dengan Hormat,
									</td>
								</tr>
								<tr>
									<td class="BOwlexa2" align="justify">
										<br>
										Terima kasih kepada RS <input type="text" class="inputuserstyle2" value="${myCase.providerId.providerName }" size="30"> 
										yang telah memberikan kepercayaan kepada kami untuk tetap bekerja sama dalam memberikan pelayanan kesehatan kepada klien kami.
									</td>
								</tr>
								<tr>
									<td class="BOwlexa2">
										<br>
										Sehubungan dengan perawatan inap pasien dibawah ini&nbsp;:
									</td>
								</tr>
							</table>
						</TD>
					</TR>
					<TR>
						<TD width="387" height=98 align="center" valign="top">
							<table width="98%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td width="30%" class="BOwlexa2">
										&nbsp;No. Kartu
									</td>
									<td class="BOwlexa2">
										&nbsp;:<c:out value="${myCase.memberId.currentCardNumber}" /> 
									</td>
								</tr>
								<tr>
									<td width="30%" class="BOwlexa2">
										&nbsp;Nama Pasien
									</td>
									<td class="BOwlexa2">
										&nbsp;:<c:out value="${myCase.memberId.firstName}" /> 
									</td>
								</tr>
								<tr>
									<td class="BOwlexa2">
										&nbsp;Perusahaan
									</td>
									<td class="BOwlexa2">
										&nbsp;:<c:out value="${myCase.memberId.memberGroupId.groupName}" /> 
									</td>
								</tr>
								<tr>
									<td class="BOwlexa2">
										&nbsp;Asuransi 
									</td>
									<td class="BOwlexa2">
										&nbsp;:<c:out value="${myCase.memberId.clientId.clientName}" /> 
									</td>
								</tr>
								<tr>
									<td class="BOwlexa2">
										&nbsp;Tanggal Rawat
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
							&nbsp;Mohon dapat diberikan informasi medis atas perawatan pasien tersebut dengan data sebagai berikut&nbsp;:
						</TD>
					</TR>
					<TR>
						<TD width="350" align="center" valign="top" colspan="3">
							<table width="100%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td class="BOwlexa2">
										<textarea name="txtRemarks" style="width:100%; height:150; resize:none;background-color: transparent; color: #000000; border:0px;font:10px Calibri;overflow:hidden">
	1.	Apa keluhan Utama pasien saat ini?
										
	2.	Sejak kapan keluhan tersebut dialami oleh pasien?
										
	3.	Apa diagnose pasien tersebut?
										
	4.	Therapy apa yang diberikan?
										
	5.	Apakah penyakit tersebut termasuk kedalam penyakit yang berhubungan dengan Hormonal/congenital/penyakit sexual dan lainnya, sebutkan?

</textarea>
										Demikian yang dapat disampaikan, atas perhatian dan kerja samanya kami ucapkan terima kasih.
									</td>
								</tr>
							</table>
						</TD>
					</TR>
					  
					<tr align="justify" valign="middle">
						<td class="BOwlexa5">			  
							<br>
							&nbsp;<b>Salam sejawat,</b>
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
							Dokter yang merawat&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<br>
							<br>
							<br>
							<br>
							<br>
							<input name="text332" type="text" class="inputuserstyle3" value="(Nama Jelas dan Cap RS)" size="40">
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
			   <%@ include file="../owlexaLetterFooterV2.jsp" %>
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