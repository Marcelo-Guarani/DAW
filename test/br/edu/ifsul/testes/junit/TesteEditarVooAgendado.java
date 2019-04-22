/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes.junit;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.VooAgendado;
import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author marce
 */
public class TesteEditarVooAgendado {
    
    EntityManager em;

    public TesteEditarVooAgendado() {
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
            VooAgendado va = em.find(VooAgendado.class, 1);
            va.setTotalPassageiros(95);

            em.getTransaction().begin();
            em.merge(va);
            em.getTransaction().commit();
        } catch (Exception e) {
           e.printStackTrace();
           System.err.println("Erro: "+e);
        }
    }
}
