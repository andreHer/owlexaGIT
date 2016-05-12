<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.ametis.cms.datamodel.Payment" %>
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

<form name="form1" action="payment" method="POST">
	<input type="hidden" name="navigation" value="gosearch">
	<input type="hidden" name="sortcolumn" value="<c:out value="${sortcolumn}" />">
	<input type="hidden" name="sortorder" value="<c:out value="${sortorder}" />">
	<input type="hidden" name="columntosort" value="<c:out value="${columntosort}" />">
	<input type="hidden" name="arah" value="">
	<input type="hidden" name="index" value="<c:out value="${index}" />">
	<input type="hidden" name="paymentId" value="<c:out value="${payment.paymentId}" />">
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tbody>
		<tr>
			<td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Search Payment</h3></td>
			<td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
		</tr>
		</tbody>
	</table>
	<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="60%">
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

							<option value="invoiceNumber" <c:if test="${searchby eq \"invoiceNumber\"}">selected="true"</c:if> >No Invoice</option>
							<option value="paymentNumber" <c:if test="${searchby eq \"paymentNumber\"}">selected="true"</c:if> >Payment Number</option>
							<option value="batchClaimNumber" <c:if test="${searchby eq \"batchClaimNumber\"}">selected="true"</c:if> >Batch Claim Number</option>
							<option value="bankName" <c:if test="${searchby eq \"bankName\"}">selected="true"</c:if> >Bank Name</option>
							<option value="accountNumber" <c:if test="${searchby eq \"accountNumber\"}">selected="true"</c:if> >Account Number</option>
							<option value="giroNumber" <c:if test="${searchby eq \"giroNumber\"}">selected="true"</c:if> >Voucher Number</option>
							<option value="payeeName" <c:if test="${searchby eq \"payeeName\"}">selected="true"</c:if> >Payee Name</option>
							
							<option value="officer" <c:if test="${searchby eq \"officer\"}">selected="true"</c:if> >Officer Name</option>
							<option value="memberName" <c:if test="${searchby eq \"memberName\"}">selected="true"</c:if> >Member Name</option>
							<option value="memberNumber" <c:if test="${searchby eq \"memberNumber\"}">selected="true"</c:if> >Member Number</option>
							<option value="providerName" <c:if test="${searchby eq \"providerName\"}">selected="true"</c:if> >Provider Name</option>
							<option value="clientName" <c:if test="${searchby eq \"clientName\"}">selected="true"</c:if> >Client Name</option>
							<option value="groupName" <c:if test="${searchby eq \"groupName\"}">selected="true"</c:if> >Group Name</option>

						</select>

					</td>

					<td class="dataLabel" nowrap="nowrap">Status:
						&nbsp;&nbsp;

						<select name="searchstatus">
							<option value="">-- All Status --</option>
							<option value="1" <c:if test="${searchstatus eq 1 }">selected</c:if> >OPEN</option>
							<option value="2" <c:if test="${searchstatus eq 2 }">selected</c:if>>PAID</option>
							<option value="3" <c:if test="${searchstatus eq 3 }">selected</c:if>>APPROVED</option>
							<option value="4" <c:if test="${searchstatus eq 4 }">selected</c:if>>BAD</option>
							<option value="5" <c:if test="${searchstatus eq 5 }">selected</c:if>>PENDING</option>
							<option value="6" <c:if test="${searchstatus eq 6 }">selected</c:if>>DISPOSITIONED</option>
							<option value="7" <c:if test="${searchstatus eq 7 }">selected</c:if>>PARTIAL PAID</option>

						</select>

					</td>
					<td class="dataLabel">
						<input title="Search [Alt+Shift+Q]" accesskey="Q" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" name="buttonSearch" value="Search" type="button" onclick="javascript:cari();">
						<input title="Search [Alt+Shift+Q]" accesskey="Q" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" name="buttonDownload" value="Download Payment" type="button" onclick="javascript:exportDisposition()">

					</td>
				</tr>


				<tr>

					<td class="dataLabel" nowrap="nowrap"> Minimum Date :
						&nbsp;&nbsp;&nbsp;&nbsp;

						<input name="minimum_date" id="jscal_field" tab="1" maxlength="10" size="11" value="<c:out value="${minimumDate}" />" type="text">
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

						<input name="maximum_date" id="jscal_field_max" tabindex="1" maxlength="10" size="11" value="<c:out value="${maximumDate}" />" type="text">
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
					<td class="dataLabel" nowrap="nowrap">Date By:
						&nbsp;&nbsp;
						<select name="dateBy">							
							<option value="paymentTime" <c:if test="${dateBy eq 'paymentTime' }">selected</c:if> >CDV Date</option>
							<option value="batchClaim.invoiceDate" <c:if test="${dateBy eq 'batchClaim.invoiceDate' }">selected</c:if>>Invoice Date</option>
							<option value="batchClaim.batchClaimDate" <c:if test="${dateBy eq 'batchClaim.batchClaimDate' }">selected</c:if>>Receive Date</option>
							<option value="batchClaim.batchDueDate" <c:if test="${dateBy eq 'batchClaim.batchDueDate' }">selected</c:if>>Due Date</option>
							<option value="dispositionDate" <c:if test="${dateBy eq 'dispositionDate' }">selected</c:if>>Submission Date</option>
							<option value="paymentConfirmDate" <c:if test="${dateBy eq 'paymentConfirmDate' }">selected</c:if>>Payment Confirm Date</option>
						</select>
					</td>
					<td class="dataLabel" nowrap="nowrap">Claim Type :
						&nbsp;&nbsp;
						<select name="claimType">
							<option value="">-- All Status --</option>
							<option value="1" <c:if test="${claimType eq 1 }">selected</c:if> >REIMBURSEMENT</option>
							<option value="2" <c:if test="${claimType eq 2 }">selected</c:if>>CASHLESS</option>
						</select>
					</td>
				</tr>
				</tbody>
			</table></td>
		</tr>
		</tbody>
	</table>

	<!-- Table Toolbar Stop -->

	<!-- Table Content Start -->

	<br />


	<table class="listView" cellspacing="0" cellpadding="0" width="100%">
		<tbody>
		<tr>
			<td class="listViewPaginationTdS1" align="left"></td>
			<td class="listViewPaginationTdS1" align="right" nowrap="nowrap"  colspan=26>

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
		<tr height="20">
			<td width="2%" nowrap="nowrap" class="listViewThS1" scope="col">No.</td>

			<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Batch Number &nbsp;
			</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Member Group &nbsp;
			</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">Client &nbsp;
			</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Provider &nbsp;
			</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">No Invoice &nbsp;
			</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">Tanggal Invoice &nbsp;
			</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">Member Name &nbsp;
			</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">Member Number &nbsp;
			</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">Received Date &nbsp;
			</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">Due Date &nbsp;
			</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">Submission Date &nbsp;
			</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">Payment Date &nbsp;
			</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">Batch Value &nbsp;
			</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">Paid Value &nbsp;
			</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">Pending Value &nbsp;
			</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">Total Claim &nbsp;
			</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">Status &nbsp;
			</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">CDV Number &nbsp;
			</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">CDV Date &nbsp;
			</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">CDV Payment Confirmation &nbsp;
			</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">Bank &nbsp;
			</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">Nomor Surat Jalan &nbsp;
			</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">Type Claim &nbsp;
			</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">Officer &nbsp;
			</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">Modified Time &nbsp;
			</td>
		</tr>


		<%
			Map<Integer, Integer> totalClaimMap = (Map) request.getAttribute("totalClaimMap");
			Map<Integer, String> memberGroupMap = (Map) request.getAttribute("memberGroupMap");
		%>
		<c:forEach items="${Payments}" var="payment" varStatus="status" >
			<%	if (i % 2 == 0) {
				rowclass = "col1";
			} else if (i % 2 != 0) {
				rowclass= "col2";
			}
				i++;
			%>

			<%
				Payment payment = (Payment) pageContext.getAttribute("payment");
			%>

		<tr height="20">
			<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><%=(i+((indexint-1)*countSet))%></td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<a href="batchclaim?navigation=detail&index=<c:out value="${index}" />&batchClaimId=<c:out value="${payment.batchClaim.batchClaimId}" />" class="linkDetail">
					<c:out value="${payment.batchClaim.batchClaimNumber}" />
				</a>
			</td>
			<%--member group--%>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<% 
				if (memberGroupMap != null && payment.getPaymentId() != null){
				%>
					<%=memberGroupMap.get(payment.getPaymentId()) %> 
				<%
				}
				%>
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<c:if test="${payment.batchClaim != null && payment.batchClaim.clientId != null}">
					<c:out value="${payment.batchClaim.clientId.clientName}"/>
				</c:if>
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<c:if test="${payment.batchClaim != null && payment.batchClaim.providerId != null}">
					<c:out value="${payment.batchClaim.providerId.providerName}"/>
				</c:if>
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<c:if test="${payment.batchClaim != null}">
					<c:out value="${payment.batchClaim.invoiceNumber}"/>
				</c:if>
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<c:if test="${payment.batchClaim != null}">
					<c:out value="${payment.batchClaim.invoiceDate}"/>
				</c:if>
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<c:if test="${payment.memberId != null}">
					<c:out value="${payment.memberId.firstName}"/>
				</c:if>
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<c:if test="${payment.memberId != null}">
					<c:out value="${payment.memberId.customerPolicyNumber}"/>
				</c:if>
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<c:if test="${payment.batchClaim != null}">
					<c:out value="${payment.batchClaim.batchClaimDate}"/>
				</c:if>
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<c:if test="${payment.batchClaim != null}">
					<c:out value="${payment.batchClaim.batchDueDate}"/>
				</c:if>
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<c:out value="${payment.paymentTime}"/>
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<c:out value="${payment.paymentConfirmTime}"/>
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
				<c:if test="${payment.batchClaim != null}">
					<fmt:formatNumber><c:out value="${payment.batchClaim.batchClaimInitialValue}"/></fmt:formatNumber>
				</c:if>
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
				<fmt:formatNumber><c:out value="${payment.paymentValue}"/></fmt:formatNumber>
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
				<c:if test="${payment.outstandingId != null}">
					<fmt:formatNumber><c:out value="${payment.outstandingId.outstandingValue}"/></fmt:formatNumber>
				</c:if>
			</td>

			<%--total claim--%>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<%
				if (totalClaimMap != null && payment.getPaymentId() != null){
					
				%>
				<%= totalClaimMap.get(payment.getPaymentId())%> 
				<%
				}
				%>
			</td>

			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<c:if test="${payment.paymentStatus.paymentStatusId eq 1}">OPEN</c:if>
				<c:if test="${payment.paymentStatus.paymentStatusId eq 2}">PAID</c:if>
				<c:if test="${payment.paymentStatus.paymentStatusId eq 3}">APPROVED</c:if>
				<c:if test="${payment.paymentStatus.paymentStatusId eq 4}">BAD</c:if>
				<c:if test="${payment.paymentStatus.paymentStatusId eq 5}">PENDING</c:if>
				<c:if test="${payment.paymentStatus.paymentStatusId eq 6}">DISPOSITIONED</c:if>
				<c:if test="${payment.paymentStatus.paymentStatusId eq 7}">PARTIAL PAID</c:if>
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<a href="payment?navigation=detail&index=<c:out value="${index}" />&paymentId=<c:out value="${payment.paymentId}" />" class="linkDetail"><c:out value="${payment.paymentNumber}" /></a>
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<c:out value="${payment.paymentTime}"/>
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<c:out value="${payment.paymentConfirmDate}"/>
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<c:out value="${payment.bankName} - ${payment.bankBranch} - ${payment.bankCity}"/>
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<c:out value="${payment.paymentNumber}"/>
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<c:if test="${payment.batchClaim != null && payment.batchClaim.batchClaimType != null}">
					<c:out value="${payment.batchClaim.batchClaimType.claimTypeName}"/>
				</c:if>
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<c:out value="${payment.modifiedBy == null ? payment.createdBy : payment.modifiedBy}" />
			</td>
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
				<c:out value="${payment.modifiedTime == null ? payment.createdTime : payment.modifiedTime}" />
			</td>

		</tr>

		</c:forEach>

		<tr>
			<td class="listViewPaginationTdS1" align="left"></td>
			<td class="listViewPaginationTdS1" align="right" nowrap="nowrap"  colspan=25>

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
	$(document).ready(function(){
		//alert("jQuery Ready");
		/*Add by aju on 20150407, for active menu...fiuuuhhhhhh*/
		var mnuName = ($("select[name=searchstatus]").val()=="1"?"mnuClaimPaymentApproval":null);
		//alert($("select[name=searchstatus]").val());
		if(mnuName != null){
			var nav = $("#mnuMainClaim");
			nav.addClass("active");

			var nav = $("#"+mnuName);
			nav.addClass("active");
		}
	});

	<%
    String nav="";
    if(request.getAttribute("navigation").equals("gosearch")||request.getAttribute("navigation").equals("golookup")){
        nav = (String)request.getAttribute("navigation");
    }
    %>
	function tambahi (){
		document.form1.navigation.value = "tambah";
		document.form1.action = "payment-form";
		document.form1.method = "GET";
		document.form1.submit();
	}
	function hapus (idx){
		var delConfirm = window.confirm ("Are You Sure Want To Delete This Entry ?");

		if (delConfirm){
			document.form1.method = "POST";
			document.form1.paymentId.value = idx;
			document.form1.action = "payment";
			document.form1.navigation.value = "delete";
			document.form1.submit();
		}
	}
	function ubah (idx){
		document.form1.method = "GET";
		document.form1.paymentId.value = idx;
		document.form1.action = "payment-form";
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
		document.form1.action = "payment";
		document.form1.method = "POST";
		document.form1.submit();
	}
	function detil (idx){
		document.form1.method = "POST";
		document.form1.paymentId.value = idx;
		document.form1.action = "payment";
		document.form1.navigation.value = "detail";
		document.form1.submit();

	}
	function exportDisposition(){
		document.form1.method = "POST";
		document.form1.navigation.value = "exportdeliver";
		document.form1.action = "payment";
		document.form1.submit();
	}
	function carisort(param){
		document.form1.navigation.value = "gosearchbysort";
		document.form1.searchtext.value="<c:out value="${searchtext}" />";
		document.form1.searchby.value="<c:out value="${searchby}" />";
		document.form1.sortcolumn.value= param;
		document.form1.method = "POST";
		document.form1.submit();
	}
</script>