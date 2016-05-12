<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<html>
<body>
<c:out value="${alert}"></c:out>
<form name="form1" method="GET" action="member" >

 search By :
 <select name="searchby">

 		   		   			<option name="firstName" <c:if test="${searchby eq \"firstName\"}">selected="true"</c:if> >firstName</option>
			   			<option name="lastName" <c:if test="${searchby eq \"lastName\"}">selected="true"</c:if> >lastName</option>
			   		   			<option name="mobilePhone" <c:if test="${searchby eq \"mobilePhone\"}">selected="true"</c:if> >mobilePhone</option>
			   			<option name="faximile" <c:if test="${searchby eq \"faximile\"}">selected="true"</c:if> >faximile</option>
			   			<option name="customerNumber" <c:if test="${searchby eq \"customerNumber\"}">selected="true"</c:if> >customerNumber</option>
			   			<option name="telephone" <c:if test="${searchby eq \"telephone\"}">selected="true"</c:if> >telephone</option>
			   			<option name="email" <c:if test="${searchby eq \"email\"}">selected="true"</c:if> >email</option>
			   			<option name="customerPolicyNumber" <c:if test="${searchby eq \"customerPolicyNumber\"}">selected="true"</c:if> >customerPolicyNumber</option>
			   		   		   		   		   		   			<option name="address" <c:if test="${searchby eq \"address\"}">selected="true"</c:if> >address</option>
			   		   			<option name="birthplace" <c:if test="${searchby eq \"birthplace\"}">selected="true"</c:if> >birthplace</option>
			   		   		   		   			<option name="province" <c:if test="${searchby eq \"province\"}">selected="true"</c:if> >province</option>
			   			<option name="city" <c:if test="${searchby eq \"city\"}">selected="true"</c:if> >city</option>
			   			<option name="GETalCode" <c:if test="${searchby eq \"GETalCode\"}">selected="true"</c:if> >GETalCode</option>
			   			<option name="country" <c:if test="${searchby eq \"country\"}">selected="true"</c:if> >country</option>
			   		   			<option name="policyAgent" <c:if test="${searchby eq \"policyAgent\"}">selected="true"</c:if> >policyAgent</option>
			   			<option name="bankAccount" <c:if test="${searchby eq \"bankAccount\"}">selected="true"</c:if> >bankAccount</option>
			   			<option name="bankAccountName" <c:if test="${searchby eq \"bankAccountName\"}">selected="true"</c:if> >bankAccountName</option>
			   			<option name="bank" <c:if test="${searchby eq \"bank\"}">selected="true"</c:if> >bank</option>
			   			<option name="department" <c:if test="${searchby eq \"department\"}">selected="true"</c:if> >department</option>
			   			<option name="jobPosition" <c:if test="${searchby eq \"jobPosition\"}">selected="true"</c:if> >jobPosition</option>
			   		   		   			<option name="createdBy" <c:if test="${searchby eq \"createdBy\"}">selected="true"</c:if> >createdBy</option>
			   		   			<option name="deletedBy" <c:if test="${searchby eq \"deletedBy\"}">selected="true"</c:if> >deletedBy</option>
			   		   			<option name="modifiedBy" <c:if test="${searchby eq \"modifiedBy\"}">selected="true"</c:if> >modifiedBy</option>
			   		   </select>
<input type="text" name="searchtext" value="<c:out value="${searchtext}" />">
<input type="button" name="searchButton" value="search" onClick="javascript:cari()">
<br>
<input type="hidden" name="navigation" value="<c:out value="${navigation}" />">
<input type="hidden" name="arah" value="">
	<input type="hidden" name="memberId" value="<c:out value="${member.memberId}" />">

<table width="95%" height="25" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>

		<td width="30" align="center">No.</td>
		<!-- ini default generated table from database -->
		   		   			<td width="30" align="center">First Name</td>
			   			<td width="30" align="center">Last Name</td>
			   		   			<td width="30" align="center">Mobile Phone</td>
			   			<td width="30" align="center">Faximile</td>
			   			<td width="30" align="center">Customer Number</td>
			   			<td width="30" align="center">Telephone</td>
			   			<td width="30" align="center">Email</td>
			   			<td width="30" align="center">Customer Policy Number</td>
			   		   		   		   		   		   			<td width="30" align="center">Address</td>
			   		   			<td width="30" align="center">Birthplace</td>
			   		   		   		   			<td width="30" align="center">Province</td>
			   			<td width="30" align="center">City</td>
			   			<td width="30" align="center">GETal Code</td>
			   			<td width="30" align="center">Country</td>
			   		   			<td width="30" align="center">Policy Agent</td>
			   			<td width="30" align="center">Bank Account</td>
			   			<td width="30" align="center">Bank Account Name</td>
			   			<td width="30" align="center">Bank</td>
			   			<td width="30" align="center">Department</td>
			   			<td width="30" align="center">Job Position</td>
			   		   		   			<td width="30" align="center">Created By</td>
			   		   			<td width="30" align="center">Deleted By</td>
			   		   			<td width="30" align="center">Modified By</td>
			   		   		<td width="30" align="center">Pilih</td>
	</tr>

	<c:forEach items="${Members}" var="member" varStatus="status" >
	<tr>

		<td width="30" align="center"><c:out value="${status.count}" /></td>
		<!-- ini default generated table from database -->
		   		   			<td width="30" align="center"><c:out value="${member.firstName}" /></td>
			   			<td width="30" align="center"><c:out value="${member.lastName}" /></td>
			   		   			<td width="30" align="center"><c:out value="${member.mobilePhone}" /></td>
			   			<td width="30" align="center"><c:out value="${member.faximile}" /></td>
			   			<td width="30" align="center"><c:out value="${member.customerNumber}" /></td>
			   			<td width="30" align="center"><c:out value="${member.telephone}" /></td>
			   			<td width="30" align="center"><c:out value="${member.email}" /></td>
			   			<td width="30" align="center"><c:out value="${member.customerPolicyNumber}" /></td>
			   		   		   		   		   		   			<td width="30" align="center"><c:out value="${member.address}" /></td>
			   		   			<td width="30" align="center"><c:out value="${member.birthplace}" /></td>
			   		   		   		   			<td width="30" align="center"><c:out value="${member.province}" /></td>
			   			<td width="30" align="center"><c:out value="${member.city}" /></td>
			   			<td width="30" align="center"><c:out value="${member.GETalCode}" /></td>
			   			<td width="30" align="center"><c:out value="${member.country}" /></td>
			   		   			<td width="30" align="center"><c:out value="${member.policyAgent}" /></td>
			   			<td width="30" align="center"><c:out value="${member.bankAccount}" /></td>
			   			<td width="30" align="center"><c:out value="${member.bankAccountName}" /></td>
			   			<td width="30" align="center"><c:out value="${member.bank}" /></td>
			   			<td width="30" align="center"><c:out value="${member.department}" /></td>
			   			<td width="30" align="center"><c:out value="${member.jobPosition}" /></td>
			   		   		   			<td width="30" align="center"><c:out value="${member.createdBy}" /></td>
			   		   			<td width="30" align="center"><c:out value="${member.deletedBy}" /></td>
			   		   			<td width="30" align="center"><c:out value="${member.modifiedBy}" /></td>
			   		   
		<td width="30" align="center">
			<!-- ini default edit table for each data -->
			<input type="button" name="pilihsaya" onClick="javascript:pilih('<c:out value="${member.memberId}" />')" value="Pilih">
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
	window.opener.setMember (idx);
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
	document.form1.action = "member";
	document.form1.method = "GET";
	document.form1.submit();
}
</script>
