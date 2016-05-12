<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<!-- Andre
Alert fail deleted -->
<%
   String sAction = (String) request.getAttribute("fail");
   if ( sAction != null && (sAction).equals("y")) { %>
   <script> window.alert("This Provider has being used");</script>
<% } %>
<!-- Alert Fail deleted -->
<%
String alert = (String) request.getAttribute("alert");
// String sAction = (String) request.getAttribute("blowup");
if (alert != null) { %>
<script> alert("gagal");</script>
<% }
int totalIndex = 0;
int count = 0;
int countSet = 0;

try {

	count = ((Integer) request.getAttribute ("count")).intValue();
	countSet = ((Integer) request.getAttribute ("countSet")).intValue();
	totalIndex = ((Integer) request.getAttribute ("halAkhir")).intValue();
	
	
	
}
catch (Exception e){
}
String rowclass = "";
int i=0;


%>
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Detail Provider</h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr> 
      <td style="padding-bottom: 2px; padding-top: 5px;">
      <c:if test="${theUser.roleId.roleId eq 0 or theUser.roleId.roleId eq 6}">        
	        <input title="Edit [Alt+Shift+E]" accesskey="E" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:ubah()" name="Edit" value=" Update " type="button">
	        <input title="Delete [Alt+Shift+D]" accesskey="D" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:hapus()" name="Delete" value=" Delete " type="button">
			  <c:if test="${provider.statusId.statusId eq 1}">	                 		
	    	    <input title="Terminate [Alt+Shift+T]" accesskey="T" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:terminateProvider()" name="Terminate" value=" Terminate " type="button">
	    	    
	    	    <input title="Block [Alt+Shift+B]" accesskey="T" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:blockProvider()" name="Block" value=" Block " type="button">        		        
	    	  </c:if>
	    	  <c:if test="${provider.statusId.statusId eq -2}">	                 		
	    	    <input title="UnBlock [Alt+Shift+B]" accesskey="T" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:unblockProvider()" name="UnBlock" value=" UnBlock " type="button">        		        
	    	  </c:if>
			  <c:if test="${provider.statusId.statusId eq 2}">	                 
	        <input title="Activate [Alt+Shift+R]" accesskey="R" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:activateProvider()" name="Active" value=" Activate " type="button">        		        
	          </c:if>
			<c:if test="${(provider.latitude ne null )}">
				<input title="Show Map" accesskey="E" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:showMapLocation()" name="Map" value=" Show Map Location " type="button">
			</c:if>
			
		 </c:if>
		 <c:if test="${theUser.roleId.roleId eq 0 or theUser.roleId.roleId eq 7 or theUser.roleId.roleId eq 8}">  
		<c:if test="${(provider.isUsingCapitation ne null and provider.isUsingCapitation eq 1 )}">
			<input title="Add Fund" accesskey="E" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:addFund()" name="adFund" value=" Add Fund " type="button">
		</c:if>
		</c:if>
		<c:if test="${theUser.roleId.roleId eq 5}">
			<c:if test="${(provider.latitude ne null )}">
				<input title="Show Map" accesskey="E" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:showMapLocation()" name="Map" value=" Show Map Location " type="button">
			</c:if>
		</c:if>
		</td>
      <td align="right"></td>
    </tr>
  </tbody>
</table>


<form action="provider" method="GET" name="form1" id="form_layout">
<input type="hidden" name="navigation" value="">
	<input type="hidden" name="providerId" value="<c:out value="${provider.providerId}" />">
	<input type="hidden" name="providerItemId" value="">
	<input type="hidden" name="caseId" value="">
	<input type="hidden" name="batchClaimId" value="">
	
<%@ include file="../mainContentProvider.jsp" %>

<br />

</form>
<script language="javascript">
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
		document.form1.action = "provider-form";
		document.form1.method = "GET";
		document.form1.submit();
	}
	function addTerminal (){
		document.form1.navigation.value = "update";
		document.form1.action = "edcterminal-form";
		document.form1.method = "GET";
		document.form1.submit();
	}
	function addFund (){
		document.form1.navigation.value = "addprovider";
		document.form1.action = "fund-form";
		document.form1.method = "GET";
		document.form1.submit();
	}
	function hapusRoom(id){
		var delConfirm = window.confirm ("Are You Sure Want To Delete This Entry ?");
		
		if (delConfirm){
			document.form1.action = "provideritem";
			document.form1.itemId.value = id;
			document.form1.navigation.value = "delete";
			document.form1.submit();
		}
	}
	function ubahRoom (id){
	
	}
	function detilRoom (id){
	
	}
	function showMapLocation (){
		window.open ("provider?navigation=showprovidermap&providerId=<c:out value="${provider.providerId}" />&url=claim-form","Search","width=800, height=600, menubar=no, status=no, toolbar=no, scrollbars=yes, resizable=yes,screenX=200,screenY=100");
	}
	function terminateProvider (){
		var delConfirm = window.confirm ("Are You Sure Want To Terminate This Provider ?");
		
		if(delConfirm){
			document.form1.navigation.value = "terminate";
			document.form1.action = "provider";
			document.form1.method = "GET";
			document.form1.submit();
		}
	}
	function blockProvider (){
		var delConfirm = window.confirm ("Are You Sure Want To Block This Provider ?");
		
		if(delConfirm){
			document.form1.navigation.value = "block";
			document.form1.action = "provider";
			document.form1.method = "GET";
			document.form1.submit();
		}
	}
	function unblockProvider (){
		var delConfirm = window.confirm ("Are You Sure Want To Unblock This Provider ?");
		
		if(delConfirm){
			document.form1.navigation.value = "unblock";
			document.form1.action = "provider";
			document.form1.method = "GET";
			document.form1.submit();
		}
	}
	function activateProvider (){
		var delConfirm = window.confirm ("Are You Sure Want To Activate This Provider ?");
		
		if(delConfirm){
			document.form1.navigation.value = "activate";
			document.form1.action = "provider";
			document.form1.method = "GET";
			document.form1.submit();
		}
	}		
	function renewProvider (){
		var delConfirm = window.confirm ("Are You Sure Want To Renew This Provider ?");
		
		if(delConfirm){
			document.form1.navigation.value = "renew";
			document.form1.action = "provider";
			document.form1.method = "GET";
			document.form1.submit();
		}
	}		
</script>
