<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<h1>Group Claim Util Report</h1>

<form action="groupclaimutilreport" method="GET" name="form1" id="form_layout">

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
						<a href="groupclaimutilreport?navigation=search&searchtext=<c:out value="${searchtext}"/>&searchby=<c:out value="${searchby}" />&index=<c:out value="${index}"/>" class="tabDetailViewDFLink" style="font-weight: normal;">Return to List&nbsp;</a>					
					</td>
				</tr>
			</tbody>
		</table>
	  </td>
    </tr>
	<input type="hidden" name="navigation" value="">
	<input type="hidden" name="id" value="<c:out value="${groupClaimUtilReport.id}" />">
	
	
		
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Report Date :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${groupClaimUtilReport.reportDate}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Group Name :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${groupClaimUtilReport.groupName}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
	
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Group Periode :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${groupClaimUtilReport.groupPeriode}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
	
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Claim Total :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${groupClaimUtilReport.claimTotal}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Claim Total Nominal :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${groupClaimUtilReport.claimTotalNominal}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Total Member :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${groupClaimUtilReport.totalMember}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Total Claim Ratio :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${groupClaimUtilReport.totalClaimRatio}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Total Inpatient :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${groupClaimUtilReport.totalInpatient}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Total Nominal Inpatient :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${groupClaimUtilReport.totalNominalInpatient}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Total Outpatient :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${groupClaimUtilReport.totalOutpatient}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Total Nominal Outpatient :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${groupClaimUtilReport.totalNominalOutpatient}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Total Dental :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${groupClaimUtilReport.totalDental}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Total Nominal Dental :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${groupClaimUtilReport.totalNominalDental}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Total Maternity :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${groupClaimUtilReport.totalMaternity}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Total Nominal Maternity :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${groupClaimUtilReport.totalNominalMaternity}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Total Optical :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${groupClaimUtilReport.totalOptical}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Total Nominal Optical :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${groupClaimUtilReport.totalNominalOptical}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Total Mcu :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${groupClaimUtilReport.totalMcu}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Total Nominal Mcu :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${groupClaimUtilReport.totalNominalMcu}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Inpatient Claim Ratio :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${groupClaimUtilReport.inpatientClaimRatio}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Outpatient Claim Ratio :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${groupClaimUtilReport.outpatientClaimRatio}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Dental Claim Ratio :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${groupClaimUtilReport.dentalClaimRatio}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Maternity Claim Ratio :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${groupClaimUtilReport.maternityClaimRatio}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Optical Claim Ratio :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${groupClaimUtilReport.opticalClaimRatio}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Mcu Claim Ratio :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${groupClaimUtilReport.mcuClaimRatio}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Group Id :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${groupClaimUtilReport.groupId}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Total Member Premium :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${groupClaimUtilReport.totalMemberPremium}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
				
	</tbody>
</table>

	<h2>Foreign Affair</h2>

		
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
		document.form1.action = "groupclaimutilreport-form";
		document.form1.method = "GET";
		document.form1.submit();
	}
</script>
