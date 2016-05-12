
package com.ametis.cms.dao;


import com.ametis.cms.datamodel.PolicyProviderDiagnosis;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.dao.DataAccessException;



// imports+ 

// imports- 


/**
 * PolicyProviderDiagnosisDao adalah dao Interface untuk operasi CRUD tabel policy_provider_diagnosis .
*/
public interface PolicyProviderDiagnosisDao 

// extends+ 

// extends- 

{	
	/*
	* Method create (PolicyProviderDiagnosis object) berfungsi untuk melakukan penambahan
	* sebuah object kedalam database
	* @param object adalah sebuah object yang ingin diubah
	* @return object baru hasil create dengan assigned primary key , exception jika gagal
	*/
	public PolicyProviderDiagnosis create (PolicyProviderDiagnosis object) throws DataAccessException ;
	/*
	* Method updatePolicyProviderDiagnosis (PolicyProviderDiagnosis object) berfungsi untuk melakukan perubahan terhadap
	* sebuah object yang terdapat didalam database
	* @param object adalah sebuah object yang ingin diubah
	* @return object hasil update apabila proses update berhasil dilakukan, dan exception jika gagal.
	*/
	public PolicyProviderDiagnosis update (PolicyProviderDiagnosis object) throws DataAccessException;
	
	/*
	* Method delete (PolicyProviderDiagnosis object) berfungsi untuk melakukan penghapusan terhadap
	* sebuah object yang terdapat didalam database
	* @param object adalah sebuah object yang ingin dihapus, isi dari object tersebut cukup dengan 
	*		 mengisi field-field primary key
	* @return no return value karena objeknya sendiri sudah dihapus - just for consistency. Again, 
	* exception if fail
	*
	*/
	
	public PolicyProviderDiagnosis delete (PolicyProviderDiagnosis object) throws DataAccessException;
	
	/*
	* Method get (PolicyProviderDiagnosis object) berfungsi untuk melakukan retrieval terhadap
	* sebuah object yang terdapat didalam database
	* @param object adalah sebuah object yang mempunyai ciri-ciri (example) sesuai dengan data yang diinginkan
	* @return Object yang dihasilkan dari proses retrieval, apabila object tidak ditemukan
	*	     maka method akan mengembalikan nilai "NULL"
	*/
	
	public PolicyProviderDiagnosis get (java.io.Serializable pkey) throws DataAccessException ;


/*
BASIC IMPLEMENTATION !! USE WITH CAUTION ! 
USE IT IF NO OTHER OPTION LEFT 
@return criteria
*/
	public Criteria getCriteria() throws Exception ;

/*
BASIC IMPLEMENTATION !! USE WITH CAUTION ! 
USE IT IF NO OTHER OPTION LEFT 
WARNING !! DONT" FORGET TO SET THE PROJECTION
example : detachedCriteria.setProjection(Property.forName("primary_key_field"));
@return DetachedCriteria
*
*/
	public DetachedCriteria getDetachedCriteria() throws Exception ;

// class+ 

// class- 

}
