package fr.cdamassy2021.dao;

import fr.cdamassy2021.model.EFG;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Florian
 */
public class EFGDaoTest {

    public EFGDaoTest() {
    }

    protected static final String TESTINSERT_EFG
            = "SELECT * FROM efg WHERE "
            + "id_createur = 1 AND "
            + "intitule = 'Enoncé' AND "
            + "id_canal = 1";

    /**
     * Réinitialisation de la base, pour maintenir la cohérence des tests malgré
     * les modifications
     *
     * @throws SQLException
     */
    @BeforeEach
    public void resetBDD() throws SQLException {
        Database.reset(LocalDateTime.now());
    }

    /**
     * Test de la méthode insert de EFGDAO, on insere un EFG dans la base et
     * execute un SELECT pour vérifier qu'on le retouve bien dans le tableau efg
     *
     * @throws SQLException
     */
    @Test
    public void testInsert() throws SQLException {
        EFGDao instance = new EFGDao();
        Connection connection = Database.getConnection();
        EFG exercice = new EFG(1, 1, 1, "Enoncé");
        boolean insertion = instance.insert(exercice);
        EFG comparant = new EFG();

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(TESTINSERT_EFG);
        if (result.next()) {
            comparant.setId(result.getInt("id_efg"));
            comparant.setIdCreateur(result.getInt("id_createur"));
            comparant.setIdCanal(result.getInt("id_canal"));
            comparant.setIntitule(result.getString("intitule"));
        }
        assertTrue(insertion);
        assertEquals(exercice, comparant);

    }

    /**
     * Test de la méthode findById On vérifie que l'EFG retourné est identique à
     * celui attendu
     *
     * @throws SQLException
     */
    @Test
    public void testFindByID() throws SQLException {
        EFGDao instance = new EFGDao();
        EFG result = instance.findById(2);
        EFG expected = new EFG(2, 1, 1, "TP cadrage");
        assertEquals(expected, result);
    }
    
    @Test
    public void testFindAllByCanal() throws SQLException {
       EFGDao instance = new EFGDao();
       ArrayList<EFG> efg = instance.findAllByCanal(2);
       int expected = 1;
       
       assertEquals(expected, efg.size());
    }
}
