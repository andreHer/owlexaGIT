<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<%
String navigation = WebUtil.getAttributeString(request,"navigation","");
%>


<!-- Page Title Stop-->
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
						<a href="member?navigation=search&searchtext=<c:out value="${searchtext}"/>&searchby=<c:out value="${searchby}" />&index=<c:out value="${index}"/>" class="tabDetailViewDFLink" style="font-weight: normal;">Return to List&nbsp;</a>					
					</td>
				</tr>
			</tbody>
		</table>
	  </td>
    </tr>
	
			
		<tr>
	      	<td class="tabDetailViewDL" valign="top" width="15%">Customer Number :&nbsp;</td>
	     	<td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${member.customerPolicyNumber}"/></td>	      	      
			<td class="tabDetailViewDL" valign="top" width="15%">Client :&nbsp;</td>
	      	<td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${member.clientName}"/></td>
	      	<td class="tabDetailViewDL" valign="top" width="15%">Bank :&nbsp;</td>
	      	<td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${member.bank}"/> - <c:out value="${member.bankBranch}" /></td>
	    </tr>
	
			
		<tr>
		  <td class="tabDetailViewDL" valign="top" width="15%">Customer Name :&nbsp;</td>  
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${member.firstName}"/>&nbsp;<c:out value="${member.lastName}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Member Group :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${member.groupName}"/>  - <c:if test="${member.isVIP eq 1}"><b>VIP MEMBER</b></c:if></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Bank Account :&nbsp;</td>  
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${member.bankAccount}"/></td>
	    </tr>    

		<tr>	      
			 <td class="tabDetailViewDL" valign="top" width="15%">Current Card Number :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${member.currentCardNumber}" /></td>
	     	 <td class="tabDetailViewDL" valign="top" width="15%">Status :&nbsp;</td>		  
			  <td class="tabDetailViewDF" valign="top" width="20%">
			  	<b>
			  	<c:if test="${member.status eq -1}">PENDING</c:if>
				<c:if test="${member.status eq 1}">ACTIVE</c:if>
				<c:if test="${member.status eq -3}">PENDING CHANGEPLAN</c:if>
				<c:if test="${member.status eq -2}">BLOCKED</c:if>
				<c:if test="${member.status eq 2}">TERMINATED</c:if>
				<c:if test="${member.status eq 3}">RESIGNED</c:if>
				<c:if test="${member.status eq 4}">INACTIVE</c:if>
				<c:if test="${member.status eq 5}">INITIALIZED</c:if>
				</b>
			  </td>	
	      	
	      <td class="tabDetailViewDL" valign="top" width="15%">Bank Account Name :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${member.bankAccountName}"/></td>
	    </tr>
	    <tr>	      
			<td class="tabDetailViewDL" valign="top" width="15%">Other Number :&nbsp;</td>		  
			<td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${member.otherMemberNumber}"/></td>
	     	<td class="tabDetailViewDL" valign="top" width="15%">&nbsp;</td>
	      	<td class="tabDetailViewDF" valign="top" width="20%"></td>
	      	
			<td class="tabDetailViewDL" valign="top" width="15%">&nbsp;</td>
	      	<td class="tabDetailViewDF" valign="top" width="20%"></td>   
	    </tr>
	    <tr>	      
			<td class="tabDetailViewDL" valign="top" width="15%"></td>		  
			<td class="tabDetailViewDF" valign="top" width="20%"></td>
	     	<td class="tabDetailViewDL" valign="top" width="15%">&nbsp;</td>
	      	<td class="tabDetailViewDF" valign="top" width="20%"></td>
	      	
			<td class="tabDetailViewDL" valign="top" width="15%">&nbsp;</td>
	      	<td class="tabDetailViewDF" valign="top" width="20%"></td>   
	    </tr>
		<tr>
	      <td class="tabDetailViewDL" valign="top" width="15%">Date of Birth :&nbsp;</td>
		  <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${member.birthplace}"/>, <c:out value="${member.birthday}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Join Date :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${member.joinDate}"/></td>
	      
			<td class="tabDetailViewDL" valign="top" width="15%">Email :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${member.email}"/></td>

	    </tr>
	
			
		<tr>
	       	<td class="tabDetailViewDL" valign="top" width="15%">Policy Holder :&nbsp;</td>
		    <td class="tabDetailViewDF" valign="top" width="20%"><a href="member?navigation=detail&memberId=<c:out value="${member.parentMember.memberId}" />" class="listViewTdLinkS1"><c:out value="${member.parentName}"/></a></td>
	      	<td class="tabDetailViewDL" valign="top" width="15%">Effective Date :&nbsp;</td>
	      	<td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${member.effectiveDate}"/> s/d <c:out value="${member.expireDate}"/></td>
	      
			<td class="tabDetailViewDL" valign="top" width="15%">Telephone :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${member.telephone}"/></td>	  

	    </tr>
	
		<tr>
	      	<td class="tabDetailViewDL" valign="top" width="15%">Department :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${member.department}"/></td>
	     	<td class="tabDetailViewDL" valign="top" width="15%">Resigned Date :&nbsp;</td>
	      	<td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${member.resignedDate}"/></td>
	      	
			 <td class="tabDetailViewDL" valign="top" width="15%">Faximile :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${member.faximile}"/></td>	 

	    </tr>
			
			
		
		<tr>
	       <td class="tabDetailViewDL" valign="top" width="15%">Job Position :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${member.jobPosition}"/></td>
	      <td class="tabDetailViewDL" valign="top" width="15%">Renewal Date :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${member.renewalDate}"/></td>
	      
			 <td class="tabDetailViewDL" valign="top" width="15%">Mobile Phone :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${member.mobilePhone}"/></td>
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
	      	<td class="tabDetailViewDL" valign="top" width="15%">Country :&nbsp;</td>
	      	<td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${member.country}"/></td>
	     	<td class="tabDetailViewDL" valign="top" width="15%">Province :&nbsp;</td>
	      	<td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${member.province}"/></td>	      
			<td class="tabDetailViewDL" valign="top" width="15%">City :&nbsp;</td>
	      	<td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${member.city}"/></td>
	    </tr>
	
		<tr>
	      	<td class="tabDetailViewDL" valign="top" width="15%">Postal Code :&nbsp;</td>	 
	      	<td class="tabDetailViewDF" valign="top" width="20%"><c:out value="${member.postalCode}"/></td>    
	     	<td class="tabDetailViewDL" valign="top" width="15%">Address :&nbsp;</td>
	      <td class="tabDetailViewDF" valign="top" width="20%" colspan="5"><c:out value="${member.address}"/></td>   
	    </tr>
		
	
	
		</tbody>
		
