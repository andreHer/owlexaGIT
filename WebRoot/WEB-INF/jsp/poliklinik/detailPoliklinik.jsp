<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<!-- Search Container Start -->
<ul id="maintab" class="shadetabs">
<li class="selected"><a href="poliklinik?navigation=detail&poliklinikId=<c:out value="${poliklinik.poliklinikId}" />" rel="tcontent1">Detail Poliklinik </a></li>
<li  ><a href="diagnosis?navigation=listpoliklinik&poliklinikId=<c:out value="${poliklinik.poliklinikId}" />" rel="tcontent2">Diagnosis List </a></li>

</ul>
<br />
<form action="poliklinik" method="GET" name="form1" id="form_layout">
	<input type="hidden" name="navigation" value="">
	<input type="hidden" name="poliklinikId" value="<c:out value="${poliklinik.poliklinikId}" />">

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
      <td colspan="20" class="listViewPaginationTdS1" style="padding: 0px;">
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tbody>
				<tr>
					<td style="text-align: left;" class="tabDetailViewDL">&nbsp;
			
					</td>
					<td class="tabDetailViewDL" align="right" nowrap="nowrap">
						<a href="poliklinik?navigation=search&searchtext=<c:out value="${searchtext}"/>&searchby=<c:out value="${searchby}" />&index=<c:out value="${index}"/>" class="tabDetailViewDFLink" style="font-weight: normal;">Return to List&nbsp;</a>					
					</td>
				</tr>
			</tbody>
		</table>
	  </td>
    </tr>
	
	
		
		<tr>
	      	<td class="tabDetailViewDL" valign="top" width="17%">Poliklinik Name :&nbsp;</td>
	      	<td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${poliklinik.poliklinikName}"/></td>
	      	<td class="tabDetailViewDL" valign="top" width="17%">Poliklinik Code :&nbsp;</td>
	      	<td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${poliklinik.poliklinikCode}"/></td>
	    </tr>
			
	
		<tr>
	      	<td class="tabDetailViewDL" valign="top" width="17%">Parent Id :&nbsp;</td>		  
			<td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${poliklinik.parentId}"/></td>
	      	<td class="tabDetailViewDL" valign="top" width="15%"></td>
	      	<td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
			
			<tr>
	      		<td class="tabDetailViewDL" valign="top" width="17%">Created Time :&nbsp;</td>		  
			  	<td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${poliklinik.createdTime}"/> - <c:out value="${poliklinik.createdBy}"/></td>
	      		<td class="tabDetailViewDL" valign="top" width="17%">Modified Time :&nbsp;</td>		  
			  	<td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${poliklinik.modifiedTime}"/> - <c:out value="${poliklinik.modifiedBy}"/></td>
	    	</tr>
						
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Description :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${poliklinik.description}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
	
					
	
				
	
	 <tr>
      <td colspan="20" class="listViewPaginationTdS1" style="padding: 0px;">
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tbody>
				<tr>
					<td style="text-align: left;" class="tabDetailViewDL"></td>
					<td class="tabDetailViewDL" align="right" nowrap="nowrap">
						<a href="poliklinik?navigation=search&searchtext=<c:out value="${searchtext}"/>&searchby=<c:out value="${searchby}" />&index=<c:out value="${index}"/>" class="tabDetailViewDFLink" style="font-weight: normal;">Return to List&nbsp;</a>					
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
		document.form1.action = "poliklinik-form";
		document.form1.method = "GET";
		document.form1.submit();
	}
</script>
