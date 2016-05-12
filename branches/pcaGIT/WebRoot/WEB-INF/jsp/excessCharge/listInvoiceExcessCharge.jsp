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
<ul id="maintab" class="shadetabs">
		
			<li >
				<a href="invoice?navigation=detail&invoiceId=<c:out value="${invoiceId}" />" rel="tcontent1">Detail Invoice</a>
			</li>
			<li class="selected">
				<a href="#" rel="tcontent2">Excess Charge List</a>
			</li>	
		</ul>
<br />
<form name="form1" action="excesscharge" method="POST">
<input type="hidden" name="navigation" value="listpayment">
<input type="hidden" name="subnavigation" value="<c:out value="${subnavigation}" />" >
<input type="hidden" name="currentnavigation" value="listpayment" />
<input type="hidden" name="arah" value="">
<input type="hidden" name="batchClaimId" value="<c:out value="${batchClaimId}" />" />
<input type="hidden" name="paymentId" value="<c:out value="${paymentId}" />" />
<input type="hidden" name="index" value="<c:out value="${index}" />">
<input type="hidden" name="invoiceId" value="<c:out value="${invoiceId}" />" />
<input type="hidden" name="excessChargeId" value="<c:out value="${excessCharge.excessChargeId}" />">
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"></td>
      <td width="100%"></td>
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
 		   		   		<option value="remarks" <c:if test="${searchby eq \"remarks\"}">selected="true"</c:if> >Remarks</option>
 		   		   		<option value="excessNumber" <c:if test="${searchby eq \"excessNumber\"}">selected="true"</c:if> >Excess Number</option>
	   		   	</select>
				            </td>
			  <td class="dataLabel" nowrap="nowrap">Status:
              &nbsp;&nbsp;
              
                <select name="status">					
                	<option value="-1">-- All Status --</option>
					<option value="1">Open</option>
					<option value="2">Paid</option>
					<option value="4">Bad Debt</option>
					
				</select>
				
              </td>
            <td class="dataLabel">
              <input title="Search [Alt+Shift+Q]" accesskey="Q" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" name="button" value="Search" type="submit">  
			</td>
            </tr>
        </tbody>
      </table></td>
    </tr>
  </tbody>
</table>

	
	<!-- Table Content Start -->
	
<br />

 	
<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	<tr>
      <td colspan="20" align="right"><table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tbody>
          <tr>
            <td  align="left">
			</td>
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
		<td width="2%" nowrap="nowrap" class="listViewThS1" scope="col">No.</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Excess Number &nbsp;</td>	   		   		   			
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Member Number &nbsp;</td>	   		   		   							
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%">Excess Date &nbsp;</td>	   		   		   							
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%">Status &nbsp;</td>	   		   		   							
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%">Reminder Date &nbsp;</td>	   		   		   							
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%">Nilai Excess &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%">Paid Excess &nbsp;</td>	   		   		   							
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%">Outstanding &nbsp;</td>
   	   	<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">&nbsp;</td>
	</tr>


	<c:forEach items="${ExcessCharges}" var="excessCharge" varStatus="status" >
	<%	if (i % 2 == 0) {
			rowclass = "col1";
		} else if (i % 2 != 0) {
			rowclass= "col2";
		}
		i++;
	%>
	 <tr height="20">
      	<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><%=(i+((indexint-1)*countSet))%></td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${excessCharge.excessChargeNumber}" /></td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${excessCharge.memberId.customerNumber}" /></td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${excessCharge.excessChargeTime}" /></td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
		
			<c:if test="${excessCharge.excessChargeStatus.paymentStatusId eq 1}">OPEN</c:if>
			<c:if test="${excessCharge.excessChargeStatus.paymentStatusId eq 2}">PAID</c:if>
			<c:if test="${excessCharge.excessChargeStatus.paymentStatusId eq 3}">APPROVED</c:if>
			<c:if test="${excessCharge.excessChargeStatus.paymentStatusId eq 4}">BAD</c:if>
			<c:if test="${excessCharge.excessChargeStatus.paymentStatusId eq 5}">PENDING</c:if>
			<c:if test="${excessCharge.excessChargeStatus.paymentStatusId eq 7}">PARTIAL PAID</c:if>
		   - 
		  	<c:if test="${excessCharge.excessLetterSent eq 1}">LETTER SENT</c:if>
		  	<c:if test="${excessCharge.excessLetterSent eq 0}">LETTER NOT SENT</c:if>
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${excessCharge.nextReminderTime}" /></td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
			<fmt:formatNumber>
				<c:out value="${excessCharge.claimId.claimExcessValue}" />
			</fmt:formatNumber>			
		</td>		   		   		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
			<fmt:formatNumber>
				<c:out value="${excessCharge.excessPaidValue}" />
			</fmt:formatNumber>			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
			<b><fmt:formatNumber>
				<c:out value="${excessCharge.outstanding}" />
			</fmt:formatNumber></b>			
		</td>
	 	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top">
	 		<c:if test="${excessCharge.excessPaidValue < excessCharge.excessChargeValue}">
	 		<a href="javascript:payExcess(<c:out value="${excessCharge.excessChargeId}" />)">
				<img src="images/pay.png" class="action_icon" alt="Payment" title="Payment"></a>&nbsp;&nbsp;
			
	 		<a href="javascript:printExcess(<c:out value="${excessCharge.excessChargeId}" />)">
				<img src="images/print.gif" class="action_icon" alt="Print" title="Print"></a>
			</c:if>
	  	</td>
    </tr>
   
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	
	</c:forEach>
	
<tr>
            <td  align="left">
            </td>
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
function printExcess (id){
	window.open ("excesscharge?navigation=print&url=detailExcessCharge&excessChargeId="+id,"Search","width=1024, height=768, menubar=yes, status=no, toolbar=no, scrollbars=yes, resizable=yes");
}


function payExcess(id){
	document.form1.navigation.value = "listinvoiceexcess";
	document.form1.action = "fund-form";
	document.form1.method = "GET";
	document.form1.excessChargeId.value = id;
	document.form1.submit();
}

function goleft(){
	document.form1.navigation.value = "listpayment";
	document.form1.arah.value="kiri";
	document.form1.method = "POST";
	document.form1.submit();
}
function goleftbgt(){
	document.form1.navigation.value = "listpayment";
	document.form1.arah.value="kiribgt";
	document.form1.method = "POST";
	document.form1.submit();
}
function goright(){
	document.form1.navigation.value = "listpayment";
	document.form1.arah.value="kanan";
	document.form1.method = "POST";
	document.form1.submit();
}
function gorightbgt(){
	document.form1.navigation.value = "listpayment";
	document.form1.arah.value="kananbgt";
	document.form1.method = "POST";
	document.form1.submit();
}
function go(){
	document.form1.navigation.value = "listpayment";
	document.form1.submit();
}
function cari (){
	document.form1.navigation.value = "gosearch";
	document.form1.action = "excesscharge";
	document.form1.method = "POST";
	document.form1.submit();
}
function detil (idx){
	document.form1.method = "POST";
	document.form1.excessChargeId.value = idx;
	document.form1.action = "excesscharge";
	document.form1.navigation.value = "detail";
	document.form1.submit();

}
</script>
