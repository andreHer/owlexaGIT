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

<form name="form1" action="excesscharge" method="POST">
<input type="hidden" name="navigation" value="gosearch">
<input type="hidden" name="sortcolumn" value="<c:out value="${sortcolumn}" />">
<input type="hidden" name="sortorder" value="<c:out value="${sortorder}" />">
<input type="hidden" name="columntosort" value="<c:out value="${columntosort}" />">
<input type="hidden" name="arah" value="">
<input type="hidden" name="index" value="<c:out value="${index}" />">
<input type="hidden" name="excessChargeId" value="<c:out value="${excessCharge.excessChargeId}" />">
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Search Excess Charge</h3></td>
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
				
						
			 		   		   		<option value="memberName" <c:if test="${searchby eq 'memberName'}">selected="true"</c:if> >Member Name</option>
			 		   		   		
 		   		   		<option value="customerNumber" <c:if test="${searchby eq 'customerNumber'}">selected="true"</c:if> >Member Number</option>
 		   		   		<option value="excessNumber" <c:if test="${searchby eq 'excessNumber'}">selected="true"</c:if> >Excess Number</option>
 		   		   		<c:if test="${(theUser.userType eq 2 )or (theUser.userType eq 10)}">
 		   		   		<option value="memberGroupName" <c:if test="${searchby eq 'memberGroupName'}">selected="true"</c:if> >Member Group</option>
 		   		   		</c:if>
 		   		   		
	   		   	</select>
				            </td>
			  <td class="dataLabel" nowrap="nowrap">Status:
              &nbsp;&nbsp;
              
                <select name="status">					
                	<option value="-1">-- All Status --</option>
					<option value="1" <c:if test="${status eq 1 }">selected</c:if>>Open</option>
					<option value="2" <c:if test="${status eq 2 }">selected</c:if>>Paid</option>
					<option value="4" <c:if test="${status eq 4 }">selected</c:if>>Bad Debt</option>
					
				</select>
				
              </td>
            <td class="dataLabel">
              <input title="Search [Alt+Shift+Q]" accesskey="Q" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" name="button" value="Search" type="button" onclick="javascript:cari()">
              <input title="" accesskey="Q" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" name="button" value="Download Excess" type="button" onclick="javascript:exportHeader()">  
                
			</td>
            </tr>
			<tr>
            
            <td class="dataLabel" nowrap="nowrap"> Minimum Date :
              &nbsp;&nbsp;&nbsp;&nbsp;
              
                <input name="minimum_date" id="jscal_field" tab="1" maxlength="10" size="11" readonly="readonly" value="<c:out value="${minimum_date}" />" type="text">
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
              
            <input name="maximum_date" id="jscal_field_max" tabindex="1" maxlength="10" size="11" readonly="readonly" value="<c:out value="${maximum_date}" />" type="text">
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
            <td align="center">
            
            </td>
          </tr>
        </tbody>
      </table></td>
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
            <td class="listViewPaginationTdS1" align="right" nowrap="nowrap" >
				
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
		<td width="2%" nowrap="nowrap" class="listViewThS1" scope="col">
			<img src="images/blank.gif" alt="asd" height="1" width="1">No.</td>		

