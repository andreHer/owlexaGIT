<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;List Product Benefit</h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>

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
<%@ include file="../mainContentProduct.jsp" %>

<form name="form1" action="productbenefit" method="POST">
<input type="hidden" name="navigation" value="list">
<input type="hidden" name="arah" value="">
<input type="hidden" name="index" value="<c:out value="${index}" />">
<input type="hidden" name="benefitId" value="<c:out value="${benefit.benefitId}" />">
<input type="hidden" name="productId" value="<c:out value="${productId}" />" >
<input type="hidden" name="productBenefitId" value="" >
</br>
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Search Product Benefit</h3></td>
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
 		   			<option value="benefitCode" <c:if test="${searchby eq \"benefitCode\"}">selected="true"</c:if> >Benefit Code</option>
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
	
<c:if test="${product.productStatus.statusId ne 1 and (theUser.roleId.roleId eq 0 or theUser.roleId.roleId eq 22)}">
<br />
<input type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:tambahi()" value=" Add Benefit ">
<c:if test="${theUser.roleId.roleId eq 0 }">
<input type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:configEDC()" value=" Configure EDC ">
</c:if>
</c:if>
<br />

 	
<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	<tr>
      <td colspan="20" align="right"></td>
    </tr>
	<tr height="20">
		<td width="2%" nowrap="nowrap" class="listViewThS1" scope="col">No.</td>		   			
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Benefit Name &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="3%">Benefit Code &nbsp;</td>	   		   		   
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Cashless Limit &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Reimburse Limit &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Benefit Method &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Shared Benefit&nbsp;</td>
		
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="3%">Deductible &nbsp;</td>
		<td scope="col" class="listViewThS1" width="3%">Max Occr&nbsp;</td>
		<td scope="col" class="listViewThS1" width="3%">Max Occr / Case&nbsp;</td>				
		<td scope="col" class="listViewThS1" width="5%">Benefit Limit / Case&nbsp;</td>
		<td scope="col" class="listViewThS1" width="3%">Principal Co Share (%)&nbsp;</td>
		<td scope="col" class="listViewThS1" width="5%">Principal Sharing (Amt)&nbsp;</td>
		<td scope="col" class="listViewThS1" width="3%">Dep Co Share (%)&nbsp;</td>
		<td scope="col" class="listViewThS1" width="5%">Dep Sharing (Amt)&nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="3%">EDC &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="3%">SMM &nbsp;</td>
   	   	<td scope="col" class="listViewThS1" nowrap="nowrap" width="3%">&nbsp;</td>
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

	    <td class="oddListRowS1" bgcolor="#e7f0fe" valign="top">
				<c:out value="${benefit.caseCategoryId.caseCategoryCode}" /> -
				<c:if test="${benefit.itemCategoryId ne null}">
					<c:out value="${benefit.itemCategoryId.itemCategoryName}" />
				</c:if>
				<c:if test="${benefit.diagnosisId ne null}">
					<c:out value="${benefit.diagnosisId.description}" />
				</c:if> 
				<!-- 
				<c:if test="${benefit.treatmentLevel eq 0}"> - KOMPLEKS</c:if>
				<c:if test="${benefit.treatmentLevel eq 1}"> - BESAR</c:if>
				<c:if test="${benefit.treatmentLevel eq 2}"> - SEDANG</c:if>
				<c:if test="${benefit.treatmentLevel eq 3}"> - KECIL</c:if>
			 -->
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;">
			<c:out value="${benefit.itemCategoryId.itemCategoryCode}"/> | <c:out value="${benefit.itemCategoryId.itemCategoryAlternateCode}" />	
			
		</td>		   				
			<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top" style="text-align: right;">
			
				<c:choose>
					<c:when test="${benefit.benefitLimit eq -1.0}">
					AS CHARGE
					</c:when>
					<c:otherwise >
						<fmt:formatNumber><c:out value="${benefit.benefitLimit}" /></fmt:formatNumber>
					</c:otherwise>
				</c:choose>
				- [<b><c:out value="${benefit.cashlessPercentage}" /> %</b>]
		</td>	
       <td class="oddListRowS1" bgcolor="#e7f0fe" valign="top" style="text-align: right;">
			
				<c:choose>
					<c:when test="${benefit.reimbursementBenefitLimit eq -1.0}">
					AS CHARGE
					</c:when>
					<c:otherwise >
						<fmt:formatNumber><c:out value="${benefit.reimbursementBenefitLimit}" /></fmt:formatNumber>
					</c:otherwise>
				</c:choose>
				- [<b><c:out value="${benefit.reimbursementPercentage}" /> %</b>]
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${benefit.benefitUsageType.benefitUsage}" />
			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${benefit.sharedBenefitId.itemCategoryId.itemCategoryName}" />
			
		</td>
							   		   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${benefit.deductibleLimit}" />
			
		</td>		   		   		
	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			

				
				<c:choose>
					<c:when test="${benefit.maxOccurance eq -1.0}">
					UNLIMITED
					</c:when>
					<c:otherwise >
						<fmt:formatNumber><c:out value="${benefit.maxOccurance}" /></fmt:formatNumber>
					</c:otherwise>
				</c:choose>
				
			
		</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<c:choose>
					<c:when test="${benefit.maxOccurancePerCase eq -1.0}">
					UNLIMITED
					</c:when>
					<c:otherwise >
						<fmt:formatNumber><c:out value="${benefit.maxOccurancePerCase}" /></fmt:formatNumber>
					</c:otherwise>
				</c:choose>
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<c:choose>
				<c:when test="${benefit.maxBenefitPerCase eq -1.0}">
				UNLIMITED
				</c:when>
				<c:otherwise >
					<fmt:formatNumber><c:out value="${benefit.maxBenefitPerCase}" /></fmt:formatNumber>
				</c:otherwise>
			</c:choose>
		</td>		   		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
				<c:if test="${(benefit.costSharingPercentage eq null) or (benefit.costSharingPercentage eq 100)}">100%</c:if>
				<c:if test="${(benefit.costSharingPercentage lt 100) and (benefit.costSharingPercentage gt 0)}"><c:out value="${benefit.costSharingPercentage}" /> %</c:if>
			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
				<c:out value="${benefit.costSharingAmount}" />
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
				<c:out value="${benefit.dependentCoSharePercentage}" /> %
		</td>  	   		   		  	   		 
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
				<c:out value="${benefit.dependentCoShareAmount}" />
		</td>  	   		   		  	   		   	   		   		  	   		   		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
				<c:if test="${benefit.isEdcEnabled eq 1}">EDC
					<c:if test="${benefit.benefitShowedOnEdc eq 1}">(Y)</c:if><c:if test="${benefit.benefitShowedOnEdc eq 0}">(N)</c:if>
					|<c:out value="${benefit.itemCategoryId.itemCategoryEDCCode}" />
				</c:if>
				<c:if test="${benefit.isEdcEnabled eq 0}">SHOW</c:if>							
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
				<c:if test="${benefit.isSMMBenefit eq 1}">YES
				</c:if>
				<c:if test="${benefit.isSMMBenefit eq 0 or benefit.isSMMBenefit eq null}">NO</c:if>							
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
	document.form1.navigation.value = "list";
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
