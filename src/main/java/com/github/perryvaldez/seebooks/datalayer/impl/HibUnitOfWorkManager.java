package com.github.perryvaldez.seebooks.datalayer.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.github.perryvaldez.seebooks.datalayer.UnitOfWorkManager;
import com.github.perryvaldez.seebooks.datalayer.WorkSession;

public class HibUnitOfWorkManager implements UnitOfWorkManager {
	private SessionFactory sessionFactory;
	
	public HibUnitOfWorkManager(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public WorkSession begin() {
        Session session = this.sessionFactory.openSession();    
        var txn = session.beginTransaction();
		return new HibWorkSession(session, txn);
	}

}
