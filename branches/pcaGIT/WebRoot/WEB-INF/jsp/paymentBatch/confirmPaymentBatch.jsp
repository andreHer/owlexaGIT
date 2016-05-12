<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<%
String alert = (String) request.getAttribute("alert");
int index = 0;
int totalIndex = 0;
int count = 0;
int countSet = 0;
String rowclass="";
int i = 0;
int indexint = 0;
try {
	index = ((Integer) request.getAttribute ("index")).intValue();
	count = ((Integer) request.getAttribute ("count")).intValue();
	countSet = ((Integer) request.getAttribute ("countSet")).intValue();
	totalIndex = ((Integer) request.getAttribute ("halAkhir")).intValue();
	
	indexint = Integer.parseInt(request.getAttribute("index").toString());
	
	
	
	
}
catch (Exception e){
}
%>


<form action="paymentbatch" method="GET" name="form1" id="form_layout">
<input type="hidden" name="bankType" value="">

	<input type="hidden" name="navigation" value="">
	<input type="hidden" name="id" value="<c:out value="${paymentBatch.id}" />">
	<input type="hidden" name="paymentBatchId" value="${paymentBatch.id}" />

<table class="tabDetailView" border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td colspan="20"  style="padding: 0px;">
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tbody>
				<tr>
					<td style="text-align: left;" class="tabDetailViewDL">&nbsp;
						
					</td>
					<td class="tabDetailViewDL" align="right" nowrap="nowrap">
						<a href="paymentbatch?navigation=search&searchtext=<c:out value="${searchtext}"/>&searchby=<c:out value="${searchby}" />&index=<c:out value="${index}"/>" class="tabDetailViewDFLink" style="font-weight: normal;">Return to List&nbsp;</a>					
					</td>
				</tr>
			</tbody>
		</table>
	  </td>
    </tr>
	
		
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Payment Batch Number :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${paymentBatch.paymentBatchNumber}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">Payment Batch Date :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${paymentBatch.paymentBatchDate}"/></td>
	    </tr>
	
			
			
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Payment Batch Confirm Date :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${paymentBatch.paymentBatchConfirmDate}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">Outstanding Payment :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><fmt:formatNumber><c:out value="${paymentBatch.outstandingPayment}"/></fmt:formatNumber></td>
	    </tr>
			
			
			
			<tr>
	      
	      <td class="tabDetailViewDL" valign="top" width="17%">Total Payment :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><fmt:formatNumber> <c:out value="${paymentBatch.totalPayment}"/></fmt:formatNumber></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">Paid Payment :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><fmt:formatNumber><c:out value="${paymentBatch.paidPayment}"/></fmt:formatNumber></td>
	      
	    </tr>
	<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Batch Status :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:if test="${paymentBatch.batchStatus eq 0}">Outstanding</c:if> <c:if test="${paymentBatch.batchStatus eq 1}">Paid</c:if></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Payment Account Source : &nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><c:out value="${paymentBatch.paymentAccountSource.bank}" /> - <c:out value="${paymentBatch.paymentAccountSource.bankAccount}" /></td>
	    </tr>
				
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Created Time :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${paymentBatch.createdTime}"/> - <c:out value="${paymentBatch.createdBy}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">Modified Time :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${paymentBatch.modifiedTime}"/> - <c:out value="${paymentBatch.modifiedBy}"/></td>
	    </tr>
			
		
			<tr>
			      <td class="tabDetailViewDL" valign="top" width="17%">Confirm Date :&nbsp;</td>
				  
					 
			      <td class="tabDetailViewDF" valign="top" width="33%">
			      	<input type="text" size="15" name="confirmDate" id="downloadDate" value="" maxlength="10" style="border-color:  #000 ; ">
							<img src="images/jscalendar.gif" alt="Enter Date" id="downloadDate_trigger" align="absmiddle" height="20" width="20">
							<script type="text/javascript">
		    					Calendar.setup({
		        					inputField     :    "downloadDate",     // id of the input field
		        					ifFormat       :    "%Y-%m-%d",      // format of the input field
		        					button         :    "downloadDate_trigger",  // trigger for the calendar (button ID)
		        					align          :    "Tl",           // alignment (defaults to "Bl")
		        					singleClick    :    true
		    					});
						 	</script>
			      
			      &nbsp;
			      </td>
	      		<td class="tabDetailViewDL" valign="top" width="17%"></td>		  
				<td class="tabDetailViewDF" valign="top" width="33%"></td>
	    </tr>
		<tr>
			      <td class="tabDetailViewDL" valign="top" width="17%">Approval Note :&nbsp;</td>
				  
					 
			      <td class="tabDetailViewDF" valign="top" width="33%" colspan=3>
		
						<textarea rows="6" cols="40" name="approvalNote"></textarea>
			      </td>
	      		
	    </tr>
		
	
	 <tr>
      <td colspan="20"  style="padding: 0px;">
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tbody>
				<tr>
					<td style="text-align: left;" class="tabDetailViewDL">&nbsp;
					</td>
					<td class="tabDetailViewDL" align="right" nowrap="nowrap">
						<a href="paymentbatch?navigation=search&searchtext=<c:out value="${searchtext}"/>&searchby=<c:out value="${searchby}" />&index=<c:out value="${index}"/>" class="tabDetailViewDFLink" style="font-weight: normal;">Return to List&nbsp;</a>					
					</td>
				</tr>
			</tbody>
		</table>
	  </td>
    </tr>
	
	</tbody>
