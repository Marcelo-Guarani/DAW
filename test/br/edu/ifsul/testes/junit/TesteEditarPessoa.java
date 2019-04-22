/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes.junit;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Pessoa;
import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author marce
 */
public class TesteEditarPessoa {
    
   EntityManager em;

    public TesteEditarPessoa() {
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
            Pessoa p = em.find(Pessoa.class, 1);
            p.setNome("Marcelo Fernandes");

            em.getTransaction().begin();
            em.merge(p);
            em.getTransaction().commit();
        } catch (Exception e) {
//           erro = true;
           e.printStackTrace();
           System.err.println("Erro: "+e);
        }
//        Assert.assertEquals(false, erro);
    }
    
}
