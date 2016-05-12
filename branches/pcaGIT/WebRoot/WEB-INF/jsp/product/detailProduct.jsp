<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<form action="product" method="GET" name="form1" id="form_layout">
<input type="hidden" name="navigation" value="">
	<input type="hidden" name="productId" value="<c:out value="${product.productId}" />">
	<input type="hidden" name="productReferenceId" value="<c:out value="${product.productId}" />" >
	<input type="hidden" name="rowset" value="<c:out value="${rowset}" />" >
	<input type="hidden" name="userType" value="<c:out value="${userType}" />">
	<input type="hidden" name="index" value="<c:out value="${index }" />" />
	<input type="hidden" name="productId" value="<c:out value="${productId}" />">
	<input type="hidden" name="productLayerLimitId" value="" />

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Detail Product</h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr> 
      <td style="padding-bottom: 2px;">        
      	<c:if test="${product.productStatus.statusId eq -1}">
	      	<c:if test="${(theUser.roleId.roleId eq 0 or theUser.roleId.roleId eq 22)}">
	      	
	        <input title="Edit [Alt+Shift+E]" accesskey="E" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:ubah()" name="Edit" value=" Update " type="button">
	        <input title="Hapus [Alt+Shift+H]" accesskey="H" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:hapus()" name="Delete" value=" Delete " type="button">
	        <input title="Activate [Alt+Shift+A]" accesskey="A" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:aktifasi()" name="act" value=" Activate " type="button">
	        
	        	<c:if test="${product.doSynchronize eq null or product.doSynchronize eq 0}">
	        		<input title="Synchronize [Alt+Shift+S]" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:synchronize()" name="synch" value=" Synchronize " type="button">
	        	</c:if>
	        </c:if>
	        
	        <c:if test="${product.isTemplate ne 1 and (theUser.roleId.roleId eq 0 or theUser.roleId.roleId eq 22)}">
	        	<input title="Set As Product Template [Alt+Shift+A]" accesskey="A" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:isTemplate()" name="act" value=" Set Template " type="button">
	        </c:if>
	        <c:if test="${product.productReference ne null and (theUser.roleId.roleId eq 0 or theUser.roleId.roleId eq 22)}">
	      	  	<input title="Add Benefit Template [Alt+Shift+B]" accesskey="B" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:addBenefitTemplate()" name="benefitTemp" value=" Add Benefit By Template " type="button">
	        </c:if>
        </c:if>        		
        <c:if test="${product.isTemplate eq 1 and (theUser.roleId.roleId eq 0 or theUser.roleId.roleId eq 22)}">
        	<input title="Add Derivative Product [Alt+Shift+A]" accesskey="A" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:addDerivative()" name="derivative" value=" Add Derivative Product " type="button">
        </c:if>
		</td>
      <td align="right"></td>
    </tr>
  </tbody>
</table>
<%@ include file="../mainContentProduct.jsp" %>
<br />

<c:if test="${theUser.userType eq 2 and theUser.roleId.roleId eq 0 || theUser.userType eq 2 and theUser.roleId.roleId eq 22 }" >
<input type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:tambahLayer()" value=" Add Limit Layer ">
</c:if>
<br />

 	
<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	<tr>
      <td colspan="20" align="right"><table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tbody>
          <tr>
            <td class="listViewPaginationTdS1" align="left">&nbsp;</td>
            <td class="listViewPaginationTdS1" align="right" nowrap="nowrap">&nbsp;</td>
          </tr>
        </tbody>
      </table></td>
    </tr>
	<tr height="20">
			<td width="2%" nowrap="nowrap" class="listViewThS1" scope="col">No.</td>		
   		   	<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Product Name</td>
   		   	<td scope="col" class="listViewThS1" nowrap="nowrap" width="20%">Diagnosis</td>
   		   	<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Layer Level</td>
   		   	<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Type</td>
   		   	<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Annual Limit</td>
   		   	<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Deductible</td>	   		   		   		   			
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Co Share Percentage</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Description</td>
			
	   	   	<td scope="col" class="listViewThS1" nowrap="nowrap" width="2%"><slot>&nbsp;</slot></td>
	</tr>
	<c:forEach items="${ProductLayerLimits}" var="productLayerLimit" varStatus="status" >
	
	 <tr height="20">
      	<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${status.index+1}" /></td>
      	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<slot>
				<c:out value="${productLayerLimit.productId.productName}" />
			</slot>
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe"  valign="top">
			<slot>
				<c:if test="${productLayerLimit.diagnosisSetId ne null}">
				<b>
					<a href="javascript:popupDiagnosisList('<c:out value="${productLayerLimit.diagnosisSetId.diagnosisSetId}" />')" class="linkDetail">
				[SMM] <c:out value="${productLayerLimit.diagnosisSetId.diagnosisSetName}" /></a>
				</b>
				</c:if>
				
			</slot>
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<slot>
				<c:out value="${productLayerLimit.layerLevel}" />
			</slot>
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<slot>
				<c:out value="${productLayerLimit.productTypeId.productTypeName}" />
			</slot>
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<slot>
				<fmt:formatNumber><c:out value="${productLayerLimit.annualLimit}" /></fmt:formatNumber>
			</slot>
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<slot>
				<fmt:formatNumber><c:out value="${productLayerLimit.deductible}" /></fmt:formatNumber> 
			</slot>
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<slot>
				<c:out value="${productLayerLimit.coSharePercentage}" /> %
			</slot>
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<slot>
				<c:out value="${productLayerLimit.description}" />
			</slot>
		</td>		   		   		
					   		   		
					   		         <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top">
		

				
				<input title="Edit [Alt+Shift+E]" accesskey="E" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:ubahLayer('<c:out value="${productLayerLimit.productLayerLimitId}" />')" name="Edit" value=" Update " type="button">
		        <input title="Hapus [Alt+Shift+H]" accesskey="H" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:hapusLayer('<c:out value="${productLayerLimit.productLayerLimitId}" />')" name="Delete" value=" Delete " type="button">
		        <input title="Activate [Alt+Shift+A]" accesskey="A" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:addLayerBenefit('<c:out value="${productLayerLimit.productLayerLimitId}" />')" name="act" value=" Add Benefit " type="button">
		        <input title="Activate [Alt+Shift+A]" accesskey="A" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:showLayerBenefit('<c:out value="${productLayerLimit.productLayerLimitId}" />')" name="act" value=" Show Benefit " type="button">
		
	  </td>
    </tr>
   
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	
	</c:forEach>
	
