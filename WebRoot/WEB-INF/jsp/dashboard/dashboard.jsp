<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


           <script type="text/javascript" src="openflashchart/swfobject.js"></script>
<script type="text/javascript">

    swfobject.embedSWF(
    "openflashchart/open-flash-chart.swf", "chartArea",
    "500", "300", "9.0.0", "expressInstall.swf",
    {"data-file":"dashboard?navigation=<c:out value="${nav}" />-<c:out value="${reportYear}" />"} );

</script>

<ul id="maintab" class="shadetabs">

    <li <c:if test="${tipe eq 'claimgrowth'}"> class="selected" </c:if>>
        <a href="dashboard?tipe=claimgrowth&navigation=dashboard" rel="tcontent1">Claim Growth</a>
    </li>
    <li <c:if test="${tipe eq 'membergrowth'}"> class="selected" </c:if>>
        <a href="dashboard?tipe=membergrowth&navigation=dashboard" rel="tcontent2">Member Growth</a>
    </li>	
	
	<c:if test="${theUser.userType eq 2 or theUser.userType eq 3}">
    <li <c:if test="${tipe eq 'claimgroup'}"> class="selected" </c:if>>
        <a href="dashboard?tipe=claimgroup&navigation=dashboard" rel="tcontent3">Group Claim</a>
    </li>
    </c:if>
    
    <c:if test="${theUser.userType eq 1 }">
    <li <c:if test="${tipe eq 'claimmember'}"> class="selected" </c:if>>
        <a href="dashboard?tipe=claimmember&navigation=dashboard" rel="tcontent4">Member Claim</a>
    </li>
    
    </c:if>
    <li <c:if test="${tipe eq 'claimprovider'}"> class="selected" </c:if>>
        <a href="dashboard?tipe=claimprovider&navigation=dashboard" rel="tcontent4">Provider Claim</a>
    </li>

    <li <c:if test="${tipe eq 'claimdiagnosis'}"> class="selected" </c:if>>
        <a href="dashboard?tipe=claimdiagnosis&navigation=dashboard" rel="tcontent5">Diagnosis Claim</a>
    </li>	

</ul>
<br />
<form action="dashboard" method="GET" name="form1" id="form_layout">
	<input type="hidden" name="test" value="<c:out value="${dashboardURL}" />" />
    <input type="hidden" name="navigation" value="dashboard">
    <input type="hidden" name="tipe" value="<c:out value="${tipe}" />">
    <input type="hidden" name="tahun" value="<c:out value="${tahun}" />" />
    <table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tbody>
            <tr> 
                <td style="padding-bottom: 2px; padding-top: 5px;">        

                    <input title="<< Previous Year " accesskey="I" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:previousYear()" name="addclaim" value=" << Previous Year " type="button">
                    <label style="color: #000; font-size: 14px;">  <c:out value="${tahun}" /> </label>
                    <input title="Next Year >>" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:nextYear()" name="complete" value=" Next Year >> " type="button">
                </td>

                <td align="right"></td>
            </tr>
        </tbody>
    </table>
    <br />
    <br />
    <table class="tabDetailView" border="0" cellpadding="0" cellspacing="0" width="100%">
        <tbody>   
            <tr>
                <td  width="100%">&nbsp;

        
            <div id="chartArea" style="width:500px; height:300px;"></div>
        
        </td>		  

        </tr>			

        </tbody>
    </table>

    <table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tbody>
            <tr> 
                <td style="padding-top: 2px;">        
                  </td>

                <td align="right">&nbsp;</td>
            </tr>
        </tbody>
    </table>




</form>
<script language="Javascript">
    
    function previousYear(){
	document.form1.action = "dashboard";
	document.form1.navigation.value = "dashboard";
        document.form1.tahun.value = parseFloat(document.form1.tahun.value) - 1;
	document.form1.submit();
    }
    function nextYear(){
	document.form1.action = "dashboard";
	document.form1.navigation.value = "dashboard";
        document.form1.tahun.value = parseFloat(document.form1.tahun.value) + 1;
	document.form1.submit();
    }
</script>