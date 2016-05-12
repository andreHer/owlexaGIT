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
							Surat Pernyataan
						</td>
					</tr>
					<TR valign="top">
						<TD colspan="2" class="BOwlexa3">
							<br>
							&nbsp;Saya yang bertanda tangan dibawah ini&nbsp;:
						</TD>
					</TR>
					<TR>
						<TD width="387" align="center" valign="top">
							<table width="98%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td width="28%" class="BOwlexa2">
										&nbsp;Nama
									</td>
									<td class="BOwlexa2">
										&nbsp;:
									</td>
								</tr>
								<tr bordercolor="#FFFFFF">
									<td width="28%" class="BOwlexa2">
										&nbsp;Tgl. Lahir
									</td>
									<td class="BOwlexa2">
										&nbsp;:
									</td>
								</tr>
								<tr bordercolor="#FFFFFF">
									<td width="28%" class="BOwlexa2">
										&nbsp;Alamat
									</td>
									<td class="BOwlexa2">
										&nbsp;:
									</td>
								</tr>
								<tr bordercolor="#FFFFFF">
									<td width="28%" class="BOwlexa2">
										&nbsp;No. Telp/HP
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
							&nbsp;Bahwa saya melakukan pendaftaran Rawat Inap&nbsp;:
						</TD>
					</TR>
					<TR>
						<TD width="387" align="center" valign="top">
							<table width="98%" border="0" cellpadding="0" cellspacing="0">
								<tr bordercolor="#FFFFFF">
									<td width="28%" class="BOwlexa2">
										&nbsp;Pada Hari/Tanggal
									</td>
									<td class="BOwlexa2">
										&nbsp;:
									</td>
									<td class="BOwlexa2">
										<fmt:formatDate pattern="E" value="<%= new java.util.Date() %>" var="daylabel"/>
										<c:choose>
											<c:when test="${daylabel eq 'Sun' }">
												Minggu
											</c:when>
											<c:when test="${daylabel eq 'Mon' }">
												Senin
											</c:when>
											<c:when test="${daylabel eq 'Tue' }">
												Selasa
											</c:when>
											<c:when test="${daylabel eq 'Wed' }">
												Rabu
											</c:when>
											<c:when test="${daylabel eq 'Thu' }">
												Kamis
											</c:when>
											<c:when test="${daylabel eq 'Fri' }">
												Jumat
											</c:when>
											<c:otherwise>
												Sabtu
											</c:otherwise>
										</c:choose>/<fmt:formatDate  type="date" value="<%= new java.util.Date() %>" pattern="dd-MMM-yyyy"/> 
									</td>
								</tr>
								<tr bordercolor="#FFFFFF">
									<td width="28%" class="BOwlexa2">
										&nbsp;Rumah Sakit
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
										&nbsp;Menempati
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
										&nbsp;Hak kamar
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
										&nbsp;Atas nama pasien
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
										&nbsp;Nama Asuransi
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
										&nbsp;Hubungan keluarga
									</td>
									<td class="BOwlexa2">
										&nbsp;:
									</td>
									<td class="BOwlexa2">
										&nbsp;<b>diri sendiri / suami / istri / anak *) </b>
									</td>
								</tr>
							</table>
						</TD>
					</TR>   
					<tr align="left" valign="middle">
						<TD colspan="2" class="BOwlexa3">
							&nbsp;Menyatakan dengan sesungguhnya bahwa saya&nbsp;:
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
										Menempati&nbsp;:&nbsp;KAMAR KELAS = <input name="text3" type="text" class="inputuserstyle2" value="__________/Rp__________ per hari dengan alasan **)" size="50">
										<br>
										<table>
											<tr>
												<td class="BOwlexa2"><input type="checkbox">Sesuai Plafon Kamar</td>
												<td class="BOwlexa2"><input type="checkbox">Kelas Kamar Tidak Tersedia</td>
												<td class="BOwlexa2"><input type="checkbox">Atas Permintaan Sendiri</td>
											</tr>
											<tr>
												<td class="BOwlexa2"><input type="checkbox">Kelas Kamar Penuh</td>
												<td class="BOwlexa2"><input type="checkbox">Lain-lain : <input name="text3" type="text" class="inputuserstyle2" value="(mohon dijelaskan)" size="50"></td>
											</tr>
										</table>
										Adapun seluruh selisih biaya (kelas, jasa dokter, lab, obat-obatan, radiologi, dll) akan diperhitungkan berdasarkan manfaat inner limit dan menjadi tanggung 
										<br>jawab saya dan akan membayar seluruh selisih biaya tersebut.
									</td>
								</tr>
								<tr bordercolor="#FFFFFF">
									<td width="5%" valign="top" align="right" style="padding:0 10px 0 0;" class="BOwlexa2">
										&nbsp;2.
									</td>
									<td align="justify"  class="BOwlexa2">
										2.	Memberikan kuasa kepada dokter spesialis, dokter umum Rumah Sakit dengan siapa (saya / istri saya/ suami saya/ anak saya )*  
										telah diperiksa atau dirawat untuk memberikan keterangan lengkap mengenai keadaan / penyakit termasuk 
										data medis terdahulu kepada Owlexa Health care  yang telah ditunjuk sebagai pihak ketiga yang sah dari Asuransi/Perusahaan.
									</td>
								</tr>
								<tr bordercolor="#FFFFFF">
									<td width="5%" valign="top" align="right" style="padding:0 10px 0 0;" class="BOwlexa2">
										&nbsp;3.
									</td>
									<td align="justify" class="BOwlexa2">
										3.	Menyadari dan mengetahui bahwa manfaat asuransi kesehatan mempunyai batasan yang telah ditentukan dalam jaminan kesehatan.
									</td>
								</tr>
								<tr bordercolor="#FFFFFF">
									<td width="5%" valign="top" align="right" style="padding:0 10px 0 0;" class="BOwlexa2">
										&nbsp;4.
									</td>
									<td align="justify" class="BOwlexa2">
										Berjanji jika oleh sebab apapun juga, jika biaya rawat inap tidak sesuai dengan manfaat dan menimbulkan selisih biaya (ekses klaim), maka saya berkewajiban 
										untuk membayar selisih biaya tersebut kepada Rumah Sakit pada saat pulang rawat inap atau jika selisih biaya tersebut diberitahukan kemudian hari maka 
										saya bersedia menyelesaikan kewajiban tersebut kepada Rumah Sakit atau Asuransi.
									</td>
								</tr>
								<tr bordercolor="#FFFFFF">
									<td width="5%" valign="top" align="right" style="padding:0 10px 0 0;" class="BOwlexa2">
										&nbsp;5.
									</td>
									<td align="justify" class="BOwlexa2">
										Bersedia mematuhi ketentuan Polis dan jika menurut ketentuan Polis biaya yang terjadi tidak dijamin, maka saya bersedia mendapatkan pelayanan kesehatan 
										dengan status pasien umum.
									</td>
								</tr>
								<tr>
									<td colspan="2" align="justify" class="BOwlexa2">
										<br>
										Demikian surat pernyataan ini saya buat dalam keadaan sadar, tanpa tekanan dan paksaan dari pihak manapun dan dapat dipertanggung   
										jawabkan dengan sebaik-baiknya untuk selanjutnya dapat dipergunakan sebagaimana mestinya.<br>
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
										<br><i>*) Coret yang tidak seusai</i>
										<br><i>*) &#10004; yang seusai</i>
									</td>
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
										<br> 
										Jakarta, <fmt:formatDate type="date" value="<%= new java.util.Date() %>" pattern="dd"/>, <c:out value="${mlabel }"/>, <fmt:formatDate type="date" value="<%= new java.util.Date() %>" pattern="yyyy"/><br>
										Yang menyatakan,
										<br>
										<br>
										<br>													  
										<br>
										<br>
										<input type="text" class="inputuserstyle2" value="(                                     )" size="60">
										<br>
										Materai&nbsp;Rp.&nbsp;6000,- 
									</td>									
								</tr>	
							</table>
						</TD>
					</TR>
				</TBODY>
			  </TABLE>
			  <%@ include file="../owlexaLetterFooterV2.jsp" %>
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