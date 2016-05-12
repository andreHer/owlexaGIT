<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page contentType="text/html" import="java.util.*" %>

<HTML>
	<HEAD>
		<!-- Owlexa Medical Form Letter -->
		<TITLE><c:out value="${myCase.memberId.currentCardNumber}" />-<c:out value="${myCase.caseNumber}" />-RESUMEMEDICFORM-<c:out value="${param.langselect }"/></TITLE>
		<% //Owlexa Letter Header Template %>
		<%@ include file="../owlexaLetterHeader.jsp" %>
		<DIV align=center>
			<center>
			<TABLE id=AutoNumber25 style="BORDER-COLLAPSE: collapse" cellSpacing=0 cellPadding=0 width=776 bgColor=#ffffff border=0>
				<!--DWLayoutTable-->
				<TBODY>
					<tr align="center">
						<td class="letterTitle2" colspan="3" valign="bottom" style="background-color:#00B0F0;">
							FORMULIR RESUME MEDIS AWAL <div style="font-size: 8px;">(Mohon diisi oleh dokter yang merawat)</div>
						</td>
					</tr>
					<tr><td>&nbsp;</td></tr>
					<tr>
						<td width="387" height=98 align="center" valign="top">
							<table width="98%" border="1" cellpadding="0" cellspacing="0" bordercolor="#00B0F0"  style="border-style: solid">
								<tr bordercolor="#FFFFFF">
									<td width="100" class="Details2" colspan="2"  style="border-style: solid">
										&nbsp;Kepada Yth,
									</td>
									<td class="Details2"  style="border-style: solid">
										<table>
											<tr>
												<td class="Details2" width="100px">
													&nbsp;Dari
												</td>
												<td class="Details2">
													:&nbsp;<input name="text3" type="text" class="inputuserstyle2" value=".................." size="50">
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td class="Details2"  style="border-style: solid">
										&nbsp;Rumah Sakit
									</td>
									<td class="Details2" style="border-style: solid">
										&nbsp;<c:out value="${myCase.providerId.providerName}"/>
									</td>
									<td class="Details2" style="border-style: solid">
										<table>
											<tr>
												<td class="Details2" width="100px">
													&nbsp;Tanggal
												</td>
												<td class="Details2">
													: &nbsp;<fmt:formatDate type="date" value="<%=new java.util.Date() %>" pattern="dd-MMM-yyyy"/>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td width="14%" class="Details2" style="border-style: solid">
										&nbsp;Up.
									</td>
									<td width="23%" class="Details2" style="border-style: solid">
										&nbsp;<input type="text" class="inputuserstyle2" value=".................." size="30">
									</td>
									<td rowspan="3" colspan="2" class="Details2" align="center" style="border-style: solid">
										<br><br>
										<input type="text" class="inputuserstyle2" value="(Acc Change Requester)" size="20">
									</td>
								</tr>
								<tr>
									<td class="Details2" style="border-style: solid">
										&nbsp;No. Telp
									</td>
									<td class="Details2" style="border-style: solid">
										&nbsp;<input type="text" class="inputuserstyle2" value=".................." size="20">
									</td>
								</tr>
								<tr>
									<td class="Details2" style="border-style: solid">
										&nbsp;No. Fax
									</td>
									<td class="Details2" style="border-style: solid">
										&nbsp;<input name="text3" type="text" class="inputuserstyle2" value=".................." size="20">
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr align="center" valign="top">
						<td width="387" align="center" valign="top">
							<table width="98%" border="1" cellpadding="0" cellspacing="0" bordercolor="#00B0F0" style="border-style: solid">
								<tr bordercolor="#FFFFFF">
									<td class="Details2" style="border-style: solid">
										&nbsp;No Kartu
									</td>
									<td class="Details2" style="border-style: solid">
										&nbsp;<c:out value="${myCase.memberId.currentCardNumber}" />
										<%-- <c:out value="${myCase.memberId.customerPolicyNumber}" />--%>
									</td>
									<td width="200" class="Details2" style="border-style: solid">
										&nbsp;Tanggal Masuk RS
									</td>
									<td class="Details2" style="border-style: solid">
										&nbsp;<fmt:formatDate value="${myCase.caseStartTime}" pattern="dd-MMM-yyyy"/>
									</td>
								</tr>
								<tr bordercolor="#FFFFFF">
									<td width="105" class="Details2" style="border-style: solid">
										&nbsp;Nama Pasien
									</td>
									<td width="210" class="Details2" style="border-style: solid">
										&nbsp;<c:out value="${myCase.memberId.firstName}" />
									</td>
									<td class="Details2" style="border-style: solid">
										&nbsp;Kamar yang Ditempati
									</td>
									<td class="Details2" style="border-style: solid">
										&nbsp;<input name="text3" type="text" class="inputuserstyle2" value="${myCase.roomAndBoard}" size="20">
									</td>
								</tr>
								<tr>
									<td class="Details2" style="border-style: solid">
										&nbsp;Jenis Kelamin
									</td>
									<td class="Details2" style="border-style: solid">
										&nbsp;<c:out value="${myCase.memberId.gender}" />
									</td>
									<td class="Details2" rowspan="2" style="border-style: solid">
										&nbsp;Diagnosa Masuk RS
									</td>
									<td class="Details2" rowspan="2" style="border-style: solid">
										&nbsp;<b><c:out value="${myCase.diagnosis1Id.diagnosisCode}" /></b>
										&nbsp;-
										&nbsp;<c:out value="${myCase.diagnosis1Id.description}" />
									</td>
								</tr>
								<tr>									
									<td class="Details2" style="border-style: solid">
										&nbsp;Tanggal Lahir
									</td>
									<td class="Details2" style="border-style: solid">
										&nbsp;<fmt:formatDate value="${myCase.memberId.birthday }" pattern="dd-MMM-yyyy"/>
									</td>
								</tr>
							</table>
						</td>
					</tr>    
					<tr align="center" valign="top">
						<TD colspan="2">
							<table width="98%" border="0" cellpadding="0" cellspacing="0" bordercolor="#6f6f6f" style="border-style: solid">
								<tr >
									<td width="96%" align="center" valign="bottom" bordercolor="#6f6f6f" class="BOwlexa3">
										<br>
										<span class="Details">
											<textarea name="txtRemarks" style="width:100%; height:30px; resize:none;background-color: transparent; color: #000000; border:2px #00B0F0 solid; rgb(0,0,0);font:10px Calibri;font-weight: bold;">KONDISI DAN KELUHAN PASIEN SAAT KONSULTASI : 