</table>
	

<br />
<ul id="maintab" class="shadetabs">
	<li>
		<a href="member?navigation=detail&memberId=<c:out value="${memberId}" />" rel="tcontent1">Detail Member</a>
	</li>
	<li>
		<a href="member?navigation=listdependant&memberId=<c:out value="${memberId}" />" rel="tcontent2">Dependent</a>
	</li>
	<c:if test="${theUser.userType eq 2}">
	<li >
		<a href="memberproduct?navigation=listmember&memberId=<c:out value="${memberId}" />" rel="tcontent3">Product History</a>
	</li>
	<li >
		<a href="memberbenefit?navigation=listmember&memberId=<c:out value="${memberId}" />" rel="tcontent3">Benefit</a>
	</li>
	<li>
		<a href="memberclausul?navigation=listmember&memberId=<c:out value="${memberId}" />" rel="tcontent3">Clausul</a>
	</li>	
	</c:if>
	<c:if test="${theUser.userType eq 1}">

	<li >
		<a href="memberbenefit?navigation=listmember&memberId=<c:out value="${memberId}" />" rel="tcontent3">Benefit</a>
	</li>
	<li>
		<a href="memberclausul?navigation=listmember&memberId=<c:out value="${memberId}" />" rel="tcontent3">Clausul</a>
	</li>	
	</c:if>
	<li>
		<a href="claim?navigation=listmember&memberId=<c:out value="${memberId}" />" rel="tcontent4">Claim</a>
	</li>	
	<li>
		<a href="case?navigation=listmember&memberId=<c:out value="${memberId}" />" rel="tcontent5">Case</a>
	</li>	
	<li class="selected">
		<a href="clientprovider?navigation=listmember&memberId=<c:out value="${memberId}" />" rel="tcontent6">Provider</a>
	</li>	
	<li>
		<a href="excesscharge?navigation=list&memberId=<c:out value="${memberId}" />" rel="tcontent7">Excess Charge</a>
	</li>	

</ul>

