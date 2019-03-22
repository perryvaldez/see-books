package com.github.perryvaldez.seebooks.models;

import com.github.perryvaldez.seebooks.models.types.KeyType;

public interface Role {
    public KeyType getId();
    public void setId(KeyType id);
    
    public String getName();
    public void setName(String name);
}
