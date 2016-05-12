<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


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
<br />
<form name="form1" action="provideredcreport" method="POST">
<input type="hidden" name="navigation" value="golistprovideredcreport">
<input type="hidden" name="arah" value="">
<input type="hidden" name="index" value="<c:out value="${index}" />">

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Provider EDC Report</h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>
<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td>
      	<table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tbody>
          <tr>
            <form>
            </form>
			<td class="dataLabel" nowrap="nowrap">Status EDC:
              &nbsp;&nbsp;
              
                <select name="status">					
					<option value="-1">-- All Status --</option>
					<option value="1" <c:if test="${status eq 1 }">selected</c:if>>DEFECTIVE</option>					
					<option value="2" <c:if test="${status eq 2 }">selected</c:if>>LOST SIGNAL</option>
					<option value="3" <c:if test="${status eq 3 }">selected</c:if>>EDC BELUM SAMPAI</option>
					<option value="4" <c:if test="${status eq 4 }">selected</c:if>>OTHER</option>															
				</select>
				
            </td>
            <td class="dataLabel" nowrap="nowrap">Interval EDC Fault:
              &nbsp;&nbsp;
<!--                 <select name="monthStatus">					 -->
<!-- 					<option value="-1" <c:if test="${monthStatus eq -1 }">selected</c:if>>1 Month</option>					 -->
<!-- 					<option value="-2" <c:if test="${monthStatus eq -2 }">selected</c:if>>2 Months</option> -->
<!-- 					<option value="-3" <c:if test="${monthStatus eq -3 }">selected</c:if>>3 Months</option> -->
<!-- 					<option value="-4" <c:if test="${monthStatus eq -4 }">selected</c:if>>4 Months</option> -->
<!-- 					<option value="-5" <c:if test="${monthStatus eq -5 }">selected</c:if>>5 Months</option>															 -->
<!-- 				</select> -->
					<select name="dayStatus">	
						<option value="0" <c:if test="${dayStatus eq 0 }">selected</c:if>>All Interval</option>
						<option value="-30" <c:if test="${dayStatus eq -30 }">selected</c:if>>>30 Days</option>					
						<option value="-60" <c:if test="${dayStatus eq -60 }">selected</c:if>>>60 Days</option>
						<option value="-90" <c:if test="${dayStatus eq -90 }">selected</c:if>>>90 Days</option>
						<option value="-120" <c:if test="${dayStatus eq -120 }">selected</c:if>>>120 Days</option>
						<option value="-160" <c:if test="${dayStatus eq -160 }">selected</c:if>>>160 Days</option>															
					</select>
				
            </td>
            <td class="dataLabel">
              <input title="Search [Alt+Shift+Q]" accesskey="Q" onClick="javascript:cari();" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" name="button" value="Search" type="button">
				<input title="" accesskey="Q" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" name="button" value="Download" type="button" onclick="javascript:downloadExcel()">
			</td>
           </tr>
           <tr>
           <%--
	           	<td class="dataLabel" nowrap="nowrap"> Minimum Date :
	              &nbsp;&nbsp;&nbsp;&nbsp;
	              
	                <input name="minimum_date" id="jscal_field" tab="1" maxlength="10" size="11"  value="<c:out value="${minimum_date}" />" type="text">
					<img src="images/jscalendar.gif" alt="Enter Date" id="jscal_trigger" align="absmiddle" height="18" width="18">
					
					<script type="text/javascript">
	    					Calendar.setup({
	        					inputField     :    "jscal_field",     // id of the input field
	        					ifFormat       :    "%Y-%m-%d",      // format of the input field
	        					button         :    "jscal_trigger",  // trigger for the calendar (button ID)
	        					align          :    "Bl",           // alignment (defaults to "Bl")
	        					singleClick    :    true
	    					});
					 	</script>
	              
				</td>
			 --%>
           </tr> 
		
        </tbody>
      	</table>
      </td>
    </tr>
  </tbody>
</table>


<br />

 	
<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	<tr>
      <td colspan="20" align="right"><table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tbody>
          <tr>
            <td class="listViewPaginationTdS1" align="left"></td>
            <td class="listViewPaginationTdS1" align="right" nowrap="nowrap"  >
				
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
		<td width="4%" nowrap="nowrap" class="listViewThS1" scope="col">
			<img src="images/blank.gif" alt="asd" height="1" width="1">No.</td>		

<!-- ini default generated table from database -->
		   		   		   		   		   		   			
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
					Terminal ID
				</td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
					Provider ID
				</td>
			   	<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">
					Provider Name
				</td>
				<td scope="col" class="listViewThS1" width="25%">
					Address
				</td>		   		   			
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
					Date Not Used
				</td>		
<!-- 				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%"> -->
<!-- 					Total Transaction -->
<!-- 				</td> -->
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
					Status
				</td>		
	</tr>


	<c:forEach items="${ProviderEdcs}" var="providerEdcReport" varStatus="status" >
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
			<c:out value="${providerEdcReport.deviceNumber }"/>
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<c:out value="${providerEdcReport.providerId.edcCode}" />
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<c:out value="${providerEdcReport.providerId.providerName}" />
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top">
			<c:out value="${providerEdcReport.providerId.address}" />
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<c:if test="${not empty providerEdcReport.lastTransaction }">
				<c:set var="startdate" value="${providerEdcReport.lastTransaction }"></c:set>
				<fmt:parseNumber value="${startdate.time / (1000*60*60*24) }" integerOnly="true" var="starttime" scope="page"/>
				<c:set var="currentdate" value="<%=new java.util.Date()%>"></c:set>
				<fmt:parseNumber value="${currentdate.time / (1000*60*60*24) }" integerOnly="true" var="currenttime" scope="request"/>
				<c:set var="interval" value="${(currenttime-starttime) }"></c:set>
				<c:out value="${interval }"></c:out> Day/s
			</c:if>
		</td>
<!-- 		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"> -->
<!-- 		</td> -->
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<c:choose>
				<c:when test="${providerEdcReport.isFault eq 1 }">
					DEFECTIVE
				</c:when>
				<c:when test="${providerEdcReport.isFault eq 2 }">
					LOST SIGNAL
				</c:when>
				<c:when test="${providerEdcReport.isFault eq 3 }">
					EDC BELUM SAMPAI
				</c:when>
				<c:otherwise>
					OTHERS
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
   
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	
	</c:forEach>
	
<tr>
            <td class="listViewPaginationTdS1" align="left"></td>
            <td class="listViewPaginationTdS1" align="right" nowrap="nowrap"  colspan=20>
				
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
	

<script language="Javascript">
<%
String nav="";
if(request.getAttribute("navigation").equals("golist")||request.getAttribute("navigation").equals("golookup")||request.getAttribute("navigation").equals("golistprovideredcreport")
	||request.getAttribute("navigation").equals("edcreport")){
	nav = (String)request.getAttribute("navigation");
}
%>
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
	document.form1.navigation.value = "golistprovideredcreport";
	document.form1.action = "provideredcreport";
	document.form1.method = "POST";
	document.form1.submit();
}
function detil (idx){
	document.form1.method = "POST";
	document.form1.caseId.value = idx;
	document.form1.action = "case";
	document.form1.navigation.value = "detail";
	document.form1.submit();

}
function downloadExcel (){
	document.form1.navigation.value = "downloadExcelProviderEDCReport";
	document.form1.action = "provideredcreport";
	document.form1.method = "POST";
	document.form1.submit();
}
</script>
