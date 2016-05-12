<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ page import="java.util.Vector" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<script type='text/javascript' src='dwr/interface/AJAXClaimItemService.js'></script>
<script type='text/javascript' src='dwr/engine.js'></script>
<script type='text/javascript' src='dwr/util.js'></script>
<script type='text/javascript' src='scripts/jquery-1.3.2.min.js'></script>
<%
String navigation = WebUtil.getAttributeString(request,"navigation","");
%>

<%
String alert = (String) request.getAttribute("alert");
int index = 0;
int totalIndex = 0;

int totalItem = (Integer) request.getAttribute("totalItem") == null ? 0 : ((Integer) request.getAttribute("totalItem")).intValue();

Vector claimItemVector  = (Vector)request.getAttribute ("claimItemVector") == null ? new Vector() : (Vector)request.getAttribute ("claimItemVector");
Vector claimAmountVector = (Vector)request.getAttribute ("claimAmountVector") == null ? new Vector() : (Vector)request.getAttribute ("claimAmountVector");

Vector claimItemNameVector  = (Vector)request.getAttribute ("claimItemNameVector") == null ? new Vector() : (Vector)request.getAttribute ("claimItemNameVector");
Vector claimItemRuleVector  = (Vector)request.getAttribute ("claimItemRuleVector") == null ? new Vector() : (Vector)request.getAttribute ("claimItemRuleVector");

Vector claimItemCodeVector  = (Vector)request.getAttribute ("claimItemCodeVector") == null ? new Vector() : (Vector)request.getAttribute ("claimItemCodeVector");
Vector claimItemIdVector = (Vector) request.getAttribute("claimItemIdVector") == null ? new Vector() : (Vector)request.getAttribute ("claimItemIdVector");
Vector claimValueVector = (Vector)request.getAttribute ("claimValueVector") == null ? new Vector() : (Vector)request.getAttribute ("claimValueVector");
Vector prePaidExcessVector = (Vector)request.getAttribute ("prePaidExcessVector") == null ? new Vector() : (Vector)request.getAttribute ("prePaidExcessVector");
Vector paidToProviderVector = (Vector)request.getAttribute ("paidToProviderVector") == null ? new Vector() : (Vector)request.getAttribute ("paidToProviderVector");
Vector faultExcessProviderVector = (Vector)request.getAttribute ("faultExcessProviderVector") == null ? new Vector() : (Vector)request.getAttribute ("faultExcessProviderVector");
Vector claimDescVector = (Vector)request.getAttribute ("claimDescVector");


System.out.println ("CLAIM ITEM : " + claimItemVector.size() );
System.out.println ("CLAIM AMOUNT : " + claimAmountVector.size() );
System.out.println ("CLAIM VALUE : " + claimValueVector.size() );
System.out.println ("CLAIM DESC : " + claimDescVector.size() );
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

<form name="form1" action="claimitem" method="POST">
<input type="hidden" name="navigation" value="saveclaimitem">
<input type="hidden" name="arah" value="">
<input type="hidden" name="totalItem" value="<c:out value="${totalItem}" />" id="idTotalItem">
<input type="hidden" name="claimId" value="<c:out value="${claimId}" />">
<input type="hidden" name="index" value="<c:out value="${index}" />">
<input type="hidden" name="batchClaimId" value="<c:out value="${batchClaimId }" />">

