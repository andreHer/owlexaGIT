<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>


<json:array items="${Providers}" var="provider">
	<json:object>
		<json:property name="providerId" value="${provider.providerId}" />
		<json:property name="providerName" value="${ provider.providerName}" />
		<json:property name="providerCode" value="${provider.providerCode }" />
		<json:property name="providerCategoryId" value="${provider.providerCategoryId.providerCategoryName }" />
		<json:property name="statusId" value="${provider.statusId.status }" />
		<json:property name="address" value="${provider.address }" />
		<json:property name="telephone" value="${provider.telephone }" />
		<json:property name="city" value="${provider.city }" />
	    <json:property name="longitude" value="${provider.longitude }" />
	    <json:property name="latitude" value="${provider.latitude }" />
	</json:object>




</json:array>
