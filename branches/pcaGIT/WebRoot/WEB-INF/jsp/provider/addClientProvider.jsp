<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<head>
	<script type="text/javascript">
function selectAll(){
	var formArray = document.getElementsByName("providers"); 
	var i = 0;
	for (i = 0; i < formArray.length; i++){
		if (formArray[i].type = 'checkbox'){
			formArray[i].checked = true;
		}
	}
}
	</script>
</head>

<%
String navigation = WebUtil.getAttributeString(request,"navigation","");
%>

<!-- Page Title Start // Should be put on <Title> tag-->

<!-- Page Title Stop-->
<%
String alert = (String) request.getAttribute("alert");
int index = 0;
int totalIndex = 0;
int count = 0;
int countSet = 0;

try {
	index = ((Integer) request.getAttribute ("index")).intValue();
	count = ((Integer) request.getAttribute ("count")).intValue();
	countSet = ((Integer) request.getAttribute ("countSet")).intValue();
	totalIndex = ((Integer) request.getAttribute ("halAkhir")).intValue();
	
	
	
}
catch (Exception e){
}

if (alert != null && !alert.trim().equals("")) {%>
	<div id="warning" align="center">
		<c:out value="${alert}"></c:out>
	</div>
	<%}%>

<%
String rowclass = "";
int i=0;
int indexint = Integer.parseInt(request.getAttribute("index").toString());
WebUtil.getAttributeInteger(request,"index",0).intValue();
%>
<!-- Search Container Start -->

<form name="form1" action="provider" method="POST" onsubmit="javascript:go()">

<input type="hidden" name="navigation" value="gosearchaddclientprovider">
<input type="hidden" name="arah" value="">
<input type="hidden" name="clientId" value="<c:out value="${clientId}" />" />
<input type="hidden" name="index" value="<c:out value="${index}" />">
				<input type="hidden" name="providerId" value="<c:out value="${provider.providerId}" />">
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Search Provider</h3></td>
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
            <form>
            </form>
            <td class="dataLabel" nowrap="nowrap">Search Keyword:
              &nbsp;&nbsp;
              
				<input size="20" name="searchtext" class="dataField" value="<c:out value="${searchtext}"/>" type="text">
              </td>
            <td class="dataLabel" nowrap="nowrap">Search Category:
              &nbsp;&nbsp;
              
                
				 <select name="searchby" class="inputbox">

	 		   			<option value="providerName" <c:if test="${searchby eq \"providerName\"}">selected="true"</c:if> >Provider Name</option>
			   			<option value="bank" <c:if test="${searchby eq \"bank\"}">selected="true"</c:if> >Bank</option>
			   			<option value="address" <c:if test="${searchby eq \"address\"}">selected="true"</c:if> >Address</option>
			   			<option value="city" <c:if test="${searchby eq \"city\"}">selected="true"</c:if> >City</option>
			   			<option value="province" <c:if test="${searchby eq \"province\"}">selected="true"</c:if> >Province</option>
			   			<option value="country" <c:if test="${searchby eq \"country\"}">selected="true"</c:if> >Country</option>
			   			<option value="postalCode" <c:if test="${searchby eq \"postalCode\"}">selected="true"</c:if> >Postal Code</option>
			   			<option value="contactPerson" <c:if test="${searchby eq \"contactPerson\"}">selected="true"</c:if> >Contact Person</option>
			   </select>
				
              </td>
			  <td class="dataLabel" nowrap="nowrap">Status:
              &nbsp;&nbsp;
              
                <select name="status">					
					<option value="-1">-- All Status --</option>
					<option value="1">Active</option>
					<option value="2">Terminated</option>					
				</select>
				
              </td>
            <td class="dataLabel">
              <input title="Search [Alt+Shift+Q]" accesskey="Q" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" name="button" value="Search" type="button"  onclick="javascript:go()">
                          
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
			nampak = " style=\"display: none;\"";
		}
		%>
		
</div>
	<!-- Table Toolbar Stop -->
	
	<!-- Table Content Start -->
