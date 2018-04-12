package com.java.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.java.dto.ProfessorDTO;
import com.java.model.Professor;
import com.java.repository.ProfessorRepository;
import com.java.util.exceptions.DataIntegrityException;
import com.java.util.exceptions.ObjectNotFoundException;

@Service
public class ProfessorService {

	@Autowired
	private ProfessorRepository professorRepository;

	public Professor find(Long id) {
		Professor obj = professorRepository.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Professor.class.getName());
		}
		return obj;
	}

	public Professor insert(Professor obj) {
		obj.setId(null);
		return professorRepository.save(obj);
	}

	public Professor update(Professor obj) {
		Professor newObj = find(obj.getId());
		updateData(newObj, obj);
		return professorRepository.save(newObj);
	}

	

	public List<Professor> findAll() {
		return professorRepository.findAll();
	}

	public Page<Professor> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return professorRepository.findAll(pageRequest);
	}

	public Professor fromDTO(ProfessorDTO professorDto) {
		return new Professor(professorDto.getId(), professorDto.getNome(),professorDto.getCpf());
	}

	private void updateData(Professor newProfessor, Professor professor) {
		newProfessor.setNome(professor.getNome());
		newProfessor.setCpf(professor.getCpf());
	}
	
	public void delete(Long id) {
		find(id);
		try {
			professorRepository.delete(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma Professor que possui produtos");
		}
	}
	

}
