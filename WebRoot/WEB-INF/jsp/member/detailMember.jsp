<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<form action="member" method="GET" name="form1" id="form_layout">
<input type="hidden" name="caseType" value="">

	<input type="hidden" name="navigation" value="">
	<input type="hidden" name="memberId" value="<c:out value="${member.memberId}" />">
	
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Detail Member</h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
      <c:if test="${theUser.userType eq 2 }">
	      <td align="right">
	      	<input title="Member Log"  name="errorLog" id="errorLog" value=" Error Log " class="errorLog" type="button" onClick="javascript:printErrorLog()">
	      </td>
	      <td align="right">
	      	<input title="Add Member Log"  name="addErrorLog" value=" Add Error Log " class="errorLog" type="button" onClick="javascript:adderrorlog()">
	      </td>
      </c:if>
    </tr>
  </tbody>
</table>

<c:set var="now" value="<%=new java.util.Date()%>"/>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr> 
      	<td style="padding-bottom: 2px; padding-top: 5px;">
      	<c:if test="${theUser.userType eq 2}">
		<c:if test="${theUser.roleId.roleId eq 0}">
			<input title="Update [Alt+Shift+U]" 			accesskey="U" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:ubah()" name="Update" value=" Update " type="button">
			
			<input title="Delete [Alt+Shift+D]" 			accesskey="D" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:hapus()" name="Delete" value=" Delete " type="button">			
			
			</c:if>
			<c:if test="${member.status eq \"2\" or member.status eq \"3\"}">
				<c:if test="${theUser.roleId.roleId eq 0}">
				<input title="Renew [Alt+Shift+R]" 			accesskey="R" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="" name="Renew" value=" Renew " type="submit">
				</c:if>
				</c:if>
			
			<c:if test="${member.status eq '1' or member.status eq '-4' or member.status eq '-3' or member.status eq '7'}">
				<c:if test="${theUser.roleId.roleId eq 0}">
					<input title="Terminate [Alt+Shift+T]" 		accesskey="T" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:terminateMember()" name="Terminate" value=" Terminate " type="button">
					
					<input title="Block [Alt+Shift+B]" 		accesskey="T" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:blockMember()" name="Block" value=" Block " type="button">
					
					<input title="Upgrade [Alt+Shift+B]" 		accesskey="U" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:upgradeMember()" name="Upgrade" value=" Upgrade " type="button">
					
					
				</c:if>
			</c:if>
			<c:if test="${member.status eq 2}">
					<c:if test="${now < member.resignedDate}">
						<input title="Terminate [Alt+Shift+T]" 		accesskey="T" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:terminateMember()" name="Terminate" value=" Terminate " type="button">
					
					<input title="Block [Alt+Shift+B]" 		accesskey="T" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:blockMember()" name="Block" value=" Block " type="button">
					
					<input title="Upgrade [Alt+Shift+B]" 		accesskey="U" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:upgradeMember()" name="Upgrade" value=" Upgrade " type="button">
					
							<c:if test="${theUser.roleId.roleId eq 0 or theUser.roleId.roleId eq 1 or theUser.roleId.roleId eq 2 }">
								<input title="Guarantee Letter [Alt+Shift+L]" 		accesskey="L" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:createGL()" name="gl" value=" Guarantee Letter " type="button">
							</c:if>
					</c:if>
					
			</c:if>
					
			<c:if test="${member.status eq '1' or member.status eq '-4' or member.status eq '-3' or member.status eq '7' }">
				<c:if test="${theUser.roleId.roleId eq 0 or theUser.roleId.roleId eq 1 or theUser.roleId.roleId eq 2 }">
					<input title="Guarantee Letter [Alt+Shift+L]" 		accesskey="L" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:createGL()" name="gl" value=" Guarantee Letter " type="button">
				</c:if>
			</c:if>
			<c:if test="${member.status eq -2 }">
				<c:if test="${theUser.roleId.roleId eq 0}">
					<input title="Terminate [Alt+Shift+T]" 		accesskey="T" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:terminateMember()" name="Terminate" value=" Terminate " type="button">
					
					<input title="UnBlock [Alt+Shift+B]" 		accesskey="T" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:unblockMember()" name="UnBlock" value=" UnBlock " type="button">
					
				</c:if>
			</c:if>
			<!-- 
			<input title="First Call [Alt+Shift+F]" 		accesskey="F" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:fc(<c:out value="${member.memberId}" />)" name="First Call" value=" First Call " type="button">
			 -->
			
			</c:if>	
		
			<c:if test="${member.status eq -1 or member.status eq -3}">
				<c:if test="${theUser.roleId.roleId eq 0}">
					<input title="Activate [Alt+Shift+A]" accesskey="A" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:aktifasi()" name="act" value=" Activate " type="button">
				</c:if>
			</c:if>	
		</td>
    </tr>
  </tbody>
