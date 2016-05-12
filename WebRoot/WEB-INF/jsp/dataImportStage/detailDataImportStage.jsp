<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Detail Data Import Session</h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>

<ul id="maintab" class="shadetabs">

    <li class="selected">
        <a href="dataimportstage?navigation=detail&id=<c:out value="${dataImportStage.id}" />" rel="tcontent1">Detail Session</a>
    </li>    
</ul>
<br />

<form action="dataimportstage" method="GET" name="form1" id="form_layout">

    <table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tbody>
            <tr> 
                <td style="padding-bottom: 2px;">        

	              	<input title="Update [Alt+Shift+E]" accesskey="E" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:markPrint()" name="Edit" value=" Mark Print " type="button">
	              	
	              	<input title="Update [Alt+Shift+E]" accesskey="E" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:downloadPrintCard()" name="Edit" value=" Download Card Print File " type="button">      
	              	<c:if test="${dataImportStage.status eq 0}">
	              		<input title="Process File [Alt+Shift+P]" accesskey="P" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:processFile()" name="Edit" value=" Process File " type="button">
	              	</c:if>

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
                                    <a href="dataimportstage?navigation=search&searchtext=<c:out value="${searchtext}"/>&searchby=<c:out value="${searchby}" />&index=<c:out value="${index}"/>" class="tabDetailViewDFLink" style="font-weight: normal;">
										<img src="images/back-arrow.png" title="Return to List"/>
									</a>				
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </td>
            </tr>
        <input type="hidden" name="navigation" value="">
        <input type="hidden" name="id" value="<c:out value="${dataImportStage.id}" />">

        <tr>
            <td class="tabDetailViewDL" valign="top" width="17%">Import Number :&nbsp;</td>
        	<td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${dataImportStage.importNumber}"/></td>
        	<td class="tabDetailViewDL" valign="top" width="17%">Import Date :&nbsp;</td>
        	<td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${dataImportStage.importDate}"/></td>
        </tr>

        <tr>
            <td class="tabDetailViewDL" valign="top" width="17%">Raw File :&nbsp;</td>
        	<td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${dataImportStage.importRawFile}"/></td>
        	<td class="tabDetailViewDL" valign="top" width="17%">Status :&nbsp;</td>
        	<td class="tabDetailViewDF" valign="top" width="33%">
       		<c:if test="${dataImportStage.status eq 0}">UPLOADED</c:if>
       		<c:if test="${dataImportStage.status eq 1}">EXTRACTED</c:if>
       		<c:if test="${dataImportStage.status eq 2}">MIGRATED</c:if></td>
        </tr>
        <tr>
            <td class="tabDetailViewDL" valign="top" width="17%">Ready File :&nbsp;</td>
	        <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${dataImportStage.importReadyFile}"/></td>
	        <td class="tabDetailViewDL" valign="top" width="17%">Received Date :&nbsp;</td>
        	<td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${dataImportStage.receivedDate}"/></td>
        </tr>
        <tr>
            <td class="tabDetailViewDL" valign="top" width="17%"></td>
	        <td class="tabDetailViewDF" valign="top" width="33%">&nbsp;</td>
	        <td class="tabDetailViewDL" valign="top" width="15%"></td>
	        <td class="tabDetailViewDF" valign="top" width="35%"></td>
        </tr>
        <tr>
           <td class="tabDetailViewDL" valign="top" width="17%">Created Time :&nbsp;</td>
	       <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${dataImportStage.createdTime}"/> - </td>
	       <td class="tabDetailViewDL" valign="top" width="17%">Modified Time :&nbsp;</td>
	       <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${dataImportStage.modifiedTime}"/> - <c:out value="${dataImportStage.modifiedBy}"/></td>
        </tr>
        <tr>
            <td class="tabDetailViewDL" valign="top" width="17%">Description :&nbsp;</td>
        	<td class="tabDetailViewDF" valign="top" width="33%" colspan="3"><c:out value="${dataImportStage.description}"/></td>
        </tr>
        </tbody>
    </table>



</form>
<script language="javascript">
    function markPrint (){
        var delConfirm = window.confirm ("Are You Sure Want To Mark Print This File ?");
        if (delConfirm){
            document.form1.navigation.value = "markprint";
            document.form1.submit();
        }
    }
    function processFile (){
        var delConfirm = window.confirm ("Are You Sure Want To Process This File ?");
        if (delConfirm){
            document.form1.navigation.value = "processfile";
            document.form1.submit();
        }
    }
    function downloadPrintCard (){
        document.form1.navigation.value = "downloadprintcard";
        document.form1.action = "memberimport";
        document.form1.submit();
    }

</script>
