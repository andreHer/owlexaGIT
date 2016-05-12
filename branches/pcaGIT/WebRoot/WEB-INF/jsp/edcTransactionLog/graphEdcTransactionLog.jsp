<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collection"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- <link rel="stylesheet" type="text/css" href="css/autocomplete.css"> -->
<link rel="stylesheet" type="text/css" href="css/button.css">

<link rel="stylesheet" type="text/css" href="scripts/jquery/jquery.multiselect.min.js">
<!-- <link rel="stylesheet" type="text/css" href="css/jquery/autocomplete/jquery.autocomplete.css"/> -->
<link rel="stylesheet" type="text/css" href="css/jquery/jquery.multiselect.css"/>

<script type="text/javascript" src="scripts/jquery/jquery.multiselect.min.js"></script>
<!-- <script type="text/javascript" src="scripts/jquery/plugin/jquery.autocomplete.pack.js"></script> -->

<style>
	.ui-autocomplete-loading {
	  background: white url("images/owlexa/ui-anim_basic_16x16.gif") right center no-repeat;
	}
</style>
	
<% /* Edit 30032015, Pengaturan Style untuk Tiap warna Case */ %>
<style type="text/css">
    tr.caseopen td{ background-color : #fbf314; }
    tr.other td{ background-color : #e7f0fe; }
    tr.level1 td{ background-color: #a5fff7; }
    tr.level2 td{ background-color: #21d0ff; }
    tr.level3 td{ background-color: #009fff; }
    legend.other {background-color: #e7f0fe; display: block;padding-left: 2px; padding-right: 2px; border-style: outset; border-color: #e7f0fe; height: 12px;}
    legend.level1 { background-color: #a5fff7; display: block; padding-left: 2px; padding-right: 2px; border-style: outset; border-color: #A9F5F2; height: 12px;}
   	legend.level2 { background-color: #21d0ff; display: block; padding-left: 2px; padding-right: 2px; border-style: outset; border-color: #58D3F7; height: 12px;}
    legend.level3 { background-color: #009fff; display: block; padding-left: 2px; padding-right: 2px; border-style: outset; border-color: #01A9DB; height: 12px;}
    legend.caseopen { background-color : #fdf200;  display: block; padding-left: 2px; padding-right: 2px; border-style: outset; border-color: #fdf200; height: 12px;}
</style>

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
<form name="form1" action="edctransactionlog" method="POST">
<input type="hidden" name="navigation" value="graphEdcTransactionLog">
<input type="hidden" name="index" value="<c:out value="${index}" />">
<input type="hidden" name="arah" value="">
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/	h3Arrow.gif"  border="0">&nbsp;Search Graph EDC Transaction Log</h3></td>
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
				<div id="timeDiv" align="center" style="color:#FCECD3;border-width: 4px;border-style: outset;border-color:black;">Refresh Page in &nbsp;<span id="time"></span></div>
					<td class="dataLabel" nowrap="nowrap">Action Code:
              			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                <select name="status" id="status" multiple="multiple">	
		               <%--  <%
						   String[] statusSearch = (String[]) request.getAttribute("actionCode");
						    System.out.print("statusSearch.le " +statusSearch.length);
// 						   if(statusSearch != null){		
						   if(statusSearch.length > 1){
						   String panjangStatus[] = new String[7];
						   
						    for (int h = 0 ; h < statusSearch.length ; h++  ){
						    
						    	String statusSearchString = statusSearch[h];
						    	panjangStatus[h] = statusSearchString;
						    	 System.out.print("panjangStatus[h] " +panjangStatus[h]);
// 						    }
								
						 %>	
						 	<c:set var="status2" value="<%= panjangStatus%>"> </c:set> 
							<option value="AUTH" <c:if test="${status2[0] eq 'AUTH'}">selected</c:if>>AUTH</option>
							<option value="MENU REGISTER" <c:if test="${status2[1] eq 'MENU REGISTER' }">selected</c:if>>MENU REGISTER</option>					
							<option value="MENU SERVICE" <c:if test="${status2[2] eq 'MENU SERVICE' }">selected</c:if>>MENU SERVICE</option>
							<option value="PAYMENT" <c:if test="${status2[3] eq 'PAYMENT' }">selected</c:if>>PAYMENT</option>
							<option value="REGISTER" <c:if test="${status2[4] eq 'REGISTER' }">selected</c:if>>REGISTER</option>
							<option value="REVERSAL CLAIM" <c:if test="${status2[5] eq 'REVERSAL CLAIM' }">selected</c:if>>REVERSAL CLAIM</option>
							<option value="REVERSAL VOID" <c:if test="${status2[6] eq 'REVERSAL VOID' }">selected</c:if>>REVERSAL VOID</option>
						 <%
						 }
						 }else{
						  %>		  --%>
							<option value="AUTH" <c:if test="${status eq 'AUTH'}">selected</c:if>>AUTH</option>
							<option value="MENU REGISTER" <c:if test="${status eq 'MENU REGISTER' }">selected</c:if>>MENU REGISTER</option>					
							<option value="MENU SERVICE" <c:if test="${status eq 'MENU SERVICE' }">selected</c:if>>MENU SERVICE</option>
							<option value="PAYMENT" <c:if test="${status eq 'PAYMENT' }">selected</c:if>>PAYMENT</option>
							<option value="REGISTER" <c:if test="${status eq 'REGISTER' }">selected</c:if>>REGISTER</option>
							<option value="REVERSAL CLAIM" <c:if test="${status eq 'REVERSAL CLAIM' }">selected</c:if>>REVERSAL CLAIM</option>
							<option value="REVERSAL VOID" <c:if test="${status eq 'REVERSAL VOID' }">selected</c:if>>REVERSAL VOID</option>
					   <%--  <%
						}
						 %> --%> 
						</select>
              		</td>
					<td class="dataLabel" nowrap="nowrap">Refresh Setting:
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<select name="refreshTimerSet" class="inputbox">
							<option value="-1">-- No Refresh --</option>
							<option value="2" <c:if test="${refreshTimerSet eq \"2\"}">selected="true"</c:if>>
								2 Minutes  
							</option>
							<option value="3" <c:if test="${refreshTimerSet eq \"3\"}">selected="true"</c:if>>
								3 Minutes
							</option>
							<option value="4" <c:if test="${refreshTimerSet eq \"4\"}">selected="true"</c:if>>
								4 Minutes
							</option>
							<option value="5" <c:if test="${refreshTimerSet eq \"5\"}">selected="true"</c:if>>
								5 Minutes
							</option>
						</select>
		              </td>
			    	  <td class="dataLabel">
	                	  <input title="Search [Alt+Shift+Q]" accesskey="Q" onClick="javascript:cari();" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" name="button" value="Search" type="submit">
				 	  </td>
           		</tr>	
           		<tr>
           		    <td class="dataLabel" nowrap="nowrap">View with period:
		              &nbsp;&nbsp;&nbsp;&nbsp;
		                <select name="dateBy" id="dateBy">					
							<option value="allthetime" <c:if test="${dateBy eq 'allthetime'}">selected</c:if>>All The Time</option>
							<option value="daily" <c:if test="${dateBy eq 'daily' }">selected</c:if>>Daily</option>					
							<option value="monthly" <c:if test="${dateBy eq 'monthly'}">selected</c:if>>Monthly</option>
							<option value="yearly" <c:if test="${dateBy eq 'yearly'}">selected</c:if>>Yearly</option>
						</select>
		            </td>
           			<td class="dataLabel" id="labelDate" nowrap="nowrap"> Date:
		              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		            <input name="chooseDate" id="jscal_field_max" tabindex="1" maxlength="10" size="11" value="<c:out value="${chooseDate}" />" type="text" onClick="javascript:lovDate()">
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
           		</tr>	
        	</tbody>
      	</table>
      	<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	<tr>
      <td colspan="20" align="right"><table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tbody>
          <tr>
            <td class="listViewPaginationTdS1" align="left"></td>
            <td class="listViewPaginationTdS1" align="right" nowrap="nowrap">
				
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
    </tr    
	<tr height="20">
		<c:if test="${dateBy eq 'allthetime'}">
			<td width="1%" nowrap="nowrap" class="listViewThS1" scope="col">No.</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="35%">Action Code&nbsp;</td>	
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="20%">Total &nbsp;</td>
		</c:if>
		<c:if test="${(dateBy eq 'daily') or (dateBy eq 'monthly') or (dateBy eq 'yearly')}">
			<td width="1%" nowrap="nowrap" class="listViewThS1" scope="col">No.</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="35%">Action Code&nbsp;</td>	
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="35%">Activity Time&nbsp;</td>	
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="20%">Total &nbsp;</td>
		</c:if>
	</tr>


	<c:set var="casecolorset"></c:set>
	<c:set var="totalClaim" value="0"/>
	<c:set var="claimValue"/>

	
	<% 
		int io = 0;
		int total = 0;
		Collection<Object[]> EdcTrasactionLogsCollection = (Collection<Object[]>) request.getAttribute("EdcTransactionLogs");
		

		
		if (EdcTrasactionLogsCollection != null && EdcTrasactionLogsCollection.size() > 0){
			Iterator<Object[]> EdcTrasactionLogsIterator = EdcTrasactionLogsCollection.iterator();
			if (EdcTrasactionLogsIterator != null){
				while (EdcTrasactionLogsIterator.hasNext()){
				Object[] EdcTransactionLogs = EdcTrasactionLogsIterator.next();
				/* //totalClaim += (Double)claim[2]; */
				//countClaim += (Long)claim[4];
	 %>

			<tr>
			<c:if test="${dateBy eq 'allthetime'}">
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
					<%=(++io + ((indexint - 1) * countSet))%>
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
					&nbsp;<c:out value="<%= (String)EdcTransactionLogs[0] %>" />
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
					&nbsp;<c:out value="<%= (Long)EdcTransactionLogs[1] %>" />
				</td>
			</c:if>
			<c:if test="${(dateBy eq 'daily') or (dateBy eq 'monthly') or (dateBy eq 'yearly')}">
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
					<%=(++io + ((indexint - 1) * countSet))%>
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
					&nbsp;<c:out value="<%= (String)EdcTransactionLogs[0] %>" />
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
					&nbsp;<c:out value="<%= (String)EdcTransactionLogs[2] %>" />
				</td>
				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
					&nbsp;<c:out value="<%= (Long)EdcTransactionLogs[1] %>" />
				</td>
			</c:if>
			</tr>
     <%
				}
			}
		}
	 %>
	<tr>
    	<td colspan="20" class="listViewHRS1"></td>
    </tr>
	<%-- </c:forEach> --%>
	
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
      </td>
    </tr>
  </tbody>
</table>
<center>
	<div id="piechart" style="width: 100%; height: 500px;color:#FCECD3;border-width: 4px;border-style: outset;border-color:black;"></div>
</center>

</form>

<script type="text/javascript">
<%
String nav="";
if(request.getAttribute("navigation").equals("graphEdcTransactionLog")){
	nav = (String)request.getAttribute("navigation");
}
%>
$(document).ready(function(){

	$("#status").multiselect();

	document.getElementById('timeDiv').style.visibility = 'hidden';
	startTimer();
	
	$("#dateBy").change(function(){
		//visible date untuk allthetime
		if ($("#dateBy").val() == 'allthetime'){//value allthetime
			document.getElementById('labelDate').style.visibility = 'hidden';
	    	document.getElementById('jscal_field_max').style.visibility = 'hidden';
	    	}
	    else{
	    	document.getElementById('labelDate').style.visibility = 'visible';
	    	document.getElementById('jscal_field_max').style.visibility = 'visible';
		}
	});
	PlaySubmit();
	
});

function goleft(){
	document.form1.navigation.value = "graphEdcTransactionLog";
	document.form1.arah.value="kiri";
	document.form1.method = "POST";
	document.form1.submit();
}
function goleftbgt(){
	document.form1.navigation.value = "graphEdcTransactionLog";
	document.form1.arah.value="kiribgt";
	document.form1.method = "POST";
	document.form1.submit();
}
function goright(){
	document.form1.navigation.value = "graphEdcTransactionLog";
	document.form1.arah.value="kanan";
	document.form1.method = "POST";
	document.form1.submit();
}
function gorightbgt(){
	document.form1.navigation.value = "graphEdcTransactionLog";
	document.form1.arah.value="kananbgt";
	document.form1.method = "POST";
	document.form1.submit();
}
function go(){
	document.form1.navigation.value = "graphEdcTransactionLog";
	document.form1.submit();
}

function PlaySubmit(){
	//visible date untuk allthetime
	if ($("#dateBy").val() == 'allthetime'){//value allthetime
		document.getElementById('labelDate').style.visibility = 'hidden';
	   	document.getElementById('jscal_field_max').style.visibility = 'hidden';
	   	}
	   else{
	   	document.getElementById('labelDate').style.visibility = 'visible';
	   	document.getElementById('jscal_field_max').style.visibility = 'visible';
	}
}

function cari (){
	document.form1.navigation.value = "<%=nav%>";
	document.form1.action = "edctransactionlog?navigation=graphEdcTransactionLog";
	document.form1.method = "POST";
	document.form1.submit();
}

function startTimer() {
	if(document.form1.refreshTimerSet.value > 0){
		document.querySelector('#timeDiv').style.fontSize = "14px";
		document.getElementById('timeDiv').style.visibility = 'visible';
	    var timer = document.form1.refreshTimerSet.value*60, minutes, seconds;
	    setInterval(function () {
	        minutes = parseInt(timer / 60, 10);
	        seconds = parseInt(timer % 60, 10);
	
	        minutes = minutes < 10 ? "0" + minutes : minutes;
	        seconds = seconds < 10 ? "0" + seconds : seconds;
	
	        document.querySelector('#time').textContent = minutes + ":" + seconds;
	        
	
	        if (--timer < 0) {
	            timer = document.form1.refreshTimerSet.value;
	            document.form1.navigation.value = "<%=nav%>";
				document.form1.submit();
	        }
	    }, 1000);
    }else{
    	document.getElementById('timeDiv').style.visibility = 'hidden';
    }
}	
</script>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">
  google.load("visualization", "1", {packages:["corechart"]});
//   corechart
  google.setOnLoadCallback(drawChart);
  function drawChart() {

    var data = new google.visualization.DataTable();
    data.addColumn('string', 'Action Code');
    data.addColumn('number', 'Activity Code');

  <% if (EdcTrasactionLogsCollection != null && EdcTrasactionLogsCollection.size() > 0){
			Iterator<Object[]> EdcTrasactionLogsIterator = EdcTrasactionLogsCollection.iterator();
			if (EdcTrasactionLogsIterator != null){
				while (EdcTrasactionLogsIterator.hasNext()){
				Object[] EdcTransactionLogs = EdcTrasactionLogsIterator.next();
%>

      data.addRow(["<%= (String)EdcTransactionLogs[0] %>",<%= (Long)EdcTransactionLogs[1] %>]);

<% 
			}
		}
	}
%>  

    var options = {
      title: 'Graph EDC Transaction Log',
      is3D: true
    };

    var chart = new google.visualization.PieChart(document.getElementById('piechart'));

    chart.draw(data, options);
  }
</script>