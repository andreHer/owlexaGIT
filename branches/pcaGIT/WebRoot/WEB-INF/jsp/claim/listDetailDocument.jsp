<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<%
String navigation = WebUtil.getAttributeString(request,"navigation","");
%>

 <!-- Page Title Start // Should be put on <Title> tag-->

<!-- Page Title Stop-->
<%
String alert = (String) request.getAttribute("alert");


if (alert != null && !alert.trim().equals("")) {%>
	<div id="warning" align="center">
		<c:out value="${alert}"></c:out>
	</div>
	<%}%>

<%
String rowclass = "";
%>
<!-- Search Container Start -->
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;List Claim Document Checklist</h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
      <c:if test="${theUser.userType eq 2 }">
	      <td align="right">
	      	<input title="Error Log"  name="errorLog" id="errorLog" value=" Error Log " class="errorLog" type="button" onClick="javascript:printErrorLog()">
	      </td>
	      <td align="right">
	      	<input title="Add Error Log"  name="addErrorLog" value=" Add Error Log " class="errorLog" type="button" onClick="javascript:adderrorlog()">
	      </td>
	   </c:if>   
    </tr>
  </tbody>
</table>

<%@ include file="../mainContentClaim.jsp" %>

<br />

<form name="form1" action="claim" method="POST">

<input type="hidden" name="navigation" value="dochecklist">
<input type="hidden" name="arah" value="">
<input type="hidden" name="subnavigation" value="<c:out value="${subnavigation}" />" />
<input type="hidden" name="currentnavigation" value="listclaim" />
<input type="hidden" name="memberId" value="<c:out value="${memberId}" />" />
<input type="hidden" name="index" value="<c:out value="${index}" />">
<input type="hidden" name="claimId" value="<c:out value="${claimId}" />">
<input type="hidden" name="theUser" value="<c:out value="${theUser}" />">
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"></td>
      <td width="100%"></td>
    </tr>
  </tbody>
</table>

	
 
<br />

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;List Document Checklist</h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table> 	
<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	
	<tr height="20">
		<td width="3%" nowrap="nowrap" class="listViewThS1" scope="col" align="center">
			<img src="images/blank.gif" alt="asd" height="1" width="1">No.</td>		
				   		   		   			
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Claim Number &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">Claim Category &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">Provider Name &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">Admission Date &nbsp;</td>				   		   		   		   		   		   			
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">Document Type &nbsp;</td>	
				
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Document Status &nbsp;</td>
	
	</tr>

	 <tr height="20">
      	<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">1.</td>
    	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<c:out value="${claim.claimNumber}" />			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${claim.caseCategoryId.caseCategoryName}" />			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${claim.providerName}" />			
		</td>				   		   		   		   		   		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${claim.admissionDate}" />			
		</td>		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="left">
			Invoice Document
		</td>		   		   		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="center">
			<input type="checkbox" name="invoiceDocument" value="1" <c:if test="${claim.invoiceDocument eq 1}">checked</c:if>/>			
		</td>	   		   		   		   		   			

    </tr>   
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
    	 <tr height="20">
      	<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">2.</td>
    	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<c:out value="${claim.claimNumber}" />			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${claim.caseCategoryId.caseCategoryName}" />			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${claim.providerName}" />			
		</td>				   		   		   		   		   		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${claim.admissionDate}" />			
		</td>		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="left">
			Lab Document
		</td>		   		   		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="center">
			<input type="checkbox" name="labDocument" value="1" <c:if test="${claim.labDocument eq 1}">checked</c:if>/>			
		</td>	   		   		   		   		   			

    </tr>
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
    	 <tr height="20">
      	<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">3.</td>
    	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<c:out value="${claim.claimNumber}" />			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${claim.caseCategoryId.caseCategoryName}" />			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${claim.providerName}" />			
		</td>				   		   		   		   		   		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${claim.admissionDate}" />			
		</td>		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="left">
			EDC Document
		</td>		   		   		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="center">
			<input type="checkbox" name="edcDocument" value="1" <c:if test="${claim.edcDocument eq 1}">checked</c:if>/>			
		</td>	   		   		   		   		   			

    </tr>	
    	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
    	 <tr height="20">
      	<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">4.</td>
    	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<c:out value="${claim.claimNumber}" />			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${claim.caseCategoryId.caseCategoryName}" />			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${claim.providerName}" />			
		</td>				   		   		   		   		   		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${claim.admissionDate}" />			
		</td>		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="left">
			Prescription Document
		</td>		   		   		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="center">
			<input type="checkbox" name="prescriptionDocument" value="1" <c:if test="${claim.prescriptionDocument eq 1}">checked</c:if>/>			
		</td>	   		   		   		   		   			

    </tr>
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
    	 <tr height="20">
      	<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">4.</td>
    	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<c:out value="${claim.claimNumber}" />			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${claim.caseCategoryId.caseCategoryName}" />			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${claim.providerName}" />			
		</td>				   		   		   		   		   		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${claim.admissionDate}" />			
		</td>		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="left">
			GL Document
		</td>		   		   		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="center">
			<input type="checkbox" name="glDocument" value="1" <c:if test="${claim.glDocument eq 1}">checked</c:if>/>			
		</td>	   		   		   		   		   			

    </tr>	
    	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
    	 <tr height="20">
      	<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">5.</td>
    	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<c:out value="${claim.claimNumber}" />			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${claim.caseCategoryId.caseCategoryName}" />			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${claim.providerName}" />			
		</td>				   		   		   		   		   		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${claim.admissionDate}" />			
		</td>		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="left">
			Referal Document
		</td>		   		   		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="center">
			<input type="checkbox" name="referalDocument" value="1" <c:if test="${claim.referalDocument eq 1}">checked</c:if>/>			
		</td>	   		   		   		   		   			

    </tr>

