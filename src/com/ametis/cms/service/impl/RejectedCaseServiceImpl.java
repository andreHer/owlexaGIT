package com.ametis.cms.service.impl;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.joda.time.DateTime;

import com.ametis.cms.dao.RejectedCaseDao;
import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.Case;
import com.ametis.cms.datamodel.CaseStatus;
import com.ametis.cms.datamodel.Client;
import com.ametis.cms.datamodel.Configuration;
import com.ametis.cms.datamodel.CostContainment;
import com.ametis.cms.datamodel.CostContainmentType;
import com.ametis.cms.datamodel.RejectedCase;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.CaseService;
import com.ametis.cms.service.ClientService;
import com.ametis.cms.service.ConfigurationService;
import com.ametis.cms.service.CostContainmentService;
import com.ametis.cms.service.RejectedCaseService;
import com.ametis.cms.util.dao.DaoSupportUtil;

// imports+ 

// imports- 

/**
 * RejectedCase is a servlet controller for rejected_case Table. All you have to
 * do is to convert necessary data field to the named parameter
 */
public class RejectedCaseServiceImpl implements RejectedCaseService

// extends+

// extends-
{

	private RejectedCaseDao rejectedCaseDao;

	private CaseService caseService;

	private CostContainmentService costContainmentService;
	private ActivityLogService activityLogService;
	private ConfigurationService configurationService;
	private ClientService clientService;
	
	
	
	public ClientService getClientService() {
		return clientService;
	}
	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}
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
	public void setRejectedCaseDao(RejectedCaseDao object) {
		this.rejectedCaseDao = object;
	}

	public RejectedCaseDao getRejectedCaseDao() {
		return this.rejectedCaseDao;
	}

	public CaseService getCaseService() {
		return caseService;
	}

	public void setCaseService(CaseService caseService) {
		this.caseService = caseService;
	}

	public CostContainmentService getCostContainmentService() {
		return costContainmentService;
	}

	public void setCostContainmentService(
			CostContainmentService costContainmentService) {
		this.costContainmentService = costContainmentService;
	}

	/*
	 * Method create (RejectedCase object) berfungsi untuk melakukan penambahan
	 * sebuah object kedalam database @param object adalah sebuah object yang
	 * ingin diubah @return object hasil kreasi,lengkap dengan assigned primary
	 * key, exception jika gagal
	 */
	public RejectedCase create(RejectedCase object, ActionUser actionUser) throws Exception {


		object.setCreatedTime(new java.sql.Timestamp(System.currentTimeMillis()));

		int numberCounter = 0;
		
		Configuration configuration = configurationService.getClientConfiguration(object.getCaseId().getMemberId().getClientId().getClientId());
		 
		 if (configuration == null){
			 configuration = configurationService.getSystemConfiguration();
		 }
		 
		 Integer isUsingSequence = configuration.getIsUsingSequenceNumber();
			
			if (isUsingSequence != null && isUsingSequence.intValue() == 1){
				String seqSQL = configuration.getCaseRejectSeqName();
				
				Session session = rejectedCaseDao.getRejectionSession();
				
				if (session != null){
					SQLQuery q = session.createSQLQuery(seqSQL);
					
					List<Object> list = q.list();
					
					if (list != null && !list.isEmpty()){
						Iterator<Object> iterator = list.iterator();
						
						if (iterator.hasNext()){
							Object nextval = iterator.next();
							
							if (nextval != null){
								BigInteger val = (BigInteger) nextval;
								numberCounter = val.intValue();
							}
						}
					}
				}
			}
			else {
				numberCounter = configuration.getCaseRejectionNumber();
			}
		System.out.println("TESTING CREATE REJECTED CASE");
		object.setDeletedStatus(new Integer(0));
		
		if (actionUser != null){					
			User user = actionUser.getUser();
			if (user != null) {
				object.setCreatedBy(user.getUsername());
			}
		}

		DateTime dateTime = new DateTime();
		

		int month = dateTime.getMonthOfYear();
		String monthStr = "";
		if (month < 10) {
			monthStr = "0"+month;
		}
		else {
			monthStr = month+"";
		}
		
		String number = configuration.getCaseRejectionNumberTemplate();
		String counter = generateCounterNumber(numberCounter);

		number = number.replace("${counter}", counter);
		number = number.replace("${month}", monthStr);
		number = number.replace("${year}", dateTime.getYear()+"");
		
		object.setRejectionNumber(number);

		RejectedCase result = rejectedCaseDao.create(object);

		if (result != null) {

			Case theCase = result.getCaseId();
			if (theCase != null) {
				/* remove by aju on 20150827, ngapain 2x reject zzzzz udah di handle sama caseService.reject()
				theCase = caseService.get(theCase.getCaseId());

				CaseStatus caseStatus = new CaseStatus();
				caseStatus.setCaseStatusId(Case.CASE_REJECT);
				theCase.setCaseStatusId(caseStatus);
*/
				caseService.reject(theCase.getCaseId(), result.getRejectionDescription(), actionUser);

				CostContainment costContainment = new CostContainment();
				costContainment.setCaseId(theCase);
				costContainment.setCostContainmentValue(object
						.getEstimatedCost());

				costContainment.setDescription(result.getRejectionNumber() + " DESC : " + result.getRejectionDescription());
				CostContainmentType costContainmentType = new CostContainmentType();
				costContainmentType.setCostContainmentTypeId(Integer
						.valueOf(CostContainmentType.CASE_REJECTION));
				
				
				
				costContainment.setClientId(theCase.getMemberId().getClientId());

				costContainment.setCostContainmentType(costContainmentType);

				costContainmentService.create(costContainment, actionUser);

			}

			configuration.setCaseRejectionNumber(configuration.getCaseRejectionNumber()+1);
			configurationService.update(configuration, actionUser);
		}
		System.out.println("SELESAI CREATE REJECTED CASE");
		return result;
	}

	private String generateCounterNumber (int res){
		
		String result = "";
		
		if (res > 0 && res < 10){
			result = "0000"+res;
		}
		else if (res >= 10 && res < 100){
			result = "000"+res;
		}
		else if (res >= 100 && res < 1000){
			result = "00"+res;
		}
		else if (res >= 1000 && res < 10000){
			result = "0"+res;
		}
		else if (res >= 10000){
			result = ""+res;
		}
			
		
		return result;
	}
	/*
	 * Method update (RejectedCase object) berfungsi untuk melakukan perubahan
	 * terhadap sebuah object yang terdapat didalam database @param object
	 * adalah sebuah object yang ingin diubah @return object hasil update,
	 * exception jika gagal
	 */
	public RejectedCase update(RejectedCase object, ActionUser actionUser) throws Exception {

		// object.setUpdatedDate (new java.sql.Date
		// (System.currentTimeMillis()));

		object.setModifiedTime(new java.sql.Timestamp(System
				.currentTimeMillis()));

		/*
		 * Gue tambahin mekanisme NULL value checking just in case user nya null
		 */
if (actionUser != null){
			
			User user = actionUser.getUser();		
		if (user != null) {
			object.setModifiedBy(user.getUsername());
		}
}
		rejectedCaseDao.update(object);
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
	public RejectedCase trash(java.io.Serializable pkey) throws Exception {
		RejectedCase object = rejectedCaseDao.get(pkey);
		rejectedCaseDao.delete(object);
		return object;
	}

	/*
	 * Method delete (RejectedCase object) berfungsi untuk melakukan penghapusan
	 * terhadap sebuah object yang terdapat didalam database @param object
	 * adalah sebuah object yang ingin dihapus, isi dari object tersebut cukup
	 * dengan mengisi field-field primary key @return updated object, exception
	 * if failed
	 */

	public RejectedCase delete(java.io.Serializable pkey, ActionUser actionUser)
			throws Exception {
		RejectedCase object = rejectedCaseDao.get(pkey);

		// object.setDeletedDate (new java.sql.Date
		// (System.currentTimeMillis()));

		object.setDeletedStatus(new Integer(1));
if (actionUser != null){
			
			User user = actionUser.getUser();
		if (user != null) {
			object.setDeletedBy(user.getUsername());
		}
}

		rejectedCaseDao.update(object);
		return object;
	}

	/*
	 * Method delete (RejectedCase object) berfungsi untuk melakukan penghapusan
	 * terhadap sebuah object yang terdapat didalam database @param object
	 * adalah sebuah object yang ingin dihapus, isi dari object tersebut cukup
	 * dengan mengisi field-field primary key @return updated object, exception
	 * if failed
	 */

	public RejectedCase delete(RejectedCase object, ActionUser actionUser) throws Exception {

		// object.setDeletedDate (new java.sql.Date
		// (System.currentTimeMillis()));

		object.setDeletedStatus(new Integer(1));
if (actionUser != null){
			
			User user = actionUser.getUser();
		if (user != null) {
			object.setDeletedBy(user.getUsername());
		}
}
		rejectedCaseDao.update(object);
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
	public RejectedCase get(java.io.Serializable pkey) throws Exception {
		RejectedCase object = null;
		object = rejectedCaseDao.get(pkey);
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

	public RejectedCase get(java.io.Serializable pkey, String[] required)
			throws Exception {
		RejectedCase object = null;
		object = rejectedCaseDao.get(pkey);
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

		Criteria c = rejectedCaseDao.getCriteria();
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

		Criteria c = rejectedCaseDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			RejectedCase element = (RejectedCase) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}

		return list;

	}

	// --req end

	public Collection search(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, boolean asc,
			String columnOrder[], int index, int offset) throws Exception {

		Criteria c = rejectedCaseDao.getCriteria();
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
		Criteria c = rejectedCaseDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			RejectedCase element = (RejectedCase) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}

		return list;

	}

	// --req end

	public Collection search(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, boolean asc,
			String columnOrder, int index, int offset) throws Exception {

		Criteria c = rejectedCaseDao.getCriteria();
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
		Criteria c = rejectedCaseDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			RejectedCase element = (RejectedCase) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}

		return list;

	}

	// --req end

	// -- 1b

	public Collection search(String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, int index, int offset)
			throws Exception {

		Criteria c = rejectedCaseDao.getCriteria();
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
		Criteria c = rejectedCaseDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			RejectedCase element = (RejectedCase) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}

		return list;

	}

	// --req end

	public Collection search(String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, boolean asc,
			String columnOrder[], int index, int offset) throws Exception {

		Criteria c = rejectedCaseDao.getCriteria();
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

		Criteria c = rejectedCaseDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			RejectedCase element = (RejectedCase) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;

	}

	// req end

	public Collection search(String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, boolean asc, String columnOrder,
			int index, int offset) throws Exception {

		Criteria c = rejectedCaseDao.getCriteria();
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

		Criteria c = rejectedCaseDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			RejectedCase element = (RejectedCase) iter.next();
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

		Criteria c = rejectedCaseDao.getCriteria();
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

		Criteria c = rejectedCaseDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil
				.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			RejectedCase element = (RejectedCase) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}

	// req end

	public Collection search(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, String[] btwnColumns,
			Object[] btwnParams1, Object[] btwnParams2, boolean asc,
			String columnOrder[], int index, int offset) throws Exception {

		Criteria c = rejectedCaseDao.getCriteria();
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

		Criteria c = rejectedCaseDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil
				.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			RejectedCase element = (RejectedCase) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}

	// req end

	public Collection search(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, String[] btwnColumns,
			Object[] btwnParams1, Object[] btwnParams2, boolean asc,
			String columnOrder, int index, int offset) throws Exception {

		Criteria c = rejectedCaseDao.getCriteria();
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

		Criteria c = rejectedCaseDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil
				.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			RejectedCase element = (RejectedCase) iter.next();
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

		Criteria c = rejectedCaseDao.getCriteria();
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

		Criteria c = rejectedCaseDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil
				.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			RejectedCase element = (RejectedCase) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}

	// req end

	public Collection search(String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, String btwnColumns,
			Object btwnParams1, Object btwnParams2, boolean asc,
			String columnOrder[], int index, int offset) throws Exception {

		Criteria c = rejectedCaseDao.getCriteria();
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

		Criteria c = rejectedCaseDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil
				.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			RejectedCase element = (RejectedCase) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}

	// req end

	public Collection search(String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, String btwnColumns,
			Object btwnParams1, Object btwnParams2, boolean asc,
			String columnOrder, int index, int offset) throws Exception {

		Criteria c = rejectedCaseDao.getCriteria();
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

		Criteria c = rejectedCaseDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil
				.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			RejectedCase element = (RejectedCase) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}

	// req end

	// -- search end

	// -- get total

	public int getTotal(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams) throws Exception {

		Criteria c = rejectedCaseDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}

	public int getTotal(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, String[] btwnColumns,
			Object[] btwnParams1, Object[] btwnParams2) throws Exception {

		Criteria c = rejectedCaseDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil
				.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}

	public int getTotal(String likeColumns, Object likeParams,
			String eqColumns, Object eqParams) throws Exception {

		Criteria c = rejectedCaseDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}

	public int getTotal(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, String btwnColumns,
			Object btwnParams1, Object btwnParams2) throws Exception {

		Criteria c = rejectedCaseDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil
				.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}

	// ------------------------------------------------------------------
	public int getTotal() throws Exception {
		Criteria c = rejectedCaseDao.getCriteria();
		int total = DaoSupportUtil.getTotal(c);
		return total;
	}

	// -- get total end

	// ---------------------------------------------------
	public Collection getAll(String[] required) throws Exception {

		Criteria c = rejectedCaseDao.getCriteria();
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			RejectedCase element = (RejectedCase) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;

	}

	public Collection getAll() throws Exception {

		Criteria c = rejectedCaseDao.getCriteria();
		List list = c.list();
		return list;
	}

	// -------------------------------------------------

	// unique Result

	public RejectedCase searchUnique(String eqColumns, Object eqParams,
			String[] required) throws Exception {
		Criteria c = rejectedCaseDao.getCriteria();
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		RejectedCase obj = (RejectedCase) c.uniqueResult();
		DaoSupportUtil.lazyInit(required, obj);
		return obj;
	}

	public RejectedCase searchUnique(String eqColumns, Object eqParams)
			throws Exception {
		Criteria c = rejectedCaseDao.getCriteria();
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		RejectedCase obj = (RejectedCase) c.uniqueResult();
		return obj;
	}

	// -----------------------------------------------

	/*
	 * BASIC IMPLEMENTATION !! USE WITH CAUTION ! USE IT IF NO OTHER OPTION LEFT
	 * @return criteria
	 */
	public Criteria getCriteria() throws Exception {
		Criteria c = rejectedCaseDao.getCriteria();
		return c;
	}

	public DetachedCriteria getDetachedCriteria() throws Exception {
		DetachedCriteria dc = rejectedCaseDao.getDetachedCriteria();
		return dc;
	}
	
	public RejectedCase getCaseRejection(Integer caseId) throws Exception {
		// TODO Auto-generated method stub
		RejectedCase result = null;
		try {
			
			String[] eqParam = {"caseId.caseId"};
			Object[] eqValue = {caseId};
			
			Collection<RejectedCase> collections = search (null,null,eqParam,eqValue,0,1);
			
			if (collections != null && collections.size() > 0){
				Iterator<RejectedCase> iterator = collections.iterator();
				if(iterator.hasNext()){
					result = iterator.next();
				}
			}
			
			
		}catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}

	// class+

	// class-
}
