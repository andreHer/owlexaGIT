<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page contentType="text/html" import="java.util.*" %>
<script type="text/javascript" src="scripts/jquery/jquery-1.4.4.min.js"></script>
<HTML>
	<HEAD>
		<c:set var="letterTypeSet" value="${param.lettertype }"></c:set>
		<c:set var="clType" value="${letterTypeSet eq 'Maternity'? 'Persalinan(Maternity)': letterTypeSet eq 'Kemotherapy'? 'Kemotherapy' : letterTypeSet eq 'Haemodialisa'? 'Haemodialisa' : 'Pembedahan' }"></c:set>
		<!-- Owlexa Confirmation Letter With Type -->
		<TITLE><c:out value="${myCase.memberId.currentCardNumber}" />-<c:out value="${myCase.caseNumber}" />-CL <c:out value="${clType }"/> </TITLE>
		<% //Owlexa Letter Header Template %>
		<%@ include file="../owlexaLetterHeader.jsp" %>
		<DIV align=center>
			<center>
			<TABLE id=AutoNumber25 style="BORDER-COLLAPSE: collapse" height=793 cellSpacing=0 cellPadding=0 width=776 bgColor=#ffffff border=0>
				<!--DWLayoutTable-->
				<TBODY>
					<tr align="center" bgcolor="#00B0F0" style="background-color:#00B0F0;" height="30px">
						<td class="letterTitle2" colspan="3" valign="middle" style="background-color:#00B0F0;">
							<c:choose>
								<c:when test="${letterTypeSet eq 'Maternity' }">
									KONFIRMASI MEDIS PERSALINAN / MELAHIRKAN
								</c:when>
								<c:when test="${letterTypeSet eq 'Kemotherapy' }">
									KONFIRMASI MEDIS TINDAKAN KEMOTHERAPY
								</c:when>
								<c:when test="${letterTypeSet eq 'Haemodialisa' }">
									KONFIRMASI MEDIS TINDAKAN HAEMODIALISA
								</c:when>
								<c:otherwise>
									KONFIRMASI MEDIS TINDAKAN PEMBEDAHAN
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<td class="BOwlexa2">
							<br>
							<table style="border:  1px solid #00B0F0;border-collapse: collapse; width: 100%">
								<tr>
									<td style="padding-left: 15px" class="BOwlexa2">
										Kepada Yth,
										<br>dr. <input type="text" class="inputuserstyle2" value="[.............................]" size="40"> 
										<br>RS. <c:out value="${myCase.providerId.providerName}" />
										<br><br>
										Dengan Hormat,
										<br>Terima kasih kepada RS. <c:out value="${myCase.providerId.providerName }"></c:out> atas kepercayaannya kepada kami untuk tetap bekerja sama dalam memberikan pelayanan kesehatan klien kami.
										Berdasarkan Perawatan yang saat ini terjadi atas nama :
										<br>
										<br>Nama Pasien&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;<c:out value="${myCase.memberId.firstName}" />
										<br>No. Kartu&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;<c:out value="${myCase.memberId.currentCardNumber}" /> 
										<br>Perusahaan&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;<c:out value="${myCase.memberId.memberGroupId.groupName}" /> 
										<br>
										<br>Maka mohon bantuannya untuk menjawab seluruh pernyataan di bawah ini terkait dengan tindakan 
										<font size="2"><c:out value="${clType }"/></font> yang akan dilakukan:
									</td>
								</tr>
								<c:choose>
									<%-- Pengaturan untuk Confirm Letter Maternity --%>
									<c:when test="${letterTypeSet eq 'Maternity' }">
										<tr valign="top" class="BOwlexa2" style="border: 1px solid #00B0F0;">
											<td width="100%">
												<div contenteditable="true" style="padding-left: 15px;padding-right:15px; overflow:hidden;width: 700px" >
												1. Indikasi kuat pasien rawat inap?
													<br>
													<br>2. Anamnesa dan pemeriksaan fisik pasien saat ini?
													<br>
													<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;G ..........			P ..........			A ..........			Usia Kehamilan..........minggu
													<br>
													<br>3. Diagnosa : 
													<br>
													<br>4. Etiologi :
													<br>
													<br>5. Rencana Persalinan :
													<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;a) Partus pervaginam persalinan 
													<table style="width: 100%">
														<tr>
															<td class="BOwlexa2">
																<div style="border: 1px solid black;width: 25px;margin-left: 50px;">&nbsp;</div>
															</td>
															<td class="BOwlexa2">
																Tanpa Penyulit
															</td>
														</tr>
														<tr>
															<td class="BOwlexa2">
																<div style="border: 1px solid black;width: 25px;margin-left: 50px;">&nbsp;</div>
															</td>
															<td class="BOwlexa2">
																Dengan Penyulit, Mohon penjelasan.................................
															</td>
														</tr>
													</table>
													<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;b) Partus melalui operasi (Sectio Caisaria)
													<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-Indikasi dilakukan SC, Mohon penjelasan.....................
													<br>
													<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;c) Jika indikasi SC karena riwayat SC sebelumnya, mohon penjelasannya indikasi SC sebelumnya?
													<br>
													<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;d) Apakah SC saat ini dilakukan Atas permintaan pasien sendiri?
													<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[Ya/Tidak], Mohon penjelasan..................
													<br>6. Mohon info waktu & tanggal dilakukannya persalinan?
													<br>
													<br>7. Mohon penjelasan keaadaan bayi baru lahir :
													<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;BBL:............gram, PB:............cm, LK:............cm, Apgar Score:...../.....
													<br>
													<br>8. Pemeriksaan penunjang beserta hasil?
													<br>
													<br>9. Therapi dan tindakan yang diberikan?
													<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Ibu : ......................................
													<br>
													<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Anak : ......................................
													<br>
												</div>
											</td>
										</tr>
									</c:when>
									<c:when test="${letterTypeSet eq 'Kemotherapy' }">
										<tr valign="top" class="BOwlexa2" style="border: 1px solid #00B0F0;">
											<td width="100%">
												<textarea id="remarkTxt" name="remarkTxt" style="border:0px;width:100%;height:100%; overflow:hidden;resize:none;" class="BOwlexa2">
	1.	a) Keluhan Masuk Rawat Inap :

		b) Tanda-tanda vital saat masuk rawat inap, TD :				Nadi:				Suhu:				RR:
	
		c) Sejak kapan pasien terdiagnosa serta mendapatkan pengobatan untuk keluhan dan dignose rawat saat ini?
	
		   [Tanggal:........... Bulan:........... Tahun:...........]
					
	2.	Indikasi kuat pasien rawat inap:..................................................................................................................................

	3.	Hasil pemeriksaan penunjang:......................................................................................................................................

	4.	a) Diagnosa Tegak:......................................................................................................................................................

		b) Etiologi:......................................................................................................................................................
	
	5.	Mohon penjelasan untuk khemotherapy:
		a) Jenis obat khemotherapy dan beserta cara pemberiannya: ................................................................................
		b) Lama pemberian untuk sekali khemotherapy : ................................................................................
		c) Interval waktu untuk masing-masing khemotyerapy : ................................................................................
		d) Berapa kuure yang diberikan kepada pasien ini : ................................................................................
		e) Berapa kuure khemotherapy yang sudah diberikan kepada pasien sebelumnya? ................................................................................
   
	6.	Apakah untuk khemotherapy selanjutnya diperlukan rawat inap? Mohon penjelasan..............................................................................
	
	7.	Perkiraan Lama Rawat Inap:..............................................................................
	
