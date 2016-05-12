<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@page contentType="text/html" import="java.util.*" %>

<HTML>
	<HEAD>
		<!-- Owlexa Rejection Letter -->
		<TITLE><c:out value="${rejectedCase.caseId.memberId.currentCardNumber}" />-<c:out value="${rejectedCase.caseId.caseNumber}" />-DL</TITLE>
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
							<u>Surat Penolakan</u>
						</TD>
					</tr>
					<tr><TD>&nbsp;</td></tr>
					<tr><TD>&nbsp;</td></tr>
					<TR>
						<TD height="141"  class="BOwlexa"  colspan="2">
							&nbsp;Kepada Yth,
							<br>
							&nbsp;<b>
								dr&nbsp;&nbsp;<input name="text2" type="text" style="background-color: #FFFFFF; color: #6f6f6f; border:0px #a8c0f0 solid; rgb(0,0,0);font:13px DinLight" value="[.....................................]" size="70">
							</b>
							<br>
							&nbsp;<b>
								RS&nbsp;<input name="text2" type="text" style="background-color: #FFFFFF; color: #6f6f6f; border:0px #a8c0f0 solid; rgb(0,0,0);font:13px DinLight" value="[.....................................]" size="70">
							</b>
							<br>
							&nbsp;<b>Perihal : Penolakan Jaminan Perawatan</b>
							<br>
							<br>   
							<br>   
							<br>   
							<br>   
							<br>       
							&nbsp;Dengan Hormat,
							<br>      
						</TD>
					</TR>	
					<TR valign="top">
						<TD height="34" colspan="2" class="BOwlexa">
							&nbsp;Terima kasih kepada RS
							&nbsp;<c:out value="${rejectedCase.caseId.providerId.providerName}" />
							&nbsp; yang telah memberikan kepercayaan kepada kami untuk tetap bekerja sama dalam memberikan pelayanan kesehatan kepada klien kami.
						</TD>
					</TR>
					<TR valign="top">
						<TD height="34" colspan="2" class="BOwlexa">
							&nbsp;Sehubungan dengan perawatan inap pasien dibawah ini&nbsp;:
						</TD>
					</TR>
					<TR>
						<TD width="387" height=98 align="center" valign="top">
							<table width="98%" border="1" cellpadding="0" cellspacing="0">
								<tr height="25" bordercolor="#FFFFFF">
									<td width="20%" class="HeaderName">
										&nbsp;Nama Pasien
									</td>
									<td colspan="3" class="HeaderName">
										&nbsp;<c:out value="${rejectedCase.caseId.memberId.firstName}" /> 
									</td>
								</tr>
								<tr height="25"  bordercolor="#FFFFFF">
									<td class="HeaderName">
										&nbsp;Perusahaan
									</td>
									<td colspan="3" class="Details">
										&nbsp;<c:out value="${rejectedCase.caseId.memberId.memberGroupId.groupName}" /> 
									</td>
								</tr>
								<tr height="25"  bordercolor="#FFFFFF">
									<td class="HeaderName">
										&nbsp;Asuransi 
									</td>
									<td colspan="3"  class="Details">
										&nbsp;<c:out value="${rejectedCase.caseId.memberId.clientId.clientName}" /> 
									</td>
								</tr>
								<tr height="25"  bordercolor="#FFFFFF">
									<td class="HeaderName">
										&nbsp;Tanggal mulai perawatan
									</td>
									<td colspan="3" class="Details">
										&nbsp;<c:out value="${rejectedCase.caseId.caseStartTime}" />
									</td>
								</tr>
								<tr height="25"  bordercolor="#FFFFFF">
									<td class="HeaderName">
										&nbsp;Diagnose
									</td>
									<td colspan="3" class="Details">
										&nbsp;<b><c:out value="${rejectedCase.caseId.diagnosis1Id.diagnosisCode}" /></b>
										&nbsp;-
										&nbsp;<c:out value="${rejectedCase.caseId.diagnosis1Id.description}" />
										&nbsp;<c:if test="${diagnosis1Status eq 'disability'}"><b>(RECURRING DIAGNOSIS)</b></c:if>
										&nbsp;<c:if test="${diag1exstat eq 'exclusion'}"><b>(DIAGNOSIS EXCLUSION)</b></c:if>
									</td>
								</tr>
							</table>
						</TD>
					</TR>
					  
					<tr align="justify" valign="middle">
						<TD height="14" colspan="2" class="BOwlexa">
							<br>
							&nbsp;Berdasarkan Resume Medis Awal yang telah diisi dan ditanda tangani oleh dr
							&nbsp;<input name="text2" type="text" style="background-color: #FFFFFF; color: #6f6f6f; border:0px #a8c0f0 solid; rgb(0,0,0);font:13px DinLight" value="[....................]" size="10">,
							&nbsp;maka <b>kami MENOLAK memberikan Jaminan Perawatan atas pasien tersebut di atas</b> dikarenakan penyakit yang dideritanya termasuk penyakit <b>Hormonal/Congenital/Herediter/PHS/ <input name="text2" type="text" style="background-color: #FFFFFF; color: #6f6f6f; border:0px #a8c0f0 solid; rgb(0,0,0);font:13px DinLight" value="[....................]" size="10"> (*) dan termasuk dalam kategori Pengecualian</b>.
						</TD>
					</TR>
					<tr align="justify" valign="middle">
						<TD height="14" colspan="2" class="BOwlexa">
							<br>
							&nbsp;Demikian yang dapat disampaikan, atas perhatian dan kerja samanya kami ucapkan terima kasih.
						</TD>
					</TR>
					<tr align="justify" valign="middle">
						<td width="50%" class="BOwlexa">			  
							<br> 
							&nbsp;Jakarta,&nbsp;<input name="text3" type="text" style="background-color: #FFFFFF; color: #6f6f6f; border:0px #a8c0f0 solid; rgb(0,0,0);font:12px DinLight" value=".................." size="15"> 
							<br>
							&nbsp;<b>Salam sejawat,</b>
							<br>
							<img src="images/owlexa/ma_citra.png"/>
							<br>
							&nbsp;[ Medical Advisor ] 
						</td>
					</TR>
					<tr align="justify" valign="middle">
						<TD height="14" colspan="2" class="BOwlexa">
							&nbsp;
						</TD>
					</TR>
					<tr align="justify" valign="middle">
						<TD height="14" colspan="2" class="BOwlexa">
							&nbsp;
						</TD>
					</TR>
					<tr align="justify" valign="middle">
						<TD height="14" colspan="2" class="BOwlexa">
							&nbsp;
						</TD>
					</TR>
					<tr align="justify" valign="middle">
						<TD height="14" colspan="2" class="BOwlexa">
							&nbsp;
						</TD>
					</TR>
					<tr align="justify" valign="middle">
						<TD height="14" colspan="2" class="BOwlexa">
							&nbsp;
						</TD>
					</TR>
					<tr align="justify" valign="middle">
						<TD height="14" colspan="2" class="BOwlexa">
							&nbsp;
						</TD>
					</TR>
					<tr align="justify" valign="middle">
						<td width="50%" class="BOwlexa">
							<table width="98%" border="0" cellpadding="0" cellspacing="0">
								<tr height="10" bordercolor="#FFFFFF">
									<td width="4%" class="HeaderName">
										&nbsp;CC
									</td>
									<td width="4%"  class="HeaderName">
										&nbsp;: 
									</td>
									<td class="HeaderName">
										&nbsp;1.&nbsp;Asuransi/Perusahaan 
									</td>
								</tr>
								<tr height="10" bordercolor="#FFFFFF">
									<td width="4%" class="HeaderName">
										&nbsp;
									</td>
									<td width="4%"  class="HeaderName">
										&nbsp;
									</td>
									<td class="HeaderName">
										&nbsp;2.&nbsp;Bagian Klaim 
									</td>
								</tr>
								<tr height="10" bordercolor="#FFFFFF">
									<td width="4%" class="HeaderName">
										&nbsp;
									</td>
									<td width="4%"  class="HeaderName">
										&nbsp; 
									</td>
									<td class="HeaderName">
										&nbsp;3.&nbsp;File 
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr align="center" valign="top">
						<TD height="155" colspan="2">
							<br/>
							<% //Owlexa Letter Footer Template %>
							<%@ include file="../owlexaLetterFooter.jsp" %>
							<br/>
							<input type="button" name="button" value="P   R   I   N   T"  size=10 onClick="window.print()" style="background:#EEF2FC;border:1px #6f6f6f solid;color:#6f6f6f;font:10px Arial;font-weight:bold;">
							<!-- 
							<input type="button" name="button2" value="S A V E   A   C O P Y"  size=10 onClick="document.execCommand('SaveAs',null,'<?php echo $sFileName;?>')" style="background:#EEF2FC;border:1px #6f6f6f solid;color:#6f6f6f;font:10px Arial;font-weight:bold;">
							-->
							<br>
							<div class="Details"><c:out value="${rejectedCase.caseId.memberId.currentCardNumber}" />-<c:out value="${rejectedCase.caseId.caseNumber}" />-DL</div>
						</TD>
					</TR>
				</TBODY>
			  </TABLE>
		</DIV>
	</BODY>
</HTML>