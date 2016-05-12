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

Vector itemIdVector  = (Vector)request.getAttribute ("itemIdVector") == null ? new Vector() : (Vector)request.getAttribute ("itemIdVector");


Vector itemNameVector  = (Vector)request.getAttribute ("itemNameVector") == null ? new Vector() : (Vector)request.getAttribute ("itemNameVector");
Vector itemCodeVector = (Vector)request.getAttribute ("itemCodeVector") == null ? new Vector() : (Vector)request.getAttribute ("itemCodeVector");
Vector itemEDCNameVector = (Vector)request.getAttribute ("itemEDCNameVector") == null ? new Vector() : (Vector)request.getAttribute ("itemEDCNameVector");

Vector itemEDCCodeVector = (Vector) request.getAttribute("itemEDCCodeVector") == null ? new Vector() : (Vector)request.getAttribute ("itemEDCCodeVector");

Vector itemCategoryNameVector = (Vector)request.getAttribute ("itemCategoryNameVector") == null ? new Vector() : (Vector)request.getAttribute ("itemCategoryNameVector");


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

<form name="form1" action="item" method="POST">
<input type="hidden" name="navigation" value="saveupdatebulk">
<input type="hidden" name="arah" value="">
<input type="hidden" name="totalItem" value="<c:out value="${totalItem}" />" id="idTotalItem">
<input type="hidden" name="caseCategoryId" value="<c:out value="${caseCategoryId}" />">

<input type="hidden" name="index" value="<c:out value="${index}" />">



<br />

 	
<table class="tabForm" cellspacing="0" cellpadding="0">
	<thead>
		<tr height="20">
			<td width="2%" nowrap="nowrap" class="listViewThS1" scope="col">No.</td>		
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">Item Name&nbsp;</td>	
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Item Code &nbsp;</td>			
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Item EDC Code &nbsp;</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Item EDC Name &nbsp;</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">Item Category Name &nbsp;</td>
		</tr>
	</thead>
	<tbody id="claimItemTableId">
	
	<%	
		for ( i = 0 ; i < itemIdVector.size(); i++){

	%>

	<input type="hidden" name="itemId" value="<%=(String) itemIdVector.get(i) %>">
	 	<tr height="20">
      		<td class="oddListRowS1" align="center" bgcolor="#4f85c6" nowrap="nowrap" valign="top"><%=i+1%>.</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<input type="text" class="input2" size="30" name="itemName" value="<%=(String)itemNameVector.get(i) %>"  > 
			</td>      	
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<input class="input2" size="20" type="text"  style="text-align: left;" name="itemCode" value="<%=(String)itemCodeVector.get(i)%>">
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<input class="input2" type="text" style="text-align: left;" name="itemEDCCode" value="<%=(String)itemEDCCodeVector.get(i)%>">
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<input class="input2" type="text"  style="text-align: left;" name="itemEDCName" value="<%=(String)itemEDCNameVector.get(i)%>">
			</td>   		   	
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<input class="input2" type="text" size=35 style="text-align: left;" name="itemCategoryName" value="<%=(String)itemCategoryNameVector.get(i)%>">
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


	function simpan(){
			
		var delConfirm = window.confirm ("Are You Sure Want To Save This Item Configuration ?");
		if (delConfirm){
			document.form1.action = "item";
			document.form1.navigation.value = "saveupdatebulk";
			document.form1.submit();
		}	
	
	}
	function cancel(){
		document.form1.navigation.value = "search";
		document.form1.action = "item";
		document.form1.submit();
	}

</script>
