<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>

<json:array items="${Providers}" var="provider">
	<json:object>
		<json:property name="id" value="${provider.providerDoctorId}" />
		<json:property name="name" value="${provider.doctorName}" />
	</json:object>
</json:array>
