/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.cdamassy2021.dao;

import fr.cdamassy2021.model.EFG;
import fr.cdamassy2021.model.Groupe;
import fr.cdamassy2021.model.Personne;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author scdel
 */
public class EFGDao implements IDao<EFG> {

    protected static final String INSERT_EFG
            = "INSERT INTO `efg` (`intitule`, `id_createur`, `id_canal`) VALUES (?, ?, ?);";

    protected final static String SELECT_BY_ID
            = "SELECT * FROM efg WHERE id_efg = ?";

    protected final static String SELECT_GROUPES
            = "SELECT mefg.id_personne, personne.prenom, personne.nom,"
            + "personne.email, personne.tel, personne.pwd, mefg.id_createur "
            + "FROM personne INNER JOIN "
            + "membre_groupe_efg AS mefg ON mefg.id_personne = personne.id_personne "
            + "INNER JOIN groupe_efg AS gefg "
            + "ON mefg.id_createur = gefg.id_createur AND mefg.id_efg = gefg.id_efg "
            + "WHERE gefg.id_efg = ? ORDER BY mefg.id_createur;";

    @Override
    public boolean insert(EFG inserted) throws SQLException {
        Boolean result = false;
        Connection connection = Database.getConnection();
        PreparedStatement statement = connection.prepareStatement(INSERT_EFG,
                Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, inserted.getIntitule());
        statement.setInt(2, inserted.getIdCreateur());
        statement.setInt(3, inserted.getIdCanal());
        statement.execute();
        ResultSet res = statement.getGeneratedKeys();
        if (res.next()) {
            inserted.setId(res.getInt(1));
        }
        result = true;
        return result;
    }

    @Override
    public void delete(EFG deleted) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Retourne l'EFG de la BDD possédant l'id fournit en argument.
     * L'EFG retourné possède également en attribut la liste des groupes associé à 
     * l'efg de la BDD.
     * @param id
     * @return
     * @throws SQLException 
     */
    @Override
    public EFG findById(long id) throws SQLException {
        EFG result = new EFG();
        Connection connection = Database.getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
        statement.setLong(1, id);
        ResultSet setEFG = statement.executeQuery();
        while (setEFG.next()) {
            result.setId(setEFG.getInt("id_efg"));
            result.setIdCanal(setEFG.getInt("id_canal"));
            result.setIdCreateur(setEFG.getInt("id_createur"));
            result.setIntitule(setEFG.getString("intitule"));
        }
//A partir d'ici commence la gestion des groupes
        ArrayList<Groupe> resultGroupes = new ArrayList<>();
        statement = connection.prepareStatement(SELECT_GROUPES);
//SELECT GROUPES Retourne la liste des membres de chaque groupes de l'EFG avec leurs infos
        statement.setLong(1, id);
        ResultSet setGroupes = statement.executeQuery();
        int numgroupePrecedent = 0;
        Groupe groupeActuel = new Groupe();
            while(setGroupes.next()){
                int numgroupeActuel = setGroupes.getInt("id_createur");
//Si le groupe de ce membre est nouveau, on doit le créer, y mettre le membre
//et l'ajouter à la liste des groupes de l'exercice
                if(numgroupeActuel!=numgroupePrecedent){ 
                    numgroupePrecedent = numgroupeActuel;
                    groupeActuel.setIdCreateur(numgroupeActuel);
                    groupeActuel.setMembres(new ArrayList<Personne>());
                    groupeActuel.getMembres().add(new Personne(
                        setGroupes.getInt("id_personne"),
                        setGroupes.getString("prenom"),
                        setGroupes.getString("nom"),
                        setGroupes.getString("email"),
                        setGroupes.getString("tel"),
                        setGroupes.getString("pwd")));
                    resultGroupes.add(groupeActuel);
//Si on déjà crée le groupe de ce membre, il suffit d'y ajouter ce membre
                }else{
                    groupeActuel.getMembres().add(new Personne(
                        setGroupes.getInt("id_personne"),
                        setGroupes.getString("prenom"),
                        setGroupes.getString("nom"),
                        setGroupes.getString("email"),
                        setGroupes.getString("tel"),
                        setGroupes.getString("pwd")));
                }
            }
        result.setGroupes(resultGroupes);
        return result;
    }

    @Override
    public ArrayList<EFG> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<EFG> getAllPaging(int noPage, int nbElementsParPage) throws
            SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<EFG> findAllByCanal(int idCanal) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
