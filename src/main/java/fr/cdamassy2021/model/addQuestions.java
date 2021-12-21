package fr.cdamassy2021.model;
import java.io.Serializable;

public class addQuestions implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private String libelleQuestion;
    private String libelleProposition;
    private String undefined;
    private String correct;
    private String incorrect;
    private String intituleQuestion;
    public String getlibelleQuestion() {
        return libelleQuestion;
    }
    public void setlibelleQuestion(String libelleQuestion) {
        this.libelleQuestion = libelleQuestion;
    }
    public String getlibelleProposition() {
        return libelleProposition;
    }
    public void setlibelleProposition(String libelleProposition) {
        this.libelleProposition = libelleProposition;
    }
    public String getundefined() {
        return undefined;
    }
    public void setundefined(String undefined) {
        this.undefined = undefined;
    }
    public String getcorrect() {
        return correct;
    }
    public void setcorrect(String correct) {
        this.correct = correct;
    }
    public String getincorrect() {
        return incorrect;
    }
    public void setincorrect(String incorrect) {
        this.incorrect = incorrect;
    }
    public String getintituleQuestion() {
        return intituleQuestion;
    }
    public void setintituleQuestion(String intituleQuestion) {
        this.intituleQuestion = intituleQuestion;
    }
}
