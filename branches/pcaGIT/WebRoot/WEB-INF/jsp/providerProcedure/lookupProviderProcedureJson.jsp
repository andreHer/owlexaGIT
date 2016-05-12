<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>

<json:array items="${ProviderProcedures}" var="providerProcedure">
	<json:object>
		<json:property name="id" value="${providerProcedure.procedureId.procedureId}" />
		<json:property name="name" value="${providerProcedure.procedureId.procedureName}" />
		<json:property name="c1" value="${providerProcedure.c1 }" />	
		<json:property name="c2" value="${providerProcedure.c2 }" />	
		<json:property name="c3" value="${providerProcedure.c3 }" />	
		<json:property name="vip" value="${providerProcedure.vip }" />	
		<json:property name="svip" value="${providerProcedure.svip }" />	
		<json:property name="rj" value="${providerProcedure.rj }" />	
		<json:property name="code" value="${providerProcedure.procedureId.procedureCode }" />	
	</json:object>
</json:array>
