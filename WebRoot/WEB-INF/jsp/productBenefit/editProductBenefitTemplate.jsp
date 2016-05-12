<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ page import="java.util.Vector" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>



<script type='text/javascript' src='dwr/interface/AJAXClaimItemService.js'></script>
<script type='text/javascript' src='dwr/engine.js'></script>
<script type='text/javascript' src='dwr/util.js'></script>
<%
String navigation = WebUtil.getAttributeString(request,"navigation","");
%>

 

<!-- Page Title Stop-->
<%
String alert = (String) request.getAttribute("alert");
int index = 0;
int totalIndex = 0;
int totalItem = WebUtil.getAttributeInteger(request,"totalItem",0).intValue();

int count = 0;
int countSet = 0;
int i = 0;



if (alert != null && !alert.trim().equals("")) {%>
	<div id="warning" align="center">
		<c:out value="${alert}"></c:out>
	</div>
	<%}%>

<%

%>
<!-- Search Container Start -->

<form name="form1" action="productbenefit" method="POST">
<input type="hidden" name="navigation" value="addtemplate-save">
<input type="hidden" name="arah" value="">
<input type="hidden" name="index" value="<c:out value="${index}" />">
<input type="hidden" name="productId" value="<c:out value="${productId }" />">

<br /> 	
<h3>Benefit - Layer Utama</h3>
<table class="tabForm" cellspacing="0" cellpadding="0">
	<tbody>
	
	<tr height="20">
		<td width="6%" nowrap="nowrap" class="listViewThS1" scope="col">
			<img src="images/blank.gif" alt="asd" height="1" width="1">No.</td>		   		   		   			
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Item Category&nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Case Category &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Usage Type &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%">Level &nbsp;</td>   		   		   
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%">Cashless Limit &nbsp;</td>        
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%">Reimburse Limit &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%">Per Case Limit &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%">Deductible &nbsp;</td>				
	</tr>

	<c:forEach items="${productBenefitCollection}" var="productBenefit" varStatus="stat">
		<input type="hidden" name="productBenefitId" value="<c:out value="${productBenefit.productBenefitId}" />">
		<input type="hidden" name="layer" value="0">
	 <tr height="20">
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<c:out value="${stat.index+1}" />			 
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<c:out value="${productBenefit.itemCategoryId.itemCategoryName }" /> 
		</td>
    
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<c:out value="${productBenefit.caseCategoryId.caseCategoryName}" />
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe"  valign="top">
			<c:out value="${productBenefit.benefitUsageType.benefitUsage}" />
		</td>  		   	
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: left;">
			<c:if test="${productBenefit.treatmentLevel eq 0}">
			KOMPLEKS
			</c:if>
			<c:if test="${productBenefit.treatmentLevel eq 1}">
			BESAR
			</c:if>
			<c:if test="${productBenefit.treatmentLevel eq 2}">
			SEDANG
			</c:if>
			<c:if test="${productBenefit.treatmentLevel eq 3}">
			KECIL
			</c:if>
			
		</td>  	
		<td class="oddListRowS1" bgcolor="#4f85c6" nowrap="nowrap" valign="top">      		
      		<input type="text" class="input2" style="text-align: right;" size="10" value=""  name="benefitLimit"  >
		</td>  	                 
		<td class="oddListRowS1" bgcolor="#4f85c6" nowrap="nowrap" valign="top">      		
      		<input type="text" class="input2" style="text-align: right;" size="10" value=""  name="reimburseBenefitLimit"  >
		</td>  	 
		<td class="oddListRowS1" bgcolor="#4f85c6" nowrap="nowrap" valign="top">      		
      		<input type="text" class="input2" style="text-align: right;" size="10" value=""  name="benefitPerCase"  >
		</td>  	                 
		<td class="oddListRowS1" bgcolor="#4f85c6" nowrap="nowrap" valign="top">      		
      		<input type="text" class="input2" style="text-align: right;" size="10" value=""  name="deductible"  >
		</td>  	 
    </tr>
   
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	</c:forEach>
	 <tr height="20">
      <td  class="listViewThS1" align="center" bgcolor="#4f85c6" nowrap="nowrap" valign="top" colspan=10></td>    
    </tr>
	</tbody>
	</table>

<c:if test="${fn:length(productBenefitLayer1Collection) > 0 }">
	<br />
	<br />
		
