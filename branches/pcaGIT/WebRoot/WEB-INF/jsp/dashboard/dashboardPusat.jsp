<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
 <%@page import="java.io.FileReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.File"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="java.text.DecimalFormat"%>

 <%
 int count = 0;
 
 int countTotalClaim = 0;
 int countUser = 0;
 float countSLA = 0;
 int countChecked = 0;
 int countPending = 0;
 int countReject = 0;
 int countCDV = 0;
 
 count = ((Integer) request.getAttribute ("count")).intValue();
 countTotalClaim = ((Integer) request.getAttribute ("countTotalClaim")).intValue();
 countUser = ((Integer) request.getAttribute ("countUser")).intValue();
 countChecked = ((Integer) request.getAttribute ("countChecked")).intValue();
 countPending = ((Integer) request.getAttribute ("countPending")).intValue();
 countReject = ((Integer) request.getAttribute ("countReject")).intValue();
 countCDV = ((Integer) request.getAttribute ("countCDV")).intValue();
 countSLA = ((Float) request.getAttribute ("countSLA")).floatValue();
  
 %>

    <body>
    
    
           &gt;<script type="text/javascript" src="openflashchart/swfobject.js"></script>
        <script type="text/javascript">
            swfobject.embedSWF(
            "openflashchart/open-flash-chart.swf", "memberGrowth",
            "550", "200", "9.0.0", "expressInstall.swf",
            {"data-file":"dashboard?navigation=membergrowth&reportYear=<c:out value="${reportYear}" />"} );
        </script>
        
        
        
       
      	 <script type="text/javascript">

            swfobject.embedSWF(
            "openflashchart/open-flash-chart.swf", "groupClaim",
            "550", "200", "9.0.0", "expressInstall.swf",
            {"data-file":"dashboard?navigation=groupclaim&reportYear=<c:out value="${reportYear}" />"} );

        </script>
        
        <html>
        <body>
        
<style type="text/css">
<!--
.style1 {
	font-size: 36px;
	font-weight: bold;
	font-family: Verdana, Arial, Helvetica, sans-serif;
}
-->
</style>

