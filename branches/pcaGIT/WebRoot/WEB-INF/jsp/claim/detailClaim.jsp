<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<%
String alert = (String) request.getAttribute("alert");
int index = 0;
int totalIndex = 0;
int count = 0;
int countSet = 0;

try {
	index = ((Integer) request.getAttribute ("index")).intValue();
	count = ((Integer) request.getAttribute ("count")).intValue();
	countSet = ((Integer) request.getAttribute ("countSet")).intValue();
	totalIndex = ((Integer) request.getAttribute ("halAkhir")).intValue();
	
	
	
}
catch (Exception e){
}
String rowclass = "";
int i=0;
%>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Detail Claim</h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
      <c:if test="${theUser.userType eq 2 }">
	      <td align="right">
	      	<input title="Error Log"  name="errorLog" value=" Error Log " id="errorLog" class="errorLog" type="button" onClick="javascript:printErrorLog()">
	      </td>
	      <td align="right">
	      	<input title="Add Error Log"  name="addErrorLog" value=" Add Error Log " class="errorLog" type="button" onClick="javascript:adderrorlog()">
	      </td>
      </c:if>
    </tr>
  </tbody>
</table>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr> 
      <td style="padding-bottom: 2px; padding-top: 5px;">   
      	<%-- Edit 20150414 by FVO, penambahan setting agar button di hidden saat status Claim masih berupa Tentative/Sementara --%>
        <c:if test="${claim.claimStatus.caseStatusId ne 19 }">
	        <c:if test="${theUser.userType eq 2}">
		   	   	<c:if test="${theUser.roleId.roleId eq 0 or theUser.roleId.roleId eq 4 or theUser.roleId.roleId eq 16  or theUser.roleId.roleId eq 1 or theUser.roleId.roleId eq 2 }">
	            
				  <c:if test="${(claim.claimStatus.caseStatusId eq 11 ) or (claim.claimStatus.caseStatusId eq 3)}">	             
						<input title="Reject [Alt+Shift+R]" accesskey="R" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:rejectClaim()" name="reject" value=" Reject " type="button">
											
		           		<input title="Pending [Alt+Shift+P]" accesskey="P" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:pendingClaim()" name="pending" value=" Pending " type="button">
		           		
		          </c:if>
	          </c:if>
	          <c:if test="${claim.claimStatus.caseStatusId eq 1}">
	          	
		           <c:if test="${theUser.roleId.roleId eq 0}">
			           <input title="Edit [Alt+Shift+E]" accesskey="E" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:ubah()" name="Edit" value=" Update " type="button">
				       <input title="Hapus [Alt+Shift+H]" accesskey="H" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:hapus()" name="Delete" value=" Delete " type="button">
				   </c:if>
				   
				   <c:if test="${theUser.roleId.roleId eq 3 or theUser.roleId.roleId eq 4 or theUser.roleId.roleId eq 1 or theUser.roleId.roleId eq 2}">
			           <input title="Edit [Alt+Shift+E]" accesskey="E" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:ubah()" name="Edit" value=" Update " type="button">
			           
			           <input title="Add Item [Alt+Shift+I]" accesskey="I" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:addClaimItem()" name="addclaim" value=" Update Claim Item " type="button">
			           
		           <input title="Extract Claim [Alt+Shift+X]" accesskey="X" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:extractClaim()" name="extract" value=" Extract Claim " type="button">
		           <input title="Complete [Alt+Shift+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:completeClaim()" name="complete" value=" Complete Claim " type="button">		       
				   </c:if>
		           
		                     
				  </c:if>
			   	   	<c:if test="${theUser.roleId.roleId eq 0 or theUser.roleId.roleId eq 4 or theUser.roleId.roleId eq 16  or theUser.roleId.roleId eq 1 or theUser.roleId.roleId eq 2 }">
			   	
					  <c:if test="${claim.claimStatus.caseStatusId eq 11}">			           
						<c:if test="${claim.isClausulExcluded eq 0}">
							<c:if test="${claim.admissionDate >= claim.memberId.effectiveDate and claim.admissionDate <= claim.memberId.expireDate }">
				           		<input title="Reject [Alt+Shift+R]" accesskey="R" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:rejectClaim()" name="reject" value=" Reject " type="button">
				           		<input title="Verify [Alt+Shift+V]" accesskey="V" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:approveClaim()" name="approve" value=" Approve " type="button">           		           		
			           		</c:if>  
						</c:if>
						<c:if test="${claim.isClausulExcluded eq 1}">
							<c:if test="${claim.admissionDate >= claim.memberId.effectiveDate and claim.admissionDate <= claim.memberId.expireDate }">
				           		<input title="Approve [Alt+Shift+V]" accesskey="V" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:approveExGratiaClaim()" name="appExGratia" value=" Approve Ex Gratia " type="button">           		           		
			           		</c:if>  
						</c:if>     
			           <input title="Complete [Alt+Shift+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:completeClaim()" name="complete" value=" Complete Claim " type="button">
						
			          </c:if>
				          <c:if test="${claim.claimStatus.caseStatusId eq 3}">
				          		<c:if test="${claim.memberId.resignedDate eq claim.memberId.expireDate and claim.admissionDate <= claim.memberId.expireDate}">
				              		<input title="Benefit Check [Alt+Shift+B]" accesskey="B" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:benefitCheck()" name="benefitcheck" value=" Benefit Check Claim " type="button">
				              	</c:if>      
				              	<c:if test="${claim.memberId.resignedDate ne claim.memberId.expireDate and claim.admissionDate <= claim.memberId.resignedDate}">
				              		<input title="Benefit Check [Alt+Shift+B]" accesskey="B" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:benefitCheck()" name="benefitcheck" value=" Benefit Check Claim " type="button">
				              	</c:if>      
				              	<c:if test="${claim.memberId.resignedDate eq null and claim.admissionDate <= claim.memberId.expireDate}">
				              		<input title="Benefit Check [Alt+Shift+B]" accesskey="B" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:benefitCheck()" name="benefitcheck" value=" Benefit Check Claim " type="button">
				              	</c:if>
				          </c:if>   
		          	
			          	<c:if test="${claim.claimStatus.caseStatusId eq 10}">
				              <input title="Complete [Alt+Shift+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:completePendingClaim()" name="complete" value=" Complete Pending Claim " type="button">
				              <input title="Extract Claim [Alt+Shift+X]" accesskey="X" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:extractClaim()" name="extract" value=" Extract Claim " type="button">          
				              <input title="Print Pending Letter [Alt+Shift+X]" accesskey="P" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:printPending()" name="pending" value=" Print Pending " type="button">
				                                                    
			          	</c:if>          
		          
			        	<c:if test="${(claim.claimStatus.caseStatusId eq 8) or (claim.claimStatus.caseStatusId eq 13) 
			        		or (claim.claimStatus.caseStatusId eq 3) or (claim.claimStatus.caseStatusId eq  11) }">
			        		<input title="Open Claim [Alt+Shift+O]" accesskey="O" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:openClaim()" name="opClaim" value=" Open Claim " type="button">
			        	</c:if> 
						<c:if test="${(claim.claimStatus.caseStatusId eq 8)  }">
			        		<input title="Void Claim [Alt+Shift+O]" accesskey="V" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:voidClaim()" name="voClaim" value=" Void Claim " type="button">
			        	</c:if> 
						<c:if test="${(claim.claimTypeId.claimTypeId eq 2) and (claim.claimExcessValue gt 0) and (claim.claimStatus.caseStatusId eq 6)}">
							<input title="Print Excess Letter [Alt+Shift+X]" accesskey="E" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:printExcessClaim()" name="excess" value=" Print Excess " type="button">
						</c:if>			
			  			<c:if test="${claim.claimStatus.caseStatusId eq 4}">
			  				<input title="Print Reject Letter [Alt+Shift+X]" accesskey="E" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:printReject()" name="excessReject" value=" Print Reject " type="button">
			  				
		   	   				<c:if test="${theUser.roleId.roleId eq 0 or theUser.roleId.roleId eq 4 }">
								<input title="Cancel Reject [Alt+Shift+X]" accesskey="E" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:cancelReject()" name="cancelRejc" value=" Cancel Reject " type="button">								   	   				
			  				</c:if>
			  			</c:if>  
		  			</c:if>      
		  			<c:if test="${claim.claimStatus.caseStatusId eq 8 or claim.claimStatus.caseStatusId eq 6}">
			 			<input title="Print Struct [Alt+Shift+P]" accesskey="E" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:printStruct()" name="struct" value=" Print Struct " type="button">
			 		</c:if>
				</c:if>
			</c:if>
		</td>
		
      <td align="right"></td>
    </tr>
  </tbody>
