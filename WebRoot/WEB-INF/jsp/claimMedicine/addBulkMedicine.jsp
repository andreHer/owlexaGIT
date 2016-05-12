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

Vector medIdVector  = (Vector)request.getAttribute ("medIdVector") == null ? new Vector() : (Vector)request.getAttribute ("medIdVector");


Vector medNameVector  = (Vector)request.getAttribute ("nameVector") == null ? new Vector() : (Vector)request.getAttribute ("nameVector");
Vector claimAmountVector = (Vector)request.getAttribute ("amountVector") == null ? new Vector() : (Vector)request.getAttribute ("amountVector");
Vector claimValueVector = (Vector)request.getAttribute ("valueVector") == null ? new Vector() : (Vector)request.getAttribute ("valueVector");

Vector medPriceVector = (Vector) request.getAttribute("medPriceVector") == null ? new Vector() : (Vector)request.getAttribute ("medPriceVector");

Vector claimDescVector = (Vector)request.getAttribute ("descVector") == null ? new Vector() : (Vector)request.getAttribute ("descVector");


System.out.println ("CLAIM ITEM : " + medIdVector.size() );
System.out.println ("CLAIM AMOUNT : " + claimAmountVector.size() );
System.out.println ("CLAIM VALUE : " + claimValueVector.size() );
System.out.println ("CLAIM DESC : " + claimDescVector.size() );

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

<form name="form1" action="claimmedicine" method="POST">
<input type="hidden" name="navigation" value="savebulk">
<input type="hidden" name="arah" value="">
<input type="hidden" name="totalItem" value="<c:out value="${totalItem}" />" id="idTotalItem">
<input type="hidden" name="claimId" value="<c:out value="${claimId}" />">
<input type="hidden" name="memberId" value="<c:out value="${memberId}" />">
<input type="hidden" name="index" value="<c:out value="${index}" />">



<!-- Search Container Stop -->


<!-- Table Container Start -->



<div class="table_container">
	<!-- Table Toolbar Start -->
		
		
		<%
		String nampak = "";
		if(navigation!=null&&navigation.equals("gosearch")){
		}else{
			nampak = " style=\"display: none;\"";
		}
		%>
		
</div>
<br />

 	
<table class="tabForm" cellspacing="0" cellpadding="0">
	<thead>
		<tr height="20">
			<td width="2%" nowrap="nowrap" class="listViewThS1" scope="col">No.</td>		
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">Medicine Name&nbsp;</td>	
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">No. of Usage &nbsp;</td>			
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Total Charge &nbsp;</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Reference Price &nbsp;</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">Description &nbsp;</td>
		</tr>
	</thead>
	<tbody id="claimItemTableId">
	
	<%	
		for ( i = 1 ; i <= 10; i++){

	%>

		<input type="hidden" id="medicine<%=i%>Id" name="medicineId" value="">
	 <tr height="20">
      	<td class="oddListRowS1" align="center" bgcolor="#4f85c6" nowrap="nowrap" valign="top"><%=i%></td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<input type="text" class="input2" size="30" id="med<%=i%>Name" name="medicineName" value="<%=(String)medNameVector.get(i-1) %>"  > 
		</td>      	
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<input class="input2" size="10" type="text"  style="text-align: right;" name="itemAmount" value="<%=(String)claimAmountVector.get(i-1)%>">
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<input class="input2" type="text" id="idItemValue<%=i%>" style="text-align: right;" name="itemValue" value="<%=(String)claimValueVector.get(i-1)%>">
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<input class="input2" type="text" id="idItemPrice<%=i%>" style="text-align: right;" name="itemPrice" value="<%=(String)medPriceVector.get(i-1)%>">
		</td>   		   	
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<TEXTAREA class="textarea2" cols="30" rows="1" name="description"><%=(String)claimDescVector.get(i-1)%></TEXTAREA>
		</td>  		   		   		
  	 
    </tr>
   
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	<%
	}
	%>
	
	</tbody>
	<tfoot>
	
	 <tr height="20">
      <td  class="listViewThS1" align="center" bgcolor="#4f85c6" nowrap="nowrap" valign="top"></td>

		<td class="listViewThS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			 
		</td>
      	<td class="listViewThS1" bgcolor="#4f85c6" nowrap="nowrap" valign="top" colspan=2>
      		<input class="input2" type="text" style="text-align: right;" name="pembayaranDimuka" id="pembayaranDimuka" value="<fmt:formatNumber pattern="#.###"><c:out value="${pembayaranDimuka}" /></fmt:formatNumber>" >
      		
		</td>
		
		<td class="listViewThS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="middle">
			<input class="input2" type="text" onclick="javascript:calculateValue()" style="text-align: right;" name="totalClaimItem" id="totalClaimItem" value="" readonly="readonly">
		</td>  		   	

		<td class="listViewThS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="middle">
			<!-- 
			<input class="input2" size="34" style="text-align: right;" type="text" name="totalClaim" id="totalClaim" value="<fmt:formatNumber pattern="#.###"><c:out value="${claimValue}" /></fmt:formatNumber>" readonly="readonly">
			 --> 
		</td>  		   		   		
			 
    </tr>
	
	</tfoot>
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
			document.form1.action = "claimmedicine";
			document.form1.navigation.value = "savebulk";
			document.form1.submit();
		}	
	
	}
	function cancel(){
		document.form1.navigation.value = "listclaim";
		document.form1.action = "claimmedicine";
		document.form1.submit();
	}

</script>
