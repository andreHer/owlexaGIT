
package com.ametis.cms.service.impl;


import com.ametis.cms.service.DiagnosisSetDetailService;
import com.ametis.cms.service.DiagnosisSetService;
import com.ametis.cms.service.MemberLimitLayerService;
import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.DiagnosisSet;
import com.ametis.cms.datamodel.MemberLimitLayer;
import com.ametis.cms.datamodel.SubscriptionStatus;
import com.ametis.cms.dao.MemberLimitLayerDao;
import java.util.Collection;
import java.util.List;
import java.util.Iterator;
import java.util.Vector;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;

import com.ametis.cms.util.dao.DaoSupportUtil;


// imports+ 

// imports- 

/**
 * MemberLimitLayer is a servlet controller for member_limit_layer Table.
 * All you have to do is to convert necessary data field to the named parameter
*/
public class MemberLimitLayerServiceImpl implements MemberLimitLayerService

// extends+ 

// extends- 
{
	
	private MemberLimitLayerDao  memberLimitLayerDao;
	private DiagnosisSetService diagnosisSetService;
	private DiagnosisSetDetailService diagnosisSetDetailService;
	
	

	public DiagnosisSetService getDiagnosisSetService() {
		return diagnosisSetService;
	}
	public void setDiagnosisSetService(DiagnosisSetService diagnosisSetService) {
		this.diagnosisSetService = diagnosisSetService;
	}
	public DiagnosisSetDetailService getDiagnosisSetDetailService() {
		return diagnosisSetDetailService;
	}
	public void setDiagnosisSetDetailService(
			DiagnosisSetDetailService diagnosisSetDetailService) {
		this.diagnosisSetDetailService = diagnosisSetDetailService;
	}
	public void setMemberLimitLayerDao (MemberLimitLayerDao object){
		this.memberLimitLayerDao = object;
	}
	public MemberLimitLayerDao getMemberLimitLayerDao (){
		return this.memberLimitLayerDao;
	}
	/*
	* Method create (MemberLimitLayer object) berfungsi untuk melakukan penambahan
	* sebuah object kedalam database
	* @param object adalah sebuah object yang ingin diubah
	* @return object hasil kreasi,lengkap dengan assigned primary key, exception jika gagal
	*/
	public MemberLimitLayer create (MemberLimitLayer object,ActionUser user) throws Exception {
			
			object.setCreatedTime (new java.sql.Timestamp (System.currentTimeMillis()));
			object.setDeletedStatus(new Integer(0));
			if (user != null && user.getUser() != null){
				object.setCreatedBy(user.getUser().getUsername());
			}

			

			MemberLimitLayer result = memberLimitLayerDao.create (object);
			return result;
		}
	/*
	* Method update (MemberLimitLayer object) berfungsi untuk melakukan perubahan terhadap
	* sebuah object yang terdapat didalam database
	* @param object adalah sebuah object yang ingin diubah
	* @return object hasil update, exception jika gagal
	*/
	public MemberLimitLayer getSMMLayer (Integer memberId, Integer diagnosisId, Integer categoryId) throws Exception {
		MemberLimitLayer result = null;
		
		if(memberId != null && diagnosisId != null && categoryId != null){
			String[] eqParam = {"productId.caseCategory.caseCategoryId",
					"memberId.memberId","deletedStatus","status"};
			Object[] eqValue = {categoryId,memberId,0,SubscriptionStatus.ACTIVE};
			
			int totalLayer = getTotal(null,null,eqParam,eqValue);
			Collection<MemberLimitLayer> list = search(null,null,eqParam,eqValue,0,totalLayer);
			if (list != null){
				Iterator<MemberLimitLayer> iterator = list.iterator();
				
				if (iterator.hasNext()){
					MemberLimitLayer layer = iterator.next();
					
					/**
					 * SMM is using DiagnosisSet 
					 */
					if (layer != null && layer.getDiagnosisSetId() != null){
						DiagnosisSet diagnosisSet = layer.getDiagnosisSetId();
						
						String[] eqParamDetail = {"diagnosisSetId.diagnosisSetId","diagnosisId.diagnosisId","deletedStatus"};
						Object[] eqParamValue = {diagnosisSet.getDiagnosisSetId(),diagnosisId,Integer.valueOf(0)};
						
						int totalDiag = diagnosisSetDetailService.getTotal(null,null,eqParamDetail,eqParamValue);
						
						if (totalDiag > 0 && layer.getActualBenefitLimit() != null && layer.getActualBenefitLimit() > 0){
							return layer;
						}
					}
				}
			}
		}
		return result;
		
	}
	public MemberLimitLayer getAvailableLayer (Integer memberId, Integer caseCategoryId) throws Exception {
		MemberLimitLayer result = null;
		
		if (memberId != null && caseCategoryId != null){
			String[] eqParam = {"memberId.memberId","productId.caseCategory.caseCategoryId","deletedStatus","status"};
			Object[] eqValue = {memberId,caseCategoryId,0,SubscriptionStatus.ACTIVE};
			
			int total = getTotal(null,null,eqParam,eqValue);
			
			Collection<MemberLimitLayer> list = search(null,null,eqParam,eqValue,true,"layer",0,total);
			
			if (list != null && list.size() > 0){
				for (MemberLimitLayer memberLimitLayer : list) {
					
					if (memberLimitLayer != null && memberLimitLayer.getDiagnosisId() == null){
						if (memberLimitLayer.getActualBenefitLimit() != null && memberLimitLayer.getActualBenefitLimit() > 0){
							result = memberLimitLayer;
							break;
						}
					}
				}
			}
			
			
		}
		
		return result;
	}
	public MemberLimitLayer update (MemberLimitLayer object,ActionUser user) throws Exception{
		object.setModifiedTime (new java.sql.Timestamp (System.currentTimeMillis()));
		
		if (user != null && user.getUser() != null){
			object.setModifiedBy (user.getUser().getUsername());
		}		
		
 		memberLimitLayerDao.update (object);
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
	public MemberLimitLayer trash (java.io.Serializable pkey) throws Exception {
		MemberLimitLayer object = memberLimitLayerDao.get (pkey);
		memberLimitLayerDao.delete (object);
		return object;
	}

	/*
	* Method delete (MemberLimitLayer object) berfungsi untuk melakukan penghapusan terhadap
	* sebuah object yang terdapat didalam database
	* @param object adalah sebuah object yang ingin dihapus, isi dari object tersebut cukup dengan
	*		 mengisi field-field primary key
	* @return updated object, exception if failed
	*/

	public MemberLimitLayer delete (java.io.Serializable pkey,ActionUser user) throws Exception{
		MemberLimitLayer object = memberLimitLayerDao.get (pkey);


		
		
		
		object.setDeletedTime (new java.sql.Timestamp (System.currentTimeMillis()));

		
		
		
		object.setDeletedStatus(new Integer(1));

		


		
			
		if (user != null && user.getUser() != null){
			object.setDeletedBy(user.getUser().getUsername());
		}

		
		memberLimitLayerDao.update (object);
		return object;
	}


	/*
	* Method delete (MemberLimitLayer object) berfungsi untuk melakukan penghapusan terhadap
	* sebuah object yang terdapat didalam database
	* @param object adalah sebuah object yang ingin dihapus, isi dari object tersebut cukup dengan
	*		 mengisi field-field primary key
	* @return updated object, exception if failed
	*/

	public MemberLimitLayer delete (MemberLimitLayer object,ActionUser user) throws Exception{
		
		
		
		object.setDeletedTime (new java.sql.Timestamp (System.currentTimeMillis()));

		

		
		
		object.setDeletedStatus(new Integer(1));

		

		
			
		if (user != null && user.getUser() != null){
			object.setDeletedBy(user.getUser().getUsername());
		}

		
		memberLimitLayerDao.update (object);
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
	public MemberLimitLayer get (java.io.Serializable pkey) throws Exception{
		MemberLimitLayer object = null;
		object = memberLimitLayerDao.get(pkey);
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

	public MemberLimitLayer get (java.io.Serializable pkey, String[] required) throws Exception{
	    MemberLimitLayer object = null;
	    object = memberLimitLayerDao.get(pkey);
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

		Criteria c = memberLimitLayerDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		return list;

	}
	//--req
	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams,String[] required, int index, int offset) throws Exception{

		Criteria c = memberLimitLayerDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			MemberLimitLayer element = (MemberLimitLayer) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}

		return list;

	}
	// --req end

	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, boolean asc, String columnOrder[],
			int index, int offset) throws Exception{

		Criteria c = memberLimitLayerDao.getCriteria();
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
		Criteria c = memberLimitLayerDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			MemberLimitLayer element = (MemberLimitLayer) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}

		return list;

	}
	// --req end



	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, boolean asc, String columnOrder,
			int index, int offset) throws Exception{

		Criteria c = memberLimitLayerDao.getCriteria();
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
		Criteria c = memberLimitLayerDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			MemberLimitLayer element = (MemberLimitLayer) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}

		return list;

	}
	// --req end


