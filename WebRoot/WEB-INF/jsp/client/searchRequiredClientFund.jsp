<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<%
String navigation = WebUtil.getAttributeString(request,"navigation","");
%>

 <!-- Page Title Start // Should be put on <Title> tag-->

<!-- Page Title Stop-->
<%
String alert = (String) request.getAttribute("alert");


if (alert != null && !alert.trim().equals("")) {%>
	<div id="warning" align="center">
		<c:out value="${alert}"></c:out>
	</div>
	<%}%>

<%

%>
<!-- Search Container Start -->

<form name="form1" action="client" method="POST">
<input type="hidden" name="navigation" value="gosearch">
<input type="hidden" name="arah" value="">

				<input type="hidden" name="clientId" value="<c:out value="${client.clientId}" />">
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Search Client</h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>
<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td><table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tbody>
          <tr>
            <td class="dataLabel" nowrap="nowrap">Search Keyword:
              &nbsp;&nbsp;
              
				<input size="20" name="searchtext" class="dataField" value="<c:out value="${searchtext}"/>" type="text">
              </td>
            <td class="dataLabel" nowrap="nowrap">Search Category:
              &nbsp;&nbsp;
              
                
								 <select name="searchby" class="inputbox">

	 		   			<option value="clientName" <c:if test="${searchby eq 'clientName'}">selected="true"</c:if> >Client Name</option>
			   			<option value="clientCategory" <c:if test="${searchby eq 'address'}">selected="true"</c:if> >Client Category</option>
			   			<option value="city" <c:if test="${searchby eq 'city'}">selected="true"</c:if> >City</option>
			   			<option value="province" <c:if test="${searchby eq 'province'}">selected="true"</c:if> >Province</option>
			   			<option value="country" <c:if test="${searchby eq 'country'}">selected="true"</c:if> >Country</option>

			   		   		   </select>
				
              </td>
			  <td class="dataLabel" nowrap="nowrap">Status:
              &nbsp;&nbsp;
              
                <select name="status">					
					<option value="-1">- All Status -</option>                
					<option value="1" <c:if test="${status eq 1 }">selected</c:if> >Active</option>
					<option value="2" <c:if test="${status eq 2 }">selected</c:if>>Terminated</option>
				</select>
				
              </td>
            <td class="dataLabel">
              <input title="Search [Alt+Shift+Q]" accesskey="Q" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" name="button" value="Search" type="submit">
                          
			</td>
            </tr>
            
        </tbody>
      </table></td>
    </tr>
  </tbody>
</table>


<!-- Search Container Stop -->


<!-- Table Container Start -->



<div class="table_container">
	<!-- Table Toolbar Start -->
		
		
		<%
		String nampak = "";
		if(navigation!=null&&navigation.equals("gosearch")){
		}else{
			nampak = " style='display: none;'";
		}
		%>
		
</div>
	<!-- Table Toolbar Stop -->
	
	<!-- Table Content Start -->

<br />

 	
<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	
	<tr height="20">
		<td width="3%" nowrap="nowrap" class="listViewThS1" scope="col">No.</td>		

<!-- ini default generated table from database -->
		   			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="20%">Client Name </td>
			
		
			   			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">
				Client Category &nbsp;</td>
			
		
			   			
					
			   			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">
				Current Fund Value &nbsp;</td>
			
		
			   			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">
				Minimum Fund Value &nbsp;</td>
			
		
			   			
			
   			
		
		
	</tr>


	<c:forEach items="${Clients}" var="client" varStatus="status" >
	
	 <tr height="20">
      <td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${status.index+1}" /></td>

      		   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<a href="client?navigation=detail&clientId=<c:out value="${client.clientId}" />" class="linkDetail"><c:out value="${client.clientName}" /></a>
			
		</td>
					   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${client.clientCategoryId.clientCategoryName}" />
			
		</td>
					   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<fmt:formatNumber><c:out value="${client.clientFundValue}" /></fmt:formatNumber>
			
		</td>
					   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<fmt:formatNumber><c:out value="${client.minimumFundValue}" /></fmt:formatNumber>
			
		</td>
								   								   		   		      
    </tr>
   
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	
	</c:forEach>
	
	</tbody>
	</table>
</form>
	
<!-- Table Content Stop -->
	<!-- Table Toolbar Start -->
	
		<!-- Table Toolbar Stop -->


<!-- Table Container Stop -->
<script language="Javascript">

function tambahi (){
	document.form1.navigation.value = "tambah";
	document.form1.action = "client-form";
	document.form1.method = "GET";
	document.form1.submit();
}
function hapus (idx){
	var delConfirm = window.confirm ("Are You Sure Want To Delete This Entry ?");

	if (delConfirm){
		document.form1.method = "POST";
		document.form1.clientId.value = idx;
		document.form1.action = "client";
		document.form1.navigation.value = "delete";
		document.form1.submit();
	}
}
function ubah (idx){
	document.form1.method = "GET";
	document.form1.clientId.value = idx;
	document.form1.action = "client-form";
	document.form1.navigation.value = "edit";
	document.form1.submit();
}

function cari (){
	document.form1.navigation.value = "gosearch";
	document.form1.action = "client";
	document.form1.method = "POST";
	document.form1.submit();
}
function detil (idx){
	document.form1.method = "POST";
	document.form1.clientId.value = idx;
	document.form1.action = "client";
	document.form1.navigation.value = "detail";
	document.form1.submit();

}
</script>
