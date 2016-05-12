<%@page import="com.ametis.cms.datamodel.Case"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<!-- <link rel="stylesheet" type="text/css" href="css/autocomplete.css"> -->
<link rel="stylesheet" type="text/css" href="css/button.css">

<!-- <link rel="stylesheet" type="text/css" href="css/jquery/autocomplete/jquery.autocomplete.css"/> -->

<!-- <script type="text/javascript" src="scripts/jquery/jquery-1.4.4.min.js"></script> -->
<!-- <script type="text/javascript" src="scripts/jquery/plugin/jquery.autocomplete.pack.js"></script> -->

	<style>
		.ui-autocomplete-loading {
		  background: white url("images/owlexa/ui-anim_basic_16x16.gif") right center no-repeat;
		}
	</style>

<%
	String alert = (String) request.getAttribute("alert");
	Integer tarifType = (Integer)  request.getAttribute("tarifType");
	String roomAndBoard = (String) request.getAttribute("roomAndBoard");

	Integer claimId = (Integer)  request.getAttribute("claimId");
	
	System.out.println ("roomAndBoard : " + roomAndBoard );
	System.out.println ("tarifType 2 : " + tarifType );
	
	if (alert != null && !alert.trim().equals("")) {%>
	<div id="warning">
		<c:out value="${alert}"></c:out>
	</div>
	<%}%>
	

<form action="caseprocedure-form" method="POST"  name="form1" id="form_layout">
<input type="hidden" name=roomAndBoard id="roomAndBoard" value="<c:out value="${roomAndBoard}" />">
<input type="hidden" name="caseCategory" id="caseCategory" value="<c:out value="${caseCategory}" />">
<input type="hidden" name="claimId" id="claimId" value="<c:out value="${claimId}" />">

	<spring:bind path="caseProcedureForm.caseId">
		<input type="hidden" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" id="caseId" maxlength="11">
	</spring:bind>
	<input type="hidden" name="navigation" value="">
	
<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>
		<tr>
			<td class="dataLabel"></td>
			<td class="dataField"></td>
		    <td class="dataLabel"></td>
		    <td class="dataField">&nbsp;</td>
		</tr>

		<spring:bind path="caseProcedureForm.caseProcedureId">
			<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
		</spring:bind>

			<tr>
				<td class="dataLabel"> Procedure/Tindakan : </td>
				<td class="dataField">
					
					<spring:bind path="caseProcedureForm.procedureName">
						<input type="text" size="50" id="procedureName" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>">
					</spring:bind>
					<spring:bind path="caseProcedureForm.procedureId">
						<input type="hidden" size="35" id="procedureId" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}"/>" maxlength="11">
						<div id="fredcaption">
							<c:out value="${status.errorMessage}" />
						</div>
					</spring:bind>
				</td>
			    <td class="dataLabel"></td>
			    <td class="dataField"></td>
			</tr>

			<tr>
				<td class="dataLabel"> Total Charge : </td>				
			
			<td class="dataField">
				<spring:bind path="caseProcedureForm.totalCharge">
					<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="22" id="idItemValue">
				</spring:bind>
			</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
	
	<% if( tarifType  != 1){ %> 
	<tr>
	<td class="dataLabel"></td>
	<td>
	<div id="captionError" hidden="hidden" style="text-align: left; font-size: 10px;" >
					<c:out value="Charge < Reference Price " />
	</div>
	</td>
	</tr>
	<tr>
				<td class="dataLabel"> Reference Price : </td>				
			
			<td class="dataField">
				<spring:bind path="caseProcedureForm.referencePrice">
					<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="22"  id="idItemPrice">
				</spring:bind>

<!-- <input type="text" size="35" name="idPrice" id="idItemPrice" maxlength="22"> -->
			</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
<% } %>
			<tr>
				<td class="dataLabel"> Description : </td>				
			

		    <td class="dataField">
			<spring:bind path="caseProcedureForm.description">
				<textarea cols="50" rows="8" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
			</spring:bind>
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
		
		<c:if test="${theUser.userType eq 2 and (theUser.roleId.roleId eq 1 or theUser.roleId.roleId eq 2)}">

			<tr>
				<td class="dataLabel"> Approval Remarks : </td>				
			

		    <td class="dataField">
			<spring:bind path="caseProcedureForm.approvalRemarks">
				<textarea cols="40" rows=5 name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>			
			</spring:bind>
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
	</c:if>
		<tr>
			<td class="dataLabel"></td>
			<td class="dataField"></td>
		    <td class="dataLabel"></td>
		    <td class="dataField">&nbsp;</td>
		</tr>
	</tbody>