<style type="text/css">
<!--
.style2 {font-size: 18px; font-weight: bold; font-family: Verdana, Arial, Helvetica, sans-serif; }
.style3 {color: #FF00CC}
-->
</style>

<style type="text/css">
<!--
.style4 {color: #0066FF}
-->
</style>

<%
//Write to do
   // Set refresh, autoload time as 120 seconds
   response.setIntHeader("Refresh", 120);
   // Get current time
   Calendar calendar = new GregorianCalendar();
   String am_pm;
   int hour = calendar.get(Calendar.HOUR);
   int minute = calendar.get(Calendar.MINUTE);
   int second = calendar.get(Calendar.SECOND);
   if(calendar.get(Calendar.AM_PM) == 0)
      am_pm = "AM";
   else
      am_pm = "PM";
   String CT = hour+":"+ minute +":"+ second +" "+ am_pm;
   //out.println("Crrent Time: " + CT + "\n");
 %>       
 
<table width="1530" border="0">
  <tr>
    <td width="301">&nbsp;</td>
    <td width="918"><div align="center"><span class="style2">claim monitoring dashboard</span> </div></td>
    <td width="297">&nbsp;</td>
  </tr>
</table>
<table width="1530" border="0">
  <tr>
    <td width="301">&nbsp;</td>
    <td width="918">&nbsp;</td>
    <td width="297">&nbsp;</td>
  </tr>
</table>
<table width="1530" border="0">
  <tr>
    <td width="301">&nbsp;</td>
    <td width="918">&nbsp;</td>
    <td width="297">&nbsp;</td>
  </tr>
</table>
<table width="200" border="0">
  <tr>
    <td><div align="center"><span class="style2">TOTAL CLAIM    
      </span>
    </div>
      <table width="300" height="390" border="1">
      <col style="background-color: #6374AB; color: #ffffff" />
        <tr>
          <td><div align="center"><span class="style2"><c:out value="${countTotalClaim}"/></span></td>
        </tr>
      </table>
    <p>&nbsp;</p></td>
    <td><span class="style4">'
        </span>
      <table width="52" height="134" border="0">
        <tr>
          <td height="123">&nbsp;</td>
        </tr>
      </table>
    <p>&nbsp;</p>
    <p><span class="style4">'
        </span></p>
    <table width="52" height="125" border="0">
      <tr>
        <td>&nbsp;</td>
      </tr>
    </table>    <p>&nbsp;</p>
    <p><span class="style4">'
        </span></p>
    <table width="51" height="101" border="0">
      <tr>
        <td>&nbsp;</td>
      </tr>
    </table>    <p>&nbsp;</p></td>
    <td><div align="center"><span class="style2">USER</span>
    </div>
      <table width="287" height="133" border="1">
      <col style="background-color: #6374AB; color: #ffffff" />
        <tr>
          <td height="127"><div align="center"><span class="style2"><c:out value="${countUser}"/></span></td>
        </tr>
      </table>
    <p>&nbsp;</p>
    <p align="center"><span class="style2">Checked</span></p>
    <table width="285" height="120" border="1">
    <col style="background-color: #6374AB; color: #ffffff" />
      <tr>
        <td><div align="center"><span class="style2"><c:out value="${countChecked}"/></span></td>
      </tr>
    </table>    <p>&nbsp;</p>
    <p><span class="style4">'
        </span></p>
    <table width="300" height="106" border="0">
      <tr>
        <td>&nbsp;</td>
      </tr>
    </table>    <p>&nbsp;</p></td>
    <td><span class="style4">'
        </span>
      <table width="52" height="131" border="0">
        <tr>
          <td height="127">&nbsp;</td>
        </tr>
      </table>
    <p>&nbsp;</p>
    <p><span class="style4">'
        </span></p>
    <table width="51" height="122" border="0">
      <tr>
        <td width="45">&nbsp;</td>
      </tr>
    </table>    <p>&nbsp;</p>
    <p><span class="style4">'
        </span></p>
    <table width="52" height="109" border="0">
      <tr>
        <td height="62">&nbsp;</td>
      </tr>
    </table>    <p>&nbsp;</p></td>
    <td><span class="style4">'
        </span>
      <table width="292" height="137" border="0">
        <tr>
          <td height="65">&nbsp;</td>
        </tr>
      </table>
    <p>&nbsp;</p>
    <p align="center"><span class="style2">Pending</span></p>
    <table width="300" height="121" border="1">
    <col style="background-color: #6374AB; color: #ffffff" />
      <tr>
        <td><div align="center"><span class="style2"><c:out value="${countPending}"/></span></td>
      </tr>
    </table>    <p>&nbsp;</p>
    <p align="center"><span class="style2">CDV ISSUE</span></p>
    <table width="300" height="110" border="1">
    <col style="background-color: #6374AB; color: #ffffff" />
      <tr>
        <td height="82"><div align="center"><span class="style2"><c:out value="${countCDV}"/></td>
      </tr>
    </table>    <p>&nbsp; </p></td>
    <td><span class="style4">'
        </span>
      <table width="48" height="126" border="0">
        <tr>
          <td width="88" height="64">&nbsp;</td>
        </tr>
      </table>
    <p>&nbsp;</p>
    <p><span class="style4">'
        </span></p>
    <table width="52" height="127" border="0">
      <tr>
        <td>&nbsp;</td>
      </tr>
    </table>    <p>&nbsp;</p>
    <p><span class="style4">'
        </span></p>
    <table width="51" height="115" border="0">
      <tr>
        <td>&nbsp;</td>
      </tr>
    </table>    <p>&nbsp;</p></td>
    <td><div align="center"><span class="style2">SLA</span>
    </div>
      <table width="298" height="127" border="1">
      <col style="background-color: #6374AB; color: #ffffff" />
        <tr>
          <td width="288"><div align="center"><span class="style2"><c:out value="${countSLA}"/></span></td>
        </tr>
      </table>
    <p>&nbsp;</p>
    <p align="center"><span class="style2">Reject</span></p>
    <table width="300" height="123" border="1">
    <col style="background-color: #6374AB; color: #ffffff" />
      <tr>
        <td><div align="center"><span class="style2"><c:out value="${countReject}"/></span></td>
      </tr>
    </table>    <p>&nbsp;</p>
    <p><span class="style4">'
        </span></p>
    <table width="300" height="114" border="0">
      <tr>
        <td>&nbsp;</td>
      </tr>
    </table>    <p>&nbsp;</p></td>
  </tr>
</table>       
        </body>
       </html>

        
        
        <form action="dashboard">
            
            
        </form>
        <div id="memberGrowth" style="width:400px; height:300px;"></div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                
        
        <br />
        <br />
        <div id="groupClaim" style="width:400px; height:300px;"></div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                
         <br />
        <br />
        
        <script language="Javascript">
			$(document).ready(function(){
				//alert("jQuery ready");
				/*Add by aju on 20150407, for active menu...fiuuuhhhhhh*/
				var nav = $("#mnuMainDashboard");
				nav.addClass("active");
				
			});
		</script>
    </body>
