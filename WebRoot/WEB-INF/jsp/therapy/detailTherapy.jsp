<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<ul id="maintab" class="shadetabs">

<li class="selected"><a href="therapy?navigation=detail&therapyId=<c:out value="${therapy.therapyId}" />" rel="tcontent1">Therapy </a></li>
<li><a href="therapy?navigation=listsub&therapyId=<c:out value="${therapy.therapyId}" />" rel="tcontent2">Sub Therapy </a></li>
<li><a href="medicine?navigation=listtherapy&therapyId=<c:out value="${therapy.therapyId}" />" rel="tcontent3">Medicine </a></li>

</ul>

<br />

<form action="therapy" method="GET" name="form1" id="form_layout">
	<input type="hidden" name="navigation" value="">
	<input type="hidden" name="therapyId" value="<c:out value="${therapy.therapyId}" />">

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
						<a href="therapy?navigation=search&searchtext=<c:out value="${searchtext}"/>&searchby=<c:out value="${searchby}" />&index=<c:out value="${index}"/>" class="tabDetailViewDFLink" style="font-weight: normal;">Return to List&nbsp;</a>					
					</td>
				</tr>
			</tbody>
		</table>
	  </td>
    </tr>
	
	
		
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Therapy Name :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${therapy.therapyName}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">Therapy Code :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${therapy.therapyCode}"/></td>
	    </tr>
	
			
	
			
			<tr>
	      		<td class="tabDetailViewDL" valign="top" width="17%">Class Index :&nbsp;</td>		  
			  	<td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${therapy.classIndex}"/></td>
	      		<td class="tabDetailViewDL" valign="top" width="17%">Coefficient :&nbsp;</td>		  
			  	<td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${therapy.coefficient}"/></td>
	    	</tr>
			
			<tr>
	      		<td class="tabDetailViewDL" valign="top" width="17%">Created Time :&nbsp;</td>		  
			  	<td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${therapy.createdTime}"/> - <c:out value="${therapy.createdBy}"/></td>
				<td class="tabDetailViewDL" valign="top" width="17%">Modified Time :&nbsp;</td>		  
			  	<td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${therapy.modifiedTime}"/> - <c:out value="${therapy.modifiedBy}"/></td>
	    	</tr>
					
	
			
			<tr>
	      		<td class="tabDetailViewDL" valign="top" width="17%">Description :&nbsp;</td>
		      	<td class="tabDetailViewDF" valign="top" width="33%" colspan=3><c:out value="${therapy.description}"/></td>
		      	
		    </tr>		
				
	<tr>
      <td colspan="20" class="listViewPaginationTdS1" style="padding: 0px;">
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tbody>
				<tr>
					<td style="text-align: left;" class="tabDetailViewDL">&nbsp;
				
					</td>
					<td class="tabDetailViewDL" align="right" nowrap="nowrap">
						<a href="therapy?navigation=search&searchtext=<c:out value="${searchtext}"/>&searchby=<c:out value="${searchby}" />&index=<c:out value="${index}"/>" class="tabDetailViewDFLink" style="font-weight: normal;">Return to List&nbsp;</a>					
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
		document.form1.action = "therapy-form";
		document.form1.method = "GET";
		document.form1.submit();
	}
</script>
