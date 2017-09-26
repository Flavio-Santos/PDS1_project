package com.flaviosantos;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.flaviosantos.domain.Categoria;
import com.flaviosantos.domain.Cidade;
import com.flaviosantos.domain.Estado;
import com.flaviosantos.domain.Produto;
import com.flaviosantos.repositories.CategoriaRepository;
import com.flaviosantos.repositories.CidadeRepository;
import com.flaviosantos.repositories.EstadoRepository;
import com.flaviosantos.repositories.ProdutoRepository;

@SpringBootApplication
public class WorkPds1Application implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepositorie;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(WorkPds1Application.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		
		Categoria cat1 = new Categoria(null, "informatica");
		Categoria cat2 = new Categoria(null, "escritorio");
		
		Produto p1 = new Produto(null, "computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		Estado est1 = new Estado(null,"Minas Gerais");
		Estado est2 = new Estado(null,"São Paulo");
		
		Cidade c1 = new Cidade(null,"Uberlandia",est1);
		Cidade c2 = new Cidade(null,"Sao Paulo",est2);
		Cidade c3 = new Cidade(null,"Campinas",est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		
		
		categoriaRepositorie.save(Arrays.asList(cat1,cat2));
		produtoRepository.save(Arrays.asList(p1,p2,p3));
		
		estadoRepository.save(Arrays.asList(est1,est2));
		cidadeRepository.save(Arrays.asList(c1,c2,c3));
		
		
	}
}
