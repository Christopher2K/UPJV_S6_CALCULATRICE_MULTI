package com.katoyikane.controleur.modules.formel;

import com.katoyikane.modele.modules.formel.MoFactorisation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * Created by christopher on 08/02/16.
 */
/*
Controleur du sixième onglet de l'application : Calcul de l'expression factorisée
 */
public class CoFactorisation
{
    /**
     * Attributs de classe
     */
    /*
    Liste des composants graphiques présent dans cet onglet
    En cas de modification, consulter : src/com.katoyikane/vue/modules/module5_factorisation.fxml
     */
    @FXML private Button bt_verfier ;
    @FXML private Button bt_reset ;
    @FXML private Button bt_afficher ;
    @FXML private TextField saisie_expression ;
    @FXML private ImageView vue_latex ;
    @FXML private ImageView vue_latex_factorisation ;

    private MoFactorisation modele = new MoFactorisation() ;            //Instance du modèle de calcul de dérivée

    /* **********
    *************
    ********** */

    /**
     * Méthodes invoquées lors des clics
     * L'attribution des écouteurs à un composant se fait dans : src/com.katoyikane/vue/modules/module5_factorisation.fxml
     */
    //Méthode invoquuée lors du clic sur le bouton de vérification
    @FXML private void btVerifierClic(ActionEvent event)
    {

    }

    //Méthodée invoquée lors du clic sur le bouton de réinitialisation
    @FXML private void btResetClic(ActionEvent event)
    {
        //Réinitialisation du modèle
        modele = new MoFactorisation();
        //Réinitilisation des champs et des vues
        vue_latex.setImage(null);
        vue_latex_factorisation.setImage(null);
        saisie_expression.setText("");
    }

    //Méthode invoquée lors du clic sur le bouton d'affichage de l'expression factorisée
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
