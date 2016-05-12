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
							PENOLAKAN PENJAMINAN PERAWATAN
						</td>
					</tr>
					<tr>
						<td>
							<table style="width: 100%">
								<tr>
									<td width="70%"></td>
									<td class="BOwlexa2">
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
										<br>Kepada Yth,
										<br>RS <c:out value="${myCase.providerId.providerName}" />
										<br>UP. <input type="text" class="inputuserstyle2" value="[.............................]" size="30">
										<br>di
										<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tempat
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td>
							<table>
								<tr>
									<td class="BOwlexa2" style="padding-left: 5px">
										Perihal
									</td>
									<td>
										: <input type="text" class="inputuserstyle2" value="[.............................]" size="40">
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td class="BOwlexa2" style="padding-left: 5px">
							<br>
							Dengan Hormat,
							<br>
							<br>Terima kasih kepada RS <c:out value="${myCase.providerId.providerName}" /> yang telah memberikan kepercayaan kepada kami untuk tetap bekerja sama 
							dalam memberikan pelayanan kesehatan kepada klien kami.
							<br>
							<br>Sehubungan dengan perawatan inap pasien di bawah ini :
						</td>
					</tr>
					<tr>
						<td>
							<table style="border:  1px solid #00B0F0;border-collapse: collapse; width: 100%">
								<tr>
									<td colspan="2" class="HeaderName4" bgcolor="#00B7D5"  align="center" width="50%" style="border-right: 1px solid #FFFFFF">
										DATA PATIENT
									</td>
								</tr>
								<tr>
									<td class="BOwlexa2" style="padding-left: 5px;border: 1px solid #00B0F0;">
										No. Kartu 
									</td>
									<td class="BOwlexa2" style="padding-left: 5px;border: 1px solid #00B0F0;">
										<c:out value="${myCase.memberId.currentCardNumber}" /> 
									</td>
								</tr>
								<tr>
									<td width="100px" class="BOwlexa2"  style="padding-left: 5px;border: 1px solid #00B0F0;" >
										Nama Pasien
									</td>
									<td colspan="3" width="150px" class="BOwlexa2"  style="padding-left: 5px;border: 1px solid #00B0F0;">
										<c:out value="${myCase.memberId.firstName}" /> 
									</td>
								</tr>
								<tr>
									<td class="BOwlexa2"  style="padding-left: 5px;border: 1px solid #00B0F0;">
										Perusahaan
									</td>
									<td class="BOwlexa2"  style="padding-left: 5px;border: 1px solid #00B0F0;">
										<c:out value="${myCase.memberId.memberGroupId.groupName}" /> 
									</td>
								</tr>
								<tr>
									<td class="BOwlexa2"  style="padding-left: 5px;border: 1px solid #00B0F0;">
										Asuransi
									</td>
									<td class="BOwlexa2"  style="padding-left: 5px;border: 1px solid #00B0F0;">
										<c:out value="${myCase.memberId.clientId.clientName }"/>
									</td>
								</tr>
								<tr>
									<td class="BOwlexa2"  style="padding-left: 5px;border: 1px solid #00B0F0;">
										Tanggal Mulai Perawatan
									</td>
									<td class="BOwlexa2"  style="padding-left: 5px;border: 1px solid #00B0F0;">
										<c:out value="${myCase.caseStartTime}" />
									</td>
								</tr>
								<tr>
									<td class="BOwlexa2"  style="padding-left: 5px;border: 1px solid #00B0F0;">
										Diagnosa
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
							Berdasarkan info medis yang di dapat dan ketentuan lain  yang disepakati, maka kami MENOLAK memberikan Jaminan Perawatan atas pasien tersebut di atas dikarenakan :
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
										<input type="text" class="inputuserstyle2" value="Termasuk penyakit Hormonal/Congenital/Herediter/PHS/(*) dan termasuk dalam kategori pengecualian" size="140">
									</td>
								</tr>
								<tr>
									<td>
										<input type="checkbox">
									</td>
									<td>
										<input type="text" class="inputuserstyle2" value="Tidak sesuai kesepakatan dalam PKS" size="80">
									</td>
									<td>
										<input type="checkbox">
									</td>
									<td class="BOwlexa2">
										Lain-lain : <input type="text" class="inputuserstyle2" value="......................" size="40">
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td class="BOwlexa2" style="padding-left: 5px">
							Demikian yang dapat disampaikan, atas perhatian dan kerja samanya kami ucapkan terima kasih.
						</td>
					</tr>
					<tr>
						<td class="BOwlexa2" style="padding-left: 5px">
							<i>(*) Coret yang tidak sesuai</i>
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
										<%--Jakarta, <fmt:formatDate value="<%=new java.util.Date() %>" pattern="dd-MM-yyyy"/><br> --%>
										Mengetahui, <br>
										Salam Sejawat
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
										1. Asuransi/Perusahaan <input type="text" class="inputuserstyle2" value="................." size="40">
									</td>
								</tr>
								<tr>
									<td></td>
									<td class="BOWlexa2" style="padding-left: 5px">
										2. Bagian Klaim
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
			<%@ include file="../owlexaLetterFooterV2.jsp" %>
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