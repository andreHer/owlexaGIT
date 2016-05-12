package com.ametis.cms.webservice;

import java.util.Collection;

import javax.jws.WebService;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.Diagnosis;
import com.ametis.cms.dto.MemberBenefitDto;
@WebService (targetNamespace="http://ametis.co.id/services/MemberBenefitWebService")

public interface IMemberBenefitWebService {

	public Collection<MemberBenefitDto> getAllMemberBenefit (Integer memberGroupId);
	public boolean synchronize (Integer memberBenefitId, ActionUser actionUser);
}
