package com.gabriel.noticias.dao;

import com.gabriel.noticias.model.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NoticiaDao extends JpaRepository<Noticia, Long>{

    @Override
    @Query("SELECT n FROM Noticia n ORDER BY n.id DESC")
    List<Noticia> findAll();

    @Query("SELECT n FROM Noticia n WHERE CONCAT(lower(n.titulo) , ' ', lower(n.categoria.nome) , ' ', lower(n.conteudo) , ' ') " +
            "LIKE lower(concat('%', ?1,'%')) ORDER BY n.id DESC ")
    List<Noticia> buscar(String string);

    @Query("SELECT n FROM Noticia n WHERE n.categoria.id = ?1")
    List<Noticia> buscarPorCategoria(Long id);
}
