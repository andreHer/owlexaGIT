<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>


<json:array items="${Banks}" var="bank">
	<json:object>
		<json:property name="id" value="${bank.bankId}" />
		<json:property name="name" value="${bank.bankName}" />
		<json:property name="number" value="${bank.bankCode }" />
	
	</json:object>




</json:array>
