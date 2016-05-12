
package com.ametis.cms.service;



import com.ametis.cms.datamodel.GroupClaimUtilReport;
import com.ametis.cms.datamodel.ActionUser;

import java.sql.Date;
import java.util.Collection;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;



// imports+ 

// imports- 

/**
 * GroupClaimUtilReport is a servlet controller for group_claim_util_report Table.
 * All you have to do is to convert necessary data field to the named parameter
*/
public interface GroupClaimUtilReportService 

// extends+ 

// extends- 
{	
/*
	* Method create (GroupClaimUtilReport object) berfungsi untuk melakukan penambahan
	* sebuah object kedalam database
	* @param object adalah sebuah object yang ingin diubah
	* @param user adalah User pelaku create
	* @return object hasil kreasi,lengkap dengan assigned primary key, exception jika gagal
	*/
	public GroupClaimUtilReport create (GroupClaimUtilReport object,com.ametis.cms.datamodel.ActionUser user) throws Exception;

	/*
	* Method update (GroupClaimUtilReport object) berfungsi untuk melakukan perubahan terhadap
	* sebuah object yang terdapat didalam database
	* @param object adalah sebuah object yang ingin diubah
	* @param user adalah User pelaku aksi yang bersangkutan
	* @return object hasil update, exception jika gagal
	*/
	public GroupClaimUtilReport update (GroupClaimUtilReport object,ActionUser user) throws Exception;	
	

	/*
	* Method trash (Object pkey) berfungsi untuk melakukan penghapusan terhadap
	* sebuah object yang terdapat didalam database - benar2 HAPUS !!!
	* @param pkey adalah sebuah object yang merepresentasikan primary key dari 
	*	     tabel yang bersangkutan. Object tersebut dapat dalam bentuk single ID maupun composite ID
	* @return no return value karena objeknya sendiri sudah dihapus - just for consistency. Again, 
	* exception if failure occured
	* WARNING ! Unvalid value for the returned object, better not use it again in any 
	* place
	*/
	public GroupClaimUtilReport trash (java.io.Serializable pkey) throws Exception;
	
	/*
	* Method delete (GroupClaimUtilReport object) berfungsi untuk melakukan penghapusan terhadap
	* sebuah object yang terdapat didalam database
	* @param pkey adalah sebuah object yang merepresentasikan primary key dari 
	*	tabel yang bersangkutan. 
	* Object tersebut dapat dalam bentuk single ID maupun composite ID
	* @param user adalah User pelaku aksi yang bersangkutan
	* @return updated object, exception if failed
	*/
	
	public GroupClaimUtilReport delete (java.io.Serializable pkey,ActionUser user) throws Exception;


	/*
	* Method delete (GroupClaimUtilReport object) berfungsi untuk melakukan penghapusan terhadap
	* sebuah object yang terdapat didalam database
	* @param object adalah sebuah object yang ingin dihapus, isi dari object tersebut cukup dengan 
	*		 mengisi field-field primary key
	* @param user adalah User pelaku aksi yang bersangkutan
	* @return updated object, exception if failed
	*/
	
	public GroupClaimUtilReport delete (GroupClaimUtilReport object,ActionUser user) throws Exception;

// -- get section - carefull !

	
	public Collection<GroupClaimUtilReport> getGroupClaimUtilReport (Date date, int index, int offset) throws Exception;
	/*
	* Method get (Object pkey) berfungsi untuk melakukan retrieval terhadap
	* sebuah object yang terdapat didalam database
	* @param pkey adalah sebuah object yang merepresentasikan primary key dari 
	*	     tabel yang bersangkutan. Object tersebut dapat dalam bentuk single ID maupun composite ID
	* @return Object yang dihasilkan dari proses retrieval, apabila object tidak ditemukan
	*	     maka method akan mengembalikan nilai "NULL"
	*/
	public GroupClaimUtilReport get (java.io.Serializable pkey) throws Exception;


	/*
	* Method get (Object pkey) berfungsi untuk melakukan retrieval terhadap
	* sebuah object yang terdapat didalam database
	* @param pkey adalah sebuah object yang merepresentasikan primary key dari 
	*	     tabel yang bersangkutan. Object tersebut dapat dalam bentuk single ID maupun composite ID
	* @param required adalah array dari field-field yang dibutuhkan dari hibernate object
	* @return Object yang dihasilkan dari proses retrieval, apabila object tidak ditemukan
	*	     maka method akan mengembalikan nilai "NULL"
	*/
	
	public GroupClaimUtilReport get (java.io.Serializable pkey, String[] required) throws Exception;


// -- get section end here


// SEARCH SECTION - PALING RUMIT !!



// -- 1
	/* Method searching objects from database
	* @param likeColumn kolom like
	* @param likeParams param like
	* @param eqColumn kolom equal
	* @param eqParams param equal
	* @param index index first
	* @param offset number of object result 
	*/
	
	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, int index, int offset) throws Exception;

	/* Method searching objects from database
	* @param likeColumn kolom like
	* @param likeParams param like
	* @param eqColumn kolom equal
	* @param eqParams param equal
	* @param required for lazy initialization problem
	* @param index index first
	* @param offset number of object result 
	*/
	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams,String[] required, int index, int offset) 
			throws Exception;

	/* Method searching objects from database
	* @param likeColumn kolom like
	* @param likeParams param like
	* @param eqColumn kolom equal
	* @param eqParams param equal
	* @param index index first
	* @param offset number of object result 
	*/
	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, boolean asc, String columnOrder[],
			int index, int offset) throws Exception;

	/* Method searching objects from database
	* @param likeColumn kolom like
	* @param likeParams param like
	* @param eqColumn kolom equal
	* @param eqParams param equal
	* @param asc - shor order Ascending or descending
	* @param columnOrder - order by the column(s)
	* @param required for lazy initialization problem
	* @param index index first
	* @param offset number of object result 
	*/
	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, boolean asc, String columnOrder[],
			String[] required,
			int index, int offset) throws Exception;


	/* Method searching objects from database
	* @param likeColumn kolom like
	* @param likeParams param like
	* @param eqColumn kolom equal
	* @param eqParams param equal
	* @param asc - shor order Ascending or descending
	* @param columnOrder - order by the column(s)
	* @param index index first
	* @param offset number of object result 
	*/

	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, boolean asc, String columnOrder,
			int index, int offset) throws Exception;

	/* Method searching objects from database
	* @param likeColumn kolom like
	* @param likeParams param like
	* @param eqColumn kolom equal
	* @param eqParams param equal
	* @param asc  shor order Ascending or descending
	* @param columnOrder - order by the column(s)
	* @param required   required for lazy initialization problem
	* @param index index first
	* @param offset number of object result 
	*/
	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, boolean asc, String columnOrder,
			String[] required,
			int index, int offset) throws Exception;



	/* Method searching objects from database
	* @param likeColumn kolom like
	* @param likeParams param like
	* @param eqColumn kolom equal
	* @param eqParams param equal
	* @param index index first
	* @param offset number of object result 
	*/

	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, int index, int offset) throws Exception;
	
	/* Method searching objects from database
	* @param likeColumn kolom like
	* @param likeParams param like
	* @param eqColumn kolom equal
	* @param eqParams param equal
	* @param required   required for lazy initialization problem
	* @param index index first
	* @param offset number of object result 
	*/
	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams,
			String[] required,
			int index, int offset) throws Exception;

	/* Method searching objects from database
	* @param likeColumn kolom like
	* @param likeParams param like
	* @param eqColumn kolom equal
	* @param eqParams param equal
	* @param asc  shor order Ascending or descending
	* @param columnOrder - order by the column(s)
	* @param index index first
	* @param offset number of object result 
	*/

	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams,
			 boolean asc, String columnOrder[],
			int index, int offset) throws Exception;


	/* Method searching objects from database
	* @param likeColumn kolom like
	* @param likeParams param like
	* @param eqColumn kolom equal
	* @param eqParams param equal
	* @param asc  shor order Ascending or descending
	* @param columnOrder - order by the column(s)
	* @param required   required for lazy initialization problem
	* @param index index first
	* @param offset number of object result 
	*/
	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams,
			 boolean asc, String columnOrder[],
			 String[] required,
			int index, int offset) throws Exception;

	/* Method searching objects from database
	* @param likeColumn kolom like
	* @param likeParams param like
	* @param eqColumn kolom equal
	* @param eqParams param equal
	* @param asc  shor order Ascending or descending
	* @param columnOrder - order by the column(s)
	* @param index index first
	* @param offset number of object result 
	*/


	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams,
			 boolean asc, String columnOrder,
			int index, int offset) throws Exception;

	/* Method searching objects from database
	* @param likeColumn kolom like
	* @param likeParams param like
	* @param eqColumn kolom equal
	* @param eqParams param equal
	* @param asc  shor order Ascending or descending
	* @param columnOrder - order by the column(s)
	* @param required   required for lazy initialization problem
	* @param index index first
	* @param offset number of object result 
	*/
	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams,
			 boolean asc, String columnOrder,
			 String[] required,
			int index, int offset) throws Exception;



