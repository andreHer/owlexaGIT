<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<script type="text/javascript" src="openflashchart/swfobject.js"></script>
	<script type="text/javascript">
	
	    swfobject.embedSWF(
	    "openflashchart/open-flash-chart.swf", "chartArea",
	    "100%", "100%", "9.0.0", "expressInstall.swf",
	    {"data-file":"dashboard?navigation=queryclaimstat"} );
	
	</script>


<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Claim Monitoring Dashboard</h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>
<form action="dashboard" method="GET" name="form1" id="form_layout">	
    <input type="hidden" name="navigation" value="dashboardclaimstat">

    
    <table class="tabDetailView" border="0" cellpadding="0" cellspacing="0" width="100%">
        <tbody>   
            <tr>
                <td  width="100%">&nbsp;
                	<div id="resize" class="ui-widget-content" style="width:800px; height:500px;">
                		<div id="chartArea"></div>
                	</div>
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
	$(document).ready(function(){
		//alert("jQuery Ready");
		/*Add by aju on 20150407, for active menu...fiuuuhhhhhh*/
    	var nav = $("#mnuMainClaim");
		nav.addClass("active");
		
		var nav = $("#mnuClaimMonitorDashboard");
		nav.addClass("active");
		
		//Add by aju on 20150428, risaiiiizzzzzz fufufu :D
		$("#resize").resizable();
	    
	    
	});
	
	function ofc_resize(left, width, top, height)
	{
	var tmp = new Array(
	'left:'+left,
	'width:'+ width,
	'top:'+top,
	'height:'+height );
	}
	
	
    
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