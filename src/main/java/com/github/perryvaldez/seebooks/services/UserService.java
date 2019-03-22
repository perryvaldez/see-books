package com.github.perryvaldez.seebooks.services;

import com.github.perryvaldez.seebooks.models.User;

public interface UserService {
    public User getUserByEmail(String email);
}
