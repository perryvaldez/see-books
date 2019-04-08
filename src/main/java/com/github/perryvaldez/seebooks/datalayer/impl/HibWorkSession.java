package com.github.perryvaldez.seebooks.datalayer.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.github.perryvaldez.seebooks.datalayer.WorkSession;
import com.github.perryvaldez.seebooks.exceptions.WorkSessionCommitException;

public class HibWorkSession implements WorkSession {
	private static final Logger LOGGER = LogManager.getLogger(HibWorkSession.class);
	
	private Session session;
	private Transaction transaction;
	private boolean closed = false;
	private boolean committed = false;
	
    public HibWorkSession(Session session, Transaction transaction) {
    	this.session = session;
    	this.transaction = transaction;
    }
	
	@Override
	public void commit() {
		if(this.committed) return;
		
        if(this.transaction != null) {
        	try {
        		this.transaction.commit();	
        		this.committed = true;
        	} catch(Exception ex) {
        		throw new WorkSessionCommitException("Unable to commit the transaction: ", ex);
        	}
            
        }
	}

	@Override
	public void rollback() {
		if(this.committed) return;
		
        if(this.transaction != null) {        	
        	try {
        		this.transaction.rollback();
        		this.committed = true;
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
		if(this.closed) return;
		
		if(!this.committed) {
			LOGGER.warn("Warning: Uncommitted transaction upon closing work session, transaction will be rolled back");
			this.rollback();
		}
		
		if(this.session != null) {
			try {
				this.session.close();
			} catch (Exception ex) {
				
			}            
		}
		
		this.closed = true;
	}

}
