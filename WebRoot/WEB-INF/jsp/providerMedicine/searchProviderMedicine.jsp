<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<%
String navigation = WebUtil.getAttributeString(request,"navigation","");
%>


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
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;List Provider Medicine</h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>

<!-- Search Container Start -->
<%@ include file="../mainContentProvider.jsp" %>
<br />
<form name="form1" action="providermedicine" method="POST">
<input type="hidden" name="navigation" value="listprovider">
<input type="hidden" name="arah" value="">
<input type="hidden" name="providerId" value="<c:out value="${providerId }" />" />
<input type="hidden" name="index" value="<c:out value="${index}" />">
				<input type="hidden" name="providerMedicineId" value="<c:out value="${providerMedicine.providerMedicineId}" />">
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Search Provider Medicine</h3></td>
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

   		   			<option value="medicineName" <c:if test="${searchby eq \"medicineName\"}">selected="true"</c:if> >Medicine Name</option>
   		   			<option value="medicineFactory" <c:if test="${searchby eq \"medicineFactory\"}">selected="true"</c:if> >Manufacture</option>
   		   			<option value="medicineType" <c:if test="${searchby eq \"medicineType\"}">selected="true"</c:if> >Medicine Type</option>
			   		   		   			
			 </select>
				
              </td>
			 
            <td class="dataLabel">
              <input title="Search [Alt+Q]" accesskey="Q" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" name="button" value="Search" type="submit">
                          
			</td>
            </tr>
		
        </tbody>
      </table></td>
    </tr>
  </tbody>
</table>
	<!-- Table Content Start -->
<br />	
<c:if test="${theUser.userType eq 2 and (theUser.roleId.roleId eq 0 or theUser.roleId.roleId eq 6)}">

<input type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:upload()" value=" Upload Medicine ">

<input type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:tambahi()" value=" Add Medicine ">

<br />
</c:if>
 	
<table class="listView" cellspacing="0" cellpadding="0">
	<tbody>
	<tr>
      <td colspan="20" align="right"><table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tbody>
          <tr>
            <td class="listViewPaginationTdS1" align="left"></td>
            <td class="listViewPaginationTdS1" align="right" nowrap="nowrap">
				
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
		<td width="2%" nowrap="nowrap" class="listViewThS1" scope="col">No.</td>		   		   		   			
		<td scope="col" class="listViewThS1"  style="text-align: center;" nowrap="nowrap" width="20%">Medicine Name &nbsp;</td>				
		<td scope="col" class="listViewThS1"  style="text-align: center;" nowrap="nowrap" width="15%">Manufacture &nbsp;</td>				
		<td scope="col" class="listViewThS1"  style="text-align: center;" nowrap="nowrap" width="10%">Medicine Type &nbsp;</td>				
		<td scope="col" class="listViewThS1"  style="text-align: center;" nowrap="nowrap" width="10%">SVIP &nbsp;</td>
		<td scope="col" class="listViewThS1"  style="text-align: center;" nowrap="nowrap" width="10%">VIP &nbsp;</td>
		<td scope="col" class="listViewThS1"  style="text-align: center;" nowrap="nowrap" width="10%">KELAS I &nbsp;</td>
		<td scope="col" class="listViewThS1"  style="text-align: center;" nowrap="nowrap" width="10%">KELAS II &nbsp;</td>
		<td scope="col" class="listViewThS1"  style="text-align: center;" nowrap="nowrap" width="10%">KELAS III &nbsp;</td>
		
		<td scope="col" class="listViewThS1"  style="text-align: center;" nowrap="nowrap" width="10%">Reference Price &nbsp;</td>
		<c:if test="${theUser.userType eq 2}">
			<c:if test="${theUser.roleId.roleId eq 0 or theUser.roleId.roleId eq 6}">			   			
				<td scope="col" class="listViewThS1"  style="text-align: center;" nowrap="nowrap" width="10%">&nbsp;</td>
			</c:if>
		</c:if>
	</tr>


	<c:forEach items="${ProviderMedicines}" var="medicine" varStatus="status" >
	<%	if (i % 2 == 0) {
			rowclass = "col1";
		} else if (i % 2 != 0) {
			rowclass= "col2";
		}
		i++;
	%>
	 <tr  height="20">
      <td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><%=(i+((indexint-1)*countSet))%></td>
      <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<c:out value="${medicine.medicineId.medicineName}" />
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<c:out value="${medicine.medicineId.medicineFactory}" />
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<c:out value="${medicine.medicineId.medicineType}" />
		</td>  		  		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;">
			<fmt:formatNumber><c:out value="${medicine.svip}" /></fmt:formatNumber>
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;">
			<fmt:formatNumber><c:out value="${medicine.vip}" /></fmt:formatNumber>
		</td>		   		   		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;">
			<fmt:formatNumber><c:out value="${medicine.kelas1}" /></fmt:formatNumber>
		</td>		   		   		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;">
			<fmt:formatNumber><c:out value="${medicine.kelas2}" /></fmt:formatNumber>
		</td>		   		   		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;">
			<fmt:formatNumber><c:out value="${medicine.kelas3}" /></fmt:formatNumber>
		</td>		   		   		
				   		   		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;">
			<fmt:formatNumber><c:out value="${medicine.medicineId.medicinePrice}" /></fmt:formatNumber>
		</td>
		<c:if test="${theUser.userType eq 2}">
			<c:if test="${theUser.roleId.roleId eq 0 or theUser.roleId.roleId eq 6}">
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;">
					
					<!-- ini default edit table for each data -->
					<a href="javascript:ubah('<c:out value="${medicine.providerMedicineId}" />')">
						<img src="images/edit.gif" class="action_icon" alt="Edit" title="Edit"></a>
		
					<!-- ini default delete table for each data -->
					<a href="javascript:hapus('<c:out value="${medicine.providerMedicineId}" />')">
						<img src="images/delete.gif" class="action_icon" alt="Delete" title="Delete"></a>		
				</td>		   		   		   	
			</c:if>
		</c:if>
		  		   		
		
    </tr>
   
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	
	</c:forEach>
	
