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

if (alert != null && !alert.trim().equals("")) {%>
	<div id="warning" align="center">
		<c:out value="${alert}"></c:out>
	</div>
	<%}%>

<%
String rowclass = "";
int i=0;

%>


<!-- Search Container Start -->

<form name="form1" action="claimreport" method="POST">
<input type="hidden" name="navigation" value="gosearch">
<input type="hidden" name="arah" value="">
<input type="hidden" name="index" value="<c:out value="${index}" />">
<input type="hidden" name="memberGroupId" value="" />
<input type="hidden" name="claimType" value="" />
				<input type="hidden" name="claimId" value="<c:out value="${claim.claimId}" />">
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Search Claim</h3></td>
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
            <td class="dataLabel" nowrap="nowrap">Member Group:
              &nbsp;&nbsp;
              
				<input size="20" name="memberGroupName" class="dataField" value="<c:out value="${searchtext}"/>" type="text"> &nbsp;&nbsp;<input title="Search [Alt+Shift+Q]" accesskey="Q" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" name="button" value="Lookup" type="button" onclick="javascript:lookupGroup()">
              </td>

			  <td class="dataLabel" nowrap="nowrap">
              &nbsp;&nbsp;
           
				
              </td>
            <td >
              <input title="Search [Alt+Shift+Q]" accesskey="Q" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" name="button" value="Bulk Report" type="button" onclick="javascript:bulkReport()">
             </td>
            </tr>
			<tr>
            
            <td class="dataLabel" nowrap="nowrap"> Minimum Date :
              &nbsp;&nbsp;
              
                <input name="minimum_date" id="jscal_field" tab="1" maxlength="10" size="11" readonly="readonly" value="<c:out value="${minDate}" />" type="text">
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
              &nbsp;&nbsp;&nbsp;&nbsp;
              
            <input name="maximum_date" id="jscal_field_max" tabindex="1" maxlength="10" size="11" readonly="readonly" value="<c:out value="${maxDate}" />" type="text">
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
			
            <td >
              

            </td>
          </tr>        </tbody>
      </table></td>
    </tr>
  </tbody>
</table>


<!-- Search Container Stop -->


<!-- Table Container Start -->



<div class="table_container">
	<!-- Table Toolbar Start -->
		
		
		<%
		String nampak = "";
		if(navigation!=null&&navigation.equals("gosearch")){
		}else{
			nampak = " style=\"display: none;\"";
		}
		%>
		
</div>
	<!-- Table Toolbar Stop -->
	
	<!-- Table Content Start -->
	
 
<br />

 	
<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	<tr>
      <td colspan="20" align="right">  
            
            </td>
    </tr>
	<tr height="20">
		<td width="6%" nowrap="nowrap" class="listViewThS1" scope="col">
			<img src="images/blank.gif" alt="asd" height="1" width="1">No.</td>		

<!-- ini default generated table from database -->
		   			
				   		   		   			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">
				Claim Number &nbsp;</td>

	<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">
				Claim Date &nbsp;</td>


					<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Member &nbsp;</td>
			   		   		   		   		   		   			
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">
				Provider Name &nbsp;</td>
			
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Charge Value &nbsp;</td>
			
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Approved Value &nbsp;</td>
			
							<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Paid Value&nbsp;</td>
		
							<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Excess Charges&nbsp;</td>
		
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Paid Excess &nbsp;</td>
		
							<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Type &nbsp;</td>
		
		
							<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				Service &nbsp;</td>
		
		
						<td scope="col" class="listViewThS1" nowrap="nowrap" width="7%">
				Status &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="7%">
				Diagnosis &nbsp;</td>
			   		   		   		   		   		   		   		   		   		   		   		   		   		   			
				
		
			   		   		   		   		   			
		
		
			   		   			
		
		
			   		   			
		
		
			   		   		   		   		   			
			   	   
	   
	</tr>


	<c:forEach items="${Claims}" var="claim" varStatus="status" >
	<%	if (i % 2 == 0) {
			rowclass = "col1";
		} else if (i % 2 != 0) {
			rowclass= "col2";
		}
		i++;
	%>
	 <tr height="20">
      <td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><%=i%></td>
		
					   		   		   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<a href="claim?navigation=detail&claimId=<c:out value="${claim.claimId}" />" class="linkDetail"><c:out value="${claim.claimNumber}" /></a>
			
		</td>
				   		   		   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${claim.claimDate}" />
			
		</td>
					   		   		   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<a href="member?navigation=detail&memberId=<c:out value="${claim.memberId.memberId}" />" class="linkDetail"><c:out value="${claim.memberId.firstName}" /> <c:out value="${claim.memberId.lastName}" /></a>
			
		</td>
			
					   		   		   		   		   		   		   		   		   		   		   		   		   		   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:if test="${claim.providerName != null}">
				<c:out value="${claim.providerName}" />
				</c:if>
				
			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
			
				<fmt:formatNumber><c:out value="${claim.claimChargeValue}" /></fmt:formatNumber>
			
		</td>
					   		   		   		   		   		
					   		   		   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right"> 
			
				<fmt:formatNumber><c:out value="${claim.claimApprovedValue}" /></fmt:formatNumber>
			
		</td>		
		   		   	   		   		   		   		   		
			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
			
				<fmt:formatNumber><c:out value="${claim.claimPaidValue}" /></fmt:formatNumber>
			
		</td>		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top" align="right">
			<fmt:formatNumber><c:out value="${claim.claimExcessValue}" /></fmt:formatNumber>
		</td>		
		<td  class="oddListRowS1"  bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				
			
		</td>
		 		   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${claim.claimTypeId.claimTypeCode}" />
			
		</td>		   		   		   		   		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${claim.caseCategoryId.caseCategoryCode}" />
			
		</td>		   		   		   		   		
					   		   		
		   		  
					   		   		
					   		   		   		   		   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${claim.claimStatus.caseStatusName}" />
			
		</td>
         <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top">
         	<c:out value="${claim.diagnosisId.diagnosisCode}" />
	  </td>
    </tr>
   
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	
	</c:forEach>
	
