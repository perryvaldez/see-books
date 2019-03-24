package com.github.perryvaldez.seebooks.models;

import java.io.Serializable;

public interface Role extends Serializable {
    public int getEnum();
    public void setEnum(int ennum);
    
    public String getName();
    public void setName(String name);
}
