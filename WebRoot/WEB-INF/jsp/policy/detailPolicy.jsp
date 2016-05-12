<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript" src="scripts/tinymce/tinymce.min.js"></script>
<script type="text/javascript">
tinymce.init({
    selector: "textarea",
    readonly : true,
	toolbar: "false",
	menubar: "false",
	height: '100%'
 });
</script>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Detail Policy</h3></td>
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>

<br />

<form action="policy" method="GET" name="form1" id="form_layout">
	<input type="hidden" name="navigation" value="">
	<input type="hidden" name="policyId" value="<c:out value="${policy.policyId}" />">
	
	
<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr> 
      <td style="padding-bottom: 2px;">        
	        <c:if test="${policy.policyStatus.statusId eq -1}">
				<input title="Edit [Alt+E]" accesskey="E" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:ubah()" name="Edit" value=" Update " type="button">		
	        	<input title="Hapus [Alt+H]" accesskey="H" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:hapus()" name="Delete" value=" Delete " type="button">
	        	<input title="Add Benefit [Alt+B]" accesskey="B" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:addBenefit()" name="benefit" value=" Add Benefit " type="button">        
	        	<input title="Add Clausul [Alt+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:addClausul()" name="clausul" value=" Add Clausul " type="button">
				<input title="Activate [Alt+A]" accesskey="A" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:activate()" name="active" value=" Activate " type="button">
						
			</c:if>
			<c:if test="${theUser.userType eq 2 and theUser.roleId.roleId eq 0 || theUser.userType eq 2 and theUser.roleId.roleId eq 22 }" >
			<c:if test="${policy.policyStatus.statusId eq 1 or policy.policyStatus.statusId eq 7}">
			
				<input title="Edit [Alt+E]" accesskey="E" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:ubah()" name="Edit" value=" Update " type="button">		
	        	
	        	<input title="Add Benefit [Alt+B]" accesskey="B" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:addBenefit()" name="benefit" value=" Add Benefit " type="button">        
	        	
	        	<input title="Add Clausul [Alt+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:addClausul()" name="clausul" value=" Add Clausul " type="button">
				<input title="Terminate [Alt+T]" accesskey="T" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:terminatePolicy()" name="Terminate" value=" Terminate " type="button">
				
				<input title="Extend Policy [Alt+X]" accesskey="X" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:extendPolicy()" name="ext" value=" Extend Policy " type="button">
				
				<input title="Renew Policy [Alt+R]" accesskey="R" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:renewPolicy()" name="renew" value=" Renew Policy " type="button">
				
				<input title="Add Fund [Alt+F]" accesskey="F" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:addFund()" name="tambahFund" value=" Add Fund " type="button">
				
				<c:if test="${policy.doSynchronize eq null or policy.doSynchronize eq 0}">
					<input title="Synchronize [Alt+S]" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:synchronize()" name="synch" value=" Synchronize " type="button">
				</c:if>
				
			</c:if>
			</c:if>

			<c:if test="${policy.policyTcFile1 ne null}">
				<input title="Activate [Alt+A]" accesskey="A" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:downloadTC1()" name="tc1" value=" TC 1 File " type="button">
			</c:if>
			<c:if test="${policy.policyTcFile2 ne null}">
				<input title="Activate [Alt+A]" accesskey="A" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:downloadTC2()" name="tc2" value=" TC 2 File " type="button">
			</c:if>
			<c:if test="${policy.policyTcFile3 ne null}">
				<input title="Activate [Alt+A]" accesskey="A" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:downloadTC3()" name="tc3" value=" TC 3 File " type="button">
			</c:if>
		
		</td>
      <td align="right"></td>
    </tr>
  </tbody>
</table>
</form>

<%@ include file="../mainContentPolicy.jsp" %>

<br/>
		

<script language="javascript">
	function hapus (){
		var delConfirm = window.confirm ("Are You Sure Want To Delete This Entry ?");
		if (delConfirm){
			document.form1.navigation.value = "delete";
			document.form1.submit();
		}
	}
	function activate (){
		var delConfirm = window.confirm ("Are You Sure Want To Activate This Policy ?");
		if (delConfirm){
			document.form1.navigation.value = "activate";
			document.form1.submit();
		}
	}
	function synchronize (){
		var delConfirm = window.confirm ("Are You Sure Want To Synchronize This Policy ?");
		if (delConfirm){
			document.form1.navigation.value = "synchronize";
			document.form1.submit();
		}
	}
	function activateAllMember (){
		var delConfirm = window.confirm ("Are You Sure Want To Activate All Member ?");
		if (delConfirm){
			document.form1.navigation.value = "activateallmember";
			
			document.form1.submit();
		}
	}
	function extendPolicy (){
		var delConfirm = window.confirm ("Are You Sure Want To Extend This Policy Periode ?");
		if (delConfirm){
			document.form1.navigation.value = "preextend";
			document.form1.submit();
		}
	}
	function renewPolicy (){
		var delConfirm = window.confirm ("Are You Sure Want To Renew This Policy ?");
		if (delConfirm){
			document.form1.navigation.value = "prerenewal";
			document.form1.submit();
		}
	}
	function terminatePolicy (){
		var delConfirm = window.confirm ("Are You Sure Want To Terminate This Policy ?");
		if (delConfirm){
			document.form1.navigation.value = "terminate";
			document.form1.submit();
		}
	}
	function kembali (){
		document.form1.navigation.value = "back";
		document.form1.submit();
	}
	function ubah (){
		document.form1.navigation.value = "update";
		document.form1.action = "policy-form";
		document.form1.method = "GET";
		document.form1.submit();
	}
	function quotation (){
		document.form1.navigation.value = "tambah";
		document.form1.action = "quotation-form";
		document.form1.method = "GET";
		document.form1.submit();
	}
	function downloadTC1(){
		document.form1.navigation.value = "downloadtc1";
		document.form1.action = "policy";
		document.form1.submit();
	}
	function downloadTC2(){
		document.form1.navigation.value = "downloadtc2";
		document.form1.action = "policy";
		document.form1.submit();
	}
	function downloadTC3(){
		document.form1.navigation.value = "downloadtc3";
		document.form1.action = "policy";
		document.form1.submit();
	}
	function addClausul (){
		document.form1.navigation.value = "tambah";
		document.form1.action = "policyclausul-form";
		document.form1.method = "GET";
		document.form1.submit();
	}
	function addBenefit (){
		document.form1.navigation.value = "tambah";
		document.form1.action = "policybenefit-form";
		document.form1.method = "GET";
		document.form1.submit();
	}
	function addFund (){
		document.form1.navigation.value = "addpolicyfund";
		document.form1.action = "fund-form";
		document.form1.method = "GET";
		document.form1.submit();
	}
</script>
