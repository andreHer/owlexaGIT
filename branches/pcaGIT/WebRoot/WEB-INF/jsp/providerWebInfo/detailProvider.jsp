<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>

<%
String alert = (String) request.getAttribute("alert");

int totalIndex = 0;
int count = 0;
int countSet = 0;

try {

	count = ((Integer) request.getAttribute ("count")).intValue();
	countSet = ((Integer) request.getAttribute ("countSet")).intValue();
	totalIndex = ((Integer) request.getAttribute ("halAkhir")).intValue();
	
	
	
}
catch (Exception e){
}
String rowclass = "";
int i=0;


%>


<ul id="maintab" class="shadetabs">

	<li class="selected">
		<a href="provider?navigation=detail&providerId=<c:out value="${provider.providerId}" />" rel="tcontent1">Detail Provider</a>
	</li>
	<li>
		<a href="batchclaim?navigation=list&providerId=<c:out value="${provider.providerId}" />" rel="tcontent2">Batch Claim</a>
	</li>
	<li>
		<a href="case?navigation=listprovider&providerId=<c:out value="${provider.providerId}" />" rel="tcontent3">Customer Case</a>
	</li>
	<li>
		<a href="provideritem?navigation=list&providerId=<c:out value="${provider.providerId}" />" rel="tcontent4">Room & Board </a>
	</li>	
        <li>
		<a href="contactperson?navigation=listprovider&providerId=<c:out value="${provider.providerId}" />" rel="tcontent5">Contact Person </a>                
	</li>	
        <li>
		<a href="bankaccount?navigation=listprovider&providerId=<c:out value="${provider.providerId}" />" rel="tcontent6">Bank Account</a>                
	</li>
   <li>
		<a href="edcterminal?navigation=listprovider&providerId=<c:out value="${provider.providerId}" />" rel="tcontent6">EDC Terminal</a>                
	</li>	
