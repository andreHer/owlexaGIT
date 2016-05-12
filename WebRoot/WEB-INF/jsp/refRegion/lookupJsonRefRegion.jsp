<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>


<json:array items="${Regions}" var="region">
	<json:object>
		<json:property name="id" value="${region.refRegionId}" />
		<json:property name="name" value="${region.regionName}" />
		<json:property name="number" value="${region.regionCode }" />
	
	</json:object>




</json:array>
