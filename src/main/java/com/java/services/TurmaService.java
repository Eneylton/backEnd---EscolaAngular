package com.java.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.java.dto.TurmaDTO;
import com.java.model.Turma;
import com.java.repository.AlunoRepository;
import com.java.repository.TurmaRepository;
import com.java.util.exceptions.DataIntegrityException;
import com.java.util.exceptions.ObjectNotFoundException;

@Service
public class TurmaService {

	@Autowired
	private TurmaRepository repo;
	
	@Autowired
	private AlunoRepository repoAluno;

	public Turma find(Long id) {
		Turma obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Turma.class.getName());
		}
		return obj;
	}

	public Turma insert(Turma obj) {
		obj.setId(null);
		
		obj.setAluno(repoAluno.findOne(obj.getAluno().getId()));
		
		return repo.save(obj);
	}

	public Turma update(Turma obj) {
		Turma newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	

	public List<Turma> findAll() {
		return repo.findAll();
	}


	public Page<Turma> findPage(String nome,Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.buscar(nome,pageRequest);
	}

	public Turma fromDTO(TurmaDTO turmaDTO) {
		return new Turma(turmaDTO.getId(), turmaDTO.getDataFim(), 
				         turmaDTO.getDataInicio(),turmaDTO.getNome(),turmaDTO.getAluno());
	}

	private void updateData(Turma turmaNew, Turma turma) {
		turmaNew.setNome(turma.getNome());
		turmaNew.setDataInicio(turma.getDataInicio());
		turmaNew.setDataFim(turma.getDataFim());
		turmaNew.setAluno(turma.getAluno());
		
	}
	
	public void delete(Long id) {
		find(id);
		try {
			repo.delete(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma Turma que possui produtos");
		}
	}
	

}