// -- 2 , between
	/* Method searching objects from database
	* @param likeColumn kolom like
	* @param likeParams param like
	* @param eqColumn kolom equal
	* @param eqParams param equal
	* @param btwnColumns between value column for searching
	* @param btwnParams1 between value 1st param for searching
	* @param btwnParams2 between value 2nd param for searching
	* @param index index first
	* @param offset number of object result 
	*/

	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, 
			String[] btwnColumns, Object[] btwnParams1,
			Object[] btwnParams2, 
			int index, int offset) throws Exception;


	/* Method searching objects from database
	* @param likeColumn kolom like
	* @param likeParams param like
	* @param eqColumn kolom equal
	* @param eqParams param equal
	* @param btwnColumns between value column for searching
	* @param btwnParams1 between value 1st param for searching
	* @param btwnParams2 between value 2nd param for searching
	* @param required   required for lazy initialization problem
	* @param index index first
	* @param offset number of object result 
	*/
	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, 
			String[] btwnColumns, Object[] btwnParams1,
			Object[] btwnParams2, 
			String[] required,
			int index, int offset) throws Exception;

	/* Method searching objects from database
	* @param likeColumn kolom like
	* @param likeParams param like
	* @param eqColumn kolom equal
	* @param eqParams param equal
	* @param btwnColumns between value column for searching
	* @param btwnParams1 between value 1st param for searching
	* @param btwnParams2 between value 2nd param for searching
	* @param asc  shor order Ascending or descending
	* @param columnOrder - order by the column(s)
	* @param index index first
	* @param offset number of object result 
	*/

	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, 
			String[] btwnColumns, Object[] btwnParams1,
			Object[] btwnParams2, 
			boolean asc, String columnOrder[],
			int index, int offset) throws Exception;


	/* Method searching objects from database
	* @param likeColumn kolom like
	* @param likeParams param like
	* @param eqColumn kolom equal
	* @param eqParams param equal
	* @param btwnColumns between value column for searching
	* @param btwnParams1 between value 1st param for searching
	* @param btwnParams2 between value 2nd param for searching
	* @param asc  shor order Ascending or descending
	* @param columnOrder - order by the column(s)
	* @param required   required for lazy initialization problem
	* @param index index first
	* @param offset number of object result 
	*/	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, 
			String[] btwnColumns, Object[] btwnParams1,
			Object[] btwnParams2, 
			boolean asc, String columnOrder[],
			String[] required,
			int index, int offset) throws Exception;


	/* Method searching objects from database
	* @param likeColumn kolom like
	* @param likeParams param like
	* @param eqColumn kolom equal
	* @param eqParams param equal
	* @param btwnColumns between value column for searching
	* @param btwnParams1 between value 1st param for searching
	* @param btwnParams2 between value 2nd param for searching
	* @param asc  shor order Ascending or descending
	* @param columnOrder - order by the column(s)
	* @param required   required for lazy initialization problem
	* @param index index first
	* @param offset number of object result 
	*/
	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, 
			String[] btwnColumns, Object[] btwnParams1,
			Object[] btwnParams2, 
			boolean asc, String columnOrder,
			int index, int offset) throws Exception;


	/* Method searching objects from database
	* @param likeColumn kolom like
	* @param likeParams param like
	* @param eqColumn kolom equal
	* @param eqParams param equal
	* @param btwnColumns between value column for searching
	* @param btwnParams1 between value 1st param for searching
	* @param btwnParams2 between value 2nd param for searching
	* @param asc  shor order Ascending or descending
	* @param columnOrder - order by the column(s)
	* @param required   required for lazy initialization problem
	* @param index index first
	* @param offset number of object result 
	*/
	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, 
			String[] btwnColumns, Object[] btwnParams1,
			Object[] btwnParams2, 
			boolean asc, String columnOrder,
			String[] required,
			int index, int offset) throws Exception;


 // -- 2b
 	/* Method searching objects from database
	* @param likeColumn kolom like
	* @param likeParams param like
	* @param eqColumn kolom equal
	* @param eqParams param equal
	* @param btwnColumns between value column for searching
	* @param btwnParams1 between value 1st param for searching
	* @param btwnParams2 between value 2nd param for searching
	* @param index index first
	* @param offset number of object result 
	*/
	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, 
			String btwnColumns, Object btwnParams1,
			Object btwnParams2, 
			int index, int offset) throws Exception;

	/* Method searching objects from database
	* @param likeColumn kolom like
	* @param likeParams param like
	* @param eqColumn kolom equal
	* @param eqParams param equal
	* @param btwnColumns between value column for searching
	* @param btwnParams1 between value 1st param for searching
	* @param btwnParams2 between value 2nd param for searching
	* @param asc  shor order Ascending or descending
	* @param index index first
	* @param offset number of object result 
	*/
	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, 
			String btwnColumns, Object btwnParams1,
			Object btwnParams2, 
			String[] required,
			int index, int offset) throws Exception;

	/* Method searching objects from database
	* @param likeColumn kolom like
	* @param likeParams param like
	* @param eqColumn kolom equal
	* @param eqParams param equal
	* @param btwnColumns between value column for searching
	* @param btwnParams1 between value 1st param for searching
	* @param btwnParams2 between value 2nd param for searching
	* @param asc  shor order Ascending or descending
	* @param columnOrder - order by the column(s)
	* @param index index first
	* @param offset number of object result 
	*/

	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, 
			String btwnColumns, Object btwnParams1,
			Object btwnParams2, 
			boolean asc, String columnOrder[],
			int index, int offset) throws Exception;

	/* Method searching objects from database
	* @param likeColumn kolom like
	* @param likeParams param like
	* @param eqColumn kolom equal
	* @param eqParams param equal
	* @param btwnColumns between value column for searching
	* @param btwnParams1 between value 1st param for searching
	* @param btwnParams2 between value 2nd param for searching
	* @param asc  shor order Ascending or descending
	* @param columnOrder - order by the column(s)
	* @param required   required for lazy initialization problem
	* @param index index first
	* @param offset number of object result 
	*/
	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, 
			String btwnColumns, Object btwnParams1,
			Object btwnParams2, 
			boolean asc, String columnOrder[],
			String[] required,
			int index, int offset) throws Exception;

	/* Method searching objects from database
	* @param likeColumn kolom like
	* @param likeParams param like
	* @param eqColumn kolom equal
	* @param eqParams param equal
	* @param btwnColumns between value column for searching
	* @param btwnParams1 between value 1st param for searching
	* @param btwnParams2 between value 2nd param for searching
	* @param asc  shor order Ascending or descending
	* @param columnOrder - order by the column(s)
	* @param index index first
	* @param offset number of object result 
	*/

	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, 
			String btwnColumns, Object btwnParams1,
			Object btwnParams2, 
			boolean asc, String columnOrder,
			int index, int offset) throws Exception;

	/* Method searching objects from database
	* @param likeColumn kolom like
	* @param likeParams param like
	* @param eqColumn kolom equal
	* @param eqParams param equal
	* @param btwnColumns between value column for searching
	* @param btwnParams1 between value 1st param for searching
	* @param btwnParams2 between value 2nd param for searching
	* @param asc  shor order Ascending or descending
	* @param columnOrder - order by the column(s)
	* @param required   required for lazy initialization problem
	* @param index index first
	* @param offset number of object result 
	*/
	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, 
			String btwnColumns, Object btwnParams1,
			Object btwnParams2, 
			boolean asc, String columnOrder,
			String[] required,
			int index, int offset) throws Exception;
	// req end

