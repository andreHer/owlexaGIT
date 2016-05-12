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
							SURAT JAMINAN RAWAT INAP
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
							<table style="border:  1px solid #00B0F0;border-collapse: collapse; width: 100%">
								<tr>
									<td colspan="4" class="HeaderName4" bgcolor="#00B7D5"  align="center" width="50%" style="border-right: 1px solid #FFFFFF">
										INFORMASI PESERTA
									</td>
									<td colspan="2" class="HeaderName4" bgcolor="#00B7D5"  align="center">
										ASURANSI
									</td>
								</tr>
								<tr style="border: 1px solid #00B0F0;">
									<td width="100px" class="BOwlexa2"  style="padding-left: 5px;border: 1px solid #00B0F0;" >
										Nama Pasien
									</td>
									<td colspan="3" width="150px" class="BOwlexa2"  style="padding-left: 5px;border: 1px solid #00B0F0;">
										:&nbsp;<c:out value="${myCase.memberId.firstName}" /> 
									</td>
									<td class="BOwlexa2"  style="padding-left: 5px;border: 1px solid #00B0F0;">
										Payor
									</td>
									<td class="BOwlexa2"  style="padding-left: 5px;">
										<c:out value="${myCase.memberId.clientId.clientName}" />
									</td>
								</tr>
								<tr style="border: 1px solid #00B0F0;">
									<td class="BOwlexa2" style="padding-left: 5px;border: 1px solid #00B0F0;">
										Nama Karyawan
									</td>
									<td colspan="3" class="BOwlexa2" style="padding-left: 5px;border: 1px solid #00B0F0;">
										:&nbsp;<c:out value="${myCase.memberId.parentMember.firstName}" /> 
									</td>
									<td class="BOwlexa2"  style="padding-left: 5px;border: 1px solid #00B0F0;">
										Perusahaan
									</td>
									<td class="BOwlexa2"  style="padding-left: 5px">
										<c:out value="${myCase.memberId.memberGroupId.groupName}" /> 
									</td>
								</tr>
								<tr style="border: 1px solid #00B0F0;">
									<td class="BOwlexa2" style="padding-left: 5px;border: 1px solid #00B0F0;">
										No. Kartu 
									</td>
									<td class="BOwlexa2" style="padding-left: 5px;border: 1px solid #00B0F0;">
										:&nbsp;<c:out value="${myCase.memberId.currentCardNumber}" /> 
									</td>
									<td width="50px" class="BOwlexa2" style="padding-left: 5px;border: 1px solid #00B0F0;">
										Tgl Lahir 
									</td>
									<td width="50px" class="BOwlexa2" style="padding-left: 5px;border: 1px solid #00B0F0;">
										:&nbsp;<c:out value="${myCase.memberId.birthday}" />
									</td>
									<td class="BOwlexa2"  style="padding-left: 5px;border: 1px solid #00B0F0;">
										No. Polis
									</td>
									<td class="BOwlexa2"  style="padding-left: 5px">
										<c:out value="${myCase.memberId.currentPolicyId.policyNumber}" /> 
									</td>
								</tr>
								<tr style="border: 1px solid #00B0F0;">
									<td class="BOwlexa2" style="padding-left: 5px;border: 1px solid #00B0F0;">
										No. Peserta
									</td>
									<td class="BOwlexa2" style="padding-left: 5px;border: 1px solid #00B0F0;">
										:&nbsp;<c:out value="${myCase.memberId.customerNumber}" />
									</td>
									<td class="BOwlexa2" style="padding-left: 5px;border: 1px solid #00B0F0;">
										Sex
									</td>
									<td class="BOwlexa2" style="padding-left: 5px;border: 1px solid #00B0F0;">
										:&nbsp;<c:out value="${myCase.memberId.gender}" />
									</td>
									<td class="BOwlexa2"  style="padding-left: 5px;border: 1px solid #00B0F0;">
										Periode Polis
									</td>
									<td class="BOwlexa2"  style="padding-left: 5px">
										<c:out value="${myCase.memberId.currentPolicyId.effectiveDate}" /> - <c:out value="${myCase.memberId.currentPolicyId.expireDate}" />
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr align="center" valign="top">
						<td>
							<br>
							<table style="border:  1px solid #00B0F0;border-collapse: collapse; width: 100%">
								<tr bordercolor="#6F6F6F" bgcolor="#00B7D5">
									<td colspan="6" align="center" class="HeaderName4">
										INFORMASI PERAWATAN 
									</td>
								</tr>
								<tr>
									<td class="BOwlexa2" style="padding-left: 5px;border: 1px solid #00B0F0;">
										Klaim No 
									</td>
									<td class="BOwlexa2" style="padding-left: 5px;border: 1px solid #00B0F0;">
										<c:out value="${myCase.claimId.claimNumber}" />
									</td>
									<td class="BOwlexa2" style="padding-left: 5px;border: 1px solid #00B0F0;">
										Rumah Sakit
									</td>
									<td colspan="3" class="BOwlexa2" style="padding-left: 5px;border: 1px solid #00B0F0;">
										<c:out value="${myCase.providerId.providerName}" />
									</td>
								</tr>
								<tr>
									<td class="BOwlexa2"  style="padding-left: 5px;border: 1px solid #00B0F0;">
										Tgl. Masuk
									</td>
									<td class="BOwlexa2"  style="padding-left: 5px;border: 1px solid #00B0F0;">
										<c:out value="${myCase.caseStartTime}" />
									</td>
									<td class="BOwlexa2"  style="padding-left: 5px;border: 1px solid #00B0F0;">
										ICD Code 
									</td>
									<td colspan="3" class="BOwlexa2" style="padding-left: 5px;border: 1px solid #00B0F0;">
										<b><c:out value="${myCase.diagnosis1Id.diagnosisCode}" /></b>
										&nbsp;-
										&nbsp;<c:out value="${myCase.diagnosis1Id.description}" />
										&nbsp;<c:if test="${diagnosis1Status eq 'disability'}"><b>(RECURRING DIAGNOSIS)</b></c:if>
										&nbsp;<c:if test="${diag1exstat eq 'exclusion'}"><b>(DIAGNOSIS EXCLUSION)</b></c:if>
									</td>
								</tr>
								<tr>									
									<td class="BOwlexa2"  style="padding-left: 5px;border: 1px solid #00B0F0;">
										Manfaat
									</td>
									<td class="BOwlexa2"  style="padding-left: 5px;border: 1px solid #00B0F0;">
										<c:out value="${myCase.caseCategoryId.caseCategoryName}" />
									</td>
									<td class="BOwlexa2"  style="padding-left: 5px;border: 1px solid #00B0F0;">
										Kamar
									</td>
									<td class="BOwlexa2"  style="padding-left: 5px;border: 1px solid #00B0F0;">
										Rp. <c:out value="${roomBenefit }"/>
									</td>
									<td class="BOwlexa2"  style="padding-left: 5px;border: 1px solid #00B0F0;">
										ICU
									</td>
									<td class="BOwlexa2"  style="padding-left: 5px;border: 1px solid #00B0F0;">
										Rp. 
										<c:choose>
											<c:when test="${icuBenefit.benefitLimit eq -1}">
											AS CHARGE
											</c:when>
											<c:otherwise >
												<c:if test="${not empty icuBenefit.benefitLimit }">
													<fmt:formatNumber><c:out value="${icuBenefit.benefitLimit}" /></fmt:formatNumber>
												</c:if>
											</c:otherwise>
										</c:choose>
									</td>
								</tr>
							</table>
						</td>
					</tr>    
					<tr style="width: 100%">
						<td>
							<div style="padding-left: 15px;padding-right:15px; overflow:hidden;border: 1px solid #00B0F0;" class="BOwlexa2">
								<b>Batasan Benefit:</b>
								<br>
								<div contenteditable="true">
									1. <br>
									2. <br>
									3. 
								</div>
								<b>Mohon segera diinformasikan ke OWLEXA HEALTHCARE dan dijadikan perhatian untuk hal berikut :</b>
								<br>
								<table>
									<tr>
										<td class="BOwlexa2" style="padding-left: 10px">
											1.
										</td>
										<td class="BOwlexa2" style="padding-left: 10px" colspan="2">
											Peserta menempati kelas diatas manfaat yang tercantum diatas
										</td>
									</tr>
									<tr>
										<td class="BOwlexa2" style="padding-left: 10px">
											2.
										</td>
										<td class="BOwlexa2" style="padding-left: 10px" colspan="2">
											Terdapat rencana operasi, CT Scan, MRI, Echocardiogram,  tindakan gigi, perawatan kehamilan, biaya obat dan tindakan dengan harga satuan diatas Rp. 500.000,-
										</td>
									</tr>
									<tr>
										<td class="BOwlexa2" style="padding-left: 10px" valign="top">
											3.
										</td>
										<td class="BOwlexa2" style="padding-left: 10px" colspan="2">
											Indikasi perawatan yang berhubungan dengan check up, kesuburan, permasangan alat bantu/prothesa, psikologi,mental disorder, kelainan kongenital, kosmetik, sircumsisi, tentament suicide, HIV/AIDS, ARC, PHS, olah raga berbahaya, transplantasi organ.
										</td>
									</tr>
									<tr>
										<td class="BOwlexa2" style="padding-left: 10px" valign="top">
											4.
										</td>
										<td class="BOwlexa2" style="padding-left: 10px" colspan="2">
											Biaya non medis atau biaya lainnya yang tidak berhubungan dengan diagnosa ditagihkan langsung kepada peserta. Contoh biaya non medis seperti dibawah ini :
										</td>
									</tr>
									<tr>
										<td>
								 		<td class="BOwlexa2">
								 			<ul>
											 	<li>
											 		<i>Telephone</i>
											 	</li>
											 	<li>
											 		<i>Thermometer</i>
											 	</li>
											 	<li>
											 		<i>Laundry</i>
											 	</li>
											 </ul>
								 		</td>
								 		<td class="BOwlexa2">
								 			<ul>
											 	<li>
											 		<i>Makanan tambahan (susu, kantin)</i>
											 	</li>
											 	<li>
											 		<i>Keperluan toilet (tissue, washlap, sabun dll)</i>
											 	</li>
											 	<li>
											 		<i>Obat bebas(Minyak gosok, minyak telon, balsem, dll)</i>
											 	</li>
											 </ul>
								 		</td>
								 	</tr>
								 	<tr>
								 		<td class="BOwlexa2" style="padding-left: 10px">
								 			5.
								 		</td>
								 		<td class="BOwlexa2" style="padding-left: 10px" valign="top" colspan="2">
								 			Konfirmasi jika peserta akan pulang melalui telepon dan mengirimkan rincian biaya melalui fax No. 021-29830400 atau email : cc.owlexa@lintasarta.co.id 
								 		</td>
								 	</tr>
								</table>
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<table>
								<tr>
									<td class="BOwlexa2" style="padding-left: 5px">
										Demikian kami sampaikan, atas perhatiannya kami ucapkan terimakasih.
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr align="center" valign="top">
						<TD height="155" colspan="2">
							<br>
							<table width="95%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td width="60%"></td>
									<td class="BOwlexa2">			  
										<br> 
										Hormat kami, <br>
										<br>
										<img src="images/owlexa/ma_citra.png"/>
										<br>
										<input type="text" class="inputuserstyle2" value="dr. Citra Andiani P" size="40"><br>
										<%-- <c:out value="${theUser.firstName}" />&nbsp;<c:out value="${theUser.lastName}" />  --%>
										<input type="text" class="inputuserstyle2" value="(Medical Advisor)" size="40">
									</td>
								</tr>	  
							</table>
							<br/>
						</TD>
					</TR>
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