<!-- ini default generated table from database -->		   		   		   			
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">Excess Number &nbsp;
			<input type="hidden" name="sortOrderChargeNumber" value="<c:out value="${sortExcessNumber?'true':'false'}" />">
			<a href="javascript:carisort('excessnumber')" class="listViewThLinkS1">
			<img src="images/arrow_<c:out value="${sortExcessNumber?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
		</td>	 	   		   			
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">Member Number &nbsp;
			<input type="hidden" name="sortOrderMemberNo" value="<c:out value="${sortMemberNo?'true':'false'}" />">
			<a href="javascript:carisort('membernumber')" class="listViewThLinkS1">
			<img src="images/arrow_<c:out value="${sortMemberNo?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
		</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Member Name &nbsp;
			<input type="hidden" name="sortOrderMemberName" value="<c:out value="${sortMemberName?'true':'false'}" />">
			<a href="javascript:carisort('membername')" class="listViewThLinkS1">
			<img src="images/arrow_<c:out value="${sortMemberName?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
		</td>
		<c:if test="${(theUser.userType eq 2) or (theUser.userType eq 10)}">
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">Member Group &nbsp;
				<input type="hidden" name="sortOrderMemberGroup" value="<c:out value="${sortMemberGroup?'true':'false'}" />">
				<a href="javascript:carisort('membergroup')" class="listViewThLinkS1">
				<img src="images/arrow_<c:out value="${sortMemberGroup?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
			</td>
		</c:if>	   		   		   							
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="8%">Excess Date &nbsp;
			<input type="hidden" name="sortOrderExcessDate" value="<c:out value="${sortExcessDate?'true':'false'}" />">
			<a href="javascript:carisort('excessdate')" class="listViewThLinkS1">
			<img src="images/arrow_<c:out value="${sortExcessDate?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
		</td> 		   							
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">Excess Charges &nbsp;
			<input type="hidden" name="sortOrderExcessCharge" value="<c:out value="${sortExcessCharge?'true':'false'}" />">
			<a href="javascript:carisort('excesscharges')" class="listViewThLinkS1">
			<img src="images/arrow_<c:out value="${sortExcessCharge?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
		</td>	
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">Status &nbsp;
			<input type="hidden" name="sortOrderStatus" value="<c:out value="${sortStatus?'true':'false'}" />">
			<a href="javascript:carisort('status')" class="listViewThLinkS1">
			<img src="images/arrow_<c:out value="${sortStatus?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
		</td>	  		   							
		<c:if test="${(theUser.userType eq 2) }">
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">Reminder Date &nbsp;
				<input type="hidden" name="sortOrderReminder" value="<c:out value="${sortReminderDate?'true':'false'}" />">
				<a href="javascript:carisort('reminderdate')" class="listViewThLinkS1">
				<img src="images/arrow_<c:out value="${sortReminderDate?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
			</td>	   							
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">Counter &nbsp;
				<input type="hidden" name="sortOrderCounter" value="<c:out value="${sortCounter?'true':'false'}" />">
				<a href="javascript:carisort('counter')" class="listViewThLinkS1">
				<img src="images/arrow_<c:out value="${sortCounter?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
			</td>	   							
   		</c:if>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">Cek Detil Excess &nbsp;
			<input type="hidden" name="sortOrderRemarks" value="<c:out value="${sortRemarks?'true':'false'}" />">
			<a href="javascript:carisort('remarks')" class="listViewThLinkS1">
				<img src="images/arrow_<c:out value="${sortRemarks?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
		</td>
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

      		   		   		   		   		   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
			<a href="excesscharge?navigation=detail&excessChargeId=<c:out value="${excessCharge.excessChargeId}" />" class="linkDetail">
				<c:out value="${excessCharge.excessChargeNumber}" />
				</a>
			
		</td>
		   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<a href="member?navigation=detail&memberId=<c:out value="${excessCharge.memberId.memberId}" />" class="linkDetail">
				<c:out value="${excessCharge.memberId.customerPolicyNumber}" />
			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				
				<c:out value="${excessCharge.memberId.firstName}" />
			
		</td>
		<c:if test="${(theUser.userType eq 2) or (theUser.userType eq 10)}">
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				
				<c:out value="${excessCharge.memberId.memberGroupId.groupName}" />
			
		</td></c:if>
								<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${excessCharge.excessChargeTime}" />
			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: right;">
			
				<fmt:formatNumber><c:out value="${excessCharge.excessChargeValue}" /></fmt:formatNumber>
			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" style="text-align: center;">

			<c:if test="${excessCharge.excessChargeStatus.paymentStatusId eq 1}">OPEN</c:if>
			<c:if test="${excessCharge.excessChargeStatus.paymentStatusId eq 2}">PAID</c:if>
			<c:if test="${excessCharge.excessChargeStatus.paymentStatusId eq 3}">APPROVED</c:if>
			<c:if test="${excessCharge.excessChargeStatus.paymentStatusId eq 4}">BAD</c:if>
			<c:if test="${excessCharge.excessChargeStatus.paymentStatusId eq 5}">PENDING</c:if>
			<c:if test="${excessCharge.excessChargeStatus.paymentStatusId eq 7}">PARTIAL PAID</c:if>			
				
			
		</td>
		
		 		   		   		<c:if test="${(theUser.userType eq 2) }">
		
					<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${excessCharge.nextReminderTime}" />
			
		</td>
					<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${excessCharge.reminderCounter}" />
			
		</td>
		</c:if>

		 <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
			<a href="claimitem?memberId=<c:out value="${excessCharge.memberId.memberId}" />&claimId=<c:out value="${excessCharge.claimId.claimId}" />#DOWN" class="linkDetail">
				<c:out value="Cek" />
				</a>
			
		</td>
					   		   		
			
    </tr>
   
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	
	</c:forEach>
	
<tr>
            <td class="listViewPaginationTdS1" align="left"></td>
            	<td class="listViewPaginationTdS1" align="right" nowrap="nowrap" colspan=20>
				
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
	document.form1.action = "excesscharge-form";
	document.form1.method = "GET";
	document.form1.submit();
}
function hapus (idx){
	var delConfirm = window.confirm ("Are You Sure Want To Delete This Entry ?");

	if (delConfirm){
		document.form1.method = "POST";
		document.form1.excessChargeId.value = idx;
		document.form1.action = "excesscharge";
		document.form1.navigation.value = "delete";
		document.form1.submit();
	}
}
function ubah (idx){
	document.form1.method = "GET";
	document.form1.excessChargeId.value = idx;
	document.form1.action = "excesscharge-form";
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
	document.form1.action = "excesscharge";
	document.form1.method = "POST";
	document.form1.submit();
}
function exportHeader (){
	document.form1.method = "GET";
	
	document.form1.action = "excesscharge";
	document.form1.navigation.value = "exportheader";
	document.form1.submit();

}
function detil (idx){
	document.form1.method = "POST";
	document.form1.excessChargeId.value = idx;
	document.form1.action = "excesscharge";
	document.form1.navigation.value = "detail";
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
