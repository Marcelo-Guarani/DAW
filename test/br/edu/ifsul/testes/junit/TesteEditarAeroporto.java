/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes.junit;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Aeroporto;
import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author marce
 */
public class TesteEditarAeroporto {
    
    EntityManager em;

    public TesteEditarAeroporto() {
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
//        boolean erro = false;
        try {
            Aeroporto a = em.find(Aeroporto.class, 17);
            a.setNome("Lauro Kurtz");

            em.getTransaction().begin();
            em.merge(a);
            em.getTransaction().commit();
        } catch (Exception e) {
//           erro = true;
           e.printStackTrace();
           System.err.println("Erro: "+e);
        }
//        Assert.assertEquals(false, erro);
    }
    
}
