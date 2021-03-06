<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>

<script type="text/javascript" src="scripts/tinymce/tinymce.min.js"></script>
<script type="text/javascript">
tinymce.init({
    selector: "textarea",
    readonly : true,
	toolbar: "false",
	menubar: "false",
	height: '100%'
 });
</script>


<%String navigation = WebUtil.getAttributeString(request,
					"navigation", "");

			%>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;List Group Member</h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>

<%@ include file="../mainContentGroup.jsp" %>


<%String alert = (String) request.getAttribute("alert");
			int index = 0;
			int totalIndex = 0;
			int count = 0;
			int countSet = 0;

			try {
				index = ((Integer) request.getAttribute("index")).intValue();
				count = ((Integer) request.getAttribute("count")).intValue();
				countSet = ((Integer) request.getAttribute("countSet"))
						.intValue();
				totalIndex = ((Integer) request.getAttribute("halAkhir"))
						.intValue();

			} catch (Exception e) {
			}

			if (alert != null && !alert.trim().equals("")) {%>
<div id="warning" align="center">
	<c:out value="${alert}"></c:out>
</div>
<%}%>

<%String rowclass = "";
			int i = 0;
			int indexint = Integer.parseInt(request.getAttribute("index")
					.toString());
			WebUtil.getAttributeInteger(request, "index", 0).intValue();

			%>
<!-- Search Container Start -->
<br />

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Search Group Member</h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>

