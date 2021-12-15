/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.cdamassy2021.model;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Java Bean / POJO 'Question'  <br>
 *  <br>
 * Une 'Question' peut être posée sur un canal ( tel un sondage) <br>
 * et/ou appartenir à questionnaire. <br>
 * Lors de son edition par un 'Membre' et si besoin est (hors réponses libres) <br>
 * il devra aussi définir des options de réponses ('Propositions') et les <br>
 * qualifier de 'correct'/'inccorect'/'non-renseigné' <br>
 *  <br>
 * @author thoma
 */
public class Question {

    public enum TypeQuestion {
        CHECK, YESNO, QCM, FREE;
        
    }
    private Long id;
    private Long canalId;
    private Long auteurId;
    private String statement;
    private TypeQuestion type;
    private ArrayList<Proposition> propositions = null;

    /**
     * CTOR:
     * This provides an identified and fully constructed Question bean
     * @param id
     * @param canalId
     * @param auteurId
     * @param ResponseType
     * @param statement
     * @param optionnalReponses
     */
    public Question(Long id, Long canalId, Long auteurId, TypeQuestion typeDeQuestion,
            String statement, ArrayList<Proposition> propositions) {
        this.id = id;
        this.canalId = canalId;
        this.auteurId = auteurId;
        this.statement = statement;
        this.type = typeDeQuestion;
        this.propositions = propositions;
    }
    
      /**
     * CTOR: (unidentified Bean) <br>
     * ! - This constructer should only be used before first insertion in <br>
     * the database (in initial case no where id has never been assigned <br>
     * to it yet).<br>
     * <br>
     * @param canalId
     * @param auteurId
     * @param ResponseType
     * @param statement
     * @param optionnalReponses
     */
    public Question(Long canalId, Long auteurId, TypeQuestion typeDeLaQuestion,
            String statement, ArrayList<Proposition> propositions) {
        this.canalId = canalId;
        this.auteurId = auteurId;
        this.statement = statement;
        this.type = typeDeLaQuestion;
        this.propositions = propositions;
    }

    /**
     * CTOR:(default)
     */
    public Question() {
    }

    /*POJO*/
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCanalId() {
        return canalId;
    }

    public void setCanalId(Long canalId) {
        this.canalId = canalId;
    }

    public Long getAuteurId() {
        return auteurId;
    }

    public void setAuteurId(Long auteurId) {
        this.auteurId = auteurId;
    }

    public TypeQuestion getType() {
        return type;
    }

    public void setType(TypeQuestion type) {
        this.type = type;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String enonceQuestion) {
        this.statement = enonceQuestion;
    }

    public ArrayList<Proposition> getOptionReponsesIds() {
        return propositions;
    }

    public void setOptionReponsesIds(ArrayList<Proposition> propositionsIds) {
        this.propositions = propositionsIds;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        if (!Objects.equals(this.statement, other.statement)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.canalId, other.canalId)) {
            return false;
        }
        if (!Objects.equals(this.auteurId, other.auteurId)) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        return true;
    }
    
}
