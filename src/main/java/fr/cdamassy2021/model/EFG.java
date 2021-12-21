package fr.cdamassy2021.model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author scdel
 */
public class EFG {

    private int id, idCreateur, idCanal;
    private String intitule;

    // private List<Groupe> groupes avec dans Groupe 
    //  private List<Personne> membres ??
    public EFG() {
    }

    public EFG(int id, int idCreateur, int idCanal, String intitule) {
        this.id = id;
        this.idCreateur = idCreateur;
        this.idCanal = idCanal;
        this.intitule = intitule;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCreateur() {
        return idCreateur;
    }

    public void setIdCreateur(int idCreateur) {
        this.idCreateur = idCreateur;
    }

    public int getIdCanal() {
        return idCanal;
    }

    public void setIdCanal(int idCanal) {
        this.idCanal = idCanal;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    @Override
    public String toString() {
        return "EFG n°" + id + ", crée par " + idCreateur + " dans " + idCanal + ". Intitule: " + intitule;
    }

}
