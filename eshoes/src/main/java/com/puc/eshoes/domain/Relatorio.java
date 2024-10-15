package com.puc.eshoes.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Relatorio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String tipo;
    @Column
    private LocalDateTime data;
    @OneToMany
    private List<Pedido> pedidos;
    @OneToMany
    private List<Produto> produtos;
}
