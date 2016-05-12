
package com.ametis.cms.service.impl;


import com.ametis.cms.service.EdcTerminalService;
import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.Case;
import com.ametis.cms.datamodel.EdcTerminal;
import com.ametis.cms.dao.EdcTerminalDao;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Iterator;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;

import com.ametis.cms.util.dao.DaoSupportUtil;


// imports+ 

// imports- 


/**
 * EdcTerminal is a servlet controller for edc_terminal Table.
 * All you have to do is to convert necessary data field to the named parameter
*/
public class EdcTerminalServiceImpl implements EdcTerminalService

// extends+ 

// extends- 

{
	
	private EdcTerminalDao  edcTerminalDao;

	public void setEdcTerminalDao (EdcTerminalDao object){
		this.edcTerminalDao = object;
	}
	public EdcTerminalDao getEdcTerminalDao (){
		return this.edcTerminalDao;
	}
	/*
	* Method create (EdcTerminal object) berfungsi untuk melakukan penambahan
	* sebuah object kedalam database
	* @param object adalah sebuah object yang ingin diubah
	* @return object hasil kreasi,lengkap dengan assigned primary key, exception jika gagal
	*/
	public EdcTerminal create (EdcTerminal object,ActionUser user) throws Exception {
			
		object.setCreatedTime(new java.sql.Timestamp(System.currentTimeMillis()));
		object.setDeletedStatus(0);
		
		if (user != null){
			if (user.getUser() != null){
				object.setCreatedBy(user.getUser().getUsername());
			}
		}


		EdcTerminal result = edcTerminalDao.create (object);
		return result;
	}
	/*
	* Method update (EdcTerminal object) berfungsi untuk melakukan perubahan terhadap
	* sebuah object yang terdapat didalam database
	* @param object adalah sebuah object yang ingin diubah
	* @return object hasil update, exception jika gagal
	*/
	public EdcTerminal update (EdcTerminal object,ActionUser user) throws Exception{

		object.setModifiedTime(new java.sql.Timestamp(System.currentTimeMillis()));
		if (user != null){
			if (user.getUser() != null){
				object.setModifiedBy(user.getUser().getUsername());
			}
		}
	
 		edcTerminalDao.update (object);
      	return object;
	}


	/*
	* Method delete (Object pkey) berfungsi untuk melakukan penghapusan terhadap
	* sebuah object yang terdapat didalam database
	* @param pkey adalah sebuah object yang merepresentasikan primary key dari
	*	     tabel yang bersangkutan. Object tersebut dapat dalam bentuk single ID maupun composite ID
	* @return no return value karena objeknya sendiri sudah dihapus - just for consistency. Again,
	* exception if failure occured
	* WARNING ! Invalid value for the returned object, better not use it again in any
	* place
	*/
	public EdcTerminal trash (java.io.Serializable pkey) throws Exception {
		EdcTerminal object = edcTerminalDao.get (pkey);
		edcTerminalDao.delete (object);
		return object;
	}

	/*
	* Method delete (EdcTerminal object) berfungsi untuk melakukan penghapusan terhadap
	* sebuah object yang terdapat didalam database
	* @param object adalah sebuah object yang ingin dihapus, isi dari object tersebut cukup dengan
	*		 mengisi field-field primary key
	* @return updated object, exception if failed
	*/

	public EdcTerminal delete (java.io.Serializable pkey,ActionUser user) throws Exception{
		EdcTerminal object = edcTerminalDao.get (pkey);

		
		object.setDeletedTime (new java.sql.Timestamp (System.currentTimeMillis()));
		object.setDeletedStatus(1);
		
		if (user != null){
			if (user.getUser() != null){
				object.setDeletedBy(user.getUser().getUsername());
			}
		}
		
		
		edcTerminalDao.update (object);
		return object;
	}


	/*
	* Method delete (EdcTerminal object) berfungsi untuk melakukan penghapusan terhadap
	* sebuah object yang terdapat didalam database
	* @param object adalah sebuah object yang ingin dihapus, isi dari object tersebut cukup dengan
	*		 mengisi field-field primary key
	* @return updated object, exception if failed
	*/

	public EdcTerminal delete (EdcTerminal object,ActionUser user) throws Exception{
		
		
		object.setDeletedTime (new java.sql.Timestamp (System.currentTimeMillis()));
		object.setDeletedStatus(1);
		
		if (user != null){
			if (user.getUser() != null){
				object.setDeletedBy(user.getUser().getUsername());
			}
		}
		
		
		edcTerminalDao.update (object);
		return object;
	}

// -- get section - carefull !


	/*
	* Method get (Object pkey) berfungsi untuk melakukan retrieval terhadap
	* sebuah object yang terdapat didalam database
	* @param pkey adalah sebuah object yang merepresentasikan primary key dari
	*	     tabel yang bersangkutan. Object tersebut dapat dalam bentuk single ID maupun composite ID
	* @return Object yang dihasilkan dari proses retrieval, apabila object tidak ditemukan
	*	     maka method akan mengembalikan nilai "NULL"
	*/
	public EdcTerminal get (java.io.Serializable pkey) throws Exception{
		EdcTerminal object = null;
		object = edcTerminalDao.get(pkey);
		return object;
	}
	/*
	* Method get (Object pkey) berfungsi untuk melakukan retrieval terhadap
	* sebuah object yang terdapat didalam database
	* @param pkey adalah sebuah object yang merepresentasikan primary key dari
	*	     tabel yang bersangkutan. Object tersebut dapat dalam bentuk single ID maupun composite ID
	* @param required adalah array dari field-field yang dibutuhkan dari hibernate object
	* @return Object yang dihasilkan dari proses retrieval, apabila object tidak ditemukan
	*	     maka method akan mengembalikan nilai "NULL"
	*/

	public EdcTerminal get (java.io.Serializable pkey, String[] required) throws Exception{
	    EdcTerminal object = null;
	    object = edcTerminalDao.get(pkey);
		DaoSupportUtil.lazyInit(required,object);
	    return object;
	}
// -- get section end here


// SEARCH SECTION - PALING RUMIT !!
// * -> plain
// *b -> with columnOrder



// -- 1
	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, int index, int offset) throws Exception{

		Criteria c = edcTerminalDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		return list;

	}
	//--req
	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams,String[] required, int index, int offset) throws Exception{

		Criteria c = edcTerminalDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			EdcTerminal element = (EdcTerminal) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}

		return list;

	}
	// --req end

	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, boolean asc, String columnOrder[],
			int index, int offset) throws Exception{

		Criteria c = edcTerminalDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		return list;

	}
	//--req
		public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, boolean asc, String columnOrder[],
			String[] required,
			int index, int offset) throws Exception{
		Criteria c = edcTerminalDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			EdcTerminal element = (EdcTerminal) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}

		return list;

	}
	// --req end



	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, boolean asc, String columnOrder,
			int index, int offset) throws Exception{

		Criteria c = edcTerminalDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		return list;
	}
	//--req
	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, boolean asc, String columnOrder,
			String[] required,
			int index, int offset) throws Exception{
		Criteria c = edcTerminalDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			EdcTerminal element = (EdcTerminal) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}

		return list;

	}
	// --req end


