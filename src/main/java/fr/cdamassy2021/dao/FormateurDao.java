package fr.cdamassy2021.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.cdamassy2021.model.Personne;
import fr.cdamassy2021.model.Questionnaire;

/**
 * @author Groupe2 23/12/2021
 */
public class FormateurDao extends PersonneDao implements DaoFormateur, Dao<Personne> {

	public FormateurDao(DaoFactory factory) {
		super(factory);
	}

	/**
	 * find personne By Id
	 * 
	 * @author Groupe2 23/12/2021
	 */
	@Override
	public Personne findById(long id) {

		return super.findById(id);
	}

	/**
	 * find All personnes
	 * 
	 * @author Groupe2 23/12/2021
	 */
	@Override
	public ArrayList<Personne> findAll() {
		String sql = "SELECT * FROM personne WHERE est_formateur=1 ";

		ArrayList<Personne> personnes = new ArrayList<>();

		try (Connection con = factory.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

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
	 * insère une personne et retourne son id dans la base de données.
	 * 
	 * @author Groupe2 23/12/2021
	 */
	@Override
	public int insert(Personne p) {
		String sql = "insert into personne (prenom, nom, email, tel, pwd, est_formateur) values(?,?,?,?,?,?)";
		try (Connection con = factory.getConnection();
				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, p.getPrenom());
			ps.setString(2, p.getNom());
			ps.setString(3, p.getEmail());
			ps.setString(4, p.getTel());
			ps.setString(5, p.getPwd());
			ps.setInt(6, 1);

			if (ps.executeUpdate() == 1) {
				ResultSet RSid = ps.getGeneratedKeys();
				RSid.next();
				return RSid.getInt(1);
			} else
				return 0;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;

		}
	}

	@Override
	public boolean update(Personne t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Personne t) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Get liste de questionnaires By IdFormateur
	 * 
	 * @author Groupe2 23/12/2021
	 */
	@Override
	public ArrayList<Questionnaire> getQuestionnairesByIdFormateur(int idFormateur) {

		String sql = "SELECT * FROM questionnaire WHERE id_createur=? ";

		ArrayList<Questionnaire> questionnaires = new ArrayList<>();

		try (Connection con = factory.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idFormateur);
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

}
