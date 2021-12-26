package fr.cdamassy2021.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import fr.cdamassy2021.model.Canal;
import fr.cdamassy2021.model.Personne;

/**
 *
 * @author borel,beatrice,huawei
 */

public class CanalDao {

	protected DaoFactory factory;

	public CanalDao(DaoFactory factory) {
		super();
		this.factory = factory;
	}

	private static final String TOUS_LES_CANAUX_DU_MEMBRE = "SELECT nom, ca.id_canal\n" +
			"FROM membre_canal \n" +
			"            INNER JOIN canal ca\n" +
			"                    ON membre_canal.id_canal = ca.id_canal\n" +
			"WHERE id_personne=?\n" +
			"ORDER BY ca.id_canal";
	private static final String TOUS_LES_CANAUX = "SELECT * FROM canal ORDER BY id_canal";
	private static final String TOUS_LES_MEMBRE_DU_CANAL = ""
			+ "SELECT id_canal,p.id_personne,p.nom,p.prenom, ajoute_a \n"
			+ "FROM membre_canal mc\n"
			+ "		INNER JOIN personne p\n"
			+ "			ON mc.id_personne = p.id_personne\n"
			+ "WHERE id_canal=?";

	opt.innerText="Veuillez choisir une région";

	/**
	 * Groupe2
	 * 23/12/2021
	 * get All Questionnaires ByI dCanal
	 */
	@Override
	public ArrayList<Questionnaire> getAllQuestionnairesByIdCanal(int idCanal) {

		String sql = "SELECT * FROM questionnaire WHERE id_canal=? ";

		ArrayList<Questionnaire> questionnaires = new ArrayList<>();

		try (Connection con = factory.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idCanal);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Questionnaire q = new Questionnaire();
				q.setIdQuestionnaire(rs.getInt("id_questionnaire"));
				q.setIdCreateur(rs.getInt("id_createur"));
				q.setIdCanal(rs.getInt("id_canal"));
				q.setLibelle(rs.getString("libelle"));
				q.setDateAjout(rs.getDate("date_ajout"));
				questionnaires.add(q);

			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return questionnaires;
	}

	/**
	 * Groupe2
	 * 23/12/2021
	 * get All Efgs By IdCanal
	 */
	@Override
	public ArrayList<EFG> getAllEfgsByIdCanal(int idCanal) {

		String sql = "SELECT * FROM efg WHERE id_canal= ? ";

		ArrayList<EFG> efgs = new ArrayList<>();

		try (Connection con = factory.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idCanal);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				EFG efg = new EFG();
				efg.setIdEFG(rs.getInt("id_efg"));
				efg.setIdCreateur(rs.getInt("id_createur"));
				efg.setIdCanal(rs.getInt("id_canal"));
				efg.setIntitule(rs.getString("intitule"));
				efg.setDateCreation(rs.getDate("date_creation"));
				efgs.add(efg);

			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return efgs;
	}

	/**
	 * Groupe2
	 * 23/12/2021
	 * get All Sondages By IdCanal
	 */
	@Override
	public ArrayList<Sondage> getAllSondagesByIdCanal(int idCanal) {

		String sql = "SELECT * FROM question WHERE id_canal=? ";

		ArrayList<Sondage> sondages = new ArrayList<>();

		try (Connection con = factory.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idCanal);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Sondage s = new Sondage();
				s.setIdQuestion(rs.getInt("id_question"));
				s.setIdQuestionnaire(rs.getInt("id_questionnaire"));
				s.setIdCreateur(rs.getInt("id_createur"));
				s.setIdCanal(rs.getInt("id_canal"));
				s.setIdTypeQuestion(rs.getInt("id_type_question"));
				s.setLibelle(rs.getString("libelle"));
				sondages.add(s);
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return sondages;
	}

	/**
	 * Groupe2
	 * 23/12/2021
	 * get All Membres By IdCanal
	 */
	@Override
	public ArrayList<Personne> getAllMembresByIdCanal(int idCanal) {
		String sql = "SELECT * FROM personne WHERE id_personne IN (SELECT id_personne FROM membre_canal where id_canal=?)";

		ArrayList<Personne> personnes = new ArrayList<>();

		try (Connection con = factory.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idCanal);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Personne personne = new Personne();
				personne.setId(rs.getInt("id_personne"));
				personne.setPrenom(rs.getString("prenom"));
				personne.setNom(rs.getString("nom"));
				personne.setEmail(rs.getString("email"));
				personne.setTel(rs.getString("tel"));
				personne.setPwd(rs.getString("pwd"));
				personnes.add(personne);
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return personnes;
	}

	/**
	 * Canal d'id et de nom donnés sur lesquels sont enregistres le membre en
	 * session.
	 * Classe le resultat par id et renvoie null si pas trouvé
	 * 
	 * @param id_personne
	 * @return Une liste de Canal
	 * @throws SQLException
	 */
	public static List<Canal> getAllByIdPersonne(int id_personne) throws SQLException {

		List<Canal> canaux = new ArrayList<>();
		Connection connection = Database.getConnection();
		PreparedStatement stmt = connection.prepareStatement(TOUS_LES_CANAUX_DU_MEMBRE);
		stmt.setInt(1, id_personne);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			canaux.add(new Canal(
					rs.getInt("id_canal"),
					rs.getString("nom")));
		}
		return canaux;
	}

	public static List<Canal> getAllCanaux() throws SQLException {

		// Mettre en dur le résultat
		/*
		 * List<Canal>canaux = new ArrayList<Canal>();
		 * Canal c1 = new Canal();
		 * Canal c2 = new Canal();
		 * Canal c3 = new Canal();
		 * Canal c4 = new Canal();
		 * c1.setId_canal(1);
		 * c1.setNom("CDA");
		 * c2.setId_canal(2);
		 * c2.setNom("BTS");
		 * c3.setId_canal(3);
		 * c3.setNom("DUT");
		 * c4.setId_canal(4);
		 * c4.setNom("AIT");
		 * canaux.add(c1);
		 * canaux.add(c2);
		 * canaux.add(c3);
		 * canaux.add(c4);
		 * return canaux;
		 */

		List<Canal> canaux = new ArrayList<>();
		Connection connection = Database.getConnection();
		PreparedStatement stmt = connection.prepareStatement(TOUS_LES_CANAUX);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			canaux.add(new Canal(
					rs.getInt("id_canal"),
					rs.getString("nom")));
		}
		return canaux;
	}

	/**
	   * Liste des membres d'un canal donnés.
	   * renvoi l'ensemble des membres en fonction de l'idCanal et renvoie null si pas trouvé
	   * 
	   * @param idCanal
	   * @return  Une liste de Personne
	   * @throws SQLException
	   */
	public static List<Personne> getMembresDuCanal(int idCanal) throws SQLException {
		
		List<Personne> membresCanal = new ArrayList<Personne>();
	    Connection connection = Database.getConnection();
	    PreparedStatement stmt = connection.prepareStatement(TOUS_LES_MEMBRE_DU_CANAL);
	    stmt.setInt(1, idCanal);
	    ResultSet rs = stmt.executeQuery();
	    while (rs.next()) {
	    	membresCanal.add(new Personne(
	              rs.getInt("id_personne"),
	              rs.getString("nom"),
	              rs.getString("prenom")
	              ));
	    }
	    return membresCanal;
	}

}>>>>>>>main
