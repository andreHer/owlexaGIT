<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<h1>Provider Set Mapping</h1>

<form action="providersetmapping" method="GET" name="form1" id="form_layout">

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr> 
      <td style="padding-bottom: 2px;">        
        
		<input title="Edit [Alt+E]" accesskey="E" class="button" onClick="javascript:ubah()" name="Edit" value=" Update " type="button">
		
        <input title="Hapus [Alt+H]" accesskey="H" class="button" onClick="javascript:hapus()" name="Delete" value=" Delete " type="button">        		
		
		</td>
      <td align="right"></td>
    </tr>
  </tbody>
</table>
<table class="tabDetailView" border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td colspan="20" class="listViewPaginationTdS1" style="padding: 0px;">
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tbody>
				<tr>
					<td style="text-align: left;" class="tabDetailViewDL">&nbsp;
						<a href="#" onclick='' class="listViewPaginationLinkS1" style="font-weight: normal;">View Change Log</a>
					</td>
					<td class="tabDetailViewDL" align="right" nowrap="nowrap">
						<a href="providersetmapping?navigation=search&searchtext=<c:out value="${searchtext}"/>&searchby=<c:out value="${searchby}" />&index=<c:out value="${index}"/>" class="tabDetailViewDFLink" style="font-weight: normal;">Return to List&nbsp;</a>					
					</td>
				</tr>
			</tbody>
		</table>
	  </td>
    </tr>
	<input type="hidden" name="navigation" value="">
	<input type="hidden" name="providerSetMappingId" value="<c:out value="${providerSetMapping.providerSetMappingId}" />">
	
	
		
	
		
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Provider Id :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${providerSetMapping.providerId}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Provider Name :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${providerSetMapping.providerName}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
	
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Provider Code :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${providerSetMapping.providerCode}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
	
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">City :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${providerSetMapping.city}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
	
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Province :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${providerSetMapping.province}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
	
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Address :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${providerSetMapping.address}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
	
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Countery :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${providerSetMapping.countery}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
	
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Status :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${providerSetMapping.status}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Created Time :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${providerSetMapping.createdTime}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Created By :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${providerSetMapping.createdBy}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
	
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Modified Time :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${providerSetMapping.modifiedTime}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Modified By :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${providerSetMapping.modifiedBy}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
	
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Deleted Time :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${providerSetMapping.deletedTime}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Deleted By :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${providerSetMapping.deletedBy}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
	
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Deleted Status :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${providerSetMapping.deletedStatus}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
				
	
	 <tr>
      <td colspan="20" class="listViewPaginationTdS1" style="padding: 0px;">
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tbody>
				<tr>
					<td style="text-align: left;" class="tabDetailViewDL">&nbsp;
						<a href="#" onclick='' class="listViewPaginationLinkS1" style="font-weight: normal;">View Change Log</a>
					</td>
					<td class="tabDetailViewDL" align="right" nowrap="nowrap">
						<a href="providersetmapping?navigation=search&searchtext=<c:out value="${searchtext}"/>&searchby=<c:out value="${searchby}" />&index=<c:out value="${index}"/>" class="tabDetailViewDFLink" style="font-weight: normal;">Return to List&nbsp;</a>					
					</td>
				</tr>
			</tbody>
		</table>
	  </td>
    </tr>
	
	</tbody>
</table>

	<h2>Foreign Affair</h2>

	
		<label for="Provider Set Id">Provider Set Id</label>
			<div id="ffbox">
			<c:out value="${providerSetMapping.providerSetId.providerSetId}" />
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
		document.form1.action = "providersetmapping-form";
		document.form1.method = "GET";
		document.form1.submit();
	}
</script>
