<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<html>
<body>
<c:out value="${alert}"></c:out>
<form name="form1" method="GET" action="role" >

 search By :
 <select name="searchby">

 		   			<option name="roleName" <c:if test="${searchby eq \"roleName\"}">selected="true"</c:if> >roleName</option>
			   			<option name="description" <c:if test="${searchby eq \"description\"}">selected="true"</c:if> >description</option>
			   			<option name="createdBy" <c:if test="${searchby eq \"createdBy\"}">selected="true"</c:if> >createdBy</option>
			   		   			<option name="modifiedBy" <c:if test="${searchby eq \"modifiedBy\"}">selected="true"</c:if> >modifiedBy</option>
			   		   			<option name="deletedBy" <c:if test="${searchby eq \"deletedBy\"}">selected="true"</c:if> >deletedBy</option>
			   		   		   </select>
<input type="text" name="searchtext" value="<c:out value="${searchtext}" />">
<input type="button" name="searchButton" value="search" onClick="javascript:cari()">
<br>
<input type="hidden" name="navigation" value="<c:out value="${navigation}" />">
<input type="hidden" name="arah" value="">
	<input type="hidden" name="roleId" value="<c:out value="${role.roleId}" />">

<table width="95%" height="25" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>

		<td width="30" align="center">No.</td>
		<!-- ini default generated table from database -->
		   			<td width="30" align="center">Role Name</td>
			   			<td width="30" align="center">Description</td>
			   			<td width="30" align="center">Created By</td>
			   		   			<td width="30" align="center">Modified By</td>
			   		   			<td width="30" align="center">Deleted By</td>
			   		   		   		<td width="30" align="center">Pilih</td>
	</tr>

	<c:forEach items="${Roles}" var="role" varStatus="status" >
	<tr>

		<td width="30" align="center"><c:out value="${status.count}" /></td>
		<!-- ini default generated table from database -->
		   			<td width="30" align="center"><c:out value="${role.roleName}" /></td>
			   			<td width="30" align="center"><c:out value="${role.description}" /></td>
			   			<td width="30" align="center"><c:out value="${role.createdBy}" /></td>
			   		   			<td width="30" align="center"><c:out value="${role.modifiedBy}" /></td>
			   		   			<td width="30" align="center"><c:out value="${role.deletedBy}" /></td>
			   		   		   
		<td width="30" align="center">
			<!-- ini default edit table for each data -->
			<input type="button" name="pilihsaya" onClick="javascript:pilih('<c:out value="${role.roleId}" />')" value="Pilih">
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
	window.opener.setRole (idx);
	window.close();
}
function goleft(){
	document.form1.navigation.value = "golookup";
	document.form1.arah.value="kiri";
	document.form1.method = "GET";
	document.form1.submit();
}
function goleftbgt(){
	document.form1.navigation.value = "golookup";
	document.form1.arah.value="kiribgt";
	document.form1.method = "GET";
	document.form1.submit();
}
function goright(){
	document.form1.navigation.value = "golookup";
	document.form1.arah.value="kanan";
	document.form1.method = "GET";
	document.form1.submit();
}
function gorightbgt(){
	document.form1.navigation.value = "golookup";
	document.form1.arah.value="kananbgt";
	document.form1.method = "GET";
	document.form1.submit();
}
function go(){
	document.form1.navigation.value = "golookup";
	document.form1.submit();
}
function cari (){
	document.form1.navigation.value = "golookup";
	document.form1.action = "role";
	document.form1.method = "GET";
	document.form1.submit();
}
</script>