</table>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td style="padding-top: 2px;">
	    <input type="hidden" name="notyet" value="">
        <input title="Save [Alt+S]" accesskey="S"  class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:simpan()" name="Save" value=" Save " type="button">
        <input title="Cancel [Alt+C]" accesskey="C"  class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:cancel()" name="Cancel" value=" Cancel " type="button">         
	  </td>
      <td align="right"></td>
    </tr>
  </tbody>
</table>
</form>

<script language="javascript">
	$(document).ready(function(){
    var urlCase = "";
    var roomAndBoard = $("#roomAndBoard").val();
    var caseCategory = parseInt($("#caseCategory").val());
    
    $("#idItemValue").focusout(function() {
    
		 			var value = parseInt($("#idItemValue").val()) ;
					var price = parseInt($("#idItemPrice").val()) ;
		 			if(value > price){
		 				$("#captionError").show();
		 			}else{
		 				$("#captionError").hide();
		 			}
	}); 
   
    
    if(<%= tarifType %>  == 1){
			urlCase = "procedure?navigation=lookupjson";
	}else if(<%= tarifType %>  == 5){
			urlCase = "providerprocedure?navigation=lookupjsonClient";
	}else if(<%= tarifType %>  == 6){
			urlCase = "providerprocedure?navigation=lookupjsonMemberGroup";
	}else if(<%= tarifType %>  == 7){
			urlCase = "providerprocedure?navigation=lookupjsonPolicy";
	}
	else {
		urlCase = "procedure?navigation=lookupjson";
	}
	
    
    $("#procedureName").autocomplete({
			source: function( request, response ) {
			$.ajax({
				/* url: "procedure?navigation=lookupjson", */
				url: urlCase,
				dataType: "json",
				data: {q: request.term,
					   caseIdauto : $("#caseId").val()},
				success: function(data) {
							response($.map(data, function(item) {
							return {
								label: item.name,
								id: item.id,
								number: item.number,
								
								c1: item.c1,
								c2: item.c2,
								c3: item.c3,
								vip: item.vip,	
								svip: item.svip,
								rj: item.rj
								};
						}));
					}
				});
			},
			minLength: 2,
			cacheLength: 0,
			select: function(event, ui) {		        
		        $(this).parents("dd").find(".error_message").hide();
		        $("#procedureId").val (ui.item.id);
		        $("#procedureName").val (ui.item.label);
		        
		         if(<%= tarifType %>  != 1){
		         	if(caseCategory == 1){
				         if(roomAndBoard == "KELAS 1"){
						      $("#idItemPrice").val (ui.item.c1);
						 }else if(roomAndBoard == "KELAS 2"){
						      $("#idItemPrice").val (ui.item.c2);
						 }else if(roomAndBoard == "KELAS 3"){
						      $("#idItemPrice").val (ui.item.c3);
						 }else if(roomAndBoard == "VIP"){
						      $("#idItemPrice").val (ui.item.vip);
						 }else if(roomAndBoard == "SVIP"){
						      $("#idItemPrice").val (ui.item.svip);
						 }
					}else if(caseCategory == 2){
				      $("#idItemPrice").val (ui.item.rj);
				 	}
				 }
			}
		})
		.autocomplete( "instance" )._renderItem = function( ul, item ) {
	      return $( "<a>" )
	        .append( item.label )
	        .appendTo( ul );
	    };
		
		/* remove by aju on 20150508, upgrade from autocomplete pulgin to jQueryUI above :D
    $("#procedureName").autocomplete("procedure?navigation=lookupjson", {
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
            return "<font color='#000' >"+ row.name +"</font>" ;
        }
    }).bind("result", function(data, value){
        $(this).parents("dd").find(".error_message").hide();
        $("#procedureId").val(value.id);
    });
    */
    
});

	
	function simpan (){
	var value = parseInt(document.getElementById("idItemValue").value) ;
	<% if( tarifType  != 1){ %> 
		var price = parseInt(document.getElementById("idItemPrice").value) ;
	<% } %>
	
	<% if( tarifType  != 1){ %> 
		if(value < price){
			var delConfirm = window.confirm ("Are You Sure Want To Save This Procedure ?");
			if (delConfirm){
				document.form1.method = "POST";
				document.form1.action = "caseprocedure-form";
				document.form1.submit();
			}
		}else{
			alert("Charge < Reference Price");
		}
	<% }else{ %>
			var delConfirm = window.confirm ("Are You Sure Want To Save This Procedure ?");
			if (delConfirm){
				document.form1.method = "POST";
				document.form1.action = "caseprocedure-form";
				document.form1.submit();
			}
	<% } %>
	}
	
	function cancel(){
		document.form1.navigation.value = "back";
		document.form1.action = "caseprocedure";
		document.form1.submit();
	}
</script>