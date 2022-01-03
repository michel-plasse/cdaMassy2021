/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.cdamassy2021.model;

import java.util.ArrayList;

/**
 * class 'Question'  <br>
 * Java Bean implementation  <br>
 * <br>
 * Une 'Question' peut être posée sur un canal (tel un sondage) <br>
 * et/ou appartenir à questionnaire. <br>
 * Lors de son edition par un 'Membre' et si besoin est (hors réponses libres)
 * <br>
 * il devra aussi définir des options de réponses ('Propositions') et les <br>
 * qualifier de 'correct'/'inccorect'/'non-renseigné' <br>
 * <br>
 *
 * @author thoma
 */
public class Question {

    public enum TypeQuestion {
        CHECK, OUINON, QCM, LIBRE/*, SMILEY, POURCENTAGE*/;
    }

    private int id;
    private int idCreateur;
    private String nomAuteur;
    private String libelle;
    private TypeQuestion type;
    private ArrayList<Proposition> propositions = new ArrayList<Proposition>();
    protected ArrayList<Reponse> reponses = new ArrayList<Reponse>();

    private boolean appartientQuestionaire;
    private int idQuestionnaire;

    private boolean estSondage;
    private int idCanal;

    /**
     * CTOR: Ce constructeur renvoi un bean Question initialisé avec la liste de
     * propositions de réponse renseignées par son auteur.
     *
     * @param id
     * @param canalId
     * @param auteurId
     * @param nomAuteur
     * @param typeDeQuestion
     * @param statement
     * @param propositions
     */
    public Question(int id, TypeQuestion typeDeQuestion, int canalId, int auteurId, String nomAuteur,
            String statement) {
        super();
        this.id = id;
        this.idCanal = canalId;
        this.idCreateur = auteurId;
        this.nomAuteur = nomAuteur;
        this.libelle = statement;
        this.type = typeDeQuestion;
    }

    /**
     * CTOR: (unidentified Bean) <br>
     * ! - This constructer should only be used before first insertion in <br>
     * the database (when no id has never been assigned to it).<br>
     * <br>
     *
     * @param canalId
     * @param auteurId
     * @param libelle
     * @param propositions
     */
    public Question(TypeQuestion typeDeLaQuestion, int canalId, int auteurId,
            String libelle) {
        super();
        this.idCanal = canalId;
        this.idCreateur = auteurId;
        this.nomAuteur = "undefined";
        this.libelle = libelle;
        this.type = typeDeLaQuestion;
    }

    /**
     * CTOR:(default)
     */
    public Question() {
        super();
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

    public String getNomAuteur() {
        return nomAuteur;
    }

    public void setNomAuteur(String nomAuteur) {
        this.nomAuteur = nomAuteur;
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

    public ArrayList<Proposition> getPropositions() {
        return propositions;
    }

    public void setPropositions(ArrayList<Proposition> propositionsIds) {
        this.propositions = propositionsIds;
    }

    public ArrayList<Reponse> getReponses() {
        return reponses;
    }

    public void setReponses(ArrayList<Reponse> reponses) {
        this.reponses = reponses;
    }

    public boolean isAppartientAQuestionaire() {
        return appartientQuestionaire;
    }

    public void setAppartientQuestionaire(boolean appartientQuestionaire) {
        this.appartientQuestionaire = appartientQuestionaire;
    }

    public int getIdQuestionaire() {
        return idQuestionnaire;
    }

    public void setIdQuestionaire(int questionaireId) {
        this.idQuestionnaire = questionaireId;
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
