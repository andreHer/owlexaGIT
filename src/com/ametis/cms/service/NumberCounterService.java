package com.ametis.cms.service;

public interface NumberCounterService {

	public int getNextClaimCounter(int clientId);
	public int getNextBatchClaimCounter(int clientId);
	public int getNextFundCounter(int clientId);
	public int getNextPaymentCounter(int clientId);
	public int getNextGuaranteeLetterCounter(int clientId);
	public int getNextGuaranteeNumberCounter(int clientId);
	public int getNextOutstandingCounter(int clientId);
	public int getNextExcessCounter(int clientId);
	public int getNextCallCounter (int clientId);
	public int getNextPendingCounter(int clientId);

	
	
}
