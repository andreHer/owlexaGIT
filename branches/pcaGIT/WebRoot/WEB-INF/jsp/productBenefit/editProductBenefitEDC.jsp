<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ page import="java.util.Vector" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<script type='text/javascript' src='dwr/interface/AJAXClaimItemService.js'></script>
<script type='text/javascript' src='dwr/engine.js'></script>
<script type='text/javascript' src='dwr/util.js'></script>
<%
String navigation = WebUtil.getAttributeString(request,"navigation","");
%>

 <!-- Page Title Start // Should be put on <Title> tag-->

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

 	
<table class="tabForm" cellspacing="0" cellpadding="0">
	<tbody>
	
	<tr height="20">
		<td width="6%" nowrap="nowrap" class="listViewThS1" scope="col">
			<img src="images/blank.gif" alt="asd" height="1" width="1">No.</td>		

<!-- ini default generated table from database -->
		   		   		   			
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">
				Item Category&nbsp;
				</td>
				  		
			
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">
				Case Category &nbsp;</td>
			
		
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="22%">
				Usage Type &nbsp;
				</td>

				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Treatment Level &nbsp;
				</td>
			   		   		   		   		   				   		   						   		   	  
	   		
			   		   		   
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				EDC Enabled &nbsp;</td>
                                
                                    
                                        
                                        <td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Limit Publication &nbsp;</td>
				
	</tr>

	<c:forEach items="${productBenefitCollection}" var="productBenefit" varStatus="stat">
	<input type="hidden" name="productBenefitId" value="<c:out value="${productBenefit.productBenefitId}" />">
	 <tr height="20">
	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<c:out value="${stat.index+1}" />.			 
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<c:out value="${productBenefit.itemCategoryId.itemCategoryName }" /> 
		</td>
    
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<c:out value="${productBenefit.caseCategoryId.caseCategoryName}" />
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<c:out value="${productBenefit.benefitUsageType.benefitUsage}" />
		</td>  		   	
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;">
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
      			<input type='radio' name='edc_<c:out value="${productBenefit.productBenefitId}" />' value = 'yes' <c:if test="${productBenefit.isEdcEnabled eq 1 }">checked</c:if> />Yes&nbsp;
      			<input type='radio' name='edc_<c:out value="${productBenefit.productBenefitId}" />' value = 'no' <c:if test="${productBenefit.isEdcEnabled eq 0 or productBenefit.isEdcEnabled eq null }">checked</c:if>/>No&nbsp;
		</td>  	 
                
		  	<td class="oddListRowS1" bgcolor="#4f85c6" nowrap="nowrap" valign="top">      
		  				
      			<input type='radio' name='published_<c:out value="${productBenefit.productBenefitId}" />' value='yes' <c:if test="${productBenefit.benefitShowedOnEdc eq 1 }">checked</c:if> />Yes&nbsp;
      			<input type='radio' name='published_<c:out value="${productBenefit.productBenefitId }" />' value='no' 
      			<c:if test="${productBenefit.benefitShowedOnEdc eq 0 or productBenefit.benefitShowedOnEdc eq null}">checked</c:if>
      			/>No&nbsp;
      			
		</td>  	 
    </tr>
   
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	</c:forEach>
	 <tr height="20">
      <td  class="listViewThS1" align="center" bgcolor="#4f85c6" nowrap="nowrap" valign="top"></td>

		<td class="listViewThS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			 
		</td>
      	<td class="listViewThS1" bgcolor="#4f85c6" nowrap="nowrap" valign="top" colspan=2>
      		
		</td>
		
		<td class="listViewThS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="middle">
		</td>  		   	

		<td class="listViewThS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="middle">
		</td>  		   		   		
		<td align="center" class="listViewThS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="center">
			 
    </tr>

	</tbody>
	</table>
	
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
	
		document.form1.navigation.value = "configedc-save";
		document.form1.action = "productbenefit";
		document.form1.submit();
	}
		
	function asCharge (idx){
		//window.alert("clicked : " + idx);
		
	}
	function cancel(){
		document.form1.navigation.value = "list";
		document.form1.action = "productbenefit";
		document.form1.submit();
	}

</script>
