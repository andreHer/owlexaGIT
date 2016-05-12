<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>


<json:array items="${Brokers}" var="broker">
	<json:object>
		<json:property name="id" value="${broker.brokerId}" />
		<json:property name="name" value="${broker.brokerName}" />
		<json:property name="number" value="${broker.brokerCode }" />
	
	</json:object>




</json:array>