</table>


<br />
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr> 
      <td style="padding-bottom: 2px;">        
        				
			<input title="Confirm Payment [Alt+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:confirmPayment()" name="exExcess" value=" Confirm Payment " type="button">
		
		</td>
      <td align="right"></td>
    </tr>
  </tbody>
</table>
<br />


 	
<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	<tr>
      <td colspan="20" align="right"><table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tbody>
          <tr>
            <td class="listViewPaginationTdS1" align="left"></td>
            	<td class="listViewPaginationTdS1" align="right" nowrap="nowrap" >
				
				<%
				if (index == 1){
				%>
				<img src="images/start_off.gif" alt="Start" align="absmiddle" border="0" height="10" width="9">
					Start&nbsp;
									
				<img src="images/previous_off.gif" alt="Previous" align="absmiddle" border="0" height="10" width="4">
					Previous&nbsp;&nbsp;					
				<%
				}
				else if ((index-1) > 0){
				
				%>
				<img src="images/start.gif" alt="Start" align="absmiddle" border="0" height="10" width="9">
					<a href="javascript:goleftbgt()" class="listViewPaginationLinkS1">
						Start&nbsp;
					</a>				
				
				<img src="images/previous.gif" alt="Previous" align="absmiddle" border="0" height="10" width="4">
					<a href="javascript:goleft()" class="listViewPaginationLinkS1">
					Previous&nbsp;&nbsp;
					</a>
				<%
				}
				%>
					<span class="pageNumbers">(<c:out value="${minIndex}" /> - <c:out value="${maxIndex}" /> of <c:out value="${count}" />)</span>&nbsp;&nbsp;
				
				<%
				if (totalIndex > index){
				%>
				
				<a href="javascript:goright()" class="listViewPaginationLinkS1">Next&nbsp;
				<img src="images/next.gif" alt="Next" align="absmiddle" border="0" height="10" width="4"></a>&nbsp;&nbsp;
				<a href="javascript:gorightbgt()" class="listViewPaginationLinkS1">End&nbsp;
					<img src="images/end.gif" alt="End" align="absmiddle" border="0" height="10" width="9"></a>
				<%
				} else {
				
				%>
				Next&nbsp;
				<img src="images/next_off.gif" alt="Next" align="absmiddle" border="0" height="10" width="4">
				&nbsp;&nbsp;
				End&nbsp;
					<img src="images/end_off.gif" alt="End" align="absmiddle" border="0" height="10" width="9">
				<%
				}
				%>
			</td>
          </tr>
        </tbody>
      </table></td>
    </tr>
	<tr height="20">
		<td width="6%" nowrap="nowrap" class="listViewThS1" scope="col">
			<img src="images/blank.gif" alt="asd" height="1" width="1">No.</td>		

