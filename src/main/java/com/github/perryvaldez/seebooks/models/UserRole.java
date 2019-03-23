package com.github.perryvaldez.seebooks.models;

import java.io.Serializable;

import com.github.perryvaldez.seebooks.models.types.KeyType;

public interface UserRole extends Serializable {
    public KeyType getUserId();
    public void setUserId(KeyType userId);
    
    public KeyType getRoleId();
    public void setRoleId(KeyType roleId);
}
