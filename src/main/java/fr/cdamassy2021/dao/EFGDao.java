package fr.cdamassy2021.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.cdamassy2021.model.EFG;
import fr.cdamassy2021.model.GroupeEfg;
import fr.cdamassy2021.model.Personne;

/**
 * Groupe2 23/12/2021
 */
public class EfgDao implements DaoEFG {

	protected DaoFactory factory;

	public EfgDao(DaoFactory factory) {
		super();
		this.factory = factory;
	}

	@Override
	public EFG findById(long id) {

		String sql = "select * from efg where id_efg=?";

		EFG efg = null;
		ResultSet rs = null;
		try (Connection con = factory.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				efg = new EFG();
				efg.setIdEFG(rs.getInt("id_efg"));
				efg.setIntitule(rs.getString("intitule"));
				efg.setDateCreation(rs.getDate("date_creation"));
				efg.setIdCreateur(rs.getInt("id_createur"));
				efg.setIdCanal(rs.getInt("id_canal"));
				efg.setTailleMinGroupe(rs.getInt("taille_minimale_groupe"));
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return efg;
	}

	@Override
	public ArrayList<EFG> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(EFG t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean update(EFG t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(EFG t) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * get all groupes by idEfg.
	 */
	@Override
	public ArrayList<GroupeEfg> getAllGroupesByIdEfg(int idEfg) {

		String sql = "SELECT * FROM groupe_efg where id_efg=?";

		ArrayList<GroupeEfg> groupes = new ArrayList<>();

		try (Connection con = factory.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idEfg);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				GroupeEfg g = new GroupeEfg();
				g.setIdCreateur(rs.getInt("id_createur"));
				g.setIdEfg(rs.getInt("id_efg"));

				groupes.add(g);
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return groupes;
	}
}
