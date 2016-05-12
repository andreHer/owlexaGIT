
<%@page import="java.util.Collection"%>
<%@page import="com.ametis.cms.datamodel.ClaimItem"%>
<%@page import="java.util.Iterator"%><%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ page import="java.util.Vector" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<script type='text/javascript' src='dwr/interface/AJAXClaimItemService.js'></script>
<script type='text/javascript' src='dwr/engine.js'></script>
<script type='text/javascript' src='dwr/util.js'></script>

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


Vector verifyDescVector = (Vector)request.getAttribute ("claimDescVector") == null ? new Vector() : (Vector) request.getAttribute("claimDescVector");


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
		   		   		   			
			
				  		
				 <td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">
					Item Name&nbsp;
				</td>	
				
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">
				No. of Services &nbsp;</td>
			
		
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%">
				Item Amount &nbsp;
				</td>
				
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="20%">
				Description &nbsp;
				</td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">
				Approval &nbsp;
				</td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">
				Description &nbsp;
				</td>
			
			   		   		   		   		   				   		   						   		   	  
	   
	</tr>

	
	<c:forEach items="${verifiableItems}" var="item" varStatus="status">
		<input type="hidden" id="item<%=i%>Id" name="claimItemId" value="<c:out value="${item.claimItemId}" />">
		 <tr height="20">
	      <td class="oddListRowS1" align="center" bgcolor="#4f85c6" nowrap="nowrap" valign="middle"><c:out value="${status.index+1}" />.</td>
	
			<td class="oddListRowS1" bgcolor="#4f85c6" nowrap="nowrap" valign="middle">
	      		
	      		<c:out value="${item.itemId.itemName }" />
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="middle" align="right">
				<fmt:formatNumber><c:out value="${item.claimItemAmount }" /></fmt:formatNumber>
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="middle" align="right">
				<fmt:formatNumber> <c:out value="${item.claimItemValue}" /> </fmt:formatNumber>
			</td>  		
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="middle" align="right">
				<c:out value="${item.claimItemDescription}" />
			</td>  		
			   	
			<td align="center" class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="center">
				<input type="radio" name="approval<c:out value="${item.claimItemId}" />" id="approveItem<c:out value="${item.claimItemId}" />"  value="approve" >&nbsp; approve&nbsp;&nbsp; 
				<input type="radio" name="approval<c:out value="${item.claimItemId}" />" id="rejectItem<c:out value="${item.claimItemId}" />"  value="reject">&nbsp; reject
			</td>  	 
			
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<textarea class="textarea2" cols="30" rows="2" name="description"></textarea>
			</td>  		   		   		
			
	    </tr>
	   
		<tr>
	      <td colspan="20" class="listViewHRS1"></td>
	    </tr>
	</c:forEach>
	<tr height="20">

			
				  		
				 <td scope="col" class="listViewThS1" nowrap="nowrap" width="15%" colspan=5">
				
				</td>	
				
			
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="20%">
				<a href="#" onclick="javascript:approveAll(<c:out value="${claimId }" />)">Approve All</a> &nbsp;
				</td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">
				<a href="#" onclick="javascript:rejectAll(<c:out value="${claimId }" />)">Reject All</a> &nbsp;
				</td>
				
			
			   		   		   		   		   				   		   						   		   	  
	   
	</tr>

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
		document.form1.navigation.value = "approveverifybulk";
		document.form1.submit();
	}

	function cancel(){
		document.form1.navigation.value = "detail";
		document.form1.action = "claim";
		document.form1.submit();
	}

	function approveAll(claimId){
			
		AJAXClaimItemService.getClaimItemList (claimId,{
			callback: function (res){
			
				if (res != null){
				
					for (i = 0; i < res.length; i++){
						var elem = document.getElementById("approveItem"+res[i]);
						elem.checked = true;
					}
					
				}
			}
		});
	
	
	}
	function rejectAll(claimId){
	
		AJAXClaimItemService.getClaimItemList (claimId,{
			callback: function (res){
			
				if (res != null){
				
					for (i = 0; i < res.length; i++){
						var elem = document.getElementById("rejectItem"+res[i]);
						elem.checked = true;
					}
					
				}
			}
		});
	}

</script>