<br />	
<input type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:tambahi()" value="Create">
<br />

 	
<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	<tr>
      <td colspan="20" align="right"><table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tbody>
          <tr>
            <td  align="left">
			</td>
            <td  align="right" nowrap="nowrap">
				
				<%
				if (index == 1){
				%>
				<img src="images/start_off.gif" alt="Start" align="absmiddle" border="0" height="10" width="9">
					Start&nbsp;
									
				<img src="images/previous_off.gif" alt="Previous" align="absmiddle" border="0" height="10" width="4">
					Previous&nbsp;&nbsp;					
				<%
				}
				else if ((index-1) > 0){
				
				%>
				<img src="images/start.gif" alt="Start" align="absmiddle" border="0" height="10" width="9">
					<a href="javascript:goleftbgt()" class="listViewPaginationLinkS1">
						Start&nbsp;
					</a>				
				
				<img src="images/previous.gif" alt="Previous" align="absmiddle" border="0" height="10" width="4">
					<a href="javascript:goleft()" class="listViewPaginationLinkS1">
					Previous&nbsp;&nbsp;
					</a>
				<%
				}
				%>
					<span class="pageNumbers">(<c:out value="${minIndex}" /> - <c:out value="${maxIndex}" /> of <c:out value="${count}" />)</span>&nbsp;&nbsp;
				
				<%
				if (totalIndex > index){
				%>
				
				<a href="javascript:goright()" class="listViewPaginationLinkS1">Next&nbsp;
				<img src="images/next.gif" alt="Next" align="absmiddle" border="0" height="10" width="4"></a>&nbsp;&nbsp;
				<a href="javascript:gorightbgt()" class="listViewPaginationLinkS1">End&nbsp;
					<img src="images/end.gif" alt="End" align="absmiddle" border="0" height="10" width="9"></a>
				<%
				} else {
				
				%>
				Next&nbsp;
				<img src="images/next_off.gif" alt="Next" align="absmiddle" border="0" height="10" width="4">
				&nbsp;&nbsp;
				End&nbsp;
					<img src="images/end_off.gif" alt="End" align="absmiddle" border="0" height="10" width="9">
				<%
				}
				%>
			</td>
          </tr>
        </tbody>
      </table></td>
    </tr>
	<tr height="20">
		<td width="6%" nowrap="nowrap" class="listViewThS1" scope="col">
			<img src="images/blank.gif" alt="asd" height="1" width="1">No.</td>		

<!-- ini default generated table from database -->
		   			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">
				<a href="provider?navigation=search&sortby=provider_name&searchby=<c:out value="${searchby}" />" class="listViewThLinkS1">Provider Name &nbsp;<img src="images/arrow.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a></td>
			
		
			   			
				
			   			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">
				<a href="provider?navigation=search&sortby=address&searchby=<c:out value="${searchby}" />" class="listViewThLinkS1">Provider Category &nbsp;<img src="images/arrow.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a></td>
			
		
			   		
			   			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">
				<a href="provider?navigation=search&sortby=city&searchby=<c:out value="${searchby}" />" class="listViewThLinkS1">City &nbsp;<img src="images/arrow.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a></td>
			
		
			   			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">
				<a href="provider?navigation=search&sortby=province&searchby=<c:out value="${searchby}" />" class="listViewThLinkS1">Province &nbsp;<img src="images/arrow.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a></td>
			
		
			   			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				<a href="provider?navigation=search&sortby=country&searchby=<c:out value="${searchby}" />" class="listViewThLinkS1">Country &nbsp;<img src="images/arrow.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a></td>
			
		
		
			 	 
		
			   	   <td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">&nbsp;
	   </td>
	</tr>


	<c:forEach items="${Providers}" var="provider" varStatus="status" >
	<%	if (i % 2 == 0) {
			rowclass = "col1";
		} else if (i % 2 != 0) {
			rowclass= "col2";
		}
		i++;
	%>
	 <tr height="20">
      <td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><%=(i+((indexint-1)*countSet))%></td>

      		   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<a href="provider?navigation=detail&providerId=<c:out value="${provider.providerId}" />" class="linkDetail"><c:out value="${provider.providerName}" /></a>
			
		</td>
					   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${provider.providerCategoryId.providerCategoryName}" />
			
		</td>
	
	
					   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${provider.city}" />
			
		</td>

					   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${provider.province}" />
			
		</td>
					   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${provider.country}" />
			
		</td>
				   	
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top">
			<input type="checkbox" name="providers" value="<c:out value="${provider.providerId}" />">
	  </td>
    </tr>
   
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	
	</c:forEach>

<tr height="20">
		<td width="6%" nowrap="nowrap" class="listViewThS1" scope="col">
			<img src="images/blank.gif" alt="asd" height="1" width="1"></td>		

<!-- ini default generated table from database -->
		   			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%" colspan="4">
				</td>
			
		
			   			
			
		
		
			 	 
		
			   	   <td scope="col" class="listViewThS1" nowrap="nowrap" width="10%" style="text-align: center;" align="center"><a href="#" onclick="javascript:pilihSemua()">Select All</a>
	   </td>
	      	   <td scope="col" class="listViewThS1" nowrap="nowrap" width="10%" style="text-align: center;" align="center"><a href="#" onclick="javascript:hilangkanSemua()">Unselect All</a>
	   </td>
	</tr>
	