</textarea>
											</td>
										</tr> 
									</c:when>
									<c:when test="${letterTypeSet eq 'Haemodialisa' }">
										<tr valign="top" class="BOwlexa2" style="border: 1px solid #00B0F0;">
											<td>
												<textarea id="remarkTxt" name="remarkTxt" style="border:0px;width:100%;height:100%; overflow:hidden;resize:none;" class="BOwlexa2">
1.	a) Keluhan Masuk Rawat Inap :

	b) Tanda-tanda vital saat masuk rawat inap, TD :				Nadi:				Suhu:				RR:
	
	c) Sejak kapan pasien terdiagnosa serta mendapatkan pengobatan untuk keluhan dan dignose rawat saat ini?
	
					[Tanggal:........... Bulan:........... Tahun:...........]
					
2.	Indikasi kuat pasien rawat inap:..................................................................................................................................

3.	Hasil pemeriksaan penunjang:......................................................................................................................................

4.	a) Diagnosa:......................................................................................................................................................

	b) Etiologi:......................................................................................................................................................
	
5.	Apakah etiologi diagnose berhubungan dengan:
	a) Kelainan konginental : [Ya/Tidak]*
	b) Kelainan herediter : [Ya/Tidak]*
	c) Lain-Lain

6.	Jika direncanakan HAEMODIALISA dalam rawat inap saat ini, mohon penjelasan:
	a) Apakah haemodialisa tersebut merupakan haemodialisa rutin yang sudah terjadwal? [Ya/Tidak]*
	   Mohon penjelasan............................................................................
	   
	b) Berapa kali direncanakan haemodialisa untuk rawat saat ini?.................................
	
	c) Apakah haemodialisa yang dijalani pasien ini dan kondisi saat ini dapat dilakukan dengan rawat jalan?
	   [Ya/Tidak]*, Mohon penjelasan.......................................................................
	   
	d) Sampai dengan kondisi bagaimana sehingga HAEMODIALISA selanjutnya dapat dilakukan dengan rawat jalan?

7.	Perkiraan Lama Rawat Inap:..............................................................................
	*)Mohon lingkari salah satu jawaban