<br />

 	
<table class="tabForm" cellspacing="0" cellpadding="0">
	<thead>
		<tr height="20">
			<td width="2%" nowrap="nowrap" class="listViewThS1" scope="col">No.</td>		
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Item Code&nbsp;</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Item Name&nbsp;</td>	
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">Benefit Rule&nbsp;</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="2%">No. of Services &nbsp;</td>			
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%">Item Amount &nbsp;</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%">PrePaid Excess  &nbsp;</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%">Paid To Provider  &nbsp;</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%">Fault Excess Provider  &nbsp;</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">Description &nbsp;</td>
		</tr>
	</thead>
	<tbody id="claimItemTableId">
	
	<%	
		for ( i = 1 ; i <= totalItem; i++){
	%>
		<input type="hidden" id="claimItem<%=i%>Id" name="claimItemId" value="<%=(String)claimItemIdVector.get(i-1)%>">
		<input type="hidden" id="item<%=i%>Id" name="itemId" value="<%=(Integer)claimItemVector.get(i-1)%>">
	 <tr height="20">
      	<td class="oddListRowS1" align="center" bgcolor="#4f85c6" nowrap="nowrap" valign="top"><%=i%></td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<input class="input2" size="10" type="text" id="item<%=i%>Code" name="itemCode" value="<%=(String)claimItemCodeVector.get(i-1)%>"> 
		</td>
      	<td class="oddListRowS1" bgcolor="#4f85c6" nowrap="nowrap" valign="top">      		
      		<input type="text" class="input2" size="20" id="item<%=i%>Name" name="itemName" value="<%=(String)claimItemNameVector.get(i-1) %>" readonly="readonly" >
		</td>
		<td class="oddListRowS1" bgcolor="#4f85c6" nowrap="nowrap" valign="top">
      		<%=(String)claimItemRuleVector.get(i-1) %>      		
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<input class="input2" size="5" type="text"  style="text-align: right;" name="itemAmount" value="<%=(String)claimAmountVector.get(i-1)%>">
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<input class="input2" type="text" onmousedown="javascript:calculateValue()" onkeydown="javascript:calculateValue()" id="idItemValue<%=i%>" style="text-align: right;" name="itemValue" value="<%=(String)claimValueVector.get(i-1)%>">
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<input class="input2" size="15" type="text"  style="text-align: right;" onmousedown="javascript:calculateValue()" name="prePaidExcess" value="<%=(String)prePaidExcessVector.get(i-1)%>">
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<input class="input2" size="15" type="text"  style="text-align: right;" name="paidToProvider" value="<%=(String)paidToProviderVector.get(i-1)%>">
		</td>  	
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<input class="input2" size="15" type="text"  style="text-align: right;" name="faultExcessProvider" value="<%=(String)faultExcessProviderVector.get(i-1)%>">
		</td>  		   	  		   	
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<TEXTAREA class="textarea2" cols="30" rows="1" name="description"><%=(String)claimDescVector.get(i-1)%></TEXTAREA>
		</td>  		   		   		
  	 
    </tr>
   
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	<%
	}
	%>
	
	</tbody>
	<tfoot>
	
	 <tr height="20">
      <td  class="listViewThS1" align="center" bgcolor="#4f85c6" nowrap="nowrap" valign="top"></td>

		<td class="listViewThS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			 
		</td>
      	<td class="listViewThS1" bgcolor="#4f85c6" nowrap="nowrap" valign="top" colspan=2>
      		
      		
		</td>
		<td class="listViewThS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			 
		</td>
		
		<td class="listViewThS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="middle">
			<input class="input2" type="text" onclick="javascript:calculateValue()" style="text-align: right;" name="totalClaimItem" id="totalClaimItem" value="" readonly="readonly">
		</td>  		   	

		<td class="listViewThS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="middle" >
			<input class="input2" type="text" size="15" style="text-align: right;" name="pembayaranDimuka" id="pembayaranDimuka" value="<fmt:formatNumber pattern="#.###"><c:out value="${pembayaranDimuka}" /></fmt:formatNumber>" >			 
		</td>
		<td class="listViewThS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			 
		</td>  		   		   		
			 
    </tr>
	
	</tfoot>
	</table>
	
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td style="padding-top: 2px;">
	    <input type="hidden" name="notyet" value="">
	    <c:if test="${batchClaimId ne null}">
	    <input title="Save & Return To Batch [Alt+Shift+B]" accesskey="B" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="this.disabled='disabled';javascript:saveReturnBatch()" name="returnBatch" value=" Save & Return To Batch " type="button"> <% //add this.disable kegunaan untuk mendisable button setelah di klik by adq %>
	    </c:if>
        <input title="Save [Alt+Shift+S]" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="this.disabled='disabled';javascript:simpan()" name="Save" value=" Save " type="button"> <% //add this.disable kegunaan untuk mendisable button setelah di klik by adq %>
        <input title="Cancel [Alt+Shift+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:cancel()" name="Cancel" value=" Cancel " type="button">
	  </td>
      <td align="right"></td>
    </tr>
  </tbody>
