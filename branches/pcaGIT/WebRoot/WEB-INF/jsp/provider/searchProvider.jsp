<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<%
String navigation = WebUtil.getAttributeString(request,"navigation","");
%>

<!-- Page Title Start // Should be put on <Title> tag-->

<!-- Page Title Stop-->
<%
String alert = (String) request.getAttribute("alert");
int index = 0;
int totalIndex = 0;
int count = 0;
int countSet = 0;

// boolean sortProviderName = (Boolean) request.getAttribute("sortProviderName");
// boolean sortProviderCat = (Boolean) request.getAttribute("sortProviderCat");
// boolean sortCity = (Boolean) request.getAttribute("sortCity");
// boolean sortProvince = (Boolean) request.getAttribute("sortProvince");
// boolean sortCountry = (Boolean) request.getAttribute("sortCountry");
// boolean sortMID = (Boolean) request.getAttribute("sortMID");
// boolean sortEdc = (Boolean) request.getAttribute("sortEdc");
// boolean sortStatus = (Boolean) request.getAttribute("sortStatus");
// boolean sortProviderCode = (Boolean) request.getAttribute("sortProviderCode");
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
<input type="hidden" name="navigation" value="gosearch">
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
            <form>
            </form>
            <td class="dataLabel" nowrap="nowrap">Search Keyword:
              &nbsp;&nbsp;
              
				<input size="20" name="searchtext" class="dataField" value="<c:out value="${searchtext}"/>" type="text">
              </td>
            <td class="dataLabel" nowrap="nowrap">Search Category:
              &nbsp;&nbsp;
              
                
				 <select name="searchby" class="inputbox">

	 		   			<option value="providerName" <c:if test="${searchby eq \"providerName\"}">selected="true"</c:if> >Provider Name</option>
			   			<option value="providerCategoryName" <c:if test="${searchby eq \"providerCategoryName\"}">selected="true"</c:if> >Provider Category</option>
			   			<option value="providerCode" <c:if test="${searchby eq \"providerCode\"}">selected="true"</c:if> >Provider Code</option>
			   			<option value="city" <c:if test="${searchby eq \"city\"}">selected="true"</c:if> >City</option>
			   			<option value="province" <c:if test="${searchby eq \"province\"}">selected="true"</c:if> >Province</option>
			   			<option value="country" <c:if test="${searchby eq \"country\"}">selected="true"</c:if> >Country</option>
			   		<option value="MID" <c:if test="${searchby eq \"MID\"}">selected="true"</c:if> >MID</option>
			   		
			   			<option value="providerEdcCode" <c:if test="${searchby eq \"providerEdcCode\"}">selected="true"</c:if> >Provider EDC Code</option>
			   </select>
				
              </td>
			  <td class="dataLabel" nowrap="nowrap">Status:
              &nbsp;&nbsp;
              
                <select name="status">					
					<option value="-1">-- All Status --</option>
					<option value="1" <c:if test="${status eq 1 }">selected</c:if>>Active</option\
					<option value="2" <c:if test="${status eq 2 }" >selected</c:if>>Terminated</option>					
				</select>
				
              </td>
            <td class="dataLabel">
              <input title="Search [Alt+Shift+Q]" accesskey="Q" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" name="button" value="Search" type="submit">
                          
			</td>
            </tr>
            <c:if test="${theUser.userType eq '2'}">
			<tr>
            
            <td class="dataLabel" nowrap="nowrap"> Minimum Date :
              &nbsp;&nbsp;&nbsp;&nbsp;
              
                <input name="minimum_date" id="jscal_field" tab="1" maxlength="10" size="11" readonly="readonly" value="" type="text">
				<img src="images/jscalendar.gif" alt="Enter Date" id="jscal_trigger" align="absmiddle" height="18" width="18">
				
				<script type="text/javascript">
    					Calendar.setup({
        					inputField     :    "jscal_field",     // id of the input field
        					ifFormat       :    "%Y-%m-%d",      // format of the input field
        					button         :    "jscal_trigger",  // trigger for the calendar (button ID)
        					align          :    "Bl",           // alignment (defaults to "Bl")
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
        					align          :    "Bl",           // alignment (defaults to "Bl")
        					singleClick    :    true
    					});
				 	</script>
			</td>
            <td class="dataLabel">&nbsp;&nbsp;
              </td>
            <td align="right">
            </td>
          </tr>
          </c:if>
        </tbody>
      </table></td>
    </tr>
  </tbody>