<br />
<form name="form1" action="memberprovider" method="POST">
<input type="hidden" name="navigation" value="gosearch">
<input type="hidden" name="arah" value="">
<input type="hidden" name="index" value="<c:out value="${index}" />">
<input type="hidden" name="memberProviderId" value="<c:out value="${memberProvider.memberProviderId}" />">
<input type="hidden" name="memberId" value="<c:out value="${memberId}" />">
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Search Member Provider</h3></td>
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
            <td class="dataLabel" nowrap="nowrap">Search Keyword:
              &nbsp;&nbsp;
              
				<input size="20" name="searchtext" class="dataField" value="<c:out value="${searchtext}"/>" type="text">
              </td>
            <td class="dataLabel" nowrap="nowrap">Search Category:
              &nbsp;&nbsp;
              
                
								 <select name="searchby" class="inputbox">

 		   		   		   			<option value="modifiedBy" <c:if test="${searchby eq \"modifiedBy\"}">selected="true"</c:if> >Provider Name</option>
			   			<option value="deletedBy" <c:if test="${searchby eq \"deletedBy\"}">selected="true"</c:if> >Province</option>
			   		   		   			<option value="createdBy" <c:if test="${searchby eq \"createdBy\"}">selected="true"</c:if> >City</option>
			   		   		   		   		   		   		   		   		   		   		   </select>
				
              </td>
			
            <td class="dataLabel">
              <input title="Search [Alt+Q]" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" accesskey="Q" class="button" name="button" value="Search" type="submit">
			</td>
            </tr>
			
        </tbody>
      </table></td>
    </tr>
  </tbody>
</table>

	
<br />
<c:if test="${theUser.userType eq 2 and (theUser.roleId.roleId eq 0)}">
<input type="button"  class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:tambahi()" value="Add Provider">
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
				
				<%
				if (index == 1){
				%>
				<img src="images/start_off.gif" alt="Start" align="absmiddle" border="0" height="10" width="9">
					Start&nbsp;
									
				<img src="images/previous_off.gif" alt="Previous" align="absmiddle" border="0" height="10" width="4">
					Previous&nbsp;&nbsp;					
				<%
				}
				else if ((index-1) > 0){
				
				%>
				<img src="images/start.gif" alt="Start" align="absmiddle" border="0" height="10" width="9">
					<a href="javascript:goleftbgt()" class="listViewPaginationLinkS1">
						Start&nbsp;
					</a>				
				
				<img src="images/previous.gif" alt="Previous" align="absmiddle" border="0" height="10" width="4">
					<a href="javascript:goleft()" class="listViewPaginationLinkS1">
					Previous&nbsp;&nbsp;
					</a>
				<%
				}
				%>
					<span class="pageNumbers">(<c:out value="${minIndex}" /> - <c:out value="${maxIndex}" /> of <c:out value="${count}" />)</span>&nbsp;&nbsp;
				
				<%
				if (totalIndex > index){
				%>
				
				<a href="javascript:goright()" class="listViewPaginationLinkS1">Next&nbsp;
				<img src="images/next.gif" alt="Next" align="absmiddle" border="0" height="10" width="4"></a>&nbsp;&nbsp;
				<a href="javascript:gorightbgt()" class="listViewPaginationLinkS1">End&nbsp;
					<img src="images/end.gif" alt="End" align="absmiddle" border="0" height="10" width="9"></a>
				<%
				} else {
				
				%>
				Next&nbsp;
				<img src="images/next_off.gif" alt="Next" align="absmiddle" border="0" height="10" width="4">
				&nbsp;&nbsp;
				End&nbsp;
					<img src="images/end_off.gif" alt="End" align="absmiddle" border="0" height="10" width="9">
				<%
				}
				%>
			</td>
          </tr>
        </tbody>
      </table></td>
    </tr>
	<tr height="20">
		<td width="6%" nowrap="nowrap" class="listViewThS1" scope="col">
			<img src="images/blank.gif" alt="asd" height="1" width="1">No.</td>		

