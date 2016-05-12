<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>


<json:array items="${Diagnosiss}" var="diagnosis">
	<json:object>
		<json:property name="id" value="${diagnosis.diagnosisId}" />
		<json:property name="name" value="${ diagnosis.description}" />
		<json:property name="number" value="${diagnosis.diagnosisCode }" />
	
	</json:object>




</json:array>
