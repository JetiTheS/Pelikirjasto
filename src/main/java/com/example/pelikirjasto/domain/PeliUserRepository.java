package com.example.pelikirjasto.domain;

import org.springframework.data.repository.CrudRepository;

public interface PeliUserRepository extends CrudRepository<PeliUser, Long> {
    PeliUser findByUsername(String username);
}
