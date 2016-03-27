package com.katoyikane.modele.modules.calculatrice;

import java.text.DecimalFormat;


/**
 * Created by christopher on 07/02/16.
 */
/*
Classe correspondant au traitement des données de la calculatrice simple
Premier onglet
 */
public class MoCalculatriceSimple
{
    /**
     * Attributs de classe
     */
    private String operateur = "" ;                                 //Stocke l'opérateur sélectionné par l'utilisateur
    private boolean operateurIsSelected = false ;                   //Indique si l'utilisateur a sélectionné un opérateur
    private double resultat = 0.0 ;                                 //Résultat incrémenté au fur et a mesure du calcul
    private String resultatAffichage = "";                          //Résultat final affiché et formaté avec 5 décimales max
    private DecimalFormat d = new DecimalFormat() ;                 //Objet restriction des décimales

    /**
     * Constructeur de la classe
     */
    public MoCalculatriceSimple()
    {
        this.operateurIsSelected = false ;
        d.setMaximumFractionDigits(5);
    }

    /**
     * Méthodes du modèle de calcul
     */

    //Méthode invoquée à la sélection d'un opérateur
    public void selectionOperation()
    {
        setOperateurIsSelected(true);
    }

    //Méthode invoquée à chaque tentative d'ajout d'un nombre par le controleur
    public void ajouterNombre(double nombre)
    {
        //Si un opérateur n'a jamais été sélectionné on stock le nombre arrivante dans la variable de résultat
        if (this.operateurIsSelected == false)
        {
            this.setResultat(nombre);
        }
        //Sinon, on demande le calcul du résultat
        else if (this.operateurIsSelected == true)
        {
            this.calcul(nombre);
        }
    }

    //Méthode invoquée à chaque tentative de calcul par le modèle
    public void calcul(double nombre)
    {
        //Calcul de la variable résultat et du nombre envoyé par la méthode ajouterNombre en fonction de l'opérateur
        switch(operateur) {
            case "+":
                this.setResultat(resultat + nombre);
                break;
            case "-":
                this.setResultat(resultat - nombre);
                break;
            case "*":
                this.setResultat(resultat * nombre);
                break;
            case "/":
                this.setResultat(resultat / nombre);
                break;
        }
        this.setOperateurIsSelected(false);
    }

    //Méthode invoquée pour l'affichage du résultat final
    public String returnResultatFinal()
    {
        return d.format(resultat);
    }

    //Méthode invoquée pour l'affichage du résultat intermédiaire
    public String returnResultat()
    {
        return Double.toString(resultat) ;
    }

    /**
     * Getters et setters
     */
    public String getOperateurSelection() {
        return operateur;
    }

    public boolean OperateurIsSelected() {
        return operateurIsSelected;
    }

    public double getResultat() {
        return resultat;
    }

    public void setOperateur(String operateur) {
        this.operateur = operateur;
    }

    public void setOperateurIsSelected(boolean operateurIsSelected) {
        this.operateurIsSelected = operateurIsSelected;
    }

    public void setResultat(double resultat) {
        this.resultat = resultat;
    }

}
