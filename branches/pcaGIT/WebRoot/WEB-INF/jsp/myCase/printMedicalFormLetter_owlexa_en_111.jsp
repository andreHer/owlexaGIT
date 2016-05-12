<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page contentType="text/html" import="java.util.*" %>

<HTML>
	<HEAD>
		<!-- Owlexa Medical Form Letter -->
		<TITLE><c:out value="${myCase.memberId.currentCardNumber}" />-<c:out value="${myCase.caseNumber}" />-RESUMEMEDICFORM-<c:out value="${param.langselect }"/> </TITLE>
		<% //Owlexa Letter Header Template %>
		<%@ include file="../owlexaLetterHeader.jsp" %>
		<DIV align=center>
			<center>
			<TABLE id=AutoNumber25 style="BORDER-COLLAPSE: collapse" cellSpacing=0 cellPadding=0 width=776 bgColor=#ffffff border=0>
				<!--DWLayoutTable-->
				<TBODY>
					<tr align="center" bgcolor="#00B0F0" style="background-color:#00B0F0;">
						<td class="letterTitle2" colspan="3" valign="bottom" style="background-color:#00B0F0;">
							Medical Assessment Form <div style="font-size: 10px;">(Should be fill by doctor in charge)</div>
						</td>
					</tr>
					<tr><td>&nbsp;</td></tr>
					<tr>
						<td width="387" height=98 align="center" valign="top">
							<table width="98%" border="1" cellpadding="0" cellspacing="0" bordercolor="#00B0F0"  style="border-style: solid">
								<tr bordercolor="#FFFFFF">
									<td width="100" class="Details2" colspan="2"  style="border-style: solid">
										&nbsp;Attention To
									</td>
									<td class="Details2"  style="border-style: solid">
										<table>
											<tr>
												<td class="Details2" width="100px">
													&nbsp;From
												</td>
												<td class="Details2">
													:&nbsp;<input name="text3" type="text" class="inputuserstyle2" value=".................." size="50">
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td class="Details2"  style="border-style: solid">
										&nbsp;Hospital's Name
									</td>
									<td class="Details2" style="border-style: solid">
										&nbsp;<c:out value="${myCase.providerId.providerName}"/>
									</td>
									<td class="Details2" style="border-style: solid">
										<table>
											<tr>
												<td class="Details2" width="100px">
													&nbsp;Date
												</td>
												<td class="Details2">
													: &nbsp;<fmt:formatDate type="date" value="<%=new java.util.Date() %>" pattern="dd-MMM-yyyy"/>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td width="14%" class="Details2" style="border-style: solid">
										&nbsp;Up.
									</td>
									<td width="23%" class="Details2" style="border-style: solid">
										&nbsp;<input type="text" class="inputuserstyle2" value=".................." size="30">
									</td>
									<td rowspan="3" colspan="2" class="Details2" align="center" style="border-style: solid">
										<br><br>
										<input type="text" class="inputuserstyle2" value="(Acc Change Requester)" size="20">
									</td>
								</tr>
								<tr>
									<td class="Details2" style="border-style: solid">
										&nbsp;Telp Number
									</td>
									<td class="Details2" style="border-style: solid">
										&nbsp;<input type="text" class="inputuserstyle2" value=".................." size="20">
									</td>
								</tr>
								<tr>
									<td class="Details2" style="border-style: solid">
										&nbsp;Fax Number
									</td>
									<td class="Details2" style="border-style: solid">
										&nbsp;<input name="text3" type="text" class="inputuserstyle2" value=".................." size="20">
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr align="center" valign="top">
						<td width="387" align="center" valign="top">
							<table width="98%" border="1" cellpadding="0" cellspacing="0" bordercolor="#00B0F0" style="border-style: solid">
								<tr>
									<td class="Details2" style="border-style: solid">
										&nbsp;Card Number
									</td>
									<td class="Details2" style="border-style: solid">
										&nbsp;<c:out value="${myCase.memberId.currentCardNumber}" />
										<%-- <c:out value="${myCase.memberId.customerPolicyNumber}" />--%>
									</td>
									<td width="200" class="Details2" style="border-style: solid">
										&nbsp;Admission Date
									</td>
									<td class="Details2" style="border-style: solid">
										&nbsp;<fmt:formatDate value="${myCase.caseStartTime}" pattern="dd-MMM-yyyy"/>
									</td>
								</tr>
								<tr>
									<td width="105" class="Details2" style="border-style: solid">
										&nbsp;Patient's Name
									</td>
									<td width="210" class="Details2" style="border-style: solid">
										&nbsp;<c:out value="${myCase.memberId.firstName}" />
									</td>
									<td class="Details2" style="border-style: solid">
										&nbsp;Ward
									</td>
									<td class="Details2" style="border-style: solid">
										&nbsp;<input name="text3" type="text" class="inputuserstyle2" value="${myCase.roomAndBoard}" size="20">
									</td>
								</tr>
								<tr>
									<td class="Details2" style="border-style: solid">
										&nbsp;Gender
									</td>
									<td class="Details2" style="border-style: solid">
										&nbsp;<c:out value="${myCase.memberId.gender}" />
									</td>
									<td class="Details2" rowspan="2" style="border-style: solid">
										&nbsp;Medical Diagnose
									</td>
									<td class="Details2" rowspan="2" style="border-style: solid">
										&nbsp;<b><c:out value="${myCase.diagnosis1Id.diagnosisCode}" /></b>
										&nbsp;-
										&nbsp;<c:out value="${myCase.diagnosis1Id.description}" />
									</td>
								</tr>
								<tr>									
									<td class="Details2" style="border-style: solid">
										&nbsp;Date of Birth
									</td>
									<td class="Details2" style="border-style: solid">
										&nbsp;<fmt:formatDate value="${myCase.memberId.birthday }" pattern="dd-MMM-yyyy"/>
									</td>
								</tr>
							</table>
						</td>
					</tr>    
					<tr align="center" valign="top">
						<TD colspan="2">
							<table width="98%" border="0" cellpadding="0" cellspacing="0" bordercolor="#6f6f6f" style="border-style: solid">
								<tr >
									<td width="96%" align="center" valign="bottom" bordercolor="#6f6f6f" class="BOwlexa3">
										<br>
										<span class="Details">
											<textarea name="txtRemarks" style="width:100%; height:30px; resize:none;background-color: transparent; color: #000000; border:2px #00B0F0 solid; rgb(0,0,0);font:10px Calibri;font-weight: bold;">General Condition : 
