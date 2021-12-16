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
public interface IQuestion {

    public enum modeQuestion {
        SONDAGECANAL, QUESTIONNAIRE;
    }
    public enum TypeQuestion {
        CHECK, OUINON, QCM, LIBRE, SMILEY, POURCENTAGE;
    }

    /*POJO*/
    public Long getId();
    
    public void setId(Long id);
    
    public Long getCanalId();

    public void setCanalId(Long canalId);

    public Long getAuteurId();

    public void setAuteurId(Long auteurId);

    public TypeQuestion getType();

    public void setType(TypeQuestion type);

    public String getStatement();

    public void setStatement(String enonceQuestion);

    public ArrayList<Proposition> getOptionReponsesIds();

    public void setPropositions(ArrayList<Proposition> propositionsIds);
    
    public boolean getEstSondage();
   
    public boolean getappartientQuestionaire();
}
