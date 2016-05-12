<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<ul id="maintab" class="shadetabs">

	<li >
		<a href="product?navigation=detail&productId=<c:out value="${productId}" />" rel="tcontent1">Detail Product</a>
	</li>
	<li class="selected">
		<a href="productbenefit?navigation=list&productId=<c:out value="${productId}" />" rel="tcontent2">Product Benefit</a>
	</li>
	<li>
		<a href="productclausul?navigation=list&productId=<c:out value="${productId}" />" rel="tcontent3">Product Clausul</a>
	</li>	
	<li>
		<a href="productclausul?navigation=listexdiagnosis&productId=<c:out value="${productId}" />" rel="tcontent4">Diagnosis Exclusion</a>
	</li>
	
</ul>
<br />

<%
String navigation = WebUtil.getAttributeString(request,"navigation","");
%>

 <!-- Page Title Start // Should be put on <Title> tag-->

<!-- Page Title Stop-->
<%
String alert = (String) request.getAttribute("alert");
int index = 0;
int totalIndex = 0;
int count = 0;
int countSet = 0;

try {
	index = ((Integer) request.getAttribute ("index")).intValue();
	count = ((Integer) request.getAttribute ("count")).intValue();
	countSet = ((Integer) request.getAttribute ("countSet")).intValue();
	totalIndex = ((Integer) request.getAttribute ("halAkhir")).intValue();
	
	
	
}
catch (Exception e){
}

if (alert != null && !alert.trim().equals("")) {%>
	<div id="warning" align="center">
		<c:out value="${alert}"></c:out>
	</div>
	<%}%>

<%
String rowclass = "";
int i=0;
int indexint = Integer.parseInt(request.getAttribute("index").toString());
WebUtil.getAttributeInteger(request,"index",0).intValue();
%>
<!-- Search Container Start -->

<form name="form1" action="productbenefit" method="POST">
<input type="hidden" name="navigation" value="gosearch">
<input type="hidden" name="arah" value="">
<input type="hidden" name="index" value="<c:out value="${index}" />">
<input type="hidden" name="benefitId" value="<c:out value="${benefit.benefitId}" />">
<input type="hidden" name="productId" value="<c:out value="${productId}" />" >
<input type="hidden" name="productBenefitId" value="" >
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Search Benefit</h3></td>
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

 		   			<option value="benefitName" <c:if test="${searchby eq \"benefitName\"}">selected="true"</c:if> >Benefit Name</option>
			   			<option value="benefitDescription" <c:if test="${searchby eq \"benefitDescription\"}">selected="true"</c:if> >Benefit Description</option>
			   		   		   		   			<option value="benefitMeasurementUnit" <c:if test="${searchby eq \"benefitMeasurementUnit\"}">selected="true"</c:if> >Benefit Measurement Unit</option>
			   		   			<option value="benefitOccuranceUnit" <c:if test="${searchby eq \"benefitOccuranceUnit\"}">selected="true"</c:if> >Benefit Occurance Unit</option>
			   		   			
			   		   </select>
				
              </td>
            <td class="dataLabel">
              <input title="Search [Alt+Shift+Q]" accesskey="Q" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" name="button" value="Search" type="submit">
			</td>
            </tr>
        </tbody>
      </table></td>
    </tr>
  </tbody>
</table>


<!-- Search Container Stop -->


<!-- Table Container Start -->



	
<c:if test="${product.productStatus.statusId ne 1 and theUser.roleId.roleId eq 0}">
<br />
<input type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:tambahi()" value=" Add Benefit ">

<input type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:configEDC()" value=" Configure EDC ">

