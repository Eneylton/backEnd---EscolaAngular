package com.java.dto;

import java.io.Serializable;
import java.util.Date;

import com.java.model.Aluno;
import com.java.model.Turma;

public class TurmaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private Date dataInicio;

	private Date dataFim;

	private String nome;

	private Aluno aluno;

	public TurmaDTO() {
		super();
	}

	public TurmaDTO(Turma turma) {
		id = turma.getId();
		nome = turma.getNome();
		dataInicio = turma.getDataInicio();
		dataFim = turma.getDataFim();
		aluno = turma.getAluno();
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

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	
	
}
