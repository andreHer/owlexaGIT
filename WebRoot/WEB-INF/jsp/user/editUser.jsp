<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<script type="text/javascript" src="scripts/owlexa.function.js"></script>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
  	<c:choose>
  		<c:when test="${empty navigation}">
  			<td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Register User</h3></td>
  		</c:when>
  		<c:when test="${navigation eq 'update'}">
  			<td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Update User</h3></td>
  		</c:when>
  		<c:otherwise>	
  			<td nowrap="nowrap"><h3><img src="images/h3Arrow.gif"  border="0">&nbsp;Update User</h3></td>
  		</c:otherwise>
  	</c:choose>
        
      <td width="100%"><img src="images/blank.gif"  height="1" width="1"></td>
    </tr>
  </tbody>
</table>

<form action="user-form" method="POST"  name="form1" id="form_layout">

<input type="hidden" name="navigation" value="">

<spring:bind path="userForm.institutionId">
<input type="hidden" name="<c:out value="${status.expression}" />" value="<c:out value="${status.value}" />" id="institutionId">
</spring:bind>

<table class="tabForm" border="0" cellpadding="0" cellspacing="0" width="100%">
	<tbody>
		


<%
String alert = (String) request.getAttribute("alert");
if (alert != null && !alert.trim().equals("")) {%>
	<div id="warning">
		<c:out value="${alert}"></c:out>
	</div>
	<%}%>


	<spring:bind path="userForm.userId">
		<input type="hidden" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" >
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>




			<tr>
				<td class="dataLabel"> </td>				
				<td class="dataField" colspan="3">
				</td>			    
			</tr>

			<tr>
				<td class="dataLabel"> Username : </td>				
			

		<td class="dataField">
		<spring:bind path="userForm.username">
			<input type="text" onkeypress="javascript:return notContainSpace(this,event)" size="35" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" />	
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
			<%
				String usernameWarning = (String) request.getAttribute("usernameWarning");
				if (usernameWarning != null && !usernameWarning.trim().equals("")) {
			%>
			<div id="warning">
				<c:out value="${usernameWarning}"></c:out>
			</div>
			<%
				}
			%>
		</spring:bind>
		</td>
		<td class="dataLabel"> User Type : </td>				
		<td class="dataField">
			<spring:bind path="userForm.userType">
				<select name="<c:out value="${status.expression}" />" id="tipe">
					<c:forEach items="${userBeans}" var="userType">
						<option value="${userType.userTypeId}" <c:if test="${userType.userTypeId eq status.value}">selected</c:if> ><c:out value="${userType.userTypeName}" /></option>
					</c:forEach>		
				</select>
			</spring:bind>
		</td>
	</tr>

			<tr>
				<td class="dataLabel"> First Name : </td>				
			

			<td class="dataField">
			<spring:bind path="userForm.firstName">
				<input type="text" size="35" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" />	
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
			    <td class="dataLabel">Role : </td>		
		<td class="dataField">
		<spring:bind path="userForm.roleId.roleId">
			<select name="<c:out value="${status.expression}" />" id="role">
				<c:forEach items="${roleBeans}" var="role">
					<option value="<c:out value="${role.roleId}"/>" class="tipe_<c:out value="${role.userTypeId.userTypeId}"/>" >
						<c:out value="${role.roleName}" />
					</option>
				</c:forEach>
			</select>
			<div id="fredcaption">
				<c:out value="${status.errorMessage}" />
			</div>
		</spring:bind>			
		</td>
	
	</tr>
	


			

			<tr>
				<td class="dataLabel"> Telephone : </td>				
			

			<td class="dataField">
				<spring:bind path="userForm.telephone">
					<input type="text" onkeypress="javascript:return isNumberKey(this,event)" size="35" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" />
					<div id="fredcaption">
						<c:out value="${status.errorMessage}" />
					</div>
				</spring:bind>
<!-- 				<spring:bind path="userForm.telephoneExt"> -->
<!-- 					<input type="text" size="11" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" />	 -->
<!-- 					<div id="fredcaption"> -->
<!-- 						<c:out value="${status.errorMessage}" /> -->
<!-- 					</div> -->
<!-- 				</spring:bind> -->
			</td>
			<div id="institution">
		  	    <td class="dataLabel" id="labelInstitution"> Institution : </td>
				<td class="dataField" id="fieldInstitution">
					<spring:bind path="userForm.institutionName">
						<input type="text" name="<c:out value="${status.expression}"/>" id="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" readonly="readonly"/>
						<input type="button" name="PILIH" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" value="Lookup" onClick="javascript:lookupInstitution()"/>
						<div id="fredcaption">
							<c:out value="${status.errorMessage}" />
						</div>
					</spring:bind>	
				</td>		
				</div>
	</tr>
	
			<tr>
				<td class="dataLabel"> Email : </td>				
			<td class="dataField">
			<spring:bind path="userForm.email">
		<input type="text" size="35" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" />	
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
			  <td class="dataLabel"> Mobile Phone : </td>				
			

			<td class="dataField">
			<spring:bind path="userForm.mobilePhone">
		<input type="text" onkeypress="javascript:return isNumberKey(this,event)" size="35" name="<c:out value="${status.expression}"/>" value="<c:out value="${status.value}" />" />	
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>

	</tr>
			<tr>
				<td class="dataLabel"> Description : </td>				
			

		    <td class="dataField" colspan="2">
			<spring:bind path="userForm.description">
			<textarea cols="60" rows="8" name="<c:out value="${status.expression}"/>"><c:out value="${status.value}" /></textarea>
				<div id="fredcaption">
					<c:out value="${status.errorMessage}" />
				</div>
			</spring:bind>
		</td>
	    <td class="dataLabel"></td>
	    <td class="dataField"></td>
	</tr>
			<tr>
				<td class="dataLabel"> </td>				
				<td class="dataField" colspan="3">
				</td>			    
			</tr>
	</tbody>
