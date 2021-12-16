/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.cdamassy2021.model;

/**
 * Java Bean / POJO 'Proposition' <br/>
 *  !TO DO: en faire une interface future +- proche<br/>
 *      --------<br/>
 * Def: <br/>
 *      Action de proposer, d'offrir, <br/>
 * de suggérer qqch. à qqn. ce qui est proposé.<br/>
 *      ---------<br/>
 * Application: proposition de réponse<br/>
 * 
 *      Lorsqu'une 'Question' est éditée par un 'Membre' <br/>
 * une ou plusieurs 'Proposition' sont aussi renseignées par ce dernier <br>
 * si nécessaire. Le membre doit aussi qualifier ou non cette proposition de 
 * correcte ou incorrecte. 
 * Des 'Participant' pourront répondre par la suite à une 'Question' en <br/>
 * choisissant parmis ces options de réponses.<br/>
 *      ---------<br/>
 * types de propositions possibles: <br/>
 * OUI/NON<br/>

 * @author thoma
 */
public class Proposition {
    
    public enum Correctness {
        CORRECT,INCORRECT,UNDEFINED;
    }
    
    private Long id;
    private String value;
    private Correctness isCorrect;
    private Long autorID;
    
    
    
}
