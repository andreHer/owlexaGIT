
package com.ametis.cms.service.impl;


import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.CaseCategoryService;
import com.ametis.cms.service.ConfigurationService;
import com.ametis.cms.service.MemberGroupService;
import com.ametis.cms.service.MemberService;
import com.ametis.cms.service.PolicyCoverageFundService;
import com.ametis.cms.service.PolicyService;
import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.ActivityLog;
import com.ametis.cms.datamodel.CaseCategory;
import com.ametis.cms.datamodel.Configuration;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.datamodel.PolicyCoverageFund;
import com.ametis.cms.datamodel.PolicyDiagnosisExclusion;
import com.ametis.cms.datamodel.SubscriptionStatus;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.datamodel.Policy;
import com.ametis.cms.dao.MemberDao;
import com.ametis.cms.dao.PolicyDao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.impl.CriteriaImpl.Subcriteria;
import org.springframework.web.multipart.MultipartFile;

import com.ametis.cms.util.StringUtil;
import com.ametis.cms.util.dao.DaoSupportUtil;


// imports+ 

// imports- 

/**
 * Policy is a servlet controller for policy Table.
 * All you have to do is to convert necessary data field to the named parameter
*/
public class PolicyServiceImpl implements PolicyService

// extends+ 

// extends- 
{
	
	private PolicyDao  policyDao;
	private MemberGroupService memberGroupService;
	private ConfigurationService configurationService;
	private PolicyCoverageFundService policyCoverageFundService;
	private ActivityLogService logService;
	private MemberDao memberService;
	private CaseCategoryService caseCategoryService;
	
	
	
	

	public CaseCategoryService getCaseCategoryService() {
		return caseCategoryService;
	}
	public void setCaseCategoryService(CaseCategoryService caseCategoryService) {
		this.caseCategoryService = caseCategoryService;
	}
	public ActivityLogService getLogService() {
		return logService;
	}
	public void setLogService(ActivityLogService logService) {
		this.logService = logService;
	}
	public PolicyCoverageFundService getPolicyCoverageFundService() {
		return policyCoverageFundService;
	}
	public void setPolicyCoverageFundService(
			PolicyCoverageFundService policyCoverageFundService) {
		this.policyCoverageFundService = policyCoverageFundService;
	}
	public ConfigurationService getConfigurationService() {
		return configurationService;
	}
	public void setConfigurationService(ConfigurationService configurationService) {
		this.configurationService = configurationService;
	}
	public MemberGroupService getMemberGroupService() {
		return memberGroupService;
	}
	public void setMemberGroupService(MemberGroupService memberGroupService) {
		this.memberGroupService = memberGroupService;
	}
	public void setPolicyDao (PolicyDao object){
		this.policyDao = object;
	}
	public PolicyDao getPolicyDao (){
		return this.policyDao;
	}
	public Policy activate (Policy object,ActionUser user) throws Exception {
		
		if (object != null){
			SubscriptionStatus status = new SubscriptionStatus();
			status.setStatusId(SubscriptionStatus.ACTIVE);
			object.setPolicyStatus(status);
			object.setModifiedTime(new java.sql.Timestamp(System.currentTimeMillis()));
			if (user != null && user.getUser() != null){
				object.setModifiedBy(user.getUser().getUsername());
			}
			
			
			policyDao.update(object);
			
			if (object.getMemberGroupId() != null){
				MemberGroup group = memberGroupService.get(object.getMemberGroupId().getMemberGroupId());
				if (group != null){
					group.setCurrentActivePolicyId(object);
					
					memberGroupService.update(group, user);
				}
			}
			
		}
		return object;
	}
	/*
	* Method create (Policy object) berfungsi untuk melakukan penambahan
	* sebuah object kedalam database
	* @param object adalah sebuah object yang ingin diubah
	* @return object hasil kreasi,lengkap dengan assigned primary key, exception jika gagal
	*/
	public Policy create (Policy object,Collection<MultipartFile> tcFiles,ActionUser user) throws Exception {
			
		object.setCreatedTime(new java.sql.Timestamp(System.currentTimeMillis()));
		object.setDeletedStatus(new Integer(0));
		
		SubscriptionStatus status = new SubscriptionStatus();
		status.setStatusId(SubscriptionStatus.PENDING);
		object.setPolicyStatus(status);
		
		if (user != null && user.getUser() != null){
			object.setCreatedBy(user.getUser().getUsername());			
		}	

		if (tcFiles != null){
			int idx = 0;
			
			String documentDirectory = "";
			
			Configuration config = configurationService.get(object.getClientId().getClientId());
			
			if (config == null){
				config = configurationService.getSystemConfiguration();
			}
			else {
				System.out.println("CLIENT CONFIGURATION =  " + config.getConfigurationId() + " CLIENT ID = " + config.getClientId().getClientId());
			}
			
			if (config != null){
				documentDirectory = config.getPolicyTermConditionPath() + System.getProperty("file.separator");
			}
			
			if (object.getInitialFundExcessValue() != null && object.getFundWarningPercentage() != null){
				object.setMinimumExcessFund(object.getFundWarningPercentage()/100*object.getInitialFundExcessValue());
			}
			if (object.getInitialFundExcessValue() != null && object.getBlockFundPercentage() != null){
				object.setExcessBlockingLimit(object.getBlockFundPercentage()/100*object.getInitialFundExcessValue());
			}
			if (object.getInitialFundValue() != null && object.getFundWarningPercentage() != null){
				object.setMinimumPolicyFund(object.getFundWarningPercentage()/100*object.getInitialFundValue());
			}
			if (object.getInitialFundValue() != null && object.getBlockFundPercentage() != null){
				object.setFundBlockingLimit(object.getBlockFundPercentage()/100*object.getInitialFundValue());
			}

			
			System.out.println("Getting Document Directory = " +  documentDirectory);
			
			for (Iterator iterator = tcFiles.iterator(); iterator.hasNext();) {
				try {
					MultipartFile multipartFile = (MultipartFile) iterator.next();
					if (multipartFile != null && !multipartFile.getOriginalFilename().equalsIgnoreCase("")){
						
						String docName = "";
						
						if (idx == 0){
							docName = object.getPolicyTcFile1();
							docName = docName.replaceAll("\\s", "");
							object.setPolicyTcFile1(docName);
							
						}
						if (idx == 1){
							docName = object.getPolicyTcFile2();
							docName = docName.replaceAll("\\s", "");
							object.setPolicyTcFile2(docName);
						}						
						if (idx == 2){
							docName = object.getPolicyTcFile3();
							docName = docName.replaceAll("\\s", "");
							object.setPolicyTcFile3(docName);
						}
						
						FileOutputStream outstream = new FileOutputStream(documentDirectory+docName);
						outstream.write(multipartFile.getBytes());
						outstream.flush();
						outstream.close();
					}					
				
				}
				catch (Exception e){
					e.printStackTrace();
				}
				idx++;
			}
		}
		Policy result = policyDao.create (object);
		
		if (result != null){
			String asoCoverageList = result.getAsoCoverageList();
			
			if (asoCoverageList != null && !asoCoverageList.equalsIgnoreCase("")){
				StringTokenizer tokenizer = new StringTokenizer(asoCoverageList);
				
				while(tokenizer.hasMoreTokens()){
					String token = tokenizer.nextToken();
					
					if (token != null){
	
						CaseCategory cc = caseCategoryService.getCaseCategory(token);
						if (cc != null){
							String[] eqParam = {"policyId.policyId","caseCategoryId.caseCategoryId","deletedStatus"};
							Object[] eqValue = {result.getPolicyId(),cc.getCaseCategoryId(),0};
							
							int total = policyCoverageFundService.getTotal(null,null,eqParam,eqValue);
							
							if (total == 0){
							
								PolicyCoverageFund pcFund = new PolicyCoverageFund();
								pcFund.setCaseCategoryId(cc);
								pcFund.setPolicyId(result);
								pcFund.setCurrentFundValue(0.0);
								
								
								policyCoverageFundService.create(pcFund, user);
							}
							
							
						}
					}
					
				}
			}
		}
		
		
		return result;
	}
	/*
	* Method update (Policy object) berfungsi untuk melakukan perubahan terhadap
	* sebuah object yang terdapat didalam database
	* @param object adalah sebuah object yang ingin diubah
	* @return object hasil update, exception jika gagal
	*/
	public Policy update (Policy object,ActionUser user) throws Exception{
			
		object.setModifiedTime (new java.sql.Timestamp (System.currentTimeMillis()));
		if (user != null && user.getUser() != null){
			object.setCreatedBy(user.getUser().getUsername());			
		}
		
		if (object.getInitialFundExcessValue() != null && object.getFundWarningPercentage() != null){
			object.setMinimumExcessFund(object.getFundWarningPercentage()/100*object.getInitialFundExcessValue());
		}
		if (object.getInitialFundExcessValue() != null && object.getBlockFundPercentage() != null){
			object.setExcessBlockingLimit(object.getBlockFundPercentage()/100*object.getInitialFundExcessValue());
		}
		if (object.getInitialFundValue() != null && object.getFundWarningPercentage() != null){
			object.setMinimumPolicyFund(object.getFundWarningPercentage()/100*object.getInitialFundValue());
		}
		if (object.getInitialFundValue() != null && object.getBlockFundPercentage() != null){
			object.setFundBlockingLimit(object.getBlockFundPercentage()/100*object.getInitialFundValue());
		}

		
 		policyDao.update (object);
      	return object;
	}
	public boolean block (Integer policyId, Date start, Date end, String notes, ActionUser user) throws Exception {
		boolean result = false;
		
		try {
			Policy policy = get(policyId);
			
			if (policy != null){
				SubscriptionStatus status = new SubscriptionStatus();
				status.setStatusId(SubscriptionStatus.BLOCKED);
				policy.setPolicyStatus(status);
				
				update(policy,user);
				
				
				String q = "SELECT m FROM Member m WHERE m.deletedStatus = 0 AND m.currentPolicyId.policyId = :pId AND m.status = :stat";
				
				Session session = memberService.getMemberSession();
				Query query = session.createQuery(q);
				query.setInteger("pId", policyId);
				query.setInteger("stat", SubscriptionStatus.ACTIVE);
				
				Collection<Member> memberList = query.list();
				
				if (memberList != null){
					for (Iterator iterator = memberList.iterator(); iterator
							.hasNext();) {
						
						Member member = (Member) iterator.next();
						
						if (member != null && member.getStatus().intValue() == SubscriptionStatus.ACTIVE){
							member.setStatus(SubscriptionStatus.BLOCKED);
							member.setModifiedTime(new java.sql.Timestamp(System.currentTimeMillis()));
							memberService.update(member);
						}
					}
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	public Policy update (Policy object,Collection<MultipartFile> tcFiles,ActionUser user) throws Exception{
		
		object.setModifiedTime (new java.sql.Timestamp (System.currentTimeMillis()));
		if (user != null && user.getUser() != null){
			object.setCreatedBy(user.getUser().getUsername());			
		}
		

		if (tcFiles != null){
			int idx = 0;
			
			String documentDirectory = "";
			
			Configuration config = configurationService.get(object.getClientId().getClientId());
			
			if (config == null){
				config = configurationService.getSystemConfiguration();
			}
			else {
				System.out.println("CLIENT CONFIGURATION =  " + config.getConfigurationId() + " CLIENT ID = " + config.getClientId().getClientId());
			}
			
			if (config != null){
				documentDirectory = config.getPolicyTermConditionPath() + System.getProperty("file.separator");
			}

			
			System.out.println("Getting Document Directory = " +  documentDirectory);

			
			for (Iterator iterator = tcFiles.iterator(); iterator.hasNext();) {
				try {
					MultipartFile multipartFile = (MultipartFile) iterator.next();
					if (multipartFile != null && !multipartFile.getOriginalFilename().equalsIgnoreCase("")){
						
						String docName = "";
						
						if (idx == 0){
							docName = object.getPolicyTcFile1();
							docName = docName.replaceAll("\\s", "");
							object.setPolicyTcFile1(docName);
							
						}
						if (idx == 1){
							docName = object.getPolicyTcFile2();
							docName = docName.replaceAll("\\s", "");
							object.setPolicyTcFile2(docName);
						}						
						if (idx == 2){
							docName = object.getPolicyTcFile3();
							docName = docName.replaceAll("\\s", "");
							object.setPolicyTcFile3(docName);
						}
						
						FileOutputStream outstream = new FileOutputStream(documentDirectory+docName);
						outstream.write(multipartFile.getBytes());
						outstream.flush();
						outstream.close();
					}					
				
				}
				catch (Exception e){
					e.printStackTrace();
				}
				idx++;
			}
		}
		if (object.getInitialFundExcessValue() != null && object.getFundWarningPercentage() != null){
			object.setMinimumExcessFund(object.getFundWarningPercentage()/100*object.getInitialFundExcessValue());
		}
		if (object.getInitialFundExcessValue() != null && object.getBlockFundPercentage() != null){
			object.setExcessBlockingLimit(object.getBlockFundPercentage()/100*object.getInitialFundExcessValue());
		}
		if (object.getInitialFundValue() != null && object.getFundWarningPercentage() != null){
			object.setMinimumPolicyFund(object.getFundWarningPercentage()/100*object.getInitialFundValue());
		}
		if (object.getInitialFundValue() != null && object.getBlockFundPercentage() != null){
			object.setFundBlockingLimit(object.getBlockFundPercentage()/100*object.getInitialFundValue());
		}
 		policyDao.update (object);
 		
 		if (object != null){
			String asoCoverageList = object.getAsoCoverageList();
			
			if (asoCoverageList != null && !asoCoverageList.equalsIgnoreCase("")){
				StringTokenizer tokenizer = new StringTokenizer(asoCoverageList);
				
				while(tokenizer.hasMoreTokens()){
					String token = tokenizer.nextToken();
					
					if (token != null){
	
						CaseCategory cc = caseCategoryService.getCaseCategory(token);
						if (cc != null){
							String[] eqParam = {"policyId.policyId","caseCategoryId.caseCategoryId","deletedStatus"};
							Object[] eqValue = {object.getPolicyId(),cc.getCaseCategoryId(),0};
							
							int total = policyCoverageFundService.getTotal(null,null,eqParam,eqValue);
							
							if (total == 0){
							
								PolicyCoverageFund pcFund = new PolicyCoverageFund();
								pcFund.setCaseCategoryId(cc);
								pcFund.setPolicyId(object);
								pcFund.setCurrentFundValue(0.0);
								
								
								policyCoverageFundService.create(pcFund, user);
							}
							
							
						}
					}
					
				}
			}
 		}
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
	public Policy trash (java.io.Serializable pkey) throws Exception {
		Policy object = policyDao.get (pkey);
		policyDao.delete (object);
		return object;
	}

	/*
	* Method delete (Policy object) berfungsi untuk melakukan penghapusan terhadap
	* sebuah object yang terdapat didalam database
	* @param object adalah sebuah object yang ingin dihapus, isi dari object tersebut cukup dengan
	*		 mengisi field-field primary key
	* @return updated object, exception if failed
	*/

	public Policy delete (java.io.Serializable pkey,ActionUser user) throws Exception{
		Policy object = policyDao.get (pkey);

		object.setDeletedTime(new java.sql.Timestamp(System.currentTimeMillis()));		
		object.setDeletedStatus(new Integer(1));
		
		if (user != null && user.getUser() != null){
			object.setCreatedBy(user.getUser().getUsername());			
		}		
		policyDao.update (object);
		return object;
	}


	/*
	* Method delete (Policy object) berfungsi untuk melakukan penghapusan terhadap
	* sebuah object yang terdapat didalam database
	* @param object adalah sebuah object yang ingin dihapus, isi dari object tersebut cukup dengan
	*		 mengisi field-field primary key
	* @return updated object, exception if failed
	*/

	public Policy delete (Policy object,ActionUser user) throws Exception{
		
		object.setDeletedTime(new java.sql.Timestamp(System.currentTimeMillis()));
		object.setDeletedStatus(new Integer(1));
		
		if (user != null && user.getUser() != null){
			object.setCreatedBy(user.getUser().getUsername());			
		}		
		policyDao.update (object);
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
	public Policy get (java.io.Serializable pkey) throws Exception{
		Policy object = null;
		object = policyDao.get(pkey);
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

	public Policy get (java.io.Serializable pkey, String[] required) throws Exception{
	    Policy object = null;
	    object = policyDao.get(pkey);
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

		Criteria c = policyDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		return list;

	}
	//--req
	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams,String[] required, int index, int offset) throws Exception{

		Criteria c = policyDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Policy element = (Policy) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}

		return list;

	}
	// --req end
	
	//start search order
	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams,String[] required, 
			String columnOrder, boolean order, int index, int offset) throws Exception{

		Criteria c = policyDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(order, columnOrder, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Policy element = (Policy) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}

		return list;

	}
	//end search order

	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, boolean asc, String columnOrder[],
			int index, int offset) throws Exception{

		Criteria c = policyDao.getCriteria();
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
		Criteria c = policyDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Policy element = (Policy) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}

		return list;

	}
	// --req end



	public Collection search (String[] likeColumns, Object[] likeParams,
			String[] eqColumns, Object[] eqParams, boolean asc, String columnOrder,
			int index, int offset) throws Exception{

		Criteria c = policyDao.getCriteria();
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
		Criteria c = policyDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Policy element = (Policy) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}

		return list;

	}
	// --req end


// -- 1b

	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams, int index, int offset) throws Exception{

		Criteria c = policyDao.getCriteria();
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
		Criteria c = policyDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Policy element = (Policy) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}

		return list;

	}
	// --req end

	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams,
			 boolean asc, String columnOrder[],
			int index, int offset) throws Exception{

		Criteria c = policyDao.getCriteria();
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

		Criteria c = policyDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Policy element = (Policy) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}
		return list;

	}
	// req end

	public Collection search (String likeColumns, Object likeParams,
			String eqColumns, Object eqParams,
			 boolean asc, String columnOrder,
			int index, int offset) throws Exception{

		Criteria c = policyDao.getCriteria();
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

		Criteria c = policyDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Policy element = (Policy) iter.next();
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

		Criteria c = policyDao.getCriteria();
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

		Criteria c = policyDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Policy element = (Policy) iter.next();
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

		Criteria c = policyDao.getCriteria();
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

		Criteria c = policyDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Policy element = (Policy) iter.next();
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

		Criteria c = policyDao.getCriteria();
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

		Criteria c = policyDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Policy element = (Policy) iter.next();
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

		Criteria c = policyDao.getCriteria();
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

		Criteria c = policyDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Policy element = (Policy) iter.next();
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

		Criteria c = policyDao.getCriteria();
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

		Criteria c = policyDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Policy element = (Policy) iter.next();
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

		Criteria c = policyDao.getCriteria();
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

		Criteria c = policyDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setLimit(index, offset, c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		DaoSupportUtil.setOrderBy(asc,columnOrder,c);
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Policy element = (Policy) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}
		return list;
	}
	// req end