<tr>
                     </tr>
                     <tr height="20">
		<td width="6%" nowrap="nowrap" class="listViewThS1" scope="col">
			<img src="images/blank.gif" alt="asd" height="1" width="1"></td>		

<!-- ini default generated table from database -->
		   			
				   		   		   			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">
				Jumlah</td>

					<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				</td>
		
							<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				</td>
			   		   		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				</td>   		   		   		   			
				<td scope="col" class="listViewThS1" style="text-align: right;" nowrap="nowrap" width="15%" align="right">
				<fmt:formatNumber><c:out value="${claimChargeValue}" /></fmt:formatNumber></td>
			
				<td scope="col" class="listViewThS1" style="text-align: right;" nowrap="nowrap" width="10%" align="right">
				<fmt:formatNumber><c:out value="${claimPaidValue}" /></fmt:formatNumber></td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%" style="text-align: right;" align="right">
				<fmt:formatNumber><c:out value="${claimPaidValue}" /></fmt:formatNumber></td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%" align="right" style="text-align: right;"><fmt:formatNumber><c:out value="${claimExcessValue}" /></fmt:formatNumber></td>
			
				
				
		
							<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
				</td>
		
						<td scope="col" class="listViewThS1" nowrap="nowrap" width="7%">
				</td>
		
			   		   		   		   		   		   		   		   		   		   		   		   		   		   			
				
		
			   		   		   		   		   			
		
		
			   		   			
		
		
			   		   			
		
		
			   		   		   		   		   			<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">&nbsp;
			   		   		   		   		   			<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">&nbsp;
			   	   <td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">&nbsp;
	   </td>
	</tr>
	
	</table>
</form>
	
	<br />
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
	function printOut(){
		window.open ("claimreport?navigation=printoutreport&url=claimreport","Search","width=1024, height=768, menubar=yes, status=no, toolbar=no, scrollbars=yes, resizable=yes");
	}
	function lookupGroup(){
		window.open ("membergroup?navigation=lookup&url=claimreport","Search","width=800, height=600, menubar=yes, status=no, toolbar=no, scrollbars=yes, resizable=yes");
	}
	function setMemberGroup(id,name){
		document.form1.memberGroupId.value = id;
		document.form1.memberGroupName.value = name;
	}
function exportData(){
	document.form1.action = "claimreport";
	document.form1.navigation.value = "printoutreport";
	document.form1.submit();
}
function go(){
	document.form1.navigation.value = "<%=nav%>";
	document.form1.submit();
}
function cari (){
	document.form1.navigation.value = "gosearch";
	document.form1.action = "claim";
	document.form1.method = "POST";
	document.form1.submit();
}

function bulkReport(){
	
	
	document.form1.navigation.value = "downloadMemberClaim";
	document.form1.action = "claimreport";
	document.form1.method = "POST";
	
	
	document.form1.submit();
	
}
</script>
