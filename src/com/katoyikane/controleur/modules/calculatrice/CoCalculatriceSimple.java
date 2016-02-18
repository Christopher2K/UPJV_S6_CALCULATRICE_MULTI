package com.katoyikane.controleur.modules.calculatrice;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import com.katoyikane.modele.modules.calculatrice.MoCalculatriceSimple;

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
    private String nombre = "";                                         //Nombre entre deux opérateurs
    private MoCalculatriceSimple modele = new MoCalculatriceSimple();   //Instance du modèle de calcul simple

    /* **********
    *************
    ********** */

    /**
     * Méthodes invoquées lors des clics
     * L'attribution des écouteurs à un composant se fait dans : src/com.katoyikane/vue/modules/module1_simple.fxml
     */

    //Méthode invoquée lors d'un clic sur un bouton de chiffre
    @FXML private void btChiffreClic(ActionEvent event)
    {
        //On ajoute à la chaine le chiffre ou l'opérateur entré
        calcul += this.getBoutonTexte((Button)event.getSource());
        //On ajout à la chaine concernant le nombre en cours, le chiffre entré ;
        nombre += this.getBoutonTexte((Button)event.getSource());
        affichage_calcul.setText(calcul);
    }

    //Méthode invoquée lors d'un clic sur un bouton d'opérateur
    @FXML private void btOperateurClic(ActionEvent event)
    {
        //On envoie au modèle le nombre précédent
        modele.ajouterNombre(Double.parseDouble(nombre));

        //On ajoute à la chaine l'opérateur passé en argument
        calcul += this.getBoutonTexte((Button)event.getSource());
        affichage_calcul.setText(calcul);

        //On indique au modèle quel opérateur on a sélectionné
        modele.setOperateur(this.getBoutonTexte((Button)event.getSource()));

        //On indique au modèle qu'on a sélectionné un opérateur
        modele.setOperateurIsSelected(true);

        //On demande l'affichage du résultat courant
        affichage_resultat.setText(modele.returnResultat());

        //On remet le nombre à zéro
        nombre = "";
    }

    //Méthodée invoquée lors d'un clic sur le bouton Egal
    @FXML private void btEgalClic(ActionEvent event)
    {
        //On ajoute à la chaine le signe égal et on passe à la ligne suivante
        calcul = calcul + getBoutonTexte((Button)event.getSource()) ;
        affichage_calcul.setText(calcul);

        //On envoie au modèle le nombre précédent
        modele.ajouterNombre(Double.parseDouble(nombre));

        //On indique au modèle que l'on a pas sélectionné un opérateur
        modele.setOperateurIsSelected(false);

        //On demande l'afichage du résultat courant
        affichage_resultat.setText(modele.returnResultat());

        //On remet le nombre à zéro
        nombre = "";

        //On remet calcul a zéro pour le calcul suivant
        calcul = "" ;
    }

    //Méthode invoquée lors d'un clic sur le bouton Clear
    @FXML private void btClearClic(ActionEvent event)
    {
        //Remise à zéro de calcul
        calcul = "" ;
        //On efface les zone d'affichage
        affichage_calcul.setText(calcul) ;
        affichage_resultat.setText("0.O") ;
        //On réinitialise le modèle
        modele = new MoCalculatriceSimple() ;
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
