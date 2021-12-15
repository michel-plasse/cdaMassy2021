/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.cdamassy2021.model;

/**
 *
 * @author thoma
 */
public class OptionnalResponse {
    
    public enum ReponseType {
        CHECK, YESNO, QCM, FREE;
    }

    public enum Correctness {
        TRUE,FALSE,UNDEFINED;
    }
    
    private Long id;
    private String value;
    private Correctness isCorrect;
    private Long autorID;
    
    
    
}
