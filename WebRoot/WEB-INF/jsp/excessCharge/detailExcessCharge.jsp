<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>



<%
String alert = (String) request.getAttribute("alert");
int index = 0;
int totalIndex = 0;
int count = 0;
int countSet = 0;
int indexint = 0;

try {

	index = WebUtil.getAttributeInteger(request,"index",0).intValue();
	count = ((Integer) request.getAttribute ("count")).intValue();
	countSet = ((Integer) request.getAttribute ("countSet")).intValue();
	totalIndex = ((Integer) request.getAttribute ("halAkhir")).intValue();
	indexint = index;
	
}
catch (Exception e){
}

if (alert != null && !alert.trim().equals("")) {%>
	<%}%>

<%
String rowclass = "";
int i=0;


%>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Detail Excess Charge</h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>

<form action="excesscharge" method="GET" name="form1" id="form_layout">

	<input type="hidden" name="navigation" value="">
	<input type="hidden" name="excessReminderId" value="">
	<input type="hidden" name="excessChargeId" value="<c:out value="${excessCharge.excessChargeId}" />">
	
	
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr> 
      <td style="padding-bottom: 2px;">        
   			<c:if test="${(theUser.userType eq 2) and (theUser.roleId.roleId eq 7 or theUser.roleId.roleId eq 8)}">
      
				<c:if test="${excessCharge.excessChargeStatus.paymentStatusId eq 1 }">
				  <input title="Pay Excess [Alt+Shift+P]" accesskey="P" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:payExcess()" name="payExcessButton" value=" Pay Excess " type="button">        		        
				  <input title="Remind Excess [Alt+Shift+R]" accesskey="R" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:remindExcess()" name="remindExcessButton" value=" Reminder Excess " type="button">        		                
				  <input title="Mark as Bad Excess Charge [Alt+Shift+B]" accesskey="B" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:badExcess()" name="badExcessButton" value=" Bad Excess " type="button">
				  
				  <c:if test="${excessCharge.excessLetterSent eq 0 }">
				  	<input title="Confirm Excess Sent [Alt+Shift+B]" accesskey="B" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:confirmSend()" name="confirmSendButton" value=" Confirm Send " type="button">
				  </c:if>
				          		                
				</c:if>  
        		<input title="Print [Alt+Shift+P]" accesskey="P" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:printExcess()" name="printExcessButton" value=" Print " type="button">
        
        	</c:if>
    	</td>
      <td align="right"></td>
    </tr>
  </tbody>
</table>

<%@ include file="../mainContentExcessCharge.jsp" %>

<br />
<!--  Excess Charge Reminder History -->

<table class="listView" width="100%" cellspacing="0" cellpadding="0" >
	<tbody>
	<tr >
      <td colspan="20" align="right"><table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tbody>
          <tr>
            <td  align="left">			</td>
            <td  align="right" nowrap="nowrap">
				
				
			</td>
          </tr>
        </tbody>
      </table></td>
    </tr>
	<tr height="20">
		<td width="6%" nowrap="nowrap" class="listViewThS1" scope="col">
			<img src="images/blank.gif" alt="asd" height="1" width="1">No.</td>		

<!-- ini default generated table from database -->
		   		   		   			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">
						Reminder Date 
					</td>
		
			   		   			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">
						Reminder By &nbsp;</td>

					<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Reminder Counter </td>			
		
			   	   <td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">&nbsp;</td>
	</tr>


	<c:forEach items="${excessReminders}" var="excessReminder">
	<%
		if (i % 2 == 0){
			rowclass = "col1";
		} 
		else{
			rowclass= "col2";
		}
		i++;
	%>
	 <tr height="20">
      <td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><%=(i+((indexint-1)*countSet))%></td>

		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
				<c:out value="${excessReminder.reminderDate}" />			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
				<c:out value="${excessReminder.reminderBy}" />			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
				<c:out value="${excessReminder.reminderCounter}" />			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top">
			<a href="javascript:printReminder('<c:out value="${excessReminder.excessReminderId}" />')">
				<img src="images/view.gif" class="action_icon" alt="View" title="View"></a>
				

			<!-- ini default edit table for each data -->



		
	  </td>
    </tr>
   
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	
	</c:forEach>
	
<tr>
            <td  align="left"></td>
            <td  align="right" nowrap="nowrap" colspan="20">
				
				
			</td>
          </tr>
	</tbody>
	</table>
<!--  END EXCESS CHARGE REMINDER HISTORY -->


</form>
<script language="javascript">
	function hapus (){
	var delConfirm = window.confirm ("Are You Sure Want To Delete This Entry ?");
		if (delConfirm){
			document.form1.navigation.value = "delete";
			document.form1.submit();
		}
	}
	function kembali (){
		document.form1.navigation.value = "back";
		document.form1.submit();
	}
	function ubah (){
		document.form1.navigation.value = "update";
		document.form1.action = "excesscharge-form";
		document.form1.method = "GET";
		document.form1.submit();
	}
	function payExcess(){
		document.form1.navigation.value = "detailexcess";
		document.form1.action = "fund-form";
		document.form1.method = "GET";
		document.form1.submit();
	}
	function badExcess(){
		var delConfirm = window.confirm ("Are You Sure Want To Marked This Entry as Bad Excess Charge ?");
		
		if (delConfirm){
			document.form1.navigation.value = "bad";
			document.form1.action = "excesscharge";
			document.form1.method = "GET";
			document.form1.submit();
		}
	}
	function confirmSend(){
		var delConfirm = window.confirm ("Are You Sure Want To Confirm Excess Sending ?");
		
		if (delConfirm){
			document.form1.navigation.value = "confirmsend";
			document.form1.action = "excesscharge";
			document.form1.method = "GET";
			document.form1.submit();
		}
	}
	function printReminder(idx){
		var delConfirm = window.confirm ("Are You Sure Want To Print this Excess Reminder ?");
		
		if (delConfirm){
			window.open ("excessreminder?navigation=print&url=detailExcessCharge&excessReminderId="+idx,"Search","width=1024, height=768, menubar=yes, status=no, toolbar=no, scrollbars=yes, resizable=yes");			
		}
	}
	function printExcess(){
		window.open ("excesscharge?navigation=print&url=detailExcessCharge&excessChargeId=<c:out value="${excessCharge.excessChargeId}" />","Search","width=1024, height=768, menubar=yes, status=no, toolbar=no, scrollbars=yes, resizable=yes");
	}
	function remindExcess(){
		document.form1.navigation.value = "tambah";
		document.form1.action = "excessreminder-form";
		document.form1.method = "GET";
		document.form1.submit();
	}
</script>
