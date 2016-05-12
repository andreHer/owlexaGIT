<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<html>
<body>
<c:out value="${alert}"></c:out>
<form name="form1" method="GET" action="diagnosis" >

 search By :
 <select name="searchby">

 		   			<option name="description" <c:if test="${searchby eq \"description\"}">selected="true"</c:if> >description</option>
			   			<option name="diagnosisCode" <c:if test="${searchby eq \"diagnosisCode\"}">selected="true"</c:if> >diagnosisCode</option>
			   			<option name="diagnosisName" <c:if test="${searchby eq \"diagnosisName\"}">selected="true"</c:if> >diagnosisName</option>
			   			<option name="initialSymptom" <c:if test="${searchby eq \"initialSymptom\"}">selected="true"</c:if> >initialSymptom</option>
			   			<option name="vitalSign" <c:if test="${searchby eq \"vitalSign\"}">selected="true"</c:if> >vitalSign</option>
			   		   		
			   		   </select>
<input type="text" name="searchtext" value="<c:out value="${searchtext}" />">
<input type="button" name="searchButton" value="search" onClick="javascript:cari()">
<br>
<input type="hidden" name="navigation" value="<c:out value="${navigation}" />">
<input type="hidden" name="arah" value="">
	<input type="hidden" name="diagnosisId" value="<c:out value="${diagnosis.diagnosisId}" />">

<table width="95%" height="25" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>

		
		<!-- ini default generated table from database -->
		   			



					<td width="6%" nowrap="nowrap" class="listViewThS1" scope="col">
						<img src="images/blank.gif" alt="asd" height="1" width="1">
						No.
					</td>

					<!-- ini default generated table from database -->

					<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">
						
						<a href="client?navigation=search&sortby=description&searchby=<c:out value="${searchby}" />" class="listViewThLinkS1">Description &nbsp;<img src="images/arrow.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
						
					</td>

					<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">
						
						<a href="client?navigation=search&sortby=address&searchby=<c:out value="${searchby}" />" class="listViewThLinkS1">Diagnosis Code &nbsp;<img src="images/arrow.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
						
					</td>

					<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">
						
						<a href="client?navigation=search&sortby=city&searchby=<c:out value="${searchby}" />" class="listViewThLinkS1">Diagnosis Name &nbsp;<img src="images/arrow.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
						
					</td>

					<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">
						
						<a href="client?navigation=search&sortby=province&searchby=<c:out value="${searchby}" />" class="listViewThLinkS1">Initial Symptom &nbsp;<img src="images/arrow.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
						
					</td>

					<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">
						
						<a href="client?navigation=search&sortby=country&searchby=<c:out value="${searchby}" />" class="listViewThLinkS1">Vital Sign &nbsp;<img src="images/arrow.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
						
					</td>
	
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">
						
						<a href="client?navigation=search&sortby=country&searchby=<c:out value="${searchby}" />" class="listViewThLinkS1">Created By &nbsp;<img src="images/arrow.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
						
					</td>
					
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">
						
						<a href="client?navigation=search&sortby=country&searchby=<c:out value="${searchby}" />" class="listViewThLinkS1">Deleted By &nbsp;<img src="images/arrow.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
						
					</td>
					
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">
						
						<a href="client?navigation=search&sortby=country&searchby=<c:out value="${searchby}" />" class="listViewThLinkS1">Modified By &nbsp;<img src="images/arrow.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
						
					</td>
					
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">
						
						<a href="client?navigation=search&sortby=country&searchby=<c:out value="${searchby}" />" class="listViewThLinkS1">Pilih &nbsp;<img src="images/arrow.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
						
					</td>

					<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
						
						&nbsp;
						
					</td>


				</tr>





				<c:forEach items="${Diagnosiss}" var="diagnosis" varStatus="status">
					
					<tr onMouseOver="'" onMouseOut=""
						onMouseDown="" height="20">
						

						<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
							
							<c:out value="${status.count}" />
							
						</td>
						<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
							
							<c:out value="${diagnosis.description}" />
							
						</td>
						<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
							
							<c:out value="${diagnosis.diagnosisCode}" />
							
						</td>
						<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
							
							<c:out value="${diagnosis.diagnosisName}" />
							
						</td>
						<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
							
							<c:out value="${diagnosis.initialSymptom}" />
							
						</td>
						<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
							
							<c:out value="${diagnosis.vitalSign}" />
							
						</td>
						<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
							
							<c:out value="${diagnosis.createdBy}" />
							
						</td>
						<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
							
							<c:out value="${diagnosis.deletedBy}" />
							
						</td>
						<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
							
							<c:out value="${diagnosis.modifiedBy}" />
							
						</td>


						<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top">

							<input type="button" name="pilihButton" value="choose" align="center" onclick="javascript:pilih('<c:out value="${diagnosis.diagnosisId}" />','<c:out value="Choose" />')" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus">


						</td>
					</tr>
					<tr>
						<td colspan="20" class="listViewHRS1"></td>
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
	window.opener.setDiagnosis (idx);
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
	document.form1.action = "diagnosis";
	document.form1.method = "GET";
	document.form1.submit();
}
</script>

