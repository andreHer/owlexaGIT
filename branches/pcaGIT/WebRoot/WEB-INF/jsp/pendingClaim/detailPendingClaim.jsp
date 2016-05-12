<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<h1>Pending Claim</h1>

<form action="pendingclaim" method="GET" name="form1" id="form_layout">

	<input type="hidden" name="navigation" value="">
	<input type="hidden" name="pendingClaimId" value="<c:out value="${pendingClaim.pendingClaimId}" />">
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr> 
      <td style="padding-bottom: 2px;">        
        <input title="Edit [Alt+Shift+E]" accesskey="E" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:ubah()" name="Edit" value=" Update " type="button">
        <input title="Hapus [Alt+Shift+H]" accesskey="H" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:hapus()" name="Delete" value=" Delete " type="button">        		
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
						<a href="#" onclick='' class="listViewPaginationLinkS1" style="font-weight: normal;">View Change Log</a>
					</td>
					<td class="tabDetailViewDL" align="right" nowrap="nowrap">
						<a href="pendingclaim?navigation=search&searchtext=<c:out value="${searchtext}"/>&searchby=<c:out value="${searchby}" />&index=<c:out value="${index}"/>" class="tabDetailViewDFLink" style="font-weight: normal;">Return to List&nbsp;</a>					
					</td>
				</tr>
			</tbody>
		</table>
	  </td>
    </tr>
	
	
		
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Claim :&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${pendingClaim.claimId.claimNumber}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Pending Category :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><c:out value="${pendingClaim.pendingCategory.pendingCategoryName}"/></td>
	    </tr>
			
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Pending Subject :&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${pendingClaim.pendingSubject}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Pending Due Date :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><c:out value="${pendingClaim.pendingDueDate}"/></td>
	    </tr>
	
			
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Created Time :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${pendingClaim.createdTime}"/>   -   <c:out value="${pendingClaim.createdBy}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">Updated Time :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${pendingClaim.updatedTime}"/>   -   <c:out value="${pendingClaim.updatedBy}"/></td>
	    </tr>
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Remarks :&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="33%" colspan="3"><c:out value="${pendingClaim.remarks}"/></td>	      
	    </tr>
	
	</tbody>
</table>

	
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
		document.form1.action = "pendingclaim-form";
		document.form1.method = "GET";
		document.form1.submit();
	}
</script>
