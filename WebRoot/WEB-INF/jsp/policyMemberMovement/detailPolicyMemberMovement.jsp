<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<ul id="maintab" class="shadetabs">

	<li class="selected">
		<a href="policymembermovement?navigation=detail&id=<c:out value="${policyMemberMovement.id}" />" rel="tcontent7">Detail Movement</a>
	</li>	
	<li >
		<a href="externalrawdata?navigation=listmovement&movementId=<c:out value="${policyMemberMovement.id}" />" rel="tcontent1">Raw Data</a>
	</li>
	
	<li>
		<a href="memberimport?navigation=listmovement&movementId=<c:out value="${policyMemberMovement.id}" />" rel="tcontent4">Imported Member</a>
	</li>	
	<li >
		<a href="member?navigation=listmovement&movementId=<c:out value="${policyMemberMovement.id}" />" rel="tcontent5">Activated Member</a>
	</li>	
	
</ul>
<br />

<form action="policymembermovement" method="GET" name="form1" id="form_layout">

	<input type="hidden" name="navigation" value="">
	<input type="hidden" name="policyId" value="<c:out value="${policyMemberMovement.policyId.policyId }" />" />
	<input type="hidden" name="id" value="<c:out value="${policyMemberMovement.id}" />">
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr> 
      <td style="padding-bottom: 2px;">
      <c:if test="${policyMemberMovement.status eq 0}">
		<input title="Edit [Alt+E]" accesskey="E" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:extract()" name="Edit" value=" Extract " type="button">
		</c:if>        
        <c:if test="${policyMemberMovement.status eq 1}">
		<input title="Edit [Alt+E]" accesskey="E" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:migrate()" name="Edit" value=" Migrate " type="button">
		</c:if>
		<c:if test="${policyMemberMovement.status eq 2}">
        <input title="Hapus [Alt+H]" accesskey="H" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:activate()" name="Delete" value=" Activate " type="button">        		
		</c:if>
		</td>
      <td align="right"></td>
    </tr>
  </tbody>
</table>
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
												<a href="policymembermovement?navigation=listpolicy&policyId=<c:out value="${policyMemberMovement.policyId.policyId}" />">Return to List&nbsp;</a>					
					</td>
				</tr>
			</tbody>
		</table>
	  </td>
    </tr>
	
	
		
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Movement Number :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%">
	     
	      <c:out value="${policyMemberMovement.movementNumber}"/></td> 
	      <td class="tabDetailViewDL" valign="top" width="17%">Policy  :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${policyMemberMovement.policyId.policyNumber}"/></td>
	    </tr>
	
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Movement Date :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${policyMemberMovement.movementDate}"/></td>
	         <td class="tabDetailViewDL" valign="top" width="17%">Movement Type :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%">
			  	
			  	<c:if test="${policyMemberMovement.movementType eq 1}">ADDITION</c:if>
			  	<c:if test="${policyMemberMovement.movementType eq 2}">DELETION</c:if>
			  	<c:if test="${policyMemberMovement.movementType eq 3}">UPGRADE/DOWNGRADE</c:if>
			  </td>
	    </tr>
	
			
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Status :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%">
			  
			  <c:if test="${policyMemberMovement.status eq 0}">INITIALIZED</c:if>
			  	<c:if test="${policyMemberMovement.status eq 1}">EXTRACTED</c:if>
			  	<c:if test="${policyMemberMovement.status eq 2}">MIGRATED</c:if>
			  	<c:if test="${policyMemberMovement.status eq 3}">ACTIVATED</c:if></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
			
						
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Movement File Path :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${policyMemberMovement.movementFilePath}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
	
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Created Time :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${policyMemberMovement.createdTime}"/> - <c:out value="${policyMemberMovement.createdBy}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">Modified Time :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${policyMemberMovement.modifiedTime}"/> - <c:out value="${policyMemberMovement.modifiedBy}"/></td>
	    </tr>
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Description :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" colspan=3 valign="top" width="33%"><c:out value="${policyMemberMovement.description}"/></td>
	    
	    </tr>
	
		
			
	
	 <tr>
      <td colspan="20"  style="padding: 0px;">
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tbody>
				<tr>
					<td style="text-align: left;" class="tabDetailViewDL">&nbsp;

					</td>
					<td class="tabDetailViewDL" align="right" nowrap="nowrap">
						<a href="policymembermovement?navigation=listpolicy&policyId=<c:out value="${policyMemberMovement.policyId.policyId}" />">Return to List&nbsp;</a>					
					</td>
				</tr>
			</tbody>
		</table>
	  </td>
    </tr>
	
	</tbody>
</table>


		
</form>
<script language="javascript">
	function migrate (){
	var delConfirm = window.confirm ("Are You Sure Want To Migrate This Entry ?");
		if (delConfirm){
			document.form1.navigation.value = "migrate";
			document.form1.submit();
		}
	}
	function activate (){
		var delConfirm = window.confirm ("Are You Sure Want To Activate This Entry ?");
		if (delConfirm){
			document.form1.navigation.value = "activate";
			document.form1.submit();
		}
	}
	
	function extract (){
		var delConfirm = window.confirm ("Are You Sure Want To Extract This Entry ?");
		if (delConfirm){
			document.form1.navigation.value = "extract";
			document.form1.submit();
		}
	}
	function kembali (){
		document.form1.navigation.value = "back";
		document.form1.submit();
	}
</script>
