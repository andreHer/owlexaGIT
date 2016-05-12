<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<ul id="maintab" class="shadetabs">

	<li class="selected">
		<a href="#" rel="tcontent1">Detail Invoice</a>
	</li>
	<li>
		<a href="excesscharge?navigation=listinvoice&invoiceId=<c:out value="${invoice.invoiceId}" />" rel="tcontent2">Excess Charge List</a>
	</li>
</ul>

<br />
<form action="invoice" method="GET" name="form1" id="form_layout">

<input type="hidden" name="navigation" value="">
<input type="hidden" name="invoiceId" value="<c:out value="${invoice.invoiceId}" />">

	
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr> 
      <td style="padding-bottom: 2px;">        
        <input title="Edit [Alt+Shift+E]" accesskey="E" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:ubah()" name="Edit" value=" Update " type="button">
        <input title="Hapus [Alt+Shift+H]" accesskey="H" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:hapus()" name="Delete" value=" Delete " type="button">
        
         <input title="Hapus [Alt+Shift+H]" accesskey="H" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:cetak1()" name="print1" value=" Print Hal 1 " type="button">        		
                		
         <input title="Hapus [Alt+Shift+H]" accesskey="H" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:cetak2()" name="print2" value=" Print Hal 2 " type="button">
         
         <input title="Hapus [Alt+Shift+H]" accesskey="H" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:cetak3()" name="print3" value=" Print Hal 3 " type="button">
         <input title="Hapus [Alt+Shift+H]" accesskey="H" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:cetak4()" name="print4" value=" Print Hal 4 " type="button">
		</td>
      <td align="right"></td>
    </tr>
  </tbody>
</table>
<table class="tabDetailView" border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td colspan="20"  style="padding: 0px;">
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tbody>
				<tr>
					<td style="text-align: left;" class="tabDetailViewDL">&nbsp;
						<a href="#" onclick='' class="listViewPaginationLinkS1" style="font-weight: normal;">View Change Log</a>
					</td>
					<td class="tabDetailViewDL" align="right" nowrap="nowrap">
						<a href="invoice?navigation=search&searchtext=<c:out value="${searchtext}"/>&searchby=<c:out value="${searchby}" />&rowset=<c:out value="${rowser}" />&index=<c:out value="${index}"/>" class="tabDetailViewDFLink" style="font-weight: normal;">Return to List&nbsp;</a>					
					</td>
				</tr>
			</tbody>
		</table>
	  </td>
    </tr>
		
	
			
		<tr>
	     
	      <td class="tabDetailViewDL" valign="top" width="15%">Invoice Number :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><c:out value="${invoice.invoiceNumber}" /></td>
	       <td class="tabDetailViewDL" valign="top" width="17%">Invoice Date :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${invoice.invoiceDate}"/></td>
	    </tr>
			
	
			
		<tr>
	      
	      <td class="tabDetailViewDL" valign="top" width="15%">Member Group :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><c:out value="${invoice.memberGroupId.groupName}" /></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">Invoice Due Date :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${invoice.invoiceDueDate}"/></td>
	    </tr>
			
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Invoice Value :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><fmt:formatNumber><c:out value="${invoice.invoiceValue}"/></fmt:formatNumber></td>
	       <td class="tabDetailViewDL" valign="top" width="17%">Invoice Status :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${invoice.invoiceStatus.paymentStatusName}"/></td>
	    </tr>
			
	
			
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Approval Time :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${invoice.approvalTime}"/> - <c:out value="${invoice.approvedBy}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
			
	
			
	
			
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Created Time :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${invoice.createdTime}"/> - <c:out value="${invoice.createdBy}"/> </td>
	      <td class="tabDetailViewDL" valign="top" width="17%">Modified Time :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${invoice.modifiedTime}"/> - <c:out value="${invoice.modifiedBy}"/></td>
	    </tr>
			
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Description :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%" colspan="3"><c:out value="${invoice.description}"/></td>
	      
	    </tr>
			
			  <tr>
      <td colspan="20"  style="padding: 0px;">
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tbody>
				<tr>
					<td style="text-align: left;" class="tabDetailViewDL">&nbsp;
					</td>
					<td class="tabDetailViewDL" align="right" nowrap="nowrap">
						<a href="invoice?navigation=search&searchtext=<c:out value="${searchtext}"/>&searchby=<c:out value="${searchby}" />&rowset=<c:out value="${rowser}" />&index=<c:out value="${index}"/>" class="tabDetailViewDFLink" style="font-weight: normal;">Return to List&nbsp;</a>					
					</td>
				</tr>
			</tbody>
		</table>
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
	function ubah (){
		document.form1.navigation.value = "update";
		document.form1.action = "invoice-form";
		document.form1.method = "GET";
		document.form1.submit();
	}
	function cetak(){
		window.open ("invoice?navigation=print&url=member-form&invoiceId=<c:out value="${invoice.invoiceId}" />","Search","width=1024, height=768, menubar=yes, status=no, toolbar=no, scrollbars=yes, resizable=yes");
		
	}
	function cetak1(){
		window.open ("invoice?navigation=printhal1&url=member-form&invoiceId=<c:out value="${invoice.invoiceId}" />","Search","width=1024, height=768, menubar=yes, status=no, toolbar=no, scrollbars=yes, resizable=yes");
		
	}
	function cetak2(){
		window.open ("invoice?navigation=printhal2&url=member-form&invoiceId=<c:out value="${invoice.invoiceId}" />","Search","width=1024, height=768, menubar=yes, status=no, toolbar=no, scrollbars=yes, resizable=yes");
		
	}
	function cetak3(){
		window.open ("invoice?navigation=printhal3&url=member-form&invoiceId=<c:out value="${invoice.invoiceId}" />","Search","width=1024, height=768, menubar=yes, status=no, toolbar=no, scrollbars=yes, resizable=yes");
		
	}
	function cetak4(){
		window.open ("invoice?navigation=printhal4&url=member-form&invoiceId=<c:out value="${invoice.invoiceId}" />","Search","width=1024, height=768, menubar=yes, status=no, toolbar=no, scrollbars=yes, resizable=yes");
		
	}
</script>
