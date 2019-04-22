/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes.junit;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Aeroporto;
import br.edu.ifsul.modelo.Cidade;
import br.edu.ifsul.modelo.Voo;
import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author marce
 */
public class TestePersistirAeroporto {

    EntityManager em;

    public TestePersistirAeroporto() {
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

            Aeroporto a = new Aeroporto();
            a.setCidade(em.find(Cidade.class, 4)); //Passo Fundo
            a.setNome("Aeroporto Lauro Kurtz");
            
            Voo v = new Voo();
            v.setDescricao("Voo com destino a Passo Fundo - RS"); 
            v.setPeriodicidade("2 vezes ao dia");
            v.setTempoEstimado(1.15);
            v.setAtivo(Boolean.TRUE);
            
            v.adicionarEscalasAeroporto(a);
            

            em.getTransaction().begin();
            em.persist(a);
            em.persist(v);
            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro: " + e);
        }
    }
}
