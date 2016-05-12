<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<!-- <link rel="stylesheet" type="text/css" href="css/jquery/autocomplete/jquery.autocomplete.css"/> -->

<script type='text/javascript' src='dwr/interface/AJAXPaymentRecipientService.js'></script>
<script type='text/javascript' src='dwr/engine.js'></script>
<script type='text/javascript' src='dwr/util.js'></script>

<!-- <script type="text/javascript" src="scripts/jquery/jquery-1.4.4.min.js"></script> -->
<!-- <script type="text/javascript" src="scripts/jquery/plugin/jquery.autocomplete.pack.js"></script> -->
<script type="text/javascript" src="scripts/owlexa.function.js"></script>

	<style>
		.ui-autocomplete-loading {
		  background: white url("images/owlexa/ui-anim_basic_16x16.gif") right center no-repeat;
		}
	</style>

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
	  <c:choose>
		<c:when test="${navigation eq 'update'}">
     		<td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Update Batch Claim</h3></td>
     	</c:when>
		<c:when test="${navigation ne 'update'}">
     		<td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Register Batch Claim</h3></td>
     	</c:when>
	  </c:choose>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>

<form action="batchclaim-form" method="POST"  name="form1" id="form_layout">
<input type="hidden" name="navigation" value="">
	<spring:bind path="batchClaimForm.batchClaimId">
		<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
	</spring:bind>




