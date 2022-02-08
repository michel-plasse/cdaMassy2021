package fr.cdamassy2021.dao;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.Test;

import fr.cdamassy2021.model.Canal;
import fr.cdamassy2021.model.Personne;
import org.junit.jupiter.api.BeforeEach;

class CanalDaoTest {

    /**
     * teste les methode de notre classe CanalDao avant de les implementer dans
     * notre servlet
     */
    @Test
    public void TestgetAllByByIdPersonne() throws Exception {

        List<Canal> listeCanaux = CanalDao.getAllByIdPersonne(1);

        int expected = 2;
        assertEquals(expected, listeCanaux.size());
        for (Canal listeC : listeCanaux) {
            System.out.println(listeC.getIdCanal() + "- " + listeC.getNomCanal());
        }
    }

    @Test
    public void TestgetAllByIdPersonne() throws Exception {

        List<Canal> listeCanaux = CanalDao.getAllByIdPersonne(1);

        int expected = 2;
        assertEquals(expected, listeCanaux.size());
        for (Canal listeC : listeCanaux) {
            System.out.println(listeC.getIdCanal() + "- " + listeC.getNomCanal());
        }
    }

    @Test
    public void TestgetAllCanaux() throws Exception {
        //
        List<Canal> listeCanaux = CanalDao.getAllCanaux();

        int expected = 3;
        assertEquals(expected, listeCanaux.size());
        for (Canal listeC : listeCanaux) {
            System.out.println(listeC.getIdCanal() + "- " + listeC.getNomCanal());
        }
    }

    @Test
    public void TestSupprimerMembresDuCanal() throws Exception {
        //given
        int idPersonneTest = 3;
        int idCanalTest = 2;

        int expected = 3;
        CanalDao.SupprimerMembreDuCanal(idPersonneTest, idCanalTest);
        List<Personne> membresCanal = CanalDao.getMembresDuCanal(idCanalTest);

        assertEquals(expected, membresCanal.size());
        for (Personne mbreC : membresCanal) {
            System.out.println(mbreC.getId() + "- " + mbreC.getNom() + " " + mbreC.getPrenom());
        }
    }

    @Test
    public void TestAjouterMembresAuCanal() throws Exception {
        //given
        int idPersonneTest = 3;
        int idCanalTest = 3;

        int expected = 1;
        CanalDao.AjouterMembreAuCanal(idPersonneTest, idCanalTest);
        List<Personne> membresCanal = CanalDao.getMembresDuCanal(idCanalTest);

        assertEquals(expected, membresCanal.size());
        for (Personne mbreC : membresCanal) {
            System.out.println(mbreC.getId() + "- " + mbreC.getNom() + " " + mbreC.getPrenom());
        }
    }
}