</textarea>
										</span>
									</td>
								</tr>
							</table>
						</TD>
					</TR>
					<tr align="left" valign="middle">
						<td class="BOwlexa3">
							<br>&nbsp;&nbsp;&nbsp;&nbsp; TANDA-TANDA VITAL : 
						</td>
					</tr>
					<tr align="center" valign="top">
						<td>
							<table width="98%" border="1" cellpadding="0" cellspacing="0" bordercolor="#00B0F0" style="border-style: solid">
								<tr>
									<td class="BOwlexa3" align="center" style="border-style: solid">
										Kesadaran
									</td>
									<td class="BOwlexa3" align="center" style="border-style: solid">
										Tekanan Darah
									</td>
									<td class="BOwlexa3" align="center" style="border-style: solid">
										Nadi
									</td>
									<td class="BOwlexa3" align="center" style="border-style: solid">
										Suhu
									</td>
									<td class="BOwlexa3" align="center" style="border-style: solid">
										Pernafasan
									</td>
									<td class="BOwlexa3" align="center" style="border-style: solid">
										Pemeriksaan Fisik
									</td>
								</tr>
								<tr >
									<td valign="bottom" bordercolor="#6f6f6f" class="BOwlexa3" style="border-style: solid">
										<span>
											<textarea style="resize:none;background-color: transparent;font:8px Calibri;border: 0;width: 100%;height: 10px"></textarea>
										</span>
									</td>
									<td valign="bottom" bordercolor="#6f6f6f" class="BOwlexa3" style="border-style: solid">
										<span>
											<textarea style="resize:none;background-color: transparent;font:8px Calibri;border: 0;width: 100%;height: 10px"></textarea>
										</span>
									</td>
									<td valign="bottom" bordercolor="#6f6f6f" class="BOwlexa3" style="border-style: solid">
										<span>
											<textarea style="resize:none;background-color: transparent;font:8px Calibri;border: 0;width: 100%;height: 10px"></textarea>
										</span>
									</td>
									<td valign="bottom" bordercolor="#6f6f6f" class="BOwlexa3" style="border-style: solid">
										<span>
											<textarea style="resize:none;background-color: transparent;font:8px Calibri;border: 0;width: 100%;height: 10px"></textarea>
										</span>
									</td>
									<td valign="bottom" bordercolor="#6f6f6f" class="BOwlexa3" style="border-style: solid">
										<span>
											<textarea style="resize:none;background-color: transparent;font:8px Calibri;border: 0;width: 100%;height: 10px"></textarea>
										</span>
									</td>
									<td valign="bottom" bordercolor="#6f6f6f" class="BOwlexa3" style="border-style: solid">
										<span>
											<textarea style="resize:none;background-color: transparent;font:8px Calibri;border: 0;width: 100%;height: 10px"></textarea>
										</span>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr align="left" valign="middle">
						<td class="BOwlexa3" colspan="6">
							&nbsp;&nbsp;&nbsp;&nbsp; DIAGNOSA MEDIS :
							<br>&nbsp;&nbsp;&nbsp;&nbsp; 1.  <input type="text" class="inputuserstyle2" value="........................" size="140">
							<br>&nbsp;&nbsp;&nbsp;&nbsp; 2.  <input type="text" class="inputuserstyle2" value="........................" size="140">
						</td>
					</tr>
					<tr align="left" valign="middle">
						<td class="BOwlexa3" colspan="1">
							<br>
							&nbsp;&nbsp;&nbsp;&nbsp; RIWAYAT PENYAKIT TERDAHULU : 
							<br>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" class="inputuserstyle2" size="140" value=".................">
							<br>
						</td>
					</tr>
					<tr align="left" valign="middle">
						<td class="BOwlexa3" colspan="1">
							<br>
							&nbsp;&nbsp;&nbsp;&nbsp; KEMUNGKINAN PENYEBAB PENYAKIT : 
							<br>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" class="inputuserstyle2" size="140" value=".................">
							<br>
						</td>
					</tr>
					<tr align="left" valign="middle">
						<td class="BOwlexa3" colspan="1">
							&nbsp;&nbsp;&nbsp;&nbsp; APAKAH PENYAKIT YANG DIDERITA SAAT INI BERHUBUNGAN DENGAN (Isi Jawaban dengan Lengkap) : 
						</td>
					</tr>
					<tr align="center" valign="top">
						<td width="387" align="center" valign="top">
							<table width="98%" border="1" cellpadding="0" cellspacing="0" bordercolor="#00B0F0" style="border-style: solid">
								<tr>
									<td width="200" class="HeaderName2" align="center" style="border-style: solid">
										Jenis
									</td>
									<td width="10" class="HeaderName2" align="center" style="border-style: solid">
										Ya
									</td>
									<td width="10" class="HeaderName2" align="center" style="border-style: solid">
										Tidak
									</td>
									<td width="200" class="HeaderName2" align="center" style="border-style: solid">
										Jenis
									</td>
									<td width="10" class="HeaderName2" align="center" style="border-style: solid">
										Ya
									</td>
									<td width="10" class="HeaderName2" align="center" style="border-style: solid">
										Tidak
									</td>
									<td width="200" class="HeaderName2" align="center" style="border-style: solid">
										Jenis
									</td>
									<td width="10" class="HeaderName2" align="center" style="border-style: solid">
										Ya
									</td>
									<td width="10" class="HeaderName2" align="center" style="border-style: solid">
										Tidak
									</td>
								</tr>
								<tr>
									<td class="Details2" style="border-style: solid">
										&nbsp;1. Congenital
									</td>
									<td align="center" style="border-style: solid">
										<input type="checkbox">
									</td>
									<td align="center" style="border-style: solid">
										<input type="checkbox">
									</td>
									<td class="Details2" style="border-style: solid">
										&nbsp;6. Kosmetik
									</td>
									<td align="center" style="border-style: solid">
										<input type="checkbox">
									</td>
									<td align="center" style="border-style: solid">
										<input type="checkbox">
									</td>
									<td class="Details2" style="border-style: solid">
										&nbsp;11. Penyakit Menular Sexual
									</td>
									<td align="center" style="border-style: solid">
										<input type="checkbox">
									</td>
									<td align="center" style="border-style: solid">
										<input type="checkbox">
									</td>
								</tr>
								<tr>
									<td class="Details2" style="border-style: solid">
										&nbsp;2. Herediter
									</td>
									<td align="center" style="border-style: solid">
										<input type="checkbox">
									</td>
									<td align="center" style="border-style: solid">
										<input type="checkbox">
									</td>
									<td class="Details2" style="border-style: solid">
										&nbsp;7. Percobaan Bunuh Diri
									</td>
									<td align="center" style="border-style: solid">
										<input type="checkbox">
									</td>
									<td align="center" style="border-style: solid">
										<input type="checkbox">
									</td>
									<td class="Details2" style="border-style: solid">
										&nbsp;12. Gangguan Kejiwaan
									</td>
									<td align="center" style="border-style: solid">
										<input type="checkbox">
									</td>
									<td align="center" style="border-style: solid">
										<input type="checkbox">
									</td>
								</tr>
								<tr>
									<td class="Details2" style="border-style: solid">
										&nbsp;3. Kehamilan
									</td>
									<td align="center" style="border-style: solid">
										<input type="checkbox">
									</td>
									<td align="center" style="border-style: solid">
										<input type="checkbox">
									</td>
									<td class="Details2" style="border-style: solid">
										&nbsp;8. Kecelakaan
									</td>
									<td align="center" style="border-style: solid">
										<input type="checkbox">
									</td>
									<td align="center" style="border-style: solid">
										<input type="checkbox">
									</td>
									<td class="Details2" style="border-style: solid">
										&nbsp;13. Olahraga Berbahaya
									</td>
									<td align="center" style="border-style: solid">
										<input type="checkbox">
									</td>
									<td align="center" style="border-style: solid">
										<input type="checkbox">
									</td>
								</tr>
								<tr>									
									<td class="Details2" style="border-style: solid">
										&nbsp;4. Fertilitas
									</td>
									<td align="center" style="border-style: solid">
										<input type="checkbox">
									</td>
									<td align="center" style="border-style: solid">
										<input type="checkbox">
									</td>
									<td class="Details2" style="border-style: solid">
										&nbsp;9. Obat terlarang
									</td>
									<td align="center" style="border-style: solid">
										<input type="checkbox">
									</td>
									<td align="center" style="border-style: solid">
										<input type="checkbox">
									</td>
									<td class="Details2" rowspan="2" colspan="3" style="border-style: solid">
										&nbsp;14. Lain-lain, Mohon informasi medis : &nbsp;<input type="text" class="inputuserstyle2" value="........................" size="50">
									</td>
								</tr>
								<tr>									
									<td class="Details2" style="border-style: solid">
										&nbsp;5. Penyakit Hormonal
									</td>
									<td align="center" style="border-style: solid">
										<input type="checkbox">
									</td>
									<td align="center" style="border-style: solid">
										<input type="checkbox">
									</td>
									<td class="Details2" style="border-style: solid">
										&nbsp;10. HIV/AIDS
									</td>
									<td align="center" style="border-style: solid">
										<input type="checkbox">
									</td>
									<td align="center" style="border-style: solid">
										<input type="checkbox">
									</td>
								</tr>
							</table>
						</td>
					</tr> 
					<tr align="left" valign="middle">
						<td class="BOwlexa" colspan="1" align="center">
							<br>
							<table  width="98%" border="1" cellpadding="0" cellspacing="0" bordercolor="#00B0F0" style="border-style: solid">
								<tr>
									<td class="HeaderName2" colspan="2"  style="border-style: solid">
										APAKAH PENYAKIT YANG DIDERITA PASIEN BISA DILAKUKAN DENGAN RAWAT JALAN?
									</td>
									<td class="Details2" style="border-style: solid">
										<input type="checkbox">&nbsp;Ya
									</td>
									<td class="Details2" style="border-style: solid">
										<input type="checkbox">&nbsp;Tidak
									</td>
								</tr>
								<tr> 
									<td class="Details2" colspan="4" style="border-style: solid">
										Apabila <b>TIDAK</b>, mohon penjelasan indikasi Rawat Inap : 
										<br><input type="text" class="inputuserstyle2" value="" size="140"><br>
										<br>
									</td>
								</tr>
								<tr>
									<td class="Details2" align="center" style="border-style: solid">
										a. Atas Permintaan Sendiri/Keluarga
									</td>
									<td class="Details2" align="center" style="border-style: solid">
										<input type="checkbox">&nbsp;Ya
									</td>
									<td class="Details2" align="center" style="border-style: solid">
										<input type="checkbox">&nbsp;Tidak
									</td>
									<td  class="Details2" rowspan="2" valign="top" style="border-style: solid">
										Perkiraan Lama Rawat Inap : <input type="text" class="inputuserstyle2" value="" size="20"><br>
									</td>
								</tr>
								<tr>
									<td class="Details2" align="center" style="border-style: solid">
										b. Atas Indikasi Medis
									</td>
									<td class="Details2" align="center" style="border-style: solid">
										<input type="checkbox">&nbsp;Ya
									</td>
									<td class="Details2" align="center" style="border-style: solid">
										<input type="checkbox">&nbsp;Tidak
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr align="left" valign="middle">
						<td class="BOwlexa3">
							&nbsp;&nbsp;&nbsp;&nbsp;INFORMASI HASIL PEMERIKSAAN PENUNJANG MEDIS
							<br>&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" class="inputuserstyle2" value="..............................................................................................................." size="120">
							<br>&nbsp;
						</td>
					</tr>
					<tr align="left" valign="middle">
						<td class="BOwlexa3">
							&nbsp;&nbsp;&nbsp;&nbsp;RENCANA TINDAKAN/THERAPY YANG DIBERIKAN
							<br>&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" class="inputuserstyle2" value="..............................................................................................................." size="120">
							<br>&nbsp;
						</td>
					</tr>
					<tr align="left" valign="middle">
						<td class="BOwlexa3">
							&nbsp;&nbsp;&nbsp;&nbsp;PERNYATAAN DOKTER
						</td>
					</tr>
					<tr align="left" valign="middle">
						<td class="Details2">
							&nbsp;&nbsp;&nbsp;&nbsp;Dengan ini saya menyatakan bahwa jawaban di atas adalah benar dan lengkap menurut pengetahuan dan keyakinan saya
						</td>
					</tr>
					<tr align="center" valign="top">
						<TD colspan="2">
							<table width="95%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td width="60%" class="BOwlexa5">			  
										<br> 
										Salam Sejawat, <br>
										<img src="images/owlexa/ma_citra.png" width="60" height="50">
										<br>
										<input name="text33" type="text" class="inputuserstyle3" value="dr. Citra Andiani P" size="50">			  
										<br>
										Medical Advisor
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
									</td>
								</tr>
							</table>
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
		<div class="Details"><c:out value="${myCase.memberId.currentCardNumber}" />-<c:out value="${myCase.caseNumber}" />-RESUMEMEDICFORM-<c:out value="${param.langselect }"/></div>
		</div>
	</BODY>
</HTML>