

package com.ametis.cms.service.impl;

import java.sql.Date;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;

import com.ametis.cms.dao.MemberBenefitDao;
import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.ClaimItem;
import com.ametis.cms.datamodel.MemberBenefit;
import com.ametis.cms.datamodel.MemberProduct;
import com.ametis.cms.datamodel.PolicyDiagnosisExclusion;
import com.ametis.cms.datamodel.SubscriptionStatus;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.ClaimItemService;
import com.ametis.cms.service.MemberBenefitService;
import com.ametis.cms.util.dao.DaoSupportUtil;

// imports+ 

// imports- 

/**
 * MemberBenefit is a servlet controller for member_benefit Table. All you have
 * to do is to convert necessary data field to the named parameter
 */
public class MemberBenefitServiceImpl implements MemberBenefitService

// extends+

// extends-

{

	private MemberBenefitDao memberBenefitDao;

	public void setMemberBenefitDao(MemberBenefitDao object) {
		this.memberBenefitDao = object;
	}

	public MemberBenefitDao getMemberBenefitDao() {
		return this.memberBenefitDao;
	}

	/*
	 * Method create (MemberBenefit object) berfungsi untuk melakukan penambahan
	 * sebuah object kedalam database
	 * 
	 * @param object adalah sebuah object yang ingin diubah
	 * 
	 * @return object hasil kreasi,lengkap dengan assigned primary key,
	 * exception jika gagal
	 */
	public MemberBenefit create(MemberBenefit object, ActionUser actionUser) throws Exception {
		object.setCreatedTime(new java.sql.Timestamp(System.currentTimeMillis()));
		object.setDeletedStatus(Integer.valueOf(0));

		if (actionUser != null) {
			User user = actionUser.getUser();
			if (user != null) {
				object.setCreatedBy(user.getUsername());
			}
		}

		MemberBenefit result = memberBenefitDao.create(object);
		return result;
	}

	/*
	 * Method update (MemberBenefit object) berfungsi untuk melakukan perubahan
	 * terhadap sebuah object yang terdapat didalam database
	 * 
	 * @param object adalah sebuah object yang ingin diubah
	 * 
	 * @return object hasil update, exception jika gagal
	 */
	public MemberBenefit update(MemberBenefit object, ActionUser actionUser) throws Exception {
		object.setModifiedTime(new java.sql.Timestamp(System.currentTimeMillis()));

		if (actionUser != null) {
			User user = actionUser.getUser();
			if (user != null) {
				object.setModifiedBy(user.getUsername());
			}
		}

		memberBenefitDao.update(object);
		return object;
	}

	/*
	 * Method delete (Object pkey) berfungsi untuk melakukan penghapusan
	 * terhadap sebuah object yang terdapat didalam database
	 * 
	 * @param pkey adalah sebuah object yang merepresentasikan primary key dari
	 * tabel yang bersangkutan. Object tersebut dapat dalam bentuk single ID
	 * maupun composite ID
	 * 
	 * @return no return value karena objeknya sendiri sudah dihapus - just for
	 * consistency. Again, exception if failure occured WARNING ! Invalid value
	 * for the returned object, better not use it again in any place
	 */
	public MemberBenefit trash(java.io.Serializable pkey) throws Exception {
		MemberBenefit object = memberBenefitDao.get(pkey);
		memberBenefitDao.delete(object);
		return object;
	}

	/*
	 * Method delete (MemberBenefit object) berfungsi untuk melakukan
	 * penghapusan terhadap sebuah object yang terdapat didalam database
	 * 
	 * @param object adalah sebuah object yang ingin dihapus, isi dari object
	 * tersebut cukup dengan mengisi field-field primary key
	 * 
	 * @return updated object, exception if failed
	 */

	public MemberBenefit delete(java.io.Serializable pkey, ActionUser actionUser) throws Exception {
		MemberBenefit object = memberBenefitDao.get(pkey);

		// object.setDeletedDate (new java.sql.Date
		// (System.currentTimeMillis()));

		object.setDeletedStatus(new Integer(1));

		if (actionUser != null) {
			User user = actionUser.getUser();

			if (user != null) {
				object.setDeletedBy(user.getUsername());
			}

		}
		memberBenefitDao.update(object);
		return object;
	}

	/*
	 * Method delete (MemberBenefit object) berfungsi untuk melakukan
	 * penghapusan terhadap sebuah object yang terdapat didalam database
	 * 
	 * @param object adalah sebuah object yang ingin dihapus, isi dari object
	 * tersebut cukup dengan mengisi field-field primary key
	 * 
	 * @return updated object, exception if failed
	 */

	public MemberBenefit delete(MemberBenefit object, ActionUser actionUser) throws Exception {

		// object.setDeletedDate (new java.sql.Date
		// (System.currentTimeMillis()));

		object.setDeletedStatus(new Integer(1));

		if (actionUser != null) {
			User user = actionUser.getUser();

			if (user != null) {
				object.setDeletedBy(user.getUsername());
			}

		}
		memberBenefitDao.update(object);
		return object;
	}

	// -- get section - carefull !

	/*
	 * Method get (Object pkey) berfungsi untuk melakukan retrieval terhadap
	 * sebuah object yang terdapat didalam database
	 * 
	 * @param pkey adalah sebuah object yang merepresentasikan primary key dari
	 * tabel yang bersangkutan. Object tersebut dapat dalam bentuk single ID
	 * maupun composite ID
	 * 
	 * @return Object yang dihasilkan dari proses retrieval, apabila object
	 * tidak ditemukan maka method akan mengembalikan nilai "NULL"
	 */
	public MemberBenefit get(java.io.Serializable pkey) throws Exception {
		MemberBenefit object = null;
		object = memberBenefitDao.get(pkey);
		return object;
	}
	/*
	 * Method get (Object pkey) berfungsi untuk melakukan retrieval terhadap
	 * sebuah object yang terdapat didalam database
	 * 
	 * @param pkey adalah sebuah object yang merepresentasikan primary key dari
	 * tabel yang bersangkutan. Object tersebut dapat dalam bentuk single ID
	 * maupun composite ID
	 * 
	 * @param required adalah array dari field-field yang dibutuhkan dari
	 * hibernate object
	 * 
	 * @return Object yang dihasilkan dari proses retrieval, apabila object
	 * tidak ditemukan maka method akan mengembalikan nilai "NULL"
	 */

	public MemberBenefit get(java.io.Serializable pkey, String[] required) throws Exception {
		MemberBenefit object = null;
		object = memberBenefitDao.get(pkey);
		DaoSupportUtil.lazyInit(required, object);
		return object;
	}
	// -- get section end here

	// SEARCH SECTION - PALING RUMIT !!
	// * -> plain
	// *b -> with columnOrder

	// -- 1
	public Collection search(String[] likeColumns, Object[] likeParams, String[] eqColumns, Object[] eqParams,
			int index, int offset) throws Exception {

		Criteria c = memberBenefitDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		return list;

	}

	// --req
	public Collection search(String[] likeColumns, Object[] likeParams, String[] eqColumns, Object[] eqParams,
			String[] required, int index, int offset) throws Exception {

		Criteria c = memberBenefitDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			MemberBenefit element = (MemberBenefit) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}

		return list;

	}
	// --req end

	public Collection search(String[] likeColumns, Object[] likeParams, String[] eqColumns, Object[] eqParams,
			boolean asc, String columnOrder[], int index, int offset) throws Exception {

		Criteria c = memberBenefitDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		return list;

	}

	// --req
	public Collection search(String[] likeColumns, Object[] likeParams, String[] eqColumns, Object[] eqParams,
			boolean asc, String columnOrder[], String[] required, int index, int offset) throws Exception {
		Criteria c = memberBenefitDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			MemberBenefit element = (MemberBenefit) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}

		return list;

	}
	// --req end

	public Collection search(String[] likeColumns, Object[] likeParams, String[] eqColumns, Object[] eqParams,
			boolean asc, String columnOrder, int index, int offset) throws Exception {

		Criteria c = memberBenefitDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		return list;
	}

	// --req
	public Collection search(String[] likeColumns, Object[] likeParams, String[] eqColumns, Object[] eqParams,
			boolean asc, String columnOrder, String[] required, int index, int offset) throws Exception {
		Criteria c = memberBenefitDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			MemberBenefit element = (MemberBenefit) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}

		return list;

	}
	// --req end

	// -- 1b

	public Collection search(String likeColumns, Object likeParams, String eqColumns, Object eqParams, int index,
			int offset) throws Exception {

		Criteria c = memberBenefitDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		return list;

	}

	// --req
	public Collection search(String likeColumns, Object likeParams, String eqColumns, Object eqParams,
			String[] required, int index, int offset) throws Exception {
		Criteria c = memberBenefitDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			MemberBenefit element = (MemberBenefit) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}

		return list;

	}
	// --req end

	public Collection search(String likeColumns, Object likeParams, String eqColumns, Object eqParams, boolean asc,
			String columnOrder[], int index, int offset) throws Exception {

		Criteria c = memberBenefitDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		return list;

	}

	// req
	public Collection search(String likeColumns, Object likeParams, String eqColumns, Object eqParams, boolean asc,
			String columnOrder[], String[] required, int index, int offset) throws Exception {

		Criteria c = memberBenefitDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			MemberBenefit element = (MemberBenefit) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;

	}
	// req end

	public Collection search(String likeColumns, Object likeParams, String eqColumns, Object eqParams, boolean asc,
			String columnOrder, int index, int offset) throws Exception {

		Criteria c = memberBenefitDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		return list;

	}

	// req
	public Collection search(String likeColumns, Object likeParams, String eqColumns, Object eqParams, boolean asc,
			String columnOrder, String[] required, int index, int offset) throws Exception {

		Criteria c = memberBenefitDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			MemberBenefit element = (MemberBenefit) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;

	}

	// req end

	// -- 2 , between

	public Collection search(String[] likeColumns, Object[] likeParams, String[] eqColumns, Object[] eqParams,
			String[] btwnColumns, Object[] btwnParams1, Object[] btwnParams2, int index, int offset) throws Exception {

		Criteria c = memberBenefitDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		List list = c.list();
		return list;
	}

	// req
	public Collection search(String[] likeColumns, Object[] likeParams, String[] eqColumns, Object[] eqParams,
			String[] btwnColumns, Object[] btwnParams1, Object[] btwnParams2, String[] required, int index, int offset)
					throws Exception {

		Criteria c = memberBenefitDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			MemberBenefit element = (MemberBenefit) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}
	// req end

	public Collection search(String[] likeColumns, Object[] likeParams, String[] eqColumns, Object[] eqParams,
			String[] btwnColumns, Object[] btwnParams1, Object[] btwnParams2, boolean asc, String columnOrder[],
			int index, int offset) throws Exception {

		Criteria c = memberBenefitDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		return list;
	}

	// req
	public Collection search(String[] likeColumns, Object[] likeParams, String[] eqColumns, Object[] eqParams,
			String[] btwnColumns, Object[] btwnParams1, Object[] btwnParams2, boolean asc, String columnOrder[],
			String[] required, int index, int offset) throws Exception {

		Criteria c = memberBenefitDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			MemberBenefit element = (MemberBenefit) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}
	// req end

	public Collection search(String[] likeColumns, Object[] likeParams, String[] eqColumns, Object[] eqParams,
			String[] btwnColumns, Object[] btwnParams1, Object[] btwnParams2, boolean asc, String columnOrder,
			int index, int offset) throws Exception {

		Criteria c = memberBenefitDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		return list;
	}

	// req
	public Collection search(String[] likeColumns, Object[] likeParams, String[] eqColumns, Object[] eqParams,
			String[] btwnColumns, Object[] btwnParams1, Object[] btwnParams2, boolean asc, String columnOrder,
			String[] required, int index, int offset) throws Exception {

		Criteria c = memberBenefitDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			MemberBenefit element = (MemberBenefit) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}

	// req end

	// -- 2b
	public Collection search(String likeColumns, Object likeParams, String eqColumns, Object eqParams,
			String btwnColumns, Object btwnParams1, Object btwnParams2, int index, int offset) throws Exception {

		Criteria c = memberBenefitDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		List list = c.list();
		return list;
	}

	// req
	public Collection search(String likeColumns, Object likeParams, String eqColumns, Object eqParams,
			String btwnColumns, Object btwnParams1, Object btwnParams2, String[] required, int index, int offset)
					throws Exception {

		Criteria c = memberBenefitDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			MemberBenefit element = (MemberBenefit) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}
	// req end

	public Collection search(String likeColumns, Object likeParams, String eqColumns, Object eqParams,
			String btwnColumns, Object btwnParams1, Object btwnParams2, boolean asc, String columnOrder[], int index,
			int offset) throws Exception {

		Criteria c = memberBenefitDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		return list;
	}

	// req
	public Collection search(String likeColumns, Object likeParams, String eqColumns, Object eqParams,
			String btwnColumns, Object btwnParams1, Object btwnParams2, boolean asc, String columnOrder[],
			String[] required, int index, int offset) throws Exception {

		Criteria c = memberBenefitDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			MemberBenefit element = (MemberBenefit) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}
	// req end

	public Collection search(String likeColumns, Object likeParams, String eqColumns, Object eqParams,
			String btwnColumns, Object btwnParams1, Object btwnParams2, boolean asc, String columnOrder, int index,
			int offset) throws Exception {

		Criteria c = memberBenefitDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		return list;
	}

	// req
	public Collection search(String likeColumns, Object likeParams, String eqColumns, Object eqParams,
			String btwnColumns, Object btwnParams1, Object btwnParams2, boolean asc, String columnOrder,
			String[] required, int index, int offset) throws Exception {

		Criteria c = memberBenefitDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			MemberBenefit element = (MemberBenefit) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}
	// req end

	// -- search end

	// -- get total

	public int getTotal(String[] likeColumns, Object[] likeParams, String[] eqColumns, Object[] eqParams)
			throws Exception {

		Criteria c = memberBenefitDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}

	public int getTotal(String[] likeColumns, Object[] likeParams, String[] eqColumns, Object[] eqParams,
			String[] btwnColumns, Object[] btwnParams1, Object[] btwnParams2) throws Exception {

		Criteria c = memberBenefitDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}

	public int getTotal(String likeColumns, Object likeParams, String eqColumns, Object eqParams) throws Exception {

		Criteria c = memberBenefitDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}

	public int getTotal(String[] likeColumns, Object[] likeParams, String[] eqColumns, Object[] eqParams,
			String btwnColumns, Object btwnParams1, Object btwnParams2) throws Exception {

		Criteria c = memberBenefitDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}

	// ------------------------------------------------------------------
	public int getTotal() throws Exception {
		Criteria c = memberBenefitDao.getCriteria();
		int total = DaoSupportUtil.getTotal(c);
		return total;
	}
	// -- get total end

	// ---------------------------------------------------
	public Collection getAll(String[] required) throws Exception {

		Criteria c = memberBenefitDao.getCriteria();
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			MemberBenefit element = (MemberBenefit) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;

	}

	public Collection getAll() throws Exception {

		Criteria c = memberBenefitDao.getCriteria();
		List list = c.list();
		return list;
	}
	// -------------------------------------------------

	// unique Result

	public MemberBenefit searchUnique(String eqColumns, Object eqParams, String[] required) throws Exception {
		Criteria c = memberBenefitDao.getCriteria();
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		MemberBenefit obj = (MemberBenefit) c.uniqueResult();
		DaoSupportUtil.lazyInit(required, obj);
		return obj;
	}

	public MemberBenefit searchUnique(String eqColumns, Object eqParams) throws Exception {
		Criteria c = memberBenefitDao.getCriteria();
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		MemberBenefit obj = (MemberBenefit) c.uniqueResult();
		return obj;
	}

	// Add 06042015, find unique with more than 1 column and value parameter
	public MemberBenefit searchUnique(String[] eqColumns, Object[] eqParams) throws Exception {
		Criteria c = memberBenefitDao.getCriteria();
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		MemberBenefit obj = (MemberBenefit) c.uniqueResult();
		return obj;
	}

	// -----------------------------------------------
	
	//Riyan
	public MemberBenefit searchUnique (String[] eqColumns, Object[] eqParams,int index,int offset)
			throws Exception{
			Criteria c = memberBenefitDao.getCriteria()
					.addOrder(Order.desc("memberBenefitId"));
			DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
			DaoSupportUtil.setLimit(index, offset, c);
			MemberBenefit obj = (MemberBenefit) c.uniqueResult();
			return obj;
		}
	
	
	

	/*
	 * BASIC IMPLEMENTATION !! USE WITH CAUTION ! USE IT IF NO OTHER OPTION LEFT
	 * 
	 * @return criteria
	 */
	public Criteria getCriteria() throws Exception {
		Criteria c = memberBenefitDao.getCriteria();
		return c;
	}

	public DetachedCriteria getDetachedCriteria() throws Exception {
		DetachedCriteria dc = memberBenefitDao.getDetachedCriteria();
		return dc;
	}

	public Collection search(String[] likeColumns, Object[] likeParams, String[] eqColumns, Object[] eqParams)
			throws Exception {

		Criteria c = memberBenefitDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);

		List list = c.list();
		return list;

	}

	public Collection<ClaimItem> getBenefitUsage(MemberBenefit memberBenefit) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public MemberBenefit getMemberBenefit(ClaimItem claimItem) throws Exception {
		// TODO Auto-generated method stub

		MemberBenefit memberBenefit = null;

		String[] params = { "caseCategoryId.caseCategoryId", "itemCategoryId.itemCategoryId", "memberId.memberId",
				"memberBenefitStatus" };

		Object[] paramValue = { claimItem.getClaimId().getCaseCategoryId().getCaseCategoryId(),
				claimItem.getItemId().getItemCategoryId().getItemCategoryId(),
				claimItem.getClaimId().getMemberId().getMemberId(), Integer.valueOf(1) };

		String[] required = { "" };

		// Collection collections =
		// benefitService.search(null,null,params,paramValue
		// ,required ,0,1);
		Collection<MemberBenefit> collections = search(null, null, params, paramValue, required, 0, 1);

		if (collections != null) {
			Iterator<MemberBenefit> benefitIterator = collections.iterator();

			if (benefitIterator != null && benefitIterator.hasNext()) {
				memberBenefit = benefitIterator.next();
			}
		}

		return memberBenefit;
	}

	public MemberBenefit getActiveMemberBenefitSMM(Integer memberLimitLayerId, Integer caseCategory, Integer itemCategoryId)
			throws Exception {
		// TODO Auto-generated method stub

		MemberBenefit result = null;

		String[] required = { "MemberBenefit.CaseCategoryId", "MemberBenefit.MemberProductId","MemberBenefit.CaseCategoryId"  };

		String[] eqColumns = { "memberLimitLayerId.memberLimitLayerId", "memberBenefitStatus", "caseCategoryId.caseCategoryId",
				"deletedStatus", "itemCategoryId.itemCategoryId", "isSMMBenefit" };
		Object[] eqParams = { memberLimitLayerId, SubscriptionStatus.ACTIVE, caseCategory, Integer.valueOf(0), itemCategoryId,
				1 };

		Collection<MemberBenefit> list = search(null, null, eqColumns, eqParams, true, "itemEdcCode", required, 0, 1);

		if (list != null) {
			Iterator<MemberBenefit> iterator = list.iterator();
			if (iterator.hasNext()) {
				result = iterator.next();
			}
		}
		return result;
	}

	public MemberBenefit getActiveMemberBenefit(Integer memberId, Integer caseCategory, Integer itemCategoryId)
			throws Exception {
		// TODO Auto-generated method stub

		MemberBenefit result = null;

		String[] required = { "MemberBenefit.CaseCategoryId", "MemberBenefit.MemberProductId","MemberBenefit.CaseCategoryId"  };

		String[] eqColumns = { "memberId.memberId", "memberBenefitStatus", "caseCategoryId.caseCategoryId",
				"deletedStatus", "itemCategoryId.itemCategoryId" };
		Object[] eqParams = { memberId, SubscriptionStatus.ACTIVE, caseCategory, Integer.valueOf(0), itemCategoryId };

		Collection<MemberBenefit> list = search(null, null, eqColumns, eqParams, true, "itemEdcCode", required, 0, 1);

		if (list != null) {
			Iterator<MemberBenefit> iterator = list.iterator();
			if (iterator.hasNext()) {
				result = iterator.next();
			}
		}
		return result;
	}

	public Collection<MemberBenefit> getMemberBenefitList(Integer memberId, Integer caseCategory) throws Exception {
		// TODO Auto-generated method stub

		String[] required = { "MemberBenefit.CaseCategoryId", "MemberBenefit.MemberProductId","MemberBenefit.CaseCategoryId"  };

		String[] eqColumns = { "memberId.memberId", "memberBenefitStatus", "caseCategoryId.caseCategoryId",
				"deletedStatus" };
		Object[] eqParams = { memberId, SubscriptionStatus.ACTIVE, caseCategory, Integer.valueOf(0) };

		Collection<MemberBenefit> result = search(null, null, eqColumns, eqParams, true, "itemEdcCode", required, 0,
				100);
		return result;
	}
	
	public Collection<MemberBenefit> getMemberBenefitList(Integer memberId, Integer caseCategory,Integer memberProductId,Integer layer) throws Exception {
		// TODO Auto-generated method stub

		Collection<MemberBenefit> result = new Vector<MemberBenefit>();
		String[] required = { "MemberBenefit.CaseCategoryId", "MemberBenefit.MemberProductId","MemberBenefit.CaseCategoryId" };

		String[] eqColumns = { "memberId.memberId", "caseCategoryId.caseCategoryId",
				"deletedStatus","memberProductId.memberProductId" };
		Object[] eqParams = { memberId, caseCategory, Integer.valueOf(0),memberProductId };

		Collection<MemberBenefit> resList = search(null, null, eqColumns, eqParams, true, "itemEdcCode", required, 0,
				100);
		
		if (resList != null && !resList.isEmpty()){
			for (Iterator iterator = resList.iterator(); iterator.hasNext();) {
				
				MemberBenefit memberBenefit = (MemberBenefit) iterator.next();
				
				if(layer == null || layer.intValue() == 0){
					if (memberBenefit.getMemberLimitLayerId() == null){
						result.add(memberBenefit);
					}
				}
				else {
					if (memberBenefit.getMemberLimitLayerId() != null && memberBenefit.getMemberLimitLayerId().getLayer().intValue() == layer){
						result.add(memberBenefit);
					}
				}
			}
		}
		
		return result;
	}

	public Collection<MemberBenefit> getMemberBenefitList(Integer memberId) throws Exception {

		String[] required = { "MemberBenefit.CaseCategoryId", "MemberBenefit.MemberProductId" ,"MemberBenefit.CaseCategoryId"};
		String[] eqColumns = { "memberId.memberId", "memberBenefitStatus", "deletedStatus" };
		Object[] eqParams = { memberId, SubscriptionStatus.ACTIVE, Integer.valueOf(0) };

		Collection<MemberBenefit> result = search(null, null, eqColumns, eqParams, required, 0, 100);
		return result;
	}

	@Override
	public Collection<MemberBenefit> getMemberBenefitByDate(Integer memberId, Integer itemCategoryId,
			Integer caseCategoryId, Date admissionDate, Date dischargeDate,Integer layer) throws Exception {

		Collection<MemberBenefit> result = new Vector<MemberBenefit>();

		try {
			String[] required = { "MemberBenefit.CaseCategoryId", "MemberBenefit.MemberProductId" ,"MemberBenefit.CaseCategoryId"};

			Session session = memberBenefitDao.getBenefitSession();

			if (session != null) {
				// Edit 20151104 by FVO, for geting benefit by admission date
				
				  String q =
				  "SELECT p FROM MemberBenefit p WHERE p.effectiveDate <= :effectiveDate AND p.memberId.memberId = :memberId "
				  +
				  " AND p.caseCategoryId.caseCategoryId = :ccId AND p.itemCategoryId.itemCategoryId = :itemId "
				  + " AND p.expireDate >= :exp AND p.deletedStatus = 0 AND " +
				  "( p.memberBenefitStatus = " + SubscriptionStatus.ACTIVE +
				  " OR p.memberBenefitStatus = " + SubscriptionStatus.UPGRADED
				  + " OR p.memberBenefitStatus = " +
				  SubscriptionStatus.REVISION +" OR p.memberBenefitStatus = " +SubscriptionStatus.EXPIRED+ " ) ";
				  
				  if (layer == null || layer == 0){
					  q += " AND p.memberLimitLayerId IS NULL";
				  }
				  else {
					  q += " AND p.memberLimitLayerId.layer = " + layer;
				  }
				 
				//ini versi yang baru, pake versi yg lama lagi diatas
				/*String q = "SELECT p FROM MemberBenefit p WHERE p.memberId.memberId = :memberId "
						+ " AND p.caseCategoryId.caseCategoryId = :ccId AND p.itemCategoryId.itemCategoryId = :itemId "
						+ " AND :effectiveDate BETWEEN p.effectiveDate AND p.expireDate-1 AND p.deletedStatus = 0 AND "
						+ "( p.memberBenefitStatus = " + SubscriptionStatus.ACTIVE + " OR p.memberBenefitStatus = "
						+ SubscriptionStatus.UPGRADED + " OR p.memberBenefitStatus = " + SubscriptionStatus.REVISION
						+ ")";*/

				org.hibernate.Query query = session.createQuery(q);
				query.setDate("exp", dischargeDate);
				query.setInteger("memberId", memberId);
				query.setInteger("ccId", caseCategoryId);
				query.setDate("effectiveDate", admissionDate);
				query.setInteger("itemId", itemCategoryId);

				if (query != null) {
					Collection<MemberBenefit> rsList = query.list();
					
					for (Iterator iterator = rsList.iterator(); iterator.hasNext();) {
						MemberBenefit memberBenefit = (MemberBenefit) iterator.next();
						DaoSupportUtil.lazyInit(required, memberBenefit);
						result.add(memberBenefit);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return result;
	}

	@Override
	public Collection<MemberBenefit> getMemberBenefitByDate(Integer memberId, Integer caseCategoryId,
			Date admissionDate, Date dischargeDate) throws Exception {

		Collection<MemberBenefit> result = new Vector<MemberBenefit>();

		try {

			Session session = memberBenefitDao.getBenefitSession();
			String[] required = { "MemberBenefit.CaseCategoryId", "MemberBenefit.MemberProductId" ,"MemberBenefit.CaseCategoryId"};

			
			// Edit 20151104 by FVO, benefit change plan tumpuk2an. saling
			// berisan. kacauu.
			
			/*
			 * seharusnya tetep expire date, jangan dibuat expireDate -1
			 * karena status aktif kepesertaan jika 1 jan 2015 s/d 31 dec 2015 berarti 31 Dec 2015 masih aktif
			 * jika menggunakan expireDate -1 maka 31 Dec 2015 yang bersangkutan sudah ga aktif lagi
			 */
			if (session != null) {
				
				String q =
				  "SELECT p FROM MemberBenefit p WHERE p.expireDate >= :exp AND p.memberId.memberId = :memberId "
				  + " AND p.caseCategoryId.caseCategoryId = :ccId " +
				  " AND p.effectiveDate <= :effectiveDate AND p.deletedStatus = 0 AND "
				  + "( p.memberBenefitStatus = " + SubscriptionStatus.ACTIVE +
				  " OR p.memberBenefitStatus = " + SubscriptionStatus.UPGRADED
				   + " OR " + "p.memberBenefitStatus = " +
				  SubscriptionStatus.REVISION +" OR p.memberBenefitStatus = " + SubscriptionStatus.EXPIRED+" ) " +
				  " ORDER BY p.expireDate ASC ";
				 
				
				//ini versi yang baru, pake versi yg lama lagi diatas
				/*String q = "SELECT p FROM MemberBenefit p WHERE p.memberId.memberId = :memberId "
						+ " AND p.caseCategoryId.caseCategoryId = :ccId "
						+ " AND (:effectiveDate BETWEEN p.effectiveDate AND p.expireDate-1 ) AND p.deletedStatus = 0 AND "
						+ "( p.memberBenefitStatus = " + SubscriptionStatus.ACTIVE + " OR p.memberBenefitStatus = "
						+ SubscriptionStatus.UPGRADED + " OR " + "p.memberBenefitStatus = "
						+ SubscriptionStatus.REVISION + ") " + " ORDER BY p.expireDate ASC ";*/

				org.hibernate.Query query = session.createQuery(q);
			    query.setDate("exp", dischargeDate);
				query.setInteger("memberId", memberId);
				query.setInteger("ccId", caseCategoryId);
				query.setDate("effectiveDate", admissionDate);

				System.out.println("QUERY = " + query.getQueryString() + " DISCHARGE = " + dischargeDate
						+ " ADMISSION = " + admissionDate + " MEMBER ID = " + memberId + " CC = " + caseCategoryId);

				if (query != null) {
					Collection<MemberBenefit> rsList = query.list();
					
					for (Iterator iterator = rsList.iterator(); iterator.hasNext();) {
						MemberBenefit memberBenefit = (MemberBenefit) iterator.next();
						DaoSupportUtil.lazyInit(required, memberBenefit);
						result.add(memberBenefit);
					}
					System.out.println("HASIL RETRIEVE BENEFIT PER DATE = " + result.size());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return result;
	}

	@Override
	public Collection<MemberBenefit> getMemberBenefitByDateSMM(Integer memberId, Integer itemCategoryId,
			Integer caseCategoryId, Date admissionDate, Date dischargeDate) throws Exception {
		// TODO Auto-generated method stub
		Collection<MemberBenefit> result = new Vector<MemberBenefit>();

		try {

			Session session = memberBenefitDao.getBenefitSession();
			String[] required = { "MemberBenefit.CaseCategoryId", "MemberBenefit.MemberProductId" ,"MemberBenefit.CaseCategoryId"};

			if (session != null) {
				// Edit 20151104 by FVO, for geting benefit by admission date
				/*
				 * String q =
				 * "SELECT p FROM MemberBenefit p WHERE p.effectiveDate <= :effectiveDate AND p.memberId.memberId = :memberId "
				 * +
				 * " AND p.caseCategoryId.caseCategoryId = :ccId AND p.itemCategoryId.itemCategoryId = :itemId "
				 * + " AND p.expireDate >= :exp AND p.deletedStatus = 0 AND " +
				 * "( p.memberBenefitStatus = " + SubscriptionStatus.ACTIVE +
				 * " OR p.memberBenefitStatus = " + SubscriptionStatus.UPGRADED
				 * + " OR p.memberBenefitStatus = " +
				 * SubscriptionStatus.REVISION +")";
				 */

				String q = "SELECT p FROM MemberBenefit p WHERE p.memberId.memberId = :memberId "
						+ " AND p.caseCategoryId.caseCategoryId = :ccId AND p.itemCategoryId.itemCategoryId = :itemId "
						+ " AND (:effectiveDate BETWEEN p.effectiveDate AND p.expireDate) AND p.isSMMBenefit = 1 AND p.deletedStatus = 0 AND "
						+ "( p.memberBenefitStatus = " + SubscriptionStatus.ACTIVE + " OR p.memberBenefitStatus = "
						+ SubscriptionStatus.UPGRADED + " OR p.memberBenefitStatus = " + SubscriptionStatus.REVISION
						+ ")";

				org.hibernate.Query query = session.createQuery(q);
				// query.setDate("exp", dischargeDate);
				query.setInteger("memberId", memberId);
				query.setInteger("ccId", caseCategoryId);
				query.setDate("effectiveDate", admissionDate);
				query.setInteger("itemId", itemCategoryId);

				if (query != null) {
					Collection<MemberBenefit> rsList = query.list();
					
					for (Iterator iterator = rsList.iterator(); iterator.hasNext();) {
						MemberBenefit memberBenefit = (MemberBenefit) iterator.next();
						DaoSupportUtil.lazyInit(required, memberBenefit);
						result.add(memberBenefit);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return result;	}

	@Override
	public Collection<MemberBenefit> getMemberBenefitByDateSMM(Integer memberId, Integer caseCategoryId,
			Date admissionDate, Date dischargeDate) throws Exception {
		// TODO Auto-generated method stub
		Collection<MemberBenefit> result = new Vector<MemberBenefit>();

		try {

			Session session = memberBenefitDao.getBenefitSession();
			String[] required = { "MemberBenefit.CaseCategoryId", "MemberBenefit.MemberProductId" ,"MemberBenefit.CaseCategoryId"};

			if (session != null) {
				String q = "SELECT p FROM MemberBenefit p WHERE p.memberId.memberId = :memberId "
						+ " AND p.caseCategoryId.caseCategoryId = :ccId "
						+ " AND (:effectiveDate BETWEEN p.effectiveDate AND p.expireDate ) AND p.deletedStatus = 0 AND p.isSMMBenefit = 1 AND "
						+ "( p.memberBenefitStatus = " + SubscriptionStatus.ACTIVE + " OR p.memberBenefitStatus = "
						+ SubscriptionStatus.UPGRADED + " OR " + "p.memberBenefitStatus = "
						+ SubscriptionStatus.REVISION + ") " + " ORDER BY p.expireDate ASC ";

				org.hibernate.Query query = session.createQuery(q);
				// query.setDate("exp", dischargeDate);
				query.setInteger("memberId", memberId);
				query.setInteger("ccId", caseCategoryId);
				query.setDate("effectiveDate", admissionDate);

				System.out.println("QUERY = " + query.getQueryString() + " DISCHARGE = " + dischargeDate
						+ " ADMISSION = " + admissionDate + " MEMBER ID = " + memberId + " CC = " + caseCategoryId);

				if (query != null) {
					Collection<MemberBenefit> rsList = query.list();
					
					for (Iterator iterator = rsList.iterator(); iterator.hasNext();) {
						MemberBenefit memberBenefit = (MemberBenefit) iterator.next();
						DaoSupportUtil.lazyInit(required, memberBenefit);
						result.add(memberBenefit);
					}
					System.out.println("HASIL RETRIEVE BENEFIT PER DATE = " + result.size());
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// class+

	// class-

}
