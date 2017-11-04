package com.flaviosantos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flaviosantos.domain.Cliente;
import com.flaviosantos.repositories.ClienteRepository;
import com.flaviosantos.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	public Cliente buscar(Integer id) {
		Cliente obj = repo.findOne(id);
		
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado id: "+id+ ", tipo: "+Cliente.class.getName());
		}
		return obj;
	}
}
