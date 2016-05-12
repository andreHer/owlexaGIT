<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>


<json:array items="${clientList}" var="client">
	<json:object>
		<json:property name="id" value="${client.clientId}" />
		<json:property name="name" value="${ client.clientName}" />
		<json:property name="number" value="${client.clientNumber }" />
	
	</json:object>




</json:array>
