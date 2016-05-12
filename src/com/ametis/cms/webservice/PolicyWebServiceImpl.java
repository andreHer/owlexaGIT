package com.ametis.cms.webservice;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Vector;

import javax.jws.WebService;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.ActivityLog;
import com.ametis.cms.datamodel.MemberGroup;
import com.ametis.cms.datamodel.Policy;
import com.ametis.cms.datamodel.Product;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.ActivityLogService;
import com.ametis.cms.service.MemberGroupService;
import com.ametis.cms.service.MemberService;
import com.ametis.cms.service.PolicyService;
import com.ametis.cms.service.ProductService;

@WebService(name = "PolicyWebService", endpointInterface = "com.ametis.cms.webservice.IPolicyWebService")

public class PolicyWebServiceImpl implements IPolicyWebService{

	private PolicyService policyService;
	private ProductService productService;
	private MemberService memberService;
	private ActivityLogService activityLogService;
	
	private MemberGroupService memberGroupService;
	
	
	public PolicyService getPolicyService() {
		return policyService;
	}
	public void setPolicyService(PolicyService policyService) {
		this.policyService = policyService;
	}
	public ProductService getProductService() {
		return productService;
	}
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	public MemberService getMemberService() {
		return memberService;
	}
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	public ActivityLogService getActivityLogService() {
		return activityLogService;
	}
	public void setActivityLogService(ActivityLogService activityLogService) {
		this.activityLogService = activityLogService;
	}
	public MemberGroupService getMemberGroupService() {
		return memberGroupService;
	}
	public void setMemberGroupService(MemberGroupService memberGroupService) {
		this.memberGroupService = memberGroupService;
	}
	public Collection<Integer> getActivePolicy() throws Exception{
		Collection<Integer> result = new Vector<Integer>();
		
		try {
			Collection<Policy> policyList = policyService.getActivePolicy();
			
			for (Policy policy : policyList) {
				if (policy != null){
					result.add(policy.getPolicyId());
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	public void synchronizePolicy(Integer policyId) throws Exception {
		// TODO Auto-generated method stub
		try {

			if (policyId != null){
				
				Policy policy = policyService.get(policyId);
				
				MemberGroup memberGroup = policy.getMemberGroupId();
				
				int newBusinessMember = 0;
				double newBusinessValue = 0;
				int movementMember = 0;
				double movementValue = 0;
				int terminationMember = 0;
				double terminationValue = 0;
				
				policy.setMovementMember(movementMember);
				policy.setMovementValue(movementValue);
				policy.setNewBusinessMember(newBusinessMember);
				policy.setNewBusinessValue(newBusinessValue);
				policy.setTerminationMember(terminationMember);
				policy.setTerminationValue(terminationValue);
				
				User user = new User();
				user.setUsername("system-daemon");
				ActionUser actionUser = new ActionUser();
				actionUser.setUser(user);
				
				policyService.update(policy, actionUser);
				
				ActivityLog log = new ActivityLog();
				log.setUsername("system-daemon");
				log.setAction("SYNCHRONIZE-POLICY");
				log.setActivityLogTime(new Timestamp(System.currentTimeMillis()));
				log.setDescription("Update Policy for : " + policy.getPolicyNumber());
				
				activityLogService.create(log);
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	@Override
	public boolean commitSynchronize(Integer policyId) throws Exception {
		// TODO Auto-generated method stub
		boolean result = false;
		
		try {
			Policy policy = policyService.get(policyId);
			
			if (policy != null){
				
				ActionUser user = new ActionUser();
				User theUser = new User();
				theUser.setUsername("activator");
				
				user.setUser(theUser);
				
				policy.setDoSynchronize(0);
				
				policyService.update(policy, user);
				
				result = true;
				
				String[] eqParam = {"policyId.policyId","deletedStatus"};
				Object[] eqValue = {policyId,0};
				
				int total = productService.getTotal(null,null,eqParam,eqValue);
				
				Collection<Product> productList = productService.search(null,null,eqParam,eqValue,0,total);
				
				if (productList != null){
					for (Product product : productList) {
						if (product != null){
							product.setDoSynchronize(0);
							
							productService.update(product, user);
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
	@Override
	public Collection<Integer> getPolicySynchronizeList() throws Exception {
		// TODO Auto-generated method stub
		Collection<Integer> result = new Vector<Integer>();
		
		try {
			System.out.println("GETTING POLICY SYNCHRONIZE LIST");
			String[] eqParam = {"doSynchronize","deletedStatus"};
			Object[] eqValue = {Integer.valueOf(1),Integer.valueOf(0)};
			
			int total = policyService.getTotal(null,null,eqParam,eqValue);
			System.out.println("TOTAL POLICY : "+ total);
			
			Collection<Policy> policyList = policyService.search(null,null,eqParam,eqValue,0,total);
			
			for (Policy policy : policyList) {
				if (policy != null){
					System.out.println("DAPET POLICY : " + policy.getPolicyId());
					result.add(policy.getPolicyId());
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	@Override
	public void updateExpiredPolicy() throws Exception {
		// TODO Auto-generated method stub
		try {
			policyService.updatePolicyExpiredStatus();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
	}

}
