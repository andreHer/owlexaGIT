<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>


<link rel="stylesheet" type="text/css" href="css/autocomplete.css">
<link rel="stylesheet" type="text/css" href="css/button.css">

<link rel="stylesheet" type="text/css" href="css/jquery/autocomplete/jquery.autocomplete.css"/>

<script type="text/javascript" src="scripts/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="scripts/jquery/plugin/jquery.autocomplete.pack.js"></script>

<%String alert = (String) request.getAttribute("alert");
			if (alert != null && !alert.trim().equals("")) {%>
							<div id="warning">
								<c:out value="${alert}"></c:out>
							</div>
							<%}%>

<form action="case" method="POST" name="form1" id="form_layout">

	<input type="hidden" name="navigation" value="docheckmember">
	<table class="moduleTitle" border="0" cellpadding="0" cellspacing="0" width="100%">
		<tbody>
			<tr>
				<td valign="top">
				
				</td>
				<td width="100%">
					<h2 style="font-size: large; color: #000;">
						Patient Registration :
					</h2>
					<br />
				</td>
				<td style="padding-top: 3px; padding-left: 5px;" align="right" nowrap="nowrap" valign="top">
					
				</td>
		</tbody>
	</table>

	<br /> 	

	<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
		<tbody>

			<tr>
				<td class="dataLabel" width="19%"></td>
				<td class="dataField" width="31%"></td>
				<td class="dataLabel" width="15%"></td>
				<td class="dataField" width="35%"></td>
			</tr>
			
			<tr>
				<td class="dataLabel" width="19%">									
					<b>Card Number :</b> <span class="required">*</span>									
				</td>
				<td class="dataField" width="31%" colspan=2>									
					<input type="text" size=35 tabindex="" name="memberNumber" id="nomorPeserta" value="">																		
				</td>
				<td class="dataField" width="35%"></td>
			</tr>
			<tr>
				<td class="dataLabel" width="19%">									
					<b>Service Type :</b> <span class="required">*</span>									
				</td>
				<td class="dataField" width="31%" colspan=2>									
					<select name="caseCategoryId">
						<option value=""> -- SELECT ONE -- </option>
						<c:forEach items="${caseCategoryList}" var="cc">
							<option value="<c:out value="${cc.caseCategoryId}" />" <c:if test="${cc.caseCategoryId eq caseCategoryId }">selected</c:if>>
								<c:out value="${cc.caseCategoryCode}" /> - <c:out value="${cc.caseCategoryName}" />
							</option>
						</c:forEach>
					</select>																		
				</td>
				<td class="dataField" width="35%"></td>
			</tr>
			<tr>
				<td class="dataLabel" width="19%">&nbsp;</td>
				<td class="dataField" width="31%">&nbsp;</td>
				<td class="dataLabel" width="15%">&nbsp;</td>
				<td class="dataField" width="35%">&nbsp;</td>
			</tr>							
		</tbody>
	</table>
	
	
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tbody>
			<tr>
				<td style="padding-top: 2px; padding-left: 1px;">
					<input title="Save [Alt+Shift+S]" accesskey="S" tabindex="" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:simpan()" name="Save" value=" Verify Membership " type="submit">					
					
				</td>
				<td align="right"></td>
			</tr>
		</tbody>
	</table>
</form>

<script language="javascript">
changeCaseCategory();

$(document).ready(function(){
	
    
     
});
	function simpan (){
		document.form1.method = "POST";
		document.form1.action = "case";
		document.form1.submit();
	}
	
	
		

		
				// forreign affairs end
</script>