<!-- ini default generated table from database -->
		   		   		   		   		   			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">
				Payment Number &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">
				Installment Number &nbsp;</td>
			   			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">
				Bank Name &nbsp;</td>
			
		
			   			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="14%">
				Account Number &nbsp;</td>
			
		
			   			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="14%">
				Giro Number &nbsp;</td>
			
		
			   			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">
				Payee Name &nbsp;</td>
			
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="14%">
				Payment Amount &nbsp;</td>
	</tr>


	<c:forEach items="${paymentCollection}" var="payment" varStatus="status" >
	<%	if (i % 2 == 0) {
			rowclass = "col1";
		} else if (i % 2 != 0) {
			rowclass= "col2";
		}
		i++;
	%>
	 <tr height="20">
      	<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><%=(i+((indexint-1)*countSet))%></td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<a href="payment?navigation=detail&index=<c:out value="${index}" />&paymentId=<c:out value="${payment.paymentId.paymentId}" />" class="linkDetail"><c:out value="${payment.paymentId.paymentNumber}" /></a>			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${payment.installmentNumber}" />			
		</td>	   		   		   			
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${payment.paymentId.bankName}" />			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${payment.paymentId.accountNumber}" />			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${payment.paymentId.giroNumber}" />			
		</td>
					   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${payment.paymentId.payeeName}" />
			
		</td>
		   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
			
				<fmt:formatNumber><c:out value="${payment.totalValue}" /></fmt:formatNumber>
			
		</td>

					   		   		   		   		
					   		   		
	</tr>
   
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	
	</c:forEach>
	
<tr>
            <td class="listViewPaginationTdS1" align="left"></td>
            	<td class="listViewPaginationTdS1" align="right" nowrap="nowrap" colspan=20>
				
				<%
				if (index == 1){
				%>
				<img src="images/start_off.gif" alt="Start" align="absmiddle" border="0" height="10" width="9">
					Start&nbsp;
									
				<img src="images/previous_off.gif" alt="Previous" align="absmiddle" border="0" height="10" width="4">
					Previous&nbsp;&nbsp;					
				<%
				}
				else if ((index-1) > 0){
				
				%>
				<img src="images/start.gif" alt="Start" align="absmiddle" border="0" height="10" width="9">
					<a href="javascript:goleftbgt()" class="listViewPaginationLinkS1">
						Start&nbsp;
					</a>				
				
				<img src="images/previous.gif" alt="Previous" align="absmiddle" border="0" height="10" width="4">
					<a href="javascript:goleft()" class="listViewPaginationLinkS1">
					Previous&nbsp;&nbsp;
					</a>
				<%
				}
				%>
					<span class="pageNumbers">(<c:out value="${minIndex}" /> - <c:out value="${maxIndex}" /> of <c:out value="${count}" />)</span>&nbsp;&nbsp;
				
				<%
				if (totalIndex > index){
				%>
				
				<a href="javascript:goright()" class="listViewPaginationLinkS1">Next&nbsp;
				<img src="images/next.gif" alt="Next" align="absmiddle" border="0" height="10" width="4">
				</a>&nbsp;&nbsp;
				<a href="javascript:gorightbgt()" class="listViewPaginationLinkS1">End&nbsp;
					<img src="images/end.gif" alt="End" align="absmiddle" border="0" height="10" width="9">
				</a>
				<%
				} else {
				
				%>
				Next&nbsp;
				<img src="images/next_off.gif" alt="Next" align="absmiddle" border="0" height="10" width="4">
				&nbsp;&nbsp;
				End&nbsp;
					<img src="images/end_off.gif" alt="End" align="absmiddle" border="0" height="10" width="9">
				<%
				}
				%>
			</td>
          </tr>
	</tbody>
	</table>
</form>
<script language="javascript">
	
	function kembali (){
		document.form1.navigation.value = "back";
		document.form1.submit();
	}
	
	function confirmPayment (){
	
	var delConfirm = window.confirm ("Are You Sure Want To Approve This Payment ?");
		if (delConfirm){
			document.form1.navigation.value = "confirm";
			document.form1.bankType.value = "bca";
			document.form1.action = "paymentbatch";
			document.form1.method = "GET";
			document.form1.submit();
		}
	}

</script>