// -- 1b

	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, int index, int offset) throws Exception{

		Criteria c = edcTerminalDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		return list;

	}
	//--req
	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams,
			String[] required,
			int index, int offset) throws Exception{
		Criteria c = edcTerminalDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			EdcTerminal element = (EdcTerminal) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}

		return list;

	}
	// --req end

	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams,
			 boolean asc, String columnOrder[],
			int index, int offset) throws Exception{

		Criteria c = edcTerminalDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		return list;

	}
	// req
	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams,
			 boolean asc, String columnOrder[],
			 String[] required,
			int index, int offset) throws Exception{

		Criteria c = edcTerminalDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			EdcTerminal element = (EdcTerminal) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}
		return list;

	}
	// req end

	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams,
			 boolean asc, String columnOrder,
			int index, int offset) throws Exception{

		Criteria c = edcTerminalDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		return list;

	}
	// req
	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams,
			 boolean asc, String columnOrder,
			 String[] required,
			int index, int offset) throws Exception{

		Criteria c = edcTerminalDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			EdcTerminal element = (EdcTerminal) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}
		return list;

	}

	// req end



// -- 2 , between

	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams,
			String[] btwnColumns, Object[] btwnParams1,
			Object[] btwnParams2,
			int index, int offset) throws Exception{

		Criteria c = edcTerminalDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		List list = c.list();
		return list;
	}
	// req
	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams,
			String[] btwnColumns, Object[] btwnParams1,
			Object[] btwnParams2,
			String[] required,
			int index, int offset) throws Exception{

		Criteria c = edcTerminalDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			EdcTerminal element = (EdcTerminal) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}
		return list;
	}
	// req end



	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams,
			String[] btwnColumns, Object[] btwnParams1,
			Object[] btwnParams2,
			boolean asc, String columnOrder[],
			int index, int offset) throws Exception{

		Criteria c = edcTerminalDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		return list;
	}
	// req
	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams,
			String[] btwnColumns, Object[] btwnParams1,
			Object[] btwnParams2,
			boolean asc, String columnOrder[],
			String[] required,
			int index, int offset) throws Exception{

		Criteria c = edcTerminalDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			EdcTerminal element = (EdcTerminal) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}
		return list;
	}
	// req end

	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams,
			String[] btwnColumns, Object[] btwnParams1,
			Object[] btwnParams2,
			boolean asc, String columnOrder,
			int index, int offset) throws Exception{

		Criteria c = edcTerminalDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		return list;
	}
	// req
	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams,
			String[] btwnColumns, Object[] btwnParams1,
			Object[] btwnParams2,
			boolean asc, String columnOrder,
			String[] required,
			int index, int offset) throws Exception{

		Criteria c = edcTerminalDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			EdcTerminal element = (EdcTerminal) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}
		return list;
	}

	// req end



 // -- 2b
	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams,
			String btwnColumns, Object btwnParams1,
			Object btwnParams2,
			int index, int offset) throws Exception{

		Criteria c = edcTerminalDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		List list = c.list();
		return list;
	}
	// req
	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams,
			String btwnColumns, Object btwnParams1,
			Object btwnParams2,
			String[] required,
			int index, int offset) throws Exception{

		Criteria c = edcTerminalDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			EdcTerminal element = (EdcTerminal) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}
		return list;
	}
	// req end

	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams,
			String btwnColumns, Object btwnParams1,
			Object btwnParams2,
			boolean asc, String columnOrder[],
			int index, int offset) throws Exception{

		Criteria c = edcTerminalDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		return list;
	}
	//req
	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams,
			String btwnColumns, Object btwnParams1,
			Object btwnParams2,
			boolean asc, String columnOrder[],
			String[] required,
			int index, int offset) throws Exception{

		Criteria c = edcTerminalDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			EdcTerminal element = (EdcTerminal) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}
		return list;
	}
	// req end

	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams,
			String btwnColumns, Object btwnParams1,
			Object btwnParams2,
			boolean asc, String columnOrder,
			int index, int offset) throws Exception{

		Criteria c = edcTerminalDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		return list;
	}
	
	public Collection searchProviderEDCFault(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, Object[] inParamValues, boolean asc, String columnOrder,
			String[] required,
			int index, int offset) throws Exception{
		Criteria c = edcTerminalDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		if(inParamValues.length >= 1)
			DaoSupportUtil.setIn("isFault",inParamValues,c);
		DaoSupportUtil.setIsNotNull("lastTransaction", c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			EdcTerminal element = (EdcTerminal) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}

		return list;
	}
	
	public Collection searchProviderEDCFault(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, String lsthColumn, Object lsthValue, Object[] inParamValues, boolean asc, String columnOrder,
			String[] required,
			int index, int offset) throws Exception{
		Criteria c = edcTerminalDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLessThanEqual(lsthValue, lsthColumn, c);
		if(inParamValues.length >= 1)
			DaoSupportUtil.setIn("isFault",inParamValues,c);
		DaoSupportUtil.setIsNotNull("lastTransaction", c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			EdcTerminal element = (EdcTerminal) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}

		return list;
	}
	
	/*public Collection<EdcTerminal> searchProviderEDCFault(Object[] inParamValues, int index, int offset) throws Exception{
	   	 Collection<EdcTerminal> result = null;
	   	 
	   	 Session session = edcTerminalDao.getEdcTerminalSession();
	        if (session != null) {
	        	Query query = session.createQuery("");
	        	String queryEdc = "SELECT edc FROM EdcTerminal edc WHERE edc.deletedStatus = 0" +
	        			" AND edc.providerId.deletedStatus = 0 " +
	        			" AND month(edc.lastTransaction) - month(edc.lastTransaction) ";
	        	if(inParamValues.length == 1){
	        		queryEdc = queryEdc + " AND edc.isFault = "+inParamValues[0];
	        	}else{
	        		queryEdc = queryEdc + " AND edc.isFault IN (";
	        		for(int i = 0;i<inParamValues.length;i++){
	        			queryEdc = queryEdc + inParamValues[i];
	        			if(i+1 < inParamValues.length){
	        				queryEdc = queryEdc + ",";
	        			}
	        		}
	        		queryEdc = queryEdc + ")";
	        	}
	       	 
	       	 Query query2 = session.createQuery("SELECT " +
	          		"c.diagnosisId.diagnosisGroupDescription, " +
	          		"(SELECT COUNT(cl.claimId) FROM Claim cl INNER JOIN cl.diagnosisId WHERE cl.claimStatus != 4 AND cl.claimStatus != -1 AND cl.claimApprovedValue > 0 " +
	          		"AND cl.diagnosisId.diagnosisGroupDescription = c.diagnosisId.diagnosisGroupDescription) AS totalClaim, " +
	          		"(SELECT COUNT(cl.claimId) FROM Claim cl INNER JOIN cl.diagnosisId WHERE cl.deletedStatus = 0 AND cl.claimStatus !=4 AND cl.claimStatus !=-1 " +
	          		"AND cl.diagnosisId.diagnosisGroupDescription = c.diagnosisId.diagnosisGroupDescription) AS totalClaimant " +
	          		"FROM Claim c JOIN c.diagnosisId JOIN c.client WHERE c.deletedStatus = 0 AND c.client.clientId = :clientId " +
	          		"AND (c.admissionDate BETWEEN :startDate AND :endDate)" +
	          		" GROUP BY c.diagnosisId.diagnosisGroupDescription, c.diagnosisId.diagnosisGroupCode " +
	          		"ORDER BY c.diagnosisId.diagnosisGroupDescription");

	       	query.setInteger("clientId", clientId);
	       	query.setDate("startDate", startDate);
	       	query.setDate("endDate", endDate);
	         //query.setInteger("claimStatus", Claim.CLAIM_PAID);
	            //EXCESSPAYMENTTYPE KOSONG
	           // Collection res = query.list();
	            
	            result = (Collection<Object[]>) query.list();
	        }
	   	 return result;
	   }
	*/
	// req
	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams,
			String btwnColumns, Object btwnParams1,
			Object btwnParams2,
			boolean asc, String columnOrder,
			String[] required,
			int index, int offset) throws Exception{

		Criteria c = edcTerminalDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			EdcTerminal element = (EdcTerminal) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}
		return list;
	}
	// req end

