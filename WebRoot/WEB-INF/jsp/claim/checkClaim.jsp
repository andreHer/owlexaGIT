<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ page import="java.util.Vector" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<%
String navigation = WebUtil.getAttributeString(request,"navigation","");
%>

 <!-- Page Title Start // Should be put on <Title> tag-->

<!-- Page Title Stop-->
<%
String alert = (String) request.getAttribute("alert");
int index = 0;
int totalIndex = 0;
int totalItem = WebUtil.getAttributeInteger(request,"totalItem",0).intValue();

Vector claimItemVector  = (Vector)request.getAttribute ("claimItemVector") == null ? new Vector() : (Vector)request.getAttribute ("claimItemVector");
Vector claimAmountVector = (Vector)request.getAttribute ("claimAmountVector") == null ? new Vector() : (Vector)request.getAttribute ("claimAmountVector");



Vector claimValueVector = (Vector)request.getAttribute ("claimValueVector") == null ? new Vector() : (Vector)request.getAttribute ("claimValueVector");
Vector claimDescVector = (Vector)request.getAttribute ("claimDescVector");

System.out.println ("CLAIM ITEM : " + claimItemVector.size() );
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

<form name="form1" action="claimitem" method="POST">
<input type="hidden" name="navigation" value="saveclaimitem">
<input type="hidden" name="arah" value="">
<input type="hidden" name="claimId" value="<c:out value="${claimId}" />">
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
	<!-- Table Toolbar Stop -->
	
	<!-- Table Content Start -->
	


<br />

 	
<table class="tabForm" cellspacing="0" cellpadding="0">
	<tbody>
	
	<tr height="20">
		<td width="6%" nowrap="nowrap" class="listViewThS1" scope="col">
			<img src="images/blank.gif" alt="asd" height="1" width="1">No.</td>		

<!-- ini default generated table from database -->
		   		   		   			
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="12%">
				
				<a href="claimitem?navigation=search&sortby=claim_number&searchby=<c:out value="${searchby}" />" class="listViewThLinkS1">Item Code&nbsp;<img src="images/arrow.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
				</td>
				  		
				 <td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">
				
				<a href="claimitem?navigation=search&sortby=claim_number&searchby=<c:out value="${searchby}" />" class="listViewThLinkS1">Item Name&nbsp;<img src="images/arrow.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
				</td>	
				
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				<a href="claimitem?navigation=search&sortby=item_name&searchby=<c:out value="${searchby}" />" class="listViewThLinkS1">Item Amount &nbsp;<img src="images/arrow.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a></td>
			
		
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">
				<a href="claimitem?navigation=search&sortby=item_value&searchby=<c:out value="${searchby}" />" class="listViewThLinkS1">Item Value &nbsp;<img src="images/arrow.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
				</td>

				<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">
				Description &nbsp;
				</td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">
					Approval Status &nbsp;
				</td>
			   		   		   		   		   				   		   						   		   	  
	   
	</tr>


	
	<%	
		for ( i = 1 ; i <= totalItem; i++){
		System.out.println ("DESC " + claimDescVector.get(i-1));
	%>
	 <tr height="20">
      <td class="oddListRowS1" align="center" bgcolor="#4f85c6" nowrap="nowrap" valign="top"><%=i%></td>

		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<input class="input2" size="10" type="text" name="itemCode" value="<%=(String)claimAmountVector.get(i-1)%>">
		</td>
      	<td class="oddListRowS1" bgcolor="#4f85c6" nowrap="nowrap" valign="top">
      		<select class="input2" name="itemId">
      			<c:forEach items="${items}" var="item">
      				<option value="<c:out value="${item.itemId}" />"

      				><c:out value="${item.itemName}" /></option>
      			</c:forEach>
      		</select>			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<input class="input2" size="10" type="text" name="itemAmount" value="<%=(String)claimAmountVector.get(i-1)%>">
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<input class="input2" type="text" name="itemValue" value="<%=(String)claimValueVector.get(i-1)%>">
		</td>  		   	
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<TEXTAREA class="textarea2" cols="30" rows="2" name="description"><%=(String)claimDescVector.get(i-1)%></TEXTAREA>
		</td>  		   	
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<input type="radio" name="decision" value="Y"> &nbsp; &nbsp; <input type="radio" name="decision" value="N">
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
      <td style="padding-bottom: 2px;">
	    <input type="hidden" name="notyet" value="">
        <input title="Save [Alt+Shift+S]" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:simpan()" name="Save" value=" Save " type="submit">
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