</textarea>
											</td>
										</tr>
									</c:when>
									<c:otherwise>
										<tr valign="top" class="BOwlexa2" style="border: 1px solid #00B0F0;">
											<td>
											<textarea id="remarkTxt" name="remarkTxt" style="border:0px;width:100%;height:100%; overflow:hidden;resize:none;" class="BOwlexa2">
	1.	Keluhan masuk rawat inap dan sejak kapan keluhan tersebut dirasakan :
	
	2.	Pemeriksaan Fisik:
			-TD:			-Nadi			-Suhu:				-RR:
			
	3.	Riwayat penyakit dahulu dan sejak kapan : ................... Tanggal/Bulan/Tahun:.............................
	
	4.	Hasil pemeriksaan penunjang:
	
	5.	Diagnosa:
			-Regio:			-Ukuran(Kedalaman):				-Jumlah:
			
	6.	Etiologi : 
	
	7.	Untuk tindakan operative, mohon informasi : 
		a)	Rencana waktu jadwal pembedaahan :..........................	c)	Jumlah sayatan:.......................
		b)	Jenis tindakan :............................................				d)	Indikasi tindakan:....................
		e)	Status pembedahan : Cito/Elektif/OCD*, *Mohon lingkari salah satu jawaban
		
	8.	Jenis Anasthesi : []Lokal		[]Umum, Mohon penjelasan indikasi:
	
	9.	Therapy yang diberikan : 
	
	10. Mohon penjelasan, Apakah diagnosa dan tindakan berhubungan dengan :
		a)	Kongenital (Ya/Tidak)*					b)	Herediter (Ya/Tidak)
		c)	Kosmetik (Ya/Tidak)*						d)	PHS (Ya/Tidak)
		e)	Dental (Ya/Tidak)*						f)	Lain-lain
	
	11.	Perkiraan Rawat Inap : 
		
	12.	Mohon informasi apakah dilakukan PA, pada pasien ini?
		a.	Ya, Hasilnya...................................................................
		b.	Tidak, Mohon penjelasan lebih lanjut..................................................</textarea>
											</td>
										</tr>
									</c:otherwise>
								</c:choose>
								<tr>
									<td>
										<table style="width: 100%; padding-left: 15px">
											<tr>
												<td class="BOwlexa2">
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Atas perhatian dan kerjasamanya kami ucapkan terima kasih.
												</td>
											</tr>
											<tr>
												<td width="50%" class="BOwlexa2">
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
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Jakarta, <fmt:formatDate type="date" value="<%= new java.util.Date() %>" pattern="dd"/>, <c:out value="${mlabel }"/>, <fmt:formatDate type="date" value="<%= new java.util.Date() %>" pattern="yyyy"/><br>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Hormat kami,</td>
												<td class="BOwlexa2">Dokter yang merawat,</td>
											</tr>
											<tr>
												<td>
													<img src="images/owlexa/ma_citra.png"/>
												</td>
												<td></td>
											</tr>
											<tr>
												<td class="BOwlexa2">
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name="text332" type="text" class="inputuserstyle3" value="dr. Citra Andiani P" size="40">
													<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Medical Advisor
												</td>
												<td class="BOwlexa2">
													<input name="text332" type="text" class="inputuserstyle3" value="Nama/Stempel Dokter/RS" size="40">
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</td>
					</tr>	
				</TBODY>
			  </TABLE>
			  <br>
			  <%@ include file="../owlexaLetterFooterV2.jsp" %>
		</DIV>
		<div align="center">
			<% //Owlexa Letter Footer Template %>
			<br/>
			<input type="button" name="button" value="P   R   I   N   T"  size=10 onClick="window.print()" style="background:#EEF2FC;border:1px #6f6f6f solid;color:#6f6f6f;font:10px Arial;font-weight:bold;">
			<!-- 
			<input type="button" name="button2" value="S A V E   A   C O P Y"  size=10 onClick="document.execCommand('SaveAs',null,'<?php echo $sFileName;?>')" style="background:#EEF2FC;border:1px #6f6f6f solid;color:#6f6f6f;font:10px Arial;font-weight:bold;">
			-->
			<div class="Details"><c:out value="${myCase.memberId.currentCardNumber}" />-<c:out value="${myCase.caseNumber}" />-CL <c:out value="${clType }"/></div>
		</div>
		<script language="javascript">
		$(document).ready(function(){
		    resize();
			function h(e) {
			  $(e).css({'height':'auto','overflow-y':'hidden'}).height(e.scrollHeight);
			}
			$('textarea').bind('mouseout', function() {
		      h(this);
		  	});
			  
		     
		});
		
		//window.onload=attachAutoResizeEvents;
		
		
		
		function resize(){
			document.getElementById('remarkTxt').style.height = 'auto';
			document.getElementById('remarkTxt').style.height = document.getElementById('remarkTxt').scrollHeight+'px';
		}
		</script>
	</BODY>
</HTML>