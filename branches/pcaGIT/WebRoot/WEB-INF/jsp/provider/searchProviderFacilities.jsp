<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<link rel="stylesheet" type="text/css" href="css/jquery/autocomplete/jquery.autocomplete.css"/>

<script type="text/javascript" src="scripts/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="scripts/jquery/plugin/jquery.autocomplete.pack.js"></script>


<%
String navigation = WebUtil.getAttributeString(request,"navigation","");
%>

<!-- Page Title Start // Should be put on <Title> tag-->

<!-- Page Title Stop-->
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

if (alert != null && !alert.trim().equals("")) {%>
	<div id="warning" align="center">
		<c:out value="${alert}"></c:out>
	</div>
	<%}%>

<%
String rowclass = "";
int i=0;

WebUtil.getAttributeInteger(request,"index",0).intValue();
%>
<!-- Search Container Start -->

<form name="form1" action="provider" method="POST">
<input type="hidden" name="navigation" value="gosearch">
<input type="hidden" name="sortcolumn" value="<c:out value="${sortcolumn}" />">
<input type="hidden" name="sortorder" value="<c:out value="${sortorder}" />">
<input type="hidden" name="columntosort" value="<c:out value="${columntosort}" />">
<input type="hidden" name="arah" value="">
<input type="hidden" name="index" value="<c:out value="${index}" />">
				
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Provider Facilities</h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>
<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td><table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tbody>
          <tr>
            <form>
            </form>
            <td class="dataLabel" nowrap="nowrap">Select Provider :
              &nbsp;&nbsp;
              
				
              </td>
            	<td class="dataLabel" nowrap="nowrap">
					<input size="50" name="providerName" id="providerName" class="dataField" value="<c:out value="${providerName}"/>" type="text">
					<input type="hidden" name="providerId" id="providerId" value="<c:out value="${providerId}" />" />
              </td>
			
            <td class="dataLabel">
              	<input title="Search [Alt+Shift+Q]" accesskey="Q" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" name="button" onclick="javascript:cari();" value="Search" type="button">
              
               	<input title="Download Report [Alt+Shift+D]" accesskey="D" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" name="button" onclick="javascript:download();" value=" Download " type="button">
                          
			</td>
            </tr>
          </tbody>
      </table></td>
    </tr>
  </tbody>
</table>

