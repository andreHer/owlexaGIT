<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<html>
<head>
	<link href="css/print.css" rel="stylesheet" type="text/css" />
</head>
<body>

<div id="content">
	<table class="box">
		<tr>
			<th colspan="2">KONFIRMASI MEDIS LANJUTAN</th>
		</tr>
		<tr>
			<th colspan="2">
				HARAP DIISI DENGAN LENGKAP<br />
				(UNTUK PROSES PENJAMINAN) MAX: 24 JAM SETELAH DITERIMA
			</th>
		</tr>
		<tr>
			<td>
				To
			</td>
			<td>
				<c:out value="${caseInvestigation.investigationDate}" />
			</td>
		</tr>
		<tr>
			<td>
				<c:out value="${caseInvestigation.caseId.providerId.providerName}" /> <br />
				Up
			</td>
			<td>
				No. Fax :  <c:out value="${caseInvestigation.caseId.providerId.faximile}" /> <br />
				From : Aplikanusa Lintasarta
			</td>
		</tr>
		<tr>
			<td >
				Nama Pasien:  
			</td>
			<td >
				<c:out value="${caseInvestigation.caseId.memberId.firstName}" /> 
			</td>
		</tr>
		<tr>
			<td >
				Jenis Kelamin: 
			</td>
			<td >
				<c:out value="${caseInvestigation.caseId.memberId.gender}" />
			</td>
		</tr>
		<tr>
			<td >
				Tanggal Lahir / Usia: 
			</td>
			<td >
				<c:out value="${caseInvestigation.caseId.memberId.birthday}" />
			</td>
		</tr>
		<tr>
			<td >
				Nomor Peserta / PT: 
			</td>
			<td >
				<c:out value="${caseInvestigation.caseId.memberId.customerNumber}" />
			</td>
		</tr>
		<tr>
			<td >
				Nama Karyawan: 
			</td>
			<td >
				<c:out value="${caseInvestigation.caseId.memberId.firstName}" /> 
			</td>
		</tr>
		<tr>
			<td >
				Plan IP / Kelas Kamar
			</td>
			<td >
				<c:out value="${caseInvestigation.caseId.roomAndBoard}" />
			</td>
		</tr>
		<tr>
			<td >
				Tanggal Masuk
			</td>
			<td >
				<c:out value="${caseInvestigation.caseId.caseStartTime}" />
			</td>
		</tr>
		<tr>
			<td >
				Diagnosa Awal
			</td>
			<td >
				<c:out value="${caseInvestigation.caseId.initialDiagnosis}" />
			</td>
		</tr>
		<tr>
			<td colspan="2">
				Terima kasih atas perawatan yang telah dokter lakukan ke pasien tersebut. Saat ini, kami memerlukan informasi tambahan dari dokter untuk perpanjangan perawatan<br />
			</td>
		</tr>
		<tr>
			<td colspan="2">
				Diagnosa lain yang ditemukan saat perawatan selain: <br />
				1.<br />
				2.<br />
				3.<br />
				<br />
				Mohon penjelasan etiologi penyakit pasien saat ini:
				<br />
			</td>
		</tr>
		<tr>
			<td colspan="2">
				Saat ini sudah dilakukan <c:out value="${caseInvestigation.caseId.longOfStay}" /> hari. Setelah dirawat, apakah menurut dokter: <br />
				<ul>
					<li>Pasien bisa rawat jalan?&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ( Ya / Tidak )</li>
					<li>Jika Tidak, apakah atas permintaan pasien?&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ( Ya / Tidak )</li>
					<li>Pasien Masih perlu dilakukan perpanjangan rawat inap?&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ( Ya / Tidak )</li>
				</ul>			
				<br />
				Masih memerlukan perawatan: ................ hari<br />
				Untuk dijelaskan indikasi kuat untuk dilakukan perpanjangan perawatan:<br />
				<br />
				<br />				
			</td>
		</tr>
		<tr>
			<td colspan="2">
				Rencana Tindakan Selanjutnya: <br />				
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
				Dr. Uchfa<br />
				Medical Advisor
			</td>
			<td class="right">
				Dokter yang merawat<br />
				<br />
				........................................<br />
				Stempel RS &amp; Tanda tangan &amp; Nama Jelas
			</td>
		</tr>
		<tr>
			<td class="center" colspan="2">
				Penting: Mohon diisi lengkap, Setelah diisi, untuk segera di fax kembali secepatnya <br />
				(021-7205662 / 72781777), Hub 082817029173 / 082817029172<br />
				untuk proses jaminan !!
			</td>
		</tr>
	</table>
</div>
<div id="footer">
	
</div>
</body>
</html>