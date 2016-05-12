<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<script type='text/javascript' src='dwr/interface/AJAXDiagnosisService.js'></script>
<script type='text/javascript' src='dwr/engine.js'></script>
<script type='text/javascript' src='dwr/util.js'></script>
<link rel="stylesheet" type="text/css" href="css/yui.css">
<link rel="stylesheet" type="text/css" href="css/dpSyntaxHighlighter.css">
<link rel="stylesheet" type="text/css" href="css/autocomplete.css">
<link rel="stylesheet" type="text/css" href="css/button.css">


<form action="claim" method="POST"  name="form1" id="form_layout">
<input type="hidden" name="navigation" value="saveextract">


		<input type="hidden" name="claimId" value="<c:out value="${claimId}" />" >


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
				<td class="dataLabel"> </td>				
			

		    <td class="dataField" colspan="3">&nbsp;</td>

	</tr>	
	<tr>
		<td class="dataLabel">Batch Number : </td>		
		<td class="dataLabel">
		
		<input type="hidden" name="batchClaimId"  value="<c:out value="${claim.batchClaimId.batchClaimId}" />">
		<c:out value="${claim.batchClaimId.batchClaimNumber}" />
		

		</td>
	    
	    <td class="dataLabel">Claim Service : </td>		
		<td class="dataLabel"><c:out value="${claim.caseCategoryId.caseCategoryName}" /></td>
	</tr>	
			<tr>
				<td class="dataLabel">Claim Number : </td>				
		<td class="dataLabel"><c:out value="${claim.claimNumber}" /></td>
	    <td class="dataLabel">Destination Batch :</td>
	    <td class="dataField">
	    	
	    	<input type="hidden" name="afterBatchClaimId" value="">
			<input type="text" size="20" disabled="disabled" name="afterBatchClaimNumber" value="">
			
			<input type="button" name="PILIH" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" value="Lookup" onClick="javascript:lookupBatch()">
		</td>

	</tr>
			
	<tr>
				<td class="dataLabel"> &nbsp; </td>				
			
			<td class="dataField">&nbsp;
			</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
			
			
				
		


			
			<tr>
				<td class="dataLabel"> Description : </td>				
			

		    <td class="dataField" colspan="3">
			
			<textarea cols="60" rows="8" class="inputbox" name=""></textarea>
			
		</td>

	</tr>


			
			<tr>
				<td class="dataLabel"> </td>				
			

		    <td class="dataField" colspan="3">&nbsp;</td>

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
      <td style="padding-top: 2px;padding-left: 1px;">
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
		document.form1.action = "claim";
		document.form1.navigation.value = "saveextract";
		document.form1.submit();
	}
	function cancel(){
	
		
		document.form1.action = "claim";
		document.form1.navigation.value = "detail";
		document.form1.submit();
	}	
	function setBatchClaim(batchId,batchNumber){
		document.form1.afterBatchClaimId.value = batchId;
		document.form1.afterBatchClaimNumber.value = batchNumber;
	}
	
	function lookupBatch (){
		window.open ("batchclaim?navigation=lookupextract&url=claim&claimId=<c:out value="${claimId}" />","Search","width=700, height=550, menubar=no, status=no, toolbar=no, scrollbars=yes, resizable=yes,screenX=200,screenY=100");
	}
			// forreign affairs end
</script>