package com.flaviosantos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.flaviosantos.domain.Categoria;
import com.flaviosantos.dto.CategoriaDTO;
import com.flaviosantos.repositories.CategoriaRepository;
import com.flaviosantos.services.exceptions.DataIntegrityException;
import com.flaviosantos.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) {
		Categoria obj = repo.findOne(id);
		
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado id: "+id+ ", tipo: "+Categoria.class.getName());
		}
		return obj;
	}
	
	public Categoria insert(Categoria obj){
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Categoria update(Categoria obj){
		Categoria newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id){
		find(id);
		try{
			repo.delete(id);			
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel Excluir uma Categoria que possui produtos");
		}
	}
	
	public List<Categoria> findAll(){
		return repo.findAll();
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Categoria fromDTO(CategoriaDTO objDto){
		return new Categoria(objDto.getId(),objDto.getNome());		
	}
	private void updateData(Categoria newObj, Categoria obj){
		newObj.setNome(obj.getNome());
	}
}
