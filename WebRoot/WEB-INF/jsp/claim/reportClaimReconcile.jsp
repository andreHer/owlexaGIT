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
int i=0;
int rowHoliday=-1;


// Integer[] holiday = (Integer[]) request.getAttribute("holiday");
%>


<!-- Search Container Start -->

<form name="form1" action="claimreport" method="POST">
<input type="hidden" name="navigation" value="gosearch">
<input type="hidden" name="arah" value="">
<input type="hidden" name="index" value="<c:out value="${index}" />">
				<input type="hidden" name="claimId" value="<c:out value="${claim.claimId}" />">
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Search Report Claim Reconcile</h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>
<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td><table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tbody>
          <tr>
            <form>
            </form>
            <td class="dataLabel" nowrap="nowrap">Search Keyword:
              &nbsp;&nbsp;
              
				<input size="20" name="searchtext" class="dataField" value="<c:out value="${searchtext}"/>" type="text">
              </td>
            <td class="dataLabel" nowrap="nowrap">Search Category:
              &nbsp;&nbsp;
              
                
								 <select name="searchby" class="inputbox">

			   	   		   			<option value="claimNumber" <c:if test="${searchby eq \"claimNumber\"}">selected="true"</c:if> >Claim Number</option>
			   	  		   			<option value="providerName" <c:if test="${searchby eq \"providerName\"}">selected="true"</c:if> >Provider Name</option>
			   	  		   			<option value="clientName" <c:if test="${searchby eq \"clientName\"}">selected="true"</c:if> >Client Name</option>
			   	  		   			<option value="memberName" <c:if test="${searchby eq \"memberName\"}">selected="true"</c:if> >Member Name</option>
			   	  		   			<option value="memberGroupName" <c:if test="${searchby eq \"memberGroupName\"}">selected="true"</c:if> >Member Group Name</option>
			   	  		   			<option value="policyNumber" <c:if test="${searchby eq \"policyNumber\"}">selected="true"</c:if> >Policy Number</option>
			   	  		   			<option value="paymentNumber" <c:if test="${searchby eq \"paymentNumber\"}">selected="true"</c:if> >Payment Number</option>
			   	  		   			<option value="officer" <c:if test="${searchby eq \"officer\"}">selected="true"</c:if> >Officer</option>
			   	  		   			<option value="invoiceNumber" <c:if test="${searchby eq \"invoiceNumber\"}">selected="true"</c:if> >Invoice Number</option>
			   	
			   </select>
				
              </td>
			  <td class="dataLabel" nowrap="nowrap">Status:
              &nbsp;&nbsp;
              
                <select name="status">					
					
					<option value="">--- All Status ---</option>
					<option value="-1" <c:if test="${status eq -1 }">selected</c:if> >VOID</option>
					<option value="1" <c:if test="${status eq 1 }">selected</c:if> >OPEN</option>					
					<option value="3" <c:if test="${status eq 3 }">selected</c:if>>VERIFIED</option>
					<option value="9" <c:if test="${status eq 9 }">selected</c:if>>APPROVED</option>
					<option value="8" <c:if test="${status eq 8 }">selected</c:if>>CHECKED</option>
					<option value="6" <c:if test="${status eq 6 }">selected</c:if>>PAID</option>
					<option value="4" <c:if test="${status eq 4 }">selected</c:if>>REJECTED</option>
					<option value="10" <c:if test="${status eq 10 }">selected</c:if>>PENDING</option>
					<option value="13" <c:if test="${status eq 13 }">selected</c:if>>CDV ISSUED</option>
				</select>
				
              </td>
            <td class="dataLabel">
            	Type &nbsp;&nbsp;:
              &nbsp;&nbsp;
              
                <select name="claimType">					
					<option value="-1" >--- All Type ---</option>
					<option value="1" <c:if test="${claimType eq 1 }">selected</c:if> >REIMBURSEMENT</option>
					<option value="2" <c:if test="${claimType eq 2 }">selected</c:if>>CASHLESS</option>
					
				</select>
             </td>
            </tr>
			<tr>
            
            <td class="dataLabel" nowrap="nowrap"> Minimum Date :
              &nbsp;&nbsp;&nbsp;&nbsp;
              
                <input name="minimum_date" id="jscal_field" tab="1" maxlength="10" size="11" readonly="readonly" value="<c:out value="${minDate}" />" type="text">
				<img src="images/jscalendar.gif" alt="Enter Date" id="jscal_trigger" align="absmiddle" height="18" width="18">
				
				<script type="text/javascript">
    					Calendar.setup({
        					inputField     :    "jscal_field",     // id of the input field
        					ifFormat       :    "%Y-%m-%d",      // format of the input field
        					button         :    "jscal_trigger",  // trigger for the calendar (button ID)
        					align          :    "Bl",           // alignment (defaults to "Bl")
        					singleClick    :    true
    					});
				 	</script>
              
			</td>
            <td class="dataLabel" nowrap="nowrap"> Maximum Date :
              &nbsp;&nbsp;&nbsp;&nbsp;
              
            <input name="maximum_date" id="jscal_field_max" tabindex="1" maxlength="10" size="11" readonly="readonly" value="<c:out value="${maxDate}" />" type="text">
			<img src="images/jscalendar.gif" alt="Enter Date" id="jscal_max_trigger" align="absmiddle" height="18" width="18">
			
					<script type="text/javascript">
    					Calendar.setup({
        					inputField     :    "jscal_field_max",     // id of the input field
        					ifFormat       :    "%Y-%m-%d",      // format of the input field
        					button         :    "jscal_max_trigger",  // trigger for the calendar (button ID)
        					align          :    "Bl",           // alignment (defaults to "Bl")
        					singleClick    :    true
    					});
				 	</script>
			</td>
			  <td class="dataLabel" nowrap="nowrap">
				Date Category  &nbsp;&nbsp;:
              &nbsp;&nbsp;
              
                <select name="dateBy">					
					<option value="claimDate" <c:if test="${dateBy eq 'claimDate' }">selected</c:if> >RECEIVED DATE</option>
					<option value="admissionDate" <c:if test="${dateBy eq 'admissionDate' }">selected</c:if> >ADMISSION DATE</option>
					<option value="dischargeDate" <c:if test="${dateBy eq 'dischargeDate' }">selected</c:if>>DISCHARGE DATE</option>
					<option value="paymentConfirmDate" <c:if test="${dateBy eq 'paymentConfirmDate' }">selected</c:if>>PAYMENT DATE</option>
					
				</select>				
              </td>
            <td align="left">
              
            </td>
          </tr>
          <tr>
            
      		<td class="dataLabel" nowrap="nowrap">Service Type &nbsp;&nbsp;:
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              
                <select name="serviceType">					
					<option value="-1" >--- All Type ---</option>
					<option value="1" <c:if test="${serviceType eq 1 }">selected</c:if> >INPATIENT</option>
					<option value="2" <c:if test="${serviceType eq 2 }">selected</c:if> >OUTPATIENT</option>
					<option value="3" <c:if test="${serviceType eq 3 }">selected</c:if> >MATERNITY</option>
					<option value="4" <c:if test="${serviceType eq 4 }">selected</c:if> >DENTAL</option>
					<option value="5" <c:if test="${serviceType eq 5 }">selected</c:if> >OPTICAL</option>
				</select>
				
              </td>
      		<td class="dataLabel" nowrap="nowrap">Claim Value &nbsp;&nbsp;:
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              <input size="20" name="claimValue" class="dataField" value="<c:out value="${claimValue}"/>" type="text"></td>
          <td class="dataLabel" nowrap="nowrap">Format Value &nbsp;&nbsp;:
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              <select name="reportFormat">					
					<option value="4" <c:if test="${reportFormat eq 4 }">selected</c:if> >Excel</option>
					<option value="1" <c:if test="${reportFormat eq 1 }">selected</c:if> >CSV - All</option>
					<option value="2" <c:if test="${reportFormat eq 2 }">selected</c:if> >Text File - Header</option>
					<option value="3" <c:if test="${reportFormat eq 3 }">selected</c:if> >Text File - Detail</option>
				</select>
				</td>
			<td>
			<input title="Search [Alt+Shift+Q]" accesskey="Q" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" name="buttonSearch" value="Search" type="button" onclick="javascript:cari()">              
              <input title="Download Report" accesskey="Q" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" name="buttonDownload" value="Download Report" type="button" onclick="javascript:exportData()">
			</td>
          </tr>
        </tbody>
      </table></td>
    </tr>
  </tbody>
