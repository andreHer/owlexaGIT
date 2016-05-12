<ul id="maintab" class="shadetabs">

	<li class="selected">
		<a href="providerset?navigation=detail&providerSetId=<c:out value="${providerSet.providerSetId}" />" rel="tcontent1">Detail Provider Profile</a>
	</li>
	<li>
		<a href="providersetmapping?providerSetId=<c:out value="${providerSet.providerSetId}" />" rel="tcontent2">Provider List</a>
	</li>
		
</ul>
<br />
<table class="tabDetailView" border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td colspan="20" class="listViewPaginationTdS1" style="padding: 0px;">
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tbody>
				<tr>
					<td style="text-align: left;" class="tabDetailViewDL">&nbsp;
	
					</td>
					<td class="tabDetailViewDL" align="right" nowrap="nowrap">
						<a href="providerset?navigation=search&searchtext=<c:out value="${searchtext}"/>&searchby=<c:out value="${searchby}" />&index=<c:out value="${index}"/>" class="tabDetailViewDFLink" style="font-weight: normal;">
							<img src="images/back-arrow.png" title="Return to List"/>
						</a>					
					</td>
				</tr>
			</tbody>
		</table>
	  </td>
    </tr>
		
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Provider Set Name :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${providerSet.providerSetName}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">Provider Set Code :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${providerSet.providerSetCode}"/></td>
	    </tr>
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Status :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${providerSet.status}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
			
			<tr>
	      		<td class="tabDetailViewDL" valign="top" width="17%">Client :&nbsp;</td>		  
			  	<td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${providerSet.clientId.clientName}"/></td>
	      		<td class="tabDetailViewDL" valign="top" width="17%">Member Group :&nbsp;</td>		  
			  	<td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${providerSet.memberGroupId.groupName}"/></td>
	    </tr>
			
		<tr>
	      	<td class="tabDetailViewDL" valign="top" width="17%">Created Time :&nbsp;</td>		  
			<td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${providerSet.createdTime}"/> - <c:out value="${providerSet.createdBy}"/></td>
		    <td class="tabDetailViewDL" valign="top" width="17%">Modified Time :&nbsp;</td>		  
			<td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${providerSet.modifiedTime}"/> - <c:out value="${providerSet.modifiedBy}"/></td>
	    </tr>
			
		<tr>
	      	<td class="tabDetailViewDL" valign="top" width="17%">Description :&nbsp;</td>
	      	<td class="tabDetailViewDF" valign="top" width="33%" colspan=3><c:out value="${providerSet.description}"/></td>
	      	
	    </tr>
	
	</tbody>
</table>