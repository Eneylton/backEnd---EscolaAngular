package com.java.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.java.model.Turma;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {
	
	@Transactional(readOnly=true)
	@Query("SELECT DISTINCT obj FROM Turma obj WHERE obj.nome LIKE %:nome% ")
	Page<Turma> buscar(@Param("nome") String nome, Pageable PageRequest);
}