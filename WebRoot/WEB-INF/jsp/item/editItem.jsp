<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<form action="item-form" method="POST"  name="form1" id="form_layout">

	<spring:bind path="itemForm.itemId">
		<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
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
				<td class="dataLabel"> Item Name : </td>				
			

			<td class="dataField">
			<spring:bind path="itemForm.itemName">
			<input type="text" size="35" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" />		
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
		<td class="dataLabel">Item Category : </td>		
		<td class="dataField">
		<spring:bind path="itemForm.itemCategoryId.itemCategoryId">
			<input type="hidden" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" id="itemCategoryId" >
		</spring:bind>
		<spring:bind path="itemForm.itemCategoryId.itemCategoryName">
		<input type="text" name="<c:out value="${status.expression}"/>" id="itemCategoryName" value="<c:out value="${status.value}" />" ><input type="button" name="PILIH" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" value="Lookup" onClick="javascript:lookupItemCategory()">
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>	
		</td>
	</tr>
	


			

			<tr>
				<td class="dataLabel"> Item Code : </td>				
			

			<td class="dataField">
			<spring:bind path="itemForm.itemCode">
<input type="text" size="35" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" />

				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	    	<td class="dataLabel"> Item Default Value : </td>				
			
			<td class="dataField">
			<spring:bind path="itemForm.itemDefaultValue">
					<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<fmt:formatNumber><c:out value="${status.value}" /></fmt:formatNumber>" maxlength="22">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
	</tr>
	

			<tr>
				<td class="dataLabel"> Item EDC Code : </td>				
			

			<td class="dataField">
			<spring:bind path="itemForm.itemEDCCode">
<input type="text" size="35" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" />

				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	    	<td class="dataLabel"> Item EDC Name : </td>				
			
			<td class="dataField">
			<spring:bind path="itemForm.itemEDCName">
					<input type="text" size="25"  name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="20">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
	</tr>
			<tr>
				<td class="dataLabel"> Item Alternate Code : </td>				
			

			<td class="dataField">
			<spring:bind path="itemForm.itemAlternateCode">
<input type="text" size="35" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" />

				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	    	<td class="dataLabel"> Member Movement Type : </td>				
			
			<td class="dataField">
				<spring:bind path="itemForm.memberMovementType">
					<select name="<c:out value="${status.expression}" />">
						<option value=""> -- SELECT IF CONTRACT ITEM -- </option>
						<option value="LOSTCARD" <c:if test="${status.value eq 'LOSTCARD'}">selected</c:if>>MEMBER LOST CARD</option>
						<option value="REPRINT" <c:if test="${status.value eq 'REPRINT'}">selected</c:if>>MEMBER REPRINT</option>
						<option value="CHANGEPLAN" <c:if test="${status.value eq 'CHANGEPLAN'}">selected</c:if>>MEMBER CHANGE PLAN</option>
					</select>

				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
	</tr>
	
	
			

		

	
		<tr>
				<td class="dataLabel"> Item Description : </td>				
			

			<td class="dataField" colspan="3">
			<spring:bind path="itemForm.itemDescription">

	<textarea  cols="60" rows="8" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>			
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
			</td>
		</tr>
	</tbody>
</table>
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td style="padding-top: 2px;">
	    <input type="hidden" name="notyet" value="">
        <input title="Save & Add [Alt+Shift+A]" accesskey="A" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:simpanAndAdd()" name="SaveAndAdd" value=" Save & Add " type="button">
        <input title="Save [Alt+Shift+S]" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:simpan()" name="Save" value=" Save " type="button">
        <input title="Cancel [Alt+Shift+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:cancel()" name="Cancel" value=" Cancel " type="button">         
	  </td>
      <td align="right"></td>
    </tr>
  </tbody>
</table>

</form>

<script language="javascript">
	function simpanAndAdd(){

		document.form1.method = "POST";
		document.form1.action = "item-form";
		document.form1.navigation.value = "simpanadd";
		document.form1.submit();
	
	}
	function simpan (){

		document.form1.method = "POST";
		document.form1.action = "item-form";
		document.form1.submit();
	}
	function cancel (){
		document.form1.navigation.value = "back";
		document.form1.action = "item";
		document.form1.submit();
	}
	// forreign affairs
		function lookupItemCategory (){
		window.open ("itemcategory?navigation=lookup&url=item-form","Search","width=700, height=550, menubar=no, status=no, toolbar=no, scrollbars=yes, resizable=yes,screenX=200,screenY=100");
	}
	function setItemCategory (idx,nama){
		
		document.form1.notyet.value="true";
		document.form1.action = "item-form";
		//document.form1.itemForm.itemCategoryId.value=idx;
		document.getElementById("itemCategoryId").value = idx;
		document.getElementById("itemCategoryName").value = nama;
		
		
	}
				// forreign affairs end
</script>