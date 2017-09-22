package com.flaviosantos;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.flaviosantos.domain.Categoria;
import com.flaviosantos.repositories.CategoriaRepository;

@SpringBootApplication
public class WorkPds1Application implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepositorie;
	
	public static void main(String[] args) {
		SpringApplication.run(WorkPds1Application.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		
		Categoria cat1 = new Categoria(null, "informatica");
		Categoria cat2 = new Categoria(null, "escritorio");
		
		categoriaRepositorie.save(Arrays.asList(cat1,cat2));
		
	}
}
