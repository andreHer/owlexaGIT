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

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Add Poliklinik</h3></td>
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

Vector poliIdVector  = (Vector)request.getAttribute ("poliIdVector") == null ? new Vector() : (Vector)request.getAttribute ("poliIdVector");


Vector poliNameVector  = (Vector)request.getAttribute ("nameVector") == null ? new Vector() : (Vector)request.getAttribute ("nameVector");
Vector locationVector = (Vector)request.getAttribute ("locationVector") == null ? new Vector() : (Vector)request.getAttribute ("locationVector");
Vector totalRoomVector = (Vector)request.getAttribute ("totalRoomVector") == null ? new Vector() : (Vector)request.getAttribute ("totalRoomVector");

Vector totalDoctorVector = (Vector) request.getAttribute("totalDoctorVector") == null ? new Vector() : (Vector)request.getAttribute ("totalDoctorVector");


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

<form name="form1" action="providerpoliklinik" method="POST">
<input type="hidden" name="navigation" value="savebulk">
<input type="hidden" name="arah" value="">
<input type="hidden" name="totalItem" value="<c:out value="${totalItem}" />" id="idTotalItem">
<input type="hidden" name="providerId" value="<c:out value="${providerId}" />">
<input type="hidden" name="index" value="<c:out value="${index}" />">

<table class="tabForm" cellspacing="0" cellpadding="0">
	<thead>
		<tr height="20">
			<td width="2%" nowrap="nowrap" class="listViewThS1" scope="col">No.</td>		
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">Poliklinik Name&nbsp;</td>	
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Location &nbsp;</td>			
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Total Room &nbsp;</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Total Doctor&nbsp;</td>			
		</tr>
	</thead>
	<tbody id="claimItemTableId">
	
	<%	
		for ( i = 1 ; i <= 15; i++){

	%>

	
	 	<tr height="20">
      		<td class="oddListRowS1" align="center" bgcolor="#4f85c6" nowrap="nowrap" valign="top"><%=i%></td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<select name="poliId">
					<option value="">-- PILIH POLIKLINIK --</option>
					<c:forEach items="${polikliniks}" var="poli">
						<option value="<c:out value="${poli.poliklinikId}" />"><c:out value="${poli.poliklinikName}" /></option>
					</c:forEach>
				</select> 
			</td>      	
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<input class="input2" type="text" size=35  style="text-align: right;" name="location" value="<%=(String)locationVector.get(i-1)%>">
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<input class="input2" type="text" id="idTotalRoom<%=i%>" style="text-align: right;" name="totalRoom" value="<%=(String)totalRoomVector.get(i-1)%>">
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<input class="input2" type="text" id="idTotalDoctor<%=i%>" style="text-align: right;" name="totalDoctor" value="<%=(String)totalDoctorVector.get(i-1)%>">
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
	


<script language="JavaScript">

	$(document).ready(function(){

		<%	
			for ( i = 1 ; i <= 10; i++){
		%>    
		    	$("#poli<%=i%>Name").autocomplete("poliklinik?navigation=lookupjson", {
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
				        $("#poli<%=i%>Id").val (value.id);
				        $("#poli<%=i%>name").val (value.name);				        
				   });
			   <%
			}
		%>
	});
	
	function simpan(){
			
		var delConfirm = window.confirm ("Are You Sure Want To Save This Poliklinik ?");
		if (delConfirm){
			document.form1.action = "providerpoliklinik";
			document.form1.navigation.value = "savebulk";
			document.form1.submit();
		}	
	
	}
	function cancel(){
		document.form1.navigation.value = "list";
		document.form1.action = "providerpoliklinik";
		document.form1.submit();
	}

</script>
