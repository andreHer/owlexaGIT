<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page contentType="text/html" import="java.util.*" %>
<script type="text/javascript" src="scripts/jquery/jquery-1.4.4.min.js"></script>
<HTML>
	<HEAD>
		<c:set var="letterTypeSet" value="${param.lettertype }"></c:set>
		<c:set var="clType" value="${letterTypeSet eq 'Maternity'? 'Maternity': letterTypeSet eq 'Kemotherapy'? 'Chemotheraphy' : letterTypeSet eq 'Haemodialisa'? 'Haemodialisa' : 'Surgery' }"></c:set>
		<!-- Owlexa Confirmation Letter With Type -->
		<TITLE><c:out value="${myCase.memberId.currentCardNumber}" />-<c:out value="${myCase.caseNumber}" />-CL <c:out value="${clType }"/> </TITLE>
		<% //Owlexa Letter Header Template %>
		<%@ include file="../owlexaLetterHeader.jsp" %>
		<DIV align=center>
			<center>
			<TABLE id=AutoNumber25 style="BORDER-COLLAPSE: collapse" height=793 cellSpacing=0 cellPadding=0 width=776 bgColor=#ffffff border=0>
				<!--DWLayoutTable-->
				<TBODY>
					<tr align="center" bgcolor="#00B0F0" style="background-color:#00B0F0;" height="30px">
						<td class="letterTitle2" colspan="3" valign="middle" style="background-color:#00B0F0;">
							<c:choose>
								<c:when test="${letterTypeSet eq 'Maternity' }">
									Medical Confirmation Letter: MATERNITY
								</c:when>
								<c:when test="${letterTypeSet eq 'Kemotherapy' }">
									Medical Confirmation Letter: CHEMOTHERAPY
								</c:when>
								<c:when test="${letterTypeSet eq 'Haemodialisa' }">
									Medical Confirmation Letter: HAEMODIALISA
								</c:when>
								<c:otherwise>
									Medical Confirmation Letter: SURGERY
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<td class="BOwlexa2">
							<br>
							<table style="border:  1px solid #00B0F0;border-collapse: collapse; width: 100%">
								<tr>
									<td style="padding-left: 15px" class="BOwlexa2">
										Attention To,
										<br>Dr(Treating Doctor) <input type="text" class="inputuserstyle2" value="[.............................]" size="40"> 
										<br>At(Hospital's Name) <c:out value="${myCase.providerId.providerName}" />
										<br><br>
										Dear Colleague,
										<br>Thank you for <c:out value="${myCase.providerId.providerName }"></c:out> who has given us the confidence to keep working together in providing health care services to our patients.
										The following patient's details as below :
										<br>
										<br>Patient Name&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;<c:out value="${myCase.memberId.firstName}" />
										<br>Card Number&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;<c:out value="${myCase.memberId.currentCardNumber}" /> 
										<br>Corporate&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;<c:out value="${myCase.memberId.memberGroupId.groupName}" /> 
										<br>
										<br>Please answer as below questions, in order to patient's plan for 
										<font size="2"><c:out value="${clType }"/></font>:
									</td>
								</tr>
								<c:choose>
									<%-- Pengaturan untuk Confirm Letter Maternity --%>
									<c:when test="${letterTypeSet eq 'Maternity' }">
										<tr valign="top" class="BOwlexa2" style="border: 1px solid #00B0F0;">
											<td>
												<div contenteditable="true" style="padding-left: 15px;padding-right:15px; overflow:hidden;width: 700px" >
												1. Chief of Complain for Hospitalized?
													<br>
													<br>2. Physical Examination?
													<br>
													<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;G ..........			P ..........			A ..........			Gestational Weeks:..........
													<br>
													<br>3. Diagnosis : 
													<br>
													<br>4. Etiology :
													<br>
													<br>5. Maternity Planning :
													<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;a) Spontaneous Labor?
													<table style="width: 100%">
														<tr>
															<td class="BOwlexa2">
																<div style="border: 1px solid black;width: 25px;margin-left: 50px;">&nbsp;</div>
															</td>
															<td class="BOwlexa2">
																Without Complications
															</td>
														</tr>
														<tr>
															<td class="BOwlexa2">
																<div style="border: 1px solid black;width: 25px;margin-left: 50px;">&nbsp;</div>
															</td>
															<td class="BOwlexa2">
																With Complications, Please Explain.................................
															</td>
														</tr>
													</table>
													<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;b) Sectio Caesarea?
													<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-SC Indication, please explain.....................
													<br>
													<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;c) If indicated for SC because of history of SC, please explain....................
													<br>
													<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;d) Is SC based on her own request?													
													<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[Yes/No], please explain..................
													<br>6. Estimated labor?
													<br>
													<br>7. Condition of Fetal/Baby :
													<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Birth Weight:............gram, Length:............cm, Head circumference:............cm, Apgar Scoring:...../.....
													<br>
													<br>8. Fetal diagnostic test result?
													<br>
													<br>9. Therapy?
													<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Mother : ......................................
													<br>
													<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Newborn baby : ......................................
													<br>
												</div>
											</td>
										</tr>
									</c:when>
									<c:when test="${letterTypeSet eq 'Kemotherapy' }">
										<tr valign="top" class="BOwlexa" style="border: 1px solid #00B0F0;">
											<td width="100%">
												
												<textarea id="remarkTxt" name="remarkTxt" style="border:0px;width:100%;height:100%; overflow:hidden;resize:none;" class="BOwlexa2">
	1.	a) Chief of Complaint :

		b) Vital SIgns, BP :				Pulse:				Temp:				RR:
	
		c) Since when patient has been diagnosed and got treatment?
	
		   [Date:........... Month:........... Years:...........]
					
	2.	Medical indication for hospitalized?..................................................................................................................................

	3.	Laboratory Examination:......................................................................................................................................

	4.	a) Diagnosis:......................................................................................................................................................

		b) Etiology:......................................................................................................................................................
	
	5.	Please explain in regard of CHEMOTHERAPY as below:
		a) Drug of Chemotherapy?: ................................................................................
		b) Duration : ................................................................................
		c) Interval : ................................................................................
		d) Kuure : ................................................................................
		e) Kuure of previous chemotherapy? ................................................................................

	6.	What criteria/condition can Chemotherapy be done for OUTPATIENT?..............................................................................
	
	7.	Estimation for length of stay :..............................................................................
	
