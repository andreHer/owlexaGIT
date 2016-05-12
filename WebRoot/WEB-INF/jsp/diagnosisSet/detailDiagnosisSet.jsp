<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<form action="diagnosisset" method="GET" name="form1" id="form_layout">
<input type="hidden" name="navigation" value="">
	<input type="hidden" name="diagnosisSetId" value="<c:out value="${diagnosisSet.diagnosisSetId}" />">
	<input type="hidden" name="diagnosisSetDetailId" value="">
		
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr> 
      <td style="padding-bottom: 2px;">        
       <c:if test="${theUser.roleId.roleId ne 6}">
			<input title="Edit [Alt+E]" accesskey="E" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:ubah()" name="Edit" value=" Update " type="button">
	       	<input title="Hapus [Alt+H]" accesskey="H" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:hapus()" name="Delete" value=" Delete " type="button">        		
           <input title="Hapus [Alt+H]" accesskey="H" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:addDiagnosis()" name="addDiag" value=" Add Diagnosis " type="button">
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
						<a href="diagnosisset?navigation=search&searchtext=<c:out value="${searchtext}"/>&searchby=<c:out value="${searchby}" />&index=<c:out value="${index}"/>" class="tabDetailViewDFLink" style="font-weight: normal;">
							<img src="images/back-arrow.png" title="Return to List"/>
						</a>
					</td>
				</tr>
			</tbody>
		</table>
	  </td>
    </tr>
	
		<tr>
	      	<td class="tabDetailViewDL" valign="top" width="17%">Diagnosis Set Name :&nbsp;</td>
	      	<td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${diagnosisSet.diagnosisSetName}"/></td>      
	      	<td class="tabDetailViewDL" valign="top" width="17%">Diagnosis Set Code :&nbsp;</td>
	      	<td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${diagnosisSet.diagnosisSetCode}"/></td>
	    </tr>
		<tr>
	      	<td class="tabDetailViewDL" valign="top" width="17%">Client :&nbsp;</td>
	      	<td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${diagnosisSet.clientId.clientName}"/></td>
	      	<td class="tabDetailViewDL" valign="top" width="15%"></td>
	      	<td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>		
		<tr>
	      	<td class="tabDetailViewDL" valign="top" width="17%">Description :&nbsp;</td> 
	      	<td class="tabDetailViewDF" valign="top" width="33%" colspan="3"><c:out value="${diagnosisSet.description}"/></td>	      
	    </tr>
	
			
	
		
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Created Time :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${diagnosisSet.createdTime}"/> - <c:out value="${diagnosisSet.createdBy}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">Modified Time :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${diagnosisSet.modifiedTime}"/> - <c:out value="${diagnosisSet.modifiedBy}"/></td>
	    </tr>
	
	</tbody>
</table>
		
</form>
<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	<tr>
      <td colspan="20" align="right"><table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tbody>
          	<tr>
            	<td class="listViewPaginationTdS1" align="left"></td>
            	<td class="listViewPaginationTdS1" align="right" nowrap="nowrap">&nbsp;</td>
          	</tr>
        </tbody>
      </table></td>
    </tr>
	<tr height="20">
		<td width="2%" nowrap="nowrap" class="listViewThS1" scope="col">No.</td>		

<!-- ini default generated table from database -->
		   		   			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Diagnosis Code</td>
			
		
			   			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Diagnosis Name</td>
			
		
			   			
					<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Description</td>
			
		
			   		   		   			
		
		
			   		   			
		
		
			   		   			
		
		
			   		   		   	   <td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">&nbsp;
	   </td>
	</tr>


	<c:forEach items="${DiagnosisSetDetails}" var="diagnosisSetDetail" varStatus="status" >
	
	 <tr height="20">
      <td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${status.index+1}" />.</td>

      		   		   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${diagnosisSetDetail.diagnosisCode}" />
			
		</td>
					   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${diagnosisSetDetail.diagnosisName}" />
			
		</td>
					   				<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${diagnosisSetDetail.description}" />
			
		</td>
					   		   		   		
					   		   		
					   		   		
					   		   		         <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top">



			<!-- ini default delete table for each data -->
			<a href="javascript:hapusItem('<c:out value="${diagnosisSetDetail.diagnosisSetDetailId}" />')">
				<img src="images/delete.gif" class="action_icon" alt="Delete" title="Delete"></a>
		
	  </td>
    </tr>
   
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	
	</c:forEach>
	
<tr>
            <td class="listViewPaginationTdS1" align="left"></td>
            <td class="listViewPaginationTdS1" align="right" nowrap="nowrap" colspan="20">
				&nbsp;
			</td>
          </tr>
	</tbody>
	</table>
<script language="javascript">
	function hapus (){
	var delConfirm = window.confirm ("Are You Sure Want To Delete This Entry ?");
		if (delConfirm){
			document.form1.action = "diagnosisset";
			
			document.form1.navigation.value = "delete";
			document.form1.submit();
		}
	}
	function hapusItem (idx){
		var delConfirm = window.confirm ("Are You Sure Want To Delete This Entry ?");
			if (delConfirm){
				document.form1.action = "diagnosissetdetail";
				document.form1.diagnosisSetDetailId.value = idx;				
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
		document.form1.action = "diagnosisset-form";
		document.form1.method = "GET";
		document.form1.submit();
	}
	function addDiagnosis (){
		document.form1.navigation.value = "addbulk";
		document.form1.action = "diagnosissetdetail";
		document.form1.method = "GET";
		document.form1.submit();
	}
</script>
