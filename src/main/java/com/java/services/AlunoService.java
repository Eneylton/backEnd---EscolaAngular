package com.java.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.java.dto.AlunoDTO;
import com.java.model.Aluno;
import com.java.repository.AlunoRepository;
import com.java.util.exceptions.DataIntegrityException;
import com.java.util.exceptions.ObjectNotFoundException;

@Service
public class AlunoService {

	@Autowired
	private AlunoRepository repo;

	public Aluno find(Long id) {
		Aluno obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Aluno.class.getName());
		}
		return obj;
	}

	public Aluno insert(Aluno obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Aluno update(Aluno obj) {
		Aluno newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	

	public List<Aluno> findAll() {
		return repo.findAll();
	}

	/*public Page<Aluno> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}*/
	
	public Page<Aluno> findPage(String nome,Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.buscar(nome,pageRequest);
	}

	public Aluno fromDTO(AlunoDTO aluno) {
		return new Aluno(aluno.getId(), aluno.getNome(), aluno.getEndereco(), aluno.getEmail(),
				aluno.getTelefone(),aluno.getSexo(),aluno.getProfessor());
	}

	private void updateData(Aluno aluno, Aluno obj) {
		aluno.setNome(obj.getNome());
		aluno.setEndereco(obj.getEndereco());
		aluno.setEmail(obj.getEmail());
		aluno.setTelefone(obj.getTelefone());
		aluno.setSexo(obj.getSexo());
		aluno.setProfessor(obj.getProfessor());
		
	}
	
	public void delete(Long id) {
		find(id);
		try {
			repo.delete(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma Aluno que possui produtos");
		}
	}
	

}