</table>

<c:if test="${(theUser.roleId.roleId eq 0 )or(theUser.roleId.roleId eq 6 ) }">
<input type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:tambahi()" value="Create">
<input title="Update Provider Config [Alt+Shift+R]" accesskey="R" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:updateProviderConfig()" name="confProvider" value=" Provider Config " type="button">
</c:if>
<c:if test="${(theUser.roleId.roleId eq 4) or(theUser.roleId.roleId eq 3)}">
<input type="button" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:tambahiClaim()" value="Create">
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
		<td width="2%" nowrap="nowrap" class="listViewThS1" scope="col">
			<img src="images/blank.gif" alt="asd" height="1" width="1">No.</td>		

<!-- ini default generated table from database -->

				<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">Provider Name &nbsp;
					<input type="hidden" name="sortByOrderProvName" value="<c:out value="${sortProviderName?'true':'false'}" />">
					<a href="javascript:carisort('providername')" class="listViewThLinkS1">
					<img src="images/arrow_<c:out value="${sortProviderName?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
				</td>
<!-- 				<c:if test=""></c:if> -->
<!-- 			   	<td scope="col" class="listViewThS1" nowrap="nowrap" width="19%">Provider Name &nbsp; -->
<!-- 					<input type="hidden" name="sortByOrderProvName" value="false"> -->
<!-- 					<a href="javascript:carisort('providername')" class="listViewThLinkS1"> -->
<!-- 					<img src="images/arrow_down.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a> -->
<!-- 				</td>		 -->

				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Provider Category &nbsp;
					<input type="hidden" name="sortByOrderProvCat" value="<c:out value="${sortProviderCat?'true':'false'}" />">
					<a href="javascript:carisort('providercategory')" class="listViewThLinkS1">
					<img src="images/arrow_<c:out value="${sortProviderCat?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
				</td>
<!-- 			 	<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Provider Category &nbsp; -->
<!-- 					<input type="hidden" name="sortByOrderProvCat" value="false"> -->
<!-- 					<a href="javascript:carisort('providercategory')" class="listViewThLinkS1"> -->
<!-- 					<img src="images/arrow_down.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a> -->
<!-- 				</td> -->

			 	<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Provider Code &nbsp;
			 		<input type="hidden" name="sortByOrderCode" value="<c:out value="${sortProviderCode?'true':'false'}" />">
					<a href="javascript:carisort('providercode')" class="listViewThLinkS1">
					<img src="images/arrow_<c:out value="${sortProviderCode?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
			 	</td>
<!-- 			  	<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Provider Code &nbsp; -->
<!-- 			 		<input type="hidden" name="sortByOrderCode" value="false"> -->
<!-- 					<a href="javascript:carisortprovidercode()" class="listViewThLinkS1"> -->
<!-- 					<img src="images/arrow_down.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a> -->
<!-- 			 	</td> -->
			  			
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">City &nbsp;
					<input type="hidden" name="sortByOrderCity" value="<c:out value="${sortCity?'true':'false'}" />">
					<a href="javascript:carisort('providercity')" class="listViewThLinkS1">
					<img src="images/arrow_<c:out value="${sortCity?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
				</td>
<!-- 				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">City &nbsp; -->
<!-- 					<input type="hidden" name="sortByOrderCity" value="false"> -->
<!-- 					<a href="javascript:carisortcity()" class="listViewThLinkS1"> -->
<!-- 					<img src="images/arrow_down.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a> -->
<!-- 				</td> -->
 		
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Province &nbsp;
					<input type="hidden" name="sortByOrderProvince" value="<c:out value="${sortProvince?'true':'false'}" />">
					<a href="javascript:carisort('providerprovince')" class="listViewThLinkS1">
					<img src="images/arrow_<c:out value="${sortProvince?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
				</td>
<!-- 			 	<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Province &nbsp; -->
<!-- 					<input type="hidden" name="sortByOrderProvince" value="false"> -->
<!-- 					<a href="javascript:carisortprovince()" class="listViewThLinkS1"> -->
<!-- 					<img src="images/arrow_down.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a> -->
<!-- 				</td> -->  			
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Country &nbsp;
					<input type="hidden" name="sortByOrderCountry" value="<c:out value="${sortCountry?'true':'false'}" />">
					<a href="javascript:carisort('providercountry')" class="listViewThLinkS1">
					<img src="images/arrow_<c:out value="${sortCountry?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
				</td>	
<!-- 			 	<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Country &nbsp; -->
<!-- 					<input type="hidden" name="sortByOrderCountry" value="false"> -->
<!-- 					<a href="javascript:carisortcountry()" class="listViewThLinkS1"> -->
<!-- 					<img src="images/arrow_down.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a> -->
<!-- 				</td> -->

				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">MID &nbsp;
					<input type="hidden" name="sortByOrderMID" value="<c:out value="${sortMID?'true':'false'}" />">
					<a href="javascript:carisort('providermid')" class="listViewThLinkS1">
					<img src="images/arrow_<c:out value="${sortMID?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
				</td>
<!-- 			 	<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">MID &nbsp; -->
<!-- 					<input type="hidden" name="sortByOrderMID" value="false"> -->
<!-- 					<a href="javascript:carisortmid()" class="listViewThLinkS1"> -->
<!-- 					<img src="images/arrow_down.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a> -->
<!-- 				</td> -->
			 
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Status &nbsp;
					<input type="hidden" name="sortByOrderStatus" value="<c:out value="${sortStatus?'true':'false'}" />">
					<a href="javascript:carisort('providerstatus')" class="listViewThLinkS1">
					<img src="images/arrow_<c:out value="${sortStatus?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
				</td>
<!-- 			 	<td scope="col" class="listViewThS1" nowrap="nowrap" width="10%">Status &nbsp; -->
<!-- 					<input type="hidden" name="sortByOrderStatus" value="false"> -->
<!-- 					<a href="javascript:carisortstatus()" class="listViewThLinkS1"> -->
<!-- 					<img src="images/arrow_down.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a> -->
<!-- 				</td> -->
	 
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">EDC  &nbsp;
					<input type="hidden" name="sortByOrderEdc" value="<c:out value="${sortEdc?'true':'false'}" />">
					<a href="javascript:carisort('provideredc')" class="listViewThLinkS1">
					<img src="images/arrow_<c:out value="${sortEdc?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
				</td>
				<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">Status Prospek  &nbsp;
					<input type="hidden" name="sortByOrderStatusProspek" value="<c:out value="${sortStatusProspek?'true':'false'}" />">
					<a href="javascript:carisort('providerstatusprospek')" class="listViewThLinkS1">
					<img src="images/arrow_<c:out value="${sortStatusProspek?'up':'down'}" />.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a>
				</td>
<!-- 			 	<td scope="col" class="listViewThS1" nowrap="nowrap" width="5%">EDC  &nbsp; -->
<!-- 					<input type="hidden" name="sortByOrderEdc" value="false"> -->
<!-- 					<a href="javascript:carisortedc()" class="listViewThLinkS1"> -->
<!-- 					<img src="images/arrow_down.gif" alt="Sort" align="absmiddle" border="0" height="9" width="8"></a> -->
<!-- 				</td> -->
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
	 
	 <c:choose>
	 <c:when test="${provider.statusId.status eq 'ACTIVE' && provider.statusProspek eq 2}" > 
      <td class="oddListRowS1StatusActive" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><%=(i+((indexint-1)*countSet))%></td>

		<td class="oddListRowS1StatusActive" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<c:if test="${navigation eq 'searchcapitation'}">
				<a href="provider?navigation=detail&index=<c:out value="${index}" />&providerId=<c:out value="${provider.providerId}" />&listnavigation=<c:out value="${navigation}" />" class="linkDetail"><c:out value="${provider.providerName}" /></a>
			</c:if>
			<c:if test="${navigation ne 'searchcapitation'}">
				<a href="provider?navigation=detail&index=<c:out value="${index}" />&providerId=<c:out value="${provider.providerId}" />" class="linkDetail"><c:out value="${provider.providerName}" /></a>
			</c:if>
		</td>
					   				<td class="oddListRowS1StatusActive" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${provider.providerCategoryId.providerCategoryName}" />
			
		</td>
	
			   				<td class="oddListRowS1StatusActive" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${provider.providerCode}" />
			
		</td>
	
					   				<td class="oddListRowS1StatusActive" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${provider.city}" />
			
		</td>

					   				<td class="oddListRowS1StatusActive" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${provider.province}" />
			
		</td>
					   				<td class="oddListRowS1StatusActive" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${provider.country}" />
			
		</td>
				   				<td class="oddListRowS1StatusActive" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${provider.edcCode}" />
			
		</td>
		<td class="oddListRowS1StatusActive" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${provider.statusId.status}" />
			
		</td>		   	
			<td class="oddListRowS1StatusActive" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:if test="${empty provider.edcCode}">
					TIDAK
				</c:if>
				<c:if test="${not empty provider.edcCode}">
					YA
				</c:if>
		</td>	
		
		<td class="oddListRowS1StatusActive" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
	      <c:if test="${provider.statusProspek eq 1}">NON PROVIDER </c:if>
	      <c:if test="${provider.statusProspek eq 2}">PROVIDER </c:if>
	    </td>
</c:when>
<c:when test="${provider.statusId.status eq 'ACTIVE' && provider.statusProspek eq 1}" > 
      <td class="oddListRowS1StatusActiveProspek" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><%=(i+((indexint-1)*countSet))%></td>

		<td class="oddListRowS1StatusActiveProspek" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<c:if test="${navigation eq 'searchcapitation'}">
				<a href="provider?navigation=detail&index=<c:out value="${index}" />&providerId=<c:out value="${provider.providerId}" />&listnavigation=<c:out value="${navigation}" />" class="linkDetail"><c:out value="${provider.providerName}" /></a>
			</c:if>
			<c:if test="${navigation ne 'searchcapitation'}">
				<a href="provider?navigation=detail&index=<c:out value="${index}" />&providerId=<c:out value="${provider.providerId}" />" class="linkDetail"><c:out value="${provider.providerName}" /></a>
			</c:if>
		</td>
					   				<td class="oddListRowS1StatusActiveProspek" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${provider.providerCategoryId.providerCategoryName}" />
			
		</td>
	
			   				<td class="oddListRowS1StatusActiveProspek" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${provider.providerCode}" />
			
		</td>
	
					   				<td class="oddListRowS1StatusActiveProspek" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${provider.city}" />
			
		</td>

					   				<td class="oddListRowS1StatusActiveProspek" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${provider.province}" />
			
		</td>
					   				<td class="oddListRowS1StatusActiveProspek" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${provider.country}" />
			
		</td>
				   				<td class="oddListRowS1StatusActiveProspek" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${provider.edcCode}" />
			
		</td>
		<td class="oddListRowS1StatusActiveProspek" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${provider.statusId.status}" />
			
		</td>		   	
			<td class="oddListRowS1StatusActiveProspek" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:if test="${provider.edcCode eq null}">
					TIDAK
				</c:if>
				<c:if test="${provider.edcCode ne null}">
					YA
				</c:if>
		</td>	
		
		<td class="oddListRowS1StatusActiveProspek" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
	      <c:if test="${provider.statusProspek eq 1}">NON PROVIDER </c:if>
	      <c:if test="${provider.statusProspek eq 2}">PROVIDER </c:if>
	    </td>
</c:when>

<c:otherwise>
	 <td class="oddListRowS1StatusTerminated" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><%=(i+((indexint-1)*countSet))%></td>

		<td class="oddListRowS1StatusTerminated" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			<c:if test="${navigation eq 'searchcapitation'}">
				<a href="provider?navigation=detail&index=<c:out value="${index}" />&providerId=<c:out value="${provider.providerId}" />&listnavigation=<c:out value="${navigation}" />" ><c:out value="${provider.providerName}" /></a>
			</c:if>
			<c:if test="${navigation ne 'searchcapitation'}">
				<a href="provider?navigation=detail&index=<c:out value="${index}" />&providerId=<c:out value="${provider.providerId}" />" ><c:out value="${provider.providerName}" /></a>
			</c:if>
		</td>
					   				<td class="oddListRowS1StatusTerminated" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${provider.providerCategoryId.providerCategoryName}" />
			
		</td>
	
			   				<td class="oddListRowS1StatusTerminated" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${provider.providerCode}" />
			
		</td>
	
					   				<td class="oddListRowS1StatusTerminated" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${provider.city}" />
			
		</td>

					   				<td class="oddListRowS1StatusTerminated" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${provider.province}" />
			
		</td>
					   				<td class="oddListRowS1StatusTerminated" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${provider.country}" />
			
		</td>
				   				<td class="oddListRowS1StatusTerminated" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${provider.edcCode}" />
			
		</td>
		<td class="oddListRowS1StatusTerminated" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:out value="${provider.statusId.status}" />
			
		</td>		   	
			<td class="oddListRowS1StatusTerminated" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
			
				<c:if test="${provider.edcCode eq null}">
					TIDAK
				</c:if>
				<c:if test="${provider.edcCode ne null}">
					YA
				</c:if>
		</td>	
		<td class="oddListRowS1StatusTerminated" bgcolor="#e7f0fe" nowrap="nowrap" valign="top">
	      <c:if test="${provider.statusProspek eq 1}">NON PROVIDER </c:if>
	      <c:if test="${provider.statusProspek eq 2}">PROVIDER </c:if>
	    </td>

</c:otherwise>
</c:choose>

    </tr>
   
	<tr>
      <td colspan="20" class="listViewHRS1"></td>
    </tr>
	
	</c:forEach>
	
<tr>
            <td class="listViewPaginationTdS1" align="left"></td>
            <td class="listViewPaginationTdS1" align="right" nowrap="nowrap" colspan=20>
				
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
if(request.getAttribute("navigation").equals("gosearch")||request.getAttribute("navigation").equals("golookup")||request.getAttribute("navigation").equals("searchedcalertdefault") ||
	request.getAttribute("navigation").equals("searchedcalertcustom") || request.getAttribute("navigation").equals("searchrollpaperalertdefault") ||
	request.getAttribute("navigation").equals("searchrollpaperalertcustom")){
	nav = (String)request.getAttribute("navigation");
}
%>
function tambahi (){
	document.form1.navigation.value = "tambah";
	document.form1.action = "provider-form";
	document.form1.method = "GET";
	document.form1.submit();
}

function tambahiClaim (){
	document.form1.navigation.value = "tambahClaim";
	document.form1.action = "providerclaim-form";
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
	document.form1.navigation.value = "gosearch";
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
function updateProviderConfig(){
	document.form1.navigation.value = "update-provider-configuration";
	document.form1.action = "providerconfig-form";
	document.form1.method = "GET";
	document.form1.submit();
}
</script>