// -- search end

//-- get total

	/* Method searching number of objects from database
	* @param likeColumn kolom like
	* @param likeParams param like
	* @param eqColumn kolom equal
	* @param eqParams param equal
	*/
	public int getTotal (String[] likeQuery, Object[] likeObject,
		String[] eqColumns, Object[] eqParams) throws Exception;

/* Method searching objects from database
	* @param likeColumn kolom like
	* @param likeParams param like
	* @param eqColumn kolom equal
	* @param eqParams param equal
	* @param btwnColumns between value column for searching
	* @param btwnParams1 between value 1st param for searching
	* @param btwnParams2 between value 2nd param for searching
	*/	
	public int getTotal (String[] likeQuery, Object[] likeObject,
		String[] eqColumns, Object[] eqParams,
		String btwnColumns[], Object btwnParams1[],
			Object btwnParams2[] 
		) throws Exception;


	/* Method searching objects from database
	* @param likeColumn kolom like
	* @param likeParams param like
	* @param eqColumn kolom equal
	* @param eqParams param equal
	*/

	public int getTotal (String likeQuery, Object likeObject,
		String eqColumns, Object eqParams) throws Exception;
	
	
	/* Method searching objects from database
	* @param likeColumn kolom like
	* @param likeParams param like
	* @param eqColumn kolom equal
	* @param eqParams param equal
	* @param btwnColumns between value column for searching
	* @param btwnParams1 between value 1st param for searching
	* @param btwnParams2 between value 2nd param for searching
	*/	
	public int getTotal (String[] likeQuery, Object[] likeObject,
		String[] eqColumns, Object[] eqParams,
		String btwnColumns, Object btwnParams1,
			Object btwnParams2 
		) throws Exception;

