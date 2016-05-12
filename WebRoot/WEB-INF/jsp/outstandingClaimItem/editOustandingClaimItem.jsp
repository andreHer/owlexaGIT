



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


<form action="oustandingclaimitem-form" method="POST"  name="form1" id="form_layout">
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
Oustanding Claim Item
</H1>
<%
String alert = (String) request.getAttribute("alert");
if (alert != null && !alert.trim().equals("")) {%>
	<div id="warning">
		<c:out value="${alert}"></c:out>
	</div>
	<%}%>


	<spring:bind path="oustandingClaimItemForm.outstandingClaimItemId">
		<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>

<input type="hidden" name="navigation" value="">





			<tr>
				<td class="dataLabel"> Item Code : </td>				
			

			<td class="dataField">
			<spring:bind path="oustandingClaimItemForm.itemCode">
			<textarea  cols="60" rows="8" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
	

		
				
		
		

			<tr>
				<td class="dataLabel"> Item Name : </td>				
			

			<td class="dataField">
			<spring:bind path="oustandingClaimItemForm.itemName">
			<textarea  cols="60" rows="8" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
	

		
				
		
		

			<tr>
				<td class="dataLabel"> Description : </td>				
			

		    <td class="dataField">
			<spring:bind path="oustandingClaimItemForm.description">
			<textarea class="inputbox" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
		

	

		
				
		
		

			<tr>
				<td class="dataLabel"> Claim Item Amount : </td>				
			
			<td class="dataField">
			<spring:bind path="oustandingClaimItemForm.claimItemAmount">
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
				<td class="dataLabel"> Claim Item Charge : </td>				
			
			<td class="dataField">
			<spring:bind path="oustandingClaimItemForm.claimItemCharge">
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
				<td class="dataLabel"> Claim Item Approved Value : </td>				
			
			<td class="dataField">
			<spring:bind path="oustandingClaimItemForm.claimItemApprovedValue">
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
				<td class="dataLabel"> Created Time : </td>				
			

						<td class="dataField">
			<spring:bind path="oustandingClaimItemForm.createdTime">
					<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" id="<c:out value="${status.value}"/>" maxlength="19">
					<img src="/WEB-INF/images/jscalendar.gif" alt="Enter Date" id="<c:out value="${status.expression}"/>_trigger" align="absmiddle" height="20" width="20">
					<script type="text/javascript">
    					Calendar.setup({
        					inputField     :    "<c:out value="${status.expression}"/>",     // id of the input field
        					ifFormat       :    "%Y/%m/%d",      // format of the input field
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
				<td class="dataLabel"> Created By : </td>				
			

			<td class="dataField">
			<spring:bind path="oustandingClaimItemForm.createdBy">
			<textarea  cols="60" rows="8" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
	

		
				
		
		

			<tr>
				<td class="dataLabel"> Outstanding Claim Id : </td>				
			
			<td class="dataField">
			<spring:bind path="oustandingClaimItemForm.outstandingClaimId">
					<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<fmt:formatNumber><c:out value="${status.value}" /></fmt:formatNumber>" maxlength="15">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
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
		document.form1.action = "oustandingclaimitem-form";
		document.form1.submit();
	}
	function kembali (){
		document.form1.navigation.value = "back";
		document.form1.action = "oustandingclaimitem";
		document.form1.submit();
	}
	// forreign affairs
			// forreign affairs end
</script>