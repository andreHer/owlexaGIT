package com.ametis.cms.service.impl;

import java.util.Vector;

import com.ametis.cms.service.MemberElectronicCardService;
import com.ametis.cms.service.MemberService;
import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberElectronicCard;
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.dao.MemberDao;
import com.ametis.cms.dao.MemberElectronicCardDao;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.SQLQuery;


import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Iterator;


import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;

import com.ametis.cms.util.dao.DaoSupportUtil;


// imports+ 

// imports- 

/**
 * MemberElectronicCard is a servlet controller for member_electronic_card
 * Table. All you have to do is to convert necessary data field to the named
 * parameter
 */
public class MemberElectronicCardServiceImpl implements
		MemberElectronicCardService



{

	private MemberDao memberDao;
	private MemberElectronicCardDao memberElectronicCardDao;

	
	public MemberDao getMemberDao() {
		return memberDao;
	}

	public void setMemberDao(MemberDao memberService) {
		this.memberDao = memberService;
	}

	public void setMemberElectronicCardDao(MemberElectronicCardDao object) {
		this.memberElectronicCardDao = object;
	}

	public MemberElectronicCardDao getMemberElectronicCardDao() {
		return this.memberElectronicCardDao;
	}

	/*
	 * Method create (MemberElectronicCard object) berfungsi untuk melakukan
	 * penambahan sebuah object kedalam database @param object adalah sebuah
	 * object yang ingin diubah @return object hasil kreasi,lengkap dengan
	 * assigned primary key, exception jika gagal
	 */
	public MemberElectronicCard create(MemberElectronicCard object,
			ActionUser user) throws Exception {

		object.setCreatedTime(new java.sql.Timestamp(System.currentTimeMillis()));
		object.setDeletedStatus(new Integer(0));

		if (user != null && user.getUser() != null) {
			object.setCreatedBy(user.getUser().getUsername());
		}

		object.setEffectiveDate(object.getRegistrationDate());
		
		
		
		MemberElectronicCard result = memberElectronicCardDao.create(object);
		
		if (result != null && result.getReplacedCardId() != null){
			MemberElectronicCard replaced = get(object.getReplacedCardId().getId());	
			if (replaced != null){
				replaced.setCardStatus(MemberElectronicCard.CARD_EXPIRED);
				replaced.setModifiedTime(new java.sql.Timestamp(System.currentTimeMillis()));
				if (user != null && user.getUser() != null){
					replaced.setModifiedBy(user.getUser().getUsername());
				}
				memberElectronicCardDao.update(replaced);
				
				result.setExpireDate(replaced.getExpireDate());
				memberElectronicCardDao.update(result);
			}
			
			
		}
		Member member = memberDao.get(result.getMemberId().getMemberId());
		
		if (member != null){
			member.setCurrentCardNumber(result.getCardNumber());
			member.setModifiedBy(user.getUser().getUsername());
			member.setModifiedTime(new java.sql.Timestamp(System.currentTimeMillis()));
			memberDao.update(member);
		}
		return result;
	}

	/*
	 * Method update (MemberElectronicCard object) berfungsi untuk melakukan
	 * perubahan terhadap sebuah object yang terdapat didalam database @param
	 * object adalah sebuah object yang ingin diubah @return object hasil
	 * update, exception jika gagal
	 */
	public MemberElectronicCard update(MemberElectronicCard object,
			ActionUser user) throws Exception {

		object.setModifiedTime(new java.sql.Timestamp(System
				.currentTimeMillis()));

		if (user != null && user.getUser() != null) {
			object.setModifiedBy(user.getUser().getUsername());
		}

		memberElectronicCardDao.update(object);
		return object;
	}

	/*
	 * Method delete (Object pkey) berfungsi untuk melakukan penghapusan
	 * terhadap sebuah object yang terdapat didalam database @param pkey adalah
	 * sebuah object yang merepresentasikan primary key dari tabel yang
	 * bersangkutan. Object tersebut dapat dalam bentuk single ID maupun
	 * composite ID @return no return value karena objeknya sendiri sudah
	 * dihapus - just for consistency. Again, exception if failure occured
	 * WARNING ! Invalid value for the returned object, better not use it again
	 * in any place
	 */
	public MemberElectronicCard trash(java.io.Serializable pkey)
			throws Exception {
		MemberElectronicCard object = memberElectronicCardDao.get(pkey);
		memberElectronicCardDao.delete(object);
		return object;
	}

	/*
	 * Method delete (MemberElectronicCard object) berfungsi untuk melakukan
	 * penghapusan terhadap sebuah object yang terdapat didalam database @param
	 * object adalah sebuah object yang ingin dihapus, isi dari object tersebut
	 * cukup dengan mengisi field-field primary key @return updated object,
	 * exception if failed
	 */

	public MemberElectronicCard delete(java.io.Serializable pkey,
			ActionUser user) throws Exception {
		MemberElectronicCard object = memberElectronicCardDao.get(pkey);

		object
				.setDeletedTime(new java.sql.Timestamp(System
						.currentTimeMillis()));

		object.setDeletedStatus(new Integer(1));

		if (user != null && user.getUser() != null) {
			object.setDeletedBy(user.getUser().getUsername());
		}

		memberElectronicCardDao.update(object);
		return object;
	}

	/*
	 * Method delete (MemberElectronicCard object) berfungsi untuk melakukan
	 * penghapusan terhadap sebuah object yang terdapat didalam database @param
	 * object adalah sebuah object yang ingin dihapus, isi dari object tersebut
	 * cukup dengan mengisi field-field primary key @return updated object,
	 * exception if failed
	 */

	public MemberElectronicCard delete(MemberElectronicCard object,
			ActionUser user) throws Exception {

		object
				.setDeletedTime(new java.sql.Timestamp(System
						.currentTimeMillis()));

		object.setDeletedStatus(new Integer(1));

		if (user != null && user.getUser() != null) {
			object.setDeletedBy(user.getUser().getUsername());
		}

		memberElectronicCardDao.update(object);
		return object;
	}

	// -- get section - carefull !

	/*
	 * Method get (Object pkey) berfungsi untuk melakukan retrieval terhadap
	 * sebuah object yang terdapat didalam database @param pkey adalah sebuah
	 * object yang merepresentasikan primary key dari tabel yang bersangkutan.
	 * Object tersebut dapat dalam bentuk single ID maupun composite ID @return
	 * Object yang dihasilkan dari proses retrieval, apabila object tidak
	 * ditemukan maka method akan mengembalikan nilai "NULL"
	 */
	public MemberElectronicCard get(java.io.Serializable pkey) throws Exception {
		MemberElectronicCard object = null;
		object = memberElectronicCardDao.get(pkey);
		return object;
	}

	/*
	 * Method get (Object pkey) berfungsi untuk melakukan retrieval terhadap
	 * sebuah object yang terdapat didalam database @param pkey adalah sebuah
	 * object yang merepresentasikan primary key dari tabel yang bersangkutan.
	 * Object tersebut dapat dalam bentuk single ID maupun composite ID @param
	 * required adalah array dari field-field yang dibutuhkan dari hibernate
	 * object @return Object yang dihasilkan dari proses retrieval, apabila
	 * object tidak ditemukan maka method akan mengembalikan nilai "NULL"
	 */

	public MemberElectronicCard get(java.io.Serializable pkey, String[] required)
			throws Exception {
		MemberElectronicCard object = null;
		object = memberElectronicCardDao.get(pkey);
		DaoSupportUtil.lazyInit(required, object);
		return object;
	}

	// -- get section end here

	// SEARCH SECTION - PALING RUMIT !!
	// * -> plain
	// *b -> with columnOrder

	// -- 1
	public Collection search(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, int index, int offset)
			throws Exception {

		Criteria c = memberElectronicCardDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		return list;

	}

	// --req
	public Collection search(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, String[] required,
			int index, int offset) throws Exception {

		Criteria c = memberElectronicCardDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			MemberElectronicCard element = (MemberElectronicCard) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}

		return list;

	}

	// --req end

	public Collection search(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, boolean asc,
			String columnOrder[], int index, int offset) throws Exception {

		Criteria c = memberElectronicCardDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		return list;

	}

	// --req
	public Collection search(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, boolean asc,
			String columnOrder[], String[] required, int index, int offset)
			throws Exception {
		Criteria c = memberElectronicCardDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			MemberElectronicCard element = (MemberElectronicCard) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}

		return list;

	}

	// --req end

	public Collection search(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, boolean asc,
			String columnOrder, int index, int offset) throws Exception {

		Criteria c = memberElectronicCardDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		return list;
	}

	// --req
	public Collection search(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, boolean asc,
			String columnOrder, String[] required, int index, int offset)
			throws Exception {
		Criteria c = memberElectronicCardDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			MemberElectronicCard element = (MemberElectronicCard) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}

		return list;

	}

	// --req end

	// -- 1b

	public Collection search(String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, int index, int offset)
			throws Exception {

		Criteria c = memberElectronicCardDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		return list;

	}

	// --req
	public Collection search(String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, String[] required, int index,
			int offset) throws Exception {
		Criteria c = memberElectronicCardDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			MemberElectronicCard element = (MemberElectronicCard) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}

		return list;

	}

	// --req end

	public Collection search(String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, boolean asc,
			String columnOrder[], int index, int offset) throws Exception {

		Criteria c = memberElectronicCardDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		return list;

	}

	// req
	public Collection search(String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, boolean asc,
			String columnOrder[], String[] required, int index, int offset)
			throws Exception {

		Criteria c = memberElectronicCardDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			MemberElectronicCard element = (MemberElectronicCard) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;

	}

	// req end

	public Collection search(String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, boolean asc, String columnOrder,
			int index, int offset) throws Exception {

		Criteria c = memberElectronicCardDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		return list;

	}

	// req
	public Collection search(String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, boolean asc, String columnOrder,
			String[] required, int index, int offset) throws Exception {

		Criteria c = memberElectronicCardDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			MemberElectronicCard element = (MemberElectronicCard) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;

	}

	// req end

	// -- 2 , between

	public Collection search(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, String[] btwnColumns,
			Object[] btwnParams1, Object[] btwnParams2, int index, int offset)
			throws Exception {

		Criteria c = memberElectronicCardDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil
				.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		List list = c.list();
		return list;
	}

	// req
	public Collection search(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, String[] btwnColumns,
			Object[] btwnParams1, Object[] btwnParams2, String[] required,
			int index, int offset) throws Exception {

		Criteria c = memberElectronicCardDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil
				.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			MemberElectronicCard element = (MemberElectronicCard) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}

	// req end

	public Collection search(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, String[] btwnColumns,
			Object[] btwnParams1, Object[] btwnParams2, boolean asc,
			String columnOrder[], int index, int offset) throws Exception {

		Criteria c = memberElectronicCardDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil
				.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		return list;
	}

	// req
	public Collection search(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, String[] btwnColumns,
			Object[] btwnParams1, Object[] btwnParams2, boolean asc,
			String columnOrder[], String[] required, int index, int offset)
			throws Exception {

		Criteria c = memberElectronicCardDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil
				.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			MemberElectronicCard element = (MemberElectronicCard) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}

	// req end

	public Collection search(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, String[] btwnColumns,
			Object[] btwnParams1, Object[] btwnParams2, boolean asc,
			String columnOrder, int index, int offset) throws Exception {

		Criteria c = memberElectronicCardDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil
				.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		return list;
	}

	// req
	public Collection search(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, String[] btwnColumns,
			Object[] btwnParams1, Object[] btwnParams2, boolean asc,
			String columnOrder, String[] required, int index, int offset)
			throws Exception {

		Criteria c = memberElectronicCardDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil
				.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			MemberElectronicCard element = (MemberElectronicCard) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}

	// req end

	// -- 2b
	public Collection search(String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, String btwnColumns,
			Object btwnParams1, Object btwnParams2, int index, int offset)
			throws Exception {

		Criteria c = memberElectronicCardDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil
				.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		List list = c.list();
		return list;
	}

	// req
	public Collection search(String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, String btwnColumns,
			Object btwnParams1, Object btwnParams2, String[] required,
			int index, int offset) throws Exception {

		Criteria c = memberElectronicCardDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil
				.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			MemberElectronicCard element = (MemberElectronicCard) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}

	// req end

	public Collection search(String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, String btwnColumns,
			Object btwnParams1, Object btwnParams2, boolean asc,
			String columnOrder[], int index, int offset) throws Exception {

		Criteria c = memberElectronicCardDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil
				.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		return list;
	}

	// req
	public Collection search(String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, String btwnColumns,
			Object btwnParams1, Object btwnParams2, boolean asc,
			String columnOrder[], String[] required, int index, int offset)
			throws Exception {

		Criteria c = memberElectronicCardDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil
				.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			MemberElectronicCard element = (MemberElectronicCard) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}

	// req end

	public Collection search(String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, String btwnColumns,
			Object btwnParams1, Object btwnParams2, boolean asc,
			String columnOrder, int index, int offset) throws Exception {

		Criteria c = memberElectronicCardDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil
				.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		return list;
	}

	// req
	public Collection search(String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, String btwnColumns,
			Object btwnParams1, Object btwnParams2, boolean asc,
			String columnOrder, String[] required, int index, int offset)
			throws Exception {

		Criteria c = memberElectronicCardDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil
				.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			MemberElectronicCard element = (MemberElectronicCard) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}

	// req end

	// -- search end

	// -- get total

	public int getTotal(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams) throws Exception {

		Criteria c = memberElectronicCardDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}

	public int getTotal(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, String[] btwnColumns,
			Object[] btwnParams1, Object[] btwnParams2) throws Exception {

		Criteria c = memberElectronicCardDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil
				.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}

	public int getTotal(String likeColumns, Object likeParams,
			String eqColumns, Object eqParams) throws Exception {

		Criteria c = memberElectronicCardDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}

	public int getTotal(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, String btwnColumns,
			Object btwnParams1, Object btwnParams2) throws Exception {

		Criteria c = memberElectronicCardDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil
				.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}

	// ------------------------------------------------------------------
	public int getTotal() throws Exception {
		Criteria c = memberElectronicCardDao.getCriteria();
		int total = DaoSupportUtil.getTotal(c);
		return total;
	}

	// -- get total end

	// ---------------------------------------------------
	public Collection getAll(String[] required) throws Exception {

		Criteria c = memberElectronicCardDao.getCriteria();
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			MemberElectronicCard element = (MemberElectronicCard) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;

	}
	
	public Collection<MemberElectronicCard> searchMemberByDobClientGroup(String[] eqColumns, String[] eqFirstName,  Object[] oFirstName, Object[] eqParams,
			String[] required) throws Exception {
		Criteria c = memberElectronicCardDao.getCriteria();
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLikeParam(eqFirstName, oFirstName, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			MemberElectronicCard element = (MemberElectronicCard) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;

	}

	public Collection getAll() throws Exception {

		Criteria c = memberElectronicCardDao.getCriteria();
		List list = c.list();
		return list;
	}

	// -------------------------------------------------

	// unique Result

	public MemberElectronicCard searchUnique(String eqColumns, Object eqParams,
			String[] required) throws Exception {
		Criteria c = memberElectronicCardDao.getCriteria();
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		MemberElectronicCard obj = (MemberElectronicCard) c.uniqueResult();
		DaoSupportUtil.lazyInit(required, obj);
		return obj;
	}

	public MemberElectronicCard searchUnique(String eqColumns, Object eqParams)
			throws Exception {
		Criteria c = memberElectronicCardDao.getCriteria();
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		MemberElectronicCard obj = (MemberElectronicCard) c.uniqueResult();
		return obj;
	}

	// -----------------------------------------------

	/*
	 * BASIC IMPLEMENTATION !! USE WITH CAUTION ! USE IT IF NO OTHER OPTION LEFT
	 * @return criteria
	 */
	public Criteria getCriteria() throws Exception {
		Criteria c = memberElectronicCardDao.getCriteria();
		return c;
	}

	public DetachedCriteria getDetachedCriteria() throws Exception {
		DetachedCriteria dc = memberElectronicCardDao.getDetachedCriteria();
		return dc;
	}
	
	@SuppressWarnings("unchecked")
	public Collection<MemberElectronicCard> getCardNumberByMember(Integer memberId) throws Exception {//buatan luthfi untuk menampilkan data membergroup berdasarkan client id
		// TODO Auto-generated method stub
		Collection<MemberElectronicCard> result = new Vector<MemberElectronicCard>();
		
		try {
			Session session = memberElectronicCardDao.getMemberElectronicCardSession();
			
			String query = "SELECT m FROM MemberElectronicCard m WHERE m.deletedStatus = 0 and m.memberId.deletedStatus = 0 and m.memberId.memberId = ? ";
			
			Query q = session.createQuery(query);
			q.setInteger(0, memberId);
			
			result = q.list();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public MemberElectronicCard getMemberCardByStatus(Integer memberId, Integer status) throws Exception {
		// TODO Auto-generated method stub
	
		MemberElectronicCard result = null;
		
		if (memberId != null && status != null){
			String[] eqParam = {"deletedStatus","memberId.memberId","cardStatus"};
			Object[] eqValue = {0,memberId,status};
			
			Collection<MemberElectronicCard> list = search(null,null,eqParam,eqValue,0,1);
			
			if (list != null){
				Iterator<MemberElectronicCard> iterator = list.iterator();
				
				if (iterator.hasNext()){
					result = iterator.next();
				}
			}
		}
		return result;
	}

	@Override
	public MemberElectronicCard getMemberCardByNumber(Integer memberId, String cardNumber) throws Exception {
		// TODO Auto-generated method stub

		MemberElectronicCard result = null;
		
		if (memberId != null && cardNumber != null){
			String[] eqParam = {"deletedStatus","memberId.memberId","cardNumber"};
			Object[] eqValue = {0,memberId,cardNumber};
			
			Collection<MemberElectronicCard> list = search(null,null,eqParam,eqValue,0,1);
			
			if (list != null){
				Iterator<MemberElectronicCard> iterator = list.iterator();
				
				if (iterator.hasNext()){
					result = iterator.next();
				}
			}
		}
		return result;
	}

	// class+

	// class-

}
