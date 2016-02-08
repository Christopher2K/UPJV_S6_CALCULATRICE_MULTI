package com.katoyikane.controleur.modules.formel;

import com.katoyikane.modele.modules.formel.MoCalculDerivee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * Created by christopher on 08/02/16.
 */
/*
Controleur du cinquième onglet de l'application : Calcul de fonction dérivée
 */
public class CoCalculDerivee
{
    /**
     * Attributs de classe
     */
    /*
    Liste des composants graphiques présent dans cet onglet
    En cas de modification, consulter : src/com.katoyikane/vue/modules/module5_derivee.fxml
     */
    @FXML private Button bt_verfier ;
    @FXML private Button bt_reset ;
    @FXML private Button bt_afficher ;
    @FXML private TextField saisie_expression ;
    @FXML private ImageView vue_latex ;
    @FXML private ImageView vue_latex_derivee ;

    private MoCalculDerivee modele = new MoCalculDerivee() ;            //Instance du modèle de calcul de dérivée

    /* **********
    *************
    ********** */

    /**
     * Méthodes invoquées lors des clics
     * L'attribution des écouteurs à un composant se fait dans : src/com.katoyikane/vue/modules/module5_derivee.fxml
     */
    //Méthode invoquuée lors du clic sur le bouton de vérification
    @FXML private void btVerifierClic(ActionEvent event)
    {

    }

    //Méthodée invoquée lors du clic sur le bouton de réinitialisation
    @FXML private void btResetClic(ActionEvent event)
    {
        //Réinitialisation du modèle
        modele = new MoCalculDerivee();
        //Réinitilisation des champs et des vues
        vue_latex.setImage(null);
        vue_latex_derivee.setImage(null);
        saisie_expression.setText("");
    }

    //Méthode invoquée lors du clic sur le bouton d'affichage de la dérivée
    @FXML private void btAfficherClic(ActionEvent event)
    {

    }

    /* **********
    *************
    ********** */

    /**
     * Méthodes propres au controleur
     */
}

