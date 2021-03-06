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

<form name="form1" action="medicine" method="POST">
<input type="hidden" name="navigation" value="gosearch">
<input type="hidden" name="sortcolumn" value="<c:out value="${sortcolumn}" />">
<input type="hidden" name="sortorder" value="<c:out value="${sortorder}" />">
<input type="hidden" name="columntosort" value="<c:out value="${columntosort}" />">
<input type="hidden" name="arah" value="">
<input type="hidden" name="index" value="<c:out value="${index}" />">
<input type="hidden" name="medicineId" value="<c:out value="${medicine.medicineId}" />">
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Search Medicine</h3></td>
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
			   			<option value="description" <c:if test="${searchby eq \"description\"}">selected="true"</c:if> >Description</option>
			   			<option value="generalDiagnose" <c:if test="${searchby eq \"generalDiagnose\"}">selected="true"</c:if> >General Diagnose</option>
			   			<option value="medicalDosage" <c:if test="${searchby eq \"medicalDosage\"}">selected="true"</c:if> >Medical Dosage</option>
			   			<option value="medicineClassification" <c:if test="${searchby eq \"medicineClassification\"}">selected="true"</c:if> >Medicine Classification</option>
			   			<option value="medicineCode" <c:if test="${searchby eq \"medicineCode\"}">selected="true"</c:if> >Medicine Code</option>
			   		   			<option value="medicineFactory" <c:if test="${searchby eq \"medicineFactory\"}">selected="true"</c:if> >Medicine Factory</option>
			   			<option value="contraIndication" <c:if test="${searchby eq \"contraIndication\"}">selected="true"</c:if> >Contra Indication</option>
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
<c:if test="${theUser.roleId.roleId ne 6}">
	<input type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:tambahi()" value="Create">
</c:if>
 	
<table class="listView" cellspacing="0" cellpadding="0">
	<tbody>
	<tr>
      <td colspan="20" align="right"><table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tbody>
          <tr>
            <td class="listViewPaginationTdS1" align="left">
			&nbsp;</td>
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
		<td width="2%" nowrap="nowrap" class="listViewThS1" scope="col">
			<img src="images/blank.gif" alt="asd" height="1" width="1">No.</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">Medicine Name &nbsp;
				<input type="hidden" name="sortByOrderName" value="<c:out value="${sortName?'true':'false'}" />">
				<a href="javascript:carisort('medicinename')" class="listViewThLinkS1">
				<img src="images/arrow_<c:out value="${sortName?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
			</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Medicine Classification &nbsp;
				<input type="hidden" name="sortByOrderClassification" value="<c:out value="${sortClassification?'true':'false'}" />">
				<a href="javascript:carisort('medicineclassification')" class="listViewThLinkS1">
				<img src="images/arrow_<c:out value="${sortClassification?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
			</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Medicine Code &nbsp;
				<input type="hidden" name="sortByOrderCode" value="<c:out value="${sortCode?'true':'false'}" />">
				<a href="javascript:carisort('medicinecode')" class="listViewThLinkS1">
				<img src="images/arrow_<c:out value="${sortCode?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
			</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Factory &nbsp;
				<input type="hidden" name="sortByOrderFactory" value="<c:out value="${sortFactory?'true':'false'}" />">
				<a href="javascript:carisort('factory')" class="listViewThLinkS1">
				<img src="images/arrow_<c:out value="${sortFactory?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
			</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Price &nbsp;
				<input type="hidden" name="sortByOrderPrice" value="<c:out value="${sortPrice?'true':'false'}" />">
				<a href="javascript:carisort('medicineprice')" class="listViewThLinkS1">
				<img src="images/arrow_<c:out value="${sortPrice?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
			</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="2%">&nbsp;
	   </td>
	</tr>


	<c:forEach items="${Medicines}" var="medicine" varStatus="status" >
	<%	if (i % 2 == 0) {
			rowclass = "col1";
		} else if (i % 2 != 0) {
			rowclass= "col2";
		}
		i++;
	%>
	 <tr onMouseOver="setPointer(this, '910cbdf4-ffae-5c1a-db84-4434fa305b0a', 'over', '#e7f0fe', '#FFFFFF', '');" onMouseOut="setPointer(this, '910cbdf4-ffae-5c1a-db84-4434fa305b0a', 'out', '#e7f0fe', '#FFFFFF', '');" onMouseDown="setPointer(this, '910cbdf4-ffae-5c1a-db84-4434fa305b0a', 'click', '#e7f0fe', '#FFFFFF', '');" height="20">
      <td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><%=(i+((indexint-1)*countSet))%></td>

      		   		   		   		   		
					   		
					   		   		   		
					   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<a href="medicine?navigation=detail&medicineId=<c:out value="${medicine.medicineId}" />&rowset=<c:out value="${rowsetint}" />&index=<c:out value="${index}" />" class="linkDetail"><c:out value="${medicine.medicineName}" /></a>
			
		</td>
					   				
					   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${medicine.medicineClassification}" />
			
		</td>
					   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${medicine.medicineCode}" />
			
		</td>
					   		   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${medicine.medicineFactory}" />
			
		</td>
					<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
			
				<fmt:formatNumber><c:out value="${medicine.medicinePrice}" /></fmt:formatNumber>
			
		</td>
         <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top">
			
			<a href="javascript:detil('<c:out value="${medicine.medicineId}" />')">
				<img src="images/view.gif" class="action_icon" alt="View" title="View"></a>
				
       	<c:if test="${theUser.roleId.roleId ne 6}">
			<a href="javascript:ubah('<c:out value="${medicine.medicineId}" />')">
				<img src="images/edit.gif" class="action_icon" alt="Edit" title="Edit"></a>

			<a href="javascript:hapus('<c:out value="${medicine.medicineId}" />')">
				<img src="images/delete.gif" class="action_icon" alt="Delete" title="Delete"></a>
		</c:if>
	  </td>
    </tr>
   
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	
	</c:forEach>
	
<tr>
            <td class="listViewPaginationTdS1" align="left">
            &nbsp;</td>
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
	document.form1.navigation.value = "tambah";
	document.form1.action = "medicine-form";
	document.form1.method = "GET";
	document.form1.submit();
}
function hapus (idx){
	var delConfirm = window.confirm ("Are You Sure Want To Delete This Entry ?");

	if (delConfirm){
		document.form1.method = "POST";
		document.form1.medicineId.value = idx;
		document.form1.action = "medicine";
		document.form1.navigation.value = "delete";
		document.form1.submit();
	}
}
function ubah (idx){
	document.form1.method = "GET";
	document.form1.medicineId.value = idx;
	document.form1.action = "medicine-form";
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
	document.form1.action = "medicine";
	document.form1.method = "POST";
	document.form1.submit();
}
function detil (idx){
	document.form1.method = "POST";
	document.form1.medicineId.value = idx;
	document.form1.action = "medicine";
	document.form1.navigation.value = "detail";
	document.form1.submit();

}
function carisort(param){
	document.form1.navigation.value = "gosearchbysort";
	document.form1.sortcolumn.value=param;
	document.form1.method = "POST";
	document.form1.submit();
}
</script>