<tr height="20">
		<td width="3%" nowrap="nowrap" class="listViewThS1" scope="col" align="center">
			<img src="images/blank.gif" alt="asd" height="1" width="1"></td>		

<!-- ini default generated table from database -->
		   			
				   		   		   			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				 &nbsp;</td>

					<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">
				 &nbsp;</td>
			   		   		   		   		   		   			
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">
				 &nbsp;</td>
			
							<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				 &nbsp;</td>

							<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%" style="text-align: right;"></td>
							<c:if test="${ theUser.roleId.roleId ne 11}">	
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%" style="text-align: center;" align="center">
					<c:if test="${claim.claimStatus.caseStatusId ne 19}">
						<input type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:updateChecklist()" value=" Update Checklist ">
					</c:if>
				</td>
			</c:if>
		
			   			
			   		   		   		   		   			
			  
	</tr>
		
	</tbody>
	</table>
</form>
	
<!-- Table Content Stop -->
	<!-- Table Toolbar Start -->
	
		<!-- Table Toolbar Stop -->



<!-- Table Container Stop -->
<script language="Javascript">
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
<%
String nav="";

	nav = (String)request.getAttribute("navigation");

%>

function updateChecklist (){

	
	var delConfirm = window.confirm ("Are You Sure Want To Update This Entry ?");

	if (delConfirm){
		document.form1.method = "POST";
		document.form1.action = "claim";
		document.form1.navigation.value = "dochecklist";
		document.form1.submit();
	}
}

function printErrorLog(){
	window.open ("firstcall?navigation=searchclaimerrorlog&claimId=<c:out value="${claim.claimId}" />","Search","width=1024, height=768, menubar=yes, status=no, toolbar=no, scrollbars=yes, resizable=yes");		
}

function adderrorlog (){ 
	window.location.href = "firstcall-form?navigation=claimelog&claimId=<c:out value="${claim.claimId}" />&&memberId=<c:out value="${claim.memberId.memberId}" />";
}

</script>
