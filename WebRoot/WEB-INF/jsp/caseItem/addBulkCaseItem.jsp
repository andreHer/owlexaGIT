<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ page import="java.util.Vector" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<script type='text/javascript' src='dwr/engine.js'></script>
<script type='text/javascript' src='dwr/util.js'></script>
<!-- <script type='text/javascript' src='scripts/jquery-1.3.2.min.js'></script> -->
<script type="text/javascript" src="scripts/owlexa.function.js"></script>
<%
String navigation = WebUtil.getAttributeString(request,"navigation","");
%>

 <!-- Page Title Start // Should be put on <Title> tag-->

<!-- Page Title Stop-->
<%
String alert = (String) request.getAttribute("alert");
int index = 0;
int totalIndex = 0;

int totalItem = (Integer) request.getAttribute("totalItem") == null ? 0 : ((Integer) request.getAttribute("totalItem")).intValue();

Vector claimItemVector  = (Vector)request.getAttribute ("claimItemVector") == null ? new Vector() : (Vector)request.getAttribute ("claimItemVector");
Vector claimAmountVector = (Vector)request.getAttribute ("claimAmountVector") == null ? new Vector() : (Vector)request.getAttribute ("claimAmountVector");
Vector claimItemRuleVector  = (Vector)request.getAttribute ("claimItemRuleVector") == null ? new Vector() : (Vector)request.getAttribute ("claimItemRuleVector");
Vector claimItemNameVector  = (Vector)request.getAttribute ("claimItemNameVector") == null ? new Vector() : (Vector)request.getAttribute ("claimItemNameVector");

Vector claimItemCodeVector  = (Vector)request.getAttribute ("claimItemCodeVector") == null ? new Vector() : (Vector)request.getAttribute ("claimItemCodeVector");
Vector claimItemIdVector = (Vector) request.getAttribute("claimItemIdVector") == null ? new Vector() : (Vector)request.getAttribute ("claimItemIdVector");
Vector claimValueVector = (Vector)request.getAttribute ("claimValueVector") == null ? new Vector() : (Vector)request.getAttribute ("claimValueVector");
Vector claimDescVector = (Vector)request.getAttribute ("claimDescVector");


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

<form name="form1" action="caseitem" method="POST">
<input type="hidden" name="navigation" value="savecaseitem">
<input type="hidden" name="arah" value="">
<input type="hidden" name="totalItem" value="<c:out value="${totalItem}" />" id="idTotalItem">
<input type="hidden" name="caseId" value="<c:out value="${caseId}" />">
<input type="hidden" name="index" value="<c:out value="${index}" />">

<br /> 	
<table class="tabForm" cellspacing="0" cellpadding="0">
	<thead>
		<tr height="20">
			<td width="2%" nowrap="nowrap" class="listViewThS1" scope="col">No.</td>		   		   		   			
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Item Code&nbsp;</td>
			
		 	<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">Item Name&nbsp;</td>
		 	<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">Benefit Rule&nbsp;</td>	
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">No. of Services &nbsp;</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">Item Amount &nbsp;</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="25%">Description &nbsp;</td>			
		</tr>
	</thead>
	<tbody id="claimItemTableId">
	
	<%	
		for ( i = 1 ; i <= totalItem; i++){
		System.out.println ("DESC " + claimDescVector.get(i-1));
	%>
		<input type="hidden" id="claimItem<%=i%>Id" name="claimItemId" value="<%=(String)claimItemIdVector.get(i-1)%>">
		<input type="hidden" id="item<%=i%>Id" name="itemId" value="<%=(Integer)claimItemVector.get(i-1)%>">
		
	 	<tr height="20">
      		<td class="oddListRowS1" align="center" bgcolor="#4f85c6" nowrap="nowrap" valign="top"><%=i%></td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<input class="input2" size="10" type="text" id="item<%=i%>Code" name="itemCode" value="<%=(String)claimItemCodeVector.get(i-1)%>" readonly="readonly"> 
			</td>
	      	<td class="oddListRowS1" bgcolor="#4f85c6" nowrap="nowrap" valign="top">      		
      			<input type="text" class="input2" size="30" id="item<%=i%>Name" name="itemName" value="<%=(String)claimItemNameVector.get(i-1) %>" readonly="readonly" >
			</td>
			<td class="oddListRowS1" bgcolor="#4f85c6" nowrap="nowrap" valign="top">      		
      			<%=(String)claimItemRuleVector.get(i-1) %>  
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<input class="input2" size="10" onkeypress="return isNumberKey(this,event);" type="text"  style="text-align: right;" name="itemAmount" value="<%=(String)claimAmountVector.get(i-1)%>">
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<input class="input2" type="text" onkeypress="return isNumberKey(this,event);" onkeydown="javascript:calculateValue()" onfocus="javascript:calculateValue()" onchange="javascript:calculateValue()" id="idItemValue<%=i%>" style="text-align: right;" name="itemValue" value="<%=(String)claimValueVector.get(i-1)%>">
			</td>  		   	
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<TEXTAREA class="textarea2" cols="45" rows="1" name="description"><%=(String)claimDescVector.get(i-1)%></TEXTAREA>
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
			<td class="listViewThS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"></td>
	      	<td class="listViewThS1" bgcolor="#4f85c6" nowrap="nowrap" valign="top" colspan=2>
	      		<input class="input2" type="text" style="text-align: right;" name="pembayaranDimuka" id="pembayaranDimuka" value="<fmt:formatNumber pattern="#.###"><c:out value="${pembayaranDimuka}" /></fmt:formatNumber>" >      		
			</td>		
			<td class="listViewThS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="middle">
				<input class="input2" type="text" onclick="javascript:calculateValue()" style="text-align: right;" name="totalClaimItem" id="totalClaimItem" value="" readonly="readonly">
			</td>
			<td class="listViewThS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="middle"></td> 		   		   		
			  	 
    	</tr>	
	</tfoot>
</table>
	
<table border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>
    	<tr>
      		<td style="padding-top: 2px;">
	    		<input type="hidden" name="notyet" value="">	    
        		<input title="Save [Alt+Shift+S]" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:simpan()" name="Save" value=" Save " type="button">
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
				
			</c:if>		
	</tr>
	<c:forEach items="${memberBenefitList}" var="benefit" varStatus="status" >
	
	 <tr height="20">
      <td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${status.index + 1}" />.</td>

      		   		   		   		   		   		   		
					   		   		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" >
			<c:out value="${benefit.itemCategoryId.itemCategoryCode}" /> <c:if test="${benefit.itemCategoryId.itemCategoryAlternateCode ne null}">/</c:if> <c:out value="${benefit.itemCategoryId.itemCategoryAlternateCode}" />
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" >
			<c:out value="${benefit.clientItemCode}" /> 
		</td>			   					   		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<a href="claimitem?memberId=<c:out value="${memberId}" />&navigation=usagetrack&memberBenefitId=<c:out value="${benefit.memberBenefitId}" />&itemCategoryId=<c:out value="${benefit.itemCategoryId.itemCategoryId}" />&prev=listmember" class="linkDetail"><c:out value="${benefit.itemCategoryId.itemCategoryName}" /></a>		
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
	function simpan(){		
		var delConfirm = window.confirm ("Are You Sure Want To Save This Case Items ?");
		if (delConfirm){
			document.form1.navigation.value = "savecaseitem";
			document.form1.submit();
		}	
	}
	function cancel(){
		document.form1.navigation.value = "search";
		document.form1.action = "caseitem";
		document.form1.submit();
	}

</script>
