

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
	
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Create Policy Member Movement</h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>
	
<form action="policymembermovement-form" method="POST"  enctype="multipart/form-data"  name="form1" id="form_layout">

<input type="hidden" name="navigation" value="<c:out value="${navigation}" />" >
<input type="hidden" name="policyId" value="<c:out value="${policyId}" />" >

<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>
		


		<spring:bind path="policyMemberMovementForm.id">
			<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>

		<spring:bind path="policyMemberMovementForm.policyId">
			<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>



			<tr>
				<td class="dataLabel"></td>				
			

			<td class="dataField">
			
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
	

		
				


			<tr>
				<td class="dataLabel"> Movement Number : </td>				
			

			<td class="dataField">
			<spring:bind path="policyMemberMovementForm.movementNumber">
			<input type=text class="inputbox" size=35  name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" />
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
	

		
				
		
		

			<tr>
				<td class="dataLabel"> Movement Date : </td>				
			

						<td class="dataField">
			<spring:bind path="policyMemberMovementForm.movementDate">
					<input type="text" name="<c:out value="${status.expression}" />" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="10">
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
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
			

		
				
		
		

			<tr>
				<td class="dataLabel"> Movement File Path : </td>				
			

		    <td class="dataField">
			<spring:bind path="policyMemberMovementForm.multipartFile">
				<input type="file" name="<c:out value="${status.expression}"/>" />
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
		
		
	

			<tr>
				<td class="dataLabel"> Movement Type : </td>				
			

						<td class="dataField">
			<spring:bind path="policyMemberMovementForm.movementType">
				<select name="<c:out value="${status.expression}" />">
					<option value="-1">--- PILIH SALAH SATU ---</option>
					<option value="1" <c:if test="${status.value eq '1' }">selected</c:if>>ADDITION</option>
					<option value="2" <c:if test="${status.value eq '2' }">selected</c:if>>DELETION</option>
					<option value="3" <c:if test="${status.value eq '3' }">selected</c:if>>UPGRADE/DOWNGRADE</option>
					
				</select>
				
			</spring:bind>
			</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
			

		
			<tr>
				<td class="dataLabel"> Import Template : </td>				
			

						<td class="dataField">
			<spring:bind path="policyMemberMovementForm.exportImportTemplate">
				<select name="<c:out value="${status.expression}" />">
					<option value="-1">--- PILIH SALAH SATU ---</option>
					
					<c:forEach items="${templates}" var="exim">
						<option value="<c:out value="${exim.id}" />" <c:if test="${exim.id eq status.value}">selected</c:if> ><c:out value="${exim.templateName}" /></option>
					</c:forEach>
					
				</select>
				
			</spring:bind>
			</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>

                        <tr>
                            <td class="dataLabel"> File Format : </td>
                            <td class="dataField">
                                <spring:bind path="policyMemberMovementForm.fileFormat">
									<select name="<c:out value="${status.expression}" />">
										<option value="csv" <c:if test="${status.value eq 'csv' }">selected</c:if>>CSV</option>
										<option value="xls" <c:if test="${status.value eq 'xls' }">selected</c:if>>Excel File</option>
									</select>
                                </spring:bind>
                            </td>
                            <td class="dataLabel"></td>
                            <td class="dataField"></td>
                        </tr>
						<tr>
                            <td class="dataLabel"> Date Format : </td>
                            <td class="dataField">
                                <spring:bind path="policyMemberMovementForm.datePattern">
									<select name="<c:out value="${status.expression}" />">
										<option value="yyyy-MM-dd" <c:if test="${status.value eq 'yyyy-MM-dd' }">selected</c:if>>yyyy-MM-dd</option>
										<option value="dd/MM/yyyy" <c:if test="${status.value eq 'dd/MM/yyyy' }">selected</c:if>>dd/MM/yyyy</option>
									</select>
                                </spring:bind>
                            </td>
                            <td class="dataLabel"></td>
                            <td class="dataField"></td>
                        </tr>
                        <tr>
                            <td class="dataLabel"> First Line Header : </td>
                            <td class="dataField">
                                <spring:bind path="policyMemberMovementForm.firstLineHeader">
									<select name="<c:out value="${status.expression}" />">
										<option value="1" <c:if test="${status.value eq '1' }">selected</c:if>>Yes</option>
										<option value="0" <c:if test="${status.value eq '0' }">selected</c:if>>No</option>
									</select>
                                </spring:bind>
                            </td>
                            <td class="dataLabel"></td>
                            <td class="dataField"></td>
                        </tr>
                        
			
		

			<tr>
				<td class="dataLabel"> Description : </td>				
			

		    <td class="dataField">
			<spring:bind path="policyMemberMovementForm.description">
			<textarea cols=40 rows=8 name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
		

	

		
			
			

			<tr>
				<td class="dataLabel"></td>				
			

			<td class="dataField">
			
		</td>
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
		<c:choose>
			<c:when test="${navigation eq 'listpolicy'}">
				document.form1.action = "policymembermovement-form";
				document.form1.navigation.value = "listpolicy";
				document.form1.policyId.value = <c:out value="${policyId}" />
			</c:when>
		</c:choose>
		document.form1.method = "POST";
		document.form1.submit();
	}
	function cancel (){
		<c:choose>
			<c:when test="${navigation eq 'listpolicy'}">
				document.form1.action = "policymembermovement";
				document.form1.navigation.value = "listpolicy";
				document.form1.policyId.value = <c:out value="${policyId}" />
			</c:when>
		</c:choose>
		document.form1.submit();
	}
	// forreign affairs
			// forreign affairs end
</script>