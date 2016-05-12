
package com.ametis.cms.service;



import java.util.Collection;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.Provider;
import com.ametis.cms.datamodel.User;



// imports+ 

// imports- 

/**
 * User is a servlet controller for user Table.
 * All you have to do is to convert necessary data field to the named parameter
*/
public interface UserService 

// extends+ 

// extends- 
{	
/*
	* Method create (User object) berfungsi untuk melakukan penambahan
	* sebuah object kedalam database
	* @param object adalah sebuah object yang ingin diubah
	* @param user adalah User pelaku create
	* @return object hasil kreasi,lengkap dengan assigned primary key, exception jika gagal
	*/
	public User create (User object,ActionUser actionUser) throws Exception;

	/*
	* Method update (User object) berfungsi untuk melakukan perubahan terhadap
	* sebuah object yang terdapat didalam database
	* @param object adalah sebuah object yang ingin diubah
	* @param user adalah User pelaku aksi yang bersangkutan
	* @return object hasil update, exception jika gagal
	*/
	public User update (User object,ActionUser actionUser) throws Exception;	
	

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
	public User trash (java.io.Serializable pkey) throws Exception;
	
	/*
	* Method delete (User object) berfungsi untuk melakukan penghapusan terhadap
	* sebuah object yang terdapat didalam database
	* @param pkey adalah sebuah object yang merepresentasikan primary key dari 
	*	tabel yang bersangkutan. 
	* Object tersebut dapat dalam bentuk single ID maupun composite ID
	* @param user adalah User pelaku aksi yang bersangkutan
	* @return updated object, exception if failed
	*/
	
	public User delete (java.io.Serializable pkey,ActionUser actionUser) throws Exception;


	/*
	* Method delete (User object) berfungsi untuk melakukan penghapusan terhadap
	* sebuah object yang terdapat didalam database
	* @param object adalah sebuah object yang ingin dihapus, isi dari object tersebut cukup dengan 
	*		 mengisi field-field primary key
	* @param user adalah User pelaku aksi yang bersangkutan
	* @return updated object, exception if failed
	*/
	
	public User delete (User object,ActionUser actionUser) throws Exception;

// -- get section - carefull !

	
	/*
	* Method get (Object pkey) berfungsi untuk melakukan retrieval terhadap
	* sebuah object yang terdapat didalam database
	* @param pkey adalah sebuah object yang merepresentasikan primary key dari 
	*	     tabel yang bersangkutan. Object tersebut dapat dalam bentuk single ID maupun composite ID
	* @return Object yang dihasilkan dari proses retrieval, apabila object tidak ditemukan
	*	     maka method akan mengembalikan nilai "NULL"
	*/
	public User get (java.io.Serializable pkey) throws Exception;

	public User getUser (String username) throws Exception;
	
	public User getUserOnlyOne (String username, String[] eqColumns, Object[] eqParams,String[] required) throws Exception;
	
	public boolean changePassword(Integer userId, String newPassword, ActionUser actionUser) throws Exception;
	/*
	* Method get (Object pkey) berfungsi untuk melakukan retrieval terhadap
	* sebuah object yang terdapat didalam database
	* @param pkey adalah sebuah object yang merepresentasikan primary key dari 
	*	     tabel yang bersangkutan. Object tersebut dapat dalam bentuk single ID maupun composite ID
	* @param required adalah array dari field-field yang dibutuhkan dari hibernate object
	* @return Object yang dihasilkan dari proses retrieval, apabila object tidak ditemukan
	*	     maka method akan mengembalikan nilai "NULL"
	*/
	
	public User get (java.io.Serializable pkey, String[] required) throws Exception;


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
	public User searchUnique (String eqColumns, Object eqParams, String[] required)
		throws Exception;
	/*
	 * getting single unique object
	 * @param eqColumns column for retrieve
	 * @param eqParams value for column
	*/
	public User searchUnique (String eqColumns, Object eqParams)
		throws Exception;
	
	public User searchUnique (String[] eqColumns, Object[] eqParams, int index, int offset)
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


	public boolean changePassword (String username, String password, String newPassword, ActionUser actionUser) throws Exception;
	public boolean changePassword (Integer userId, String password, String newPassword, ActionUser actionUser) throws Exception;
	
	public boolean block (String username, ActionUser actionUser) throws Exception;
	public boolean activate (String username, ActionUser actionUser) throws Exception;
	public boolean block (Integer userId, ActionUser actionUser) throws Exception;
	public boolean activate (Integer userId, ActionUser actionUser) throws Exception;
	
	public boolean login (String username, String password, String session, String ipAddress) throws Exception;
	public User loginMobile (String username, String password, String session, String ipAddress) throws Exception;
	
	//Add by aju on 20150722, for mobile application
	public boolean loginMobileApplication (String username, String password, String session, String ipAddress) throws Exception;
	
	//Add by aju on 20151012, for mobile application support ge ce em
	public boolean loginMobileApplicationGcmSupport (String username, String password, String session, String ipAddress, String mobileDeviceId) throws Exception;
	
	public boolean logout (String username, String session) throws Exception;
	
	public Collection<User> getUserByRole (Integer roleId) throws Exception;
// class+ 

// class- 
}
