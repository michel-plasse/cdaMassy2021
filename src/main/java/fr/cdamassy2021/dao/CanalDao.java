/**
 * 
 */
package fr.cdamassy2021.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.cdamassy2021.model.Canal;
import fr.cdamassy2021.model.EFG;
import fr.cdamassy2021.model.Personne;
import fr.cdamassy2021.model.Questionnaire;
import fr.cdamassy2021.model.Sondage;


public class CanalDao implements DaoCanal {
	
	protected DaoFactory factory;

	public CanalDao(DaoFactory factory) {
		super();
		this.factory = factory;
	}
	
	@Override
	public Canal findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Canal> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(Canal t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean update(Canal t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Canal t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Questionnaire> getAllQuestionnairesByIdCanal(int idCanal) {
		
		String sql = "SELECT * FROM questionnaire WHERE id_canal=? ";

		ArrayList<Questionnaire> questionnaires = new ArrayList<>();

		try (Connection con = factory.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idCanal);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Questionnaire q=new Questionnaire();
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

	@Override
	public ArrayList<EFG> getAllEfgsByIdCanal(int idCanal) {
		
		String sql = "SELECT * FROM efg WHERE id_canal=? ";

		ArrayList<EFG> efgs = new ArrayList<>();

		try (Connection con = factory.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idCanal);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				EFG e=new EFG();
				e.setIdEFG(rs.getInt("id_efg"));
				e.setIdCreateur(rs.getInt("id_createur"));
				e.setIdCanal(rs.getInt("id_canal"));
				e.setIntitule(rs.getString("libelle"));
				e.setDateCreation(rs.getDate("date_ajout"));
				efgs.add(e);
				
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return efgs;
	}


	@Override
	public ArrayList<Sondage> getAllSondagesByIdCanal(int idCanal) {
		
		String sql = "SELECT * FROM question WHERE id_canal=? ";

		ArrayList<Sondage> sondages = new ArrayList<>();

		try (Connection con = factory.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idCanal);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Sondage s=new Sondage();
				s.setIdQuestion(rs.getInt("id_question"));
				s.setIdQuestionnaire(rs.getInt("id_questionnaire"));
				s.setIdCreateur(rs.getInt("id_createur"));
				s.setIdCanal(rs.getInt("id_canal"));
				s.setIdTypeQuestion(rs.getInt("id_typeQuestion"));
				s.setLibelle(rs.getString("libelle"));
				sondages.add(s);
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return sondages;
	}

	@Override
	public ArrayList<Personne> getAllMembresByIdCanal(int idCanal) {
		String sql = "SELECT * FROM personne WHERE id_personne IN (SELECT id_personne FROM membre_canal where id_canal=?)";

		ArrayList<Personne> personnes = new ArrayList<>();

		try (Connection con = factory.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idCanal);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Personne personne=new Personne();
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

	

}
