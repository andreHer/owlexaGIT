<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<form action="claimitem-form" method="POST"  name="form1" id="form_layout">
	<spring:bind path="claimItemForm.claimItemId">
		<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>

<input type="hidden" name="navigation" value="">

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
			<td>
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<tbody>





		

	

	<tr>
		<spring:bind path="claimItemForm.claimId.claimId">
		<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="11">		
		</spring:bind>			
		<td class="dataLabel">Claim : </td>		
		<td class="dataField">
		<spring:bind path="claimItemForm.claimId.claimNumber">
		<input type="text" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" size="35">
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>			
		</td>
	    <td class="dataLabel">Item : </td>		
		<td class="dataField">
		<spring:bind path="claimItemForm.itemId.itemId">
			<input type="hidden" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" id="itemId">
		</spring:bind>
		<spring:bind path="claimItemForm.itemId.itemName">
		<input type="text" name="<c:out value="${status.expression}"/>" id="itemName" value="<c:out value="${status.value}" />">
				<input type="button" name="PILIH" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" value="Lookup" onClick="javascript:lookupItem()">
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>	

		</td>
	</tr>

			<tr>
				<td class="dataLabel"> Item Value : </td>				
			
			<td class="dataField">
			<spring:bind path="claimItemForm.claimItemValue">
					<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<fmt:formatNumber><c:out value="${status.value}" /></fmt:formatNumber>" maxlength="22">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
	    <td class="dataLabel">Measurement Unit : </td>		
		<td class="dataField">
		<spring:bind path="claimItemForm.measurementUnitId">
		<select name="<c:out value="${status.expression}"/>">
			<c:forEach items="${measurementUnit}" var="mu"> 
				<option value="<c:out value="${mu.measurementUnitId}" />" <c:if test="${status.value eq mu.measurementUnitId}">selected</c:if>><c:out value="${mu.measurementUnitName}" /></option>
			</c:forEach>
		</select>
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>	
		</td>
	</tr>



			

			<tr>
				<td class="dataLabel"> Item Amount : </td>				
			
			<td class="dataField">
			<spring:bind path="claimItemForm.claimItemAmount">
					<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<fmt:formatNumber><c:out value="${status.value}" /></fmt:formatNumber>" maxlength="22">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>




			<tr>
				<td class="dataLabel"> Claim Item Description : </td>				
			

		    <td class="dataField" colspan="3">
			<spring:bind path="claimItemForm.claimItemDescription">
			<textarea cols="60" rows="8" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	   
	</tr>

						</tbody>
				</table>
			</td>
		</tr>
	</tbody>
</table>

</form>

<script language="javascript">
	function simpan (){

		document.form1.method = "POST";
		document.form1.action = "claimitem-form";
		document.form1.submit();
	}
	function kembali (){
		document.form1.navigation.value = "back";
		document.form1.action = "claimitem";
		document.form1.submit();
	}
	// forreign affairs
	function lookupItem (){
		window.open ("item?navigation=lookup&url=claimitem-form","Search","width=700, height=550, menubar=no, status=no, toolbar=no, scrollbars=yes, resizable=yes,screenX=200,screenY=100");
	}
	function setItem (idx,nama){
	
		document.form1.notyet.value="true";
		document.form1.action = "claimitem-form";
		//document.form1.claimItemForm.itemId.value=idx;
		document.getElementById("itemId").value = idx;
		document.getElementById("itemName").value = nama;
		
	
	
	}
	
	
				// forreign affairs end
</script>