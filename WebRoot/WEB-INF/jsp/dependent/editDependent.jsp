



<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>




<form action="dependent-form" method="POST"  name="form1" id="form_layout">
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td style="padding-bottom: 2px;">
	    <input type="hidden" name="notyet" value="">
        <input title="Save [Alt+S]" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:simpan()" name="Save" value=" Save " type="submit">
        <input title="Cancel [Alt+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:cancel()" name="Cancel" value=" Cancel " type="submit">         
	  </td>
      <td align="right"></td>
    </tr>
  </tbody>
</table>

<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>
		<tr>
			<td>
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<tbody>


<%
String alert = (String) request.getAttribute("alert");
if (alert != null && !alert.trim().equals("")) {%>
	<div id="warning">
		<c:out value="${alert}"></c:out>
	</div>
	<%}%>


	<spring:bind path="dependentForm.dependentId">
		<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>

<input type="hidden" name="navigation" value="">

	<spring:bind path="dependentForm.memberId.memberId">
		<input type="hidden" name="memberId" value="<c:out value="${status.value}" />">
	</spring:bind>
	<tr>
		<td class="dataLabel">Member : </td>		
		<td class="dataField">
		<spring:bind path="dependentForm.memberId.customerNumber">
		<input type="text" name="member" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="11">
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>	

		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>





			<tr>
				<td class="dataLabel"> Dependant Name : </td>				
	

			<td class="dataField">
			<spring:bind path="dependentForm.dependantName">
		<input type="text" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />">			
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
	

		
				
		
		

			<tr>
				<td class="dataLabel"> Dependant Number : </td>				
			

			<td class="dataField">
			<spring:bind path="dependentForm.dependantNumber">
			<input type="text" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
	




			<tr>
				<td class="dataLabel"> Birthdate : </td>				
			

						<td class="dataField">
			<spring:bind path="dependentForm.birthdate">
					<input type="text" name="<c:out value="${status.expression}" />" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="10">
					<img src="images/jscalendar.gif" alt="Enter Date" id="<c:out value="${status.expression}"/>_trigger" align="absmiddle" height="20" width="20">
					<script type="text/javascript">
    					Calendar.setup({
        					inputField     :    "<c:out value="${status.expression}"/>",     // id of the input field
        					ifFormat       :    "%Y/%m/%d",      // format of the input field
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
		<td class="dataLabel">Status : </td>		
		<td class="dataField">
		<spring:bind path="dependentForm.status">
			<select name="<c:out value="${status.expression}" />">
				<c:forEach items="${statusbeans}" var="stat">
				<option value="<c:out value="${stat.statusId}" />" <c:if test="${status.value eq stat.statusId}">selected</c:if>><c:out value="${stat.status}" /></value>
				</c:forEach>
			</select>

			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>	

		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>

		

		
	<tr>
		<td class="dataLabel">Relationship : </td>		
		<td class="dataField">
		<spring:bind path="dependentForm.relationshipId">
			<select name="<c:out value="${status.expression}" />">
				<c:forEach items="${relationships}" var="rel">
				<option value="<c:out value="${rel.relationshipId}" />" <c:if test="${status.value eq rel.relationshipId}">selected</c:if>><c:out value="${rel.relationshipName}" /></value>
				</c:forEach>
			</select>

			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>	
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
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
        <input title="Save [Alt+S]" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:simpan()" name="Save" value=" Save " type="submit">
        <input title="Cancel [Alt+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:cancel()" name="Cancel" value=" Cancel " type="submit">         
	  </td>
      <td align="right"></td>
    </tr>
  </tbody>
</table>

</form>

<script language="javascript">
	function simpan (){

		document.form1.method = "POST";
		document.form1.action = "dependent-form";
		document.form1.submit();
	}
	function cancel (){
		document.form1.navigation.value = "detail";
		document.form1.action = "member";
		document.form1.submit();
	}
	function kembali (){
		document.form1.navigation.value = "back";
		document.form1.action = "dependent";
		document.form1.submit();
	}
	
</script>