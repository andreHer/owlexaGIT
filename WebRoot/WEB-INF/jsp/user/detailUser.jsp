<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>



<form action="user" method="GET" name="form1" id="form_layout">

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr> 
      <td style="padding-bottom: 2px;">        
        <input title="Edit [Alt+Shift+E]" accesskey="E" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:ubah()" name="Edit" value=" Update " type="button">
        <input title="Delete [Alt+Shift+D]" accesskey="D" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:hapus()" name="Delete" value=" Delete " type="button">        		
        <input title="Change Password [Alt+Shift+P]" accesskey="P" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:changePassword()" name="ChangePasswords" value=" Change Password " type="button">        		        
		<c:if test="${user.status.statusId eq \"1\"}">
			<input title="Block [Alt+Shift+B]" accesskey="B" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:blockUser()" name="block" value=" Block " type="button">        		        
		</c:if>		
		<c:if test="${user.status.statusId eq \"2\"}">
			<input title="Activate [Alt+Shift+A]" accesskey="A" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:activateUser()" name="activate" value=" Activate " type="button">        		                
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
						<a href="user?navigation=search&searchtext=<c:out value="${searchtext}"/>&searchby=<c:out value="${searchby}" />&index=<c:out value="${index}" />&index=<c:out value="${index}"/>" class="tabDetailViewDFLink" style="font-weight: normal;">
							<img src="images/back-arrow.png" title="Return to List"/>
						</a>	
					</td>
				</tr>
			</tbody>
		</table>
	  </td>
    </tr>
	<input type="hidden" name="navigation" value="">
	<input type="hidden" name="userId" value="<c:out value="${user.userId}" />">
	
	
		
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Username :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${user.username}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Role </td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><c:out value="${user.roleId.roleName}" /></td>
	    </tr>
	
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">First Name :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${user.firstName}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">Status :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%">
			  <c:if test="${user.status.statusId eq -1}">PENDING</c:if>
			<c:if test="${user.status.statusId eq 1}">ACTIVE</c:if>
			<c:if test="${user.status.statusId eq -3}">PENDING CHANGEPLAN</c:if>
			<c:if test="${user.status.statusId eq -2}">BLOCKED</c:if>
			<c:if test="${user.status.statusId eq 2}">TERMINATED</c:if>
			<c:if test="${user.status.statusId eq 3}">RESIGNED</c:if>
			<c:if test="${user.status.statusId eq 4}">INACTIVE</c:if>
			<c:if test="${user.status.statusId eq 5}">INITIALIZED</c:if></td>
	    </tr>
	
			
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Last Name :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${user.lastName}"/></td>
	       <td class="tabDetailViewDL" valign="top" width="17%">Telephone :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${user.telephone}"/></td>
	    </tr>
	
			
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Email :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${user.email}"/></td>
   <td class="tabDetailViewDL" valign="top" width="17%">Telephone Ext :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${user.telephoneExt}"/></td>
	    </tr>
	
	
	
			
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">Mobile Phone :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${user.mobilePhone}"/></td>

	    </tr>
			
	
			
		
	
			
	
			
	
			
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Created Time :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${user.createdTime}"/>  - <c:out value="${user.createdBy}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">Modified Time :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${user.modifiedTime}"/> - <c:out value="${user.modifiedBy}"/></td>
	    </tr>
			
	
	
			
	
	    <tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Description :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%" colspan="3"><c:out value="${user.description}"/></td>
	    
	    </tr>
	</tbody>
</table>
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr> 
      <td style="padding-top: 2px;">        
      
		</td>
      <td align="right"></td>
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
	function changePassword(){
		document.form1.navigation.value = "chpass";
		document.form1.action = "user";
		document.form1.submit();
	}
	function blockUser(){
		document.form1.navigation.value = "block";
		document.form1.action = "user";
		document.form1.submit();
	}
	function activateUser(){
		document.form1.navigation.value = "activate";
		document.form1.action = "user";
		document.form1.submit();
	}
	function ubah (){
		document.form1.navigation.value = "update";
		document.form1.action = "user-form";
		document.form1.method = "GET";
		document.form1.submit();
	}
</script>
