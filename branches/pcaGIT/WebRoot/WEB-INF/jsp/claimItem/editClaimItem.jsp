<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<%
String alert = (String) request.getAttribute("alert");
if (alert != null && !alert.trim().equals("")) {%>
	<div id="warning">
		<c:out value="${alert}"></c:out>
	</div>
	<%}%>

<form action="claimitem-form" method="POST"  name="form1" id="form_layout">
	<input type="hidden" name="index" value="0" >
		<input type="hidden" name="navigation" value="">
		
		


<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>
		



		<spring:bind path="claimItemForm.claimItemId">
			<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
		</spring:bind>


		

			<tr>
				<td class="dataLabel"> &nbsp; </td>				
			

		    <td class="dataField" colspan="3">&nbsp;</td>
	   
	</tr>
	

	<tr>
		<spring:bind path="claimItemForm.claimId">
		<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="11">		
		</spring:bind>			
		<td class="dataLabel">Claim : </td>		
		<td class="dataField">
		<spring:bind path="claimItemForm.claimNumber">
			<input type="text" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" size="35" readonly="readonly">
		</spring:bind>			
		</td>
	    <td class="dataLabel">Item : </td>		
		<td class="dataField">
		<spring:bind path="claimItemForm.itemId">
			<input type="hidden" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" id="itemId">
		</spring:bind>
		<spring:bind path="claimItemForm.itemName">
			<input type="text" size="35"  name="<c:out value="${status.expression}"/>" id="itemName" value="<c:out value="${status.value}" />" readonly="readonly">
		</spring:bind>	
		<input type="button" name="PILIH" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" value="Lookup" onClick="javascript:lookupItem('diagnosis3')">
		</td>
	</tr>
	<tr>
		<td class="dataLabel"> Item Value : </td>				
		<td class="dataField">
			<spring:bind path="claimItemForm.claimItemValue">
				<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="22">				
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
		<td class="dataLabel"> Item Amount : </td>				
		<td class="dataField">
			<spring:bind path="claimItemForm.claimItemAmount">
					<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="22">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>

	</tr>
	
	<tr>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
	<tr>
		<td class="dataLabel"> &nbsp;</td>				
		<td class="dataField"> &nbsp;
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>		
	<tr>
		<td class="dataLabel"> Approval Value : </td>				
		<td class="dataField">
			<spring:bind path="claimItemForm.claimItemApprovedValue">
					<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="22">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	    <td class="dataLabel">Excess Charges : </td>
	    <td class="dataField">
				<input type="text" size="35" name="excessCharge">	    
	    </td>
	</tr>

	<tr>
		<td class="dataLabel"> Item Check Remarks : </td>				
	    <td class="dataField" colspan="3">
			<spring:bind path="claimItemForm.claimItemDescription">
			<textarea cols="60" rows="8" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	   
	</tr>


			<tr>
				<td class="dataLabel"> &nbsp; </td>				
			

		    <td class="dataField" colspan="3">&nbsp;</td>
	   
	</tr>

	</tbody>
</table>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td style="padding-top: 2px;">
	    <input type="hidden" name="notyet" value="">
        <input title="Save [Alt+Shift+S]" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:simpan()" name="Simpan" value=" Simpan " type="submit">
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
		document.form1.action = "claimitem-form";
		document.form1.submit();
	}
	function cancel (){	
		document.form1.navigation.value = "detail";
		document.form1.action = "claimitem";
		document.form1.method = "GET";
		document.form1.submit();
	}
	function checkExcess (){
		var claimType = document.form1.claimType.value;
		
		if (claimType == 2){

			var claimItemValue = parseInt(document.form1.claimItemValue.value);
			
			var approvedItemValue = document.form1.claimItemApprovedValue.value;
			
			var excess = claimItemValue - approvedItemValue;
			
			document.form1.excessCharge.value = excess;
			
			
		}
	}
	function setItem(elem,itemId,itemCode,itemName){
		document.getElementById("itemId").value = itemId;
	//	document.getElementById(elem+"Code").value = itemCode;
		document.getElementById("itemName").value = itemName;
		
	}
	function lookupItem (itemEnum){
		window.open ("item?navigation=lookup&url=claimitem&parentElem="+itemEnum,"Search","width=700, height=550, menubar=no, status=no, toolbar=no, scrollbars=yes, resizable=yes,screenX=200,screenY=100");
	}
</script>