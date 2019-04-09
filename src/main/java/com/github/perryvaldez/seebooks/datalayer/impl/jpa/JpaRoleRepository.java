package com.github.perryvaldez.seebooks.datalayer.impl.jpa;

import org.springframework.data.repository.CrudRepository;

import com.github.perryvaldez.seebooks.models.impl.hibernate.HibRole;

public interface JpaRoleRepository extends CrudRepository<HibRole, Long> {
    HibRole findById(long id);
}
