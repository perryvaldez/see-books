package com.github.perryvaldez.seebooks.models;

import com.github.perryvaldez.seebooks.models.types.KeyType;

public interface UserRole {
    public KeyType getUserId();
    public void setUserId(KeyType userId);
    
    public KeyType getRoleId();
    public void setRoleId(KeyType roleId);
}
