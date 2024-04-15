package com.example.demo.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String descricao;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAlteracao;

    private String observacao;

    private String prioridade;

    private String status;
    @Temporal(TemporalType.DATE)
    private Date dataPrevisao;

    @Temporal(TemporalType.DATE)
    private Date dataInicio;

    @Temporal(TemporalType.DATE)
    private Date dataTermino;
    @ManyToOne
    @JoinColumn(name = "fk_usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "fk_task_id")
    private Task task;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private List<Historico> historicos;


    public String getPrioridade() {
        // Retorne a prioridade da tarefa
        return this.prioridade;
    }

    public void setPrioridade(String prioridade) {
        // Defina a prioridade da tarefa
        this.prioridade = prioridade;
    }

    public String getStatus() {
        // Retorne o status da tarefa
        return this.status;
    }

    public void setStatus(String status) {
        // Defina o status da tarefa
        this.status = status;
    }
    public void setDataPrevisao(Date dataPrevisao) {
        if (this.dataPrevisao == null) {
            this.dataPrevisao = dataPrevisao;
        }
    }

    public void setDataInicio(Date dataInicio) {
        if (this.dataInicio == null) {
            this.dataInicio = dataInicio;
        }
    }

    public void setDataTermino(Date dataTermino) {
        if (this.dataTermino == null) {
            this.dataTermino = dataTermino;
        }
    }
}
