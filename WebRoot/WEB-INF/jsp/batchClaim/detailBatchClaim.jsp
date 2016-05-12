<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<%
Integer claimTotal = (Integer) request.getAttribute("claimTotal");
%>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Detail Batch Claim</h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>

<form action="batchclaim" method="GET" name="form1" id="form_layout">

	<input type="hidden" name="navigation" value="">
	<input type="hidden" name="batchClaimId" value="<c:out value="${batchClaim.batchClaimId}" />">
	<input type="hidden" name="claimTotal" id="claimTotal" value="<c:out value="${claimTotal}" />">
	<input type="hidden" name="batchClaimTotal" id="batchClaimTotal" value="<c:out value="${batchClaim.totalClaim}" />">
	
	
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr> 
      <td style="padding-bottom: 2px; padding-top: 8px;">        
      
      	<c:if test="${batchClaim.batchClaimStatus.caseStatusId eq 1}">
    
	        <c:if test="${theUser.roleId.roleId eq 0 or theUser.roleId.roleId eq 3 or theUser.roleId.roleId eq 4 or theUser.roleId.roleId eq 15}">
		        <input title="Edit [Alt+Shift+E]" accesskey="E" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:ubah()" name="Edit" value=" Update " type="button">
		        <input title="Hapus [Alt+Shift+H]" accesskey="H" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:hapus()" name="Delete" value=" Delete " type="button">
		                		
	        <input title="Add Claim [Alt+Shift+C]" accesskey="H" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:addClaim()" name="tambahClaim" value=" Add Claim " type="button">
	        
	        <c:if test="${batchClaim.batchClaimType.claimTypeId eq 2}">
	        <input title="Add Swipe [Alt+Shift+S]" accesskey="H" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:addSwipeClaim()" name="tambahClaim" value=" Add Swipe Claim " type="button">
	        
	        <input title="Add Case Claim [Alt+Shift+X]" accesskey="H" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:addCaseClaim()" name="tambahCaseClaim" value=" Add Case Claim " type="button">
	        
	        </c:if>
	                		
			<input title="Complete[Alt+Shift+E]" accesskey="P" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:completeBatch()" name="completBatch" value=" Complete Batch " type="button">
			</c:if>
        </c:if>
	
		
      	<c:if test="${batchClaim.batchClaimStatus.caseStatusId eq 5}">
			<c:if test="${theUser.roleId.roleId eq 0 or theUser.roleId.roleId eq 3 or theUser.roleId.roleId eq 4 or theUser.roleId.roleId eq 7 or theUser.roleId.roleId eq 8}">
	        	<input title="Pay Batch Claim [Alt+Shift+E]" accesskey="P" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:paymentBatch()" name="batchPayment" value=" Pay Batch " type="button">
	        </c:if>
        </c:if>
		<c:if test="${theUser.roleId.roleId eq 0 or theUser.roleId.roleId eq 3 or theUser.roleId.roleId eq 4 or theUser.roleId.roleId eq 15}">        		
		
      		<c:if test="${(batchClaim.batchClaimStatus.caseStatusId eq 12) or (batchClaim.batchClaimStatus.caseStatusId eq 13)  or 
	      		(batchClaim.batchClaimStatus.caseStatusId eq 5) or (batchClaim.batchClaimStatus.caseStatusId eq 15) or (batchClaim.batchClaimStatus.caseStatusId eq 6)}">
		        
		        <input title="Open Batch [Alt+Shift+E]" accesskey="P" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:openBatch()" name="opBatch" value=" Open Batch " type="button">
		        
		        <c:if test="${(theUser.roleId.roleId eq 0) and (openClaim > 0) }">
		        	<input title="Verify Bulk [Alt+Shift+V]" accesskey="V" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:verifyBulk()" name="verify" value=" Verify Bulk " type="button">
		        </c:if>
	        
	        	<input title="Print COB" accesskey="P" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:printCOB()" name="prCoB" value=" Print COB Claim " type="button">
	        	
	        
        	</c:if>
        
	        <c:if test="${(batchClaim.batchClaimStatus.caseStatusId eq 12)  or (batchClaim.batchClaimStatus.caseStatusId eq 15)}">
	        	<input title="Close Batch [Alt+Shift+C]" accesskey="P" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:closeBatch()" name="clBatch" value=" Close Batch " type="button">	        
	        </c:if>
	        	<% /* Edit 20032015, untuk pengecekan Surat Jalan Hanya akan Muncul jika sudah melakukan Pembayaran */ %>
	        <c:if test="${paymentbean ne null}">
