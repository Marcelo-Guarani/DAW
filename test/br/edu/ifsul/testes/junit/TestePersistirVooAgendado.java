/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes.junit;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Aeroporto;
import br.edu.ifsul.modelo.Voo;
import br.edu.ifsul.modelo.VooAgendado;
import java.util.Calendar;
import java.util.Locale;
import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author marce
 */
public class TestePersistirVooAgendado {

    EntityManager em;

    public TestePersistirVooAgendado() {
    }

    @Before
    public void setUp() {
        em = EntityManagerUtil.getEntityManager();
    }

    @After
    public void tearDown() {
        em.close();
    }

    @Test
    public void teste() {
        try {
            VooAgendado va = new VooAgendado();
            va.setAeronave("Embraer A195");
            va.setData(Calendar.getInstance());
            va.setTotalPassageiros(90);
           
            
            Voo v = new Voo();
            v.setAtivo(Boolean.TRUE);
            v.setDescricao("Destino a Campinas");
            v.setPeriodicidade("diário");
            v.setTempoEstimado(1.15);
            
            
            v.adicionarVooAgendado(va);
            v.adicionarEscalasAeroporto(em.find(Aeroporto.class, 16));

            em.getTransaction().begin();
            em.persist(v);
            em.persist(va);
            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro: " + e);
        }
    }
}
