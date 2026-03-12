package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;



@Data
@Entity
@Table(name = "Contas")

public class Conta {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

     @Column (nullable = false)
        private String nome;

     @Column (nullable = false)
        private Double saldo;

        @JsonIgnore
            @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL, orphanRemoval = true)
            private List<Transacao> transacoes;

         



}



