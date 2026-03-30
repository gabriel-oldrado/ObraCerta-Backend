package com.obracerta.crud_usuario.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tarefas")
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "projeto_id", nullable = false)
    @JsonIgnore
    private Projeto projeto;

    private String nome;

    @Column
    private int itensAFazer;

    private int quantidadeFeita = 0;

    @Enumerated(EnumType.STRING)
    private Prioridade prioridade;

    private Boolean concluida = false;

    public Tarefa() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Projeto getProjeto() { return projeto; }
    public void setProjeto(Projeto projeto) { this.projeto = projeto; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getItensAFazer() { return itensAFazer; }
    public void setItensAFazer(int itensAFazer) { this.itensAFazer = itensAFazer; }

    public int getQuantidadeFeita() { return quantidadeFeita; }
    public void setQuantidadeFeita(int quantidadeFeita) { this.quantidadeFeita = quantidadeFeita; }

    public Prioridade getPrioridade() { return prioridade; }
    public void setPrioridade(Prioridade prioridade) { this.prioridade = prioridade; }

    public Boolean getConcluida() { return concluida; }
    public void setConcluida(Boolean concluida) { this.concluida = concluida; }
}