<br />
<c:if test="${provider ne null}">
<table class="tabDetailView" border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td colspan="20"  style="padding: 0px;">
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tbody>
				<tr>
					<td style="text-align: left;" class="tabDetailViewDL">&nbsp;
					</td>
				</tr>
			</tbody>
		</table>
	  </td>
    </tr>
	
		
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="15%">Provider Name :&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${provider.providerName}"/> (<b><c:out value="${provider.statusId.status}" /></b>)</td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Telephone :&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${provider.telephone}"/> - <c:out value="${provider.contactPerson}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Contract Number :&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${provider.contractNumber}"/></td>
	      
	    </tr>
		<tr>
		<td class="tabDetailViewDL" valign="top" width="15%">Provider Category :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${provider.providerCategoryId.providerCategoryName}"/></td>
	      
	      <td class="tabDetailViewDL" valign="top" width="15%">Faximile :&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${provider.faximile}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Contract Start Date :&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${provider.contractStartDate}"/></td>
	      
	    </tr>
	    <tr>
	      <td class="tabDetailViewDL" valign="top" width="15%">Group Name :&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:if test="${provider.providerGroupId eq null}">NONE</c:if> <c:if test="${provider.providerGroupId ne null}"><c:out value="${provider.providerGroupId.providerName}"/></c:if></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Email :&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${provider.email}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Contract End Date :&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${provider.contractEndDate}"/></td>
	      
	    </tr>
		<tr>
			<td class="tabDetailViewDL" valign="top" width="15%">Specialization :&nbsp;</td>
	      	<td class="tabDetailViewDF" valign="top" width="20%"><c:if test="${provider.providerSpecId eq null}">NONE</c:if><c:if test="${provider.providerSpecId ne null}"><c:out value="${provider.providerSpecId.poliklinikName}" /></c:if></td>
	      
	       	<td class="tabDetailViewDL" valign="top" width="15%">Website :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${provider.website}"/></td>
		  <td class="tabDetailViewDL" valign="top" width="15%">Contract Renewal Type :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:if test="${provider.renewalType eq 1}">AUTO</c:if><c:if test="${provider.renewalType eq 0}">NEW CONTRACT</c:if></td>
	    </tr>
	    <tr>
			<td class="tabDetailViewDL" valign="top" width="15%">Provider Code :&nbsp;</td>
	      	<td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${provider.providerCode}" /></td>
	      
	       	<td class="tabDetailViewDL" valign="top" width="15%">&nbsp;</td>
	      	<td class="tabDetailViewDF" valign="top" width="20%"></td>
		  	<td class="tabDetailViewDL" valign="top" width="15%"></td>
	      	<td class="tabDetailViewDF" valign="top" width="20%"></td>
	    </tr>
	

		<tr>
	      <td class="tabDetailViewDL" valign="top" width="15%">&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%"></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="20%"></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%"></td>
	    </tr>
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="15%">Bank Account :&nbsp;</td> 			 
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${provider.bankAccount}"/></td>
		  <td class="tabDetailViewDL" valign="top" width="15%">Country :&nbsp;</td>		 	
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${provider.country}"/></td>      
		  <td class="tabDetailViewDL" valign="top" width="15%">Provider EDC Code :&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${provider.edcCode}" /></td>
	    </tr>
	
			
	
			
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="15%">Account Name :&nbsp;</td>		 
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${provider.accountName}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Province :&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${provider.province}"/></td>
		  <td class="tabDetailViewDL" valign="top" width="15%">Discount :&nbsp;</td> 
	      <td class="tabDetailViewDF" valign="top" width="20%"><b><fmt:formatNumber><c:out value="${provider.discount}"/></fmt:formatNumber></b> %</td>
	    </tr>
	
					
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="15%">Bank :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${provider.bank}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">City :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${provider.city}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Payment Periode :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${provider.paymentPeriode}" /> hari</td>

	    </tr>
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="15%">Bank Branch :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${provider.bankBranch}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Confirmation :&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:if test="${provider.confirmation eq \'Y\'}" >Confirmation</c:if><c:if test="${provider.confirmation eq \'N\'}" >No Confirmation</c:if></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Capitation Fund :&nbsp;</td> 
	      <td class="tabDetailViewDF" valign="top" width="20%"><b><fmt:formatNumber><c:out value="${provider.currentCapitationFund}"/></fmt:formatNumber></b></td>
	    </tr>
	    <tr>
	      <td class="tabDetailViewDL" valign="top" width="15%">Payment Currency :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${provider.providerCurrencyId.currencyName}" /> </td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Created Time :&nbsp;</td>		  
		  <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${provider.createdTime}"/>  -  <c:out value="${provider.createdBy}"/></td>	      
	      <td class="tabDetailViewDL" valign="top" width="15%">Modified Time :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${provider.modifiedTime}"/> - <c:out value="${provider.modifiedBy}"/></td>
	    </tr>

		<tr>
	      <td class="tabDetailViewDL" valign="top" width="15%">&nbsp;</td> 
	      <td class="tabDetailViewDF" valign="top" width="20%"></td>
	      <td class="tabDetailViewDL" valign="top" width="15%"></td>
	      <td class="tabDetailViewDF" valign="top" width="20%"></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%"></td>
	    </tr>
		
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="15%">Address :&nbsp;</td>			 
	      <td class="tabDetailViewDF" valign="top" width="33%" colspan="5"><c:out value="${provider.address}"/></td>
	    </tr>					
	</tbody>
</table>
</c:if>
<br />
 	
