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

<form name="form1" action="providerclaimutilreport" method="POST">
<input type="hidden" name="navigation" value="gosearch">
<input type="hidden" name="arah" value="">
<input type="hidden" name="index" value="<c:out value="${index}" />">
				<input type="hidden" name="id" value="<c:out value="${providerClaimUtilReport.id}" />">
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Provider Claim Util Report</h3></td>
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
 
			  
            <td class="dataLabel">
              <input title="Search [Alt+Q]" accesskey="Q" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" name="button" value="Search" type="submit">
              <input title="Clear [Alt+D]" accesskey="D" onClick="javascript:download()" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" name="clear" value=" Download " type="button">            
			</td>
            </tr>
			<tr>
            
            <td class="dataLabel" nowrap="nowrap"> Minimum Date :
              &nbsp;&nbsp;&nbsp;&nbsp;
              
                <input name="minimum_date" id="jscal_field" tab="1" maxlength="10" size="11" readonly="readonly" value="" type="text">
				<img src="images/jscalendar.gif" alt="Enter Date" id="jscal_trigger" align="absmiddle" height="18" width="18">
				
				<script type="text/javascript">
    					Calendar.setup({
        					inputField     :    "jscal_field",     // id of the input field
        					ifFormat       :    "%Y/%m/%d",      // format of the input field
        					button         :    "jscal_trigger",  // trigger for the calendar (button ID)
        					align          :    "Tl",           // alignment (defaults to "Bl")
        					singleClick    :    true
    					});
				 	</script>
              
			</td>
            
            <td class="dataLabel">&nbsp;&nbsp;
              </td>
            <td align="right">
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

 	
<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	<tr>
      <td colspan="20" align="right"><table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tbody>
          <tr>
            <td  align="left">
			<a target="_blank" href="providerclaimutilreport?navigation=export&searchby=<c:out value="${searchby}"/>" class="listViewPaginationLinkS1"><img src="images/export.gif" alt="Export" align="absmiddle" border="0" height="7" width="8">&nbsp;Export</a>&nbsp;</td>
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
		   			
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Provider Name &nbsp;</td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Total Claim
				</td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">
				Claim
				</td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Total IP
				</td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">
				IP
				</td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Total OP
				</td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">
				OP
				</td>
	   			<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Total Maternity
				</td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">
				Maternity
				</td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Total Dental
				</td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">
				Dental
				</td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Total OPT
				</td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">
				OPT
				</td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Total MCU
				</td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">
				MCU
				</td>
	</tr>


	<c:forEach items="${ProviderClaimUtilReports}" var="providerClaimUtilReport" varStatus="status" >
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
				<c:out value="${providerClaimUtilReport.providerName}" />
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
			<fmt:formatNumber><c:out value="${providerClaimUtilReport.totalNominalClaim}" /></fmt:formatNumber>
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
			<c:out value="${providerClaimUtilReport.totalClaim}" />
		</td>			   		   		   		   		   		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
			<fmt:formatNumber><c:out value="${providerClaimUtilReport.totalNominalInpatient}" /></fmt:formatNumber>
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
			<c:out value="${providerClaimUtilReport.totalInpatient}" />
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
			<fmt:formatNumber><c:out value="${providerClaimUtilReport.totalNominalOutpatient}" /></fmt:formatNumber>
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
			<c:out value="${providerClaimUtilReport.totalOutpatient}" />
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
			<fmt:formatNumber><c:out value="${providerClaimUtilReport.totalNominalMaterinity}" /></fmt:formatNumber>
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
			<c:out value="${providerClaimUtilReport.totalMaternity}" />
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
			<fmt:formatNumber><c:out value="${providerClaimUtilReport.totalNominalDental}" /></fmt:formatNumber>
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
			<c:out value="${providerClaimUtilReport.totalDental}" />
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
			<fmt:formatNumber><c:out value="${providerClaimUtilReport.totalNominalOptical}" /></fmt:formatNumber>
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
			<c:out value="${providerClaimUtilReport.totalOptical}" />
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
			<fmt:formatNumber><c:out value="${providerClaimUtilReport.totalNominalMcu}" /></fmt:formatNumber>
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
			<c:out value="${providerClaimUtilReport.totalMcu}" />
		</td>
    </tr>
   
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	
	</c:forEach>
	
<tr>
            <td  align="left"><a target="_blank" href="providerclaimutilreport?navigation=export&searchby=<c:out value="${searchby}"/>" class="listViewPaginationLinkS1"><img src="images/export.gif" alt="Export" align="absmiddle" border="0" height="7" width="8">&nbsp;Export</a>&nbsp;</td>
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
function tambahi (){
	document.form1.navigation.value = "tambah";
	document.form1.action = "providerclaimutilreport-form";
	document.form1.method = "GET";
	document.form1.submit();
}
function hapus (idx){
	var delConfirm = window.confirm ("Are You Sure Want To Delete This Entry ?");

	if (delConfirm){
		document.form1.method = "POST";
		document.form1.id.value = idx;
		document.form1.action = "providerclaimutilreport";
		document.form1.navigation.value = "delete";
		document.form1.submit();
	}
}
function ubah (idx){
	document.form1.method = "GET";
	document.form1.id.value = idx;
	document.form1.action = "providerclaimutilreport-form";
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
	document.form1.action = "providerclaimutilreport";
	document.form1.method = "POST";
	document.form1.submit();
}
function detil (idx){
	document.form1.method = "POST";
	document.form1.id.value = idx;
	document.form1.action = "providerclaimutilreport";
	document.form1.navigation.value = "detail";
	document.form1.submit();

}
function download(){
	document.form1.method = "GET";
	document.form1.navigation.value = "downloadprovider";
	document.form1.submit();
}
</script>
