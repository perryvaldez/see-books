package com.github.perryvaldez.seebooks.models;

import java.io.Serializable;

import com.github.perryvaldez.seebooks.models.types.KeyType;

public interface Privilege extends Serializable {
    public KeyType getId();
    public void setId(KeyType id);
    
    public KeyType getRoleId();
    public void setRoleId(KeyType roleId);
    
    public KeyType getRealmId();
    public void setRealmId(KeyType realmId);
    
    public int getActionEnum();
    public void setActionEnum(int actionEnum);
    
    public KeyType getObjectId();
    public void setObjectId(KeyType objectId);
    
    public boolean isOwnedObjectOnly();
    public void setOwnedObjectOnly(boolean ownedObjectOnly);
}
