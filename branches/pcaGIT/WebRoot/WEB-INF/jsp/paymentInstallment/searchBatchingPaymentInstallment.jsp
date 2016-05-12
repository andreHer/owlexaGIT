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

<!-- Page Title Stop-->
<%
String alert = (String) request.getAttribute("alert");


if (alert != null && !alert.trim().equals("")) {%>
	<div id="warning" align="center">
		<c:out value="${alert}"></c:out>
	</div>
	<%}%>


<br />

<form name="form1" action="paymentinstallment" method="POST">
<input type="hidden" name="navigation" value="gosearchbatching">
<input type="hidden" name="arah" value="">
<input type="hidden" name="index" value="<c:out value="${index}" />">
<input type="hidden" name="paymentInstallmentId" value="<c:out value="${paymentInstallment.paymentInstallmentId}" />">
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Search Payment Installment</h3></td>
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

		<td class="dataLabel" nowrap="nowrap">Client Name:
	              &nbsp;&nbsp;
	              		<input name="clientName" id="clientName" class="dataField" value="<c:out value="${clientName}"/>" type="text" size=40>
						<input type="hidden" name="clientId" id="clientId" value="<c:out value="${clientId}" />" />
	              </td>
	            	<td class="dataLabel" nowrap="nowrap">			
					
	              </td>
	            <td class="dataLabel">
	              <input title="Search [Alt+Shift+Q]" accesskey="Q" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" name="button" value="Search" type="submit"> 
	              <input title="Search [Alt+Shift+Q]" accesskey="Q" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" name="button" value="Create Payment Batch" type="button" onclick="javascript:createPaymentBatch()">      
	              
	                          
				</td>
            </tr>
		
        </tbody>
      </table></td>
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
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%"></td>		   		   			
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Installment Number</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Payment Number</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Payment Date</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Total Claim</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Paid Value</td>
			<td scope="col" class="listViewThS1" nowrap="nowrap" width="25%">Description</td>
			
	</tr>


	<c:forEach items="${PaymentInstallments}" var="paymentInstallment" varStatus="status" >
	
	 <tr height="20">
      	<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${status.index+1}" /></td>
      	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top">
					   		         
			<input type="checkbox" name="paymentInstallmentList" value="<c:out value="${paymentInstallment.paymentInstallmentId}" />">
	  </td>
      	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<slot>
				<c:out value="${paymentInstallment.installmentNumber}" />
			</slot>
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<slot>
				<c:out value="${paymentInstallment.paymentId.paymentNumber}" />
			</slot>
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<slot>
				<c:out value="${paymentInstallment.paymentDate}" />
			</slot>
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<slot>
				<c:out value="${paymentInstallment.totalClaim}" />
			</slot>
		</td>			   		   		   		   		   	
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<slot>
				<fmt:formatNumber><c:out value="${paymentInstallment.totalValue}" /></fmt:formatNumber>
			</slot>
		</td>	
		<td class="oddListRowS1" bgcolor="#e7f0fe" align="left" valign="top">
			<c:out value="${paymentInstallment.description}" />
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
</form>
	
<!-- Table Content Stop -->
	<!-- Table Toolbar Start -->
	
		<!-- Table Toolbar Stop -->



<!-- Table Container Stop -->
<script language="Javascript">

   $("#clientName").autocomplete("client?navigation=lookupjson", {
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
        $("#clientId").val(value.id);
    });
<%
String nav="";
if(request.getAttribute("navigation").equals("gosearch")||request.getAttribute("navigation").equals("golookup")){
	nav = (String)request.getAttribute("navigation");
}
%>
function tambahi (){
	document.form1.navigation.value = "tambah";
	document.form1.action = "paymentinstallment-form";
	document.form1.method = "GET";
	document.form1.submit();
}
function hapus (idx){
	var delConfirm = window.confirm ("Are You Sure Want To Delete This Entry ?");

	if (delConfirm){
		document.form1.method = "POST";
		document.form1.paymentInstallmentId.value = idx;
		document.form1.action = "paymentinstallment";
		document.form1.navigation.value = "delete";
		document.form1.submit();
	}
}
function ubah (idx){
	document.form1.method = "GET";
	document.form1.paymentInstallmentId.value = idx;
	document.form1.action = "paymentinstallment-form";
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
	document.form1.navigation.value = "gosearchbatching";
	document.form1.action = "paymentinstallment";
	document.form1.method = "POST";
	document.form1.submit();
}
function detil (idx){
	document.form1.method = "POST";
	document.form1.paymentInstallmentId.value = idx;
	document.form1.action = "paymentinstallment";
	document.form1.navigation.value = "detail";
	document.form1.submit();

}

function createPaymentBatch(){
	document.form1.method = "GET";
	document.form1.navigation.value = "tambah";
	document.form1.action = "paymentbatch-form";
	document.form1.submit();
}
</script>
