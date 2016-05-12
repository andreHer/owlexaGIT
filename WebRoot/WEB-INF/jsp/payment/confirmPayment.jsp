<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<form action="payment-form" method="POST"  name="form1" id="form_layout">
	<spring:bind path="paymentForm.paymentId">
		<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>

<spring:bind path="paymentForm.batchClaim">
		<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>
		
<input type="hidden" name="navigation" value="">

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td style="padding-bottom: 2px;">
	    <input type="hidden" name="notyet" value="">
	    
        <input title="Save [Alt+Shift+S]" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:simpan()" name="Save" value=" Save Payment " type="submit">
        
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
				<td class="dataLabel"> Nomor Batch : </td>				
			

			<td class="dataField">
			<spring:bind path="paymentForm.batchNumber">
				<input class="inputtext" type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" / readonly="readonly">
			
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
				<td class="dataLabel"> Pengirim Batch : </td>				
			

			<td class="dataField">
			<spring:bind path="paymentForm.paymentRecipient">
				<input class="inputtext" type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" />
			
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>	</tr>
	


			<tr>
				<td class="dataLabel"> Payment Value : </td>				
			
			<td class="dataField">
			<spring:bind path="paymentForm.paymentValue">
					<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="22">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>




			

			<tr>
			<td class="dataLabel"> Bank Name : </td>				
			

			<td class="dataField">
			<spring:bind path="paymentForm.bankName">
				<input class="inputtext" type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" />
			
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
			<td class="dataLabel"> Bank Branch : </td>				
			

			<td class="dataField">
			<spring:bind path="paymentForm.bankBranch">
				<input class="inputtext" type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" />
			
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>	</tr>

			





	
	


			

			<tr>
				<td class="dataLabel"> Account Number : </td>				
			

			<td class="dataField">
			<spring:bind path="paymentForm.accountNumber">
				<input class="inputtext" type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" />
			
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	    <td class="dataLabel"> Payee Name : </td>				
			

			<td class="dataField">
			<spring:bind path="paymentForm.payeeName">
				<input class="inputtext" type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" />
			
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>

	</tr>
	


			

			<tr>
				<td class="dataLabel"> Giro Number : </td>				
			

			<td class="dataField">
			<spring:bind path="paymentForm.giroNumber">
				<input class="inputtext" type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" />
			
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
	


				
			


			

			

			
			


			

		
	

			<tr>
				<td class="dataLabel"> Remarks : </td>				
			

		    <td class="dataField" colspan="3">
			<spring:bind path="paymentForm.remarks">
			<textarea rows="8" cols="45" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
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
	    
        <input title="Save [Alt+Shift+S]" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:simpan()" name="Save" value=" Save Payment " type="submit">
        
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
		document.form1.action = "payment-form";
		document.form1.submit();
	}
	function kembali (){
		document.form1.navigation.value = "back";
		document.form1.action = "payment";
		document.form1.submit();
	}
	// forreign affairs
		function lookupOutstanding (){
		window.open ("outstanding?navigation=lookup&url=payment-form","Search","width=700, height=550, menubar=no, status=no, toolbar=no, scrollbars=yes, resizable=yes,screenX=200,screenY=100");
	}
	function setOutstanding (idx){
		document.form1.method = "POST";
		document.form1.notyet.value="true";
		document.form1.action = "payment-form";
		//document.form1.paymentForm.outstandingId.value=idx;
		document.getElementById("outstandingId").value = idx;
		document.form1.submit();
		}
			function lookupMember (){
		window.open ("member?navigation=lookup&url=payment-form","Search","width=700, height=550, menubar=no, status=no, toolbar=no, scrollbars=yes, resizable=yes,screenX=200,screenY=100");
	}
	function setMember (idx){
		document.form1.method = "POST";
		document.form1.notyet.value="true";
		document.form1.action = "payment-form";
		//document.form1.paymentForm.memberId.value=idx;
		document.getElementById("memberId").value = idx;
		document.form1.submit();
		}
			function lookupProvider (){
		window.open ("provider?navigation=lookup&url=payment-form","Search","width=700, height=550, menubar=no, status=no, toolbar=no, scrollbars=yes, resizable=yes,screenX=200,screenY=100");
	}
	function setProvider (idx){
		document.form1.method = "POST";
		document.form1.notyet.value="true";
		document.form1.action = "payment-form";
		//document.form1.paymentForm.providerId.value=idx;
		document.getElementById("providerId").value = idx;
		document.form1.submit();
		}
				// forreign affairs end
</script>