</table>


<!-- Search Container Stop -->


	
 
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
		   			
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">
				Receive Date &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">
				Submission Date &nbsp;</td>   		   		   			
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">
				Number Batch &nbsp;</td>	   		   			
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">
				Claim Number &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Member &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">
				Employee Name &nbsp;</td>
		<td scope="col" class="listViewThS1"  width="10%">
				Card Number &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">
				Client Name &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">
				Group Name &nbsp;</td>		
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">
				Admission  &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">
				Discharge  &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">
				Provider Name &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="7%">
				Diagnosis &nbsp;</td>	
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Charges &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Approved &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">
				Pre Paid Excess &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Excess &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Refund &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Paid To Provider &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Type &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Service &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="7%">
				Status &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="7%">
				Claim Remarks &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">
				Officer &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">
				Modified Time &nbsp;</td>
					
		<!-- 			
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Pembayaran Dimuka &nbsp;</td>	
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Paid &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">SLA &nbsp;</td>	 -->
		  	   
	   
	</tr>


	<c:forEach items="${Claims}" var="claim" varStatus="status" >
	<%	if (i % 2 == 0) {
			rowclass = "col1";
		} else if (i % 2 != 0) {
			rowclass= "col2";
		}
		i++;
		rowHoliday++;
		
		
								
	%>
	 <tr height="20">
      <td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><%=i%></td>
	  <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top">
         	<c:out value="${claim.claimDate}" />
	  </td>
	  <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top">
         	<c:out value="${claim.paymentId.paymentTime}" />
	  </td>
	  <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top">
         	<c:out value="${claim.batchClaimId.batchClaimNumber}" />
	  </td>
	  <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<a href="claim?navigation=detail&claimId=<c:out value="${claim.claimId}" />" class="linkDetail"><c:out value="${claim.claimNumber}" /></a>
	  </td>
	  <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<a href="member?navigation=detail&memberId=<c:out value="${claim.memberId.memberId}" />" class="linkDetail"><c:out value="${claim.memberId.firstName}" /> <c:out value="${claim.memberId.lastName}" /></a>
	  </td>
	  <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top">
         	<c:out value="${claim.memberId.parentMember.firstName}" />
	  </td>
	  <td class="oddListRowS1" bgcolor="#e7f0fe"  valign="top" align="right">
			<c:out value="${claim.cardNumber}" />
	  </td>
	  <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			 <c:if test="${claim.batchClaimId != null}">
				 <c:if test="${claim.batchClaimId.clientId != null}">
					 <c:out value="${claim.batchClaimId.clientId.clientName}" />
				 </c:if>
			 </c:if>
	  </td>
	  <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			 <c:out value="${claim.groupName}" />
	  </td>
	  <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<c:out value="${claim.admissionDate}" />
			
	  </td>
	  <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
	   		<c:out value="${claim.dischargeDate}" />
	  </td>
	  <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
			<c:if test="${claim.providerName != null}">
			<c:out value="${claim.providerName}" />
			</c:if>
	  </td>
	  <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top">
         	<c:out value="${claim.diagnosisId.diagnosisCode}" />
	  </td>
	  <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
			<fmt:formatNumber><c:out value="${claim.claimChargeValue}" /></fmt:formatNumber>
	  </td>
	  <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right"> 
			<fmt:formatNumber><c:out value="${claim.claimApprovedValue}" /></fmt:formatNumber>
	  </td>
	  <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right"> 
			<fmt:formatNumber><c:out value="${claim.prePaidExcess}" /></fmt:formatNumber>
	  </td>
	  <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
			<fmt:formatNumber maxFractionDigits="2"><c:out value="${claim.claimExcessValue}" /></fmt:formatNumber>
	  </td>
	  <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
			<fmt:formatNumber maxFractionDigits="2"><c:out value="${claim.refund}" /></fmt:formatNumber>
	  </td>	
	  <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
			<fmt:formatNumber maxFractionDigits="2"><c:out value="${claim.paidToProvider}" /></fmt:formatNumber>
	  </td>	
	  <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<c:out value="${claim.claimTypeId.claimTypeCode}" />
	  </td>		
	  <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<c:out value="${claim.caseCategoryId.caseCategoryCode}" />
	  </td>		   		   		   		   		
	  <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
		    <c:out value="${claim.claimStatus.caseStatusName}" />
	  </td>	
	  <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top">
			 <c:out value="${claim.claimStatus.caseStatusId eq 4 ? claim.rejectionRemarks : claim.claimRemarks}" />
	  </td>
	  <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top">
         	<c:out value="${claim.modifiedBy}" />
	  </td>
	  <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top">
         	<c:out value="${claim.modifiedTime}" />
	  </td>
		
		
		
		<%-- <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right"> 
			<fmt:formatNumber><c:out value="${claim.pembayaranDimuka}" /></fmt:formatNumber>
			
		</td>	   	   		   		   		   		   		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
			<fmt:formatNumber><c:out value="${claim.claimPaidValue}" /></fmt:formatNumber>
			
		</td>	

	  <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top">
         	<%=holiday[rowHoliday]%> Hari
	  </td> --%>
    </tr>
   
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
    
