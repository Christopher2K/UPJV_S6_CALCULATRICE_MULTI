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
    @FXML private Button bt_ln ;
    @FXML private Button bt_exponentielle ;
    @FXML private Button bt_pi ;
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

    //Méthode invoquée lors d'un clic sur un chiffre ou un des opérateurs classiques, une parenthèse, une constante
    @FXML private void btClassiqueClic(ActionEvent event)
    {
        //On ajoute à la chaine le chiffre ou l'opérateur entré
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
                calcul += "arccos(" ;
                break;
            case "sin" :
                calcul += "sin(" ;
                break;
            case "arcsin" :
                calcul += "arcsin(" ;
                break;
            case "tan" :
                calcul += "tan(" ;
                break;
            case "arctan" :
                calcul += "arctan(" ;
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
                calcul += "sqrt(" ;
                break ;
            case "^(x)" :
                calcul += "^(" ;
                break ;
            case "ln(x)" :
                calcul += "ln(" ;
                break ;
            case "e^(x)" :
                calcul += "e^(" ;
                break ;
            case "π" :
                calcul += "pi" ;
            case "e" :
                calcul += "e" ;
        }

        //On affiche de nouveau le calcul
        affichage_calcul.setText(calcul);
    }

    //Méthodée invoquée lors d'un clic sur le bouton Egale
    @FXML private void btEgalClic(ActionEvent event)
    {
        //On envoit au modèle le calcul courant
        modele.setCalcul(calcul) ;
        //On affichage le résultat
        affichage_resultat.setText(modele.getResultat());

        //On ajoute à la chaine le signe égal
        affichage_calcul.setText(calcul + "=");

        //On indique au modèle que le bouton égal a été cliqué au moins une fois
        modele.setEgalIsClicked(true);

        //On remet la variable calcul du modèle a zéro
        this.modele.reset();
    }

    //Méthode invoquée lors d'un clic sur le bouton opposé
    @FXML private void btOpposeClic(ActionEvent event)
    {
        //On envoit le calcul au modèle
        modele.setCalcul(calcul);

        //On ajoute à la chaine l'expression de l'opposé et on l'affiche
        calcul = "-1*(" + calcul + ")" ;
        affichage_calcul.setText(calcul) ;

        //On affiche le résultat
        affichage_resultat.setText(modele.getOppose());

        //On met à jour la variable sensé contenir le calcul en cours
        calcul = affichage_calcul.getText();

        //On remet la variable calcul du modèle a zéro
        this.modele.reset();
    }

    //Méthode invoquée lors d'un clic sur le bouton d'inverse
    @FXML private void btInverseClic(ActionEvent event)
    {
        //On envoit le calcul au modèle
        modele.setCalcul(calcul);

        //On ajoute à la chaine l'expression de l'inverse et on l'affiche
        calcul = "1/(" + calcul + ")" ;
        affichage_calcul.setText(calcul) ;

        //On affiche le résultat
        affichage_resultat.setText(modele.getInverse());

        //On met à jour la variable sensé contenir le calcul en cours
        calcul = affichage_calcul.getText();

        //On remet la variable calcul du modèle a zéro
        this.modele.reset();
    }

    //Méthode invoquée lors d'un clic sur le bouton Clear
    @FXML private void btClearClic(ActionEvent event)
    {
        //On remet le string calcul a zéro pour le calcul suivant ainsi que le modèle
        calcul = "" ;
        this.modele.reset();

        //On efface les zone d'affichage
        affichage_calcul.setText(calcul);
        affichage_resultat.setText("0.0");

        //On réinitialise le bouton égal
        modele.setEgalIsClicked(false);
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