//------------------------------------------------------------------
	/*
	*	getting total number of current objects in database
	*/
	public int getTotal () throws Exception;
//-- get total end



//---------------------------------------------------
	/*
	getting all object
	@param required - for lazy init problem
	*/
	public Collection getAll (String[] required) throws Exception;
	
	/*
	getting all object
	*/

	public Collection getAll () throws Exception;
//-------------------------------------------------


// unique Result
	/*
	 * getting single unique object
	 * @param eqColumns column for retrieve
	 * @param eqParams value for column
	 *	@param required - for lazy init problem
	*/
	public GroupClaimUtilReport searchUnique (String eqColumns, Object eqParams, String[] required)
		throws Exception;
	/*
	 * getting single unique object
	 * @param eqColumns column for retrieve
	 * @param eqParams value for column
	*/
	public GroupClaimUtilReport searchUnique (String eqColumns, Object eqParams)
		throws Exception;


/**
BASIC IMPLEMENTATION !! USE WITH CAUTION ! 
USE IT IF NO OTHER OPTIONS LEFT 
@return criteria
*/
	public Criteria getCriteria() throws Exception ;

/**
BASIC IMPLEMENTATION !! USE WITH CAUTION ! 
USE IT IF NO OTHER OPTIONS LEFT 
@return detached criteria
*/
	public DetachedCriteria getDetachedCriteria() throws Exception;
	public Collection<GroupClaimUtilReport> getGroupClaimUtilReport (int index, int offset) throws Exception;
	public Date getLastReportDate () throws Exception;


// class+ 

// class- 
}
