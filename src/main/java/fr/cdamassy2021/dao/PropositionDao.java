package fr.cdamassy2021.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.cdamassy2021.model.Proposition;
import fr.cdamassy2021.model.Question;


public class PropositionDao implements Dao<Proposition>{
    private static final String INSERT = "INSERT INTO proposition (id_question,libelle) VALUES ( ?, ?);";
   // private static final String SELECTBYID = "SELECT * FROM question WHERE id_question=?";
   // private static final String TOUS_LES_MEMBRES = "SELECT * FROM question LIMIT ?, ?";
    public PropositionDao() {

    }

    @Override
    public boolean insert(Proposition inserted) throws SQLException {
        Boolean result = false;
        Connection connection = Database.getConnection();
        //compile la requete
        PreparedStatement stmt = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
        stmt.setInt(1, inserted.getIdQuestion());
        stmt.setString(2, inserted.getLibelle());
        //stmt.setInt(3, inserted.getIsCorrect().ordinal());
       
        stmt.execute();
        // Récupérer le id auto-incrémenté
        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            // Le id est dans la 1ere colonne trouvee
            inserted.setIdProposition(rs.getInt(1));
        }
        result = true;
        return result;
    }

	@Override
	public void delete(Proposition deleted) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Proposition findById(long id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Proposition> findAll() {
		// TODO Auto-generated method stub
		return null;
	}


}
