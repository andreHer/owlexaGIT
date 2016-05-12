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

<form action="claim" method="POST"  name="form1" id="form_layout">

	<input type="hidden" name="navigation" value="saveapproveexgratia">	
	<input type="hidden" name="claimId" value="<c:out value="${claim.claimId}" />" >

<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>
		<tr>
			<td>
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<tbody>
						<tr>
							<td class="dataLabel"></td>
							<td class="dataField"></td>
							<td class="dataLabel"></td>
							<td class="dataField"></td>
						</tr>
						<tr>
							<td class="dataLabel"> Claim : </td>
							<td class="dataField">				
								<input type="text" size="35" name="claimNumber" value="<c:out value="${claim.claimNumber}" />">			
							</td>
							<td class="dataLabel">  </td>
							<td class="dataField"></td>
						</tr>			
						<tr>
							<td class="dataLabel"> Remarks : </td>
						    <td class="dataField" colspan="3">			
								<textarea rows="8" cols="60" name="remarks"></textarea>			
							</td>
						</tr>		
						<tr>
							<td class="dataLabel"></td>
							<td class="dataField"></td>
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
		document.form1.action = "claim";
		document.form1.submit();
	}
	function kembali (){
		document.form1.navigation.value = "detail";
		document.form1.action = "claim";
		document.form1.submit();
	}
	// forreign affairs
			// forreign affairs end
</script>