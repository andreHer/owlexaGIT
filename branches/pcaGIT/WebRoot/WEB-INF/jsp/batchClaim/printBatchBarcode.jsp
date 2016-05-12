<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


      <script type="text/javascript" src="scripts/jquery/jquery-1.4.4.min.js"></script>
   		      <script type="text/javascript" src="scripts/jquery/jquery-barcode.js"></script>
   		

<form action="batchclaim" method="GET" name="form1" id="form_layout">

	<input type="hidden" name="navigation" value="">
	<input type="hidden" name="batchClaimId" value="<c:out value="${batchNumber}" />">
	
	<div id="barcodeTarget" class="barcodeTarget" ></div>
	
</form>
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
	// ----- 
	function generateBarcode(){
        //var value = "${batchClaim.batchClaimNumber}"; // $("#barcodeValue").val();
        var value = "${batchNumber}";
        var btype = "code128";//$("input[name=btype]:checked").val();
        var renderer ="css"; // $("input[name=renderer]:checked").val();
        
// 		var quietZone = false;
//         if ($("#quietzone").is(':checked') || $("#quietzone").attr('checked')){
//           quietZone = true;
//         }
		
        var settings = {
          output:renderer,
          bgColor: '#FFFFFF', 
          color:'#000000',
          barWidth: 1,
          barHeight: 80
        //  moduleSize: 5,
//           posX: 10,
//           posY: 20,
//           addQuietZone: 1
        };
//       if ($("#rectangular").is(':checked') || $("#rectangular").attr('checked')){
//            value = {code:value, rect: true};
//          }
//         if (renderer == 'canvas'){
//           clearCanvas();
//           $("#barcodeTarget").hide();
//           $("#canvasTarget").show().barcode(value, btype, settings);
//         } else {
//         $("#canvasTarget").hide();
          $("#barcodeTarget").html("").show().barcode(value, btype, settings);
//         }
       }
	
	      
      $(document).ready(function() {
     
    generateBarcode();
});
  </script>
