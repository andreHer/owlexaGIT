<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<form action="fund" method="GET" name="form1" id="form_layout">
<input type="hidden" name="fundId" value="<c:out value="${fund.fundId}" />" />

	<input type="hidden" name="navigation" value="">
	<input type="hidden" name="fundId" value="<c:out value="${fund.fundId}" />">
	
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr> 
      <td style="padding-bottom: 2px;">    
      <c:if test="${theUser.roleId.roleId eq 0 or theUser.roleId.roleId eq 7}">
      	<c:if test="${fund.fundStatus.paymentStatusId eq 5}" >    
        	<input title="Edit [Alt+Shift+E]" accesskey="E" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:ubah()" name="Edit" value=" Update " type="button">
        	<input title="Approve [Alt+Shift+A]" accesskey="A" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:approve()" name="Approval" value=" Approve " type="button">
        	<input title="Print Request Letter [Alt+Shift+R]" accesskey="A" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:printRequestLetter()" name="PrintRequest" value=" Print Request Letter " type="button">
        	
        </c:if>
        <c:if test="${fund.fundStatus.paymentStatusId eq 3}" >   
			<!--         	
        	<input title="Print Fund Letter [Alt+Shift+R]" accesskey="A" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:printApprovalLetter()" name="PrintApproval" value=" Print Approval Letter " type="button">
        	 -->
        </c:if>
        </c:if>
		</td>
      <td align="right"></td>
    </tr>
  </tbody>
</table>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Detail Fund</h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>

<%@ include file="../mainContentFund.jsp" %>

</form>
<script language="javascript">
	
	function kembali (){
		document.form1.navigation.value = "back";
		document.form1.submit();
	}
	function ubah (){
		document.form1.navigation.value = "update";
		document.form1.action = "fund-form";
		document.form1.method = "GET";
		document.form1.submit();
	}
	function printRequestLetter (){
		window.open ("fund?navigation=printrequest&url=detailExcessCharge&fundId=<c:out value="${fund.fundId}" />","Search","width=1024, height=768, menubar=yes, status=no, toolbar=no, scrollbars=yes, resizable=yes");
	}
	function printApprovalLetter (){
		window.open ("fund?navigation=printfund&url=detailExcessCharge&fundId=<c:out value="${fund.fundId}" />","Search","width=1024, height=768, menubar=yes, status=no, toolbar=no, scrollbars=yes, resizable=yes");
	}
	function approve (){
		var activateConfirm = window.confirm ("Are You Sure Want To Approve This Additional Floating Fund ?");
		
		if (activateConfirm){
		
			document.form1.navigation.value = "approve";
			document.form1.action = "fund";
			document.form1.method = "GET";
			document.form1.submit();
		}
	}
</script>
