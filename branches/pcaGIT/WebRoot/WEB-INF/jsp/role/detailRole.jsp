<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<ul id="maintab" class="shadetabs">

	<li class="selected">
		<a href="#" rel="tcontent1">Detail Role</a>
	</li>
	<li>
		<a href="roleaction?navigation=list&roleId=<c:out value="${role.roleId}" />" rel="tcontent2">Authority Code</a>
	</li>

</ul>
<br />

<form action="role" method="GET" name="form1" id="form_layout">
	<input type="hidden" name="navigation" value="">
	<input type="hidden" name="roleId" value="<c:out value="${role.roleId}" />">

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr> 
      <td style="padding-bottom: 2px;">        
        <input title="Edit [Alt+Shift+E]" accesskey="E" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:ubah()" name="Edit" value=" Update " type="button">
        <input title="Add Action [Alt+Shift+A]" accesskey="A" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:addAction()" name="Delete" value=" Add Action " type="button">        		
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
						<a href="role?navigation=search&searchtext=<c:out value="${searchtext}"/>&searchby=<c:out value="${searchby}" />&rowset=<c:out value="${rowser}" />&index=<c:out value="${index}"/>" class="tabDetailViewDFLink" style="font-weight: normal;">Return to List&nbsp;</a>					
					</td>
				</tr>
			</tbody>
		</table>
	  </td>
    </tr>
	
	
		
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Role Name :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${role.roleName}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
	
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Menu Template URL :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${role.menuTemplateUrl}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Sidebar Template URL :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><c:out value="${role.sidebarTemplateUrl}"/></td>
	    </tr>
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Created Time :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${role.createdTime}"/> - <c:out value="${role.createdBy}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">Modified Time :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${role.modifiedTime}"/> - <c:out value="${role.modifiedBy}"/></td>

	    </tr>
			
			
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Description :&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="33%" colspan="3"><c:out value="${role.description}"/></td>
	    </tr>
	
			
	
			
			
    <tr>
      <td colspan="20"  style="padding: 0px;">
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tbody>
				<tr>
					<td style="text-align: left;" class="tabDetailViewDL">&nbsp;

					</td>
					<td class="tabDetailViewDL" align="right" nowrap="nowrap">
						<a href="role?navigation=search&searchtext=<c:out value="${searchtext}"/>&searchby=<c:out value="${searchby}" />&rowset=<c:out value="${rowser}" />&index=<c:out value="${index}"/>" class="tabDetailViewDFLink" style="font-weight: normal;">Return to List&nbsp;</a>					
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
	
	function addAction (){
	
	}
	function kembali (){
		document.form1.navigation.value = "back";
		document.form1.submit();
	}
	function ubah (){
		document.form1.navigation.value = "update";
		document.form1.action = "role-form";
		document.form1.method = "GET";
		document.form1.submit();
	}
</script>
