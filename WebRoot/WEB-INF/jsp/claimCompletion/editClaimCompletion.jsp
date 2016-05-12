



<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<form action="claimcompletion-form" method="POST"  name="form1" id="form_layout">

	<spring:bind path="claimCompletionForm.completeClaimId">
		<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
		</spring:bind>
		<spring:bind path="claimCompletionForm.claimId">
			<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
		</spring:bind>

<input type="hidden" name="navigation" value="">



<%
String alert = (String) request.getAttribute("alert");
if (alert != null && !alert.trim().equals("")) {%>
	<div id="warning">
		<c:out value="${alert}"></c:out>
	</div>
	<%}%>

<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>




							<tr>
								<td class="dataLabel"></td>
								<td class="dataLabel"></td>
								<td class="dataField"></td>
								<td class="dataLabel"></td>
							</tr>
							<tr>
								<td class="dataLabel">Pending Claim : </td>		
								<td class="dataField">
								<spring:bind path="claimCompletionForm.claimNumber">
								<input readonly type="text" size=35 name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="12">
									<div id="fredcaption">
										<c:out value="${status.errorMessage}" />
									</div>
								</spring:bind>	
								</td>
							    <td class="dataLabel"></td>
							    <td class="dataField"></td>
							</tr>
							<tr>
								<td class="dataLabel"> Complete Date : </td>
								<td class="dataField">
									<spring:bind path="claimCompletionForm.completeDate">
											<input type="text" name="<c:out value="${status.expression}" />" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="10">
											<img src="images/jscalendar.gif" alt="Enter Date" id="<c:out value="${status.expression}"/>_trigger" align="absmiddle" height="20" width="20">
											<script type="text/javascript">
						    					Calendar.setup({
						        					inputField     :    "<c:out value="${status.expression}"/>",     // id of the input field
						        					ifFormat       :    "%Y-%m-%d",      // format of the input field
						        					button         :    "<c:out value="${status.expression}"/>_trigger",  // trigger for the calendar (button ID)
						        					align          :    "Tl",           // alignment (defaults to "Bl")
						        					singleClick    :    true
						    					});
										 	</script>
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
									</td>
							    <td class="dataLabel"></td>
							    <td class="dataField"></td>
							</tr>
			
							<tr>
								<td class="dataLabel"> Completion Remarks : </td>				
							
					
							    <td class="dataField">
									<spring:bind path="claimCompletionForm.completionRemarks">
									<textarea rows="8" cols="60" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
										<div id="fredcaption">
											<c:out value="${status.errorMessage}" />
										</div>
									</spring:bind>
								</td>
							    <td class="dataLabel"></td>
							    <td class="dataField"></td>
							</tr>
		
							<tr>
								<td class="dataLabel">&nbsp; </td>
						    	<td class="dataField">&nbsp;</td>
							    <td class="dataLabel"></td>
							    <td class="dataField"></td>
							</tr>
			
		</tbody>
</table>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td style="padding-top: 2px;">
	    <input type="hidden" name="notyet" value="">
        <input title="Save [Alt+S]" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:simpan()" name="Save" value=" Save " type="submit">
        <input title="Cancel [Alt+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:cancel()" name="Cancel" value=" Cancel " type="button">         
	  </td>
      <td align="right"></td>
    </tr>
  </tbody>
</table>


</form>

<script language="javascript">
	function simpan (){

		document.form1.method = "POST";
		document.form1.action = "claimcompletion-form";
		document.form1.submit();
	}
	function cancel (){
		document.form1.navigation.value = "detail";
		document.form1.action = "claim";
		document.form1.submit();
	}
</script>