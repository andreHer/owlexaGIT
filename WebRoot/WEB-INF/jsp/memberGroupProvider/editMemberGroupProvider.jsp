<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>

<%String alert = (String) request.getAttribute("alert");
if (alert != null && !alert.trim().equals("")) {%>
<div id="warning">
	<c:out value="${alert}"></c:out>
</div>
<%}%>



<form action="membergroupprovider-form" enctype="multipart/form-data" method="POST" name="form1" id="form_layout">	

	<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
		<tbody>


			<spring:bind path="memberGroupProviderForm.memberGroupProviderId">
				<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>

			<input type="hidden" name="navigation" value="">






			<tr>
				<td class="dataLabel">
					Member Group :
				</td>
				<td class="dataField">
					<spring:bind path="memberGroupProviderForm.memberGroupId">
						<input type="text" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="11">
						<div id="fredcaption">
							<c:out value="${status.errorMessage}" />
						</div>
					</spring:bind>
					<input type="button" name="PILIH" class="btn" value="pilih" onClick="javascript:lookupMemberGroup()">
				</td>
				<td class="dataLabel"></td>
				<td class="dataField"></td>
			</tr>


			<tr>
				<td class="dataLabel">
					Upload File :
				</td>
				<td class="dataField">
					<spring:bind path="memberGroupProviderForm.providerId">
						<input type="text" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="11">
						<div id="fredcaption">
							<c:out value="${status.errorMessage}" />
						</div>
					</spring:bind>
					<input type="button" name="PILIH" class="btn" value="pilih" onClick="javascript:lookupProvider()">
				</td>
				<td class="dataLabel"></td>
				<td class="dataField"></td>
			</tr>

		</tbody>
	</table>
	
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tbody>
			<tr>
				<td style="padding-bottom: 2px;">
					<input type="hidden" name="notyet" value="">
					<input title="Save [Alt+Shift+S]" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:simpan()" name="Save" value=" Save " type="submit">
					<input title="Cancel [Alt+Shift+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:cancel()" name="Cancel" value=" Cancel " type="button">
				</td>
				<td align="right"></td>
			</tr>
		</tbody>
	</table>

</form>

<script language="javascript">
	function simpan (){

		document.form1.method = "POST";
		document.form1.action = "membergroupprovider-form";
		document.form1.submit();
	}
	function kembali (){
		document.form1.navigation.value = "back";
		document.form1.action = "membergroupprovider";
		document.form1.submit();
	}
	// forreign affairs
		function lookupMemberGroup (){
		window.open ("membergroup?navigation=lookup&url=membergroupprovider-form","Search","width=700, height=550, menubar=no, status=no, toolbar=no, scrollbars=yes, resizable=yes,screenX=200,screenY=100");
	}
	function setMemberGroup (idx){
		document.form1.method = "POST";
		document.form1.notyet.value="true";
		document.form1.action = "membergroupprovider-form";
		//document.form1.memberGroupProviderForm.memberGroupId.value=idx;
		document.getElementById("memberGroupId").value = idx;
		document.form1.submit();
		}
			function lookupProvider (){
		window.open ("provider?navigation=lookup&url=membergroupprovider-form","Search","width=700, height=550, menubar=no, status=no, toolbar=no, scrollbars=yes, resizable=yes,screenX=200,screenY=100");
	}
	function setProvider (idx){
		document.form1.method = "POST";
		document.form1.notyet.value="true";
		document.form1.action = "membergroupprovider-form";
		//document.form1.memberGroupProviderForm.providerId.value=idx;
		document.getElementById("providerId").value = idx;
		document.form1.submit();
		}
				// forreign affairs end
</script>