</table>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  <tbody>
    <tr>
      <td style="padding-top: 2px; padding-left: 1px;">
	    <input type="hidden" name="notyet" value="">
        <input title="Save [Alt+Shift+S]" accesskey="S" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:simpan()" name="Save" value=" Save " type="submit">
        <input title="Cancel [Alt+Shift+C]" accesskey="C" class="ui-button ui-widget ui-state-default ui-corner-all ui-state-focus" onClick="javascript:cancel()" name="Cancel" value=" Cancel " type="button">         
	  </td>
      <td align="right"></td>
    </tr>
  </tbody>
</table>
</form>

<script language="javascript">

	$(document).ready(function(){
		
		$("#tipe").change(function(){
			//hidden institution untuk TPA
			if ($("#tipe").val() != 2){//usertypeid TPA
				document.getElementById('labelInstitution').style.visibility = 'visible';
		    	document.getElementById('fieldInstitution').style.visibility = 'visible';
		    	}
		    else{
		    	document.getElementById('labelInstitution').style.visibility = 'hidden';
		    	document.getElementById('fieldInstitution').style.visibility = 'hidden';
			}
			
			//untuk perubahan combo box role berdasarkan usertype
			if($(this).data('options') == undefined){
		    /*Taking an array of all options-2 and kind of embedding it on the select1*/
		   	 $(this).data('options',$('#role option').clone());
		    } 
			var id = $(this).val();
			var options = $(this).data('options').filter('.tipe_' + id);
			$('#role').html(options);
		});
		PlaySubmit();
		
	});
	
	function PlaySubmit(){
			//hidden institution untuk TPA
			if ($("#tipe").val() != 2){//usertypeid TPA
				document.getElementById('labelInstitution').style.visibility = 'visible';
		    	document.getElementById('fieldInstitution').style.visibility = 'visible';
		    	}
		    else{
		    	document.getElementById('labelInstitution').style.visibility = 'hidden';
		    	document.getElementById('fieldInstitution').style.visibility = 'hidden';
			}
			
			//untuk perubahan combo box role berdasarkan usertype
			if($('#tipe').data('options') == undefined){
		    /*Taking an array of all options-2 and kind of embedding it on the select1*/
		   	 $('#tipe').data('options',$('#role option').clone());
		    } 
			var id = $('#tipe').val();
			var options = $('#tipe').data('options').filter('.tipe_' + id);
			$('#role').html(options);
		}
	
// 	function toggleFields() {
// 	    if ($("#tipe").val() != 2)
// 	        $("#institution").show();
// 	    else
// 	        $("#institution").hide();
// 	}
	
	function simpan (){

		document.form1.method = "POST";
		document.form1.action = "user-form";
		document.form1.submit();
	}
	function kembali (){
		document.form1.navigation.value = "back";
		document.form1.action = "user";
		document.form1.submit();
	}
	function cancel (){
		document.form1.navigation.value = "cancel";
		document.form1.action = "user";
		document.form1.submit();

	}
	function setMember (idx,nama){
		var institutionId = document.getElementById("institutionId");
		institutionId.value = idx;
		
		var institutionName = document.getElementById("institutionName");
		institutionName.value = nama;
	}
	function setMemberGroup (idx,nama){
		var institutionId = document.getElementById("institutionId");
		institutionId.value = idx;
		
		var institutionName = document.getElementById("institutionName");
		institutionName.value = nama;
	}
	function setProvider (idx,nama){
		var institutionId = document.getElementById("institutionId");
		institutionId.value = idx;
		
		var institutionName = document.getElementById("institutionName");
		institutionName.value = nama;
	}
	function setBroker (idx,nama){
		var institutionId = document.getElementById("institutionId");
		institutionId.value = idx;
		
		var institutionName = document.getElementById("institutionName");
		institutionName.value = nama;
	}
	function setClient (idx,nama){
		var institutionId = document.getElementById("institutionId");
		institutionId.value = idx;
		
		var institutionName = document.getElementById("institutionName");
		institutionName.value = nama;
	}
	function lookupInstitution (){
		var tipe = document.getElementById("tipe").value;
		
		
		if (tipe == 1){
			window.open ("membergroup?navigation=lookup&url=batchclaim-form","Search","width=700, height=550, menubar=no, status=no, toolbar=no, scrollbars=yes, resizable=yes,screenX=200,screenY=100");			
		}
		else if (tipe == 5){
			window.open ("broker?navigation=lookup&url=batchclaim-form","Search","width=700, height=550, menubar=no, status=no, toolbar=no, scrollbars=yes, resizable=yes,screenX=200,screenY=100");
		}
		else if (tipe == 7){
			window.open ("member?navigation=lookup&url=batchclaim-form","Search","width=700, height=550, menubar=no, status=no, toolbar=no, scrollbars=yes, resizable=yes,screenX=200,screenY=100");
		}
		else if (tipe == 3){
			window.open ("client?navigation=lookup&url=batchclaim-form","Search","width=700, height=550, menubar=no, status=no, toolbar=no, scrollbars=yes, resizable=yes,screenX=200,screenY=100");	
		}
		else if (tipe == 4){
			window.open ("provider?navigation=lookup&url=batchclaim-form","Search","width=700, height=550, menubar=no, status=no, toolbar=no, scrollbars=yes, resizable=yes,screenX=200,screenY=100");	
			
		}
	}

</script>