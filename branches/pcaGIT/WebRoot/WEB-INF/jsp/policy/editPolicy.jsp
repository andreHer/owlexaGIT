<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<link rel="stylesheet" type="text/css" href="css/jquery/autocomplete/jquery.autocomplete.css"/>

<script type='text/javascript' src='dwr/interface/AJAXPaymentRecipientService.js'></script>
<script type='text/javascript' src='dwr/engine.js'></script>
<script type='text/javascript' src='dwr/util.js'></script>
<script type="text/javascript" src="scripts/tinymce/tinymce.min.js"></script>
<script type="text/javascript" src="scripts/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="scripts/jquery/plugin/jquery.autocomplete.pack.js"></script>
<script type="text/javascript" src="scripts/owlexa.function.js"></script>

<script type="text/javascript">
tinymce.init({
    selector: "textarea"
 });
</script>

<form action="policy-form" method="POST"  enctype="multipart/form-data" name="form1" id="form_layout">
<%
String alert = (String) request.getAttribute("alert");
if (alert != null && !alert.trim().equals("")) {%>
	<div id="warning">
		<c:out value="${alert}"></c:out>
	</div>
	<%}%>


<input type="hidden" name="navigation" value="">

		<spring:bind path="policyForm.policyId">
			<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
		</spring:bind>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Register Policy </h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>	
