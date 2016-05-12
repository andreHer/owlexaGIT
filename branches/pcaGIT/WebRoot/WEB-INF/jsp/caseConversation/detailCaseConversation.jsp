<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<form action="caseconversation" method="GET" name="form1" id="form_layout">

	<input type="hidden" name="navigation" value="">
	<input type="hidden" name="conversationId" value="<c:out value="${caseConversation.conversationId}" />">
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr> 
      <td style="padding-bottom: 2px;">        
        <input title="Edit [Alt+Shift+E]" accesskey="E" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:ubah()" name="Edit" value=" Update " type="button">
        <input title="Hapus [Alt+Shift+H]" accesskey="H" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:hapus()" name="Delete" value=" Delete " type="button">        		
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
		
					</td>
					<td class="tabDetailViewDL" align="right" nowrap="nowrap">
						<a href="caseconversation?navigation=list&searchtext=<c:out value="${searchtext}"/>&searchby=<c:out value="${searchby}" />&rowset=<c:out value="${rowset}" />&caseId=<c:out value="${caseConversation.caseId.caseId}" />&index=<c:out value="${index}"/>" class="tabDetailViewDFLink" style="font-weight: normal;">
							<img src="images/back-arrow.png" title="Return to List"/>
						</a>					
					</td>
				</tr>
			</tbody>
		</table>
	  </td>
    </tr>
	
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Case Number :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${caseConversation.caseId.caseNumber}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"></td>
	    </tr>
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Conversation Subject :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${caseConversation.conversationSubject}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">Conversation Category :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${caseConversation.conversationCategory.conversationCategoryName}"/></td>
	    </tr>
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Conversation Time :&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${caseConversation.conversationTime}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
		
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="33%"></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Created Time :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${caseConversation.createdTime}"/> - <c:out value="${caseConversation.createdBy}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">Modified Time :&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${caseConversation.modifiedTime}"/> - <c:out value="${caseConversation.modifiedBy}"/></td>

	    </tr>
			
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Conversation Description :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="33%" colspan="3"><c:out value="${caseConversation.conversationDescription}"/></td>
	    </tr>
	
	<tr>
      <td colspan="20"  style="padding: 0px;">
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tbody>
				<tr>
					<td style="text-align: left;" class="tabDetailViewDL">&nbsp;
		
					</td>
<!-- 					<td class="tabDetailViewDL" align="right" nowrap="nowrap"> -->
<!-- 						<a href="caseconversation?navigation=list&searchtext=<c:out value="${searchtext}"/>&searchby=<c:out value="${searchby}" />&rowset=<c:out value="${rowset}" />&caseId=<c:out value="${caseConversation.caseId.caseId}" />&index=<c:out value="${index}"/>" class="tabDetailViewDFLink" style="font-weight: normal;">Return to List&nbsp;</a>					 -->
<!-- 					</td> -->
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
		document.form1.action = "caseconversation-form";
		document.form1.method = "GET";
		document.form1.submit();
	}
</script>
