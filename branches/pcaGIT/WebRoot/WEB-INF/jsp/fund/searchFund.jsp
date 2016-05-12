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

<form name="form1" action="fund" method="POST">
<input type="hidden" name="navigation" value="gosearch">
<input type="hidden" name="sortcolumn" value="<c:out value="${sortcolumn}" />">
<input type="hidden" name="sortorder" value="<c:out value="${sortorder}" />">
<input type="hidden" name="columntosort" value="<c:out value="${columntosort}" />">
<input type="hidden" name="arah" value="">
<input type="hidden" name="index" value="<c:out value="${index}" />">
<input type="hidden" name="fundId" value="<c:out value="${fund.fundId}" />">
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Search Fund</h3></td>
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

 		   		   		   		   			<option value="fundCode" <c:if test="${searchby eq \"fundCode\"}">selected="true"</c:if> >Fund Code</option>
			   			<option value="description" <c:if test="${searchby eq \"description\"}">selected="true"</c:if> >Description</option>
			   			<option value="fund_category" <c:if test="${searchby eq \"fund_category\"}">selected="true"</c:if> >Fund Category</option>
			   			<option value="client" <c:if test="${searchby eq \"client\"}">selected="true"</c:if> >Client</option>
			   </select>
				
              </td>
			  <td class="dataLabel" nowrap="nowrap">Status:
              &nbsp;&nbsp;
              
                <select name="searchstatus">					
					<option value="">OK</option>
					<option value="">Reject</option>
				</select>
				
              </td>
            <td class="dataLabel">
              <input title="Search [Alt+Shift+Q]" accesskey="Q" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" name="button" value="Search" type="button" onclick="javascript:cari()">
                          
			</td>
            </tr>
			<tr>
            
            <td class="dataLabel" nowrap="nowrap"> Minimum Date :
              &nbsp;&nbsp;&nbsp;&nbsp;
              
                <input name="minimum_date" id="jscal_field" tab="1" maxlength="10" size="11" readonly="readonly" value="<c:out value="${minimumDate}" />" type="text">
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
            <td class="dataLabel" nowrap="nowrap"> Maximum Date :
              &nbsp;&nbsp;&nbsp;&nbsp;
              
            <input name="maximum_date" id="jscal_field_max" tabindex="1" maxlength="10" size="11" readonly="readonly" value="<c:out value="${maximumDate}" />" type="text">
			<img src="images/jscalendar.gif" alt="Enter Date" id="jscal_max_trigger" align="absmiddle" height="18" width="18">
			
					<script type="text/javascript">
    					Calendar.setup({
        					inputField     :    "jscal_field_max",     // id of the input field
        					ifFormat       :    "%Y-%m-%d",      // format of the input field
        					button         :    "jscal_max_trigger",  // trigger for the calendar (button ID)
        					align          :    "Bl",           // alignment (defaults to "Bl")
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


<br />	
<input type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:tambahi()" value=" Request Floating Fund ">
<br />
<br />
 	
<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	<tr>
      <td colspan="20" align="right"><table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tbody>
          <tr>
            <td  align="left"></td>
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
		<td width="5%" nowrap="nowrap" class="listViewThS1" scope="col">
			<img src="images/blank.gif" alt="asd" height="1" width="1">No.</td>
			<c:choose>
				<c:when test="${sortFundCode }">
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="20%">Fund Code &nbsp;
						<input type="hidden" name=sortByOrderCode value="true">
						<a href="javascript:carisortfundcode()" class="listViewThLinkS1">
						<img src="images/arrow_up.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
					</td>
				</c:when>
				<c:otherwise>
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="20%">Fund Code &nbsp;
						<input type="hidden" name=sortByOrderCode value="false">
						<a href="javascript:carisortfundcode()" class="listViewThLinkS1">
						<img src="images/arrow_down.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
					</td>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${sortFundCat }">
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="20%">Fund Category&nbsp;
						<input type="hidden" name=sortByOrderCategory value="true">
						<a href="javascript:carisortfundcategory()" class="listViewThLinkS1">
						<img src="images/arrow_up.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
					</td>
				</c:when>
				<c:otherwise>
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="20%">Fund Category&nbsp;
						<input type="hidden" name=sortByOrderCategory value="false">
						<a href="javascript:carisortfundcategory()" class="listViewThLinkS1">
						<img src="images/arrow_down.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
					</td>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${sortClient }">
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="20%">Client &nbsp;
						<input type="hidden" name=sortByOrderClient value="true">
						<a href="javascript:carisortclient()" class="listViewThLinkS1">
						<img src="images/arrow_up.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
					</td>
				</c:when>
				<c:otherwise>
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="20%">Client &nbsp;
						<input type="hidden" name=sortByOrderClient value="false">
						<a href="javascript:carisortclient()" class="listViewThLinkS1">
						<img src="images/arrow_down.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
					</td>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${sortFundValue }">
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="20%">Fund Value &nbsp;
						<input type="hidden" name=sortByOrderFundValue value="true">
						<a href="javascript:carisortfundvalue()" class="listViewThLinkS1">
						<img src="images/arrow_up.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
					</td>
				</c:when>
				<c:otherwise>
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="20%">Fund Value &nbsp;
						<input type="hidden" name=sortByOrderFundValue value="false">
						<a href="javascript:carisortfundvalue()" class="listViewThLinkS1">
						<img src="images/arrow_down.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
					</td>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${sortStatus }">
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">Status&nbsp;
						<input type="hidden" name=sortByOrderStatus value="true">
						<a href="javascript:carisortfundstatus()" class="listViewThLinkS1">
						<img src="images/arrow_up.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
					</td>
				</c:when>
				<c:otherwise>
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">Status&nbsp;
						<input type="hidden" name=sortByOrderStatus value="false">
						<a href="javascript:carisortfundstatus()" class="listViewThLinkS1">
						<img src="images/arrow_down.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
					</td>
				</c:otherwise>
			</c:choose>
	</tr>


	<c:forEach items="${Funds}" var="fund" varStatus="status" >
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
			<a href="fund?navigation=detail&fundId=<c:out value="${fund.fundId}" />" class="linkDetail"><c:out value="${fund.fundCode}" /></a>			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${fund.fundType.fundCategoryName}" />			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<a href="client?navigation=detail&clientId=<c:out value="${fund.clientId.clientId}" />" class="linkDetail"><c:out value="${fund.clientId.clientName}" /></a>			
		</td>
		  <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${fund.fundCurrency.currencyCode}" />  <fmt:formatNumber><c:out value="${fund.fundValue}" /></fmt:formatNumber>
			
		</td>
						   		   		   		
					   		   		
					   		   		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top">
			<c:if test="${fund.fundStatus.paymentStatusId eq 1}">OPEN</c:if>
			<c:if test="${fund.fundStatus.paymentStatusId eq 2}">PAID</c:if>
			<c:if test="${fund.fundStatus.paymentStatusId eq 3}">APPROVED</c:if>
			<c:if test="${fund.fundStatus.paymentStatusId eq 4}">BAD</c:if>
			<c:if test="${fund.fundStatus.paymentStatusId eq 5}">PENDING</c:if>
			<c:if test="${fund.fundStatus.paymentStatusId eq 7}">PARTIAL PAID</c:if>
			
			
	  	</td>
    </tr>
   
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	
	</c:forEach>
	
<tr>
            <td  align="left"></td>
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
	document.form1.action = "fund-form";
	document.form1.method = "GET";
	document.form1.submit();
}
function hapus (idx){
	var delConfirm = window.confirm ("Are You Sure Want To Delete This Entry ?");

	if (delConfirm){
		document.form1.method = "POST";
		document.form1.fundId.value = idx;
		document.form1.action = "fund";
		document.form1.navigation.value = "delete";
		document.form1.submit();
	}
}
function ubah (idx){
	document.form1.method = "GET";
	document.form1.fundId.value = idx;
	document.form1.action = "fund-form";
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
	document.form1.action = "fund";
	document.form1.method = "POST";
	document.form1.submit();
}
function detil (idx){
	document.form1.method = "POST";
	document.form1.fundId.value = idx;
	document.form1.action = "fund";
	document.form1.navigation.value = "detail";
	document.form1.submit();

}
function carisortfundcode(){
	document.form1.navigation.value = "gosearchbysort";
	document.form1.sortcolumn.value="fundcode";
	document.form1.method = "POST";
	document.form1.submit();
}
function carisortfundcategory(){
	document.form1.navigation.value = "gosearchbysort";
	document.form1.sortcolumn.value="fundcategory";
	document.form1.method = "POST";
	document.form1.submit();
}
function carisortclient(){
	document.form1.navigation.value = "gosearchbysort";
	document.form1.sortcolumn.value="client";
	document.form1.method = "POST";
	document.form1.submit();
}
function carisortfundvalue(){
	document.form1.navigation.value = "gosearchbysort";
	document.form1.sortcolumn.value="fundvalue";
	document.form1.method = "POST";
	document.form1.submit();
}
function carisortfundstatus(){
	document.form1.navigation.value = "gosearchbysort";
	document.form1.sortcolumn.value="status";
	document.form1.method = "POST";
	document.form1.submit();
}
</script>