</table>

<%@ include file="../mainContentMember.jsp" %>
	

<br />
 	
<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	<tr>
      <td colspan="20" align="right"><table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tbody>
          <tr>	            
            <td class="listViewPaginationTdS1" align="left">&nbsp;</td>
            <td class="listViewPaginationTdS1" align="right" nowrap="nowrap" ></td>
          </tr>
        </tbody>
      </table></td>
    </tr>
	<tr height="20">
		   <td width="2%" nowrap="nowrap" class="listViewThS1" scope="col">
			<img src="images/blank.gif" alt="asd" height="1" width="1">No.</td>
		   <td scope="col" class="listViewThS1" nowrap="nowrap" width="8%">Product Code&nbsp;</td>
		   <td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Product Type&nbsp;</td>
		   <td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Service&nbsp;</td>
		   <td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Shared Plan&nbsp;</td>
		   <td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Client&nbsp;</td>
		   <td scope="col" class="listViewThS1" nowrap="nowrap" width="8%">Effective&nbsp;</td>
		   <td scope="col" class="listViewThS1" nowrap="nowrap" width="8%">Expire&nbsp;		</td>   
		   <td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Benefit Usage&nbsp;</td>
		   		   
		   <td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Actual Benefit Limit&nbsp;		   </td>
		   <td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Annual Benefit&nbsp;		   </td>
		   <td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Usage %&nbsp;		   </td>
		   		   
		      
		   <td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Status&nbsp;	</td>
		   	
	</tr>


	<c:forEach items="${MemberProducts}" var="memberProduct" varStatus="status" >
	
	 <tr height="20">
      <td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${status.index + 1}" /></td>
     		   		   		   		   		   		   		   		   		
       <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="left" valign="top">
			<c:out value="${memberProduct.productId.productCode}" />
	  </td>
	  <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="left" valign="top">
			<c:out value="${memberProduct.productId.productType.productTypeName}" />
	  </td>
	  <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="left" valign="top">
			<c:out value="${memberProduct.productId.caseCategory.caseCategoryCode}" />
	  </td>
	  <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="left" valign="top">
			<c:out value="${memberProduct.secondaryCoverageId.productId.productName}" />
	  </td>
	  <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="left" valign="top">
			<c:out value="${memberProduct.memberId.clientId.clientName}" />
	  </td>
       <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="left" valign="top">
			<c:out value="${memberProduct.registerDate}" />
	  </td>					   		   		
       <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="left" valign="top">
			<c:out value="${memberProduct.expireDate}" />
	  </td>					   		   							   		   		
       <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="left" valign="top" style="text-align: right;">
			<fmt:formatNumber><c:out value="${memberProduct.benefitUsage}" /></fmt:formatNumber>
	  </td>
	  <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="left" valign="top"  style="text-align: right;">	  		
			<c:if test="${memberProduct.familyProductId eq null and memberProduct.secondaryCoverageId eq null}">
		  		<c:if test="${memberProduct.actualBenefitLimit eq -1.0}">
	      			UNLIMITED
	      		</c:if>
	      		<c:if test="${memberProduct.actualBenefitLimit > -1.0}">      		
					<fmt:formatNumber><c:out value="${memberProduct.actualBenefitLimit}" /></fmt:formatNumber>
				</c:if>
				<c:if test="${memberProduct.actualBenefitLimit < -1.0}">      		
					<fmt:formatNumber><c:out value="${memberProduct.actualBenefitLimit}" /></fmt:formatNumber>
				</c:if>
			</c:if>
			<c:if test="${memberProduct.secondaryCoverageId ne null}">
		  		<c:if test="${memberProduct.secondaryCoverageId.actualBenefitLimit eq -1.0}">
	      			UNLIMITED
	      		</c:if>
	      		<c:if test="${memberProduct.secondaryCoverageId.actualBenefitLimit > -1.0}">      		
					<fmt:formatNumber><c:out value="${memberProduct.secondaryCoverageId.actualBenefitLimit}" /></fmt:formatNumber>
				</c:if>
				<c:if test="${memberProduct.secondaryCoverageId.actualBenefitLimit < -1.0}">      		
					<fmt:formatNumber><c:out value="${memberProduct.secondaryCoverageId.actualBenefitLimit}" /></fmt:formatNumber>
				</c:if>
			</c:if>
			
			<c:if test="${memberProduct.familyProductId ne null}">
		  		<c:if test="${memberProduct.familyProductId.actualBenefitLimit eq -1.0}">
	      			UNLIMITED
	      		</c:if>
	      		<c:if test="${memberProduct.familyProductId.actualBenefitLimit > -1.0}">      		
					<fmt:formatNumber><c:out value="${memberProduct.familyProductId.actualBenefitLimit}" /></fmt:formatNumber>
				</c:if>
				<c:if test="${memberProduct.familyProductId.actualBenefitLimit < -1.0}">      		
					<fmt:formatNumber><c:out value="${memberProduct.familyProductId.actualBenefitLimit}" /></fmt:formatNumber>
				</c:if>
			</c:if>
	  </td>
      <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="left" valign="top"  style="text-align: right;">
      		
			
			<c:if test="${memberProduct.secondaryCoverageId eq null and memberProduct.familyProductId eq null}">
		  		<c:if test="${memberProduct.annualBenefit eq -1.0}">
	      			UNLIMITED
	      		</c:if>
	      		<c:if test="${memberProduct.actualBenefitLimit > -1.0}">      		
					<fmt:formatNumber><c:out value="${memberProduct.annualBenefit}" /></fmt:formatNumber>
				</c:if>
				<c:if test="${memberProduct.actualBenefitLimit < -1.0}">      		
					<fmt:formatNumber><c:out value="${memberProduct.annualBenefit}" /></fmt:formatNumber>
				</c:if>
			</c:if>
			<c:if test="${memberProduct.secondaryCoverageId ne null}">
		  		<c:if test="${memberProduct.secondaryCoverageId.actualBenefitLimit eq -1.0}">
	      			UNLIMITED
	      		</c:if>
	      		<c:if test="${memberProduct.secondaryCoverageId.actualBenefitLimit > -1.0}">      		
					<fmt:formatNumber><c:out value="${memberProduct.secondaryCoverageId.annualBenefit}" /></fmt:formatNumber>
				</c:if>
				<c:if test="${memberProduct.secondaryCoverageId.actualBenefitLimit < -1.0}">      		
					<fmt:formatNumber><c:out value="${memberProduct.secondaryCoverageId.annualBenefit}" /></fmt:formatNumber>
				</c:if>
			</c:if>
			<c:if test="${memberProduct.familyProductId ne null}">
		  		<c:if test="${memberProduct.familyProductId.actualBenefitLimit eq -1.0}">
	      			UNLIMITED
	      		</c:if>
	      		<c:if test="${memberProduct.familyProductId.actualBenefitLimit > -1.0}">      		
					<fmt:formatNumber><c:out value="${memberProduct.familyProductId.annualBenefit}" /></fmt:formatNumber>
				</c:if>
				<c:if test="${memberProduct.familyProductId.actualBenefitLimit < -1.0}">      		
					<fmt:formatNumber><c:out value="${memberProduct.familyProductId.annualBenefit}" /></fmt:formatNumber>
				</c:if>
			</c:if>
	  </td>
	  <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="left" valign="top"  style="text-align: right;">
			<fmt:formatNumber><c:out value="${memberProduct.benefitUsagePercentage}" /></fmt:formatNumber> %
	  </td>
      <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="left" valign="top">
			<c:out value="${memberProduct.memberProductStatus.status}" />
	  </td>		   		   							   		   		

    </tr>
   
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	
	</c:forEach>
	
		<tr>
            <td class="listViewPaginationTdS1" align="left">&nbsp;</td>
            <td class="listViewPaginationTdS1" align="right" nowrap="nowrap" colspan=20></td>
          </tr>
	</tbody>
	</table>
