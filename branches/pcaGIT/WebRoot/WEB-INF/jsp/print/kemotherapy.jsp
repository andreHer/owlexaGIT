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
			<th colspan="2">RESUME MEDIS KEMOTHERAPY</th>
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
		</tr>		<tr>
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
				Mohon informasi anamnesa, keluhan dan kondisi pasien saat ini?
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
				Mohon penjelasan untuk khemotherapy:<br />
				a. Jenis obat khemotherapy beserta cara pemberiannya?<br /><br />
				b. Lama pemberian untuk sekali khemotherapy?<br /><br />
				c. Interval waktu untuk masing khemotherapy?<br /><br />
				d. Berapa kali total kemotherapy yang dibutuhkan utnuk pasien ini?<br /><br />
			</td>
		</tr>
		<tr>
			<td colspan="2">
				Apakah kemotherapy saat ini dapat dilakukan rawat jalan? ( Ya / Tidak )<br />
				Mohon penjelasan: <br />
			</td>
		</tr>
		<tr>
			<td colspan="2">
				Apakah untuk kemotherapy selanjutnya diperlukan rawat inap? ( Ya / Tidak )<br />
				Mohon penjelasan: <br />
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