<%--      <% 
     rowHoliday++; 
      %> 
 --%>	
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
				 <td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				 </td>
				 <td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				 </td>
							<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				</td>
			   		   		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				</td>   	
				 <td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				 </td>
				  <td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				 </td>
				  <td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				 </td>
				  <td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				 </td>
				  <td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				 </td>
				<td scope="col" class="listViewThS1" style="text-align: right;" nowrap="nowrap" width="15%" align="right">
				<fmt:formatNumber><c:out value="${claimChargeValue}" /></fmt:formatNumber></td>
			
				 <td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				 </td>
							<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				</td>
				 <td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
			<fmt:formatNumber maxFractionDigits="2"><c:out value="${claimExcessValue}" /></fmt:formatNumber></td>
			
				
				
		
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%"></td>
		
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="7%"></td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">&nbsp;</td>
			   	<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">&nbsp;</td>		   	   
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
if(request.getAttribute("navigation").equals("gosearch")||request.getAttribute("navigation").equals("golookup") || request.getAttribute("navigation").equals("gosearchclaimreconcile")){
	nav = (String)request.getAttribute("navigation");
}
%>
	function printOut(){
		window.open ("claimreport?navigation=printoutreportClaimReconcile&url=claimreport","Search","width=1024, height=768, menubar=yes, status=no, toolbar=no, scrollbars=yes, resizable=yes");
	}
function exportData(){
	document.form1.action = "claimreport";
	document.form1.navigation.value = "printoutreportClaimReconcile";
	document.form1.submit();
}
function go(){
	document.form1.navigation.value = "<%=nav%>";
	document.form1.submit();
}
function cari (){
	document.form1.navigation.value = "gosearchclaimreconcile";
	document.form1.action = "claimreport";
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
