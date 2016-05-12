<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>




<form action="productbenefit" method="GET" name="form1" id="form_layout">
<input type="hidden" name="navigation" value="">
	<input type="hidden" name="productBenefitId" value="<c:out value="${productBenefit.productBenefitId}" />">
	
	
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr> 
      <td style="padding-bottom: 2px;">        
        <input title="Edit [Alt+Shift+E]" accesskey="E" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:ubah()" name="Edit" value=" Update " type="button">
        <input title="Hapus [Alt+Shift+H]" accesskey="H" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:hapus()" name="Delete" value=" Delete " type="button">        		
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
						<a href="productbenefit?navigation=list&productId=<c:out value="${productId }" />" class="tabDetailViewDFLink" style="font-weight: normal;">
							<img src="images/back-arrow.png" title="Return to List"/>
						</a>	
					</td>
				</tr>
			</tbody>
		</table>
	  </td>
    </tr>
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Product :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${productBenefit.productId.productName}"/> - (<c:if test="${productBenefit.isEdcEnabled eq 1}">SWIPE</c:if><c:if test="${ productBenefit.isEdcEnabled eq 0}">SHOW</c:if>)</td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Product Code :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><c:out value="${productBenefit.productId.productCode}"/></td>
	    </tr>
			
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Item Category :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${productBenefit.itemCategoryId.itemCategoryName}"/> &nbsp;&nbsp;(<c:out value="${productBenefit.caseCategoryId.caseCategoryName}"/>)</td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Limit Publication :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><c:if test="${productBenefit.benefitShowedOnEdc eq 1}">YES</c:if><c:if test="${ productBenefit.benefitShowedOnEdc eq 0}">NO</c:if></td>
	    </tr>
			
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Cashless Benefit Limit :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%">
			  	<fmt:formatNumber ><c:out value="${productBenefit.benefitLimit}"/></fmt:formatNumber>
			  	
			  	&nbsp;&nbsp; (<c:out value="${productBenefit.benefitUsageType.benefitUsage}" />) &nbsp;per (<c:out value="${productBenefit.measurementUnit.measurementUnitName}" />)
			  </td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Reimbursement Limit :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><fmt:formatNumber ><c:out value="${productBenefit.reimbursementBenefitLimit}"/></fmt:formatNumber></td>
	    </tr>
			
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Provider Contract :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${productBenefit.providerContract}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Treatment Location :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%">	<c:out value="${productBenefit.treatmentLocation.location}" />
	      </td>
	    </tr>
			
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Max Occurance :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%">

			  
			  	<c:choose>
					<c:when test="${productBenefit.maxOccurance eq -1.0}">
					UNLIMITED
					</c:when>
					<c:otherwise >
						<fmt:formatNumber><c:out value="${productBenefit.maxOccurance}" /></fmt:formatNumber>
					</c:otherwise>
				</c:choose>
			  
			  </td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
			
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Discount :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${productBenefit.discount}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%">&nbsp;</td>
	    </tr>
			 	
		
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Modified Time :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${productBenefit.modifiedTime}"/> - <c:out value="${productBenefit.modifiedBy}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">Created Time :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${productBenefit.createdTime}"/> - <c:out value="${productBenefit.createdBy}"/></td>
	    </tr>
			
	
		
			<tr>
      <td colspan="20"  style="padding: 0px;">
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tbody>
				<tr>
					<td style="text-align: left;" class="tabDetailViewDL">&nbsp;
						
					</td>
<!-- 					<td class="tabDetailViewDL" align="right" nowrap="nowrap"> -->
<!-- 						<a href="productbenefit?navigation=list&productId=<c:out value="${productId }" />" class="tabDetailViewDFLink" style="font-weight: normal;">Return to List&nbsp;</a>					 -->
<!-- 					</td> -->
				</tr>
			</tbody>
		</table>
	  </td>
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
	function kembali (){
		document.form1.navigation.value = "back";
		document.form1.submit();
	}
	function ubah (){
		document.form1.navigation.value = "update";
		document.form1.action = "productbenefit-form";
		document.form1.method = "GET";
		document.form1.submit();
	}
</script>
