package com.github.perryvaldez.seebooks.models;

import java.io.Serializable;

import com.github.perryvaldez.seebooks.models.types.KeyType;

public interface User extends Serializable {
  public KeyType getId();
  public void setId(KeyType id);
  
  public String getEmail();
  public void setEmail(String email);
  
  public String getPassword();
  public void setPassword(String password);
}
