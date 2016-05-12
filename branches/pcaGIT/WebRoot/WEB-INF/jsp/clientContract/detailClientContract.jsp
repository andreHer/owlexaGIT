<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Detail Client Contract History</h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>

<form action="clientcontract" method="GET" name="form1" id="form_layout">
<input type="hidden" name="navigation" value="">
	<input type="hidden" name="clientContractId" value="<c:out value="${clientContract.clientContractId}" />">
	<input type="hidden" name="clientContractItemId" value="">
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr> 
      <td style="padding-bottom: 2px;">        
        
        	<c:if test="${clientContract.contractStatus eq 0 or clientContract.contractStatus eq null}">
			<input title="Edit [Alt+E]" accesskey="E" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:ubah()" name="Edit" value=" Update " type="button">
		
	        <input title="Hapus [Alt+H]" accesskey="H" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:hapus()" name="Delete" value=" Delete " type="button">        		
        
            <input title="Activate [Alt+A]" accesskey="H" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:activate()" name="Activ" value=" Activate " type="button">
            </c:if>
		
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
						
					</td>
					<td class="tabDetailViewDL" align="right" nowrap="nowrap">
						<a href="clientcontract?navigation=search&searchtext=<c:out value="${searchtext}"/>&searchby=<c:out value="${searchby}" />&index=<c:out value="${index}"/>" class="tabDetailViewDFLink" style="font-weight: normal;">Return to List&nbsp;</a>					
					</td>
				</tr>
			</tbody>
		</table>
	  </td>
    </tr>
	
	
	
		
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Client Contract Number :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${clientContract.clientContractNumber}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Client : &nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><c:out value="${clientContract.clientId.clientName}" /></td>
	    </tr>
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Contract Date :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${clientContract.contractStartDate}"/> s/d <c:out value="${clientContract.contractEndDate}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Contract Type :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><c:out value="${clientContract.contractTypeId.contractTypeName}" /> &nbsp;&nbsp;(<c:out value="${clientContract.currencyId.currencyName}" />)</td>
	    </tr>
			
				
		<tr>
			<td class="tabDetailViewDL" valign="top" width="17%">Contract Status :&nbsp;</td>
		  	<td class="tabDetailViewDF" valign="top" width="33%"><c:if test="${clientContract.contractStatus eq 0}">DRAFT</c:if><c:if test="${clientContract.contractStatus eq 1}">ACTIVE</c:if><c:if test="${clientContract.contractStatus eq 4}">TERMINATED</c:if></td>
	      	<td class="tabDetailViewDL" valign="top" width="17%">Product Type :&nbsp;</td>
		  	<td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${clientContract.productTypeId.productTypeName}"/></td>	      
	    </tr>
	    
	    
		<tr>
	      	<td class="tabDetailViewDL" valign="top" width="17%">&nbsp;</td>		  
			<td class="tabDetailViewDF" valign="top" width="33%"></td>
	      	<td class="tabDetailViewDL" valign="top" width="15%"></td>
	      	<td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
		
			
		<tr>
	      	<td class="tabDetailViewDL" valign="top" width="17%">Is Using Proration :&nbsp;</td>		  
			<td class="tabDetailViewDF" valign="top" width="33%"><c:if test="${clientContract.isUsingProration eq 1}">YES</c:if> <c:if test="${clientContract.isUsingProration eq 0}">NO</c:if></td>
	      	<td class="tabDetailViewDL" valign="top" width="17%">Pro-Rate Method :&nbsp;</td>
		    <td class="tabDetailViewDF" valign="top" width="33%">
		    	<c:if test="${clientContract.paymentPeriode eq 1 }">MONTHLY</c:if>
		    	<c:if test="${clientContract.paymentPeriode eq 2 }">QUARTERLY</c:if>
		    	<c:if test="${clientContract.paymentPeriode eq 3 }">SEMESTER</c:if>
		    	<c:if test="${clientContract.paymentPeriode eq -1 }">NO-PRORATE</c:if>
		    </td>
	    </tr>
		<tr>
	      	<td class="tabDetailViewDL" valign="top" width="17%">Is Using Refund :&nbsp;</td>		  
			<td class="tabDetailViewDF" valign="top" width="33%"><c:if test="${clientContract.isRefundAvailable eq 1}">YES</c:if> <c:if test="${clientContract.isRefundAvailable eq 0}">NO</c:if></td>
	      	<td class="tabDetailViewDL" valign="top" width="17%">Membership Periode :&nbsp;</td>
		    <td class="tabDetailViewDF" valign="top" width="33%">
		    	<c:if test="${clientContract.membershipPeriode eq 1 }">MONTHLY</c:if>
		    	<c:if test="${clientContract.membershipPeriode eq 2 }">QUARTERLY</c:if>
		    	<c:if test="${clientContract.membershipPeriode eq 3 }">SEMESTER</c:if>
		    	<c:if test="${clientContract.membershipPeriode eq 4 }">ANNUALY</c:if>	
		    </td>
	    </tr>
		<tr>
	      	<td class="tabDetailViewDL" valign="top" width="17%">Is Using Volume :&nbsp;</td>		  
			<td class="tabDetailViewDF" valign="top" width="33%"><c:if test="${clientContract.isUsingVolumeLevel eq 1}">YES</c:if><c:if test="${clientContract.isUsingVolumeLevel eq 0}">NO</c:if></td>
	      	<td class="tabDetailViewDL" valign="top" width="17%">Payment Periode :&nbsp;</td>
		    <td class="tabDetailViewDF" valign="top" width="33%">
		    	<c:if test="${clientContract.paymentPeriode eq 1 }">MONTHLY</c:if>
		    	<c:if test="${clientContract.paymentPeriode eq 2 }">QUARTERLY</c:if>
		    	<c:if test="${clientContract.paymentPeriode eq 3 }">SEMESTER</c:if>
		    	<c:if test="${clientContract.paymentPeriode eq 4 }">ANNUALY</c:if>
		    </td>
	    </tr>
	    
	    <tr>
	      	<td class="tabDetailViewDL" valign="top" width="17%">Cut Off Date :&nbsp;</td>
		  	<td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${clientContract.billingCutOffDate}"/></td>
	      	<td class="tabDetailViewDL" valign="top" width="15%">Invoice Due Length :&nbsp;</td>
	      	<td class="tabDetailViewDF" valign="top" width="35%"><c:out value="${clientContract.invoiceDueLength}"/> day(s)</td>
	    </tr>
	    <tr>
	      	<td class="tabDetailViewDL" valign="top" width="17%">Discount (%) :&nbsp;</td>
		  	<td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${clientContract.discountPercentage}"/> %</td>
	      	<td class="tabDetailViewDL" valign="top" width="15%">&nbsp;</td>
	      	<td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>			
	    <tr>
	      	<td class="tabDetailViewDL" valign="top" width="17%">&nbsp;</td>
		  	<td class="tabDetailViewDF" valign="top" width="33%">&nbsp;</td>
	      	<td class="tabDetailViewDL" valign="top" width="15%"></td>
	      	<td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>				
		<tr>
	      	<td class="tabDetailViewDL" valign="top" width="17%">Total Member :&nbsp;</td>
		  	<td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${clientContract.totalMember}"/></td>
	      	<td class="tabDetailViewDL" valign="top" width="15%"></td>
	      	<td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>			
		<tr>
	      	<td class="tabDetailViewDL" valign="top" width="17%">Description :&nbsp;</td>
	      	<td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${clientContract.description}"/></td>
	      	<td class="tabDetailViewDL" valign="top" width="15%"></td>
	      	<td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
	
			
			<tr>
			<td class="tabDetailViewDL" valign="top" width="17%">Modified Time :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${clientContract.modifiedTime}"/> - <c:out value="${clientContract.modifiedBy}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">Created Time :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${clientContract.createdTime}"/> - <c:out value="${clientContract.createdBy}"/></td>
	      
	    </tr>
			
			
			
				
	
	 <tr>
      <td colspan="20" class="listViewPaginationTdS1" style="padding: 0px;">
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tbody>
				<tr>
					<td style="text-align: left;" class="tabDetailViewDL">&nbsp;
						
					</td>
					<td class="tabDetailViewDL" align="right" nowrap="nowrap">
						<a href="clientcontract?navigation=search&searchtext=<c:out value="${searchtext}"/>&searchby=<c:out value="${searchby}" />&index=<c:out value="${index}"/>" class="tabDetailViewDFLink" style="font-weight: normal;">Return to List&nbsp;</a>					
					</td>
				</tr>
			</tbody>
		</table>
	  </td>
    </tr>
	
	</tbody>
