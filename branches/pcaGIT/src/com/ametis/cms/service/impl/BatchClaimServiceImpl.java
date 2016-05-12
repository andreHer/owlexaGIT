package com.ametis.cms.service.impl;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;

import com.ametis.cms.dao.BatchClaimDao;
import com.ametis.cms.dao.ClaimDao;
import com.ametis.cms.dao.PaymentDao;
import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.BatchClaim;
import com.ametis.cms.datamodel.CaseStatus;
import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.datamodel.ClaimReceiving;
import com.ametis.cms.datamodel.ClaimType;
import com.ametis.cms.datamodel.Configuration;
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.datamodel.MessageTemplate;
import com.ametis.cms.datamodel.Notification;
import com.ametis.cms.datamodel.Outstanding;
import com.ametis.cms.datamodel.Payment;
import com.ametis.cms.datamodel.PaymentRecipient;
import com.ametis.cms.datamodel.PaymentStatus;
import com.ametis.cms.datamodel.Policy;
import com.ametis.cms.datamodel.Provider;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.dto.PaymentReportDetail;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.BatchClaimService;
import com.ametis.cms.service.ClaimReceivingService;
import com.ametis.cms.service.ClientService;
import com.ametis.cms.service.ConfigurationService;
import com.ametis.cms.service.MemberGroupService;
import com.ametis.cms.service.MemberService;
import com.ametis.cms.service.NotificationService;
import com.ametis.cms.service.NumberCounterService;
import com.ametis.cms.service.OutstandingService;
import com.ametis.cms.service.ProviderService;
import com.ametis.cms.util.dao.DaoSupportUtil;

// imports+ 

// imports- 

/**
 * BatchClaim is a servlet controller for batch_claim Table. All you have to do
 * is to convert necessary data field to the named parameter
 */
public class BatchClaimServiceImpl implements BatchClaimService

// extends+

// extends-

