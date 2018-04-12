package com.java.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.java.dto.SexoDTO;
import com.java.model.Sexo;
import com.java.repository.SexoRepository;
import com.java.util.exceptions.DataIntegrityException;
import com.java.util.exceptions.ObjectNotFoundException;

@Service
public class SexoService {

	@Autowired
	private SexoRepository repo;

	public Sexo find(Long id) {
		Sexo obj = repo.findOne(id);
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Sexo.class.getName());
		}
		return obj;
	}

	public Sexo insert(Sexo obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Sexo update(Sexo obj) {
		Sexo newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	

	public List<Sexo> findAll() {
		return repo.findAll();
	}

	public Page<Sexo> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Sexo fromDTO(SexoDTO objDto) {
		return new Sexo(objDto.getId(), objDto.getDescricao());
	}

	private void updateData(Sexo newObj, Sexo obj) {
		newObj.setDescricao(obj.getDescricao());
	}
	
	public void delete(Long id) {
		find(id);
		try {
			repo.delete(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma Sexo que possui produtos");
		}
	}
	

}
