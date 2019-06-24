/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes.junit;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Passagem;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author marce
 */
public class TesteListarPassagens {
    
    EntityManager em;
    
    public TesteListarPassagens() {
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
    public void teste(){
        List<Passagem> passagens = em.createQuery("from Passagem").getResultList();
        for(Passagem p: passagens){
            System.out.println(p.getPessoa());
            System.out.println(p.getVooAgendado().getData());
            System.out.println(p.getVooAgendado().getVoo());
        }
    }
}
