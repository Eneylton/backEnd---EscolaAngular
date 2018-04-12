package com.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.model.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

}