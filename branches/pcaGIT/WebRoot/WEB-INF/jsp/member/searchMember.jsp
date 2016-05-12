<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ page import="com.ametis.cms.util.WebUtil"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>


<%String navigation = WebUtil.getAttributeString(request,
					"navigation", "");

			%>

<!-- Page Title Start // Should be put on <Title> tag-->

<!-- Page Title Stop-->
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

<c:set var="now" value="<%=new java.util.Date()%>"/>
<form name="form1" action="member" method="POST">
	<input type="hidden" name="navigation" value="gosearch">
	<input type="hidden" name="arah" value="">
	<input type="hidden" name="sortcolumn" value="<c:out value="${sortcolumn}" />">
	<input type="hidden" name="sortorder" value="<c:out value="${sortorder}" />">
	<input type="hidden" name="columntosort" value="<c:out value="${columntosort}" />">
	<input type="hidden" name="index" value="<c:out value="${index}" />">
	<input type="hidden" name="memberId" value="<c:out value="${member.memberId}" />">
	<table border="0" cellpadding="0" cellspacing="0" width="100%">
		<tbody>
			<tr>
				<td nowrap="nowrap">
					<h3>
						<img src="images/h3Arrow.gif" border="0">
						&nbsp;Search Member
					</h3>
				</td>
				<td width="100%">
					<img src="images/blank.gif" height="1" width="1">
				</td>
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
								<td class="dataLabel" nowrap="nowrap">
									
									Search Keyword:
									
									&nbsp;&nbsp;
									
									<input size="20" name="searchtext" class="dataField" value="<c:out value="${searchtext}"/>" type="text">
									
								</td>
								<td class="dataLabel" nowrap="nowrap">
									
									Search Category:
									
									&nbsp;&nbsp;
									

									<select name="searchby" class="inputbox">
										<option value="cardNumber" <c:if test="${searchby eq 'groupName'}">selected="true"</c:if>>
											EDC Card Number
										</option>
										<option value="firstName" <c:if test="${searchby eq 'firstName'}">selected="true"</c:if>>
											Name
										</option>										
										<option value="customerPolicyNumber" <c:if test="${searchby eq \"customerPolicyNumber\"}">selected="true"</c:if>>
											Customer Number
										</option>										
										<c:if test="${theUser.userType eq 2}">
											<option value="clientName" <c:if test="${searchby eq \"clientName\"}">selected="true"</c:if>>
												Client Name
											</option>
											<option value="groupName" <c:if test="${searchby eq \"groupName\"}">selected="true"</c:if>>
												Group Name
											</option>											
										</c:if>
										<option value="policyNumber" <c:if test="${searchby eq \"policyNumber\"}">selected="true"</c:if>>
											Policy Number
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
										<option value="1" <c:if test="${status eq 1 }">selected</c:if>>
											ACTIVE
										</option>
										<option value="2" <c:if test="${status eq 2 }">selected</c:if>>
											TERMINATED
										</option>
										<option value="3" <c:if test="${status eq 3 }">selected</c:if>>
											RESIGNED
										</option>										
										<option value="-1" <c:if test="${status eq -1 }">selected</c:if>>
											PENDING
										</option>
										<option value="-3" <c:if test="${status eq -3 }">selected</c:if>>
											PENDING CHANGEPLAN
										</option>		
										<option value="-2" <c:if test="${status eq -2 }">selected</c:if>>
											BLOCKED
										</option>
										<option value="-6" <c:if test="${status eq -6 }">selected</c:if>>
											EXPIRED
										</option>
										<option value="7" <c:if test="${status eq 7 }">selected</c:if>>
											EXTEND ACTIVE
										</option>																
												
									</select>

									
								</td>
								<td class="dataLabel">
									
									
									<input title="Search [Alt+Shift+Q]" accesskey="Q" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" name="button" value="Search" type="submit">
									
								</td>
							</tr>
							<tr>
            
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
            <td class="dataLabel" nowrap="nowrap"> Maximum Date :
              &nbsp;&nbsp;&nbsp;&nbsp;
              
            <input name="maximum_date" id="jscal_field_max" tabindex="1" maxlength="10" size="11" value="<c:out value="${maximum_date}" />" type="text">
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
            <td class="dataLabel" nowrap="nowrap">Date &nbsp;&nbsp;:
              &nbsp;&nbsp;
              
                <select name="dateBy">					
					<option value="birthday" <c:if test="${dateBy eq 'birthday'}">selected</c:if>>Date of Birth</option>
					<option value="effectiveDate" <c:if test="${dateBy eq 'effectiveDate' }">selected</c:if>>Effective Date</option>					
					<option value="expireDate" <c:if test="${dateBy eq 'expireDate'}">selected</c:if>>Expire Date</option>
					<option value="joinDate" <c:if test="${dateBy eq 'joinDate'}">selected</c:if>>Join Date</option>
				</select>
				
              </td>
            <td align="right" class="dataLabel" >
            </td>
          </tr>

						</tbody>
					</table>
				</td>
			</tr>
		</tbody>
	</table>



	<c:if test="${(theUser.roleId.roleId eq 0 ) or (theUser.roleId.roleId eq 16 )}">
	<!-- 
	<input type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:tambahi()" value="Create">
	 -->
	</c:if>
	<br />


	<table class="listView" cellspacing="0" cellpadding="0" width="100%">
		<tbody>
			<tr>
				<td colspan="20" align="right">
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tbody>
							<tr>
				        <td class="listViewPaginationTdS1" align="left"></td>
            			<td class="listViewPaginationTdS1" align="right" nowrap="nowrap" >

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
				<td width="1%" nowrap="nowrap" class="listViewThS1" scope="col">
					<img src="images/blank.gif" alt="asd" height="1" width="1">
					No.
				</td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Card Number &nbsp;
					<input type="hidden" name="sortByOrderCard" value="<c:out value="${sortCardNumber?'true':'false'}" />">
					<a href="javascript:carisort('cardnumber')" class="listViewThLinkS1">
					<img src="images/arrow_<c:out value="${sortCardNumber?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
				</td>
				<!-- ini default generated table from database -->

				<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">Name &nbsp;	
					<input type="hidden" name="sortByOrderMemberName" value="<c:out value="${sortNameMember?'true':'false'}" />">
					<a href="javascript:carisort('membername')" class="listViewThLinkS1">
					<img src="images/arrow_<c:out value="${sortNameMember?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
				</td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Date Of Birth &nbsp;
					<input type="hidden" name="sortByOrderDOB" value="<c:out value="${sortBirthday?'true':'false'}" />">
					<a href="javascript:carisort('dateofbirth')" class="listViewThLinkS1">
					<img src="images/arrow_<c:out value="${sortBirthday?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
				</td>				 
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Customer Number &nbsp;	
					<input type="hidden" name="sortByOrderCusNo" value="<c:out value="${sortCustomerNo?'true':'false'}" />">
					<a href="javascript:carisort('customernumber')" class="listViewThLinkS1">
					<img src="images/arrow_<c:out value="${sortCustomerNo?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
				</td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">Client	&nbsp;
					<input type="hidden" name="sortByOrderClientName" value="<c:out value="${sortClientName?'true':'false'}" />">
					<a href="javascript:carisort('clientname')" class="listViewThLinkS1">
					<img src="images/arrow_<c:out value="${sortClientName?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
				</td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%"> C-Code	&nbsp;
					<input type="hidden" name="sortByOrderCCode" value="<c:out value="${sortClientId?'true':'false'}" />">
					<a href="javascript:carisort('clientcode')" class="listViewThLinkS1">
					<img src="images/arrow_<c:out value="${sortClientId?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
				</td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">Group &nbsp;	
					<input type="hidden" name="sortByOrderGroup" value="<c:out value="${sortGroupName?'true':'false'}" />">
					<a href="javascript:carisort('group')" class="listViewThLinkS1">
					<img src="images/arrow_<c:out value="${sortGroupName?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>				
				</td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">G-Code &nbsp;
					<input type="hidden" name="sortByOrderGroupCode" value="<c:out value="${sortGroupId?'true':'false'}" />">
					<a href="javascript:carisort('groupcode')" class="listViewThLinkS1">
					<img src="images/arrow_<c:out value="${sortGroupId?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
				</td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Effective Date &nbsp;
 					<input type="hidden" name="sortByOrderEffective" value="<c:out value="${sortEffectiveDate?'true':'false'}" />">
					<a href="javascript:carisort('effectivedate')" class="listViewThLinkS1">
					<img src="images/arrow_<c:out value="${sortEffectiveDate?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>				
				</td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Expire Date &nbsp;
					<input type="hidden" name="sortByOrderExpire" value="<c:out value="${sortExpireDate?'true':'false'}" />">
					<a href="javascript:carisort('expiredate')" class="listViewThLinkS1">
					<img src="images/arrow_<c:out value="${sortExpireDate?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>			
				</td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%">Policy Number &nbsp;
 					<input type="hidden" name="sortByOrderPolicy" value="<c:out value="${sortPolicyNo?'true':'false'}" />">
					<a href="javascript:carisort('policynumber')" class="listViewThLinkS1">
					<img src="images/arrow_<c:out value="${sortPolicyNo?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>						
				</td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Status &nbsp;
					<input type="hidden" name="sortByOrderStatus" value="<c:out value="${sortStatus?'true':'false'}" />">
					<a href="javascript:carisort('status')" class="listViewThLinkS1">
					<img src="images/arrow_<c:out value="${sortStatus?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>						
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
				<tr onMouseOver="" onMouseOut=""
					onMouseDown="" height="20">
					<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
						<%=(i + ((indexint - 1) * countSet))%>
					</td>
					<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
						
						<c:out value="${member.currentCardNumber}" />
						
					</td>	
					<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
						
						<a href="member?navigation=detail&memberId=<c:out value="${member.memberId}" />" class="linkDetail"><c:out value="${member.firstName}" />&nbsp;<c:out value="${member.lastName}" /></a>
						
					</td>
					<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
						
						<c:out value="${member.birthday}" />
						
					</td>
					<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
						
						<c:out value="${member.customerPolicyNumber}" />
						
					</td>		
					<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">						
						<c:out value="${member.clientName}" />						
					</td>
					<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">						
						<c:out value="${member.clientId.clientCode}" />						
					</td>		
					<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">						
						<c:out value="${member.groupName}" />						
					</td>
					<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">						
						<c:out value="${member.memberGroupId.memberGroupCode}" />						
					</td>
					<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
						
						<c:out value="${member.effectiveDate}" />
						
					</td>
					<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
						
						<c:out value="${member.expireDate}" />
						
					</td>	
					<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
						
						<c:out value="${member.currentPolicyNumber}" />
						
					</td>	
					<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
						<c:if test="${member.status eq -1}">PENDING</c:if>
						<c:if test="${member.status eq 1}">ACTIVE</c:if>
						<c:if test="${member.status eq -3}">PENDING CHANGEPLAN</c:if>
						<c:if test="${member.status eq -2}">BLOCKED</c:if>						
						<c:if test="${member.status eq 2}">
							<c:if test="${now < member.resignedDate}">
								ACTIVE
							</c:if>
							<c:if test="${now >= member.resignedDate}">
								TERMINATED
							</c:if>
							
							
						</c:if>
						<c:if test="${member.status eq 3}">RESIGNED</c:if>
						<c:if test="${member.status eq 4}">INACTIVE</c:if>
						<c:if test="${member.status eq 5}">INITIALIZED</c:if>
						<c:if test="${member.status eq 7}">GRACE PERIODE</c:if>
						<c:if test="${member.status eq 8}">EXTEND ACTIVE</c:if>
						<c:if test="${member.status eq -4}">PENDING RENEW</c:if>
						<c:if test="${member.status eq -5}">PENDING MUTATION</c:if>
						<c:if test="${member.status eq -6}">EXPIRED</c:if>
					</td>	

				</tr>

				<tr>
					<td colspan="20" class="listViewHRS1"></td>
				</tr>

			</c:forEach>

			<tr>
				<td class="listViewPaginationTdS1" align="left"></td>
            	<td class="listViewPaginationTdS1" align="right" nowrap="nowrap" colspan=20>

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
	document.form1.navigation.value = "<%=nav%>";
	document.form1.arah.value="kiri";
	document.form1.searchtext.value="<c:out value="${searchtext}" />";
	document.form1.searchby.value="<c:out value="${searchby}" />";
	document.form1.method = "POST";
	document.form1.submit();
}
function goleftbgt(){
	document.form1.navigation.value = "<%=nav%>";
	document.form1.arah.value="kiribgt";
	document.form1.searchtext.value="<c:out value="${searchtext}" />";
	document.form1.searchby.value="<c:out value="${searchby}" />";
	document.form1.method = "POST";
	document.form1.submit();
}
function goright(){
	document.form1.navigation.value = "<%=nav%>";
	document.form1.arah.value="kanan";
	document.form1.searchtext.value="<c:out value="${searchtext}" />";
	document.form1.searchby.value="<c:out value="${searchby}" />";
	document.form1.method = "POST";
	document.form1.submit();
}
function gorightbgt(){
	document.form1.navigation.value = "<%=nav%>";
	document.form1.arah.value="kananbgt";
	document.form1.searchtext.value="<c:out value="${searchtext}" />";
	document.form1.searchby.value="<c:out value="${searchby}" />";
	document.form1.method = "POST";
	document.form1.submit();
}
function go(){
	document.form1.navigation.value = "<%=nav%>";
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
function cari (){
	document.form1.navigation.value = "gosearch";
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
