<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>


<json:array items="${Countries}" var="country">
	<json:object>
		<json:property name="id" value="${country.id}" />
		<json:property name="name" value="${ country.countryName}" />
		<json:property name="number" value="${country.countryCode }" />
	
	</json:object>




</json:array>
