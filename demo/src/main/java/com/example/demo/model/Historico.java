package com.example.demo.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.Date;

@Entity
@Getter
@Setter
public class Historico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String descricao;

    @Temporal(TemporalType.DATE)
    private Date dataAlteracao;

    private String observacao;
    private String prioridade;
    private String status;

    @ManyToOne
    @JoinColumn(name = "fk_usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "fk_task_id")
    private Task task;
}
