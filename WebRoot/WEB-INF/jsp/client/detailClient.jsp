<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>

<form action="client" method="GET" name="form1" id="form_layout">

					<input type="hidden" name="navigation" value="">
					<input type="hidden" name="clientId" value="<c:out value="${client.clientId}" />">
					
			<table border="0" cellpadding="0" cellspacing="0" width="100%">
			  <tbody>
			    <tr>
			      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Detail Client</h3></td>
			      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
			    </tr>
			  </tbody>
			</table>

			<table border="0" cellpadding="0" cellspacing="0" width="100%">
				<tbody>
					<tr>
						<td style="padding-bottom: 2px;">
							<c:if test="${theUser.roleId.roleId eq 0}">
								<input title="Edit [Alt+Shift+E]" accesskey="E" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:ubah()" name="Edit" value=" Update " type="button">
								<input title="Hapus [Alt+Shift+H]" accesskey="H" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:hapus()" name="Delete" value=" Delete " type="button">
								
								
									<input title="Update Configuration [Alt+Shift+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:updateConfig(<c:out value="${client.clientId }" />)" name="conf" value=" Update Configuration " type="button">
									<input title="Update Risk [Alt+Shift+R]" accesskey="R" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:updateRiskConfig(<c:out value="${client.clientId }" />)" name="confRisk" value=" Risk Config " type="button">
									<input title="Update Number [Alt+Shift+N]" accesskey="N" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:updateNumberConfig(<c:out value="${client.clientId }" />)" name="confNumber" value=" Number Config " type="button">
									<input title="Activate [Alt+Shift+A]" accesskey="N" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:activate(<c:out value="${client.clientId }" />)" name="activateClient" value=" Activate " type="button">

								<c:if test="${client.statusId.statusId eq 1}">
									<input title="Add Floating Fund [Alt+Shift+A]" accesskey="N" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:addFund(<c:out value="${client.clientId }" />)" name="fund" value=" Top Up Fund " type="button">
								</c:if>
								<input title="Register Contract [Alt+Shift+A]" accesskey="N" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:addContract(<c:out value="${client.clientId }" />)" name="conract" value=" Register Contract " type="button">
							</c:if>
						
						</td>
						<td align="right"></td>
					</tr>
				</tbody>
			</table>
			
		</form>

<%@ include file="../mainContentClient.jsp" %>

<script language="javascript">
<c:if test="${theUser.roleId.roleId eq 0}">
	function hapus (){
	var delConfirm = window.confirm ("Are You Sure Want To Delete This Entry ?");
		if (delConfirm){
			document.form1.navigation.value = "delete";
			document.form1.submit();
		}
	}
	function activate (id){
		var delConfirm = window.confirm ("Are You Sure Want To Activate This Client ?");
		if (delConfirm){
			document.form1.clientId.value = id;
			document.form1.navigation.value = "activate";
			document.form1.submit();
		}
	}
	function addFund (id){
		var delConfirm = window.confirm ("Are You Sure Want To Top Fund For This Client ?");
		if (delConfirm){
			document.form1.clientId.value = id;
			document.form1.action = "fund-form";
			document.form1.navigation.value = "addclient";
			document.form1.submit();
		}
	}
	function addContract (id){
		var delConfirm = window.confirm ("Are You Sure Want To Register Contract For This Client ?");
		if (delConfirm){
			document.form1.clientId.value = id;
			document.form1.action = "clientcontract-form";
			document.form1.navigation.value = "register";
			document.form1.submit();
		}
	}
	function ubah (){
		document.form1.navigation.value = "update";
		document.form1.action = "client-form";
		document.form1.method = "GET";
		document.form1.submit();
	}
	function updateConfig(id){
		document.form1.clientId.value = id; 
		document.form1.navigation.value = "update-configuration";
		document.form1.action = "configuration-form";
		document.form1.method = "GET";
		document.form1.submit();
	}
	function updateRiskConfig(id){
		document.form1.navigation.value = "update-risk-configuration";
		document.form1.clientId.value = id; 
		document.form1.action = "riskconfig-form";
		document.form1.method = "GET";
		document.form1.submit();
	}
	function updateNumberConfig(id){
		document.form1.navigation.value = "update-number-configuration";
		document.form1.clientId.value = id; 
		document.form1.action = "numberconfig-form";
		document.form1.method = "GET";
		document.form1.submit();
	}
	</c:if>
	function kembali (){
		document.form1.navigation.value = "back";
		document.form1.submit();
	}
</script>
