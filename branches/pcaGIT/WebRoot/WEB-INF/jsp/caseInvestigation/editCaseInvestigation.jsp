<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<!-- <link rel="stylesheet" type="text/css" href="css/jquery/autocomplete/jquery.autocomplete.css"/> -->

<!-- <script type="text/javascript" src="scripts/jquery/jquery-1.4.4.min.js"></script> -->
<!-- <script type="text/javascript" src="scripts/jquery/plugin/jquery.autocomplete.pack.js"></script> -->
<script type="text/javascript" src="scripts/owlexa.function.js"></script>

	<style>
		.ui-autocomplete-loading {
		  background: white url("images/owlexa/ui-anim_basic_16x16.gif") right center no-repeat;
		}
	</style>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
		<c:choose>
			<c:when test="${navigation eq 'tambah'}">
      			<td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Register Case Investigation</h3></td>
      		</c:when>
      	</c:choose>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>

<form action="caseinvestigation-form" method="POST"  name="form1" id="form_layout">


	<spring:bind path="caseInvestigationForm.caseInvestigationId">
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
								<td class="dataLabel">
									
								</td>


								<td class="dataField">
									
								</td>
								<td class="dataLabel">
								
								</td>
								<td class="dataField">
									
									
								</td>
							</tr>

			<tr>
				<td class="dataLabel"> Case : </td>				
			
			<td class="dataField">
			<spring:bind path="caseInvestigationForm.case.caseId">
				<input type="hidden" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />">
				<input type="hidden" name="caseId" value="<c:out value="${status.value}" />">
			</spring:bind>
			<spring:bind path="caseInvestigationForm.case.caseNumber">
					<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="11">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
	   
	   <td class="dataLabel"> Investigation Category : </td>				
			

						<td class="dataField">
			<spring:bind path="caseInvestigationForm.investigationCategoryId.investigationCategoryId">
				<select name="<c:out value="${status.expression}" />">
					<c:forEach items="${investigationCategories}" var="ic">
						<option value="<c:out value="${ic.investigationCategoryId}" />" 
						<c:if test="${ic.investigationCategoryId eq status.value}">selected</c:if>><c:out value="${ic.investigationCategoryName}" /></option>
					</c:forEach>
				</select>
				
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
	</tr>

		

		
				
		
		

		
				
		
		
				
		
		

			<tr>
				<td class="dataLabel"> Investigation Subject : </td>				
			

			<td class="dataField">
			<spring:bind path="caseInvestigationForm.investigationSubject">
				<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" >
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	   

				<td class="dataLabel"> Head Doctor : </td>				
			

			<td class="dataField">
			<spring:bind path="caseInvestigationForm.headDoctor">
				<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" >
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	</tr>
	

		
					<tr>
				<td class="dataLabel"> Investigation Date : </td>				
			

						<td class="dataField">
			<spring:bind path="caseInvestigationForm.investigationDate">
					<input type="text"  name="<c:out value="${status.expression}" />" readonly="readonly" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="10">
					<img src="images/jscalendar.gif" alt="Enter Date" id="<c:out value="${status.expression}"/>_trigger" align="absmiddle" height="20" width="20">
					<script type="text/javascript">
    					Calendar.setup({
        					inputField     :    "<c:out value="${status.expression}"/>",     // id of the input field
        					ifFormat       :    "%Y-%m-%d",      // format of the input field
        					button         :    "<c:out value="${status.expression}"/>_trigger",  // trigger for the calendar (button ID)
        					align          :    "Br",           // alignment (defaults to "Bl")
        					singleClick    :    true
    					});
				 	</script>
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
	   	<td class="dataLabel"> Consult Doctor : </td>				
			

			<td class="dataField">
			<spring:bind path="caseInvestigationForm.consultDoctor">
			<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" >
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	   
	</tr>
			

		
				
						<tr>
				<td class="dataLabel"> Treatment Day(s) : </td>				
			

			<td class="dataField">
				<spring:bind path="caseInvestigationForm.totalDays">
					<input type="text"  name="<c:out value="${status.expression}" />" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="10">						
				</spring:bind>
			</td>
			<td class="dataLabel"> Nurse : </td>				
			

			<td class="dataField">
				<spring:bind path="caseInvestigationForm.nurse">
					<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" >
					<div id="fredcaption">
						<c:out value="${status.errorMessage}" />
					</div>
				</spring:bind>
			</td>

		</tr>		
			<tr>
				<td class="dataLabel"> Medical Procedure : </td>
				<td class="dataField">
					<spring:bind path="caseInvestigationForm.procedureName">
						<input type="text"  size="35" id="procedureName" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" >					
					</spring:bind>
					<spring:bind path="caseInvestigationForm.procedureId">
						<input type="hidden" id="procedureId" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" maxlength="35">						
					</spring:bind>
				</td>	 		
				<td class="dataLabel">&nbsp;</td>
				<td class="dataField">&nbsp;</td>
			</tr>
			<tr>
				<td class="dataLabel"> Cost Estimation : </td>
				<td class="dataField">
					<spring:bind path="caseInvestigationForm.procedureCostEstimation">
						<input type="text"  size="35" onkeypress="return isNumberKey(this,event);" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" >					
					</spring:bind>
				</td>	 		
				<td class="dataLabel"> Reference Cost : </td>
				<td class="dataField">&nbsp;
					<spring:bind path="caseInvestigationForm.procedureCostReference">
						<input type="text"  size="35" id="procedureCostReference"  onkeypress="return isNumberKey(this,event);" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" >					
					</spring:bind>
				</td>
			</tr>

