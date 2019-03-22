package com.github.perryvaldez.seebooks.datalayer.impl;

import java.math.BigInteger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.github.perryvaldez.seebooks.datalayer.KeyGen;
import com.github.perryvaldez.seebooks.models.types.KeyType;
import com.github.perryvaldez.seebooks.models.types.impl.NumericKeyType;

public class DbNumericKeyGen implements KeyGen {
    @PersistenceContext
	private EntityManager entityManager;

	public DbNumericKeyGen() {}
	
	@Override
	public KeyType generateKey() {	
        var query = this.entityManager.createNativeQuery("SELECT nextval('keygen') AS id");
        var result = query.getResultList();

        var numId = (BigInteger) result.get(0);
        KeyType id = new NumericKeyType(numId.longValue());
				
        return id;
	}
}
