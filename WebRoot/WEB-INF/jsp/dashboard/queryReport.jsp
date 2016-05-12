<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<%
    String navigation = WebUtil.getAttributeString(request, "navigation", "");
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
    int i = 0;

%>


<script type="text/javascript">

    swfobject.embedSWF(
    "openflashchart/open-flash-chart.swf", "chartArea",
    "800", "600", "9.0.0", "expressInstall.swf",
    {"data-file":"<c:out value="${dashboardURL}" />"} );

</script>
<!-- Search Container Start -->

<form name="form1" action="dashboard" method="POST">
    <input type="hidden" name="navigation" value="gosearch">
    <input type="hidden" name="arah" value="">
    <input type="hidden" name="index" value="<c:out value="${index}" />">
    <table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tbody>
            <tr>
                <td nowrap="nowrap"><h3>Graphical Report</h3></td>
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
                                <option value="providerName" <c:if test="${searchby eq 'providerName'}">selected="true"</c:if> >Provider Name</option>
                                <option value="clientName" <c:if test="${searchby eq 'clientName'}">selected="true"</c:if> >Client Name</option>
                                <option value="memberGroupName" <c:if test="${searchby eq 'memberGroupName'}">selected="true"</c:if> >Member Group Name</option>
                                <option value="policyNumber" <c:if test="${searchby eq 'policyNumber'}">selected="true"</c:if> >Policy Number</option>
                                <option value="officer" <c:if test="${searchby eq 'officer'}">selected="true"</c:if> >Officer</option>
                            </select>

                            </td>
                            <td class="dataLabel" nowrap="nowrap">Status:
                                &nbsp;&nbsp;

                                <select name="status">					
                                    <option value="-1">--- All Status ---</option>
                                    <option value="1" <c:if test="${status eq 1 }">selected</c:if>>Open</option>
                                <option value="2" <c:if test="${status eq 2 }">selected</c:if>>Pending Document</option>
                                <option value="7" <c:if test="${status eq 7 }">selected</c:if>>Pending Investigation</option>
                                <option value="3" <c:if test="${status eq 3 }">selected</c:if>>Verified</option>
                                <option value="9" <c:if test="${status eq 9 }">selected</c:if>>Approved</option>
                                <option value="8" <c:if test="${status eq 8 }">selected</c:if>>Benefit Checked</option>
                                <option value="6" <c:if test="${status eq 6 }">selected</c:if>>Paid</option>
                                <option value="4" <c:if test="${status eq 4 }">selected</c:if>>Rejected</option>
                                <option value="10" <c:if test="${status eq 10 }">selected</c:if>>Pending</option>
                                </select>

                            </td>
                            <td class="dataLabel">
                                <input title="Search [Alt+Shift+Q]" accesskey="Q" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" name="button" value="Show Report" type="submit">
                                
                            </td>
                </tr>
                <tr>

                    <td class="dataLabel" nowrap="nowrap"> Minimum Date :
                        &nbsp;&nbsp;&nbsp;&nbsp;

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
                <td class="dataLabel" nowrap="nowrap">Type &nbsp;&nbsp;:
                    &nbsp;&nbsp;

                    <select name="claimType">					
                        <option value="-1" >--- All Type ---</option>
                        <option value="1" <c:if test="${claimType eq 1 }">selected</c:if> >REIMBURSEMENT</option>
                        <option value="2" <c:if test="${claimType eq 2 }">selected</c:if>>CASHLESS</option>

                        </select>

                    </td>
                    <td align="right">
                        
                    </td>
                </tr>
                <tr>

                    <td class="dataLabel" nowrap="nowrap">Service Type &nbsp;&nbsp;:
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

                        <select name="serviceType">					
                            <option value="-1" >--- All Type ---</option>
                            <option value="1" <c:if test="${serviceType eq 1 }">selected</c:if> >INPATIENT</option>
                        <option value="2" <c:if test="${serviceType eq 2 }">selected</c:if> >OUTPATIENT</option>
                        <option value="3" <c:if test="${serviceType eq 3 }">selected</c:if> >MATERNITY</option>
                        <option value="4" <c:if test="${serviceType eq 4 }">selected</c:if> >DENTAL</option>
                        <option value="5" <c:if test="${serviceType eq 5 }">selected</c:if> >OPTICAL</option>
                        </select>

                    </td>
                    
                <td class="dataLabel" nowrap="nowrap">Periode &nbsp;&nbsp;:
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <select name="reportFormat">					
                        <option value="1" <c:if test="${reportFormat eq 1 }">selected</c:if> >Yearly</option>
                        <option value="2" <c:if test="${reportFormat eq 2 }">selected</c:if> >Monthly</option>
                        <option value="3" <c:if test="${reportFormat eq 3 }">selected</c:if> >Weekly</option>
                        <option value="4" <c:if test="${reportFormat eq 4 }">selected</c:if> >Daily</option>

                        </select>
                    </td>

                </tr>
            </tbody>
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
    if (navigation != null && navigation.equals("gosearch")) {
    } else {
        nampak = " style=\"display: none;\"";
    }
%>

</div>
<!-- Table Toolbar Stop -->

<!-- Table Content Start -->

<br />

<br />
<center>
 <div id="chartArea" style="width:800px; height:600px; " ></div>
</center>
<br />

</form>

<br />
<!-- Table Content Stop -->
<!-- Table Toolbar Start -->

<!-- Table Toolbar Stop -->



<!-- Table Container Stop -->
<script language="Javascript">
    
    function query(){
	document.form1.action = "dashboard";
	document.form1.navigation.value = "query";
	document.form1.submit();
    }
</script>