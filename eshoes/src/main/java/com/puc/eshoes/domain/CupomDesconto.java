package com.puc.eshoes.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CupomDesconto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String codigo;
    @Column
    private Double percentualDesconto;
    @Column
    private LocalDateTime dataValidade;
}
