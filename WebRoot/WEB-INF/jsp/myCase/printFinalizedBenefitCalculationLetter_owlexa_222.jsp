<%@page import="com.ametis.cms.datamodel.CaseItem"%>
<%@page import="com.ametis.cms.datamodel.Case"%>
<%@page import="com.ametis.cms.datamodel.MemberBenefit"%>
<%@page import="com.ametis.cms.service.MemberBenefitService"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page contentType="text/html" import="java.util.*" %>

<HTML>
	<HEAD>
		<!-- Owlexa Confirmation Letter -->
		<TITLE><c:out value="${myCase.memberId.currentCardNumber}" />-<c:out value="${myCase.caseNumber}" />-BC</TITLE>
		<% //Owlexa Letter Header Template %>
		<%@ include file="../owlexaLetterHeader.jsp" %>
		<DIV align="center">
			<TABLE id=AutoNumber25 style="BORDER-COLLAPSE: collapse" cellSpacing=0 cellPadding=0 width=776 bgColor=#ffffff border=0>
				<!--DWLayoutTable-->
				<TBODY>
					<tr align="center" bgcolor="#00B0F0" style="background-color:#00B0F0;" height="30px">
						<td class="letterTitle2" colspan="3" valign="middle" style="background-color:#00B0F0;">
							<c:out value="${param.type eq 'finalized'? 'PERHITUNGAN PASIEN PULANG' : 'PERHITUNGAN PENJAMINAN SEMENTARA' }"/>
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
								<td colspan="4" class="HeaderName4" bgcolor="00B0F0"  align="center" width="50%">
									DATA PASIEN
								</td>
							</tr>
							<tr>
								<td width="200px" class="BOwlexa2" style="padding-left: 5px;border: 1px solid #00B0F0;">
									Nama Pasien
								</td>
								<td class="BOwlexa2" width="200px" style="padding-left: 5px;border: 1px solid #00B0F0;">
									<c:out value="${myCase.memberId.firstName }"/>
								</td>
								<td class="BOwlexa2" style="padding-left: 5px;border: 1px solid #00B0F0;">
									Tanggal Masuk RS
								</td>
								<td class="BOwlexa2" width="150px" style="padding-left: 5px;border: 1px solid #00B0F0;">
									<fmt:formatDate value="${myCase.caseStartTime }" pattern="dd-MMM-yyyy"/>
								</td>
							<tr>
								<td class="BOwlexa2" style="padding-left: 5px;border: 1px solid #00B0F0;">
									No. Peserta
								</td>
								<td class="BOwlexa2" style="padding-left: 5px;border: 1px solid #00B0F0;">
									<c:out value="${myCase.memberId.customerNumber}" />
								</td>
								<c:choose>
									<c:when test="${param.type eq 'finalized' }">
										<td class="BOwlexa2" style="padding-left: 5px;border: 1px solid #00B0F0;">
											Tanggal Keluar RS
										</td>
										<td class="BOwlexa2" style="padding-left: 5px;border: 1px solid #00B0F0;">
											<fmt:formatDate value="${myCase.caseEndTime}" pattern="dd-MMM-yyyy"/>
										</td>
									</c:when>
									<c:otherwise>
										<td class="BOwlexa" bgcolor="#cccccc">
										</td>
										<td class="BOwlexa" bgcolor="#cccccc">
										</td>
									</c:otherwise>
								</c:choose>
							</tr>
							<tr>
								<td class="BOwlexa2" style="padding-left: 5px;border: 1px solid #00B0F0;">
									Jenis Kelamin
								</td>
								<td class="BOwlexa2" style="padding-left: 5px;border: 1px solid #00B0F0;">
									<c:out value="${myCase.memberId.gender}" />
								</td>
								<c:choose>
									<c:when test="${param.type eq 'finalized' }">
										<td class="BOwlexa2" style="padding-left: 5px;border: 1px solid #00B0F0;">
											Lama Rawat
										</td>
										<td class="BOwlexa2" style="padding-left: 5px;border: 1px solid #00B0F0;">
											<input type="text" class="inputuserstyle2" value="${myCase.longOfStay } Hari" size="20">
										</td>
									</c:when>
									<c:otherwise>
										<td class="BOwlexa" bgcolor="#cccccc">
										</td>
										<td class="BOwlexa" bgcolor="#cccccc">
										</td>
									</c:otherwise>
								</c:choose>
							</tr>
							<tr>
								<td class="BOwlexa2" style="padding-left: 5px;border: 1px solid #00B0F0;">
									Tanggal Lahir
								</td>
								<td class="BOwlexa2" style="padding-left: 5px;border: 1px solid #00B0F0;">
									<fmt:formatDate value="${myCase.memberId.birthday}" pattern="dd-MMM-yyyy"/>
								</td>
								<td class="BOwlexa2" style="padding-left: 5px;border: 1px solid #00B0F0;">
									Kamar yang Ditempati
								</td>
								<td class="BOwlexa2" style="padding-left: 5px;border: 1px solid #00B0F0;">
									<input type="text" class="inputuserstyle2" value="${myCase.roomAndBoardUsage}" size="20">
								</td>
							</tr>
							<tr>
								<td class="BOwlexa2" style="padding-left: 5px;border: 1px solid #00B0F0;">
									Asuransi/Perusahaan
								</td>
								<td class="BOwlexa2" style="padding-left: 5px;border: 1px solid #00B0F0;" width="200px">
									<c:out value="${myCase.memberId.clientId.clientName}" /> 
									<c:if test="${not empty myCase.memberId.memberGroupId}">
										/ <c:out value="${myCase.memberId.memberGroupId.groupName}" />
									</c:if>
								</td>
								<td class="BOwlexa2" style="padding-left: 5px;border: 1px solid #00B0F0;">
									Diagnosa Medis
								</td>
								<td class="BOwlexa2" style="padding-left: 5px;border: 1px solid #00B0F0;" width="150px">
									<c:out value="${myCase.diagnosis1Id.description}" />
								</td>
							</tr>
						</table>
					</td>
				</tr>
			<tr>
				<td>
					<br>
					<c:if test="${param.type eq 'notfinalized' }">
					<table style="border:  1px solid #00B0F0;border-collapse: collapse; width: 100%">
						<tr>
							<td class="HeaderName4" bgcolor="#00B0F0"  align="center" style="border-right: 1px solid #FFFFFF">
								BENEFIT
							</td>
							<td class="HeaderName4" bgcolor="#00B0F0"  align="center" width="15%" style="border-right: 1px solid #FFFFFF">
								BATASAN BENEFIT
							</td>
							<td class="HeaderName4" bgcolor="#00B0F0"  align="center" width="16%" style="border-right: 1px solid #FFFFFF">
								BIAYA RS
							</td>
							<td class="HeaderName4" bgcolor="#00B0F0"  align="center" width="16%" style="border-right: 1px solid #FFFFFF">
								APPROVAL
							</td>
							<td class="HeaderName4" bgcolor="#00B0F0"  align="center" width="16%" style="border-right: 1px solid #FFFFFF">
								EXCESS CLAIM
							</td>
						</tr>
						<c:forEach items="${claimItemList}" var="claimItem" varStatus="status" >
							<tr>
								<td class="BOwlexa2" style="border: 1px solid #00B0F0">
									&nbsp;<c:out value="${claimItem.itemId.itemName }"></c:out>
								</td>
								<td class="BOwlexa3" style="border: 1px solid #00B0F0">
									&nbsp;
									<c:if test="${not empty claimItem.memberBenefitId }">
									<c:choose>
										<c:when test="${claimItem.claimId.claimTypeId.claimTypeId eq 1 }">
											<c:choose>
												<c:when test="${claimItem.memberBenefitId.reimbursementAsCharge}">
												AS CHARGE
												</c:when>
												<c:otherwise >
													<fmt:formatNumber><c:out value="${claimItem.memberBenefitId.reimbursementBenefitLimit}" /></fmt:formatNumber>
												</c:otherwise>
											</c:choose>
										</c:when>
										<c:otherwise>
											<c:choose>
												<c:when test="${claimItem.memberBenefitId.isAsCharge}">
													AS CHARGE
												</c:when>
												<c:otherwise >
													<fmt:formatNumber><c:out value="${claimItem.memberBenefitId.benefitLimit}" /></fmt:formatNumber>
												</c:otherwise>
											</c:choose>
										</c:otherwise>
									</c:choose>
									</c:if>
								</td>
								<td class="BOwlexa2" align="right" style="border: 1px solid #00B0F0">
									<fmt:formatNumber><c:out value="${claimItem.claimItemValue}" /></fmt:formatNumber>&nbsp;
								</td>
								<td class="BOwlexa2" align="right" style="border: 1px solid #00B0F0">
									<c:choose>
										<c:when test="${not empty claimItem.claimItemCoveredValue }">
											<fmt:formatNumber><c:out value="${claimItem.claimItemCoveredValue}" /></fmt:formatNumber>&nbsp;
										</c:when>
										<c:otherwise>
											<fmt:formatNumber>0</fmt:formatNumber>&nbsp;
										</c:otherwise>
									</c:choose>
								</td>
								<td class="BOwlexa2" align="right" style="border: 1px solid #00B0F0">
									<fmt:formatNumber><c:out value="${claimItem.excessValue}" /></fmt:formatNumber>&nbsp;
								</td>
							</tr>	
						</c:forEach>
						<tr>
							<td class="BOwlexa5" style="border: 1px solid #00B0F0">
								&nbsp;TOTAL
							</td>
							<td class="BOwlexa5" align="center" style="border: 1px solid #00B0F0">
							</td>
							<td class="BOwlexa5" align="right" style="border: 1px solid #00B0F0">
								<fmt:formatNumber ><c:out value="${totalCharge}" /></fmt:formatNumber>&nbsp;
							</td>
							<td class="BOwlexa5" align="right" style="border: 1px solid #00B0F0">
								<fmt:formatNumber > <c:out value="${totalApproved}" /></fmt:formatNumber>&nbsp;
							</td>
							<td class="BOwlexa5" align="right" style="border: 1px solid #00B0F0">
								<fmt:formatNumber ><c:out value="${totalExcess}" /></fmt:formatNumber>&nbsp;
							</td>
						</tr>
					</table>
					</c:if>
					<c:if test="${param.type eq 'finalized' }">
						<table style="border:  1px solid #00B0F0;border-collapse: collapse; width: 100%">
							<tr>
								<td class="HeaderName4" bgcolor="#00B0F0"  align="center" style="border-right: 1px solid #FFFFFF">
									BENEFIT
								</td>
								<td class="HeaderName4" bgcolor="#00B0F0"  align="center" width="15%" style="border-right: 1px solid #FFFFFF">
									BATASAN BENEFIT
								</td>
								<td class="HeaderName4" bgcolor="#00B0F0"  align="center" width="16%" style="border-right: 1px solid #FFFFFF">
									BIAYA RS
								</td>
								<td class="HeaderName4" bgcolor="#00B0F0"  align="center" width="16%" style="border-right: 1px solid #FFFFFF">
									APPROVAL
								</td>
								<td class="HeaderName4" bgcolor="#00B0F0"  align="center" width="16%" style="border-right: 1px solid #FFFFFF">
									EXCESS CLAIM
								</td>
							</tr>
							<c:forEach items="${claimItemList}" var="claimItemfin" varStatus="status" >
								<tr>
									<td class="BOwlexa2" style="border: 1px solid #00B0F0">
										&nbsp;<c:out value="${claimItemfin.itemId.itemName }"></c:out>
									</td>
									<td class="BOwlexa3" style="border: 1px solid #00B0F0">
										&nbsp;
										<c:if test="${not empty claimItemfin.memberBenefitId }">
										<c:choose>
											<c:when test="${claimItemfin.claimId.claimTypeId.claimTypeId eq 1 }">
												<c:choose>
													<c:when test="${claimItemfin.memberBenefitId.reimbursementAsCharge}">
													AS CHARGE
													</c:when>
													<c:otherwise >
														<fmt:formatNumber><c:out value="${claimItemfin.memberBenefitId.reimbursementBenefitLimit}" /></fmt:formatNumber>
													</c:otherwise>
												</c:choose>
											</c:when>
											<c:otherwise>
												<c:choose>
													<c:when test="${claimItemfin.memberBenefitId.isAsCharge}">
														AS CHARGE
													</c:when>
													<c:otherwise >
														<fmt:formatNumber><c:out value="${claimItemfin.memberBenefitId.benefitLimit}" /></fmt:formatNumber>
													</c:otherwise>
												</c:choose>
											</c:otherwise>
										</c:choose>
										</c:if>
									</td>
									<td class="BOwlexa2" align="right" style="border: 1px solid #00B0F0">
										<fmt:formatNumber><c:out value="${claimItemfin.claimItemValue}" /></fmt:formatNumber>&nbsp;
									</td>
									<td class="BOwlexa2" align="right" style="border: 1px solid #00B0F0">
										<fmt:formatNumber><c:out value="${claimItemfin.claimItemApprovedValue}" /></fmt:formatNumber>&nbsp;
									</td>
									<td class="BOwlexa2" align="right" style="border: 1px solid #00B0F0">
										<fmt:formatNumber><c:out value="${claimItemfin.excessValue}" /></fmt:formatNumber>&nbsp;
									</td>
								</tr>	
							</c:forEach>
							<tr>
								<td class="BOwlexa5" style="border: 1px solid #00B0F0">
									&nbsp;TOTAL
								</td>
								<td class="BOwlexa5" align="right" style="border: 1px solid #00B0F0">
								</td>
								<td class="BOwlexa5" align="right" style="border: 1px solid #00B0F0">
									<fmt:formatNumber ><c:out value="${checkClaim.claimChargeValue}" /></fmt:formatNumber>&nbsp;
								</td>
								<td class="BOwlexa5" align="right" style="border: 1px solid #00B0F0">
									<fmt:formatNumber > <c:out value="${checkClaim.claimApprovedValue}" /></fmt:formatNumber>&nbsp;
								</td>
								<td class="BOwlexa5" align="right" style="border: 1px solid #00B0F0">
									<fmt:formatNumber ><c:out value="${checkClaim.claimExcessValue}" /></fmt:formatNumber>&nbsp;
								</td>
							</tr>
						</table>
					</c:if>
				</td>
			</tr>
			<tr>
				<td>
					<br>
					<table style="border:  1px solid #00B0F0;border-collapse: collapse; width: 100%">
						<tr>
							<td class="BOwlexa2">
								<c:if test="${param.type eq 'finalized' }">
									<div contenteditable="true">
										Keterangan : <br>
										&emsp;&emsp;1. &emsp; Excess klaim yang harus dibayar di Rumah Sakit sebesar Rp. <fmt:formatNumber ><c:out value="${param.type eq 'finalized'? checkClaim.claimExcessValue : totalExcess}" /></fmt:formatNumber><br>
										&emsp;&emsp;2. &emsp; Non medis dan food supplement tidak dijamin <br>
										&emsp;&emsp;3. &emsp; Estimasi ini bersifat sementara, jika ada biaya yang belum ditagihkan
										di Rumah Sakit maka akan ditagih kemudian
									</div>
								</c:if>
								<c:if test="${param.type eq 'notfinalized' }">
									<div contenteditable="true">
										Keterangan : <br>
										&emsp;&emsp;1. &emsp; Form ini bukan penjaminan Akhir, hanya untuk memperlihatkan selisih biaya yang sudah terjadi yang harus dibayar di tempat<br>
										&emsp;&emsp;2. &emsp; Mohon selisih biaya yang sudah terjadi dapat diinformasikan ke peserta<br>
										&emsp;&emsp;3. &emsp; Estimasi ini bersifat sementara,  dan dapat berubah sewaktu waktu
									</div>
								</c:if>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<br>
					<table style="BORDER-COLLAPSE: collapse" cellSpacing=0 cellPadding=0 width=776 bgColor=#ffffff border=0>
					<tr>
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
							Jakarta, <fmt:formatDate type="date" value="<%= new java.util.Date() %>" pattern="dd"/>&nbsp; <c:out value="${mlabel }"/>&nbsp; <fmt:formatDate type="date" value="<%= new java.util.Date() %>" pattern="yyyy"/>
						</td>
					</tr>
					<tr align="center">
						<td class="BOwlexa2" width="30%">
						</td>
						<td width="30%"></td>
						<td class="BOwlexa2" align="center">Mengetahui,</td>
					</tr>
					<tr>
						<td class="BOwlexa2">			  
							Nama Petugas,
						</td>
							<td class="BOwlexa2" align="center">Pihak Keluarga,</td>
							<td class="BOwlexa2" align="center">			  
							Pihak Rumah Sakit
						</td>
					</tr>
					<tr>
						<td class="BOwlexa2" colspan="1">
							<br>
							<br>
							<br>
							<br>&nbsp;
						</td>
						<td class="BOwlexa2" colspan="1">
							&nbsp;
						</td>
						<td class="BOwlexa2" colspan="1">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td class="BOwlexa2">
							(<c:out value="${theUser.firstName }"></c:out>)
						</td>
						<td align="center">
							<input type="text" class="inputuserstyle2" value="(Nama Jelas)" size="40" style="text-align: center;">
						</td>
						<td class="BOwlexa2" align="center">
							<input type="text" class="inputuserstyle2" value="(Nama Jelas & Cap RS)" size="40" style="text-align: center;">
						</td>
					</tr>
				</table>
				</td>
			</tr>
				</TBODY>
			</TABLE>
		</DIV>
		<div align="center">
		<br><br>
		<% //Owlexa Letter Footer Template %>
		<%@ include file="../owlexaLetterFooterV2.jsp" %>
		<br/>
		<input type="button" name="button" value="P   R   I   N   T"  size=10 onClick="window.print()" style="background:#EEF2FC;border:1px #6f6f6f solid;color:#6f6f6f;font:10px Arial;font-weight:bold;">
		<!-- 
		<input type="button" name="button2" value="S A V E   A   C O P Y"  size=10 onClick="document.execCommand('SaveAs',null,'<?php echo $sFileName;?>')" style="background:#EEF2FC;border:1px #6f6f6f solid;color:#6f6f6f;font:10px Arial;font-weight:bold;">
		-->
		<br>
		<div class="Details"><c:out value="${myCase.memberId.currentCardNumber}" />-<c:out value="${myCase.caseNumber}" />-BC</div>
	</div>
</HTML>
	</BODY>