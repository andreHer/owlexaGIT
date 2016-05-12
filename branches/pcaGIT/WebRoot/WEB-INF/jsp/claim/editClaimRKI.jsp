<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<%@page import="java.util.Vector"%>
<link rel="stylesheet" type="text/css" href="css/autocomplete.css">
<link rel="stylesheet" type="text/css" href="css/button.css">

<link rel="stylesheet" type="text/css" href="css/jquery/autocomplete/jquery.autocomplete.css"/>

<script type="text/javascript" src="scripts/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="scripts/jquery/plugin/jquery.autocomplete.pack.js"></script>


<%

String alert = (String) request.getAttribute("alert");
int index = 0;
int totalIndex = 0;

int totalItem = (Integer) request.getAttribute("totalItem") == null ? 0 : ((Integer) request.getAttribute("totalItem")).intValue();

Vector claimItemVector  = (Vector)request.getAttribute ("claimItemVector") == null ? new Vector() : (Vector)request.getAttribute ("claimItemVector");
Vector claimAmountVector = (Vector)request.getAttribute ("claimAmountVector") == null ? new Vector() : (Vector)request.getAttribute ("claimAmountVector");

Vector claimItemNameVector  = (Vector)request.getAttribute ("claimItemNameVector") == null ? new Vector() : (Vector)request.getAttribute ("claimItemNameVector");

Vector claimItemCodeVector  = (Vector)request.getAttribute ("claimItemCodeVector") == null ? new Vector() : (Vector)request.getAttribute ("claimItemCodeVector");
Vector claimItemIdVector = (Vector) request.getAttribute("claimItemIdVector") == null ? new Vector() : (Vector)request.getAttribute ("claimItemIdVector");
Vector claimValueVector = (Vector)request.getAttribute ("claimValueVector") == null ? new Vector() : (Vector)request.getAttribute ("claimValueVector");
Vector claimDescVector = (Vector)request.getAttribute ("claimDescVector");


System.out.println ("CLAIM ITEM : " + claimItemVector.size() );

int count = 0;
int countSet = 0;
int i = 0;


if (alert != null && !alert.trim().equals("")) {%>
	<div id="warning">
		<c:out value="${alert}"></c:out>
	</div>
	<%}%>
<br />

<form action="claim" method="POST"  name="form1" id="form_layout">
	<input type="hidden" name="navigation" value="saveclaimrki">
	<input type="hidden" name="claimId" value="" >
	<input type="hidden" name="caseId" value="<c:out value="${caseId}" />" >
