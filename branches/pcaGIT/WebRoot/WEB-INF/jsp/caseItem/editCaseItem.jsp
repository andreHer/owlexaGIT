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
if (alert != null && !alert.trim().equals("")) {%>
	<div id="warning">
		<c:out value="${alert}"></c:out>
	</div>
	<%}%>

<form action="caseitem-form" method="POST"  name="form1" id="form_layout">
	<spring:bind path="caseItemForm.caseItemId">
		<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
		</spring:bind>
	<spring:bind path="caseItemForm.caseId">
		<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
	</spring:bind>	
<input type="hidden" name="navigation" value="">


<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>
		<tr>
			<td>
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
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
				<td class="dataLabel"> Item / Cost Factor : </td>				
			

		<td class="dataField">
			<spring:bind path="caseItemForm.itemId">
				<select name="<c:out value="${status.expression}" />" >
					<option value=""> -- PILIH SALAH SATU -- </option>
					<c:forEach items="${itemList}" var="item">
						<option value="<c:out value="${item.itemId}" />" <c:if test="${status.value eq item.itemId}">selected</c:if>><c:out value="${item.itemName}" /></option>
					</c:forEach>
				</select>				
			</spring:bind>
			</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
		<tr>
				<td class="dataLabel"> Usage Amount : </td>				
			
			<td class="dataField">
			<spring:bind path="caseItemForm.usageAmount">
					<input type="text" size="15" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="22">
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
			</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>



			

			<tr>
				<td class="dataLabel"> Usage Value : </td>				
			
			<td class="dataField">
			<spring:bind path="caseItemForm.usageValue">
					<input type="text" size="35" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" maxlength="22">
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
			<spring:bind path="caseItemForm.description">
			<textarea rows="8" cols="60" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	    
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
        <input title="Save [Alt+Shift+S]" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:simpan()" name="Save" value=" Save " type="submit">
        <input title="Cancel [Alt+Shift+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:cancel()" name="Cancel" value=" Cancel " type="button">         
	  </td>
      <td align="right"></td>
    </tr>
  </tbody>
</table>

</form>

<script language="javascript">


	$(document).ready(function(){
    
    $("#namaItem").autocomplete({
			source: function( request, response ) {
			$.ajax({
				url: "<c:out value="${jsonURL}" />",
				dataType: "json",
				data: {q: request.term},
				success: function(data) {
							response($.map(data, function(item) {
							return {
								label: item.name,
								id: item.id,
								number: item.number
								};
						}));
					}
				});
			},
			minLength: 2,
			select: function(event, ui) {		        
		        $(this).parents("dd").find(".error_message").hide();
        		$("#procedureId").val(ui.item.id);
			}
		})
		.autocomplete( "instance" )._renderItem = function( ul, item ) {
	      return $( "<a>" )
	        .append( item.label )
	        .appendTo( ul );
	    };
		
		/* remove by aju on 20150508, upgrade from autocomplete pulgin to jQueryUI above :D
    $("#namaItem").autocomplete("<c:out value="${jsonURL}" />", {
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

		document.form1.method = "POST";
		document.form1.action = "caseitem-form";
		document.form1.submit();
	}
	function cancel (){
		document.form1.navigation.value = "list";
		document.form1.action = "caseitem";
		document.form1.submit();
	}
	
	
</script>