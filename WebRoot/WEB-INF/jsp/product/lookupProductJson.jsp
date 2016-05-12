<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>


<json:array items="${Products}" var="product">
	<json:object>
		<json:property name="id" value="${product.productId}" />
		<json:property name="name" value="${ product.productName}" />
		<json:property name="code" value="${product.productCode }" />	
	</json:object>
</json:array>