<h3>Benefit - Layer 1</h3>
<table class="tabForm" cellspacing="0" cellpadding="0">
	<tbody>
	
	<tr height="20">
		<td width="6%" nowrap="nowrap" class="listViewThS1" scope="col">
			<img src="images/blank.gif" alt="asd" height="1" width="1">No.</td>		   		   		   			
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Item Category&nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Case Category &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Usage Type &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%">Level &nbsp;</td>   		   		   
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%">Cashless Limit &nbsp;</td>        
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%">Reimburse Limit &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%">Per Case Limit &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%">Deductible &nbsp;</td>				
	</tr>

	<c:forEach items="${productBenefitLayer1Collection}" var="productBenefit" varStatus="stat">
	<input type="hidden" name="productBenefitId" value="<c:out value="${productBenefit.productBenefitId}" />">
	<input type="hidden" name="layer" value="1">
	 <tr height="20">
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<c:out value="${stat.index+1}" />			 
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<c:out value="${productBenefit.itemCategoryId.itemCategoryName }" /> 
		</td>
    
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<c:out value="${productBenefit.caseCategoryId.caseCategoryName}" />
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe"  valign="top">
			<c:out value="${productBenefit.benefitUsageType.benefitUsage}" />
		</td>  		   	
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: left;">
			<c:if test="${productBenefit.treatmentLevel eq 0}">
			KOMPLEKS
			</c:if>
			<c:if test="${productBenefit.treatmentLevel eq 1}">
			BESAR
			</c:if>
			<c:if test="${productBenefit.treatmentLevel eq 2}">
			SEDANG
			</c:if>
			<c:if test="${productBenefit.treatmentLevel eq 3}">
			KECIL
			</c:if>
			
		</td>  	
		<td class="oddListRowS1" bgcolor="#4f85c6" nowrap="nowrap" valign="top">      		
      		<input type="text" class="input2" style="text-align: right;" size="10" value=""  name="benefitLimit"  >
		</td>  	                 
		<td class="oddListRowS1" bgcolor="#4f85c6" nowrap="nowrap" valign="top">      		
      		<input type="text" class="input2" style="text-align: right;" size="10" value=""  name="reimburseBenefitLimit"  >
		</td>  	 
		<td class="oddListRowS1" bgcolor="#4f85c6" nowrap="nowrap" valign="top">      		
      		<input type="text" class="input2" style="text-align: right;" size="10" value=""  name="benefitPerCase"  >
		</td>  	                 
		<td class="oddListRowS1" bgcolor="#4f85c6" nowrap="nowrap" valign="top">      		
      		<input type="text" class="input2" style="text-align: right;" size="10" value=""  name="deductible"  >
		</td>  	 
    </tr>
   
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	</c:forEach>
	 <tr height="20">
      <td  class="listViewThS1" align="center" bgcolor="#4f85c6" nowrap="nowrap" valign="top" colspan=10></td>    
    </tr>
	</tbody>
	</table>
		
</c:if>	
<c:if test="${fn:length(productBenefitLayer2Collection) > 0 }">
<br />
	<br />
<h3>Benefit - Layer 2</h3>
<table class="tabForm" cellspacing="0" cellpadding="0">
	<tbody>
	
	<tr height="20">
		<td width="6%" nowrap="nowrap" class="listViewThS1" scope="col">
			<img src="images/blank.gif" alt="asd" height="1" width="1">No.</td>		   		   		   			
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Item Category&nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Case Category &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Usage Type &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%">Level &nbsp;</td>   		   		   
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%">Cashless Limit &nbsp;</td>        
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%">Reimburse Limit &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%">Per Case Limit &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%">Deductible &nbsp;</td>				
	</tr>

	<c:forEach items="${productBenefitLayer2Collection}" var="productBenefit" varStatus="stat">
	<input type="hidden" name="productBenefitId" value="<c:out value="${productBenefit.productBenefitId}" />">
	<input type="hidden" name="layer" value="2">
	 <tr height="20">
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<c:out value="${stat.index+1}" />			 
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<c:out value="${productBenefit.itemCategoryId.itemCategoryName }" /> 
		</td>
    
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<c:out value="${productBenefit.caseCategoryId.caseCategoryName}" />
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe"  valign="top">
			<c:out value="${productBenefit.benefitUsageType.benefitUsage}" />
		</td>  		   	
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: left;">
			<c:if test="${productBenefit.treatmentLevel eq 0}">
			KOMPLEKS
			</c:if>
			<c:if test="${productBenefit.treatmentLevel eq 1}">
			BESAR
			</c:if>
			<c:if test="${productBenefit.treatmentLevel eq 2}">
			SEDANG
			</c:if>
			<c:if test="${productBenefit.treatmentLevel eq 3}">
			KECIL
			</c:if>
			
		</td>  	
		<td class="oddListRowS1" bgcolor="#4f85c6" nowrap="nowrap" valign="top">      		
      		<input type="text" class="input2" style="text-align: right;" size="10" value=""  name="benefitLimit"  >
		</td>  	                 
		<td class="oddListRowS1" bgcolor="#4f85c6" nowrap="nowrap" valign="top">      		
      		<input type="text" class="input2" style="text-align: right;" size="10" value=""  name="reimburseBenefitLimit"  >
		</td>  	 
		<td class="oddListRowS1" bgcolor="#4f85c6" nowrap="nowrap" valign="top">      		
      		<input type="text" class="input2" style="text-align: right;" size="10" value=""  name="benefitPerCase"  >
		</td>  	                 
		<td class="oddListRowS1" bgcolor="#4f85c6" nowrap="nowrap" valign="top">      		
      		<input type="text" class="input2" style="text-align: right;" size="10" value=""  name="deductible"  >
		</td>  	 
    </tr>
   
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	</c:forEach>
	 <tr height="20">
      <td  class="listViewThS1" align="center" bgcolor="#4f85c6" nowrap="nowrap" valign="top" colspan=10></td>    
    </tr>
	</tbody>
	</table>
	</c:if>
	<c:if test="${fn:length(productBenefitLayer3Collection) > 0 }">
	<br />
	<br />
	
