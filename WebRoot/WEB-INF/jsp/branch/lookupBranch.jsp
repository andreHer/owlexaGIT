<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<html>
<body>
<c:out value="${alert}"></c:out>
<form name="form1" method="GET" action="branch" >

 search By :
 <select name="searchby">

 		   			<option name="branchName" <c:if test="${searchby eq \"branchName\"}">selected="true"</c:if> >branchName</option>
			   			<option name="address" <c:if test="${searchby eq \"address\"}">selected="true"</c:if> >address</option>
			   			<option name="country" <c:if test="${searchby eq \"country\"}">selected="true"</c:if> >country</option>
			   			<option name="city" <c:if test="${searchby eq \"city\"}">selected="true"</c:if> >city</option>
			   			<option name="province" <c:if test="${searchby eq \"province\"}">selected="true"</c:if> >province</option>
			   		   		   			<option name="createdBy" <c:if test="${searchby eq \"createdBy\"}">selected="true"</c:if> >createdBy</option>
			   		   			<option name="deletedBy" <c:if test="${searchby eq \"deletedBy\"}">selected="true"</c:if> >deletedBy</option>
			   		   		   			<option name="modifiedBy" <c:if test="${searchby eq \"modifiedBy\"}">selected="true"</c:if> >modifiedBy</option>
			   			<option name="branchHead" <c:if test="${searchby eq \"branchHead\"}">selected="true"</c:if> >branchHead</option>
			   			<option name="branchFinanceHead" <c:if test="${searchby eq \"branchFinanceHead\"}">selected="true"</c:if> >branchFinanceHead</option>
			   			<option name="branchActuaryHead" <c:if test="${searchby eq \"branchActuaryHead\"}">selected="true"</c:if> >branchActuaryHead</option>
			   			<option name="branchMarketingHead" <c:if test="${searchby eq \"branchMarketingHead\"}">selected="true"</c:if> >branchMarketingHead</option>
			   			<option name="branchClaimHead" <c:if test="${searchby eq \"branchClaimHead\"}">selected="true"</c:if> >branchClaimHead</option>
			   </select>
<input type="text" name="searchtext" value="<c:out value="${searchtext}" />">
<input type="button" name="searchButton" value="search" onClick="javascript:cari()">
<br>
<input type="hidden" name="navigation" value="<c:out value="${navigation}" />">
<input type="hidden" name="arah" value="">
	<input type="hidden" name="branchId" value="<c:out value="${branch.branchId}" />">

<table width="95%" height="25" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>

		<td width="30" align="center">No.</td>
		<!-- ini default generated table from database -->
		   			<td width="30" align="center">Branch Name</td>
			   			<td width="30" align="center">Address</td>
			   			<td width="30" align="center">Country</td>
			   			<td width="30" align="center">City</td>
			   			<td width="30" align="center">Province</td>
			   		   		   			<td width="30" align="center">Created By</td>
			   		   			<td width="30" align="center">Deleted By</td>
			   		   		   			<td width="30" align="center">Modified By</td>
			   			<td width="30" align="center">Branch Head</td>
			   			<td width="30" align="center">Branch Finance Head</td>
			   			<td width="30" align="center">Branch Actuary Head</td>
			   			<td width="30" align="center">Branch Marketing Head</td>
			   			<td width="30" align="center">Branch Claim Head</td>
			   		<td width="30" align="center">Pilih</td>
	</tr>

	<c:forEach items="${Branchs}" var="branch" varStatus="status" >
	<tr>

		<td width="30" align="center"><c:out value="${status.count}" /></td>
		<!-- ini default generated table from database -->
		   			<td width="30" align="center"><c:out value="${branch.branchName}" /></td>
			   			<td width="30" align="center"><c:out value="${branch.address}" /></td>
			   			<td width="30" align="center"><c:out value="${branch.country}" /></td>
			   			<td width="30" align="center"><c:out value="${branch.city}" /></td>
			   			<td width="30" align="center"><c:out value="${branch.province}" /></td>
			   		   		   			<td width="30" align="center"><c:out value="${branch.createdBy}" /></td>
			   		   			<td width="30" align="center"><c:out value="${branch.deletedBy}" /></td>
			   		   		   			<td width="30" align="center"><c:out value="${branch.modifiedBy}" /></td>
			   			<td width="30" align="center"><c:out value="${branch.branchHead}" /></td>
			   			<td width="30" align="center"><c:out value="${branch.branchFinanceHead}" /></td>
			   			<td width="30" align="center"><c:out value="${branch.branchActuaryHead}" /></td>
			   			<td width="30" align="center"><c:out value="${branch.branchMarketingHead}" /></td>
			   			<td width="30" align="center"><c:out value="${branch.branchClaimHead}" /></td>
			   
		<td width="30" align="center">
			<!-- ini default edit table for each data -->
			<input type="button" name="pilihsaya" onClick="javascript:pilih('<c:out value="${branch.branchId}" />')" value="Pilih">
		</td>
	</tr>
	</c:forEach>
	</table>
	<table>
	<tr>
		<td width="30" align="center">
			<a href="javascript:goleftbgt()"><img src="images/butkiri1.gif" width="20" height="20"></a>
		</td>
		<td width="30" align="center">
			<a href="javascript:goleft()"><img src="images/butkiri.gif" width="20" height="20"></a>
		</td>
		<td width="30" align="center">
			<a href="javascript:goright()"><img src="images/butkanan.gif" width="20" height="20"></a>
		</td>
		<td width="30" align="center">
			<a href="javascript:gorightbgt()"><img src="images/butkanan1.gif" width="20" height="20"></a>
		</td>
		<td width="130" align="center">
			<span class="ChildTableSummaryStyle">GoTo</span>
				<input type="text" name="index" value="<c:out value="${index}"/>" class="inptype" size="3">
				&nbsp;
				<a href="javascript:go()"><img src="images/butgo.gif" width="30" height="25" align="absmiddle"></a>
		</td>
		<td align="right">
			<font color="#999999" class="ChildTableSummaryStyle">Page <c:out value="${index}"/> From <c:out value="${halAkhir}"/>, Total <c:out value="${count}" /> record</font>
		</td>
	</tr>
</table>

</form>
</body>
</html>
<script language="Javascript">

function pilih (idx){
	window.opener.setBranch (idx);
	window.close();
}
function goleft(){
	document.form1.navigation.value = "golookup";
	document.form1.arah.value="kiri";
	document.form1.method = "POST";
	document.form1.submit();
}
function goleftbgt(){
	document.form1.navigation.value = "golookup";
	document.form1.arah.value="kiribgt";
	document.form1.method = "POST";
	document.form1.submit();
}
function goright(){
	document.form1.navigation.value = "golookup";
	document.form1.arah.value="kanan";
	document.form1.method = "POST";
	document.form1.submit();
}
function gorightbgt(){
	document.form1.navigation.value = "golookup";
	document.form1.arah.value="kananbgt";
	document.form1.method = "POST";
	document.form1.submit();
}
function go(){
	document.form1.navigation.value = "golookup";
	document.form1.submit();
}
function cari (){
	document.form1.navigation.value = "golookup";
	document.form1.action = "branch";
	document.form1.method = "POST";
	document.form1.submit();
}
</script>
