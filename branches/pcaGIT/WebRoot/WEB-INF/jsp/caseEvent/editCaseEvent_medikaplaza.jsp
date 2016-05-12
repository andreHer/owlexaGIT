<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<form action="caseevent-form" method="POST"  name="form1" id="form_layout">
	<spring:bind path="caseEventForm.caseEventId">
		<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>

<input type="hidden" name="navigation" value="">


<%
String alert = (String) request.getAttribute("alert");
if (alert != null && !alert.trim().equals("")) {%>
	<div id="warning">
		<c:out value="${alert}"></c:out>
	</div>
	<%}%>


<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>
		<tr>
			<td>
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<tbody>



<tr>
			<td class="dataLabel"> &nbsp;</td>				
			

		    <td class="dataField"> </td>
		    
			<td class="dataLabel"> &nbsp;</td>				
			

		    <td class="dataField"> </td>

</tr>

	<tr>

		<td class="dataLabel">Case : </td>		
		<td class="dataField">

		<spring:bind path="caseEventForm.case.caseId">
			<input type="hidden" name="caseId" value="<c:out value="${status.value}" />">
			<input type="hidden" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />">
		</spring:bind>
		<spring:bind path="caseEventForm.case.caseNumber">
		<input type="text" name="caseNumber" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="11">
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>	
		</td>
    		<td class="dataLabel"> Event Category : </td>				
			<td class="dataField">
			<spring:bind path="caseEventForm.eventCategoryId.eventCategoryId">
				<select name="<c:out value="${status.expression}" />">
					<c:forEach items="${eventCategories}" var="ec">
						<option value="<c:out value="${ec.eventCategoryId}" />" <c:if test="${status.value eq ec.eventCategoryId}">selected</c:if>><c:out value="${ec.eventCategoryName}" /></option>
					</c:forEach>
				</select>
				
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
	    
	</tr>
								<tr>

								<td class="dataLabel">
									Monitor Time :
								</td>


								<td class="dataField">
							
											<spring:bind path="caseEventForm.date">
										<INPUT type="text" size="10" readonly="readonly" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" id="date" maxlength="19">
									</spring:bind>

										<spring:bind path="caseEventForm.hour">
											<select name="<c:out value="${status.expression}" />" tabindex="1">
												<c:forEach items="${hours}" var="h">
													<option value="<c:out value="${h}" />" <c:if test="${status.value eq h}">selected</c:if>><c:out value="${h}" /></option>
												</c:forEach>
											</select>
										</spring:bind>									
										:
										<spring:bind path="caseEventForm.minute">
											<select name="<c:out value="${status.expression}" />" tabindex="2">
												<c:forEach items="${minutes}" var="m">
													<option value="<c:out value="${m}" />" <c:if test="${status.value eq m}">selected</c:if>><c:out value="${m}" /></option>
												</c:forEach>
											</select>
										</spring:bind>										
	
															</td>
								<td class="dataLabel">
									Initial Diagnosis :
								</td>
								<td class="dataField">
									<spring:bind path="caseEventForm.case.initialDiagnosis">
										<input type="text" accesskey="D" size="35" name="initial" value="<c:out value="${status.value}"/>">
									</spring:bind>							
								</td>

							</tr>
					<tr>
				<td class="dataLabel"> Doctor Consult : </td>				
			

			<td class="dataField">
			<spring:bind path="caseEventForm.doctorConsult">
				<input type="text" accesskey="D" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
				<td class="dataLabel"> Nurse : </td>				
			

			<td class="dataField">
			<spring:bind path="caseEventForm.nurse">
				<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
		
	</tr>
			

			<tr>
				<td class="dataLabel"> Cost Estimation : </td>				
			
			<td class="dataField">
			<spring:bind path="caseEventForm.costEstimation">
					<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<fmt:formatNumber><c:out value="${status.value}" /></fmt:formatNumber>" maxlength="22">
				
			</spring:bind>
			</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>


			<tr>
				<td class="dataLabel"> Vital Sign : </td>				
			

		    <td class="dataField">
			<spring:bind path="caseEventForm.vitalSign">
			<textarea cols="40" rows="6" class="inputbox" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	<td class="dataLabel"> Therapy : </td>				
			

		    <td class="dataField">
			<spring:bind path="caseEventForm.therapy">
			<textarea cols="40" rows="6" class="inputbox" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	</tr>
		

	
