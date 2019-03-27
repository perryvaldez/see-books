package com.github.perryvaldez.seebooks.models;

import java.io.Serializable;

import com.github.perryvaldez.seebooks.models.types.KeyType;

public interface Privilege extends Serializable {
    public KeyType getId();
    public void setId(KeyType id);
    
    public Role getRole();
    public void setRole(Role role);
    
    public Realm getRealm();
    public void setRealm(Realm realm);
    
    public PermissionAction getAction();
    public void setAction(PermissionAction action);
    
    public PermissionObject getObject();
    public void setObject(PermissionObject object);
    
    public boolean isOwnedObjectOnly();
    public void setOwnedObjectOnly(boolean ownedObjectOnly);
    
    public String serialize();
}
