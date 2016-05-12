<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Detail Payment</h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>

<form action="payment" method="GET" name="form1" id="form_layout">
<input type="hidden" name="navigation" value="">
	<input type="hidden" name="paymentId" value="<c:out value="${payment.paymentId}" />">
	
	
		
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr> 
      <td style="padding-bottom: 2px;">        
        <c:if test="${(theUser.roleId.roleId eq 0 or theUser.roleId.roleId eq 4 or theUser.roleId.roleId eq 7) and payment.paymentStatus.paymentStatusId eq 1 }">
 
        <input title="Edit [Alt+Shift+E]" accesskey="E" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:ubah()" name="Edit" value=" Update " type="button">
        <input title="Hapus [Alt+Shift+H]" accesskey="H" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:hapus()" name="Delete" value=" Delete " type="button">
 		</c:if>
 		<c:if test="${theUser.roleId.roleId eq 3 or theUser.roleId.roleId eq 4 or theUser.roleId.roleId eq 7 or theUser.roleId.roleId eq 8}">
	        <input title="Print Payment [Alt+Shift+P]" accesskey="P" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:printPayment()" name="printPay" value=" Print Payment " type="button">
	        <input title="Print Payment Detail [Alt+Shift+P]" accesskey="X" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:printPaymentDetail()" name="printPayDet" value=" Print Payment Detail " type="button">
	        <input title="Print Payment Detail [Alt+Shift+P]" accesskey="X" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:downloadExcell()" name="down" value=" Download Report " type="button">
	        <input title="Print Payment Detail [Alt+Shift+P]" accesskey="X" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:downloadClaim()" name="downC" value=" Download Claim " type="button">
        </c:if>
        <c:if test="${theUser.roleId.roleId eq 3 or theUser.roleId.roleId eq 4 or theUser.roleId.roleId eq 7 or theUser.roleId.roleId eq 8}">
	        <c:if test="${(payment.dispositionDate eq null and payment.paymentStatus.paymentStatusId eq 1) or payment.paymentStatus.paymentStatusId eq 8}">
				<input title="CDV Disposition [Alt+Shift+P]" accesskey="C" 
				class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:sentPayment()" name="sentPay" 
					value=" Send Payment " type="button">
			</c:if>
	         
		</c:if>
		
		<c:if test="${(payment.paymentStatus.paymentStatusId eq 1 or payment.paymentStatus.paymentStatusId eq 6 
			or payment.paymentStatus.paymentStatusId eq 7 or payment.paymentStatus.paymentStatusId eq 8) and (theUser.roleId.roleId eq 7 or theUser.roleId.roleId eq 8)}">
			<input title="Confirm Payment [Alt+Shift+P]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:confirmPay()" name="confPayDet" value=" Confirm Payment " type="button">
		</c:if>
		<c:if test="${theUser.roleId.roleId eq 4}">
		<input title="Download Recap" accesskey="P" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:printDeliveryLetter()" name="dlRecap" value=" Delivery Letter " type="button">
		</c:if>
				</td>
      <td align="right"></td>
    </tr>
  </tbody>
</table>

<%@ include file="../mainContentPayment.jsp" %>

<br />

</form>
<script language="javascript">
	function hapus (){
	var delConfirm = window.confirm ("Are You Sure Want To Delete This Entry ?");
		if (delConfirm){
			document.form1.navigation.value = "delete";
			document.form1.submit();
		}
	}
	function confirmPay(){
		
		
		var delConfirm = window.confirm ("Are You Sure Want To Confirm This Payment ?");
		if (delConfirm){
			document.form1.navigation.value = "confirm";
			document.form1.action = "payment-form";
			document.form1.submit();
		}
	}
	function sentPayment(){
		var delConfirm = window.confirm ("Are You Sure Want To Deliver This Payment ?");
		if (delConfirm){
			document.form1.navigation.value = "deliver";
			document.form1.action = "payment-form";
			document.form1.submit();
		}
	}
	function kembali (){
		document.form1.navigation.value = "back";
		document.form1.submit();
	}
	function ubah (){
		document.form1.navigation.value = "update";
		document.form1.action = "payment-form";
		document.form1.method = "GET";
		document.form1.submit();
	}
	function downloadExcell (){
		document.form1.navigation.value = "downloadexcel";
		document.form1.action = "payment";
		document.form1.method = "GET";
		document.form1.submit();
	}
	function downloadClaim (){
		document.form1.navigation.value = "downloadclaim";
		document.form1.action = "payment";
		document.form1.method = "GET";
		document.form1.submit();
	}
	function printPayment(){
		window.open ("payment?navigation=printpayment&url=detailpayment&paymentId=<c:out value="${payment.paymentId}" />","Search","width=1024, height=768, menubar=yes, status=no, toolbar=no, scrollbars=yes, resizable=yes");
	}
	function printPaymentDetail(){
		window.open ("payment?navigation=printpaymentdetails&url=detailpayment&paymentId=<c:out value="${payment.paymentId}" />","Search","width=1024, height=768, menubar=yes, status=no, toolbar=no, scrollbars=yes, resizable=yes");
	}
	function printCashCredit(){
		window.open ("payment?navigation=printcc&url=detailpayment&paymentId=<c:out value="${payment.paymentId}" />","Search","width=1024, height=768, menubar=yes, status=no, toolbar=no, scrollbars=yes, resizable=yes");
	}
	function printDeliveryLetter(){		
		window.open ("payment?navigation=printdeliveryletter&paymentId=<c:out value="${payment.paymentId}" />","Search","width=1024, height=768, menubar=yes, status=no, toolbar=no, scrollbars=yes, resizable=yes");		
	}
</script>