<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>
						
		<tr>
			<td class="dataLabel"> </td>
  			<td class="dataField" colspan="3">&nbsp;</td>
		</tr>	
		
		<tr>
			<td class="dataLabel">Card Number * : </td>		
			<td class="dataField">				
				<input type="hidden" size=35 id="memberId" tabindex="2"  name="memberId" value="<c:out value="${memberId}" />" >
				<input type="text" size=35 id="member_number" tabindex="2"  name="cardNumber" value="<c:out value="${cardNumber}" />" >&nbsp;									
			</td>
		    <td class="dataLabel">Claim Service * : </td>		
			<td class="dataField">
				<input type="hidden" size=35 id="caseCategoryId" tabindex="2"  name="caseCategoryId" value="<c:out value="${caseCategoryId}" />" >
				<input type="text" readonly="readonly" size=35 id="ccName" tabindex="2"  name="ccName" value="<c:out value="${caseCategory.caseCategoryName}" />" >&nbsp;
				
																	
			</td>	
		</tr>
		<tr>
		    <td class="dataLabel">Member Name : </td>		
			<td class="dataField">
				<input type="text" tabindex="3"  id="name" name="memberName" value="<c:out value="${memberName}" />" size="35" >
			</td>	  
			<td class="dataLabel"> Provider Name * : </td>
			<td class="dataField">
				<input type="text" readonly="readonly" size="35" id="providerName" name="providerName" value="<c:out value="${providerName}"/>" >
				<input type="hidden" id="providerId" name="providerId" value="<c:out value="${providerId}"/>" maxlength="35">
			</td>	 		
		</tr>
		<tr>
		    <td class="dataLabel"></td>		
			<td class="dataField">
				
			</td>	  
			<td class="dataLabel"> Claim Currency * : </td>
			<td class="dataField">
				<select name="claimCurrencyId">
					<option value="">-- SELECT ONE --</option>
					<c:forEach items="${currencyList}" var="currency">
						<option value="${currency.currencyId}" <c:if test="${currency.currencyId eq claimCurrencyId}">selected</c:if>><c:out value="${currency.currencyName}" /></option>
					</c:forEach>
				</select>
			</td>	 		
		</tr>
	
		
		<tr>
			<td class="dataLabel">&nbsp; </td>
			<td class="dataField">&nbsp;</td>
		    <td class="dataLabel"></td>
		    <td class="dataField"></td>
		</tr>
		<tr>
			<td class="dataLabel"></td>
			<td class="dataField"></td>
		    <td class="dataLabel">Diagnosis 1 * : </td>
		    <td class="dataField">
		    	<input type="hidden" id="diagnosis1Id" name="diagnosis1Id" value="<c:out value="${diagnosis1Id}" />">
				<input type="text"  size="40" id="diagnosis1Text" name="diagnosis1Text" value="<c:out value="${diagnosis1Text}" />">
				<div id="diagnosisvalidator">
					<c:out value="Diagnosis is Required" />
				</div>
			</td>
		</tr>

		<tr>
			<td class="dataLabel"> Admission Date * : </td>
			<td class="dataField">
					<input type="hidden" id="admissionDateChk" name="admissionDateChk" value="<c:out value="${admissionDate}" />">
					<input type="text" name="admissionDate" id="admissionDate" value="<c:out value="${admissionDate}" />" maxlength="10" onchange="javascript:checkAdmissionDate()">
					<img src="images/jscalendar.gif" alt="Enter Date" id="admissionDate_trigger"  height="20" width="20">
					<script type="text/javascript">
    					Calendar.setup({
        					inputField     :    "admissionDate",     // id of the input field
        					ifFormat       :    "%Y-%m-%d",      // format of the input field
        					button         :    "admissionDate_trigger",  // trigger for the calendar (button ID)
        					align          :    "Bl",           // alignment (defaults to "Bl")
        					singleClick    :    true
    					});
				 	</script>
				 	<div id="admissionvalidator">
						<c:out value="Admission Date is Required" />
					</div>

			</td>
		    <td class="dataLabel">Diagnosis 2 : </td>
		    <td class="dataField">		    	
		    	<input type="hidden" id="diagnosis2Id" name="diagnosis2Id" value="<c:out value="${diagnosis2Id}" />">				
				<input type="text" tabindex="2" id="diagnosis2Text"  size="40"  name="diagnosis2Text" value="<c:out value="${diagnosis2Text }" />">
				
			</td>
		</tr>
		<tr>
			<td class="dataLabel"> Discharge Date * : </td>		
			<td class="dataField">
				<input type="text" name="dischargeDate" id="dischargeDate" value="<c:out value="${dischargeDate}" />" maxlength="10">
				<img src="images/jscalendar.gif" alt="Enter Date" id="dischargeDate_trigger" height="20" width="20">
				<script type="text/javascript">
   					Calendar.setup({
       					inputField     :    "dischargeDate",     // id of the input field
       					ifFormat       :    "%Y-%m-%d",      // format of the input field
       					button         :    "dischargeDate_trigger",  // trigger for the calendar (button ID)
       					align          :    "Bl",           // alignment (defaults to "Bl")
       					singleClick    :    true
   					});
			 	</script>
				<div id="dischargevalidator">
					<c:out value="Discharge Date is Required" />
				</div>
			</td>
    		<td class="dataLabel">Diagnosis 3 :</td>
    		<td class="dataField">    	
				<input type="hidden" id="diagnosis3Id" name="diagnosis3Id" value="<c:out value="${diagnosis3Id}" />">				
				<input type="text" size="40" id="diagnosis3Text"  name="diagnosis3Text" value="<c:out value="${diagnosis3Text}" />">	
			</td>
		</tr>		
			
					
			
			<tr>
				<td class="dataLabel"></td>
				<td class="dataField"></td>	    	
				<td class="dataLabel">Surgery : </td>		
				<td class="dataField">		
						<input type="checkbox" name="isSurgery" value="1">
		
						<select name="<c:out value="${status.expression}" />">
							<option value="-1"> -- PILIH LEVEL -- </option>				
							<option value="0" <c:if test="${status.value eq \"0\" }">selected</c:if>>KOMPLEKS</option>
							<option value="1" <c:if test="${status.value eq \"1\" }">selected</c:if>>BESAR</option>
							<option value="2" <c:if test="${status.value eq \"2\" }">selected</c:if>>SEDANG</option>
							<option value="3" <c:if test="${status.value eq \"3\" }">selected</c:if>>KECIL</option>
							<option value="4" <c:if test="${status.value eq \"4\" }">selected</c:if>>MINOR</option>
						</select>
				
				</td>
			</tr>	
			<tr>
				<td class="dataLabel"></td>
				<td class="dataField"></td>	    	
				<td class="dataLabel">Complication : </td>		
				<td class="dataField">		
					<select name="hasComplication" id="hasComplication">
						<option value="1" <c:if test="${status.value eq 1}">selected</c:if>>Tanpa Komplikasi</option>				
						<option value="2" <c:if test="${status.value eq 2}">selected</c:if>>Komplikasi Ringan</option>
						<option value="3" <c:if test="${status.value eq 3}">selected</c:if>>Komplikasi Berat</option>
					</select>
				</td>
			</tr>	
							
								
		
	</tbody>
