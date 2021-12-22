package fr.cdamassy2021.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.cdamassy2021.model.Personne;

public class PersonneDao implements DaoPersonne {
	
	protected DaoFactory factory;

	public PersonneDao(DaoFactory factory) {
		super();
		this.factory = factory;
	}


	@Override
	public Personne findById(long id) {
		String sql = "select * from personne where id_personne=?";

		Personne personne = null;
		ResultSet rs = null;
		try (Connection con = factory.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				personne  = new Personne ();
				personne.setId(rs.getInt("id_personne"));
				personne.setPrenom(rs.getString("prenom"));
				personne.setNom(rs.getString("nom"));
				personne.setEmail(rs.getString("email"));
				personne.setTel(rs.getString("tel"));
				personne.setPwd(rs.getString("pwd"));
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return personne;
	}

	@Override
	public ArrayList<Personne> findAll() {
		String sql = "select * from personne";

		ArrayList<Personne> personnes = new ArrayList<>();

		try (Connection con = factory.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

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

	@Override
	public int insert(Personne p) {
		String sql = "insert into personne (prenom, nom, email, tel, pwd) values(?,?,?,?,?)";
		try (Connection con = factory.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, p.getPrenom());
			ps.setString(2, p.getNom());
			ps.setString(3, p.getEmail());
			ps.setString(4, p.getTel());
			ps.setString(2, p.getPwd());
			
			if(ps.executeUpdate()==1) {
				ResultSet RSid = ps.getGeneratedKeys();
				RSid.next();
				System.out.println( RSid.getInt(1));
				return RSid.getInt(1);}
				else return 0;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;

		}
	}

	@Override
	public boolean update(Personne t) {
		return false;
	}

	@Override
	public boolean delete(Personne t) {
		return false;
	}


	public static List<Personne> getTousMembres(int i, int j) {
		// TODO Auto-generated method stub
		return null;
	}


	public static Personne getByLoginPassword(String login, String pwd) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
