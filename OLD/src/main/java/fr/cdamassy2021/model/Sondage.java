/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.cdamassy2021.model;

import java.util.ArrayList;

/**
 * Un Sondage est une question posée sur un canal.
 * Il utilise une clase SondageResult pour enregistrer 
 * les resultats en groupant les reponses similaires.
 * 
 * @author thoma
 */
public class Sondage extends Question {

    private ArrayList<SondageResult> results = new ArrayList<>();

    public Sondage() {
        super();
    }

    public Sondage(int idQuestion, TypeQuestion typeDeLaQuestion, int canalId, int auteurId,
            String nomAuteur, String libelle) {
        super(idQuestion, typeDeLaQuestion, canalId, auteurId, nomAuteur, libelle);
    }

    /*
    * Groupement de reponses utilisateur par réponses
    */
    public class SondageResult {
        public SondageResult(){
            super();
        }
        public String NomsPersonnes;
        public int nbVotes;
        public String libelle;

        public String getNomsPersonnes() {
            return NomsPersonnes;
        }

        public int getNbVotes() {
            return nbVotes;
        }

        public String getLibelle() {
            return libelle;
        }
        
        
    };

    
    /*
    *  Initialise la liste des resultats du sondage en groupant les utilisateurs
    *  par réponses dans des elements SondagesResult.
    */
    public void setResults() {

        if (reponses.size() != 0) {
            SondageResult newSondageResult = new SondageResult();
            newSondageResult.NomsPersonnes = reponses.get(0).getNomPersonne();
            newSondageResult.libelle = reponses.get(0).getLibelle();
            newSondageResult.nbVotes = 1;
            results.add(newSondageResult);
            System.out.println("size:" + results.size());
            for (int i = 1; i < reponses.size(); i++) {
                boolean isMatch = false;
                for (int j = 0; j < results.size(); j++) {
                    SondageResult sr = results.get(j);
                    if (reponses.get(i).getLibelle().equals(results.get(j).libelle)) {
                        sr.NomsPersonnes += (", "+reponses.get(i).getNomPersonne());
                        sr.nbVotes++;
                        isMatch = true;
                    }
                }
                if (!isMatch) {
                    SondageResult newSondResult = new SondageResult();
                    newSondResult.NomsPersonnes = reponses.get(i).getNomPersonne();
                    newSondResult.libelle = reponses.get(i).getLibelle();
                    newSondResult.nbVotes = 1;
                    results.add(newSondResult);
                }
            }
        }
    }

    public ArrayList<SondageResult> getResults() {
        return results;
    }
}