<tr>
            <td  align="left">&nbsp;</td>
            <td  align="right" nowrap="nowrap" colspan="20">
				
				<%
				if (index == 1){
				%>
				<img src="images/start_off.gif" alt="Start" align="absmiddle" border="0" height="10" width="9">
					Start&nbsp;
									
				<img src="images/previous_off.gif" alt="Previous" align="absmiddle" border="0" height="10" width="4">
					Previous&nbsp;&nbsp;					
				<%
				}
				else if ((index-1) > 0){
				
				%>
				<img src="images/start.gif" alt="Start" align="absmiddle" border="0" height="10" width="9">
					<a href="javascript:goleftbgt()" class="listViewPaginationLinkS1">
						Start&nbsp;
					</a>				
				
				<img src="images/previous.gif" alt="Previous" align="absmiddle" border="0" height="10" width="4">
					<a href="javascript:goleft()" class="listViewPaginationLinkS1">
					Previous&nbsp;&nbsp;
					</a>
				<%
				}
				%>
					<span class="pageNumbers">(<c:out value="${minIndex}" /> - <c:out value="${maxIndex}" /> of <c:out value="${count}" />)</span>&nbsp;&nbsp;
				
				<%
				if (totalIndex > index){
				%>
				
				<a href="javascript:goright()" class="listViewPaginationLinkS1">Next&nbsp;
				<img src="images/next.gif" alt="Next" align="absmiddle" border="0" height="10" width="4">
				</a>&nbsp;&nbsp;
				<a href="javascript:gorightbgt()" class="listViewPaginationLinkS1">End&nbsp;
					<img src="images/end.gif" alt="End" align="absmiddle" border="0" height="10" width="9">
				</a>
				<%
				} else {
				
				%>
				Next&nbsp;
				<img src="images/next_off.gif" alt="Next" align="absmiddle" border="0" height="10" width="4">
				&nbsp;&nbsp;
				End&nbsp;
					<img src="images/end_off.gif" alt="End" align="absmiddle" border="0" height="10" width="9">
				<%
				}
				%>
			</td>
          </tr>
	</tbody>
	</table>

</form>
	
<!-- Table Content Stop -->
	<!-- Table Toolbar Start -->
	
		<!-- Table Toolbar Stop -->



<!-- Table Container Stop -->
<script language="Javascript">
<%
String nav="";
if(request.getAttribute("navigation").equals("gosearch")||request.getAttribute("navigation").equals("golookup")){
	nav = (String)request.getAttribute("navigation");
}
%>

function pilihSemua(){
	var formArray = document.getElementsByName("providers"); 
	var i = 0;
	for (i = 0; i < formArray.length; i++){
		if (formArray[i].type = 'checkbox'){
			formArray[i].checked = true;
		}
	}
}
function hilangkanSemua(){
	var formArray = document.getElementsByName("providers"); 
	var i = 0;
	for (i = 0; i < formArray.length; i++){
		if (formArray[i].type = 'checkbox'){
			formArray[i].checked = false;
		}
	}
}

function tambahi (){

	
	document.form1.navigation.value = "saveproviders";
	document.form1.action = "clientprovider";
	document.form1.method = "GET";
	document.form1.submit();
	
}

function goleft(){
	document.form1.navigation.value = "addclientprovider";
	document.form1.arah.value="kiri";
	document.form1.method = "GET";
	document.form1.submit();
}
function goleftbgt(){
	document.form1.navigation.value = "addclientprovider";
	document.form1.arah.value="kiribgt";
	document.form1.method = "GET";
	document.form1.submit();
}
function goright(){
	document.form1.navigation.value = "addclientprovider";
	document.form1.arah.value="kanan";
	document.form1.method = "GET";
	document.form1.submit();
}
function gorightbgt(){
	document.form1.navigation.value = "addclientprovider";
	document.form1.arah.value="kananbgt";
	document.form1.method = "GET";
	document.form1.submit();
}
function go(){
	document.form1.navigation.value = "addclientprovider";
	document.form1.submit();
}
function cari (){
	document.form1.navigation.value = "addclientprovider";
	document.form1.action = "provider";
	document.form1.method = "GET";
	document.form1.submit();
}
function detil (idx){
	document.form1.method = "GET";
	document.form1.providerId.value = idx;
	document.form1.action = "provider";
	document.form1.navigation.value = "detail";
	document.form1.submit();

}
</script>
