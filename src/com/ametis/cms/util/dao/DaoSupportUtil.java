
package com.ametis.cms.util.dao;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


// imports+ 

// imports- 

public class DaoSupportUtil extends HibernateDaoSupport

// extends+ 

// extends- 
{
	


	/*
	 * Uyo ini ada catatan dari gue, buat persistent set belum di handle ya
	 * contoh gini : a relasi one to many ke b a punya persistent set donk atas
	 * b di class a nah gimana cara invoke fieldoffset dari masingoffset anggota
	 * set dari b ? apa dibikin representasi tree aja ya ? dengan dibedakan
	 * masingoffset tree node yang mau di invoke ?
	 * 
	 */

	public static void lazyInit(String[] need, Object elem) {
		if (need == null)
			return;
		for (int ii = 0; ii < need.length; ii++) {
			String[] strings = need[ii].split("[.]");
			Object obj = elem;
			Object objtmp = null;
			
			if (obj != null){
				if (obj.getClass().getSimpleName().equals(strings[0])) {
					for (int i = 1; i < strings.length; i++) {
						try {
							if (obj instanceof Collection) {
								Collection coll = (Collection) obj;
								ArrayList ls = new ArrayList(coll.size());
								for (Iterator iter = coll.iterator(); iter
										.hasNext();) {
									Object element = (Object) iter.next();
									
									Method method = element.getClass().getMethod(
											"get" + strings[i], null);
									objtmp = method.invoke(element, null);									
									
									ls.add(objtmp);
									// break;
								}
								obj = ls;
								if (obj == null)
									break;
							} else {
								Method method = obj.getClass().getMethod(
										"get" + strings[i], null);
								obj = method.invoke(obj, null);
								if (obj == null)
									break;
							}

							
							
							
							System.out.println("get" + strings[i] + " = " + obj);
						} catch (SecurityException e) {
							e.printStackTrace();
						} catch (NoSuchMethodException e) {
							e.printStackTrace();
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}

	protected Object searchMax(String column, Class theClass) {
		Criteria criteria = getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createCriteria(theClass);
		criteria.setProjection(Projections.max(column));
		List list = criteria.list();
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	protected Object searchMin(String column, Class theClass) {
		Criteria criteria = getHibernateTemplate().getSessionFactory()
				.getCurrentSession().createCriteria(theClass);
		criteria.setProjection(Projections.min(column));
		List list = criteria.list();
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	public static void setLikeParam(String[] likeColumns, Object[] likeParams, Criteria criteria) {
		if(likeColumns==null||likeParams==null){
			return;
		}
		for (int i0 = 0; i0 < likeColumns.length; i0++) {
			if(likeColumns[i0].indexOf(".")>=0){
				StringTokenizer st = new StringTokenizer(likeColumns[i0],".");
				int tokens = st.countTokens();
				String subAlias="";
				String subs="";
				String checkAlias="";
				for (int i = 0; i < tokens-1; i++) {
					String aliases = st.nextToken();
					if(i==0){
						subAlias=aliases;
					}else{
						subAlias=subs+"."+aliases;
					}
					//Add by Feiby Veronika
					//To avoid Duplicate alias in Criteria
					//CMIIW
					//System.out.println("Position(" + aliases + ") : "+criteria.toString().indexOf(aliases));
					checkAlias = subAlias.concat(":").concat(aliases);
					//if(criteria.toString().indexOf(aliases)<0){
					//	criteria.createAlias(subAlias,aliases,CriteriaSpecification.LEFT_JOIN);
					//}
					if(criteria.toString().indexOf(checkAlias)<0){
						criteria.createAlias(subAlias,aliases);
					}
					subs = aliases;
				}
				int j = likeColumns[i0].lastIndexOf(".");
				int k = likeColumns[i0].lastIndexOf(".",j-1);
				criteria.add(Restrictions.ilike(likeColumns[i0].substring(k+1),(String) likeParams[i0],MatchMode.ANYWHERE));
			}else{
				criteria.add(Restrictions.ilike(likeColumns[i0],(String)likeParams[i0],MatchMode.ANYWHERE));
			}
		}
	}
	
	public static void setLikeParamOr(String[] likeColumns, Object[] likeParams, Criteria criteria) {
		Disjunction or = Restrictions.disjunction();
		if(likeColumns==null||likeParams==null){
			return;
		}
		for (int i0 = 0; i0 < likeColumns.length; i0++) {
			if(likeColumns[i0].indexOf(".")>=0){
				StringTokenizer st = new StringTokenizer(likeColumns[i0],".");
				int tokens = st.countTokens();
				String subAlias="";
				String subs="";
				String checkAlias="";
				for (int i = 0; i < tokens-1; i++) {
					String aliases = st.nextToken();
					if(i==0){
						subAlias=aliases;
					}else{
						subAlias=subs+"."+aliases;
					}
					//Add by Feiby Veronika
					//To avoid Duplicate alias in Criteria
					//CMIIW
					//System.out.println("Position(" + aliases + ") : "+criteria.toString().indexOf(aliases));
					checkAlias = subAlias.concat(":").concat(aliases);
					//if(criteria.toString().indexOf(aliases)<0){
					//	criteria.createAlias(subAlias,aliases,CriteriaSpecification.LEFT_JOIN);
					//}
					if(criteria.toString().indexOf(checkAlias)<0){
						criteria.createAlias(subAlias,aliases);
					}
					subs = aliases;
				}
				int j = likeColumns[i0].lastIndexOf(".");
				int k = likeColumns[i0].lastIndexOf(".",j-1);
				or.add(Restrictions.ilike(likeColumns[i0].substring(k+1),(String) likeParams[i0],MatchMode.ANYWHERE));
				System.out.println("Restrictions.disjunction() 1: "+criteria.toString());
			}else{
				or.add(Restrictions.ilike(likeColumns[i0],(String)likeParams[i0],MatchMode.ANYWHERE));
				System.out.println("Restrictions.disjunction() 2: "+criteria.toString());
			}
			criteria.add(or);
		}
	}
	
	public static void setLikeParam(String likeColumns, Object likeParams, Criteria criteria) {
		if(likeColumns==null||likeParams==null){
			return;
		}
			if(likeColumns.indexOf(".")>=0){
				StringTokenizer st = new StringTokenizer(likeColumns,".");
				int tokens = st.countTokens();
				String subAlias="";
				String subs="";
				for (int i = 0; i < tokens-1; i++) {
					String aliases = st.nextToken();
					if(i==0){
						subAlias=aliases;
					}else{
						subAlias=subs+"."+aliases;
					}
					criteria.createAlias(subAlias,aliases);
					subs = aliases;
				}
				int j = likeColumns.lastIndexOf(".");
				int k = likeColumns.lastIndexOf(".",j-1);
				criteria.add(Restrictions.ilike(likeColumns.substring(k+1),(String) likeParams,MatchMode.ANYWHERE));
			}else{
				criteria.add(Restrictions.ilike(likeColumns,(String)likeParams,MatchMode.ANYWHERE));
				
			}
	}

	public static void setEqParam(String[] eqColumns, Object[] eqParams, Criteria criteria) {
		if(eqColumns==null||eqParams==null){
			return;
		}
		for (int i0 = 0; i0 < eqColumns.length; i0++) {
			if(eqColumns[i0].indexOf(".")>=0){
				StringTokenizer st = new StringTokenizer(eqColumns[i0],".");
				int tokens = st.countTokens();
				String subAlias="";
				String subs="";
				String aliasCheckExist ="";
				for (int i = 0; i < tokens-1; i++) {
					String aliases = st.nextToken();
					if(i==0){
						subAlias=aliases;
					}else{
						subAlias=subs+"."+aliases;
					}
					//Add by Feiby Veronika
					//To avoid Duplicate alias in Criteria
					//CMIIW
					//System.out.println("Position(" + aliases + ") : "+criteria.toString().indexOf(aliases));
					aliasCheckExist = subAlias.concat(":").concat(aliases);
					//if(criteria.toString().indexOf(aliases)<0){
					//	criteria.createAlias(subAlias,aliases,CriteriaSpecification.LEFT_JOIN);
					//}
					if(criteria.toString().indexOf(aliasCheckExist)<0){
						criteria.createAlias(subAlias,aliases);
					}
					subs = aliases;
				}
				int j = eqColumns[i0].lastIndexOf(".");
				int k = eqColumns[i0].lastIndexOf(".",j-1);
//				if (eqColumns != null) {
//					for (int i0 = 0; i0 < eqColumns.length; i0++) {
						criteria.add(Property.forName(eqColumns[i0].substring(k+1)).eq(eqParams[i0]));
//					}
//				}
			}else{
				criteria.add(Property.forName(eqColumns[i0]).eq(eqParams[i0]));
			}
			
		}
		//System.out.println("SQL EQ:"+criteria);
	}
	public static void setEqParam(String eqColumns, Object eqParams, Criteria criteria) {
		if(eqColumns==null||eqParams==null){
			return;
		}
		if(eqColumns.indexOf(".")>=0){
			StringTokenizer st = new StringTokenizer(eqColumns,".");
			int tokens = st.countTokens();
			String subAlias="";
			String subs="";
			for (int i = 0; i < tokens-1; i++) {
				String aliases = st.nextToken();
				if(i==0){
					subAlias=aliases;
				}else{
					subAlias=subs+"."+aliases;
				}
				criteria.createAlias(subAlias,aliases);
				subs = aliases;
			}
			int j = eqColumns.lastIndexOf(".");
			int k = eqColumns.lastIndexOf(".",j-1);
			criteria.add(Property.forName(eqColumns.substring(k+1)).eq(eqParams));
		}else{
			criteria.add(Property.forName(eqColumns).eq(eqParams));
		}
	}
	
	//[Start]Add by aju on 20150819, for Not Equal :D
	public static void setNeqParam(String[] eqColumns, Object[] eqParams, Criteria criteria) {
		if(eqColumns==null||eqParams==null){
			return;
		}
		for (int i0 = 0; i0 < eqColumns.length; i0++) {
			if(eqColumns[i0].indexOf(".")>=0){
				StringTokenizer st = new StringTokenizer(eqColumns[i0],".");
				int tokens = st.countTokens();
				String subAlias="";
				String subs="";
				String aliasCheckExist ="";
				for (int i = 0; i < tokens-1; i++) {
					String aliases = st.nextToken();
					if(i==0){
						subAlias=aliases;
					}else{
						subAlias=subs+"."+aliases;
					}
					//Add by Feiby Veronika
					//To avoid Duplicate alias in Criteria
					//CMIIW
					//System.out.println("Position(" + aliases + ") : "+criteria.toString().indexOf(aliases));
					aliasCheckExist = subAlias.concat(":").concat(aliases);
					//if(criteria.toString().indexOf(aliases)<0){
					//	criteria.createAlias(subAlias,aliases,CriteriaSpecification.LEFT_JOIN);
					//}
					if(criteria.toString().indexOf(aliasCheckExist)<0){
						criteria.createAlias(subAlias,aliases);
					}
					subs = aliases;
				}
				int j = eqColumns[i0].lastIndexOf(".");
				int k = eqColumns[i0].lastIndexOf(".",j-1);
//				if (eqColumns != null) {
//					for (int i0 = 0; i0 < eqColumns.length; i0++) {
						criteria.add(Property.forName(eqColumns[i0].substring(k+1)).ne(eqParams[i0]));
//					}
//				}
			}else{
				criteria.add(Property.forName(eqColumns[i0]).ne(eqParams[i0]));
			}
			
		}
		//System.out.println("SQL EQ:"+criteria);
	}
	public static void setNeqParam(String eqColumns, Object eqParams, Criteria criteria) {
		if(eqColumns==null||eqParams==null){
			return;
		}
		if(eqColumns.indexOf(".")>=0){
			StringTokenizer st = new StringTokenizer(eqColumns,".");
			int tokens = st.countTokens();
			String subAlias="";
			String subs="";
			for (int i = 0; i < tokens-1; i++) {
				String aliases = st.nextToken();
				if(i==0){
					subAlias=aliases;
				}else{
					subAlias=subs+"."+aliases;
				}
				criteria.createAlias(subAlias,aliases);
				subs = aliases;
			}
			int j = eqColumns.lastIndexOf(".");
			int k = eqColumns.lastIndexOf(".",j-1);
			criteria.add(Property.forName(eqColumns.substring(k+1)).ne(eqParams));
		}else{
			criteria.add(Property.forName(eqColumns).ne(eqParams));
		}
	}
	//[End]Add by aju on 20150819, for Not Equal :D
	public static void setBetweenParam(String[] btwnColumns, Object[] betweenParams1,  Object[] betweenParamsoffset, Criteria criteria) {
		if(btwnColumns==null){
			return;
		}
		for (int i0 = 0; i0 < btwnColumns.length; i0++) {
			if(btwnColumns[i0].indexOf(".")>=0){
				StringTokenizer st = new StringTokenizer(btwnColumns[i0],".");
				int tokens = st.countTokens();
				String subAlias="";
				String subs="";
				for (int i = 0; i < tokens-1; i++) {
					String aliases = st.nextToken();
					if(i==0){
						subAlias=aliases;
					}else{
						subAlias=subs+"."+aliases;
					}
					criteria.createAlias(subAlias,aliases);
					subs = aliases;
				}
				int j = btwnColumns[i0].lastIndexOf(".");
				int k = btwnColumns[i0].lastIndexOf(".",j-1);
//				if (eqColumns != null) {
//					for (int i0 = 0; i0 < eqColumns.length; i0++) {
						criteria.add(Property.forName(btwnColumns[i0].substring(k+1)).between(betweenParams1[i0],betweenParamsoffset[i0]));
//					}
//				}
			}else{
				criteria.add(Property.forName(btwnColumns[i0]).between(betweenParams1[i0],betweenParamsoffset[i0]));
			}
		}
	}
	public static void setBetweenParam(String btwnColumns, Object betweenParams1,  Object betweenParamsoffset, Criteria criteria) {
		if(btwnColumns==null){
			return;
		}
		if(btwnColumns.indexOf(".")>=0){
			StringTokenizer st = new StringTokenizer(btwnColumns,".");
			int tokens = st.countTokens();
			String subAlias="";
			String subs="";
			for (int i = 0; i < tokens-1; i++) {
				String aliases = st.nextToken();
				if(i==0){
					subAlias=aliases;
				}else{
					subAlias=subs+"."+aliases;
				}
				criteria.createAlias(subAlias,aliases);
				subs = aliases;
			}
			int j = btwnColumns.lastIndexOf(".");
			int k = btwnColumns.lastIndexOf(".",j-1);
			criteria.add(Property.forName(btwnColumns.substring(k+1)).between(betweenParams1,betweenParamsoffset));
		}else{
			criteria.add(Property.forName(btwnColumns).between(betweenParams1,betweenParamsoffset));
		}
	}

	public static int getTotal(Criteria criteria){
		criteria.setProjection(Projections.rowCount());
		//System.out.println("SQL = "+criteria);
		List list = criteria.list();
		if (list.size() > 0) {
			return ((Integer)list.get(0)).intValue();
		} else {
			return 0;
		}
	}
	
	public static void setOrderBy(boolean asc,String columnOrder[],Criteria criteria){
		/*if(asc){
			for (int i = 0; i < columnOrder.length; i++) {
				criteria.addOrder(Order.asc(columnOrder[i]));
			}
		}else{
			for (int i = 0; i < columnOrder.length; i++) {
				criteria.addOrder(Property.forName(columnOrder[i]).desc());
			}
		}
		*/
		//Edit 2015805 by Feiby Veronika, for sorting more than 1 column order (Array)
		if(columnOrder==null){
			return;
		}
		for (int i0 = 0; i0 < columnOrder.length; i0++) {
			if(columnOrder[i0].indexOf(".")>=0){
				StringTokenizer st = new StringTokenizer(columnOrder[i0],".");
				int tokens = st.countTokens();
				String subAlias="";
				String subs="";
				String checkAlias="";
				for (int i = 0; i < tokens-1; i++) {
					String aliases = st.nextToken();
					if(i==0){
						subAlias=aliases;
					}else{
						subAlias=subs+"."+aliases;
					}
					//Add by Feiby Veronika
					//To avoid Duplicate alias in Criteria
					//CMIIW
					//System.out.println("Position(" + aliases + ") : "+criteria.toString().indexOf(aliases));
					checkAlias = subAlias.concat(":".concat(aliases));
					//if(criteria.toString().indexOf(aliases)<0){
					//	criteria.createAlias(subAlias,aliases, CriteriaSpecification.LEFT_JOIN);
					//}
					if(criteria.toString().indexOf(checkAlias)<0){
						criteria.createAlias(subAlias,aliases, CriteriaSpecification.LEFT_JOIN);
					}
					
					subs = aliases;
				}
				int j = columnOrder[i0].lastIndexOf(".");
				int k = columnOrder[i0].lastIndexOf(".",j-1);
				if(asc)
					criteria.addOrder(Property.forName(columnOrder[i0].substring(k+1)).asc());
				else
					criteria.addOrder(Property.forName(columnOrder[i0].substring(k+1)).desc());
			}else{
				if(asc){
					criteria.addOrder(Order.asc(columnOrder[i0]));
				}else{
					criteria.addOrder(Property.forName(columnOrder[i0]).desc());
				}
			}
		}
		
	}
	public static void setOrderBy(boolean asc,String columnOrder,Criteria criteria){
	//  Default Order 
	//	if(asc){
	//		criteria.addOrder(Order.asc(columnOrder));
	//	}else{
	//		criteria.addOrder(Property.forName(columnOrder).desc());
	//	}
		
		//ADD 11032015 by FVO
		//To handle column order with foreign affairs
		//CMIIW :D
		if(columnOrder==null){
			return;
		}
		if(columnOrder.indexOf(".")>=0){
			StringTokenizer st = new StringTokenizer(columnOrder,".");
			int tokens = st.countTokens();
			String subAlias="";
			String subs="";
			String checkAlias="";
			for (int i = 0; i < tokens-1; i++) {
				String aliases = st.nextToken();
				if(i==0){
					subAlias=aliases;
				}else{
					subAlias=subs+"."+aliases;
				}
				//Add by Feiby Veronika
				//To avoid Duplicate alias in Criteria
				//CMIIW
				//System.out.println("Position(" + aliases + ") : "+criteria.toString().indexOf(aliases));
				checkAlias = subAlias.concat(":".concat(aliases));
				//if(criteria.toString().indexOf(aliases)<0){
				//	criteria.createAlias(subAlias,aliases, CriteriaSpecification.LEFT_JOIN);
				//}
				if(criteria.toString().indexOf(checkAlias)<0){
					criteria.createAlias(subAlias,aliases, CriteriaSpecification.LEFT_JOIN);
				}
				
				subs = aliases;
			}
			int j = columnOrder.lastIndexOf(".");
			int k = columnOrder.lastIndexOf(".",j-1);
			if(asc)
				criteria.addOrder(Property.forName(columnOrder.substring(k+1)).asc());
			else
				criteria.addOrder(Property.forName(columnOrder.substring(k+1)).desc());
		}else{
			if(asc){
				criteria.addOrder(Order.asc(columnOrder));
			}else{
				criteria.addOrder(Property.forName(columnOrder).desc());
			}
		}
		//System.out.println("SQL = "+criteria);
	}
	public static void setLimit(int index,int offset,Criteria criteria){
		if(index>=0){
			criteria.setFirstResult(index);
		}
		if(offset>=0){
			criteria.setMaxResults(offset);
		}
	}
	public static void setGreaterThan(Object val, String col, Criteria criteria){
		criteria.add(Property.forName(col).gt(val));
	}
	public static void setGreaterThanEqual(Object val, String col, Criteria criteria){
		criteria.add(Property.forName(col).ge(val));
	}
	public static void setLessThan(Object val, String col, Criteria criteria){
		criteria.add(Property.forName(col).lt(val));
	}
	public static void setLessThanEqual(Object val, String col, Criteria criteria){
		if(val==null || col==null){
			return;
		}
		if(col.indexOf(".")>=0){
			StringTokenizer st = new StringTokenizer(col,".");
			int tokens = st.countTokens();
			String subAlias="";
			String subs="";
			String checkAlias="";
			for (int i = 0; i < tokens-1; i++) {
				String aliases = st.nextToken();
				if(i==0){
					subAlias=aliases;
				}else{
					subAlias=subs+"."+aliases;
				}
				checkAlias = subAlias.concat(":".concat(aliases));
				if(criteria.toString().indexOf(checkAlias)<0){
					criteria.createAlias(subAlias,aliases, CriteriaSpecification.LEFT_JOIN);
				}
				
				subs = aliases;
			}
			int j = col.lastIndexOf(".");
			int k = col.lastIndexOf(".",j-1);
			criteria.add(Property.forName(col.substring(k+1)).le(val));
		}else{
			criteria.add(Property.forName(col).le(val));
		}
	}
	
	//Add 20150820 by FVO
	public static void setLessThanEqual(String[] col, Object[] val, Criteria criteria) {
		if(col==null||val==null){
			return;
		}
		for (int i0 = 0; i0 < col.length; i0++) {
			if(col[i0].indexOf(".")>=0){
				StringTokenizer st = new StringTokenizer(col[i0],".");
				int tokens = st.countTokens();
				String subAlias="";
				String subs="";
				String aliasCheckExist ="";
				for (int i = 0; i < tokens-1; i++) {
					String aliases = st.nextToken();
					if(i==0){
						subAlias=aliases;
					}else{
						subAlias=subs+"."+aliases;
					}
					aliasCheckExist = subAlias.concat(":").concat(aliases);
					if(criteria.toString().indexOf(aliasCheckExist)<0){
						criteria.createAlias(subAlias,aliases);
					}
					subs = aliases;
				}
				int j = col[i0].lastIndexOf(".");
				int k = col[i0].lastIndexOf(".",j-1);
						criteria.add(Property.forName(col[i0].substring(k+1)).le(val[i0]));
			}else{
				criteria.add(Property.forName(col[i0]).le(val[i0]));
			}
			
		}
		//System.out.println("SQL EQ:"+criteria);
	}
	//Add 20150821 by FVO
	public static void setGreaterThan(String[] col, Object[] val, Criteria criteria) {
		if(col==null||val==null){
			return;
		}
		for (int i0 = 0; i0 < col.length; i0++) {
			if(col[i0].indexOf(".")>=0){
				StringTokenizer st = new StringTokenizer(col[i0],".");
				int tokens = st.countTokens();
				String subAlias="";
				String subs="";
				String aliasCheckExist ="";
				for (int i = 0; i < tokens-1; i++) {
					String aliases = st.nextToken();
					if(i==0){
						subAlias=aliases;
					}else{
						subAlias=subs+"."+aliases;
					}
					aliasCheckExist = subAlias.concat(":").concat(aliases);
					if(criteria.toString().indexOf(aliasCheckExist)<0){
						criteria.createAlias(subAlias,aliases);
					}
					subs = aliases;
				}
				int j = col[i0].lastIndexOf(".");
				int k = col[i0].lastIndexOf(".",j-1);
						criteria.add(Property.forName(col[i0].substring(k+1)).gt(val[i0]));
			}else{
				criteria.add(Property.forName(col[i0]).gt(val[i0]));
			}
			
		}
		//System.out.println("SQL EQ:"+criteria);
	}
	//Add 20150821 by FVO, new criteria for field IS NOT NULL
	public static void setIsNotNull(String colName, Criteria criteria){
		criteria.add(Property.forName(colName).isNotNull());
	}

	public static void setDistinct(String col, Criteria criteria) {
		criteria.setProjection(Projections.distinct(Property.forName(col)));
	}

	protected void setDistinct(Class arg, Criteria criteria) {
		Session s = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String[] properties = s.getSessionFactory().getClassMetadata(arg).getPropertyNames();
		ProjectionList list = Projections.projectionList();
		for (int i = 0; i < properties.length; ++i)
		    list.add(Projections.property(properties[i]), properties[i]);
		criteria.setProjection(Projections.distinct(list)).
			setResultTransformer(new AliasToBeanResultTransformer(arg));
	}

	public static Collection createQuery(String sql, Session s){
		SQLQuery query = s.createSQLQuery(sql);
		query.addScalar("result",Hibernate.STRING);
		return query.list();
	}
	public Collection createQuery(String sql){
		Session s = getHibernateTemplate().getSessionFactory().getCurrentSession();
		SQLQuery query = s.createSQLQuery(sql);
		query.addScalar("result",Hibernate.STRING);
		return query.list();
	}
	public static SQLQuery getQuery(String sql, Session s){
		SQLQuery query = s.createSQLQuery(sql);
		query.addScalar("result",Hibernate.STRING);
		return query;
	}
	public SQLQuery getQuery(String sql){
		Session s = getHibernateTemplate().getSessionFactory().getCurrentSession();
		SQLQuery query = s.createSQLQuery(sql);
		query.addScalar("result",Hibernate.STRING);
		return query;
	}
	public SQLQuery getQueryLong(String sql){
		Session s = getHibernateTemplate().getSessionFactory().getCurrentSession();
		SQLQuery query = s.createSQLQuery(sql);
		query.addScalar("result",Hibernate.LONG);
		return query;
	}
	public static void setNotIn(String colName, DetachedCriteria dc, Criteria criteria){
		criteria.add(Property.forName(colName).notIn(dc));
	}
	public static void setIn(String colName, DetachedCriteria dc, Criteria criteria){
		criteria.add(Property.forName(colName).in(dc));
	}
	public static void setIn(String colName, Collection dc, Criteria criteria){
		criteria.add(Property.forName(colName).in(dc));
	}
	public static void setIn(String colName, Object[] dc, Criteria criteria){
		criteria.add(Property.forName(colName).in(dc));
		System.out.println("SQL IN:"+criteria);
	}
	
	public static void setIn(String[] inColumns, Object[] inParams, Criteria criteria) {
		if(inColumns==null||inParams==null){
			return;
		}
		for (int i0 = 0; i0 < inColumns.length; i0++) {
			if(inColumns[i0].indexOf(".")>=0){
				StringTokenizer st = new StringTokenizer(inColumns[i0],".");
				int tokens = st.countTokens();
				String subAlias="";
				String subs="";
				String aliasCheckExist ="";
				for (int i = 0; i < tokens-1; i++) {
					String aliases = st.nextToken();
					if(i==0){
						subAlias=aliases;
					}else{
						subAlias=subs+"."+aliases;
					}
					//Add by Feiby Veronika
					//To avoid Duplicate alias in Criteria
					//CMIIW
					//System.out.println("Position(" + aliases + ") : "+criteria.toString().indexOf(aliases));
					aliasCheckExist = subAlias.concat(":").concat(aliases);
					//if(criteria.toString().indexOf(aliases)<0){
					//	criteria.createAlias(subAlias,aliases,CriteriaSpecification.LEFT_JOIN);
					//}
					if(criteria.toString().indexOf(aliasCheckExist)<0){
						criteria.createAlias(subAlias,aliases);
					}
					subs = aliases;
				}
				int j = inColumns[i0].lastIndexOf(".");
				int k = inColumns[i0].lastIndexOf(".",j-1);
//				if (eqColumns != null) {
//					for (int i0 = 0; i0 < eqColumns.length; i0++) {
						criteria.add(Property.forName(inColumns[i0].substring(k+1)).in(inParams));
//					}
//				}
			}else{
				criteria.add(Property.forName(inColumns[i0]).in(inParams));
			}
			
		}
	//	System.out.println("SQL EQ:"+criteria);
	}
	
	public static void setNotIn(String colName, Collection dc, Criteria criteria){
		criteria.add(Restrictions.not( Property.forName(colName).in(dc)));
		
	}
	public static void setNotIn(String colName, Object[] dc, Criteria criteria){
		criteria.add(Restrictions.not( Property.forName(colName).in(dc)));
		System.out.println("SQL IN:"+criteria);
	}
	
	public Collection getNamedQuery(String name,String col,long l){
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query namedQuery = session.getNamedQuery(name);
		namedQuery.setLong(col,l);
		return namedQuery.list();
	}
	public Query getNamedQuery(String name){
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query namedQuery = session.getNamedQuery(name);
		return namedQuery;
	}
// detachedCriteria

	/*
	 * DetachedCriteria dc = DetachedCriteria.forClass(DomainHostMap.class);
    dc.setProjection(Property.forName("hostId"));
    dc.add(Property.forName("domain.domainId").eq(new Long(domainID)));
//    DaoSupportUtil.setEqParam("domain.domainId",new Long(domainID),dc);
	Criteria c2 = hostDao.getCriteria();
	DaoSupportUtil.setIn("hostId",dc,c2);
	List list2 = c2.list();
	Host[] res = new Host[list2.size()];
    list2.toArray(res);
	 * */
// class+ 

// class- 
}
