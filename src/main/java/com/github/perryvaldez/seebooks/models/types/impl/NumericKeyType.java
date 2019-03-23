package com.github.perryvaldez.seebooks.models.types.impl;

import com.github.perryvaldez.seebooks.models.types.KeyType;

public final class NumericKeyType implements KeyType {
	private static final long serialVersionUID = 20190223L;
	
	final long value;
	
	public NumericKeyType() {
		this.value = 0;
	}
    
    public NumericKeyType(long value) {
    	this.value = value;
    }
    
    public long getValue() {
    	return this.value;
    }
    
    @Override
    public String toString() {
    	return this.serialize();
    }
    
    @Override
    public boolean equals(Object obj) {
    	NumericKeyType k = (NumericKeyType) obj;   	
    	return (this.value == k.getValue());
    }
    
    @Override
    public int hashCode() {
    	return Long.hashCode(this.value);
    }

	@Override
	public String serialize() {
		return "" + value;
	}
}
