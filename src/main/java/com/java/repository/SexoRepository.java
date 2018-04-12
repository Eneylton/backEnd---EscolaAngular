package com.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.model.Sexo;

@Repository
public interface SexoRepository extends JpaRepository<Sexo, Long> {

}