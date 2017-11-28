package com.flaviosantos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.flaviosantos.domain.Cidade;
import com.flaviosantos.domain.Cliente;
import com.flaviosantos.domain.Endereco;
import com.flaviosantos.domain.enums.TipoCliente;
import com.flaviosantos.dto.ClienteDTO;
import com.flaviosantos.dto.ClienteNewDTO;
import com.flaviosantos.repositories.CidadeRepository;
import com.flaviosantos.repositories.ClienteRepository;
import com.flaviosantos.repositories.EnderecoRepository;
import com.flaviosantos.services.exceptions.DataIntegrityException;
import com.flaviosantos.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private BCryptPasswordEncoder pe;
	
	public Cliente find(Integer id) {
		Cliente obj = repo.findOne(id);
		
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado id: "+id+ ", tipo: "+Cliente.class.getName());
		}
		return obj;
	}
	
	public Cliente insert(Cliente obj){
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.save(obj.getEnderecos());
		return obj;
	}
	
	public Cliente update(Cliente obj){
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id){
		find(id);
		try{
			repo.delete(id);			
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel Excluir porque há pedidos relacionados");
		}
	}
	
	public List<Cliente> findAll(){
		return repo.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO objDto){
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null, null);		
	}
	
	public Cliente fromDTO(ClienteNewDTO objDto){
		 Cliente cli = new Cliente(null , objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipo()), pe.encode(objDto.getSenha()));
		 Cidade cid = cidadeRepository.findOne(objDto.getCidadeId());
		 Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid);
		 cli.getEnderecos().add(end);
		 cli.getTelefones().add(objDto.getTelefone1());
		 if(objDto.getTelefone2()!=null){
			 cli.getTelefones().add(objDto.getTelefone2());
		 }
		 if(objDto.getTelefone3()!=null){
			 cli.getTelefones().add(objDto.getTelefone3());
		 }
		 return cli;
	}
	
	private void updateData(Cliente newObj, Cliente obj){
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}
