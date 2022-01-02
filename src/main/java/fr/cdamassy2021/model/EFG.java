package fr.cdamassy2021.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author scdel Exercices en groupe
 */
public class EFG {

    private int id, idCreateur, idCanal;
    private String intitule;
    private List<Groupe> groupes;

    public EFG() {
    }

    /**
     * Constructeur avec tous les paramètres, par exemple pourr récupérer depuis
     * la BDD
     *
     * @param id
     * @param idCreateur
     * @param idCanal
     * @param intitule
     * @param desgroupes
     */
    public EFG(int id, int idCreateur, int idCanal, String intitule, List<Groupe> desgroupes) {
        this.id = id;
        this.idCreateur = idCreateur;
        this.idCanal = idCanal;
        this.intitule = intitule;
        this.groupes = desgroupes;
    }

    /**
     * Constructeur sans groupes, pour l'initialisation
     *
     * @param id
     * @param idCreateur
     * @param idCanal
     * @param intitule
     */
    public EFG(int id, int idCreateur, int idCanal, String intitule) {
        this.id = id;
        this.idCreateur = idCreateur;
        this.idCanal = idCanal;
        this.intitule = intitule;
        this.groupes = new ArrayList<Groupe>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCreateur() {
        return idCreateur;
    }

    public void setIdCreateur(int idCreateur) {
        this.idCreateur = idCreateur;
    }

    public int getIdCanal() {
        return idCanal;
    }

    public void setIdCanal(int idCanal) {
        this.idCanal = idCanal;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public List<Groupe> getGroupes() {
        return groupes;
    }

    public void setGroupes(List<Groupe> groupes) {
        this.groupes = groupes;
    }

    @Override
    public String toString() {
        return "EFG n°" + id + ", crée par " + idCreateur + " dans " + idCanal + ". Intitule: " + intitule;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + this.id;
        hash = 89 * hash + this.idCreateur;
        hash = 89 * hash + this.idCanal;
        hash = 89 * hash + Objects.hashCode(this.intitule);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EFG other = (EFG) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.idCreateur != other.idCreateur) {
            return false;
        }
        if (this.idCanal != other.idCanal) {
            return false;
        }
        if (!Objects.equals(this.intitule, other.intitule)) {
            return false;
        }
        for (int i = 0; i > this.groupes.size(); i++) {
            if (!Objects.equals(this.groupes.get(i), other.getGroupes().get(i))) {
                return false;
            }
        }
        return true;
    }

}
