package com.github.perryvaldez.seebooks.models;

import java.io.Serializable;

import com.github.perryvaldez.seebooks.models.types.KeyType;

public interface Role extends Serializable {
    public KeyType getId();
    public void setId(KeyType id);
    
    public String getName();
    public void setName(String name);
}
