<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Detail Medicine Procedure</h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>

<form action="procedure" method="GET" name="form1" id="form_layout">
<input type="hidden" name="navigation" value="">
	<input type="hidden" name="prodecureId" value="<c:out value="${procedure.procedureId}" />">
	
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr> 
      <td style="padding-bottom: 2px;">       
        <c:if test="${theUser.roleId.roleId ne 6}">
	        <input title="Edit [Alt+Shift+E]" accesskey="E" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:ubah()" name="Edit" value=" Update " type="button">
	        <input title="Hapus [Alt+Shift+H]" accesskey="H" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:hapus()" name="Delete" value=" Delete " type="button">        		
		</c:if>
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
						<a href="procedure" class="tabDetailViewDFLink" style="font-weight: normal;">
							<img src="images/back-arrow.png" title="Return to List"/>
						</a>			
					</td>
				</tr>
			</tbody>
		</table>
	  </td>
    </tr>
	
			
		<tr>
	      	<td class="tabDetailViewDL" valign="top" width="17%">Procedure Name :&nbsp;</td>
	      	<td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${procedure.procedureName}"/></td>
	      	<td class="tabDetailViewDL" valign="top" width="17%">Procedure Code :&nbsp;</td>
	      	<td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${procedure.procedureCode}"/></td>
	    </tr>		
			
		<tr>
			<td class="tabDetailViewDL" valign="top" width="17%">Created Time :&nbsp;</td>
	    	<td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${procedure.createdTime}"/> - <c:out value="${procedure.createdBy}"/></td>
	      	<td class="tabDetailViewDL" valign="top" width="17%">Modified Time :&nbsp;</td>
	      	<td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${procedure.modifiedTime}"/> - <c:out value="${procedure.modifiedBy}"/></td>
	      
	    </tr>
		<tr>
	      	<td class="tabDetailViewDL" valign="top" width="17%">Description :&nbsp;</td>
	      	<td class="tabDetailViewDF" valign="top" width="33%" colspan=3><c:out value="${procedure.description}"/></td>
	    </tr>	
	
			
				
	
	 <tr>
      <td colspan="20" class="listViewPaginationTdS1" style="padding: 0px;">
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tbody>
				<tr>
					<td style="text-align: left;" class="tabDetailViewDL">&nbsp;</td>
					<td class="tabDetailViewDL" align="right" nowrap="nowrap">
											
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
		document.form1.action = "procedure-form";
		document.form1.method = "GET";
		document.form1.submit();
	}
</script>
