package com.test.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.test.model.ActivityType;

public class ActivityTypeDaoImpl implements ActivityTypeDao {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
	    this.sessionFactory = sessionFactory;
	}
	    
	@Override
	public void save(ActivityType a) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(a);
		tx.commit();
		session.close();
	}
		
	@SuppressWarnings("unchecked")
	@Override
	public List<ActivityType> list() {
		Session session = this.sessionFactory.openSession();
		List<ActivityType> aList = session.createQuery("from ActivityType").list();
		session.close();
		return aList;
	}

}