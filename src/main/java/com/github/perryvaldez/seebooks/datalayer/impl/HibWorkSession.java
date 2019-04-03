package com.github.perryvaldez.seebooks.datalayer.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.github.perryvaldez.seebooks.datalayer.WorkSession;

public class HibWorkSession implements WorkSession {
	private static final Logger LOGGER = LogManager.getLogger(HibWorkSession.class);
	
	private Session session;
	private Transaction transaction;
	
    public HibWorkSession(Session session, Transaction transaction) {
    	this.session = session;
    	this.transaction = transaction;
    }
	
	@Override
	public void commit() {
        if(this.transaction != null) {
        	try {
        		this.transaction.commit();	
        	} catch(Exception ex) {
        		LOGGER.error("Unable to commit the transaction: ", ex);
        	}
            
        }
	}

	@Override
	public void rollback() {
        if(this.transaction != null) {        	
        	try {
        		this.transaction.rollback();	
        	} catch(Exception ex) {
        		LOGGER.error("Unable to roll back the transaction: ", ex);
        	}
        }
	}

	public Session getSession() {
		return session;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	@Override
	public void close() {
		if(this.session != null) {
			try {
				this.session.close();
			} catch (Exception ex) {
				
			}            
		}		
	}

}
