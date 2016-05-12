<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<form action="sms" method="POST"  name="form1" id="form_layout">
<input type="hidden" name="jenis" value="free">
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td style="padding-bottom: 2px;">
	    <input type="hidden" name="notyet" value="">
        <input title="Send [Alt+Shift+S]" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:simpan()" name="Save" value=" Send " type="submit">
                 
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
				<td class="dataLabel"> Tujuan : </td>				
			

			<td class="dataField">
			<textarea cols="60" rows="8" name="destination"></textarea>
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
	


			

			<tr>
				<td class="dataLabel"> Isi : </td>				
			

		    <td class="dataField">
			
			<textarea cols="60" rows="8" name="isi"></textarea>
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			
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


</form>

<script language="javascript">
	function simpan (){

		document.form1.method = "POST";
		document.form1.action = "sms";
		document.form1.submit();
	}

</script>