<tr>
            <td class="listViewPaginationTdS1" align="left">&nbsp;</td>
            <td class="listViewPaginationTdS1" align="right" nowrap="nowrap" colspan="20">
				&nbsp;
					</td>
          </tr>
	</tbody>
	</table>

</form>
<script language="javascript" >

	<c:if test="${product.productStatus.statusId eq -1}">
	function hapus (){
		var delConfirm = window.confirm ("Are You Sure Want To Delete This Entry ?");
		if (delConfirm){
			document.form1.navigation.value = "delete";
			document.form1.submit();
		}
	}
	function hapusLayer (indx){
		var delConfirm = window.confirm ("Are You Sure Want To Delete This Entry ?");
		if (delConfirm){
			document.form1.productLayerLimitId.value = indx;
			document.form1.action = "productlayerlimit";
			document.form1.navigation.value = "delete";
			document.form1.submit();
		}
	}
	function aktifasi(){
		var delConfirm = window.confirm ("Are You Sure Want To Activate This Product ?");
		if (delConfirm){
			document.form1.navigation.value = "activate";
			document.form1.submit();
		}
	}
	function synchronize(){
		var delConfirm = window.confirm ("Are You Sure Want To Synchronize This Product ?");
		if (delConfirm){
			document.form1.navigation.value = "synchronize";
			document.form1.submit();
		}
	}
	function addBenefit(){
		
		document.form1.action = "productbenefit-form";
		document.form1.method = "GET";
		document.form1.submit();
	}
	function addBenefitTemplate(){
		
		document.form1.action = "productbenefit";
		document.form1.method = "GET";
		document.form1.navigation.value = "addtemplate-form";
		document.form1.submit();
	}
	function addClausul(){
		
		document.form1.action = "productclausul-form";
		document.form1.method = "GET";
		document.form1.submit();
	}
	
	function ubah (){
		document.form1.navigation.value = "update";
		document.form1.action = "product-form";
		document.form1.method = "GET";
		document.form1.submit();
	}
	function ubahLayer (idx){
		document.form1.navigation.value = "update";
		document.form1.productLayerLimitId.value = idx;
		document.form1.action = "productlayerlimit-form";
		document.form1.method = "GET";
		document.form1.submit();
	}
	function addLayerBenefit (idx){		
		document.form1.productLayerLimitId.value = idx;
		document.form1.action = "productbenefit-form";
		document.form1.method = "GET";
		document.form1.submit();
	}
	function showLayerBenefit (idx){		
		window.open ("productbenefit?navigation=lookup&productLayerId="+idx+"&url=member-form","Search","width=1200, height=768, menubar=no, status=no, toolbar=no, scrollbars=yes, resizable=yes,screenX=200,screenY=100");
	}
	</c:if>
	<c:if test="${product.isTemplate ne 1}">

	function isTemplate(){
		document.form1.navigation.value = "settemplate";
		document.form1.action = "product";
		document.form1.method = "GET";
		document.form1.submit();
	}
	</c:if>
	function kembali (){
		document.form1.navigation.value = "back";
		document.form1.submit();
	}
	function tambahLayer() {
		document.form1.navigation.value = "tambah";
		
		document.form1.action = "productlayerlimit-form";
		document.form1.method = "GET";
		document.form1.submit();
	}
	function popupDiagnosisList(diagSetId){
		window.open ("diagnosissetdetail?navigation=lookup&diagnosisSetId="+diagSetId+"&url=member-form","Search","width=700, height=550, menubar=no, status=no, toolbar=no, scrollbars=yes, resizable=yes,screenX=200,screenY=100");
	}
	function addDerivative() {
		document.form1.navigation.value = "derivative";
		
		document.form1.action = "product-form";
		document.form1.method = "GET";
		document.form1.submit();
	}
</script>
