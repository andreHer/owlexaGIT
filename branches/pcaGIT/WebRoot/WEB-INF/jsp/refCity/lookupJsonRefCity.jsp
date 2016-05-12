<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>


<json:array items="${Cities}" var="city">
	<json:object>
		<json:property name="id" value="${city.id}" />
		<json:property name="name" value="${ city.cityName}" />
		<json:property name="number" value="${city.cityCode }" />
		<json:property name="number" value="${city.cityPhoneCode }" />
	
	</json:object>




</json:array>
