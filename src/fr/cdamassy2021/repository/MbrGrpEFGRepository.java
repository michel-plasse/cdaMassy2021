package fr.cdamassy2021.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.cdamassy2021.entity.MbrGrpEFG;

@Repository
public interface MbrGrpEFGRepository extends CrudRepository<MbrGrpEFG, Long> {
	
/*	@Query(value= " SELECT m.idEfg, m.idCreateur, m.idPersonne,"
				+ " 		p.idPersonne, p.nom, p.prenom"
				+ "		FROM MbrGrpEFG m"
				+ "		JOIN fetch Personne p"
				+ "		ON m.idPersonne = p.idPersonne"
				+ "		where m.idEfg in :idEfg "
	)	

*/
	/**
	 * Basé sur le lien @ManyToOne de MbrGrpEFG vers Personne
	 * @return List<MbrGrpEFGDto>
	 */
	
	@Query(value = "SELECT NEW fr.cdamassy2021.dto.MbrGrpEFGDto(m.idPersonne, m.idCreateur, m.idEfg, m.personne.nom, m.personne.prenom )"
				+  " FROM MbrGrpEFG m"
				+  " WHERE m.idEfg in :idEfg" )
	List<Object> queryEfgById(@Param("idEfg") int idEfg);
	

}