<!-- 				<input title="Download Recap" accesskey="P" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:downloadRecap()" name="dlRecap" value=" Download Recap " type="button"> -->
<!-- 				<input title="Download Recap" accesskey="P" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:printDownloadRecap()" name="dlRecap" value=" Delivery Letter " type="button"> -->
			</c:if>
			</c:if>      
			
			<input title="Print Barcode" accesskey="B" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"
					onClick="javascript:printBarcode()" name="prBC" value=" Print Barcode " type="button" target="blank"> 
		</td>
		
      <td align="right"></td>
    </tr>
  </tbody>
</table>
</form>

<%@ include file="../mainContentBatchClaim.jsp" %>

<br />

<script language="javascript">
	function downloadRecap(){		
		document.form1.navigation.value = "downloadclaim";
		document.form1.action = "batchclaim";
		document.form1.method = "GET";
		document.form1.submit();		
	}
	function printCOB(){		
		window.open ("batchclaim?navigation=printcob&url=detailpayment&batchClaimId=<c:out value="${batchClaim.batchClaimId}" />","Search","width=1024, height=768, menubar=yes, status=no, toolbar=no, scrollbars=yes, resizable=yes");		
	}
	function hapus (){
		var delConfirm = window.confirm ("Are You Sure Want To Delete This Entry ?");
		if (delConfirm){
			document.form1.navigation.value = "delete";
			document.form1.submit();
		}
	}
	function ubah (){
		document.form1.navigation.value = "update";
		document.form1.action = "batchclaim-form";
		document.form1.method = "GET";
		document.form1.submit();
	}
	function verifyBulk(){
		var delConfirm = window.confirm ("Are You Sure Want To Do Bulk Verification over this Batch Claim ?");
		if (delConfirm){
			document.form1.navigation.value = "verifybulk";
			document.form1.submit();
		}
	}
	function kembali (){
		document.form1.navigation.value = "back";
		document.form1.submit();
	}
	function paymentBatch(){
		document.form1.navigation.value = "paybatch";
		document.form1.action = "payment-form";
		document.form1.submit();
	}
	
	function addSwipeClaim (){
		document.form1.navigation.value = "attachswipe";
		document.form1.action = "claim";
		document.form1.method = "GET";
		document.form1.submit();
	}
	function addCaseClaim (){
		document.form1.navigation.value = "attachcase";
		document.form1.action = "claim";
		document.form1.method = "GET";
		document.form1.submit();
	}
	function addClaim (){
		
		 /* var totalClaim = document.getElementById("claimTotal").value;
		 var batchClaimTotal = document.getElementById("batchClaimTotal").value;
		 
		
		if(totalClaim >= batchClaimTotal){
		alert("Penambahan claim tidak boleh dari batas maksimum");
		}
		
		else{
		} */
		document.form1.navigation.value = "tambah";
		document.form1.action = "claim-form";
		document.form1.method = "GET";
		document.form1.submit();
		
	}
	function completeBatch (){
		var delConfirm = window.confirm ("Are You Sure Want To Complete This Batch ?");
	
		if (delConfirm){
			document.form1.navigation.value = "complete";
			document.form1.action = "batchclaim";
			document.form1.method = "GET";
			document.form1.submit();
		}
	}
	function openBatch (){
		var delConfirm = window.confirm ("Are You Sure Want To ReOpen This Batch ?");
	
		if (delConfirm){
			document.form1.navigation.value = "open";
			document.form1.action = "batchclaim";
			document.form1.method = "GET";
			document.form1.submit();
		}
	}
	function closeBatch (){
	
		var delConfirm = window.confirm ("Are You Sure Want To Close This Batch ?");
	
		if (delConfirm){
			document.form1.navigation.value = "close";
			document.form1.action = "batchclaim";
			document.form1.method = "GET";
			document.form1.submit();
		}
	}
	function printPayment(){
		window.open ("batchclaim?navigation=printpayment&url=detailpayment&batchClaimId=<c:out value="${batchClaim.batchClaimId}" />","Search","width=1024, height=768, menubar=yes, status=no, toolbar=no, scrollbars=yes, resizable=yes");
	}
	function printPaymentDetail(){
		window.open ("batchclaim?navigation=printpaymentdetails&url=detailpayment&batchClaimId=<c:out value="${batchClaim.batchClaimId}" />","Search","width=1024, height=768, menubar=yes, status=no, toolbar=no, scrollbars=yes, resizable=yes");
	}
	function printBarcode(){		
		window.open ("batchclaim?navigation=printbarcode&batchClaimId=<c:out value="${batchClaim.batchClaimId}" />","Search","width=1024, height=768, menubar=yes, status=no, toolbar=no, scrollbars=yes, resizable=yes");		
	}
	function printDownloadRecap(){		
		window.open ("batchclaim?navigation=printdownloadrecap&batchClaimId=<c:out value="${batchClaim.batchClaimId}" />","Search","width=1024, height=768, menubar=yes, status=no, toolbar=no, scrollbars=yes, resizable=yes");		
	}
</script>
