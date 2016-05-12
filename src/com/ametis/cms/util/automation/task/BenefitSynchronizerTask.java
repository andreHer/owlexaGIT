package com.ametis.cms.util.automation.task;

import java.util.Collection;
import java.util.Iterator;


import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.Member;
import com.ametis.cms.datamodel.Policy;
import com.ametis.cms.datamodel.Product;
import com.ametis.cms.datamodel.User;
import com.ametis.cms.service.MemberService;
import com.ametis.cms.service.PolicyService;
import com.ametis.cms.service.ProductService;

public class BenefitSynchronizerTask {

	private MemberService memberService;
	private PolicyService policyService;
	private ProductService productService;
	
	

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public PolicyService getPolicyService() {
		return policyService;
	}

	public void setPolicyService(PolicyService policyService) {
		this.policyService = policyService;
	}

	public MemberService getMemberService() {
		return memberService;
	}

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	public void activateSynchronize (){
		try {
			
			User theUser = new User();
			theUser.setUsername("system-activator");
			
			ActionUser actionUser = new ActionUser();
			actionUser.setUser(theUser);
			
			System.out.println("GETTING POLICY SYNCHRONIZE LIST");
			String[] eqParam = {"doSynchronize","deletedStatus"};
			Object[] eqValue = {Integer.valueOf(1),Integer.valueOf(0)};
			
			int total = policyService.getTotal(null,null,eqParam,eqValue);
			System.out.println("TOTAL POLICY : "+ total);
			
			Collection<Policy> policyList = policyService.search(null,null,eqParam,eqValue,0,total);
			
			
			for (Policy policy : policyList) {
				if (policy != null){
					Collection<Object> pendingEmployeeList = memberService.getAllPendingSynchronizeEmployee(policy.getPolicyId());
					Collection<Object> pendingMemberList = memberService.getAllPendingSynchronizeMember(policy.getPolicyId());
					
					for (Iterator iterator = pendingEmployeeList.iterator(); iterator.hasNext();) {
						Object object = (Object) iterator.next();
						
						System.out.println(object.toString() + " Hash = " + object.hashCode() + " CLASS = " + object.getClass());
						if (object instanceof Member){
							Member memberId = (Member) object;
							
							try {
								System.out.println("SYNCHRONIZE EMPLOYEE ID = " + memberId.getMemberId());
								memberService.activateSynchronize(memberId.getMemberId(), actionUser);
							}
							catch (Exception e){
								e.printStackTrace();
							}
						}
						
					}
					
					for (Iterator iterator = pendingMemberList.iterator(); iterator.hasNext();) {
						Object object = (Object) iterator.next();
						
						if (object instanceof Member){
							Member memberId = (Member) object;
							try {
								System.out.println("SYNCHRONIZE DEPENDENT ID = " + memberId.getMemberId());
								memberService.activateSynchronize(memberId.getMemberId(), actionUser);
							}
							catch (Exception e){
								e.printStackTrace();
							}
						}
						if (object instanceof Long){
							Integer memberId = ((Long) object).intValue();
							
							try {
								System.out.println("SYNCHRONIZE EMPLOYEE ID = " + memberId);
								memberService.activateSynchronize(memberId, actionUser);
							}
							catch (Exception e){
								e.printStackTrace();
							}
						}
					}
					
					policy.setDoSynchronize(0);
					policyService.update(policy, actionUser);
					
					String[] eqParamProduct = {"doSynchronize","policyId.policyId","deletedStatus"};
					Object[] eqValueProduct = {1, policy.getPolicyId(),0};
					
					int totalProduct = productService.getTotal(null,null,eqParamProduct,eqValueProduct);
					Collection<Product> productList = productService.search(null,null,eqParamProduct,eqValueProduct,0,totalProduct);
					
					for (Iterator iterator = productList.iterator(); iterator.hasNext();) {
						Product product = (Product) iterator.next();
						
						if (product != null){
							product.setDoSynchronize(0);
							productService.update(product, actionUser);
						}
					}
				}
			}			
		}catch (Exception e){
			e.printStackTrace();
		}

	}
}
