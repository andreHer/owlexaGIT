package com.ametis.cms.service.impl;

import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;

import com.ametis.cms.dao.ClientDao;
import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.CaseStatus;
import com.ametis.cms.datamodel.Client;
import com.ametis.cms.datamodel.Configuration;
import com.ametis.cms.datamodel.SubscriptionStatus;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.ClientService;
import com.ametis.cms.service.ConfigurationService;
import com.ametis.cms.service.NumberCounterService;
import com.ametis.cms.util.dao.DaoSupportUtil;


// imports+ 

// imports- 

/**
 * Client is a servlet controller for client Table. All you have to do is to
 * convert necessary data field to the named parameter
 */
public class ClientServiceImpl implements ClientService

// extends+

// extends-

{

	private ClientDao clientDao;
	private ActivityLogService activityLogService;
	private ConfigurationService configurationService;
	private NumberCounterService numberCounterService;

	
	public ConfigurationService getConfigurationService() {
		return configurationService;
	}

	public void setConfigurationService(ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}

	public NumberCounterService getNumberCounterService() {
		return numberCounterService;
	}

	public void setNumberCounterService(NumberCounterService numberCounterService) {
		this.numberCounterService = numberCounterService;
	}

	public ActivityLogService getActivityLogService() {
		return activityLogService;
	}

	public void setActivityLogService(ActivityLogService activityLogService) {
		this.activityLogService = activityLogService;
	}

	public void setClientDao(ClientDao object) {
		this.clientDao = object;
	}

	public ClientDao getClientDao() {
		return this.clientDao;
	}

	/*
	 * Method create (Client object) berfungsi untuk melakukan penambahan sebuah
	 * object kedalam database @param object adalah sebuah object yang ingin
	 * diubah @return object hasil kreasi,lengkap dengan assigned primary key,
	 * exception jika gagal
	 */
	public Client create(Client object, ActionUser actionUser) throws Exception {

		// object.setCreatedDate (new java.sql.Date
		// (System.currentTimeMillis()));

		// ----

		object.setCreatedTime(new java.sql.Timestamp(System.currentTimeMillis()));
		object.setDeletedStatus(Integer.valueOf(0));

		if (actionUser != null) {
			User user = actionUser.getUser();

			if (user != null) {
				object.setCreatedBy(user.getUsername());
			}
		}

		Client result = clientDao.create(object);
		Configuration configuration = new Configuration();
		configuration.setClientId(result);
		configuration.setBatchNumber(1);
		configuration.setCallNumber(1);
		configuration.setCaseRejectionNumber(1);
		configuration.setCdvNumber(1);
		configuration.setClaimNumber(1);
		configuration.setClaimPendingNumber(1);
		configuration.setCaseNumber(1);
		configuration.setClaimRejectionNumber(1);
		configuration.setExcessNumber(1);
		configuration.setFundNumber(1);
		
		configuration.setBankAccount(result.getClientBankAccount());
		configuration.setBankAccountName(result.getAccountName());
		configuration.setBankBranch(result.getBankBranch());
		configuration.setBankName(result.getBankName());
		configuration.setCompany(result.getClientName());
		configuration.setCompanyCode(result.getClientCode());
		
		String code = result.getClientCode() == null ? "" : result.getClientCode().toUpperCase();
		
		configuration.setCallNumberTemplate("${counter}/FC/"+code+"/${year}");
		configuration.setCaseNumberTemplate("${counter}/GL/"+code+"/${year}");
		configuration.setCaseRejectionNumberTemplate("${counter}/CASE/REJECT/"+code+"/${year}");
		configuration.setClaimRejectionNumberTemplate("${counter}/CL/REJECT/"+code+"/${year}");
		configuration.setClaimPendingNumberTemplate("${counter}/CL/PEND/"+code+"/${year}");
		configuration.setClaimNumberTemplate("${counter}/C/"+code+"/${ctype}/${category}/${year}");
		configuration.setExcessNumberTemplate("${counter}/EX/"+code+"/${year}");
		configuration.setFundNumberTemplate("${counter}/FF/"+code+"/${fundType}/${year}");
		configuration.setPaymentNumberTemplate("${counter}/PAY/"+code+"/${year}");
		configuration.setBatchNumberTemplate("${counter}/BATCH/"+code+"/${recipient}/${year}");
		configuration.setInvoiceNumberTemplate("${counter}/INVOICE/"+code+"/${month}/${year}");
		
		configuration.setIsUsingSequenceNumber(1);
		configuration.setBatchNumberSeqName("select nextval('batch_num_"+code+"_seq')");
		configuration.setClaimNumberSeqName("select nextval('claim_num_"+code+"_seq')");
		configuration.setCaseNumberSeqName("select nextval('case_num_"+code+"_seq')");
		configuration.setFundNumberSeqName("select nextval('fund_num_"+code+"_seq')");
		configuration.setCallNumberSeqName("select nextval('call_num_"+code+"_seq')");
		configuration.setCaseRejectSeqName("select nextval('case_reject_num_"+code+"_seq')");
		configuration.setClaimPendingSeqName("select nextval('claim_pending_num_"+code+"_seq')");
		configuration.setClaimRejectSeqName("select nextval('claim_reject_num_"+code+"_seq')");
		
		configuration.setInvoiceNumberSeqName("select nextval('invoice_num_"+code+"_seq')");
		configuration.setCdvNumberSeqName("select nextval('cdv_num_"+code+"_seq')");
		configuration.setExcessNumberSeqName("select nextval('excess_num_"+code+"_seq')");
		configuration.setCardNumberSeqName("card_num_"+code+"_seq");
		
		configuration.setUploadStorageDirectory("/usr/local/document/upload/"+code);
		configuration.setSwipeCardPrefix("100062"+code);		
		
		configuration.setMemberStoragePath("/usr/local/document/upload/"+code+"/member");
		configuration.setReportPath("/usr/local/document/upload/"+code+"/report");
		configuration.setPolicyTermConditionPath("/usr/local/document/upload/"+code+"/tc");
		
		
		configurationService.create(configuration, actionUser);
		
		java.io.File rootDoc = new java.io.File("/usr/local/document/upload/"+code);
		if (!rootDoc.exists()){
			rootDoc.mkdirs();
		}
		rootDoc = new java.io.File("/usr/local/document/upload/"+code+"/member");
		if (!rootDoc.exists()){
			rootDoc.mkdirs();
		}
		rootDoc = new java.io.File("/usr/local/document/upload/"+code+"/report");
		if (!rootDoc.exists()){
			rootDoc.mkdirs();
		}
		 rootDoc = new java.io.File("/usr/local/document/upload/"+code+"/tc");
		if (!rootDoc.exists()){
			rootDoc.mkdirs();
		}
		return result;
	}

	/*
	 * Method update (Client object) berfungsi untuk melakukan perubahan
	 * terhadap sebuah object yang terdapat didalam database @param object
	 * adalah sebuah object yang ingin diubah @return object hasil update,
	 * exception jika gagal
	 */
	public Client update(Client object, ActionUser actionUser) throws Exception {

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

		clientDao.update(object);
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
	public Client trash(java.io.Serializable pkey) throws Exception {
		Client object = clientDao.get(pkey);
		clientDao.delete(object);
		return object;
	}

	/*
	 * Method delete (Client object) berfungsi untuk melakukan penghapusan
	 * terhadap sebuah object yang terdapat didalam database @param object
	 * adalah sebuah object yang ingin dihapus, isi dari object tersebut cukup
	 * dengan mengisi field-field primary key @return updated object, exception
	 * if failed
	 */

	public Client delete(java.io.Serializable pkey, ActionUser actionUser)
			throws Exception {
		Client object = clientDao.get(pkey);

		// object.setDeletedDate (new java.sql.Date
		// (System.currentTimeMillis()));
		object.setDeletedTime(new java.sql.Timestamp(System.currentTimeMillis()));

		object.setDeletedStatus(new Integer(1));

		if (actionUser != null) {
			User user = actionUser.getUser();
			if (user != null) {
				object.setDeletedBy(user.getUsername());
			}
		}

		clientDao.update(object);
		return object;
	}

	/*
	 * Method delete (Client object) berfungsi untuk melakukan penghapusan
	 * terhadap sebuah object yang terdapat didalam database @param object
	 * adalah sebuah object yang ingin dihapus, isi dari object tersebut cukup
	 * dengan mengisi field-field primary key @return updated object, exception
	 * if failed
	 */

	public Client delete(Client object, ActionUser actionUser) throws Exception {

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

		clientDao.update(object);
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
	public Client get(java.io.Serializable pkey) throws Exception {
		Client object = null;
		object = clientDao.get(pkey);
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

	public Client get(java.io.Serializable pkey, String[] required)
			throws Exception {
		Client object = null;
		object = clientDao.get(pkey);
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

		Criteria c = clientDao.getCriteria();
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

		Criteria c = clientDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Client element = (Client) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}

		return list;

	}

	// --req end
	
	//start search order by
	public Collection search(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, String[] required, String columnOrder, boolean asc,
			int index, int offset) throws Exception {

		Criteria c = clientDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Client element = (Client) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}

		return list;

	}
	//end search order by

	public Collection search(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, boolean asc,
			String columnOrder[], int index, int offset) throws Exception {

		Criteria c = clientDao.getCriteria();
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
		Criteria c = clientDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Client element = (Client) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}

		return list;

	}

	// --req end

	public Collection search(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, boolean asc,
			String columnOrder, int index, int offset) throws Exception {

		Criteria c = clientDao.getCriteria();
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
		Criteria c = clientDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Client element = (Client) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}

		return list;

	}

	// --req end

	// -- 1b

	public Collection search(String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, int index, int offset)
			throws Exception {

		Criteria c = clientDao.getCriteria();
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
		Criteria c = clientDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Client element = (Client) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}

		return list;

	}

	// --req end

	public Collection search(String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, boolean asc,
			String columnOrder[], int index, int offset) throws Exception {

		Criteria c = clientDao.getCriteria();
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

		Criteria c = clientDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Client element = (Client) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;

	}

	// req end

	public Collection search(String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, boolean asc, String columnOrder,
			int index, int offset) throws Exception {

		Criteria c = clientDao.getCriteria();
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

		Criteria c = clientDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Client element = (Client) iter.next();
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

		Criteria c = clientDao.getCriteria();
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

		Criteria c = clientDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil
				.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Client element = (Client) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}

	// req end

	public Collection search(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, String[] btwnColumns,
			Object[] btwnParams1, Object[] btwnParams2, boolean asc,
			String columnOrder[], int index, int offset) throws Exception {

		Criteria c = clientDao.getCriteria();
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

		Criteria c = clientDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil
				.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Client element = (Client) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}

	// req end

	public Collection search(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, String[] btwnColumns,
			Object[] btwnParams1, Object[] btwnParams2, boolean asc,
			String columnOrder, int index, int offset) throws Exception {

		Criteria c = clientDao.getCriteria();
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

		Criteria c = clientDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil
				.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Client element = (Client) iter.next();
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

		Criteria c = clientDao.getCriteria();
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

		Criteria c = clientDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil
				.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Client element = (Client) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}

	// req end

	public Collection search(String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, String btwnColumns,
			Object btwnParams1, Object btwnParams2, boolean asc,
			String columnOrder[], int index, int offset) throws Exception {

		Criteria c = clientDao.getCriteria();
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

		Criteria c = clientDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil
				.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Client element = (Client) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}

	// req end

	public Collection search(String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, String btwnColumns,
			Object btwnParams1, Object btwnParams2, boolean asc,
			String columnOrder, int index, int offset) throws Exception {

		Criteria c = clientDao.getCriteria();
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

		Criteria c = clientDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil
				.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Client element = (Client) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}

	// req end

	// -- search end

	// -- get total

	public int getTotal(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams) throws Exception {

		Criteria c = clientDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}

	public int getTotal(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, String[] btwnColumns,
			Object[] btwnParams1, Object[] btwnParams2) throws Exception {

		Criteria c = clientDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil
				.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}

	public int getTotal(String likeColumns, Object likeParams,
			String eqColumns, Object eqParams) throws Exception {

		Criteria c = clientDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}

	public int getTotal(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, String btwnColumns,
			Object btwnParams1, Object btwnParams2) throws Exception {

		Criteria c = clientDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil
				.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}

	// ------------------------------------------------------------------
	public int getTotal() throws Exception {
		Criteria c = clientDao.getCriteria();
		int total = DaoSupportUtil.getTotal(c);
		return total;
	}

	// -- get total end

	// ---------------------------------------------------
	public Collection getAll(String[] required) throws Exception {

		Criteria c = clientDao.getCriteria();
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Client element = (Client) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;

	}

	public Collection getAll() throws Exception {

		Criteria c = clientDao.getCriteria();
		List list = c.list();
		return list;
	}
	
	public Collection getAll(String[] eqColumns, Object[] eqParams) throws Exception {

		Criteria c = clientDao.getCriteria();
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		List list = c.list();
		return list;
	}

	// -------------------------------------------------

	// unique Result

	public Client searchUnique(String eqColumns, Object eqParams,
			String[] required) throws Exception {
		Criteria c = clientDao.getCriteria();
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		Client obj = (Client) c.uniqueResult();
		DaoSupportUtil.lazyInit(required, obj);
		return obj;
	}

	public Client searchUnique(String eqColumns, Object eqParams)
			throws Exception {
		Criteria c = clientDao.getCriteria();
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		Client obj = (Client) c.uniqueResult();
		return obj;
	}
	
	public Client searchUnique (String[] eqColumns, Object[] eqParams, int index, int offset)
			throws Exception {
		Criteria c = clientDao.getCriteria();
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		Client obj = (Client) c.uniqueResult();
		return obj;
	}

	// -----------------------------------------------

	/*
	 * BASIC IMPLEMENTATION !! USE WITH CAUTION ! USE IT IF NO OTHER OPTION LEFT
	 * @return criteria
	 */
	public Criteria getCriteria() throws Exception {
		Criteria c = clientDao.getCriteria();
		return c;
	}

	public DetachedCriteria getDetachedCriteria() throws Exception {
		DetachedCriteria dc = clientDao.getDetachedCriteria();
		return dc;
	}

	public Client activate(Serializable pkey, ActionUser actionUser)
			throws Exception {
		// TODO Auto-generated method stub

		Client client = null;
		if (pkey != null) {
			client = clientDao.get(pkey);

			if (client != null) {
				SubscriptionStatus subscriptionStatus = new SubscriptionStatus();
				subscriptionStatus.setStatusId(SubscriptionStatus.ACTIVE);
				client.setStatusId(subscriptionStatus);
				client = update(client, actionUser);
			}

		}
		return client;
	}

	public Client terminate(Serializable pkey, ActionUser actionUser)
			throws Exception {
		// TODO Auto-generated method stub

		Client client = null;

		if (pkey != null) {
			client = clientDao.get(pkey);

			if (client != null) {
				SubscriptionStatus subscriptionStatus = new SubscriptionStatus();
				subscriptionStatus.setStatusId(SubscriptionStatus.TERMINATED);
				client.setStatusId(subscriptionStatus);
				client = update(client, actionUser);
			}

		}
		return client;

	}

	public Client renewal(Serializable pkey, Date contractStart,
			Date contractEnd, ActionUser actionUser) throws Exception {
		// TODO Auto-generated method stub
		Client client = null;
		if (pkey != null) {
			client = clientDao.get(pkey);

			if (client != null) {

				client.setRenewalDate(contractStart);
				client.setExpireDate(contractEnd);

				SubscriptionStatus subscriptionStatus = new SubscriptionStatus();
				subscriptionStatus.setStatusId(SubscriptionStatus.ACTIVE);
				client.setStatusId(subscriptionStatus);
				client = update(client, actionUser);
			}

		}
		return client;
	}

	public Collection<Client> searchClient(String q) throws Exception {
		// TODO Auto-generated method stub

		Collection<Client> result = new Vector<Client>();
		try {
			
			String[] likeParam = {"clientName"};
			Object[] likeValue = {q};
			
			String[] eqParam = {"deletedStatus","statusId.statusId"};
			Object[] eqValue = {0,SubscriptionStatus.ACTIVE};
			
			result = search(likeParam,likeValue,eqParam,eqValue,0,10);
			
			/**
			Session session = clientDao.getClientSession();

			String sqlQuery = "SELECT c FROM "
					+ "Client c WHERE c.clientName LIKE :keyword AND c.deletedStatus = 0 ORDER BY c.clientName ASC";

			Query query = session.createQuery(sqlQuery);
			query.setString("keyword", "%" + q + "%");
			query.setMaxResults(10);
			
			
			result = query.list();
			*/
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;

	}

	public Collection<Client> searchRequiredClientFund() throws Exception {
		// TODO Auto-generated method stub

		Collection<Client> result = new Vector<Client>();
		try {
			Session session = clientDao.getClientSession();

			String sqlQuery = "SELECT c FROM "
					+ "Client c WHERE c.clientFundValue <= c.minimumFundValue AND c.isUsingFloatingFund = 1 " +
							" AND c.deletedStatus = 0 ORDER BY c.clientName ASC";

			Query query = session.createQuery(sqlQuery);
						
			result = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;

	}

	public Client getClient(String clientCode) throws Exception {
		// TODO Auto-generated method stub
		Client result = new Client();
		try {
			Session session = clientDao.getClientSession();

			String sqlQuery = "SELECT c FROM "
					+ "Client c WHERE c.clientCode = :code";

			Query query = session.createQuery(sqlQuery);
			query.setString("code", clientCode);
			query.setMaxResults(1);
			
			
			result = (Client) query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;

	}


	public int getTotalEmptyFundClient() throws Exception {
		// TODO Auto-generated method stub
		int result = 0;
		
		try {
			Session session = clientDao.getClientSession();
			
			if (session != null){
				String query = "SELECT count(c) FROM Client c WHERE c.minimumFundValue >= c.clientFundValue " +
						"AND c.isUsingFloatingFund = :usingFund " +
						"AND c.deletedStatus = 0";
				
				
				org.hibernate.Query q = session.createQuery(query);
				q.setInteger("usingFund",1);
				
				Number num = (Number) q.uniqueResult();
				result = num.intValue();				
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
