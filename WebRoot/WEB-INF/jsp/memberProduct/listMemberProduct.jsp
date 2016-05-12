<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<%
String navigation = WebUtil.getAttributeString(request,"navigation","");
%>

 <!-- Page Title Start // Should be put on <Title> tag-->

<!-- Page Title Stop-->

<!-- Search Container Start -->

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"></td>
      <td width="100%"></td>
    </tr>
  </tbody>
</table>
<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td><table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tbody>
          <tr>

            <td class="dataLabel" nowrap="nowrap">Search Keyword:
              &nbsp;&nbsp;
              
				<input size="20" name="searchtext" class="dataField" value="" type="text">
              </td>
            <td class="dataLabel" nowrap="nowrap">Search Category:
              &nbsp;&nbsp;
              
                <select name="search_category">
					
				</select>
              </td>
			  <td class="dataLabel" nowrap="nowrap">Status:
              &nbsp;&nbsp;
              
                <select name="search_status">					
					<option value="">Reject</option>
				</select>
				
              </td>
            <td class="dataLabel">
              <input title="Search [Alt+Shift+Q]" accesskey="Q" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" name="button" value="Search" type="submit">
                          
			</td>
            </tr>			
        </tbody>
      </table></td>
    </tr>
  </tbody>
</table>


<!-- Search Container Stop -->


<!-- Table Container Start -->



<div class="table_container">
	<!-- Table Toolbar Start -->
		<div id="toolbar">
		<div class="left">
			<a href="#" onClick="tambahi()" class="btn_create"><c:out value="${CREATE}" /></a>
		</div>
		<div class="right">
			<a href="#" class="btn_search" onclick="Effect.toggle('search_float','blind'); return false;">
			<c:out value="${SEARCH}" /></a>
		</div>
		<div style="clear: both;"></div>
		<%
		String nampak = "";
		if(navigation!=null&&navigation.equals("gosearch")){
		}else{
			nampak = " style=\"display: none;\"";
		}
		%>
		<div id="search_float"<%=nampak%>>
			<h4>
				<c:out value="${SEARCH}" /> Data memberProduct
			</h4>
			<form name=form1 onSubmit="cari()">
				<label for="domain_name">
					memberProduct
				</label>
				<input type="text" class="inputbox" name="searchtext" value="<c:out value="${searchtext}" />">
				<BR>
				<label for="status">
					<c:out value="${BASED_ON}" />
				</label>
<BR/>
				<div style="float: right;padding-right: 10px;">
				<input type="button" class="btn_search2" name="searchButton" value="search" onClick="javascript:cari()">
				</div>
				<div style="clear: both;"></div>
				<input type="hidden" name="navigation" value="<c:out value="${navigation}" />">
				<input type="hidden" name="arah" value="">
								<input type="hidden" name="memberProductId" value="<c:out value="${memberProduct.memberProductId}" />">
							</form>
		</div>
	</div>
	<!-- Table Toolbar Stop -->
	<!-- Table Content Start -->
<%
String alert = (String) request.getAttribute("alert");
if (alert != null && !alert.trim().equals("")) {%>
	<div id="warning">
		<c:out value="${alert}"></c:out>
	</div>
	<%}%>

<LABEL><c:out value="${LIST_OF}" />  Member Product</LABEL>

<table cellspacing="0" cellpadding="0">
		<th width="10">
			No.
		</th>

<!-- ini default generated table from database -->
		   		   		   		   		   		   		   		   			<th>Created By</th>
			   		   			<th>Deleted By</th>
			   		   			<th>Modified By</th>
			   		   		   		<th>Action</th>

<%
String rowclass = "";
int i=0;
int indexint = Integer.parseInt(request.getAttribute("index").toString());
WebUtil.getAttributeInteger(request,"index",0).intValue();
%>
	<c:forEach items="${MemberProducts}" var="memberProduct" varStatus="status" >
	<%	if (i % 2 == 0) {
			rowclass = "col1";
		} else if (i % 2 != 0) {
			rowclass= "col2";
		}
		i++;
	%>

	 

	
	<tr class="<%=rowclass%>" onmouseover="this.className='highlight'" onmouseout="this.className='<%=rowclass%>'" height="20">

		<td><%=(i+indexint-1)%></td>
		<!-- ini default generated table from database -->
		   		   		   		   		   		   		   		   			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${memberProduct.createdBy}" />
			
		</td>
			   		   			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${memberProduct.deletedBy}" />
			
		</td>
			   		   			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${memberProduct.modifiedBy}" />
			
		</td>
			   		   		   
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center">
			<!-- ini default edit table for each data -->
			<a href="javascript:detil('<c:out value="${memberProduct.memberProductId}" />')">
				<img src="images/icon_view.gif" class="action_icon" alt="View" title="View"></a>

			<!-- ini default edit table for each data -->
			<a href="javascript:ubah('<c:out value="${memberProduct.memberProductId}" />')">
				<img src="images/icon_edit.gif" class="action_icon" alt="Edit" title="Edit"></a>

			<!-- ini default delete table for each data -->
			<a href="javascript:hapus('<c:out value="${memberProduct.memberProductId}" />')">
				<img src="images/icon_delete.gif" class="action_icon" alt="Delete" title="Delete"></a>

		</td>
	</tr>
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	</c:forEach>
	</table>

	<table cellspacing="0" cellpadding="0" style="margin-top: 2px;">
	<tr>
		<td>
			<a href="javascript:goleftbgt()"><img src="images/butkiri1.gif" width="20" height="20"></a>
		</td>
		<td>
			<a href="javascript:goleft()"><img src="images/butkiri.gif" width="20" height="20"></a>
		</td>
		<td>
			<a href="javascript:goright()"><img src="images/butkanan.gif" width="20" height="20"></a>
		</td>
		<td>
			<a href="javascript:gorightbgt()"><img src="images/butkanan1.gif" width="20" height="20"></a>
		</td>
		<td>
			<span class="ChildTableSummaryStyle">GoTo</span>
				<input type="text" name="index" value="<c:out value="${index}"/>" class="inptype" size="3">
				&nbsp;
				<a href="javascript:go()"><img src="images/butgo.gif" width="30" height="25" align="absmiddle"></a>
		</td>
		<td>
			<font color="#999999" class="ChildTableSummaryStyle"><c:out value="${PAGE}" /> <c:out value="${index}"/> <c:out value="${FROM}" /> <c:out value="${halAkhir}"/>, Total <c:out value="${count}" /> record</font>
		</td>
	</tr>
</table>
<!-- Table Content Stop -->
	<!-- Table Toolbar Start -->
	<div id="toolbar">
		<div class="left">
			<a href="#" onClick="tambahi()" class="btn_create"><c:out value="${CREATE}" /></a>
		</div>
	</div>
	<div style="clear:both;"></div>
		<!-- Table Toolbar Stop -->

</div>

<!-- Table Container Stop -->
<script language="Javascript">
<%
String nav="";
if(request.getAttribute("navigation").equals("gosearch")||request.getAttribute("navigation").equals("golookup")){
	nav = (String)request.getAttribute("navigation");
}
%>

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
	document.form1.navigation.value = "gosearch";
	document.form1.action = "memberproduct";
	document.form1.method = "POST";
	document.form1.submit();
}
function detil (idx){
	document.form1.method = "POST";
	document.form1.memberProductId.value = idx;
	document.form1.action = "memberproduct";
	document.form1.navigation.value = "detail";
	document.form1.submit();

}
</script>
