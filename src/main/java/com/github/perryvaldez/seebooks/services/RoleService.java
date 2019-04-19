package com.github.perryvaldez.seebooks.services;

import java.util.List;

import com.github.perryvaldez.seebooks.models.Role;
import com.github.perryvaldez.seebooks.models.types.KeyType;

public interface RoleService {
    public List<Role> getRolesExceptIds(List<KeyType> idList);
}
