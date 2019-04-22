/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author marce
 */
@Entity
@Table (name = "aeroporto")
public class Aeroporto implements Serializable{
    @Id
    @SequenceGenerator(name = "seq_cidade", sequenceName = "seq_cidade_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_cidade", strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @NotBlank(message = "Campo nome é obrigatório!")
    @Length(max = 30, message = "O nome da cidade deve conter até {max} caracteres!")
    @Column(name = "nome", nullable = false, length = 30)
    private String nome;
    
    @Column(name = "operacaoNoturna")
    private Boolean operacaoNoturna;
    
    @ManyToOne
    @JoinColumn(name = "cidade", referencedColumnName = "id", nullable = false)
    @NotNull(message = "A cidade deve ser informada!")
    @ForeignKey(name = "fk_cidade")
    private Cidade cidade;
    
    @ManyToMany
    @JoinTable(name = "escalas",
            joinColumns
            = @JoinColumn(name = "aeroporto", referencedColumnName = "id", nullable = false),
            inverseJoinColumns
            = @JoinColumn(name = "voo", referencedColumnName = "id", nullable = false)
    )
    private Set<Voo> listaEscalas = new HashSet<>();
    
    public Aeroporto(){
        operacaoNoturna = false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getOperacaoNoturna() {
        return operacaoNoturna;
    }

    public void setOperacaoNoturna(Boolean operacaoNoturna) {
        this.operacaoNoturna = operacaoNoturna;
    }
    
     public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + Objects.hashCode(this.id);
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
        final Aeroporto other = (Aeroporto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    public void adicionarEscalasVoo(Voo v) {
        listaEscalas.add(v);
    }

    public void removerEscalasVoo(Voo v) {
        listaEscalas.remove(v);
    }
    
     @Override
    public String toString() {
        return nome;
    }
    
      
}