</form>
<script language="javascript">
$(document).ready(function(){
	$.get("firstcall?navigation=jsontotalmemberelog&memberId=<c:out value="${member.memberId }"/>", function(data){
	  //alert("Data: " + data);
	  if(data>0){
	  	blinker();
	  }
	});
});

	//add by aju on 20150803, for linkedCard COB :D
	function popupMember(memberId){
		window.open ("member?navigation=detail&memberId="+memberId);
	}

	function blinker(){
		document.getElementById("errorLog").style.backgroundColor="red";
		setTimeout("document.getElementById('errorLog').style.backgroundColor=''", 500);
		setTimeout("blinker()",1500);
	}
	function hapus (){
	var delConfirm = window.confirm ("Are You Sure Want To Delete This Entry ?");
		if (delConfirm){
			document.form1.navigation.value = "delete";
			document.form1.submit();
		}
	}
	function kembali (){
		document.form1.navigation.value = "back";
		document.form1.submit();
	}
	function ubah (){
		document.form1.navigation.value = "update";
		document.form1.action = "member-form";
		document.form1.method = "GET";
		document.form1.submit();
	}
	function createGL (){
		document.form1.caseType.value = 2;
		document.form1.action = "case-form";
		document.form1.method = "GET";
		document.form1.submit();
	}
	function createGN (){
		document.form1.caseType.value = 1;
		document.form1.action = "case-form";
		document.form1.method = "GET";
		document.form1.submit();
	}
	function fc (mid){
		window.open ("firstcall-form?navigation=member&url=claim-form&memberId="+mid,"Search","width=1024, height=800, menubar=no, status=no, toolbar=no, scrollbars=yes, resizable=yes,screenX=200,screenY=100");
		
	}
	function upgradeMember(){
		var activateConfirm = window.confirm ("Are You Sure Want To Upgrade This Member ?");
		
		if (activateConfirm){
			document.form1.navigation.value = "preupgrade";
			document.form1.submit();
		}
	}
	function blockMember(){
		var activateConfirm = window.confirm ("Are You Sure Want To Block This Member ?");
		
		if (activateConfirm){
			document.form1.navigation.value = "preblock";
			document.form1.submit();
		}
	}
	function unblockMember(){
		var activateConfirm = window.confirm ("Are You Sure Want To unBlock This Member ?");
		
		if (activateConfirm){
			document.form1.navigation.value = "preunblock";
			document.form1.submit();
		}
	}
	function terminateMember(){
		var activateConfirm = window.confirm ("Are You Sure Want To Terminate This Member ?");
		
		if (activateConfirm){
			document.form1.navigation.value = "preterminate";
			document.form1.submit();
		}
	}
	function aktifasi(){
		var activateConfirm = window.confirm ("Are You Sure Want To Activate This Member ?");
		
		if (activateConfirm){
			document.form1.navigation.value = "activate";
			document.form1.submit();
		} 
	}
	
	function printErrorLog(){
		window.open ("firstcall?navigation=searchmembererrorlog&memberId=<c:out value="${member.memberId}" />","Search","width=1024, height=768, menubar=yes, status=no, toolbar=no, scrollbars=yes, resizable=yes");		
	}
	
	function adderrorlog (){ 
		window.location.href = "firstcall-form?navigation=memberelog&memberId=<c:out value="${member.memberId}" />";
	}
	
	function detilErrorLog (idx){
// 		document.form1.method = "GET";
// 		document.form1.callId.value = idx;
// 		document.form1.action = "firstcall";
// 		document.form1.navigation.value = "detail";
// 		document.form1.submit();
	window.location.href = "firstcall-form?navigation=updatememberelog&memberId=<c:out value="${member.memberId}" />&callId="+idx;
	}
</script>
