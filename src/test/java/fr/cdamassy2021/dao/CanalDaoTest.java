package fr.cdamassy2021.dao;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.Test;

import fr.cdamassy2021.model.Canal;
import fr.cdamassy2021.model.Personne;

class CanalDaoTest {
/**
 * teste les methode de notre classe CanalDao avant de les implementer dans notre servlet
 */
   @Test
   public void TestgetAllByByIdPersonne() throws Exception {
       
       List<Canal> listeCanaux = CanalDao.getAllByIdPersonne(1);
       
       int expected = 2;
       assertEquals(expected, listeCanaux.size());
       for (Canal listeC : listeCanaux) {
           System.out.println(listeC.getIdCanal()+ "- "  + listeC.getNomCanal());
       }
   }
   @Test
    public void TestgetMembresDuCanal() throws Exception {
        //
       //given:
        int idCanalTest = 1;
        List<Personne> membresCanal = CanalDao.getMembresDuCanal(idCanalTest);
        
        int expected = 7;
        assertEquals(expected, membresCanal.size());
        for (Personne mbreC : membresCanal) {
            System.out.println(mbreC.getId()+ "- "  + mbreC.getNom() + " " + mbreC.getPrenom());
        }
    }
}