<!-- ini default generated table from database -->
		   		   		   			
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">&nbsp;Provider
		
		</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">&nbsp;Provider Category
		
		</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">&nbsp;City
		
		</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">&nbsp;Province
		
		</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">&nbsp;Country
		
		</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">PPK1</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">PPK2</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">PPK3</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%"></td>
	</tr>


	<c:forEach items="${MemberProviders}" var="memberProvider" varStatus="status" >
	<%	if (i % 2 == 0) {
			rowclass = "col1";
		} else if (i % 2 != 0) {
			rowclass= "col2";
		}
		i++;
	%>
	 <tr onMouseOver="setPointer(this, '910cbdf4-ffae-5c1a-db84-4434fa305b0a', 'over', '#e7f0fe', '#FFFFFF', '');" onMouseOut="setPointer(this, '910cbdf4-ffae-5c1a-db84-4434fa305b0a', 'out', '#e7f0fe', '#FFFFFF', '');" onMouseDown="setPointer(this, '910cbdf4-ffae-5c1a-db84-4434fa305b0a', 'click', '#e7f0fe', '#FFFFFF', '');" height="20">
      <td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><%=(i+((indexint-1)*countSet))%></td>

	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="left" valign="top"><c:out value="${memberProvider.providerId.providerName}" /></td>					   		   		
 	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="left" valign="top"><c:out value="${memberProvider.providerId.providerCategoryId.providerCategoryName}" /></td>					   		   							   		   		
 	   		   		   		
	 <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="left" valign="top"><c:out value="${memberProvider.providerId.city}" /></td>
	 <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="left" valign="top"><c:out value="${memberProvider.providerId.province}" /></td>
	 <td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="left" valign="top"><c:out value="${memberProvider.providerId.country}" /></td>
	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top"><c:if test="${memberProvider.ppk1 eq 1}">Y</c:if><c:if test="${memberProvider.ppk1 eq 0}">N</c:if></td>					   		
	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top"><c:if test="${memberProvider.ppk2 eq 1}">Y</c:if><c:if test="${memberProvider.ppk2 eq 0}">N</c:if></td>
	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top"><c:if test="${memberProvider.ppk3 eq 1}">Y</c:if><c:if test="${memberProvider.ppk3 eq 0}">N</c:if></td>
					   		   		   		
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="center" valign="top">
		

			<!-- ini default delete table for each data -->
			<a href="javascript:hapus('<c:out value="${memberProvider.memberProviderId}" />')">
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
				
				<%
				if (index == 1){
				%>
				<img src="images/start_off.gif" alt="Start" align="absmiddle" border="0" height="10" width="9">
					Start&nbsp;
									
				<img src="images/previous_off.gif" alt="Previous" align="absmiddle" border="0" height="10" width="4">
					Previous&nbsp;&nbsp;					
				<%
				}
				else if ((index-1) > 0){
				
				%>
				<img src="images/start.gif" alt="Start" align="absmiddle" border="0" height="10" width="9">
					<a href="javascript:goleftbgt()" class="listViewPaginationLinkS1">
						Start&nbsp;
					</a>				
				
				<img src="images/previous.gif" alt="Previous" align="absmiddle" border="0" height="10" width="4">
					<a href="javascript:goleft()" class="listViewPaginationLinkS1">
					Previous&nbsp;&nbsp;
					</a>
				<%
				}
				%>
					<span class="pageNumbers">(<c:out value="${minIndex}" /> - <c:out value="${maxIndex}" /> of <c:out value="${count}" />)</span>&nbsp;&nbsp;
				
				<%
				if (totalIndex > index){
				%>
				
				<a href="javascript:goright()" class="listViewPaginationLinkS1">Next&nbsp;
				<img src="images/next.gif" alt="Next" align="absmiddle" border="0" height="10" width="4">
				</a>&nbsp;&nbsp;
				<a href="javascript:gorightbgt()" class="listViewPaginationLinkS1">End&nbsp;
					<img src="images/end.gif" alt="End" align="absmiddle" border="0" height="10" width="9">
				</a>
				<%
				} else {
				
				%>
				Next&nbsp;
				<img src="images/next_off.gif" alt="Next" align="absmiddle" border="0" height="10" width="4">
				&nbsp;&nbsp;
				End&nbsp;
					<img src="images/end_off.gif" alt="End" align="absmiddle" border="0" height="10" width="9">
				<%
				}
				%>
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
<%
String nav="";
if(request.getAttribute("navigation").equals("gosearch")||request.getAttribute("navigation").equals("golookup")){
	nav = (String)request.getAttribute("navigation");
}
%>
function tambahi (){
	document.form1.navigation.value = "tambah";
	document.form1.action = "memberprovider-form";
	document.form1.method = "GET";
	document.form1.submit();
}
function hapus (idx){
	var delConfirm = window.confirm ("Are You Sure Want To Delete This Entry ?");

	if (delConfirm){
		document.form1.method = "POST";
		document.form1.memberProviderId.value = idx;
		document.form1.action = "memberprovider";
		document.form1.navigation.value = "delete";
		document.form1.submit();
	}
}
function ubah (idx){
	document.form1.method = "GET";
	document.form1.memberProviderId.value = idx;
	document.form1.action = "memberprovider-form";
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
	document.form1.action = "memberprovider";
	document.form1.method = "POST";
	document.form1.submit();
}
function detil (idx){
	document.form1.method = "POST";
	document.form1.memberProviderId.value = idx;
	document.form1.action = "memberprovider";
	document.form1.navigation.value = "detail";
	document.form1.submit();

}
</script>
