package com.github.perryvaldez.seebooks.datalayer.impl;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.github.perryvaldez.seebooks.datalayer.KeyUtilities;
import com.github.perryvaldez.seebooks.models.types.KeyType;
import com.github.perryvaldez.seebooks.models.types.impl.NumericKeyType;

public class DbNumericKeyUtilities implements KeyUtilities {
    @PersistenceContext
	private EntityManager entityManager;

	public DbNumericKeyUtilities() {}
	
	@Override
	public KeyType generateKey() {	
        var query = this.entityManager.createNativeQuery("SELECT nextval('keygen') AS id");
        var result = query.getResultList();

        var numId = (BigInteger) result.get(0);
        KeyType id = new NumericKeyType(numId.longValue());
				
        return id;
	}

	@Override
	public KeyType makeKey(Object key) {
		NumericKeyType ret = null;
		if (!(key instanceof String) && !((key instanceof Long))) {
			throw new IllegalArgumentException("Invalid key: " + key);
		} else if ((key instanceof Long)) {
		    long numKey = (long) key;
			ret = new NumericKeyType(numKey);
			
		} else { // key instanceof String
			String strKey = (String) key;
		    long numKey = Long.parseLong(strKey, 10);
			ret = new NumericKeyType(numKey);
		}
		
		return ret;
	}

	@Override
	public List<KeyType> makeListOfKeys(List<?> keyList) {
		return keyList.stream().map(id -> this.makeKey(id)).collect(Collectors.toList()); 
	}
}
