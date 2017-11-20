package com.flaviosantos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.flaviosantos.domain.Categoria;
import com.flaviosantos.domain.Produto;
import com.flaviosantos.repositories.CategoriaRepository;
import com.flaviosantos.repositories.ProdutoRepository;
import com.flaviosantos.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository repo;
	
	public Produto find(Integer id) {
		Produto obj = repo.findOne(id);
		
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado id: "+id+ ", tipo: "+Produto.class.getName());
		}
		return obj;
	}
	
	public Page<Produto> search(String nome,List <Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAll(ids);
		return repo.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
	}
}