// -- search end

//-- get total
	
	public int getTotalProviderEDCFault(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, Object[] inParamValues) throws Exception{
		Criteria c = edcTerminalDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		if(inParamValues.length >= 1)
			DaoSupportUtil.setIn("isFault",inParamValues,c);
		DaoSupportUtil.setIsNotNull("lastTransaction", c);
		int total = DaoSupportUtil.getTotal(c);
		return total;
	}
	
	public int getTotalProviderEDCFault(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, String lsthColumn, Object lsthValue, Object[] inParamValues) throws Exception{
		Criteria c = edcTerminalDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLessThanEqual(lsthValue, lsthColumn, c);
		if(inParamValues.length >= 1)
			DaoSupportUtil.setIn("isFault",inParamValues,c);
		DaoSupportUtil.setIsNotNull("lastTransaction", c);
		int total = DaoSupportUtil.getTotal(c);
		return total;
	}

	public int getTotal (String[] likeColumns, Object[] likeParams,
		String[] eqColumns, Object[] eqParams) throws Exception{

		Criteria c = edcTerminalDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}
	public int getTotal (String[] likeColumns, Object[] likeParams,
		String[] eqColumns, Object[] eqParams,
		String[] btwnColumns, Object[] btwnParams1,
			Object[] btwnParams2
		) throws Exception{

		Criteria c = edcTerminalDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}
	public int getTotal (String likeColumns, Object likeParams,
		String eqColumns, Object eqParams) throws Exception{

		Criteria c = edcTerminalDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}
	public int getTotal (String[] likeColumns, Object[] likeParams,
		String[] eqColumns, Object[] eqParams,
		String btwnColumns, Object btwnParams1,
			Object btwnParams2
		) throws Exception{

		Criteria c = edcTerminalDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}
