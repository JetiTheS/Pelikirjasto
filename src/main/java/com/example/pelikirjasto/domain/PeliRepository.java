package com.example.pelikirjasto.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PeliRepository extends CrudRepository<Peli, Long> {
    List<Peli> findByNimi(@Param("nimi") String nimi);
}
