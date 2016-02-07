package com.katoyikane.controleur.modules.calculatrice;

import com.katoyikane.modele.modules.calculatrice.MoCalculatriceScientifique;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Created by christopher on 07/02/16.
 */
/*
Controleur du second onglet de l'application : Calculatrice scientifique
 */
public class CoCalculatriceScientifique
{
    /**
     * Attributs de classe
     */
    /*
    Liste des composants graphiques présent dans cet onglet
    En cas de modification, consulter : src/com.katoyikane/vue/modules/module2_scientifique.fxml
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
    @FXML private Button bt_pgauche ;
    @FXML private Button bt_pdroite ;
    @FXML private Button bt_cos ;
    @FXML private Button bt_arccos ;
    @FXML private Button bt_sin ;
    @FXML private Button bt_arcsin ;
    @FXML private Button bt_tan ;
    @FXML private Button bt_arctan ;
    @FXML private Button bt_racinecarree ;
    @FXML private Button bt_puissance ;
    @FXML private Button bt_log10 ;
    @FXML private Button bt_exponentielle ;
    @FXML private Button bt_e ;
    @FXML private Button bt_pi ;
    @FXML private Button bt_inverse ;
    @FXML private Button bt_oppose ;
    @FXML private Button bt_clear ;
    @FXML private Button bt_egal ;
    @FXML private Label affichage_resultat ;
    @FXML private Label affichage_calcul ;

    private String calcul = "";                                                     //Chaine affichée dans le textArea correspondant au calcul
    private MoCalculatriceScientifique modele = new MoCalculatriceScientifique();   //Instance du modèle de calcul simple

    /* **********
    *************
    ********** */

    /**
     * Méthodes invoquées lors des clics
     * L'attribution des écouteurs à un composant se fait dans : src/com.katoyikane/vue/modules/module2_scientifique.fxml
     */

    //Méthode invoquée lors d'un clic sur un chiffre ou un des opérateurs classiques
    @FXML private void btChiffreOperateurClic(ActionEvent event)
    {
        //On ajoute à la chaine le chiffre ou l'opérateur entré
        calcul += this.getBoutonTexte((Button)event.getSource());
        affichage_calcul.setText(calcul);
    }

    //Méthode invoquée lors d'un clic sur une parenthèse
    @FXML private void btParentheseClic(ActionEvent event)
    {
        //On ajoute à la parenthèse voulue par l'utilisateur
        calcul += this.getBoutonTexte((Button)event.getSource());
        affichage_calcul.setText(calcul);
    }

    //Méthode invoquée lors d'un clic sur un opérateur de trigonométrie
    @FXML private void btOperateurTrigoClic(ActionEvent event)
    {
        //On stocke l'intitulé du bouton cliqué dans une variable afin d'utiliser le switch en fonction des cas
        String operationTrigo = this.getBoutonTexte((Button)event.getSource());
        switch (operationTrigo)
        {
            case "cos" :
                calcul += "cos(" ;
                break;
            case "arccos" :
                calcul += "cos^1(" ;
                break;
            case "sin" :
                calcul += "sin(" ;
                break;
            case "arcsin" :
                calcul += "sin^-1(" ;
                break;
            case "tan" :
                calcul += "tan" ;
                break;
            case "arctan" :
                calcul += "tan^1" ;
                break;
        }
        affichage_calcul.setText(calcul);
    }

    //Méthode invoquée lors d'un clic sur un opérateur scientifique
    @FXML private void btOperateurScientifiqueClic(ActionEvent event)
    {
        //On stocke l'intitulé du bouton cliqué dans une variable afin d'utiliser le switch en fonction des cas
        String operationScientifique = this.getBoutonTexte((Button)event.getSource());
        switch (operationScientifique)
        {
            case "√(x)" :
                calcul += "√(" ;
                break ;
            case "^(x)" :
                calcul += "^(" ;
                break ;
            case "log10(x)" :
                calcul += "log10(" ;
                break ;
            case "e^(x)" :
                calcul += "e^(" ;
                break ;
        }
        affichage_calcul.setText(calcul);
    }

    //Méthode invoquée lors d'un clic sur une constante
    @FXML private void btConstanteClic(ActionEvent event)
    {
        //On ajoute à la chaine la constante selectionnée
        calcul += this.getBoutonTexte((Button)event.getSource());
        affichage_calcul.setText(calcul);
    }

    //Méthodée invoquée lors d'un clic sur le bouton Egale
    @FXML private void btEgalClic(ActionEvent event)
    {
        //On ajoute à la chaine le signe égal et on passe à la ligne suivante
        calcul += getBoutonTexte((Button)event.getSource()) ;
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
