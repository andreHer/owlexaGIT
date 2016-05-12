<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>


<json:array items="${Clausuls}" var="clausul">
	<json:object>
		<json:property name="id" value="${clausul.clausulId}" />
		<json:property name="name" value="${clausul.clausulName}" />
		<json:property name="code" value="${clausul.clausulCode}" />
	
	</json:object>




</json:array>