<form name="form1" action="member" method="POST">
	<input type="hidden" name="navigation" value="golistgroup">
	<input type="hidden" name="arah" value="">
	<input type="hidden" name="index" value="<c:out value="${index}" />">
	<input type="hidden" name="memberId" value="<c:out value="${member.memberId}" />">
	<input type="hidden" name="memberGroupId" value="<c:out value="${memberGroup.memberGroupId}" />">
	<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
		<tbody>
			<tr>
				<td>
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tbody>
							<tr>
								<form>
								</form>
								<td class="dataLabel" nowrap="nowrap">
									
									Search Keyword:
									
									&nbsp;&nbsp;
									
									<input size="20" name="searchtext" class="dataField" value="<c:out value="${searchtext}"/>" type="text">
									
								</td>
								<td class="dataLabel" nowrap="nowrap">
									
									Search Category:
									
									&nbsp;&nbsp;
									

									<select name="searchby" class="inputbox">

										<option value="firstName" <c:if test="${searchby eq \"firstName\"}">selected="true"</c:if>>
											First Name
										</option>
										<option value="lastName" <c:if test="${searchby eq \"lastName\"}">selected="true"</c:if>>
											Last Name
										</option>										
										<option value="customerNumber" <c:if test="${searchby eq \"customerNumber\"}">selected="true"</c:if>>
											Customer Number
										</option>										
										<option value="customerPolicyNumber" <c:if test="${searchby eq \"customerPolicyNumber\"}">selected="true"</c:if>>
											Customer Policy Number
										</option>
										<option value="address" <c:if test="${searchby eq \"address\"}">selected="true"</c:if>>
											Address
										</option>
										<option value="province" <c:if test="${searchby eq \"province\"}">selected="true"</c:if>>
											Province
										</option>										
										<option value="clientName" <c:if test="${searchby eq \"clientName\"}">selected="true"</c:if>>
											Client Name
										</option>
										<option value="groupName" <c:if test="${searchby eq \"groupName\"}">selected="true"</c:if>>
											Group Name
										</option>
									</select>

									
								</td>
								<td class="dataLabel" nowrap="nowrap">
									
									Status:
									
									&nbsp;&nbsp;
									
									<select name="status">
										<option value="">
											All Status
										</option>
										<option value="1">
											Active
										</option>
										<option value="2">
											Terminated
										</option>
										<option value="3">
											Resigned
										</option>
										<option value="-1">
											Pending
										</option>											
									</select>

									
								</td>
								<td class="dataLabel">
									
									
									<input title="Search [Alt+Shift+Q]" accesskey="Q" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" name="button" value="Search" type="submit">
									
								</td>
							</tr>
							<tr>

								<!--<td class="dataLabel" nowrap="nowrap">
									
									Minimum Date :
									
									&nbsp;&nbsp;&nbsp;&nbsp;
									
									<input name="minimum_date" id="jscal_field" tab="1" maxlength="10" size="11" readonly="readonly" value="" type="text">
									<img src="images/jscalendar.gif" alt="Enter Date" id="jscal_trigger" align="absmiddle" height="18" width="18">

									<script type="text/javascript">
    					Calendar.setup({
        					inputField     :    "jscal_field",     // id of the input field
        					ifFormat       :    "%Y-%m-%d",      // format of the input field
        					button         :    "jscal_trigger",  // trigger for the calendar (button ID)
        					align          :    "Tl",           // alignment (defaults to "Bl")
        					singleClick    :    true
    					});
				 	</script>
									
								</td>
								--><!--<td class="dataLabel" nowrap="nowrap">
									
									Maximum Date :
									
									&nbsp;&nbsp;&nbsp;&nbsp;
									
									<input name="maximum_date" id="jscal_field_max" tabindex="1" maxlength="10" size="11" readonly="readonly" value="" type="text">
									<img src="images/jscalendar.gif" alt="Enter Date" id="jscal_max_trigger" align="absmiddle" height="18" width="18">
									
									<script type="text/javascript">
    					Calendar.setup({
        					inputField     :    "jscal_field_max",     // id of the input field
        					ifFormat       :    "%Y-%m-%d",      // format of the input field
        					button         :    "jscal_max_trigger",  // trigger for the calendar (button ID)
        					align          :    "Tl",           // alignment (defaults to "Bl")
        					singleClick    :    true
    					});
				 	</script>
								</td>
								--><!--<td class="dataLabel">
									
									&nbsp;&nbsp;
									
								</td>
								<td align="right">
								</td>
							--></tr>
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
				<td colspan="20" align="right">
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tbody>
							<tr>
								<td  align="left">
									&nbsp;
								</td>
								<td  align="right" nowrap="nowrap">

									<%if (index == 1) {

			%>
									<img src="images/start_off.gif" alt="Start" align="absmiddle" border="0" height="10" width="9">
									Start&nbsp;

									<img src="images/previous_off.gif" alt="Previous" align="absmiddle" border="0" height="10" width="4">
									Previous&nbsp;&nbsp;
									<%} else if ((index - 1) > 0) {

			%>
									<img src="images/start.gif" alt="Start" align="absmiddle" border="0" height="10" width="9">
									<a href="javascript:goleftbgt()" class="listViewPaginationLinkS1"> Start&nbsp; </a>

									<img src="images/previous.gif" alt="Previous" align="absmiddle" border="0" height="10" width="4">
									<a href="javascript:goleft()" class="listViewPaginationLinkS1"> Previous&nbsp;&nbsp; </a>
									<%}

			%>
									<span class="pageNumbers">(<c:out value="${minIndex}" /> - <c:out value="${maxIndex}" /> of <c:out value="${count}" />)</span>&nbsp;&nbsp;

									<%if (totalIndex > index) {

			%>

									<a href="javascript:goright()" class="listViewPaginationLinkS1">Next&nbsp; <img src="images/next.gif" alt="Next" align="absmiddle" border="0" height="10" width="4"></a>&nbsp;&nbsp; <a href="javascript:gorightbgt()" class="listViewPaginationLinkS1">End&nbsp;
										<img src="images/end.gif" alt="End" align="absmiddle" border="0" height="10" width="9"></a>
									<%} else {

			%>
									Next&nbsp;
									<img src="images/next_off.gif" alt="Next" align="absmiddle" border="0" height="10" width="4">
									&nbsp;&nbsp; End&nbsp;
									<img src="images/end_off.gif" alt="End" align="absmiddle" border="0" height="10" width="9">
									<%}

			%>
								</td>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>
			<tr height="20">
				<td width="2%" nowrap="nowrap" class="listViewThS1" scope="col">
					<img src="images/blank.gif" alt="asd" height="1" width="1">
					No.
				</td>

				<!-- ini default generated table from database -->

				<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">Name &nbsp;</td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">Customer Number</td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">Group &nbsp;</td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Policy Agent</td>			
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Card Number &nbsp;</td>	
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Status &nbsp;
				</td>				
			

				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
					
					&nbsp;
					
				</td>
			</tr>


			<c:forEach items="${Members}" var="member" varStatus="status">
				<%if (i % 2 == 0) {
				rowclass = "col1";
			} else if (i % 2 != 0) {
				rowclass = "col2";
			}
			i++;

			%>
				<tr onMouseOver="'" onMouseOut=""
					onMouseDown="" height="20">
					<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
						<%=(i + ((indexint - 1) * countSet))%>
					</td>

					<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
						
						<a href="member?navigation=detail&memberId=<c:out value="${member.memberId}" />" class="linkDetail"><c:out value="${member.firstName}" />&nbsp;<c:out value="${member.lastName}" /></a>
						
					</td>
					<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
						
						<c:out value="${member.customerPolicyNumber}" />
					</td>		
					
					<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
						
						<c:out value="${member.memberGroupId.groupName}" />
						
					</td>	
					<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
						
						<c:out value="${member.policyAgent}" />
						
					</td>	
					<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
						
						<c:out value="${member.currentCardNumber}" />
						
					</td>	
					<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
						
						
					<c:if test="${member.status eq -1}">PENDING</c:if>
						<c:if test="${member.status eq 1}">ACTIVE</c:if>
						<c:if test="${member.status eq -3}">PENDING CHANGEPLAN</c:if>
						<c:if test="${member.status eq -2}">BLOCKED</c:if>
						<c:if test="${member.status eq 2}">TERMINATED</c:if>
						<c:if test="${member.status eq 3}">RESIGNED</c:if>
						<c:if test="${member.status eq 4}">INACTIVE</c:if>
						<c:if test="${member.status eq 5}">INITIALIZED</c:if>
						<c:if test="${member.status eq 7}">GRACE PERIODE</c:if>
						<c:if test="${member.status eq 8}">EXTEND ACTIVE</c:if>
						<c:if test="${member.status eq -4}">PENDING RENEW</c:if>
						
					</td>	
						
										
										
					



					<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top">
						<a href="javascript:detil('<c:out value="${member.memberId}" />')"> <img src="images/view.gif" class="action_icon" alt="View" title="View"></a>
						
						<c:if test="${member.status eq -1}">

						<!-- ini default edit table for each data -->
						<a href="javascript:ubah('<c:out value="${member.memberId}" />')"> <img src="images/edit.gif" class="action_icon" alt="Edit" title="Edit"></a>

						<!-- ini default delete table for each data -->
						<a href="javascript:hapus('<c:out value="${member.memberId}" />')"> <img src="images/delete.gif" class="action_icon" alt="Delete" title="Delete"></a>
						</c:if>
					</td>
				</tr>

				<tr>
					<td colspan="20" class="listViewHRS1"></td>
				</tr>

			</c:forEach>

			<tr>
				<td  align="left">
					&nbsp;
				</td>
				<td  align="right" nowrap="nowrap" colspan="20">

					<%if (index == 1) {

			%>
					<img src="images/start_off.gif" alt="Start" align="absmiddle" border="0" height="10" width="9">
					Start&nbsp;

					<img src="images/previous_off.gif" alt="Previous" align="absmiddle" border="0" height="10" width="4">
					Previous&nbsp;&nbsp;
					<%} else if ((index - 1) > 0) {

			%>
					<img src="images/start.gif" alt="Start" align="absmiddle" border="0" height="10" width="9">
					<a href="javascript:goleftbgt()" class="listViewPaginationLinkS1"> Start&nbsp; </a>

					<img src="images/previous.gif" alt="Previous" align="absmiddle" border="0" height="10" width="4">
					<a href="javascript:goleft()" class="listViewPaginationLinkS1"> Previous&nbsp;&nbsp; </a>
					<%}

			%>
					<span class="pageNumbers">(<c:out value="${minIndex}" /> - <c:out value="${maxIndex}" /> of <c:out value="${count}" />)</span>&nbsp;&nbsp;

					<%if (totalIndex > index) {

			%>

					<a href="javascript:goright()" class="listViewPaginationLinkS1">Next&nbsp; <img src="images/next.gif" alt="Next" align="absmiddle" border="0" height="10" width="4"> </a>&nbsp;&nbsp; <a href="javascript:gorightbgt()" class="listViewPaginationLinkS1">End&nbsp;
						<img src="images/end.gif" alt="End" align="absmiddle" border="0" height="10" width="9"> </a>
					<%} else {

			%>
					Next&nbsp;
					<img src="images/next_off.gif" alt="Next" align="absmiddle" border="0" height="10" width="4">
					&nbsp;&nbsp; End&nbsp;
					<img src="images/end_off.gif" alt="End" align="absmiddle" border="0" height="10" width="9">
					<%}

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
	document.form1.action = "member-form";
	document.form1.method = "GET";
	document.form1.submit();
}
function hapus (idx){
	var delConfirm = window.confirm ("Are You Sure Want To Delete This Entry ?");

	if (delConfirm){
		document.form1.method = "POST";
		document.form1.memberId.value = idx;
		document.form1.action = "member";
		document.form1.navigation.value = "delete";
		document.form1.submit();
	}
}
function ubah (idx){
	document.form1.method = "GET";
	document.form1.memberId.value = idx;
	document.form1.action = "member-form";
	document.form1.navigation.value = "edit";
	document.form1.submit();
}
function goleft(){
	document.form1.navigation.value = "golistpolicy";
	document.form1.arah.value="kiri";
	document.form1.method = "POST";
	document.form1.submit();
}
function goleftbgt(){
	document.form1.navigation.value = "golistpolicy";
	document.form1.arah.value="kiribgt";
	document.form1.method = "POST";
	document.form1.submit();
}
function goright(){
	document.form1.navigation.value = "golistpolicy";
	document.form1.arah.value="kanan";
	document.form1.method = "POST";
	document.form1.submit();
}
function gorightbgt(){
	document.form1.navigation.value = "golistpolicy";
	document.form1.arah.value="kananbgt";
	document.form1.method = "POST";
	document.form1.submit();
}
function go(){
	document.form1.navigation.value = "golistpolicy";
	document.form1.submit();
}
function cari (){
	document.form1.navigation.value = "golistpolicy";
	document.form1.action = "member";
	document.form1.method = "POST";
	document.form1.submit();
}
function detil (idx){
	document.form1.method = "POST";
	document.form1.memberId.value = idx;
	document.form1.action = "member";
	document.form1.navigation.value = "detail";
	document.form1.submit();

}
</script>
