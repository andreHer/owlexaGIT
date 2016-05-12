
package com.ametis.cms.service;



import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.Case;
import com.ametis.cms.datamodel.Provider;

import com.ametis.cms.datamodel.CostContainment;
import com.ametis.cms.util.dao.DaoSupportUtil;



// imports+ 

// imports- 

/**
 * Case is a servlet controller for case Table.
 * All you have to do is to convert necessary data field to the named parameter
*/
public interface CaseService 

// extends+ 

// extends- 
{	
/*
	* Method create (Case object) berfungsi untuk melakukan penambahan
	* sebuah object kedalam database
	* @param object adalah sebuah object yang ingin diubah
	* @param user adalah User pelaku create
	* @return object hasil kreasi,lengkap dengan assigned primary key, exception jika gagal
	*/
	public Case create (Case object,ActionUser actionUser) throws Exception;
	public Case createCaseItem (Integer claimId, Collection claimItem, Double pembulatan,ActionUser actionUser) throws Exception;
	public boolean reversalCase (Integer caseId, String note , ActionUser user) throws Exception;
	public boolean voidReferCase (Integer caseId, String note, ActionUser user) throws Exception;
	public boolean voidCase(Integer caseId, String node, ActionUser user) throws Exception;
	public boolean cancelDischargeCase (Integer caseId, String node, ActionUser user) throws Exception;
	public boolean assignCase (Integer caseId,Integer userId, String note, ActionUser user) throws Exception;
	public Integer updateActiveCaseLoS () throws Exception;
	
	public boolean pendingCase(Integer caseId,Integer status,Integer assigneeId, String note, ActionUser user) throws Exception;


	/*
	* Method approve (Case object) berfungsi untuk melakukan penambahan
	* sebuah object kedalam database
	* @param object adalah sebuah object yang ingin diubah
	* @param user adalah User pelaku create
	* @return object hasil kreasi,lengkap dengan assigned primary key, exception jika gagal
	*/
	public boolean approve (Serializable object,ActionUser actionUser) throws Exception;
	public boolean approveExGratia (Serializable object,String approvalNote,byte[] exGratiaDocument,String fileName,ActionUser actionUser) throws Exception;
	
	/*
	* Method create (Case object) berfungsi untuk melakukan penambahan
	* sebuah object kedalam database
	* @param object adalah sebuah object yang ingin diubah
	* @param user adalah User pelaku create
	* @return object hasil kreasi,lengkap dengan assigned primary key, exception jika gagal
	*/
	
	public boolean reject (Serializable object, String rejectNote,ActionUser actionUser) throws Exception;
	
	/*
	* Method update (Case object) berfungsi untuk melakukan perubahan terhadap
	* sebuah object yang terdapat didalam database
	* @param object adalah sebuah object yang ingin diubah
	* @param user adalah User pelaku aksi yang bersangkutan
	* @return object hasil update, exception jika gagal
	*/
	public Case update (Case object,ActionUser actionUser) throws Exception;	
	

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
	public Case trash (java.io.Serializable pkey) throws Exception;
	
	/*
	* Method delete (Case object) berfungsi untuk melakukan penghapusan terhadap
	* sebuah object yang terdapat didalam database
	* @param pkey adalah sebuah object yang merepresentasikan primary key dari 
	*	tabel yang bersangkutan. 
	* Object tersebut dapat dalam bentuk single ID maupun composite ID
	* @param user adalah User pelaku aksi yang bersangkutan
	* @return updated object, exception if failed
	*/
	
	public Case delete (java.io.Serializable pkey,ActionUser actionUser) throws Exception;


	/*
	* Method delete (Case object) berfungsi untuk melakukan penghapusan terhadap
	* sebuah object yang terdapat didalam database
	* @param object adalah sebuah object yang ingin dihapus, isi dari object tersebut cukup dengan 
	*		 mengisi field-field primary key
	* @param user adalah User pelaku aksi yang bersangkutan
	* @return updated object, exception if failed
	*/
	
	public Case delete (Case object,ActionUser actionUser) throws Exception;

// -- get section - carefull !

	
	/*
	* Method get (Object pkey) berfungsi untuk melakukan retrieval terhadap
	* sebuah object yang terdapat didalam database
	* @param pkey adalah sebuah object yang merepresentasikan primary key dari 
	*	     tabel yang bersangkutan. Object tersebut dapat dalam bentuk single ID maupun composite ID
	* @return Object yang dihasilkan dari proses retrieval, apabila object tidak ditemukan
	*	     maka method akan mengembalikan nilai "NULL"
	*/
	public Case get (java.io.Serializable pkey) throws Exception;


	/*
	* Method get (Object pkey) berfungsi untuk melakukan retrieval terhadap
	* sebuah object yang terdapat didalam database
	* @param pkey adalah sebuah object yang merepresentasikan primary key dari 
	*	     tabel yang bersangkutan. Object tersebut dapat dalam bentuk single ID maupun composite ID
	* @param required adalah array dari field-field yang dibutuhkan dari hibernate object
	* @return Object yang dihasilkan dari proses retrieval, apabila object tidak ditemukan
	*	     maka method akan mengembalikan nilai "NULL"
	*/
	
	public Case get (java.io.Serializable pkey, String[] required) throws Exception;
	
	public boolean close (java.io.Serializable pkey, java.sql.Timestamp closingTime,ActionUser actionUser) throws Exception;
	public boolean closeCase (Case theCase, ActionUser user) throws Exception;
	public boolean renew (java.io.Serializable pkey, Case memberCase, ActionUser actionUser) throws Exception;
	public boolean transfer (java.io.Serializable pkey, Case transferedCase, ActionUser actionUser) throws Exception;
	public boolean referCase (Case theCase, ActionUser actionUser) throws Exception;
	public boolean closeAndRefer (Case theCase, ActionUser user) throws Exception; 


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
	public Collection<Case> searchCase ( String[] eqCase,  Object[] oCase, String[] sStatus,  Object[] oStatus, 
			String[] required) throws Exception;
	
	
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
	
	//begin aulia
	public Collection searchMultiCase (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, /*String[] inColumns*/  Object[] inParamsStatus, Object[] inParamsCat, boolean asc, 
			String columnOrder, String[] required,
			int index, int offset) throws Exception;
	
	public Collection searchMultiCase (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams,  /*String[] inColumns*/  Object[] inParamsStatus, Object[] inParamsCat, boolean asc,
			String columnOrder[], String[] required,
			int index, int offset) throws Exception;
	//end
	
	public Collection searchCaseInvestigation (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams,  Object[] inParamsValue,boolean asc, String columnOrder,
			String[] required,
			int index, int offset) throws Exception;
	
	public int getTotalCaseInvestigation (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, Object[] inParamsValue) throws Exception;
	
	public Collection searchCaseManualRegistation (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, Object[] inParamValues, boolean asc, String columnOrder,
			String[] required,
			int index, int offset) throws Exception;
	
	public int getTotalCaseRegisterManual (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, Object[] inParamValues) throws Exception;

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

	public Collection<Object[]> searchCaseReference (String clientName, String groupName,
			String serviceName, String providerName, String diagnosisName, Integer state, Integer province,
			Integer city,
			Date minimumDate, Date maxDate, String dateBy, int index, int offset) throws Exception;
	
	
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
	
	public int getTotalMultiCase (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, Object[] inParamsStatus, Object[] inParamsCat) throws Exception;

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
	
	public int getTotalCaseReference (String clientName, String groupName,
			String serviceName, String providerName, String diagnosisName, Integer state, Integer province,
			Integer city,
			Date minimumDate, Date maxDate, String dateBy ) throws Exception;
	
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
	public Case searchUnique (String eqColumns, Object eqParams, String[] required)
		throws Exception;
	/*
	 * getting single unique object
	 * @param eqColumns column for retrieve
	 * @param eqParams value for column
	*/
	public Case searchUnique (String eqColumns, Object eqParams)
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
	public int getTotalNewCase () throws Exception;
	//Add by aju on 20150804, for COB :D
	public int getTotalNewCOBCase () throws Exception;
	public int getTotalExpireCase () throws Exception;
	public int getTotalClosingCase () throws Exception;
	public int getTotalMonitorCase() throws Exception ;
	public int getTotalCaseInvestigation() throws Exception;
	public Collection<Case> getCaseList (String[] sStatus,  Object[] oStatus, 
			String[] required) throws Exception;
	
	//Add 20150909 by FV0, untuk requirement provider 6, provider report- mencari jumlah transaksi yang terjadi di rumah sakit selama edc tersebut rusak
	public int getTotalProviderCaseManualRegistration(Integer providerId, Integer edcTerminalId) throws Exception;

// class+ 

// class- 
}