</textarea>
										</span>
									</td>
								</tr>
							</table>
						</TD>
					</TR>
					<tr align="left" valign="middle">
						<td class="BOwlexa3">
							<br>&nbsp;&nbsp;&nbsp;&nbsp; VITAL SIGN : 
						</td>
					</tr>
					<tr align="center" valign="top">
						<td>
							<table width="98%" border="1" cellpadding="0" cellspacing="0" bordercolor="#00B0F0" style="border-style: solid">
								<tr>
									<td class="BOwlexa3" align="center" style="border-style: solid">
										Conciousness
									</td>
									<td class="BOwlexa3" align="center" style="border-style: solid">
										Blood Pressure
									</td>
									<td class="BOwlexa3" align="center" style="border-style: solid">
										Pulse
									</td>
									<td class="BOwlexa3" align="center" style="border-style: solid">
										Temperature
									</td>
									<td class="BOwlexa3" align="center" style="border-style: solid">
										Respiratory Rate
									</td>
									<td class="BOwlexa3" align="center" style="border-style: solid">
										Physical Examination
									</td>
								</tr>
								<tr >
									<td valign="bottom" bordercolor="#6f6f6f" class="BOwlexa3" style="border-style: solid">
										<span>
											<textarea style="resize:none;background-color: transparent;font:8px Calibri;border: 0;width: 100%;height: 10px"></textarea>
										</span>
									</td>
									<td valign="bottom" bordercolor="#6f6f6f" class="BOwlexa3" style="border-style: solid">
										<span>
											<textarea style="resize:none;background-color: transparent;font:8px Calibri;border: 0;width: 100%;height: 10px"></textarea>
										</span>
									</td>
									<td valign="bottom" bordercolor="#6f6f6f" class="BOwlexa3" style="border-style: solid">
										<span>
											<textarea style="resize:none;background-color: transparent;font:8px Calibri;border: 0;width: 100%;height: 10px"></textarea>
										</span>
									</td>
									<td valign="bottom" bordercolor="#6f6f6f" class="BOwlexa3" style="border-style: solid">
										<span>
											<textarea style="resize:none;background-color: transparent;font:8px Calibri;border: 0;width: 100%;height: 10px"></textarea>
										</span>
									</td>
									<td valign="bottom" bordercolor="#6f6f6f" class="BOwlexa3" style="border-style: solid">
										<span>
											<textarea style="resize:none;background-color: transparent;font:8px Calibri;border: 0;width: 100%;height: 10px"></textarea>
										</span>
									</td>
									<td valign="bottom" bordercolor="#6f6f6f" class="BOwlexa3" style="border-style: solid">
										<span>
											<textarea style="resize:none;background-color: transparent;font:8px Calibri;border: 0;width: 100%;height: 10px"></textarea>
										</span>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr align="left" valign="middle">
						<td class="BOwlexa3" colspan="6">
							&nbsp;&nbsp;&nbsp;&nbsp; WORKING DIAGNOSIS :
							<br>&nbsp;&nbsp;&nbsp;&nbsp; 1.  <input type="text" class="inputuserstyle2" value="........................" size="140">
							<br>&nbsp;&nbsp;&nbsp;&nbsp; 2.  <input type="text" class="inputuserstyle2" value="........................" size="140">
							<br>&nbsp;&nbsp;&nbsp;&nbsp; 2.  <input type="text" class="inputuserstyle2" value="........................" size="140">
						</td>
					</tr>
					<tr align="left" valign="middle">
						<td class="BOwlexa3" colspan="1">
							&nbsp;&nbsp;&nbsp;&nbsp; HISTORY OF ILLNESS : 
							<br>
							&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" class="inputuserstyle2" size="140" value=".................">
							<br>
						</td>
					</tr>
					<tr align="left" valign="middle">
						<td class="BOwlexa3" colspan="1">
							<br>
							&nbsp;&nbsp;&nbsp;&nbsp; ETIOLOGY : 
							<br>
							&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" class="inputuserstyle2" size="140" value=".................">
							<br>
						</td>
					</tr>
					<tr align="left" valign="middle">
						<td class="BOwlexa3" colspan="1">
							&nbsp;&nbsp;&nbsp;&nbsp; DOES THE ILLNESS RELATED TO : 
						</td>
					</tr>
					<tr align="center" valign="top">
						<td width="387" align="center" valign="top">
							<table width="98%" border="1" cellpadding="0" cellspacing="0" bordercolor="#00B0F0" style="border-style: solid">
								<tr>
									<td width="200" class="HeaderName2" align="center" style="border-style: solid">
										Type
									</td>
									<td width="10" class="HeaderName2" align="center" style="border-style: solid">
										Yes
									</td>
									<td width="10" class="HeaderName2" align="center" style="border-style: solid">
										No
									</td>
									<td width="200" class="HeaderName2" align="center" style="border-style: solid">
										Type
									</td>
									<td width="10" class="HeaderName2" align="center" style="border-style: solid">
										Yes
									</td>
									<td width="10" class="HeaderName2" align="center" style="border-style: solid">
										No
									</td>
									<td width="200" class="HeaderName2" align="center" style="border-style: solid">
										Type
									</td>
									<td width="10" class="HeaderName2" align="center" style="border-style: solid">
										Yes
									</td>
									<td width="10" class="HeaderName2" align="center" style="border-style: solid">
										No
									</td>
								</tr>
								<tr>
									<td class="Details2" style="border-style: solid">
										&nbsp;1. Congenital
									</td>
									<td align="center" style="border-style: solid">
										<input type="checkbox">
									</td>
									<td align="center" style="border-style: solid">
										<input type="checkbox">
									</td>
									<td class="Details2" style="border-style: solid">
										&nbsp;6. Cosmetic
									</td>
									<td align="center" style="border-style: solid">
										<input type="checkbox">
									</td>
									<td align="center" style="border-style: solid">
										<input type="checkbox">
									</td>
									<td class="Details2" style="border-style: solid">
										&nbsp;11. Sexual Tramitted Disease
									</td>
									<td align="center" style="border-style: solid">
										<input type="checkbox">
									</td>
									<td align="center" style="border-style: solid">
										<input type="checkbox">
									</td>
								</tr>
								<tr>
									<td class="Details2" style="border-style: solid">
										&nbsp;2. Hereditary
									</td>
									<td align="center" style="border-style: solid">
										<input type="checkbox">
									</td>
									<td align="center" style="border-style: solid">
										<input type="checkbox">
									</td>
									<td class="Details2" style="border-style: solid">
										&nbsp;7. Suicide attempts or plans
									</td>
									<td align="center" style="border-style: solid">
										<input type="checkbox">
									</td>
									<td align="center" style="border-style: solid">
										<input type="checkbox">
									</td>
									<td class="Details2" style="border-style: solid">
										&nbsp;12. Psychiatric Disorder
									</td>
									<td align="center" style="border-style: solid">
										<input type="checkbox">
									</td>
									<td align="center" style="border-style: solid">
										<input type="checkbox">
									</td>
								</tr>
								<tr>
									<td class="Details2" style="border-style: solid">
										&nbsp;3. Pregnancy
									</td>
									<td align="center" style="border-style: solid">
										<input type="checkbox">
									</td>
									<td align="center" style="border-style: solid">
										<input type="checkbox">
									</td>
									<td class="Details2" style="border-style: solid">
										&nbsp;8. Accident
									</td>
									<td align="center" style="border-style: solid">
										<input type="checkbox">
									</td>
									<td align="center" style="border-style: solid">
										<input type="checkbox">
									</td>
									<td class="Details2" style="border-style: solid">
										&nbsp;13. Dangerous Sport
									</td>
									<td align="center" style="border-style: solid">
										<input type="checkbox">
									</td>
									<td align="center" style="border-style: solid">
										<input type="checkbox">
									</td>
								</tr>
								<tr>									
									<td class="Details2" style="border-style: solid">
										&nbsp;4. Fertility
									</td>
									<td align="center" style="border-style: solid">
										<input type="checkbox">
									</td>
									<td align="center" style="border-style: solid">
										<input type="checkbox">
									</td>
									<td class="Details2" style="border-style: solid">
										&nbsp;9. Drugs
									</td>
									<td align="center" style="border-style: solid">
										<input type="checkbox">
									</td>
									<td align="center" style="border-style: solid">
										<input type="checkbox">
									</td>
									<td class="Details2" rowspan="2" colspan="3" style="border-style: solid">
										&nbsp;14. Others : &nbsp;<input type="text" class="inputuserstyle2" value="........................" size="50">
									</td>
								</tr>
								<tr>									
									<td class="Details2" style="border-style: solid">
										&nbsp;5. Hormonal Disease 
									</td>
									<td align="center" style="border-style: solid">
										<input type="checkbox">
									</td>
									<td align="center" style="border-style: solid">
										<input type="checkbox">
									</td>
									<td class="Details2" style="border-style: solid">
										&nbsp;10. HIV/AIDS
									</td>
									<td align="center" style="border-style: solid">
										<input type="checkbox">
									</td>
									<td align="center" style="border-style: solid">
										<input type="checkbox">
									</td>
								</tr>
							</table>
						</td>
					</tr> 
					<tr><td><br></td></tr>   
					<tr align="left" valign="middle">
						<td class="BOwlexa" colspan="1" align="center">
							<table  width="98%" border="1" cellpadding="0" cellspacing="0" bordercolor="#00B0F0" style="border-style: solid">
								<tr>
									<td class="HeaderName2" colspan="2"  style="border-style: solid">
										COULD HAEMODIALISA BE DONE FOR OUTPATIENT?
									</td>
									<td class="Details2" style="border-style: solid">
										<input type="checkbox">&nbsp;Yes
									</td>
									<td class="Details2" style="border-style: solid">
										<input type="checkbox">&nbsp;No
									</td>
								</tr>
								<tr> 
									<td class="Details2" colspan="4" style="border-style: solid">
										If <b>NO</b>, Please explain the indication for Hospitalized : 
										<br><input type="text" class="inputuserstyle2" value="" size="140"><br>
										<br>
									</td>
								</tr>
								<tr>
									<td class="Details2" align="center" style="border-style: solid">
										a. Own/Family Request
									</td>
									<td class="Details2" align="center" style="border-style: solid">
										<input type="checkbox">&nbsp;Yes
									</td>
									<td class="Details2" align="center" style="border-style: solid">
										<input type="checkbox">&nbsp;No
									</td>
									<td  class="Details2" rowspan="2" valign="top" style="border-style: solid">
										Estimation for Length of Stay : <input type="text" class="inputuserstyle2" value="" size="20"><br>
									</td>
								</tr>
								<tr>
									<td class="Details2" align="center" style="border-style: solid">
										b. Medical Indication
									</td>
									<td class="Details2" align="center" style="border-style: solid">
										<input type="checkbox">&nbsp;Yes
									</td>
									<td class="Details2" align="center" style="border-style: solid">
										<input type="checkbox">&nbsp;No
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr align="left" valign="middle">
						<td class="BOwlexa3">
							&nbsp;&nbsp;&nbsp;&nbsp;LABORATORY EXAMINATION
							<br>&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" class="inputuserstyle2" value="..............................................................................................................." size="120">
							<br>&nbsp;
						</td>
					</tr>
					<tr align="left" valign="middle">
						<td class="BOwlexa3">
							&nbsp;&nbsp;&nbsp;&nbsp;INTERVENTION/THERAPY
							<br>&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" class="inputuserstyle2" value="..............................................................................................................." size="120">
							<br>&nbsp;
						</td>
					</tr>
					<tr align="left" valign="middle">
						<td class="BOwlexa3">
							&nbsp;&nbsp;&nbsp;&nbsp;DOCTOR DECLARATION : 
						</td>
					</tr>
					<tr align="left" valign="middle">
						<td class="Details2">
							&nbsp;&nbsp;&nbsp;&nbsp;I hereby declare that all the information presented here are not influenced or withheld by anyone
						</td>
					</tr>
					<tr align="center" valign="top">
						<TD colspan="2">
							<table width="95%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td width="60%" class="BOwlexa5">			  
										<br> 
										Sincerely, <br>
										<img src="images/owlexa/ma_citra.png" width="60" height="50">
										<br>
										<input name="text33" type="text" class="inputuserstyle3" value="dr. Citra Andiani P" size="50">			  
										<br>
										Medical Advisor
									</td>
									<td class="BOwlexa5">		
									  	<br>
										Treating Physician/Doctor&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<br>
										<br>
										<br>
										<br>
										<br>
										<input name="text332" type="text" class="inputuserstyle3" value="(Signature over Written Name & Stamp)" size="40">
										<br>
									</td>
								</tr>
							</table>
						</TD>
					</TR>
				</TBODY>
			  </TABLE>
			  <%@ include file="../owlexaLetterFooterEnV2.jsp" %>
		</DIV>
		<% //Owlexa Letter Footer Template %>
		<div align="center">
		<input type="button" name="button" value="P   R   I   N   T"  size=10 onClick="window.print()" style="background:#EEF2FC;border:1px #6f6f6f solid;color:#6f6f6f;font:10px Arial;font-weight:bold;">
		<!-- 
		<input type="button" name="button2" value="S A V E   A   C O P Y"  size=10 onClick="document.execCommand('SaveAs',null,'<?php echo $sFileName;?>')" style="background:#EEF2FC;border:1px #6f6f6f solid;color:#6f6f6f;font:10px Arial;font-weight:bold;">
		-->
		<br>
		<div class="Details"><c:out value="${myCase.memberId.currentCardNumber}" />-<c:out value="${myCase.caseNumber}" />-RESUMEMEDICFORM-<c:out value="${param.langselect }"/></div>
		</div>
	</BODY>
</HTML>