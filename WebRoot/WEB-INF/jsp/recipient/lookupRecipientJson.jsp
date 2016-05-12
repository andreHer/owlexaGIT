<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>


<json:array items="${RecipientDtos}" var="dto">
	<json:object>
		<json:property name="id" value="${dto.id}" />
		<json:property name="name" value="${ dto.nama}" />
		<json:property name="number" value="${dto.number }" />
		<json:property name="label" value="${dto.label }" />
	</json:object>
</json:array>