<h3>Benefit - Layer 3</h3>
<table class="tabForm" cellspacing="0" cellpadding="0">
	<tbody>
	
	<tr height="20">
		<td width="6%" nowrap="nowrap" class="listViewThS1" scope="col">
			<img src="images/blank.gif" alt="asd" height="1" width="1">No.</td>		   		   		   			
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Item Category&nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Case Category &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Usage Type &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%">Level &nbsp;</td>   		   		   
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%">Cashless Limit &nbsp;</td>        
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%">Reimburse Limit &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%">Per Case Limit &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%">Deductible &nbsp;</td>				
	</tr>

	<c:forEach items="${productBenefitLayer3Collection}" var="productBenefit" varStatus="stat">
	<input type="hidden" name="productBenefitId" value="<c:out value="${productBenefit.productBenefitId}" />">
	<input type="hidden" name="layer" value="3">
	 <tr height="20">
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<c:out value="${stat.index+1}" />			 
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<c:out value="${productBenefit.itemCategoryId.itemCategoryName }" /> 
		</td>
    
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<c:out value="${productBenefit.caseCategoryId.caseCategoryName}" />
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe"  valign="top">
			<c:out value="${productBenefit.benefitUsageType.benefitUsage}" />
		</td>  		   	
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: left;">
			<c:if test="${productBenefit.treatmentLevel eq 0}">
			KOMPLEKS
			</c:if>
			<c:if test="${productBenefit.treatmentLevel eq 1}">
			BESAR
			</c:if>
			<c:if test="${productBenefit.treatmentLevel eq 2}">
			SEDANG
			</c:if>
			<c:if test="${productBenefit.treatmentLevel eq 3}">
			KECIL
			</c:if>
			
		</td>  	
		<td class="oddListRowS1" bgcolor="#4f85c6" nowrap="nowrap" valign="top">      		
      		<input type="text" class="input2" style="text-align: right;" size="10" value=""  name="benefitLimit"  >
		</td>  	                 
		<td class="oddListRowS1" bgcolor="#4f85c6" nowrap="nowrap" valign="top">      		
      		<input type="text" class="input2" style="text-align: right;" size="10" value=""  name="reimburseBenefitLimit"  >
		</td>  	 
		<td class="oddListRowS1" bgcolor="#4f85c6" nowrap="nowrap" valign="top">      		
      		<input type="text" class="input2" style="text-align: right;" size="10" value=""  name="benefitPerCase"  >
		</td>  	                 
		<td class="oddListRowS1" bgcolor="#4f85c6" nowrap="nowrap" valign="top">      		
      		<input type="text" class="input2" style="text-align: right;" size="10" value=""  name="deductible"  >
		</td>  	 
    </tr>
   
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	</c:forEach>
	 <tr height="20">
      <td  class="listViewThS1" align="center" bgcolor="#4f85c6" nowrap="nowrap" valign="top" colspan=10></td>    
    </tr>
	</tbody>
	</table>
	</c:if>
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td style="padding-top: 2px;">
	    <input type="hidden" name="notyet" value="">
	    <input title="Save  [Alt+Shift+B]" accesskey="B" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:simpan()" name="returnBatch" value=" Save & Return To Product " type="button">
        <input title="Cancel [Alt+Shift+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:cancel()" name="Cancel" value=" Cancel " type="button">
	  </td>
      <td align="right"></td>
    </tr>
  </tbody>
</table>
	
</form>
	
<!-- Table Content Stop -->
	<!-- Table Toolbar Start -->
	
		<!-- Table Toolbar Stop -->


<!-- Table Container Stop -->

<script language="JavaScript">
	function simpan(){
	
		document.form1.navigation.value = "addtemplate-save";
		document.form1.action = "productbenefit";
		document.form1.submit();
	}
		
	function asCharge (idx){
		//window.alert("clicked : " + idx);
		
	}
	function cancel(){
		document.form1.navigation.value = "detail";
		document.form1.action = "product";
		document.form1.submit();
	}

</script>
