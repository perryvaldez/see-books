package com.github.perryvaldez.seebooks.datalayer;

import java.util.List;

import com.github.perryvaldez.seebooks.models.types.KeyType;

public interface KeyUtilities {
    public KeyType generateKey();
    public KeyType makeKey(Object key);
    public List<KeyType> makeListOfKeys(List<?> keyList);
}