</table>

<br />
<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>


	<tr height="20">
		<td width="2%" nowrap="nowrap" class="listViewThS1" scope="col">
			<img src="images/blank.gif" alt="asd" height="1" width="1">No.</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%" style="text-align: center;">Code &nbsp;</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%" style="text-align: center;">Client Code &nbsp;</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%" style="text-align: center;">Item Category &nbsp;</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%" style="text-align: center;">Method &nbsp;</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%" style="text-align: center;">Shared Benefit &nbsp;</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%" style="text-align: center;">Cashless &nbsp;</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%" style="text-align: center;">Reimburse &nbsp;</td>
			<c:if test="${theUser.userType eq 2}">
					<td scope="col" class="listViewThS1" width="5%" style="text-align: center;">
				Max Occur. &nbsp;</td>
		
					<td scope="col" class="listViewThS1" width="3%" style="text-align: center;">
				Max Occur. per Case&nbsp;</td>
			   			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%" style="text-align: center;">
				Usage &nbsp;</td>
				<td scope="col" class="listViewThS1" width="3%" style="text-align: center;">
				Deductible &nbsp;</td>
					<td scope="col" class="listViewThS1" width="3%" style="text-align: center;">
				Share (%) &nbsp;</td>
				<td scope="col" class="listViewThS1" width="5%" style="text-align: center;">
				Share (Amt) &nbsp;</td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%" style="text-align: center;">
				EDC &nbsp;</td>
				
			</c:if>		
	</tr>
	<c:forEach items="${memberBenefitList}" var="benefit" varStatus="status" >
	
	 <tr height="20">
      <td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${status.index + 1}" />.</td>

      		   		   		   		   		   		   		
					   		   		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" >
			<c:out value="${benefit.itemCategoryId.itemCategoryCode}" /> <c:if test="${benefit.itemCategoryId.itemCategoryAlternateCode ne null}">/</c:if> <c:out value="${benefit.itemCategoryId.itemCategoryAlternateCode}" />
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" >
			<c:out value="${benefit.clientItemCode} " /> 
		</td>			   					   		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<a href="claimitem?memberId=<c:out value="${memberId}" />&navigation=usagetrack&memberBenefitId=<c:out value="${benefit.memberBenefitId}" />&itemCategoryId=<c:out value="${benefit.itemCategoryId.itemCategoryId}" />&prev=listmember" class="linkDetail"><c:out value="${benefit.itemCategoryId.itemCategoryName}" /></a>	<c:out value="(${benefit.effectiveDate} s/d ${benefit.expireDate})" />	
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" >
			<c:if test="${benefit.benefitCalculationMethod.benefitUsageTypeId eq 1}">
				OCCURANCE
			</c:if>
			<c:if test="${benefit.benefitCalculationMethod.benefitUsageTypeId eq 2}">
				PER DISABILITY
			</c:if>			
			<c:if test="${benefit.benefitCalculationMethod.benefitUsageTypeId eq 3}">
				ANNUAL LIMIT
			</c:if>
			<c:if test="${benefit.benefitCalculationMethod.benefitUsageTypeId eq 4}">
				MAX OCCUR PER CASE
			</c:if>
			<c:if test="${benefit.benefitCalculationMethod.benefitUsageTypeId eq 5}">
				MAX DAILY
			</c:if>
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" >
			<c:out value="${benefit.sharedBenefitId.itemCategoryId.itemCategoryName}" />
		
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top" style="text-align: right;">
				<c:choose>			
					<c:when test="${benefit.sharedBenefitId eq null}">
						<c:choose>
							<c:when test="${benefit.benefitLimit eq -1}">
							AS CHARGE - [<b><c:out value="${benefit.cashlessPercentage}" /> %</b>]
							</c:when>
							<c:otherwise >
								<fmt:formatNumber><c:out value="${benefit.benefitLimit}" /></fmt:formatNumber>
								- [<b><c:out value="${benefit.cashlessPercentage}" /> %</b>]
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						SHARED
					</c:otherwise>
				</c:choose>				
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top" style="text-align: right;">			
				<c:choose>			
					<c:when test="${benefit.sharedBenefitId eq null}">
						<c:choose>
							<c:when test="${benefit.reimbursementBenefitLimit eq -1}">
							AS CHARGE - [<b><c:out value="${benefit.reimbursementPercentage}" /> %</b>]
							</c:when>
							<c:otherwise >
								<fmt:formatNumber><c:out value="${benefit.reimbursementBenefitLimit}" /></fmt:formatNumber>
								- [<b><c:out value="${benefit.reimbursementPercentage}" /> %</b>]
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						SHARED
					</c:otherwise>
				</c:choose>
			
		</td>
			<c:if test="${theUser.userType eq 2}">
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
			<c:if test="${benefit.maxBenefitOccurance ne -1}">			
				<fmt:formatNumber><c:out value="${benefit.maxBenefitOccurance}" /></fmt:formatNumber>
			</c:if>
			<c:if test="${benefit.maxBenefitOccurance eq -1}">			
				UNLIMITED
			</c:if>			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
			<c:if test="${benefit.maxOccurancePerCase ne -1}">			
				<fmt:formatNumber><c:out value="${benefit.maxOccurancePerCase}" /></fmt:formatNumber>
			</c:if>			
			<c:if test="${benefit.maxOccurancePerCase eq -1}">
				UNLIMITED
			</c:if>
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">			
				<fmt:formatNumber><c:out value="${benefit.benefitUsage}" /> </fmt:formatNumber> - [<b><fmt:formatNumber><c:out value="${benefit.benefitOccuranceUsage}" /></fmt:formatNumber></b> ]			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">			
				<fmt:formatNumber><c:out value="${benefit.deductibleLimit}" /></fmt:formatNumber>			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;">			
				<c:choose>
					<c:when test="${benefit.costSharingPercentage < 100}">
						<fmt:formatNumber><c:out value="${benefit.costSharingPercentage}" /></fmt:formatNumber> %
					</c:when>
					<c:otherwise>
					<center>-</center>
					</c:otherwise>
					
				</c:choose>
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;">			
				
						<fmt:formatNumber><c:out value="${benefit.costSharingAmount}" /></fmt:formatNumber> 
					
			</td>	
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: right;">
				<c:if test="${benefit.isEDCEnabled}">EDC
					<c:if test="${benefit.benefitLimitPublishedOnEdc eq 1}">(Y)</c:if>
					<c:if test="${benefit.benefitLimitPublishedOnEdc eq 0}">(N)</c:if>
					|<c:if test="${benefit.itemCategoryId.itemCategoryEDCCode ne null}"><c:out value="${benefit.itemCategoryId.itemCategoryEDCCode}" /></c:if>
				</c:if>
				<c:if test="${not benefit.isEDCEnabled}">SHOW</c:if>							
			</td>
			
		
		</c:if>
    </tr>
   
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	
	</c:forEach>
	
	</tbody>
