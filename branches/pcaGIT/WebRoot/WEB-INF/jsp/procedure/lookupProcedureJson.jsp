<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>


<json:array items="${Procedures}" var="procedure">
	<json:object>
		<json:property name="id" value="${procedure.procedureId}" />
		<json:property name="name" value="${procedure.procedureName}" />
		<json:property name="code" value="${procedure.procedureCode }" />			
	</json:object>
</json:array>
