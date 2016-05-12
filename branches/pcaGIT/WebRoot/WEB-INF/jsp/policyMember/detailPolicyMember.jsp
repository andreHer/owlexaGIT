<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<h1>Policy Member</h1>

<form action="policymember" method="GET" name="form1" id="form_layout">

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr> 
      <td style="padding-bottom: 2px;">        
        
		<input title="Edit [Alt+E]" accesskey="E" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:ubah()" name="Edit" value=" Update " type="button">
		
        <input title="Hapus [Alt+H]" accesskey="H" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:hapus()" name="Delete" value=" Delete " type="button">        		
		
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
						<a href="policymember?navigation=search&searchtext=<c:out value="${searchtext}"/>&searchby=<c:out value="${searchby}" />&index=<c:out value="${index}"/>" class="tabDetailViewDFLink" style="font-weight: normal;">Return to List&nbsp;</a>					
					</td>
				</tr>
			</tbody>
		</table>
	  </td>
    </tr>
	<input type="hidden" name="navigation" value="">
	<input type="hidden" name="policyMemberId" value="<c:out value="${policyMember.policyMemberId}" />">
	
	
		
	
		
	
		
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Join Date :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${policyMember.joinDate}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Effective Date :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${policyMember.effectiveDate}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Expire Date :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${policyMember.expireDate}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Resigned Date :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${policyMember.resignedDate}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Status :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${policyMember.status}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
				
	</tbody>
</table>

	<h2>Foreign Affair</h2>

	
		<label for="Policy Id">Policy Id</label>
			<div id="ffbox">
			<c:out value="${policyMember.policyId.policyId}" />
			</div>
	
		<label for="Member Id">Member Id</label>
			<div id="ffbox">
			<c:out value="${policyMember.memberId.memberId}" />
			</div>
		
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
		document.form1.action = "policymember-form";
		document.form1.method = "GET";
		document.form1.submit();
	}
</script>
