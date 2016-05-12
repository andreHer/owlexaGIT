<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Detail Procedure </h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>
<br />
<form action="providerprocedure" method="GET" name="form1" id="form_layout">

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr> 
      <td style="padding-bottom: 2px;">        
    	
		<input title="Edit [Alt+E]" accesskey="E" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:ubah()" name="Edit" value=" Update " type="button">
		
        <input title="Hapus [Alt+H]" accesskey="H" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus"  onClick="javascript:hapus()" name="Delete" value=" Delete " type="button">        		
		
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
		
					</td>
					<td class="tabDetailViewDL" align="right" nowrap="nowrap">
						<a href="providerprocedure?navigation=search&searchtext=<c:out value="${searchtext}"/>&searchby=<c:out value="${searchby}" />&index=<c:out value="${index}"/>&providerId=<c:out value="${providerProcedure.providerId.providerId}" />" class="tabDetailViewDFLink" style="font-weight: normal;">Return to List&nbsp;</a>					
					</td>
				</tr>
			</tbody>
		</table>
	  </td>
    </tr>
	<input type="hidden" name="navigation" value="">
	<input type="hidden" name="providerProcedureId" value="<c:out value="${providerProcedure.providerProcedureId}" />">			
		<tr>
	      	<td class="tabDetailViewDL" valign="top" width="17%">Provider Name :&nbsp;</td>		  
			<td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${providerProcedure.providerId.providerName}"/></td>
	      	<td class="tabDetailViewDL" valign="top" width="17%">Procedure Name :&nbsp;</td>		  
			<td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${providerProcedure.procedureId.procedureName}"/></td>
	    </tr>
			
			
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Average Length Of Stay :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${providerProcedure.averageLengthOfStay}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Rawat Jalan :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><fmt:formatNumber><c:out value="${providerProcedure.rj}"/></fmt:formatNumber></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Kelas I :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><fmt:formatNumber><c:out value="${providerProcedure.c1}"/></fmt:formatNumber></td>
	    </tr>
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Super VIP :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><fmt:formatNumber><c:out value="${providerProcedure.svip}"/></fmt:formatNumber></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Kelas II :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><fmt:formatNumber><c:out value="${providerProcedure.c2}"/></fmt:formatNumber></td>
	    </tr>
			
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">VIP :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><fmt:formatNumber><c:out value="${providerProcedure.vip}"/></fmt:formatNumber></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Kelas III :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><fmt:formatNumber><c:out value="${providerProcedure.c3}"/></fmt:formatNumber></td>
	    </tr>
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>	
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Modified Time :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${providerProcedure.modifiedTime}"/> - <c:out value="${providerProcedure.modifiedBy}"/></td>
	            <td class="tabDetailViewDL" valign="top" width="17%">Created Time :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${providerProcedure.createdTime}"/> - <c:out value="${providerProcedure.createdBy}"/></td>
	    </tr>
			
	
				
	
	 <tr>
      <td colspan="20" class="listViewPaginationTdS1" style="padding: 0px;">
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tbody>
				<tr>
					<td style="text-align: left;" class="tabDetailViewDL">&nbsp;

					</td>
					<td class="tabDetailViewDL" align="right" nowrap="nowrap">
						<a href="providerprocedure?navigation=search&searchtext=<c:out value="${searchtext}"/>&searchby=<c:out value="${searchby}" />&index=<c:out value="${index}"/>&providerId=<c:out value="${providerProcedure.providerId.providerId}" />" class="tabDetailViewDFLink" style="font-weight: normal;">Return to List&nbsp;</a>					
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
		document.form1.action = "providerprocedure-form";
		document.form1.method = "GET";
		document.form1.submit();
	}
</script>
