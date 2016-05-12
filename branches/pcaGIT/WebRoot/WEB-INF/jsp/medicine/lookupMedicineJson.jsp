<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>

<json:array items="${Medicines}" var="medicine">
	<json:object>
		<json:property name="id" value="${medicine.medicineId}" />
		<json:property name="name" value="${medicine.medicineName}" />
		<json:property name="price" value="${medicine.medicinePrice }" />	
	</json:object>
</json:array>
