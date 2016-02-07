package com.katoyikane.controleur.modules;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import com.katoyikane.modele.modules.MoCalculatriceSimple;

/**
 * Created by christopher on 07/02/16.
 */
/*
Controleur du premier onglet de l'application
 */
public class CoCalculatriceSimple
{
    /**
     * Attributs de classe
     */
    /*
    Liste des composants graphiques présent dans cet onglet
    En cas de modification, consulter : src/com.katoyikane/vue/modules/module1_simple.fxml
     */
    @FXML private Button bt_1 ;
    @FXML private Button bt_2 ;
    @FXML private Button bt_3 ;
    @FXML private Button bt_4 ;
    @FXML private Button bt_5 ;
    @FXML private Button bt_6 ;
    @FXML private Button bt_7 ;
    @FXML private Button bt_8 ;
    @FXML private Button bt_9 ;
    @FXML private Button bt_0 ;
    @FXML private Button bt_point ;
    @FXML private Button bt_plus ;
    @FXML private Button bt_moins ;
    @FXML private Button bt_multiplication ;
    @FXML private Button bt_division ;
    @FXML private Button bt_clear ;
    @FXML private Button bt_egal ;
    @FXML private Label affichage_resultat ;
    @FXML private Label affichage_calcul ;

    private String calcul = "";                                         //Chaine affichée dans le textArea correspondant au calcul
    private MoCalculatriceSimple modele = new MoCalculatriceSimple();   //Instance du modèle de calcul simple

    /* **********
    *************
    ********** */

    /**
     * Méthodes invoquée lors des clics
     */
    /*
    Méthode invoquée lors d'un clic sur un bouton de chiffre
    L'attribution des écouteur à un composant se fait dans : src/com.katoyikane/vue/modules/module1_simple.fxml
     */
    @FXML private void btChiffreOperateurClic(ActionEvent event)
    {
        //On ajoute à la chaine le chiffre ou l'opérateur entré
        calcul += this.getBoutonTexte((Button)event.getSource());
        affichage_calcul.setText(calcul);
    }

    //Méthodée invoquée lors d'un clic sur le bouton Egale
    @FXML private void btEgalClic(ActionEvent event)
    {
        //On ajoute à la chaine le signe égal et on passe à la ligne suivante
        calcul = calcul + getBoutonTexte((Button)event.getSource()) ;
        affichage_calcul.setText(calcul);
        //On remet calcul a zéro pour le calcul suivant
        calcul = "" ;
    }

    //Méthode invoquée lors d'un clic sur le bouton Clear
    @FXML private void btClearClic(ActionEvent event)
    {
        //Remise à zéro de calcul
        calcul = "";
        //On efface les zone d'affichage
        affichage_calcul.setText(calcul);
        affichage_resultat.setText(calcul);
    }


    /* **********
    *************
    ********** */

    /**
     * Méthodes propres au controleur
     */
    //Méthode retournant le texte du bouton passé en paramètre
    private String getBoutonTexte(Button bt)
    {
        return bt.getText();
    }
}
