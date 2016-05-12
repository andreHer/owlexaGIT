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

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;List Member Excess Charge</h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
      <c:if test="${theUser.userType eq 2 }">
	      <td align="right">
	      	<input title="Error Log"  name="errorLog" id="errorLog" value=" Error Log " class="errorLog" type="button" onClick="javascript:printErrorLog()">
	      </td>
	      <td align="right">
	      	<input title="Add Error Log"  name="addErrorLog" value=" Add Error Log " class="errorLog" type="button" onClick="javascript:adderrorlog()">
	      </td>
      </c:if>
    </tr>
  </tbody>
</table>

<%@ include file="../mainContentMember.jsp" %>

<br />
<form name="form1" action="excesscharge" method="POST">
<input type="hidden" name="navigation" value="golist">
<input type="hidden" name="subnavigation" value="<c:out value="${subnavigation}" />" >
<input type="hidden" name="currentnavigation" value="listmember" />
<input type="hidden" name="memberId" value="<c:out value="${memberId}" />" />
<input type="hidden" name="arah" value="">
<input type="hidden" name="index" value="<c:out value="${index}" />">
<input type="hidden" name="excessChargeId" value="<c:out value="${excessCharge.excessChargeId}" />">
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"></td>
      <td width="100%"></td>
    </tr>
  </tbody>
  <tbody>
		    <tr>
		      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Search Member Excess Charge</h3></td>
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
 		   		   		<option value="excessNumber" <c:if test="${searchby eq \"excessNumber\"}">selected="true"</c:if> >Excess Number</option>
 		   		   		<option value="claimNumber" <c:if test="${searchby eq \"claimNumber\"}">selected="true"</c:if> >Claim Number</option>
 		   		   		<option value="customerNumber" <c:if test="${searchby eq \"customerNumber\"}">selected="true"</c:if> >Member Number</option>
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
              <input title="Search [Alt+Shift+Q]" accesskey="Q" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" name="button" value="Search" type="submit">  
			</td>
            </tr>
            <tr>
            <td class="dataLabel" nowrap="nowrap">Date Category:
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              
                
				<select name="dateby" class="inputbox">

					<option value="">-- ALL CATEGORY --</option>
					<option value="excessChargeTime" <c:if test="${dateby eq \"excessChargeTime\"}">selected="true"</c:if> >Excess Date</option>
			   		<option value="nextReminderTime" <c:if test="${dateby eq \"nextReminderTime\"}">selected="true"</c:if> >Reminder Date</option>
			   </select>
				
              </td>
            
            <td class="dataLabel" nowrap="nowrap"> Minimum Date :
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              
                <input name="minimum_date" id="jscal_field" tab="1" maxlength="10" size="11" value="<c:out value="${minimum_date}" />" type="text">
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
            <td class="dataLabel" nowrap="nowrap"> Maximum Date :
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              
            <input name="maximum_date" id="jscal_field_max" tabindex="1" maxlength="10" size="11" value="<c:out value="${maximum_date}" />" type="text">
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
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">Excess Number </td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">Claim Number</td>	   		   		   			
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">Member Number</td>	   		   		   							
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Excess Date </td>	   		   		   							
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Excess Value &nbsp;</td>	
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Status &nbsp;</td>	   		   		   							
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Reminder Date</td>	   		   		   							
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Counter </td>	   		   		   							
			
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
			
				<c:out value="${excessCharge.excessChargeNumber}" />
			
		</td>
		   		   		   		   		   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${excessCharge.claimId.claimNumber}" />
			
		</td>
		   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${excessCharge.memberId.customerNumber}" />
			
		</td>
								<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${excessCharge.excessChargeTime}" />
			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<fmt:formatNumber>
				<c:out value="${excessCharge.excessChargeValue}" /></fmt:formatNumber>
			
		</td>
					<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				
			<c:if test="${excessCharge.excessChargeStatus.paymentStatusId eq 1}">OPEN</c:if>
			<c:if test="${excessCharge.excessChargeStatus.paymentStatusId eq 2}">PAID</c:if>
			<c:if test="${excessCharge.excessChargeStatus.paymentStatusId eq 3}">APPROVED</c:if>
			<c:if test="${excessCharge.excessChargeStatus.paymentStatusId eq 4}">BAD</c:if>
			<c:if test="${excessCharge.excessChargeStatus.paymentStatusId eq 5}">PENDING</c:if>
			<c:if test="${excessCharge.excessChargeStatus.paymentStatusId eq 7}">PARTIAL PAID</c:if>			
				
			
		</td>
					<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${excessCharge.nextReminderTime}" />
			
		</td>
					<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${excessCharge.reminderCounter}" />
			
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
	

<script language="Javascript">
$(document).ready(function(){
	$.get("firstcall?navigation=jsontotalmemberelog&memberId=<c:out value="${member.memberId }"/>", function(data){
	  //alert("Data: " + data);
	  if(data>0){
	  	blinker();
	  }
	});
});
function blinker(){
	document.getElementById("errorLog").style.backgroundColor="red";
	setTimeout("document.getElementById('errorLog').style.backgroundColor=''", 500);
	setTimeout("blinker()",1500);
}
<%
String nav="";
if(request.getAttribute("navigation").equals("gosearch")||request.getAttribute("navigation").equals("golookup")){
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
	document.form1.navigation.value = "golist";
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
function printErrorLog(){
	window.open ("firstcall?navigation=searchmembererrorlog&memberId=<c:out value="${member.memberId}" />","Search","width=1024, height=768, menubar=yes, status=no, toolbar=no, scrollbars=yes, resizable=yes");		
}

function adderrorlog (){ 
	window.location.href = "firstcall-form?navigation=memberelog&memberId=<c:out value="${member.memberId}" />";
}
</script>
