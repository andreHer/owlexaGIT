<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ page import="java.util.Vector" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<link rel="stylesheet" type="text/css" href="css/autocomplete.css">
<link rel="stylesheet" type="text/css" href="css/button.css">

<link rel="stylesheet" type="text/css" href="css/jquery/autocomplete/jquery.autocomplete.css"/>

<script type="text/javascript" src="scripts/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="scripts/jquery/plugin/jquery.autocomplete.pack.js"></script>


<%
String navigation = WebUtil.getAttributeString(request,"navigation","");
%>

 <!-- Page Title Start // Should be put on <Title> tag-->

<!-- Page Title Stop-->
<%


String alert = (String) request.getAttribute("alert");
int index = 0;
int totalIndex = 0;

int totalItem = (Integer) request.getAttribute("totalItem") == null ? 0 : ((Integer) request.getAttribute("totalItem")).intValue();

Vector itemIdVector  = (Vector)request.getAttribute ("itemId") == null ? new Vector() : (Vector)request.getAttribute ("itemId");
Vector itemNameVector  = (Vector)request.getAttribute ("itemNameList") == null ? new Vector() : (Vector)request.getAttribute ("itemNameList");
Vector caseCategoryList = (Vector) request.getAttribute("caseCategoryId") == null ? new Vector(): (Vector) request.getAttribute("caseCategoryId");

Vector claimTypeVector  = (Vector)request.getAttribute ("claimType") == null ? new Vector() : (Vector)request.getAttribute ("claimType");
Vector defaultPriceVector = (Vector)request.getAttribute ("defaultPrice") == null ? new Vector() : (Vector)request.getAttribute ("defaultPrice");
Vector itemPriceVector = (Vector)request.getAttribute ("itemPrice") == null ? new Vector() : (Vector)request.getAttribute ("itemPrice");
Vector descVector = (Vector)request.getAttribute ("description") == null ? new Vector() : (Vector)request.getAttribute ("description");

Vector contractUnitVector = (Vector)request.getAttribute ("contractUnit") == null ? new Vector() : (Vector)request.getAttribute ("contractUnit");



int count = 0;
int countSet = 0;
int i = 0;



if (alert != null && !alert.trim().equals("")) {%>
	<div id="warning" align="center">
		<c:out value="${alert}"></c:out>
	</div>
	<%}%>

<%

%>
<!-- Search Container Start -->

<form name="form1" action="casemedicine" method="POST">
<input type="hidden" name="navigation" value="savebulk">
<input type="hidden" name="arah" value="">
<input type="hidden" name="totalItem" value="<c:out value="${totalItem}" />" id="idTotalItem">
<input type="hidden" name="caseId" value="<c:out value="${caseId}" />">
<input type="hidden" name="memberId" value="<c:out value="${memberId}" />">
<input type="hidden" name="index" value="<c:out value="${index}" />">



<br />

 	
<table class="tabForm" cellspacing="0" cellpadding="0">
	<thead>
		<tr height="20">
			<td width="2%" nowrap="nowrap" class="listViewThS1" scope="col">No.</td>		
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">Item Name&nbsp;</td>	
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Coverage Type &nbsp;</td>			
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Claim Type &nbsp;</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Contract Unit Type &nbsp;</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Default Price &nbsp;</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Agreed Price &nbsp;</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">Description &nbsp;</td>
		</tr>
	</thead>
	<tbody id="claimItemTableId">
	
	<%	
		for ( i = 1 ; i <= totalItem; i++){
	%>

	<input type="hidden" id="medicine<%=i%>Id" name="medicineId" value="">
	 	<tr height="20">
      		<td class="oddListRowS1" align="center" bgcolor="#4f85c6" nowrap="nowrap" valign="top"><%=i%></td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<input type="text" class="input2" size="60" id="item<%=i%>Name" name="itemName" value="<%=(String)itemNameVector.get(i-1) %>"  > 
			</td>      	
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<select name="" >
					<option value=""></option>
					<c:forEach items="${caseCategoryList}" var="cc">
						<option value="<c:out value="${cc.caseCategoryId}" />" ><c:out value="${cc.caseCategoryName}" /></option>
					</c:forEach>
				</select>
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<select name="">
					<option value=""></option>					
					<option value="1">REIMBURSEMENT</option>
					<option value="2">CASHLESS</option>
					<option value="3">ALL</option>
					
				</select>
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<select name="">
					<option value=""></option>					
					<option value="1">PER CLAIM</option>
					<option value="2">PER MEMBER</option>
					
				</select>
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<input class="input2" type="text" id="idItemValue<%=i%>" style="text-align: right;" name="itemValue" value="<%=(String)defaultPriceVector.get(i-1) %>">
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<input class="input2" type="text" id="idItemPrice<%=i%>" style="text-align: right;" name="itemPrice" value="<%=(String)itemPriceVector.get(i-1)%>">
			</td>   		   	
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<TEXTAREA class="textarea2" cols="30" rows="1" name="description"><%=(String)descVector.get(i-1)%></TEXTAREA>
			</td>
    	</tr>   
		<tr>
	      <td colspan="20" class="listViewHRS1"></td>
	    </tr>
	<%
	}
	%>
	
	</tbody>

	</table>
	
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
	  <tbody>
	    <tr>
	      <td style="padding-top: 2px;">
		    <input type="hidden" name="notyet" value="">
		   
	        <input title="Save [Alt+Shift+S]" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:simpan()" name="Save" value=" Save " type="button">
	        <input title="Cancel [Alt+Shift+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:cancel()" name="Cancel" value=" Cancel " type="button">
	                  
		  </td>
	      <td align="right"></td>
	    </tr>
	  </tbody>
	</table>
	
</form>
	
<!-- Table Content Stop -->
	<!-- Table Toolbar Start -->
	
		<!-- Table Toolbar Stop -->


<!-- Table Container Stop -->

<script language="JavaScript">

	$(document).ready(function(){

		<%	
			for ( i = 1 ; i <= 10; i++){
		%>    
		    	$("#med<%=i%>Name").autocomplete("medicine?navigation=lookupjson", {
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
			            return  "<font color='#000' style='align: left;' >"+  row.name +"</font>" ;
			        }			             
				    }).bind("result", function(data, value){
				        $(this).parents("dd").find(".error_message").hide();
				        $("#medicine<%=i%>Id").val (value.id);
				        $("#med<%=i%>name").val (value.name);
				        $("#idItemPrice<%=i%>").val (value.price);
				   });
			   <%
			}
		%>
	});
	
	function simpan(){
			
		var delConfirm = window.confirm ("Are You Sure Want To Save This Medicine ?");
		if (delConfirm){
			document.form1.action = "casemedicine";
			document.form1.navigation.value = "savebulk";
			document.form1.submit();
		}	
	
	}
	function cancel(){
		document.form1.navigation.value = "list";
		document.form1.action = "casemedicine";
		document.form1.submit();
	}

</script>
