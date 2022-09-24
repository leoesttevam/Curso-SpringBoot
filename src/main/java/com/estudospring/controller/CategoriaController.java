package com.estudospring.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estudospring.model.Categoria;
import com.estudospring.service.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {
	
	@Autowired
	private CategoriaService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Categoria obj = service.buscar(id);
		
		return ResponseEntity.ok().body(obj);
	}
}
