<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


   

<ul id="maintab" class="shadetabs">

    <li >
        <a href="dashboard?tipe=claimgrowth&navigation=dashboard" rel="tcontent1">Chart Report</a>
    </li>
    <li class="selected">
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

                   
                </td>

                <td align="right"></td>
            </tr>
        </tbody>
    </table>
    <b>Top 10 Claimant Statistic</b>
    <br />
    <br />
    <table class="tabDetailView" border="0" cellpadding="0" cellspacing="0" width="100%">
        <tbody>   
            <tr>
                <td  width="100%">
                	<table class="listView" cellspacing="0" cellpadding="0" width="100%">
						<tbody>
						
						<tr height="20">
							<td width="4%" nowrap="nowrap" class="listViewThS1" scope="col">No.</td>	
							<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Member Name</td>
							<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Employee Name</td>
							 <!-- 
							<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Employee Number</td> -->
							<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Count</td>
							<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Claim Amount</td>	   	
						</tr>
						<c:forEach items="${Top10Claimants}" var="claim" varStatus="status" >	
						 	<tr height="20">
					      		<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${status.index+1}" /></td>
					      		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${claim.memberName}" /></td>
								<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${claim.employeeName}" /></td>
								<!-- 
								<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${claim.employeeNumber}" /></td>
								 -->			   		   		
								<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${claim.claimCount}" /></td>
								<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${claim.claimAmount}" /></td>								
					    	</tr>
					    	<tr>
					      		<td colspan="20" class="listViewHRS1"></td>
					    	</tr>	
						</c:forEach>
						
						</tbody>
					</table>
		        </td>
		        <td>
		         	
		        </td>
       		 </tr>			
			 <tr>
                <td  width="100%">&nbsp;
                <br />
                <br />
<b>Top 10 Treatment Upgrade Statistic</b>
	<br />
	<br />
        
            <table class="listView" cellspacing="0" cellpadding="0" width="100%">
						<tbody>
						
						<tr height="20">
							<td width="4%" nowrap="nowrap" class="listViewThS1" scope="col">No.</td>	
							<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Member Name</td>
							<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Employee Name</td>
							<!-- 
							<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Employee Number</td> -->
							<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Count</td>
							<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Claim Amount</td>	   	
						</tr>
						<c:forEach items="${Top10Upgrades}" var="claim" varStatus="status" >	
						 	<tr height="20">
					      		<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${status.index+1}" /></td>
					      		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${claim.memberName}" /></td>
								<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${claim.employeeName}" /></td>
								<!-- 
								<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${claim.employeeNumber}" /></td>
								 -->			   		   		
								<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${claim.claimCount}" /></td>
								<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${claim.claimAmount}" /></td>								
					    	</tr>
					    	<tr>
					      		<td colspan="20" class="listViewHRS1"></td>
					    	</tr>	
						</c:forEach>
						
						</tbody>
					</table>
            
        
        </td>
        <td>
         	
        </td>		  

        </tr>			
        <tr>
                <td  width="100%">&nbsp;
  <br />
                <br />
<b>Top 10 Diagnosis Statistic</b>
	<br />
        
            <table class="listView" cellspacing="0" cellpadding="0" width="100%">
						<tbody>
						
						<tr height="20">
							<td width="4%" nowrap="nowrap" class="listViewThS1" scope="col">No.</td>	
							<td scope="col" class="listViewThS1" nowrap="nowrap" width="20%">Diagnosis Name</td>
							<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Diagnosis Code</td>
							<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Count</td>
							<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Claim Amount</td>	   	
						</tr>
						<c:forEach items="${Top10Diagnosis}" var="claim" varStatus="status" >	
						 	<tr height="20">
					      		<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${status.index+1}" /></td>
					      		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${claim.diagnosisName}" /></td>
								<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${claim.diagnosisCode}" /></td>
											   		   		
								<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${claim.claimCount}" /></td>
								<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${claim.claimAmount}" /></td>								
					    	</tr>
					    	<tr>
					      		<td colspan="20" class="listViewHRS1"></td>
					    	</tr>	
						</c:forEach>
						
						</tbody>
					</table>
            
        
        </td>
        <td>
         	
        </td>		  

        </tr>	
         <tr>
                <td  width="100%">&nbsp;

        
            <table class="listView" cellspacing="0" cellpadding="0" width="100%">
						<tbody>
						
						<tr height="20">
							<td width="4%" nowrap="nowrap" class="listViewThS1" scope="col">No.</td>	
							<td scope="col" class="listViewThS1" nowrap="nowrap" width="30%">Provider Name</td>
							<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Count</td>
							<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Claim Amount</td>	   	
						</tr>
						<c:forEach items="${Top10Providers}" var="claim" varStatus="status" >	
						 	<tr height="20">
					      		<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${status.index+1}" /></td>
					      		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${claim.providerName}" /></td>			   		   		
								<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${claim.claimCount}" /></td>
								<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${claim.claimAmount}" /></td>								
					    	</tr>
					    	<tr>
					      		<td colspan="20" class="listViewHRS1"></td>
					    	</tr>	
						</c:forEach>
						
						</tbody>
					</table>
            
        
        </td>
        <td>
         	
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