<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	<tr>
      <td colspan="20" align="right"><table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tbody>
          <tr>
            <td class="listViewPaginationTdS1" align="left"></td>
            <td class="listViewPaginationTdS1" align="right" nowrap="nowrap">
            &nbsp;				
			</td>
          </tr>
        </tbody>
      </table></td>
    </tr>
	<tr height="20">
		<td width="2%" nowrap="nowrap" class="listViewThS1" scope="col">No.</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="20%">Poliklinik Name</td>		
   	   	<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">Poliklinik Code</td>
   	   	<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Location</td>
   	   	<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Total Room</td>
   	   	<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Total Doctor</td>
	</tr>


	<c:forEach items="${PoliklinikList}" var="providerPoliklinik" varStatus="status" >
	<%	if (i % 2 == 0) {
			rowclass = "col1";
		} else if (i % 2 != 0) {
			rowclass= "col2";
		}
		i++;
	%>
	 <tr height="20">
      	<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${status.index+1}" /></td>		
      		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="left" valign="top"><c:out value="${providerPoliklinik.poliklinikId.poliklinikName}" /></td>				   		   		
      		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="left" valign="top"><c:out value="${providerPoliklinik.poliklinikId.poliklinikCode}" /></td>
      		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="left" valign="top"><c:out value="${providerPoliklinik.location}" /></td>
      		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="left" valign="top"><c:out value="${providerPoliklinik.totalRoom}" /></td>
      		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="left" valign="top"><c:out value="${providerPoliklinik.totalDoctor}" /></td>

    </tr>
   
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	
	</c:forEach>
	
<tr>
            <td class="listViewPaginationTdS1" align="left"></td>
            <td class="listViewPaginationTdS1" align="right" nowrap="nowrap" colspan=20>
				&nbsp;
			</td>
          </tr>
	</tbody>
</table>

<br />


<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	<tr>
      <td colspan="20" align="right"><table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tbody>
          <tr>
            <td class="listViewPaginationTdS1" align="left"></td>
            <td class="listViewPaginationTdS1" align="right" nowrap="nowrap">
            &nbsp;				
			</td>
          </tr>
        </tbody>
      </table></td>
    </tr>
	<tr height="20">
		<td width="2%" nowrap="nowrap" class="listViewThS1" scope="col">No.</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Doctor Name </td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Poliklinik</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Monday</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Tuesday</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Wednesday</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Thursday</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Friday</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Saturday</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Sunday</td>
	</tr>
	<% i = 0; %>

	<c:forEach items="${doctorList}" var="providerDoctor" varStatus="status" >
	<%	if (i % 2 == 0) {
			rowclass = "col1";
		} else if (i % 2 != 0) {
			rowclass= "col2";
		}
		i++;
	%>
	 <tr height="20">
      	<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${status.index+1}" /></td>
      	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${providerDoctor.doctorName}" /></td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${providerDoctor.providerPoliklinikId.poliklinikName}" /></td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top"><c:out value="${providerDoctor.monday}" /></td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top"><c:out value="${providerDoctor.tuesday}" /></td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top"><c:out value="${providerDoctor.wednesday}" /></td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top"><c:out value="${providerDoctor.thursday}" /></td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top"><c:out value="${providerDoctor.friday}" /></td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top"><c:out value="${providerDoctor.saturday}" /></td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" valign="top"><c:out value="${providerDoctor.sunday}" /></td>

    </tr>
   
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	
	</c:forEach>
	
<tr>
            <td class="listViewPaginationTdS1" align="left"></td>
            <td class="listViewPaginationTdS1" align="right" nowrap="nowrap" colspan=20>
				&nbsp;
			</td>
          </tr>
	</tbody>
</table>

<br />

<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	<tr>
      <td colspan="20" align="right"><table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tbody>
          <tr>
            <td class="listViewPaginationTdS1" align="left"></td>
            <td class="listViewPaginationTdS1" align="right" nowrap="nowrap">
            &nbsp;				
			</td>
          </tr>
        </tbody>
      </table></td>
    </tr>
	<tr height="20">
		<td width="1%" nowrap="nowrap" class="listViewThS1" scope="col">No.</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Item</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Item Code</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Super VIP </td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">VIP </td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Kelas I </td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Kelas II </td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Kelas III </td>
	</tr>

	<% i = 0; %>
	<c:forEach items="${ProviderItemList}" var="providerItem" varStatus="status" >
	<%	if (i % 2 == 0) {
			rowclass = "col1";
		} else if (i % 2 != 0) {
			rowclass= "col2";
		}
		i++;
	%>
	 <tr height="20">
    	<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${status.index+1}" /></td>
  
         <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="left" valign="top"><c:out value="${providerItem.itemId.itemName}" /></td>
         
         <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="left" valign="top"><c:out value="${providerItem.itemId.itemCode}" /></td>
         <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="right" valign="top">
         	<fmt:formatNumber><c:out value="${providerItem.svip}" /></fmt:formatNumber>
         </td>
         <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="right" valign="top">
         	<fmt:formatNumber><c:out value="${providerItem.vip}" /></fmt:formatNumber>
         </td>                           
         <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="right" valign="top">
         	<fmt:formatNumber><c:out value="${providerItem.kelas1}" /></fmt:formatNumber>
         </td>                           
         <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="right" valign="top">
         	<fmt:formatNumber><c:out value="${providerItem.kelas2}" /></fmt:formatNumber>
         </td>                           
         <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="right" valign="top">
         	<fmt:formatNumber><c:out value="${providerItem.kelas3}" /></fmt:formatNumber>
         </td>
    </tr>
   
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	
	</c:forEach>
	
