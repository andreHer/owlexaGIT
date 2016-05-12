<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<form action="action-form" method="POST"  name="form1" id="form_layout">
<input type="hidden" name="navigation" value="">

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td style="padding-bottom: 2px;">
	    <input type="hidden" name="notyet" value="">
	    <input title="Save and Add [Alt+Shift+S]" accesskey="A" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:simpanDanTambah()" name="Save" value=" Save & Add " type="button">
        <input title="Save [Alt+Shift+S]" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:simpan()" name="Save" value=" Save " type="button">
        <input title="Cancel [Alt+Shift+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:cancel()" name="Cancel" value=" Cancel " type="button">         
	  </td>
      <td align="right"></td>
    </tr>
  </tbody>
</table>

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


	<spring:bind path="actionForm.actionId">
		<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>






			<tr>
				<td class="dataLabel"> Action Name : </td>				
			

			<td class="dataField">
			<spring:bind path="actionForm.actionName">
		<input type="text" size="35" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" />	
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	    <td class="dataLabel"> Action Code : </td>
	    <td class="dataField">
	    <spring:bind path="actionForm.actionCode">
	<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" >
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
	    </td>
	</tr>
		<tr>
				<td class="dataLabel"> Action URL : </td>				
			

		    <td class="dataField">
			<spring:bind path="actionForm.actionUrl">
		<input type="text" size="35" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" />	
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	    <td class="dataLabel"> Action Group : </td>
	    <td class="dataField">
	    	<spring:bind path="actionForm.actionGroupId">
				<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" maxlength="11">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
	    </td>
	</tr>
		

			<tr>
				<td class="dataLabel"> Description : </td>				
			

		    <td class="dataField" colspan="2">
			<spring:bind path="actionForm.description">
			<textarea cols="60" rows="8" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
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
        <input title="Save and Add [Alt+Shift+S]" accesskey="A" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:simpanDanTambah()" name="Save" value=" Save & Add " type="button">
        <input title="Save [Alt+Shift+S]" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:simpan()" name="Save" value=" Save " type="button">
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
		document.form1.action = "action-form";
		document.form1.submit();
	}
	function simpanDanTambah (){

		document.form1.method = "POST";
		document.form1.action = "action-form";
		document.form1.navigation.value = "simpandantambah";
		document.form1.submit();
	}	
	function kembali (){
		document.form1.navigation.value = "back";
		document.form1.action = "action";
		document.form1.submit();
	}
	function cancel (){
		document.form1.navigation.value = "back";
		document.form1.action = "action";
		document.form1.submit();
	}
	// forreign affairs
			// forreign affairs end
</script>