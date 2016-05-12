<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>

<json:array items="${ProviderMedicines}" var="providerMedicine">
	<json:object>
		<json:property name="id" value="${providerMedicine.medicineId.medicineId}" />
		<json:property name="name" value="${providerMedicine.medicineId.medicineName}" />
		<json:property name="kelas1" value="${providerMedicine.kelas1 }" />	
		<json:property name="kelas2" value="${providerMedicine.kelas2 }" />	
		<json:property name="kelas3" value="${providerMedicine.kelas3 }" />	
		<json:property name="vip" value="${providerMedicine.vip }" />	
		<json:property name="svip" value="${providerMedicine.svip }" />	
		<json:property name="rj" value="${providerMedicine.rj }" />	
		<json:property name="referencePrice" value="${providerMedicine.referencePrice }" />
	</json:object>
</json:array>
