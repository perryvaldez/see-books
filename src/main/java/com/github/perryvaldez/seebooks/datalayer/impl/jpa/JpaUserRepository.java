package com.github.perryvaldez.seebooks.datalayer.impl.jpa;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.github.perryvaldez.seebooks.models.impl.hibernate.HibUser;

public interface JpaUserRepository extends CrudRepository<HibUser, Long> {
    HibUser findByEmail(String email);
    List<HibUser> findAll();
}
