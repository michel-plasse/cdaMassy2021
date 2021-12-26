package fr.cdamassy2021.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.cdamassy2021.model.GroupeEfg;
import fr.cdamassy2021.model.Personne;

/**
 * 
 * @author Groupe2 23/12/2021
 */
public class GroupeEfgDao implements DaoGroupeEfg {

	protected DaoFactory factory;

	/**
	 * @author Groupe2 23/12/2021
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
	 * @author Groupe2 23/12/2021
	 * 
	 *         Le int en retour est en fait un booleen 0 en cas d'echec et 1 en cas
	 *         de réussite. Insère un groupeEfg ainsi que son créateur dans la table
	 *         des membres du premier. utilise deux requetes en commit/rollback.
	 *         Cette méthode est utilisée lors de la création d'un groupeEfg par la
	 *         méthode creeGroupeEfg de la classe Groupe Efg.
	 */
	@Override
	public int insert(GroupeEfg g) {
		String sql = "insert into groupe_efg (id_createur, id_efg) values(?,?)";
		String sql1 = "insert into membre_groupe_efg (id_personne, id_createur, id_efg) values(?,?,?)";
		try (Connection con = factory.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				PreparedStatement ps1 = con.prepareStatement(sql1)) {
			try {
				con.setAutoCommit(false);
				ps.setInt(1, g.getIdCreateur());
				ps.setInt(2, g.getIdEfg());
				ps1.setInt(1, g.getIdCreateur());
				ps1.setInt(2, g.getIdCreateur());
				ps1.setInt(3, g.getIdEfg());
				int res = ps.executeUpdate() * ps1.executeUpdate();
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

	/**
	 * @author Groupe2 23/12/2021
	 * 
	 *         Trouve un groupe par son id et trouve la liste des membres de ce
	 *         groupe et la lui associe. Utilise getAllMembresByIdGroupe(long idEfg,
	 *         long idCreateur)
	 */
	@Override
	public GroupeEfg findById(long idEfg, long idCreateur) {

		String sql = "SELECT * FROM groupe_efg WHERE id_efg=? and id_createur=?";
		GroupeEfg g = null;
		ArrayList<Personne> membres = null;
		ResultSet rs = null;
		try (Connection con = factory.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(2, idCreateur);
			ps.setLong(1, idEfg);
			rs = ps.executeQuery();
			if (rs.next()) {
				g = new GroupeEfg();
				g.setIdCreateur(rs.getInt("id_createur"));
				g.setIdEfg(rs.getInt("id_efg"));
			}
			DaoGroupeEfg daoGroupeEfg = DaoFactory.getInstance().getGroupeEfgDao();
			membres = daoGroupeEfg.getAllMembresByIdGroupe(idEfg, idCreateur);
			if (g != null)
				g.setMembres(membres);
			return g;
		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
		}
		return g;
	}

	/**
	 * @author Groupe2 23/12/2021
	 * 
	 *         Trouve la liste des membres d'un groupe par l'id du dernier.
	 */
	@Override
	public ArrayList<Personne> getAllMembresByIdGroupe(long idEfg, long idCreateur) {

		String sql = "SELECT * FROM personne WHERE id_personne IN (SELECT id_personne FROM membre_groupe_efg WHERE id_efg=? and id_createur=?)";
		ArrayList<Personne> membres = null;
		ResultSet rs = null;
		try (Connection con = factory.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			membres = new ArrayList<Personne>();
			ps.setLong(2, idCreateur);
			ps.setLong(1, idEfg);
			rs = ps.executeQuery();
			while (rs.next()) {
				Personne personne = new Personne();
				personne.setId(rs.getInt("id_personne"));
				personne.setPrenom(rs.getString(2));
				personne.setNom(rs.getString("nom"));
				personne.setEmail(rs.getString("email"));
				personne.setTel(rs.getString("tel"));
				personne.setPwd(rs.getString("pwd"));
				membres.add(personne);
			}
			if (membres.size() > 0)
				return membres;
			else
				return null;

		} catch (SQLException e) {

			System.out.println(e.getMessage());
			return null;
		}

	}

	/**
	 * Groupe2 23/12/2021
	 * 
	 * @param idMembre
	 * @return le groupe dont est membre la personne dont le id est idMembre, s'il
	 *         existe. Va servir à interdir à une personne de créer un groupe si
	 *         elle est déjà membre d'un groupe.
	 */
	@Override
	public GroupeEfg findGroupeByIdEfgIdMembre(int idEfg, int idMembre) {
		String sql = "SELECT * FROM groupe_efg WHERE id_efg=? and id_personne IN (SELECT id_personne FROM membre_groupe_efg WHERE id_efg=?)";
		GroupeEfg g = null;
		ResultSet rs = null;
		try (Connection con = factory.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setLong(1, idEfg);
			ps.setLong(2, idMembre);
			rs = ps.executeQuery();
			if (rs.next()) {
				g = new GroupeEfg();
				g.setIdCreateur(rs.getInt("id_createur"));
				g.setIdEfg(rs.getInt("id_efg"));
			}
		} catch (SQLException e1) {
			System.out.println(e1.getMessage());
		}
		return g;
	}
}
