package fr.cdamassy2021.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.cdamassy2021.model.GroupeEfg;

public class GroupeEfgDao implements DaoGroupeEfg {
	
	protected DaoFactory factory;
	
	

	/**
	 * @param factory
	 */
	public GroupeEfgDao(DaoFactory factory) {
		super();
		this.factory = factory;
	}

	@Override
	public GroupeEfg findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<GroupeEfg> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	
	/**
	 * le int en retour est en fait un booleen 0 en cas d'echec et 1 en cas de réussite. 
	 */
	@Override
	public int insert(GroupeEfg g) {
		String sql = "insert into groupe_efg (id_createur, id_efg) values(?,?)";
		String sql1="insert into membre_groupe_efg (id_personne, id_createur, id_efg) values(?,?,?)";
		try(Connection con = factory.getConnection(); PreparedStatement ps = con.prepareStatement(sql); PreparedStatement ps1 = con.prepareStatement(sql1)){
		try  {
			con.setAutoCommit(false);
			ps.setInt(1, g.getIdCreateur());
			ps.setInt(2, g.getIdEfg());
			ps1.setInt(1, g.getIdCreateur());
			ps1.setInt(2, g.getIdCreateur());
			ps1.setInt(3, g.getIdEfg());
			int res = ps.executeUpdate()*ps1.executeUpdate(); 
			con.commit();
			return res;
		} catch (Exception e) {
			con.rollback();
			System.out.println(e.getMessage());
			return 0;

		}
	} catch (SQLException e1) {
		System.out.println(e1.getMessage());

	}
		return 0;
	}
	@Override
	public boolean update(GroupeEfg t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(GroupeEfg t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public GroupeEfg findById(long idEfg, long idCreateur) {
		// TODO Auto-generated method stub
		return null;
	}	      

}
