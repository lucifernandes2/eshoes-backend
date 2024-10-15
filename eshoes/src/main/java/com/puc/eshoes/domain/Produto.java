package com.puc.eshoes.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String nome;
    @Column
    private String tamanho;
    @Column
    private Double preco;
    @Column
    private Integer estoque;
    @OneToOne
    @JoinColumn(name = "id_categoria_produto", referencedColumnName = "id")
    private CategoriaProduto categoriaProduto;

}
