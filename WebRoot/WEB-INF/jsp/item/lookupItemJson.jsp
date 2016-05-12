<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<json:array items="${Items}" var="item">
	<json:object>
		<json:property name="id" value="${item.itemId}" />
		<json:property name="name" value="${ item.itemName}" />
		<json:property name="code" value="${ item.itemCode}" />		
	</json:object>
</json:array>
