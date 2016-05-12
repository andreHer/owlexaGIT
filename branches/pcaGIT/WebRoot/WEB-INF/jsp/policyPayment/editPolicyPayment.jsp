



<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<script type="text/javascript" src="index.php_files/sugar_3.js"></script>
<script type="text/javascript" src="index.php_files/cookie.js"></script>
<link rel="stylesheet" type="text/css" media="all" href="index.php_files/calendar-win2k-cold-1.css">
<link rel="stylesheet" type="text/css" media="all" href="index.php_files/style.css">
<script type="text/javascript" src="index.php_files/calendar.js"></script><script type="text/javascript" src="index.php_files/calendar-en.js"></script><script type="text/javascript" src="index.php_files/calendar-setup_3.js"></script><script type="text/javascript">
	var time_reg_format = '([0-9]{1,2}):([0-9]{1,2})';
	var date_reg_format = '([0-9]{4})-([0-9]{1,2})-([0-9]{1,2})';
	var date_reg_positions = {'Y': 1,'m': 2,'d': 3};
	var time_separator = ':';
	var cal_date_format = '%Y-%m-%d';
	var time_offset = 3600;
</script>



<link rel="SHORTCUT ICON" href="">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"><title>AXA Assistance - Customer Relationship Management</title>

<style type="text/css">@import url("themes/RipCurl/style.css"); </style>
<link href="index.php_files/navigation.css" rel="stylesheet" type="text/css">
<script language="javascript" src="index.php_files/menu.js"></script>
<script language="javascript" src="index.php_files/cookie_002.js"></script>


<form action="policypayment-form" method="POST"  name="form1" id="form_layout">
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

<H1>
Policy Payment
</H1>
<%
String alert = (String) request.getAttribute("alert");
if (alert != null && !alert.trim().equals("")) {%>
	<div id="warning">
		<c:out value="${alert}"></c:out>
	</div>
	<%}%>


	<spring:bind path="policyPaymentForm.policyPaymentId">
		<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>

<input type="hidden" name="navigation" value="">







		
	<tr>
		<td class="dataLabel">Policy Id : </td>		
		<td class="dataField">
		<spring:bind path="policyPaymentForm.policyId">
		<input type="text" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="11">
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>	
		<input type="button" name="PILIH" class="btn" value="pilih" onClick="javascript:lookupPolicy()">
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

</form>

<script language="javascript">
	function simpan (){

		document.form1.method = "POST";
		document.form1.action = "policypayment-form";
		document.form1.submit();
	}
	function kembali (){
		document.form1.navigation.value = "back";
		document.form1.action = "policypayment";
		document.form1.submit();
	}
	// forreign affairs
		function lookupPolicy (){
		window.open ("policy?navigation=lookup&url=policypayment-form","Search","width=700, height=550, menubar=no, status=no, toolbar=no, scrollbars=yes, resizable=yes,screenX=200,screenY=100");
	}
	function setPolicy (idx){
		document.form1.method = "POST";
		document.form1.notyet.value="true";
		document.form1.action = "policypayment-form";
		//document.form1.policyPaymentForm.policyId.value=idx;
		document.getElementById("policyId").value = idx;
		document.form1.submit();
		}
				// forreign affairs end
</script>