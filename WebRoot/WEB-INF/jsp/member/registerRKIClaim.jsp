<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<link rel="stylesheet" type="text/css" href="css/autocomplete.css">
<link rel="stylesheet" type="text/css" href="css/button.css">

<link rel="stylesheet" type="text/css" href="css/jquery/autocomplete/jquery.autocomplete.css"/>

<script type="text/javascript" src="scripts/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="scripts/jquery/plugin/jquery.autocomplete.pack.js"></script>




<form action="member-rki-form" method="POST"  name="form1" id="form_layout">
<input type="hidden" name="navigation" value="saveregisterrki">



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
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Register Manual</h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>
<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>
								
		<tr>
			<td class="dataLabel"> </td>
  			<td class="dataField" colspan="3">&nbsp;</td>
		</tr>
		
		<c:if test="${theUser.userType eq 2}">
			<tr>
			    <td class="dataLabel" width="20%">Provider Name : </td>		
				<td class="dataField" width="30%">
					<spring:bind path="memberForm.providerId">
					<input type="hidden" tabindex="3"  id="providerId" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" size="35" >
					</spring:bind>
					<spring:bind path="memberForm.providerName">
					<input type="text" tabindex="1"  id="providerName" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" size="35" >
						<div id="fredcaption">
							<c:out value="${status.errorMessage}" />
						</div>
					<br/></spring:bind>
				</td>
				<td class="dataLabel"></td>
				<td class="dataField"></td>	 		
			</tr>
		</c:if>
		
		<c:if test="${theUser.userType eq 4}">
			<tr>
			    <td class="dataLabel" width="20%">Provider Name : </td>		
				<td class="dataField" width="30%">
					<spring:bind path="memberForm.providerId">
					<input type="hidden" tabindex="3" readonly="readonly" id="providerId" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" size="35" >
					</spring:bind>
					<spring:bind path="memberForm.providerName">
					<input type="text" tabindex="1" readonly="readonly"  id="providerName" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" size="35" >
						<div id="fredcaption">
							<c:out value="${status.errorMessage}" />
						</div>
					<br/></spring:bind>
				</td>
				<td class="dataLabel"></td>
				<td class="dataField"></td>	 		
			</tr>
		</c:if>
		
		<tr>
		    <td class="dataLabel">Member Card Number * : </td>		
			<td class="dataField">
				<spring:bind path="memberForm.cardNumber">
					<%-- <input type="text" tabindex="2" size="35" name="<c:out value="${status.expression}"/>" 
						value="<c:out value="${status.value}" />" id="cardNumber" > --%>
					<input type="text" size="35" class="dataField"
						name="cardNumber" id="cardNumber" value="<c:out value="${cardNumber}"/>" >
					<input title="Save [Alt+Shift+S]" accesskey="S" 
						class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" 
						onClick="javascript:findCardNumber()" name="Save" value="Filter Service" type="button">
					<div id="fredcaption">
						<c:out value="${status.errorMessage}" />
					</div>
				<br/></spring:bind>				
			</td>	  
			<td class="dataLabel"></td>
			<td class="dataField"></td>	 		
		</tr>
		<tr>					    
		    <td class="dataLabel">Claim Service * : </td>
			<td class="dataField">
				<spring:bind path="memberForm.caseCategoryId">
				<select tabindex="3" name="<c:out value="${status.expression}"/>" id="caseCategoryId">
					<option value=""> -- SELECT SERVICE -- </option>
					<c:forEach items="${caseCategoryList}" var="cc">
						<option parentCaseCategoryId="<c:out value="${cc.parentCategoryId.caseCategoryId}" />" value="<c:out value="${cc.caseCategoryId}" />" 
							<c:if test="${cc.caseCategoryId eq status.value }">selected</c:if> >
								<c:out value="${cc.caseCategoryName}" /> - <c:out value="${cc.caseCategoryCode}" />
						</option>
					</c:forEach>
				</select>
				<div id="fredcaption">
					<c:if test="${cardNumber eq null or ''}" >
						<c:out value="${status.errorMessage}" />
					</c:if>
					<c:if test="${cardNumber ne null and cc.caseCategoryId ne status.value }" >
						<c:out value="${status.errorMessage}" />
					</c:if>
				</div>
				<br/></spring:bind>							
			</td>
			<td class="dataLabel"></td>		
			<td class="dataField"></td>		
		</tr>
		<tr>
		    <td class="dataLabel">Admission Date : </td>		
			<td class="dataField">
				<spring:bind path="memberForm.admissionDate">
						<input type="text" size="25" name="<c:out value="${status.expression}" />" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" maxlength="30">
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
					<br/></spring:bind>				
			</td>	  
			<td class="dataLabel"></td>
			<td class="dataField"></td>	 		
		</tr>
		<tr id="preAdmissionRow">
			<!-- Add by aju on 20150824, for Pre Admission/Pre Authorization -->
			<td class="dataLabel">
				Pre Admission :
			</td>
			<td class="dataField">
				<spring:bind path="memberForm.preAdmission">
					<input type="checkbox"
						name="<c:out value="${status.expression}" />" value="1" id="preAdmissionCheck">
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
			<td class="dataField"></td>	 
			<td class="dataLabel"></td>
			<td class="dataField"></td>	 
		</tr>
		<%--<tr>
			<td class="dataLabel">Manual Registration Reason : </td>		
			<td class="dataField">
				<spring:bind path="memberForm.manualRegistration">
					<select name="<c:out value="${status.expression}"/>">
							<option value="1" <c:if test="${status.value eq 1}">selected</c:if>>DEFECTIVE</option>				
							<option value="2" <c:if test="${status.value eq 2}">selected</c:if>>LOST SIGNAL</option>
							<option value="3" <c:if test="${status.value eq 3}">selected</c:if>>EDC BELUM SAMPAI</option>
							<option value="4" <c:if test="${status.value eq 4}">selected</c:if>>OTHER</option>
					</select>
					<div id="fredcaption">
						<c:out value="${status.errorMessage}" />
					</div>
				</spring:bind>	
			</td>
			<td class="dataLabel"></td>
			<td class="dataField"></td>	 
		</tr>
		 --%>
		<tr>
			<td class="dataLabel"> </td>
	    	<td class="dataField" colspan="3">&nbsp;</td>
	    </tr>
		
	</tbody>
