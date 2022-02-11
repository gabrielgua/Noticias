package com.gabriel.noticias.model;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode
public class Noticia {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String conteudo;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

}
