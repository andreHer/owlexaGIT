<%@ page import="com.ametis.cms.datamodel.EdcTerminal" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.util.Map" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<%
	String nav = (String)request.getAttribute("navigation");

	Integer pageNo = (Integer) request.getAttribute ("pageNo");
	Integer totalRow = (Integer) request.getAttribute ("totalRow");
	Integer limitRow = (Integer) request.getAttribute ("limitRow");
	Integer maxPage = (Integer) request.getAttribute ("maxPage");

	int noRow = 0;
	String rowclass = "";

	int startRow = (pageNo * limitRow) - limitRow + 1;
	int endRow = (pageNo * limitRow);

	if(startRow > totalRow) startRow = totalRow;
	if(endRow > totalRow) endRow = totalRow;

	boolean isFirstPage = (pageNo == 1);
	boolean isLastPage = ((int) pageNo == (int) maxPage);

	String startGif = "start";
	String prevGif = "previous";
	String nextGif = "next";
	String endGif = "end";

	if (isFirstPage) {
		startGif = "start_off";
		prevGif = "previous_off";
	}

	if (isLastPage) {
		nextGif = "next_off";
		endGif = "end_off";
	}

%>

<style>
	.disabled-link {
		pointer-events: none;
		cursor: default;
	}
</style>

<!-- Search Container Start -->


<form name="form1" action="provider" method="POST">
<input type="hidden" name="navigation" value="searchReport">
<input type="hidden" name="arah" value="">
<input type="hidden" name="pageNo" value="<c:out value="${pageNo}" />">

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Provider Report</h3></td>
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
              
				<input size="20" name="searchText" class="dataField" value="<c:out value="${searchText}"/>" type="text">
              </td>
            <td class="dataLabel" nowrap="nowrap">Search Category:
              &nbsp;&nbsp;

				 <select name="searchBy" class="inputbox">

	 		   			<option value="providerId.providerName" <c:if test="${searchBy eq \"providerId.providerName\"}">selected="true"</c:if> >Merchant Name</option>
			   			<option value="deviceNumber" <c:if test="${searchBy eq \"deviceNumber\"}">selected="true"</c:if> >Terminal ID</option>
					 	<option value="providerId.providerCode" <c:if test="${searchBy eq \"providerId.providerCode\"}">selected="true"</c:if> >Merchant ID</option>
					 	<option value="providerId.address" <c:if test="${searchBy eq \"providerId.address\"}">selected="true"</c:if> >Alamat Merchant</option>
					 	<option value="providerId.contactPerson" <c:if test="${searchBy eq \"providerId.contactPerson\"}">selected="true"</c:if> >Contact Person</option>
					 	<option value="providerId.telephone" <c:if test="${searchBy eq \"providerId.telephone\"}">selected="true"</c:if> >Telepon</option>

			   </select>
				
              </td>
            <td class="dataLabel">
              <input title="Search [Alt+Shift+Q]" accesskey="Q" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" name="button" value="Search" type="submit" onclick="javascript:searchReport()">
              <input title="Download" accesskey="Q" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" name="button" value="Download" type="submit" onclick="javascript:generateReport()">

			</td>
            </tr>
        </tbody>
      </table></td>
    </tr>
  </tbody>
</table>


