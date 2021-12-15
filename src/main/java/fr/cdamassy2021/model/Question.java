/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.cdamassy2021.model;

import java.util.ArrayList;

/**
 * Java Bean / POJO 'Question' 
 * Une question peut être posée sur un canal (sondage)
 * Ou et/ou appartenir à questionnaire.
 * 
 * @author thoma
 */
public class Question {

    private Long id;
    private Long canalId;
    private Long auteurId;
    private String statement;
    private OptionnalResponse.ReponseType type;
    private ArrayList<OptionnalResponse> optionnalResponse = null;

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
    public Question(Long id, Long canalId, Long auteurId, OptionnalResponse.ReponseType responseType,
            String statement, ArrayList<OptionnalResponse> optionnalResponses) {
        this.id = id;
        this.canalId = canalId;
        this.auteurId = auteurId;
        this.statement = statement;
        this.type = responseType;
        this.optionnalResponse = optionnalResponses;
    }
    
      /**
     * CTOR: (unidentified Bean)
     * This constructer should be used only when creating
     * a bean before storing it in the database gives it an id.
     * @param canalId
     * @param auteurId
     * @param ResponseType
     * @param statement
     * @param optionnalReponses
     */
    public Question(Long canalId, Long auteurId, OptionnalResponse.ReponseType responseType,
            String statement, ArrayList<OptionnalResponse> optionnalResponses) {
        this.canalId = canalId;
        this.auteurId = auteurId;
        this.statement = statement;
        this.type = responseType;
        this.optionnalResponse = optionnalResponses;
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

    public OptionnalResponse.ReponseType getType() {
        return type;
    }

    public void setType(OptionnalResponse.ReponseType type) {
        this.type = type;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String enonceQuestion) {
        this.statement = enonceQuestion;
    }

    public ArrayList<OptionnalResponse> getOptionReponsesIds() {
        return optionnalResponse;
    }

    public void setOptionReponsesIds(ArrayList<OptionnalResponse> reponsesIds) {
        this.optionnalResponse = reponsesIds;
    }

}