</textarea>
											</td>
										</tr> 
									</c:when>
									<c:when test="${letterTypeSet eq 'Haemodialisa' }">
										<tr valign="top" class="BOwlexa2" style="border: 1px solid #00B0F0;">
											<td>
												<textarea id="remarkTxt" name="remarkTxt" style="border:0px;width:100%;height:100%; overflow:hidden;resize:none;" class="BOwlexa2">
1.	a) Chief of Complaint :

	b) Vital Signs, BP :				Pulse:				Temp:				RR:
	
	c) Since when patient has been diagnosed and got treatment?
	
					[Date:........... Month:........... Years:...........]
					
2.	Indication for Hospitalized:..................................................................................................................................

3.	Laboratory Examintation:......................................................................................................................................

4.	a) Diagnosis:......................................................................................................................................................

	b) Etiology:......................................................................................................................................................
	
5.	Etiology is related to:
	a) Congenital Disorder : [Yes/No]*
	b) Hereditatry Disorder : [Yes/No]*
	c) Etc

6.	Please explain if there is any indication of Hospitalized for HAEMODIALISA:
	a) Is HAEMODIALISA scheduled routinely? [Yes/No]*
	   (Please Explain)............................................................................
	   
	b) How many times for HAEMODIALISA's planning?.................................
	
	c) Could HAEMODIALISA be done for OUTPATIENT?
	   [Yes/No]*, (Please Explain).......................................................................
	   
	d) What criteria/condition can HAEMODIALISA be done for OUTPATIENT?

