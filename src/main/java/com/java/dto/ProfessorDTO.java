package com.java.dto;

import java.io.Serializable;

import com.java.model.Professor;

public class ProfessorDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
    private String nome;
    private String cpf;

	public ProfessorDTO() {
		super();
	}

	public ProfessorDTO(Professor professor) {
		id   = professor.getId();
		nome = professor.getNome();
		cpf  = professor.getCpf();
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	
	
}
