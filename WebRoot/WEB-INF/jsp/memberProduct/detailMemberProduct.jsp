<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>



<form action="memberproduct" method="GET" name="form1" id="form_layout">

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr> 
      <td style="padding-bottom: 2px;">        
        <input title="Edit [Alt+Shift+E]" accesskey="E" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:ubah()" name="Edit" value=" Update " type="button">
                		
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
						<a href="memberproduct?navigation=search&searchtext=<c:out value="${searchtext}"/>&searchby=<c:out value="${searchby}" />&rowset=<c:out value="${rowser}" />&index=<c:out value="${index}"/>" class="tabDetailViewDFLink" style="font-weight: normal;">Return to List&nbsp;</a>					
					</td>
				</tr>
			</tbody>
		</table>
	  </td>
    </tr>
	<input type="hidden" name="navigation" value="">
	<input type="hidden" name="memberProductId" value="<c:out value="${memberProduct.memberProductId}" />">
	
	
		
	
		
	
		
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Register Date :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${memberProduct.registerDate}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
			
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Expire Date :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${memberProduct.expireDate}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
			
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Renewal Date :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${memberProduct.renewalDate}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
			
	
		
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Created Time :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${memberProduct.createdTime}"/> - <c:out value="${memberProduct.createdBy}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
			
	
			
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Modified Time :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${memberProduct.modifiedTime}"/> - <c:out value="${memberProduct.modifiedBy}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
			
	
			
	
	
			
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Is Product Active :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${memberProduct.isProductActive}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
				
	</tbody>
</table>

	<h2>Foreign Affair</h2>

	
		<label for="Status Id">Status Id</label>
			<div id="ffbox">
			<c:out value="${memberProduct.memberProductStatus.statusId}" />
			</div>
	
		<label for="Member Id">Member Id</label>
			<div id="ffbox">
			<c:out value="${memberProduct.memberId.memberId}" />
			</div>
	
		<label for="Product Id">Product Id</label>
			<div id="ffbox">
			<c:out value="${memberProduct.productId.productId}" />
			</div>
		<center>
		<input type="button" class="btn_back" name="balik" value="Back" onclick="javascript:kembali()">
		<input type="button" class="btn" name="update" value="Update" onclick="javascript:ubah()">
		<input type="button" class="btn" name="delete" value="Delete" onclick="javascript:hapus()">
	</center>
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
		document.form1.action = "memberproduct-form";
		document.form1.method = "GET";
		document.form1.submit();
	}
</script>