</table>

<%@ include file="../mainContentClaim.jsp" %>

<br/>
<form action="claim" method="GET" name="form1" id="form_layout">
<input type="hidden" name="navigation" value="">
<input type="hidden" name="claimId" value="<c:out value="${claim.claimId}" />">
<input type="hidden" name="pendingClaimId" value="<c:out value="${pendingClaimId}" />">
<input type="hidden" name="batchClaimId" value="<c:out value="${claim.batchClaimId.batchClaimId}" />" >
<input type="hidden" name="correctionType" value="claim" />
	
</form>
<script language="javascript">
$(document).ready(function(){
	$.get("firstcall?navigation=jsontotalclaimelog&claimId=<c:out value="${claim.claimId }"/>", function(data){
	  //alert("Data: " + data);
	  if(data>0){
	  	blinker();
	  }
	});
});
function blinker(){
	document.getElementById("errorLog").style.backgroundColor="red";
	setTimeout("document.getElementById('errorLog').style.backgroundColor=''", 500);
	setTimeout("blinker()",1500);
}

	function printReject(){
		window.open ("rejectedclaim?navigation=print&claimId=<c:out value="${claim.claimId}" />&url=claim-form","Search","width=1024, height=768, menubar=yes, status=no, toolbar=no, scrollbars=yes, resizable=yes");
	}
	function printPending(){
		window.open ("pendingclaim?navigation=print&claimId=<c:out value="${claim.claimId}" />&url=claim-form","Search","width=1024, height=768, menubar=yes, status=no, toolbar=no, scrollbars=yes, resizable=yes");
	}
	function printExcessClaim (){
	
		window.open ("report?navigation=printExcessLetter&claimId=<c:out value="${claim.claimId}" />&url=claim-form","Print Excess Claim","width=800, height=600, menubar=no, status=no, toolbar=no, scrollbars=yes, resizable=yes,screenX=200,screenY=100");		
	
	}
	function printStruct(){
		window.open ("claim?navigation=printclaimrki&claimId=<c:out value="${claim.claimId}" />&url=claim-form","Search","width=300, height=500, menubar=yes, status=no, toolbar=no, scrollbars=yes, resizable=yes");
	}
	function cancelReject (){
		
			document.form1.action = "claim";
			document.form1.navigation.value = "precancelreject";	
			document.form1.method = "GET";		
			document.form1.submit();
			
		}
	function requestCorrection(){
		
		var delConfirm = window.confirm ("Are You Sure Want To Correct This Entry ?");
		if (delConfirm){
			document.form1.action = "problem-form";
			document.form1.method = "GET";
			document.form1.navigation.value = "correction";
			document.form1.submit();
		}
	}
	function extractClaim (){
	
		document.form1.action = "claim";
		document.form1.method = "GET";
		document.form1.navigation.value = "extract";
		document.form1.submit();
	}
	
	<c:if test="${claim.claimStatus.caseStatusId eq 1}" >
	function hapus (){
	var delConfirm = window.confirm ("Are You Sure Want To Delete This Entry ?");
		if (delConfirm){
			document.form1.navigation.value = "delete";
			document.form1.submit();
		}
	}
	function kembali (){
		document.form1.navigation.value = "back";
		document.form1.submit();
	}
	
	function ubah (){
		document.form1.navigation.value = "update";
		document.form1.action = "claim-form";
		document.form1.method = "GET";
		document.form1.submit();
	}
	
	
	function addClaimItem (){		
		document.form1.action = "claimitem";
		document.form1.navigation.value = "addclaimitem";
		document.form1.method = "GET";		
		document.form1.submit();		
	}
	</c:if>
	
	<c:if test="${claim.claimStatus.caseStatusId eq 3}" >
		function benefitCheck (){
			document.form1.action = "claimitem";
			document.form1.navigation.value = "checkitembulk";	
			document.form1.method = "GET";		
			document.form1.submit();
		}
	</c:if>

	<c:if test="${claim.claimStatus.caseStatusId eq 10}">
	    function completePendingClaim (){
		
			document.form1.action = "claimcompletion-form";
			document.form1.navigation.value = "complete";
			document.form1.method = "GET";		
			document.form1.submit();		
		}
	
	</c:if>
	function completeClaim (){
		
			var delConfirm = window.confirm ("Are You Sure Want To Complete This Claim ?");
			if (delConfirm){
				document.form1.action = "claim";
				document.form1.navigation.value = "complete";
				document.form1.method = "GET";		
				document.form1.submit();	
			}	
		}
		function openClaim (){
		
			var delConfirm = window.confirm ("Are You Sure Want To ReOpen This Claim ?");
			if (delConfirm){
				document.form1.action = "claim";
				document.form1.navigation.value = "open";
				document.form1.method = "GET";		
				document.form1.submit();
			}		
		}
	function pendingClaim (){
		document.form1.action = "pendingclaim-form";
		document.form1.navigation.value = "tambah";
		
		document.form1.method = "GET";
		
		document.form1.submit();
	}
	<c:if test="${claim.claimStatus.caseStatusId eq 11 or claim.claimStatus.caseStatusId eq 8}">
    
	    function verifyClaim (){
		
			document.form1.action = "claimitem";
			document.form1.navigation.value = "verifybulk";
	
			document.form1.method = "GET";		
			document.form1.submit();
			
		}
		function voidClaim (){
		
			document.form1.action = "claim";
			document.form1.navigation.value = "prevoid";	
			document.form1.method = "GET";		
			document.form1.submit();
			
		}
	    
	    function approveClaim (){		
			document.form1.action = "claim";
			document.form1.navigation.value = "approve";	
			document.form1.method = "GET";		
			document.form1.submit();			
		}
		function approveExGratiaClaim (){	
			var delConfirm = window.confirm ("Are You Sure Want To Approve ExGratia This Claim ?");
			if (delConfirm){	
				document.form1.action = "claim";
				document.form1.navigation.value = "approveexgratia";	
				document.form1.method = "GET";		
				document.form1.submit();
			}			
		}
		
		function rejectClaim (){
			document.form1.action = "rejectedclaim-form";
			document.form1.navigation.value = "tambah";
			document.form1.method = "GET";
			
			document.form1.submit();
		}
	      </c:if>
      function rejectClaim (){
		document.form1.action = "rejectedclaim-form";
		document.form1.navigation.value = "tambah";
		document.form1.method = "GET";
		
		document.form1.submit();
	}
	function printErrorLog(){
		window.open ("firstcall?navigation=searchclaimerrorlog&claimId=<c:out value="${claim.claimId}" />","Search","width=1024, height=768, menubar=yes, status=no, toolbar=no, scrollbars=yes, resizable=yes");		
	}
	
	function adderrorlog (){ 
		window.location.href = "firstcall-form?navigation=claimelog&claimId=<c:out value="${claim.claimId}" />&&memberId=<c:out value="${claim.memberId.memberId}" />";
	}
</script>
