package com.estudospring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estudospring.model.Categoria;
import com.estudospring.model.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer>{
	
	
}
