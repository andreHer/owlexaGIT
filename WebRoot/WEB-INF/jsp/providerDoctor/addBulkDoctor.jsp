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
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Add Doctor</h3></td>
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


Vector poliIdVector  = (Vector)request.getAttribute ("poliIdVector") == null ? new Vector() : (Vector)request.getAttribute ("poliIdVector");


Vector doctorNameVector  = (Vector)request.getAttribute ("nameVector") == null ? new Vector() : (Vector)request.getAttribute ("nameVector");
Vector mondayVector = (Vector)request.getAttribute ("mondayVector") == null ? new Vector() : (Vector)request.getAttribute ("mondayVector");
Vector tuesdayVector = (Vector)request.getAttribute ("tuesdayVector") == null ? new Vector() : (Vector)request.getAttribute ("tuesdayVector");
Vector wednesdayVector = (Vector) request.getAttribute("wednesdayVector") == null ? new Vector() : (Vector)request.getAttribute ("wednesdayVector");
Vector thursdayVector = (Vector) request.getAttribute("thursdayVector") == null ? new Vector() : (Vector)request.getAttribute ("thursdayVector");
Vector fridayVector = (Vector) request.getAttribute("fridayVector") == null ? new Vector() : (Vector)request.getAttribute ("fridayVector");
Vector saturdayVector = (Vector) request.getAttribute("saturdayVector") == null ? new Vector() : (Vector)request.getAttribute ("saturdayVector");
Vector sundayVector = (Vector) request.getAttribute("sundayVector") == null ? new Vector() : (Vector)request.getAttribute ("sundayVector");


int count = 0;
int countSet = 0;
int i = 0;



if (alert != null && !alert.trim().equals("")) {%>
	<div id="warning" align="center">
		<c:out value="${alert}"></c:out>
	</div>
	<%}%>

<form name="form1" action="providerproduct" method="POST">
<input type="hidden" name="navigation" value="savebulk">
<input type="hidden" name="arah" value="">

<input type="hidden" name="providerId" value="<c:out value="${providerId}" />">
<input type="hidden" name="index" value="<c:out value="${index}" />">

<table class="tabForm" cellspacing="0" cellpadding="0">
	<thead>
		<tr height="20">
			<td width="2%" nowrap="nowrap" class="listViewThS1" scope="col">No.</td>		
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">Doctor Name&nbsp;</td>	
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Poliklinik &nbsp;</td>			
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Monday &nbsp;</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Tuesday&nbsp;</td>			
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Wednesday&nbsp;</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Thursday&nbsp;</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Friday&nbsp;</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Saturday&nbsp;</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Sunday&nbsp;</td>
		</tr>
	</thead>
	<tbody id="claimItemTableId">
	
	<%	
		for ( i = 1 ; i <= 15; i++){

	%>

	
	 	<tr height="20">
      		<td class="oddListRowS1" align="center" bgcolor="#4f85c6" nowrap="nowrap" valign="top"><%=i%></td>
      		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<input class="input2" type="text" size=35  name="doctorName" value="<%=(String)doctorNameVector.get(i-1)%>">
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<select name="poliId">
					<option value="">-- PILIH POLIKLINIK --</option>
					<c:forEach items="${polikliniks}" var="poli">
						<option value="<c:out value="${poli.poliklinikId.poliklinikId}" />"><c:out value="${poli.poliklinikId.poliklinikName}" /></option>
					</c:forEach>
				</select> 
			</td>      	
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<input class="input2" type="text" size=10  name="monday" value="<%=(String)mondayVector.get(i-1)%>">
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<input class="input2" type="text" size=10 name="tuesday" value="<%=(String)tuesdayVector.get(i-1)%>">
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<input class="input2" type="text" size=10 name="wednesday" value="<%=(String)wednesdayVector.get(i-1)%>">
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<input class="input2" type="text" size=10 name="thursday" value="<%=(String)thursdayVector.get(i-1)%>">
			</td>   		   	
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<input class="input2" type="text" size=10 name="friday" value="<%=(String)fridayVector.get(i-1)%>">
			</td>   		   	
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<input class="input2" type="text" size=10 name="saturday" value="<%=(String)saturdayVector.get(i-1)%>">
			</td>   		   	   		   	
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<input class="input2" type="text" size=10 name="sunday" value="<%=(String)sundayVector.get(i-1)%>">
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


	
	function simpan(){
			
		var delConfirm = window.confirm ("Are You Sure Want To Save This Doctor ?");
		if (delConfirm){
			document.form1.action = "providerdoctor";
			document.form1.navigation.value = "savebulk";
			document.form1.submit();
		}	
	
	}
	function cancel(){
		document.form1.navigation.value = "list";
		document.form1.action = "providerdoctor";
		document.form1.submit();
	}

</script>