</table>
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  	<tbody>
    	<tr>
	      	<td style="padding-top: 2px;padding-left: 1px;">
		    	<input type="hidden" name="notyet" value="">
		    	<c:if test="${theUser.userType eq 2}">
		    		<c:if test="${theUser.roleId.roleId eq 19 or theUser.roleId.roleId eq 5}">
			    		<input title="Save [Alt+Shift+S]" tabindex="4" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:registerRKI()" name="Save" value=" Register " type="button">		    		        
		    		</c:if>		    		
		    	</c:if>
		    	<c:if test="${theUser.userType eq 4}">		    		
		    		<input title="Save [Alt+Shift+S]" tabindex="4" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:registerRKI()" name="Save" value=" Register " type="button">
		    	</c:if>	        	         
	      	</td>
	      	<td align="right"></td>
    	</tr>
  	</tbody>
</table>
</form>

<script language="javascript">
	
	
	$(document).ready(function(){
		
		//Add by aju on 20150824, default hide the preAdmissionRow
		$("#preAdmissionRow").hide();
     
	    $("#providerName").autocomplete("provider?navigation=lookupjson", {
	        max: 7,
	        dataType: "json",
	        parse: function(data) {
	            return $.map(data, function(row) {
	                return {
	                    data: row,
	                    value: row.name,
	                    result: row.name
	                }
	            });
	        },
	        formatItem: function(row) {
	            return  "<font color='#000' style='align: left;' >"+ row.name +"</font>" ;
	        }
	    }).bind("result", function(data, value){
	        $(this).parents("dd").find(".error_message").hide();
	        $("#providerId").val (value.id);
	    });
	    
	    //Add by aju on 20150824, for checking the caseCategory parentId
	    $("#caseCategoryId").change(function(){
// 	    	alert("caseCategoryId : " + $("#caseCategoryId").val() + "\nparentCaseCategoryId : " + $("option:selected", this).attr("parentCaseCategoryId"));
	    	var showPreAdmissionRow = ($("option:selected", this).attr("parentCaseCategoryId")=="1"?true:false);
// 	    	alert(showPreAdmissionRow);
	    	if(showPreAdmissionRow){
	    		$("#preAdmissionRow").show();
	    		$("#preAdmissionCheck").removeAttr('checked');
	    	}
	    	else{
	    		$("#preAdmissionRow").hide();
	    		$("#preAdmissionCheck").removeAttr('checked');
	    	}
	    });
	    
	    var x = document.getElementById("cardNumber").value;
	    if (x == ""){
	    	document.getElementById("caseCategoryId").disabled=true;
	    }else{
	    	document.getElementById("caseCategoryId").disabled=false;
	    }
	     
	});
	
	function findCardNumber(){
    	
		//value check input cardNumber		
//     	var x = document.getElementById("cardNumber").value;
//     	var text;
//     	if (x == ""){
//     		text = 'card number is null';
//     		$("#caseCategoryId").disabled=true; 		
//     	}else{
//     		text = 'card number is'+x;
//     		$("#caseCategoryId").disabled=false;
//     	}
//     	return alert(text);
    	
    	document.form1.navigation.value = "gosearch";
		document.form1.action = "member-rki-form";
		document.form1.method = "POST";
		document.form1.submit();
    	    	    	
	}
	
	function registerRKI(){
		
		document.form1.method = "POST";
		document.form1.navigation.value = "saveregisterrki";
		document.form1.action = "member-rki-form";
		document.form1.submit();
		
	}

</script>