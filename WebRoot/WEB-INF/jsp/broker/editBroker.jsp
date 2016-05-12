



<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<form action="broker-form" method="POST"  name="form1" id="form_layout">
<input type="hidden" name="navigation" value="">

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





	<spring:bind path="brokerForm.brokerId">
		<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>




			<tr>
				<td class="dataLabel">  </td>				
			

			<td class="dataField">
		
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
	

			<tr>
				<td class="dataLabel"> Broker Name : </td>				
			

			<td class="dataField">
			<spring:bind path="brokerForm.brokerName">
				<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" >
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
	

		
				
		
		

			<tr>
				<td class="dataLabel"> Broker Code : </td>				
			

			<td class="dataField">
			<spring:bind path="brokerForm.brokerCode">
			<input type="text" size="15" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" >
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
	

		
				
		
		

			<tr>
				<td class="dataLabel"> Address : </td>				
			

		    <td class="dataField">
			<spring:bind path="brokerForm.address">
			<textarea cols="40" rows="8" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
		

	

		
				
		
		

			<tr>
				<td class="dataLabel"> City : </td>				
			

			<td class="dataField">
			<spring:bind path="brokerForm.city">
			<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" >
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
	

		
				
		
		

			<tr>
				<td class="dataLabel"> Province : </td>				
			

			<td class="dataField">
			<spring:bind path="brokerForm.province">
			<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" >
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
	

		
				
		
		

			<tr>
				<td class="dataLabel"> Country : </td>				
			

			<td class="dataField">
			<spring:bind path="brokerForm.country">
			<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" >
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
	

		
				
		
		

			<tr>
				<td class="dataLabel"> Zipcode : </td>				
			

			<td class="dataField">
			<spring:bind path="brokerForm.zipcode">
		<input type="text" size="7" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" >
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
	

		
				
		
		

			<tr>
				<td class="dataLabel"> Telephone : </td>				
			

			<td class="dataField">
			<spring:bind path="brokerForm.telephone">
			<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" >
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
	

		
				
		
		

			<tr>
				<td class="dataLabel"> Faximile : </td>				
			

			<td class="dataField">
			<spring:bind path="brokerForm.faximile">
			<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" >
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
	

		
				
		
		

			<tr>
				<td class="dataLabel"> Broker Type : </td>				
			

						<td class="dataField">
			<spring:bind path="brokerForm.brokerType">
				<select name="<c:out value="${status.expression}" />" >
					<option value="">--- SILAKAN PILIH ---</option>
				
					<option value="1" <c:if test="${status.value eq '1'}">selected</c:if>>BROKER</option>
					<option value="2" <c:if test="${status.value eq '2'}">selected</c:if>>AGEN</option>
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
				<td class="dataLabel"></td>				
			

						<td class="dataField">
		
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
		document.form1.action = "broker-form";
		document.form1.submit();
	}
	function kembali (){
		document.form1.navigation.value = "back";
		document.form1.action = "broker";
		document.form1.submit();
	}
	// forreign affairs
			// forreign affairs end
</script>