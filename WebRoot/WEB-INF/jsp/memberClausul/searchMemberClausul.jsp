<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<%
String navigation = WebUtil.getAttributeString(request,"navigation","");
%>

 <!-- Page Title Start // Should be put on <Title> tag-->
<H1>
Member Clausul
</H1>
<!-- Page Title Stop-->

<!-- Search Container Start -->

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif" alt="Call Search" border="0">&nbsp;Search Member Clausul</h3></td>
      <td width="100%"><img src="images/blank.gif" alt="asd" height="1" width="1"></td>
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
			<tr>
            
            <td class="dataLabel" nowrap="nowrap"> Minimum Date :
              &nbsp;&nbsp;&nbsp;&nbsp;
              
                <input name="minimum_date" id="jscal_field" tabindex="1" maxlength="10" size="11" readonly="readonly" value="" type="text">
				<img src="images/jscalendar.gif" alt="Enter Date" id="jscal_trigger" align="absmiddle" height="18" width="18">
				
				<script type="text/javascript">
    					Calendar.setup({
        					inputField     :    "jscal_field",     // id of the input field
        					ifFormat       :    "%Y-%m-%d",      // format of the input field
        					button         :    "jscal_trigger",  // trigger for the calendar (button ID)
        					align          :    "Tl",           // alignment (defaults to "Bl")
        					singleClick    :    true
    					});
				 	</script>
              
			</td>
            <td class="dataLabel" nowrap="nowrap"> Maximum Date :
              &nbsp;&nbsp;&nbsp;&nbsp;
              
            <input name="maximum_date" id="jscal_field_max" tabindex="1" maxlength="10" size="11" readonly="readonly" value="" type="text">
			<img src="images/jscalendar.gif" alt="Enter Date" id="jscal_max_trigger" align="absmiddle" height="18" width="18">
			
					<script type="text/javascript">
    					Calendar.setup({
        					inputField     :    "jscal_field_max",     // id of the input field
        					ifFormat       :    "%Y-%m-%d",      // format of the input field
        					button         :    "jscal_max_trigger",  // trigger for the calendar (button ID)
        					align          :    "Tl",           // alignment (defaults to "Bl")
        					singleClick    :    true
    					});
				 	</script>
			</td>
            <td class="dataLabel">&nbsp;&nbsp;
              </td>
            <td align="right">
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
				<c:out value="${SEARCH}" /> Data memberClausul
			</h4>
			<form name=form1 onSubmit="cari()">
				<label for="domain_name">
					memberClausul
				</label>
				<input type="text" class="inputbox" name="searchtext" value="<c:out value="${searchtext}" />">
				<BR>
				<label for="status">
					<c:out value="${BASED_ON}" />
				</label>
				 <select name="searchby" class="inputbox">

 		   		   		   		   			<option name="createdBy" <c:if test="${searchby eq \"createdBy\"}">selected="true"</c:if> >Created By</option>
			   		   			<option name="deletedBy" <c:if test="${searchby eq \"deletedBy\"}">selected="true"</c:if> >Deleted By</option>
			   		   			<option name="modifiedBy" <c:if test="${searchby eq \"modifiedBy\"}">selected="true"</c:if> >Modified By</option>
			   		   </select>
<BR/>
				<div style="float: right;padding-right: 10px;">
				<input type="button" class="btn_search2" name="searchButton" value="search" onClick="javascript:cari()">
				</div>
				<div style="clear: both;"></div>
				<input type="hidden" name="navigation" value="<c:out value="${navigation}" />">
				<input type="hidden" name="arah" value="">
								<input type="hidden" name="memberClausulId" value="<c:out value="${memberClausul.memberClausulId}" />">
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

<LABEL><c:out value="${LIST_OF}" />  Member Clausul</LABEL>

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
	<c:forEach items="${MemberClausuls}" var="memberClausul" varStatus="status" >
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
			
				<c:out value="${memberClausul.createdBy}" />
			
		</td>
			   		   			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${memberClausul.deletedBy}" />
			
		</td>
			   		   			<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${memberClausul.modifiedBy}" />
			
		</td>
			   		   
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center">
			<!-- ini default edit table for each data -->
			<a href="javascript:detil('<c:out value="${memberClausul.memberClausulId}" />')">
				<img src="images/icon_view.gif" class="action_icon" alt="View" title="View"></a>

			<!-- ini default edit table for each data -->
			<a href="javascript:ubah('<c:out value="${memberClausul.memberClausulId}" />')">
				<img src="images/icon_edit.gif" class="action_icon" alt="Edit" title="Edit"></a>

			<!-- ini default delete table for each data -->
			<a href="javascript:hapus('<c:out value="${memberClausul.memberClausulId}" />')">
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
function tambahi (){
	document.form1.navigation.value = "tambah";
	document.form1.action = "memberclausul-form";
	document.form1.method = "GET";
	document.form1.submit();
}
function hapus (idx){
	var delConfirm = window.confirm ("Are You Sure Want To Delete This Entry ?");

	if (delConfirm){
		document.form1.method = "POST";
		document.form1.memberClausulId.value = idx;
		document.form1.action = "memberclausul";
		document.form1.navigation.value = "delete";
		document.form1.submit();
	}
}
function ubah (idx){
	document.form1.method = "GET";
	document.form1.memberClausulId.value = idx;
	document.form1.action = "memberclausul-form";
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
	document.form1.navigation.value = "gosearch";
	document.form1.action = "memberclausul";
	document.form1.method = "POST";
	document.form1.submit();
}
function detil (idx){
	document.form1.method = "POST";
	document.form1.memberClausulId.value = idx;
	document.form1.action = "memberclausul";
	document.form1.navigation.value = "detail";
	document.form1.submit();

}
</script>
