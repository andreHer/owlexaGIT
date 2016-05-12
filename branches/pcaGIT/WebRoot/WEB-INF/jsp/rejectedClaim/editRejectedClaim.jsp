<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<form action="rejectedclaim-form" method="POST"  name="form1" id="form_layout">


<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
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
<%
String alert = (String) request.getAttribute("alert");
if (alert != null && !alert.trim().equals("")) {%>
	<div id="warning">
		<c:out value="${alert}"></c:out>
	</div>
	<%}%>


	<spring:bind path="rejectedClaimForm.rejectedClaimId">
		<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>

<input type="hidden" name="navigation" value="">



		<tr>
			<td class="dataLabel"> Claim : </td>				
			
			<td class="dataField">
			<spring:bind path="rejectedClaimForm.claimId">
					<input type="hidden" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			<spring:bind path="rejectedClaimForm.claimNumber">
				<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />">
			</spring:bind>
			</td>
					    		<td class="dataLabel"> Rejection Category : </td>				
			

						<td class="dataField">
			<spring:bind path="rejectedClaimForm.rejectionCategory.rejectCategoryId">
				<select name="<c:out value="${status.expression}" />">
					<c:forEach items="${rejectCategory}" var="reject">
						<option value="<c:out value="${reject.rejectCategoryId}" />" <c:if test="${status.value eq reject.rejectCategoryId}" >selected</c:if> ><c:out value="${reject.rejectCategoryName}" /></option>
					</c:forEach>
				</select>
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
	</tr>
	


			<tr>
			<td class="dataLabel"> Rejection Subject : </td>				
			

			<td class="dataField">
			<spring:bind path="rejectedClaimForm.rejectionSubject">
			<input type="text" size="35" value="<c:out value="${status.value}" />" name="<c:out value="${status.expression}" />" >
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
			
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>



			

			


			

	

			

			<tr>
				<td class="dataLabel"> Description : </td>				
			

		    <td class="dataField" colspan="3">
			<spring:bind path="rejectedClaimForm.description">
			<textarea rows="8" cols="60" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
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
		document.form1.action = "rejectedclaim-form";
		document.form1.submit();
	}
	function cancel (){
		document.form1.navigation.value = "detail";
		document.form1.action = "claim";
		document.form1.submit();
	}
	// forreign affairs
			// forreign affairs end
</script>