// -- 1b

	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, int index, int offset) throws Exception{

		Criteria c = memberLimitLayerDao.getCriteria();
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
		Criteria c = memberLimitLayerDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			MemberLimitLayer element = (MemberLimitLayer) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}

		return list;

	}
	// --req end

	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams,
			 boolean asc, String columnOrder[],
			int index, int offset) throws Exception{

		Criteria c = memberLimitLayerDao.getCriteria();
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

		Criteria c = memberLimitLayerDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			MemberLimitLayer element = (MemberLimitLayer) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}
		return list;

	}
	// req end

	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams,
			 boolean asc, String columnOrder,
			int index, int offset) throws Exception{

		Criteria c = memberLimitLayerDao.getCriteria();
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

		Criteria c = memberLimitLayerDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			MemberLimitLayer element = (MemberLimitLayer) iter.next();
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

		Criteria c = memberLimitLayerDao.getCriteria();
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

		Criteria c = memberLimitLayerDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			MemberLimitLayer element = (MemberLimitLayer) iter.next();
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

		Criteria c = memberLimitLayerDao.getCriteria();
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

		Criteria c = memberLimitLayerDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			MemberLimitLayer element = (MemberLimitLayer) iter.next();
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

		Criteria c = memberLimitLayerDao.getCriteria();
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

		Criteria c = memberLimitLayerDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			MemberLimitLayer element = (MemberLimitLayer) iter.next();
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

		Criteria c = memberLimitLayerDao.getCriteria();
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

		Criteria c = memberLimitLayerDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			MemberLimitLayer element = (MemberLimitLayer) iter.next();
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

		Criteria c = memberLimitLayerDao.getCriteria();
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

		Criteria c = memberLimitLayerDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			MemberLimitLayer element = (MemberLimitLayer) iter.next();
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

		Criteria c = memberLimitLayerDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		return list;
	}
	// req
	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams,
			String btwnColumns, Object btwnParams1,
			Object btwnParams2,
			boolean asc, String columnOrder,
			String[] required,
			int index, int offset) throws Exception{

		Criteria c = memberLimitLayerDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			MemberLimitLayer element = (MemberLimitLayer) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}
		return list;
	}
	// req end

