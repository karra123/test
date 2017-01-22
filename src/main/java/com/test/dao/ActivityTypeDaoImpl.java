package com.test.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.test.model.ActivityType;

@Component
@Repository("activityTypeDao")
public class ActivityTypeDaoImpl implements ActivityTypeDao {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
	    return this.sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
	    this.sessionFactory = sessionFactory;
	}
	    
	@Override
	@Transactional
	public void save(ActivityType a) {
		//Session session = this.sessionFactory.openSession();
		//Transaction tx = session.beginTransaction();
		//session.persist(a);
		//tx.commit();
		//session.close();
		this.sessionFactory.getCurrentSession().persist(a);
	}
		
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<ActivityType> list() {
		//Session session = this.sessionFactory.openSession();
		//List<ActivityType> aList = session.createQuery("from ActivityType").list();
		//session.close();
		//return aList;
		return this.sessionFactory.getCurrentSession().createQuery("from ActivityType").getResultList();
	}

}