// -- search end

//-- get total

	public int getTotal (String[] likeColumns, Object[] likeParams,
		String[] eqColumns, Object[] eqParams) throws Exception{

		Criteria c = policyDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		System.out.println("DIDALAM GET TOTAL");
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}
	public int getTotal (String[] likeColumns, Object[] likeParams,
		String[] eqColumns, Object[] eqParams,
		String[] btwnColumns, Object[] btwnParams1,
			Object[] btwnParams2
		) throws Exception{

		Criteria c = policyDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}
	public int getTotal (String likeColumns, Object likeParams,
		String eqColumns, Object eqParams) throws Exception{

		Criteria c = policyDao.getCriteria();
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

		Criteria c = policyDao.getCriteria();
		DaoSupportUtil.setLikeParam(likeColumns,likeParams,c);
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		DaoSupportUtil.setBetweenParam(btwnColumns,btwnParams1, btwnParams2,c);
		int total = DaoSupportUtil.getTotal(c);
		return total;

	}
//------------------------------------------------------------------
	public int getTotal () throws Exception{
		Criteria c = policyDao.getCriteria();
		int total = DaoSupportUtil.getTotal(c);
		return total;
	}
//-- get total end



//---------------------------------------------------
	public Collection getAll (String[] required) throws Exception{

		Criteria c = policyDao.getCriteria();
		List list = c.list();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Policy element = (Policy) iter.next();
   			DaoSupportUtil.lazyInit(required,element);
		}
		return list;

	}
	public Collection getAll () throws Exception{

		Criteria c = policyDao.getCriteria();
		List list = c.list();
		return list;
	}
