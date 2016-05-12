<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Extend Policy</h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>
<br />

<form action="policy" method="GET" name="form1" id="form_layout">
	<input type="hidden" name="navigation" value="">
	<input type="hidden" name="policyId" value="<c:out value="${policy.policyId}" />">
	
	

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
						<a href="policy?navigation=search&searchtext=<c:out value="${searchtext}"/>&searchby=<c:out value="${searchby}" />&index=<c:out value="${index}"/>" class="tabDetailViewDFLink" style="font-weight: normal;">Return to List&nbsp;</a>					
					</td>
				</tr>
			</tbody>
		</table>
	  </td>
    </tr>

		
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Policy Number :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${policy.policyNumber}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Client :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><c:out value="${policy.clientId.clientName}" /></td>
	    </tr>
	
			
	
		
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Effective Date :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><fmt:formatDate value="${policy.effectiveDate}"/>   s/d   <fmt:formatDate value="${policy.expireDate}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Group Name :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><c:out value="${policy.memberGroupId.groupName}" /></td>
	    </tr>
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Request Date :&nbsp;</td>
		  
			 <td class="tabDetailViewDF" valign="top" width="33%"><fmt:formatDate value="${policy.requestDate}" /></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Status :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%">
	      
					<c:if test="${policy.policyStatus.statusId eq -1}">PENDING</c:if>
						<c:if test="${policy.policyStatus.statusId eq 1}">ACTIVE</c:if>
						<c:if test="${policy.policyStatus.statusId eq -3}">PENDING CHANGEPLAN</c:if>
						<c:if test="${policy.policyStatus.statusId eq -2}">BLOCKED</c:if>
						<c:if test="${policy.policyStatus.statusId eq 2}">TERMINATED</c:if>
						<c:if test="${policy.policyStatus.statusId eq 3}">RESIGNED</c:if>
						<c:if test="${policy.policyStatus.statusId eq 4}">INACTIVE</c:if>
						<c:if test="${policy.policyStatus.statusId eq 5}">INITIALIZED</c:if>
	      </td>
	    </tr>
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Expire Date :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><fmt:formatDate value="${policy.expireDate}"/> </td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Policy Type : </td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><c:if test="${policy.policyType eq 1}">INDEMNITY</c:if><c:if test="${policy.policyType eq 2}">MANAGED CARE </c:if></td>
	    </tr>
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Policy Date :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><fmt:formatDate value="${policy.policyDate}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Total Premium :</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%">&nbsp;</td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
	    
	    
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Request Date :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="33%" colspan="3">
	      		<input type="text" name="extendDate" id="extendDate" value="<c:out value="${extendDate}" />" maxlength="10">
					<img src="images/jscalendar.gif" alt="Enter Date" id="extendDate_trigger" align="absmiddle" height="20" width="20">
					<script type="text/javascript">
    					Calendar.setup({
        					inputField     :    "extendDate",     // id of the input field
        					ifFormat       :    "%Y-%m-%d",      // format of the input field
        					button         :    "extendDate_trigger",  // trigger for the calendar (button ID)
        					align          :    "Br",           // alignment (defaults to "Bl")
        					singleClick    :    true
    					});
				 	</script>
	      </td>	      
	    </tr>
	    <tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Expire Date :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="33%" colspan="3">
	      		<input type="text" name="expireDate" id="expireDate" value="<c:out value="${expireDate}" />" maxlength="10">
					<img src="images/jscalendar.gif" alt="Enter Date" id="expireDate_trigger" align="absmiddle" height="20" width="20">
					<script type="text/javascript">
    					Calendar.setup({
        					inputField     :    "expireDate",     // id of the input field
        					ifFormat       :    "%Y-%m-%d",      // format of the input field
        					button         :    "expireDate_trigger",  // trigger for the calendar (button ID)
        					align          :    "Br",           // alignment (defaults to "Bl")
        					singleClick    :    true
    					});
				 	</script>
	      </td>	      
	    </tr>
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Extend Notes :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="33%" colspan="3">
	      	
	      	<textarea rows="8" cols="60" name="notes" style="border: 1px #000 solid;"><c:out value="${notes}" /></textarea>
	      	    
	      </td>	      
	    </tr>
	
			

	
			
	    <tr>
      <td colspan="20"  style="padding: 0px;">
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tbody>
				<tr>
					<td style="text-align: left;" class="tabDetailViewDL">&nbsp;
						
					</td>
					<td class="tabDetailViewDL" align="right" nowrap="nowrap">
						<a href="policy?navigation=search&searchtext=<c:out value="${searchtext}"/>&searchby=<c:out value="${searchby}" />&index=<c:out value="${index}"/>" class="tabDetailViewDFLink" style="font-weight: normal;">Return to List&nbsp;</a>					
					</td>
				</tr>
			</tbody>
		</table>
	  </td>
    </tr>
		
	</tbody>
</table>
<br />
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr> 
      <td style="padding-bottom: 2px;">        
	        
			
			<input title="Terminate [Alt+T]" accesskey="H" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:extend()" name="renewBtn" value=" Extend Policy " type="button">
			
			<input title="Terminate [Alt+T]" accesskey="H" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:cancel()" name="cancelBtn" value=" Cancel " type="button">

	
		
		</td>
      <td align="right"></td>
    </tr>
  </tbody>
</table>
		
</form>
<script language="javascript">
	function extend (){
		var delConfirm = window.confirm ("Are You Sure Want To Extend This Policy Periode ?");
		if (delConfirm){
			document.form1.navigation.value = "extend";
			document.form1.submit();
		}
	}

	function kembali (){
		document.form1.navigation.value = "back";
		document.form1.submit();
	}
	function ubah (){
		document.form1.navigation.value = "update";
		document.form1.action = "policy-form";
		document.form1.method = "GET";
		document.form1.submit();
	}
	function quotation (){
		document.form1.navigation.value = "tambah";
		document.form1.action = "quotation-form";
		document.form1.method = "GET";
		document.form1.submit();
	}
	function downloadTC1(){
		document.form1.navigation.value = "downloadtc1";
		document.form1.action = "policy";
		document.form1.submit();
	}
	function downloadTC2(){
		document.form1.navigation.value = "downloadtc2";
		document.form1.action = "policy";
		document.form1.submit();
	}
	function downloadTC3(){
		document.form1.navigation.value = "downloadtc3";
		document.form1.action = "policy";
		document.form1.submit();
	}
	function addClausul (){
		document.form1.navigation.value = "tambah";
		document.form1.action = "policyclausul-form";
		document.form1.method = "GET";
		document.form1.submit();
	}
	function addBenefit (){
		document.form1.navigation.value = "tambah";
		document.form1.action = "policybenefit-form";
		document.form1.method = "GET";
		document.form1.submit();
	}
</script>
