<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>


<form action="member" method="POST" name="form1" id="form_layout">
<input type="hidden" name="navigation" value="saveupgrade">
	
	<%String alert = (String) request.getAttribute("alert");
			if (alert != null && !alert.trim().equals("")) {%>
							<div id="warning">
								<c:out value="${alert}"></c:out>
							</div>
							<%}%>
							
		
	<input type="hidden" name="memberId" value="<c:out value="${member.memberId}" />">
		
	<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
		<tbody>
			<tr>
				<td>
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tbody>
							<tr>
								<td class="dataLabel"></td>
								<td class="dataField"></td>
								<td class="dataLabel"></td>
								<td class="dataField"></td>
							</tr>

							<tr>							
								<td class="dataLabel">Client :</td>
								<td class="dataField">
									<input type="text" readonly="readonly" id="clientName" name="clientName" value="<c:out value="${member.clientId.clientName}" />" />
								</td>							
								<td class="dataLabel">Member Group :</td>
								<td class="dataField">
									<input type="text" readonly="readonly" id="memberGroupName" name="memberGroupName" value="<c:out value="${member.memberGroupId.groupName}" />" />									
								</td>
							</tr>
							<tr>
								<td class="dataLabel">Member Name :</td>
								<td class="dataField">									
									<input type="text" size="35" name="memberName" value="<c:out value="${member.firstName}" />" />									
								</td>
								<td class="dataLabel">Birthday :</td>
								<td class="dataField">									
									<input type="text" size="25" name="birthday"  value="<c:out value="${member.birthday}" />" readonly="readonly">									
								</td>	
							</tr>
							<tr>
								<td class="dataLabel">Customer Number :</td>
								<td class="dataField">									
									<input type="text" size="35" name="customerNumber" value="<c:out value="${member.customerPolicyNumber}" />" />									
								</td>
								<td class="dataLabel"></td>
								<td class="dataField"></td>								
							</tr>
							<tr>								
								<td class="dataLabel">Insurance Product :</td>
								<td class="dataField">									
									<input type="text" size="35" name="currentProductCode" value="<c:out value="${customerProduct}" />" size="35"> <input type="button" name="PILIH" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" value="Lookup" onClick="javascript:lookupProduct()">										
								</td>
								<td class="dataLabel">Effective Date :</td>
								<td class="dataField">									
									<input type="text" size="25" name="effectiveDate" id="effectiveDate" value="<c:out value="${effectiveDate}" />" maxlength="30">
									<img src="images/jscalendar.gif" alt="Enter Date" id="effectiveDate_trigger" align="absmiddle" height="20" width="20">
									<script type="text/javascript">
				    					Calendar.setup({
				        					inputField     :    "effectiveDate",     // id of the input field
				        					ifFormat       :    "%Y-%m-%d",      // format of the input field
				        					button         :    "effectiveDate_trigger",  // trigger for the calendar (button ID)
				        					align          :    "Bl",           // alignment (defaults to "Bl")
				        					singleClick    :    true
				    					});
								 	</script>
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
				<td style="padding-top: 2px;">
					<input type="hidden" name="notyet" value="">
					<input title="Save [Alt+Shift+S]" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:simpan()" name="Save" value=" Save " type="submit">
					<input title="Cancel [Alt+Shift+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:kembali()" name="Cancel" value=" Cancel " type="button">
				</td>
				<td align="right"></td>
			</tr>
		</tbody>
	</table>

</form>

<script language="javascript">
	function simpan (){

		document.form1.method = "POST";
		document.form1.action = "member";
		document.form1.submit();
	}
	function kembali (){
		document.form1.navigation.value = "detail";
		document.form1.action = "member";
		document.form1.submit();
	}
	
	function lookupProduct(){
		window.open ("product?navigation=lookup&url=member-form","Search","width=700, height=550, menubar=no, status=no, toolbar=no, scrollbars=yes, resizable=yes,screenX=200,screenY=100");
	}
	function appendProduct (productCode){
		var currentProduct = document.form1.currentProductCode.value;		
		currentProduct += " " + productCode;		
		document.form1.currentProductCode.value = currentProduct;
	}
	
</script>
