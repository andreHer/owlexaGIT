<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>


<json:array items="${Providers}" var="provider">
	<json:object>
		<json:property name="id" value="${provider.providerId}" />
		<json:property name="name" value="${ provider.providerName}" />
		<json:property name="number" value="${provider.providerCode }" />
	
	</json:object>




</json:array>
