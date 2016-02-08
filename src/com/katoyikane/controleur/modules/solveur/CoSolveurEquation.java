package com.katoyikane.controleur.modules.solveur;

import com.katoyikane.vue.popup.PopUpEqSecondDegre;
import com.katoyikane.vue.popup.PopUpEqSimple;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * Created by christopher on 07/02/16.
 */
/*
Controleur du quatrième onglet de l'application : Solveur d'équations
 */
public class CoSolveurEquation
{
    /**
     * Attributs de classe
     */
    /*
    Liste des composants graphiques présent dans cet onglet
    En cas de modification, consulter : src/com.katoyikane/vue/modules/module4_solveur.fxml
     */
    @FXML private Button bt_verifier1 ;
    @FXML private Button bt_verifier2 ;
    @FXML private Button bt_reset1 ;
    @FXML private Button bt_reset2 ;
    @FXML private Button bt_resoudre1 ;
    @FXML private Button bt_resoudre2 ;
    @FXML private TextField saisie_eq1 ;
    @FXML private TextField saisie_eq2a ;
    @FXML private TextField saisie_eq2b ;
    @FXML private TextField saisie_eq2c ;
    @FXML private ImageView vue_latex1 ;
    @FXML private ImageView vue_latex2 ;

    /* **********
    *************
    ********** */


    /**
     * Méthodes invoquées lors des clics
     * L'attribution des écouteurs à un composant se fait dans : src/com.katoyikane/vue/modules/module4_solveur.fxml
     */
    //Méthode invoquée lors du clic sur le bouton de vérification des équations simples
    @FXML private void btVerifierEq1Clic(ActionEvent event)
    {

    }

    //Méthode invoquée lors du clic sur le bouton de vérification des équation du secon degré
    @FXML private void btVerifierEq2Clic(ActionEvent event)
    {

    }

    //Méthode invoquée lors d'un clic sur un des deux boutons de réinitialisation
    @FXML private void btResetClic(ActionEvent event)
    {

    }

    //Méthode invoquée lors d'un clic sur un des deux bouton de résolution
    @FXML private void btResoudreClic(ActionEvent event) throws Exception
    {
        if(event.getSource() == (Button) bt_resoudre1)
        {
            PopUpEqSimple.afficher();
        }
        else
        {
            PopUpEqSecondDegre.afficher();
        }
    }

    /* **********
    *************
    ********** */

    /**
     * Méthodes propres au controleur
     */


}
