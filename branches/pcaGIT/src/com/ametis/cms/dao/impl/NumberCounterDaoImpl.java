package com.ametis.cms.dao.impl;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ametis.cms.dao.NumberCounterDao;
import com.ametis.cms.datamodel.Claim;
import com.ametis.cms.datamodel.NumberCounter;
import com.ametis.cms.util.dao.DaoSupportUtil;

public class NumberCounterDaoImpl extends DaoSupportUtil implements NumberCounterDao{

	public Criteria getCriteria() throws Exception {
		HibernateTemplate template = this.getHibernateTemplate();
		SessionFactory sessionFactory = template.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(NumberCounter.class);
		return criteria;
	}
	public NumberCounter getNumberCounter(int clientId) throws Exception {
		// TODO Auto-generated method stub
		String[] eqColumn = {"client.clientId"};
		Object[] eqParam = {Integer.valueOf(clientId)};

		Criteria c = getCriteria();
		DaoSupportUtil.setEqParam(eqColumn, eqParam, c);
		DaoSupportUtil.setLimit(0, 1, c);
		
		List list = c.list();
		
		NumberCounter result = null;
		
		if (list != null){
			Iterator<NumberCounter> iterator = list.iterator();
			
			if (iterator != null && iterator.hasNext()){
				result = iterator.next();
			}
		}
		

		//return (NumberCounter) this.getHibernateTemplate().load(NumberCounter.class, Integer.valueOf(0));
		return result;
	}

	public NumberCounter updateNumberCounter(NumberCounter counter) throws Exception {
		// TODO Auto-generated method stub
		 this.getHibernateTemplate().save(counter);
		 return counter;
	}
	

	
}
