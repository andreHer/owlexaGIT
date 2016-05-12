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

<%
String alert = (String) request.getAttribute("alert");
int index = 0;
int totalIndex = 0;
int count = 0;
int countSet = 0;


try {
	index = ((Integer) request.getAttribute ("index")).intValue();
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
int indexint = Integer.parseInt(request.getAttribute("index").toString());
WebUtil.getAttributeInteger(request,"index",0).intValue();
%>
<!-- Search Container Start -->

<form name="form1" action="provider" method="POST">
<input type="hidden" name="navigation" value="gosearchexclusion">
<input type="hidden" name="sortcolumn" value="<c:out value="${sortcolumn}" />">
<input type="hidden" name="sortorder" value="<c:out value="${sortorder}" />">
<input type="hidden" name="columntosort" value="<c:out value="${columntosort}" />">
<input type="hidden" name="arah" value="">
<input type="hidden" name="index" value="<c:out value="${index}" />">
				<input type="hidden" name="providerId" value="<c:out value="${provider.providerId}" />">
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Search Provider</h3></td>
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
            <td class="dataLabel" nowrap="nowrap">Client :
              &nbsp;&nbsp;              
					<input size="30" name="clientName" id="clientName" class="dataField" value="<c:out value="${clientName}"/>" type="text">
					<input type="hidden" name="clientId" id="clientId" value="<c:out value="${clientId}" />" />
					              
			</td>
            <td class="dataLabel" nowrap="nowrap">Policy :
              &nbsp;&nbsp;
              		
              		<input size="30" name="policyNumber" id="policyNumber" class="dataField" value="<c:out value="${policyNumber}"/>" type="text">
					<input type="hidden" name="policyId" id="policyId" value="<c:out value="${policyId}" />" />
                
			
				
              </td>
              
			  <td class="dataLabel" nowrap="nowrap">
				 
              </td>
            <td class="dataLabel">
              <input title="Search [Alt+Shift+Q]" accesskey="Q" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" name="buttonSearch" value="Search" type="button" onclick="javascript:cari();"> 
              <input title="Download Report [Alt+Shift+R]" accesskey="R" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" name="buttonDownload" value="Download" type="button" onclick="javascript:download()">
                          
			</td>
            </tr>
            <tr>
            <td class="dataLabel" nowrap="nowrap">Group :
              &nbsp;&nbsp;              
					<input size="30" name="memberGroupName" id="memberGroupName" class="dataField" value="<c:out value="${memberGroupName}"/>" type="text">
					<input type="hidden" name="memberGroupId" id="memberGroupId" value="<c:out value="${memberGroupId}" />" />
              	</td>
            	<td class="dataLabel" nowrap="nowrap">
              	</td>
              
			  		<td class="dataLabel" nowrap="nowrap"></td>
            		<td class="dataLabel">
            			<input title="Clear Parameter" accesskey="P" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" name="btnClear" value="Clear Search Parameter" type="button" onclick="javascript:clearParam()">
            		</td>
            </tr>
            
			
        </tbody>
      </table></td>
    </tr>
  </tbody>
</table>


<br />
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
		<td width="2%" nowrap="nowrap" class="listViewThS1" scope="col"><img src="images/blank.gif" alt="asd" height="1" width="1">No.</td>		
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%"><a href="provider?navigation=search&sortby=provider_name&searchby=<c:out value="${searchby}" />" class="listViewThLinkS1">Provider Name &nbsp;<img src="images/arrow.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a></td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Provider Category &nbsp;</td>   			
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">City &nbsp;</td>	   			
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Province &nbsp;</td>	   			
		
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">IP &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">OP &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">MT &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">DE &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">OPT &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">LAB/MCU &nbsp;</td>
		
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">PPK1 &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">PPK2 &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">PPK3 &nbsp;</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">EDC  &nbsp;</td>
	</tr>
		


	<c:forEach items="${Providers}" var="provider" varStatus="status" >
	<%	if (i % 2 == 0) {
			rowclass = "col1";
		} else if (i % 2 != 0) {
			rowclass= "col2";
		}
		i++;
	%>
	 
      <tr height="20">
      	<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${status.index+1}" /></td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<a href="provider?navigation=detail&index=<c:out value="${index}" />&providerId=<c:out value="${provider.providerId}" />" class="linkDetail"><c:out value="${provider.providerName}" /></a>			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${provider.providerCategoryId.providerCategoryName}" />			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${provider.city}" />			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${provider.province}" />			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${provider.inpatient}" />			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${provider.outpatient}" />			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${provider.maternity}" />			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${provider.dental}" />			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${provider.optical}" />			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${provider.lab}" />			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${provider.ppk1}" />			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${provider.ppk2}" />			
		</td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:out value="${provider.ppk3}" />			
		</td>		   	
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">			
			<c:if test="${provider.edcCode eq null}">
				TIDAK
			</c:if>
			<c:if test="${provider.edcCode ne null}">
				YA
			</c:if>
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
    
    $("#memberGroupName").autocomplete("membergroup?navigation=lookupjson", {
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
            return "<font color='#000' >"+ row.number +" - </font>" +    "<font color='#000' >"+ row.name +"</font>" ;
        }
    }).bind("result", function(data, value){
        $(this).parents("dd").find(".error_message").hide();
        $("#memberGroupId").val(value.id);        
    });
    
     $("#policyNumber").autocomplete("policy?navigation=lookupjson", {
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
            return "<font color='#000' >"+ row.number +" - </font>" +    "<font color='#000' >"+ row.name +"</font>" ;
        }
    }).bind("result", function(data, value){
        $(this).parents("dd").find(".error_message").hide();
        $("#policyId").val(value.id);        
    });
    
<%
String nav="";
if(request.getAttribute("navigation").equals("gosearch")||request.getAttribute("navigation").equals("golookup")){
	nav = (String)request.getAttribute("navigation");
}
%>

function download (){
	document.form1.method = "GET";	
	document.form1.navigation.value = "downloadexclusion";
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
	document.form1.navigation.value = "gosearchexclusion";
	document.form1.action = "provider";
	document.form1.method = "POST";
	document.form1.submit();
}
function clearParam (){
	document.form1.policyId.value = "";
	document.form1.clientId.value = "";
	document.form1.memberGroupId.value = "";
	document.form1.policyNumber.value = "";
	document.form1.memberGroupName.value = "";
	document.form1.clientName.value = "";
	
	
}
function detil (idx){
	document.form1.method = "POST";
	document.form1.providerId.value = idx;
	document.form1.action = "provider";
	document.form1.navigation.value = "detail";
	document.form1.submit();

}
function carisort(param){
	document.form1.navigation.value = "gosearchexclusionbysort";
	document.form1.searchtext.value="<c:out value="${searchtext}" />";
	document.form1.searchby.value="<c:out value="${searchby}" />";
	document.form1.sortcolumn.value=param;
	document.form1.method = "POST";
	document.form1.submit();
}
</script>
