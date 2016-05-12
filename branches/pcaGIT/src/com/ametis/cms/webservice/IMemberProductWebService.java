package com.ametis.cms.webservice;

import java.util.Collection;

import javax.jws.WebService;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.Diagnosis;
import com.ametis.cms.dto.CaseCategoryDto;
import com.ametis.cms.dto.MemberBenefitDto;
@WebService (targetNamespace="http://ametis.co.id/services/MemberProductWebService")
public interface IMemberProductWebService {
	public Collection<com.ametis.cms.dto.MemberProductDto> getAllMemberProduct (Integer memberGroupId);
	public boolean synchronize (Integer memberProductId, ActionUser actionUser);
	public Collection<CaseCategoryDto> getGroupCaseCategories(Integer groupId);
	
}
