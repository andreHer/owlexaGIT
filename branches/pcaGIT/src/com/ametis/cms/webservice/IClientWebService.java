package com.ametis.cms.webservice;

import java.util.Collection;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.ametis.cms.dto.ClientDto;

@WebService (targetNamespace="http://ametis.co.id/services/ClientWebService")
public interface IClientWebService {

	@WebMethod
	public Collection<ClientDto> getClientList ();//buatan Luthfi, penambahan method getclientlist dari tabel client.
		
}
 