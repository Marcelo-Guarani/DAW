/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author marce
 */
@Entity
@Table(name = "voo")
public class Voo implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_voo", sequenceName = "seq_voo_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_voo", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @NotBlank(message = "A descrição do voo é obrigatório por vias de segurança!")
    @Column(name = "descricao", nullable = false, columnDefinition = "TEXT")
    private String descricao;

    @NotNull(message = "O tempo estimado do voo deve ser informado!")
    @Column(name = "tempoEstimado", nullable = false, columnDefinition = "numeric(10,2)")
    private Double tempoEstimado;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo;

    @NotBlank(message = "A periodicidade do voo deve ser informada!")
    @Column(name = "periodicidade", nullable = false)
    private String periodicidade;

    @ManyToMany
    @JoinTable(name = "escalas",
            joinColumns
            = @JoinColumn(name = "voo", referencedColumnName = "id", nullable = false),
            inverseJoinColumns
            = @JoinColumn(name = "aeroporto", referencedColumnName = "id", nullable = false)
    )
    private Set<Aeroporto> listaEscalas = new HashSet<>();

    @OneToMany(mappedBy = "voo", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<VooAgendado> voosAgendados = new ArrayList<>();

    public Voo() {
        ativo = false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getTempoEstimado() {
        return tempoEstimado;
    }

    public void setTempoEstimado(Double tempoEstimado) {
        this.tempoEstimado = tempoEstimado;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public String getPeriodicidade() {
        return periodicidade;
    }

    public void setPeriodicidade(String periodicidade) {
        this.periodicidade = periodicidade;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Voo other = (Voo) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public void adicionarEscalasAeroporto(Aeroporto a) {
        listaEscalas.add(a);
    }

    public void removerEscalasAeroporto(Aeroporto a) {
        listaEscalas.remove(a);
    }

    public void adicionarVooAgendado(VooAgendado va) {
        voosAgendados.add(va);
        va.setVoo(this);
    }

    public void removerVooAgendado(VooAgendado va) {
        voosAgendados.remove(va);
    }

    public void removerVooAgendado(int index) {
        voosAgendados.remove(index);
    }

    @Override
    public String toString() {
        return descricao;
    }

    public Set<Aeroporto> getListaEscalas() {
        return listaEscalas;
    }

    public void setListaEscalas(Set<Aeroporto> listaEscalas) {
        this.listaEscalas = listaEscalas;
    }

    public List<VooAgendado> getVoosAgendados() {
        return voosAgendados;
    }

    public void setVoosAgendados(List<VooAgendado> voosAgendados) {
        this.voosAgendados = voosAgendados;
    }
    
    

}
