package com.katoyikane.modele.modules.etude;

import java.util.ArrayList;

/**
 * Created by christopher on 07/02/16.
 */
/*
Classe correspondant au traitement des données de l'étude de fonction
Troisième onglet
 */
public class MoEtudeFonction
{
    /**
     * Attributs de classe
     */
    private int intervalle[] = new int[2];                          //Stocke l'invervalle entré par l'utilisateur
    private String fonction = "" ;                                  //Stocke la fonction entrée par l'utilisateur si elle est validée
    private char inconnu ;                                          //Stocke la lettre de l'inconnu de la fonction
    private ArrayList<Integer> indices = new ArrayList<Integer>();  //Contient la liste d'indices entrée par l'utilisateur
    private ArrayList<Double> resultats = new ArrayList<Double>();  //Contient la liste des résultats selon les indices

    /**
     * Constructeur de la classe
     */
    public MoEtudeFonction()
    {

    }

    /**
     * Méthodes du modèle d'étude de fonction
     */
    public boolean verification(String fonction)
    {
        boolean isOk = false ;
        String inconnu ;

        //TODO finir la tâche de vérification

        return isOk ;
    }


}
