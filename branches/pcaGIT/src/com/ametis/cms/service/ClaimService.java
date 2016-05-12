
package com.ametis.cms.service;



import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;

import com.ametis.cms.datamodel.Action;
import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.BatchClaim;
import com.ametis.cms.datamodel.Case;
import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.datamodel.ClaimItem;
import com.ametis.cms.datamodel.Holiday;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.datamodel.Provider;
import com.ametis.cms.dto.ClaimDto;
import com.ametis.cms.dto.PaymentReportDetail;
import com.ametis.cms.util.dao.DaoSupportUtil;



// imports+ 

// imports- 

/**
 * Claim is a servlet controller for claim Table.
 * All you have to do is to convert necessary data field to the named parameter
*/
public interface ClaimService 

// extends+ 

// extends- 
{	
/*
	* Method create (Claim object) berfungsi untuk melakukan penambahan
	* sebuah object kedalam database
	* @param object adalah sebuah object yang ingin diubah
	* @param user adalah User pelaku create
	* @return object hasil kreasi,lengkap dengan assigned primary key, exception jika gagal
	*/
	
	public boolean disposition(Claim object, ActionUser actionUser) throws Exception ;
	public Collection<Object> getPayableUploadedClaimList (Integer paymentId) throws Exception;
	public void recordHistory(Claim object,String actionType,String note, ActionUser actionUser) throws Exception ;
	public Claim create (Claim object,ActionUser actionUser) throws Exception;
	public Claim createClaimRKI (Claim object,Collection<ClaimItem> claimItems,ActionUser actionUser) throws Exception;
	public String approveClaim(Integer claimId, ActionUser user) throws Exception ;
	public String approveClaimBenefit(Integer claimId,
            Collection<ClaimItem> claimItems, ActionUser user) throws Exception;
	public Integer addTotalItem (Integer claimId) throws Exception;
	public String approveExGratia(Integer claimId, String remarks, ActionUser user) throws Exception ;
	public boolean attachSwipeClaim (Integer batchClaimId, Integer[] swipeClaimId, ActionUser actionUser) throws Exception;
	
	public Collection<Claim> getUnpaidMemberReimbursementClaim(Integer memberId) throws Exception;
	public int getTotalUnpaidMemberReimbursementClaim(Integer memberId) throws Exception;
	public int getTotalUnInvoicedClaim(Integer clientId, Integer claimStatus, Integer claimType,
			Integer caseCategoryId) throws Exception;
	
	public double getTotalBenefitUsage (Member member, Integer caseCategoryId, Date startDate, Date endDate) throws Exception;

	public boolean changeMemberClaim (Integer claimId, Integer memberId, ActionUser actionUser) throws Exception;
	
	public boolean changeClaimCategory (Integer claimId, Integer caseCategoryId, ActionUser actionUser) throws Exception;
	
	public ClaimItem updateClaimItem (ClaimItem claimItem, ActionUser actionUser) throws Exception;
	public boolean deleteClaimITem (ClaimItem claimItem, ActionUser actionUser) throws Exception;
	public Integer reduceTotalItem (Integer claimId) throws Exception;
	public double getTotalBenefitUsage (MemberGroup memberGroup, Date startDate, Date endDate) throws Exception;
	public double getTotalBenefitUsage (Member member, Date startDate, Date endDate) throws Exception;
	public double getTotalBenefitUsage (Provider provider, Date startDate, Date endDate) throws Exception;
	public Claim updateCaseCalculationClaim(Claim object, ActionUser actionUser) throws Exception;
	
	public double getTotalBenefitUsage (Integer caseCategoryId, Member memberId, Integer status) throws Exception;
	
	public boolean approve (Claim object, ActionUser actionUser) throws Exception;
	//Add 20150416 by FVO, for open claim From Tentative Status to Open Status when do cost calculation
	public boolean openClaimItems (Claim object, ActionUser actionUser) throws Exception;
	public Claim updateClaim(Claim object, ActionUser actionUser) throws Exception;
	
	public Claim extract (Integer claimId, BatchClaim batchClaim, ActionUser actionUser) throws Exception;

	public String approveMedicalItem (Claim object, ClaimItem claimItem, ActionUser actionUser) throws Exception;
	
	public String approveMedicalBulk (Integer claimId, Collection<ClaimItem> claimItems, ActionUser user) throws Exception;
	
	public String approveCheckItemBulk (Integer claimId, Collection<ClaimItem> claimItems, ActionUser user) throws Exception;
	
	public String approveCheckItemBulkEDC (Integer claimId, Collection<ClaimItem> claimItems, ActionUser user) throws Exception;
	
	//add 20150420 by FVO, generate Surat Jalan
	public Collection<Object[]> generateClaimDownloadRecap(Integer paymentId) throws Exception;
	
	public boolean reject (Claim object, ActionUser actionUser) throws Exception;
	public Claim openClaim (Serializable claimId, ActionUser actionUser) throws Exception;
	public Claim completeClaim (Serializable claimId, ActionUser actionUser) throws Exception;
	public int verifyBulk (Serializable batchClaimId, ActionUser actionUser ) throws Exception;
	public long getTotalOpenClaim (Serializable batchClaimId) throws Exception;
	
	//Add by aju on 20150804, for COB :D
	public int getTotalClaimDaily (int status) throws Exception;//adq
	
	double getTotalUsageValue(String memberNumber, Date startDate, Date endDate) throws Exception;//adq
	
	public int getTotalCOBOpenClaim () throws Exception;
	
	public Integer getNextClaim (Serializable batchClaimId) throws Exception;
	
	public Claim refreshClaim (Serializable claimId, ActionUser actionUser) throws Exception;
	
	public void convertClaimFromBatch(Serializable batchClaimId, Integer fromCaseType,Integer toCaseType ) throws Exception;
	
	/*
	 * method pay berfungsi untuk melakukan pembayaran terhadap claim tersebut
	 */
	public boolean pay (Claim object, ActionUser actionUser) throws Exception;
	
	/*
	 * method Finalize berfungsi untuk menandakan bahwa claim tersebut telah
	 * melewati fase claim checking sehingga untuk tahapan selanjutnya adalah
	 * pembayaran
	 */
	public boolean finalize (Claim object, ActionUser actionUser) throws Exception;
	
	public boolean finalizeCorrection (Claim object, Collection<ClaimItem> items, ActionUser actionUser) throws Exception;
	
	
	public boolean voidClaim (Integer claimId,String traceNumber, ActionUser actionUser) throws Exception;
	public boolean voidClaim (Integer claimId,String traceNumber, String notes, ActionUser actionUser) throws Exception;
	public boolean cancelRejectClaim (Integer claimId, String notes, ActionUser actionUser) throws Exception;

	public boolean synchronizeClaim (Integer claimId, ActionUser actionUser) throws Exception;
	
	/*
	* Method create (Claim object) berfungsi untuk melakukan penambahan
	* sebuah object kedalam database
	* @param object adalah sebuah object yang ingin diubah
	* @param user adalah User pelaku create
	* @return object hasil kreasi,lengkap dengan assigned primary key, exception jika gagal
	*/
	public Claim createClaimItem (Claim object, Collection claimItem, Double pembulatan,ActionUser actionUser) throws Exception;
	public Claim createClaimItemEDC (Claim object, Collection claimItem, Double pembulatan,ActionUser actionUser) throws Exception;
	public Claim createClaimItem (Integer claimId, Collection claimItem, Double pembulatan,ActionUser actionUser) throws Exception;
	/*
	* Method update (Claim object) berfungsi untuk melakukan perubahan terhadap
	* sebuah object yang terdapat didalam database
	* @param object adalah sebuah object yang ingin diubah
	* @param user adalah User pelaku aksi yang bersangkutan
	* @return object hasil update, exception jika gagal
	*/
	public Claim update (Claim object,ActionUser actionUser) throws Exception;	
	

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
	public Claim trash (java.io.Serializable pkey) throws Exception;
	
	/*
	* Method delete (Claim object) berfungsi untuk melakukan penghapusan terhadap
	* sebuah object yang terdapat didalam database
	* @param pkey adalah sebuah object yang merepresentasikan primary key dari 
	*	tabel yang bersangkutan. 
	* Object tersebut dapat dalam bentuk single ID maupun composite ID
	* @param user adalah User pelaku aksi yang bersangkutan
	* @return updated object, exception if failed
	*/
	
	public Claim delete (java.io.Serializable pkey,ActionUser actionUser) throws Exception;


	/*
	* Method delete (Claim object) berfungsi untuk melakukan penghapusan terhadap
	* sebuah object yang terdapat didalam database
	* @param object adalah sebuah object yang ingin dihapus, isi dari object tersebut cukup dengan 
	*		 mengisi field-field primary key
	* @param user adalah User pelaku aksi yang bersangkutan
	* @return updated object, exception if failed
	*/
	
	
	public Claim delete (Claim object,ActionUser actionUser) throws Exception;
	
	
// -- get section - carefull !

	
	/*
	* Method get (Object pkey) berfungsi untuk melakukan retrieval terhadap
	* sebuah object yang terdapat didalam database
	* @param pkey adalah sebuah object yang merepresentasikan primary key dari 
	*	     tabel yang bersangkutan. Object tersebut dapat dalam bentuk single ID maupun composite ID
	* @return Object yang dihasilkan dari proses retrieval, apabila object tidak ditemukan
	*	     maka method akan mengembalikan nilai "NULL"
	*/
	public Claim get (java.io.Serializable pkey) throws Exception;


	/*
	* Method get (Object pkey) berfungsi untuk melakukan retrieval terhadap
	* sebuah object yang terdapat didalam database
	* @param pkey adalah sebuah object yang merepresentasikan primary key dari 
	*	     tabel yang bersangkutan. Object tersebut dapat dalam bentuk single ID maupun composite ID
	* @param required adalah array dari field-field yang dibutuhkan dari hibernate object
	* @return Object yang dihasilkan dari proses retrieval, apabila object tidak ditemukan
	*	     maka method akan mengembalikan nilai "NULL"
	*/
	
	public Claim get (java.io.Serializable pkey, String[] required) throws Exception;


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

	//[Start] Add by aju on 20150819, add Not Equal param fufufu :D
	/* Method searching objects from database
	* @param likeColumn kolom like
	* @param likeParams param like
	* @param eqColumn kolom equal
	* @param eqParams param equal
	* @param neqColumn kolom equal
	* @param neqParams param equal
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
			String[] neqColumns, Object[] neqParams,
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

	//Add by aju on 20150819, add filter Not Equal fufufu
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
		String[] neqColumns, Object[] neqParams,
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
	public Claim searchUnique (String eqColumns, Object eqParams, String[] required)
		throws Exception;
	/*
	 * getting single unique object
	 * @param eqColumns column for retrieve
	 * @param eqParams value for column
	*/
	public Claim searchUnique (String eqColumns, Object eqParams)
		throws Exception;

	/*
	 * getting single unique object
	 * @param eqColumns column for retrieve
	 * @param eqParams value for column
	*/
	//Add 07042015, for handle multiple Equal column
	public Claim searchUnique(String[] eqColumns, Object[] eqParams, int index, int offset)
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
			String[] eqColumns, Object[] eqParams,String[] required, Double claimChargeValue) 
			throws Exception;
	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams,String betweenParam,Object minBetween, Object maxBetween, String[] required, Double claimChargeValue) 
			throws Exception;

	public Collection<Claim> searchExcessClaim (String[] likeColumns, Object[] likeParams,String[] eqColumn, Object[] eqParam,
			String[] betweenParam, Object[] minBetween, Object[] maxBetween, int index, int offset) throws Exception;
	
	public int getTotalExcessClaim (String[] likeColumns, Object[] likeParams,String[] eqColumn, Object[] eqParam,
			String[] betweenParam, Object[] minBetween, Object[] maxBetween) throws Exception;
	
	public Collection<PaymentReportDetail> getBatchClaimDetail(Integer batchClaimId) throws Exception;
	
	//riyan
	public Collection<Claim> getBatchClaimId(Integer batchClaimId) throws Exception;
	
	public Collection<PaymentReportDetail> getPaymentReport(Integer paymentId) throws Exception;
	
	public Collection<ClaimDto> getMemberClaimList(Integer memberId,
			Date startDate, Date endDate) throws Exception;
	
	public int getTotalOpenEdcClaim() throws Exception;
	public int getTotalOpenClaim() throws Exception;
	public int getTotalVerifiedClaim () throws Exception;
	public int getTotalOpenCaseConvertedClaim() throws Exception;
	
	public int getTotalRegisteredClaim (Integer clientId, Date start, Date end,Integer claimType,Integer caseCategoryId) throws Exception;
	
	public Integer getClaimDisabilityDiagnosis(Integer diagnosisId,Integer caseCategoryId,Integer memberId,Date minDate, Date maxDate) throws Exception;
	
	
	public Collection searchMultiStatus (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, Object[] inParamsStatus, Object[] inParamsCat,Object[] notInParamsStatus, Object[] notInParamsCat,
			boolean asc, String columnOrder,
			String[] required,	int index, int offset) throws Exception;
	
	public Collection searchMultiStatus (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, Object[] inParamsStatus, Object[] inParamsCat,Object[] notInParamsStatus, Object[] notInParamsCat,
			boolean asc, String columnOrder,String dateBy,Date minDate, Date maxDate,
			String[] required,	int index, int offset) throws Exception;
	
	public int getTotalMultiStatus (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, Object[] inParamsStatus, Object[] inParamsCat,Object[] notInParamsStatus, Object[] notInParamsCat) throws Exception;
	
	public int getTotalMultiStatus (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, Object[] inParamsStatus, Object[] inParamsCat,Object[] notInParamsStatus, Object[] notInParamsCat,
			String dateBy, Date minimumDate, Date maxDate) throws Exception;
	
		
	public Collection searchMultiStatus (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams,Object[] inParamsStatus, Object[] inParamsCat,Object[] notInParamsStatus, Object[] notInParamsCat, 
			boolean asc,String columnOrder[],String[] required, int index, int offset) throws Exception;
		
	public Long getTotalClaimPendingReject(Integer batchClaimId) throws Exception ;
	
	public Collection<Claim> getUnbilledPolicyClaim(Integer policyId,Integer claimStatus,Integer claimTypeId, Integer caseCategoryId) throws Exception;
	public Collection<Claim> getUnbilledClientClaim(Integer policyId,Integer claimStatus,Integer claimTypeId, Integer caseCategoryId) throws Exception;
	 
}
