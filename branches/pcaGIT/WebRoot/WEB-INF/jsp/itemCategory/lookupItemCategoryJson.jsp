<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>


<json:array items="${itemCategoryList}" var="item">
	<json:object>
		<json:property name="id" value="${item.itemCategoryId}" />
		<json:property name="name" value="${ item.itemCategoryName}" />
		<json:property name="code" value="${item.itemCategoryCode }" />
	
	</json:object>




</json:array>