<tr>
            <td class="listViewPaginationTdS1" align="left"></td>
            <td class="listViewPaginationTdS1" align="right" nowrap="nowrap" colspan=20>
				&nbsp;
			</td>
          </tr>
	</tbody>
</table>
</form>
	
<!-- Table Content Stop -->
	<!-- Table Toolbar Start -->
	
		<!-- Table Toolbar Stop -->



<!-- Table Container Stop -->
<script language="Javascript">
$(document).ready(function(){
    
    $("#providerName").autocomplete("provider?navigation=lookupjson", {
        max: 7,
        dataType: "json",
        parse: function(data) {
            return $.map(data, function(row) {
                return {
                    data: row,
                    value: row.name,
                    result: row.name
                }
            });
        },
        formatItem: function(row) {
            return "<font color='#000' >"+ row.name +"</font>" ;
        }
    }).bind("result", function(data, value){
        $(this).parents("dd").find(".error_message").hide();             
	    $("#providerId").val(value.id);
    });
 });

<%
String nav="";
if(request.getAttribute("navigation").equals("gosearch")||request.getAttribute("navigation").equals("golookup")){
	nav = (String)request.getAttribute("navigation");
}
%>
function tambahi (){
	document.form1.navigation.value = "tambah";
	document.form1.action = "provider-form";
	document.form1.method = "GET";
	document.form1.submit();
}
function hapus (idx){
	var delConfirm = window.confirm ("Are You Sure Want To Delete This Entry ?");

	if (delConfirm){
		document.form1.method = "POST";
		document.form1.providerId.value = idx;
		document.form1.action = "provider";
		document.form1.navigation.value = "delete";
		document.form1.submit();
	}
}
function ubah (idx){
	document.form1.method = "GET";
	document.form1.providerId.value = idx;
	document.form1.action = "provider-form";
	document.form1.navigation.value = "edit";
	document.form1.submit();
}
function goleft(){
	document.form1.navigation.value = "<%=nav%>";
	document.form1.arah.value="kiri";
	document.form1.method = "POST";
	document.form1.submit();
}
function goleftbgt(){
	document.form1.navigation.value = "<%=nav%>";
	document.form1.arah.value="kiribgt";
	document.form1.method = "POST";
	document.form1.submit();
}
function goright(){
	document.form1.navigation.value = "<%=nav%>";
	document.form1.arah.value="kanan";
	document.form1.method = "POST";
	document.form1.submit();
}
function gorightbgt(){
	document.form1.navigation.value = "<%=nav%>";
	document.form1.arah.value="kananbgt";
	document.form1.method = "POST";
	document.form1.submit();
}
function go(){
	document.form1.navigation.value = "<%=nav%>";
	document.form1.submit();
}
function cari (){
	document.form1.navigation.value = "gosearchfacilities";
	document.form1.action = "provider";
	document.form1.method = "POST";
	document.form1.submit();
}
function download (){
	document.form1.navigation.value = "downloadfacilities";
	document.form1.action = "provider";
	document.form1.method = "POST";
	document.form1.submit();
}
function detil (idx){
	document.form1.method = "POST";
	document.form1.providerId.value = idx;
	document.form1.action = "provider";
	document.form1.navigation.value = "detail";
	document.form1.submit();

}
function carisort(param){
	document.form1.navigation.value = "gosearchbysort";
	document.form1.searchtext.value="<c:out value="${searchtext}" />";
	document.form1.searchby.value="<c:out value="${searchby}" />";
	document.form1.sortcolumn.value=param;
	document.form1.method = "POST";
	document.form1.submit();
}
</script>
