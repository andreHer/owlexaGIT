package com.ametis.cms.service.impl;

import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;

import com.ametis.cms.dao.NumberCounterDao;
import com.ametis.cms.datamodel.Client;
import com.ametis.cms.datamodel.NumberCounter;
import com.ametis.cms.service.NumberCounterService;
import com.ametis.cms.util.dao.DaoSupportUtil;

public class NumberCounterServiceImpl implements NumberCounterService{

	private NumberCounterDao numberCounterDao;
	
	
	
	public NumberCounterDao getNumberCounterDao() {
		return numberCounterDao;
	}

	public void setNumberCounterDao(NumberCounterDao numberCounterDao) {
		this.numberCounterDao = numberCounterDao;
	}

	public int getNextPendingCounter(int clientId){
		int counter = 0;
		
		try {
				
			NumberCounter ctr = numberCounterDao.getNumberCounter(clientId);
			
			if (ctr == null){
				ctr = createNumberCounter(clientId);
			}
			
			counter = ctr.getPendingNumberCounter();
			
			counter += 1;
			
			ctr.setPendingNumberCounter(counter);
			numberCounterDao.updateNumberCounter(ctr);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return counter;
	}
	private NumberCounter createNumberCounter(int clientId){
		NumberCounter counter = null;
		try {
			Client client = new Client();
			client.setClientId(Integer.valueOf(clientId));
			
			counter = new NumberCounter();
			counter.setBatchNumberCounter(0);
			counter.setCallNumberCounter(0);
			counter.setClaimNumberCounter(0);
			counter.setClient(client);
			counter.setExcessNumberCounter(0);
			counter.setFundNumberCounter(0);
			counter.setGuaranteeLettterCounter(0);
			counter.setGuaranteeNumberCounter(0);
			counter.setOtherPaymentCounter(0);
			counter.setOutstandingNumberCounter(0);
			counter.setPaymentNumberCounter(0);
			counter.setPendingNumberCounter(0);
			
			counter = numberCounterDao.updateNumberCounter(counter);
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return counter;
	}
	public int getNextBatchClaimCounter(int clientId) {
		// TODO Auto-generated method stub
		
		int counter = 0;
		
		try {
	
		
			NumberCounter ctr = numberCounterDao.getNumberCounter(clientId);
			
			if (ctr == null){
				ctr = createNumberCounter(clientId);
			}
			
			counter = ctr.getBatchNumberCounter();
			
			counter += 1;
			
			ctr.setBatchNumberCounter(counter);
			numberCounterDao.updateNumberCounter(ctr);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return counter;
	}

	public int getNextClaimCounter(int clientId) {
		// TODO Auto-generated method stub
		int counter = 0;
		
		try {
			NumberCounter ctr = numberCounterDao.getNumberCounter(clientId);
			
			if (ctr == null){
				ctr = createNumberCounter(clientId);
			}
			
			counter = ctr.getClaimNumberCounter();
			
			counter += 1;
			
			ctr.setClaimNumberCounter(counter);
			numberCounterDao.updateNumberCounter(ctr);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return counter;
	}

	public int getNextExcessCounter(int clientId) {
		// TODO Auto-generated method stub
		int counter = 0;
		
		try {
			NumberCounter ctr = numberCounterDao.getNumberCounter(clientId);
			
			if (ctr == null){
				ctr = createNumberCounter(clientId);
			}
			
			counter = ctr.getExcessNumberCounter();
			
			counter += 1;
			
			ctr.setExcessNumberCounter(counter);
			numberCounterDao.updateNumberCounter(ctr);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return counter;
	}

	public int getNextFundCounter(int clientId) {
		// TODO Auto-generated method stub
		int counter = 0;
		
		try {
			NumberCounter ctr = numberCounterDao.getNumberCounter(clientId);
			if (ctr == null){
				ctr = createNumberCounter(clientId);
			}
			
			counter = ctr.getFundNumberCounter();

			counter += 1;
			
			ctr.setFundNumberCounter(counter);
			numberCounterDao.updateNumberCounter(ctr);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return counter;
	}

	public int getNextGuaranteeLetterCounter(int clientId) {
		// TODO Auto-generated method stub
		int counter = 0;
		
		try {
			NumberCounter ctr = numberCounterDao.getNumberCounter(clientId);
			
			if (ctr == null){
				ctr = createNumberCounter(clientId);
			}
			counter = ctr.getGuaranteeLettterCounter();
		
			counter += 1;
			
			ctr.setGuaranteeLettterCounter(counter);
			numberCounterDao.updateNumberCounter(ctr);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return counter;
	}

	public int getNextGuaranteeNumberCounter(int clientId) {
		// TODO Auto-generated method stub
		int counter = 0;
		
		try {
			NumberCounter ctr = numberCounterDao.getNumberCounter(clientId);
			if (ctr == null){
				ctr = createNumberCounter(clientId);
			}
			counter = ctr.getGuaranteeNumberCounter();
			
			counter += 1;
			
			ctr.setGuaranteeNumberCounter(counter);
			numberCounterDao.updateNumberCounter(ctr);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return counter;
	}

	public int getNextOutstandingCounter(int clientId) {
		// TODO Auto-generated method stub
		int counter = 0;
		
		try {
			NumberCounter ctr = numberCounterDao.getNumberCounter(clientId);
			
			if (ctr == null){
				ctr = createNumberCounter(clientId);
			}
			counter = ctr.getOutstandingNumberCounter();
			
			counter += 1;
			
			ctr.setOutstandingNumberCounter(counter);
			numberCounterDao.updateNumberCounter(ctr);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return counter;
	}

	public int getNextPaymentCounter(int clientId) {
		// TODO Auto-generated method stub
		int counter = 0;
		
		try {
			NumberCounter ctr = numberCounterDao.getNumberCounter(clientId);
			if (ctr == null){
				ctr = createNumberCounter(clientId);
			}
			counter = ctr.getPaymentNumberCounter();
			counter += 1;
			
			ctr.setPaymentNumberCounter(counter);
			numberCounterDao.updateNumberCounter(ctr);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return counter;
	}

	public int getNextCallCounter(int clientId) {
	int counter = 0;
		
		try {
			NumberCounter ctr = numberCounterDao.getNumberCounter(clientId);
			if (ctr == null){
				ctr = createNumberCounter(clientId);
			}
			counter = ctr.getCallNumberCounter();
			counter += 1;
			
			ctr.setCallNumberCounter(counter);
			numberCounterDao.updateNumberCounter(ctr);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return counter;
	}



}
