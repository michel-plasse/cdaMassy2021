package fr.cdamassy2021.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.cdamassy2021.model.EFG;
import fr.cdamassy2021.model.GroupeEfg;
import fr.cdamassy2021.model.Personne;

public class EfgDao implements DaoEFG {
	
	protected DaoFactory factory;

	public EfgDao(DaoFactory factory) {
		super();
		this.factory = factory;
	}


	@Override
	public EFG findById(long id) {
		// TODO Auto-generated method stub
		return null;
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

	
	@Override
	public ArrayList<GroupeEfg> listerGroupesByIdEfg(int idEfg) {
		
		String sql = "SELECT * FROM groupe_efg where id_efg=?";

		ArrayList<GroupeEfg> groupes = new ArrayList<>();

		try (Connection con = factory.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, idEfg);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				GroupeEfg g=new GroupeEfg();
				g.setIdCreateur(rs.getInt("id_createur"));
				g.setIdEfg(rs.getInt("id_efg"));
				
				groupes.add(g);
			}
		} catch (SQLException e) {
			e.getMessage();
		}
		return groupes;
	}


	@Override
	public GroupeEfg creerGroupe(int IdCreateur) {
		
		return null;
	}
	
	

}
