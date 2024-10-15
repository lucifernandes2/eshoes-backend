package com.puc.eshoes.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private LocalDateTime data;
    @Column
    private String status;
    @OneToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private Usuario idUsuario;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cupom_desconto", referencedColumnName = "id")
    private CupomDesconto cupomDesconto;
}
