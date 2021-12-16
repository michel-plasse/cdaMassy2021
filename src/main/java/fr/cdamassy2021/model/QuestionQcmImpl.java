/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.cdamassy2021.model;

import java.util.ArrayList;
import java.util.Objects;

/**
 * @author thoma
 */
public class QuestionQcmImpl implements IQuestion {

    private Long id;

    private Long auteurId;
    private String statement;
    private IQuestion.TypeQuestion type;
    private ArrayList<Proposition> propositions = null;

    private boolean appartientQuestionaire;
    private Long questionaireId;

    private boolean estSondage;
    private Long canalId;

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
    public QuestionQcmImpl(Long id, Long canalId, Long auteurId, IQuestion.TypeQuestion typeDeQuestion,
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
     *
     * @param canalId
     * @param auteurId
     * @param typeDeQuestion
     * @param statement
     * @param propositions
     */
    public QuestionQcmImpl(Long canalId, Long auteurId, IQuestion.TypeQuestion typeDeLaQuestion,
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
    public QuestionQcmImpl() {
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

    public IQuestion.TypeQuestion getType() {
        return type;
    }

    public void setType(IQuestion.TypeQuestion type) {
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

    public void setPropositions(ArrayList<Proposition> propositionsIds) {
        this.propositions = propositionsIds;
    }

    public boolean isAppartientQuestionaire() {
        return appartientQuestionaire;
    }

    public void setAppartientQuestionaire(boolean appartientQuestionaire) {
        this.appartientQuestionaire = appartientQuestionaire;
    }

    public Long getQuestionaireId() {
        return questionaireId;
    }

    public void setQuestionaireId(Long questionaireId) {
        this.questionaireId = questionaireId;
    }

    public boolean isEstSondage() {
        return estSondage;
    }

    public void setEstSondage(boolean estSondage) {
        this.estSondage = estSondage;
    }

    @Override
    public boolean getEstSondage() {
        return estSondage;
    }

    @Override
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
        final QuestionQcmImpl other = (QuestionQcmImpl) obj;
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
