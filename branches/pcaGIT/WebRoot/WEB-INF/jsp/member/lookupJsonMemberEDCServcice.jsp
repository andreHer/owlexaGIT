<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>


<%-- <json:array items="${EDCServices}" var="edcservice"> --%>
	<json:object>
		<json:property name="actionCode" value="${EDCServices.actionCode}" />
		<json:property name="additionalMessage" value="${EDCServices.additionalMessage}" />
		<json:property name="referenceNumber" value="${EDCServices.referenceNumber}" />
		<json:property name="reason" value="${EDCServices.reason}" />
		<json:property name="result" value="${EDCServices.result}" />
	</json:object>
<%-- </json:array> --%>

