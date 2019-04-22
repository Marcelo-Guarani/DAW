/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes.junit;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Classe;
import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author marce
 */
public class TestePersistirClasse {

    EntityManager em;

    public TestePersistirClasse() {
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
            Classe executiva = new Classe();
            executiva.setNome("National Flight - Executive");
            executiva.setValor(1400.00);
            
            Classe economica = new Classe();
            economica.setNome("National Flight - Economic");
            economica.setValor(600.00);

            em.getTransaction().begin();
            em.persist(executiva);
            em.persist(economica);
            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro: " + e);
        }
    }
}
