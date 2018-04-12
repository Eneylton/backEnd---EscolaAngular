package com.java.dto;

import java.io.Serializable;

import com.java.model.Aluno;
import com.java.model.Professor;
import com.java.model.Sexo;

public class AlunoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String nome;

	private String endereco;

	private String email;

	private String telefone;

	private Sexo sexo;

	private Professor professor;

	public AlunoDTO() {
		super();
	}

	public AlunoDTO(Aluno obj) {
		id = obj.getId();
		nome = obj.getNome();
		endereco = obj.getEndereco();
		email = obj.getEmail();
		telefone = obj.getTelefone();
		sexo = obj.getSexo();
		professor = obj.getProfessor();
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

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

}
