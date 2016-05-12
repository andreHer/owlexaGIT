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

<form action="providerprocedure-form" method="POST"  name="form1" id="form_layout">
	<input type="hidden" name="navigation" value="">

	<spring:bind path="providerProcedureForm.providerProcedureId">
		<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >	
	</spring:bind>
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


			<tr>
				<td class="dataLabel"> Provider : </td>				
				<td class="dataField">
					<spring:bind path="providerProcedureForm.providerId">
							<input type="hidden" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" maxlength="11">
						
					</spring:bind>
					<spring:bind path="providerProcedureForm.providerName">
						<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>">
						<div id="fredcaption">
							<c:out value="${status.errorMessage}" />
						</div>
					</spring:bind>
				</td>
			    <td class="dataLabel"></td>
			    <td class="dataField"></td>
			</tr>
			

		
				
		
		

			<tr>
				<td class="dataLabel"> Procedure : </td>				
			

						<td class="dataField">
			<spring:bind path="providerProcedureForm.procedureId">
				<input type="hidden" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" maxlength="11">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			<spring:bind path="providerProcedureForm.procedureName">
				<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
			

		
				
		
		

			<tr>
				<td class="dataLabel"> Average Length Of Stay : </td>				
			

						<td class="dataField">
			<spring:bind path="providerProcedureForm.averageLengthOfStay">
	<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" maxlength="11">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
			

		
				
		
		

			<tr>
				<td class="dataLabel"> Rawat Jalan : </td>				
			
			<td class="dataField">
			<spring:bind path="providerProcedureForm.rj">
					<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="22">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>

	
			<tr>
				<td class="dataLabel"> Kelas 1 : </td>				
			
			<td class="dataField">
			<spring:bind path="providerProcedureForm.c1">
					<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="22">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
	
	
			<tr>
				<td class="dataLabel"> Kelas 2 : </td>				
			
			<td class="dataField">
			<spring:bind path="providerProcedureForm.c2">
					<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="22">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
	
	
			<tr>
				<td class="dataLabel"> Kelas 3 : </td>				
			
			<td class="dataField">
			<spring:bind path="providerProcedureForm.c3">
					<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="22">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
	
	
			<tr>
				<td class="dataLabel"> VIP : </td>				
			
			<td class="dataField">
			<spring:bind path="providerProcedureForm.vip">
					<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="22">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
	
	
			<tr>
				<td class="dataLabel"> Super VIP : </td>				
			
			<td class="dataField">
			<spring:bind path="providerProcedureForm.svip">
					<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="22">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
	
	
			<tr>
				<td class="dataLabel"> Rawat Jalan : </td>				
			
			<td class="dataField">
			<spring:bind path="providerProcedureForm.rj">
					<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="22">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
	
		
				
		
		

			<tr>
				<td class="dataLabel"> Version Date : </td>				
			

						<td class="dataField">
			<spring:bind path="providerProcedureForm.versionDate">
					<input type="text" size="35" name="<c:out value="${status.expression}" />" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="10">
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
				<td class="dataLabel"> Procedure Class : </td>				
			

						<td class="dataField">
			<spring:bind path="providerProcedureForm.procedureClass">
	<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" maxlength="11">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
			

		
				
		

			<tr>
				<td class="dataLabel">
					&nbsp;
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
        <input title="Save [Alt+Shift+E]" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:simpan()" name="Save" value=" Simpan " type="button">
        <input title="Cancel [Alt+Shift+H]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:kembali()" name="Cancel" value=" Cancel " type="button">        		
		</td>
      <td align="right"></td>
    </tr>
  </tbody>
</table>
</form>

<script language="javascript">
	function simpan (){

		document.form1.method = "POST";
		document.form1.action = "providerprocedure-form";
		document.form1.submit();
	}
	function kembali (){
		document.form1.navigation.value = "back";
		document.form1.action = "providerprocedure";
		document.form1.submit();
	}
	// forreign affairs
			// forreign affairs end
</script>