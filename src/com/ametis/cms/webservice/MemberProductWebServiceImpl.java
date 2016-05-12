package com.ametis.cms.webservice;

import java.util.Collection;
import java.util.Vector;

import javax.jws.WebService;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.dto.CaseCategoryDto;
import com.ametis.cms.dto.MemberProductDto;
import com.ametis.cms.service.ClaimService;
import com.ametis.cms.service.MemberProductService;
import com.ametis.cms.service.MemberService;
import com.ametis.cms.service.ProductService;

@WebService(name = "MemberProductWebService", endpointInterface = "com.ametis.cms.webservice.IMemberProductWebService")

public class MemberProductWebServiceImpl implements IMemberProductWebService {
	private MemberProductService memberProductService;
	private MemberService memberService;
	private ProductService productService;
	private ClaimService claimService;
	
	
	
	public ClaimService getClaimService() {
		return claimService;
	}

	public void setClaimService(ClaimService claimService) {
		this.claimService = claimService;
	}

	public MemberProductService getMemberProductService() {
		return memberProductService;
	}

	public void setMemberProductService(MemberProductService memberProductService) {
		this.memberProductService = memberProductService;
	}

	public MemberService getMemberService() {
		return memberService;
	}

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public Collection<MemberProductDto> getAllMemberProduct(
			Integer memberGroupId) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean synchronize(Integer memberProductId, ActionUser actionUser) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public Collection<CaseCategoryDto> getGroupCaseCategories(Integer groupId) {
		// TODO Auto-generated method stub
		Collection<CaseCategoryDto> result = new Vector<CaseCategoryDto>();
		try {
			String[] eqParam = {"deletedStatus","isProductActive"};
			Object[] eqValue = {};
			
			
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}

}