<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	<tr>
      <td colspan="20" align="right"><table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tbody>
          <tr>
            <td class="listViewPaginationTdS1" align="left"></td>
            <td class="listViewPaginationTdS1" align="right" nowrap="nowrap">
				
					<a href="javascript:goleftbgt()" class="listViewPaginationLinkS1 <% if (isFirstPage) {%> disabled-link <%}%>">
						<img src="images/<%= startGif %>.gif" alt="Start" align="absmiddle" border="0" height="10" width="9">
						Start&nbsp;
					</a>				
				
					<a href="javascript:goleft()" class="listViewPaginationLinkS1 <% if (isFirstPage) {%> disabled-link <%}%>">
						<img src="images/<%= prevGif %>.gif" alt="Previous" align="absmiddle" border="0" height="10" width="4">
						Previous&nbsp;&nbsp;
					</a>

				<span class="pageNumbers">(<%= startRow %> - <%= endRow %> of <%= totalRow %>)</span>&nbsp;&nbsp;
				
				<a href="javascript:goright()" class="listViewPaginationLinkS1 <% if (isLastPage) {%> disabled-link <%}%>">
					<img src="images/<%= nextGif %>.gif" alt="Next" align="absmiddle" border="0" height="10" width="4">
					Next&nbsp;
				</a>&nbsp;&nbsp;

				<a href="javascript:gorightbgt()" class="listViewPaginationLinkS1 <% if (isLastPage) {%> disabled-link <%}%>">
					<img src="images/<%= endGif %>.gif" alt="End" align="absmiddle" border="0" height="10" width="9">
					End&nbsp;
				</a>
			</td>
          </tr>
        </tbody>
      </table></td>
    </tr>
	<tr height="20">
		<td width="2%" nowrap="nowrap" class="listViewThS1" scope="col">
			<img src="images/blank.gif" alt="asd" height="1" width="1">No.</td>

		<!-- ini default generated table from database -->

		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Merchant Name &nbsp;
		</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Terminal ID &nbsp;
		</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Merchant ID &nbsp;
		</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">Alamat Merchant &nbsp;
		</td>
		<td scope="col" class="listViewThS1" width="10%">Berapa Lama EDC Tidak Terpakai (hari) &nbsp;
		</td>
		<td scope="col" class="listViewThS1" width="10%">Jumlah Transaksi Selama EDC Tidak Terpakai &nbsp;
		</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Contact Person &nbsp;
		</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Telepon &nbsp;
		</td>
	</tr>


	<%
		Map<Integer, Integer> totalTrxPerTerminalMap = (Map) request.getAttribute("totalTrxPerTerminalMap");
	%>
	<c:forEach items="${edcTerminals}" var="edcTerminal" varStatus="status" >
	<%	if (noRow % 2 == 0) {
			rowclass = "col1";
		} else if (noRow % 2 != 0) {
			rowclass= "col2";
		}
		noRow++;
	%>
		<%
			EdcTerminal edcTerminal = (EdcTerminal) pageContext.getAttribute("edcTerminal");
		%>
	 <tr height="20">
	 
	 	<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><%=(noRow+((pageNo-1)*limitRow))%></td>

		<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top" width="15%">
				<c:out value="${edcTerminal.providerId.providerName}" />
		</td>

	 	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<c:out value="${edcTerminal.deviceNumber}" />
		</td>
	
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<c:out value="${edcTerminal.providerId.edcCode}" />
		</td>

		<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top">
				<c:out value="${edcTerminal.providerId.address}" />
		</td>

		<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top" align="right" width="10%">
			<%
				String diffDaysStr = "";

				Date lastTransactionDate = edcTerminal.getLastTransaction();
				if (lastTransactionDate != null) {
					long diffDays = (new java.util.Date().getTime() - lastTransactionDate.getTime()) / (1000 * 60 * 60 * 24);
					diffDaysStr = Long.valueOf(diffDays).toString();
				}
				if(diffDaysStr.equals(""))
					diffDaysStr ="0";
			%>
			<%= diffDaysStr %>
		</td>

	 	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right" width="10%">
				<%= totalTrxPerTerminalMap.get(edcTerminal.getId()) %>
		</td>

		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<c:out value="${edcTerminal.providerId.contactPerson}" />
		</td>

		 <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			 <c:out value="${edcTerminal.providerId.telephone}" />
		 </td>

    </tr>
   
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	
	</c:forEach>

	<tr>
		<td colspan="20" align="right"><table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tbody>
			<tr>
				<td class="listViewPaginationTdS1" align="left"></td>
				<td class="listViewPaginationTdS1" align="right" nowrap="nowrap" colspan="25">

					<a href="javascript:goleftbgt()" class="listViewPaginationLinkS1 <% if (isFirstPage) {%> disabled-link <%}%>">
						<img src="images/<%= startGif %>.gif" alt="Start" align="absmiddle" border="0" height="10" width="9">
						Start&nbsp;
					</a>

					<a href="javascript:goleft()" class="listViewPaginationLinkS1 <% if (isFirstPage) {%> disabled-link <%}%>">
						<img src="images/<%= prevGif %>.gif" alt="Previous" align="absmiddle" border="0" height="10" width="4">
						Previous&nbsp;&nbsp;
					</a>

					<span class="pageNumbers">(<%= startRow %> - <%= endRow %> of <%= totalRow %>)</span>&nbsp;&nbsp;

					<a href="javascript:goright()" class="listViewPaginationLinkS1 <% if (isLastPage) {%> disabled-link <%}%>">
						<img src="images/<%= nextGif %>.gif" alt="Next" align="absmiddle" border="0" height="10" width="4">
						Next&nbsp;
					</a>&nbsp;&nbsp;

					<a href="javascript:gorightbgt()" class="listViewPaginationLinkS1 <% if (isLastPage) {%> disabled-link <%}%>">
						<img src="images/<%= endGif %>.gif" alt="End" align="absmiddle" border="0" height="10" width="9">
						End&nbsp;
					</a>
				</td>
			</tr>
			</tbody>
		</table></td>
	</tr>
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
	document.form1.action = "provider-form";
	document.form1.method = "GET";
	document.form1.submit();
}

function tambahiClaim (){
	document.form1.navigation.value = "tambahClaim";
	document.form1.action = "providerclaim-form";
	document.form1.method = "GET";
	document.form1.submit();
}

function hapus (idx){
	var delConfirm = window.confirm ("Are You Sure Want To Delete This Entry ?");

	if (delConfirm){
		document.form1.method = "POST";
		document.form1.providerId.value = idx;
		document.form1.action = "provider";
		document.form1.navigation.value = "delete";
		document.form1.submit();
	}
}
function ubah (idx){
	document.form1.method = "GET";
	document.form1.providerId.value = idx;
	document.form1.action = "provider-form";
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
function generateReport (){
	document.form1.navigation.value = "generateReport";
	document.form1.action = "provider";
	document.form1.method = "POST";
	document.form1.submit();
}
function searchReport (){
	document.form1.navigation.value = "searchReport";
	document.form1.action = "provider";
	document.form1.method = "POST";
	document.form1.submit();
}
function detil (idx){
	document.form1.method = "POST";
	document.form1.providerId.value = idx;
	document.form1.action = "provider";
	document.form1.navigation.value = "detail";
	document.form1.submit();

}
function carisort(param){
	document.form1.navigation.value = "gosearchbysort";
	document.form1.searchtext.value="<c:out value="${searchtext}" />";
	document.form1.searchby.value="<c:out value="${searchby}" />";
	document.form1.sortcolumn.value=param;
	document.form1.method = "POST";
	document.form1.submit();
}
function updateProviderConfig(){
	document.form1.navigation.value = "update-provider-configuration";
	document.form1.action = "providerconfig-form";
	document.form1.method = "GET";
	document.form1.submit();
}
</script>
