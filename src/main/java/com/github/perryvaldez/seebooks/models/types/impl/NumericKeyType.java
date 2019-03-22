package com.github.perryvaldez.seebooks.models.types.impl;

import com.github.perryvaldez.seebooks.models.types.KeyType;

public final class NumericKeyType implements KeyType {
    final long value;
    
    public NumericKeyType(long value) {
    	this.value = value;
    }
    
    public long getValue() {
    	return this.value;
    }
    
    @Override
    public String toString() {
    	return "" + this.value;
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
}
