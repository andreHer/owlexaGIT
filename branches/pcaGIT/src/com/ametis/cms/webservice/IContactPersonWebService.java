package com.ametis.cms.webservice;

import java.util.Collection;

import javax.jws.WebService;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.datamodel.Diagnosis;
import com.ametis.cms.dto.MemberBenefitDto;
import com.ametis.cms.dto.ContactPersonDto;

@WebService (targetNamespace="http://ametis.co.id/services/ContactPersonWebService")

public interface IContactPersonWebService {

	public Collection<ContactPersonDto> searchContactPersonByName (String name);
//	public Collection<ContactPersonDto> searchContactPersonByCardNumber (String cardNumber);

}