</table>
	
	<br />
<table class="tabForm" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	
	<tr height="20">
		<td width="4%" nowrap="nowrap" class="listViewThS1" scope="col">
			<img src="images/blank.gif" alt="asd" height="1" width="1">No.</td>		

<!-- ini default generated table from database -->
		   		   		   			
			
				  		
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">
				Clausul &nbsp;</td>
			
			
		
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Clausul Category&nbsp;</td>
			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="40%">
				Detail Info&nbsp;</td>
		
			   			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Age Limitation &nbsp;</td>
			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Coverage Status &nbsp;</td>
		
			   			
			
			   		   		   		   		   				   		   						   		   	  
	   
	</tr>

	
	<c:forEach items="${productClausulList}" var="clausul" varStatus="status">
			 <tr height="20">
      <td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${status.index + 1}" />.</td>

      		   		   		   		   		   		   		
					   		   		
					   		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<a href="" class="linkDetail">
			
		<c:out value="${clausul.clausulId.clausulName}" />
			</a>		
		</td>
		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" >
			
				<c:out value="${clausul.clausulId.clausulCategoryId.clausulCategoryName}" />
			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: left;">
				<c:if test="${clausul.diagnosisId ne null}">
					<c:out value="${clausul.diagnosisId.diagnosisCode}" />
				</c:if>
			
		</td>		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: left;">
				<c:if test="${clausul.parameterUmur ne -1}">
				<c:if test="${clausul.parameterUmur eq 2}">dibawah</c:if><c:if test="${clausul.parameterUmur eq 1}">diatas</c:if> &nbsp;<c:out value="${clausul.umur}" /> tahun
				</c:if>
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" style="text-align: left;">
				<c:if test="${clausul.decision ne -1}">
					<c:if test="${clausul.decision eq 1}">
					dicover
					</c:if>
					<c:if test="${clausul.decision eq 2}">
					tidak dicover
					</c:if>
					
				</c:if>
			
		</td>
	
		
    </tr>
   
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	
			</c:forEach>
