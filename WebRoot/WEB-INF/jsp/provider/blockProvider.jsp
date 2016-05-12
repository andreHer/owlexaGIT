<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<ul id="maintab" class="shadetabs">

<form action="provider" method="POST" name="form1" id="form_layout">

	<input type="hidden" name="navigation" value="confirmblock">
	<input type="hidden" name="providerId" value="<c:out value="${provider.providerId}" />">

	
<table class="tabDetailView" border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td colspan="20"  style="padding: 0px;">
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tbody>
				<tr>
					<td style="text-align: left;" class="tabDetailViewDL">&nbsp;
					
					</td>
					<td class="tabDetailViewDL" align="right" nowrap="nowrap">
						&nbsp;					
					</td>
				</tr>
			</tbody>
		</table>
	  </td>
    </tr>
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Provider Name :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${provider.providerName}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Provider Category :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><c:out value="${provider.providerCategoryId.providerCategoryName}" /></td>
	    </tr>	
	
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Block Reason :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="33%" colspan="3">	      	
	      		<textarea rows="8" cols="60" name="reason" style="border: 1px #000 solid;"></textarea>	      		      
	      </td>	      
	    </tr>
		
	    <tr>
      <td colspan="20"  style="padding: 0px;">
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tbody>
				<tr>
					<td style="text-align: left;" class="tabDetailViewDL">&nbsp;
						
					</td>
					<td class="tabDetailViewDL" align="right" nowrap="nowrap">
						&nbsp;			
					</td>
				</tr>
			</tbody>
		</table>
	  </td>
    </tr>
	</tbody>
</table>
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td style="padding-top: 2px;">
	    <input type="hidden" name="notyet" value="">
        <input title="Save [Alt+Shift+S]" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:blockProvider()" name="Save" value=" Block Provider " type="submit">
        <input title="Cancel [Alt+Shift+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:cancel()" name="Cancel" value=" Cancel " type="button">         
	  </td>
      <td align="right"></td>
    </tr>
  </tbody>
</table>
		
		
</form>
<script language="javascript">
	
	
	function cancel (){
		document.form1.navigation.value = "detail";
		document.form1.submit();
	}
	
	function blockProvider (){
	
		var delConfirm = window.confirm ("Are You Sure Want To Block This Provider ?");
		if (delConfirm){
			document.form1.navigation.value = "confirmblock";
			document.form1.action = "provider";
			document.form1.method = "POST";
			document.form1.submit();	
		}
	}
	
</script>
