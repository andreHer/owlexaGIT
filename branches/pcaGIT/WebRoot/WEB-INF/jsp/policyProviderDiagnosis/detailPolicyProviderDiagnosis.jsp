<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<h1>Policy Provider Diagnosis</h1>

<form action="policyproviderdiagnosis" method="GET" name="form1" id="form_layout">

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
						<a href="policyproviderdiagnosis?navigation=search&searchtext=<c:out value="${searchtext}"/>&searchby=<c:out value="${searchby}" />&index=<c:out value="${index}"/>" class="tabDetailViewDFLink" style="font-weight: normal;">Return to List&nbsp;</a>					
					</td>
				</tr>
			</tbody>
		</table>
	  </td>
    </tr>
	<input type="hidden" name="navigation" value="">
	<input type="hidden" name="policyProviderDiagnosisId" value="<c:out value="${policyProviderDiagnosis.policyProviderDiagnosisId}" />">
	
	
		
	
		
	
		
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%"><slot>Exclusion Id :&nbsp;</slot></td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><slot><c:out value="${policyProviderDiagnosis.exclusionId}"/></slot></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"><slot></slot></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><slot></slot></td>
	    </tr>
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%"><slot>Created Time :&nbsp;</slot></td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><slot><c:out value="${policyProviderDiagnosis.createdTime}"/></slot></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"><slot></slot></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><slot></slot></td>
	    </tr>
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%"><slot>Created By :&nbsp;</slot></td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><slot><c:out value="${policyProviderDiagnosis.createdBy}"/></slot></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"><slot></slot></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><slot></slot></td>
	    </tr>
	
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%"><slot>Modified Time :&nbsp;</slot></td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><slot><c:out value="${policyProviderDiagnosis.modifiedTime}"/></slot></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"><slot></slot></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><slot></slot></td>
	    </tr>
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%"><slot>Modified By :&nbsp;</slot></td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><slot><c:out value="${policyProviderDiagnosis.modifiedBy}"/></slot></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"><slot></slot></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><slot></slot></td>
	    </tr>
	
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%"><slot>Deleted Time :&nbsp;</slot></td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><slot><c:out value="${policyProviderDiagnosis.deletedTime}"/></slot></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"><slot></slot></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><slot></slot></td>
	    </tr>
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%"><slot>Deleted By :&nbsp;</slot></td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><slot><c:out value="${policyProviderDiagnosis.deletedBy}"/></slot></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"><slot></slot></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><slot></slot></td>
	    </tr>
	
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%"><slot>Deleted Status :&nbsp;</slot></td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><slot><c:out value="${policyProviderDiagnosis.deletedStatus}"/></slot></td>
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
						<a href="policyproviderdiagnosis?navigation=search&searchtext=<c:out value="${searchtext}"/>&searchby=<c:out value="${searchby}" />&index=<c:out value="${index}"/>" class="tabDetailViewDFLink" style="font-weight: normal;">Return to List&nbsp;</a>					
					</td>
				</tr>
			</tbody>
		</table>
	  </td>
    </tr>
	
	</tbody>
</table>

	<h2>Foreign Affair</h2>

	
		<label for="Diagnosis Id">Diagnosis Id</label>
			<div id="ffbox">
			<c:out value="${policyProviderDiagnosis.diagnosisId.diagnosisId}" />
			</div>
	
		<label for="Policy Provider Id">Policy Provider Id</label>
			<div id="ffbox">
			<c:out value="${policyProviderDiagnosis.policyProviderId.policyProviderId}" />
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
		document.form1.action = "policyproviderdiagnosis-form";
		document.form1.method = "GET";
		document.form1.submit();
	}
</script>