<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>
		<tr>
			<td class="dataLabel">&nbsp;</td>
			<td class="dataField"></td>
		    <td class="dataLabel"></td>
		    <td class="dataField"></td>
		</tr>
		<tr>
			<td class="dataLabel">Member Group : </td>		
			<td class="dataField">
				<spring:bind path="policyForm.memberGroupId">
					<input type="hidden" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />">
					<div id="fredcaption">
						<c:out value="${status.errorMessage}" />
					</div>
				</spring:bind>
				<spring:bind path="policyForm.memberGroupName">
					<input type="text" size="35" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />">
					<div id="fredcaption">
						<c:out value="${status.errorMessage}" />
					</div>			
				</spring:bind>		
			</td>
			<td class="dataLabel">Client : </td>		
			<td class="dataField">
				<spring:bind path="policyForm.clientId">
					<input type="hidden" name="<c:out value="${status.expression}" />" id="clientId" value="<c:out value="${status.value}" />">
					<div id="fredcaption">
						<c:out value="${status.errorMessage}" />
					</div>
				</spring:bind>
				<spring:bind path="policyForm.clientName">
					<input type="text" size="35" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
					<div id="fredcaption">
						<c:out value="${status.errorMessage}" />
					</div>	
				</spring:bind>
			</td>
		</tr>
		<tr>
			<td class="dataLabel"> Policy Number : </td>
			<td class="dataField">
				<spring:bind path="policyForm.policyNumber">
					<input type="text" size="35" name="<c:out value="${status.expression}" />" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
					<div id="fredcaption">
						<c:out value="${status.errorMessage}" />
					</div>
				</spring:bind>
			</td>
	    	<td class="dataLabel"> Policy Type : </td>
			<td class="dataField">
				<spring:bind path="policyForm.policyType">
					<select name="<c:out value="${status.expression}" />">					
						<option value="1" <c:if test="${status.value eq 1 }">selected</c:if>> INDEMNITY </option>
						<option value="2" <c:if test="${status.value eq 2 }">selected</c:if>> MANAGED CARE </option>
					</select>
				</spring:bind>
			</td>
		</tr>
		<tr>
			<td class="dataLabel"> Group Code : </td>
			<td class="dataField">
				<spring:bind path="policyForm.groupCode">
					<input type="text" size="35" name="<c:out value="${status.expression}" />" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
					<div id="fredcaption">
						<c:out value="${status.errorMessage}" />
					</div>
				</spring:bind>
			</td>
	    	<td class="dataLabel">Total Premium : </td>
			<td class="dataField">
				<spring:bind path="policyForm.totalPremium">
					<input type="text" size="35" name="<c:out value="${status.expression}" />" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
					<div id="fredcaption">
						<c:out value="${status.errorMessage}" />
					</div>
				</spring:bind>
			</td>
		</tr>
		<tr>
				<td class="dataLabel"> Card Type : </td>
				<td class="dataField">
				<spring:bind path="policyForm.cardTypeId">
					<select name="<c:out value="${status.expression }" />">
						<option value=""></option>
						<c:forEach items="${cardTypeList}" var="cardType">
							<option value="<c:out value="${cardType.cardTypeId}" />" <c:if test="${status.value eq cardType.cardTypeId }">selected</c:if>><c:out value="${cardType.cardTypeName}" /></option>											
						</c:forEach>									
					</select>
					
					
				</spring:bind>
			</td>
			<td class="dataLabel"> Swipe Card Prefix : </td>
			<td class="dataField">
				<spring:bind path="policyForm.swipeCardPrefix">
					<input type="text" size="35" onkeypress="return isNumberKey(this,event)" name="<c:out value="${status.expression}" />" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >				
				</spring:bind>
			</td>
		</tr>	
	
			<tr>
				<td class="dataLabel"> CoB Status : </td>
				<td class="dataField">
					<spring:bind path="policyForm.cobStatus">
						<select name="<c:out value="${status.expression}" />">					
							<option value="" > -- PILIH SALAH SATU -- </option>
							<option value="0" <c:if test="${status.value eq 0 }">selected</c:if>> TIDAK </option>
							<option value="1" <c:if test="${status.value eq 1 }">selected</c:if>> YA - MENGGUNAKAN CoB </option>
						</select>
					</spring:bind>
				</td>
				<td class="dataLabel"> Customer Number Prefix : </td>
				<td class="dataField">
					<spring:bind path="policyForm.customerNumberPrefix">
						<input type="text" size="35" onkeypress="return isNumberKey(this,event)" name="<c:out value="${status.expression}" />" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
						<div id="fredcaption">
							<c:out value="${status.errorMessage}" />
						</div>
					</spring:bind>
				</td>
			</tr>

		<tr>
			<td class="dataLabel"> Product Type : </td>
			<td class="dataField">
				<spring:bind path="policyForm.productType">
					<select name="<c:out value="${status.expression}" />">						
						<option value="-1"> -- PILIH SALAH SATU -- </option>
						<c:forEach items="${productTypes}" var="type">
							<option value="<c:out value="${type.productTypeId}" />" <c:if test="${status.value eq type.productTypeId}">selected</c:if> ><c:out value="${type.productTypeName}" /></option>
						</c:forEach>						
					</select>
				</spring:bind>
			</td>
	    	<td class="dataLabel"> Provider Allocation : </td>
			<td class="dataField">
				<spring:bind path="policyForm.providerAllocation">				
					<select name="<c:out value="${status.expression }" />">
						<option value="">-- PILIH SALAH SATU --</option>
						<option value="0" <c:if test="${status.value eq 0 }" >selected</c:if>>ALL PROVIDER</option>
						<option value="1" <c:if test="${status.value eq 1 }" >selected</c:if>>CLIENT LEVEL</option>
						<option value="2" <c:if test="${status.value eq 2 }" >selected</c:if>>GROUP LEVEL</option>				
						<option value="3" <c:if test="${status.value eq 3 }" >selected</c:if>>MEMBER LEVEL</option>
						<option value="4" <c:if test="${status.value eq 4 }" >selected</c:if>>POLICY LEVEL</option>
					</select>
					<div id="fredcaption">
						<c:out value="${status.errorMessage}" />
					</div>
				</spring:bind>
			</td>
		</tr>
	
				
		
		<tr>			
		    <td class="dataLabel">&nbsp;</td>
		    <td class="dataField"></td>
		    <td class="dataLabel"></td>
		    <td class="dataField"></td>
		</tr>
		<tr>
			<td class="dataLabel"> Effective Date : </td>
			<td class="dataField">
				<spring:bind path="policyForm.effectiveDate">
					<input type="text" readonly="readonly" name="<c:out value="${status.expression}" />" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="10">
					<img src="images/jscalendar.gif" alt="Enter Date" id="<c:out value="${status.expression}"/>_trigger" align="absmiddle" height="20" width="20">
					<script type="text/javascript">
    					Calendar.setup({
        					inputField     :    "<c:out value="${status.expression}"/>",     // id of the input field
        					ifFormat       :    "%Y-%m-%d",      // format of the input field
        					button         :    "<c:out value="${status.expression}"/>_trigger",  // trigger for the calendar (button ID)
        					align          :    "Bl",           // alignment (defaults to "Bl")
        					singleClick    :    true
    					});
				 	</script>
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
	    		<td class="dataLabel"> Expire Date : </td>				
			

						<td class="dataField">
			<spring:bind path="policyForm.expireDate">
					<input type="text" readonly="readonly" name="<c:out value="${status.expression}" />" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="10">
					<img src="images/jscalendar.gif" alt="Enter Date" id="<c:out value="${status.expression}"/>_trigger" align="absmiddle" height="20" width="20">
					<script type="text/javascript">
    					Calendar.setup({
        					inputField     :    "<c:out value="${status.expression}"/>",     // id of the input field
        					ifFormat       :    "%Y-%m-%d",      // format of the input field
        					button         :    "<c:out value="${status.expression}"/>_trigger",  // trigger for the calendar (button ID)
        					align          :    "Bl",           // alignment (defaults to "Bl")
        					singleClick    :    true
    					});
				 	</script>
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>	    
		</tr>
		<tr>
			<td class="dataLabel"> Request Date : </td>
			<td class="dataField">
				<spring:bind path="policyForm.requestDate">
					<input type="text" readonly="readonly" name="<c:out value="${status.expression}" />" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="10">
					<img src="images/jscalendar.gif" alt="Enter Date" id="<c:out value="${status.expression}"/>_trigger" align="absmiddle" height="20" width="20">
					<script type="text/javascript">
    					Calendar.setup({
        					inputField     :    "<c:out value="${status.expression}"/>",     // id of the input field
        					ifFormat       :    "%Y-%m-%d",      // format of the input field
        					button         :    "<c:out value="${status.expression}"/>_trigger",  // trigger for the calendar (button ID)
        					align          :    "Bl",           // alignment (defaults to "Bl")
        					singleClick    :    true
    					});
				 	</script>
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
			<td class="dataLabel"> Policy Date : </td>
			<td class="dataField">
				<spring:bind path="policyForm.policyDate">
					<input type="text" readonly="readonly" name="<c:out value="${status.expression}" />" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="10">
					<img src="images/jscalendar.gif" alt="Enter Date" id="<c:out value="${status.expression}"/>_trigger" align="absmiddle" height="20" width="20">
					<script type="text/javascript">
    					Calendar.setup({
        					inputField     :    "<c:out value="${status.expression}"/>",     // id of the input field
        					ifFormat       :    "%Y-%m-%d",      // format of the input field
        					button         :    "<c:out value="${status.expression}"/>_trigger",  // trigger for the calendar (button ID)
        					align          :    "Bl",           // alignment (defaults to "Bl")
        					singleClick    :    true
    					});
				 	</script>
					<div id="fredcaption">
						<c:out value="${status.errorMessage}" />
					</div>
				</spring:bind>
			</td>
		</tr>
		<tr>
			<spring:bind path="policyForm.brokerId">
				<input type="hidden" name="<c:out value="${status.expression}" />" id="brokerId" value="<c:out value="${status.value}" />">
				<div id="fredcaption">
						<c:out value="${status.errorMessage}" />
					</div>
			</spring:bind>		
				
			<td class="dataLabel">Broker : </td>		
			<td class="dataField">
			<spring:bind path="policyForm.brokerName">
				<input type="text" size="35" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
				<div id="fredcaption">
						<c:out value="${status.errorMessage}" />
					</div>
			</spring:bind>	
			
			</td>
		    <td class="dataLabel">Is Using ASO Fund : </td>		
			<td class="dataField">
			<spring:bind path="policyForm.isUsingFloatingFund">
				<select name="<c:out value="${status.expression}" />">
					<option value="">--- PILIH SALAH SATU ---</option>
					<option value="1" <c:if test="${status.value eq 1}">selected</c:if>>YA, Per Policy Fund</option>
					<option value="2" <c:if test="${status.value eq 2}">selected</c:if>>YA, Per Coverage Fund</option>
					<option value="0" <c:if test="${status.value eq 0}">selected</c:if>>Tidak</option>
				</select>
				<div id="fredcaption">
						<c:out value="${status.errorMessage}" />
					</div>
			</spring:bind>	
			
			</td>
		</tr>
		<tr>			
		    <td class="dataLabel">&nbsp;</td>
		    <td class="dataField"></td>
		    <td class="dataLabel"></td>
		    <td class="dataField"></td>
		</tr>
		<tr>
			<td class="dataLabel"> Initial Fund : </td>
			<td class="dataField">
				<spring:bind path="policyForm.initialFundLimit">
					<input type="text" size="25" onkeypress="return isNumberKey(this,event)" name="<c:out value="${status.expression}" />" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
					<div id="fredcaption">
						<c:out value="${status.errorMessage}" />
					</div>
				</spring:bind>
			</td>
		    <td class="dataLabel"> Fund Percentage Warning : </td>
			<td class="dataField">
				<spring:bind path="policyForm.fundWarningPercentage">				
					<input type="text" size="5" onkeypress="return isNumberKey(this,event)" name="<c:out value="${status.expression}" />" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" > %
					<div id="fredcaption">
						<c:out value="${status.errorMessage}" />
					</div>
				</spring:bind>
			</td>
		</tr>
		<tr>
			<td class="dataLabel"> Initial Excess Fund : </td>
			<td class="dataField">
				<spring:bind path="policyForm.initialExcessFundLimit">
					<input type="text" size="25" onkeypress="return isNumberKey(this,event)" name="<c:out value="${status.expression}" />" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
					<div id="fredcaption">
						<c:out value="${status.errorMessage}" />
					</div>
				</spring:bind>
			</td>
		    <td class="dataLabel"> Block Percentage Warning : </td>
			<td class="dataField">
				<spring:bind path="policyForm.blockedPercentage">				
					<input type="text" size="5" onkeypress="return isNumberKey(this,event)" name="<c:out value="${status.expression}" />" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" > %
					<div id="fredcaption">
						<c:out value="${status.errorMessage}" />
					</div>
				</spring:bind>
			</td>
		</tr>
		<tr>
			<td class="dataLabel"> Reimburse Expire Length : </td>
			<td class="dataField">
				<spring:bind path="policyForm.reimburseExpireLength">
					<input type="text" size="5" onkeypress="return isNumberKey(this,event)" name="<c:out value="${status.expression}" />" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" > day (s)
					<div id="fredcaption">
						<c:out value="${status.errorMessage}" />
					</div>
				</spring:bind>
			</td>
		    <td class="dataLabel"> Excess Paid Upfront : </td>
			<td class="dataField">
				<spring:bind path="policyForm.excessPaidUpfront">				
					<select name="<c:out value="${status.expression }" />">
						<option value="-1">-- PILIH SALAH SATU --</option>
						<option value="0" <c:if test="${status.value eq 0 }" >selected</c:if>>EXCESS DIJAMIN DAHULU</option>
						<option value="1" <c:if test="${status.value eq 1 }" >selected</c:if>>EXCESS DIBAYAR DI TEMPAT</option>				
					</select>
					<div id="fredcaption">
						<c:out value="${status.errorMessage}" />
					</div>
				</spring:bind>
			</td>
		</tr>	
		
		<tr>
			<td class="dataLabel"> Reimburse Max Acceptance : </td>
			<td class="dataField">
				<spring:bind path="policyForm.reimburseMaxReceive">
					<input type="text" size="5" onkeypress="return isNumberKey(this,event)" name="<c:out value="${status.expression}" />" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" > day (s)
					<div id="fredcaption">
						<c:out value="${status.errorMessage}" />
					</div>
				</spring:bind>
			</td>
		    <td class="dataLabel"> Excess Expire Length : </td>
			<td class="dataField">
				<spring:bind path="policyForm.excessExpireLength">
					<input type="text" size="5" onkeypress="return isNumberKey(this,event)" name="<c:out value="${status.expression}" />" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" > day (s)
					<div id="fredcaption">
						<c:out value="${status.errorMessage}" />
					</div>
				</spring:bind>
			</td>
		</tr>
		<tr>
		<td class="dataLabel"> Tarif Type : </td>
			<td class="dataField">
				<spring:bind path="policyForm.tarifType">
					<select name="<c:out value="${status.expression}" />">						
						<!-- <option value="-1"> -- PILIH SALAH SATU -- </option> -->
						<c:forEach items="${tarifTypeList}" var="tarifTypes">
							<option value="<c:out value="${tarifTypes.tarifTypeId}" />" <c:if test="${status.value eq tarifTypes.tarifTypeId}">selected</c:if> ><c:out value="${tarifTypes.tarifTypeName}" /></option>
						</c:forEach>						
					</select>
				</spring:bind>
			</td>
		</tr>
		
		<tr>
			
		    <td class="dataLabel"></td>
		    <td class="dataField"></td>
		</tr>
		<tr>
			<td class="dataLabel">Policy TC File 1 :&nbsp;</td>
			<td class="dataField">
				<input type="text" size="35" id="tcFileName1" value="<c:out value="${policyForm.tcFileName1}" />" readonly /><br/>
				<spring:bind path="policyForm.tcFile1">
					<input type="file" name="<c:out value="${status.expression}" />" value="<c:out value="${policyForm.tcFileName1}" />"  onchange="checkfile(this);" accept="application/pdf, application/msword, application/vnd.openxmlformats-officedocument.wordprocessingml.document, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel" />
				</spring:bind>
			</td>
			 <td class="dataLabel">Policy TC File 2 :&nbsp;</td>
			<td class="dataField">
				<input type="text" size="35" id="tcFileName2" value="<c:out value="${policyForm.tcFileName2}" />" readonly /><br/>
				<spring:bind path="policyForm.tcFile2">
					<input type="file" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" onchange="checkfile(this);" accept="application/pdf, application/msword, application/vnd.openxmlformats-officedocument.wordprocessingml.document, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel" />
				</spring:bind>
			</td>
		</tr>
		<tr>
			<td class="dataLabel">Policy TC File 3 :&nbsp;</td>
			<td class="dataField">
				<input type="text" size="35" id="tcFileName3" value="<c:out value="${policyForm.tcFileName3}" />" readonly /><br/>
				<spring:bind path="policyForm.tcFile3">
					<input type="file" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" onchange="checkfile(this);" accept="application/pdf, application/msword, application/vnd.openxmlformats-officedocument.wordprocessingml.document, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel" />
				</spring:bind>
			</td>
		    <td class="dataLabel">&nbsp;</td>
			<td class="dataField">&nbsp;</td>
		</tr>
		<tr>
			<td class="dataLabel">Policy T&C Note&nbsp;</td>
			<td class="dataField" colspan="3">
				<spring:bind path="policyForm.policyNotes">
					<textarea cols="50" rows="8" class="ckeditor" name="<c:out value="${status.expression}" />" ><c:out value="${status.value}" /></textarea>
				</spring:bind>
			</td>
		    
		</tr>
		<tr>
			<td class="dataLabel">&nbsp;</td>
			<td class="dataField"></td>
		    <td class="dataLabel"></td>
		    <td class="dataField"></td>
		</tr>
	</tbody>
