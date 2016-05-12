<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<script type="text/javascript" src="scripts/tinymce/tinymce.min.js"></script>


<!-- <link rel="stylesheet" type="text/css" href="css/jquery/autocomplete/jquery.autocomplete.css"/> -->
<!-- <script type="text/javascript" src="scripts/jquery/jquery-1.4.4.min.js"></script> -->
<!-- <script type="text/javascript" src="scripts/jquery/plugin/jquery.autocomplete.pack.js"></script> -->
<script type="text/javascript" src="scripts/owlexa.function.js"></script>

	<style>
		.ui-autocomplete-loading {
		  background: white url("images/owlexa/ui-anim_basic_16x16.gif") right center no-repeat;
		}
	</style>


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

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Register Monitoring </h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>	
<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
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
									<spring:bind path="caseEventForm.initialDiagnosis">
										<input type="text" accesskey="D" size="35" id="initialDiagnosis" name="<c:out value="${status.expression }" />" value="<c:out value="${status.value}"/>" >
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
					<input type="text" onkeypress="return isNumberKey(this,event)" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="22">
								<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>


			<tr>
				<td class="dataLabel" id="vitalsign"> Vital Sign : <div id="cVitalSign_feedback"></div></td>				
			

		    <td class="dataField">
			<spring:bind path="caseEventForm.vitalSign">
			<textarea cols="40" rows="6" class="inputbox" name="<c:out value="${status.expression}"/>" maxlength="4000" id="cVitalSign"><c:out value="${status.value}" /></textarea>
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	<td class="dataLabel"> Therapy : <div id="cTherapy_feedback"></div></td>				
			

		    <td class="dataField">
			<spring:bind path="caseEventForm.therapy">
			<textarea cols="40" rows="6" class="inputbox" name="<c:out value="${status.expression}"/>" maxlength="4000" id="cTherapy"><c:out value="${status.value}" /></textarea>
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
			<td class="dataLabel"> Remarks : <div id="cRemarks_feedback"></div></td>				
			

		    <td class="dataField">
			<spring:bind path="caseEventForm.remarks">
			<textarea  rows="6" cols="40" name="<c:out value="${status.expression}"/>" maxlength="4000" id="cRemarks"><c:out value="${status.value}" /></textarea>
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>

				<td class="dataLabel"> Description : <div id="cDescription_feedback"></div></td>				
		    <td class="dataField" colspan="3">
			<spring:bind path="caseEventForm.description">
			<textarea rows="6" cols="40" name="<c:out value="${status.expression}"/>" maxlength="4000" id="cDescription"><c:out value="${status.value}" /></textarea>
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

		<td class="dataLabel"> Diagnostic Testing : <div id="cDiagnostic_feedback"></div></td>				
	    <td class="dataField">
			<spring:bind path="caseEventForm.diagnosticTesting">
				<textarea rows="5" cols="40" name="<c:out value="${status.expression}"/>" maxlength="4000" id="cDiagnostic"><c:out value="${status.value}" /></textarea>
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
		<td class="dataLabel"> Procedure : <div id="cProcedure_feedback"></div></td>				
	    <td class="dataField" colspan=3>
			<spring:bind path="caseEventForm.procedurePlan">
				<textarea rows="5" cols="40" name="<c:out value="${status.expression}"/>" maxlength="4000" id="cProcedure"><c:out value="${status.value}" /></textarea>
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
    
    $("#initialDiagnosis").autocomplete({
			source: function( request, response ) {
			$.ajax({
				url: "diagnosis?navigation=lookupjson",
				dataType: "json",
				data: {q: request.term},
				success: function(data) {
							response($.map(data, function(item) {
							return {
								label: item.name,
								id: item.id,
								number: item.number
								};
						}));
					}
				});
			},
			minLength: 2,
			select: function(event, ui) {			        		
        		$(this).parents("dd").find(".error_message").hide();        
        		$("#initialDiagnosis").val(ui.item.label);
			}
		})
		.autocomplete( "instance" )._renderItem = function( ul, item ) {
	      return $( "<a>" )
	        .append( item.label )
	        .appendTo( ul );
	    };
    
    /* remove by aju on 20150508, upgrade from autocomplete pulgin to jQueryUI above :D
    $("#initialDiagnosis").autocomplete("diagnosis?navigation=lookupjson", {
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
            return "<font color='#000' >"+ row.name +"</font>" ;
        }
    }).bind("result", function(data, value){
        $(this).parents("dd").find(".error_message").hide();        
        $("#initialDiagnosis").val(value.name);
    });
    */
   
    //$("#vitalsignid").keydown(function(event) {
    //	var currentString = $("#vitalsignid").val();
	//    $("#lblvital").html(currentString.length);
	 //   if (currentString.length <= 500 )  {  /*or whatever your number is*/
	   //   do some css with your div
	 //   } else {
	     // do some differnt stuff with your div
	//    }
	//});
	
	$('#cVitalSign_feedback').html('('+$('#cVitalSign').val().length +'/' + $('#cVitalSign').attr('maxlength') + ')');
	$('#cTherapy_feedback').html('('+$('#cTherapy').val().length +'/' + $('#cTherapy').attr('maxlength') + ')');
	$('#cRemarks_feedback').html('('+$('#cRemarks').val().length +'/' + $('#cRemarks').attr('maxlength') + ')');
	$('#cDescription_feedback').html('('+$('#cDescription').val().length +'/' + $('#cDescription').attr('maxlength') + ')');
	$('#cDiagnostic_feedback').html('('+$('#cDiagnostic').val().length +'/' + $('#cDiagnostic').attr('maxlength') + ')');
	$('#cProcedure_feedback').html('('+$('#cProcedure').val().length +'/' + $('#cProcedure').attr('maxlength') + ')');
	
	
    $('textarea[maxlength]').keyup(function(){  
        //get the limit from maxlength attribute  
        var limit = parseInt($(this).attr('maxlength'));  
        //get the current text inside the textarea  
        var text = $(this).val();  
        //count the number of characters in the text  
        var chars = text.length;  
        
        //alert($(this).attr('id'));
        $('#' + $(this).attr('id') +'_feedback').html('('+$(this).val().length +'/' + $(this).attr('maxlength') + ')');
  
        //check if there are more characters then allowed  
        if(chars > limit){  
            //and if there are use substr to get the text before the limit  
            var new_text = text.substr(0, limit);  
  
            //and change the current text with the new text  
            $(this).val(new_text);  
        }  
    }); 
});
	
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
		document.form1.navigation.value = "list";
		document.form1.action = "caseevent";
		document.form1.submit();
	}
	
				// forreign affairs end
</script>