7.	Estimation for length of stay:..............................................................................
</textarea>
											</td>
										</tr>
									</c:when>
									<c:otherwise>
										<tr valign="top" class="BOwlexa2" style="border: 1px solid #00B0F0;">
											<td>
												<textarea id="remarkTxt" name="remarkTxt" style="border:0px;width:100%;height:100%; overflow:hidden;resize:none;" class="BOwlexa2">
	1.	Chief of Complaint :
	
	2.	Vital Signs:
			-BP:			-P:			-T:				-RR:
			
	3.	History of Illness : 
	
		[Date :..................... Month:.............. Years:.........]
	4.	Laboratory Examination :
	
	5.	Diagnosis:
			-Regio:			-Depth:				-Amount:
			
	6.	Etiology : 
	
	7.	For surgery's information : 
		a)	Schedule :.............................................		c)	Incision:.......................
		b)	Type of surgery :............................................	d)	Indication:....................
		e)	Surgical Status : Cito/Elektif/OCD*
		
	8.	Type of anaesthesia : []Local		[]General, Indication:
	
	9.	Therapy : 
	
	10. Diagnosis is related to :
		a)	Congenital (Yes/No)*					b)	Hereditary (Yes/No)*
		c)	Cosmetic (Yes/No)*					d)	Sexually Transmitted Disease (Yes/No)*
		e)	Dental (Yes/No)*						f)	Others
	
	11.	Estimation for length of stay : 
	
	12.	If any PA result : 
		a.	Yes, result :...................................................................
		b.	No, Plesae Explain :..................................................</textarea>
											</td>
										</tr>
									</c:otherwise>
								</c:choose>
								<tr>
									<td>
										<table style="width: 100%; padding-left: 15px">
											<tr>
												<td class="BOwlexa2">
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Thank you for you kind cooperation
												</td>
											</tr>
											<tr>
												<td width="50%" class="BOwlexa2">
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Jakarta, <fmt:formatDate type="date" value="<%= new java.util.Date() %>" pattern="dd, MMMM, yyyy"/><br>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Sincerely,</td>
												<td class="BOwlexa2">Physician/Doctor,</td>
											</tr>
											<tr>
												<td>
													<img src="images/owlexa/ma_citra.png"/>
												</td>
												<td></td>
											</tr>
											<tr>
												<td class="BOwlexa2">
													&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name="text332" type="text" class="inputuserstyle3" value="dr. Citra Andiani P" size="40">
													<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Medical Advisor
												</td>
												<td class="BOwlexa2">
													<input name="text332" type="text" class="inputuserstyle3" value="Signature over Writtern Name & Stamp" size="40">
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</td>
					</tr>	
				</TBODY>
			  </TABLE>
			  <br>
			  <%@ include file="../owlexaLetterFooterEnV2.jsp" %>
		</DIV>
		<div align="center">
			<% //Owlexa Letter Footer Template %>
			<br/>
			<input type="button" name="button" value="P   R   I   N   T"  size=10 onClick="window.print()" style="background:#EEF2FC;border:1px #6f6f6f solid;color:#6f6f6f;font:10px Arial;font-weight:bold;">
			<!-- 
			<input type="button" name="button2" value="S A V E   A   C O P Y"  size=10 onClick="document.execCommand('SaveAs',null,'<?php echo $sFileName;?>')" style="background:#EEF2FC;border:1px #6f6f6f solid;color:#6f6f6f;font:10px Arial;font-weight:bold;">
			-->
			<div class="Details"><c:out value="${myCase.memberId.currentCardNumber}" />-<c:out value="${myCase.caseNumber}" />-CL <c:out value="${clType }"/></div>
		</div>
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
</HTML>