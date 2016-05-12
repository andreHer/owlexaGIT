<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Detail Error Log</h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>

<form action="firstcall" method="GET" name="form1" id="form_layout">


	<input type="hidden" name="navigation" value="">
	<input type="hidden" name="callId" value="<c:out value="${firstCall.callId}" />">
	<input type="hidden" name="caseId" value="${param.caseId }">
	<input type="hidden" name="memberId" value="${param.memberId }">
	<input type="hidden" name="claimId" value="${param.claimId }">
		
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr> 
      <td style="padding-bottom: 2px;">   
      	<c:if test="${theUser.userType eq 2 and (theUser.roleId.roleId eq 0 or theUser.roleId.roleId eq 1 or theUser.roleId.roleId eq 2)}">
      		<c:if test="${firstCall.status.caseStatusId eq 1}">
        	<input title="Close Error Log [Alt+Shift+R]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:closeCall()" name="closeCa" value=" Close " type="button">
        	
        	<input title="Update [Alt+Shift+R]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:ubah()" name="updateCa" value=" Update " type="button">
        	</c:if>      	
        </c:if>
		</td>
      <td align="right"></td>
    </tr>
  </tbody>
</table>

<%@ include file="../mainContentFirstCall.jsp" %>

<br/>
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr> 
      <td style="padding-top: 2px;" align="center">        
       <c:if test="${theUser.roleId.roleId eq 0}">
		</c:if>
		<c:if test="${subnav eq 'member'}">
		<input title="Edit [Alt+Shift+E]" accesskey="E" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:tutup()" name="closeWindow" value=" Close " type="button">
		</c:if>
		</td>
      
    </tr>
  </tbody>
</table>

	
		
</form>
<script language="javascript">
	function closeCall (){
		var delConfirm = window.confirm ("Are You Sure Want To Close This First Call ?");
		if (delConfirm){
			document.form1.navigation.value = "close";
			document.form1.submit();
		}
	}
	function tutup(){
		window.close();
	}
	function kembali (){
		document.form1.navigation.value = "back";
		document.form1.submit();
	}
	function ubah (){
		<c:choose>
			<c:when test="${param.subnav eq 'caseelog'}"> 
				document.form1.navigation.value = "caseelog";
			</c:when>
			<c:when test="${param.subnav eq 'memberelog'}">
				document.form1.navigation.value = "memberelog";
			</c:when>
			<c:when test="${param.subnav eq 'claimelog' }">
				document.form1.navigation.value = "claimelog";
			</c:when>
			<c:when test="${param.subnav eq 'updatecaseelog'}"> 
				document.form1.navigation.value = "updatecaseelog";
			</c:when>
			<c:when test="${param.subnav eq 'updatememberelog'}">
				document.form1.navigation.value = "updatememberelog";
			</c:when>
			<c:when test="${param.subnav eq 'updateclaimelog' }">
				document.form1.navigation.value = "updateclaimelog";
			</c:when>
			<c:otherwise>
				document.form1.navigation.value = "update";
			</c:otherwise>
		</c:choose>
		document.form1.action = "firstcall-form";
		document.form1.method = "GET";
		document.form1.submit();
	}
</script>
