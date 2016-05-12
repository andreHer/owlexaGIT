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

<script type="text/javascript" src="scripts/owlexa.function.js"></script>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Add Provider Item</h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>

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

Vector medIdVector  = (Vector)request.getAttribute ("itemIdVector") == null ? new Vector() : (Vector)request.getAttribute ("itemIdVector");

Vector codeVector  = (Vector)request.getAttribute ("codeVector") == null ? new Vector() : (Vector)request.getAttribute ("codeVector");
Vector itemNameVector  = (Vector)request.getAttribute ("nameVector") == null ? new Vector() : (Vector)request.getAttribute ("nameVector");

Vector claimSVIPVector = (Vector)request.getAttribute ("valueSVIPVector") == null ? new Vector() : (Vector)request.getAttribute ("valueSVIPVector");
Vector claimVIPVector = (Vector)request.getAttribute ("valueVIPVector") == null ? new Vector() : (Vector)request.getAttribute ("valueVIPVector");
Vector claimC1Vector = (Vector)request.getAttribute ("valueC1Vector") == null ? new Vector() : (Vector)request.getAttribute ("valueC1Vector");
Vector claimC2Vector = (Vector)request.getAttribute ("valueC2Vector") == null ? new Vector() : (Vector)request.getAttribute ("valueC2Vector");
Vector claimC3Vector = (Vector)request.getAttribute ("valueC3Vector") == null ? new Vector() : (Vector)request.getAttribute ("valueC3Vector");
Vector claimRJVector = (Vector)request.getAttribute ("valueRJVector") == null ? new Vector() : (Vector)request.getAttribute ("valueRJVector");





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

<form name="form1" action="providermedicine" method="POST">
<input type="hidden" name="navigation" value="savebulk">
<input type="hidden" name="arah" value="">
<input type="hidden" name="totalItem" value="<c:out value="${totalItem}" />" id="idTotalItem">
<input type="hidden" name="providerId" value="<c:out value="${providerId}" />">
<input type="hidden" name="index" value="<c:out value="${index}" />">

<table class="tabForm" cellspacing="0" cellpadding="0">
	<thead>
		<tr height="20">
			<td width="2%" nowrap="nowrap" class="listViewThS1" scope="col">No.</td>		
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">Item Name&nbsp;</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">Item Code&nbsp;</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Super VIP&nbsp;</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">VIP &nbsp;</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Kelas I &nbsp;</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Kelas II &nbsp;</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Kelas III &nbsp;</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">RJ &nbsp;</td>
			
		</tr>
	</thead>
	<tbody id="claimItemTableId">
	
	<%	
		for ( i = 1 ; i <= 10; i++){

	%>

		<input type="hidden" id="item<%=i%>Id" name="itemId" value="">
	 <tr height="20">
      	<td class="oddListRowS1" align="center" bgcolor="#4f85c6" nowrap="nowrap" valign="top"><%=i%></td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<input type="text" class="input2" size="35" id="item<%=i%>Name" name="itemName" value="<%=(String)itemNameVector.get(i-1) %>"  > 
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<input type="text" class="input2" size="10" id="item<%=i%>Code" name="itemCode" value="<%=(String)codeVector.get(i-1) %>"  > 
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<input onkeypress="javascript:return isNumberKey(this,event)" size="15" class="input2" type="text" id="idItemSVIPValue<%=i%>" style="text-align: right;" name="svipValue" value="<%=(String)claimSVIPVector.get(i-1)%>">
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<input onkeypress="javascript:return isNumberKey(this,event)" size="15" class="input2" type="text" id="idItemVIPValue<%=i%>" style="text-align: right;" name="vipValue" value="<%=(String)claimVIPVector.get(i-1)%>">
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<input onkeypress="javascript:return isNumberKey(this,event)" size="15" class="input2" type="text" id="idItemC1Value<%=i%>" style="text-align: right;" name="kelas1Value" value="<%=(String)claimC1Vector.get(i-1)%>">
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<input onkeypress="javascript:return isNumberKey(this,event)" size="15" class="input2" type="text" id="idItemC2Value<%=i%>" style="text-align: right;" name="kelas2Value" value="<%=(String)claimC2Vector.get(i-1)%>">
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<input onkeypress="javascript:return isNumberKey(this,event)" size="15" class="input2" type="text" id="idItemC3Value<%=i%>" style="text-align: right;" name="kelas3Value" value="<%=(String)claimC3Vector.get(i-1)%>">
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<input onkeypress="javascript:return isNumberKey(this,event)" size="15" class="input2" type="text" id="idItemRJValue<%=i%>" style="text-align: right;" name="rjValue" value="<%=(String)claimRJVector.get(i-1)%>">
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
      		
		</td>
		
		<td class="listViewThS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="middle" colspan=5>
			
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


<script language="JavaScript">

	$(document).ready(function(){

		<%	
			for ( i = 1 ; i <= 10; i++){
		%>    
		    	$("#item<%=i%>Name").autocomplete("item?navigation=lookupjson", {
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
				        $("#item<%=i%>Id").val (value.id);
				        $("#item<%=i%>Name").val (value.name);
				       	$("#item<%=i%>Code").val (value.code);
				      
				   });
			   <%
			}
		%>
	});
	
	function simpan(){
			
		var delConfirm = window.confirm ("Are You Sure Want To Save This Provider Item ?");
		if (delConfirm){
			document.form1.action = "provideritem";
			document.form1.navigation.value = "savebulk";
			document.form1.submit();
		}	
	
	}
	function cancel(){
		
		document.form1.action = "provideritem";
		document.form1.navigation.value = "listprovider";
		document.form1.providerId.value = <c:out value="${providerId}" />
		document.form1.submit();
			
	
	}

</script>
