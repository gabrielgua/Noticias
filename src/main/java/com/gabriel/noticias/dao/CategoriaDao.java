package com.gabriel.noticias.dao;

import com.gabriel.noticias.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaDao extends JpaRepository<Categoria, Long> {

}
