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
			<input type="hidden" name="categoryId" value="<c:out value="${categoryId}" />" />

			<c:forEach items="${medicineCategoryList}" var="medicine">
				<div class="row-fluid">
					<div class="span5">
						<div class="block">				
							<div class="data-fluid">
								<button style="width: 100%;" class="btn btn-primary" onclick="javascript:searchMedicine(<c:out value="${medicine.medicineCategoryId}" />);"><c:out value="${medicine.medicineCategoryName}" /></button>
							</div>						
						</div>
					</div>
				</div>
			</c:forEach>
		</form>
	</body>
</html>

<script language="javascript">
		
	function searchMedicine(id){
		document.form1.action = "mobile";
		document.form1.method = "POST";
		document.form1.categoryId.value = id;
		document.form1.navigation.value = "searchmedicine";
		document.form1.submit();
	}
	

</script>