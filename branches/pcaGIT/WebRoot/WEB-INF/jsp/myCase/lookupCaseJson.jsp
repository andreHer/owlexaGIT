<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>


<json:array items="${Cases}" var="case">
	<json:object>
		<json:property name="id" value="${case.caseId}" />
		<json:property name="number" value="${case.caseNumber }" />		
		<json:property name="admissionDate" value="${case.caseStartTime}" />
		<json:property name="dischargeDate" value="${case.caseEndTime}" />
	</json:object>




</json:array>
