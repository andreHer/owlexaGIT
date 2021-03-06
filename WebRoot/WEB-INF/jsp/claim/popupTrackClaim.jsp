<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<head>


		<script type="text/javascript" src="scripts/sugar_3.js"></script>
		<script type="text/javascript" src="scripts/cookie.js"></script>
		<link rel="stylesheet" type="text/css" media="all" href="css/calendar-win2k-cold-1.css" />
		<link rel="stylesheet" type="text/css" media="all" href="css/style.css" />
	
		
<script src="scripts/prototype.js" type="text/javascript"></script>
  <script src="scripts/scriptaculous.js" type="text/javascript"></script>
  	<link rel="stylesheet" type="text/css" href="css/tabcontent.css" />
<script type="text/javascript" src="scripts/tabcontent.js">
/***********************************************
* Tab Content script- � Dynamic Drive DHTML code library (www.dynamicdrive.com)
* This notice MUST stay intact for legal use
* Visit Dynamic Drive at http://www.dynamicdrive.com/ for full source code
***********************************************/
</script>
<link rel="stylesheet" type="text/css" href="css/reset-fonts-grids.css" />
        <!-- CSS for Menu -->

        <link rel="stylesheet" type="text/css" href="css/menu.css" />
 
 
        <!-- Page-specific styles -->

        <!-- Namespace source file -->
<!-- calendar stylesheet -->
	

		<!-- main calendar program -->
		<script type="text/javascript" src="scripts/calendar.js"></script>

		
        <!-- Menu source file -->
       
		
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Aplikanusa Lintasarta - Healthcare Management System</title>


		<link href="css/navigation.css" rel="stylesheet" type="text/css" />
		<script language="javascript" src="scripts/menu.js"></script>
		<script language="javascript" src="scripts/cookie_002.js"></script>

	</head>
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
int i=0;

%>


<!-- Search Container Start -->

<form name="form1" action="claimreport" method="POST">
<input type="hidden" name="navigation" value="gotrack">
<input type="hidden" name="arah" value="">
<input type="hidden" name="index" value="<c:out value="${index}" />">
				<input type="hidden" name="claimId" value="<c:out value="${claim.claimId}" />">
			

<!-- Search Container Stop -->


<!-- Table Container Start -->



<div class="table_container">
	<!-- Table Toolbar Start -->
		
		
		<%
		String nampak = "";
		if(navigation!=null&&navigation.equals("gosearch")){
		}else{
			nampak = " style=\"display: none;\"";
		}
		%>
		
</div>
	<!-- Table Toolbar Stop -->
	
	<!-- Table Content Start -->
	
 
<br />

 	
<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	<tr>
      <td colspan="20" align="right">  
            
            </td>
    </tr>
	<tr height="20">
		<td width="6%" nowrap="nowrap" class="listViewThS1" scope="col">
			<img src="images/blank.gif" alt="asd" height="1" width="1">No.</td>		

<!-- ini default generated table from database -->
		   			
				   		   		   			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">
				Claim Number &nbsp;</td>

	<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">
				Claim Date &nbsp;</td>


					<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Member &nbsp;</td>
			   		   		   		   		   		   			
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">
				Provider Name &nbsp;</td>
			
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Charge &nbsp;</td>
			
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Approved &nbsp;</td>
			
						
		
							<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Excess &nbsp;</td>
		
					
		
						<td scope="col" class="listViewThS1" nowrap="nowrap" width="7%">
				Status &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="7%">
				Diagnosis &nbsp;</td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="7%">
				CDV Date &nbsp;</td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="7%">
				Payment Number &nbsp;</td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="7%">
				Payment Date &nbsp;</td>
			   		   		   		   		   		   		   		   		   		   		   		   		   		   			
				
		
			   		   		   		   		   			
		
		
			   		   			
		
		
			   		   			
		
		
			   		   		   		   		   			
			   	   
	   
	</tr>


	<c:forEach items="${Claims}" var="claim" varStatus="status" >
	<%	if (i % 2 == 0) {
			rowclass = "col1";
		} else if (i % 2 != 0) {
			rowclass= "col2";
		}
		i++;
	%>
	 <tr height="20">
      <td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><%=i%></td>
		
					   		   		   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<a href="claim?navigation=detail&claimId=<c:out value="${claim.claimId}" />" class="linkDetail"><c:out value="${claim.claimNumber}" /></a>
			
		</td>
				   		   		   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${claim.claimDate}" />
			
		</td>
					   		   		   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<a href="member?navigation=detail&memberId=<c:out value="${claim.memberId.memberId}" />" class="linkDetail"><c:out value="${claim.memberId.firstName}" /> <c:out value="${claim.memberId.lastName}" /></a>
			
		</td>
			
					   		   		   		   		   		   		   		   		   		   		   		   		   		   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:if test="${claim.providerName != null}">
				<c:out value="${claim.providerName}" />
				</c:if>
				
			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
			
				<fmt:formatNumber><c:out value="${claim.claimChargeValue}" /></fmt:formatNumber>
			
		</td>
					   		   		   		   		   		
					   		   		   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right"> 
			
				<fmt:formatNumber><c:out value="${claim.claimApprovedValue}" /></fmt:formatNumber>
			
		</td>		
		   		   	   		   		   		   		   		
			
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
			<fmt:formatNumber><c:out value="${claim.claimExcessValue}" /></fmt:formatNumber>
		</td>		
		
					   		   		
		   		  
					   		   		
					   		   		   		   		   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${claim.claimStatus.caseStatusName}" />
			
		</td>
         <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top">
         	<c:out value="${claim.diagnosisId.diagnosisCode}" />
	  </td>
	  <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top">
         	<c:if test="${claim.paymentId ne null}">
         	
         		<c:out value="${claim.paymentId.paymentTime}" />
         		
         	</c:if>
	  </td>
	  <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top">
         	<c:if test="${claim.paymentId ne null}">
         	<a href="payment?navigation=detail&paymentId=<c:out value="${claim.paymentId.paymentId}" />" class="linkDetail">
         		<c:out value="${claim.paymentId.paymentNumber}" />
         		</a>
         	</c:if>
	  </td>
	  <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top">
         	<c:if test="${(claim.paymentId ne null) and (claim.paymentId.paymentConfirmDate ne null)}">
         	
         		<c:out value="${claim.paymentId.paymentConfirmDate}" />
         		
         	</c:if>
	  </td>
    </tr>
   
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	
	</c:forEach>
	
