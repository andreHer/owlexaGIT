<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>



<form action="user" method="GET" name="form1" id="form_layout">
	<input type="hidden" name="navigation" value="">
	<input type="hidden" name="userId" value="<c:out value="${user.userId}" />">

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
						<a href="user?navigation=search&searchtext=<c:out value="${searchtext}"/>&searchby=<c:out value="${searchby}" />&index=<c:out value="${index}"/>" class="tabDetailViewDFLink" style="font-weight: normal;">Return to List&nbsp;</a>					
					</td>
				</tr>
			</tbody>
		</table>
	  </td>
    </tr>
	
	
		
	
			
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
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${user.status.status}"/></td>
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
    <tr>
      <td colspan="20"  style="padding: 0px;">
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tbody>
				<tr>
					<td style="text-align: left;" class="tabDetailViewDL">&nbsp;

					</td>
					<td class="tabDetailViewDL" align="right" nowrap="nowrap">
						<a href="user?navigation=search&searchtext=<c:out value="${searchtext}"/>&searchby=<c:out value="${searchby}" />&index=<c:out value="${index}"/>" class="tabDetailViewDFLink" style="font-weight: normal;">Return to List&nbsp;</a>					
					</td>
				</tr>
			</tbody>
		</table>
	  </td>
    </tr>				
	</tbody>
</table>
	
</form>
