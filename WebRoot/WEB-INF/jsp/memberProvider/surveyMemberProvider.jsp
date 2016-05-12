<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page import="com.ametis.cms.util.WebUtil" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<%
String navigation = WebUtil.getAttributeString(request,"navigation","");
%>


<!-- Page Title Stop-->

<!-- Search Container Start -->


<br />
<form name="form1" action="memberprovider" method="POST">
<input type="hidden" name="navigation" value="savesurvey">
<input type="hidden" name="memberProviderId" value="<c:out value="${memberProviderId}" />">
<input type="hidden" name="memberId" value="<c:out value="${memberId}" />">

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3>Survey Kepuasan Peserta</h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>


<!-- Search Container Stop -->


<!-- Table Container Start -->



<div class="table_container">
	<!-- Table Toolbar Start -->
		
		
		<%
		String nampak = "";
		if(navigation!=null&&navigation.equals("gosearch")){
		}else{
			nampak = " style=\"display: none;\"";
		}
		%>
		
</div>
	<!-- Table Toolbar Stop -->
	

<br />
<br />

 	
<table class="listView" cellspacing="0" cellpadding="0" width="100%">
	<tbody>
	<tr>
      <td colspan="20" align="right"></td>
    </tr>
	<tr height="20">
		<td width="2%" nowrap="nowrap" class="listViewThS1" scope="col">No.</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">&nbsp;Pertanyaan Survey</td>
		<td scope="col" class="listViewThS1" nowrap="nowrap" width="15%">&nbsp;Nilai</td>
		<td scope="col" class="listViewThS1" width="30%">&nbsp;Catatan</td>		
	</tr>


	<c:forEach items="${qItemList}" var="qItem" varStatus="status" >
	
	 <tr  height="20">
      	<td class="oddListRowS1" align="center" bgcolor="#e7f0fe" nowrap="nowrap" valign="top"><c:out value="${status.index+1}" /></td>
		<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="left" valign="top"><c:out value="${qItem.questionaireSubject}" /></td>					   		   		
	 	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap" align="left" valign="top">
	 		<input type="radio" name="vote_<c:out value="${qItem.questionaireItemId}" />" value="2"> Sangat Baik &nbsp;
	 		<input type="radio" name="vote_<c:out value="${qItem.questionaireItemId}" />" value="1"> Baik &nbsp;
	 		<input type="radio" name="vote_<c:out value="${qItem.questionaireItemId}" />" value="-1"> Buruk &nbsp;
	 		<input type="radio" name="vote_<c:out value="${qItem.questionaireItemId}" />" value="-2"> Sangat Buruk &nbsp;
	 	</td>
	 	<td class="oddListRowS1" bgcolor="#e7f0fe" nowrap="nowrap"  valign="top">
	 		<textarea rows="2" cols="50" name="review_<c:out value="${ qItem.questionaireItemId}" />"></textarea>
	 	</td>
    </tr>
   
	<tr>
      <td colspan="20" class="listViewHRS1" style="border-bottom: 1px solid #000;"></td>
    </tr>
	
	</c:forEach>
	
<tr>
            <td class="listViewPaginationTdS1" align="left"></td>
            <td class="listViewPaginationTdS1" align="right" nowrap="nowrap" colspan="20">
			</td>
          </tr>
	</tbody>
	</table>
</form>
	
<!-- Table Content Stop -->
	<!-- Table Toolbar Start -->
<input title="Simpan Penilaian" 		accesskey="T" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:survey()" name="SimpanSurvey" value=" Simpan Penilaian " type="button">	
		<!-- Table Toolbar Stop -->



<!-- Table Container Stop -->
<script language="Javascript">

function survey (){
	var delConfirm = window.confirm ("Are You Sure Want To Review This Provider ?");

	if (delConfirm){
		document.form1.method = "POST";		
		document.form1.action = "memberprovider";
		document.form1.navigation.value = "savesurvey";
		document.form1.submit();
	}
}

</script>
