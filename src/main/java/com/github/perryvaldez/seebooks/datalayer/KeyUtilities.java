package com.github.perryvaldez.seebooks.datalayer;

import com.github.perryvaldez.seebooks.models.types.KeyType;

public interface KeyUtilities {
    public KeyType generateKey();
    public KeyType makeKey(Object key);
}
