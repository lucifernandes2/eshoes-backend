package com.puc.eshoes.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private Integer estoque;
    @OneToOne
    @JoinColumn(name = "id_pedido", referencedColumnName = "id")
    private Pedido idPedido;
    @OneToOne
    @JoinColumn(name = "id_produto", referencedColumnName = "id")
    private Produto idProduto;
}
