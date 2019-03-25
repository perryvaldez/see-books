package com.github.perryvaldez.seebooks.services;

import java.util.List;

import com.github.perryvaldez.seebooks.models.Role;
import com.github.perryvaldez.seebooks.models.User;

public interface UserService {
    public User getUserByEmail(String email);
    public List<Role> getUserRoles(User user);
}