//-------------------------------------------------

// unique Result

	public Policy searchUnique (String eqColumns, Object eqParams, String[] required)
		throws Exception{
		Criteria c = policyDao.getCriteria();
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		Policy obj = (Policy) c.uniqueResult();
		DaoSupportUtil.lazyInit(required,obj);
		return obj;
	}
	public Policy searchUnique (String eqColumns, Object eqParams)
		throws Exception{
		Criteria c = policyDao.getCriteria();
		DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
		Policy obj = (Policy) c.uniqueResult();
		return obj;
	}
	
	public Policy searchUnique (String[] eqColumns, Object[] eqParams,int index,int offset)
			throws Exception{
			Criteria c = policyDao.getCriteria();
			DaoSupportUtil.setEqParam(eqColumns,eqParams,c);
			DaoSupportUtil.setLimit(index, offset, c);
			Policy obj = (Policy) c.uniqueResult();
			return obj;
		}

// -----------------------------------------------

/*
BASIC IMPLEMENTATION !! USE WITH CAUTION !
USE IT IF NO OTHER OPTION LEFT
@return criteria
*/
	public Criteria getCriteria() throws Exception {
		Criteria c = policyDao.getCriteria();
		return c;
	}

	public DetachedCriteria getDetachedCriteria() throws Exception {
		DetachedCriteria dc = policyDao.getDetachedCriteria();
		return dc;
	}

	public Policy getGroupActivePolicy (Integer groupId,Integer clientId) throws Exception {
		Policy result = null;
		
		try {
			String[] eqParam = {"policyStatus.statusId","deletedStatus","memberGroupId.memberGroupId","clientId.clientId"};
			Object[] eqValue = {SubscriptionStatus.ACTIVE,Integer.valueOf(0),groupId,clientId};
			
			int total = getTotal(null,null,eqParam,eqValue);
			Collection<Policy> list = search(null,null,eqParam,eqValue,0, total);
			
			if (list != null){
				Iterator<Policy> iterator = list.iterator();
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
	public Policy getGroupActivePolicy (Integer groupId) throws Exception {
		Policy result = null;
		
		try {
			String[] eqParam = {"policyStatus.statusId","deletedStatus","memberGroupId.memberGroupId"};
			Object[] eqValue = {SubscriptionStatus.ACTIVE,Integer.valueOf(0),groupId};
			
			int total = getTotal(null,null,eqParam,eqValue);
			Collection<Policy> list = search(null,null,eqParam,eqValue,  0, total);
			
			if (list != null){
				Iterator<Policy> iterator = list.iterator();
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
	public Policy getActivePolicyByNumber (String policyNumber) throws Exception {
		Policy result = null;
		
		try {
			String[] eqParam = {"policyStatus.statusId","deletedStatus","policyNumber"};
			Object[] eqValue = {SubscriptionStatus.ACTIVE,Integer.valueOf(0),policyNumber};
			
			int total = getTotal(null,null,eqParam,eqValue);
			Collection<Policy> list = search(null,null,eqParam,eqValue,  0, total);
			
			if (list != null){
				Iterator<Policy> iterator = list.iterator();
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
	public Collection<Policy> getActivePolicy() throws Exception {
		// TODO Auto-generated method stub
		
		Collection<Policy> result = null;
		
		try {
			
			String[] eqParam = {"policyStatus"};
			Object[] eqValue = {SubscriptionStatus.ACTIVE};
			
			int total = getTotal(null,null,eqParam,eqValue);
			result = search(null,null,eqParam,eqValue,  0, total);
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	@Override
	public boolean activatePolicy(Long policyId, ActionUser user)
			throws Exception {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			
			Policy policy = get(policyId.intValue());
			if (policy != null){
				SubscriptionStatus status = new SubscriptionStatus();
				status.setStatusId(SubscriptionStatus.ACTIVE);			
				
				policy.setPolicyStatus(status);
				
				update(policy,user);
				
				result = true;
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	@Override
	public boolean terminatePolicy(Long policyId, Date terminateDate,
			ActionUser user) throws Exception {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			
			Policy policy = get(policyId.intValue());
			if (policy != null){
				SubscriptionStatus status = new SubscriptionStatus();
				status.setStatusId(SubscriptionStatus.TERMINATED);			
				
				policy.setExpireDate(terminateDate);
				policy.setPolicyStatus(status);
				
				update(policy,user);
				
				result = true;
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	@Override
	public boolean extend(Policy object, Date extendDate, Date expireDate,
			String notes, ActionUser user) throws Exception {
		// TODO Auto-generated method stub
		
		boolean result = false;
		try {
			
		

			String q = "SELECT m FROM Member m WHERE m.deletedStatus = 0 AND m.currentPolicyId.policyId = :pId AND m.status = :status";
			Query query = memberService.getMemberSession().createQuery(q);
			
			query.setInteger("pId", object.getPolicyId());
			query.setInteger("status", SubscriptionStatus.ACTIVE);
			
			Collection<Member> memberList = query.list();
			
			if (memberList != null){
				for (Iterator iterator = memberList.iterator(); iterator
						.hasNext();) {
					
					Member member = (Member) iterator.next();
					
					if (member != null){
						member.setPreviousExpireDate(member.getExpireDate());
						member.setExpireDate(expireDate);
						member.setStatus(SubscriptionStatus.EXTENDED_ACTIVE);
						
						memberService.update(member);
					}
					
				}
			}
			SubscriptionStatus status = new SubscriptionStatus();
			status.setStatusId(SubscriptionStatus.EXTENDED_ACTIVE);
			object.setPolicyStatus(status);
			object.setPreviousExpireDate(object.getExpireDate());
			object.setExpireDate(expireDate);
			object.setExtendedDate(extendDate);
			
			update(object, user);
			
			ActivityLog log = new ActivityLog();
			log.setAction("EXTEND POLICY");

			log.setUsername(user.getUser().getUsername());
			log.setActivityLogTime(new java.sql.Timestamp(System.currentTimeMillis()));
			log.setDescription("EXTEND POLICY WITH ID = " + object.getPolicyId());
			
			
			logService.create(log);

			
			
			result = true;
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	@Override
	public boolean renewal(Policy object, Date renewalDate, Date expireDate,
			String notes, ActionUser user) throws Exception {
		// TODO Auto-generated method stub
		
		boolean result = false;
		try {
			
			String q = "SELECT m FROM Member m WHERE m.deletedStatus = 0 AND m.currentPolicyId.policyId = :pId" +
					" AND (m.status = :status OR m.status = :other)";
			Query query = memberService.getMemberSession().createQuery(q);
			
			query.setInteger("pId", object.getPolicyId());
			query.setInteger("status", SubscriptionStatus.ACTIVE);
			query.setInteger("other", SubscriptionStatus.EXTENDED_ACTIVE);
			
			Collection<Member> memberList = query.list();
			
			if (memberList != null){
				for (Iterator iterator = memberList.iterator(); iterator
						.hasNext();) {
					
					Member member = (Member) iterator.next();
					
					if (member != null){
						member.setPreviousExpireDate(member.getExpireDate());
						member.setNextExpireDate(expireDate);
						member.setNextEffectiveDate(renewalDate);
						member.setExpireDate(expireDate);
						member.setRenewalDate(renewalDate);
						member.setStatus(SubscriptionStatus.PENDING_RENEWAL);
						
						memberService.update(member);
					}
					
				}
			}
			SubscriptionStatus status = new SubscriptionStatus();
			status.setStatusId(SubscriptionStatus.ACTIVE);
			object.setPolicyStatus(status);
			object.setPreviousExpireDate(object.getExpireDate());
			object.setExpireDate(expireDate);
			object.setRenewalDate(renewalDate);
			
			update(object, user);
			
			ActivityLog log = new ActivityLog();
			log.setAction("RENEW POLICY");

			log.setUsername(user.getUser().getUsername());
			log.setActivityLogTime(new java.sql.Timestamp(System.currentTimeMillis()));
			log.setDescription("RENEW POLICY WITH ID = " + object.getPolicyId());
			
			
			logService.create(log);

			
			
			result = true;
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;

	}
	public MemberDao getMemberService() {
		return memberService;
	}
	public void setMemberService(MemberDao memberService) {
		this.memberService = memberService;
	}
	@Override
	public byte[] getPolicyFile(Integer policyId, String fileName)
			throws Exception {
		// TODO Auto-generated method stub
		byte[] result = null;
		
		try {
			
			String[] required = {"Policy.ClientId"};
			Policy policy = get(policyId,required);
			
			if (policy != null){
				String documentDirectory = "";
				
				Configuration config = configurationService.get(policy.getClientId().getClientId());
				
				if (config == null){
					config = configurationService.getSystemConfiguration();
				}
				
				if (config != null){
					documentDirectory = config.getPolicyTermConditionPath() + System.getProperty("file.separator");
				}
				
				
				File theFile = new File(documentDirectory+fileName);
				
				if (theFile != null){
					result = new byte[(int)theFile.length()];
					
					FileInputStream instream = new FileInputStream(theFile);
					
					instream.read(result);
					
					instream.close();
				}
				
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	@Override
	public Policy getActivePolicyByNumber(String policyNumber, Integer clientId)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	public Policy getPolicyByNumber(String policyNumber, Integer clientId)
		throws Exception {
	// TODO Auto-generated method stub
		Policy result = null;
		
		String[] eqParam = {"policyNumber","clientId.clientId","deletedStatus"};
		Object[] eqValue = {policyNumber,clientId,0};
		
		Collection<Policy> policyList = search(null,null,eqParam,eqValue,0,1);
		
		if (policyList != null){
			Iterator<Policy> iterator = policyList.iterator();
			
			if (iterator.hasNext()){
				result = iterator.next();
			}
		}
		
		return result;
	}
	@Override
	public void updatePolicyExpiredStatus() throws Exception {
		// TODO Auto-generated method stub
		
		try {
			String qSelect = "SELECT p FROM Policy p WHERE p.expireDate < :exp AND p.policyStatus.statusId = :stat";
			Session session = policyDao.getPolicySession();
			
			Query q = session.createQuery(qSelect);
			q.setDate("exp", new java.sql.Date(System.currentTimeMillis()));
			q.setInteger("stat", SubscriptionStatus.ACTIVE);
			
			List<Policy> list = q.list();
			
			ActionUser actionUser = new ActionUser();
			User user = new User();
			user.setUsername("system-daemon");
			actionUser.setUser(user);
			
			SubscriptionStatus status = new SubscriptionStatus();
			status.setStatusId(SubscriptionStatus.TERMINATED);
			
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Policy policy = (Policy) iterator.next();
				
				if (policy != null){
					policy.setPolicyStatus(status);
					
					update(policy,actionUser);
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	@Override
	public boolean unblock(Integer policyId, String notes, ActionUser user)
			throws Exception {
		// TODO Auto-generated method stub
		
		Policy policy = get(policyId);
		SubscriptionStatus status = new SubscriptionStatus();
		status.setStatusId(SubscriptionStatus.ACTIVE);
		
		policy.setPolicyStatus(status);
		policyDao.update(policy);
		
		String q = "SELECT m FROM Member m WHERE m.deletedStatus = 0 AND m.currentPolicyId.policyId = :pId AND m.status = :stat";
		
		Session session = memberService.getMemberSession();
		Query query = session.createQuery(q);
		query.setInteger("pId", policyId);
		query.setInteger("stat", SubscriptionStatus.BLOCKED);
		
		Collection<Member> memberList = query.list();
		
		if (memberList != null){
			for (Iterator iterator = memberList.iterator(); iterator
					.hasNext();) {
				
				Member member = (Member) iterator.next();
				
				if (member != null ){
					member.setStatus(SubscriptionStatus.ACTIVE);
					member.setModifiedTime(new java.sql.Timestamp(System.currentTimeMillis()));
					memberService.update(member);
				}
			}
		}
		
		return true;
	}
	@Override
	public Collection<Policy> getClientActivePolicy(Integer clientId) throws Exception {
		// TODO Auto-generated method stub
		Collection<Policy> result = null;
		String[] eqParam = {"clientId.clientId","policyStatus.statusId","deletedStatus"};
		Object[] eqValue = {clientId,SubscriptionStatus.ACTIVE,0};
		
		int total = getTotal(null,null,eqParam,eqValue);
		
		result = search(null,null,eqParam,eqValue,0,total);
		
		return result;
	}
	@Override
	public Policy getActivePolicyByNumber(String policyNumber, String clientCode) throws Exception {
		// TODO Auto-generated method stub
		
		Policy result = null;
		
		String[] eqParam = {"policyNumber","clientId.clientCode","deletedStatus"};
		Object[] eqValue = {policyNumber,clientCode,0};
		
		Collection<Policy> policyList = search(null,null,eqParam,eqValue,0,1);
		
		if (policyList != null){
			Iterator<Policy> iterator = policyList.iterator();
			
			if (iterator.hasNext()){
				result = iterator.next();
			}
		}
		
		return result;
	}

}