</table>
<br />
<table class="tabForm" cellspacing="0" cellpadding="0">
	<thead>
		<tr height="20">
			<td width="2%" nowrap="nowrap" class="listViewThS1" scope="col">No.</td>		
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">Item Code&nbsp;</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">Item Name&nbsp;</td>	
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">No. of Services &nbsp;</td>			
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">Item Amount &nbsp;</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">Description &nbsp;</td>
		</tr>
	</thead>
	<tbody id="claimItemTableId">
	
	<%	
		for ( i = 1 ; i <= totalItem; i++){
		//System.out.println ("DESC " + claimDescVector.get(i-1));
	%>		
		<input type="hidden" id="item<%=i%>Id" name="itemId" value="<%=(Integer)claimItemVector.get(i-1)%>">
	 <tr height="20">
      <td class="oddListRowS1" align="center" bgcolor="#4f85c6" nowrap="nowrap" valign="top"><%=i%></td>

		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<input class="input2" size="20" type="text" id="item<%=i%>Code" name="itemCode" value="<%=(String)claimItemCodeVector.get(i-1)%>"> 
		</td>
      	<td class="oddListRowS1" bgcolor="#4f85c6" nowrap="nowrap" valign="top">
      		
      		<input type="text" class="input2" size="30" id="item<%=i%>Name" name="itemName" value="<%=(String)claimItemNameVector.get(i-1) %>" readonly="readonly" >
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<input class="input2" size="10" type="text"  style="text-align: right;" name="itemAmount" value="<%=(String)claimAmountVector.get(i-1)%>">
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<input class="input2" type="text" onkeydown="javascript:calculateValue()" onfocus="javascript:calculateValue()" onchange="javascript:calculateValue()" id="idItemValue<%=i%>" style="text-align: right;" name="itemValue" value="<%=(String)claimValueVector.get(i-1)%>">
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
		
		<td class="listViewThS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="middle">
			<input class="input2" type="text" onclick="javascript:calculateValue()" style="text-align: right;" name="totalClaimItem" id="totalClaimItem" value="" readonly="readonly">
		</td>  		   	

		<td class="listViewThS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="middle">
			<!-- 
			<input class="input2" size="34" style="text-align: right;" type="text" name="totalClaim" id="totalClaim" value="<fmt:formatNumber pattern="#.###"><c:out value="${claimValue}" /></fmt:formatNumber>" readonly="readonly">
			 --> 
		</td>  		   		   		
			 
    </tr>
	
	</tfoot>
	</table>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  	<tbody>
    	<tr>
	      	<td style="padding-top: 2px;padding-left: 1px;">
		    	<input type="hidden" name="notyet" value="">
		    	<c:if test="${theUser.userType eq 2}">
		    		<c:if test="${theUser.roleId.roleId eq 19 or theUser.roleId.roleId eq 5}">
			    		<%-- <input title="Save [Alt+Shift+S]" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:register()" name="Save" value=" Process Payment " type="submit">--%>
			    		<input title="Save [Alt+Shift+S]" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:register()" name="Save" value=" Process Payment ">
			    		<input title="Save [Alt+Shift+S]" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:cancel()" name="Save" value=" Cancel " type="button">
			    	</c:if>		    		
		    	</c:if>	     
		    	<c:if test="${theUser.userType eq 4}">
		    		
			    	
			    		<input title="Save [Alt+Shift+S]" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:register()" name="Save" value=" Process Payment ">
			    		<input title="Save [Alt+Shift+S]" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:cancel()" name="Save" value=" Cancel " type="button">
			    			    		
		    	</c:if>	    	         
	      	</td>
	      	<td align="right"></td>
    	</tr>
  	</tbody>
</table>

<c:if test="${theUser.userType eq 2}" >

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
</c:if>
	
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

<script language="javascript">
	
	
	$(document).ready(function(){

	document.getElementById('diagnosisvalidator').style.visibility = 'hidden';
	document.getElementById('admissionvalidator').style.visibility = 'hidden';
	document.getElementById('dischargevalidator').style.visibility = 'hidden';
    
    $("#member_number").autocomplete("member?navigation=lookupjson", {
        max: 7,
        dataType: "json",
        parse: function(data) {
            return $.map(data, function(row) {
                return {
                    data: row,
                    value: row.name,
                    result: row.name
                }
            });
        },
        formatItem: function(row) {
            return  "<font color='#000' style='align: left;' >"+ row.number + " - " + row.name +"</font>" ;
        },
         extraParams: {
       		memberGroupId: function() { return $("#memberGroupId").val(); }
   		}        
	    }).bind("result", function(data, value){
	        $(this).parents("dd").find(".error_message").hide();
	        $("#memberId").val (value.id);
	        $("#name").val (value.name);
	        $("#member_number").val (value.number);
	    });
	    
	    $("#providerName").autocomplete("provider?navigation=lookupjson", {
	        max: 7,
	        dataType: "json",
	        parse: function(data) {
	            return $.map(data, function(row) {
	                return {
	                    data: row,
	                    value: row.name,
	                    result: row.name
	                }
	            });
	        },
	        formatItem: function(row) {
	            return  "<font color='#000' style='align: left;' >"+ row.name +"</font>" ;
	        }
	    }).bind("result", function(data, value){
	        $(this).parents("dd").find(".error_message").hide();
	        $("#providerId").val (value.id);
	    });
	    
	    $("#diagnosis1Text").autocomplete("diagnosis?navigation=lookupjson", {
	        max: 7,
	        dataType: "json",
	        parse: function(data) {
	            return $.map(data, function(row) {
	                return {
	                    data: row,
	                    value: row.name,
	                    result: row.name
	                }
	            });
	        },
	        formatItem: function(row) {
	            return "<font color='#000'  >"+ row.number +" - </font>" + "<font color='#000' >"+ row.name +"</font>" ;
	        }
	    }).bind("result", function(data, value){
	        $(this).parents("dd").find(".error_message").hide();
	        $("#diagnosis1Id").val(value.id);
	    });
	    
	    $("#diagnosis2Text").autocomplete("diagnosis?navigation=lookupjson", {
	        max: 7,
	        dataType: "json",
	        parse: function(data) {
	            return $.map(data, function(row) {
	                return {
	                    data: row,
	                    value: row.name,
	                    result: row.name
	                }
	            });
	        },
	        formatItem: function(row) {
	            return "<font color='#000'  >"+ row.number +" - </font>" + "<font color='#000' >"+ row.name +"</font>" ;
	        }
	    }).bind("result", function(data, value){
	        $(this).parents("dd").find(".error_message").hide();
	        $("#diagnosis2Id").val(value.id);
	    });
	    
	    $("#diagnosis3Text").autocomplete("diagnosis?navigation=lookupjson", {
	        max: 7,
	        dataType: "json",
	        parse: function(data) {
	            return $.map(data, function(row) {
	                return {
	                    data: row,
	                    value: row.name,
	                    result: row.name
	                }
	            });
	        },
	        formatItem: function(row) {
	            return "<font color='#000'  >"+ row.number +" - </font>" + "<font color='#000' >"+ row.name +"</font>" ;
	        }
	    }).bind("result", function(data, value){
	        $(this).parents("dd").find(".error_message").hide();
	        $("#diagnosis3Id").val(value.id);
	    });	    
	    
	});

	function checkAdmissionDate(){
		var admissionDate = document.form1.admissionDate.value;
		var serviceCategoryId = document.form1.caseCategoryId.value;
		
		if (serviceCategoryId == 2 || serviceCategoryId == 4 || serviceCategoryId == 5 || serviceCategoryId == 9){
			document.form1.dischargeDate.value = admissionDate;
		}
		else if (serviceCategoryId == 1 || serviceCategoryId == 3){
			document.form1.dischargeDate.value = "";
		}
	}
	function updateItem(){
		window.alert("ganti tipe");
		
	}
	function register (){
		//alert('FFF');
		//alert('aaaa'+document.form1.admissionDate.value);
		//alert('aaaa'+document.form1.diagnosis1Text.value);
		if(document.form1.diagnosis1Id.value !=null && document.form1.diagnosis1Text.value != "" &&
			document.form1.diagnosis1Text.value != null && document.form1.admissionDate.value != null &&
			document.form1.admissionDate.value != "" && document.form1.dischargeDate.value != null &&
			document.form1.dischargeDate.value != ""){
			document.getElementById('diagnosisvalidator').style.visibility = 'hidden';
				document.getElementById('admissionvalidator').style.visibility = 'hidden';
				
				document.form1.method = "POST";
				document.form1.navigation.value = "saveclaimrki";
				document.form1.action = "claim";
				document.form1.submit();
		}else{
			alert("Input Data is Not Complete");
		}
		
		if(document.form1.admissionDate.value == null || document.form1.admissionDate.value == ""){
			document.getElementById('admissionvalidator').style.visibility = 'visible';
			//alert('Admission Date Is Required');
		}else{
			document.getElementById('admissionvalidator').style.visibility = 'hidden';
		}
		if(document.form1.diagnosis1Text.value == null || document.form1.diagnosis1Id.value == null ||
			document.form1.diagnosis1Text.value == ""){
			document.getElementById('diagnosisvalidator').style.visibility = 'visible';
			//alert('Diagnosis Is Required');
		}else{
			document.getElementById('diagnosisvalidator').style.visibility = 'hidden';
		}
		if(document.form1.dischargeDate.value == null || document.form1.dischargeDate.value == ""){
			document.getElementById('dischargevalidator').style.visibility = 'visible';
			//alert('Discharge Date Is Required');
		}else{
			document.getElementById('dischargevalidator').style.visibility = 'hidden';
		}
		
		
		<c:if test="${not empty diagnosis1Id and not empty diagnosis1Text}">
// 			document.form1.method = "POST";
// 			document.form1.navigation.value = "saveclaimrki";
// 			document.form1.action = "claim";
// 			document.form1.submit();
		</c:if>
	}
	function simpan (){
		document.form1.method = "POST";
		document.form1.action = "claim-form";
		document.form1.submit();
	}
	function cancel (){
		document.form1.method = "GET";
		document.form1.action = "case";
		document.form1.navigation.value = "searchopen";
		document.form1.submit();
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

</script>