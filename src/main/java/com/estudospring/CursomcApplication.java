package com.estudospring;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.estudospring.model.Categoria;
import com.estudospring.model.Cidade;
import com.estudospring.model.Estado;
import com.estudospring.model.Produto;
import com.estudospring.repository.CategoriaRepository;
import com.estudospring.repository.CidadeRepository;
import com.estudospring.repository.EstadoRepository;
import com.estudospring.repository.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoria;
	
	@Autowired
	private ProdutoRepository produto;
	
	@Autowired
	private CidadeRepository cidade;
	
	@Autowired 
	private EstadoRepository estado;
	

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade city1 = new Cidade(null, "Belo Horizonte", est1);
		Cidade city2 = new Cidade(null, "São Paulo", est2);
		Cidade city3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(city1));
		est2.getCidades().addAll(Arrays.asList(city2, city3));
		
		categoria.saveAll(Arrays.asList(cat1, cat2));
		produto.saveAll(Arrays.asList(p1, p2, p3));
		estado.saveAll(Arrays.asList(est1, est2));
		cidade.saveAll(Arrays.asList(city1, city2, city3));
	}

}
