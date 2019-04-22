/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author marce
 */
@Entity
@Table(name = "voo_agendado")
public class VooAgendado implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_voo_agendado", sequenceName = "seq_voo_agendado_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_voo_agendado", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @NotBlank(message = "A aeronave deve ser informada!")
    @Column(name = "aeronave", nullable = false)
    private String aeronave;

    @NotNull(message = "O total de passageiros deve ser informado!")
    @Column(name = "totalPassageiros", nullable = false)
    private Integer totalPassageiros;

    @NotNull(message = "O data do voo deve ser informado!")
    @Column(name = "data", nullable = false)
    private Calendar data;

    @ManyToOne
    @JoinColumn(name = "voo", referencedColumnName = "id", nullable = false)
    @NotNull(message = "O voo deve ser informado!")
    @ForeignKey(name = "fk_voo")
    private Voo voo;

    @OneToMany(mappedBy = "vooAgendado", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Passagem> passagens = new ArrayList<>();

    public VooAgendado() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAeronave() {
        return aeronave;
    }

    public void setAeronave(String aeronave) {
        this.aeronave = aeronave;
    }

    public Integer getTotalPassageiros() {
        return totalPassageiros;
    }

    public void setTotalPassageiros(Integer totalPassageiros) {
        this.totalPassageiros = totalPassageiros;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public Voo getVoo() {
        return voo;
    }

    public void setVoo(Voo voo) {
        this.voo = voo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.id);
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
        final VooAgendado other = (VooAgendado) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public void adicionarPassagem(Passagem p) {
        passagens.add(p);
        p.setVooAgendado(this);
    }

    public void removerPassagem(Passagem p) {
        passagens.remove(p);
    }

    public void removerPassagem(int index) {
        passagens.remove(index);
    }
    
    @Override
    public String toString() {
        return aeronave +" - "+ totalPassageiros;
    }
    
}