</table>
</form>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td style="padding-top: 2px;">
	    <input type="hidden" name="notyet" value="">
        <input title="Save [Alt+S]" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:simpan()" name="Save" value=" Save " type="submit">
        <input title="Cancel [Alt+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:cancel()" name="Cancel" value=" Cancel " type="button">         
	  </td>
      <td align="right"></td>
    </tr>
  </tbody>
</table>

<script language="javascript">

	$("#memberGroupName").autocomplete("membergroup?navigation=lookupjson", {
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
	    $("#memberGroupId").val(value.id);
	});

	$("#clientName").autocomplete("client?navigation=lookupjson", {
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
        $("#clientId").val(value.id);
    });
    
    $("#brokerName").autocomplete("broker?navigation=lookupjson", {
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
        $("#brokerId").val(value.id);
    });
    
	function simpan (){

		document.form1.method = "POST";
		document.form1.action = "policy-form";
		document.form1.submit();
	}
	function cancel (){
		<c:if test="${navigation eq 'register'}">
		document.form1.navigation.value = "detail";
		document.form1.action = "membergroup";
		document.form1.submit();
		</c:if>
		<c:if test="${navigation ne 'register' and navigation ne 'tambah'}">
			document.form1.navigation.value = "detail";
			document.form1.action = "policy";
			document.form1.submit();
		</c:if>
		<c:if test="${navigation eq 'tambah'}">
			document.form1.navigation.value = "search";
			document.form1.action = "policy";
			document.form1.submit();
		</c:if>
	}
	function lookupCC(){
		window.open ("casecategory?navigation=lookup&url=member-form","Search","width=700, height=550, menubar=no, status=no, toolbar=no, scrollbars=yes, resizable=yes,screenX=200,screenY=100");
	}
	
	function appendProduct (productCode){
		var currentProduct = document.form1.asoCoverageList.value;
		
		currentProduct += " " + productCode;
		
		document.form1.asoCoverageList.value = currentProduct;
	}
	
	//Add by aju on 20150226, add file checker on client side fufufu :D
	function checkfile(sender) {
	    var validExts = new Array(".xlsx", ".xls",".doc", ".docx",".pdf");
	    var fileExt = sender.value;
	    //alert(fileExt);
	    fileExt = fileExt.substring(fileExt.lastIndexOf('.'));
	    if (validExts.indexOf(fileExt) < 0) {
	      alert("Invalid file selected (*." + fileExt + ") !!!\nValid files are of " +
	               validExts.toString() + " types.");
	               sender.value="";
	      return false;
	    }
	    else return true;
	}
</script>