<tr>
            <td class="listViewPaginationTdS1" align="left"></td>
            <td class="listViewPaginationTdS1" align="right" nowrap="nowrap" colspan="20">
				
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
function tambahi (){
	document.form1.navigation.value = "addbulk";
	document.form1.action = "providermedicine";
	document.form1.method = "GET";
	document.form1.submit();
}
function upload (){
	document.form1.navigation.value = "upload";
	document.form1.action = "providermedicine-upload";
	document.form1.method = "GET";
	document.form1.submit();
}
function hapus (idx){
	var delConfirm = window.confirm ("Are You Sure Want To Delete This Entry ?");

	if (delConfirm){
		document.form1.method = "POST";
		document.form1.providerMedicineId.value = idx;
		document.form1.action = "providermedicine";
		document.form1.navigation.value = "delete";
		document.form1.submit();
	}
}
function ubah (idx){
	document.form1.method = "GET";
	document.form1.providerMedicineId.value = idx;
	document.form1.action = "providermedicine-form";
	document.form1.navigation.value = "edit";
	document.form1.submit();
}
function goleft(){
	document.form1.navigation.value = "<%=nav%>";
	document.form1.arah.value="kiri";
	document.form1.method = "POST";
	document.form1.submit();
}
function goleftbgt(){
	document.form1.navigation.value = "<%=nav%>";
	document.form1.arah.value="kiribgt";
	document.form1.method = "POST";
	document.form1.submit();
}
function goright(){
	document.form1.navigation.value = "<%=nav%>";
	document.form1.arah.value="kanan";
	document.form1.method = "POST";
	document.form1.submit();
}
function gorightbgt(){
	document.form1.navigation.value = "<%=nav%>";
	document.form1.arah.value="kananbgt";
	document.form1.method = "POST";
	document.form1.submit();
}
function go(){
	document.form1.navigation.value = "<%=nav%>";
	document.form1.submit();
}
function cari (){
	document.form1.navigation.value = "gosearch";
	document.form1.action = "providermedicine";
	document.form1.method = "POST";
	document.form1.submit();
}
function detil (idx){
	document.form1.method = "POST";
	document.form1.providerMedicineId.value = idx;
	document.form1.action = "providermedicine";
	document.form1.navigation.value = "detail";
	document.form1.submit();

}
</script>
