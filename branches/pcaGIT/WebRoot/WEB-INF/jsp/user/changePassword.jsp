<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>


<form action="user" method="POST" name="form1" id="form_layout">

<%String alert = (String) request.getAttribute("alert");
			if (alert != null && !alert.trim().equals("")) {%>
							<div id="warning" style="text-align: center; color: #FFF; font-size: 13px; font-weight: bold;">
								<c:out value="${alert}"></c:out>
							</div>
							<%}%>
							<br />
							<br />
	

							
	<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
		<tbody>
			<tr>
				<td>
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tbody>



							<input type="hidden" name="userId" value="<c:out value="${userId}" />">
			<tr>
				<td class="dataLabel"> </td>				
				<td class="dataField" colspan="3">
				</td>			    
			</tr>

							<input type="hidden" name="navigation" value="<c:out value="${navigation}" />">
							<tr>
								<td class="dataLabel">
									Old Password :
								</td>


								<td class="dataField">
									<input type="password" size="35" name="oldPassword" value="" />
									
								</td>
								<td class="dataLabel"></td>
								<td class="dataField"></td>
							</tr>



							<tr>
								<td class="dataLabel">
									Password :
								</td>


								<td class="dataField">
									<input type="password" size="35" name="password" value="" />
								</td>
								<td class="dataLabel"></td>
								<td class="dataField"></td>
							</tr>


							<tr>
								<td class="dataLabel">
									Confirm Password :
								</td>


								<td class="dataField">
									<input type="password" size="35" name="confirmPassword" value="" />

								</td>
								<td class="dataLabel">
								</td>


								<td class="dataField">
								</td>
							</tr>
										<tr>
				<td class="dataLabel"> </td>				
				<td class="dataField" colspan="3">
				</td>			    
			</tr>
						</tbody>
					</table>
				</td>
			</tr>
		</tbody>
	</table>

	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tbody>
			<tr>
				<td style="padding-top: 2px; padding-left: 1px;">
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
		document.form1.action = "user";
		document.form1.navigation.value = "savemypass";
		document.form1.submit();
	}
	function kembali (){
		document.form1.navigation.value = "back";
		document.form1.action = "user";
		document.form1.submit();
	}
	function cancel (){
		document.form1.navigation.value = "cancel";
		document.form1.action = "user";
		document.form1.submit();

	}

</script>