</ul>
<br />
<form action="provider" method="GET" name="form1" id="form_layout">
<input type="hidden" name="navigation" value="">
	<input type="hidden" name="providerId" value="<c:out value="${provider.providerId}" />">
	<input type="hidden" name="providerItemId" value="">
	<input type="hidden" name="caseId" value="">
	<input type="hidden" name="batchClaimId" value="">
	
	
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr> 
      <td style="padding-bottom: 2px; padding-top: 5px;">
      <c:if test="${theUser.roleId.roleId eq 0}">        
        <input title="Edit [Alt+Shift+E]" accesskey="E" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:ubah()" name="Edit" value=" Update " type="button">
        <input title="Delete [Alt+Shift+D]" accesskey="D" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:hapus()" name="Delete" value=" Delete " type="button">
        </c:if>        		
		  <c:if test="${provider.statusId.statusId eq 1}">	                 		
    	    <input title="Terminate [Alt+Shift+T]" accesskey="T" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:terminateProvider()" name="Terminate" value=" Terminate " type="button">        		        
    	  </c:if>
		  <c:if test="${provider.statusId.statusId eq 2}">	                 
        <input title="Renew [Alt+Shift+R]" accesskey="R" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:renewProvider()" name="Renew" value=" Renew " type="button">        		        
          </c:if>
		<c:if test="${(provider.latitude ne null )}">
			<input title="Show Map" accesskey="E" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:showMapLocation()" name="Map" value=" Show Map Location " type="button">
		</c:if>
		<c:if test="${(provider.edcCode ne null )}">
			<input title="Add Terminal" accesskey="E" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:addTerminal()" name="adTerm" value=" Add EDC Terminal " type="button">
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
						<a href="provider?navigation=search&searchtext=<c:out value="${searchtext}"/>&searchby=<c:out value="${searchby}" />&index=<c:out value="${index}"/>" class="tabDetailViewDFLink" style="font-weight: normal;">Return to List&nbsp;</a>					
					</td>
				</tr>
			</tbody>
		</table>
	  </td>
    </tr>
	
		
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Provider Name :&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${provider.providerName}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Provider Category :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><c:out value="${provider.providerCategoryId.providerCategoryName}"/></td>
	    </tr>
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Contract Number :&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${provider.contractNumber}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Contract Renewal Type :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><c:if test="${provider.renewalType eq 1}">AUTO</c:if><c:if test="${provider.renewalType eq 0}">NEW CONTRACT</c:if></td>
	    </tr>
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">&nbsp;</td> 			 
	      <td class="tabDetailViewDF" valign="top" width="33%"></td>
	       <td class="tabDetailViewDL" valign="top" width="17%">&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="33%"></td>
	    </tr>
	

		
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Bank Account :&nbsp;</td> 			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${provider.bankAccount}"/></td>
	       <td class="tabDetailViewDL" valign="top" width="17%">Contract End Date :&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${provider.contractEndDate}"/></td>
	    </tr>
	
			
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Account Name :&nbsp;</td>		 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${provider.accountName}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">Contract Start Date :&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${provider.contractStartDate}"/></td>
	    </tr>
	
					
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Bank :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${provider.bank}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">Contact Person :&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${provider.contactPerson}"/></td>

	    </tr>
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Bank Branch :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${provider.bankBranch}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">Confirmation :&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:if test="${provider.confirmation eq \'Y\'}" >Confirmation</c:if><c:if test="${provider.confirmation eq \'N\'}" >No Confirmation</c:if></td>

	    </tr>
			<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="33%"></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="33%"></td>

	    </tr>
		
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">City :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${provider.city}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">Telephone :&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${provider.telephone}"/></td>

	    </tr>
	
			
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Province :&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${provider.province}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">Faximile :&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${provider.faximile}"/></td>

	    </tr>
	
			
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Country :&nbsp;</td>		 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${provider.country}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="17%">Email :&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${provider.email}"/></td>
	    </tr>
	
			
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Postal Code :&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${provider.postalCode}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Website :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><c:out value="${provider.website}"/></td>
	    </tr>
	
			
	
			

	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">&nbsp;</td>
		  
			 
	      <td class="tabDetailViewDF" valign="top" width="33%"></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
	
			
	
		
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Created Time :&nbsp;</td>
		  
			  <td class="tabDetailViewDF" valign="top" width="33%"><c:out value="${provider.createdTime}"/>  -  <c:out value="${provider.createdBy}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Modified Time :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="35%"><c:out value="${provider.modifiedTime}"/> - <c:out value="${provider.modifiedBy}"/></td>
	    </tr>
			
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="33%"></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="35%"></td>
	    </tr>
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="17%">Address :&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="33%" colspan="3"><c:out value="${provider.address}"/></td>
	    </tr>
	
    <tr>
      <td colspan="20"  style="padding: 0px;">
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tbody>
				<tr>
					<td style="text-align: left;" class="tabDetailViewDL">&nbsp;

					</td>
					<td class="tabDetailViewDL" align="right" nowrap="nowrap">
						<a href="provider?navigation=search&searchtext=<c:out value="${searchtext}"/>&searchby=<c:out value="${searchby}" />&index=<c:out value="${index}"/>" class="tabDetailViewDFLink" style="font-weight: normal;">Return to List&nbsp;</a>					
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
		document.form1.action = "provider-form";
		document.form1.method = "GET";
		document.form1.submit();
	}
	function addTerminal (){
		document.form1.navigation.value = "update";
		document.form1.action = "edcterminal-form";
		document.form1.method = "GET";
		document.form1.submit();
	}
	function hapusRoom(id){
		var delConfirm = window.confirm ("Are You Sure Want To Delete This Entry ?");
		
		if (delConfirm){
			document.form1.action = "provideritem";
			document.form1.itemId.value = id;
			document.form1.navigation.value = "delete";
			document.form1.submit();
		}
	}
	function ubahRoom (id){
	
	}
	function detilRoom (id){
	
	}
	function showMapLocation (){
		window.open ("provider?navigation=showprovidermap&providerId=<c:out value="${provider.providerId}" />&url=claim-form","Search","width=800, height=600, menubar=no, status=no, toolbar=no, scrollbars=yes, resizable=yes,screenX=200,screenY=100");
	}
	function terminateProvider (){
		var delConfirm = window.confirm ("Are You Sure Want To Terminate This Provider ?");
		
		if(delConfirm){
			document.form1.navigation.value = "terminate";
			document.form1.action = "provider";
			document.form1.method = "GET";
			document.form1.submit();
		}
	}
	function renewProvider (){
		var delConfirm = window.confirm ("Are You Sure Want To Renew This Provider ?");
		
		if(delConfirm){
			document.form1.navigation.value = "renew";
			document.form1.action = "provider";
			document.form1.method = "GET";
			document.form1.submit();
		}
	}		
</script>
