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
		<form action="mobile" name="form1" method="post">
			<input type="hidden" name="navigation" value="<c:out value="${navigation}" />">
			<input type="hidden" name="login" value="<c:out value="${login}" />" />
			<input type="hidden" name="password" value="<c:out value="${password}" />" />
			<div class="row-fluid">
				<div class="span6">
					<div class="block">
						<div class="data-fluid">
							<div class="row-form">
	                            <div class="span3">Provider Name:</div>
	                            <div class="span5">
	                            <input type="text" value="<c:out value="${provider.providerName}" />" readonly="readonly"/>
	                            </div>
	                        </div>
	                        <div class="row-form">
	                            <div class="span3">Type :</div>
	                            <div class="span5">
	                            <input type="text" value="<c:out value="${provider.providerCategoryId.providerCategoryName}" />" readonly="readonly"/>
	                            </div>
	                        </div>
	                        <div class="row-form">
	                            <div class="span3">City:</div>
	                            <div class="span5">
	                            <input type="text" value="<c:out value="${provider.city}" />" readonly="readonly"/></div>
	                        </div>     
	                        <div class="row-form">
	                            <div class="span3">Province:</div>
	                            <div class="span5">
	                            <input type="text" value="<c:out value="${provider.province}" />" readonly="readonly"/></div>
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
										<th width="50%" style="background-color: #E9E9E9;">Daftar Poliklinik</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${poliklinikList}" var="poliklinik">
										<tr>
											<td><c:out value="${poliklinik.poliklinikId.poliklinikName}" /></td>										
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</form>
		<div class="row-fluid">
	            <div class="span4">                
					<div class="row-form">
	                	<button class="btn btn-primary" type="button" onclick="javascript:kembali();">Return To List</button>	                	
	                </div>
	            </div>	            
	        </div>
	</body>
</html>
<script language="javascript">
	function kembali(){
		document.form1.action = "mobile";
		document.form1.navigation.value = "gosearchprovider";
		document.form1.submit();
	}
</script>

