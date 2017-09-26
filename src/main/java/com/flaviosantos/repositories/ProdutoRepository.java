package com.flaviosantos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flaviosantos.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
	
}