<tr>
			<td class="dataLabel"> &nbsp;</td>				
		    <td class="dataField"> </td>
		    
			<td class="dataLabel"> &nbsp;</td>				
			

		    <td class="dataField"> </td>

</tr>
		

			<tr>
				<td class="dataLabel"> Remarks : </td>				
			

		    <td class="dataField">
			<spring:bind path="caseEventForm.remarks">
			<textarea  rows="6" cols="40" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>

				<td class="dataLabel"> Description : </td>				
		    <td class="dataField" colspan="3">
			<spring:bind path="caseEventForm.description">
			<textarea rows="6" cols="40" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
			</tr>


			<tr>
	    <td class="dataLabel">&nbsp;</td>
	    <td class="dataField">&nbsp;</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
		

	


			<tr>
				<td class="dataLabel"> Provider Contact : </td>				
			

			<td class="dataField">
			<spring:bind path="caseEventForm.providerContact">
							<textarea rows="5" cols="40" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
			</spring:bind>
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
	

		
				

		

	

		
				
		
		

		
				
		
		

			<tr>
	    <td class="dataLabel">&nbsp;</td>
	    <td class="dataField">&nbsp;</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
		

	

		
				
		
		



		
		

			<tr>
				<td class="dataLabel"> Subjective : </td>				
			

		    <td class="dataField">
			<spring:bind path="caseEventForm.subjective">
				<textarea rows="5" cols="40" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
			</spring:bind>
		</td>
				<td class="dataLabel"> Objective : </td>				
			

		    <td class="dataField">
			<spring:bind path="caseEventForm.objective">
				<textarea rows="5" cols="40" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
			</spring:bind>
		</td>
	</tr>


			<tr>
	    <td class="dataLabel">&nbsp;</td>
	    <td class="dataField">&nbsp;</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
		

	


			<tr>
				<td class="dataLabel"> Assesment : </td>				
			

		    <td class="dataField">
			<spring:bind path="caseEventForm.assesment">
				<textarea rows="5" cols="40" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
			</spring:bind>
		</td>
				<td class="dataLabel"> Plan : </td>				
			

		    <td class="dataField">
			<spring:bind path="caseEventForm.plan">
				<textarea rows="5" cols="40" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
			</spring:bind>
		</td>

	</tr>

			<tr>
	    <td class="dataLabel">&nbsp;</td>
	    <td class="dataField">&nbsp;</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
		

	

		



			<tr>
				<td class="dataLabel"> Ekg : </td>				
			

			<td class="dataField">
			<spring:bind path="caseEventForm.ekg">
				<textarea rows="5" cols="40" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
			</spring:bind>
		</td>
		<td class="dataLabel"> Impression : </td>				
			

			<td class="dataField">
			<spring:bind path="caseEventForm.impression">
				<textarea rows="5" cols="40" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>			
			</spring:bind>
		</td>
	</tr>
	

				

			<tr>
	    <td class="dataLabel">&nbsp;</td>
	    <td class="dataField">&nbsp;</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
		

	

				

			<tr>
				<td class="dataLabel"> Chief Complaint : </td>				
			

		    <td class="dataField">
			<spring:bind path="caseEventForm.chiefComplaint">
				<textarea rows="5" cols="40" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
			</spring:bind>
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
		

			<tr>
	    <td class="dataLabel">&nbsp;</td>
	    <td class="dataField">&nbsp;</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
		

	


	

		
				
		
		

			<tr>
				<td class="dataLabel"> Current Medication : </td>				
			

		    <td class="dataField">
			<spring:bind path="caseEventForm.currentMedication">
				<textarea rows="5" cols="40" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
			</spring:bind>
		</td>
				<td class="dataLabel"> Allergies : </td>				
			

		    <td class="dataField">
			<spring:bind path="caseEventForm.allergies">
				<textarea rows="5" cols="40" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
			</spring:bind>
		</td>

	</tr>
		

			<tr>
	    <td class="dataLabel">&nbsp;</td>
	    <td class="dataField">&nbsp;</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
		

	


			<tr>
				<td class="dataLabel"> Physical Examination : </td>				
			

		    <td class="dataField">
			<spring:bind path="caseEventForm.physicalExamination">
				<textarea rows="5" cols="40" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
			</spring:bind>
		</td>
		<td class="dataLabel"> Diagnostic Testing : </td>				
	    <td class="dataField">
			<spring:bind path="caseEventForm.diagnosticTesting">
				<textarea rows="5" cols="40" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
			</spring:bind>
		</td>

	</tr>
		

	

			<tr>
	    <td class="dataLabel">&nbsp;</td>
	    <td class="dataField">&nbsp;</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
		

	


		
				
		
		
			

			<tr>
				<td class="dataLabel"> Family History : </td>				
			

		    <td class="dataField">
			<spring:bind path="caseEventForm.familyHistory">
				<textarea rows="5" cols="40" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
			</spring:bind>
		</td>
	   	<td class="dataLabel"> Social History : </td>				
			

		    <td class="dataField">
			<spring:bind path="caseEventForm.socialHistory">
				<textarea rows="5" cols="40" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
			</spring:bind>
		</td>
	</tr>
		


			<tr>
	    <td class="dataLabel">&nbsp;</td>
	    <td class="dataField">&nbsp;</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
		

	

		
		

			<tr>
				<td class="dataLabel"> Past Medical History : </td>				
			

		    <td class="dataField">
			<spring:bind path="caseEventForm.pastMedicalHistory">
				<textarea rows="5" cols="40" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>				
			</spring:bind>
		</td>
		<td class="dataLabel"> Past Surgical History : </td>				
			

		    <td class="dataField">
				<spring:bind path="caseEventForm.pastSurgicalHistory">
					<textarea rows="5" cols="40" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
			
			</spring:bind>
		</td>
	</tr>
		

			<tr>
	    <td class="dataLabel">&nbsp;</td>
	    <td class="dataField">&nbsp;</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
		

	


	
		
		

			<tr>
				<td class="dataLabel"> Diagnosis Aspect : </td>				
			

		    <td class="dataField">
			<spring:bind path="caseEventForm.diagnosisAspect">
							<textarea rows="5" cols="40" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
			</spring:bind>
		</td>
				<td class="dataLabel"> Procedure Plan : </td>				
			

		    <td class="dataField">
			<spring:bind path="caseEventForm.procedurePlan">
				<textarea rows="5" cols="40" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
			</spring:bind>
		</td>

	</tr>
		
				
		
		

		

	

<tr>
			<td class="dataLabel"> &nbsp;</td>				
			

		    <td class="dataField"> </td>
		    
			<td class="dataLabel"> &nbsp;</td>				
			

		    <td class="dataField"> </td>

</tr>
						</tbody>
				</table>
			</td>
		</tr>
	</tbody>
</table>
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td style="padding-top: 2px;">
	    <input type="hidden" name="notyet" value="">
        <input title="Save [Alt+Shift+S]" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:simpan()" name="Save" value=" Save " type="submit">
        <input title="Cancel [Alt+Shift+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:cancel()" name="Cancel" value=" Cancel " type="button">         
	  </td>
      <td align="right"></td>
    </tr>
  </tbody>
</table>

</form>

<script language="javascript">
	function simpan (){
		document.form1.method = "POST";
		document.form1.action = "caseevent-form";
		document.form1.navigation.value = "save";
		document.form1.submit();
	}
	function kembali (){
		document.form1.navigation.value = "back";
		document.form1.action = "caseevent";
		document.form1.submit();
	}
	function cancel (){
		document.form1.navigation.value = "detail";
		document.form1.action = "case";
		document.form1.submit();
	}
	
				// forreign affairs end
</script>