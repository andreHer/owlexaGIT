<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>


<json:array items="${Provinces}" var="province">
	<json:object>
		<json:property name="id" value="${province.id}" />
		<json:property name="name" value="${province.provinceName}" />
		<json:property name="number" value="${province.provinceCode }" />
		
	
	</json:object>




</json:array>
