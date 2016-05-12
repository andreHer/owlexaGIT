<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>


<json:array items="${Providers}" var="provider">
	<json:object>
		<json:property name="id" value="${provider.providerId.providerId}" />
		<json:property name="name" value="${ provider.providerId.providerName}" />
		<json:property name="number" value="${provider.providerId.providerCode }" />
	
	</json:object>




</json:array>