</c:if>
<br />

 	
<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	<tr>
      <td colspan="20" align="right"></td>
    </tr>
	<tr height="20">
		<td width="2%" nowrap="nowrap" class="listViewThS1" scope="col">No.</td>		   			
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">Benefit Name &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Benefit Code &nbsp;</td>	   		   		   
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Cashless Limit &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Reimburse Limit &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Benefit Method &nbsp;</td>
		<td scope="col" class="listViewThS1" width="5%">Max Occr&nbsp;</td>
		<td scope="col" class="listViewThS1" width="5%">Max Occr / Case&nbsp;</td>				
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Provider&nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Sharing&nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%">EDC &nbsp;</td>
   	   	<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">&nbsp;</td>
	</tr>


	<c:forEach items="${ProductBenefits}" var="benefit" varStatus="status" >
		<c:if test="${benefit.productLayerId eq null}">
		
		
			<%	if (i % 2 == 0) {
					rowclass = "col1";
				} else if (i % 2 != 0) {
					rowclass= "col2";
				}
				i++;
			%>
		
		 <tr height="20">
	      <td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><%=i%>.</td>
	
	      		   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
					<c:out value="${benefit.caseCategoryId.caseCategoryCode}" /> -
					<c:out value="${benefit.itemCategoryId.itemCategoryName}" /> 
					<c:if test="${benefit.treatmentLevel eq 0}"> - KOMPLEKS</c:if>
					<c:if test="${benefit.treatmentLevel eq 1}"> - BESAR</c:if>
					<c:if test="${benefit.treatmentLevel eq 2}"> - SEDANG</c:if>
					<c:if test="${benefit.treatmentLevel eq 3}"> - KECIL</c:if>
				
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;">
				<c:out value="${benefit.itemCategoryId.itemCategoryCode}"/> | <c:out value="${benefit.itemCategoryId.itemCategoryAlternateCode}" />	
				
			</td>		   				
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;">
				
					<c:choose>
						<c:when test="${benefit.isAsCharge}">
						AS CHARGE
						</c:when>
						<c:otherwise >
							<fmt:formatNumber><c:out value="${benefit.benefitLimit}" /></fmt:formatNumber>
						</c:otherwise>
					</c:choose>
				
			</td>
	                			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;">
				
					<c:choose>
						<c:when test="${benefit.reimburseAsCharge}">
						AS CHARGE
						</c:when>
						<c:otherwise >
							<fmt:formatNumber><c:out value="${benefit.reimbursementBenefitLimit}" /></fmt:formatNumber>
						</c:otherwise>
					</c:choose>
				
			</td>		   		   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				
					<c:out value="${benefit.benefitUsageType.benefitUsage}" />
				
			</td>
						   		   		
								   		   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				
					<c:out value="${benefit.maxOccurance}" />
				
			</td>
					   		   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				
					<c:out value="${benefit.maxOccurancePerCase}" />
				
			</td>
					   		   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				
					<c:if test="${benefit.providerContract eq 1}">YES</c:if>
					<c:if test="${(benefit.providerContract eq 0) or (benefit.providerContract eq null)}">YES</c:if>
				
			</td>   		   		
					   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				
					<c:if test="${(benefit.costSharingPercentage eq null) or (benefit.costSharingPercentage eq 100)}">100%</c:if>
					<c:if test="${(benefit.costSharingPercentage lt 100) and (benefit.costSharingPercentage gt 0)}"><c:out value="${benefit.costSharingPercentage}" /> %</c:if>
				
			</td>  	   		   		
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
					<c:if test="${benefit.isEdcEnabled eq 1}">EDC
						<c:if test="${benefit.benefitShowedOnEdc eq 1}">(Y)</c:if><c:if test="${benefit.benefitShowedOnEdc eq 0}">(N)</c:if>
						|<c:out value="${benefit.itemCategoryId.itemCategoryEDCCode}" />
					</c:if>
					<c:if test="${benefit.isEdcEnabled eq 0}">SHOW</c:if>							
				</td>
						   		         <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top">
			<a href="javascript:detil('<c:out value="${benefit.productBenefitId}" />')">
					<img src="images/view.gif" class="action_icon" alt="View" title="View"></a>
	
				<c:if test="${theUser.roleId.roleId eq 0}">
				<c:if test="${benefit.productId.productStatus.statusId ne 1}">
				<!-- ini default edit table for each data -->
				<a href="javascript:ubah('<c:out value="${benefit.productBenefitId}" />')">
					<img src="images/edit.gif" class="action_icon" alt="Edit" title="Edit"></a>
	
				<!-- ini default delete table for each data -->
				<a href="javascript:hapus('<c:out value="${benefit.productBenefitId}" />')">
					<img src="images/delete.gif" class="action_icon" alt="Delete" title="Delete"></a>
			
				
				</c:if>
				</c:if>
		  </td>
	    </tr>
	   
		<tr>
	      <td colspan="20" class="listViewHRS1"></td>
	    </tr>
		</c:if>
	</c:forEach>
	</tbody>
	</table>
</form>
	
<!-- Table Content Stop -->
	<!-- Table Toolbar Start -->
	
		<!-- Table Toolbar Stop -->



<!-- Table Container Stop -->
<script language="Javascript">
<%
String nav="";
if(request.getAttribute("navigation").equals("gosearch")||request.getAttribute("navigation").equals("golookup")){
	nav = (String)request.getAttribute("navigation");
}
%>
<c:if test="${product.productStatus.statusId ne 2 and theUser.roleId.roleId eq 0 }" >
function tambahi (){
	document.form1.navigation.value = "tambah";
	document.form1.action = "productbenefit-form";
	document.form1.method = "GET";
	document.form1.submit();
}
function hapus (idx){
	var delConfirm = window.confirm ("Are You Sure Want To Delete This Entry ?");

	if (delConfirm){
		document.form1.method = "POST";
		document.form1.productBenefitId.value = idx;
		document.form1.action = "productbenefit";
		document.form1.navigation.value = "delete";
		document.form1.submit();
	}
}
function ubah (idx){
	document.form1.method = "GET";
	document.form1.productBenefitId.value = idx;
	document.form1.action = "productbenefit-form";
	document.form1.navigation.value = "edit";
	document.form1.submit();
}
function configEDC (){
	document.form1.method = "GET";
	document.form1.action = "productbenefit";
	document.form1.navigation.value = "configedc";
	document.form1.submit();
}
</c:if>
function goleft(){
	document.form1.navigation.value = "list";
	document.form1.arah.value="kiri";
	document.form1.method = "POST";
	document.form1.submit();
}
function goleftbgt(){
	document.form1.navigation.value = "list";
	document.form1.arah.value="kiribgt";
	document.form1.method = "POST";
	document.form1.submit();
}
function goright(){
	document.form1.navigation.value = "list";
	document.form1.arah.value="kanan";
	document.form1.method = "POST";
	document.form1.submit();
}
function gorightbgt(){
	document.form1.navigation.value = "list";
	document.form1.arah.value="kananbgt";
	document.form1.method = "POST";
	document.form1.submit();
}
function go(){
	document.form1.navigation.value = "list";
	document.form1.submit();
}
function cari (){
	document.form1.navigation.value = "gosearch";
	document.form1.action = "productbenefit";
	document.form1.method = "POST";
	document.form1.submit();
}
function detil (idx){
	document.form1.method = "POST";
	document.form1.productBenefitId.value = idx;
	document.form1.action = "productbenefit";
	document.form1.navigation.value = "detail";
	document.form1.submit();

}
</script>