// -- search end

//-- get total

	public int getTotal (String[] likeColumns, Object[] likeParams,
		String[] eqColumns, Object[] eqParams) throws Exception{

		Criteria c = memberLimitLayerDao.getCriteria();
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

		Criteria c = memberLimitLayerDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}
	public int getTotal (String likeColumns, Object likeParams,
		String eqColumns, Object eqParams) throws Exception{

		Criteria c = memberLimitLayerDao.getCriteria();
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

		Criteria c = memberLimitLayerDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}
//------------------------------------------------------------------
	public int getTotal () throws Exception{
		Criteria c = memberLimitLayerDao.getCriteria();
		int total = DaoSupportUtil.getTotal(c);
		return total;
	}
//-- get total end



//---------------------------------------------------
	public Collection getAll (String[] required) throws Exception{

		Criteria c = memberLimitLayerDao.getCriteria();
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			MemberLimitLayer element = (MemberLimitLayer) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}
		return list;

	}
	public Collection getAll () throws Exception{

		Criteria c = memberLimitLayerDao.getCriteria();
		List list = c.list();
		return list;
	}
//-------------------------------------------------

// unique Result

	public MemberLimitLayer searchUnique (String eqColumns, Object eqParams, String[] required)
		throws Exception{
		Criteria c = memberLimitLayerDao.getCriteria();
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		MemberLimitLayer obj = (MemberLimitLayer) c.uniqueResult();
		DaoSupportUtil.lazyInit(required,obj);
		return obj;
	}
	public MemberLimitLayer searchUnique (String eqColumns, Object eqParams)
		throws Exception{
		Criteria c = memberLimitLayerDao.getCriteria();
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		MemberLimitLayer obj = (MemberLimitLayer) c.uniqueResult();
		return obj;
	}

