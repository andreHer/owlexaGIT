<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>


<json:array items="${PolicyList}" var="policy">
	<json:object>
		<json:property name="id" value="${policy.policyId}" />
		<json:property name="name" value="${policy.clientId.clientName}" />
		<json:property name="number" value="${policy.policyNumber }" />
		<json:property name="groupName" value="${policy.memberGroupId.groupName }" />
		<json:property name="clientId" value="${policy.clientId.clientId }" />
	</json:object>




</json:array>
