package com.java.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.java.dto.ProfessorDTO;
import com.java.model.Professor;
import com.java.services.ProfessorService;

@RestController
@RequestMapping(value="/professores")
public class ProfessorResource {
	
	@Autowired
	private ProfessorService prfessorService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Professor> find(@PathVariable Long id) {
		Professor obj = prfessorService.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ProfessorDTO>> findAll() {
		List<Professor> list = prfessorService.findAll();
		List<ProfessorDTO> listDto = list.stream().map(obj -> new ProfessorDTO(obj)).collect(Collectors.toList());  
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value="/professorPaginacao", method=RequestMethod.GET)
	public ResponseEntity<Page<ProfessorDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Professor> list = prfessorService.findPage(page, linesPerPage, orderBy, direction);
		Page<ProfessorDTO> listDto = list.map(obj -> new ProfessorDTO(obj));  
		return ResponseEntity.ok().body(listDto);
	}
	
	
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody ProfessorDTO objDto) {
        Professor obj = prfessorService.fromDTO(objDto);
		obj = prfessorService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody ProfessorDTO objDto, @PathVariable Long id) {
		Professor obj = prfessorService.fromDTO(objDto);
		obj.setId(id);
		obj = prfessorService.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		prfessorService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