{

	private ClaimDao claimDao;
	private BatchClaimDao batchClaimDao;

	private OutstandingService outstandingService;

	private ActivityLogService activityLogService;

	private NumberCounterService numberCounterService;

	private ClientService clientService;

	private ConfigurationService configurationService;

	private PaymentDao paymentDao;

	private NotificationService notificationService;
	private ProviderService providerService;
	private MemberService memberService;
	private MemberGroupService memberGroupService;
	private ClaimReceivingService claimReceivingService;

	public ClaimReceivingService getClaimReceivingService() {
		return claimReceivingService;
	}

	public void setClaimReceivingService(
			ClaimReceivingService claimReceivingService) {
		this.claimReceivingService = claimReceivingService;
	}

	public ProviderService getProviderService() {
		return providerService;
	}

	public void setProviderService(ProviderService providerService) {
		this.providerService = providerService;
	}

	public MemberService getMemberService() {
		return memberService;
	}

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	public MemberGroupService getMemberGroupService() {
		return memberGroupService;
	}

	public void setMemberGroupService(MemberGroupService memberGroupService) {
		this.memberGroupService = memberGroupService;
	}

	public NotificationService getNotificationService() {
		return notificationService;
	}

	public void setNotificationService(NotificationService notificationService) {
		this.notificationService = notificationService;
	}

	public ClaimDao getClaimDao() {
		return claimDao;
	}

	public void setClaimDao(ClaimDao claimDao) {
		this.claimDao = claimDao;
	}

	public PaymentDao getPaymentDao() {
		return paymentDao;
	}

	public void setPaymentDao(PaymentDao paymentDao) {
		this.paymentDao = paymentDao;
	}

	public ConfigurationService getConfigurationService() {
		return configurationService;
	}

	public void setConfigurationService(
			ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}

	public ClientService getClientService() {
		return clientService;
	}

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	public NumberCounterService getNumberCounterService() {
		return numberCounterService;
	}

	public void setNumberCounterService(
			NumberCounterService numberCounterService) {
		this.numberCounterService = numberCounterService;
	}

	public ActivityLogService getActivityLogService() {
		return activityLogService;
	}

	public void setActivityLogService(ActivityLogService activityLogService) {
		this.activityLogService = activityLogService;
	}

	public OutstandingService getOutstandingService() {
		return outstandingService;
	}

	public void setOutstandingService(OutstandingService outstandingService) {
		this.outstandingService = outstandingService;
	}

	public void setBatchClaimDao(BatchClaimDao object) {
		this.batchClaimDao = object;
	}

	public BatchClaimDao getBatchClaimDao() {
		return this.batchClaimDao;
	}

	public boolean refreshBatch(Serializable batchClaimId, ActionUser actionUser)
			throws Exception {
		boolean result = false;

		try {

			BatchClaim batchClaim = get(batchClaimId);

			if (batchClaim != null) {

				if (batchClaim.getBatchClaimStatus().getCaseStatusId()
						.intValue() == BatchClaim.PAYMENT_ISSUED) {

				} else if (batchClaim.getBatchClaimStatus().getCaseStatusId()
						.intValue() == BatchClaim.PAYMENT_ISSUED) {

				}

				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private String getBatchNumber(BatchClaim batchClaim) {

		String number = "";

		try {
			Calendar calendar = Calendar.getInstance();

			String month = "";
			String year = "";
			String jenisBatch = "";
			int counter = 0;

			Configuration configuration = configurationService
					.getClientConfiguration(batchClaim.getClientId()
							.getClientId());

			if (configuration == null) {
				configuration = configurationService.getSystemConfiguration();
			}
			Integer isUsingSequence = configuration.getIsUsingSequenceNumber();

			if (isUsingSequence != null && isUsingSequence.intValue() == 1) {
				String seqSQL = configuration.getBatchNumberSeqName();

				Session session = claimDao.getClaimSession();
				if (session != null) {
					SQLQuery q = session.createSQLQuery(seqSQL);

					List<Object> list = q.list();

					if (list != null && !list.isEmpty()) {
						Iterator<Object> iterator = list.iterator();

						if (iterator.hasNext()) {
							Object nextval = iterator.next();

							if (nextval != null) {

								BigInteger num = (BigInteger) nextval;
								if (num != null) {
									counter = num.intValue();
								}
							}
						}
					}
				}
			} else {
				counter = configuration.getBatchNumber();
			}

			if (calendar != null) {
				year = calendar.get(Calendar.YEAR) + "";
				// month = calendar.get(Calendar.MONTH);

				int monthInt = calendar.get(Calendar.MONTH) + 1;

				if (monthInt < 10) {
					month = "0" + monthInt;
				} else {
					month = "" + monthInt;
				}

			}

			String recipientCode = "";

			number = "/" + month + "/" + year;

			if (batchClaim != null) {
				ClaimType ctype = batchClaim.getBatchClaimType();

				if (ctype != null
						&& ctype.getClaimTypeId() == ClaimType.CASHLESS) {
					jenisBatch = "C";
				} else if (ctype != null
						&& ctype.getClaimTypeId() == ClaimType.REIMBURESEMENT) {
					jenisBatch = "R";
				} else if (ctype != null
						&& ctype.getClaimTypeId() == ClaimType.REIMBURSEMENT_KHUSUS) {
					jenisBatch = "RK";
				}
				PaymentRecipient recipient = batchClaim.getPaymentRecipient();

				if (recipient != null
						&& recipient.getPaymentRecipientId() == PaymentRecipient.MEMBER_GROUP) {
					MemberGroup group = batchClaim.getMemberGroupId();

					if (group != null) {
						// recipientCode = group.getMemberGroupCode();
						recipientCode = "MG";
					}
				} else if (recipient != null
						&& recipient.getPaymentRecipientId() == PaymentRecipient.PROVIDER) {
					Provider provider = batchClaim.getProviderId();

					if (provider != null) {
						// recipientCode = provider.getProviderCode();
						recipientCode = "P";
					}
				} else if (recipient != null
						&& recipient.getPaymentRecipientId() == PaymentRecipient.MEMBER) {
					recipientCode = "M";
				}
			}

			String counterStr = "";

			if (counter < 10) {
				counterStr = "000" + counter;
			} else if (counter >= 10 && counter < 100) {
				counterStr = "00" + counter;
			} else if (counter >= 100 && counter < 1000) {
				counterStr = "0" + counter;
			} else if (counter >= 1000) {
				counterStr = "" + counter;
			}

			String batchNumber = configuration.getBatchNumberTemplate();

			batchNumber = batchNumber.replace("${counter}", counterStr);
			batchNumber = batchNumber.replace("${month}", month);
			batchNumber = batchNumber.replace("${year}", year);
			batchNumber = batchNumber.replace("${recipient}", recipientCode);
			batchNumber = batchNumber.replace("${batchType}", jenisBatch);

			number = batchNumber;

			configuration.setBatchNumber(configuration.getBatchNumber() + 1);
			configurationService.update(configuration, null);

			/*
			 * [nomor urut]/[bulan]/[tahun]/[jenis batch]/[kode provider] atau
			 * [kode group] 0001/02/2009/C/MBA
			 * 
			 * 0001/02/2009/R/MBA
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}
		return number;
	}

	/*
	 * Method create (BatchClaim object) berfungsi untuk melakukan penambahan
	 * sebuah object kedalam database @param object adalah sebuah object yang
	 * ingin diubah @return object hasil kreasi,lengkap dengan assigned primary
	 * key, exception jika gagal
	 */
	public BatchClaim create(BatchClaim object, ActionUser actionUser)
			throws Exception {

		object.setDeletedStatus(Integer.valueOf(0));

		if (actionUser != null) {
			User user = actionUser.getUser();
			if (user != null) {
				object.setCreatedBy(user.getUsername());

			}
		}

		object.setCreatedTime(new java.sql.Timestamp(System.currentTimeMillis()));

		String nomorBatch = getBatchNumber(object);

		object.setIncompleteClaim(object.getTotalClaim());
		object.setBatchClaimNumber(nomorBatch);
		object.setBatchClaimFinalValue(Double.valueOf(0));
		object.setBatchClaimPaidValue(Double.valueOf(0));
		object.setBatchExcessValue(Double.valueOf(0));
		object.setBatchPaidExcessValue(Double.valueOf(0));

		BatchClaim result = batchClaimDao.create(object);

		if (result != null) {

			CaseStatus batchStatus = result.getBatchClaimStatus();

			if (batchStatus != null
					&& batchStatus.getCaseStatusId().intValue() == BatchClaim.BATCH_OPEN) {

				Outstanding outstanding = new Outstanding();
				outstanding.setBatchClaimId(result);
				outstanding.setClientId(result.getClientId());
				outstanding.setOutstandingValue(result
						.getBatchClaimInitialValue());
				outstanding.setOutstandingExcessValue(Double.valueOf(0));
				outstanding.setOutstandingTime(new Timestamp(System
						.currentTimeMillis()));

				PaymentStatus status = new PaymentStatus();
				status.setPaymentStatusId(Integer
						.valueOf(PaymentStatus.PAYMENT_OPEN));
				outstanding.setOutstandingStatus(status);

				outstandingService.create(outstanding, actionUser);
			}

			/**
			 * BUG-FIX 0000019: Update Batch Claim
			 * Solution: Claim Receive tidak secara otomatis di mark as REGISTERED, 
			 * karena 1 Claim Received bisa untuk beberapa Batch Claim
			 * 
			 * 
			if (result.getClaimReceivingId() != null) {
				ClaimReceiving receiving = claimReceivingService.get(result
						.getClaimReceivingId().getClaimReceivingId());

				if (receiving != null) {
					receiving.setReceivingStatus(ClaimReceiving.REGISTERED);

					claimReceivingService.update(receiving, actionUser);
				}
			}
			*/
		}

		return result;
	}

	/*
	 * Method update (BatchClaim object) berfungsi untuk melakukan perubahan
	 * terhadap sebuah object yang terdapat didalam database @param object
	 * adalah sebuah object yang ingin diubah @return object hasil update,
	 * exception jika gagal
	 */
	public BatchClaim update(BatchClaim object, ActionUser actionUser)
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

		batchClaimDao.update(object);
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
	public BatchClaim trash(java.io.Serializable pkey) throws Exception {
		BatchClaim object = batchClaimDao.get(pkey);
		batchClaimDao.delete(object);
		return object;
	}

	/*
	 * Method delete (BatchClaim object) berfungsi untuk melakukan penghapusan
	 * terhadap sebuah object yang terdapat didalam database @param object
	 * adalah sebuah object yang ingin dihapus, isi dari object tersebut cukup
	 * dengan mengisi field-field primary key @return updated object, exception
	 * if failed
	 */

	public BatchClaim delete(java.io.Serializable pkey, ActionUser actionUser)
			throws Exception {
		BatchClaim object = batchClaimDao.get(pkey);

		// object.setDeletedDate (new java.sql.Date
		// (System.currentTimeMillis()));

		object.setDeletedStatus(new Integer(1));
		if (actionUser != null) {
			User user = actionUser.getUser();
			if (user != null) {
				object.setDeletedBy(user.getUsername());
			}
		}

		batchClaimDao.update(object);
		return object;
	}

	/*
	 * Method delete (BatchClaim object) berfungsi untuk melakukan penghapusan
	 * terhadap sebuah object yang terdapat didalam database @param object
	 * adalah sebuah object yang ingin dihapus, isi dari object tersebut cukup
	 * dengan mengisi field-field primary key @return updated object, exception
	 * if failed
	 */

	public BatchClaim delete(BatchClaim object, ActionUser actionUser)
			throws Exception {

		// object.setDeletedDate (new java.sql.Date
		// (System.currentTimeMillis()));

		object.setDeletedStatus(new Integer(1));

		if (actionUser != null) {
			User user = actionUser.getUser();
			if (user != null) {
				object.setDeletedBy(user.getUsername());
			}
		}

		batchClaimDao.update(object);
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
	public BatchClaim get(java.io.Serializable pkey) throws Exception {
		BatchClaim object = null;
		object = batchClaimDao.get(pkey);
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

	public BatchClaim get(java.io.Serializable pkey, String[] required)
			throws Exception {
		BatchClaim object = null;
		object = batchClaimDao.get(pkey);
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

		Criteria c = batchClaimDao.getCriteria();
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

		Criteria c = batchClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			BatchClaim element = (BatchClaim) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}

		return list;

	}

	// --req end

	public Collection search(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, boolean asc,
			String columnOrder[], int index, int offset) throws Exception {

		Criteria c = batchClaimDao.getCriteria();
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
		Criteria c = batchClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			BatchClaim element = (BatchClaim) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}

		return list;

	}

	// --req end

	public Collection search(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, boolean asc,
			String columnOrder, int index, int offset) throws Exception {

		Criteria c = batchClaimDao.getCriteria();
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
		Criteria c = batchClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			BatchClaim element = (BatchClaim) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}

		return list;

	}

	// --req end

	// -- 1b

	public Collection search(String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, int index, int offset)
			throws Exception {

		Criteria c = batchClaimDao.getCriteria();
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
		Criteria c = batchClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			BatchClaim element = (BatchClaim) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}

		return list;

	}

	// --req end

	public Collection search(String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, boolean asc,
			String columnOrder[], int index, int offset) throws Exception {

		Criteria c = batchClaimDao.getCriteria();
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

		Criteria c = batchClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			BatchClaim element = (BatchClaim) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;

	}

	// req end

	public Collection search(String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, boolean asc, String columnOrder,
			int index, int offset) throws Exception {

		Criteria c = batchClaimDao.getCriteria();
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

		Criteria c = batchClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			BatchClaim element = (BatchClaim) iter.next();
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

		Criteria c = batchClaimDao.getCriteria();
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

		Criteria c = batchClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil
				.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			BatchClaim element = (BatchClaim) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}

	// req end

	public Collection search(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, String[] btwnColumns,
			Object[] btwnParams1, Object[] btwnParams2, boolean asc,
			String columnOrder[], int index, int offset) throws Exception {

		Criteria c = batchClaimDao.getCriteria();
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

		Criteria c = batchClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil
				.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			BatchClaim element = (BatchClaim) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}

	// req end

	public Collection search(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, String[] btwnColumns,
			Object[] btwnParams1, Object[] btwnParams2, boolean asc,
			String columnOrder, int index, int offset) throws Exception {

		Criteria c = batchClaimDao.getCriteria();
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

		Criteria c = batchClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil
				.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			BatchClaim element = (BatchClaim) iter.next();
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

		Criteria c = batchClaimDao.getCriteria();
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

		Criteria c = batchClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil
				.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			BatchClaim element = (BatchClaim) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}

	// req end

	public Collection search(String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, String btwnColumns,
			Object btwnParams1, Object btwnParams2, boolean asc,
			String columnOrder[], int index, int offset) throws Exception {

		Criteria c = batchClaimDao.getCriteria();
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

		Criteria c = batchClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil
				.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			BatchClaim element = (BatchClaim) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}

	// req end

	public Collection search(String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, String btwnColumns,
			Object btwnParams1, Object btwnParams2, boolean asc,
			String columnOrder, int index, int offset) throws Exception {

		Criteria c = batchClaimDao.getCriteria();
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

		Criteria c = batchClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil
				.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		DaoSupportUtil.setOrderBy(asc, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			BatchClaim element = (BatchClaim) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;
	}

	// req end

	// -- search end

	// -- get total

	public int getTotal(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams) throws Exception {

		Criteria c = batchClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}

	public int getTotal(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, String[] btwnColumns,
			Object[] btwnParams1, Object[] btwnParams2) throws Exception {

		Criteria c = batchClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil
				.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}

	public int getTotal(String likeColumns, Object likeParams,
			String eqColumns, Object eqParams) throws Exception {

		Criteria c = batchClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}

	public int getTotal(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, String btwnColumns,
			Object btwnParams1, Object btwnParams2) throws Exception {

		Criteria c = batchClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil
				.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}

	// ------------------------------------------------------------------
	public int getTotal() throws Exception {
		Criteria c = batchClaimDao.getCriteria();
		int total = DaoSupportUtil.getTotal(c);
		return total;
	}

	// -- get total end

	// ---------------------------------------------------
	public Collection getAll(String[] required) throws Exception {

		Criteria c = batchClaimDao.getCriteria();
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			BatchClaim element = (BatchClaim) iter.next();
			DaoSupportUtil.lazyInit(required, element);
		}
		return list;

	}

	public Collection getAll() throws Exception {

		Criteria c = batchClaimDao.getCriteria();
		List list = c.list();
		return list;
	}

	// -------------------------------------------------

	// unique Result

	public BatchClaim searchUnique(String eqColumns, Object eqParams,
			String[] required) throws Exception {
		Criteria c = batchClaimDao.getCriteria();
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		BatchClaim obj = (BatchClaim) c.uniqueResult();
		DaoSupportUtil.lazyInit(required, obj);
		return obj;
	}

	public BatchClaim searchUnique(String eqColumns, Object eqParams)
			throws Exception {
		Criteria c = batchClaimDao.getCriteria();
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		BatchClaim obj = (BatchClaim) c.uniqueResult();
		return obj;
	}

	// -----------------------------------------------

	/*
	 * BASIC IMPLEMENTATION !! USE WITH CAUTION ! USE IT IF NO OTHER OPTION LEFT
	 * 
	 * @return criteria
	 */
	public Criteria getCriteria() throws Exception {
		Criteria c = batchClaimDao.getCriteria();
		return c;
	}

	public DetachedCriteria getDetachedCriteria() throws Exception {
		DetachedCriteria dc = batchClaimDao.getDetachedCriteria();
		return dc;
	}

	public BatchClaim extractClaim(BatchClaim newBatch, Claim[] extractedClaim,
			ActionUser actionUser) throws Exception {
		BatchClaim result = null;

		try {
			if (newBatch != null && extractedClaim != null) {

			}
		} catch (Exception e) {

		}

		return result;
	}

	public BatchClaim finalize(Serializable pkey, ActionUser actionUser)
			throws Exception {
		// TODO Auto-generated method stub
		BatchClaim batch = get(pkey);

		try {

			if (batch != null) {
				Outstanding outstanding = new Outstanding();

				outstanding.setBatchClaimId(batch);
				outstanding.setOutstandingValue(batch
						.getBatchClaimInitialValue());

				outstanding.setClientId(batch.getClientId());
				outstanding.setOutstandingExcessValue(Double.valueOf(0));
				outstanding.setOutstandingTime(new Timestamp(System
						.currentTimeMillis()));

				PaymentStatus status = new PaymentStatus();
				status.setPaymentStatusId(Integer
						.valueOf(Outstanding.OUTSTANDING_OPEN));

				outstanding.setOutstandingStatus(status);

				outstandingService.create(outstanding, actionUser);

				return batch;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public BatchClaim closeBatch(Serializable pkey, ActionUser actionUser)
			throws Exception {
		// TODO Auto-generated method stub

		try {

			String[] required = { "BatchClaim.Claims" };
			BatchClaim batch = get(pkey, required);

			if (batch != null) {

				Payment payment = null;

				try {
					payment = paymentDao.getPaymentByBatchId(batch
							.getBatchClaimId());
				} catch (Exception e) {
					e.printStackTrace();
				}

				CaseStatus batchStatus = new CaseStatus();

				double claimValue = 0.0;
				double claimApprovedValue = 0.0;
				double claimExcessValue = 0.0;
				// recap total total

				Collection<Claim> claims = batch.getClaims();

				int totalClaim = 0;
				int totalCdvIssuedClaim = 0;

				if (claims != null && claims.size() > 0) {
					Iterator<Claim> claimIterator = claims.iterator();

					if (claimIterator != null) {
						Claim claim = null;

						while (claimIterator.hasNext()) {
							claim = claimIterator.next();
							totalClaim += 1;

							if (claim != null
									&& claim.getDeletedStatus().intValue() == 0) {
								claimValue += claim.getClaimChargeValue();

								if (claim.getClaimApprovedValue() != null) {
									claimApprovedValue += claim
											.getClaimApprovedValue();
								} else {
									claimApprovedValue += 0;
								}

								if (claim.getClaimExcessValue() != null) {
									claimExcessValue += claim
											.getClaimExcessValue();
								} else {
									claimExcessValue += 0;
								}

								if (payment != null) {

									if (payment.getPaymentStatus()
											.getPaymentStatusId().intValue() == PaymentStatus.PAYMENT_PAID) {
										if (claim.getClaimStatus()
												.getCaseStatusId().intValue() == Claim.CLAIM_PAYMENT_ISSUED) {
											claim.setClaimStatus(new CaseStatus(
													Claim.CLAIM_PAID));
										}
									} else {
										if (claim.getClaimStatus()
												.getCaseStatusId().intValue() == Claim.CLAIM_PAYMENT_ISSUED) {
											totalCdvIssuedClaim += 1;
										}
									}
									claimDao.update(claim);

								}
							}
						}
					}
				}

				batch.setBatchClaimInitialValue(claimValue);
				batch.setBatchClaimFinalValue(claimApprovedValue);
				batch.setBatchExcessValue(claimExcessValue);
				batch.setBatchClaimCloseDate(new java.sql.Date(System
						.currentTimeMillis()));

				if (totalCdvIssuedClaim == totalClaim) {
					batchStatus.setCaseStatusId(BatchClaim.PAYMENT_ISSUED);
				} else if (totalClaim > totalCdvIssuedClaim) {
					batchStatus.setCaseStatusId(BatchClaim.CLOSED);
				}

				batch.setBatchClaimStatus(batchStatus);

				update(batch, actionUser);
				return batch;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public BatchClaim paidBatch(Serializable pkey, ActionUser actionUser)
			throws Exception {
		// TODO Auto-generated method stub

		try {
			BatchClaim batch = get(pkey);

			if (batch != null) {

				CaseStatus batchStatus = new CaseStatus();
				batchStatus.setCaseStatusId(BatchClaim.PAID);
				batch.setBatchClaimStatus(batchStatus);
				update(batch, actionUser);

				return batch;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public BatchClaim correctionBatch(Serializable pkey, ActionUser actionUser)
			throws Exception {
		// TODO Auto-generated method stub

		try {
			BatchClaim batch = get(pkey);

			if (batch != null) {

				CaseStatus batchStatus = new CaseStatus();
				batchStatus.setCaseStatusId(BatchClaim.CORRECTION);
				batch.setBatchClaimStatus(batchStatus);
				update(batch, actionUser);

				return batch;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public BatchClaim installmentPaidBatch(Serializable pkey,
			ActionUser actionUser) throws Exception {
		// TODO Auto-generated method stub

		try {
			BatchClaim batch = get(pkey);

			if (batch != null) {

				CaseStatus batchStatus = new CaseStatus();
				batchStatus.setCaseStatusId(BatchClaim.INSTALLMENT_PAYMENT);
				batch.setBatchClaimStatus(batchStatus);
				update(batch, actionUser);

				return batch;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Collection search(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, String btwnColumns,
			Object btwnParams1, Object btwnParams2) throws Exception {
		// TODO Auto-generated method stub
		Criteria c = batchClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);

		DaoSupportUtil
				.setBetweenParam(btwnColumns, btwnParams1, btwnParams2, c);
		List list = c.list();
		return list;

	}

	public Collection search(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams) throws Exception {
		Criteria c = batchClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);

		List list = c.list();
		return list;

	}

	public boolean isBatchChecked(Integer batchId) throws Exception {
		boolean result = false;

		String[] required = { "BatchClaim.Claims" };
		BatchClaim batchClaim = get(batchId, required);

		if (batchClaim != null) {
			Collection<Claim> claims = batchClaim.getClaims();

			boolean completeBatch = true;

			if (claims != null && claims.size() > 0) {

				Iterator<Claim> claimIterator = claims.iterator();

				if (claimIterator != null) {

					Claim uncheckedClaim = null;

					while (claimIterator.hasNext()) {
						uncheckedClaim = claimIterator.next();

						if (uncheckedClaim != null) {
							if (uncheckedClaim.getClaimStatus()
									.getCaseStatusId() == Claim.CLAIM_OPEN) {
								completeBatch &= false;
							} else if (uncheckedClaim.getClaimStatus()
									.getCaseStatusId() == Claim.CLAIM_VERIFIED) {
								completeBatch &= false;
							} else if (uncheckedClaim.getClaimStatus()
									.getCaseStatusId() == Claim.CLAIM_PENDING) {
								completeBatch &= false;
							} else if (uncheckedClaim.getClaimStatus()
									.getCaseStatusId() == Claim.CLAIM_REJECT) {
								completeBatch &= true;
							} else {
								completeBatch &= true;
							}
						}
					}
				}
			}

			if (completeBatch) {
				result = true;
			}

			if (batchClaim.getIncompleteClaim() != null
					&& batchClaim.getIncompleteClaim() > 0) {
				result = false;
			}
		}

		return result;
	}

	public Collection<PaymentReportDetail> getBatchReportDetail(
			Integer batchClaimId) throws Exception {
		// TODO Auto-generated method stub

		Collection<PaymentReportDetail> result = null;

		return result;
	}

	public BatchClaim completeBatch(Serializable pkey, ActionUser actionUser)
			throws Exception {
		// TODO Auto-generated method stub

		BatchClaim batchClaim = get(pkey);
		if (batchClaim != null) {

			CaseStatus status = new CaseStatus();
			status.setCaseStatusId(BatchClaim.COMPLETE);
			batchClaim.setBatchClaimStatus(status);

			String[] param = { "batchClaimId.batchClaimId" };
			Object[] value = { batchClaim.getBatchClaimId() };

			Collection<Outstanding> outstandingCollection = outstandingService
					.search(null, null, param, value, 0, 1);

			Configuration config = configurationService
					.getSystemConfiguration();
			if (config != null) {
				if (batchClaim.getClientId() != null) {
					config = configurationService
							.getClientConfiguration(batchClaim.getClientId()
									.getClientId());
				}
			}
			if (config != null) {
				if (config.getIsUsingClientNotification() != null
						&& config.getIsUsingClientNotification().intValue() == 1) {

					if (batchClaim.getBatchClaimType().getClaimTypeId()
							.intValue() == ClaimType.REIMBURESEMENT) {
						if (batchClaim.getPaymentRecipient()
								.getPaymentRecipientId().intValue() == PaymentRecipient.MEMBER) {

							String destination = "";
							MessageTemplate template = new MessageTemplate();

							Notification notification = new Notification();

							if (batchClaim.getMemberId() != null
									&& batchClaim.getMemberId().getEmail() != null) {
								destination = batchClaim.getMemberId()
										.getEmail();
								template.setId(MessageTemplate.MEMBER_BATCH_RECEIVED_EMAIL);
								notification.setMemberId(batchClaim
										.getMemberId());
							}

							notification.setBatchClaimId(batchClaim);
							notification.setTemplateId(template);
							notification.setDestination(destination);
							notification.setStatus(0);
							notification
									.setMessageType(Notification.MEMBER_CLAIM_NOTIFICATION_EMAIL);
							notification.setSender("system-daemon");

							notificationService
									.create(notification, actionUser);
						}
					}

				}
			}
			if (outstandingCollection != null) {
				Iterator<Outstanding> iterator = outstandingCollection
						.iterator();

				if (iterator != null) {
					Outstanding outstanding = iterator.next();

					if (outstanding != null) {

					}
				}
			}
		}
		return batchClaim;
	}

	public BatchClaim openBatch(Serializable pkey, ActionUser actionUser)
			throws Exception {
		BatchClaim batchClaim = get(pkey);
		if (batchClaim != null) {

			CaseStatus status = new CaseStatus();
			status.setCaseStatusId(BatchClaim.BATCH_OPEN);
			batchClaim.setBatchClaimStatus(status);
		}
		return batchClaim;
	}

	public Collection search(String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, String dateSearch,
			Date minimumDate, Date maxDate, String[] required) throws Exception {
		// TODO Auto-generated method stub

		Criteria c = batchClaimDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns, likeParams, c);
		DaoSupportUtil.setEqParam(eqColumns, eqParams, c);
		DaoSupportUtil.lazyInit(required, c);

		DaoSupportUtil.setBetweenParam(dateSearch, minimumDate, maxDate, c);

		List list = c.list();

		return list;
	}

	public BatchClaim voidBatch(Serializable pkey, Integer method,
			ActionUser actionUser) throws Exception {
		// TODO Auto-generated method stub

		try {

		} catch (Exception e) {

		}
		return null;
	}

	public BatchClaim voidBatch(Serializable pkey, ActionUser actionUser)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isBatchPaymentIssued(Integer batchId) throws Exception {
		// TODO Auto-generated method stub

		boolean result = false;

		try {
			BatchClaim batch = get(batchId);

			if (batch != null
					&& batch.getBatchClaimStatus().getCaseStatusId().intValue() == BatchClaim.PAYMENT_ISSUED) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void recalculateBatch(Integer batchId, ActionUser actionUser)
			throws Exception {
		// TODO Auto-generated method stub

		String[] required = { "BatchClaim.Claims", "BatchClaim.Payments" };
		BatchClaim batch = get(batchId, required);

		double claimValue = 0.0;
		double claimApprovedValue = 0.0;
		double claimExcessValue = 0.0;
		// recap total total

		Collection<Claim> claims = batch.getClaims();

		if (claims != null && claims.size() > 0) {
			Iterator<Claim> claimIterator = claims.iterator();

			if (claimIterator != null) {
				Claim claim = null;

				while (claimIterator.hasNext()) {
					claim = claimIterator.next();

					if (claim != null
							&& (claim.getDeletedStatus() != null && claim
									.getDeletedStatus().intValue() == 0)) {
						claimValue += claim.getClaimChargeValue();

						if (claim.getClaimApprovedValue() != null) {
							claimApprovedValue += claim.getClaimApprovedValue();
						} else {
							claimApprovedValue += 0;
						}

						if (claim.getClaimExcessValue() != null) {
							claimExcessValue += claim.getClaimExcessValue();
						} else {
							claimExcessValue += 0;
						}
					}
				}
			}
		}
		batch.setBatchClaimFinalValue(claimApprovedValue);
		batch.setBatchClaimInitialValue(claimValue);
		batch.setBatchExcessValue(claimExcessValue);

		if (batch.getBatchClaimStatus().getCaseStatusId().intValue() == BatchClaim.PAYMENT_ISSUED) {
			if (batch.getBatchClaimType().getClaimTypeId().intValue() == ClaimType.CASHLESS
					|| batch.getBatchClaimType().getClaimTypeId().intValue() == ClaimType.REIMBURSEMENT_KHUSUS) {
				batch.setBatchClaimPaidValue(claimValue);
			} else {
				batch.setBatchClaimPaidValue(claimApprovedValue);

				Payment payment = paymentDao.getPaymentByBatchId(batch
						.getBatchClaimId());

				if (payment != null) {
					payment.setPaymentValue(claimApprovedValue);

					paymentDao.update(payment);
				}
			}

		}
		update(batch, actionUser);

	}

	public BatchClaim getCurrentEDCBatch(String providerCode,
			String terminalCode, String insuranceCode, Date date)
			throws Exception {
		// TODO Auto-generated method stub

		BatchClaim result = null;

		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public BatchClaim registerEDCBatch(BatchClaim batchClaim,
			ActionUser actionUser) throws Exception {
		// TODO Auto-generated method stub
		BatchClaim result = null;

		try {

			batchClaim.setDeletedStatus(Integer.valueOf(0));
			batchClaimDao.create(batchClaim);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Collection<Policy> getPolicyList(Integer batchClaimId)
			throws Exception {
		// TODO Auto-generated method stub

		Collection<Policy> result = new Vector<Policy>();

		try {
			String qStr = "SELECT p FROM Policy p WHERE "
					+ " p.policyId IN (SELECT DISTINCT c.policyId FROM Claim c WHERE c.batchClaimId.batchClaimId = :bcId)";
			Session session = batchClaimDao.getBatchSession();

			org.hibernate.Query q = session.createQuery(qStr);
			q.setInteger("bcId", batchClaimId);

			result = q.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// class+

	// class-

}