<tr>
                     </tr>
                     <tr height="20">
		<td width="6%" nowrap="nowrap" class="listViewThS1" scope="col">
			<img src="images/blank.gif" alt="asd" height="1" width="1"></td>		

<!-- ini default generated table from database -->
		   			
				   		   		   			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">
				Jumlah</td>

					<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				</td>
		
							<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				</td>
			   		   		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				</td>   		   		   		   			
				<td scope="col" class="listViewThS1" style="text-align: right;" nowrap="nowrap" width="15%" align="right">
				<fmt:formatNumber><c:out value="${claimChargeValue}" /></fmt:formatNumber></td>
			
				<td scope="col" class="listViewThS1" style="text-align: right;" nowrap="nowrap" width="10%" align="right">
				<fmt:formatNumber><c:out value="${claimPaidValue}" /></fmt:formatNumber></td>
				
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%" align="right" style="text-align: right;">
			<fmt:formatNumber><c:out value="${claimExcessValue}" /></fmt:formatNumber></td>
			
				
				
		
			   		   		   		   		   		   		   		   		   		   		   		   		   		   			
				
		
			   		   		   		   		   			
		
		
			   		   			
		
		
			   		   			
		
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">&nbsp;
			   		   		   		   		   			<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">&nbsp;
			   		   		   		   		   			<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">&nbsp;
			   		   		   		   		   			<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">&nbsp;
			   	   <td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">&nbsp;
	   </td>
	</tr>
	
	</table>
</form>
	
	<br />
<!-- Table Content Stop -->
	<!-- Table Toolbar Start -->
	
		<!-- Table Toolbar Stop -->



<!-- Table Container Stop -->
<script language="Javascript">
<%
String nav="";
if(request.getAttribute("navigation").equals("gosearch")||request.getAttribute("navigation").equals("golookup")){
	nav = (String)request.getAttribute("navigation");
}
%>
	function printOut(){
		window.open ("claimreport?navigation=printoutreport&url=claimreport","Search","width=1024, height=768, menubar=yes, status=no, toolbar=no, scrollbars=yes, resizable=yes");
	}
function exportData(){
	document.form1.action = "claimreport";
	document.form1.navigation.value = "printoutreport";
	document.form1.submit();
}
function go(){
	document.form1.navigation.value = "<%=nav%>";
	document.form1.submit();
}
function cari (){
	document.form1.navigation.value = "gosearch";
	document.form1.action = "claim";
	document.form1.method = "POST";
	document.form1.submit();
}
function detil (idx){
	document.form1.method = "GET";
	document.form1.claimId.value = idx;
	document.form1.action = "claim";
	document.form1.navigation.value = "detail";
	document.form1.submit();

}
function printPopup(){
	var claimType = document.form1.claimType.value;
	var searchtext = document.form1.searchtext.value;
	var searchby = document.form1.searchby.value;
	var status = document.form1.status.value;
	var minDate = document.form1.minimum_date.value;
	var maxDate = document.form1.maximum_date.value;
	var serviceType = document.form1.serviceType.value;
	var claimValue = document.form1.claimValue.value;
	
	var urlAdd = "&claimValue="+claimValue+"&claimType="+claimType+"&searchtext="+searchtext+"&status="+status+"&searchby="+searchby+"&minimum_date="+minDate+"&maximum_date="+maxDate+"&serviceType="+serviceType;
	window.open ("claimreport?navigation=printpopup&url=claimreport"+urlAdd,"Search","width=1024, height=768, menubar=yes, status=no, toolbar=no, scrollbars=yes, resizable=yes");
}
</script>
