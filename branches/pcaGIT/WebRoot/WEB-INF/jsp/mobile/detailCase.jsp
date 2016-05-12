<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>


<html>
	<head>
		<link href="css/mobile/stylesheets.css" rel="stylesheet" type="text/css" />
		<script type='text/javascript' src='dwr/interface/AJAXCaseService.js'></script>
		<script type='text/javascript' src='dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
	</head>
	<body>
		<form name="form1" action="mobile" method="post">
			<input type="hidden" name="navigation" value="<c:out value="${navigation}" />" />
			<input type="hidden" name="login" value="<c:out value="${login}" />" />
			<input type="hidden" name="password" value="<c:out value="${password}" />" />
			<input type="hidden" name="caseId" value="<c:out value="${caseId }" />"/>
			
			<div class="row-fluid">
				<div class="span6">
					<div class="block">
						<div class="data-fluid">
							<div class="row-form">
	                            <div class="span3">Name:</div>
	                            <div class="span5"><input type="text" name=memberName"" value="<c:out value="${myCase.memberId.firstName}" />"/></div>
	                        </div>
	                        <div class="row-form">
	                            <div class="span3">Company:</div>
	                            <div class="span5"><input type="text" value="<c:out value="${myCase.memberId.memberGroupId.groupName }" />" readonly="readonly"/></div>
	                        </div>
	                        <div class="row-form">
	                            <div class="span3">Status / Expire Date:</div>
	                            <div class="span5"><input type="text" value="<c:out value="${status}" /> / <c:out value="${myCase.memberId.expireDate}" />" readonly="readonly"/></div>
	                        </div>     
	                        <div class="row-form">
	                            <div class="span3">Diagnosis:</div>
	                            <div class="span5"><input type="text" value="<c:out value="${myCase.diagnosis1Id.diagnosisName}" />" readonly="readonly"/></div>
	                        </div>   
						</div>
					</div>
				</div>
			</div>
			<div class="row-fluid">
				<div class="span4">
					<div class="block">
						<div class="data-fluid">
							<table cellpadding="0" cellspacing="0" width="100%" class="table">
								<thead>
									<tr>
										<th width="25%" style="background-color: #E9E9E9;">Item</th>
										<th width="5%" style="background-color: #E9E9E9;">Amount</th>
										<th width="10%" style="background-color: #E9E9E9;">Charge</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${caseItemList}" var="caseItem">
										<tr>
											<td><c:out value="${caseItem.itemId.itemName}" /></td>
											<td style="text-align: right;"><fmt:formatNumber><c:out value="${caseItem.usageAmount}" /></fmt:formatNumber></td>
											<td style="text-align: right;"><fmt:formatNumber><c:out value="${caseItem.usageValue}" /></fmt:formatNumber></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
			<div class="row-fluid">
		          <div class="span4">                
						<div class="row-form">
							<c:if test="${myCase.caseStatusId.caseStatusId eq 9}">
			                	<button class="btn btn-primary" type="button" onclick="javascript:closeCase();">Close</button>
			                	<button class="btn btn-primary" type="button" onClick="javascript:addItem();">Cost Factor</button>
			                	<button class="btn btn-primary" type="button" onClick="javascript:addMedicine();">Medicine</button>
			                	<button class="btn btn-primary" type="button" onClick="javascript:addProcedure();">Procedure</button>
			                	<button class="btn btn-primary" type="button" onClick="javascript:addDiagnostic();">Diagnostic</button>
		                	</c:if>
		                	<c:if test="${myCase.caseStatusId.caseStatusId eq 15}">
		                		<button class="btn btn-primary" type="button" onclick="javascript:referCase();">Refer Case</button>
		                	</c:if>
		                </div>
		          </div>	            
		   	</div>
	    </form>
	</body>
</html>
<script language="javascript">

	function closeCase (){
		var delConfirm = window.confirm ("Are You Sure Want To Close This Case ?");		
		if (delConfirm){
			document.form1.navigation.value = "closecase";
			document.form1.submit();
		}
	}
	function referCase(){
		var delConfirm = window.confirm ("Are You Sure Want To Refer This Case ?");		
		if (delConfirm){
			document.form1.navigation.value = "refercase";
			document.form1.submit();
		}
	}
	function addItem (){
		document.form1.navigation.value = "additem";
		document.form1.submit();
	}
	function addMedicine (){		
		document.form1.navigation.value = "addmedicine";
		document.form1.submit();		
	}
	function addProcedure (){		
		document.form1.navigation.value = "addprocedure";
		document.form1.submit();		
	}
	function addDiagnostic (){		
		document.form1.navigation.value = "adddiagnostic";
		document.form1.submit();		
	}
</script>