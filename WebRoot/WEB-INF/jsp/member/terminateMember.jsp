<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>


<form action="member" method="POST" name="form1" id="form_layout" onSubmit="return simpan()">
<input type="hidden" name="navigation" value="">
<input type="hidden" name="memberId" value="<c:out value="${member.memberId}" />" />
	
	<%String alert = (String) request.getAttribute("alert");
			if (alert != null && !alert.trim().equals("")) {%>
							<div id="warning">
								<c:out value="${alert}"></c:out>
							</div>
							<%}%>
							
			<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />">
			
	<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
		<tbody>
			<tr>
				<td>
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tbody>


						<tr>
								<td class="dataLabel">
									
								</td>


								<td class="dataField">
									
								</td>
								<td class="dataLabel">
								
								</td>
								<td class="dataField">
									
									
								</td>
							</tr>


							

							





							<tr>
								<td class="dataLabel">
									Member Name :
								</td>
								<td class="dataField">
									
										<input type="text" size="35" name="memberName" value="<c:out value="${member.firstName}"/>"  readonly="readonly">
									
								</td>
								
								<td class="dataLabel">
									Customer Number :
								</td>
								<td class="dataField">
									
										<input type="text" size="35" name="customerPolicyNumber" value="<c:out value="${member.customerPolicyNumber}"/>"  readonly="readonly"/>
										
								</td>
							
							
								
							</tr>






							<tr>
							
								
								
								
								
								<td class="dataLabel">
									Join Date :
								</td>
								<td class="dataField">
									
										<input type="text" size="25" name="joinDate" id="idJoinDate" value="<c:out value="${member.joinDate}" />" maxlength="30"  readonly="readonly">
										
								</td>
								<td class="dataLabel">								
									Expire Date :
								</td>
								<td class="dataField">
									
										<input type="text" size="25" name="expDate" id="idExpDate" value="<c:out value="${member.expireDate}" />" maxlength="30"  readonly="readonly">
										
								</td>
								
							</tr>





							
							<tr>
		
								<td class="dataLabel">
									Effective Date :
								</td>
								<td class="dataField">
									
										<input type="text" size="25" name="effectiveDate" id="effectiveDate" value="<c:out value="${member.effectiveDate}" />" maxlength="30"  readonly="readonly">
										
								</td>
								<td class="dataLabel">
									Resignation Date :
								</td>
								<td class="dataField">
									
										<input type="text" size="25" name="terminationDate" id="terminationDate" value="" maxlength="30"  >
										<img src="images/jscalendar.gif" alt="Enter Date" id="termination_trigger" align="absmiddle" height="20" width="20">
										<script type="text/javascript">
					    					Calendar.setup({
					        					inputField     :    "terminationDate",     // id of the input field
					        					ifFormat       :    "%Y-%m-%d",      // format of the input field
					        					button         :    "termination_trigger",  // trigger for the calendar (button ID)
					        					align          :    "Tl",           // alignment (defaults to "Bl")
					        					singleClick    :    true
					    					});
									 	</script>
								</td>
						
							</tr>

							<tr>
								<td class="dataLabel">
									
								</td>


								<td class="dataField">
									
								</td>
								<td class="dataLabel">
								
								</td>
								<td class="dataField">
									
									
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
					<input id="doTerminate" title="Save [Alt+Shift+S]" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:simpan()" name="Save" value=" Save " type="submit">
					<input title="Cancel [Alt+Shift+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:kembali()" name="Cancel" value=" Cancel " type="button">
				</td>
				<td align="right"></td>
			</tr>
		</tbody>
	</table>

</form>

<script language="javascript">
	$(document).ready(function(){
	
	//alert('jQuery ready');
	$("#doTerminate").click(function(){
		//alert($("#terminationDate").val());
		/* 
		var terminateDate = $("#terminationDate").val();
		if(terminateDate==null || terminateDate==""){
			e.preventDefault();
			alert("woohoo");
		}
		 */
	});
	
	});

	function simpan (){
	
		var xxx = document.getElementById('terminationDate').value;
		//alert(xxx);
		if(xxx==null || xxx==""){
			alert("Please pick Resignation Date...");
			return false;
		}
		else{
			//alert("Terminate the member...");
			document.form1.method = "POST";
			document.form1.navigation.value = "terminate";
			document.form1.action = "member";
			document.form1.submit();
			return true;
		}
	
		
		//document.form1.method = "POST";
		//document.form1.navigation.value = "terminate";
		//document.form1.action = "member";
		//document.form1.submit();
		
	}
	function kembali (){
		document.form1.navigation.value = "detail";
		document.form1.action = "member";
		document.form1.submit();
	}
	
				// forreign affairs end
</script>
