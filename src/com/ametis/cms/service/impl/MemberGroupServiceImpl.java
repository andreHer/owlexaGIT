package com.ametis.cms.service.impl;

import java.sql.Date;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;

import com.ametis.cms.dao.MemberGroupDao;
import com.ametis.cms.datamodel.ActionResult;
import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.ActivityLog;
import com.ametis.cms.datamodel.Configuration;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.datamodel.PaymentStatus;
import com.ametis.cms.datamodel.SubscriptionStatus;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.ConfigurationService;
import com.ametis.cms.service.MemberGroupService;
import com.ametis.cms.util.dao.DaoSupportUtil;

// imports+ 

// imports- 

/**
 * MemberGroup is a servlet controller for member_group Table. All you have to
 * do is to convert necessary data field to the named parameter
 */
public class MemberGroupServiceImpl implements MemberGroupService

// extends+

// extends-

{

	private MemberGroupDao memberGroupDao;
	private ActivityLogService activityLogService;
	private ConfigurationService configurationService;
	
	

	public ConfigurationService getConfigurationService() {
		return configurationService;
	}

	public void setConfigurationService(ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}

	public ActivityLogService getActivityLogService() {
		return activityLogService;
	}

	public void setActivityLogService(ActivityLogService activityLogService) {
		this.activityLogService = activityLogService;
	}

	public void setMemberGroupDao(MemberGroupDao object) {
		this.memberGroupDao = object;
	}

	public MemberGroupDao getMemberGroupDao() {
		return this.memberGroupDao;
	}

	/*
	 * Method create (MemberGroup object) berfungsi untuk melakukan penambahan
	 * sebuah object kedalam database @param object adalah sebuah object yang
	 * ingin diubah @return object hasil kreasi,lengkap dengan assigned primary
	 * key, exception jika gagal
	 */
	public MemberGroup create(MemberGroup object, ActionUser actionUser)
			throws Exception {

		object.setCreatedTime(new java.sql.Timestamp(System.currentTimeMillis()));
		object.setDeletedStatus(Integer.valueOf(0));

		if (actionUser != null) {
			User user = actionUser.getUser();
			if (user != null) {
				object.setCreatedBy(user.getUsername());
			}
		}
		Configuration configuration = configurationService.getSystemConfiguration();
		
		if (configuration != null){
			int groupCodeNumber = configuration.getGroupCodeNumber() == null ? 0 : configuration.getGroupCodeNumber();
			String gStr = "";
			if (groupCodeNumber < 10){
				gStr = "000"+groupCodeNumber;
			}
			if (groupCodeNumber>= 10 && groupCodeNumber < 100){
				gStr = "00"+groupCodeNumber;
			}
			if (groupCodeNumber>= 100 && groupCodeNumber < 1000){
				gStr = "0"+groupCodeNumber;
			}
			if (groupCodeNumber>= 1000 && groupCodeNumber < 10000){
				gStr = ""+groupCodeNumber;
			}
			groupCodeNumber += 1;
			
			object.setMemberGroupCode(object.getTipe()+gStr);
			configuration.setGroupCodeNumber(groupCodeNumber);
			configurationService.update(configuration, actionUser);
		}

		MemberGroup result = memberGroupDao.create(object);
		return result;
	}

	/*
	 * Method update (MemberGroup object) berfungsi untuk melakukan perubahan
	 * terhadap sebuah object yang terdapat didalam database @param object
	 * adalah sebuah object yang ingin diubah @return object hasil update,
	 * exception jika gagal
	 */
	public MemberGroup update(MemberGroup object, ActionUser actionUser)
			throws Exception {

		// object.setUpdatedDate (new java.sql.Date
		// (System.currentTimeMillis()));

		object.setModifiedTime(new java.sql.Timestamp(System
				.currentTimeMillis()));

		/*
		 * Gue tambahin mekanisme NULL value checking just in case user nya null
		 */
		if (actionUser != null) {
			User user = actionUser.getUser();
			if (user != null) {
				object.setModifiedBy(user.getUsername());
			}
		}

		memberGroupDao.update(object);
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
	public MemberGroup trash(java.io.Serializable pkey) throws Exception {
		MemberGroup object = memberGroupDao.get(pkey);
		memberGroupDao.delete(object);
		return object;
	}

	/*
	 * Method delete (MemberGroup object) berfungsi untuk melakukan penghapusan
	 * terhadap sebuah object yang terdapat didalam database @param object
	 * adalah sebuah object yang ingin dihapus, isi dari object tersebut cukup
	 * dengan mengisi field-field primary key @return updated object, exception
	 * if failed
	 */

	public MemberGroup delete(java.io.Serializable pkey, ActionUser actionUser)
			throws Exception {
		MemberGroup object = memberGroupDao.get(pkey);

		// object.setDeletedDate (new java.sql.Date
		// (System.currentTimeMillis()));

		object.setDeletedStatus(new Integer(1));
		object.setDeletedTime(new java.sql.Timestamp(System.currentTimeMillis()));

		if (actionUser != null) {
			User user = actionUser.getUser();
			if (user != null) {
				object.setDeletedBy(user.getUsername());
			}
		}

		memberGroupDao.update(object);
		return object;
	}

	/*
	 * Method delete (MemberGroup object) berfungsi untuk melakukan penghapusan
	 * terhadap sebuah object yang terdapat didalam database @param object
	 * adalah sebuah object yang ingin dihapus, isi dari object tersebut cukup
	 * dengan mengisi field-field primary key @return updated object, exception
	 * if failed
	 */

	public MemberGroup delete(MemberGroup object, ActionUser actionUser)
			throws Exception {

		// object.setDeletedDate (new java.sql.Date
		// (System.currentTimeMillis()));

		object.setDeletedStatus(new Integer(1));
		object.setDeletedTime(new java.sql.Timestamp(System.currentTimeMillis()));

		if (actionUser != null) {
			User user = actionUser.getUser();

			if (user != null) {
				object.setDeletedBy(user.getUsername());
			}
		}

		memberGroupDao.update(object);
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
	public MemberGroup get(java.io.Serializable pkey) throws Exception {
		MemberGroup object = null;
		object = memberGroupDao.get(pkey);
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

	public MemberGroup get(java.io.Serializable pkey, String[] required)
			throws Exception {
		MemberGroup object = null;
		object = memberGroupDao.get(pkey);
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

		Criteria c = memberGroupDao.getCriteria();
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

		Criteria c = memberGroupDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			MemberGroup element = (MemberGroup) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}

		return list;

	}

	// --req end
	
	//start search order by
	public Collection search(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, String[] required, String columnOrder, boolean order,
			int index, int offset) throws Exception {

		Criteria c = memberGroupDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(order, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			MemberGroup element = (MemberGroup) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}

		return list;

	}
	//end search order by

	public Collection search(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, boolean asc,
			String columnOrder[], int index, int offset) throws Exception {

		Criteria c = memberGroupDao.getCriteria();
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
		Criteria c = memberGroupDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			MemberGroup element = (MemberGroup) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}

		return list;

	}

	// --req end

	public Collection search(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, boolean asc,
			String columnOrder, int index, int offset) throws Exception {

		Criteria c = memberGroupDao.getCriteria();
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
		Criteria c = memberGroupDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			MemberGroup element = (MemberGroup) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}

		return list;

	}

	// --req end

	// -- 1b

	public Collection search(String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, int index, int offset)
			throws Exception {

		Criteria c = memberGroupDao.getCriteria();
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
		Criteria c = memberGroupDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			MemberGroup element = (MemberGroup) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}

		return list;

	}

	// --req end

	public Collection search(String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, boolean asc,
			String columnOrder[], int index, int offset) throws Exception {

		Criteria c = memberGroupDao.getCriteria();
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

		Criteria c = memberGroupDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			MemberGroup element = (MemberGroup) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;

	}

	// req end

	public Collection search(String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, boolean asc, String columnOrder,
			int index, int offset) throws Exception {

		Criteria c = memberGroupDao.getCriteria();
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

		Criteria c = memberGroupDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			MemberGroup element = (MemberGroup) iter.next();
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

		Criteria c = memberGroupDao.getCriteria();
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

		Criteria c = memberGroupDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil
				.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			MemberGroup element = (MemberGroup) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}

	// req end

	public Collection search(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, String[] btwnColumns,
			Object[] btwnParams1, Object[] btwnParams2, boolean asc,
			String columnOrder[], int index, int offset) throws Exception {

		Criteria c = memberGroupDao.getCriteria();
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

		Criteria c = memberGroupDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil
				.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			MemberGroup element = (MemberGroup) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}

	// req end

	public Collection search(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, String[] btwnColumns,
			Object[] btwnParams1, Object[] btwnParams2, boolean asc,
			String columnOrder, int index, int offset) throws Exception {

		Criteria c = memberGroupDao.getCriteria();
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

		Criteria c = memberGroupDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil
				.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			MemberGroup element = (MemberGroup) iter.next();
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

		Criteria c = memberGroupDao.getCriteria();
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

		Criteria c = memberGroupDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil
				.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			MemberGroup element = (MemberGroup) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}

	// req end

	public Collection search(String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, String btwnColumns,
			Object btwnParams1, Object btwnParams2, boolean asc,
			String columnOrder[], int index, int offset) throws Exception {

		Criteria c = memberGroupDao.getCriteria();
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

		Criteria c = memberGroupDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil
				.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			MemberGroup element = (MemberGroup) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}

	// req end

	public Collection search(String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, String btwnColumns,
			Object btwnParams1, Object btwnParams2, boolean asc,
			String columnOrder, int index, int offset) throws Exception {

		Criteria c = memberGroupDao.getCriteria();
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

		Criteria c = memberGroupDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil
				.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			MemberGroup element = (MemberGroup) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}

	// req end

	// -- search end

	// -- get total

	public int getTotal(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams) throws Exception {

		Criteria c = memberGroupDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}

	public int getTotal(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, String[] btwnColumns,
			Object[] btwnParams1, Object[] btwnParams2) throws Exception {

		Criteria c = memberGroupDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil
				.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}

	public int getTotal(String likeColumns, Object likeParams,
			String eqColumns, Object eqParams) throws Exception {

		Criteria c = memberGroupDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}

	public int getTotal(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, String btwnColumns,
			Object btwnParams1, Object btwnParams2) throws Exception {

		Criteria c = memberGroupDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil
				.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}

	// ------------------------------------------------------------------
	public int getTotal() throws Exception {
		Criteria c = memberGroupDao.getCriteria();
		int total = DaoSupportUtil.getTotal(c);
		return total;
	}

	// -- get total end

	// ---------------------------------------------------
	public Collection getAll(String[] required) throws Exception {

		Criteria c = memberGroupDao.getCriteria();
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			MemberGroup element = (MemberGroup) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;

	}

	public Collection getAll() throws Exception {

		Criteria c = memberGroupDao.getCriteria();
		List list = c.list();
		return list;
	}

	// -------------------------------------------------

	// unique Result

	public MemberGroup searchUnique(String eqColumns, Object eqParams,
			String[] required) throws Exception {
		Criteria c = memberGroupDao.getCriteria();
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		MemberGroup obj = (MemberGroup) c.uniqueResult();
		DaoSupportUtil.lazyInit(required, obj);
		return obj;
	}

	public MemberGroup searchUnique(String eqColumns, Object eqParams)
			throws Exception {
		Criteria c = memberGroupDao.getCriteria();
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		MemberGroup obj = (MemberGroup) c.uniqueResult();
		return obj;
	}

	// -----------------------------------------------

	/*
	 * BASIC IMPLEMENTATION !! USE WITH CAUTION ! USE IT IF NO OTHER OPTION LEFT
	 * @return criteria
	 */
	public Criteria getCriteria() throws Exception {
		Criteria c = memberGroupDao.getCriteria();
		return c;
	}

	public DetachedCriteria getDetachedCriteria() throws Exception {
		DetachedCriteria dc = memberGroupDao.getDetachedCriteria();
		return dc;
	}

	public boolean activate(Integer memberGroupId, ActionUser actionUser)
			throws Exception {
		// TODO Auto-generated method stub

		boolean result = false;

		if (memberGroupId != null) {
			MemberGroup memberGroup = get(memberGroupId);

			if (memberGroup != null) {
				SubscriptionStatus status = new SubscriptionStatus();
				status.setStatusId(MemberGroup.MEMBER_GROUP_ACTIVE);
				memberGroup.setStatus(status);
				update(memberGroup, actionUser);
				result = true;
			}
		}
		return result;
	}

	public Collection<MemberGroup> searchGroup(String keyword) throws Exception {
		// TODO Auto-generated method stub

		Collection<MemberGroup> result = new Vector<MemberGroup>();

		try {

			String[] likeParam = {"groupName"};
			Object[] likeValue = {keyword};
			
			String[] eqParam = {"deletedStatus"};
			Object[] eqValue = {0};
			
			result = search(likeParam,likeValue,eqParam,eqValue,0,10);
			
			/**
			
			Session session = memberGroupDao.getGroupSession();

			String query = "SELECT m FROM MemberGroup m WHERE (m.groupName LIKE :keyword) "
					+ "AND m.deletedStatus = 0 ORDER BY m.groupName ASC";

			Query q = session.createQuery(query);
			q.setString("keyword",  "%" + keyword + "%");
			q.setMaxResults(10);

			result = q.list();
			*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	
	public Collection<MemberGroup> searchOutstandingExcessGroup(String keyword)
			throws Exception {
		
		Collection<MemberGroup> result = new Vector<MemberGroup>();

		try {

			Session session = memberGroupDao.getGroupSession();

			String query = "SELECT m FROM MemberGroup m WHERE (m.groupName LIKE :keyword) "
					+ "AND m.deletedStatus = 0 AND m.memberGroupId IN (SELECT DISTINCT ex.memberId.memberGroupId.memberGroupId FROM ExcessCharge ex " +
							"WHERE ex.deletedStatus = 0 AND ex.excessLetterSent = 0 AND ex.excessChargeStatus.paymentStatusId = :status) ORDER BY m.groupName ASC";

			Query q = session.createQuery(query);
			q.setString("keyword",  "%" + keyword + "%");
			q.setInteger("status", PaymentStatus.PAYMENT_OPEN);
			q.setMaxResults(10);

			result = q.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	
	public ActionResult block(Integer memberGroupId, String reason,Date start, Date end,
			ActionUser actionUser) throws Exception {
		// TODO Auto-generated method stub
		MemberGroup member = get(memberGroupId);
		ActionResult result = new ActionResult();
		result.setActionCode("BLOCKMEMBERGROUP");
		result.setResult(false);
		
		
		if (member != null && member.getStatus().getStatusId().intValue() == SubscriptionStatus.ACTIVE){
			
			SubscriptionStatus block = new SubscriptionStatus();
			block.setStatusId(SubscriptionStatus.BLOCKED);
			member.setStatus(block);
			String previousReason = member.getSuspendReason() == null ? reason+"-"+new java.sql.Timestamp(System.currentTimeMillis()).toString() : member.getSuspendReason()+"|"+reason+"-"+new java.sql.Timestamp(System.currentTimeMillis()).toString();
			
			member.setSuspendReason(previousReason);
			update(member,actionUser);
			
			ActivityLog log = new ActivityLog();
			log.setAction("BLOCK GROUP");
			log.setDescription("BLOCK MEMBER GROUP " + member.getGroupName() + " reason : " + reason);
			log.setActivityLogTime(new java.sql.Timestamp(System.currentTimeMillis()));
			log.setIpAddress(actionUser.getIpAddress());
			log.setMemberGroupId(member);
			
			String username = "";
			if (actionUser != null && actionUser.getUser() != null){
				username = actionUser.getUser().getUsername();
			}
			
			log.setUsername(username);
			
			activityLogService.create(log);
			
			
			result.setResultObject(member);
			result.setResult(true);
			result.setAdditionalMessage("Sukses melakukan block group");
		}
		
		
		return result;
	}

	
	public ActionResult unblock(Integer memberGroupId, String reason,
			ActionUser actionUser) throws Exception {
		MemberGroup member = get(memberGroupId);
		ActionResult result = new ActionResult();
		result.setActionCode("UNBLOCKMEMBERGROUP");
		result.setResult(false);
		
		
		if (member != null && member.getStatus().getStatusId().intValue() == SubscriptionStatus.BLOCKED){
			
			SubscriptionStatus block = new SubscriptionStatus();
			block.setStatusId(SubscriptionStatus.ACTIVE);
			member.setStatus(block);

			String previousReason = member.getUnsuspendReason() == null ? reason+"-"+new java.sql.Timestamp(System.currentTimeMillis()).toString() 
					: member.getUnsuspendReason()+"|"+reason+"-"+new java.sql.Timestamp(System.currentTimeMillis()).toString();
			
			member.setUnsuspendReason(previousReason);
			update(member,actionUser);
			
			ActivityLog log = new ActivityLog();
			log.setAction("UNBLOCKMEMBERGROUP");
			log.setDescription("UNBLOCK MEMBER GROUP " + member.getGroupName() + " reason : " + reason);
			log.setActivityLogTime(new java.sql.Timestamp(System.currentTimeMillis()));
			log.setIpAddress(actionUser.getIpAddress());
			
			String username = "";
			if (actionUser != null && actionUser.getUser() != null){
				username = actionUser.getUser().getUsername();
			}
			
			log.setUsername(username);
			
			activityLogService.create(log);
			
			
			
			result.setResultObject(member);
			result.setResult(true);
			result.setAdditionalMessage("Sukses melakukan unblock member");
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public Collection<MemberGroup> getMemberGroupByClient(Integer clientId) throws Exception {//buatan luthfi untuk menampilkan data membergroup berdasarkan client id
		// TODO Auto-generated method stub
		Collection<MemberGroup> result = new Vector<MemberGroup>();
		
		try {
			Session session = memberGroupDao.getGroupSession();
			
			String query = "SELECT m FROM MemberGroup m WHERE m.deletedStatus = 0 and m.clientId.deletedStatus = 0 and m.clientId.clientId = ? ";
			
			Query q = session.createQuery(query);
			q.setInteger(0, clientId);
			
			result = q.list();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}

	
	public MemberGroup getMemberGroupByCode(String groupCode) throws Exception {
		// TODO Auto-generated method stub
		MemberGroup result = null;
		
		try {
			String[] eqParam = {"memberGroupCode","deletedStatus"};
			Object[] eqValue = {groupCode,Integer.valueOf(0)};
			
			Collection<MemberGroup> groupList = search(null,null,eqParam,eqValue,0,1);
			
			if (groupList != null){
				Iterator<MemberGroup> iterator = groupList.iterator();
				
				if (iterator.hasNext()){
					result = iterator.next();
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		return result;
	}

	// class+

	// class-

}
