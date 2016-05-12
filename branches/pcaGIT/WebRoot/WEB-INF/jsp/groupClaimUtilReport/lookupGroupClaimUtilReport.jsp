<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<html>
<body>
<c:out value="${alert}"></c:out>
<form name="form1" method="GET" action="groupclaimutilreport" >

 search By :
 <select name="searchby">

 		   		   			<option name="groupName" <c:if test="${searchby eq \"groupName\"}">selected="true"</c:if> >groupName</option>
			   			<option name="groupPeriode" <c:if test="${searchby eq \"groupPeriode\"}">selected="true"</c:if> >groupPeriode</option>
			   		   		   		   		   		   		   		   		   		   		   		   		   		   		   		   		   		   		   		   		   		   		   		   		   </select>
<input type="text" name="searchtext" value="<c:out value="${searchtext}" />">
<input type="button" name="searchButton" value="search" onClick="javascript:cari()">
<br>
<input type="hidden" name="navigation" value="<c:out value="${navigation}" />">
<input type="hidden" name="arah" value="">
	<input type="hidden" name="id" value="<c:out value="${groupClaimUtilReport.id}" />">

<table width="95%" height="25" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>

		<td width="30" align="center">No.</td>
		<!-- ini default generated table from database -->
		   		   			<td width="30" align="center">Group Name</td>
			   			<td width="30" align="center">Group Periode</td>
			   		   		   		   		   		   		   		   		   		   		   		   		   		   		   		   		   		   		   		   		   		   		   		   		   		<td width="30" align="center">Pilih</td>
	</tr>

	<c:forEach items="${GroupClaimUtilReports}" var="groupClaimUtilReport" varStatus="status" >
	<tr>

		<td width="30" align="center"><c:out value="${status.count}" /></td>
		<!-- ini default generated table from database -->
		   		   			<td width="30" align="center"><c:out value="${groupClaimUtilReport.groupName}" /></td>
			   			<td width="30" align="center"><c:out value="${groupClaimUtilReport.groupPeriode}" /></td>
			   		   		   		   		   		   		   		   		   		   		   		   		   		   		   		   		   		   		   		   		   		   		   		   		   
		<td width="30" align="center">
			<!-- ini default edit table for each data -->
			<input type="button" name="pilihsaya" onClick="javascript:pilih('<c:out value="${groupClaimUtilReport.id}" />')" value="Pilih">
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
	window.opener.setGroupClaimUtilReport (idx);
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
	document.form1.action = "groupclaimutilreport";
	document.form1.method = "POST";
	document.form1.submit();
}
</script>