<tr>
				<td class="dataLabel"> &nbsp;</td>				
			

			<td class="dataField">
			&nbsp;
		</td>
				<td class="dataLabel"> &nbsp; </td>				
			

			<td class="dataField">&nbsp;
		</td>
	</tr>
			
			<tr>
				<td class="dataLabel"> Medicine : </td>
				<td class="dataField">
					<spring:bind path="caseInvestigationForm.medicineName">
						<input type="text"  size="35" id="medicineName" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" >					
					</spring:bind>
					<spring:bind path="caseInvestigationForm.medicineId">
						<input type="hidden" id="medicineId" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" maxlength="35">						
					</spring:bind>
				</td>	 		
				<td class="dataLabel">&nbsp;</td>
				<td class="dataField">&nbsp;</td>
			</tr>
			<tr>
				<td class="dataLabel"> Medicine Est. Cost : </td>
				<td class="dataField">
					<spring:bind path="caseInvestigationForm.medicineCostEstimation">
						<input type="text"  size="35" onkeypress="return isNumberKey(this,event);" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" >					
					</spring:bind>
				</td>	 		
				<td class="dataLabel"> Medicine Ref. Cost : </td>
				<td class="dataField">&nbsp;
					<spring:bind path="caseInvestigationForm.medicineCostReference">
						<input type="text"  size="35" id="medicineCostReference" onkeypress="return isNumberKey(this,event);" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" >					
					</spring:bind>
				</td>
			</tr>
			<tr>
				<td class="dataLabel"> Decision : </td>				
			

				<td class="dataField">
					<spring:bind path="caseInvestigationForm.decision">
						<input type="radio" name="<c:out value="${status.expression}" />" value="1" <c:if test="${status.value eq 1}">checked="checked"</c:if> > Yes &nbsp;&nbsp;
						<input type="radio" name="<c:out value="${status.expression}" />" value="2" <c:if test="${status.value eq 2}">checked="checked"</c:if>> No &nbsp;&nbsp;
						<input type="radio" name="<c:out value="${status.expression}" />" value="3" <c:if test="${status.value eq 3}">checked="checked"</c:if>> None &nbsp;&nbsp;			 
						<div id="fredcaption">
							<c:out value="${status.errorMessage}" />
						</div>
					</spring:bind>
				</td>
		    	<td class="dataLabel"></td>				
				
	
				<td class="dataField"></td>
			</tr>
			
			<tr>
				<td class="dataLabel"> &nbsp;</td>				
			

			<td class="dataField">
			&nbsp;
		</td>
				<td class="dataLabel"> &nbsp; </td>				
			

			<td class="dataField">&nbsp;
		</td>
	</tr>
	
		
	







			<tr>
				<td class="dataLabel"> Conciousness : </td>				
			

			<td class="dataField">
			<spring:bind path="caseInvestigationForm.conciousness">
				<input type="text" size="35" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value }" />">
			</spring:bind>
		</td>
	    	<td class="dataLabel"> GCS Eye : </td>				
			

			<td class="dataField">
			<spring:bind path="caseInvestigationForm.gcsE">
				<select name="<c:out value="${status.expression}"/>">
					<option value="0">-- SELECT ONE --</option>
					<option value="1" <c:if test="${status.value eq 1 }">selected</c:if>>1</option>
					<option value="2" <c:if test="${status.value eq 2 }">selected</c:if>>2</option>
					<option value="3" <c:if test="${status.value eq 3 }">selected</c:if>>3</option>
					<option value="4" <c:if test="${status.value eq 4 }">selected</c:if>>4</option>
					<option value="5" <c:if test="${status.value eq 5 }">selected</c:if>>5</option>
					<option value="6" <c:if test="${status.value eq 6 }">selected</c:if>>6</option>
				</select>
			</spring:bind>
		</td>
	</tr>
	

		
				
		
		

			<tr>
				<td class="dataLabel"> Blood Pressure : </td>				
			

			<td class="dataField">
			<spring:bind path="caseInvestigationForm.bloodPressure">
				<input type="text" size="35" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value }" />">
			</spring:bind>
		</td>
				<td class="dataLabel"> GCS Motor : </td>				
			

			<td class="dataField">
			<spring:bind path="caseInvestigationForm.gcsM">
				<select name="<c:out value="${status.expression}"/>">
					<option value="0">-- SELECT ONE --</option>
					<option value="1" <c:if test="${status.value eq 1 }">selected</c:if>>1</option>
					<option value="2" <c:if test="${status.value eq 2 }">selected</c:if>>2</option>
					<option value="3" <c:if test="${status.value eq 3 }">selected</c:if>>3</option>
					<option value="4" <c:if test="${status.value eq 4 }">selected</c:if>>4</option>
					<option value="5" <c:if test="${status.value eq 5 }">selected</c:if>>5</option>
					<option value="6" <c:if test="${status.value eq 6 }">selected</c:if>>6</option>
				</select>
			</spring:bind>
		</td>
	</tr>
	

		
				
		
		

			<tr>
				<td class="dataLabel"> Artery : </td>				
			

			<td class="dataField">
			<spring:bind path="caseInvestigationForm.artery">
				<input type="text" size="35" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value }" />">
			</spring:bind>
		</td>
	<td class="dataLabel"> GCS Verbal : </td>				
			

			<td class="dataField">
			<spring:bind path="caseInvestigationForm.gcvV">
				<select name="<c:out value="${status.expression}"/>">
					<option value="0">-- SELECT ONE --</option>
					<option value="1" <c:if test="${status.value eq 1 }">selected</c:if>>1</option>
					<option value="2" <c:if test="${status.value eq 2 }">selected</c:if>>2</option>
					<option value="3" <c:if test="${status.value eq 3 }">selected</c:if>>3</option>
					<option value="4" <c:if test="${status.value eq 4 }">selected</c:if>>4</option>
					<option value="5" <c:if test="${status.value eq 5 }">selected</c:if>>5</option>
					<option value="6" <c:if test="${status.value eq 6 }">selected</c:if>>6</option>
				</select>				
			</spring:bind>
		</td>
	</tr>
	

		
				
		
			<tr>
				<td class="dataLabel"> &nbsp;</td>				
			

			<td class="dataField">&nbsp;
		</td>
	    	<td class="dataLabel"> &nbsp; </td>				
			

			<td class="dataField">&nbsp;
		</td>
	</tr>
	
		

			<tr>
				<td class="dataLabel"> Temperature : </td>				
			

			<td class="dataField">
			<spring:bind path="caseInvestigationForm.temperature">
				<input type="text" size="35" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value }" />">
			</spring:bind>
		</td>
	    	<td class="dataLabel"> IPPV Status : </td>				
			

			<td class="dataField">
			<spring:bind path="caseInvestigationForm.ippvStatus">
				<input type="text" size="15" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value }" />">
			</spring:bind>
		</td>
	</tr>
	

		
				
		
		

			<tr>
				<td class="dataLabel"> Respiratory : </td>				
			

			<td class="dataField">
			<spring:bind path="caseInvestigationForm.respiratory">
				<input type="text" size="35" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value }" />">
			</spring:bind>
		</td>
	    	<td class="dataLabel"> SIPPV Status : </td>				
			

			<td class="dataField">
			<spring:bind path="caseInvestigationForm.sippvStatus">
				<input type="text" size="15" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value }" />">
			</spring:bind>
		</td>

	</tr>
	

		
				

			

		
				
		
		

			<tr>
				<td class="dataLabel"> Oxygen Saturation : </td>				
			

			<td class="dataField">
			<spring:bind path="caseInvestigationForm.satOxygen">
				<input type="text" size="35" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value }" />">
			</spring:bind>
		</td>
				<td class="dataLabel"> Ventilator Status : </td>				
			

			<td class="dataField">
			<spring:bind path="caseInvestigationForm.ventilatorStatus">
				<input type="text" size="25" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value }" />">
			</spring:bind>
		</td>
	</tr>
	

		
			<tr>
				<td class="dataLabel"> &nbsp;</td>				
			

			<td class="dataField">
			&nbsp;
		</td>
				<td class="dataLabel"> &nbsp; </td>				
			

			<td class="dataField">&nbsp;
		</td>
	</tr>
	
				
		
		

	

		
				
		
		
				
		
		

			<tr>
				<td class="dataLabel"> Pulse : </td>				
			

			<td class="dataField">
			<spring:bind path="caseInvestigationForm.pulse">
				<input type="text" size="35" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value }" />">
			</spring:bind>
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>


		
				
		
		

			<tr>
				<td class="dataLabel"> Tracheostomy : </td>				
			

			<td class="dataField">
			<spring:bind path="caseInvestigationForm.tracheostomy">
				<input type="text" size="35" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value }" />">
			</spring:bind>
		</td>
		<td class="dataLabel"> PH : </td>				
			

			<td class="dataField">
			<spring:bind path="caseInvestigationForm.ph">
				<input type="text" size="25" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value }" />">
			</spring:bind>
		</td>
	</tr>
	

		
				
		
		

			<tr>
				<td class="dataLabel"> Peep : </td>				
			

			<td class="dataField">
			<spring:bind path="caseInvestigationForm.peep">
				<input type="text" size="35" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value }" />">
			</spring:bind>
		</td>
				<td class="dataLabel"> PO2 : </td>				
			

			<td class="dataField">
			<spring:bind path="caseInvestigationForm.po2">
				<input type="text" size="25" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value }" />">
			</spring:bind>
		</td>
	</tr>
	

		
		
	


			<tr>
				<td class="dataLabel"> IV Line 1 : </td>				
			

			<td class="dataField">
			<spring:bind path="caseInvestigationForm.ivLine1">
				<input type="text" size="35" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value }" />">
			</spring:bind>
		</td>
	    <td class="dataLabel"> Percentage PC O2 : </td>				
			

			<td class="dataField">
			<spring:bind path="caseInvestigationForm.percentagePcO2">
				<input type="text" size="15" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value }" />">
			</spring:bind>
		</td>
	</tr>
	

		
				
		
		

			<tr>
				<td class="dataLabel"> IV Line 2 : </td>				
			

			<td class="dataField">
			<spring:bind path="caseInvestigationForm.ivLine2">
				<input type="text" size="35" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value }" />">			
			</spring:bind>
		</td>
	    	<td class="dataLabel"> Percentage HC O3 : </td>				
			

			<td class="dataField">
			<spring:bind path="caseInvestigationForm.percentageHcO3">
				<input type="text" size="15" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value }" />">
			</spring:bind>
		</td>
	</tr>
	

			<tr>
				<td class="dataLabel"> Description : </td>				
			

		    <td class="dataField" colspan="3">
			<spring:bind path="caseInvestigationForm.description">
			<textarea cols="60" rows="8" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>

	</tr>
		
	
		<tr>
								<td class="dataLabel">
									
								</td>


								<td class="dataField">
									
								</td>
								<td class="dataLabel">
								
								</td>
								<td class="dataField">
									
									
								</td>
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


	$(document).ready(function(){

	$("#procedureName").autocomplete({
			source: function( request, response ) {
			$.ajax({
				url: "procedure?navigation=lookupjson",
				dataType: "json",
				data: {q: request.term},
				success: function(data) {
							response($.map(data, function(item) {
							return {
								label: item.name,
								id: item.id,
								code: item.code
								};
						}));
					}
				});
			},
			minLength: 2,
			select: function(event, ui) {		        
		        $(this).parents("dd").find(".error_message").hide();
		        $("#procedureId").val (ui.item.id);
		        $("#procedureName").val (ui.item.label);
			}
		})
		.autocomplete( "instance" )._renderItem = function( ul, item ) {
	      return $( "<a>" )
	        .append( item.code + " - " + item.label )
	        .appendTo( ul );
	    };
		
		/* remove by aju on 20150508, upgrade from autocomplete pulgin to jQueryUI above :D
    $("#procedureName").autocomplete("procedure?navigation=lookupjson", {
        max: 7,
        dataType: "json",
        parse: function(data) {
            return $.map(data, function(row) {
                return {
                    data: row,
                    value: row.name,
                    result: row.name
                }
            });
        },
        formatItem: function(row) {
            return  "<font color='#000' style='align: left;' >"+ row.code + " - " + row.name +"</font>" ;
        }        
	    }).bind("result", function(data, value){
	        $(this).parents("dd").find(".error_message").hide();
	        $("#procedureId").val (value.id);
	        $("#procedureName").val (value.name);
	        
	    });
	    */
	    
	    	$("#medicineName").autocomplete({
			source: function( request, response ) {
			$.ajax({
				url: "medicine?navigation=lookupjson",
				dataType: "json",
				data: {q: request.term},
				success: function(data) {
							response($.map(data, function(item) {
							return {
								label: item.name,
								id: item.id,
								price: item.price
								};
						}));
					}
				});
			},
			minLength: 2,
			select: function(event, ui) {		        
		        $(this).parents("dd").find(".error_message").hide();
		        $("#medicineId").val (ui.item.id);
		        $("#medicineName").val (ui.item.name);
		        $("#medicineCostReference").val (ui.item.price);
			}
		})
		.autocomplete( "instance" )._renderItem = function( ul, item ) {
	      return $( "<a>" )
	        .append( item.label )
	        .appendTo( ul );
	    };
		
		/* remove by aju on 20150508, upgrade from autocomplete pulgin to jQueryUI above :D	    
	    $("#medicineName").autocomplete("medicine?navigation=lookupjson", {
        max: 7,
        dataType: "json",
        parse: function(data) {
            return $.map(data, function(row) {
                return {
                    data: row,
                    value: row.name,
                    result: row.name
                }
            });
        },
        formatItem: function(row) {
            return  "<font color='#000' style='align: left;' >" + row.name +"</font>" ;
        }        
	    }).bind("result", function(data, value){
	        $(this).parents("dd").find(".error_message").hide();
	        $("#medicineId").val (value.id);
	        $("#medicineName").val (value.name);
	        $("#medicineCostReference").val (value.price);
	        
	    });
	    */
	    
	});
	function simpan (){

		document.form1.method = "POST";
		document.form1.action = "caseinvestigation-form";
		document.form1.submit();
		document.form1.navigation.value = "tambah";		
	}
	function kembali (){
		document.form1.navigation.value = "detail";		
		document.form1.action = "case";
		document.form1.submit();
	}
	function cancel (){
		document.form1.navigation.value = "list";		
		document.form1.action = "caseinvestigation";
		document.form1.submit();
	}
	// forreign affairs
			// forreign affairs end
</script>