// -----------------------------------------------

/*
BASIC IMPLEMENTATION !! USE WITH CAUTION !
USE IT IF NO OTHER OPTION LEFT
@return criteria
*/
	public Criteria getCriteria() throws Exception {
		Criteria c = memberLimitLayerDao.getCriteria();
		return c;
	}

	public DetachedCriteria getDetachedCriteria() throws Exception {
		DetachedCriteria dc = memberLimitLayerDao.getDetachedCriteria();
		return dc;
	}
	@Override
	public Collection<MemberLimitLayer> getAdditionalLayer(Integer memberId,
			Integer caseCategoryId) throws Exception {
		// TODO Auto-generated method stub
		Collection<MemberLimitLayer> result = new Vector<MemberLimitLayer>();

		
		if (memberId != null && caseCategoryId != null){
			String[] eqParam = {"memberId.memberId","productId.caseCategory.caseCategoryId","deletedStatus","status"};
			Object[] eqValue = {memberId,caseCategoryId,0,SubscriptionStatus.ACTIVE};
			
			int total = getTotal(null,null,eqParam,eqValue);
			
			Collection<MemberLimitLayer> list = search(null,null,eqParam,eqValue,true,"layer",0,total);
			
			if (list != null && list.size() > 0){
				for (MemberLimitLayer memberLimitLayer : list) {
					
					if (memberLimitLayer != null && memberLimitLayer.getDiagnosisId() == null){
						if (memberLimitLayer.getActualBenefitLimit() != null && memberLimitLayer.getActualBenefitLimit() > 0){
							result.add(memberLimitLayer);
							
						}
					}
				}
			}
		}
		
		return result;
	}
	@Override
	public MemberLimitLayer getAvailableLayer(Integer memberId, Integer caseCategoryId, Integer memberProductId)
			throws Exception {
		// TODO Auto-generated method stub
		MemberLimitLayer result = null;

		
		if (memberId != null && caseCategoryId != null && memberProductId != null){
			
			Session session = memberLimitLayerDao.getLayerSession();
					
			String query = "SELECT m FROM MemberLimitLayer m WHERE m.memberId.memberId = :memberId"
					+ " AND m.memberProductId.memberProductId = :mpId AND m.deletedStatus = 0 AND "
					+ " ( m.status = " + SubscriptionStatus.ACTIVE + " OR m.status = " + SubscriptionStatus.EXPIRED + " ) "
							+ " AND m.actualBenefitLimit > 0 ORDER BY m.layer ASC";
			
			Query q = session.createQuery(query);
			q.setInteger("memberId", memberId);
			q.setInteger("mpId", memberProductId);
			
			List<MemberLimitLayer> list = q.list(); 
			
			if (list != null && list.size() > 0){
				for (MemberLimitLayer memberLimitLayer : list) {
					
					if (memberLimitLayer != null && memberLimitLayer.getDiagnosisId() == null){
						if (memberLimitLayer.getActualBenefitLimit() != null && memberLimitLayer.getActualBenefitLimit() > 0){
							result = memberLimitLayer;
							break;							
						}
					}
				}
			}
		}
		
		return result;
	}
	@Override
	public Collection<MemberLimitLayer> getLayerList(Integer memberProductId) throws Exception {
		// TODO Auto-generated method stub
		
		Collection<MemberLimitLayer> result = null;
		
		String[] eqParam = {"memberProductId.memberProductId","deletedStatus"};
		Object[] eqValue = {memberProductId,0};
		
		int total = getTotal(null,null,eqParam,eqValue);
		
		result = search(null,null,eqParam,eqValue,0,total);
		
		return result;
	}
	@Override
	public MemberLimitLayer getMemberLimitLayer(Integer memberProductId, Integer layer,Integer status) throws Exception {
		// TODO Auto-generated method stub
		
		String[] eqParam = {"memberProductId.memberProductId","layer","deletedStatus","status"};
		Object[] eqValue = {memberProductId,layer,0,status};
		
		Collection<MemberLimitLayer> memberLimitLayer = search(null,null,eqParam,eqValue,0,1);
		
		MemberLimitLayer result = null;
		Iterator<MemberLimitLayer> iterator = memberLimitLayer.iterator();
		
		if (iterator.hasNext()){
			result = iterator.next();
		}
		
		return result;
	}




// class+ 

// class- 
}
