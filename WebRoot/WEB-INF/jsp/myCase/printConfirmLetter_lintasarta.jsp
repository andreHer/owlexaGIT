<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<html>
<head>
	<link href="css/print.css" rel="stylesheet" type="text/css" />
</head>
<body>
<center>
<table class="tabDetailView" border="0" cellpadding="0" cellspacing="0" width="80%" >
  <tbody>    
		<tr>
	      <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;">
	      &nbsp;&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;"></td>
	      <td class="tabDetailViewDF" align="right" valign="top" width="15%" style="border: 0px solid #000; font-size: 12px;">
	      </td>	
	    </tr>		
	    
		
	</tbody>
</table>
</center>
<br />
<br />
<br />
<br />
<br />
<br />
<div id="content" style="padding-left: 10px;">
	<table class="box" >
		<tr>
			<th colspan="2">KONFIRMASI MEDIS GENERAL</th>
		</tr>
		<tr>
			<th colspan="2">
				HARAP DIISI DENGAN LENGKAP<br />
				(UNTUK PROSES PENJAMINAN)
			</th>
		</tr>
		<tr>
			<td>
				To
			</td>
			<td>
				<c:out value="${myCase.providerId.providerName}" /> 
			</td>
		</tr>
		<tr>
			<td>				
				Up
			</td>
			<td>				
				From : PT. APLIKANUSA LINTASARTA
			</td>
		</tr>
		<tr>
			<td >
				Nama Pasien : 
			</td>
			<td >
				<c:out value="${myCase.memberId.firstName}" /> 
			</td>
		</tr>
		<tr>
			<td >
				Jenis Kelamin : 
			</td>
			<td >
				<c:out value="${myCase.memberId.gender}" />
			</td>
		</tr>
		<tr>
			<td >
				Tanggal Lahir / Usia : 
			</td>
			<td >
				<c:out value="${myCase.memberId.birthday}" />
			</td>
		</tr>
		<tr>
			<td >
				Nomor Peserta / PT : 
			</td>
			<td >
				<c:out value="${myCase.memberId.customerNumber}" />
			</td>
		</tr>
		<tr>
			<td >
				Nama Karyawan : 
			</td>
			<td >
				<c:out value="${myCase.memberId.firstName}" />
			</td>
		</tr>
		<tr>
			<td>
				Plan IP / Kelas Kamar : 
			</td>
			<td >
				<c:out value="${myCase.roomAndBoard}" />
			</td>
		</tr>
		<tr>
			<td>
				Tanggal Masuk : 
			</td>
			<td >
				<c:out value="${myCase.caseStartTime}" />
			</td>
		</tr>
		<tr>
			<td > 
				Diagnosa Awal : 
			</td>
			<td >
				<c:out value="${myCase.initialDiagnosis}" />
			</td>
		</tr>
		<tr>
			<td colspan="2">
				Mohon informasi anamnesa, keluhan dan kondisi pasien saat ini?
				<br />
				<br />
				<br />
				<br />
				<br />
				<br />
				
			</td>
		</tr>
		<tr>
			<td>
				Kesadaran:<br />
				Tekanan Darah:
			</td>
			<td>
				Nadi:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Pernapasan:<br />				
				Suhu:
			</td>
		</tr>
		<tr>
			<td colspan="2">
				Apakah etiologi berhubungan dengan:<br />
				a. Gangguan Menstrual&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;: ( Ya / Tidak )<br />
				b. Hormonal&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;: ( Ya / Tidak )<br />
				c. Kesuburan / Infertilitas&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;: ( Ya / Tidak )<br />
				d. Lain-lain, untuk dijelaskan:
			</td>
		</tr>
		<tr>
			<td colspan="2">
				1. Diagnosa Medis yang ditemukan apa saja: <br />
				<br />
				2. Apakah pasien sudah mempunyai keturunan? <br />
				<br />
				3. Mohon penjelasan Etiologi penyakit saat ini <br />
				<br />
				<br />
			</td>
		</tr>
		<tr>
			<td colspan="2">
				Rencana Tindakan Selanjutnya apa ?<br />
				<br />
				Rencana Operasi pada saat ini : <br />
				Jenis Operasi:&nbsp;&nbsp;&nbsp;&nbsp;( ) Efektif&nbsp;&nbsp;( ) Cito<br />
				Golongan Operasi:&nbsp;&nbsp;&nbsp;&nbsp;( ) Kecil&nbsp;&nbsp;( ) Sedang&nbsp;&nbsp;( ) Besar&nbsp;&nbsp;( ) Kompleks<br />
				Jenis Narkose:&nbsp;&nbsp;&nbsp;&nbsp;( ) Lokal&nbsp;&nbsp;( ) Umum&nbsp;&nbsp;( ) Epidural
			</td>
		</tr>
		<tr>
			<td colspan="2">
				Mohon informasi, hasil pemeriksaan penunjang Laboratorium / Radiologi / dll:<br />
			</td>
		</tr>
		<tr>
			<td colspan="2">
				Therapi yang diberikan:<br />
				IVFD:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Injeksi:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Oral:<br />
				<br />
				Perkiraan Lama Perawatan:
			</td>
		</tr>
		<tr>
			<td colspan="2">
				PERNYATAAN DOKTER:<br />
				<em>Dengan ini menyatakan bahwa semua jawaban di atas adalah benar dan lengkap menurut pengetahuan dan keyakinan saya.</em>
			</td>
		</tr>
		<tr>
			<td>
				Hormat Kami<br />
				<br />
				<br />
				<br />
				dr. Frans Dennis Martogi<br />
				Medical Advisor
			</td>
			<td class="right">
				Dokter yang merawat<br />
				<br />
				<br />
				<br />
				........................................<br />
				Stempel RS &amp; Tanda tangan &amp; Nama Jelas
			</td>
		</tr>
		<tr>
			<td class="center" colspan="2">
				
			</td>
		</tr>
	</table>
</div>
<div id="footer">
	
</div>
</body>
</html>