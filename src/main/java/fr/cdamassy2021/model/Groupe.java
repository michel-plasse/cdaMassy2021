
package fr.cdamassy2021.model;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author Florian
 */
public class Groupe {
    private List<Personne> membres;
    private int idCreateur;
    
    public Groupe() {
    }

    public Groupe(List<Personne> membres, int idCreateur) {
        this.membres = membres;
        this.idCreateur = idCreateur;
    }

    public List<Personne> getMembres() {
        return membres;
    }

    public void setMembres(List<Personne> membres) {
        this.membres = membres;
    }

    public int getIdCreateur() {
        return idCreateur;
    }

    public void setIdCreateur(int idCreateur) {
        this.idCreateur = idCreateur;
    }

    @Override
    public String toString() {
        return "Groupe crée par "+ idCreateur;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.membres);
        hash = 17 * hash + this.idCreateur;
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
        final Groupe other = (Groupe) obj;
        if (this.idCreateur != other.idCreateur) {
            return false;
        }
        for(int i = 0;i<membres.size();i++){
            if (!membres.get(i).equals(other.getMembres().get(i))){
                return false;
            }
        }
        return true;
    }
    
}
