<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page contentType="text/html" import="java.util.*" %>

<HTML>
	<HEAD>
		<!-- Owlexa Confirmation Letter -->
		<TITLE><c:out value="${clientNumber}" />-SJALANGLOBAL</TITLE>
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
			.BOwlexaHeader2 {
				font-family: DinLight,Verdana, Arial, Helvetica, sans-serif;
				font-size: 12px;
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
		    .inputuserstyle{
		    	background-color: #FFFFFF; color: #6f6f6f; border:0px #a8c0f0 solid; rgb(0,0,0);
		    	line-height:5px;font-family: DinLight;
				font-size: 12px;
				font-weight: bold;
		    }
		    
			-->
			
		</style>
	</head>
	<BODY link=#000000 vLink=#000000 aLink=#000000 leftMargin=0 bottomMargin=0 marginwidth="0" topmargin="0">
		<%@ include file="../owlexaLetterHeader.jsp" %>
		<DIV align="center">
			<TABLE id=AutoNumber25 style="BORDER-COLLAPSE: collapse" cellSpacing=0 cellPadding=0 width=800 bgColor=#ffffff border=0>
				<!--DWLayoutTable-->
				<TBODY>
					<tr align="center">
						<td class="letterTitle" width="30px" height="30" align="center" colspan="3">
							<br style="line-height:10px;"/>
							<div  style="background-color: #cccccc;">PENGIRIMAN DOKUMEN KLAIM ASURANSI <br> <c:out value="${clientText }"/> </div>
						</td>
					</tr>
				</TBODY>
			  </TABLE>
		</DIV>
		<div align="center">
			<br/><br/><br/>
			<table style="BORDER-COLLAPSE: collapse" cellSpacing=0 cellPadding=0 width=776 bgColor=#ffffff border=0>	
				<tr align="left">
					<td class="BOwlexa"  width="300">
						Penanggung Jawab di Pihak Client
					</td>
					<td class="BOwlexa">
						: <input name="text2" type="text" class="inputuserstyle" value="${contactPerson.name }" size="35"/>
					</td>
				</tr>
				
				<tr>
					<td class="BOwlexa">
						Tanggal Kirim Klaim <br>
					</td>
					<td class="BOwlexa">
						<%--: <fmt:formatDate type="date" value="<%=new java.util.Date() %>" pattern="dd-MMM-yyyy"/> --%>
						: <fmt:formatDate type="date" value="<%= new Date() %>" pattern="dd-MMM-yyyy"/>
					</td>
				</tr>
				<%--<tr align="left">
					<td class="BOwlexa">
						<br>
						Kepada Yth, <br>
						<br>
						<c:out value="${batchObj.clientId.clientName }"/> <br>
						<br>
						UP : <input name="text2" type="text" class="inputuserstyle" value="........." size="35"/>
					</td>
				</tr>
				 --%>
			</table>
		</div>
		<div align="center">
		<br/><br/>
			<table style="BORDER-COLLAPSE: collapse" cellSpacing=0 cellPadding=0 width=800>
				<tr>
					<td width="200px" class="BOwlexa">
						<table width="100%" border="1" cellpadding="0" cellspacing="0">
							<tr bgcolor="#cccccc">
								<td class="BOwlexaHeader2" align="center" width="25px">
									&nbsp;NO.
								</td>
								<td class="BOwlexaHeader2">
									&nbsp;HOSPITAL
								</td>
								<td class="BOwlexaHeader2">
									&nbsp;INVOICE
								</td>
								<td class="BOwlexaHeader2">
									PAYMENT NUMBER
								</td>
								<td class="BOwlexaHeader2">
									DATE OF RECEIVED
								</td>
								<td class="BOwlexaHeader2">
									DATE OF SUBMISSION
								</td>
								<td class="BOwlexaHeader2">
									TOTAL CLAIM
								</td>
								<td class="BOwlexaHeader2">
									TOTAL PAYMENT
								</td>
								<td class="BOwlexaHeader2">
									&nbsp;MODIFIED
								</td>
								<td class="BOwlexaHeader2">
									&nbsp;CREATED
								</td>
								<td class="BOwlexaHeader2">
									&nbsp;STATUS
								</td>
							</tr>
							<% int i = 0; %>
								<c:set var="claimTotal" value="0"/>
								<c:set var="paymentTotal" value="0"/>
								<c:forEach items="${paymentCollection}" var="payment" varStatus="status" >
								<%	
									i++;
								%>
								 <tr height="20">
							     	<td class="Details"  nowrap="nowrap" width="20px" align="center"><%=i%></td>
							     	<td class="Details">
							     		<c:choose>
							     			<c:when test="${empty payment[1] }">
							     				NPC
							     			</c:when>
							     			<c:otherwise>
							     				<c:out value="${payment[1] }"/>
							     			</c:otherwise>
							     		</c:choose>
							  		</td>
									<td class="Details"  nowrap="nowrap">
								  		&nbsp;<c:out value="${payment[2] }"/>
									</td>
									<td class="Details"  nowrap="nowrap">	
									  	&nbsp;<c:out value="${payment[3] }"/>
									</td>
									<td class="Details"  nowrap="nowrap">
										&nbsp;<fmt:formatDate value="${payment[4] }" pattern="dd-MMM-yyyy"/>
									</td>
									<td class="Details"  nowrap="nowrap">	
										&nbsp;<fmt:formatDate value="${payment[5] }" pattern="dd-MMM-yyyy"/>
									</td>	
									<td class="Details"  nowrap="nowrap" align="right">
										&nbsp;<c:out value="${payment[6] }"/>&nbsp;
										<c:set var="claimTotal" value="${claimTotal + payment[6] }"/>
									</td>
									<td class="Details"  nowrap="nowrap" align="right">
										&nbsp;<fmt:formatNumber><c:out value="${payment[7] }"/></fmt:formatNumber>&nbsp;
										<c:set var="paymentTotal" value="${paymentTotal + payment[7] }"/>
									</td>
									<td class="Details"  nowrap="nowrap">	
										&nbsp;<c:out value="${payment[8] }"/>	
									</td>	
									<td class="Details"  nowrap="nowrap">	
										&nbsp;<c:out value="${payment[10] }"/>	
									</td>
									<td class="Details"  nowrap="nowrap">	
										<c:choose>
											<c:when test="${payment[14].claimTypeId eq 1 }">
												&nbsp;REIMBURSMENT
											</c:when>
											<c:when test="${payment[14].claimTypeId eq 2 }">
												&nbsp;CASHLESS
											</c:when>
											<c:otherwise>
												&nbsp;REIMBURSMENT KHUSUS
											</c:otherwise>
										</c:choose>
										<%-- <c:out value="${payment[10] }"/> --%>	
									</td>	   		   		   		   		
							    </tr>
								
								</c:forEach>
								<tr>
									<td colspan="5"></td>
									<td class="Details"  nowrap="nowrap" align="right">TOTAL</td>
									<td class="Details"  nowrap="nowrap" align="right"><c:out value="${claimTotal }"/>&nbsp;</td>
									<td class="Details"  nowrap="nowrap" align="right">
										<fmt:formatNumber><c:out value="${paymentTotal }"/></fmt:formatNumber>&nbsp;
									</td>
									<td colspan="3"></td>
								</tr>		
						</table>
					</td>
				</tr>
			</table>
			<br><br>
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
						(<input name="text2" type="text" class="inputuserstyle" value="${usermonitor }" size="25" style="text-align:center"/>)
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
						<div class="Details"><c:out value="${clientNumber}" />-SJALANGLOBAL</div>
					</TD>
				</tr>
			</table>
		</div>
		<div align="center">
		<%--@ include file="../owlexaLetterFooter.jsp" --%>
	</div>
</HTML>
	</BODY>