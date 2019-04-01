package com.github.perryvaldez.seebooks.services;

import java.util.List;

import com.github.perryvaldez.seebooks.models.Privilege;
import com.github.perryvaldez.seebooks.models.Role;
import com.github.perryvaldez.seebooks.models.User;
import com.github.perryvaldez.seebooks.models.types.UserWithRoles;

public interface UserService {
    public User getUserByEmail(String email);
    public List<User> getUsers();
    public List<UserWithRoles> getUsersWithRoles();
    public List<Role> getUserRoles(User user);
    public List<Privilege> getUserPrivileges(User user);
}
