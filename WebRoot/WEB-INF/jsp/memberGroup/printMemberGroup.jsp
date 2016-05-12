<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<head>


		<script type="text/javascript" src="scripts/sugar_3.js"></script>
		<script type="text/javascript" src="scripts/cookie.js"></script>
		<link rel="stylesheet" type="text/css" media="all" href="css/calendar-win2k-cold-1.css" />
		<link rel="stylesheet" type="text/css" media="all" href="css/style.css" />
	
		
<script src="scripts/prototype.js" type="text/javascript"></script>
  <script src="scripts/scriptaculous.js" type="text/javascript"></script>
  	<link rel="stylesheet" type="text/css" href="css/tabcontent.css" />
<script type="text/javascript" src="scripts/tabcontent.js">
/***********************************************
* Tab Content script- © Dynamic Drive DHTML code library (www.dynamicdrive.com)
* This notice MUST stay intact for legal use
* Visit Dynamic Drive at http://www.dynamicdrive.com/ for full source code
***********************************************/
</script>
<link rel="stylesheet" type="text/css" href="css/reset-fonts-grids.css" />
        <!-- CSS for Menu -->

        <link rel="stylesheet" type="text/css" href="css/menu.css" />
 
 
        <!-- Page-specific styles -->

        <!-- Namespace source file -->
<!-- calendar stylesheet -->
	

		<!-- main calendar program -->
		<script type="text/javascript" src="scripts/calendar.js"></script>

		
        <!-- Menu source file -->
       
		
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Aplikanusa Lintasarta - Healthcare Management System</title>


		<link href="css/navigation.css" rel="stylesheet" type="text/css" />
		<script language="javascript" src="scripts/menu.js"></script>
		<script language="javascript" src="scripts/cookie_002.js"></script>

	</head>


<form action="membergroup" method="GET" name="form1" id="form_layout">
<input type="hidden" name="navigation" value="">
	<input type="hidden" name="memberGroupId" value="<c:out value="${memberGroup.memberGroupId}" />">
	
<table class="tabDetailView" border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>


			
	
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Group Name :&nbsp;</td>		 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${memberGroup.groupName}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">Join Date :&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${memberGroup.joinDate}"/></td>
	      
	    </tr>
			
			
	
			
		<tr>
		  <td class="tabDetailViewDL" valign="top" width="17%">Group Code :&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${memberGroup.memberGroupCode}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">Effective Date :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${memberGroup.effectiveDate}"/></td>	      
	    </tr>
			
	
			
		<tr>
	     <td class="tabDetailViewDL" valign="top" width="17%">Policy Number :&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${memberGroup.policyNumber}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">Renewal Date :&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${memberGroup.renewalDate}"/></td>	      
	    </tr>
			
	
			
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Group Limit :&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="33%"><fmt:formatNumber><c:out value="${memberGroup.groupLimit}"/></fmt:formatNumber></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">Resigned Date :&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${memberGroup.resignedDate}"/></td>	      
	    </tr>
			
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Actual Group Limit :&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="33%"><fmt:formatNumber><c:out value="${memberGroup.actualGroupLimit}"/></fmt:formatNumber></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">Expire Date :&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${memberGroup.expireDate}"/></td>
	      
	    </tr>
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Status :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${memberGroup.status.status}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Client :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><c:out value="${memberGroup.clientId.clientName}"/></td>
	    </tr>			
	
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="15%">&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
		
	
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Annual Premium :&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><fmt:formatNumber> <c:out value="${memberGroup.annualGroupPremium}"/></fmt:formatNumber></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="33%"></td>	      
	    </tr>
	
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Benefit Usage :&nbsp;</td>		 
	      <td class="tabDetailViewDF" valign="top" width="33%"><fmt:formatNumber><c:out value="${totalBenefitUsage}"/></fmt:formatNumber></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">Claim Percentage :&nbsp;</td>		 
	      <td class="tabDetailViewDF" valign="top" width="33%"><fmt:formatNumber pattern="#,##.##"><c:out value="${claimPercentage}"/></fmt:formatNumber> %</td>
	    </tr>	
		
		
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="15%">&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">City :&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${memberGroup.city}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">Telephone :&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${memberGroup.telephone}"/></td>	      
	    </tr>
	
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Province :&nbsp;</td>		 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${memberGroup.province}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">Faximile :&nbsp;</td>		 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${memberGroup.faximile}"/></td>
	    </tr>
	    
	    
	    
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Country :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${memberGroup.country}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">Website :&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${memberGroup.website}"/></td>	      
	    </tr>		
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Postal Code :&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${memberGroup.postalCode}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">Email :&nbsp;</td>		 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${memberGroup.email}"/></td>
	      
	    </tr>
	
			
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="15%">&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>	
	
	
			
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Bank Account :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${memberGroup.bankAccount}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Bank :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><c:out value="${memberGroup.bank}"/></td>
	    </tr>
	
			
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Bank Account Name :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${memberGroup.bankAccountName}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Bank Branch :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><c:out value="${memberGroup.bankBranch}"/></td>
	    </tr>
	
			
	
				
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Created Time :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${memberGroup.createdTime}"/>  -   <c:out value="${memberGroup.createdBy}"/></td>
  	      <td class="tabDetailViewDL" valign="top" width="17%">Modified Time :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${memberGroup.modifiedTime}"/>  -  <c:out value="${memberGroup.modifiedBy}"/></td>
	    </tr>
			

				<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Address :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" colspan="3" valign="top" width="33%"><c:out value="${memberGroup.address}"/></td>
	      
	    </tr>
		
			
				
	</tbody>
</table>


</form>
