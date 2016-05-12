
package com.ametis.cms.service;



import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;

import com.ametis.cms.datamodel.ActionResult;
import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberElectronicCard;
import com.ametis.cms.datamodel.MemberIdAndPolicyId;



// imports+ 

// imports- 

/**
 * Member is a servlet controller for member Table.
 * All you have to do is to convert necessary data field to the named parameter
*/
public interface MemberService 

// extends+ 

// extends- 
{	
/*
	* Method create (Member object) berfungsi untuk melakukan penambahan
	* sebuah object kedalam database
	* @param object adalah sebuah object yang ingin diubah
	* @param user adalah User pelaku create
	* @return object hasil kreasi,lengkap dengan assigned primary key, exception jika gagal
	*/
	
	public ActionResult activate (Integer memberId, ActionUser actionUser) throws Exception;
	public ActionResult activateMutation (Integer memberId,String newPolicyNumber,String additionalInfo,Date effectiveDate, Date expireDate,ActionUser actionUser) throws Exception;
	public ActionResult activateUpgrade (Integer memberId, ActionUser actionUser) throws Exception;
	public ActionResult activateSynchronize (Integer memberId, ActionUser actionUser) throws Exception;
	public ActionResult activateRenewal (Integer memberId, ActionUser actionUser) throws Exception;
	public ActionResult block (Integer memberId,String reason,Date suspendStart,Date suspendEnd, ActionUser actionUser) throws Exception;
	public ActionResult unblock (Integer memberId,String reason, ActionUser actionUser) throws Exception;
	
	public boolean updateExpiredMember () throws Exception;
	
	public ActionResult terminate (Integer memberId, Date terminateDate,ActionUser actionUser) throws Exception;
	
	public Collection<Object> activateAllPendingMember(Integer memberGroupId, ActionUser actionUser) throws Exception;
	public Collection<Object> activateAllPolicyMember(Integer policyId, ActionUser actionUser) throws Exception ;
	
	public Collection<Object> getAllPendingEmployee() throws Exception;
	public Collection<Object> getAllPendingMember() throws Exception;
	
	public Collection<Object> getAllPendingUpgradeEmployee() throws Exception;
	public Collection<Object> getAllPendingUpgradeMember() throws Exception;
	
	public Collection<Object> getAllPendingRenewEmployee() throws Exception;
	public Collection<Object> getAllPendingRenewMember() throws Exception;
	
	public Collection<Object> getAllPendingSynchronizeEmployee(Integer policyId) throws Exception;
	public Collection<Object> getAllPendingSynchronizeMember(Integer policyId) throws Exception;
	
	public Collection<Integer> getAutoRenewalNomineeList () throws Exception;
	public boolean activateSuspendRelease(ActionUser user) throws Exception ;
	public boolean activateAdvanceRenewal (ActionUser user) throws Exception;
	
	public ActionResult authorizeMemberCard (Integer memberId, String cardNumber) throws Exception;
	
	
	public ActionResult terminate (String memberNumbers, Date terminateDate,ActionUser actionUser) throws Exception;
	
	public Member create (Member object,ActionUser actionUser) throws Exception;
	public Member create (Member object,String productCode,ActionUser actionUser) throws Exception;
	public Member getMember (String memberNumber, String bday) throws Exception;
	public Member getMember (String memberNumber) throws Exception;
	public Member getMemberByPolicyNumber (String memberNumber, String policyNumber) throws Exception;
	public Member getMemberByCardNumber (String memberNumber, String cardNumber) throws Exception;
	public Member getMemberByCardNumber (String cardNumber) throws Exception;
	
	public int getTotalRegisteredMember(Integer clientId, Date start, Date end) throws Exception;
	
	public Collection<Member> getMemberList (Integer memberGroupId) throws Exception;
	
	public Member getMember (String memberNumber, Integer clientId, Integer memberGroupId) throws Exception;
	
	public int[] getYearlyMemberGrowth (int year) throws Exception;
	public int[] getYearlyBranchMemberGrowth (int year, Integer clientId) throws Exception;
	public int[] getYearlyGroupMemberGrowth (int year, Integer memberGroupId) throws Exception;
	
	public double[] getYearlyMemberPremiumGrowth (int year) throws Exception;
	public double[] getYearlyBranchMemberPremiumGrowth (int year, Integer clientId) throws Exception;
	
	public Collection<Object[]> getAllMember (Integer status) throws Exception; 

	/*
	* Method update (Member object) berfungsi untuk melakukan perubahan terhadap
	* sebuah object yang terdapat didalam database
	* @param object adalah sebuah object yang ingin diubah
	* @param user adalah User pelaku aksi yang bersangkutan
	* @return object hasil update, exception jika gagal
	*/
	public Member update (Member object,ActionUser actionUser) throws Exception;	
	

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
	public Member trash (java.io.Serializable pkey) throws Exception;
	
	/*
	* Method delete (Member object) berfungsi untuk melakukan penghapusan terhadap
	* sebuah object yang terdapat didalam database
	* @param pkey adalah sebuah object yang merepresentasikan primary key dari 
	*	tabel yang bersangkutan. 
	* Object tersebut dapat dalam bentuk single ID maupun composite ID
	* @param user adalah User pelaku aksi yang bersangkutan
	* @return updated object, exception if failed
	*/
	
	public Member delete (java.io.Serializable pkey,ActionUser actionUser) throws Exception;


	/*
	* Method delete (Member object) berfungsi untuk melakukan penghapusan terhadap
	* sebuah object yang terdapat didalam database
	* @param object adalah sebuah object yang ingin dihapus, isi dari object tersebut cukup dengan 
	*		 mengisi field-field primary key
	* @param user adalah User pelaku aksi yang bersangkutan
	* @return updated object, exception if failed
	*/
	
	public Member delete (Member object,ActionUser actionUser) throws Exception;

// -- get section - carefull !

	
	/*
	* Method get (Object pkey) berfungsi untuk melakukan retrieval terhadap
	* sebuah object yang terdapat didalam database
	* @param pkey adalah sebuah object yang merepresentasikan primary key dari 
	*	     tabel yang bersangkutan. Object tersebut dapat dalam bentuk single ID maupun composite ID
	* @return Object yang dihasilkan dari proses retrieval, apabila object tidak ditemukan
	*	     maka method akan mengembalikan nilai "NULL"
	*/
	public Member get (java.io.Serializable pkey) throws Exception;
	


	/*
	* Method get (Object pkey) berfungsi untuk melakukan retrieval terhadap
	* sebuah object yang terdapat didalam database
	* @param pkey adalah sebuah object yang merepresentasikan primary key dari 
	*	     tabel yang bersangkutan. Object tersebut dapat dalam bentuk single ID maupun composite ID
	* @param required adalah array dari field-field yang dibutuhkan dari hibernate object
	* @return Object yang dihasilkan dari proses retrieval, apabila object tidak ditemukan
	*	     maka method akan mengembalikan nilai "NULL"
	*/
	
	public Member get (java.io.Serializable pkey, String[] required) throws Exception;
	public Collection<Member> searchMemberByName (String keyword,String category,int index, int offset) throws Exception;
	public Collection<Member> searchDependent (Integer parentId,int index, int offset) throws Exception;
	public Collection<Member> searchMemberByClient (String keyword, String category, Integer clientId, int index, int offset) throws Exception;
	public Collection<Member> searchMemberByClientAndGroup (String keyword, String category, Integer clientId,
			Integer groupId, int index, int offset) throws Exception;
	
	


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
	* @param required for lazy initialization problem
	* @param columnOrder kolom order
	* @param order untuk asc desc
	* @param index index first
	* @param offset number of object result 
	*/
	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams,String[] required, 
			String columnOrder, boolean order, int index, int offset) 
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
	public Member searchUnique (String eqColumns, Object eqParams, String[] required)
		throws Exception;
	/*
	 * getting single unique object
	 * @param eqColumns column for retrieve
	 * @param eqParams value for column
	*/
	public Member searchUnique (String eqColumns, Object eqParams)
		throws Exception;
	
	public Member searchUnique (String[] eqColumns, Object[] eqParams,int index,int offset)
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


	public Member getParentMember (String dependantCode, Integer memberGroupId,Integer clientId) throws Exception;
	public String synchronizeSuspendReactive() throws Exception;
	public Collection<Member> getParentList (Integer memberGroupId) throws Exception;
	public Collection<Member> getDependentList (Integer memberId) throws Exception;
	public Collection<Member> getParentListByAlphabet (Integer memberGroupId, String alphabet) throws Exception;
	
	public Collection<Member> searchMember(String q) throws Exception;
	public Collection<Member> searchMemberAndGroup(String q, Integer groupId) throws Exception;
	public Collection<Member> searchMemberAndClient(String q, Integer clientId) throws Exception;
	public Collection<Member> searchMemberClientPolicy(String firstName, Integer clientId, Integer memberGroupId, Integer currentId)
			throws Exception;
	public Collection<Member> searchMemberByDobClientGroup(String[] eqColumns, String[] eqFirstName,  Object[] oFirstName, Object[] eqParams,
			String[] required) throws Exception;
	public Collection<Member> searchMemberByDob(String[] eqColumns, Object[] eqParams,
			String[] required) throws Exception;
	
	public Collection<Member> searchMemberList(String[] eqColumns, Object[] eqParams, Object[] eqParams2,
			String[] required) throws Exception;
	
	
	
	public void populateUpdateMemberProduct(ActionUser actionUser,Integer memberId) throws Exception;
	
	public void activateAndUpdate (ActionUser actionUser, Integer memberId) throws Exception;
// class+ 
	public String generateCardNumber(String string, String clientCode, Logger logger, ArrayList<String> errorList);
	public Member getEmployee(String memberId, String policyNumber, String clientCode)
			throws Exception;

// class- 
}
