<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
	String alert = (String) request.getAttribute("alert");
%>

<br />
<form action="claim" method="GET" name="form1" id="form_layout">
<input type="hidden" name="navigation" value="">
<input type="hidden" name="memberId" value="<c:out value="${memberId}" />" />
<input type="hidden" name="caseCategoryId" value="<c:out value="${caseCategoryId}" />" />
<input type="hidden" name="providerId" value="<c:out value="${providerId}" />" />

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr> 
      <td style="padding-bottom: 2px; padding-top: 5px;">        
        
	        <c:if test="${theUser.userType eq 2}">
	          	<c:if test="${registerStatus eq 1 && !preAdmission}">      
	          		<input title="Print Register" accesskey="E" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:printRKI()" name="printReg" value=" Print Register " type="button">       	
	          	</c:if>  			        
			</c:if>
			<c:if test="${theUser.userType eq 4}">
	          	<c:if test="${registerStatus eq 1}">      
	          		<input title="Print Register" accesskey="E" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:printRKI()" name="printReg" value=" Print Register " type="button">       	
	          	</c:if>  			        
			</c:if>
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
						<a href="member-rki-form" class="tabDetailViewDFLink" style="font-weight: normal;">Return to Registration&nbsp;</a>					
					</td>
				</tr>
			</tbody>
		</table>
	  </td>
    </tr>		
	
    	<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Member Group :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${member.memberGroupId.groupName}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Member Status :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%">
	      <c:if test="${member.status eq -1}">PENDING</c:if>
			<c:if test="${member.status eq 1}">ACTIVE</c:if>
			<c:if test="${member.status eq -3}">PENDING CHANGEPLAN</c:if>
			<c:if test="${member.status eq -2}">BLOCKED</c:if>
			<c:if test="${member.status eq 2}">TERMINATED</c:if>
			<c:if test="${member.status eq 3}">RESIGNED</c:if>
			<c:if test="${member.status eq 4}">INACTIVE</c:if>
			<c:if test="${member.status eq 5}">INITIALIZED</c:if>
	      </td>
	    </tr>	
	    <tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Member Name :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%">
		  <c:choose>
		  	<c:when test="${theUser.userType eq 4}">
		  		<c:out value="${member.firstName}"/> <c:out value="${member.lastName}"/>
		  	</c:when>
		  	<c:otherwise>
		  		<a href="member?navigation=detail&memberId=<c:out value="${member.memberId}" />" class="listViewTdLinkS1"><c:out value="${member.firstName}"/> <c:out value="${member.lastName}"/></a>
		  	</c:otherwise>
		  </c:choose>
		  </td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Relationship :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%">
	      	<c:if test="${member.relationshipId.relationshipId eq 1}">SPOUSE</c:if>
	      	<c:if test="${member.relationshipId.relationshipId eq 2}">CHILD</c:if>
	      	<c:if test="${member.relationshipId.relationshipId eq 3}">EMPLOYEE</c:if>
			</td>
	    </tr>	
	       <tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Card Number :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${cardNumber}" /></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Expire Date :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><c:out value="${member.expireDate}"/></td>
	    </tr>	
	    
	   	<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>			
   		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Register Date :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${registerDate}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Hospital / Clinic / Dr. :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><c:out value="${provider.providerName}"/></td>
	    </tr>			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Register Status :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"><b><c:if test="${registerStatus eq 1}">SUCCESS</c:if><c:if test="${registerStatus eq 0}">FAILED</c:if></b></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Reject Reason :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><b><c:out value="${rejectReason}" /></b></td>
	    </tr>			
			
	
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="33%"></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>			
					
		
		
	
		

		    <tr>
      <td colspan="20"  style="padding: 0px;">
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tbody>
				<tr>
					<td style="text-align: left;" class="tabDetailViewDL">&nbsp;
					</td>
					<td class="tabDetailViewDL" align="right" nowrap="nowrap">
						<a href="member-rki-form" class="tabDetailViewDFLink" style="font-weight: normal;">Return to Registration&nbsp;</a>					
					</td>
				</tr>
			</tbody>
		</table>
	  </td>
    </tr>		
	</tbody>
</table>


		
	
</form>

<br />

<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	<tr height="20">
		<td width="1%" nowrap="nowrap" class="listViewThS1" scope="col"  style="text-align: center;">
			<img src="images/blank.gif" alt="asd" height="1" width="1">No.</td>		
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%"  style="text-align: center;">
				Item Name &nbsp;</td>
			
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%" style="text-align: center;">
				Benefit Limit &nbsp;</td>
					
		
					
			
	</tr>


	<c:forEach items="${benefitList}" var="benefit" varStatus="status" >
	
	 <tr height="20">
      	<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${status.index+1}" /></td>      		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${fn:toUpperCase(benefit.itemCategoryId.itemCategoryEDCName)}" />			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;">
			Sesuai Bnf
		</td>  		
					   		   		
    </tr>
   
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	
	</c:forEach>

	</tbody>
	</table>
<script language="javascript">

	function printRKI(){
		window.open ("member?navigation=printrki&memberId=<c:out value="${memberId}" />&caseCategoryId=<c:out value="${caseCategoryId}" />&providerId=<c:out value="${providerId}" />&url=claim-form","Search","width=1024, height=768, menubar=yes, status=no, toolbar=no, scrollbars=yes, resizable=yes");
	}
	
	
	
	function paymentProcess (){		
		document.form1.action = "claim";
		document.form1.navigation.value = "claimrki";
		document.form1.method = "GET";		
		document.form1.submit();		
	}
	
	

</script>
