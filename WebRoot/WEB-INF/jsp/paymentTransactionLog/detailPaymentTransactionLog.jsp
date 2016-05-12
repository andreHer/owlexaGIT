<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<h1>Payment Transaction Log</h1>

<form action="paymenttransactionlog" method="GET" name="form1" id="form_layout">

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr> 
      <td style="padding-bottom: 2px;">        
        
		<input title="Edit [Alt+E]" accesskey="E" class="button" onClick="javascript:ubah()" name="Edit" value=" Update " type="button">
		
        <input title="Hapus [Alt+H]" accesskey="H" class="button" onClick="javascript:hapus()" name="Delete" value=" Delete " type="button">        		
		
		</td>
      <td align="right"></td>
    </tr>
  </tbody>
</table>
<table class="tabDetailView" border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td colspan="20" class="listViewPaginationTdS1" style="padding: 0px;">
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tbody>
				<tr>
					<td style="text-align: left;" class="tabDetailViewDL">&nbsp;
						<a href="#" onclick='' class="listViewPaginationLinkS1" style="font-weight: normal;">View Change Log</a>
					</td>
					<td class="tabDetailViewDL" align="right" nowrap="nowrap">
						<a href="paymenttransactionlog?navigation=search&searchtext=<c:out value="${searchtext}"/>&searchby=<c:out value="${searchby}" />&index=<c:out value="${index}"/>" class="tabDetailViewDFLink" style="font-weight: normal;">Return to List&nbsp;</a>					
					</td>
				</tr>
			</tbody>
		</table>
	  </td>
    </tr>
	<input type="hidden" name="navigation" value="">
	<input type="hidden" name="idProses" value="<c:out value="${paymentTransactionLog.idProses}" />">
	
	
		
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%"><slot>Claim Number :&nbsp;</slot></td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><slot><c:out value="${paymentTransactionLog.claimNumber}"/></slot></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"><slot></slot></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><slot></slot></td>
	    </tr>
	
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%"><slot>No Voucher Number :&nbsp;</slot></td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><slot><c:out value="${paymentTransactionLog.noVoucherNumber}"/></slot></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"><slot></slot></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><slot></slot></td>
	    </tr>
	
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%"><slot>Payment Date :&nbsp;</slot></td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><slot><c:out value="${paymentTransactionLog.paymentDate}"/></slot></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"><slot></slot></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><slot></slot></td>
	    </tr>
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%"><slot>Currency :&nbsp;</slot></td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><slot><c:out value="${paymentTransactionLog.currency}"/></slot></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"><slot></slot></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><slot></slot></td>
	    </tr>
	
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%"><slot>Amount Paid :&nbsp;</slot></td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><slot><c:out value="${paymentTransactionLog.amountPaid}"/></slot></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"><slot></slot></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><slot></slot></td>
	    </tr>
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%"><slot>Bank Name :&nbsp;</slot></td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><slot><c:out value="${paymentTransactionLog.bankName}"/></slot></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"><slot></slot></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><slot></slot></td>
	    </tr>
	
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%"><slot>Account Name :&nbsp;</slot></td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><slot><c:out value="${paymentTransactionLog.accountName}"/></slot></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"><slot></slot></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><slot></slot></td>
	    </tr>
	
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%"><slot>Bank Code :&nbsp;</slot></td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><slot><c:out value="${paymentTransactionLog.bankCode}"/></slot></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"><slot></slot></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><slot></slot></td>
	    </tr>
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%"><slot>Account Number :&nbsp;</slot></td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><slot><c:out value="${paymentTransactionLog.accountNumber}"/></slot></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"><slot></slot></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><slot></slot></td>
	    </tr>
				
	
	 <tr>
      <td colspan="20" class="listViewPaginationTdS1" style="padding: 0px;">
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tbody>
				<tr>
					<td style="text-align: left;" class="tabDetailViewDL">&nbsp;
						<a href="#" onclick='' class="listViewPaginationLinkS1" style="font-weight: normal;">View Change Log</a>
					</td>
					<td class="tabDetailViewDL" align="right" nowrap="nowrap">
						<a href="paymenttransactionlog?navigation=search&searchtext=<c:out value="${searchtext}"/>&searchby=<c:out value="${searchby}" />&index=<c:out value="${index}"/>" class="tabDetailViewDFLink" style="font-weight: normal;">Return to List&nbsp;</a>					
					</td>
				</tr>
			</tbody>
		</table>
	  </td>
    </tr>
	
	</tbody>
</table>

	<h2>Foreign Affair</h2>

		
</form>
<script language="javascript">
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
		document.form1.action = "paymenttransactionlog-form";
		document.form1.method = "GET";
		document.form1.submit();
	}
</script>