<tr height="20">
		<td width="4%" nowrap="nowrap" class="listViewThS1" scope="col">
			<img src="images/blank.gif" alt="asd" height="1" width="1"></td>		

<!-- ini default generated table from database -->
		   		   		   			
			
				  		
				 <td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">
				
				
				</td>	
				
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="16%" colspan=1 align="right" style="text-align: right;">
				 </td>
			
		
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				</td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%" colspan=1 align="right" style="text-align: right;">
				</td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%" colspan=1 align="right" style="text-align: right;">
				</td>
			   
	</tr>
	</tbody>
	</table>
	
</form>
	
<script language="JavaScript">
	function setItem(elem,itemId,itemCode,itemName){
		document.getElementById(elem+"Id").value = itemId;
		document.getElementById(elem+"Code").value = itemCode;
		document.getElementById(elem+"Name").value = itemName;
		
	}
	function tambahItem (){
	
			var claimId = document.form1.claimId.value;
		
			var itemCodeArray = document.form1.itemCode;
			
			
			var itemNameArrayInput = document.form1.itemName;
			var itemAmountArrayInput = document.form1.itemAmount;
			var itemValueArrayInput = document.form1.itemValue;
			var descArrayInput = document.form1.description;
			var itemIdArrayInput = document.form1.itemId;
			var itemCodeArrayInput = document.form1.itemCode;
			
			var itemNameStr = "";
			var itemAmountStr = "";
			var itemValueStr = "";
			var descStr = "";
			var itemIdStr = "";
			var itemCodeStr = "";
						
			if (itemNameArrayInput != null && itemNameArrayInput.length == null){
				itemNameStr = document.form1.itemName.value;
				itemAmountStr = document.form1.itemAmount.value;
				itemValueStr = document.form1.itemValue.value;
				descStr = document.form1.description.value;
				itemIdStr = document.form1.itemId.value;
				itemCodeStr = document.form1.itemCode.value;				
			}
			
			if (itemNameArrayInput != null){			
				for (var i = 0; i < itemNameArrayInput.length; i++){				
					
					if (itemNameArrayInput[i].value == null || itemNameArrayInput[i].value == ""){
						itemNameStr += " ";
					}
					else {						
						itemNameStr += itemNameArrayInput[i].value;						
					}
					
					if (itemAmountArrayInput[i].value == null || itemAmountArrayInput[i].value == ""){
						itemAmountStr += " ";
					}
					else {
						itemAmountStr += itemAmountArrayInput[i].value;						
					}
					
					if (itemValueArrayInput[i].value == null || itemValueArrayInput[i].value == ""){
						itemValueStr += " ";
					}
					else {						
						itemValueStr += itemValueArrayInput[i].value;
					}
					
					if (descArrayInput[i].value == null || descArrayInput[i].value == ""){
						descStr += " ";
					}
					else {						
						descStr += descArrayInput[i].value;
					}
					
					if (itemIdArrayInput[i].value == null || itemIdArrayInput[i].value == ""){
						itemIdStr += " ";
					}
					else {						
						itemIdStr += itemIdArrayInput[i].value;
					}
					if (itemCodeArrayInput[i].value == null || itemCodeArrayInput[i].value == ""){						
						itemCodeStr += " ";
					}
					else {						
						itemCodeStr += itemCodeArrayInput[i].value;
					}
					
					if (i != (itemNameArrayInput.length - 1)){
						itemNameStr += "|";
						itemAmountStr += "|";
						itemValueStr += "|";
						descStr += "|";
						itemIdStr += "|";
						itemCodeStr += "|";
					}
				}
			}
			
			
			AJAXClaimItemService.addClaimItem (itemIdStr, itemCodeStr, itemNameStr,itemAmountStr,itemValueStr, descStr, claimId, {
			callback: function (res){
				var innerTabel = document.getElementById("claimItemTableId");			
				innerTabel.innerHTML = res;
				calculateValue();
			}
		}); 
	
	}
	function addItem (){
		
		var innerTabel = document.getElementById("claimItemTableId");
		var claimId = document.form1.claimId.value;
		window.alert('hola' + claimId);
		
		AJAXClaimItemService.addClaimItem(claimId,{
			callback: function (res){
				innerTabel.innerHTML += res;			
			}		
		});
	}
	function pembulatanAction (){
		
		var totalClaim = document.getElementById("totalClaim").value;
		var totalClaimItem = document.getElementById("totalClaimItem").value;
		var downPayment = document.getElementById("pembayaranDimuka").value;
		
		AJAXClaimItemService.pembulatan (totalClaim,totalClaimItem,downPayment,{
			callback: function (res){
				document.form1.pembulatan.value = res;
				
			}
		});
	}
	function saveReturnBatch(){
		
		var totalClaimItem = document.getElementById("totalClaimItem").value;
		var downPayment = document.getElementById("pembayaranDimuka").value;
		
		var delConfirm = window.confirm ("Are You Sure Want To Save This Claim Items ?");
		if (delConfirm){
			document.form1.navigation.value = "saveitemandreturnbatch";
			document.form1.submit();
		}
		//add else adq kegunaan untuk refrest pag whitout reload
		else{
		window.top.location = window.top.location
		}
        //end add
	}
	function simpan(){
	
		//var totalClaim = document.getElementById("totalClaim").value;
		var totalClaimItem = document.getElementById("totalClaimItem").value;
		var downPayment = document.getElementById("pembayaranDimuka").value;
		
		var delConfirm = window.confirm ("Are You Sure Want To Save This Claim Items ?");
		if (delConfirm){
			document.form1.navigation.value = "saveclaimitem";
			document.form1.submit();
		}
		//add else adq kegunaan untuk refresh page whitout reload
		else{
		window.top.location = window.top.location
		}
	    //end add
	}
	function checkBalance(){
		
		if (parseFloat(totalClaim) != parseFloat(totalClaimItem)){
			window.alert ("Jumlah Nilai Claim Item Tidak Sesuai dengan nilai Claim !");
			return false;
		}
		else {
			return true;
		}
	}
	function calculateValue(){
		
		var itemValueArrayInput = document.form1.itemValue;
		var itemPrePaidArrayInput = document.form1.prePaidExcess;
		
		var entryValue = 0;
		var pembulatan = 0;	
		var prepaidValue = 0;
		
		
		for (var i = 0; i < itemValueArrayInput.length; i++){							
			var currentValue = itemValueArrayInput[i].value;
			
			if (currentValue == '' || currentValue == null || isNaN(currentValue)){
				currentValue = 0;				
			}
			else {				
				currentValue = parseFloat(itemValueArrayInput[i].value);
			}
			entryValue += currentValue;
		}	
		document.form1.totalClaimItem.value = entryValue;
		
		for (var i = 0; i < itemPrePaidArrayInput.length; i++){							
			var currentValue = itemPrePaidArrayInput[i].value;
			
			if (currentValue == '' || currentValue == null || isNaN(currentValue)){
				currentValue = 0;				
			}
			else {				
				currentValue = parseFloat(itemPrePaidArrayInput[i].value);
			}
			prepaidValue += currentValue;
		}	
		document.form1.pembayaranDimuka.value = prepaidValue;
		
	}
	function hapusItem (idx){
		var delConfirm = window.confirm ("Are You Sure Want To Delete This Item ?");
		
		if (delConfirm){
		
			var claimId = document.form1.claimId.value;
		
			var itemCodeArray = document.form1.itemCode;
			
			
			var itemNameArrayInput = document.form1.itemName;
			var itemAmountArrayInput = document.form1.itemAmount;
			var itemValueArrayInput = document.form1.itemValue;
			var descArrayInput = document.form1.description;
			var itemIdArrayInput = document.form1.itemId;
			var itemCodeArrayInput = document.form1.itemCode;
			
			var itemNameStr = "";
			var itemAmountStr = "";
			var itemValueStr = "";
			var descStr = "";
			var itemIdStr = "";
			var itemCodeStr = "";
			
			for (var i = 0; i < itemNameArrayInput.length; i++){
				
				
				if (itemNameArrayInput[i].value == null || itemNameArrayInput[i].value == ""){
					itemNameStr += " ";
				}
				else {
					
					itemNameStr += itemNameArrayInput[i].value;
				}
				
				if (itemAmountArrayInput[i].value == null || itemAmountArrayInput[i].value == ""){
					itemAmountStr += " ";
				}
				else {
					itemAmountStr += itemAmountArrayInput[i].value;
					
				}
				
				if (itemValueArrayInput[i].value == null || itemValueArrayInput[i].value == ""){
					itemValueStr += " ";
				}
				else {
					
					itemValueStr += itemValueArrayInput[i].value;
				}
				
				if (descArrayInput[i].value == null || descArrayInput[i].value == ""){
					descStr += " ";
				}
				else {
					
					descStr += descArrayInput[i].value;
				}
				
				if (itemIdArrayInput[i].value == null || itemIdArrayInput[i].value == ""){
					itemIdStr += " ";
				}
				else {
					
					itemIdStr += itemIdArrayInput[i].value;
				}
				if (itemCodeArrayInput[i].value == null || itemCodeArrayInput[i].value == ""){
					
					itemCodeStr += " ";
				}
				else {
					
					itemCodeStr += itemCodeArrayInput[i].value;
				}
				
				if (i != (itemNameArrayInput.length - 1)){
					itemNameStr += "|";
					itemAmountStr += "|";
					itemValueStr += "|";
					descStr += "|";
					itemIdStr += "|";
					itemCodeStr += "|";
				}
			}
			
			
			
			AJAXClaimItemService.hapusItem (itemIdStr, itemCodeStr, itemNameStr,itemAmountStr,itemValueStr, descStr, idx, claimId, {
			callback: function (res){
				var innerTabel = document.getElementById("claimItemTableId");
				
				
				innerTabel.innerHTML = res;
				calculateValue();
			}
		}); 
		}
	}
	function cancel(){
		document.form1.navigation.value = "detail";
		document.form1.action = "claim";
		document.form1.submit();
	}

</script>