//------------------------------------------------------------------
	public int getTotal () throws Exception{
		Criteria c = edcTerminalDao.getCriteria();
		int total = DaoSupportUtil.getTotal(c);
		return total;
	}
//-- get total end


	public int getTotalEdcTerminalNotInProvider(Integer providerId, String deviceNumber){
		int total = 0;
		try{
			Session session = edcTerminalDao.getEdcTerminalSession();
			if(session != null){
				Query query = session.createQuery("SELECT COUNT(*) FROM EdcTerminal edc WHERE " +
						"edc.deletedStatus = 0 AND edc.deviceNumber = :deviceNumber AND " +
						"edc.providerId != :providerId");
				
				query.setString("deviceNumber", deviceNumber);
				query.setInteger("providerId", providerId);
				Number num = (Number) query.uniqueResult();
				total = num.intValue();
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return total;
	}
	
	public int getTotalEdcTerminal(String deviceNumber){
		int total = 0;
		try{
			Session session = edcTerminalDao.getEdcTerminalSession();
			if(session != null){
				Query query = session.createQuery("SELECT COUNT(*) FROM EdcTerminal edc INNER JOIN edc.providerId WHERE " +
						"edc.deletedStatus = 0 AND edc.deviceNumber = :deviceNumber AND edc.providerId.deletedStatus = 0");
				
				query.setString("deviceNumber", deviceNumber);
				Number num = (Number) query.uniqueResult();
				total = num.intValue();
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return total;
	}
	
	public int getTotalEdcTerminalAlert(Date alertDate){
		int total = 0;
		try{
			Session session = edcTerminalDao.getEdcTerminalSession();
			if(session != null){
				/*Query query = session.createQuery("SELECT COUNT(DISTINCT edc.providerId.providerId) FROM EdcTerminal edc INNER JOIN edc.providerId WHERE " +
						"edc.deletedStatus = 0 AND edc.lastTransaction <= :alertDate " +
						"AND edc.providerId.edcAlert IS NOT NULL " +
						"AND edc.providerId.deletedStatus = 0 ");
				*/
				Query query = session.createQuery("SELECT COUNT(DISTINCT edc.providerId.providerId) FROM EdcTerminal edc INNER JOIN edc.providerId WHERE " +
						"edc.deletedStatus = 0 AND edc.lastTransaction <= :alertDate " +
						"AND edc.providerId.edcAlert <= 0 " +
						"AND edc.providerId.deletedStatus = 0 ");
				
				query.setDate("alertDate", alertDate);
				Number num = (Number) query.uniqueResult();
				total = num.intValue();
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return total;
	}

//---------------------------------------------------
	public Collection getAll (String[] required) throws Exception{

		Criteria c = edcTerminalDao.getCriteria();
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			EdcTerminal element = (EdcTerminal) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}
		return list;

	}
	public Collection getAll () throws Exception{

		Criteria c = edcTerminalDao.getCriteria();
		List list = c.list();
		return list;
	}
//-------------------------------------------------

// unique Result

	public EdcTerminal searchUnique (String eqColumns, Object eqParams, String[] required)
		throws Exception{
		Criteria c = edcTerminalDao.getCriteria();
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		EdcTerminal obj = (EdcTerminal) c.uniqueResult();
		DaoSupportUtil.lazyInit(required,obj);
		return obj;
	}
	public EdcTerminal searchUnique (String eqColumns, Object eqParams)
		throws Exception{
		Criteria c = edcTerminalDao.getCriteria();
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		EdcTerminal obj = (EdcTerminal) c.uniqueResult();
		return obj;
	}
	public EdcTerminal searchUnique (String[] eqColumns, Object[] eqParams, int index, int offset)
		throws Exception{
		Criteria c = edcTerminalDao.getCriteria();
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		EdcTerminal obj = (EdcTerminal) c.uniqueResult();
		return obj;
	}

// -----------------------------------------------

/*
BASIC IMPLEMENTATION !! USE WITH CAUTION !
USE IT IF NO OTHER OPTION LEFT
@return criteria
*/
	public Criteria getCriteria() throws Exception {
		Criteria c = edcTerminalDao.getCriteria();
		return c;
	}

	public DetachedCriteria getDetachedCriteria() throws Exception {
		DetachedCriteria dc = edcTerminalDao.getDetachedCriteria();
		return dc;
	}
	@Override
	public EdcTerminal getEdcTerminal(String merchantId, String terminalId)
			throws Exception {
		// TODO Auto-generated method stub
		
		EdcTerminal result = null;
		
		if (merchantId != null && terminalId != null){
			String[] eqParam = {"deletedStatus","providerId.edcCode","deviceNumber"};
			Object[] eqValue = {0,merchantId,terminalId};
			
			Collection<EdcTerminal> list = search(null,null,eqParam,eqValue,0,1);
			
			if (list != null){
				Iterator<EdcTerminal> terminalIterator = list.iterator();
				
				if (terminalIterator.hasNext()){
					result = terminalIterator.next();
				}
			}
		}
		return result;
	}




// class+ 

// class- 

}