<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>
		
		<tr>
			<td class="dataLabel"></td>
			<td class="dataField"></td>
			<td class="dataLabel"></td>
			<td class="dataField"></td>
		</tr>

		<tr>
			<td class="dataLabel">Claim Type * : </td>		
			<td class="dataField">
				<spring:bind path="batchClaimForm.batchClaimType">
					<select name="${status.expression}" onchange="javascript:changeClaimType()" id="ctype">
						<option value=""> -- SELECT CLAIM TYPE -- </option>
						<c:forEach items="${claimType}" var="claimType">
							<option value="${claimType.claimTypeId}" <c:if test="${claimType.claimTypeId eq status.value}">selected</c:if> ><c:out value="${claimType.claimTypeName}" /></option>
						</c:forEach>
					</select>
					<div id="fredcaption">
						<c:out value="${status.errorMessage}" />
					</div>
				</spring:bind>
			</td>			   
  				<td class="dataLabel">Payment Recipient * : </td>		
			<td class="dataField">
				<spring:bind path="batchClaimForm.paymentRecipient">
				<select name="<c:out value="${status.expression}"/>" id="paymentRecipient">
					<c:forEach items="${paymentRecipient}" var="recipient">
						<option value="${recipient.paymentRecipientId}" <c:if test="${recipient.paymentRecipientId eq status.value}">selected</c:if> ><c:out value="${recipient.paymentRecipientName}" /></option>
					</c:forEach>
				</select>
		
					<div id="fredcaption">
						<c:out value="${status.errorMessage}" />
					</div>
				</spring:bind>	
			</td>
		</tr>
		<tr>
			<td class="dataLabel">Claim Receive * :</td>
			<td class="dataField">
				<spring:bind path="batchClaimForm.claimReceivingId">
					<select name="<c:out value="${status.expression}" />" onchange="javascript:completeForm();" id="claimReceiveId">
						<option value="">-- SELECT ONE --</option>
						<c:forEach items="${claimReceiveList}" var="claim">							
							<option value="<c:out value="${claim.claimReceivingId}" />" 
								<c:if test="${claim.claimReceivingId eq status.value}">selected</c:if> >
									<c:out value="${claim.receivingNumber}" />  | <c:out value="${claim.clientId.clientName}" /> | <c:out value="${claim.providerId.providerName}" />									 
							</option>
						</c:forEach>
					</select>
					<div id="fredcaption">
						<c:out value="${status.errorMessage}" />
					</div>	
				</spring:bind>
				
			</td>
			<td class="dataLabel">&nbsp;</td>		
			<td class="dataField"></td>
		</tr>
		<tr>
			<td class="dataLabel">Client * :</td>
			<td class="dataField">
				<spring:bind path="batchClaimForm.clientId">
					<input type="hidden" id="clientId"  name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" />
				</spring:bind>
				<spring:bind path="batchClaimForm.clientName">
					<input type="text"  id="clientName" size="35" readonly="readonly" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" />
					<!--  <input type="button" name="PILIHCLIENT" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" value="Lookup" onClick="javascript:lookupClient()"> -->
										<div id="fredcaption">
						<c:out value="${status.errorMessage}" />
					</div>
				</spring:bind>
			</td>
			<td class="dataLabel">Recipient * : </td>		
			<td class="dataField">
				<spring:bind path="batchClaimForm.claimerId">
					<input type="hidden" id="claimerId" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />">
				</spring:bind>
			
				<spring:bind path="batchClaimForm.claimer">
				<input type="text" name="<c:out value="${status.expression}"/>" size="35" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
						<!--  <input type="button" name="PILIH" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" value="Lookup" onClick="javascript:lookupRecipient()"> -->
					<div id="fredcaption">
						<c:out value="${status.errorMessage}" />
					</div>
				</spring:bind>
			</td>
		</tr>
		
		<tr>	
			<td class="dataLabel"></td>		
			<td class="dataField"></td>
			<td class="dataLabel"></td>		
			<td class="dataField"></td>
		</tr>
			
		<tr>
			<td class="dataLabel"> Received Date * : </td>
			<td class="dataField">
				<spring:bind path="batchClaimForm.batchClaimDate">
						<input type="text" name="<c:out value="${status.expression}" />" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="10">
						<img src="images/jscalendar.gif" alt="Enter Date" id="<c:out value="${status.expression}"/>_trigger" align="absmiddle" height="20" width="20"> 
						<!-- <script type="text/javascript">
	    					Calendar.setup({
	        					inputField     :    "<c:out value="${status.expression}"/>",     // id of the input field
	        					ifFormat       :    "%Y-%m-%d",      // format of the input field
	        					button         :    "<c:out value="${status.expression}"/>_trigger",  // trigger for the calendar (button ID)
	        					align          :    "Bl",           // alignment (defaults to "Bl")
	        					singleClick    :    true
	    					});
					 	</script> -->
					<div id="fredcaption">
						<c:out value="${status.errorMessage}" />
					</div>
				</spring:bind>
			</td>		
			<td class="dataLabel"> Due Date * : </td>
			<td class="dataField">
				<spring:bind path="batchClaimForm.batchDueDate">
						<input type="text" name="<c:out value="${status.expression}" />" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="10">
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
  			<td class="dataLabel"> Claim Currency : </td>	
  			<td class="dataField">
				<spring:bind path="batchClaimForm.claimCurrency">
				<select name="<c:out value="${status.expression}"/>">
					<option value=""> -- SELECT CURRENCY -- </option>
					<c:forEach items="${currencies}" var="cur">
						<option value="${cur.currencyId}" <c:if test="${(status.value eq cur.currencyId) or (cur.isDefault eq 1)}">selected</c:if>><c:out value="${cur.currencyName}"/></option>
					</c:forEach>
				</select>
					<div id="fredcaption">
						<c:out value="${status.errorMessage}" />
					</div>
				</spring:bind>
			</td>
		  	<td class="dataLabel"> Payment Currency : </td>	
		  	<td class="dataField">
				<spring:bind path="batchClaimForm.paymentCurrency">
				<select name="<c:out value="${status.expression}"/>">
					<option value=""> -- SELECT CURRENCY -- </option>
					<c:forEach items="${currencies}" var="cur">
						<option value="${cur.currencyId}" <c:if test="${(status.value eq cur.currencyId) or (cur.isDefault eq 1)}">selected</c:if>><c:out value="${cur.currencyName}"/></option>
					</c:forEach>
				</select>
					<div id="fredcaption">
						<c:out value="${status.errorMessage}" />
					</div>
				</spring:bind>
			</td>
		</tr>
		<tr>
	  		<td class="dataLabel"> <!-- Reference Number :  --></td>
	  		<td class="dataField">
	  			<!-- 
				<spring:bind path="batchClaimForm.batchNumberPsea">
					<input type="text" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" size="30">
					<div id="fredcaption">
						<c:out value="${status.errorMessage}" />
					</div>
				</spring:bind>
				 -->
			</td>
	  	
	 		<td class="dataLabel">Priority : </td>		
			<td class="dataField">
				<spring:bind path="batchClaimForm.priority">
					<select name="<c:out value="${status.expression}"/>">
						<option value=""> -- SELECT PRIORITY -- </option>
						<c:forEach items="${priority}" var="prio">
							<option value="<c:out value="${prio.priorityId}" />" <c:if test="${(status.value eq prio.priorityId) or (prio.isDefault eq 1)}">selected</c:if>><c:out value="${prio.priorityCode}" /></option>
						</c:forEach>
					</select>
					<div id="fredcaption">
						<c:out value="${status.errorMessage}" />
					</div>
				</spring:bind>
			</td>
		</tr>

		<tr>
			<td class="dataLabel"></td>
			<td class="dataField"></td>
	  		<td class="dataLabel"></td>	
			<td class="dataField"></td>	
		</tr>
		<tr>
			<td class="dataLabel"> Invoice Number * : </td>
			<td class="dataField">
				<spring:bind path="batchClaimForm.invoiceNumber">
					<input type="text" size="35" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" />	
					<div id="fredcaption">
						<c:out value="${status.errorMessage}" />
					</div>
				</spring:bind>
			</td>   
   			<td class="dataLabel"> Batch Amount * : </td>			
			<td class="dataField">
				<spring:bind path="batchClaimForm.batchClaimInitialValue">
						<input type="text" size="35" onkeypress="return isNumberKey(this,event)" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="22">
					<div id="fredcaption">
						<c:out value="${status.errorMessage}" />
					</div>
				</spring:bind>
			</td>
		</tr>			
		<tr>
			<td class="dataLabel"> Invoice Date * : </td>
			<td class="dataField">
				<spring:bind path="batchClaimForm.invoiceDate">
						<input type="text" name="<c:out value="${status.expression}" />" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="10">
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
	   		<td class="dataLabel"> Number of Claim * : </td>				
			<td class="dataField">
				<spring:bind path="batchClaimForm.totalClaim">

					<input type="text" size="35"  onkeypress="return isNumberKey(this,event)" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" maxlength="11">
					<div id="fredcaption">
						<c:out value="${status.errorMessage}" />
					</div>
				</spring:bind>
			</td>
		</tr>
		<tr>
			<td class="dataLabel">
				Payment Method :
			</td>
			<td class="dataField">
				<spring:bind path="batchClaimForm.paymentMethod">
					<select name="<c:out value="${status.expression}"/>">
						<option value=""> -- SELECT PAYMENT METHOD -- </option>
						<c:forEach items="${paymentMethod}" var="paymentMethod">
							<option value="${paymentMethod.paymentMethodId}" <c:if test="${(status.value eq paymentMethod.paymentMethodId) or (paymentMethod.isDefault eq 1)}">selected</c:if>>
								<c:out value="${paymentMethod.paymentMethod}" />
							</option>
						</c:forEach>
					</select>
					<div id="fredcaption">
						<c:out value="${status.errorMessage}" />
					</div>
				</spring:bind>
			</td>
	   		<td class="dataLabel"> Incomplete Claim  : </td>				
			<td class="dataField">
				<spring:bind path="batchClaimForm.incompleteClaim">

					<input type="text" size="35"  onkeypress="return isNumberKey(this,event)" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" maxlength="11">
					<div id="fredcaption">
						<c:out value="${status.errorMessage}" />
					</div>
				</spring:bind>
			</td>	
		   	<%-- <td class="dataLabel"> Biaya Materai : </td>
			<td class="dataField">
				<spring:bind path="batchClaimForm.biayaMaterai">
					<input type="text" onkeypress="return isNumberKey(this,event)" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" maxlength="11">
					<div id="fredcaption">
						<c:out value="${status.errorMessage}" />
					</div>
				</spring:bind>
			</td> --%>
		</tr>	
		<tr>			
   			<td class="dataLabel"> Unregistered : </td>			
			<td class="dataField">
				<spring:bind path="batchClaimForm.unregistered">
					<input type="checkbox"  name="<c:out value="${status.expression}" />" value="Y" <c:if test="${status.value eq \"Y\"}" >checked</c:if>>
					<div id="fredcaption">
						<c:out value="${status.errorMessage}" />
					</div>
				</spring:bind>
			</td>	
   			<td class="dataLabel"></td>
			<td class="dataField"></td>
		</tr>

		<tr>
			<td class="dataLabel"></td>		
			<td class="dataField">&nbsp;</td>
		    <td class="dataLabel"></td>
		    <td class="dataField"></td>
		</tr>
		<tr>
			<td class="dataLabel"> Description : </td>	
	    	<td class="dataField" colspan="3">
				<spring:bind path="batchClaimForm.description">
				<textarea cols="60" rows="8" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
					<div id="fredcaption">
						<c:out value="${status.errorMessage}" />
					</div>
				</spring:bind>
			</td>
    
		</tr>		
		<tr>
			<td class="dataLabel"></td>
			<td class="dataField"></td>
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
    /*Add by aju on 20150407, for active menu...fiuuuhhhhhh*/
    var nav = $("#mnuMainClaim");
	nav.addClass("active");
	
	var nav = $("#mnuClaimRegister");
	nav.addClass("active");
    
    $("#clientName").autocomplete({
			source: function( request, response ) {
			$.ajax({
				url: "client?navigation=lookupjson",
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
        		$("#clientId").val(ui.item.id);
			}
		})
		.autocomplete( "instance" )._renderItem = function( ul, item ) {
	      return $( "<a>" )
	        .append( item.label )
	        .appendTo( ul );
	    };
    
    /* remove by aju on 20150508, upgrade from autocomplete pulgin to jQueryUI above :D
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
    */
    
    $("#claimer").autocomplete({
			source: function( request, response ) {
			$.ajax({
				url: "recipient?navigation=lookupjson",
				dataType: "json",
				data: {
					q: request.term,
					recipient: $("#paymentRecipient").val(),
       				clientid: $("#clientId").val()
					},
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
        		$("#claimerId").val(ui.item.id);
			}
		})
		.autocomplete( "instance" )._renderItem = function( ul, item ) {
	      return $( "<a>" )
	        .append( item.number + " - " + item.label )
	        .appendTo( ul );
	    };
    
    /* remove by aju on 20150508, upgrade from autocomplete pulgin to jQueryUI above :D
    $("#claimer").autocomplete("recipient?navigation=lookupjson", {
        dataType: "json",
        cacheLength: 0,
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
            return "<font color='#000' >"+ row.number +" - </font>" +    "<font color='#000' >"+ row.name +"</font>" ;
        },
        extraParams: {
       		recipient: function() { return $("#paymentRecipient").val(); },
       		clientid: function() { return $("#clientId").val(); }
   		}
    }).bind("result", function(data, value){
        $(this).parents("dd").find(".error_message").hide();
        $("#claimerId").val(value.id);
        
    });
    */
    
});
	
	function completeForm(){

         var id = document.getElementById("claimReceiveId").value;
         var clientId = document.getElementById("clientId");
         var clientName = document.getElementById("clientName");
         var claimerId = document.getElementById("claimerId");
         var claimerName = document.getElementById("claimer");
         var receiveDate = document.getElementById("batchClaimDate");
         
         //numbOfClaim
//          var totalClaim = document.getElementById("totalClaim");
         
         var recipient = document.getElementById("paymentRecipient").value;


         AJAXPaymentRecipientService.completeBatchClaimForm(id, {
              callback:function (res){
                    clientId.value = res.clientId;
                    clientName.value = res.clientName;
                    receiveDate.value = res.receiveDate;
//                     totalClaim.value = res.totalReceiving;
                    
                    if (recipient == 1){
                    	claimerId.value = res.memberGroupId;
                    	claimerName.value = res.memberGroupName;
                    }
                    else if (recipient == 2){
                    	claimerId.value = res.memberId;
                    	claimerName.value = res.memberName;
                    }
                    else if (recipient == 3){
                    	claimerId.value = res.providerId;
                    	claimerName.value = res.providerName;
                    }
                    
              }
         });
	}	
	function simpan (){

		document.form1.method = "POST";
		document.form1.action = "batchclaim-form";
		document.form1.submit();
	}
	function cancel (){
		document.form1.method = "GET";
		document.form1.action = "batchclaim";
		
		<c:if test="${navigation eq 'update'}">
		document.form1.navigation.value = "detail";
		</c:if>
		document.form1.submit();
		//document.form1.submit();
	}
	// forreign affairs
	
	function lookupRecipient (){
		var tipe = document.getElementById("paymentRecipient").value;
		
		var cid = document.getElementById("clientId").value;
		
		if (tipe == 1){
			if (cid == ''){
				window.alert("Please Specify Client");
			}
			else {
				window.open ("membergroup?navigation=lookup&url=batchclaim-form&clientId="+cid,"Search","width=700, height=550, menubar=no, status=no, toolbar=no, scrollbars=yes, resizable=yes,screenX=200,screenY=100");
			}
		}
		else if (tipe == 2){
			if (cid == ''){
				window.alert("Please Specify Client");
			}
			else {
				window.open ("member?navigation=lookup&url=batchclaim-form&clientId="+cid,"Search","width=700, height=550, menubar=no, status=no, toolbar=no, scrollbars=yes, resizable=yes,screenX=200,screenY=100");
			}
		}
		else if (tipe == 3){
			window.open ("provider?navigation=lookup&url=batchclaim-form","Search","width=700, height=550, menubar=no, status=no, toolbar=no, scrollbars=yes, resizable=yes,screenX=200,screenY=100");	
		}
	}
 function changeClaimType(){
                //window.alert("CHANGE CLAIM TYPE");
                var ctype = document.getElementById("ctype").value;
                var payRecipient = document.getElementById("paymentRecipient");


                AJAXPaymentRecipientService.getPaymentRecipient (ctype, {
                        callback:function (paymentRecipientRes){
                                paymentRecipient.innerHTML = paymentRecipientRes;


                        }


                });

        }

	function setMember (idx,nama){
		document.form1.notyet.value="true";
		document.form1.action = "batchclaim-form";
		//document.form1.batchClaimForm.memberGroupId.value=idx;
		document.getElementById("claimerId").value = idx;
		document.getElementById("claimer").value= nama;
		
		if (idx == ''){
			window.alert("Policy Holder is not yet Activated");
		}
	}
	


				// forreign affairs end
</script>
