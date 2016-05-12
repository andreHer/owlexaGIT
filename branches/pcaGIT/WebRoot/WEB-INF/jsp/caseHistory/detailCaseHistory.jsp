<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<h1>Case History</h1>

<form action="casehistory" method="GET" name="form1" id="form_layout">

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
						<a href="casehistory?navigation=search&searchtext=<c:out value="${searchtext}"/>&searchby=<c:out value="${searchby}" />&index=<c:out value="${index}"/>" class="tabDetailViewDFLink" style="font-weight: normal;">Return to List&nbsp;</a>					
					</td>
				</tr>
			</tbody>
		</table>
	  </td>
    </tr>
	<input type="hidden" name="navigation" value="">
	<input type="hidden" name="caseHistoryId" value="<c:out value="${caseHistory.caseHistoryId}" />">
	
	
		
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%"><slot>Case Id :&nbsp;</slot></td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><slot><c:out value="${caseHistory.caseId}"/></slot></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"><slot></slot></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><slot></slot></td>
	    </tr>
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%"><slot>History Time :&nbsp;</slot></td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><slot><c:out value="${caseHistory.historyTime}"/></slot></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"><slot></slot></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><slot></slot></td>
	    </tr>
			
	
		
	
		
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%"><slot>Created Time :&nbsp;</slot></td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><slot><c:out value="${caseHistory.createdTime}"/></slot></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"><slot></slot></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><slot></slot></td>
	    </tr>
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%"><slot>Created By :&nbsp;</slot></td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><slot><c:out value="${caseHistory.createdBy}"/></slot></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"><slot></slot></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><slot></slot></td>
	    </tr>
	
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%"><slot>Modified Time :&nbsp;</slot></td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><slot><c:out value="${caseHistory.modifiedTime}"/></slot></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"><slot></slot></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><slot></slot></td>
	    </tr>
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%"><slot>Modified By :&nbsp;</slot></td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><slot><c:out value="${caseHistory.modifiedBy}"/></slot></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"><slot></slot></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><slot></slot></td>
	    </tr>
	
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%"><slot>Deleted Time :&nbsp;</slot></td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><slot><c:out value="${caseHistory.deletedTime}"/></slot></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"><slot></slot></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><slot></slot></td>
	    </tr>
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%"><slot>Deleted By :&nbsp;</slot></td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><slot><c:out value="${caseHistory.deletedBy}"/></slot></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"><slot></slot></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><slot></slot></td>
	    </tr>
	
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%"><slot>Deleted Status :&nbsp;</slot></td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><slot><c:out value="${caseHistory.deletedStatus}"/></slot></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"><slot></slot></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><slot></slot></td>
	    </tr>
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%"><slot>Before Action Data :&nbsp;</slot></td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><slot><c:out value="${caseHistory.beforeActionData}"/></slot></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"><slot></slot></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><slot></slot></td>
	    </tr>
	
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%"><slot>After Action Data :&nbsp;</slot></td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><slot><c:out value="${caseHistory.afterActionData}"/></slot></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"><slot></slot></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><slot></slot></td>
	    </tr>
	
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%"><slot>Action By :&nbsp;</slot></td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><slot><c:out value="${caseHistory.actionBy}"/></slot></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"><slot></slot></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><slot></slot></td>
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
						<a href="casehistory?navigation=search&searchtext=<c:out value="${searchtext}"/>&searchby=<c:out value="${searchby}" />&index=<c:out value="${index}"/>" class="tabDetailViewDFLink" style="font-weight: normal;">Return to List&nbsp;</a>					
					</td>
				</tr>
			</tbody>
		</table>
	  </td>
    </tr>
	
	</tbody>
</table>

	<h2>Foreign Affair</h2>

	
		<label for="Case Status Id">Case Status Id</label>
			<div id="ffbox">
			<c:out value="${caseHistory.afterStatus.caseStatusId}" />
			</div>
	
		<label for="Case Status Id">Case Status Id</label>
			<div id="ffbox">
			<c:out value="${caseHistory.beforeStatus.caseStatusId}" />
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
		document.form1.action = "casehistory-form";
		document.form1.method = "GET";
		document.form1.submit();
	}
</script>
