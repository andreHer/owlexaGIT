<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>


<json:array items="${Members}" var="member">
	<json:object>
		<json:property name="id" value="${member.memberId}" />
		<json:property name="name" value="${ member.firstName}" />
		<json:property name="number" value="${member.customerPolicyNumber }" />
		<json:property name="client" value="${member.clientName}" />
		<json:property name="group" value="${member.groupName}" />
		<json:property name="parent" value="${member.parentName}" />
		<json:property name="parentNumber" value="${member.parentNumber}" />
		<json:property name="department" value="${member.department}" />
		<json:property name="jobPosition" value="${member.jobPosition}" />
		<json:property name="expireDate" value="${member.expireDate}" />
		<json:property name="status" value="${member.status}" />
		<json:property name="birthday" value="${member.birthday}" />
	</json:object>




</json:array>
