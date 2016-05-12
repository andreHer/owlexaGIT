<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>


<json:array items="${SetList}" var="diagnosis">
	<json:object>
		<json:property name="id" value="${diagnosis.diagnosisSetId}" />
		<json:property name="name" value="${ diagnosis.diagnosisSetName}" />
		<json:property name="number" value="${diagnosis.diagnosisSetCode }" />
	
	</json:object>




</json:array>
