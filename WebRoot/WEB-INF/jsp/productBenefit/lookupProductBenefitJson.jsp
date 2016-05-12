<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>


<json:array items="${ProductBenefitList}" var="benefit">
	<json:object>
		<json:property name="id" value="${benefit.productBenefitId}" />
		<json:property name="name" value="${ benefit.itemCategoryId.itemCategoryName}" />
		<json:property name="limit" value="${ benefit.benefitLimit}" />
	
	</json:object>




</json:array>
