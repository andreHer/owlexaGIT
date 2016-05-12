<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Detail Member Group</h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr> 
      <td style="padding-bottom: 2px;">        
        <c:if test="${theUser.roleId.roleId eq 0}">
	        <input title="Update [Alt+Shift+E]" accesskey="E" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:ubah()" name="Edit" value=" Update " type="button">
	        <input title="Delete [Alt+Shift+H]" accesskey="H" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:hapus()" name="Delete" value=" Delete " type="button">
	        
	        <!-- 
	        <input title="Activate All Member [Alt+Shift+A]" accesskey="A" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:activateAllMember()" name="ActivateAllM" value=" Activate All Member " type="button">
	         -->
	        
        </c:if>        		
			<c:if test="${memberGroup.status.statusId eq 1}" >
				<c:if test="${theUser.roleId.roleId eq 0}">
	        		<input title="Terminate [Alt+Shift+T]" accesskey="T" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:terminate()" name="termina" value=" Terminate " type="button">
	        		
	        		<input title="Block [Alt+Shift+B]" 		accesskey="T" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:blockGroup()" name="Block" value=" Block " type="button">
				</c:if>	        		
        	</c:if>
        	<c:if test="${memberGroup.status.statusId eq -2 }">
				<c:if test="${theUser.roleId.roleId eq 0}">
					<input title="UnBlock [Alt+Shift+B]" 		accesskey="T" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:unblockGroup()" name="UnBlock" value=" UnBlock " type="button">					
				</c:if>
			</c:if>
			<c:if test="${memberGroup.status.statusId eq -1}" >
        	<input title="Activate [Alt+Shift+A]" accesskey="A" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:aktifasi()" name="active" value=" Activate " type="button">        		        
        	</c:if>
        	<c:if test="${!(theUser.roleId.roleId eq 5 and theUser.userType eq 2) }">
        	<c:if test="${!(theUser.roleId.roleId eq 1 and theUser.userType eq 2) }">
  	        <c:if test="${theUser.roleId.roleId ne 6}">
        		<input title="Register Policy [Alt+Shift+A]" accesskey="A" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:registerPolicy()" name="regPolicy" value=" Register Policy " type="button">
        	</c:if>
        	</c:if>
        	</c:if>
        	
        	
        	<c:if test="${(memberGroup.latitude ne null )}">
			<input title="Show Map" accesskey="E" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:showMapLocation()" name="Map" value=" Show Map Location " type="button">
			</c:if>
		</td>
      <td align="right"></td>
    </tr>
  </tbody>
</table>

<%@ include file="../mainContentGroup.jsp" %>

<br />
<form action="membergroup" method="GET" name="form1" id="form_layout">
<input type="hidden" name="navigation" value="">
	<input type="hidden" name="memberGroupId" value="<c:out value="${memberGroup.memberGroupId}" />">
	
</form>
<script language="javascript">
	function printMember(){
	
		var memberGroupId = document.form1.memberGroupId.value;
		window.open ("membergroup?navigation=print&memberGroupId="+memberGroupId,"Search","width=1024, height=768, menubar=yes, status=no, toolbar=no, scrollbars=yes, resizable=yes");		
	}
	function showMapLocation (){
		window.open ("membergroup?navigation=showgroupmap&memberGroupId=<c:out value="${memberGroup.memberGroupId}" />&url=claim-form","Search","width=800, height=600, menubar=no, status=no, toolbar=no, scrollbars=yes, resizable=yes,screenX=200,screenY=100");
	}
	function registerPolicy(){
		
		document.form1.action = "policy-form";
		
		document.form1.navigation.value = "register";
		document.form1.submit();
		
	
	}
<c:if test="${theUser.roleId.roleId eq 0}">
	function hapus (){
		var delConfirm = window.confirm ("Are You Sure Want To Delete This Entry ?");
		if (delConfirm){
			document.form1.navigation.value = "delete";
			document.form1.submit();
		}
	}
	function activateAllMember (){
		var delConfirm = window.confirm ("Are You Sure Want To Activate All Member ?");
		if (delConfirm){
			document.form1.action = "member";
			document.form1.navigation.value = "activateall";
			document.form1.submit();
		}
	}
		function blockGroup(){
		var activateConfirm = window.confirm ("Are You Sure Want To Block This Group ?");
		
		if (activateConfirm){
			document.form1.navigation.value = "preblock";
			document.form1.submit();
		}
	}
	function unblockGroup(){
		var activateConfirm = window.confirm ("Are You Sure Want To unBlock This Group ?");
		
		if (activateConfirm){
			document.form1.navigation.value = "preunblock";
			document.form1.submit();
		}
	}
	
	</c:if>
	function kembali (){
		document.form1.navigation.value = "back";
		document.form1.submit();
	}
	<c:if test="${theUser.roleId.roleId eq 0}">
	function ubah (){
		document.form1.navigation.value = "update";
		document.form1.action = "membergroup-form";
		document.form1.method = "GET";
		document.form1.submit();
	}
	</c:if>
	<c:if test="${memberGroup.status.statusId eq 1}" >
	<c:if test="${theUser.roleId.roleId eq 0}">
	function terminate (){
		var delConfirm = window.confirm ("Are You Sure Want To Terminate This Entry ?");
		if (delConfirm){

			document.form1.navigation.value = "terminate";
			document.form1.submit();
		}
	}
	</c:if>
	</c:if>
	<c:if test="${memberGroup.status.statusId eq -1}" >
	<c:if test="${theUser.roleId.roleId eq 0}">
	function aktifasi (){
		var delConfirm = window.confirm ("Are You Sure Want To Activate This Entry ?");
		if (delConfirm){
			document.form1.navigation.value = "activate";
			document.form1.submit();
		}	
	}
	</c:if>
	</c:if>
</script>
