package com.sena.disocc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sena.disocc.modelo.TipoPeticion;

@Repository
public interface TipoPeticionRepository extends JpaRepository<TipoPeticion, Integer>{

}
