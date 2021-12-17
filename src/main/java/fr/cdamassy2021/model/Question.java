/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.cdamassy2021.model;

import java.util.ArrayList;
import java.util.Objects;

/**
 * class 'IQuestion'  <br>
 * Java Bean implementation  <br>
 *  <br>
 Une 'IQuestion' peut être posée sur un canal (tel un sondage) <br>
 * et/ou appartenir à questionnaire. <br>
 * Lors de son edition par un 'Membre' et si besoin est (hors réponses libres) <br>
 * il devra aussi définir des options de réponses ('Propositions') et les <br>
 * qualifier de 'correct'/'inccorect'/'non-renseigné' <br>
 *  <br>
 * @author thoma
 */
public class Question {
    
    public enum modeQuestion {
        SONDAGECANAL, QUESTIONNAIRE;
    }
    public enum TypeQuestion {
        CHECK, OUINON, QCM, LIBRE, SMILEY, POURCENTAGE;
    }
    
    private int id;

    private int idCreateur;
    private String libelle;
    private TypeQuestion type;
    private ArrayList<Proposition> propositions = null;

    private boolean appartientQuestionaire;
    private int idQuestionnaire;

    private boolean estSondage;
    private int idCanal;

    /**
     * CTOR: This provides an identified and fully constructed Question bean
     *
     * @param id
     * @param canalId
     * @param auteurId
     * @param typeDeQuestion
     * @param statement
     * @param propositions
     */
    public Question(int id, TypeQuestion typeDeQuestion,int canalId, int auteurId,
            String statement, ArrayList<Proposition> propositions) {
        this.id = id;
        this.idCanal = canalId;
        this.idCreateur = auteurId;
        this.libelle = statement;
        this.type = typeDeQuestion;
        this.propositions = propositions;
    }

    /**
     * CTOR: (unidentified Bean) <br>
     * ! - This constructer should only be used before first insertion in <br>
     * the database (in initial case no where id has never been assigned <br>
     * to it yet).<br>
     * <br>
     *
     * @param canalId
     * @param auteurId
     * @param typeDeQuestion
     * @param statement
     * @param propositions
     */
    public Question(TypeQuestion typeDeLaQuestion,int canalId, int auteurId, 
            String statement, ArrayList<Proposition> propositions) {
        this.idCanal = canalId;
        this.idCreateur = auteurId;
        this.libelle = statement;
        this.type = typeDeLaQuestion;
        this.propositions = propositions;
    }

    /**
     * CTOR:(default)
     */
    public Question() {
    }

    /*POJO*/
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCanalId() {
        return idCanal;
    }

    public void setCanalId(int canalId) {
        this.idCanal = canalId;
    }

    public int getIdCreateur() {
        return idCreateur;
    }

    public void setIdCreateur(int auteurId) {
        this.idCreateur = auteurId;
    }

    public TypeQuestion getType() {
        return type;
    }

    public void setType(TypeQuestion type) {
        this.type = type;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String enonceQuestion) {
        this.libelle = enonceQuestion;
    }

    public ArrayList<Proposition> getOptionReponsesIds() {
        return propositions;
    }

    public void setPropositions(ArrayList<Proposition> propositionsIds) {
        this.propositions = propositionsIds;
    }

    public boolean isAppartientQuestionaire() {
        return appartientQuestionaire;
    }

    public void setAppartientQuestionaire(boolean appartientQuestionaire) {
        this.appartientQuestionaire = appartientQuestionaire;
    }

    public int getQuestionaireId() {
        return idQuestionnaire;
    }

    public void setQuestionaireId(int questionaireId) {
        this.idQuestionnaire = questionaireId;
    }

    public boolean isEstSondage() {
        return estSondage;
    }

    public void setEstSondage(boolean estSondage) {
        this.estSondage = estSondage;
    }

    public boolean getEstSondage() {
        return estSondage;
    }

    public boolean getappartientQuestionaire() {
        return appartientQuestionaire;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Question other = (Question) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.idCreateur != other.idCreateur) {
            return false;
        }
        if (this.idQuestionnaire != other.idQuestionnaire) {
            return false;
        }
        if (this.idCanal != other.idCanal) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Question{" + "id=" + id + ", auteurId=" + idCreateur + ", statement=" + libelle + ", type=" + type + ", propositions=" + propositions + ", appartientQuestionaire=" + appartientQuestionaire + ", questionaireId=" + idQuestionnaire + ", estSondage=" + estSondage + ", canalId=" + idCanal + '}';
    }

    
}

