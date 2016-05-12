package com.ametis.cms.webservice;

import java.sql.Date;
import java.util.Collection;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.ametis.cms.datamodel.ActionUser;
import com.ametis.cms.dto.BankAccountDto;
import com.ametis.cms.dto.MemberDto;
import com.ametis.cms.dto.PolicyDto;
import com.ametis.cms.dto.ProviderDto;
import com.ametis.cms.dto.CaseDto;

@WebService (targetNamespace="http://ametis.co.id/services/CaseWebService")

public interface ICaseWebService {


	@WebMethod
	public Collection<CaseDto> searchCaseByCaseNumber (String caseNumber);//buatan luthfi
	@WebMethod
	public Collection<CaseDto> getCaseList();
}