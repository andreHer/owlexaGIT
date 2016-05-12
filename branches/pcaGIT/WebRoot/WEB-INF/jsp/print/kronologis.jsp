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
			<th colspan="2">FORM KRONOLOGIS</th>
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
				Mohon jelaskan urutan pada saat kejadiannya:
				<br />
				<br />
				<br />
				<br />
				<br />
				<br />
				<br />
				<br />
			</td>
		</tr>
		<tr>
			<td colspan="2">
				Apakah peserta sebagai pengendara?&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ( YA / TIDAK )<br />
				Bila YA, apakah ada SIM?&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ( YA / TIDAK )<br />
				Bila YA, mohon fax fotocopy SIM ke 021-7205662<br />
				Bila TIDAK ada SIM, mohon penjelasannya:
				<br />
				<br />
				<br />
			</td>
		</tr>
		<tr>
			<td colspan="2">
				Mohon jelaskan bagian tubuh yang terkena cedera:<br />
				<br />
				<br />
				<br />
			</td>
		</tr>
		<tr>
			<td colspan="2">
				PERNYATAAN DOKTER / PESERTA:<br />
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
				Tanda tangan &amp; Nama Jelas
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