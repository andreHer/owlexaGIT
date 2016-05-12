<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>



<ul id="maintab" class="shadetabs">

	<li class="selected">
		<a href="quotation?navigation=detail&quotationId=<c:out value="${quotation.quotationId}" />" rel="tcontent1">Detail Quotation</a>
	</li>
	
	<li>
		<a href="quotationproduct?navigation=listquotation&quotationId=<c:out value="${quotation.quotationId}" />" rel="tcontent4">Product List</a>
	</li>	
	<li>
		<a href="#" rel="tcontent5">Risk Configuration</a>
	</li>		
	<li >
		<a href="#" rel="tcontent7">Loading Cost</a>
	</li>
     

</ul>
<br />

<form action="quotation" method="GET" name="form1" id="form_layout">

	<input type="hidden" name="navigation" value="">
	<input type="hidden" name="quotationId" value="<c:out value="${quotation.quotationId}" />">
	
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr> 
      <td style="padding-bottom: 2px;">
      	<c:if test="${quotation.status.statusId eq -1}">
		<input title="Edit [Alt+E]" accesskey="E" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:ubah()" name="Edit" value=" Update " type="button">
		
        <input title="Hapus [Alt+H]" accesskey="H" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:hapus()" name="Delete" value=" Delete " type="button">
                		
        
        <input title="Recalculate [Alt+H]" accesskey="H" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:calculate()" name="Delete" value=" Calculate " type="button">
        
        <input title="Activate [Alt+H]" accesskey="H" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:activate()" name="Delete" value=" Activate " type="button">
        </c:if>
		
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
	
					</td>
					<td class="tabDetailViewDL" align="right" nowrap="nowrap">
						<a href="quotation?navigation=search&searchtext=<c:out value="${searchtext}"/>&searchby=<c:out value="${searchby}" />&index=<c:out value="${index}"/>" class="tabDetailViewDFLink" style="font-weight: normal;">Return to List&nbsp;</a>					
					</td>
				</tr>
			</tbody>
		</table>
	  </td>
    </tr>
	
	
	
		
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Quotation No :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${quotation.quotationNo}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Client : &nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><c:out value="${quotation.clientId.clientName}" /></td>
	    </tr>
	
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Quotation Date :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${quotation.quotationDate}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Member Group : &nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><c:out value="${quotation.memberGroupId.groupName}" /></td>
	    </tr>
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Request Date :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${quotation.requestDate}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Broker / Agent : &nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><c:out value="${quotation.brokerId.brokerName}" /></td>
	    </tr>
			
	
		
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Effective Date :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${quotation.effectiveDate}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Status :&nbsp;&nbsp; </td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><c:out value="${quotation.status.status}" /></td>
	    </tr>
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Renewal Date :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${quotation.renewalDate}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
			
		<tr>
	      	  <td class="tabDetailViewDL" valign="top" width="17%">Payment Mode :&nbsp;</td>		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${quotation.paymentMode.paymentModeName}"/></td>
	      	  <td class="tabDetailViewDL" valign="top" width="17%">Quotation Type :&nbsp;</td>		  
			  <td class="tabDetailViewDF" valign="top" width="33%">
				  <c:if test="${quotation.quotationTypeId.quotationTypeId eq 1}">NEW</c:if>
				  <c:if test="${quotation.quotationTypeId.quotationTypeId eq 2}">RENEWAL</c:if>
			  </td>
	    </tr>
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
			
			
	
			
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Is Individual :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%">
			  	<c:if test="${quotation.isIndividual eq 1}" >YES</c:if>
			  	<c:if test="${quotation.isIndividual eq 0}" >NO</c:if>
			  </td>
	      	  <td class="tabDetailViewDL" valign="top" width="17%">Is Wife Only :&nbsp;</td>		  
			  <td class="tabDetailViewDF" valign="top" width="33%">			  
			  	<c:if test="${quotation.isWifeOnly eq 1}" >YES</c:if>
			  	<c:if test="${quotation.isWifeOnly eq 0}" >NO</c:if>
			  </td>
	    </tr>
			
		<tr>
	      	<td class="tabDetailViewDL" valign="top" width="17%">Is Family Plan :&nbsp;</td>		  
			<td class="tabDetailViewDF" valign="top" width="33%">
			  	<c:if test="${quotation.isIndividual eq 1}" >YES</c:if>
			  	<c:if test="${quotation.isIndividual eq 0}" >NO</c:if>
			</td>
	       	<td class="tabDetailViewDL" valign="top" width="17%">Is Unit Premi :&nbsp;</td>		  
		  	<td class="tabDetailViewDF" valign="top" width="33%">			  
			  	<c:if test="${quotation.isUnitPremi eq 1}" >YES</c:if>
			  	<c:if test="${quotation.isUnitPremi eq 0}" >NO</c:if>
		  	</td>
	    </tr>
			
		
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Is Discount Group By Employee :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%">
			  	<c:if test="${quotation.isDiscountGroupByEmployee eq 1}" >YES</c:if>
			  	<c:if test="${quotation.isDiscountGroupByEmployee eq 0}" >NO</c:if>
			  	</td>
	      <td class="tabDetailViewDL" valign="top" width="17%">Max Children :&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${quotation.maxChildren}"/></td>
	    </tr>
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Installment Amount :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${quotation.installmentAmount}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Aggregate Limit :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${quotation.aggregateLimit}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Toc :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${quotation.toc}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
		
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Is Agent Commision Gross Premi :&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="33%">			  
			  	<c:if test="${quotation.isAgentCommisionGrossPremi eq 1}" >YES</c:if>
			  	<c:if test="${quotation.isAgentCommisionGrossPremi eq 0}" >NO</c:if>
		  </td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
			
	
		
		
	
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%">&nbsp;</td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>		
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Is ASO Policy :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%">			  
			  	<c:if test="${quotation.isDiscountGroupByEmployee eq 1}" >YES</c:if>
			  	<c:if test="${quotation.isDiscountGroupByEmployee eq 0}" >NO</c:if>			  	
			</td>
	      	<td class="tabDetailViewDL" valign="top" width="17%">ASO Deposit :&nbsp;</td>
		  
			<td class="tabDetailViewDF" valign="top" width="33%"><fmt:formatNumber><c:out value="${quotation.asoDeposit}"/></fmt:formatNumber></td>
	    </tr>
			
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">BRC Date :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${quotation.brcDate}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Requested By :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${quotation.requestedBy}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
	
			
			
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Uang Pertanggungan :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${quotation.uangPertanggungan}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
			
		<tr>
	      	  <td class="tabDetailViewDL" valign="top" width="17%">Uang Premi :&nbsp;</td>		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><fmt:formatNumber><c:out value="${quotation.uangPremi}"/></fmt:formatNumber></td>
		      <td class="tabDetailViewDL" valign="top" width="17%">Total Member :&nbsp;</td>		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${quotation.totalMember}"/></td>
	    </tr>
			
		
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Budget Premi :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${quotation.budgetPremi}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Comission :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${quotation.comission}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Claim Ratio :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${quotation.claimRatio}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
			
	
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Remarks :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="33%" colspan=3><c:out value="${quotation.remarks}"/></td>
	      
	    </tr>
	
			
	</tbody>
</table>


		
</form>
<script language="javascript">
	function hapus (){
	var delConfirm = window.confirm ("Are You Sure Want To Delete This Entry ?");
		if (delConfirm){
			document.form1.navigation.value = "delete";
			document.form1.submit();
		}
	}
	function calculate (){
	var delConfirm = window.confirm ("Are You Sure Want To Recalculate This Quotation ?");
		if (delConfirm){
			document.form1.navigation.value = "calculate";
			document.form1.submit();
		}
	}
	function kembali (){
		document.form1.navigation.value = "back";
		document.form1.submit();
	}
	function ubah (){
		document.form1.navigation.value = "update";
		document.form1.action = "quotation-form";
		document.form1.method = "GET";
		document.form1.submit();
	}
</script>
