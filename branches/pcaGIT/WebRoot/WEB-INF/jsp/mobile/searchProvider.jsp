<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="spring" uri="/WEB-INF/spring.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>

<html>
	<head>
		<link href="css/mobile/stylesheets.css" rel="stylesheet" type="text/css" />
		<link href="css/mobile/mystyles.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
		<form action="mobile" name="form1" method="POST">
			<input type="hidden" name="navigation" value="gosearchprovider" />
			<div class="row-fluid">
	            <div class="span6">
	                <div class="block">                                   
	                    <div class="data-fluid">                        
	                        <div class="row-form">
	                            <div class="span3">Province :</div>
	                            <div class="span5">
	                            	<select name="provinceId">
	                            		<option value="">-- SELECT ONE --</option>
		                            	<c:forEach items="${provinceList}" var="province">
		                            		<option value="<c:out value="${province.id}" />" 
		                            		<c:if test="${province.id eq provinceId }">selected</c:if>><c:out value="${province.provinceName}" /></option>	                            		
		                            	</c:forEach>
	                            	</select>
	                            </div>
	                        </div>
	                        <div class="row-form">
	                            <div class="span3">City :</div>
	                            <div class="span5">
	                            	<select name="cityId">
	                            		<option value="">-- SELECT ONE --</option>
		                            	<c:forEach items="${cityList}" var="city">
		                            		<option value="<c:out value="${city.id}" />" 
		                            		<c:if test="${city.id eq cityId }">selected</c:if>><c:out value="${city.cityName}" /></option>	                            		
		                            	</c:forEach>
	                            	</select>
	                            </div>
	                        </div>
	                        <div class="row-form">
	                            <div class="span3">Provider Category :</div>
	                            <div class="span5">
	                            	<select name="providerCategoryId">
	                            		<option value="">-- SELECT ONE --</option>
		                            	<c:forEach items="${providerCategoryList}" var="category">
		                            		<option value="<c:out value="${category.providerCategoryId}" />" 
		                            		<c:if test="${category.providerCategoryId eq providerCategoryId }">selected</c:if>><c:out value="${category.providerCategoryName}" /></option>
		                            		
		                            	</c:forEach>
	                            	</select>
	                            </div>
	                        </div>      
	                    </div>
	                </div>
	            </div>            
	        </div>
	        <div class="row-fluid">
		            <div class="span6">                
						<div class="row-form">
		                	<button class="btn btn-primary" type="button" onclick="javascript:searchProvider();">Search</button>	                	
		                </div>
		            </div>	            
		        </div>
			<br />
			<div class="row-fluid">
				<div class="span5">
					<div class="block">				
						<div class="data-fluid">
							<table cellpadding="0" cellspacing="0" width="100%" class="table">
								<thead>
									<tr>
										<th width="25%" style="background-color: #E9E9E9;">Provider Name</th>
										<th width="25%" style="background-color: #E9E9E9;">City</th>
										<th width="25%" style="background-color: #E9E9E9;">Province</th>
										<th width="25%" style="background-color: #E9E9E9;">Type</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${providerList}" var="provider">
										<tr>
											<td><a href="mobile?navigation=detailprovider&providerId=<c:out value="${provider.providerId}" />"><c:out value="${provider.providerName}" /></a></td>
											<td><c:out value="${provider.city}" /></td>
											<td><c:out value="${provider.province}" /></td>
											<td><c:out value="${provider.providerCategoryId.providerCategoryName}" /></td>
										</tr>		
									</c:forEach>	
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</form>
	</body>
</html>

<script language="javascript">
		
	function searchProvider(){
		document.form1.action = "mobile";
		document.form1.method = "POST";
		document.form1.navigation.value = "gosearchprovider";
		document.form1.submit();
	}
	

</script>