package com.github.perryvaldez.seebooks.models.types;

import java.io.Serializable;
import java.util.Objects;

public final class KeyValuePair<K, V> implements Serializable {
	private static final long serialVersionUID = 20190408L;
	
	private final K key;
	private final V value;
	
	public KeyValuePair(K key, V value) {
		this.key = key;
		this.value = value;
	}

	public K getKey() {
		return key;
	}

	public V getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "<" + this.key + ", " + this.value + ">";
	}
	
	@Override
	public boolean equals(Object obj) {
        if(obj instanceof KeyValuePair<?, ?>) {
        	try {
        		@SuppressWarnings("unchecked")
				KeyValuePair<K, V> kv = (KeyValuePair<K, V>) obj;	
        		
        		if(this.key.equals(kv.getKey()) && (this.value.equals(kv.getValue()))) {
        			return true;
        		}
        		
        		return false;
        		
        	} catch (ClassCastException ex) {
        		return false;
        	}       	        	
        }
        
        return false;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(this.key, this.value);
	}
}