</table>

<br />

<br />

<c:if test="${theUser.roleId.roleId ne 6}">
	<input type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:tambahItem()" value=" Add Additional Service ">
</c:if>
<br />
<br />
 	
<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	<tr>
      <td colspan="20" align="right"><table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tbody>
          <tr>
            <td class="listViewPaginationTdS1" align="left"></td>
            <td class="listViewPaginationTdS1" align="right" nowrap="nowrap">
				&nbsp;
			</td>
          </tr>
        </tbody>
      </table></td>
    </tr>
	<tr height="20">
		<td width="2%" nowrap="nowrap" class="listViewThS1" scope="col">No.</td>		
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Item Name</td>		
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Billing Subject</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%">Claim Type</td>
		
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Coverage Type</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Volume</td>
		
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Item Price</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Item Unit</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Description</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%"></td>		
	</tr>


	<c:forEach items="${ClientContractItems}" var="clientContractItem" varStatus="status" >

	 <tr height="20">
      	<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${status.index + 1}" /></td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${clientContractItem.itemId.itemName}" />		
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:if test="${clientContractItem.contractUnitType eq 1 }">PER CLAIM</c:if>
			<c:if test="${clientContractItem.contractUnitType eq 2 }">PER MEMBER</c:if>
			<c:if test="${clientContractItem.contractUnitType eq 3 }">MONTHLY FIXED FEE</c:if>
			<c:if test="${clientContractItem.contractUnitType eq 4 }">QUARTER FIXED FEE</c:if>
			<c:if test="${clientContractItem.contractUnitType eq 5 }">SEMESTER FIXED FEE</c:if>
			<c:if test="${clientContractItem.contractUnitType eq 6 }">ANNUAL FIXED FEE</c:if>
					
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">	
			<c:if test="${clientContractItem.claimType eq 1}">REIMBURSEMENT</c:if>		
			<c:if test="${clientContractItem.claimType eq 2}">CASHLESS</c:if>
			<c:if test="${clientContractItem.claimType eq 3}">ALL</c:if>				
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${clientContractItem.caseCategoryId.caseCategoryName}" />		
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<fmt:formatNumber><c:out value="${clientContractItem.volumeStart}" /></fmt:formatNumber> s/d <fmt:formatNumber><c:out value="${clientContractItem.volumeEnd}" /></fmt:formatNumber> 		
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<fmt:formatNumber><c:out value="${clientContractItem.itemPrice}" /></fmt:formatNumber>		
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			
			<c:if test="${clientContractItem.contractUnitType eq 1}">PER CLAIM</c:if>		
			<c:if test="${clientContractItem.contractUnitType eq 2}">PER MEMBER</c:if>	
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${clientContractItem.description}" />		
		</td>
					   		   		   		
					   		   		
					   		   		
        <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top">		

			<!-- ini default edit table for each data -->
			<a href="javascript:ubahItem('<c:out value="${clientContractItem.clientContractItemId}" />')">
				<img src="images/edit.gif" class="action_icon" alt="Edit" title="Edit"></a>

			<!-- ini default delete table for each data -->
			<a href="javascript:hapusItem('<c:out value="${clientContractItem.clientContractItemId}" />')">
				<img src="images/delete.gif" class="action_icon" alt="Delete" title="Delete"></a>
		
	  </td>
    </tr>
   
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	
	</c:forEach>
	
