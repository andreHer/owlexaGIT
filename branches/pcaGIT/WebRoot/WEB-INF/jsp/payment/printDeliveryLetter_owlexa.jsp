<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page contentType="text/html" import="java.util.*" %>
<script type="text/javascript" src="scripts/jquery/jquery-1.4.4.min.js"></script>
<HTML>
	<HEAD>
		<!-- Owlexa Confirmation Letter -->
		<TITLE><c:out value="${payment.paymentNumber}" />-SJALAN</TITLE>
		<META http-equiv=Content-Type content="text/html; charset=iso-8859-1">
		<META content="Owlexa Healthcare" name=description>
		<META content="Insurances, healthcare and more..." name=keywords>
		<META content="Insurances, healthcare and more..." name=key-phrases>
		<META content="Insurances, healthcare and more..." name=category>
		<META content=www.owlexa.co.id name=owlexa>
		<style type="text/css">
			@font-face {
			   font-family: DinLight;
			   src: url(css/fonts/FF_DIN_Light.otf) format("opentype");
			}
			
			@font-face {
			   font-family: DinRegular;
			   src: url(css/fonts/FF_DIN_Regular.otf) format("opentype");
			}
			
			@font-face {
			   font-family: DinBold;
			   src: url(css/fonts/FF_DIN_Bold.otf) format("opentype");
			}
			
			body{
				font-family:DinLight,Verdana;
				font-size:12px;
			}
			
			.letterTitle {
				font-family: DinBold, Arial, Helvetica, sans-serif;
				font-size: 24px;
				color: rgb(109,110,112);
			}
			
			<!--
			@font-face {
			   font-family: DinLight;
			   src: url(css/fonts/FF_DIN_Light.otf) format("opentype");
			}

			@font-face {
			   font-family: DinRegular;
			   src: url(css/fonts/FF_DIN_Regular.otf) format("opentype");
			}

			@font-face {
			   font-family: DinBold;
			   src: url(css/fonts/FF_DIN_Bold.otf) format("opentype");
			}

			body{
				font-family:DinLight,Verdana;
				font-size:12px;
			}

			.letterTitle {
				font-family: DinBold, Arial, Helvetica, sans-serif;
				font-size: 24px;
				color: rgb(109,110,112);
			}

			.style1 {
				font-family: DinLight,Geneva, Arial, Helvetica, sans-serif;
				font-size: 18px;
				font-weight: bold;
				color: #6f6f6f;
			}
			.HeaderName {font-family: DinLight,Verdana, Arial, Helvetica, sans-serif; font-size: 10px; font-weight: bold; color:#6f6f6f;}
			.Details {font-family: DinLight,Verdana, Arial, Helvetica, sans-serif; font-size: 10px; color: #6f6f6f; }
			.DetailsRed {font-family: DinLight,Verdana, Arial, Helvetica, sans-serif; font-size: 10px; color: #FF0000; }
			.style43 {font-family: DinLight,Verdana, Arial, Helvetica, sans-serif; font-size: 11px; font-weight: bold; color: #FFFFFF; }
			.AssessStyleHeader {
				font-size: 9;
				color: #FFFF00;
			}
			.AssessStyleD {font-family:DinLight,Verdana ; font-size: 9px; color: #305CB8; }
			.BOwlexa {
				font-family: DinLight,Verdana, Arial, Helvetica, sans-serif;
				font-size: 12px;
				font-weight: bold;
				color: #6f6f6f;
			}
			.BOwlexaHeader {
				font-family: DinLight,Verdana, Arial, Helvetica, sans-serif;
				font-size: 16px;
				font-weight: bold;
				color: #262626;
			}
			.style55 {
				font-size: 9px;
				font-family: DinLight,Verdana;
				color: #6f6f6f;
			}
			.style58 {
				font-size: 11px;
				font-weight: bold;
			}
			.style59 {color: #6f6f6f}
			.style60 {font-family: DinLight,Verdana; font-size: 8px;}
			.style72 {font-family: DinLight,Geneva, Arial, Helvetica, sans-serif; font-size: 24px; font-weight: bold; color: #6f6f6f; }
			.style73 {font-family: DinLight,Verdana}
			.style75 {font-family: DinLight,Geneva, Arial, Helvetica, sans-serif; font-size: 14px; font-weight: bold; color: #6f6f6f; }
			
			.inputuserstyle{
		    	background-color: #FFFFFF; color: #6f6f6f; border:0px #a8c0f0 solid; rgb(0,0,0);font:13px DinLight;
		    	line-height:5px;
		    }
		    
			-->
			
		</style>
	</head>
	<BODY link=#000000 vLink=#000000 aLink=#000000 leftMargin=0 bottomMargin=0 marginwidth="0" topmargin="0">
		<%@ include file="../owlexaLetterHeader.jsp" %>
		<DIV align="center">
			<TABLE id=AutoNumber25 style="BORDER-COLLAPSE: collapse" cellSpacing=0 cellPadding=0 width=776 bgColor=#ffffff border=0>
				<!--DWLayoutTable-->
				<TBODY>
					<tr align="center">
						<td class="letterTitle" width="30px" height="30" align="center" colspan="3">
							<br style="line-height:10px;"/>
							<div  style="background-color: #cccccc;">SURAT JALAN : Klaim Rawat Inap/Rawat Jalan/Reimbursment</div>
						</td>
					</tr>
				</TBODY>
			  </TABLE>
		</DIV>
		<div align="center">
			<br/><br/><br/>
			<table style="BORDER-COLLAPSE: collapse" cellSpacing=0 cellPadding=0 width=776 bgColor=#ffffff border=0>	
				<tr>
					<td class="BOwlexa">
						Nomor Surat Jalan Asuransi
					</td>
					<td class="BOwlexa">
						: <c:out value="${payment.paymentNumber }"></c:out>
					</td>
				</tr>
				<tr align="left">
					<td class="BOwlexa" width="300">
						Nomor Batch
					</td>
					<td class="BOwlexa">
						: <c:out value="${payment.batchClaim.batchClaimNumber }"></c:out><br>
					</td>
				</tr>
				<tr>
					<td class="BOwlexa">
						Nomor Invoice
					</td>
					<td class="BOwlexa">
						: <c:out value="${payment.batchClaim.invoiceNumber }"></c:out>
					</td>
				</tr>
				<tr>
					<td class="BOwlexa">
						Kode Provider
					</td>
					<td class="BOwlexa">
						: <c:out value="${batchObj.providerId.providerCode }"/>
					</td>
				</tr>
				<tr>
					<td class="BOwlexa">
						Tanggal Terima Klaim<br>
					</td>
					<td class="BOwlexa">
						: <fmt:formatDate value="${batchObj.batchClaimDate }" pattern="dd-MMM-yyyy"/><br>
					</td>
				</tr>
				<tr>
					<td class="BOwlexa">
						Tanggal Kirim Klaim <br>
					</td>
					<td class="BOwlexa">
						<%--: <fmt:formatDate type="date" value="<%=new java.util.Date() %>" pattern="dd-MMM-yyyy"/> --%>
						: <fmt:formatDate type="date" value="${payment.paymentTime }" pattern="dd-MMM-yyyy"/>
					</td>
				</tr>
				<tr align="left">
					<td class="BOwlexa">
						<br>
						Kepada Yth, <br>
						<br>
						<c:out value="${batchObj.clientId.clientName }"/> <br>
						<br>
						UP : <input name="text2" type="text" class="inputuserstyle" value="<c:out value="${contactPerson.name}"/>" size="50"/> <br />
						<input name="text3" type="text" class="inputuserstyle" value="<c:out value="${batchObj.clientId.address}"/>" size="50"/>
					</td>
				</tr>
			</table>
		</div>
		<div align="center">
		<br/><br/>
			<table style="BORDER-COLLAPSE: collapse" cellSpacing=0 cellPadding=0 width=776>
				<tr>
					<td width="200px" class="BOwlexa">
						<table width="98%" border="1" cellpadding="0" cellspacing="0">
							<tr align="center" bgcolor="#cccccc">
								<td class="BOwlexaHeader" colspan="4">
									KLAIM : <c:choose>
												<c:when test="${batchObj.paymentRecipient.paymentRecipientId eq 3}">
													<c:out value="${batchObj.providerId.providerName}" />
												</c:when>
												<c:when test="${batchObj.paymentRecipient.paymentRecipientId eq 2}">
													<c:out value="${batchObj.memberId.firstName}" />
												</c:when>
												<c:otherwise>
													<c:out value="${batchObj.memberGroupId.groupName}" />
												</c:otherwise>
											</c:choose><br>
									Total Tagihan
								</td>
							</tr>
															
							<tr>
								<td class="BOwlexa" align="center" width="50px">
									NO.
								</td>
								<td class="BOwlexa" width="400px" align="center">
									CORPORATE NAME
								</td>
								<td class="BOwlexa" align="center">
									JUMLAH CLAIMS
								</td>
								<td class="BOwlexa" width="150px" align="center">
									TOTAL CLAIMS<br>
									DIBAYAR ASURANSI
								</td>
							</tr>
							<c:set var="totalClaim" value="0"/>
							<c:set var="countClaim" value="0"/>
							<c:set var="claimValue"/>
							<% int i = 0;
								Double totalClaim = 0.0;
								Double totalClaimPerGroup = 0.0;
								Long countClaim = 0L;
								Collection<Object[]> claimCollection = (Collection<Object[]>) request.getAttribute("claimCollection");
								if (claimCollection != null && claimCollection.size() > 0){
									Iterator<Object[]> claimIterator = claimCollection.iterator();
									if (claimIterator != null){
										while (claimIterator.hasNext()){
										Object[] claim = claimIterator.next();
										//totalClaim += (Double)claim[2];
										countClaim += (Long)claim[6];
										if(claim[8]!=null)
											totalClaim += (Double)claim[8];
							 %>
											<tr>
												<td class="BOwlexa" align="center">
													<c:out value="<%= ++i %>"/>
												</td>
												<td class="BOwlexa">
													&nbsp;<c:out value="<%= (String)claim[1] %>" />
												</td>
												<td class="BOwlexa" align="center">
													<c:out value="<%= (Long)claim[6] %>"/>
												</td>
												<td class="BOwlexa" align="right">
													&nbsp;
													<fmt:formatNumber><c:out value="<%= (Double)claim[8] %>"/></fmt:formatNumber>&nbsp;
												</td>
											</tr>
							<%
										}
									}
								}
							 %>
							 <tr>
							 	<td/>
							 	<td class="BOwlexa" align="right">
							 		TOTAL&nbsp;
							 	</td>
							 	<td class="BOwlexa" align="center">
							 		<c:out value="<%=countClaim %>"/>
							 		<%--<c:out value="${countClaim }"/> --%>
							 	</td>
							 	<td class="BOwlexa" align="right">
							 		<%--&nbsp;<fmt:formatNumber><c:out value="<%=totalClaim %>"/></fmt:formatNumber> --%>
							 		&nbsp;&nbsp;<fmt:formatNumber><c:out value="<%=totalClaim %>"/> </fmt:formatNumber>&nbsp;
							 	</td>
							 </tr>
							 <tr>
							 	<td/>
							 	<td class="BOwlexa" align="right">
							 		JUMLAH CLAIM PENDING DAN REJECT&nbsp;
							 	</td>
							 	<td class="BOwlexa" align="center">
							 		<c:out value="${totPendingAndReject}" />
							 		<%--<c:out value="${countClaim }"/> --%>
							 </td>
							 <td/>
							 </tr>
						</table>
					</td>
				</tr>
			</table>
			<br><br>
			<br>
			<table style="BORDER-COLLAPSE: collapse" cellSpacing=0 cellPadding=0 width=776>
				<tr>
					<td class="BOwlexa">
						REMARKS : <br>
						<textarea id="remarkTxt" name="remarkTxt" rows="8" style="border:1px solid black;width:100%;overflow:hidden;" class="BOwlexa"><c:out value="${payment.remarks }"></c:out></textarea> 
					</td>
				</tr>
				<!-- <tr>
					<td style="border: 1px solid black;">
						
					<td>
				</tr>
				 -->
			</table>
			<br>
			<table style="BORDER-COLLAPSE: collapse" cellSpacing=0 cellPadding=0 width=776 bgColor=#ffffff border=0>
				<tr>
					<td class="BOwlexa" width="500px">			  
						Penerima,
						<br>
						<br>
						<br>
						<br>
						<br>
						(<input name="text2" type="text" class="inputuserstyle" value="" size="25"/>)
						<br><c:out value="${batchObj.clientId.clientName }"/>
					</td>
					<td class="BOwlexa" align="left">			  
						Hormat kami,
						<br>
						<br>
						<br>
						<br>
						<br>
						(<input name="text2" type="text" class="inputuserstyle" value="" size="25"/>)
						<br>PT. Aplikanusa Lintasarta
					</td>
				</tr>
				<tr>
					<td class="BOwlexa" colspan="2">
						<br>
						Surat ini diterbitkan secara otomatis, tanda tangan petugas tidak diperlukan<br>
					</td>
				</tr>
				<tr align="center" valign="top">
					<TD height="155" colspan="2">
						<br>
						<%@ include file="../owlexaLetterFooter.jsp" %>
						<br/>
						<input type="button" name="button" value="P   R   I   N   T"  size=10 onClick="window.print()" style="background:#EEF2FC;border:1px #6f6f6f solid;color:#6f6f6f;font:10px Arial;font-weight:bold;">
						<!-- 
						<input type="button" name="button2" value="S A V E   A   C O P Y"  size=10 onClick="document.execCommand('SaveAs',null,'<?php echo $sFileName;?>')" style="background:#EEF2FC;border:1px #6f6f6f solid;color:#6f6f6f;font:10px Arial;font-weight:bold;">
						-->
						<br>
						<div class="Details"><c:out value="${payment.paymentNumber}" />-SJALAN</div>
					</TD>
				</tr>
			</table>
		</div>
		<div align="center">
		<%--@ include file="../owlexaLetterFooter.jsp" --%>
	</div>
</HTML>
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