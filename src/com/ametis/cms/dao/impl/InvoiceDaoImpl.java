
package com.ametis.cms.dao.impl;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ametis.cms.dao.InvoiceDao;
import com.ametis.cms.datamodel.Invoice;
import com.ametis.cms.util.dao.DaoSupportUtil;

// imports+ 

// imports- 

/**
 * InvoiceDao adalah bean implementation untuk DAO tabel invoice.
*/
public class InvoiceDaoImpl extends DaoSupportUtil implements InvoiceDao

// extends+ 

// extends- 
{
	

	/*
	* Method create (Invoice object) berfungsi untuk melakukan penambahan
	* sebuah object kedalam database
	* @param object adalah sebuah object yang ingin diubah
	* @return object baru hasil create dengan assigned primary key , exception jika gagal
	*/
	public Invoice create (Invoice object) throws DataAccessException {
		this.getHibernateTemplate().save(object);
		return object;

	}
	/*
	* Method updateInvoice (Invoice object) berfungsi untuk melakukan perubahan terhadap
	* sebuah object yang terdapat didalam database
	* @param object adalah sebuah object yang ingin diubah
	* @return object hasil update apabila proses update berhasil dilakukan, dan exception jika gagal.
	*/
	public Invoice update (Invoice object) throws DataAccessException{
 	    this.getHibernateTemplate().update(object);
	    return object;
	}
	/*
	* Method delete (Invoice object) berfungsi untuk melakukan penghapusan terhadap
	* sebuah object yang terdapat didalam database
	* @param object adalah sebuah object yang ingin dihapus, isi dari object tersebut cukup dengan
	*		 mengisi field-field primary key
	* @return no return value karena objeknya sendiri sudah dihapus - just for consistency. Again,
	* exception if fail
	*
	*/

	public Invoice delete (Invoice object) throws DataAccessException{
		this.getHibernateTemplate().delete(object);
		return object;
	}
	/*
	* Method get (Invoice object) berfungsi untuk melakukan retrieval terhadap
	* sebuah object yang terdapat didalam database
	* @param object adalah sebuah object yang mempunyai ciri-ciri (example) sesuai dengan data yang diinginkan
	* @return Object yang dihasilkan dari proses retrieval, apabila object tidak ditemukan
	*	     maka method akan mengembalikan nilai "NULL"
	*/

	public Invoice get (java.io.Serializable pkey) throws DataAccessException {
		return (Invoice) this.getHibernateTemplate().get (Invoice.class, pkey);
	}


/*
BASIC IMPLEMENTATION !! USE WITH CAUTION !
USE IT IF NO OTHER OPTION LEFT
@return criteria
*/
	public Criteria getCriteria() throws Exception {
		HibernateTemplate template = this.getHibernateTemplate();
		SessionFactory sessionFactory = template.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Invoice.class);
		return criteria;
	}

/*
BASIC IMPLEMENTATION !! USE WITH CAUTION !
USE IT IF NO OTHER OPTION LEFT
WARNING !! DONT" FORGET TO SET THE PROJECTION
example : detachedCriteria.setProjection(Property.forName("primary_key_field"));
@return DetachedCriteria
*
*/
	public DetachedCriteria getDetachedCriteria() throws Exception {
		DetachedCriteria dc = DetachedCriteria.forClass(Invoice.class);
		return dc;
	}
	
	public Session getInvoiceSession() throws Exception {
		// TODO Auto-generated method stub
		HibernateTemplate template = this.getHibernateTemplate();
		SessionFactory sessionFactory = template.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		
		return session;
	}



//------------------------------------------------
// GAGAL TERUS -GAK SEMUA JALAN DENGAN BAIK - DINONAKTIFKAN
/*	public Collection searchInvoice (Invoice object) throws Exception{

		HibernateTemplate template = getHibernateTemplate();
		SessionFactory sessionFactory = template.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Invoice.class);
		criteria.add(Example.create(object));
		return criteria.list();
	}
*/
// class+ 

// class- 
}
