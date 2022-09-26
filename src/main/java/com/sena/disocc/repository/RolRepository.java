package com.sena.disocc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sena.disocc.modelo.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer>{

}