<tr>
            <td class="listViewPaginationTdS1" align="left"></td>
            <td class="listViewPaginationTdS1" align="right" nowrap="nowrap" colspan="20">
			</td>
          </tr>
	</tbody>
	</table>

		
</form>
<script language="javascript">
function activate (){
	var delConfirm = window.confirm ("Are You Sure Want To Activate This Contract ?");
		if (delConfirm){
			document.form1.navigation.value = "activate";
			document.form1.submit();
		}
	}
	function hapus (){
		var delConfirm = window.confirm ("Are You Sure Want To Delete This Entry ?");
		if (delConfirm){
			document.form1.navigation.value = "delete";
			document.form1.submit();
		}
	}
	function hapusItem (idx){
		var delConfirm = window.confirm ("Are You Sure Want To Delete This Entry ?");
			if (delConfirm){
				document.form1.action = "clientcontractitem";
				document.form1.method = "POST";
				document.form1.clientContractItemId.value = idx;
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
		document.form1.action = "clientcontract-form";
		document.form1.method = "GET";
		document.form1.submit();
	}
	function ubahItem (idx){
		document.form1.navigation.value = "update";
		document.form1.clientContractItemId.value = idx;
		document.form1.action = "clientcontractitem-form";
		document.form1.method = "GET";
		document.form1.submit();
	}
	function tambahItem (){
		document.form1.navigation.value = "addbulk";
		document.form1.action = "clientcontractitem-form";
		document.form1.method = "GET";
		document.form1.submit();
	}
</script>
