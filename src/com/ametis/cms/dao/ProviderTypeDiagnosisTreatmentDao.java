
package com.ametis.cms.dao;


import com.ametis.cms.datamodel.ProviderTypeDiagnosisTreatment;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.dao.DataAccessException;



// imports+ 

// imports- 

/**
 * ProviderTypeDiagnosisTreatmentDao adalah dao Interface untuk operasi CRUD tabel provider_type_diagnosis_treatment .
*/
public interface ProviderTypeDiagnosisTreatmentDao 

// extends+ 

// extends- 
{	
	/*
	* Method create (ProviderTypeDiagnosisTreatment object) berfungsi untuk melakukan penambahan
	* sebuah object kedalam database
	* @param object adalah sebuah object yang ingin diubah
	* @return object baru hasil create dengan assigned primary key , exception jika gagal
	*/
	public ProviderTypeDiagnosisTreatment create (ProviderTypeDiagnosisTreatment object) throws DataAccessException ;
	/*
	* Method updateProviderTypeDiagnosisTreatment (ProviderTypeDiagnosisTreatment object) berfungsi untuk melakukan perubahan terhadap
	* sebuah object yang terdapat didalam database
	* @param object adalah sebuah object yang ingin diubah
	* @return object hasil update apabila proses update berhasil dilakukan, dan exception jika gagal.
	*/
	public ProviderTypeDiagnosisTreatment update (ProviderTypeDiagnosisTreatment object) throws DataAccessException;
	
	/*
	* Method delete (ProviderTypeDiagnosisTreatment object) berfungsi untuk melakukan penghapusan terhadap
	* sebuah object yang terdapat didalam database
	* @param object adalah sebuah object yang ingin dihapus, isi dari object tersebut cukup dengan 
	*		 mengisi field-field primary key
	* @return no return value karena objeknya sendiri sudah dihapus - just for consistency. Again, 
	* exception if fail
	*
	*/
	
	public ProviderTypeDiagnosisTreatment delete (ProviderTypeDiagnosisTreatment object) throws DataAccessException;
	
	/*
	* Method get (ProviderTypeDiagnosisTreatment object) berfungsi untuk melakukan retrieval terhadap
	* sebuah object yang terdapat didalam database
	* @param object adalah sebuah object yang mempunyai ciri-ciri (example) sesuai dengan data yang diinginkan
	* @return Object yang dihasilkan dari proses retrieval, apabila object tidak ditemukan
	*	     maka method akan mengembalikan nilai "NULL"
	*/
	
	public ProviderTypeDiagnosisTreatment get (java.io.Serializable pkey) throws DataAccessException ;


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
