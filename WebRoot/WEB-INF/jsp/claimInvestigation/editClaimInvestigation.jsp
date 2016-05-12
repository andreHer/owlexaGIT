
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<form action="claiminvestigation-form" method="POST"  name="form1" id="form_layout">
<input type="hidden" name="navigation" value="">



	<spring:bind path="claimInvestigationForm.claimInvestigationId">
		<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
	</spring:bind>

<%
String alert = (String) request.getAttribute("alert");
if (alert != null && !alert.trim().equals("")) {%>
	<div id="warning">
		<c:out value="${alert}"></c:out>
	</div>
	<%}%>
	
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Register Claim Investigation </h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>	

<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>
		<tr>
			<td class="dataLabel">&nbsp;</td>
			<td class="dataLabel">&nbsp;</td>					
			<td class="dataLabel">&nbsp;</td>
			<td class="dataLabel">&nbsp;</td>										
		</tr>
		<tr>
			<td class="dataLabel"> Claim: </td>
			<td class="dataField">
			<spring:bind path="claimInvestigationForm.claimId">
			<input type="hidden" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" >
			</spring:bind>
			<spring:bind path="claimInvestigationForm.claimNumber">
					<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" >
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
			<td class="dataLabel">Investigation Category : </td>
			<td class="dataField">
			<spring:bind path="claimInvestigationForm.investigationCategoryId">
				<select name="<c:out value="${status.expression}" />">
					<c:forEach items="${investigationCategories}" var="ic">
						<option value="<c:out value="${ic.investigationCategoryId}" />" 
						<c:if test="${ic.investigationCategoryId eq status.value}">selected</c:if>><c:out value="${ic.investigationCategoryName}" /></option>
					</c:forEach>
				</select>
				
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
	</tr>
		
		
		
		

			<tr>
				<td class="dataLabel"> Investigation Subject : </td>				
			

			<td class="dataField">
			<spring:bind path="claimInvestigationForm.investigationSubject">
			<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" >
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	    
	    <td class="dataLabel"> Head Doctor : </td>				
			

			<td class="dataField">
			<spring:bind path="claimInvestigationForm.headDoctor">
			<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" >
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	</tr>
	

			<tr>
				<td class="dataLabel"> Investigation Date : </td>				
			

						<td class="dataField">
			<spring:bind path="claimInvestigationForm.investigationDate">
					<input type="text"  name="<c:out value="${status.expression}" />" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="10">
					<img src="images/jscalendar.gif" alt="Enter Date" id="<c:out value="${status.expression}"/>_trigger" align="absmiddle" height="20" width="20">
					<script type="text/javascript">
    					Calendar.setup({
        					inputField     :    "<c:out value="${status.expression}"/>",     // id of the input field
        					ifFormat       :    "%Y-%m-%d",      // format of the input field
        					button         :    "<c:out value="${status.expression}"/>_trigger",  // trigger for the calendar (button ID)
        					align          :    "Bl",           // alignment (defaults to "Bl")
        					singleClick    :    true
    					});
				 	</script>
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
			<td class="dataLabel"> Consult Doctor : </td>				
			

			<td class="dataField">
			<spring:bind path="claimInvestigationForm.consultDoctor">
			<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" >
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	</tr>
			

			<tr>
			
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	    	<td class="dataLabel"> Nurse : </td>				
			

			<td class="dataField">
			<spring:bind path="claimInvestigationForm.nurse">
			<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" >
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	</tr>
	

			<tr>
				<td class="dataLabel"> Description : </td>				
			

		    <td class="dataField" colspan="3">
			<spring:bind path="claimInvestigationForm.description">
			<textarea cols="60" rows="8" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	
	</tr>
		

	
		
				
		
		
		<tr>
			<td class="dataLabel">&nbsp;</td>
			<td class="dataLabel">&nbsp;</td>					
			<td class="dataLabel">&nbsp;</td>
			<td class="dataLabel">&nbsp;</td>										
		</tr>
		
	</tbody>
</table>
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td style="padding-top: 2px;">
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
		document.form1.action = "claiminvestigation-form";
		document.form1.submit();
	}
	function cancel (){
		document.form1.navigation.value = "back";
		document.form1.action = "claiminvestigation";
		document.form1.submit();
	}
	// forreign affairs
			// forreign affairs end
</script>