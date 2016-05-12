<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


           <script type="text/javascript" src="openflashchart/swfobject.js"></script>
<script type="text/javascript">

    swfobject.embedSWF(
    "openflashchart/open-flash-chart.swf", "claimGrowthChartArea",
    "500", "300", "9.0.0", "expressInstall.swf",
    {"data-file":"dashboard?navigation=claimgrowth-<c:out value="${reportYear}" />"} );

</script>
        
     <script type="text/javascript">

    swfobject.embedSWF(
    "openflashchart/open-flash-chart.swf", "memberGrowthChartArea",
    "500", "300", "9.0.0", "expressInstall.swf",
    {"data-file":"dashboard?navigation=membergrowth-<c:out value="${reportYear}" />"} );

</script>
<script type="text/javascript">
    swfobject.embedSWF(
    "openflashchart/open-flash-chart.swf", "groupClaimChartArea",
    "500", "300", "9.0.0", "expressInstall.swf",
    {"data-file":"dashboard?navigation=groupclaim-<c:out value="${reportYear}" />"} );

</script>
<script type="text/javascript">

    swfobject.embedSWF(
    "openflashchart/open-flash-chart.swf", "memberClaimChartArea",
    "500", "300", "9.0.0", "expressInstall.swf",
    {"data-file":"dashboard?navigation=claimprovider-<c:out value="${reportYear}" />"} );

</script>
<script type="text/javascript">

    swfobject.embedSWF(
    "openflashchart/open-flash-chart.swf", "providerClaimChartArea",
    "500", "300", "9.0.0", "expressInstall.swf",
    {"data-file":"dashboard?navigation=claimdiagnosis-<c:out value="${reportYear}" />"} );

</script>
   

<ul id="maintab" class="shadetabs">

    <li <c:if test="${tipe eq 'claimgrowth'}"> class="selected" </c:if>>
        <a href="dashboard?tipe=claimgrowth&navigation=dashboard" rel="tcontent1">Chart Report</a>
    </li>
    <li <c:if test="${tipe eq 'statistic'}"> class="selected" </c:if>>
        <a href="dashboard?navigation=statistic" rel="tcontent2">Data Statistic</a>
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

        
            <div id="claimGrowthChartArea" style="width:500px; height:300px;"></div>
            
        
        </td>
        <td>
         	<div id="memberGrowthChartArea" style="width:500px; height:300px;"></div>
        </td>		  

        </tr>			
			 <tr>
                <td  width="100%">&nbsp;

        
            <div id="groupClaimChartArea" style="width:500px; height:300px;"></div>
            
        
        </td>
        <td>
         	<div id="memberClaimChartArea" style="width:500px; height:300px;"></div>
        </td>		  

        </tr>			
        <tr>
                <td  width="100%">&nbsp;

        
            <div id="providerClaimChartArea" style="width:500px; height:300px;"></div>
            
        
        </td>
        <td>
         	<div id="diagnosisClaimChartArea" style="width:500px; height:300px;"></div>
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