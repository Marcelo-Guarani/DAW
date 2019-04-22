/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes.junit;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Classe;
import br.edu.ifsul.modelo.Passagem;
import br.edu.ifsul.modelo.Pessoa;
import br.edu.ifsul.modelo.Voo;
import br.edu.ifsul.modelo.VooAgendado;
import java.util.Calendar;
import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author marce
 */
public class TestePersistirPassagem {

    EntityManager em;

    public TestePersistirPassagem() {
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
            Passagem p = new Passagem();
            p.setVooAgendado(em.find(VooAgendado.class, 1));
            p.setClasse(em.find(Classe.class, 2)); // National Flight - Executive
            p.setBagagem(2);
            p.setDataCompra(Calendar.getInstance());
            p.setPessoa(em.find(Pessoa.class, 1)); //Marcelo
            
            VooAgendado va = new VooAgendado();
            va.setAeronave("A720");
            va.setData(Calendar.getInstance());
            va.setTotalPassageiros(80);
            va.setVoo(em.find(Voo.class, 10));
            va.adicionarPassagem(p);

            em.getTransaction().begin();
            em.persist(va);
            em.persist(p);
            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro: " + e);
        }
    }
}
