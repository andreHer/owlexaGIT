<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>


<json:array items="${MemberGroups}" var="mg">
	<json:object>
		<json:property name="id" value="${mg.memberGroupId}" />
		<json:property name="name" value="${ mg.groupName}" />
		<json:property name="number" value="${mg.memberGroupCode }" />	
	</json:object>




</json:array>
