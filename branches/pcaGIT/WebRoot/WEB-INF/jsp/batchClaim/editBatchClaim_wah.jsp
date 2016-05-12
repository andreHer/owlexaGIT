<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<script type='text/javascript' src='dwr/interface/AJAXPaymentRecipientService.js'></script>
<script type='text/javascript' src='dwr/engine.js'></script>
<script type='text/javascript' src='dwr/util.js'></script>

<form action="batchclaim-form" method="POST"  name="form1" id="form_layout">
<input type="hidden" name="navigation" value="">
<spring:bind path="batchClaimForm.batchClaimId">
		<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td style="padding-bottom: 2px;">
	    <input type="hidden" name="notyet" value="">
        <input title="Save [Alt+Shift+S]" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:simpan()" name="Save" value=" Save " type="submit">
        <input title="Cancel [Alt+Shift+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:cancel()" name="Cancel" value=" Cancel " type="button">         
	  </td>
      <td align="right"></td>
    </tr>
  </tbody>
</table>
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
	<td class="dataLabel"> Received Date : </td>				
			

						<td class="dataField">
			<spring:bind path="batchClaimForm.batchClaimDate">
					<input type="text" name="<c:out value="${status.expression}" />" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="10">
					<img src="images/jscalendar.gif" alt="Enter Date" id="<c:out value="${status.expression}"/>_trigger" align="absmiddle" height="20" width="20">
					<script type="text/javascript">
    					Calendar.setup({
        					inputField     :    "<c:out value="${status.expression}"/>",     // id of the input field
        					ifFormat       :    "%Y-%m-%d",      // format of the input field
        					button         :    "<c:out value="${status.expression}"/>_trigger",  // trigger for the calendar (button ID)
        					align          :    "Tl",           // alignment (defaults to "Bl")
        					singleClick    :    true
    					});
				 	</script>
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>

		
				<td class="dataLabel"> Due Date : </td>				
			

						<td class="dataField">
			<spring:bind path="batchClaimForm.batchDueDate">
					<input type="text" name="<c:out value="${status.expression}" />" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="10">
					<img src="images/jscalendar.gif" alt="Enter Date" id="<c:out value="${status.expression}"/>_trigger" align="absmiddle" height="20" width="20">
					<script type="text/javascript">
    					Calendar.setup({
        					inputField     :    "<c:out value="${status.expression}"/>",     // id of the input field
        					ifFormat       :    "%Y-%m-%d",      // format of the input field
        					button         :    "<c:out value="${status.expression}"/>_trigger",  // trigger for the calendar (button ID)
        					align          :    "Tl",           // alignment (defaults to "Bl")
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
				<td class="dataLabel">Claim Type : </td>		
				<td class="dataField">
				<spring:bind path="batchClaimForm.batchClaimType.claimTypeId">
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
			   
			   <td class="dataLabel">Payment Recipient : </td>		
		<td class="dataField">
		<spring:bind path="batchClaimForm.paymentRecipient.paymentRecipientId">
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
			<td class="dataLabel">
									Client :
								</td>


								<td class="dataField">
									<spring:bind path="batchClaimForm.clientId.clientId">
										<input type="hidden" id="clientId" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" />
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
									<spring:bind path="batchClaimForm.clientId.clientName">
										<input type="text" readonly="readonly" id="clientName" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" />
										<input type="button" name="PILIHCLIENT" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" value="Lookup" onClick="javascript:lookupClient()">
									</spring:bind>
								</td>
		
	 	<td class="dataLabel">Priority : </td>		
			<td class="dataField">
		<spring:bind path="batchClaimForm.priority.priorityId">
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
								
								
								<td class="dataLabel">Recipient : </td>		
		<td class="dataField">
		<spring:bind path="batchClaimForm.claimerId">
			<input type="hidden" id="claimerId" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />">
		</spring:bind>
	
		<spring:bind path="batchClaimForm.claimer">
		<input type="text" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" readonly="readonly">
				<input type="button" name="PILIH" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" value="Lookup" onClick="javascript:lookupRecipient()">
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>	

		</td>
								<td class="dataLabel">
									Payment Method :
								</td>


								<td class="dataField">
									<spring:bind path="batchClaimForm.paymentMethod.paymentMethodId">
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

							</tr>
		<tr>
	  	<td class="dataLabel"> Claim Currency : </td>				
			

			<td class="dataField">
			<spring:bind path="batchClaimForm.claimCurrency.currencyId">
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
			<spring:bind path="batchClaimForm.paymentCurrency.currencyId">
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
				<td class="dataLabel">&nbsp; </td>				
			

			<td class="dataField">
		&nbsp;
		</td>
	  		<td class="dataLabel">&nbsp; </td>				
			

			<td class="dataField">
		&nbsp;
		</td>

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
	   <td class="dataLabel"> Batch Amount : </td>				
			
			<td class="dataField">
			<spring:bind path="batchClaimForm.batchClaimInitialValue">
					<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="22">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
	</tr>
			

			
			<tr>
				<td class="dataLabel"> Invoice Number : </td>				
			

			<td class="dataField">
			<spring:bind path="batchClaimForm.invoiceNumber">
		<input type="text" size="35" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" />	
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	   	<td class="dataLabel"> Number of Claim : </td>				
			<td class="dataField">
			<spring:bind path="batchClaimForm.totalClaim">
	<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<fmt:formatNumber><c:out value="${status.value}"/></fmt:formatNumber>" maxlength="11">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
	</tr>
	
	
			
			<tr>
				<td class="dataLabel"> Invoice Date : </td>				
			

						<td class="dataField">
			<spring:bind path="batchClaimForm.invoiceDate">
					<input type="text" name="<c:out value="${status.expression}" />" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="10">
					<img src="images/jscalendar.gif" alt="Enter Date" id="<c:out value="${status.expression}"/>_trigger" align="absmiddle" height="20" width="20">
					<script type="text/javascript">
    					Calendar.setup({
        					inputField     :    "<c:out value="${status.expression}"/>",     // id of the input field
        					ifFormat       :    "%Y-%m-%d",      // format of the input field
        					button         :    "<c:out value="${status.expression}"/>_trigger",  // trigger for the calendar (button ID)
        					align          :    "Tl",           // alignment (defaults to "Bl")
        					singleClick    :    true
    					});
				 	</script>
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>		
	   	<td class="dataLabel"> Biaya Materai : </td>				
			

						<td class="dataField">
			<spring:bind path="batchClaimForm.biayaMaterai">
	<input type="text"  name="<c:out value="${status.expression}" />" value="<fmt:formatNumber><c:out value="${status.value}"/></fmt:formatNumber>" maxlength="11">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
	</tr>
	
	

<tr>
		<td class="dataLabel"></td>		
		<td class="dataField">&nbsp;</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>


		
	

		
	

			<tr>
				<td class="dataLabel"> Description : </td>				
			

		    <td class="dataField" colspan="2">
			<spring:bind path="batchClaimForm.description">
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
      <td style="padding-bottom: 2px;">
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
		document.form1.action = "batchclaim-form";
		document.form1.submit();
	}
	function cancel (){
		history.back(-1);
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
	
	function lookupClient (){
		window.open ("client?navigation=lookup&url=batchclaim-form","Search","width=700, height=550, menubar=no, status=no, toolbar=no, scrollbars=yes, resizable=yes,screenX=200,screenY=100");
	}
	function setClient (idx,nama){

		document.form1.action = "batchclaim-form";
		//document.form1.batchClaimForm.clientId.value=idx;
		document.getElementById("clientId").value = idx;
		document.getElementById("clientName").value = nama;

	}
	
	function setMemberGroup (idx, nama){

		document.form1.notyet.value="true";
		document.form1.action = "batchclaim-form";
		//document.form1.batchClaimForm.memberGroupId.value=idx;
		document.getElementById("claimerId").value = idx;
		document.getElementById("claimer").value= nama;

	}
	function lookupProvider (){
		window.open ("provider?navigation=lookup&url=batchclaim-form","Search","width=700, height=550, menubar=no, status=no, toolbar=no, scrollbars=yes, resizable=yes,screenX=200,screenY=100");
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
	function setProvider (idx,nama){

		document.form1.notyet.value="true";
		document.form1.action = "batchclaim-form";
		//document.form1.batchClaimForm.memberGroupId.value=idx;
		document.getElementById("claimerId").value = idx;
		document.getElementById("claimer").value= nama;

	}
	
				// forreign affairs end
</script>
